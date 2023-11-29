package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "LineString", version = 2)
public class LineString extends MapFeatureBase implements MapFactory.MapLineString {
    private static final String TAG = "LineString";
    private static final MapFactory.MapFeatureVisitor<Double> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MapFactory.MapFeatureVisitor<Double>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) objArr[0], mapRectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) objArr[0], mapRectangle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) objArr[0], mapCircle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) objArr[0], mapCircle));
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((MapFactory.MapLineString) objArr[0], mapPolygon));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((MapFactory.MapLineString) objArr[0], mapPolygon));
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapLineString, (MapFactory.MapLineString) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapLineString, (MapFactory.MapLineString) objArr[0]));
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            if (objArr[1].booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(mapMarker, (MapFactory.MapLineString) objArr[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(mapMarker, (MapFactory.MapLineString) objArr[0]));
        }
    };
    private List<GeoPoint> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = new ArrayList();

    public LineString(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        StrokeWidth(3);
        mapFeatureContainer.addFeature(this);
    }

    @Options(MapFeature.class)
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The type of the map feature.")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.LineString;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A list of latitude and longitude pairs that represent the line segments of the polyline.")
    public YailList Points() {
        return GeometryUtil.pointsListToYailList(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }

    @SimpleProperty
    public void Points(YailList yailList) {
        if (yailList.size() < 2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(yailList.length() - 1));
            return;
        }
        try {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = GeometryUtil.pointsFromYailList(yailList);
            clearGeometry();
            this.map.getController().updateFeaturePosition((MapFactory.MapLineString) this);
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", e.getErrorCode(), e.getArguments());
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void PointsFromString(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() >= 2) {
                int length = jSONArray.length();
                int i = 0;
                while (i < length) {
                    JSONArray optJSONArray = jSONArray.optJSONArray(i);
                    if (optJSONArray == null) {
                        throw new DispatchableError(ErrorMessages.ERROR_EXPECTED_ARRAY_AT_INDEX, Integer.valueOf(i), jSONArray.get(i).toString());
                    } else if (optJSONArray.length() >= 2) {
                        double optDouble = optJSONArray.optDouble(0, Double.NaN);
                        double optDouble2 = optJSONArray.optDouble(1, Double.NaN);
                        if (!GeometryUtil.isValidLatitude(optDouble)) {
                            throw new DispatchableError(ErrorMessages.ERROR_INVALID_LATITUDE_IN_POINT_AT_INDEX, Integer.valueOf(i), jSONArray.get(0).toString());
                        } else if (GeometryUtil.isValidLongitude(optDouble2)) {
                            arrayList.add(new GeoPoint(optDouble, optDouble2));
                            i++;
                        } else {
                            throw new DispatchableError(ErrorMessages.ERROR_INVALID_LONGITUDE_IN_POINT_AT_INDEX, Integer.valueOf(i), jSONArray.get(1).toString());
                        }
                    } else {
                        throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_FIELDS, Integer.valueOf(i), Integer.valueOf(str.length()));
                    }
                }
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = arrayList;
                clearGeometry();
                this.map.getController().updateFeaturePosition((MapFactory.MapLineString) this);
                return;
            }
            throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(jSONArray.length()));
        } catch (JSONException e) {
            Log.e(TAG, "Malformed string to LineString.PointsFromString", e);
            Form $form = this.container.$form();
            $form.dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_LINESTRING_PARSE_ERROR, e.getMessage());
        } catch (DispatchableError e2) {
            Form $form2 = this.container.$form();
            int errorCode = e2.getErrorCode();
            StringBuilder sb = new StringBuilder();
            sb.append(e2.getArguments());
            $form2.dispatchErrorOccurredEvent(this, "PointsFromString", errorCode, sb.toString());
        }
    }

    @DesignerProperty(defaultValue = "3")
    @SimpleProperty
    public void StrokeWidth(int i) {
        super.StrokeWidth(i);
    }

    public List<GeoPoint> getPoints() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    public <T> T accept(MapFactory.MapFeatureVisitor<T> mapFeatureVisitor, Object... objArr) {
        return mapFeatureVisitor.visit((MapFactory.MapLineString) this, objArr);
    }

    /* access modifiers changed from: protected */
    public Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }

    public void updatePoints(List<GeoPoint> list) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = list;
        clearGeometry();
    }
}
