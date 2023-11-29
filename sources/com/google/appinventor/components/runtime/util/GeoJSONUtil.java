package com.google.appinventor.components.runtime.util;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.runtime.BluetoothConnectionBase;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.LineString;
import com.google.appinventor.components.runtime.Marker;
import com.google.appinventor.components.runtime.Polygon;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

public final class GeoJSONUtil {
    private static final Map<String, PropertyApplication> oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    private static final Map<String, Integer> yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;

    public interface PropertyApplication {
        void afterConnect(BluetoothConnectionBase bluetoothConnectionBase);

        void apply(MapFactory.MapFeature mapFeature, Object obj);

        void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase);
    }

    static {
        HashMap hashMap = new HashMap();
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = hashMap;
        hashMap.put("black", -16777216);
        hashMap.put("blue", -14575886);
        hashMap.put("cyan", Integer.valueOf(Component.COLOR_CYAN));
        hashMap.put("darkgray", Integer.valueOf(Component.COLOR_DARK_GRAY));
        hashMap.put("gray", Integer.valueOf(Component.COLOR_GRAY));
        hashMap.put("green", Integer.valueOf(Component.COLOR_GREEN));
        hashMap.put("lightgray", Integer.valueOf(Component.COLOR_LIGHT_GRAY));
        Integer valueOf = Integer.valueOf(Component.COLOR_PINK);
        hashMap.put("pink", valueOf);
        hashMap.put("orange", Integer.valueOf(Component.COLOR_ORANGE));
        hashMap.put("pink", valueOf);
        hashMap.put("red", Integer.valueOf(Component.COLOR_RED));
        hashMap.put("white", -1);
        hashMap.put("yellow", Integer.valueOf(Component.COLOR_YELLOW));
        HashMap hashMap2 = new HashMap();
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = hashMap2;
        hashMap2.put("anchorHorizontal".toLowerCase(), new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature).AnchorHorizontal(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("anchorVertical".toLowerCase(), new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature).AnchorHorizontal();
                }
            }
        });
        hashMap2.put("description", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Description(obj.toString());
            }
        });
        hashMap2.put("draggable", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Draggable(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        });
        hashMap2.put("fill", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                int i;
                if (mapFeature instanceof MapFactory.HasFill) {
                    MapFactory.HasFill hasFill = (MapFactory.HasFill) mapFeature;
                    if (obj instanceof Number) {
                        i = ((Number) obj).intValue();
                    } else {
                        i = GeoJSONUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(obj.toString());
                    }
                    hasFill.FillColor(i);
                }
            }
        });
        hashMap2.put("fill-opacity", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.HasFill) {
                    ((MapFactory.HasFill) mapFeature).FillOpacity(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("height", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature).Height(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("image", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature).ImageAsset(obj.toString());
                }
            }
        });
        hashMap2.put("infobox", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.EnableInfobox(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        });
        hashMap2.put("stroke", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                int i;
                if (mapFeature instanceof MapFactory.HasStroke) {
                    MapFactory.HasStroke hasStroke = (MapFactory.HasStroke) mapFeature;
                    if (obj instanceof Number) {
                        i = ((Number) obj).intValue();
                    } else {
                        i = GeoJSONUtil.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(obj.toString());
                    }
                    hasStroke.StrokeColor(i);
                }
            }
        });
        hashMap2.put("stroke-opacity", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.HasStroke) {
                    ((MapFactory.HasStroke) mapFeature).StrokeOpacity(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("stroke-width", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.HasStroke) {
                    ((MapFactory.HasStroke) mapFeature).StrokeWidth(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("title", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Title(obj.toString());
            }
        });
        hashMap2.put("width", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                if (mapFeature instanceof MapFactory.MapMarker) {
                    ((MapFactory.MapMarker) mapFeature).Width(GeoJSONUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj));
                }
            }
        });
        hashMap2.put("visible", new PropertyApplication() {
            public final void apply(MapFactory.MapFeature mapFeature, Object obj) {
                mapFeature.Visible(GeoJSONUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(obj));
            }
        });
    }

    private GeoJSONUtil() {
    }

    static int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
        String lowerCase = str.toLowerCase();
        Integer num = yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT.get(lowerCase);
        if (num != null) {
            return num.intValue();
        }
        if (lowerCase.startsWith("#")) {
            return vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(lowerCase.substring(1));
        }
        return lowerCase.startsWith("&h") ? vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(lowerCase.substring(2)) : Component.COLOR_RED;
    }

    private static int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        int i = -16777216;
        int i2 = 0;
        if (str.length() == 3) {
            while (i2 < str.length()) {
                int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str.charAt(i2));
                i |= (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME << 4)) << ((2 - i2) << 3);
                i2++;
            }
            return i;
        } else if (str.length() == 6) {
            while (i2 < 3) {
                int i3 = i2 * 2;
                i |= (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str.charAt(i3 + 1)) | (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str.charAt(i3)) << 4)) << ((2 - i2) << 3);
                i2++;
            }
            return i;
        } else if (str.length() == 8) {
            int i4 = 0;
            while (i2 < 4) {
                int i5 = i2 * 2;
                i4 |= (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str.charAt(i5 + 1)) | (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str.charAt(i5)) << 4)) << ((3 - i2) << 3);
                i2++;
            }
            return i4;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if ('a' > c || c > 'f') {
            c2 = 'A';
            if ('A' > c || c > 'F') {
                throw new IllegalArgumentException("Invalid hex character. Expected [0-9A-Fa-f].");
            }
        }
        return (c - c2) + 10;
    }

    public static MapFactory.MapFeature processGeoJSONFeature(String str, MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Iterator it = ((LList) yailList.getCdr()).iterator();
        String str2 = null;
        YailList yailList2 = null;
        YailList yailList3 = null;
        while (it.hasNext()) {
            YailList yailList4 = (YailList) it.next();
            String string = yailList4.getString(0);
            Object object = yailList4.getObject(1);
            if (CommonProperties.TYPE.equals(string)) {
                str2 = (String) object;
            } else if ("geometry".equals(string)) {
                yailList2 = (YailList) object;
            } else if ("properties".equals(string)) {
                yailList3 = (YailList) object;
            } else {
                Log.w(str, String.format("Unsupported field \"%s\" in JSON format", new Object[]{string}));
            }
        }
        if (!"Feature".equals(str2)) {
            throw new IllegalArgumentException(String.format("Unknown type \"%s\"", new Object[]{str2}));
        } else if (yailList2 != null) {
            MapFactory.MapFeature hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, mapFeatureContainer, yailList2);
            if (yailList3 != null) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, yailList3);
            }
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        } else {
            throw new IllegalArgumentException("No geometry defined for feature.");
        }
    }

    private static MapFactory.MapFeature hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Iterator it = ((LList) yailList.getCdr()).iterator();
        YailList yailList2 = null;
        String str2 = null;
        while (it.hasNext()) {
            YailList yailList3 = (YailList) it.next();
            String string = yailList3.getString(0);
            Object object = yailList3.getObject(1);
            if (CommonProperties.TYPE.equals(string)) {
                str2 = (String) object;
            } else if ("coordinates".equals(string)) {
                yailList2 = object;
            } else {
                Log.w(str, String.format("Unsupported field \"%s\" in JSON format", new Object[]{string}));
            }
        }
        if (yailList2 == null) {
            throw new IllegalArgumentException("No coordinates found in GeoJSON Feature");
        } else if (MapFactory.MapFeatureType.TYPE_POINT.equals(str2)) {
            if (yailList2.length() == 3) {
                Marker marker = new Marker(mapFeatureContainer);
                marker.Latitude(((Number) yailList2.get(2)).doubleValue());
                marker.Longitude(((Number) yailList2.get(1)).doubleValue());
                return marker;
            }
            throw new IllegalArgumentException("Invalid coordinate supplied in GeoJSON");
        } else if (MapFactory.MapFeatureType.TYPE_LINESTRING.equals(str2)) {
            if (yailList2.size() >= 2) {
                LineString lineString = new LineString(mapFeatureContainer);
                lineString.Points(swapCoordinates(yailList2));
                return lineString;
            }
            throw new IllegalArgumentException("Too few coordinates supplied in GeoJSON");
        } else if (MapFactory.MapFeatureType.TYPE_POLYGON.equals(str2)) {
            Polygon polygon = new Polygon(mapFeatureContainer);
            Iterator it2 = yailList2.iterator();
            it2.next();
            polygon.Points(swapCoordinates((YailList) it2.next()));
            if (it2.hasNext()) {
                polygon.HolePoints(YailList.makeList((List) swapNestedCoordinates((LList) ((Pair) yailList2.getCdr()).getCdr())));
            }
            polygon.Initialize();
            return polygon;
        } else if (MapFactory.MapFeatureType.TYPE_MULTIPOLYGON.equals(str2)) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapFeatureContainer, yailList2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static MapFactory.MapPolygon hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapFeatureContainer mapFeatureContainer, YailList yailList) {
        Polygon polygon = new Polygon(mapFeatureContainer);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = yailList.iterator();
        it.next();
        while (it.hasNext()) {
            YailList yailList2 = (YailList) it.next();
            arrayList.add(swapCoordinates((YailList) yailList2.get(1)));
            arrayList2.add(YailList.makeList((List) swapNestedCoordinates((LList) ((Pair) yailList2.getCdr()).getCdr())));
        }
        polygon.Points(YailList.makeList((List) arrayList));
        polygon.HolePoints(YailList.makeList((List) arrayList2));
        polygon.Initialize();
        return polygon;
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, MapFactory.MapFeature mapFeature, YailList yailList) {
        Iterator it = yailList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof YailList) {
                YailList yailList2 = (YailList) next;
                String obj = yailList2.get(1).toString();
                PropertyApplication propertyApplication = oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS.get(obj.toLowerCase());
                if (propertyApplication != null) {
                    propertyApplication.apply(mapFeature, yailList2.get(2));
                } else {
                    Log.i(str, String.format("Ignoring GeoJSON property \"%s\"", new Object[]{obj}));
                }
            }
        }
    }

    static boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Object obj) {
        while (!(obj instanceof Boolean)) {
            if (obj instanceof String) {
                String str = (String) obj;
                return !"false".equalsIgnoreCase(str) && str.length() != 0;
            } else if (obj instanceof FString) {
                obj = obj.toString();
            } else {
                throw new IllegalArgumentException();
            }
        }
        return ((Boolean) obj).booleanValue();
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    static int m9hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        if (obj instanceof FString) {
            return Integer.parseInt(obj.toString());
        }
        throw new IllegalArgumentException();
    }

    static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        if (obj instanceof String) {
            return Float.parseFloat((String) obj);
        }
        if (obj instanceof FString) {
            return Float.parseFloat(obj.toString());
        }
        throw new IllegalArgumentException();
    }

    public static List<YailList> getGeoJSONFeatures(String str, String str2) throws JSONException {
        JSONArray jSONArray = new JSONObject(Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(str2)).getJSONArray("features");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public static String getGeoJSONType(String str, String str2) throws JSONException {
        return new JSONObject(Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(str)).optString(str2);
    }

    private static YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if ((obj instanceof Boolean) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String)) {
                arrayList.add(YailList.makeList(new Object[]{next, obj}));
            } else if (obj instanceof JSONArray) {
                arrayList.add(YailList.makeList(new Object[]{next, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, (JSONArray) obj)}));
            } else if (obj instanceof JSONObject) {
                arrayList.add(YailList.makeList(new Object[]{next, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, (JSONObject) obj)}));
            } else if (!JSONObject.NULL.equals(obj)) {
                Log.wtf(str, "Unrecognized/invalid type in JSON object: " + obj.getClass());
                throw new IllegalArgumentException("Unrecognized/invalid type in JSON object");
            }
        }
        return YailList.makeList((List) arrayList);
    }

    private static YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if ((obj instanceof Boolean) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String)) {
                arrayList.add(obj);
            } else if (obj instanceof JSONArray) {
                arrayList.add(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, (JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                arrayList.add(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, (JSONObject) obj));
            } else if (!JSONObject.NULL.equals(obj)) {
                Log.wtf(str, "Unrecognized/invalid type in JSON object: " + obj.getClass());
                throw new IllegalArgumentException("Unrecognized/invalid type in JSON object");
            }
        }
        return YailList.makeList((List) arrayList);
    }

    private static String Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(String str) {
        return str.charAt(0) == 65279 ? str.substring(1) : str;
    }

    static final class a implements MapFactory.MapFeatureVisitor<Void> {
        private final PrintStream hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* synthetic */ a(PrintStream printStream, byte b) {
            this(printStream);
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapCircle);
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapLineString);
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker);
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapPolygon);
        }

        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapRectangle);
        }

        private a(PrintStream printStream) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = printStream;
        }

        private void hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(String str) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"type\":\"");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Object obj) {
            try {
                String jsonRepresentation = JsonUtil.getJsonRepresentation(obj);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\":");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(jsonRepresentation);
            } catch (JSONException e) {
                Log.w("GeoJSONUtil", "Unable to serialize the value of \"" + str + "\" as JSON", e);
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, String str2) {
            if (str2 != null && !TextUtils.isEmpty(str2)) {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, str2);
            }
        }

        private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(str);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\":\"&H");
            String hexString = Integer.toHexString(i);
            for (int i2 = 8; i2 > hexString.length(); i2--) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("0");
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(hexString);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GeoPoint geoPoint) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint.getLongitude());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint.getLatitude());
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(geoPoint)) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(geoPoint.getAltitude());
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
        }

        private void vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(String str) {
            PrintStream printStream = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream.print(",\"properties\":{\"$Type\":\"" + str + "\"");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapFeature mapFeature) {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("description", mapFeature.Description());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("draggable", Boolean.valueOf(mapFeature.Draggable()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("infobox", Boolean.valueOf(mapFeature.EnableInfobox()));
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("title", mapFeature.Title());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("visible", Boolean.valueOf(mapFeature.Visible()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.HasStroke hasStroke) {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("stroke", hasStroke.StrokeColor());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("stroke-opacity", Float.valueOf(hasStroke.StrokeOpacity()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("stroke-width", Integer.valueOf(hasStroke.StrokeWidth()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.HasFill hasFill) {
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("fill", hasFill.FillColor());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("fill-opacity", Float.valueOf(hasFill.FillOpacity()));
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<GeoPoint> list) {
            boolean z = true;
            for (GeoPoint next : list) {
                if (!z) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("[");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getLongitude());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getLatitude());
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(next)) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(next.getAltitude());
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]");
                z = false;
            }
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapMarker mapMarker) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapMarker.getCentroid());
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(mapMarker.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapMarker);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapMarker);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapMarker);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("anchorHorizontal", Integer.valueOf(mapMarker.AnchorHorizontal()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("anchorVertical", Integer.valueOf(mapMarker.AnchorVertical()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("height", Integer.valueOf(mapMarker.Height()));
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("image", mapMarker.ImageAsset());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("width", Integer.valueOf(mapMarker.Width()));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapLineString mapLineString) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"LineString\",\"coordinates\":[");
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapLineString.getPoints());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(mapLineString.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapLineString);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapLineString);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapPolygon mapPolygon) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            boolean z = true;
            if (mapPolygon.getPoints().size() > 1) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[");
                Iterator<List<List<GeoPoint>>> it = mapPolygon.getHolePoints().iterator();
                for (List<GeoPoint> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 : mapPolygon.getPoints()) {
                    if (!z) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                    }
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("[");
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                    if (it.hasNext()) {
                        for (List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 : it.next()) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<GeoPoint>) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3);
                        }
                    }
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]");
                    z = false;
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapPolygon.getPoints().get(0));
                if (!mapPolygon.getHolePoints().isEmpty()) {
                    for (List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 : mapPolygon.getHolePoints().get(0)) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",");
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((List<GeoPoint>) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4);
                    }
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("]}");
            }
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(mapPolygon.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapPolygon);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapPolygon);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapPolygon);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapCircle mapCircle) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(',');
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(mapCircle.getCentroid());
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(mapCircle.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapCircle);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapCircle);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapCircle);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MapFactory.MapRectangle mapRectangle) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("{");
            hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO("Feature");
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print(",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[");
            PrintStream printStream = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream.print("[" + mapRectangle.WestLongitude() + "," + mapRectangle.NorthLatitude() + "],");
            PrintStream printStream2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream2.print("[" + mapRectangle.WestLongitude() + "," + mapRectangle.SouthLatitude() + "],");
            PrintStream printStream3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream3.print("[" + mapRectangle.EastLongitude() + "," + mapRectangle.SouthLatitude() + "],");
            PrintStream printStream4 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream4.print("[" + mapRectangle.EastLongitude() + "," + mapRectangle.NorthLatitude() + "],");
            PrintStream printStream5 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            printStream5.print("[" + mapRectangle.WestLongitude() + "," + mapRectangle.NorthLatitude() + "]]}");
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R(mapRectangle.getClass().getName());
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.MapFeature) mapRectangle);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasStroke) mapRectangle);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((MapFactory.HasFill) mapRectangle);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("NorthLatitude", Double.valueOf(mapRectangle.NorthLatitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("WestLongitude", Double.valueOf(mapRectangle.WestLongitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("SouthLatitude", Double.valueOf(mapRectangle.SouthLatitude()));
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("EastLongitude", Double.valueOf(mapRectangle.EastLongitude()));
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.print("}}");
            return null;
        }

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
        private static boolean m10hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GeoPoint geoPoint) {
            return Double.compare(0.0d, geoPoint.getAltitude()) != 0;
        }
    }

    public static void writeFeaturesAsGeoJSON(List<MapFactory.MapFeature> list, String str) throws IOException {
        PrintStream printStream = null;
        try {
            PrintStream printStream2 = new PrintStream(new FileOutputStream(str));
            try {
                a aVar = new a(printStream2, (byte) 0);
                printStream2.print("{\"type\": \"FeatureCollection\", \"features\":[");
                Iterator<MapFactory.MapFeature> it = list.iterator();
                if (it.hasNext()) {
                    it.next().accept(aVar, new Object[0]);
                    while (it.hasNext()) {
                        printStream2.print(',');
                        it.next().accept(aVar, new Object[0]);
                    }
                }
                printStream2.print("]}");
                IOUtils.closeQuietly("GeoJSONUtil", printStream2);
            } catch (Throwable th) {
                th = th;
                printStream = printStream2;
                IOUtils.closeQuietly("GeoJSONUtil", printStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly("GeoJSONUtil", printStream);
            throw th;
        }
    }

    public static YailList swapCoordinates(YailList yailList) {
        Iterator it = yailList.iterator();
        it.next();
        while (it.hasNext()) {
            YailList yailList2 = (YailList) it.next();
            Object obj = yailList2.get(1);
            Pair pair = (Pair) yailList2.getCdr();
            pair.setCar(yailList2.get(2));
            ((Pair) pair.getCdr()).setCar(obj);
        }
        return yailList;
    }

    public static <E> List<List<E>> swapCoordinates2(List<List<E>> list) {
        for (List next : list) {
            Object obj = next.get(0);
            next.set(0, next.get(1));
            next.set(1, obj);
        }
        return list;
    }

    public static LList swapNestedCoordinates(LList lList) {
        for (LList lList2 = lList; !lList2.isEmpty(); lList2 = (LList) ((Pair) lList2).getCdr()) {
            swapCoordinates((YailList) lList2.get(0));
        }
        return lList;
    }
}
