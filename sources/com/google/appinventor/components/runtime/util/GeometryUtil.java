package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.IterationError;
import com.google.appinventor.components.runtime.util.MapFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

public final class GeometryUtil {
    public static final double EARTH_RADIUS = 6378137.0d;
    public static final double ONE_DEG_IN_METERS = 111319.49079327358d;
    public static final int WEB_MERCATOR_SRID = 4326;
    private static final GeometryFactory hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new GeometryFactory(new PrecisionModel(), WEB_MERCATOR_SRID);

    public static boolean isValidLatitude(double d) {
        return -90.0d <= d && d <= 90.0d;
    }

    public static boolean isValidLongitude(double d) {
        return -180.0d <= d && d <= 180.0d;
    }

    private GeometryUtil() {
    }

    public static double coerceToDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    public static GeoPoint coerceToPoint(Object obj, Object obj2) {
        double coerceToDouble = coerceToDouble(obj);
        double coerceToDouble2 = coerceToDouble(obj2);
        if (Double.isNaN(coerceToDouble)) {
            throw new IllegalArgumentException("Latitude must be a numeric.");
        } else if (Double.isNaN(coerceToDouble2)) {
            throw new IllegalArgumentException("Longitude must be a numeric.");
        } else if (coerceToDouble < -90.0d || coerceToDouble > 90.0d) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        } else if (coerceToDouble2 >= -180.0d && coerceToDouble2 <= 180.0d) {
            return new GeoPoint(coerceToDouble, coerceToDouble2);
        } else {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }
    }

    public static YailList asYailList(IGeoPoint iGeoPoint) {
        return YailList.makeList(new Object[]{Double.valueOf(iGeoPoint.getLatitude()), Double.valueOf(iGeoPoint.getLongitude())});
    }

    public static YailList pointsListToYailList(List<? extends IGeoPoint> list) {
        ArrayList arrayList = new ArrayList();
        for (IGeoPoint asYailList : list) {
            arrayList.add(asYailList(asYailList));
        }
        return YailList.makeList((List) arrayList);
    }

    public static GeoPoint pointFromYailList(YailList yailList) {
        if (yailList.length() >= 3) {
            try {
                return coerceToPoint(yailList.get(1), yailList.get(2));
            } catch (IllegalArgumentException unused) {
                throw new DispatchableError(ErrorMessages.ERROR_INVALID_POINT, yailList.get(1), yailList.get(2));
            }
        } else {
            throw new DispatchableError(ErrorMessages.ERROR_INVALID_NUMBER_OF_VALUES_IN_POINT, 2, Integer.valueOf(yailList.length() - 1));
        }
    }

    public static List<GeoPoint> pointsFromYailList(YailList yailList) {
        ArrayList arrayList = new ArrayList();
        Iterator it = yailList.iterator();
        it.next();
        int i = 1;
        while (it.hasNext()) {
            try {
                arrayList.add(pointFromYailList((YailList) TypeUtil.castNotNull(it.next(), YailList.class, "list")));
                i++;
            } catch (DispatchableError e) {
                throw IterationError.fromError(i, e);
            }
        }
        return arrayList;
    }

    public static Geometry createGeometry(GeoPoint geoPoint) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPoint(geoPointToCoordinate(geoPoint));
    }

    public static Geometry createGeometry(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLineString(pointsToCoordinates(list));
    }

    public static Geometry createGeometry(double d, double d2, double d3, double d4) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPolygon(new Coordinate[]{new Coordinate(d2, d), new Coordinate(d2, d3), new Coordinate(d4, d3), new Coordinate(d4, d), new Coordinate(d2, d)});
    }

    public static Geometry createGeometry(List<List<GeoPoint>> list, List<List<List<GeoPoint>>> list2) {
        if (list == null) {
            throw new IllegalArgumentException("points must not be null.");
        } else if (list2 == null || list2.isEmpty() || list2.size() == list.size()) {
            int size = list.size();
            Geometry[] geometryArr = new Polygon[size];
            if (list2 == null || list2.isEmpty()) {
                int i = 0;
                for (List<GeoPoint> ringToPolygon : list) {
                    geometryArr[i] = ringToPolygon(ringToPolygon);
                    i++;
                }
            } else {
                Iterator<List<List<GeoPoint>>> it = list2.iterator();
                int i2 = 0;
                for (List<GeoPoint> ringToPolygon2 : list) {
                    geometryArr[i2] = ringToPolygon(ringToPolygon2, it.next());
                    i2++;
                }
            }
            if (size == 1) {
                return geometryArr[0];
            }
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createMultiPolygon(geometryArr);
        } else {
            throw new IllegalArgumentException("holes must either be null or the same length as points.");
        }
    }

    public static GeoPoint getMidpoint(List<GeoPoint> list) {
        if (list.isEmpty()) {
            return new GeoPoint(0.0d, 0.0d);
        }
        if (list.size() == 1) {
            return new GeoPoint(list.get(0));
        }
        return jtsPointToGeoPoint(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLineString(pointsToCoordinates(list)).getCentroid());
    }

    public static GeoPoint getCentroid(List<List<GeoPoint>> list, List<List<List<GeoPoint>>> list2) {
        return jtsPointToGeoPoint(createGeometry(list, list2).getCentroid());
    }

    public static Polygon ringToPolygon(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPolygon(geoPointsToLinearRing(list));
    }

    public static Coordinate[] pointsToCoordinates(List<GeoPoint> list) {
        boolean equals = list.get(0).equals(list.get(list.size() - 1));
        Coordinate[] coordinateArr = new Coordinate[(list.size() + (equals ^ true ? 1 : 0))];
        int i = 0;
        for (GeoPoint geoPointToCoordinate : list) {
            coordinateArr[i] = geoPointToCoordinate(geoPointToCoordinate);
            i++;
        }
        if (!equals) {
            coordinateArr[i] = coordinateArr[0];
        }
        return coordinateArr;
    }

    public static LinearRing geoPointsToLinearRing(List<GeoPoint> list) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createLinearRing(pointsToCoordinates(list));
    }

    public static Polygon ringToPolygon(List<GeoPoint> list, List<List<GeoPoint>> list2) {
        LinearRing geoPointsToLinearRing = geoPointsToLinearRing(list);
        LinearRing[] linearRingArr = new LinearRing[list2.size()];
        int i = 0;
        for (List<GeoPoint> geoPointsToLinearRing2 : list2) {
            linearRingArr[i] = geoPointsToLinearRing(geoPointsToLinearRing2);
            i++;
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createPolygon(geoPointsToLinearRing, linearRingArr);
    }

    public static GeoPoint jtsPointToGeoPoint(Point point) {
        return new GeoPoint(point.getY(), point.getX());
    }

    public static Coordinate geoPointToCoordinate(GeoPoint geoPoint) {
        return new Coordinate(geoPoint.getLongitude(), geoPoint.getLatitude());
    }

    public static double distanceBetween(IGeoPoint iGeoPoint, IGeoPoint iGeoPoint2) {
        double radians = Math.toRadians(iGeoPoint.getLatitude());
        double radians2 = Math.toRadians(iGeoPoint.getLongitude());
        double radians3 = Math.toRadians(iGeoPoint2.getLatitude());
        double pow = Math.pow(Math.sin((radians3 - radians) / 2.0d), 2.0d) + (Math.cos(radians) * Math.cos(radians3) * Math.pow(Math.sin((Math.toRadians(iGeoPoint2.getLongitude()) - radians2) / 2.0d), 2.0d));
        return Math.atan2(Math.sqrt(pow), Math.sqrt(1.0d - pow)) * 2.0d * 6378137.0d;
    }

    public static double distanceBetween(MapFactory.MapMarker mapMarker, GeoPoint geoPoint) {
        return distanceBetween(mapMarker.getLocation(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetween(MapFactory.MapMarker mapMarker, MapFactory.MapMarker mapMarker2) {
        return distanceBetween(mapMarker.getLocation(), mapMarker2.getLocation());
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return mapMarker.getGeometry().distance(mapLineString.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return mapMarker.getGeometry().distance(mapPolygon.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        double distanceTo = ((double) mapMarker.getCentroid().distanceTo(mapCircle.getCentroid())) - mapCircle.Radius();
        if (distanceTo < 0.0d) {
            return 0.0d;
        }
        return distanceTo;
    }

    public static double distanceBetweenEdges(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return mapMarker.getGeometry().distance(mapRectangle.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, GeoPoint geoPoint) {
        return mapLineString.getGeometry().distance(createGeometry(geoPoint)) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapLineString mapLineString2) {
        return mapLineString.getGeometry().distance(mapLineString2.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapPolygon mapPolygon) {
        return mapLineString.getGeometry().distance(mapPolygon.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapCircle mapCircle) {
        double distance = (mapLineString.getGeometry().distance(createGeometry(mapCircle.getCentroid())) * 111319.49079327358d) - mapCircle.Radius();
        if (distance < 0.0d) {
            return 0.0d;
        }
        return distance;
    }

    public static double distanceBetweenEdges(MapFactory.MapLineString mapLineString, MapFactory.MapRectangle mapRectangle) {
        return mapLineString.getGeometry().distance(mapRectangle.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, GeoPoint geoPoint) {
        return mapPolygon.getGeometry().distance(createGeometry(geoPoint)) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapPolygon mapPolygon2) {
        return mapPolygon.getGeometry().distance(mapPolygon2.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapCircle mapCircle) {
        double distance = (mapPolygon.getGeometry().distance(createGeometry(mapCircle.getCentroid())) * 111319.49079327358d) - mapCircle.Radius();
        if (distance < 0.0d) {
            return 0.0d;
        }
        return distance;
    }

    public static double distanceBetweenEdges(MapFactory.MapPolygon mapPolygon, MapFactory.MapRectangle mapRectangle) {
        return mapPolygon.getGeometry().distance(mapRectangle.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, GeoPoint geoPoint) {
        double distanceBetween = distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) geoPoint) - mapCircle.Radius();
        if (distanceBetween < 0.0d) {
            return 0.0d;
        }
        return distanceBetween;
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, MapFactory.MapCircle mapCircle2) {
        double distanceBetween = (distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) mapCircle2.getCentroid()) - mapCircle.Radius()) - mapCircle2.Radius();
        if (distanceBetween < 0.0d) {
            return 0.0d;
        }
        return distanceBetween;
    }

    public static double distanceBetweenEdges(MapFactory.MapCircle mapCircle, MapFactory.MapRectangle mapRectangle) {
        double distance = (mapRectangle.getGeometry().distance(createGeometry(mapCircle.getCentroid())) * 111319.49079327358d) - mapCircle.Radius();
        if (distance < 0.0d) {
            return 0.0d;
        }
        return distance;
    }

    public static double distanceBetweenEdges(MapFactory.MapRectangle mapRectangle, GeoPoint geoPoint) {
        return mapRectangle.getGeometry().distance(createGeometry(geoPoint)) * 111319.49079327358d;
    }

    public static double distanceBetweenEdges(MapFactory.MapRectangle mapRectangle, MapFactory.MapRectangle mapRectangle2) {
        return mapRectangle.getGeometry().distance(mapRectangle2.getGeometry()) * 111319.49079327358d;
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapLineString.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapPolygon.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapMarker.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapLineString mapLineString2) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapLineString2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapPolygon mapPolygon) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapPolygon.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapLineString mapLineString, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapLineString.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapPolygon mapPolygon2) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapPolygon2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapCircle mapCircle) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapCircle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapPolygon mapPolygon, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapPolygon.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, MapFactory.MapCircle mapCircle2) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) mapCircle2.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapCircle mapCircle, MapFactory.MapRectangle mapRectangle) {
        return distanceBetween((IGeoPoint) mapCircle.getCentroid(), (IGeoPoint) mapRectangle.getCentroid());
    }

    public static double distanceBetweenCentroids(MapFactory.MapRectangle mapRectangle, GeoPoint geoPoint) {
        return distanceBetween((IGeoPoint) mapRectangle.getCentroid(), (IGeoPoint) geoPoint);
    }

    public static double distanceBetweenCentroids(MapFactory.MapRectangle mapRectangle, MapFactory.MapRectangle mapRectangle2) {
        return distanceBetween((IGeoPoint) mapRectangle.getCentroid(), (IGeoPoint) mapRectangle2.getCentroid());
    }

    public static double bearingTo(MapFactory.MapMarker mapMarker, MapFactory.MapMarker mapMarker2) {
        return mapMarker.getCentroid().bearingTo(mapMarker2.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return mapMarker.getCentroid().bearingTo(mapLineString.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return mapMarker.getCentroid().bearingTo(mapPolygon.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return mapMarker.getCentroid().bearingTo(mapRectangle.getCentroid());
    }

    public static double bearingToEdge(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return mapMarker.getCentroid().bearingTo(mapCircle.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapLineString mapLineString) {
        return mapMarker.getCentroid().bearingTo(mapLineString.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapPolygon mapPolygon) {
        return mapMarker.getCentroid().bearingTo(mapPolygon.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapRectangle mapRectangle) {
        return mapMarker.getCentroid().bearingTo(mapRectangle.getCentroid());
    }

    public static double bearingToCentroid(MapFactory.MapMarker mapMarker, MapFactory.MapCircle mapCircle) {
        return mapMarker.getCentroid().bearingTo(mapCircle.getCentroid());
    }

    public static List<GeoPoint> polygonToList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        if (jSONArray.length() >= 3) {
            int i = 0;
            while (i < jSONArray.length()) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                if (jSONArray2.length() >= 2) {
                    if (jSONArray2.length() == 2) {
                        arrayList.add(new GeoPoint(jSONArray2.getDouble(0), jSONArray2.getDouble(1)));
                    } else {
                        arrayList.add(new GeoPoint(jSONArray2.getDouble(0), jSONArray2.getDouble(1), jSONArray2.getDouble(2)));
                    }
                    i++;
                } else {
                    throw new JSONException("Invalid number of dimensions in polygon, expected 2.");
                }
            }
            return arrayList;
        }
        throw new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Too few points in Polygon, expected 3.");
    }

    public static List<List<GeoPoint>> multiPolygonToList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray.length() == 0) {
            return arrayList;
        }
        if (jSONArray.getJSONArray(0).optJSONArray(0) == null) {
            arrayList.add(polygonToList(jSONArray));
        } else {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(polygonToList(jSONArray.getJSONArray(i)));
            }
        }
        return arrayList;
    }

    public static YailList multiPolygonToYailList(List<List<GeoPoint>> list) {
        LinkedList linkedList = new LinkedList();
        for (List<GeoPoint> pointsListToYailList : list) {
            linkedList.add(pointsListToYailList(pointsListToYailList));
        }
        return YailList.makeList((List) linkedList);
    }

    public static List<List<GeoPoint>> multiPolygonFromYailList(YailList yailList) {
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = yailList.listIterator(1);
        while (listIterator.hasNext()) {
            arrayList.add(pointsFromYailList((YailList) TypeUtil.castNotNull(listIterator.next(), YailList.class, "list")));
        }
        return arrayList;
    }

    public static List<List<List<GeoPoint>>> multiPolygonHolesFromYailList(YailList yailList) {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        ListIterator listIterator = yailList.listIterator(1);
        while (listIterator.hasNext()) {
            try {
                arrayList.add(multiPolygonFromYailList((YailList) TypeUtil.castNotNull(listIterator.next(), YailList.class, "list")));
                i++;
            } catch (DispatchableError e) {
                throw IterationError.fromError(i, e);
            }
        }
        return arrayList;
    }

    public static List<List<List<GeoPoint>>> multiPolygonHolesToList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray.getJSONArray(0).getJSONArray(0).optJSONArray(0) == null) {
            arrayList.add(multiPolygonToList(jSONArray));
        } else {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(multiPolygonToList(jSONArray.getJSONArray(i)));
            }
        }
        return arrayList;
    }

    public static boolean isPolygon(YailList yailList) {
        if (yailList.size() < 3) {
            return false;
        }
        try {
            pointFromYailList((YailList) TypeUtil.castNotNull(yailList.get(1), YailList.class, "list"));
            return true;
        } catch (DispatchableError unused) {
            return false;
        }
    }

    public static boolean isMultiPolygon(YailList yailList) {
        if (yailList.size() <= 0 || !isPolygon((YailList) TypeUtil.castNotNull(yailList.get(1), YailList.class, "list"))) {
            return false;
        }
        return true;
    }
}
