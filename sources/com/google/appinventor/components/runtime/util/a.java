package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.SVG;
import com.google.appinventor.components.common.MapType;
import com.google.appinventor.components.common.ScaleUnits;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.view.ZoomControlView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayWithIW;
import org.osmdroid.views.overlay.OverlayWithIWVisitor;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.infowindow.OverlayInfoWindow;
import org.osmdroid.views.overlay.mylocation.IMyLocationConsumer;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

class a implements MapFactory.MapController, MapListener {
    /* access modifiers changed from: private */
    public static final String TAG = "a";
    private static final float[] mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = {Float.NaN, 0.0f, 0.5f, 1.0f};
    private static final float[] sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = {Float.NaN, 0.0f, 1.0f, 0.5f};
    private Set<MapFactory.MapFeatureCollection> Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = new HashSet();
    private float M2XSrcNVsTj86KbWYhtzmFwqCl4FRN4hJC3YQ2jC5nTG9V14APZgqJsIs4HMKSeu = Float.NaN;
    /* access modifiers changed from: private */
    public boolean N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = false;
    private boolean UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    private boolean WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp;
    private final Form form;
    private RelativeLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SVG f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private MapType f310hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final C0001a f311hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private e f312hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ZoomControlView f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private MapView f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ScaleBarOverlay f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private CompassOverlay f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private RotationGestureOverlay f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private OverlayInfoWindow f318hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final MyLocationNewOverlay f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private Set<MapFactory.MapEventListener> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = new HashSet();

    /* renamed from: sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt  reason: collision with other field name */
    private Set<MapFactory.MapFeature> f320sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = new HashSet();
    private Map<MapFactory.MapFeature, OverlayWithIW> wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = new HashMap();
    private boolean zoomEnabled;

    /* renamed from: com.google.appinventor.components.runtime.util.a$a  reason: collision with other inner class name */
    static class C0001a implements LocationSensor.LocationSensorListener, IMyLocationProvider {
        private boolean enabled;
        private Location hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private IMyLocationConsumer f331hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private LocationSensor vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

        public final void onDistanceIntervalChanged(int i) {
        }

        public final void onProviderDisabled(String str) {
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public final void onTimeIntervalChanged(int i) {
        }

        private C0001a() {
            this.enabled = false;
        }

        /* synthetic */ C0001a(byte b) {
            this();
        }

        public final void setSource(LocationSensor locationSensor) {
            LocationSensor locationSensor2 = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
            if (locationSensor2 != locationSensor) {
                if (locationSensor2 != null) {
                    locationSensor2.Enabled(false);
                }
                this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = locationSensor;
                if (locationSensor != null) {
                    locationSensor.Enabled(this.enabled);
                }
            }
        }

        public final void onLocationChanged(Location location) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = location;
            IMyLocationConsumer iMyLocationConsumer = this.f331hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (iMyLocationConsumer != null) {
                iMyLocationConsumer.onLocationChanged(location, this);
            }
        }

        public final boolean startLocationProvider(IMyLocationConsumer iMyLocationConsumer) {
            this.f331hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = iMyLocationConsumer;
            LocationSensor locationSensor = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
            if (locationSensor != null) {
                locationSensor.Enabled(true);
                this.enabled = true;
            }
            return this.enabled;
        }

        public final void stopLocationProvider() {
            LocationSensor locationSensor = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
            if (locationSensor != null) {
                locationSensor.Enabled(false);
            }
            this.enabled = false;
        }

        public final Location getLastKnownLocation() {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        }

        public final void destroy() {
            this.f331hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    class e extends Overlay {
        /* access modifiers changed from: private */
        public boolean YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;

        public final void draw(Canvas canvas, MapView mapView, boolean z) {
        }

        private e() {
            this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = true;
        }

        /* synthetic */ e(a aVar, byte b) {
            this();
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView) {
            return !this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
        }

        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView) {
            return !this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
        }

        public final boolean onLongPress(MotionEvent motionEvent, MapView mapView) {
            IGeoPoint fromPixels = mapView.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY());
            double latitude = fromPixels.getLatitude();
            double longitude = fromPixels.getLongitude();
            for (MapFactory.MapEventListener onLongPress : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                onLongPress.onLongPress(latitude, longitude);
            }
            return false;
        }
    }

    class c extends Handler {
        private c() {
        }

        /* synthetic */ c(a aVar, byte b) {
            this();
        }

