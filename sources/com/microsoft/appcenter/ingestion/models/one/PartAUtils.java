package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Device;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.utils.context.UserIdContext;
import java.util.Locale;
import java.util.regex.Pattern;
import org.slf4j.Marker;

public class PartAUtils {
    private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z0-9]((\\.(?!(\\.|$)))|[_a-zA-Z0-9]){3,99}$");

    public static String getTargetKey(String str) {
        return str.split("-")[0];
    }

    public static void setName(CommonSchemaLog commonSchemaLog, String str) throws IllegalArgumentException {
        if (str != null) {
            Pattern pattern = NAME_REGEX;
            if (pattern.matcher(str).matches()) {
                commonSchemaLog.setName(str);
                return;
            }
            throw new IllegalArgumentException("Name must match '" + pattern + "' but was '" + str + "'.");
        }
        throw new IllegalArgumentException("Name cannot be null.");
    }

    public static void addPartAFromLog(Log log, CommonSchemaLog commonSchemaLog, String str) {
        Device device = log.getDevice();
        commonSchemaLog.setVer("3.0");
        commonSchemaLog.setTimestamp(log.getTimestamp());
        commonSchemaLog.setIKey("o:" + getTargetKey(str));
        commonSchemaLog.addTransmissionTarget(str);
        if (commonSchemaLog.getExt() == null) {
            commonSchemaLog.setExt(new Extensions());
        }
        commonSchemaLog.getExt().setProtocol(new ProtocolExtension());
        commonSchemaLog.getExt().getProtocol().setDevModel(device.getModel());
        commonSchemaLog.getExt().getProtocol().setDevMake(device.getOemName());
        commonSchemaLog.getExt().setUser(new UserExtension());
        commonSchemaLog.getExt().getUser().setLocalId(UserIdContext.getPrefixedUserId(log.getUserId()));
        String str2 = "-";
        commonSchemaLog.getExt().getUser().setLocale(device.getLocale().replace("_", str2));
        commonSchemaLog.getExt().setOs(new OsExtension());
        commonSchemaLog.getExt().getOs().setName(device.getOsName());
        OsExtension os = commonSchemaLog.getExt().getOs();
        os.setVer(device.getOsVersion() + str2 + device.getOsBuild() + str2 + device.getOsApiLevel());
        commonSchemaLog.getExt().setApp(new AppExtension());
        commonSchemaLog.getExt().getApp().setVer(device.getAppVersion());
        AppExtension app = commonSchemaLog.getExt().getApp();
        app.setId("a:" + device.getAppNamespace());
        commonSchemaLog.getExt().setNet(new NetExtension());
        commonSchemaLog.getExt().getNet().setProvider(device.getCarrierName());
        commonSchemaLog.getExt().setSdk(new SdkExtension());
        SdkExtension sdk = commonSchemaLog.getExt().getSdk();
        sdk.setLibVer(device.getSdkName() + str2 + device.getSdkVersion());
        commonSchemaLog.getExt().setLoc(new LocExtension());
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        if (device.getTimeZoneOffset().intValue() >= 0) {
            str2 = Marker.ANY_NON_NULL_MARKER;
        }
        objArr[0] = str2;
        objArr[1] = Integer.valueOf(Math.abs(device.getTimeZoneOffset().intValue() / 60));
        objArr[2] = Integer.valueOf(Math.abs(device.getTimeZoneOffset().intValue() % 60));
        commonSchemaLog.getExt().getLoc().setTz(String.format(locale, "%s%02d:%02d", objArr));
        commonSchemaLog.getExt().setDevice(new DeviceExtension());
    }
}
