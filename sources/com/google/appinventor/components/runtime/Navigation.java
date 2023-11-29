package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.TransportMethod;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Navigation", iconName = "images/navigation.png", nonVisible = true, version = 2)
@UsesLibraries({"osmdroid.jar"})
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class Navigation extends AndroidNonvisibleComponent implements Component {
    public static final String OPEN_ROUTE_SERVICE_URL = "https://api.openrouteservice.org/v2/directions/";
    private String IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = OPEN_ROUTE_SERVICE_URL;
    private GeoPoint hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new GeoPoint(0.0d, 0.0d);
    private TransportMethod hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = TransportMethod.Foot;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public YailDictionary f239hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = YailDictionary.makeDictionary();
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = "";
    private String vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = "en";
    private GeoPoint vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new GeoPoint(0.0d, 0.0d);

    public Navigation(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleFunction(description = "Request directions from the routing service.")
    public void RequestDirections() {
        if (this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp.equals("")) {
            this.form.dispatchErrorOccurredEvent(this, Constants.AUTHORIZATION_HEADER, ErrorMessages.ERROR_INVALID_API_KEY, new Object[0]);
            return;
        }
        final GeoPoint geoPoint = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
        final GeoPoint geoPoint2 = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
        final TransportMethod transportMethod = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    Navigation.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Navigation.this, geoPoint, geoPoint2, transportMethod);
                } catch (IOException unused) {
                    Navigation.this.form.dispatchErrorOccurredEvent(Navigation.this, "RequestDirections", 0, new Object[0]);
                } catch (JSONException unused2) {
                    Navigation.this.form.dispatchErrorOccurredEvent(Navigation.this, "RequestDirections", 0, new Object[0]);
                }
            }
        });
    }

    @SimpleProperty(userVisible = false)
    public void ServiceURL(String str) {
        this.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 = str;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "API Key for Open Route Service.")
    public void ApiKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "latitude")
    @SimpleProperty
    public void StartLatitude(double d) {
        if (GeometryUtil.isValidLatitude(d)) {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setLatitude(d);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "StartLatitude", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The latitude of the start location.")
    public double StartLatitude() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getLatitude();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "longitude")
    @SimpleProperty
    public void StartLongitude(double d) {
        if (GeometryUtil.isValidLongitude(d)) {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setLongitude(d);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "StartLongitude", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The longitude of the start location.")
    public double StartLongitude() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getLongitude();
    }

    @SimpleProperty(description = "Set the start location.")
    public void StartLocation(MapFactory.MapFeature mapFeature) {
        GeoPoint centroid = mapFeature.getCentroid();
        double latitude = centroid.getLatitude();
        double longitude = centroid.getLongitude();
        if (!GeometryUtil.isValidLatitude(latitude)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetStartLocation", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(latitude));
        } else if (!GeometryUtil.isValidLongitude(longitude)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetStartLocation", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(longitude));
        } else {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setCoords(latitude, longitude);
        }
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "latitude")
    @SimpleProperty
    public void EndLatitude(double d) {
        if (GeometryUtil.isValidLatitude(d)) {
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.setLatitude(d);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "EndLatitude", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The latitude of the end location.")
    public double EndLatitude() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getLatitude();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "longitude")
    @SimpleProperty
    public void EndLongitude(double d) {
        if (GeometryUtil.isValidLongitude(d)) {
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.setLongitude(d);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "EndLongitude", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The longitude of the end location.")
    public double EndLongitude() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getLongitude();
    }

    @Options(TransportMethod.class)
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String TransportationMethod() {
        return TransportationMethodAbstract().toUnderlyingValue();
    }

    public TransportMethod TransportationMethodAbstract() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void TransportationMethodAbstract(TransportMethod transportMethod) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = transportMethod;
    }

    @DesignerProperty(defaultValue = "foot-walking", editorType = "navigation_method")
    @SimpleProperty(description = "The transportation method used for determining the route.")
    public void TransportationMethod(@Options(TransportMethod.class) String str) {
        TransportMethod fromUnderlyingValue = TransportMethod.fromUnderlyingValue(str);
        if (fromUnderlyingValue != null) {
            TransportationMethodAbstract(fromUnderlyingValue);
        }
    }

    @SimpleProperty(description = "Set the end location.")
    public void EndLocation(MapFactory.MapFeature mapFeature) {
        GeoPoint centroid = mapFeature.getCentroid();
        double latitude = centroid.getLatitude();
        double longitude = centroid.getLongitude();
        if (!GeometryUtil.isValidLatitude(latitude)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetEndLocation", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(latitude));
        } else if (!GeometryUtil.isValidLongitude(longitude)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetEndLocation", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(longitude));
        } else {
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.setCoords(latitude, longitude);
        }
    }

    @DesignerProperty(defaultValue = "en")
    @SimpleProperty(description = "The language to use for textual directions.")
    public void Language(String str) {
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = str;
    }

    @SimpleProperty
    public String Language() {
        return this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ;
    }

    @SimpleProperty(description = "Content of the last response as a dictionary.")
    public YailDictionary ResponseContent() {
        return this.f239hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Event triggered when the Openrouteservice returns the directions.")
    public void GotDirections(YailList yailList, YailList yailList2, double d, double d2) {
        Log.d("Navigation", "GotDirections");
        EventDispatcher.dispatchEvent(this, "GotDirections", yailList, yailList2, Double.valueOf(d), Double.valueOf(d2));
    }

    private static String getResponseContent(HttpURLConnection httpURLConnection) throws IOException {
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (contentEncoding == null) {
            contentEncoding = "UTF-8";
        }
        Log.d("Navigation", Integer.toString(httpURLConnection.getResponseCode()));
        InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), contentEncoding);
        try {
            int contentLength = httpURLConnection.getContentLength();
            StringBuilder sb = contentLength != -1 ? new StringBuilder(contentLength) : new StringBuilder();
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } finally {
            inputStreamReader.close();
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Navigation navigation, GeoPoint geoPoint, GeoPoint geoPoint2, TransportMethod transportMethod) throws IOException, JSONException {
        BufferedOutputStream bufferedOutputStream;
        Navigation navigation2 = navigation;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(navigation2.IQizCoownq3QMuqjiN3SEtpvqAzYOMGAuQhX0ytYpmBBjgggyq4HRoSxolTbNj90 + transportMethod.toUnderlyingValue() + "/geojson/").openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json; charset=UTF-8");
        httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_POST);
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, navigation2.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp);
        try {
            StringBuilder sb = new StringBuilder("{\"coordinates\": ");
            int[] iArr = new int[2];
            iArr[1] = 2;
            iArr[0] = 2;
            Double[][] dArr = (Double[][]) Array.newInstance(Double.class, iArr);
            dArr[0][0] = Double.valueOf(geoPoint.getLongitude());
            dArr[0][1] = Double.valueOf(geoPoint.getLatitude());
            dArr[1][0] = Double.valueOf(geoPoint2.getLongitude());
            dArr[1][1] = Double.valueOf(geoPoint2.getLatitude());
            sb.append(JsonUtil.getJsonRepresentation(dArr));
            sb.append(", \"language\": \"");
            sb.append(navigation2.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ);
            sb.append("\"}");
            byte[] bytes = sb.toString().getBytes("UTF-8");
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(bytes, 0, bytes.length);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (httpURLConnection.getResponseCode() != 200) {
                navigation2.form.dispatchErrorOccurredEvent(navigation2, "RequestDirections", ErrorMessages.ERROR_ROUTING_SERVICE_ERROR, Integer.valueOf(httpURLConnection.getResponseCode()), httpURLConnection.getResponseMessage());
                httpURLConnection.disconnect();
                return;
            }
            String responseContent = getResponseContent(httpURLConnection);
            Log.d("Navigation", responseContent);
            final YailDictionary yailDictionary = (YailDictionary) JsonUtil.getObjectFromJson(responseContent, true);
            YailList yailList = (YailList) yailDictionary.get("features");
            if (yailList.size() > 0) {
                YailDictionary yailDictionary2 = (YailDictionary) yailList.getObject(0);
                YailDictionary yailDictionary3 = (YailDictionary) yailDictionary2.getObjectAtKeyPath(Arrays.asList(new String[]{"properties", "summary"}));
                final double doubleValue = ((Double) yailDictionary3.get("distance")).doubleValue();
                final double doubleValue2 = ((Double) yailDictionary3.get("duration")).doubleValue();
                final YailList makeList = YailList.makeList((List) YailDictionary.walkKeyPath(yailDictionary2, Arrays.asList(new Object[]{"properties", "segments", YailDictionary.ALL, "steps", YailDictionary.ALL, "instruction"})));
                final YailList swapCoordinates = GeoJSONUtil.swapCoordinates((YailList) yailDictionary2.getObjectAtKeyPath(Arrays.asList(new String[]{"geometry", "coordinates"})));
                navigation2.form.runOnUiThread(new Runnable() {
                    public final void run() {
                        YailDictionary unused = Navigation.this.f239hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = yailDictionary;
                        Navigation.this.GotDirections(makeList, swapCoordinates, doubleValue, doubleValue2);
                    }
                });
            } else {
                navigation2.form.dispatchErrorOccurredEvent(navigation2, "RequestDirections", ErrorMessages.ERROR_NO_ROUTE_FOUND, new Object[0]);
            }
            httpURLConnection.disconnect();
        } catch (Exception e) {
            try {
                navigation2.form.dispatchErrorOccurredEvent(navigation2, "RequestDirections", ErrorMessages.ERROR_UNABLE_TO_REQUEST_DIRECTIONS, e.getMessage());
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            bufferedOutputStream.close();
            throw th;
        }
    }
}
