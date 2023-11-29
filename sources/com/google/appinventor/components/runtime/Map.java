package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.MapType;
import com.google.appinventor.components.common.ScaleUnits;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FileWriteOperation;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.ScopedFile;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.util.BoundingBox;

@DesignerComponent(category = ComponentCategory.MAPS, description = "<p>A two-dimensional container that renders map tiles in the background and allows for multiple Marker elements to identify points on the map. Map tiles are supplied by OpenStreetMap contributors and the United States Geological Survey.</p><p>The Map component provides three utilities for manipulating its boundaries within App Inventor. First, a locking mechanism is provided to allow the map to be moved relative to other components on the Screen. Second, when unlocked, the user can pan the Map to any location. At this new location, the &quot;Set Initial Boundary&quot; button can be pressed to save the current Map coordinates to its properties. Lastly, if the Map is moved to a different location, for example to add Markers off-screen, then the &quot;Reset Map to Initial Bounds&quot; button can be used to re-center the Map at the starting location.</p>", version = 6)
@UsesLibraries({"osmdroid.aar", "osmdroid.jar", "androidsvg.jar", "jts.jar"})
@SimpleObject
@UsesAssets(fileNames = "location.png, marker.svg")
@UsesPermissions({"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})
public class Map extends MapFeatureContainerBase implements MapFactory.MapEventListener {
    private static final String TAG = "Map";
    private final Handler androidUIHandler = new Handler();
    /* access modifiers changed from: private */
    public Form form;
    private boolean havePermission = false;
    private MapFactory.MapController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private LocationSensor wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;

    public Map getMap() {
        return this;
    }

    public Map(ComponentContainer componentContainer) {
        super(componentContainer);
        this.form = componentContainer.$form();
        Log.d(TAG, "Map.<init>");
        componentContainer.$add(this);
        Width(176);
        Height(144);
        CenterFromString("42.359144, -71.093612");
        ZoomLevel(13);
        EnableZoom(true);
        EnablePan(true);
        MapTypeAbstract(MapType.Road);
        ShowCompass(false);
        LocationSensor(new LocationSensor(componentContainer.$form(), false));
        ShowUser(false);
        ShowZoom(false);
        EnableRotation(false);
        ShowScale(false);
    }

    public View getView() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            MapFactory.MapController newMap = MapFactory.newMap(this.container.$form());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = newMap;
            newMap.addEventListener(this);
        }
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getView();
    }

    @DesignerProperty(defaultValue = "42.359144, -71.093612", editorType = "geographic_point")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "<p>Set the initial center coordinate of the map. The value is specified as a comma-separated pair of decimal latitude and longitude coordinates, for example, <code>42.359144, -71.093612</code>.</p><p>In blocks code, it is recommended for performance reasons to use SetCenter with numerical latitude and longitude rather than convert to the string representation for use with this property.</p>")
    public void CenterFromString(String str) {
        String[] split = str.split(",");
        if (split.length != 2) {
            String str2 = TAG;
            Log.e(str2, str + " is not a valid point.");
            InvalidPoint(str + " is not a valid point.");
            return;
        }
        try {
            double parseDouble = Double.parseDouble(split[0].trim());
            try {
                double parseDouble2 = Double.parseDouble(split[1].trim());
                if (parseDouble > 90.0d || parseDouble < -90.0d) {
                    InvalidPoint(String.format("Latitude %f is out of bounds.", new Object[]{Double.valueOf(parseDouble)}));
                } else if (parseDouble2 > 180.0d || parseDouble2 < -180.0d) {
                    InvalidPoint(String.format("Longitude %f is out of bounds.", new Object[]{Double.valueOf(parseDouble2)}));
                } else {
                    String str3 = TAG;
                    Log.i(str3, "Setting center to " + parseDouble + ", " + parseDouble2);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCenter(parseDouble, parseDouble2);
                }
            } catch (NumberFormatException unused) {
                Log.e(TAG, String.format("%s is not a valid number.", new Object[]{split[1]}));
                InvalidPoint(String.format("%s is not a valid number.", new Object[]{split[1]}));
            }
        } catch (NumberFormatException unused2) {
            Log.e(TAG, String.format("%s is not a valid number.", new Object[]{split[0]}));
            InvalidPoint(String.format("%s is not a valid number.", new Object[]{split[0]}));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The latitude of the center of the map.")
    public double Latitude() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLatitude();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The longitude of the center of the map.")
    public double Longitude() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLongitude();
    }

    @DesignerProperty(defaultValue = "13", editorType = "map_zoom")
    @SimpleProperty
    public void ZoomLevel(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoom(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The zoom level of the map. Valid values of ZoomLevel are dependent on the tile provider and the latitude and longitude of the map. For example, zoom levels are more constrained over oceans than dense city centers to conserve space for storing tiles, so valid values may be 1-7 over ocean and 1-18 over cities. Tile providers may send warning or error tiles if the zoom level is too great for the server to support.")
    public int ZoomLevel() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getZoom();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnableZoom(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If this property is set to true, multitouch zoom gestures are allowed on the map. Otherwise, the map zoom cannot be changed by the user except via the zoom control buttons.")
    public boolean EnableZoom() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomEnabled();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    public void Rotation(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotation(f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets or gets the rotation of the map in decimal degrees if any")
    public float Rotation() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRotation();
    }

    @DesignerProperty(defaultValue = "1", editorType = "map_type")
    @SimpleProperty
    public void MapType(@Options(MapType.class) int i) {
        MapType fromUnderlyingValue = MapType.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            MapTypeAbstract(fromUnderlyingValue);
        }
    }

    @Options(MapType.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The type of tile layer to use as the base of the map. Valid values are: 1 (Roads), 2 (Aerial), 3 (Terrain)")
    public int MapType() {
        return MapTypeAbstract().toUnderlyingValue().intValue();
    }

    public MapType MapTypeAbstract() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapTypeAbstract();
    }

    public void MapTypeAbstract(MapType mapType) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapTypeAbstract(mapType);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowCompass(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompassEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show a compass icon rotated based on user orientation.")
    public boolean ShowCompass() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompassEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowZoom(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show zoom buttons on the map.")
    public boolean ShowZoom() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomControlEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowUser(final boolean z) {
        if (!this.havePermission) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    Map.this.form.askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, true);
                                Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, z);
                                return;
                            }
                            Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, false);
                            Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, z);
                            Map.this.form.dispatchPermissionDeniedEvent((Component) Map.this, "ShowUser", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        } else {
            vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(z);
        }
    }

    private void vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(boolean z) {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setShowUserEnabled(z);
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "ShowUser", e);
        } catch (Exception e2) {
            String str = TAG;
            Log.e(str, e2.getMessage());
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Show the user's location on the map.")
    public boolean ShowUser() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isShowUserEnabled();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void EnableRotation(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotationEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to true, the user can use multitouch gestures to rotate the map around its current center.")
    public boolean EnableRotation() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRotationEnabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnablePan(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPanEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable two-finger panning of the Map")
    public boolean EnablePan() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isPanEnabled();
    }

    @SimpleProperty
    public void BoundingBox(YailList yailList) {
        double coerceToDouble = GeometryUtil.coerceToDouble(((YailList) yailList.get(1)).get(1));
        double coerceToDouble2 = GeometryUtil.coerceToDouble(((YailList) yailList.get(1)).get(2));
        double coerceToDouble3 = GeometryUtil.coerceToDouble(((YailList) yailList.get(2)).get(1));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBoundingBox(new BoundingBox(coerceToDouble, GeometryUtil.coerceToDouble(((YailList) yailList.get(2)).get(2)), coerceToDouble3, coerceToDouble2));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Bounding box for the map stored as [[North, West], [South, East]].")
    public YailList BoundingBox() {
        BoundingBox boundingBox = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBoundingBox();
        return YailList.makeList((Object[]) new YailList[]{YailList.makeList((Object[]) new Double[]{Double.valueOf(boundingBox.getLatNorth()), Double.valueOf(boundingBox.getLonWest())}), YailList.makeList((Object[]) new Double[]{Double.valueOf(boundingBox.getLatSouth()), Double.valueOf(boundingBox.getLonEast())})});
    }

    @DesignerProperty(editorType = "component:com.google.appinventor.components.runtime.LocationSensor")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Uses the provided LocationSensor for user location data rather than the built-in location provider.")
    public void LocationSensor(LocationSensor locationSensor) {
        LocationSensor.LocationSensorListener locationListener = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLocationListener();
        LocationSensor locationSensor2 = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
        if (locationSensor2 != null) {
            locationSensor2.removeListener(locationListener);
        }
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = locationSensor;
        if (locationSensor != null) {
            locationSensor.addListener(locationListener);
        }
    }

    public LocationSensor LocationSensor() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ShowScale(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScaleVisible(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Shows a scale reference on the map.")
    public boolean ShowScale() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isScaleVisible();
    }

    @DesignerProperty(defaultValue = "1", editorType = "map_unit_system")
    @SimpleProperty
    public void ScaleUnits(@Options(ScaleUnits.class) int i) {
        ScaleUnits fromUnderlyingValue = ScaleUnits.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            $form().dispatchErrorOccurredEvent(this, "ScaleUnits", ErrorMessages.ERROR_INVALID_UNIT_SYSTEM, Integer.valueOf(i));
            return;
        }
        ScaleUnitsAbstract(fromUnderlyingValue);
    }

    @Options(ScaleUnits.class)
    @SimpleProperty
    public int ScaleUnits() {
        return ScaleUnitsAbstract().toUnderlyingValue().intValue();
    }

    public ScaleUnits ScaleUnitsAbstract() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getScaleUnitsAbstract();
    }

    public void ScaleUnitsAbstract(ScaleUnits scaleUnits) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScaleUnitsAbstract(scaleUnits);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's latitude if ShowUser is enabled.")
    public double UserLatitude() {
        try {
            LocationSensor locationSensor = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
            if (locationSensor == null) {
                return -999.0d;
            }
            return locationSensor.Latitude();
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "UserLatitude", e);
            return -999.0d;
        } catch (Exception e2) {
            String str = TAG;
            Log.e(str, e2.getMessage());
            return -999.0d;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user's longitude if ShowUser is enabled.")
    public double UserLongitude() {
        try {
            LocationSensor locationSensor = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
            if (locationSensor == null) {
                return -999.0d;
            }
            return locationSensor.Longitude();
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "UserLongitude", e);
            return -999.0d;
        } catch (Exception e2) {
            String str = TAG;
            Log.e(str, e2.getMessage());
            return -999.0d;
        }
    }

    @SimpleFunction(description = "Pan the map center to the given latitude and longitude and adjust the zoom level to the specified zoom.")
    public void PanTo(double d, double d2, int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.panTo(d, d2, i, 1.0d);
    }

    @SimpleFunction(description = "Create a new marker with default properties at the specified latitude and longitude.")
    public Marker CreateMarker(double d, double d2) {
        Marker marker = new Marker(this);
        marker.SetLocation(d, d2);
        return marker;
    }

    @SimpleFunction(description = "Save the contents of the Map to the specified path.")
    public void Save(String str) {
        final ArrayList arrayList = new ArrayList(this.features);
        if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || str.startsWith("file:/")) {
            final File file = str.startsWith("file:") ? new File(URI.create(str)) : new File(str);
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, arrayList, file);
                }
            });
            return;
        }
        new FileWriteOperation($form(), this, "Save", str, $form().DefaultFileScope()) {
            public final void processFile(ScopedFile scopedFile) {
                Map.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map.this, arrayList, new File(URI.create(FileUtil.resolveFileName(this.form, scopedFile))));
            }
        }.run();
    }

    @SimpleEvent(description = "Map has been initialized and is ready for user interaction.")
    public void Ready() {
        EventDispatcher.dispatchEvent(this, "Ready", new Object[0]);
    }

    @SimpleEvent(description = "User has changed the map bounds by panning or zooming the map.")
    public void BoundsChange() {
        EventDispatcher.dispatchEvent(this, "BoundsChange", new Object[0]);
    }

    @SimpleEvent(description = "User has changed the zoom level of the map.")
    public void ZoomChange() {
        EventDispatcher.dispatchEvent(this, "ZoomChange", new Object[0]);
    }

    @SimpleEvent(description = "An invalid coordinate was supplied during a maps operation. The message parameter will have more details about the issue.")
    public void InvalidPoint(String str) {
        EventDispatcher.dispatchEvent(this, "InvalidPoint", str);
    }

    @SimpleEvent(description = "The user tapped at a point on the map.")
    public void TapAtPoint(double d, double d2) {
        EventDispatcher.dispatchEvent(this, "TapAtPoint", Double.valueOf(d), Double.valueOf(d2));
    }

    @SimpleEvent(description = "The user double-tapped at a point on the map. This event will be followed by a ZoomChanged event if zooming gestures are enabled and the map is not at the highest possible zoom level.")
    public void DoubleTapAtPoint(double d, double d2) {
        EventDispatcher.dispatchEvent(this, "DoubleTapAtPoint", Double.valueOf(d), Double.valueOf(d2));
    }

    @SimpleEvent(description = "The user long-pressed at a point on the map.")
    public void LongPressAtPoint(double d, double d2) {
        EventDispatcher.dispatchEvent(this, "LongPressAtPoint", Double.valueOf(d), Double.valueOf(d2));
    }

    public MapFactory.MapController getController() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onReady(MapFactory.MapController mapController) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.Ready();
            }
        });
    }

    public void onBoundsChanged() {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.BoundsChange();
            }
        });
    }

    public void onZoom() {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.ZoomChange();
            }
        });
    }

    public void onSingleTap(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.TapAtPoint(d3, d4);
            }
        });
    }

    public void onDoubleTap(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.DoubleTapAtPoint(d3, d4);
            }
        });
    }

    public void onLongPress(double d, double d2) {
        final double d3 = d;
        final double d4 = d2;
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                Map.this.LongPressAtPoint(d3, d4);
            }
        });
    }

    public void onFeatureClick(final MapFactory.MapFeature mapFeature) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                mapFeature.Click();
            }
        });
    }

    public void onFeatureLongPress(final MapFactory.MapFeature mapFeature) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                mapFeature.LongClick();
            }
        });
    }

    public void onFeatureStartDrag(final MapFactory.MapFeature mapFeature) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                mapFeature.StartDrag();
            }
        });
    }

    public void onFeatureDrag(final MapFactory.MapFeature mapFeature) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                mapFeature.Drag();
            }
        });
    }

    public void onFeatureStopDrag(final MapFactory.MapFeature mapFeature) {
        this.container.$form().runOnUiThread(new Runnable() {
            public final void run() {
                mapFeature.StopDrag();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapMarker mapMarker) {
        this.features.add(mapMarker);
        mapMarker.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapMarker);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapLineString mapLineString) {
        this.features.add(mapLineString);
        mapLineString.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapLineString);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapPolygon mapPolygon) {
        this.features.add(mapPolygon);
        mapPolygon.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapPolygon);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapRectangle mapRectangle) {
        this.features.add(mapRectangle);
        mapRectangle.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapRectangle);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapCircle mapCircle) {
        this.features.add(mapCircle);
        mapCircle.setMap(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addFeature(mapCircle);
    }

    public void removeFeature(MapFactory.MapFeature mapFeature) {
        this.features.remove(mapFeature);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeFeature(mapFeature);
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map map, List list, File file) {
        try {
            GeoJSONUtil.writeFeaturesAsGeoJSON(list, file.getAbsolutePath());
        } catch (IOException e) {
            map.$form().runOnUiThread(new Runnable() {
                public final void run() {
                    Map.this.$form().dispatchErrorOccurredEvent(Map.this, "Save", ErrorMessages.ERROR_EXCEPTION_DURING_MAP_SAVE, e.getMessage());
                }
            });
        }
    }
}
