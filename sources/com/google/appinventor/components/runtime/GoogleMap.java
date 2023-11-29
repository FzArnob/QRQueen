package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GoogleMapStyleOptions;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Visible component that show information on Google map.", helpUrl = "https://docs.kodular.io/components/google/google-maps/", iconName = "images/gMap.png", version = 5)
@UsesLibraries({"play-services-base.jar", "play-services-base.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-location.jar", "play-services-location.aar", "play-services-maps.jar", "play-services-maps.aar", "play-services-tasks.jar", "play-services-tasks.aar", "core-runtime.jar", "core-runtime.aar", "gson.jar"})
@SimpleObject
@UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "com.google.android.providers.gsf.permission.READ_GSERVICES"})
public class GoogleMap extends AndroidViewComponent implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnCameraChangeListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnPoiClickListener, OnMapReadyCallback, OnPauseListener, OnResumeListener, OnInitializeListener {
    private static final AtomicInteger B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new AtomicInteger(1);
    public static final double RADIUS_OF_EARTH_METERS = 6371009.0d;
    private static final LocationRequest hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(100);

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static final AtomicInteger f130hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AtomicInteger(1);
    private static final AtomicInteger vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new AtomicInteger(1);
    private static final AtomicInteger wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new AtomicInteger(1);

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Float f131B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private HashMap<Marker, Integer> f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new HashMap<>();
    private float Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = 2.0f;
    private int EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = Color.HSVToColor(20, new float[]{0.0f, 1.0f, 1.0f});
    private boolean HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = false;
    private int I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = -14575886;
    private boolean JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = false;
    private boolean KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = false;
    private boolean OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = true;
    private int PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = 1;
    private String SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;

    /* renamed from: SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk  reason: collision with other field name */
    private boolean f133SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = false;
    private String SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = GoogleMapStyleOptions.THEME_STANDARD;

    /* renamed from: SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3  reason: collision with other field name */
    private boolean f134SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = false;
    private boolean UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = false;
    private int W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = 0;
    private final String XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS;

    /* renamed from: XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS  reason: collision with other field name */
    private boolean f135XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = false;
    private boolean YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = true;
    private boolean ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = true;
    private final Handler androidUIHandler = new Handler();
    private String eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08;

    /* renamed from: eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08  reason: collision with other field name */
    private boolean f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = false;
    private final Form form;
    private HashMap<Polygon, Integer> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new HashMap<>();
    private boolean havePermission = false;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Activity f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Bundle f138hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private LinearLayout f139hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private GoogleApiClient f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private com.google.android.gms.maps.GoogleMap f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SupportMapFragment f142hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private UiSettings f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private LatLng f144hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Float f145hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = false;
    private int kiRcALaZrUgDipBGGRCywmcwXV6owEI5LzNEBeYdXQPOcCNVgoIiuO1Q0ZEin4tA = 20;
    private List<a> l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = new ArrayList(1);
    private int lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = -16777216;
    private boolean muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = false;
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = "";

    /* renamed from: vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq  reason: collision with other field name */
    private final HashMap<Integer, Polyline> f146vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new HashMap<>();

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private YailList f147wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private Float f148wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private HashMap<Object, Integer> f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new HashMap<>();

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void onDisconnected() {
    }

