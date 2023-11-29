package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.MapFeature;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "Circle", version = 2)
public class Circle extends PolygonBase implements MapFactory.MapCircle {
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapCircle) objArr[0], mapRectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapCircle) objArr[0], mapRectangle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapCircle, (MapFactory.MapCircle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapCircle, (MapFactory.MapCircle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon, (MapFactory.MapCircle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon, (MapFactory.MapCircle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString, (MapFactory.MapCircle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString, (MapFactory.MapCircle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker, (MapFactory.MapCircle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker, (MapFactory.MapCircle) objArr[0]));
        }
    };
    private double B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private double f71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private GeoPoint f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new GeoPoint(0.0d, 0.0d);
    private double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public Circle(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        mapFeatureContainer.addFeature(this);
    }

    @Options(MapFeature.class)
    @SimpleProperty(description = "Returns the type of the feature. For Circles, this returns MapFeature.Circle (\"Circle\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.Circle;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty
    public void Radius(double d) {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapCircle) this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The radius of the circle in meters.")
    public double Radius() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty(defaultValue = "0", editorType = "latitude")
    @SimpleProperty
    public void Latitude(double d) {
        if (GeometryUtil.isValidLatitude(d)) {
            this.f71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = d;
            this.f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLatitude(d);
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapCircle) this);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "Latitude", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The latitude of the center of the circle.")
    public double Latitude() {
        return this.f71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @DesignerProperty(defaultValue = "0", editorType = "longitude")
    @SimpleProperty
    public void Longitude(double d) {
        if (GeometryUtil.isValidLongitude(d)) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = d;
            this.f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLongitude(d);
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapCircle) this);
            return;
        }
        getDispatchDelegate().dispatchErrorOccurredEvent(this, "Longitude", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The longitude of the center of the circle.")
    public double Longitude() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @SimpleFunction(description = "Set the center of the Circle.")
    public void SetLocation(double d, double d2) {
        if (!GeometryUtil.isValidLatitude(d)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetLocation", ErrorMessages.ERROR_INVALID_LATITUDE, Double.valueOf(d));
        } else if (!GeometryUtil.isValidLongitude(d2)) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "SetLocation", ErrorMessages.ERROR_INVALID_LONGITUDE, Double.valueOf(d2));
        } else {
            this.f71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = d;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = d2;
            this.f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLatitude(d);
            this.f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLongitude(d2);
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapCircle) this);
        }
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapCircle) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.f72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public void updateCenter(double d, double d2) {
        this.f71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = d;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = d2;
        clearGeometry();
    }
}
