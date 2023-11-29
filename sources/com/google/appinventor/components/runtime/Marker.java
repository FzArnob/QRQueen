package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HorizontalAlignment;
import com.google.appinventor.components.common.MapFeature;
import com.google.appinventor.components.common.VerticalAlignment;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "<p>An icon positioned at a point to indicate information on a map. Markers can be used to provide an info window, custom fill and stroke colors, and custom images to convey information to the user.</p>", version = 4)
@UsesLibraries({"osmdroid.aar", "androidsvg.jar"})
public class Marker extends MapFeatureBaseWithFill implements MapFactory.MapMarker {
    private static final String TAG = "Marker";
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapMarker) objArr[0], mapRectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapMarker) objArr[0], mapRectangle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapMarker) objArr[0], mapCircle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapMarker) objArr[0], mapCircle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapMarker) objArr[0], mapPolygon));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapMarker) objArr[0], mapPolygon));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapMarker) objArr[0], mapLineString));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapMarker) objArr[0], mapLineString));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            return Double.valueOf(GeometryUtil.distanceBetween((MapFactory.MapMarker) objArr[0], mapMarker));
        }
    };
    private static final MapFactory.MapFeatureVisitor<Double> wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.bearingToCentroid(objArr[0], mapRectangle));
            }
            return Double.valueOf(GeometryUtil.bearingToEdge(objArr[0], mapRectangle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.bearingToCentroid(objArr[0], mapCircle));
            }
            return Double.valueOf(GeometryUtil.bearingToEdge(objArr[0], mapCircle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.bearingToCentroid(objArr[0], mapPolygon));
            }
            return Double.valueOf(GeometryUtil.bearingToEdge(objArr[0], mapPolygon));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.bearingToCentroid(objArr[0], mapLineString));
            }
            return Double.valueOf(GeometryUtil.bearingToEdge(objArr[0], mapLineString));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            return Double.valueOf(GeometryUtil.bearingTo(objArr[0], mapMarker));
        }
    };
    private int PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = -1;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private HorizontalAlignment f236hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = HorizontalAlignment.Center;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private VerticalAlignment f237hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = VerticalAlignment.Bottom;
    private String imagePath = "";
    private int opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = -1;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private GeoPoint f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new GeoPoint(0.0d, 0.0d);

    @SimpleProperty(userVisible = false)
    public void ShowShadow(boolean z) {
    }

    @SimpleProperty(description = "Gets whether or not the shadow of the Marker is shown.")
    public boolean ShowShadow() {
        return false;
    }

    public Marker(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        mapFeatureContainer.addFeature(this);
        ShowShadow(false);
        AnchorHorizontal(3);
        AnchorVertical(3);
        ImageAsset("");
        Width(-1);
        Height(-1);
        Latitude(0.0d);
        Longitude(0.0d);
    }

    @Options(MapFeature.class)
    @SimpleProperty(description = "Returns the type of the feature. For Markers, this returns MapFeature.Marker (\"Marker\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.Marker;
    }

    @DesignerProperty(defaultValue = "0", editorType = "latitude")
    @SimpleProperty
    public void Latitude(double d) {
        Log.d(TAG, "Latitude");
        if (d < -90.0d || d > 90.0d) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Latitude", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(d));
            return;
        }
        this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setLatitude(d);
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapMarker) this);
    }

    @SimpleProperty
    public double Latitude() {
        return this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getLatitude();
    }

    @DesignerProperty(defaultValue = "0", editorType = "longitude")
    @SimpleProperty
    public void Longitude(double d) {
        Log.d(TAG, "Longitude");
        if (d < -180.0d || d > 180.0d) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Longitude", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(d));
            return;
        }
        this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setLongitude(d);
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapMarker) this);
    }

    @SimpleProperty
    public double Longitude() {
        return this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getLongitude();
    }

    @DesignerProperty(editorType = "image_asset")
    @SimpleProperty
    public void ImageAsset(@Asset String str) {
        Log.d(TAG, "ImageAsset");
        this.imagePath = str;
        this.map.getController().updateFeatureImage(this);
    }

    @SimpleProperty(description = "The ImageAsset property is used to provide an alternative image for the Marker.")
    public String ImageAsset() {
        return this.imagePath;
    }

    @SimpleProperty
    public void StrokeColor(int i) {
        super.StrokeColor(i);
        this.map.getController().updateFeatureStroke(this);
    }

    @DesignerProperty(defaultValue = "3", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AnchorHorizontal(@Options(HorizontalAlignment.class) int i) {
        HorizontalAlignment fromUnderlyingValue = HorizontalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.container.$form().dispatchErrorOccurredEvent(this, "AnchorHorizontal", ErrorMessages.ERROR_INVALID_ANCHOR_HORIZONTAL, Integer.valueOf(i));
            return;
        }
        AnchorHorizontalAbstract(fromUnderlyingValue);
    }

    @Options(HorizontalAlignment.class)
    @SimpleProperty(description = "The horizontal alignment property controls where the Marker's anchor is located relative to its width. The choices are: 1 = left aligned, 3 = horizontally centered, 2 = right aligned.")
    public int AnchorHorizontal() {
        return AnchorHorizontalAbstract().toUnderlyingValue().intValue();
    }

    public HorizontalAlignment AnchorHorizontalAbstract() {
        return this.f236hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void AnchorHorizontalAbstract(HorizontalAlignment horizontalAlignment) {
        if (horizontalAlignment != this.f236hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) {
            this.f236hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = horizontalAlignment;
            this.map.getController().updateFeaturePosition((MapFactory.MapMarker) this);
        }
    }

    @DesignerProperty(defaultValue = "3", editorType = "vertical_alignment")
    @SimpleProperty
    public void AnchorVertical(@Options(VerticalAlignment.class) int i) {
        VerticalAlignment fromUnderlyingValue = VerticalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.container.$form().dispatchErrorOccurredEvent(this, "AnchorVertical", ErrorMessages.ERROR_INVALID_ANCHOR_VERTICAL, Integer.valueOf(i));
            return;
        }
        AnchorVerticalAbstract(fromUnderlyingValue);
    }

    @Options(VerticalAlignment.class)
    @SimpleProperty(description = "The vertical alignment property controls where the Marker's anchor is located relative to its height. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.")
    public int AnchorVertical() {
        return AnchorVerticalAbstract().toUnderlyingValue().intValue();
    }

    public VerticalAlignment AnchorVerticalAbstract() {
        return this.f237hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void AnchorVerticalAbstract(VerticalAlignment verticalAlignment) {
        if (verticalAlignment != null) {
            this.f237hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = verticalAlignment;
            this.map.getController().updateFeaturePosition((MapFactory.MapMarker) this);
        }
    }

    @SimpleProperty
    public void Width(int i) {
        this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = i;
        this.map.getController().updateFeatureSize(this);
    }

    @SimpleProperty
    public int Width() {
        int i = this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY;
        if (i == -2) {
            return this.map.getView().getWidth();
        }
        return i < -1000 ? (int) (((((double) (-i)) - 0.00408935546875d) / 100.0d) * ((double) this.map.getView().getWidth())) : i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void WidthPercent(int i) {
        this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = -1000 - i;
        this.map.getController().updateFeatureSize(this);
    }

    @SimpleProperty
    public void Height(int i) {
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = i;
        this.map.getController().updateFeatureSize(this);
    }

    @SimpleProperty
    public int Height() {
        int i = this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
        if (i == -2) {
            return this.map.getView().getHeight();
        }
        return i < -1000 ? (int) (((((double) (-i)) - 0.00408935546875d) / 100.0d) * ((double) this.map.getView().getHeight())) : i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void HeightPercent(int i) {
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = -1000 - i;
        this.map.getController().updateFeatureSize(this);
    }

    @SimpleFunction(description = "Set the location of the marker.")
    public void SetLocation(double d, double d2) {
        Log.d(TAG, "SetLocation");
        this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setCoords(d, d2);
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapMarker) this);
    }

    public double DistanceToPoint(double d, double d2, boolean z) {
        return DistanceToPoint(d, d2);
    }

    @SimpleFunction(description = "Compute the distance, in meters, between a map feature and a latitude, longitude point.")
    public double DistanceToPoint(double d, double d2) {
        return GeometryUtil.distanceBetween((MapFactory.MapMarker) this, new GeoPoint(d, d2));
    }

    @SimpleFunction(description = "Returns the bearing from the Marker to the given latitude and longitude, in degrees from due north.")
    public double BearingToPoint(double d, double d2) {
        return this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.bearingTo(new GeoPoint(d, d2));
    }

    @SimpleFunction(description = "Returns the bearing from the Marker to the given map feature, in degrees from due north. If the centroids parameter is true, the bearing will be to the center of the map feature. Otherwise, the bearing will be computed to the point in the feature nearest the Marker.")
    public double BearingToFeature(MapFactory.MapFeature mapFeature, boolean z) {
        if (mapFeature == null) {
            return -1.0d;
        }
        return ((Double) mapFeature.accept(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, this, Boolean.valueOf(z))).doubleValue();
    }

    public IGeoPoint getLocation() {
        return this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    public void updateLocation(double d, double d2) {
        this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new GeoPoint(d, d2);
        clearGeometry();
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapMarker) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.f238wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
    }
}
