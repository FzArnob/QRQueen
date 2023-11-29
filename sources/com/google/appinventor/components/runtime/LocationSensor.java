package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component providing location information, including longitude, latitude, altitude (if supported by the device), speed (if supported by the device), and address.  This can also perform \"geocoding\", converting a given address (not necessarily the current one) to a latitude (with the <code>LatitudeFromAddress</code> method) and a longitude (with the <code>LongitudeFromAddress</code> method).</p>\n<p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have location sensing enabled through wireless networks or GPS satellites (if outdoors).</p>\nLocation information might not be immediately available when an app starts.  You'll have to wait a short time for a location provider to be found and used, or wait for the OnLocationChanged event", iconName = "images/locationSensor.png", nonVisible = true, version = 3)
@SimpleObject
@UsesPermissions({"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_MOCK_LOCATION", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"})
public class LocationSensor extends AndroidNonvisibleComponent implements Component, Deleteable, OnResumeListener, OnStopListener {
    /* access modifiers changed from: private */
    public static final String LOG_TAG = "LocationSensor";
    public static final int UNKNOWN_VALUE = 0;
    /* access modifiers changed from: private */
    public double B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private List<String> F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    /* access modifiers changed from: private */
    public double LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;

    /* renamed from: LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn  reason: collision with other field name */
    private boolean f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private boolean XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u;
    private String ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ;
    private final Handler androidUIHandler;
    private boolean bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV;
    private boolean enabled;
    private int f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z;
    private Form form;
    private final Handler handler;
    private boolean havePermission;
    private boolean hqHtvKVLFGgMVMSKZM3hViIxQKdIh8YXfZOV4lmVWV1e2Mndp07BOheWTgPW1jrN;
    /* access modifiers changed from: private */
    public double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Criteria f195hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Geocoder f196hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public Location f197hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final LocationManager f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private LocationProvider f199hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private a f200hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean initialized;
    private int jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO;
    /* access modifiers changed from: private */
    public float speed;
    private final Set<LocationSensorListener> wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public interface LocationSensorListener extends LocationListener {
        void onDistanceIntervalChanged(int i);

        void onTimeIntervalChanged(int i);

        void setSource(LocationSensor locationSensor);
    }

    class a implements LocationListener {
        private a() {
        }

        /* synthetic */ a(LocationSensor locationSensor, byte b) {
            this();
        }

        public final void onLocationChanged(Location location) {
            Location unused = LocationSensor.this.f197hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = location;
            double unused2 = LocationSensor.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = location.getLongitude();
            double unused3 = LocationSensor.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = location.getLatitude();
            float unused4 = LocationSensor.this.speed = location.getSpeed();
            if (location.hasAltitude()) {
                LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this);
                double unused5 = LocationSensor.this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = location.getAltitude();
            }
            if (LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this) != 0.0d || LocationSensor.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(LocationSensor.this) != 0.0d) {
                LocationSensor.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(LocationSensor.this);
                final double B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = LocationSensor.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(LocationSensor.this);
                final double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this);
                final double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = LocationSensor.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(LocationSensor.this);
                final float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this);
                final Location location2 = location;
                LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this).post(new Runnable() {
                    public final void run() {
                        LocationSensor.this.LocationChanged(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3);
                        for (LocationSensorListener onLocationChanged : LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this)) {
                            onLocationChanged.onLocationChanged(location2);
                        }
                    }
                });
            }
        }

        public final void onProviderDisabled(String str) {
            LocationSensor.this.StatusChanged(str, "Disabled");
            LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this);
            if (LocationSensor.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(LocationSensor.this)) {
                LocationSensor.this.RefreshProvider("onProviderDisabled");
            }
        }

        public final void onProviderEnabled(String str) {
            LocationSensor.this.StatusChanged(str, "Enabled");
            LocationSensor.this.RefreshProvider("onProviderEnabled");
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                LocationSensor.this.StatusChanged(str, "OUT_OF_SERVICE");
                if (str.equals(LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this))) {
                    LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this);
                    LocationSensor.this.RefreshProvider("onStatusChanged");
                }
            } else if (i == 1) {
                LocationSensor.this.StatusChanged(str, "TEMPORARILY_UNAVAILABLE");
            } else if (i == 2) {
                LocationSensor.this.StatusChanged(str, "AVAILABLE");
                if (!str.equals(LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this)) && !LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this).contains(str)) {
                    LocationSensor.this.RefreshProvider("onStatusChanged");
                }
            }
        }
    }

    public LocationSensor(ComponentContainer componentContainer) {
        this(componentContainer, true);
    }

    public LocationSensor(ComponentContainer componentContainer, boolean z) {
        super(componentContainer.$form());
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new HashSet();
        this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV = false;
        this.initialized = false;
        this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = 0.0d;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = 0.0d;
        this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = 0.0d;
        this.speed = 0.0f;
        this.hqHtvKVLFGgMVMSKZM3hViIxQKdIh8YXfZOV4lmVWV1e2Mndp07BOheWTgPW1jrN = false;
        this.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u = false;
        this.androidUIHandler = new Handler();
        this.enabled = true;
        this.havePermission = false;
        this.form = componentContainer.$form();
        this.enabled = z;
        this.handler = new Handler();
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = 60000;
        this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = 5;
        Activity $context = componentContainer.$context();
        this.f196hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Geocoder($context);
        this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (LocationManager) $context.getSystemService("location");
        this.f195hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Criteria();
        this.f200hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new a(this, (byte) 0);
        this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = new ArrayList();
        Enabled(z);
    }

    public void Initialize() {
        this.initialized = true;
        Enabled(this.enabled);
    }

    @SimpleEvent
    public void LocationChanged(double d, double d2, double d3, float f) {
        EventDispatcher.dispatchEvent(this, "LocationChanged", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Float.valueOf(f));
    }

    @SimpleEvent
    public void StatusChanged(String str, String str2) {
        if (this.enabled) {
            EventDispatcher.dispatchEvent(this, "StatusChanged", str, str2);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ProviderName() {
        String str = this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ;
        return str == null ? "NO PROVIDER" : str;
    }

    @SimpleProperty
    public void ProviderName(String str) {
        this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = str;
        if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str) || !hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str)) {
            RefreshProvider("ProviderName");
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean ProviderLocked() {
        return this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV;
    }

    @SimpleProperty
    public void ProviderLocked(boolean z) {
        this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV = z;
    }

    @DesignerProperty(defaultValue = "60000", editorType = "sensor_time_interval")
    @SimpleProperty
    public void TimeInterval(int i) {
        if (i >= 0 && i <= 1000000) {
            this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = i;
            if (this.enabled) {
                RefreshProvider("TimeInterval");
            }
            for (LocationSensorListener onTimeIntervalChanged : this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                onTimeIntervalChanged.onTimeIntervalChanged(this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.")
    public int TimeInterval() {
        return this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z;
    }

    @DesignerProperty(defaultValue = "5", editorType = "sensor_dist_interval")
    @SimpleProperty
    public void DistanceInterval(int i) {
        if (i >= 0 && i <= 1000) {
            this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO = i;
            if (this.enabled) {
                RefreshProvider("DistanceInterval");
            }
            for (LocationSensorListener onDistanceIntervalChanged : this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                onDistanceIntervalChanged.onDistanceIntervalChanged(this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.")
    public int DistanceInterval() {
        return this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasLongitudeLatitude() {
        return this.hqHtvKVLFGgMVMSKZM3hViIxQKdIh8YXfZOV4lmVWV1e2Mndp07BOheWTgPW1jrN && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAltitude() {
        return this.XBLVE91Zp5kvRRn2aG2d0scvywAN7zOQf8gZkaueK9XPU163NLSIe4vWoTpXMo6u && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAccuracy() {
        return Accuracy() != 0.0d && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Longitude() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Latitude() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Altitude() {
        return this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Accuracy() {
        Location location = this.f197hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (location != null && location.hasAccuracy()) {
            return (double) this.f197hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAccuracy();
        }
        LocationProvider locationProvider = this.f199hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (locationProvider != null) {
            return (double) locationProvider.getAccuracy();
        }
        return 0.0d;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(final boolean z) {
        if (!this.havePermission) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this).askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this, true);
                                LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this, z);
                                Log.d(LocationSensor.LOG_TAG, "Permission Granted");
                                return;
                            }
                            LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this, false);
                            LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this, z);
                            LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LocationSensor.this).dispatchPermissionDeniedEvent((Component) LocationSensor.this, "Enabled", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        } else {
            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(z);
        }
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(boolean z) {
        this.enabled = z;
        if (this.initialized) {
            if (!z) {
                stopListening();
            } else {
                RefreshProvider("Enabled");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        if (r11.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T <= 180.0d) goto L_0x002e;
     */
    @com.google.appinventor.components.annotations.SimpleProperty(category = com.google.appinventor.components.annotations.PropertyCategory.BEHAVIOR)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String CurrentAddress() {
        /*
            r11 = this;
            boolean r0 = r11.hqHtvKVLFGgMVMSKZM3hViIxQKdIh8YXfZOV4lmVWV1e2Mndp07BOheWTgPW1jrN
            if (r0 == 0) goto L_0x0023
            double r0 = r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r2 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0023
            r2 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0023
            double r0 = r11.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r2 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x002e
        L_0x0023:
            double r0 = r11.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r2 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x00a7
        L_0x002e:
            android.location.Geocoder r5 = r11.f196hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x006a }
            double r6 = r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x006a }
            double r8 = r11.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ Exception -> 0x006a }
            r10 = 1
            java.util.List r0 = r5.getFromLocation(r6, r8, r10)     // Catch:{ Exception -> 0x006a }
            if (r0 == 0) goto L_0x00a7
            int r1 = r0.size()     // Catch:{ Exception -> 0x006a }
            r2 = 1
            if (r1 != r2) goto L_0x00a7
            r1 = 0
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x006a }
            android.location.Address r0 = (android.location.Address) r0     // Catch:{ Exception -> 0x006a }
            if (r0 == 0) goto L_0x00a7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006a }
            r2.<init>()     // Catch:{ Exception -> 0x006a }
        L_0x0050:
            int r3 = r0.getMaxAddressLineIndex()     // Catch:{ Exception -> 0x006a }
            if (r1 > r3) goto L_0x0065
            java.lang.String r3 = r0.getAddressLine(r1)     // Catch:{ Exception -> 0x006a }
            r2.append(r3)     // Catch:{ Exception -> 0x006a }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ Exception -> 0x006a }
            int r1 = r1 + 1
            goto L_0x0050
        L_0x0065:
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x006a }
            return r0
        L_0x006a:
            r0 = move-exception
            boolean r1 = r0 instanceof java.lang.IllegalArgumentException
            if (r1 != 0) goto L_0x0090
            boolean r1 = r0 instanceof java.io.IOException
            if (r1 != 0) goto L_0x0090
            boolean r1 = r0 instanceof java.lang.IndexOutOfBoundsException
            if (r1 == 0) goto L_0x0078
            goto L_0x0090
        L_0x0078:
            java.lang.String r1 = LOG_TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unexpected exception thrown by getting current address "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
            goto L_0x00a7
        L_0x0090:
            java.lang.String r1 = LOG_TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Exception thrown by getting current address "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.e(r1, r0)
        L_0x00a7:
            java.lang.String r0 = "No address available"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.LocationSensor.CurrentAddress():java.lang.String");
    }

    @SimpleFunction(description = "Derives latitude of given address")
    public double LatitudeFromAddress(String str) {
        try {
            List<Address> fromLocationName = this.f196hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getFromLocationName(str, 1);
            String str2 = LOG_TAG;
            Log.i(str2, "latitude addressObjs size is " + fromLocationName.size() + " for " + str);
            if (fromLocationName != null && fromLocationName.size() != 0) {
                return fromLocationName.get(0).getLatitude();
            }
            throw new IOException("");
        } catch (Exception unused) {
            this.form.dispatchErrorOccurredEvent(this, "LatitudeFromAddress", 101, str);
            return 0.0d;
        }
    }

    @SimpleFunction(description = "Derives longitude of given address")
    public double LongitudeFromAddress(String str) {
        try {
            List<Address> fromLocationName = this.f196hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getFromLocationName(str, 1);
            String str2 = LOG_TAG;
            Log.i(str2, "longitude addressObjs size is " + fromLocationName.size() + " for " + str);
            if (fromLocationName != null && fromLocationName.size() != 0) {
                return fromLocationName.get(0).getLongitude();
            }
            throw new IOException("");
        } catch (Exception unused) {
            this.form.dispatchErrorOccurredEvent(this, "LongitudeFromAddress", 102, str);
            return 0.0d;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public List<String> AvailableProviders() {
        return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    }

    public void RefreshProvider(final String str) {
        if (this.initialized) {
            stopListening();
            if (!this.havePermission) {
                this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this).askPermission(new BulkPermissionRequest(this, "RefreshProvider", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION") {
                            public final void onGranted() {
                                LocationSensor.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this, true);
                                this.RefreshProvider(str);
                                Log.d(LocationSensor.LOG_TAG, "Permission Granted");
                            }
                        });
                    }
                });
            }
            if (!this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV || B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ)) {
                this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProviders(true);
                String bestProvider = this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBestProvider(this.f195hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                if (bestProvider != null && !bestProvider.equals(this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.get(0))) {
                    this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho.add(0, bestProvider);
                }
                for (String next : this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho) {
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(next);
                    this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2) {
                        if (!this.bEgrYPbd5peKqyXwAOBm3whGEG8qvODU2pBvqaxE9h7HUpHdWqfLhYhcIZ9UxgZV) {
                            this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = next;
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ);
        }
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = str;
        LocationProvider provider = this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProvider(str);
        if (provider == null) {
            String str2 = LOG_TAG;
            Log.d(str2, "getProvider(" + str + ") returned null");
            return false;
        }
        stopListening();
        this.f199hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = provider;
        this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.requestLocationUpdates(str, (long) this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z, (float) this.jfz4esSp2gG8GcabwIhRTwYj9V6OBMxneQTmg596S0YedndAxvJdoaPHqPTMCjiO, this.f200hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = true;
        return true;
    }

    private void stopListening() {
        if (this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn) {
            this.f198hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeUpdates(this.f200hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.f199hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            this.f194LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
        }
    }

    public void onResume() {
        if (this.enabled) {
            RefreshProvider("onResume");
        }
    }

    public void onStop() {
        stopListening();
    }

    public void onDelete() {
        stopListening();
    }

    public void addListener(LocationSensorListener locationSensorListener) {
        locationSensorListener.setSource(this);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.add(locationSensorListener);
    }

    public void removeListener(LocationSensorListener locationSensorListener) {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.remove(locationSensorListener);
        locationSensorListener.setSource((LocationSensor) null);
    }

    private static boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
        return str == null || str.length() == 0;
    }
}
