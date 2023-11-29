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
import com.google.appinventor.components.runtime.util.YailList;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "Rectangle", version = 2)
public class Rectangle extends PolygonBase implements MapFactory.MapRectangle {
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapRectangle, (MapFactory.MapRectangle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapRectangle, (MapFactory.MapRectangle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapCircle, (MapFactory.MapRectangle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapCircle, (MapFactory.MapRectangle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon, (MapFactory.MapRectangle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon, (MapFactory.MapRectangle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString, (MapFactory.MapRectangle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString, (MapFactory.MapRectangle) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker, (MapFactory.MapRectangle) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker, (MapFactory.MapRectangle) objArr[0]));
        }
    };
    private double ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = 0.0d;
    private double KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = 0.0d;
    private double sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = 0.0d;
    private double wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = 0.0d;

    public Rectangle(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        mapFeatureContainer.addFeature(this);
    }

    @Options(MapFeature.class)
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the feature. For rectangles, this returns MapFeature.Rectangle (\"Rectangle\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.Rectangle;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void EastLongitude(double d) {
        this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double EastLongitude() {
        return this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void NorthLatitude(double d) {
        this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double NorthLatitude() {
        return this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void SouthLatitude(double d) {
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double SouthLatitude() {
        return this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void WestLongitude(double d) {
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = d;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
    }

    @SimpleProperty
    public double WestLongitude() {
        return this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;
    }

    @SimpleFunction(description = "Returns the center of the Rectangle as a list of the form (Latitude Longitude).")
    public YailList Center() {
        return GeometryUtil.asYailList(getCentroid());
    }

    @SimpleFunction(description = "Returns the bounding box of the Rectangle in the format ((North West) (South East)).")
    public YailList Bounds() {
        return YailList.makeList((Object[]) new YailList[]{YailList.makeList((Object[]) new Double[]{Double.valueOf(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2), Double.valueOf(this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0)}), YailList.makeList((Object[]) new Double[]{Double.valueOf(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb), Double.valueOf(this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud)})});
    }

    @SimpleFunction(description = "Moves the Rectangle so that it is centered on the given latitude and longitude while attempting to maintain the width and height of the Rectangle as measured from the center to the edges.")
    public void SetCenter(double d, double d2) {
        if (d < -90.0d || d > 90.0d) {
            this.container.$form().dispatchErrorOccurredEvent(this, "SetCenter", ErrorMessages.ERROR_INVALID_POINT, Double.valueOf(d), Double.valueOf(d2));
        } else if (d2 < -180.0d || d2 > 180.0d) {
            this.container.$form().dispatchErrorOccurredEvent(this, "SetCenter", ErrorMessages.ERROR_INVALID_POINT, Double.valueOf(d), Double.valueOf(d2));
        } else {
            GeoPoint centroid = getCentroid();
            GeoPoint geoPoint = new GeoPoint(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, centroid.getLongitude());
            GeoPoint geoPoint2 = new GeoPoint(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb, centroid.getLongitude());
            GeoPoint geoPoint3 = new GeoPoint(centroid.getLatitude(), this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud);
            GeoPoint geoPoint4 = new GeoPoint(centroid.getLatitude(), this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0);
            double distanceBetween = GeometryUtil.distanceBetween((IGeoPoint) geoPoint, (IGeoPoint) geoPoint2) / 2.0d;
            double distanceBetween2 = GeometryUtil.distanceBetween((IGeoPoint) geoPoint3, (IGeoPoint) geoPoint4) / 2.0d;
            centroid.setCoords(d, d2);
            this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = centroid.destinationPoint(distanceBetween, 0.0f).getLatitude();
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = centroid.destinationPoint(distanceBetween, 180.0f).getLatitude();
            this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = centroid.destinationPoint(distanceBetween2, 90.0f).getLongitude();
            this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = centroid.destinationPoint(distanceBetween2, 270.0f).getLongitude();
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapRectangle) this);
        }
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapRectangle) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2, this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud, this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb, this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0);
    }

    public void updateBounds(double d, double d2, double d3, double d4) {
        this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = d;
        this.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = d2;
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = d3;
        this.ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = d4;
        clearGeometry();
    }
}
