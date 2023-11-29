package com.google.appinventor.components.runtime;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.MapFeature;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "Polygon", version = 2)
public class Polygon extends PolygonBase implements MapFactory.MapPolygon {
    private static final String TAG = "Polygon";
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapPolygon) objArr[0], mapRectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapPolygon) objArr[0], mapRectangle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapPolygon) objArr[0], mapCircle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapPolygon) objArr[0], mapCircle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapPolygon, (MapFactory.MapPolygon) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapPolygon, (MapFactory.MapPolygon) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString, (MapFactory.MapPolygon) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString, (MapFactory.MapPolygon) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker, (MapFactory.MapPolygon) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker, (MapFactory.MapPolygon) objArr[0]));
        }
    };
    private List<List<GeoPoint>> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = new ArrayList();
    private boolean initialized = false;
    private List<List<List<GeoPoint>>> sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = new ArrayList();
    private boolean t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = false;

    public Polygon(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        mapFeatureContainer.addFeature(this);
    }

    public void Initialize() {
        this.initialized = true;
        clearGeometry();
        this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
        this.map.getController().updateFeatureHoles(this);
        this.map.getController().updateFeatureText(this);
    }

    @Options(MapFeature.class)
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the feature. For polygons, this returns MapFeature.Polygon (\"Polygon\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.Polygon;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw the polygon.")
    public YailList Points() {
        if (this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (!this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg) {
            return GeometryUtil.pointsListToYailList(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.get(0));
        }
        LinkedList linkedList = new LinkedList();
        for (List<GeoPoint> pointsListToYailList : this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH) {
            linkedList.add(GeometryUtil.pointsListToYailList(pointsListToYailList));
        }
        return YailList.makeList((List) linkedList);
    }

    @SimpleProperty
    public void Points(YailList yailList) {
        try {
            if (GeometryUtil.isPolygon(yailList)) {
                this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = false;
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.clear();
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.add(GeometryUtil.pointsFromYailList(yailList));
            } else if (GeometryUtil.isMultiPolygon(yailList)) {
                this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = true;
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = GeometryUtil.multiPolygonFromYailList(yailList);
            } else {
                throw new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            }
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", e.getErrorCode(), e.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Constructs a polygon from the given list of coordinates.")
    public void PointsFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = new ArrayList();
            this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = new ArrayList();
                this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = false;
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
                return;
            }
            List<List<GeoPoint>> multiPolygonToList = GeometryUtil.multiPolygonToList(jSONArray);
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = multiPolygonToList;
            this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = multiPolygonToList.size() > 1;
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition((MapFactory.MapPolygon) this);
            }
        } catch (JSONException e) {
            Form $form = this.container.$form();
            $form.dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, e.getMessage());
        } catch (DispatchableError e2) {
            HandlesEventDispatching dispatchDelegate = getDispatchDelegate();
            int errorCode = e2.getErrorCode();
            StringBuilder sb = new StringBuilder();
            sb.append(e2.getArguments());
            dispatchDelegate.dispatchErrorOccurredEvent(this, "PointsFromString", errorCode, sb.toString());
        }
    }

    @SimpleProperty
    public YailList HolePoints() {
        if (this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (!this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg) {
            return GeometryUtil.multiPolygonToYailList(this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.get(0));
        }
        LinkedList linkedList = new LinkedList();
        for (List<List<GeoPoint>> multiPolygonToYailList : this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb) {
            linkedList.add(GeometryUtil.multiPolygonToYailList(multiPolygonToYailList));
        }
        return YailList.makeList((List) linkedList);
    }

    @SimpleProperty
    public void HolePoints(YailList yailList) {
        try {
            if (yailList.size() == 0) {
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = new ArrayList();
            } else if (this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg) {
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = GeometryUtil.multiPolygonHolesFromYailList(yailList);
            } else if (GeometryUtil.isMultiPolygon(yailList)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(GeometryUtil.multiPolygonFromYailList(yailList));
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = arrayList;
            } else {
                throw new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HolePoints", e.getErrorCode(), e.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(description = "Constructs holes in a polygon from a given list of coordinates per hole.")
    public void HolePointsFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = new ArrayList();
            this.map.getController().updateFeatureHoles(this);
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = new ArrayList();
                this.map.getController().updateFeatureHoles(this);
                return;
            }
            this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = GeometryUtil.multiPolygonHolesToList(jSONArray);
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
            String str2 = TAG;
            Log.d(str2, "Points: " + this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse point string", e);
            Form $form = this.container.$form();
            $form.dispatchErrorOccurredEvent(this, "HolePointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, e.getMessage());
        }
    }

    @SimpleFunction(description = "Returns the centroid of the Polygon as a (latitude, longitude) pair.")
    public YailList Centroid() {
        return super.Centroid();
    }

    public List<List<GeoPoint>> getPoints() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    public List<List<List<GeoPoint>>> getHolePoints() {
        return this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapPolygon) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH, this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb);
    }

    public void updatePoints(List<List<GeoPoint>> list) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.clear();
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.addAll(list);
        clearGeometry();
    }

    public void updateHolePoints(List<List<List<GeoPoint>>> list) {
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.clear();
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb.addAll(list);
        clearGeometry();
    }
}