    public GoogleMap(ComponentContainer componentContainer) throws IOException {
        super(componentContainer);
        Log.i("GoogleMap", "In the constructor of GoogleMap");
        Activity $context = componentContainer.$context();
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context;
        Form $form = componentContainer.$form();
        this.form = $form;
        this.f138hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $form.getOnCreateBundle();
        Log.i("GoogleMap", "savedInstanceState in GM: " + this.f138hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        LinearLayout linearLayout = new LinearLayout($context);
        this.f139hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = linearLayout;
        linearLayout.setId(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME());
        String str = "map_" + System.currentTimeMillis();
        this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = str;
        Log.i("GoogleMap", "map_tag:" + str);
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable($context);
        Log.i("GoogleMap", "googlePlayServicesAvailable:".concat(String.valueOf(isGooglePlayServicesAvailable)));
        if (isGooglePlayServicesAvailable == 1) {
            $form.dispatchErrorOccurredEvent(this, "checkGooglePlayServiceSDK", ErrorMessages.ERROR_GOOGLE_PLAY_NOT_INSTALLED, new Object[0]);
        } else if (isGooglePlayServicesAvailable == 2) {
            $form.dispatchErrorOccurredEvent(this, "checkGooglePlayServiceSDK", ErrorMessages.ERROR_GOOGLE_PLAY_SERVICE_UPDATE_REQUIRED, new Object[0]);
        } else if (isGooglePlayServicesAvailable == 3) {
            $form.dispatchErrorOccurredEvent(this, "checkGooglePlayServiceSDK", ErrorMessages.ERROR_GOOGLE_PLAY_DISABLED, new Object[0]);
        } else if (isGooglePlayServicesAvailable == 9) {
            $form.dispatchErrorOccurredEvent(this, "checkGooglePlayServiceSDK", ErrorMessages.ERROR_GOOGLE_PLAY_INVALID, new Object[0]);
        }
        try {
            $context.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            this.form.dispatchErrorOccurredEvent(this, "checkGoogleMapInstalled", ErrorMessages.ERROR_GOOGLE_MAP_NOT_INSTALLED, new Object[0]);
        }
        SupportMapFragment findFragmentByTag = this.form.getSupportFragmentManager().findFragmentByTag(this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS);
        this.f142hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = findFragmentByTag;
        if (findFragmentByTag == null) {
            Log.i("GoogleMap", "mMapFragment is null.");
            this.f142hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = SupportMapFragment.newInstance();
            FragmentTransaction beginTransaction = this.form.getSupportFragmentManager().beginTransaction();
            Log.i("GoogleMap", "here before adding fragment");
            beginTransaction.replace(this.f139hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getId(), this.f142hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS);
            beginTransaction.commit();
        }
        mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT();
        componentContainer.$add(this);
        Width(-2);
        Height(-2);
        this.form.registerForOnInitialize(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnPause(this);
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        AtomicInteger atomicInteger;
        int i;
        int i2;
        do {
            atomicInteger = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
            i = atomicInteger.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    @SimpleProperty
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleProperty
    public void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    private void mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT() {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = false;
            Log.i("GoogleMap", "setUpMapIfNeeded. mMap is null");
            this.f142hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapAsync(this);
            return;
        }
        this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = true;
    }

    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        Log.i("GoogleMap", "Yes, we have a google map...");
        this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = true;
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = googleMap;
        CameraPosition.Builder builder = new CameraPosition.Builder(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition());
        LatLng latLng = this.f144hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (latLng != null) {
            builder.target(latLng);
        }
        Float f = this.f145hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (f != null) {
            builder.zoom(f.floatValue());
        }
        Float f2 = this.f148wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
        if (f2 != null) {
            builder.tilt(f2.floatValue());
        }
        Float f3 = this.f131B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (f3 != null) {
            builder.bearing(f3.floatValue());
        }
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.moveCamera(CameraUpdateFactory.newCameraPosition(builder.build()));
        String str = this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08;
        if (str != null) {
            Style(str);
        } else {
            String str2 = this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk;
            if (str2 != this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3) {
                Theme(str2);
            }
        }
        Log.i("GoogleMap", "in setUpMap()");
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMarkerClickListener(this);
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnInfoWindowClickListener(this);
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMarkerDragListener(this);
        this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnPoiClickListener(this);
        UiSettings uiSettings = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getUiSettings();
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = uiSettings;
        uiSettings.setCompassEnabled(this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN);
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotateGesturesEnabled(this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScrollGesturesEnabled(this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1);
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlsEnabled(this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO);
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomGesturesEnabled(this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T);
        MapIsReady();
    }

    private void l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j() {
        if (this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new GoogleApiClient.Builder(this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void ApiKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @SimpleProperty(description = "Get the API Key")
    public String ApiKey() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @SimpleFunction(description = "Enables/disables the compass widget on the map's ui. Call this only after event \"MapIsReady\" is received")
    public void EnableCompass(boolean z) {
        if (this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08) {
            this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = z;
            this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompassEnabled(z);
        }
    }

    @SimpleProperty(description = "Indicates whether the compass widget is currently enabled in the map ui")
    public boolean CompassEnabled() {
        return this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompassEnabled();
    }

    @SimpleFunction(description = "Enables/disables the capability to rotate a map on the ui. Call this only after the event \"MapIsReady\" is received.")
    public void EnableRotate(boolean z) {
        if (this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08) {
            this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = z;
            this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotateGesturesEnabled(z);
        }
    }

    @SimpleProperty(description = "Indicates whether the capability to rotate a map on the ui is currently enabled")
    public boolean RotateEnabled() {
        return this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRotateGesturesEnabled();
    }

    @SimpleFunction(description = "Enables/disables the capability to scroll a map on the ui. Call this only after the event \"MapIsReady\" is received")
    public void EnableScroll(boolean z) {
        if (this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08) {
            this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = z;
            this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScrollGesturesEnabled(z);
        }
    }

    @SimpleProperty(description = "Indicates whether the capability to scroll a map on the ui is currently enabled")
    public boolean ScrollEnabled() {
        return this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isScrollGesturesEnabled();
    }

    @SimpleFunction(description = "Enables/disables the zoom widget on the map's ui. Call this only after the event \"MapIsReady\" is received")
    public void EnableZoomControl(boolean z) {
        this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = z;
        this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlsEnabled(z);
    }

    @SimpleProperty(description = "Indicates whether the zoom widget on the map ui is currently enabled")
    public boolean ZoomControlEnabled() {
        return this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomControlsEnabled();
    }

    @SimpleFunction(description = "Enables/disables zoom gesture on the map ui. Call this only after the event  \"MapIsReady\" is received. ")
    public void EnableZoomGesture(boolean z) {
        if (this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08) {
            this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = z;
            this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomGesturesEnabled(z);
        }
    }

    @SimpleProperty(description = "Indicates whether the zoom gesture is currently enabled")
    public boolean ZoomGestureEnabled() {
        return this.f143hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomGesturesEnabled();
    }

    @SimpleEvent(description = "Indicates that the map has been rendered and ready for adding markers or changing other settings. Please add or updating markers within this event")
    public void MapIsReady() {
        Log.i("GoogleMap", "Map is ready for adding markers and other setting");
        if (this.f136eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 && this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN && !this.f135XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS) {
            this.f135XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = true;
            Log.i("GoogleMap", "Map is ready for adding markers and other setting");
            EventDispatcher.dispatchEvent(this, "MapIsReady", new Object[0]);
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private Object m2hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, Integer.valueOf(i));
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        }
        this.form.dispatchErrorOccurredEvent(this, "getCircleIfExisted", ErrorMessages.ERROR_GOOGLE_MAP_CIRCLE_NOT_EXIST, Integer.toString(i));
        return null;
    }

    @SimpleFunction(description = "Remove a circle for the map. Returns true if successfully removed, false if the circle does not exist with the specified id")
    public boolean RemoveCircle(int i) {
        Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, Integer.valueOf(i));
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == null) {
            return false;
        }
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof a) {
            a aVar = (a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.remove();
            aVar.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.remove();
            aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j.remove(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
        }
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof Circle) {
            ((Circle) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).remove();
        }
        this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.remove(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
        return true;
    }

    @SimpleFunction(description = "Set the property of an existing circle. Properties include: \"alpha\"(number, value ranging from 0~255), \"color\" (nimber, hue value ranging 0~360), \"radius\"(number in meters)")
    public void UpdateCircle(int i, String str, Object obj) {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("GoogleMap", "inputs: " + i + "," + str + ", " + obj);
            float[] fArr = new float[3];
            Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i);
            Circle circle = null;
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof a) {
                    circle = ((a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                }
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof Circle) {
                    circle = (Circle) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                }
                try {
                    Float valueOf = Float.valueOf(obj.toString());
                    if (str.equals("alpha")) {
                        Color.colorToHSV(circle.getFillColor(), fArr);
                        circle.setFillColor(Color.HSVToColor(valueOf.intValue(), fArr));
                    }
                    if (str.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                        circle.setFillColor(Color.HSVToColor(Color.alpha(circle.getFillColor()), new float[]{valueOf.floatValue(), 1.0f, 1.0f}));
                    }
                    if (str.equals("radius")) {
                        circle.setRadius((double) valueOf.floatValue());
                        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof a) {
                            Marker marker = ((a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            ((a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
                            ((a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMarker(new MarkerOptions().position(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker.getPosition(), (double) valueOf.floatValue())).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(210.0f)));
                        }
                    }
                } catch (NumberFormatException unused) {
                    this.form.dispatchErrorOccurredEvent(this, "UpdateCircle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, obj.toString());
                }
            } else {
                this.form.dispatchErrorOccurredEvent(this, "UpdateCircle", ErrorMessages.ERROR_GOOGLE_MAP_CIRCLE_NOT_EXIST, Integer.valueOf(i));
            }
        }
    }

    @SimpleFunction(description = "Get all circles Ids. A short cut to get all the references for the eixisting circles")
    public YailList GetAllCircleIDs() {
        return YailList.makeList((Collection) this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.values());
    }

    @SimpleEvent(description = "Event been raised after the action of moving a draggable circle is finished. Possible a user drag the center of the circle or drag the radius marker of the circle")
    public void FinishedDraggingCircle(int i, double d, double d2, double d3) {
        final int i2 = i;
        final double d4 = d;
        final double d5 = d2;
        final double d6 = d3;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "a draggable circle:" + i2 + "finished been dragged");
                EventDispatcher.dispatchEvent(GoogleMap.this, "FinishedDraggingCircle", Integer.valueOf(i2), Double.valueOf(d4), Double.valueOf(d5), Double.valueOf(d6));
            }
        });
    }

    public View getView() {
        return this.f139hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onResume() {
        Log.i("GoogleMap", "in onResume...Google Map redraw");
        if (this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd && this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
            this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.connect();
        }
        mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT();
    }

    public void onInitialize() {
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = true;
        MapIsReady();
    }

    @SimpleFunction(description = "Enable or disable my location widget control for Google Map. One can call GetMyLocation() to obtain the current location after enable this.\"")
    public void EnableMyLocation(final boolean z) {
        if (!this.havePermission) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this).askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this, true);
                                GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this, z);
                                return;
                            }
                            GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this, false);
                            GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this, z);
                            GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleMap.this).dispatchPermissionDeniedEvent((Component) GoogleMap.this, "EnableMyLocation", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        } else {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(z);
        }
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(boolean z) {
        Log.i("GoogleMap", "@EnableMyLocation:".concat(String.valueOf(z)));
        if (this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd != z) {
            this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = z;
        }
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            try {
                googleMap.setMyLocationEnabled(z);
                if (z) {
                    l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j();
                    this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.connect();
                    return;
                }
                this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disconnect();
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "EnableMyLocation", e);
            } catch (Exception e2) {
                Log.e("GoogleMap", e2.getMessage());
            }
        }
    }

    @SimpleProperty(description = "Indicates whether my locaiton UI control is currently enabled for the Google map.")
    public boolean MyLocationEnabled() {
        return this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
    }

    @SimpleFunction(description = "Get current location using Google Map Service. Return a YailList with first item beingthe latitude, the second item being the longitude, and last time being the accuracy of the reading.")
    public YailList GetMyLocation() {
        ArrayList arrayList = new ArrayList();
        GoogleApiClient googleApiClient = this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleApiClient != null && googleApiClient.isConnected()) {
            Log.i("GoogleMap", "client is connected");
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            arrayList.add(Double.valueOf(lastLocation.getLatitude()));
            arrayList.add(Double.valueOf(lastLocation.getLongitude()));
            arrayList.add(Float.valueOf(lastLocation.getAccuracy()));
        }
        return YailList.makeList((List) arrayList);
    }

    @SimpleFunction(description = "Set the layer of Google map. Default layer is \"normal\", other choices including \"hybrid\",\"satellite\", and \"terrain\" ")
    public void SetMapType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1579103941:
                if (str.equals("satellite")) {
                    c = 0;
                    break;
                }
                break;
            case -1423437003:
                if (str.equals("terrain")) {
                    c = 1;
                    break;
                }
                break;
            case -1202757124:
                if (str.equals("hybrid")) {
                    c = 2;
                    break;
                }
                break;
            case -1039745817:
                if (str.equals("normal")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = 2;
                break;
            case 1:
                this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = 3;
                break;
            case 2:
                this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = 4;
                break;
            case 3:
                this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = 1;
                break;
            default:
                Log.i("GoogleMap", "Error setting layer with name ".concat(String.valueOf(str)));
                Form form2 = this.form;
                form2.dispatchErrorOccurredEvent(this, "SetMapType", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, str + " is not the correct type");
                break;
        }
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            googleMap.setMapType(this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR);
        }
    }

    @SimpleFunction(description = "Enable/Disable to listen to map's click event")
    public void EnableMapClickListener(boolean z) {
        Log.i("GoogleMap", "@EnableMapClickListener:".concat(String.valueOf(z)));
        if (this.f134SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 != z) {
            this.f134SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = z;
        }
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("GoogleMap", "enable map listener?: ".concat(String.valueOf(z)));
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMapClickListener(z ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the mapClick event listener is currently enabled")
    public boolean MapClickListenerEnabled() {
        return this.f134SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;
    }

    @SimpleFunction(description = "Enable/disable to listen to map's long click event")
    public void EnableMapLongClickListener(boolean z) {
        Log.i("GoogleMap", "@EnableMapLongClickListener:".concat(String.valueOf(z)));
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 != z) {
            this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = z;
        }
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("GoogleMap", "enable long click listener?:".concat(String.valueOf(z)));
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMapLongClickListener(z ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the map longClick listener is currently enabled")
    public boolean MapLongClickListenerEnabled() {
        return this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0;
    }

    @SimpleFunction(description = "Enable/Disable to listen to map's camera position changed event")
    public void EnableMapCameraPosChangeListener(boolean z) {
        Log.i("GoogleMap", "@EnableMapCameraPosChangeListener:".concat(String.valueOf(z)));
        if (this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG != z) {
            this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = z;
        }
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("GoogleMap", "enable cameraChangedListener?:".concat(String.valueOf(z)));
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnCameraChangeListener(z ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the map camera's position changed listener is currently enabled")
    public boolean MapCameraChangedListenerEnabled() {
        return this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG;
    }

    @SimpleProperty(description = "Indicates the current map type")
    public String MapType() {
        int i = this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR;
        if (i == 1) {
            return "normal";
        }
        if (i == 2) {
            return "satellite";
        }
        if (i == 3) {
            return "terrain";
        }
        if (i != 4) {
            return null;
        }
        return "hybrid";
    }

    @SimpleFunction(description = "Adding a list of YailLists for markers. The representation of a maker in the inner YailList is composed of: lat(double) [required], long(double) [required], Color, title(String), snippet(String), draggable(boolean). Return a list of unqiue ids for the added  markers. Note that the markers ids are not meant to persist after  the app is closed, but for temporary references to the markers within the program only. Return an empty list if any error happen in the input")
    public YailList AddMarkers(YailList yailList) {
        Object[] objArr;
        Double d;
        Double d2;
        boolean z;
        String str;
        boolean z2;
        int i = 3;
        float[] fArr = new float[3];
        ArrayList arrayList = new ArrayList();
        Object[] array = yailList.toArray();
        int length = array.length;
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            Object obj = array[i3];
            if (obj instanceof YailList) {
                Log.i("GoogleMap", "interior YailLiat");
                YailList yailList2 = (YailList) obj;
                boolean z3 = yailList2.size() >= 2;
                Object object = yailList2.getObject(i2);
                Object object2 = yailList2.getObject(1);
                Log.i("GoogleMap", "Type: " + object.getClass());
                Log.i("GoogleMap", "Type: " + object2.getClass());
                Double valueOf = Double.valueOf(0.0d);
                Double valueOf2 = Double.valueOf(0.0d);
                if (!(object instanceof DFloNum) || !(object2 instanceof DFloNum)) {
                    d = valueOf;
                    d2 = valueOf2;
                    z3 = false;
                } else {
                    d = Double.valueOf(((DFloNum) object).doubleValue());
                    d2 = Double.valueOf(((DFloNum) object2).doubleValue());
                }
                if (d.doubleValue() < -90.0d || d.doubleValue() > 90.0d || d2.doubleValue() < -180.0d || d2.doubleValue() > 180.0d) {
                    z3 = false;
                }
                int i4 = this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q;
                if (yailList2.size() >= i) {
                    Object object3 = yailList2.getObject(2);
                    StringBuilder sb = new StringBuilder("Type: ");
                    z = z3;
                    sb.append(object3.getClass());
                    Log.i("GoogleMap", sb.toString());
                    Log.i("GoogleMap", "Value: " + object3.toString());
                    if (object3 instanceof IntNum) {
                        i4 = ((IntNum) object3).intValue();
                    } else {
                        z = false;
                    }
                } else {
                    z = z3;
                }
                if (yailList2.size() >= 4) {
                    Object object4 = yailList2.getObject(3);
                    Log.i("GoogleMap", "Type: " + object4.getClass());
                    Log.i("GoogleMap", "Value: " + object4.toString());
                    str = object4.toString();
                } else {
                    str = "";
                }
                String str2 = "";
                if (yailList2.size() >= 5) {
                    Object object5 = yailList2.getObject(4);
                    StringBuilder sb2 = new StringBuilder("Type: ");
                    objArr = array;
                    sb2.append(object5.getClass());
                    Log.i("GoogleMap", sb2.toString());
                    Log.i("GoogleMap", "Value: " + object5.toString());
                    str2 = object5.toString();
                } else {
                    objArr = array;
                }
                if (yailList2.size() >= 6) {
                    Object object6 = yailList2.getObject(5);
                    Log.i("GoogleMap", "Type: " + object6.getClass());
                    Log.i("GoogleMap", "Value: " + object6.toString());
                    if (object6 instanceof Boolean) {
                        z2 = ((Boolean) object6).booleanValue();
                    } else {
                        z2 = false;
                        z = false;
                    }
                } else {
                    z2 = false;
                }
                Color.colorToHSV(i4, fArr);
                if (z) {
                    int incrementAndGet = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.incrementAndGet();
                    arrayList.add(Integer.valueOf(incrementAndGet));
                    i = 3;
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d, d2, incrementAndGet, fArr[0], str, str2, z2);
                } else {
                    i = 3;
                }
            } else {
                objArr = array;
                this.form.dispatchErrorOccurredEvent(this, "AddMarkers", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, "marker is not represented as list");
            }
            i3++;
            array = objArr;
            i2 = 0;
        }
        return YailList.makeList((List) arrayList);
    }

    private int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Double d, Double d2, int i, float f, String str, String str2, boolean z) {
        Log.i("GoogleMap", "@addMarkerToMap");
        Marker addMarker = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMarker(new MarkerOptions().position(new LatLng(d.doubleValue(), d2.doubleValue())).icon(BitmapDescriptorFactory.defaultMarker(f)));
        if (!str.isEmpty()) {
            addMarker.setTitle(str);
        }
        if (!str2.isEmpty()) {
            addMarker.setSnippet(str2);
        }
        addMarker.setDraggable(z);
        this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.put(addMarker, Integer.valueOf(i));
        return i;
    }

    @SimpleFunction(description = "Add a list of markers composed of name-value pairs. Name fields for a marker are: \"lat\" (type double) [required], \"lng\"(type double) [required], \"color\"(type int)[in hue value ranging from 0~360], \"title\"(type String), \"snippet\"(type String), \"draggable\"(type boolean)")
    public YailList GetMarkers() {
        return this.f147wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ec, code lost:
        r23 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ef, code lost:
        r23 = r4;
        r22 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f9, code lost:
        r17 = r4;
        r3 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ff, code lost:
        r23 = r4;
        r22 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        r1 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011e, code lost:
        r19 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0121, code lost:
        r3 = r21;
        r17 = r23;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x021e A[Catch:{ JsonSyntaxException -> 0x026e }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[ExcHandler: JsonSyntaxException (unused com.google.gson.JsonSyntaxException), SYNTHETIC, Splitter:B:24:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[ExcHandler: JsonSyntaxException (unused com.google.gson.JsonSyntaxException), PHI: r21 r23 
      PHI: (r21v8 java.lang.String) = (r21v12 java.lang.String), (r21v12 java.lang.String), (r21v14 java.lang.String), (r21v14 java.lang.String) binds: [B:48:0x010a, B:50:0x0112, B:35:0x00d8, B:36:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r23v4 java.util.ArrayList) = (r23v8 java.util.ArrayList), (r23v8 java.util.ArrayList), (r23v13 java.util.ArrayList), (r23v13 java.util.ArrayList) binds: [B:48:0x010a, B:50:0x0112, B:35:0x00d8, B:36:?] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:35:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x017b A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x017c A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01a2 A[SYNTHETIC, Splitter:B:87:0x01a2] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b0 A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01b1 A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c3 A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01c6 A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01d2 A[Catch:{ JsonSyntaxException -> 0x0228 }] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Adding a list of markers that are represented as JsonArray.  The inner JsonObject represents a markerand is composed of name-value pairs. Name fields for a marker are: \"lat\" (type double) [required], \"lng\"(type double) [required], \"color\"(type int)[in hue value ranging from 0~360], \"title\"(type String), \"snippet\"(type String), \"draggable\"(type boolean)")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void AddMarkersFromJson(java.lang.String r27) {
        /*
            r26 = this;
            r8 = r26
            r9 = r27
            java.lang.String r10 = "draggable"
            java.lang.String r11 = "snippet"
            java.lang.String r12 = "title"
            java.lang.String r13 = "color"
            java.lang.String r14 = "AddMarkersFromJson"
            java.lang.String r15 = ","
            java.lang.String r7 = "lng"
            java.lang.String r6 = "lat"
            java.lang.String r5 = "GoogleMap"
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            com.google.gson.JsonParser r0 = new com.google.gson.JsonParser
            r0.<init>()
            r1 = 3
            float[] r3 = new float[r1]
            r16 = 0
            com.google.gson.JsonElement r0 = r0.parse(r9)     // Catch:{ JsonSyntaxException -> 0x0271 }
            boolean r1 = r0.isJsonArray()     // Catch:{ JsonSyntaxException -> 0x0271 }
            if (r1 == 0) goto L_0x0253
            com.google.gson.JsonArray r0 = r0.getAsJsonArray()     // Catch:{ JsonSyntaxException -> 0x024e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x024e }
            java.lang.String r2 = "It's a JsonArry: "
            r1.<init>(r2)     // Catch:{ JsonSyntaxException -> 0x024e }
            java.lang.String r2 = r0.toString()     // Catch:{ JsonSyntaxException -> 0x024e }
            r1.append(r2)     // Catch:{ JsonSyntaxException -> 0x024e }
            java.lang.String r1 = r1.toString()     // Catch:{ JsonSyntaxException -> 0x024e }
            android.util.Log.i(r5, r1)     // Catch:{ JsonSyntaxException -> 0x024e }
            java.util.Iterator r18 = r0.iterator()     // Catch:{ JsonSyntaxException -> 0x024e }
        L_0x004c:
            boolean r0 = r18.hasNext()     // Catch:{ JsonSyntaxException -> 0x024e }
            if (r0 == 0) goto L_0x024b
            java.lang.Object r0 = r18.next()     // Catch:{ JsonSyntaxException -> 0x024e }
            com.google.gson.JsonElement r0 = (com.google.gson.JsonElement) r0     // Catch:{ JsonSyntaxException -> 0x024e }
            boolean r1 = r0.isJsonObject()     // Catch:{ JsonSyntaxException -> 0x024e }
            if (r1 == 0) goto L_0x022c
            com.google.gson.JsonObject r0 = r0.getAsJsonObject()     // Catch:{ JsonSyntaxException -> 0x024e }
            com.google.gson.JsonElement r1 = r0.get(r6)     // Catch:{ JsonSyntaxException -> 0x024e }
            if (r1 == 0) goto L_0x022c
            com.google.gson.JsonElement r1 = r0.get(r7)     // Catch:{ JsonSyntaxException -> 0x024e }
            if (r1 != 0) goto L_0x0070
            goto L_0x022c
        L_0x0070:
            com.google.gson.JsonElement r1 = r0.get(r6)     // Catch:{ JsonSyntaxException -> 0x024e }
            com.google.gson.JsonPrimitive r1 = (com.google.gson.JsonPrimitive) r1     // Catch:{ JsonSyntaxException -> 0x024e }
            com.google.gson.JsonElement r2 = r0.get(r7)     // Catch:{ JsonSyntaxException -> 0x024e }
            com.google.gson.JsonPrimitive r2 = (com.google.gson.JsonPrimitive) r2     // Catch:{ JsonSyntaxException -> 0x024e }
            r19 = 0
            boolean r21 = r1.isString()     // Catch:{ NumberFormatException -> 0x0127 }
            if (r21 == 0) goto L_0x0104
            boolean r21 = r2.isString()     // Catch:{ NumberFormatException -> 0x0127 }
            if (r21 == 0) goto L_0x0104
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0127 }
            r21 = r14
            java.lang.String r14 = "jpLat:"
            r9.<init>(r14)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r14 = r1.toString()     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            r9.append(r14)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r9 = r9.toString()     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            android.util.Log.i(r5, r9)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r14 = "jpLng:"
            r9.<init>(r14)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r14 = r2.toString()     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            r9.append(r14)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r9 = r9.toString()     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            android.util.Log.i(r5, r9)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.String r1 = r1.getAsString()     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch:{ NumberFormatException -> 0x00ff, JsonSyntaxException -> 0x00f9 }
            r14 = r10
            double r9 = r1.doubleValue()     // Catch:{ NumberFormatException -> 0x00f4, JsonSyntaxException -> 0x00f9 }
            java.lang.Double r1 = new java.lang.Double     // Catch:{ NumberFormatException -> 0x00ef, JsonSyntaxException -> 0x00f9 }
            java.lang.String r2 = r2.getAsString()     // Catch:{ NumberFormatException -> 0x00ef, JsonSyntaxException -> 0x00f9 }
            r1.<init>(r2)     // Catch:{ NumberFormatException -> 0x00ef, JsonSyntaxException -> 0x00f9 }
            double r1 = r1.doubleValue()     // Catch:{ NumberFormatException -> 0x00ef, JsonSyntaxException -> 0x00f9 }
            r22 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00ec, JsonSyntaxException -> 0x00f9 }
            r23 = r4
            java.lang.String r4 = "convert to double:"
            r14.<init>(r4)     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            r14.append(r9)     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            r14.append(r15)     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            r14.append(r1)     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            java.lang.String r4 = r14.toString()     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            android.util.Log.i(r5, r4)     // Catch:{ NumberFormatException -> 0x011e, JsonSyntaxException -> 0x0121 }
            goto L_0x011a
        L_0x00ec:
            r23 = r4
            goto L_0x011e
        L_0x00ef:
            r23 = r4
            r22 = r14
            goto L_0x011c
        L_0x00f4:
            r23 = r4
            r22 = r14
            goto L_0x012d
        L_0x00f9:
            r17 = r4
            r3 = r21
            goto L_0x0274
        L_0x00ff:
            r23 = r4
            r22 = r10
            goto L_0x012d
        L_0x0104:
            r23 = r4
            r22 = r10
            r21 = r14
            com.google.gson.JsonElement r1 = r0.get(r6)     // Catch:{ NumberFormatException -> 0x012d, JsonSyntaxException -> 0x0121 }
            double r9 = r1.getAsDouble()     // Catch:{ NumberFormatException -> 0x012d, JsonSyntaxException -> 0x0121 }
            com.google.gson.JsonElement r1 = r0.get(r7)     // Catch:{ NumberFormatException -> 0x011c, JsonSyntaxException -> 0x0121 }
            double r1 = r1.getAsDouble()     // Catch:{ NumberFormatException -> 0x011c, JsonSyntaxException -> 0x0121 }
        L_0x011a:
            r4 = 1
            goto L_0x0132
        L_0x011c:
            r1 = r19
        L_0x011e:
            r19 = r9
            goto L_0x012f
        L_0x0121:
            r3 = r21
            r17 = r23
            goto L_0x0274
        L_0x0127:
            r23 = r4
            r22 = r10
            r21 = r14
        L_0x012d:
            r1 = r19
        L_0x012f:
            r9 = r19
            r4 = 0
        L_0x0132:
            r19 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r14 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r14 < 0) goto L_0x0156
            r19 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r14 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r14 > 0) goto L_0x0156
            r19 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r14 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r14 < 0) goto L_0x0156
            r19 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r14 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r14 <= 0) goto L_0x016e
        L_0x0156:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r14 = "Lat/Lng wrong range:"
            r4.<init>(r14)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r4.append(r9)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r4.append(r15)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r4.append(r1)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r4 = r4.toString()     // Catch:{ JsonSyntaxException -> 0x0228 }
            android.util.Log.i(r5, r4)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r4 = 0
        L_0x016e:
            int r14 = r8.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q     // Catch:{ JsonSyntaxException -> 0x0228 }
            android.graphics.Color.colorToHSV(r14, r3)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r14 = r3[r16]     // Catch:{ JsonSyntaxException -> 0x0228 }
            com.google.gson.JsonElement r19 = r0.get(r13)     // Catch:{ JsonSyntaxException -> 0x0228 }
            if (r19 != 0) goto L_0x017c
            goto L_0x0185
        L_0x017c:
            com.google.gson.JsonElement r14 = r0.get(r13)     // Catch:{ JsonSyntaxException -> 0x0228 }
            int r14 = r14.getAsInt()     // Catch:{ JsonSyntaxException -> 0x0228 }
            float r14 = (float) r14     // Catch:{ JsonSyntaxException -> 0x0228 }
        L_0x0185:
            r19 = 0
            int r19 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r19 < 0) goto L_0x0191
            r19 = 1135869952(0x43b40000, float:360.0)
            int r19 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r19 <= 0) goto L_0x0197
        L_0x0191:
            java.lang.String r4 = "Wrong color"
            android.util.Log.i(r5, r4)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r4 = 0
        L_0x0197:
            com.google.gson.JsonElement r19 = r0.get(r12)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r20 = ""
            if (r19 != 0) goto L_0x01a2
            r19 = r20
            goto L_0x01aa
        L_0x01a2:
            com.google.gson.JsonElement r19 = r0.get(r12)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r19 = r19.getAsString()     // Catch:{ JsonSyntaxException -> 0x0228 }
        L_0x01aa:
            com.google.gson.JsonElement r24 = r0.get(r11)     // Catch:{ JsonSyntaxException -> 0x0228 }
            if (r24 != 0) goto L_0x01b1
            goto L_0x01b9
        L_0x01b1:
            com.google.gson.JsonElement r20 = r0.get(r11)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r20 = r20.getAsString()     // Catch:{ JsonSyntaxException -> 0x0228 }
        L_0x01b9:
            r24 = r11
            r11 = r22
            com.google.gson.JsonElement r22 = r0.get(r11)     // Catch:{ JsonSyntaxException -> 0x0228 }
            if (r22 != 0) goto L_0x01c6
            r22 = 0
            goto L_0x01d0
        L_0x01c6:
            com.google.gson.JsonElement r0 = r0.get(r11)     // Catch:{ JsonSyntaxException -> 0x0228 }
            boolean r0 = r0.getAsBoolean()     // Catch:{ JsonSyntaxException -> 0x0228 }
            r22 = r0
        L_0x01d0:
            if (r4 == 0) goto L_0x021e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r4 = "Adding marker"
            r0.<init>(r4)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r0.append(r9)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r0.append(r15)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r0.append(r1)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.String r0 = r0.toString()     // Catch:{ JsonSyntaxException -> 0x0228 }
            android.util.Log.i(r5, r0)     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.util.concurrent.atomic.AtomicInteger r0 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq     // Catch:{ JsonSyntaxException -> 0x0228 }
            int r4 = r0.incrementAndGet()     // Catch:{ JsonSyntaxException -> 0x0228 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ JsonSyntaxException -> 0x0228 }
            r25 = r5
            r5 = r23
            r5.add(r0)     // Catch:{ JsonSyntaxException -> 0x021b }
            java.lang.Double r9 = java.lang.Double.valueOf(r9)     // Catch:{ JsonSyntaxException -> 0x021b }
            java.lang.Double r2 = java.lang.Double.valueOf(r1)     // Catch:{ JsonSyntaxException -> 0x021b }
            r0 = r26
            r1 = r9
            r9 = 1
            r10 = r3
            r3 = r4
            r17 = r5
            r4 = r14
            r14 = r25
            r5 = r19
            r19 = r6
            r6 = r20
            r20 = r7
            r7 = r22
            r0.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ JsonSyntaxException -> 0x026e }
            goto L_0x023a
        L_0x021b:
            r17 = r5
            goto L_0x022a
        L_0x021e:
            r10 = r3
            r14 = r5
            r19 = r6
            r20 = r7
            r17 = r23
            r9 = 1
            goto L_0x023a
        L_0x0228:
            r17 = r23
        L_0x022a:
            r9 = 1
            goto L_0x026e
        L_0x022c:
            r17 = r4
            r19 = r6
            r20 = r7
            r24 = r11
            r21 = r14
            r9 = 1
            r14 = r5
            r11 = r10
            r10 = r3
        L_0x023a:
            r9 = r27
            r3 = r10
            r10 = r11
            r5 = r14
            r4 = r17
            r6 = r19
            r7 = r20
            r14 = r21
            r11 = r24
            goto L_0x004c
        L_0x024b:
            r17 = r4
            goto L_0x0286
        L_0x024e:
            r17 = r4
            r9 = 1
            r3 = r14
            goto L_0x0275
        L_0x0253:
            r17 = r4
            r21 = r14
            r9 = 1
            com.google.appinventor.components.runtime.Form r0 = r8.form     // Catch:{ JsonSyntaxException -> 0x026e }
            r1 = 12012(0x2eec, float:1.6832E-41)
            java.lang.Object[] r2 = new java.lang.Object[r9]     // Catch:{ JsonSyntaxException -> 0x026e }
            java.lang.String r3 = "markers needs to be represented as JsonArray"
            r2[r16] = r3     // Catch:{ JsonSyntaxException -> 0x026e }
            r3 = r21
            r0.dispatchErrorOccurredEvent(r8, r3, r1, r2)     // Catch:{ JsonSyntaxException -> 0x0275 }
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)     // Catch:{ JsonSyntaxException -> 0x0275 }
            r8.f147wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0     // Catch:{ JsonSyntaxException -> 0x0275 }
            goto L_0x0286
        L_0x026e:
            r3 = r21
            goto L_0x0275
        L_0x0271:
            r17 = r4
            r3 = r14
        L_0x0274:
            r9 = 1
        L_0x0275:
            com.google.appinventor.components.runtime.Form r0 = r8.form
            r1 = 12014(0x2eee, float:1.6835E-41)
            java.lang.Object[] r2 = new java.lang.Object[r9]
            r2[r16] = r27
            r0.dispatchErrorOccurredEvent(r8, r3, r1, r2)
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)
            r8.f147wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0
        L_0x0286:
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)
            r8.f147wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleMap.AddMarkersFromJson(java.lang.String):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0246 A[SYNTHETIC] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Adding a list of YailList for markers. The inner YailList represents a marker and is composed of lat(Double) [required], long(Double) [required], color(int)[in hue value ranging from 0-360], title(String), snippet(String), draggable(boolean). Return a list of unique ids for the markers that are added")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.appinventor.components.runtime.util.YailList AddMarkersHue(com.google.appinventor.components.runtime.util.YailList r22) {
        /*
            r21 = this;
            r8 = r21
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.lang.Object[] r10 = r22.toArray()
            int r11 = r10.length
            r12 = 0
            r13 = 0
        L_0x000e:
            if (r13 >= r11) goto L_0x0263
            r0 = r10[r13]
            boolean r1 = r0 instanceof com.google.appinventor.components.runtime.util.YailList
            java.lang.String r2 = "AddMarkersHue"
            r3 = 12012(0x2eec, float:1.6832E-41)
            r4 = 1
            if (r1 == 0) goto L_0x024f
            java.lang.String r1 = "GoogleMap"
            java.lang.String r5 = "Interior YailLiat"
            android.util.Log.i(r1, r5)
            com.google.appinventor.components.runtime.util.YailList r0 = (com.google.appinventor.components.runtime.util.YailList) r0
            int r5 = r0.size()
            r6 = 2
            java.lang.String r7 = "AddMarkers"
            if (r5 >= r6) goto L_0x003a
            com.google.appinventor.components.runtime.Form r5 = r8.form
            java.lang.Object[] r14 = new java.lang.Object[r4]
            java.lang.String r15 = "Need more than 2 inputs"
            r14[r12] = r15
            r5.dispatchErrorOccurredEvent(r8, r7, r3, r14)
            r5 = 0
            goto L_0x003b
        L_0x003a:
            r5 = 1
        L_0x003b:
            java.lang.Object r14 = r0.getObject(r12)
            java.lang.Object r15 = r0.getObject(r4)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r3 = "Type: "
            r6.<init>(r3)
            java.lang.Class r12 = r14.getClass()
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r1, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r3)
            java.lang.Class r12 = r15.getClass()
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r1, r6)
            r16 = 0
            java.lang.Double r6 = java.lang.Double.valueOf(r16)
            java.lang.Double r12 = java.lang.Double.valueOf(r16)
            boolean r4 = r14 instanceof gnu.math.DFloNum
            if (r4 == 0) goto L_0x0093
            boolean r4 = r15 instanceof gnu.math.DFloNum
            if (r4 != 0) goto L_0x007e
            goto L_0x0093
        L_0x007e:
            gnu.math.DFloNum r14 = (gnu.math.DFloNum) r14
            double r17 = r14.doubleValue()
            java.lang.Double r4 = java.lang.Double.valueOf(r17)
            gnu.math.DFloNum r15 = (gnu.math.DFloNum) r15
            double r14 = r15.doubleValue()
            java.lang.Double r6 = java.lang.Double.valueOf(r14)
            goto L_0x00a5
        L_0x0093:
            com.google.appinventor.components.runtime.Form r4 = r8.form
            r5 = 1
            java.lang.Object[] r14 = new java.lang.Object[r5]
            java.lang.String r5 = "Not a number for latitude or longitude"
            r15 = 0
            r14[r15] = r5
            r5 = 12012(0x2eec, float:1.6832E-41)
            r4.dispatchErrorOccurredEvent(r8, r2, r5, r14)
            r4 = r6
            r6 = r12
            r5 = 0
        L_0x00a5:
            double r14 = r4.doubleValue()
            r17 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r12 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r12 < 0) goto L_0x00d9
            double r14 = r4.doubleValue()
            r17 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r12 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r12 > 0) goto L_0x00d9
            double r14 = r6.doubleValue()
            r17 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r12 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r12 < 0) goto L_0x00d9
            double r14 = r6.doubleValue()
            r17 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r12 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r12 <= 0) goto L_0x00e9
        L_0x00d9:
            com.google.appinventor.components.runtime.Form r5 = r8.form
            r12 = 1
            java.lang.Object[] r14 = new java.lang.Object[r12]
            java.lang.String r12 = "Range for the latitude or longitude is wrong"
            r15 = 0
            r14[r15] = r12
            r12 = 12012(0x2eec, float:1.6832E-41)
            r5.dispatchErrorOccurredEvent(r8, r7, r12, r14)
            r5 = 0
        L_0x00e9:
            java.util.concurrent.atomic.AtomicInteger r12 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            int r12 = r12.incrementAndGet()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            int r15 = r0.size()
            r14 = 3
            r18 = r5
            java.lang.String r5 = "Value: "
            if (r15 < r14) goto L_0x0163
            r15 = 2
            java.lang.Object r15 = r0.getObject(r15)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r3)
            r19 = r10
            java.lang.Class r10 = r15.getClass()
            r14.append(r10)
            java.lang.String r10 = r14.toString()
            android.util.Log.i(r1, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r5)
            java.lang.String r14 = r15.toString()
            r10.append(r14)
            java.lang.String r10 = r10.toString()
            android.util.Log.i(r1, r10)
            boolean r10 = r15 instanceof gnu.math.IntNum
            if (r10 == 0) goto L_0x013d
            gnu.math.IntNum r15 = (gnu.math.IntNum) r15
            int r2 = r15.intValue()
            float r2 = (float) r2
            r17 = r2
            r20 = r11
            r15 = r18
            goto L_0x016b
        L_0x013d:
            com.google.appinventor.components.runtime.Form r10 = r8.form
            r20 = r11
            r14 = 1
            java.lang.Object[] r11 = new java.lang.Object[r14]
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = r15.toString()
            r14.append(r15)
            java.lang.String r15 = " is not a number"
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            r15 = 0
            r11[r15] = r14
            r14 = 12012(0x2eec, float:1.6832E-41)
            r10.dispatchErrorOccurredEvent(r8, r2, r14, r11)
            r15 = 0
            goto L_0x0169
        L_0x0163:
            r19 = r10
            r20 = r11
            r15 = r18
        L_0x0169:
            r17 = 1131413504(0x43700000, float:240.0)
        L_0x016b:
            int r2 = r0.size()
            r10 = 4
            java.lang.String r11 = ""
            if (r2 < r10) goto L_0x01a5
            r2 = 3
            java.lang.Object r2 = r0.getObject(r2)
            r14 = r2
            java.lang.String r14 = (java.lang.String) r14
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r3)
            r18 = r11
            java.lang.Class r11 = r2.getClass()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            android.util.Log.i(r1, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r5)
            java.lang.String r2 = r2.toString()
            r10.append(r2)
            java.lang.String r2 = r10.toString()
            android.util.Log.i(r1, r2)
            goto L_0x01a9
        L_0x01a5:
            r18 = r11
            r14 = r18
        L_0x01a9:
            int r2 = r0.size()
            r10 = 5
            if (r2 < r10) goto L_0x01e0
            r2 = 4
            java.lang.Object r2 = r0.getObject(r2)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r3)
            r18 = r11
            java.lang.Class r11 = r2.getClass()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            android.util.Log.i(r1, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r5)
            java.lang.String r2 = r2.toString()
            r10.append(r2)
            java.lang.String r2 = r10.toString()
            android.util.Log.i(r1, r2)
        L_0x01e0:
            int r2 = r0.size()
            r10 = 6
            if (r2 < r10) goto L_0x0230
            r2 = 5
            java.lang.Object r0 = r0.getObject(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.lang.Class r3 = r0.getClass()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r5)
            java.lang.String r3 = r0.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L_0x021e
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r7 = r0
            goto L_0x0231
        L_0x021e:
            com.google.appinventor.components.runtime.Form r0 = r8.form
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "marker not as a list"
            r3 = 0
            r1[r3] = r2
            r2 = 12012(0x2eec, float:1.6832E-41)
            r0.dispatchErrorOccurredEvent(r8, r7, r2, r1)
            r7 = 0
            r15 = 0
            goto L_0x0231
        L_0x0230:
            r7 = 0
        L_0x0231:
            if (r15 == 0) goto L_0x0246
            r9.add(r12)
            int r3 = r12.intValue()
            r0 = r21
            r1 = r4
            r2 = r6
            r4 = r17
            r5 = r14
            r6 = r18
            r0.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r1, r2, r3, r4, r5, r6, r7)
        L_0x0246:
            int r13 = r13 + 1
            r10 = r19
            r11 = r20
            r12 = 0
            goto L_0x000e
        L_0x024f:
            com.google.appinventor.components.runtime.Form r0 = r8.form
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "Marker is not represented as list"
            r4 = 0
            r1[r4] = r3
            r3 = 12012(0x2eec, float:1.6832E-41)
            r0.dispatchErrorOccurredEvent(r8, r2, r3, r1)
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r9)
            return r0
        L_0x0263:
            com.google.appinventor.components.runtime.util.YailList r0 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleMap.AddMarkersHue(com.google.appinventor.components.runtime.util.YailList):com.google.appinventor.components.runtime.util.YailList");
    }

    @SimpleFunction(description = "Set the property of a marker, note that the marker has to be added first or else will throw an exception! Properties include: \"color\"(hue value ranging from 0~360), \"title\", \"snippet\", \"draggable\"(give either true or false as the value).")
    public void UpdateMarker(int i, String str, Object obj) {
        String trim = str.trim();
        String trim2 = obj.toString().trim();
        Log.i("GoogleMap", "@UpdateMarker");
        Log.i("GoogleMap", "markerId:".concat(String.valueOf(i)));
        Log.i("GoogleMap", "prop:".concat(String.valueOf(str)));
        Log.i("GoogleMap", "value:".concat(String.valueOf(obj)));
        Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i);
        Log.i("GoogleMap", "marker?:".concat(String.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2)));
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            if (trim.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                Log.i("GoogleMap", "we are changing color");
                Float valueOf = Float.valueOf(trim2);
                if (valueOf.floatValue() < 0.0f || valueOf.floatValue() > 360.0f) {
                    this.form.dispatchErrorOccurredEvent(this, "UpdateMarker", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, valueOf.toString());
                } else {
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setIcon(BitmapDescriptorFactory.defaultMarker(valueOf.floatValue()));
                }
            }
            if (trim.equals("title")) {
                Log.i("GoogleMap", "we are changing title");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setTitle(trim2);
            }
            if (trim.equals("snippet")) {
                Log.i("GoogleMap", "we are changing snippet");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setSnippet(trim2);
            }
            if (trim.equals("draggable")) {
                Log.i("GoogleMap", "we are changing draggable");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setDraggable(Boolean.parseBoolean(trim2));
            }
        }
    }

    @SimpleFunction(description = "Get all the existing markers's Ids")
    public YailList GetAllMarkerID() {
        return YailList.makeList((Collection) this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.values());
    }

    private Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        Marker marker = (Marker) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, Integer.valueOf(i));
        if (marker == null) {
            this.form.dispatchErrorOccurredEvent(this, "getMarkerIfExisted", ErrorMessages.ERROR_GOOGLE_MAP_MARKER_NOT_EXIST, Integer.toString(i));
        }
        return marker;
    }

    @SimpleFunction(description = "Remove a marker from the map")
    public void RemoveMarker(int i) {
        Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.remove();
        }
    }

    public void onMarkerDrag(Marker marker) {
        Log.i("GoogleMap", "Dragging M:".concat(String.valueOf(marker)));
        Integer num = this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker);
        if (num != null) {
            LatLng position = marker.getPosition();
            OnMarkerDrag(num.intValue(), position.latitude, position.longitude);
        }
        for (a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            if (next.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker) || next.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker)) {
                next.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker);
            }
        }
    }

    public void onMarkerDragEnd(Marker marker) {
        Integer num = this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker);
        if (num != null) {
            LatLng position = marker.getPosition();
            OnMarkerDragEnd(num.intValue(), position.latitude, position.longitude);
        }
        for (a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            if (next.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker) || next.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker)) {
                next.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker);
                int intValue = this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(next).intValue();
                LatLng position2 = next.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPosition();
                FinishedDraggingCircle(intValue, position2.latitude, position2.longitude, next.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
            }
        }
    }

    public void onMarkerDragStart(Marker marker) {
        Integer num = this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker);
        if (num != null) {
            LatLng position = marker.getPosition();
            OnMarkerDragStart(num.intValue(), position.latitude, position.longitude);
        }
        for (a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            if (next.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker) || next.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker)) {
                next.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker);
            }
        }
    }

    @SimpleEvent(description = "When a marker starts been dragged")
    public void OnMarkerDragStart(int i, double d, double d2) {
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "a marker:" + i2 + "starts been dragged");
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMarkerDragStart", Integer.valueOf(i2), Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleEvent(description = "When a marker is been dragged")
    public void OnMarkerDrag(int i, double d, double d2) {
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "a marker:" + i2 + "is been dragged");
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMarkerDrag", Integer.valueOf(i2), Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleEvent(description = "When the user drags a marker and finish the action, returning marker's id and it's latest position")
    public void OnMarkerDragEnd(int i, double d, double d2) {
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "a marker:" + i2 + "finishes been dragged");
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMarkerDragEnd", Integer.valueOf(i2), Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleEvent(description = "When a marker is clicked")
    public void OnMarkerClick(int i, double d, double d2) {
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "a marker:" + i2 + "is clicked");
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMarkerClick", Integer.valueOf(i2), Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleEvent(description = "When the marker's infowindow is clicked, returning marker's id")
    public void InfoWindowClicked(final int i) {
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "A marker: " + i + " its info window is clicked");
                EventDispatcher.dispatchEvent(GoogleMap.this, "InfoWindowClicked", Integer.valueOf(i));
            }
        });
    }

    public void onInfoWindowClick(Marker marker) {
        InfoWindowClicked(this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker).intValue());
    }

    public boolean onMarkerClick(Marker marker) {
        LatLng position = marker.getPosition();
        OnMarkerClick(this.f132B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker).intValue(), position.latitude, position.longitude);
        return false;
    }

    private static <T, E> T hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map<T, E> map, E e) {
        for (Map.Entry next : map.entrySet()) {
            if (e.equals(next.getValue())) {
                return next.getKey();
            }
        }
        return null;
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        CameraPositionChanged(Double.valueOf(cameraPosition.target.latitude).doubleValue(), Double.valueOf(cameraPosition.target.longitude).doubleValue(), Float.valueOf(cameraPosition.bearing).floatValue(), Float.valueOf(cameraPosition.tilt).floatValue(), Float.valueOf(cameraPosition.zoom).floatValue());
    }

    @SimpleEvent(description = "Called after the camera position of a map has changed.")
    public void CameraPositionChanged(double d, double d2, float f, float f2, float f3) {
        final double d3 = d;
        final double d4 = d2;
        final float f4 = f;
        final float f5 = f2;
        final float f6 = f3;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "Camera's position has changed:" + d3 + ", " + d4 + ", " + f4 + "," + f5 + ", " + f6);
                EventDispatcher.dispatchEvent(GoogleMap.this, "CameraPositionChanged", Double.valueOf(d3), Double.valueOf(d4), Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f6));
            }
        });
    }

    public void onMapLongClick(LatLng latLng) {
        OnMapLongClick(latLng.latitude, latLng.longitude);
    }

    @SimpleEvent(description = "Called when the user makes a long-press gesture on the map")
    public void OnMapLongClick(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "Map is longclicked at:" + d3 + ", " + d4);
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMapLongClick", Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    public void onMapClick(LatLng latLng) {
        Log.i("GoogleMap", "receive google maps's onMapClick");
        OnMapClick(latLng.latitude, latLng.longitude);
    }

    @SimpleEvent(description = "Called when the user makes a tap gesture on the map")
    public void OnMapClick(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "map is clicked at:" + d3 + ", " + d4);
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnMapClick", Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleFunction(description = "Move the map's camera to the specified position and zoom level")
    public void MoveCamera(double d, double d2, float f) {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(d, d2), f));
            this.f145hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Float.valueOf(f);
            this.f144hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new LatLng(d, d2);
        }
    }

    @SimpleFunction(description = "Transforms the camera such that the specified latitude/longitude bounds are centered on screen at the greatest possible zoom level. Need to specify both latitudes and longitudes for both northeast location and southwest location of the bounding box")
    public void BoundCamera(double d, double d2, double d3, double d4) {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(d, d2), new LatLng(d3, d4)), 0));
        }
    }

    class a {
        Marker B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        final Circle hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        final Marker f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        public a(LatLng latLng, double d, float f, int i, int i2) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = d;
            this.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((GoogleMap) GoogleMap.this).addMarker(new MarkerOptions().position(latLng).draggable(true));
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((GoogleMap) GoogleMap.this).addMarker(new MarkerOptions().position(GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(latLng, d)).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(210.0f)));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((GoogleMap) GoogleMap.this).addCircle(new CircleOptions().center(latLng).radius(d).strokeWidth(f).strokeColor(i).fillColor(i2));
        }

        public final boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Marker marker) {
            if (marker.equals(this.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCenter(marker.getPosition());
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setPosition(GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker.getPosition(), this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
                return true;
            } else if (!marker.equals(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)) {
                return false;
            } else {
                double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f152hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPosition(), this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getPosition());
                this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRadius(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public static LatLng hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LatLng latLng, double d) {
        return new LatLng(latLng.latitude, latLng.longitude + (Math.toDegrees(d / 6371009.0d) / Math.cos(Math.toRadians(latLng.latitude))));
    }

    public void onConnected(Bundle bundle) {
        Log.i("GoogleMap", "onConnected to location listener.....");
        LocationServices.FusedLocationApi.requestLocationUpdates(this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this);
    }

    public void onPause() {
        Log.i("GoogleMap", "OnPause, remote LocationClient");
        if (this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            Log.i("GoogleMap", "before location client disconnect");
            this.f140hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disconnect();
        }
    }

    public void onLocationChanged(Location location) {
        OnLocationChanged(location.getLatitude(), location.getLongitude());
    }

    @SimpleEvent(description = "Triggers this event when user location has changed. Only works when EnableMylocation is set to true")
    public void OnLocationChanged(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.f137hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(new Runnable() {
            public final void run() {
                Log.i("GoogleMap", "location changed" + d3 + d4);
                EventDispatcher.dispatchEvent(GoogleMap.this, "OnLocationChanged", Double.valueOf(d3), Double.valueOf(d4));
            }
        });
    }

    @SimpleFunction(description = "A Polygon is an enclosed shape that can be used to mark areas on the map.")
    public void addPolygon(double d, double d2, double d3, double d4) {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.put(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addPolygon(new PolygonOptions().add(new LatLng[]{new LatLng(d, d4), new LatLng(d2, d4), new LatLng(d2, d3), new LatLng(d, d3), new LatLng(d, d4)})), 1);
        }
    }

    @SimpleFunction(description = "Clear all Polygons.")
    public void clearAllPolygons() {
        for (Polygon remove : this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.keySet()) {
            remove.remove();
        }
    }

    @SimpleFunction(description = "Draw central square.")
    public void drawCentralSquare() {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            LatLngBounds latLngBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
            LatLng latLng = latLngBounds.northeast;
            LatLng latLng2 = latLngBounds.southwest;
            double d = latLng.latitude;
            double d2 = latLng.latitude;
            double d3 = latLng2.latitude;
            double d4 = latLng2.longitude;
            double d5 = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().target.latitude;
            double d6 = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().target.longitude;
            double d7 = (d5 - d3) * 0.5d;
            double d8 = (d6 - d4) * 0.5d;
            AddMarkersFromJson("[{lat:" + d5 + ",lng:" + d6 + "}]");
            double d9 = d5 + d7;
            double d10 = d6 + d8;
            double d11 = d5 - d7;
            double d12 = d6 - d8;
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.put(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addPolygon(new PolygonOptions().add(new LatLng[]{new LatLng(d9, d10), new LatLng(d11, d10), new LatLng(d11, d12), new LatLng(d9, d12), new LatLng(d9, d10)})), 1);
            return;
        }
    }

    @SimpleFunction(description = "Get bounding box.")
    public String getBoundingBox(double d, double d2, double d3) {
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        double d4 = 1000.0d * d3;
        double cos = Math.cos(radians) * 4.0680631590769E13d;
        double sin = Math.sin(radians) * 4.040829980355529E13d;
        double cos2 = Math.cos(radians) * 6378137.0d;
        double sin2 = Math.sin(radians) * 6356752.3d;
        double sqrt = Math.sqrt(((cos * cos) + (sin * sin)) / ((cos2 * cos2) + (sin2 * sin2)));
        double cos3 = Math.cos(radians) * sqrt;
        double d5 = d4 / sqrt;
        double d6 = radians - d5;
        double d7 = radians + d5;
        double d8 = d4 / cos3;
        double d9 = radians2 - d8;
        double d10 = radians2 + d8;
        return Math.toDegrees(d6) + "," + Math.toDegrees(d9) + "," + Math.toDegrees(d7) + "," + Math.toDegrees(d10);
    }

    @SimpleFunction(description = "Add overlay.")
    public void addOverlay() {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addGroundOverlay(new GroundOverlayOptions().position(new LatLng(40.714086d, -74.228697d), 8600.0f, 6500.0f));
        }
    }

    @SimpleFunction(description = "Add title overlay.")
    public void addTileOverlay() {
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTileOverlay(new TileOverlayOptions().tileProvider(new UrlTileProvider() {
                public final URL getTileUrl(int i, int i2, int i3) {
                    boolean z = false;
                    String format = String.format("http://my.image.server/images/%d/%d/%d.png", new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
                    if (i3 >= 12 && i3 <= 16) {
                        z = true;
                    }
                    if (!z) {
                        return null;
                    }
                    try {
                        return new URL(format);
                    } catch (MalformedURLException e) {
                        throw new AssertionError(e);
                    }
                }
            }));
        }
    }

    @SimpleFunction(description = "Get map center. If a error occures the output will be '-999'.")
    public String getMapCenter() {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return googleMap != null ? googleMap.getCameraPosition().target.toString() : "-999";
    }

    @DesignerProperty(defaultValue = "15", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified zoom level.")
    public void CameraZoomLevel(float f) {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(f));
        } else {
            this.f145hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Float.valueOf(f);
        }
    }

    @SimpleProperty(description = "Gets the current zoom level of the map's camera.")
    public float CameraZoomLevel() {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            return googleMap.getCameraPosition().zoom;
        }
        return Float.NaN;
    }

    @DesignerProperty(defaultValue = "standard", editorType = "google_map_theme")
    @SimpleProperty(description = "Sets the theme of the map. The choices are \"standard\"(default), \"silver\", \"retro\", \"dark\", \"night\", \"aubergine\", \"vintage\", \"kodular\" and \"roads-only\".")
    public void Theme(String str) {
        if (str == null) {
            return;
        }
        if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = str;
        } else if (!GoogleMapStyleOptions.JSON_BY_THEME.containsKey(str)) {
            this.form.dispatchErrorOccurredEvent(this, "SetTheme", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_THEME, str);
        } else if (this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapStyle(new MapStyleOptions(GoogleMapStyleOptions.JSON_BY_THEME.get(str)))) {
            this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = str;
        } else {
            Form form2 = this.form;
            form2.dispatchErrorOccurredEvent(this, "SetStyle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_STYLE_JSON, str + "(theme)");
        }
    }

    @SimpleProperty(description = "Gets the theme of the map. The choices are \"standard\"(default), \"silver\", \"retro\", \"dark\", \"night\", \"aubergine\", \"vintage\", \"kodular\" and \"roads-only\". Returns \"custom\" if the style of the map has been set from json.")
    public String Theme() {
        return this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;
    }

    @SimpleProperty(description = "Sets the style of the map from json. Just use a text field and paste there the json data. Create a custom map style at https://mapstyle.withgoogle.com/. Set the theme to \"standard\" to clear the style json.")
    public void Style(String str) {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap == null) {
            this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = str;
        } else if (googleMap.setMapStyle(new MapStyleOptions(str))) {
            this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = GoogleMapStyleOptions.THEME_CUSTOM;
        } else {
            this.form.dispatchErrorOccurredEvent(this, "SetStyle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_STYLE_JSON, str);
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified tilt, the angle (in degrees) from the nadir (directly facing the Earth). Must be a value between 0.0 and 90.0")
    public void CameraAngle(float f) {
        if (f >= 0.0f && f <= 90.0f) {
            com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (googleMap != null) {
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition()).tilt(f).build()));
            } else {
                this.f148wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = Float.valueOf(f);
            }
        }
    }

    @SimpleProperty(description = "Gets the current tilt, the angle (in degrees) from the nadir (directly facing the Earth), of the map's camera.")
    public float CameraAngle() {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            return googleMap.getCameraPosition().tilt;
        }
        return Float.NaN;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified bearing, the direction that the camera is pointing in (in degrees clockwise from north).")
    public void CameraRotation(float f) {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition()).bearing(f).build()));
        } else {
            this.f131B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Float.valueOf(f);
        }
    }

    @SimpleProperty(description = "Gets the current bearing, the direction that the camera is pointing in (in degrees clockwise from north), of the map's camera.")
    public float CameraRotation() {
        com.google.android.gms.maps.GoogleMap googleMap = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (googleMap != null) {
            return googleMap.getCameraPosition().bearing;
        }
        return Float.NaN;
    }

    @Deprecated
    @SimpleFunction(description = "Deprecated block! Don't use this anymore. Use instead 'Camera Zoom Level'.")
    public float getZoomLevelInfo() {
        return CameraZoomLevel();
    }

    @SimpleEvent(description = "This event will be invoked when a user clicks on a point of interest. This can be a shop, coffee-bar or else.")
    public void OnPointOfInterestClick(double d, double d2, String str, String str2) {
        EventDispatcher.dispatchEvent(this, "OnPointOfInterestClick", Double.valueOf(d), Double.valueOf(d2), str, str2);
    }

    public void onPoiClick(PointOfInterest pointOfInterest) {
        if (pointOfInterest != null) {
            OnPointOfInterestClick(pointOfInterest.latLng.latitude, pointOfInterest.latLng.longitude, pointOfInterest.name, pointOfInterest.placeId);
        }
    }

    private static List<LatLng> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < yailList.size(); i++) {
            YailList yailList2 = (YailList) yailList.getObject(i);
            arrayList.add(new LatLng(((Number) yailList2.getObject(0)).doubleValue(), ((Number) yailList2.getObject(1)).doubleValue()));
        }
        return arrayList;
    }

    @SimpleFunction(description = "Convert a JsonArray of points (lat, lng pairs) to a list.")
    public YailList GetPointsFromJson(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            for (List makeList : (List) JsonUtil.getObjectFromJson(str)) {
                arrayList.add(YailList.makeList(makeList));
            }
            return YailList.makeList((List) arrayList);
        } catch (Exception unused) {
            return null;
        }
    }

    @SimpleFunction(description = "This block will return the unique id of the new added polyline. Create a new polyline on the map. Use for 'points' a list of lat, lng pairs. A integer for the 'width' (in pixel) and a valid color for the 'color' parameter.")
    public int AddPolyline(YailList yailList, float f, int i) {
        Polyline addPolyline = this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addPolyline(new PolylineOptions().addAll(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList)).width(f).color(i));
        int incrementAndGet = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.incrementAndGet();
        this.f146vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.put(Integer.valueOf(incrementAndGet), addPolyline);
        return incrementAndGet;
    }

    @SimpleFunction(description = "Use this block to remove a polyline from the map. It will return true if it was successful.")
    public boolean RemovePolyline(int i) {
        Polyline remove = this.f146vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.remove(Integer.valueOf(i));
        if (remove == null) {
            return false;
        }
        remove.remove();
        return true;
    }

    @SimpleFunction(description = "Update any polyline with the given id. You can change the property values for 'width' (in pixel), 'color' or 'points (a list of lat, lng pairs).")
    public void UpdatePolyline(int i, String str, Object obj) {
        Polyline polyline = this.f146vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.get(Integer.valueOf(i));
        if (polyline != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -982754077:
                    if (str.equals("points")) {
                        c = 0;
                        break;
                    }
                    break;
                case 94842723:
                    if (str.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                        c = 1;
                        break;
                    }
                    break;
                case 113126854:
                    if (str.equals("width")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    try {
                        polyline.setPoints(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj));
                        return;
                    } catch (Exception e) {
                        Log.e("GoogleMap", e.getMessage());
                        return;
                    }
                case 1:
                    try {
                        polyline.setColor(((Number) obj).intValue());
                        return;
                    } catch (Exception e2) {
                        Log.e("GoogleMap", e2.getMessage());
                        return;
                    }
                case 2:
                    try {
                        polyline.setWidth(((Number) obj).floatValue());
                        return;
                    } catch (Exception e3) {
                        Log.e("GoogleMap", e3.getMessage());
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @SimpleFunction(description = "This will return a list with all available polyline id's.")
    public YailList GetAllPolylineIds() {
        return YailList.makeList((Set) this.f146vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.keySet());
    }

    @SimpleFunction(description = "Create a circle overlay on the map UI with specified latitude and longitude for center. \"hue\" (min 0, max 360) and \"alpha\" (min 0, max 255) are used to set color and transparency level of the circle, \"strokeWidth\" and \"strokeColor\" are for the perimeter of the circle. Returning a unique id of the circle for future reference to events raised by moving this circle. If the circle isset to be draggable, two default markers will appear on the map: one in the center of the circle, another on the perimeter.")
    public int AddCircle(double d, double d2, double d3, int i, float f, float f2, int i2, boolean z) {
        double d4 = d;
        double d5 = d2;
        int incrementAndGet = f130hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.incrementAndGet();
        int HSVToColor = Color.HSVToColor(i, new float[]{f, 1.0f, 1.0f});
        if (z) {
            a aVar = new a(new LatLng(d, d2), d3, f2, i2, HSVToColor);
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j.add(aVar);
            this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(aVar, Integer.valueOf(incrementAndGet));
        } else {
            this.f149wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(this.f141hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addCircle(new CircleOptions().center(new LatLng(d, d2)).radius(d3).strokeWidth(f2).strokeColor(i2).fillColor(HSVToColor)), Integer.valueOf(incrementAndGet));
        }
        return incrementAndGet;
    }

    static /* synthetic */ double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LatLng latLng, LatLng latLng2) {
        float[] fArr = new float[1];
        Location.distanceBetween(latLng.latitude, latLng.longitude, latLng2.latitude, latLng2.longitude, fArr);
        return (double) fArr[0];
    }
}
