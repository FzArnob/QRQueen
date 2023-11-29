package com.microsoft.appcenter.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Extensions implements Model {
    private static final String APP = "app";
    private static final String DEVICE = "device";
    private static final String LOC = "loc";
    private static final String METADATA = "metadata";
    private static final String NET = "net";
    private static final String OS = "os";
    private static final String PROTOCOL = "protocol";
    private static final String SDK = "sdk";
    private static final String USER = "user";
    private AppExtension app;
    private DeviceExtension device;
    private LocExtension loc;
    private MetadataExtension metadata;

    /* renamed from: net  reason: collision with root package name */
    private NetExtension f345net;
    private OsExtension os;
    private ProtocolExtension protocol;
    private SdkExtension sdk;
    private UserExtension user;

    public MetadataExtension getMetadata() {
        return this.metadata;
    }

    public void setMetadata(MetadataExtension metadataExtension) {
        this.metadata = metadataExtension;
    }

    public ProtocolExtension getProtocol() {
        return this.protocol;
    }

    public void setProtocol(ProtocolExtension protocolExtension) {
        this.protocol = protocolExtension;
    }

    public UserExtension getUser() {
        return this.user;
    }

    public void setUser(UserExtension userExtension) {
        this.user = userExtension;
    }

    public DeviceExtension getDevice() {
        return this.device;
    }

    public void setDevice(DeviceExtension deviceExtension) {
        this.device = deviceExtension;
    }

    public OsExtension getOs() {
        return this.os;
    }

    public void setOs(OsExtension osExtension) {
        this.os = osExtension;
    }

    public AppExtension getApp() {
        return this.app;
    }

    public void setApp(AppExtension appExtension) {
        this.app = appExtension;
    }

    public NetExtension getNet() {
        return this.f345net;
    }

    public void setNet(NetExtension netExtension) {
        this.f345net = netExtension;
    }

    public SdkExtension getSdk() {
        return this.sdk;
    }

    public void setSdk(SdkExtension sdkExtension) {
        this.sdk = sdkExtension;
    }

    public LocExtension getLoc() {
        return this.loc;
    }

    public void setLoc(LocExtension locExtension) {
        this.loc = locExtension;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("metadata")) {
            MetadataExtension metadataExtension = new MetadataExtension();
            metadataExtension.read(jSONObject.getJSONObject("metadata"));
            setMetadata(metadataExtension);
        }
        if (jSONObject.has(PROTOCOL)) {
            ProtocolExtension protocolExtension = new ProtocolExtension();
            protocolExtension.read(jSONObject.getJSONObject(PROTOCOL));
            setProtocol(protocolExtension);
        }
        if (jSONObject.has(USER)) {
            UserExtension userExtension = new UserExtension();
            userExtension.read(jSONObject.getJSONObject(USER));
            setUser(userExtension);
        }
        if (jSONObject.has(DEVICE)) {
            DeviceExtension deviceExtension = new DeviceExtension();
            deviceExtension.read(jSONObject.getJSONObject(DEVICE));
            setDevice(deviceExtension);
        }
        if (jSONObject.has(OS)) {
            OsExtension osExtension = new OsExtension();
            osExtension.read(jSONObject.getJSONObject(OS));
            setOs(osExtension);
        }
        if (jSONObject.has(APP)) {
            AppExtension appExtension = new AppExtension();
            appExtension.read(jSONObject.getJSONObject(APP));
            setApp(appExtension);
        }
        if (jSONObject.has(NET)) {
            NetExtension netExtension = new NetExtension();
            netExtension.read(jSONObject.getJSONObject(NET));
            setNet(netExtension);
        }
        if (jSONObject.has(SDK)) {
            SdkExtension sdkExtension = new SdkExtension();
            sdkExtension.read(jSONObject.getJSONObject(SDK));
            setSdk(sdkExtension);
        }
        if (jSONObject.has(LOC)) {
            LocExtension locExtension = new LocExtension();
            locExtension.read(jSONObject.getJSONObject(LOC));
            setLoc(locExtension);
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        if (getMetadata() != null) {
            jSONStringer.key("metadata").object();
            getMetadata().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getProtocol() != null) {
            jSONStringer.key(PROTOCOL).object();
            getProtocol().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getUser() != null) {
            jSONStringer.key(USER).object();
            getUser().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getDevice() != null) {
            jSONStringer.key(DEVICE).object();
            getDevice().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getOs() != null) {
            jSONStringer.key(OS).object();
            getOs().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getApp() != null) {
            jSONStringer.key(APP).object();
            getApp().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getNet() != null) {
            jSONStringer.key(NET).object();
            getNet().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getSdk() != null) {
            jSONStringer.key(SDK).object();
            getSdk().write(jSONStringer);
            jSONStringer.endObject();
        }
        if (getLoc() != null) {
            jSONStringer.key(LOC).object();
            getLoc().write(jSONStringer);
            jSONStringer.endObject();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Extensions extensions = (Extensions) obj;
        MetadataExtension metadataExtension = this.metadata;
        if (metadataExtension == null ? extensions.metadata != null : !metadataExtension.equals(extensions.metadata)) {
            return false;
        }
        ProtocolExtension protocolExtension = this.protocol;
        if (protocolExtension == null ? extensions.protocol != null : !protocolExtension.equals(extensions.protocol)) {
            return false;
        }
        UserExtension userExtension = this.user;
        if (userExtension == null ? extensions.user != null : !userExtension.equals(extensions.user)) {
            return false;
        }
        DeviceExtension deviceExtension = this.device;
        if (deviceExtension == null ? extensions.device != null : !deviceExtension.equals(extensions.device)) {
            return false;
        }
        OsExtension osExtension = this.os;
        if (osExtension == null ? extensions.os != null : !osExtension.equals(extensions.os)) {
            return false;
        }
        AppExtension appExtension = this.app;
        if (appExtension == null ? extensions.app != null : !appExtension.equals(extensions.app)) {
            return false;
        }
        NetExtension netExtension = this.f345net;
        if (netExtension == null ? extensions.f345net != null : !netExtension.equals(extensions.f345net)) {
            return false;
        }
        SdkExtension sdkExtension = this.sdk;
        if (sdkExtension == null ? extensions.sdk != null : !sdkExtension.equals(extensions.sdk)) {
            return false;
        }
        LocExtension locExtension = this.loc;
        LocExtension locExtension2 = extensions.loc;
        if (locExtension != null) {
            return locExtension.equals(locExtension2);
        }
        if (locExtension2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        MetadataExtension metadataExtension = this.metadata;
        int i = 0;
        int hashCode = (metadataExtension != null ? metadataExtension.hashCode() : 0) * 31;
        ProtocolExtension protocolExtension = this.protocol;
        int hashCode2 = (hashCode + (protocolExtension != null ? protocolExtension.hashCode() : 0)) * 31;
        UserExtension userExtension = this.user;
        int hashCode3 = (hashCode2 + (userExtension != null ? userExtension.hashCode() : 0)) * 31;
        DeviceExtension deviceExtension = this.device;
        int hashCode4 = (hashCode3 + (deviceExtension != null ? deviceExtension.hashCode() : 0)) * 31;
        OsExtension osExtension = this.os;
        int hashCode5 = (hashCode4 + (osExtension != null ? osExtension.hashCode() : 0)) * 31;
        AppExtension appExtension = this.app;
        int hashCode6 = (hashCode5 + (appExtension != null ? appExtension.hashCode() : 0)) * 31;
        NetExtension netExtension = this.f345net;
        int hashCode7 = (hashCode6 + (netExtension != null ? netExtension.hashCode() : 0)) * 31;
        SdkExtension sdkExtension = this.sdk;
        int hashCode8 = (hashCode7 + (sdkExtension != null ? sdkExtension.hashCode() : 0)) * 31;
        LocExtension locExtension = this.loc;
        if (locExtension != null) {
            i = locExtension.hashCode();
        }
        return hashCode8 + i;
    }
}
