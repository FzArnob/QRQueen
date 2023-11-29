package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
public abstract class MapFeatureBase implements MapFactory.HasStroke, MapFactory.MapFeature {
    private MapFactory.MapFeatureVisitor<Double> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapRectangle, objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapRectangle, objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapCircle, objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapCircle, objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon, objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon, objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString, objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString, objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            return Double.valueOf(GeometryUtil.distanceBetween(mapMarker, objArr[0]));
        }
    };

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private GeoPoint f234B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
    private String G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = "";
    private boolean IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = false;
    private String IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = "";
    private int SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = -16777216;
    private int YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = 1;
    protected MapFactory.MapFeatureContainer container = null;
    private final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Geometry f235hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    protected Map map = null;
    private boolean sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = false;
    private float tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = 1.0f;
    private boolean visible = true;

    /* access modifiers changed from: protected */
    public abstract Geometry computeGeometry();

    protected MapFeatureBase(MapFactory.MapFeatureContainer mapFeatureContainer, MapFactory.MapFeatureVisitor<Double> mapFeatureVisitor) {
        this.container = mapFeatureContainer;
        this.map = mapFeatureContainer.getMap();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = mapFeatureVisitor;
        Description("");
        Draggable(false);
        EnableInfobox(false);
        StrokeColor(-16777216);
        StrokeOpacity(1.0f);
        StrokeWidth(1);
        Title("");
        Visible(true);
    }

    public void setMap(MapFactory.MapFeatureContainer mapFeatureContainer) {
        this.map = mapFeatureContainer.getMap();
    }

    public void removeFromMap() {
        this.map.getController().removeFeature(this);
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty
    public void Visible(boolean z) {
        if (this.visible != z) {
            this.visible = z;
            if (z) {
                this.map.getController().showFeature(this);
            } else {
                this.map.getController().hideFeature(this);
            }
            this.map.getView().invalidate();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public boolean Visible() {
        return this.visible;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public void StrokeColor(int i) {
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = i;
        this.map.getController().updateFeatureStroke(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The paint color used to outline the map feature.")
    public int StrokeColor() {
        return this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty
    public void StrokeOpacity(float f) {
        this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = f;
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = (Math.round(f * 255.0f) << 24) | (this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 & 16777215);
        this.map.getController().updateFeatureStroke(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The opacity of the stroke used to outline the map feature.")
    public float StrokeOpacity() {
        return this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;
    }

    @DesignerProperty(defaultValue = "1", editorType = "integer")
    @SimpleProperty
    public void StrokeWidth(int i) {
        this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = i;
        this.map.getController().updateFeatureStroke(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of the stroke used to outline the map feature.")
    public int StrokeWidth() {
        return this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Draggable(boolean z) {
        this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = z;
        this.map.getController().updateFeatureDraggable(this);
    }

    @SimpleProperty(description = "The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.")
    public boolean Draggable() {
        return this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;
    }

    @DesignerProperty
    @SimpleProperty
    public void Title(String str) {
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = str;
        this.map.getController().updateFeatureText(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The title displayed in the info window that appears when the user clicks on the map feature.")
    public String Title() {
        return this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    @DesignerProperty
    @SimpleProperty
    public void Description(String str) {
        this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = str;
        this.map.getController().updateFeatureText(this);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The description displayed in the info window that appears when the user clicks on the map feature.")
    public String Description() {
        return this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void EnableInfobox(boolean z) {
        this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = z;
        this.map.getController().updateFeatureText(this);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable or disable the infobox window display when the user taps the feature.")
    public boolean EnableInfobox() {
        return this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt;
    }

    @SimpleFunction(description = "Show the infobox for the feature. This will show the infobox even if {@link #EnableInfobox} is set to false.")
    public void ShowInfobox() {
        this.map.getController().showInfobox(this);
    }

    @SimpleFunction(description = "Hide the infobox if it is shown. If the infobox is not visible this function has no effect.")
    public void HideInfobox() {
        this.map.getController().hideInfobox(this);
    }

    public YailList Centroid() {
        return GeometryUtil.asYailList(getCentroid());
    }

    @SimpleFunction(description = "Compute the distance, in meters, between a map feature and a latitude, longitude point.")
    public double DistanceToPoint(double d, double d2, boolean z) {
        return ((Double) accept(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, new GeoPoint(d, d2), Boolean.valueOf(z))).doubleValue();
    }

    @SimpleFunction(description = "Compute the distance, in meters, between two map features.")
    public double DistanceToFeature(MapFactory.MapFeature mapFeature, boolean z) {
        if (mapFeature == null) {
            return -1.0d;
        }
        return ((Double) mapFeature.accept(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, Boolean.valueOf(z))).doubleValue();
    }

    @SimpleEvent(description = "The user clicked on the feature.")
    public void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
        this.container.FeatureClick(this);
    }

    @SimpleEvent(description = "The user long-pressed on the feature. This event will only trigger if Draggable is false.")
    public void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
        this.container.FeatureLongClick(this);
    }

    @SimpleEvent(description = "The user started a drag operation.")
    public void StartDrag() {
        EventDispatcher.dispatchEvent(this, "StartDrag", new Object[0]);
        this.container.FeatureStartDrag(this);
    }

    @SimpleEvent(description = "The user dragged the map feature.")
    public void Drag() {
        EventDispatcher.dispatchEvent(this, "Drag", new Object[0]);
        this.container.FeatureDrag(this);
    }

    @SimpleEvent(description = "The user stopped a drag operation.")
    public void StopDrag() {
        EventDispatcher.dispatchEvent(this, "StopDrag", new Object[0]);
        this.container.FeatureStopDrag(this);
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this.map.getDispatchDelegate();
    }

    public final synchronized GeoPoint getCentroid() {
        if (this.f234B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
            this.f234B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = GeometryUtil.jtsPointToGeoPoint(getGeometry().getCentroid());
        }
        return this.f234B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public final synchronized Geometry getGeometry() {
        if (this.f235hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f235hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = computeGeometry();
        }
        return this.f235hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    /* access modifiers changed from: protected */
    public final synchronized void clearGeometry() {
        this.f234B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
        this.f235hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    }
}