        public final void handleMessage(Message message) {
            if (message.what == 0) {
                if (!a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this) && a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).canDispatchEvent((Component) null, "MapReady")) {
                    boolean unused = a.this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = true;
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).runOnUiThread(new Runnable() {
                        public final void run() {
                            for (MapFactory.MapEventListener onReady : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                                onReady.onReady(a.this);
                            }
                        }
                    });
                }
                a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
            }
        }
    }

    class b extends MapView {
        public final void onDetach() {
        }

        public b(Context context) {
            super(context, (MapTileProviderBase) null, new c(a.this, (byte) 0));
        }

        /* access modifiers changed from: protected */
        public final void onSizeChanged(int i, int i2, int i3, int i4) {
            scrollTo(getScrollX() + ((i3 - i) / 2), getScrollY() + ((i4 - i2) / 2));
            a.super.onSizeChanged(i, i2, i3, i4);
        }
    }

    a(Form form2) {
        OpenStreetMapTileProviderConstants.setUserAgentValue(form2.getApplication().getPackageName());
        File file = new File(form2.getCacheDir(), "osmdroid");
        if (file.exists() || file.mkdirs()) {
            Configuration.getInstance().setOsmdroidBasePath(file);
            File file2 = new File(file, "tiles");
            if (file2.exists() || file2.mkdirs()) {
                Configuration.getInstance().setOsmdroidTileCache(file2);
                this.WrlQqhIKmKuB9b4JVzpHXmCJrSlbkHSNA5ubvsLC9K31KYZzZrethddcAVSmF8Zp = true;
            }
        }
        this.form = form2;
        this.f312hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new e(this, (byte) 0);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new b(form2.getApplicationContext());
        C0001a aVar = new C0001a((byte) 0);
        this.f311hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
        this.f318hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new OverlayInfoWindow(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTilesScaledToDpi(true);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapListener(this);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(this.f312hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addOnTapListener(new MapView.OnTapListener() {
            public final void onSingleTap(MapView mapView, double d, double d2) {
                for (MapFactory.MapEventListener onSingleTap : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onSingleTap.onSingleTap(d, d2);
                }
            }

            public final void onDoubleTap(MapView mapView, double d, double d2) {
                for (MapFactory.MapEventListener onDoubleTap : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onDoubleTap.onDoubleTap(d, d2);
                }
            }
        });
        this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ZoomControlView(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MyLocationNewOverlay(aVar, this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = scaleBarOverlay;
        scaleBarOverlay.setAlignBottom(true);
        this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAlignRight(true);
        this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disableScaleBar();
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        RelativeLayout relativeLayout = new RelativeLayout(form2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = relativeLayout;
        relativeLayout.setClipChildren(true);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, new RelativeLayout.LayoutParams(-1, -1));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
    }

    public View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public double getLatitude() {
        return this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapCenter().getLatitude();
    }

    public double getLongitude() {
        return this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapCenter().getLongitude();
    }

    public void setCenter(double d2, double d3) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().setCenter(new GeoPoint(d2, d3));
    }

    public void setZoom(int i) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().setZoom((double) i);
        this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateButtons();
    }

    public int getZoom() {
        return (int) this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getZoomLevel(true);
    }

    public void setZoomEnabled(boolean z) {
        this.zoomEnabled = z;
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMultiTouchControls(z);
    }

    public boolean isZoomEnabled() {
        return this.zoomEnabled;
    }

    public void setMapType(MapFactory.MapType mapType) {
        MapType fromUnderlyingValue = MapType.fromUnderlyingValue(Integer.valueOf(mapType.ordinal()));
        if (fromUnderlyingValue != null) {
            setMapTypeAbstract(fromUnderlyingValue);
        }
    }

    public MapFactory.MapType getMapType() {
        return MapFactory.MapType.values()[this.f310hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toUnderlyingValue().intValue()];
    }

    public void setMapTypeAbstract(MapType mapType) {
        this.f310hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mapType;
        int i = AnonymousClass6.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT[mapType.ordinal()];
        if (i == 1) {
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTileSource(TileSourceFactory.MAPNIK);
        } else if (i == 2) {
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTileSource(TileSourceFactory.USGS_SAT);
        } else if (i == 3) {
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTileSource(TileSourceFactory.USGS_TOPO);
        }
    }

    public MapType getMapTypeAbstract() {
        return this.f310hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void setCompassEnabled(boolean z) {
        if (z && this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new CompassOverlay(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getContext(), this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public final boolean onPreDraw() {
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).setCompassCenter((((float) a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).getMeasuredWidth()) / a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).getContext().getResources().getDisplayMetrics().density) - 35.0f, 35.0f);
                    return true;
                }
            });
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        CompassOverlay compassOverlay = this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (compassOverlay == null) {
            return;
        }
        if (z) {
            if (compassOverlay.getOrientationProvider() != null) {
                this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableCompass();
            } else {
                this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableCompass(new InternalCompassOrientationProvider(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getContext()));
            }
            this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onOrientationChanged(this.M2XSrcNVsTj86KbWYhtzmFwqCl4FRN4hJC3YQ2jC5nTG9V14APZgqJsIs4HMKSeu, (IOrientationProvider) null);
            return;
        }
        this.M2XSrcNVsTj86KbWYhtzmFwqCl4FRN4hJC3YQ2jC5nTG9V14APZgqJsIs4HMKSeu = compassOverlay.getOrientation();
        this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disableCompass();
    }

    public boolean isCompassEnabled() {
        CompassOverlay compassOverlay = this.f316hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return compassOverlay != null && compassOverlay.isCompassEnabled();
    }

    public void setZoomControlEnabled(boolean z) {
        if (this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO != z) {
            this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(z ? 0 : 8);
            this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = z;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public boolean isZoomControlEnabled() {
        return this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    }

    public void setShowUserEnabled(boolean z) {
        this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setEnabled(z);
        if (z) {
            this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableMyLocation();
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            return;
        }
        this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disableMyLocation();
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().remove(this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public boolean isShowUserEnabled() {
        return this.f319hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnabled();
    }

    public void setRotationEnabled(boolean z) {
        if (z && this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new RotationGestureOverlay(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        RotationGestureOverlay rotationGestureOverlay = this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (rotationGestureOverlay != null) {
            rotationGestureOverlay.setEnabled(z);
            if (z) {
                this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            } else {
                this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().remove(this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            }
        }
    }

    public boolean isRotationEnabled() {
        RotationGestureOverlay rotationGestureOverlay = this.f317hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        return rotationGestureOverlay != null && rotationGestureOverlay.isEnabled();
    }

    public void setPanEnabled(boolean z) {
        boolean unused = this.f312hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = z;
    }

    public boolean isPanEnabled() {
        return this.f312hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    }

    public void panTo(double d2, double d3, int i, double d4) {
        Animation animation;
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().animateTo(new GeoPoint(d2, d3));
        if (this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().zoomTo((double) i) && (animation = this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAnimation()) != null) {
            animation.setDuration((long) (d4 * 1000.0d));
        }
    }

    public void addEventListener(MapFactory.MapEventListener mapEventListener) {
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(mapEventListener);
        if ((this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT || ViewCompat.isAttachedToWindow(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) && this.form.canDispatchEvent((Component) null, "MapReady")) {
            this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = true;
            mapEventListener.onReady(this);
        }
    }

    public void addFeature(final MapFactory.MapMarker mapMarker) {
        AnonymousClass8 r0 = new AsyncCallbackPair<Marker>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                Marker marker = (Marker) obj;
                marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    public final boolean onMarkerClick(Marker marker, MapView mapView) {
                        for (MapFactory.MapEventListener onFeatureClick : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                            onFeatureClick.onFeatureClick(mapMarker);
                        }
                        if (!mapMarker.EnableInfobox()) {
                            return false;
                        }
                        marker.showInfoWindow();
                        return false;
                    }

                    public final boolean onMarkerLongPress(Marker marker, MapView mapView) {
                        for (MapFactory.MapEventListener onFeatureLongPress : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                            onFeatureLongPress.onFeatureLongPress(mapMarker);
                        }
                        return false;
                    }
                });
                marker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                    public final void onMarkerDrag(Marker marker) {
                        for (MapFactory.MapEventListener onFeatureDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                            onFeatureDrag.onFeatureDrag(mapMarker);
                        }
                    }

                    public final void onMarkerDragEnd(Marker marker) {
                        GeoPoint position = marker.getPosition();
                        mapMarker.updateLocation(position.getLatitude(), position.getLongitude());
                        for (MapFactory.MapEventListener onFeatureStopDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                            onFeatureStopDrag.onFeatureStopDrag(mapMarker);
                        }
                    }

                    public final void onMarkerDragStart(Marker marker) {
                        for (MapFactory.MapEventListener onFeatureStartDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                            onFeatureStartDrag.onFeatureStartDrag(mapMarker);
                        }
                    }
                });
                if (mapMarker.Visible()) {
                    a.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((OverlayWithIW) marker);
                } else {
                    a.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((OverlayWithIW) marker);
                }
            }

            public final void onFailure(String str) {
                Log.e(a.TAG, "Unable to create marker: ".concat(String.valueOf(str)));
            }
        };
        final Marker marker = new Marker(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.put(mapMarker, marker);
        marker.setDraggable(mapMarker.Draggable());
        marker.setTitle(mapMarker.Title());
        marker.setSnippet(mapMarker.Description());
        marker.setPosition(new GeoPoint(mapMarker.Latitude(), mapMarker.Longitude()));
        marker.setAnchor(0.5f, 1.0f);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker, (AsyncCallbackPair<Drawable>) new AsyncCallbackFacade<Drawable, Marker>(r0) {
            public final /* synthetic */ void onSuccess(Object obj) {
                marker.setIcon((Drawable) obj);
                this.callback.onSuccess(marker);
            }

            public final void onFailure(String str) {
                this.callback.onFailure(str);
            }
        });
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(final MapFactory.MapFeature mapFeature, Polygon polygon) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.put(mapFeature, polygon);
        polygon.setOnClickListener(new Polygon.OnClickListener() {
            public final boolean onLongClick(Polygon polygon, MapView mapView, GeoPoint geoPoint) {
                for (MapFactory.MapEventListener onFeatureLongPress : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureLongPress.onFeatureLongPress(mapFeature);
                }
                return true;
            }

            public final boolean onClick(Polygon polygon, MapView mapView, GeoPoint geoPoint) {
                for (MapFactory.MapEventListener onFeatureClick : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureClick.onFeatureClick(mapFeature);
                }
                if (!mapFeature.EnableInfobox()) {
                    return true;
                }
                polygon.showInfoWindow(geoPoint);
                return true;
            }
        });
        polygon.setOnDragListener(new Polygon.OnDragListener() {
            public final void onDragStart(Polygon polygon) {
                for (MapFactory.MapEventListener onFeatureStartDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureStartDrag.onFeatureStartDrag(mapFeature);
                }
            }

            public final void onDrag(Polygon polygon) {
                for (MapFactory.MapEventListener onFeatureDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureDrag.onFeatureDrag(mapFeature);
                }
            }

            public final void onDragEnd(Polygon polygon) {
                MapFactory.MapFeature mapFeature = mapFeature;
                if (mapFeature instanceof MapFactory.MapCircle) {
                    int size = polygon.getPoints().size();
                    double d = 0.0d;
                    double d2 = 0.0d;
                    for (GeoPoint geoPoint : polygon.getPoints()) {
                        d += geoPoint.getLatitude();
                        d2 += geoPoint.getLongitude();
                    }
                    if (size > 0) {
                        double d3 = (double) size;
                        ((MapFactory.MapCircle) mapFeature).updateCenter(d / d3, d2 / d3);
                    } else {
                        ((MapFactory.MapCircle) mapFeature).updateCenter(0.0d, 0.0d);
                    }
                } else if (mapFeature instanceof MapFactory.MapRectangle) {
                    double d4 = -180.0d;
                    double d5 = 90.0d;
                    double d6 = -90.0d;
                    double d7 = 180.0d;
                    for (GeoPoint geoPoint2 : polygon.getPoints()) {
                        double latitude = geoPoint2.getLatitude();
                        double longitude = geoPoint2.getLongitude();
                        d6 = Math.max(d6, latitude);
                        d5 = Math.min(d5, latitude);
                        d4 = Math.max(d4, longitude);
                        d7 = Math.min(d7, longitude);
                    }
                    ((MapFactory.MapRectangle) mapFeature).updateBounds(d6, d7, d5, d4);
                } else {
                    MapFactory.MapPolygon mapPolygon = (MapFactory.MapPolygon) mapFeature;
                    d dVar = (d) polygon;
                    ArrayList arrayList = new ArrayList();
                    for (Polygon points : dVar.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                        arrayList.add(points.getPoints());
                    }
                    mapPolygon.updatePoints(arrayList);
                    ((MapFactory.MapPolygon) mapFeature).updateHolePoints(dVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME());
                }
                for (MapFactory.MapEventListener onFeatureStopDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureStopDrag.onFeatureStopDrag(mapFeature);
                }
            }
        });
        if (mapFeature.Visible()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((OverlayWithIW) polygon);
        } else {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((OverlayWithIW) polygon);
        }
    }

    public void removeFeature(MapFactory.MapFeature mapFeature) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().remove(this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature));
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.remove(mapFeature);
    }

    public void updateFeaturePosition(MapFactory.MapMarker mapMarker) {
        Marker marker = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapMarker);
        if (marker != null) {
            marker.setAnchor(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt[mapMarker.AnchorHorizontal()], mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT[mapMarker.AnchorVertical()]);
            marker.setPosition(new GeoPoint(mapMarker.Latitude(), mapMarker.Longitude()));
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeaturePosition(MapFactory.MapLineString mapLineString) {
        Polyline polyline = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapLineString);
        if (polyline != null) {
            polyline.setPoints(mapLineString.getPoints());
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeaturePosition(MapFactory.MapPolygon mapPolygon) {
        d dVar = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapPolygon);
        if (dVar != null) {
            dVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(mapPolygon.getPoints());
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeatureHoles(MapFactory.MapPolygon mapPolygon) {
        d dVar = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapPolygon);
        if (dVar != null) {
            dVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(mapPolygon.getHolePoints());
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeaturePosition(MapFactory.MapCircle mapCircle) {
        GeoPoint geoPoint = new GeoPoint(mapCircle.Latitude(), mapCircle.Longitude());
        Polygon polygon = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapCircle);
        if (polygon != null) {
            polygon.setPoints(Polygon.pointsAsCircle(geoPoint, mapCircle.Radius()));
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeaturePosition(MapFactory.MapRectangle mapRectangle) {
        Polygon polygon = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapRectangle);
        if (polygon != null) {
            polygon.setPoints(Polygon.pointsAsRect(new BoundingBox(mapRectangle.NorthLatitude(), mapRectangle.EastLongitude(), mapRectangle.SouthLatitude(), mapRectangle.WestLongitude())));
            this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        }
    }

    public void updateFeatureFill(final MapFactory.HasFill hasFill) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(hasFill);
        if (overlayWithIW != null) {
            overlayWithIW.accept(new OverlayWithIWVisitor() {
                public final void visit(Polyline polyline) {
                }

                public final void visit(final Marker marker) {
                    a.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapMarker) hasFill, (AsyncCallbackPair<Drawable>) new AsyncCallbackPair<Drawable>() {
                        public final /* synthetic */ void onSuccess(Object obj) {
                            marker.setIcon((Drawable) obj);
                            a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                        }

                        public final void onFailure(String str) {
                            Log.e(a.TAG, "Unable to update fill color for marker: ".concat(String.valueOf(str)));
                        }
                    });
                }

                public final void visit(Polygon polygon) {
                    polygon.setFillColor(hasFill.FillColor());
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                }
            });
        }
    }

    public void updateFeatureStroke(final MapFactory.HasStroke hasStroke) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(hasStroke);
        if (overlayWithIW != null) {
            overlayWithIW.accept(new OverlayWithIWVisitor() {
                public final void visit(final Marker marker) {
                    a.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapMarker) hasStroke, (AsyncCallbackPair<Drawable>) new AsyncCallbackPair<Drawable>() {
                        public final /* synthetic */ void onSuccess(Object obj) {
                            marker.setIcon((Drawable) obj);
                            a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                        }

                        public final void onFailure(String str) {
                            Log.e(a.TAG, "Unable to update stroke color for marker: ".concat(String.valueOf(str)));
                        }
                    });
                }

                public final void visit(Polyline polyline) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    polyline.setColor(hasStroke.StrokeColor());
                    polyline.setWidth(((float) hasStroke.StrokeWidth()) * displayMetrics.density);
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                }

                public final void visit(Polygon polygon) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    polygon.setStrokeColor(hasStroke.StrokeColor());
                    polygon.setStrokeWidth(((float) hasStroke.StrokeWidth()) * displayMetrics.density);
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                }
            });
        }
    }

    public void updateFeatureText(MapFactory.MapFeature mapFeature) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature);
        if (overlayWithIW != null) {
            overlayWithIW.setTitle(mapFeature.Title());
            overlayWithIW.setSnippet(mapFeature.Description());
        }
    }

    public void updateFeatureDraggable(MapFactory.MapFeature mapFeature) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature);
        if (overlayWithIW != null) {
            overlayWithIW.setDraggable(mapFeature.Draggable());
        }
    }

    public void updateFeatureImage(MapFactory.MapMarker mapMarker) {
        final Marker marker = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapMarker);
        if (marker != null) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker, (AsyncCallbackPair<Drawable>) new AsyncCallbackPair<Drawable>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    marker.setIcon((Drawable) obj);
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                }

                public final void onFailure(String str) {
                    Log.e(a.TAG, "Unable to update feature image: ".concat(String.valueOf(str)));
                }
            });
        }
    }

    public void updateFeatureSize(MapFactory.MapMarker mapMarker) {
        final Marker marker = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapMarker);
        if (marker != null) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker, (AsyncCallbackPair<Drawable>) new AsyncCallbackPair<Drawable>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    marker.setIcon((Drawable) obj);
                    a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this).invalidate();
                }

                public final void onFailure(String str) {
                    Log.wtf(a.TAG, "Cannot find default marker");
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapMarker mapMarker, AsyncCallbackPair<Drawable> asyncCallbackPair) {
        String ImageAsset = mapMarker.ImageAsset();
        if (ImageAsset == null || ImageAsset.length() == 0 || ImageAsset.endsWith(".svg")) {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(mapMarker, asyncCallbackPair);
        } else {
            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(mapMarker, asyncCallbackPair);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067 A[SYNTHETIC, Splitter:B:26:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(com.google.appinventor.components.runtime.util.MapFactory.MapMarker r7, com.google.appinventor.components.runtime.util.AsyncCallbackPair<android.graphics.drawable.Drawable> r8) {
        /*
            r6 = this;
            com.caverock.androidsvg.SVG r0 = r6.f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            java.lang.String r1 = "Unable to read Marker asset"
            java.lang.String r2 = "Invalid SVG in Marker asset"
            if (r0 != 0) goto L_0x003b
            org.osmdroid.views.MapView r0 = r6.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ SVGParseException -> 0x0022, IOException -> 0x001b }
            android.content.Context r0 = r0.getContext()     // Catch:{ SVGParseException -> 0x0022, IOException -> 0x001b }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ SVGParseException -> 0x0022, IOException -> 0x001b }
            java.lang.String r3 = "marker.svg"
            com.caverock.androidsvg.SVG r0 = com.caverock.androidsvg.SVG.getFromAsset(r0, r3)     // Catch:{ SVGParseException -> 0x0022, IOException -> 0x001b }
            r6.f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0     // Catch:{ SVGParseException -> 0x0022, IOException -> 0x001b }
            goto L_0x0028
        L_0x001b:
            r0 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r1, r0)
            goto L_0x0028
        L_0x0022:
            r0 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r2, r0)
        L_0x0028:
            com.caverock.androidsvg.SVG r0 = r6.f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r0 == 0) goto L_0x0033
            com.caverock.androidsvg.SVG$Svg r0 = r0.getRootElement()
            if (r0 == 0) goto L_0x0033
            goto L_0x003b
        L_0x0033:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Unable to load SVG from assets"
            r7.<init>(r8)
            throw r7
        L_0x003b:
            java.lang.String r0 = r7.ImageAsset()
            r3 = 0
            if (r0 == 0) goto L_0x0095
            int r4 = r0.length()
            if (r4 == 0) goto L_0x0095
            org.osmdroid.views.MapView r4 = r6.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ SVGParseException -> 0x005e, IOException -> 0x0057 }
            android.content.Context r4 = r4.getContext()     // Catch:{ SVGParseException -> 0x005e, IOException -> 0x0057 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ SVGParseException -> 0x005e, IOException -> 0x0057 }
            com.caverock.androidsvg.SVG r4 = com.caverock.androidsvg.SVG.getFromAsset(r4, r0)     // Catch:{ SVGParseException -> 0x005e, IOException -> 0x0057 }
            goto L_0x0065
        L_0x0057:
            r4 = move-exception
            java.lang.String r5 = TAG
            android.util.Log.e(r5, r1, r4)
            goto L_0x0064
        L_0x005e:
            r4 = move-exception
            java.lang.String r5 = TAG
            android.util.Log.e(r5, r2, r4)
        L_0x0064:
            r4 = r3
        L_0x0065:
            if (r4 != 0) goto L_0x0094
            com.google.appinventor.components.runtime.Form r5 = r6.form     // Catch:{ SVGParseException -> 0x0084, IOException -> 0x007a }
            java.io.InputStream r3 = com.google.appinventor.components.runtime.util.MediaUtil.openMedia(r5, r0)     // Catch:{ SVGParseException -> 0x0084, IOException -> 0x007a }
            com.caverock.androidsvg.SVG r0 = com.caverock.androidsvg.SVG.getFromInputStream(r3)     // Catch:{ SVGParseException -> 0x0084, IOException -> 0x007a }
            java.lang.String r1 = TAG
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r1, r3)
            r3 = r0
            goto L_0x0095
        L_0x0078:
            r7 = move-exception
            goto L_0x008e
        L_0x007a:
            r0 = move-exception
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0078 }
            android.util.Log.e(r2, r1, r0)     // Catch:{ all -> 0x0078 }
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r2, r3)
            goto L_0x0094
        L_0x0084:
            r0 = move-exception
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0078 }
            android.util.Log.e(r1, r2, r0)     // Catch:{ all -> 0x0078 }
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r1, r3)
            goto L_0x0094
        L_0x008e:
            java.lang.String r8 = TAG
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r8, r3)
            throw r7
        L_0x0094:
            r3 = r4
        L_0x0095:
            if (r3 != 0) goto L_0x0099
            com.caverock.androidsvg.SVG r3 = r6.f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
        L_0x0099:
            android.graphics.drawable.Drawable r7 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.util.MapFactory.MapMarker) r7, (com.caverock.androidsvg.SVG) r3)     // Catch:{ Exception -> 0x00a1 }
            r8.onSuccess(r7)     // Catch:{ Exception -> 0x00a1 }
            return
        L_0x00a1:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.onFailure(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.a.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(com.google.appinventor.components.runtime.util.MapFactory$MapMarker, com.google.appinventor.components.runtime.util.AsyncCallbackPair):void");
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(final MapFactory.MapMarker mapMarker, final AsyncCallbackPair<Drawable> asyncCallbackPair) {
        MediaUtil.getBitmapDrawableAsync(this.form, mapMarker.ImageAsset(), new AsyncCallbackPair<BitmapDrawable>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) obj;
                bitmapDrawable.setAlpha(Math.round(mapMarker.FillOpacity() * 255.0f));
                asyncCallbackPair.onSuccess(bitmapDrawable);
            }

            public final void onFailure(String str) {
                asyncCallbackPair.onSuccess(a.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker, a.this.f309hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
            }
        });
    }

    private static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SVG.Svg svg) {
        if (svg.width != null) {
            return svg.width.floatValue();
        }
        if (svg.viewBox != null) {
            return svg.viewBox.width;
        }
        return 30.0f;
    }

    private static float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SVG.Svg svg) {
        if (svg.height != null) {
            return svg.height.floatValue();
        }
        if (svg.viewBox != null) {
            return svg.viewBox.height;
        }
        return 50.0f;
    }

    /* access modifiers changed from: private */
    public Drawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapMarker mapMarker, SVG svg) {
        Iterator it;
        Iterator it2;
        SVG.Svg rootElement = svg.getRootElement();
        float f = this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getContext().getResources().getDisplayMetrics().density;
        float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = mapMarker.Height() <= 0 ? B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(rootElement) : (float) mapMarker.Height();
        float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = mapMarker.Width() <= 0 ? hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(rootElement) : (float) mapMarker.Width();
        float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T / B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(rootElement);
        float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 / hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(rootElement);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        PaintUtil.changePaint(paint, mapMarker.FillColor());
        PaintUtil.changePaint(paint2, mapMarker.StrokeColor());
        SVG.Length length = new SVG.Length(((float) mapMarker.StrokeWidth()) / ((float) Math.sqrt((double) ((B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 * B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2) + (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 * hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3)))));
        Iterator it3 = rootElement.getChildren().iterator();
        while (it3.hasNext()) {
            SVG.SvgConditionalElement svgConditionalElement = (SVG.SvgObject) it3.next();
            if (svgConditionalElement instanceof SVG.SvgConditionalElement) {
                SVG.SvgConditionalElement svgConditionalElement2 = svgConditionalElement;
                svgConditionalElement2.baseStyle.fill = new SVG.Colour(paint.getColor());
                svgConditionalElement2.baseStyle.fillOpacity = Float.valueOf(((float) paint.getAlpha()) / 255.0f);
                svgConditionalElement2.baseStyle.stroke = new SVG.Colour(paint2.getColor());
                svgConditionalElement2.baseStyle.strokeOpacity = Float.valueOf(((float) paint2.getAlpha()) / 255.0f);
                svgConditionalElement2.baseStyle.strokeWidth = length;
                svgConditionalElement2.baseStyle.specifiedFlags = 61;
                if (svgConditionalElement2.style != null) {
                    if ((svgConditionalElement2.style.specifiedFlags & 1) == 0) {
                        svgConditionalElement2.style.fill = new SVG.Colour(paint.getColor());
                        svgConditionalElement2.style.specifiedFlags |= 1;
                    }
                    if ((svgConditionalElement2.style.specifiedFlags & 4) == 0) {
                        svgConditionalElement2.style.fillOpacity = Float.valueOf(((float) paint.getAlpha()) / 255.0f);
                        it2 = it3;
                        svgConditionalElement2.style.specifiedFlags |= 4;
                    } else {
                        it2 = it3;
                    }
                    if ((svgConditionalElement2.style.specifiedFlags & 8) == 0) {
                        svgConditionalElement2.style.stroke = new SVG.Colour(paint2.getColor());
                        it = it2;
                        svgConditionalElement2.style.specifiedFlags |= 8;
                    } else {
                        it = it2;
                    }
                    if ((svgConditionalElement2.style.specifiedFlags & 16) == 0) {
                        svgConditionalElement2.style.strokeOpacity = Float.valueOf(((float) paint2.getAlpha()) / 255.0f);
                        svgConditionalElement2.style.specifiedFlags |= 16;
                    }
                    if ((svgConditionalElement2.style.specifiedFlags & 32) == 0) {
                        svgConditionalElement2.style.strokeWidth = length;
                        svgConditionalElement2.style.specifiedFlags |= 32;
                    }
                    it3 = it;
                }
            }
            it = it3;
            it3 = it;
        }
        Picture renderToPicture = svg.renderToPicture();
        Picture picture = new Picture();
        Canvas beginRecording = picture.beginRecording((int) ((hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 + (((float) mapMarker.StrokeWidth()) * 2.0f)) * f), (int) ((B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T + (((float) mapMarker.StrokeWidth()) * 2.0f)) * f));
        beginRecording.scale(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 * f, f * B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2);
        beginRecording.translate(length.floatValue(), length.floatValue());
        renderToPicture.draw(beginRecording);
        picture.endRecording();
        return new PictureDrawable(picture);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Polygon polygon, MapFactory.MapFeature mapFeature) {
        polygon.setDraggable(mapFeature.Draggable());
        polygon.setTitle(mapFeature.Title());
        polygon.setSnippet(mapFeature.Description());
        MapFactory.HasStroke hasStroke = (MapFactory.HasStroke) mapFeature;
        polygon.setStrokeColor(hasStroke.StrokeColor());
        polygon.setStrokeWidth((float) hasStroke.StrokeWidth());
        polygon.setFillColor(((MapFactory.HasFill) mapFeature).FillColor());
        polygon.setInfoWindow(this.f318hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public void showFeature(MapFactory.MapFeature mapFeature) {
        if (!this.f320sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.contains(mapFeature)) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature));
        }
    }

    /* access modifiers changed from: protected */
    public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(OverlayWithIW overlayWithIW) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().add(overlayWithIW);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public void hideFeature(MapFactory.MapFeature mapFeature) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature));
    }

    /* access modifiers changed from: protected */
    public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(OverlayWithIW overlayWithIW) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().remove(overlayWithIW);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public boolean isFeatureVisible(MapFactory.MapFeature mapFeature) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature);
        return overlayWithIW != null && this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlayManager().contains(overlayWithIW);
    }

    public boolean isFeatureCollectionVisible(MapFactory.MapFeatureCollection mapFeatureCollection) {
        return !this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.contains(mapFeatureCollection);
    }

    public void setFeatureCollectionVisible(MapFactory.MapFeatureCollection mapFeatureCollection, boolean z) {
        if (!z && this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.contains(mapFeatureCollection)) {
            return;
        }
        if (z && !this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.contains(mapFeatureCollection)) {
            return;
        }
        if (z) {
            this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.remove(mapFeatureCollection);
            for (MapFactory.MapFeature next : mapFeatureCollection) {
                this.f320sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.remove(next);
                if (next.Visible()) {
                    showFeature(next);
                }
            }
            return;
        }
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.add(mapFeatureCollection);
        for (MapFactory.MapFeature next2 : mapFeatureCollection) {
            this.f320sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.add(next2);
            hideFeature(next2);
        }
    }

    public void showInfobox(MapFactory.MapFeature mapFeature) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature).showInfoWindow();
    }

    public void hideInfobox(MapFactory.MapFeature mapFeature) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature).closeInfoWindow();
    }

    public boolean isInfoboxVisible(MapFactory.MapFeature mapFeature) {
        OverlayWithIW overlayWithIW = this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.get(mapFeature);
        return overlayWithIW != null && overlayWithIW.isInfoWindowOpen();
    }

    public BoundingBox getBoundingBox() {
        return this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBoundingBox();
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().setCenter(boundingBox.getCenter());
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getController().zoomToSpan(boundingBox.getLatitudeSpan(), boundingBox.getLongitudeSpan());
    }

    public boolean onScroll(ScrollEvent scrollEvent) {
        for (MapFactory.MapEventListener onBoundsChanged : this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            onBoundsChanged.onBoundsChanged();
        }
        return true;
    }

    public boolean onZoom(ZoomEvent zoomEvent) {
        this.f313hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateButtons();
        for (MapFactory.MapEventListener onZoom : this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
            onZoom.onZoom();
        }
        return true;
    }

    public LocationSensor.LocationSensorListener getLocationListener() {
        return this.f311hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public int getOverlayCount() {
        System.err.println(this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlays());
        return this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getOverlays().size();
    }

    public void setRotation(float f) {
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapOrientation(f);
    }

    public float getRotation() {
        return this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapOrientation();
    }

    public void setScaleVisible(boolean z) {
        this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setEnabled(z);
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public boolean isScaleVisible() {
        return this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnabled();
    }

    public void setScaleUnits(MapFactory.MapScaleUnits mapScaleUnits) {
        int i = AnonymousClass6.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j[mapScaleUnits.ordinal()];
        if (i == 1) {
            this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.metric);
        } else if (i == 2) {
            this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.imperial);
        } else {
            throw new IllegalArgumentException("Unallowable unit system: ".concat(String.valueOf(mapScaleUnits)));
        }
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public MapFactory.MapScaleUnits getScaleUnits() {
        int i = AnonymousClass6.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH[this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getUnitsOfMeasure().ordinal()];
        if (i == 1) {
            return MapFactory.MapScaleUnits.IMPERIAL;
        }
        if (i == 2) {
            return MapFactory.MapScaleUnits.METRIC;
        }
        throw new IllegalStateException("Somehow we have an unallowed unit system");
    }

    /* renamed from: com.google.appinventor.components.runtime.util.a$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
        static final /* synthetic */ int[] KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
        static final /* synthetic */ int[] l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
        static final /* synthetic */ int[] mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;

        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|15|16|(2:17|18)|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        static {
            /*
                com.google.appinventor.components.common.ScaleUnits[] r0 = com.google.appinventor.components.common.ScaleUnits.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = r0
                r1 = 1
                com.google.appinventor.components.common.ScaleUnits r2 = com.google.appinventor.components.common.ScaleUnits.Metric     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.ScaleUnits r3 = com.google.appinventor.components.common.ScaleUnits.Imperial     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                org.osmdroid.views.overlay.ScaleBarOverlay$UnitsOfMeasure[] r2 = org.osmdroid.views.overlay.ScaleBarOverlay.UnitsOfMeasure.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = r2
                org.osmdroid.views.overlay.ScaleBarOverlay$UnitsOfMeasure r3 = org.osmdroid.views.overlay.ScaleBarOverlay.UnitsOfMeasure.imperial     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH     // Catch:{ NoSuchFieldError -> 0x0038 }
                org.osmdroid.views.overlay.ScaleBarOverlay$UnitsOfMeasure r3 = org.osmdroid.views.overlay.ScaleBarOverlay.UnitsOfMeasure.metric     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                com.google.appinventor.components.runtime.util.MapFactory$MapScaleUnits[] r2 = com.google.appinventor.components.runtime.util.MapFactory.MapScaleUnits.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r2
                com.google.appinventor.components.runtime.util.MapFactory$MapScaleUnits r3 = com.google.appinventor.components.runtime.util.MapFactory.MapScaleUnits.METRIC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r2 = l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j     // Catch:{ NoSuchFieldError -> 0x0053 }
                com.google.appinventor.components.runtime.util.MapFactory$MapScaleUnits r3 = com.google.appinventor.components.runtime.util.MapFactory.MapScaleUnits.IMPERIAL     // Catch:{ NoSuchFieldError -> 0x0053 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                com.google.appinventor.components.common.MapType[] r2 = com.google.appinventor.components.common.MapType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = r2
                com.google.appinventor.components.common.MapType r3 = com.google.appinventor.components.common.MapType.Road     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r1 = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT     // Catch:{ NoSuchFieldError -> 0x006e }
                com.google.appinventor.components.common.MapType r2 = com.google.appinventor.components.common.MapType.Aerial     // Catch:{ NoSuchFieldError -> 0x006e }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.google.appinventor.components.common.MapType r1 = com.google.appinventor.components.common.MapType.Terrain     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.a.AnonymousClass6.<clinit>():void");
        }
    }

    public void setScaleUnitsAbstract(ScaleUnits scaleUnits) {
        int i = AnonymousClass6.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho[scaleUnits.ordinal()];
        if (i == 1) {
            this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.metric);
        } else if (i == 2) {
            this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.imperial);
        }
        this.f314hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    public ScaleUnits getScaleUnitsAbstract() {
        int i = AnonymousClass6.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH[this.f315hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getUnitsOfMeasure().ordinal()];
        if (i == 1) {
            return ScaleUnits.Imperial;
        }
        if (i == 2) {
            return ScaleUnits.Metric;
        }
        throw new IllegalStateException("Somehow we have an unallowed unit system");
    }

    static class d extends Polygon {
        List<Polygon> PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = new ArrayList();
        private Polygon.OnClickListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private Polygon.OnDragListener f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private boolean sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;

        d() {
        }

        public final void showInfoWindow() {
            if (this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.size() > 0) {
                this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.get(0).showInfoWindow();
            }
        }

        public final void draw(Canvas canvas, MapView mapView, boolean z) {
            for (Polygon draw : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                draw.draw(canvas, mapView, z);
            }
        }

        public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(List<List<GeoPoint>> list) {
            Iterator<Polygon> it = this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.iterator();
            Iterator<List<GeoPoint>> it2 = list.iterator();
            while (it.hasNext() && it2.hasNext()) {
                it.next().setPoints(it2.next());
            }
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            while (it2.hasNext()) {
                Polygon polygon = new Polygon();
                polygon.setPoints(it2.next());
                polygon.setStrokeColor(getStrokeColor());
                polygon.setFillColor(getFillColor());
                polygon.setStrokeWidth(getStrokeWidth());
                polygon.setInfoWindow(getInfoWindow());
                polygon.setDraggable(this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW);
                polygon.setOnClickListener(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                polygon.setOnDragListener(this.f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.add(polygon);
            }
        }

        public final List<List<List<GeoPoint>>> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            ArrayList arrayList = new ArrayList();
            for (Polygon holes : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                arrayList.add(holes.getHoles());
            }
            return arrayList;
        }

        public final void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(List<List<List<GeoPoint>>> list) {
            if (list == null || list.isEmpty()) {
                for (Polygon holes : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                    holes.setHoles(Collections.emptyList());
                }
            } else if (list.size() == this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.size()) {
                Iterator<Polygon> it = this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY.iterator();
                Iterator<List<List<GeoPoint>>> it2 = list.iterator();
                while (it.hasNext() && it2.hasNext()) {
                    it.next().setHoles(it2.next());
                }
            } else {
                throw new IllegalArgumentException("Holes and points are not of the same arity.");
            }
        }

        public final void setDraggable(boolean z) {
            a.super.setDraggable(z);
            this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = z;
            for (Polygon draggable : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                draggable.setDraggable(z);
            }
        }

        public final void setOnClickListener(Polygon.OnClickListener onClickListener) {
            a.super.setOnClickListener(onClickListener);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = onClickListener;
            for (Polygon onClickListener2 : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                onClickListener2.setOnClickListener(onClickListener);
            }
        }

        public final void setOnDragListener(Polygon.OnDragListener onDragListener) {
            a.super.setOnDragListener(onDragListener);
            this.f332hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = onDragListener;
            for (Polygon onDragListener2 : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                onDragListener2.setOnDragListener(onDragListener);
            }
        }

        public final void setStrokeWidth(float f) {
            a.super.setStrokeWidth(f);
            for (Polygon strokeWidth : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                strokeWidth.setStrokeWidth(f);
            }
        }

        public final void setStrokeColor(int i) {
            a.super.setStrokeColor(i);
            for (Polygon strokeColor : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                strokeColor.setStrokeColor(i);
            }
        }

        public final void setFillColor(int i) {
            a.super.setFillColor(i);
            for (Polygon fillColor : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                fillColor.setFillColor(i);
            }
        }

        public final void setTitle(String str) {
            a.super.setTitle(str);
            for (Polygon title : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                title.setTitle(str);
            }
        }

        public final void setSnippet(String str) {
            a.super.setSnippet(str);
            for (Polygon snippet : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                snippet.setSnippet(str);
            }
        }

        public final boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView) {
            for (Polygon onSingleTapConfirmed : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                if (onSingleTapConfirmed.onSingleTapConfirmed(motionEvent, mapView)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean contains(MotionEvent motionEvent) {
            for (Polygon contains : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                if (contains.contains(motionEvent)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean onLongPress(MotionEvent motionEvent, MapView mapView) {
            boolean contains = contains(motionEvent);
            if (contains) {
                if (this.mDraggable) {
                    this.mIsDragged = true;
                    closeInfoWindow();
                    this.mDragStartPoint = motionEvent;
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDragStart(this);
                    }
                    moveToEventPosition(motionEvent, this.mDragStartPoint, mapView);
                } else if (this.mOnClickListener != null) {
                    this.mOnClickListener.onLongClick(this, mapView, mapView.getProjection().fromPixels((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
            return contains;
        }

        public final void moveToEventPosition(MotionEvent motionEvent, MotionEvent motionEvent2, MapView mapView) {
            for (Polygon moveToEventPosition : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                moveToEventPosition.moveToEventPosition(motionEvent, motionEvent2, mapView);
            }
        }

        public final void finishMove(MotionEvent motionEvent, MotionEvent motionEvent2, MapView mapView) {
            for (Polygon finishMove : this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY) {
                finishMove.finishMove(motionEvent, motionEvent2, mapView);
            }
        }

        public final boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
            if (this.mDraggable && this.mIsDragged) {
                if (motionEvent.getAction() == 1) {
                    this.mIsDragged = false;
                    finishMove(this.mDragStartPoint, motionEvent, mapView);
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDragEnd(this);
                    }
                    return true;
                } else if (motionEvent.getAction() == 2) {
                    moveToEventPosition(motionEvent, this.mDragStartPoint, mapView);
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDrag(this);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void addFeature(final MapFactory.MapLineString mapLineString) {
        Polyline polyline = new Polyline();
        polyline.setDraggable(mapLineString.Draggable());
        polyline.setTitle(mapLineString.Title());
        polyline.setSnippet(mapLineString.Description());
        polyline.setPoints(mapLineString.getPoints());
        polyline.setColor(mapLineString.StrokeColor());
        polyline.setWidth((float) mapLineString.StrokeWidth());
        polyline.setInfoWindow(this.f318hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0.put(mapLineString, polyline);
        polyline.setOnClickListener(new Polyline.OnClickListener() {
            public final boolean onClick(Polyline polyline, MapView mapView, GeoPoint geoPoint) {
                for (MapFactory.MapEventListener onFeatureClick : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureClick.onFeatureClick(mapLineString);
                }
                if (!mapLineString.EnableInfobox()) {
                    return true;
                }
                polyline.showInfoWindow(geoPoint);
                return true;
            }

            public final boolean onLongClick(Polyline polyline, MapView mapView, GeoPoint geoPoint) {
                for (MapFactory.MapEventListener onFeatureLongPress : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureLongPress.onFeatureLongPress(mapLineString);
                }
                return true;
            }
        });
        polyline.setOnDragListener(new Polyline.OnDragListener() {
            public final void onDragStart(Polyline polyline) {
                for (MapFactory.MapEventListener onFeatureStartDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureStartDrag.onFeatureStartDrag(mapLineString);
                }
            }

            public final void onDrag(Polyline polyline) {
                for (MapFactory.MapEventListener onFeatureDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureDrag.onFeatureDrag(mapLineString);
                }
            }

            public final void onDragEnd(Polyline polyline) {
                mapLineString.updatePoints(polyline.getPoints());
                for (MapFactory.MapEventListener onFeatureStopDrag : a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(a.this)) {
                    onFeatureStopDrag.onFeatureStopDrag(mapLineString);
                }
            }
        });
        if (mapLineString.Visible()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((OverlayWithIW) polyline);
        } else {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((OverlayWithIW) polyline);
        }
    }

    public void addFeature(MapFactory.MapPolygon mapPolygon) {
        d dVar = new d();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Polygon) dVar, (MapFactory.MapFeature) mapPolygon);
        dVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(mapPolygon.getPoints());
        dVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(mapPolygon.getHolePoints());
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapPolygon, (Polygon) dVar);
    }

    public void addFeature(MapFactory.MapCircle mapCircle) {
        Polygon polygon = new Polygon();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(polygon, (MapFactory.MapFeature) mapCircle);
        polygon.setPoints(Polygon.pointsAsCircle(new GeoPoint(mapCircle.Latitude(), mapCircle.Longitude()), mapCircle.Radius()));
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapCircle, polygon);
    }

    public void addFeature(MapFactory.MapRectangle mapRectangle) {
        BoundingBox boundingBox = new BoundingBox(mapRectangle.NorthLatitude(), mapRectangle.EastLongitude(), mapRectangle.SouthLatitude(), mapRectangle.WestLongitude());
        Polygon polygon = new Polygon();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(polygon, (MapFactory.MapFeature) mapRectangle);
        polygon.setPoints(new ArrayList(Polygon.pointsAsRect(boundingBox)));
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapRectangle, polygon);
    }
}
