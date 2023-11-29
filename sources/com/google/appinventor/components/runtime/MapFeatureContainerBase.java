package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;

@SimpleObject
public abstract class MapFeatureContainerBase extends AndroidViewComponent implements MapFactory.MapFeatureContainer {
    private static final int ERROR_CODE_IO_EXCEPTION = -2;
    private static final int ERROR_CODE_MALFORMED_GEOJSON = -3;
    private static final int ERROR_CODE_MALFORMED_URL = -1;
    private static final int ERROR_CODE_UNKNOWN_TYPE = -4;
    private static final String ERROR_IO_EXCEPTION = "Unable to download content from URL";
    private static final String ERROR_MALFORMED_GEOJSON = "Malformed GeoJSON response. Expected FeatureCollection as root element.";
    private static final String ERROR_MALFORMED_URL = "The URL is malformed";
    private static final String ERROR_UNKNOWN_TYPE = "Unrecognized/invalid type in JSON object";
    private static final String GEOJSON_FEATURECOLLECTION = "FeatureCollection";
    private static final String GEOJSON_FEATURES = "features";
    private static final String GEOJSON_GEOMETRYCOLLECTION = "GeometryCollection";
    private static final String GEOJSON_TYPE = "type";
    private static final String TAG = "MapFeatureContainerBase";
    private final MapFactory.MapFeatureVisitor<Void> featureAdder = new MapFactory.MapFeatureVisitor<Void>() {
        public final /* synthetic */ Object visit(MapFactory.MapRectangle mapRectangle, Object[] objArr) {
            MapFeatureContainerBase.this.addFeature(mapRectangle);
            return null;
        }

        public final /* synthetic */ Object visit(MapFactory.MapCircle mapCircle, Object[] objArr) {
            MapFeatureContainerBase.this.addFeature(mapCircle);
            return null;
        }

        public final /* synthetic */ Object visit(MapFactory.MapPolygon mapPolygon, Object[] objArr) {
            MapFeatureContainerBase.this.addFeature(mapPolygon);
            return null;
        }

        public final /* synthetic */ Object visit(MapFactory.MapLineString mapLineString, Object[] objArr) {
            MapFeatureContainerBase.this.addFeature(mapLineString);
            return null;
        }

        public final /* synthetic */ Object visit(MapFactory.MapMarker mapMarker, Object[] objArr) {
            MapFeatureContainerBase.this.addFeature(mapMarker);
            return null;
        }
    };
    protected List<MapFactory.MapFeature> features = new CopyOnWriteArrayList();

    protected MapFeatureContainerBase(ComponentContainer componentContainer) {
        super(componentContainer);
    }

    @SimpleProperty
    public void Features(YailList yailList) {
        for (MapFactory.MapFeature removeFromMap : this.features) {
            removeFromMap.removeFromMap();
        }
        this.features.clear();
        ListIterator listIterator = yailList.listIterator(1);
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            if (next instanceof MapFactory.MapFeature) {
                addFeature((MapFactory.MapFeature) next);
            }
        }
        getMap().getView().invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The list of features placed on this map. This list also includes any features created by calls to FeatureFromDescription")
    public YailList Features() {
        return YailList.makeList((List) this.features);
    }

    @SimpleEvent(description = "The user clicked on a map feature.")
    public void FeatureClick(MapFactory.MapFeature mapFeature) {
        EventDispatcher.dispatchEvent(this, "FeatureClick", mapFeature);
        if (getMap() != this) {
            getMap().FeatureClick(mapFeature);
        }
    }

    @SimpleEvent(description = "The user long-pressed on a map feature.")
    public void FeatureLongClick(MapFactory.MapFeature mapFeature) {
        EventDispatcher.dispatchEvent(this, "FeatureLongClick", mapFeature);
        if (getMap() != this) {
            getMap().FeatureLongClick(mapFeature);
        }
    }

    @SimpleEvent(description = "The user started dragging a map feature.")
    public void FeatureStartDrag(MapFactory.MapFeature mapFeature) {
        EventDispatcher.dispatchEvent(this, "FeatureStartDrag", mapFeature);
        if (getMap() != this) {
            getMap().FeatureStartDrag(mapFeature);
        }
    }

    @SimpleEvent(description = "The user dragged a map feature.")
    public void FeatureDrag(MapFactory.MapFeature mapFeature) {
        EventDispatcher.dispatchEvent(this, "FeatureDrag", mapFeature);
        if (getMap() != this) {
            getMap().FeatureDrag(mapFeature);
        }
    }

    @SimpleEvent(description = "The user stopped dragging a map feature.")
    public void FeatureStopDrag(MapFactory.MapFeature mapFeature) {
        EventDispatcher.dispatchEvent(this, "FeatureStopDrag", mapFeature);
        if (getMap() != this) {
            getMap().FeatureStopDrag(mapFeature);
        }
    }

    @SimpleFunction(description = "<p>Load a feature collection in <a href=\"https://en.wikipedia.org/wiki/GeoJSON\">GeoJSON</a> format from the given url. On success, the event GotFeatures will be raised with the given url and a list of the features parsed from the GeoJSON as a list of (key, value) pairs. On failure, the LoadError event will be raised with any applicable HTTP response code and error message.</p>")
    public void LoadFromURL(final String str) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                MapFeatureContainerBase.this.performGet(str);
            }
        });
    }

    @SimpleFunction
    public Object FeatureFromDescription(YailList yailList) {
        try {
            return GeoJSONUtil.processGeoJSONFeature(TAG, this, yailList);
        } catch (IllegalArgumentException e) {
            Form $form = $form();
            $form.dispatchErrorOccurredEvent(this, "FeatureFromDescription", -3, e.getMessage());
            return e.getMessage();
        }
    }

    @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
    public void GotFeatures(String str, YailList yailList) {
        if (!EventDispatcher.dispatchEvent(this, "GotFeatures", str, yailList)) {
            Iterator it = yailList.iterator();
            it.next();
            while (it.hasNext()) {
                FeatureFromDescription((YailList) it.next());
            }
        }
    }

    @SimpleEvent(description = "An error was encountered while processing a GeoJSON document at the given url. The responseCode parameter will contain an HTTP status code and the errorMessage parameter will contain a detailed error message.")
    public void LoadError(String str, int i, String str2) {
        if (EventDispatcher.dispatchEvent(this, "LoadError", str, Integer.valueOf(i), str2)) {
            return;
        }
        if (str.startsWith("file:")) {
            $form().dispatchErrorOccurredEvent(this, "LoadFromURL", ErrorMessages.ERROR_CANNOT_READ_FILE, str);
            return;
        }
        $form().dispatchErrorOccurredEvent(this, "LoadFromURL", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, str);
    }

    public Activity $context() {
        return this.container.$context();
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        throw new UnsupportedOperationException("Map.$add() called");
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        throw new UnsupportedOperationException("Map.setChildWidth called");
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        throw new UnsupportedOperationException("Map.setChildHeight called");
    }

    public void removeFeature(MapFactory.MapFeature mapFeature) {
        this.features.remove(mapFeature);
        getMap().removeFeature(mapFeature);
    }

    public Iterator<MapFactory.MapFeature> iterator() {
        return this.features.iterator();
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapMarker mapMarker) {
        this.features.add(mapMarker);
        getMap().addFeature(mapMarker);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapLineString mapLineString) {
        this.features.add(mapLineString);
        getMap().addFeature(mapLineString);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapPolygon mapPolygon) {
        this.features.add(mapPolygon);
        getMap().addFeature(mapPolygon);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapCircle mapCircle) {
        this.features.add(mapCircle);
        getMap().addFeature(mapCircle);
    }

    /* access modifiers changed from: package-private */
    public void addFeature(MapFactory.MapRectangle mapRectangle) {
        this.features.add(mapRectangle);
        getMap().addFeature(mapRectangle);
    }

    public void addFeature(MapFactory.MapFeature mapFeature) {
        mapFeature.accept(this.featureAdder, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void performGet(String str) {
        try {
            String loadUrl = loadUrl(str);
            if (loadUrl != null) {
                processGeoJSON(str, loadUrl);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception retreiving GeoJSON", e);
            $form().dispatchErrorOccurredEvent(this, "LoadFromURL", -4, e.toString());
        }
    }

    private String loadUrl(final String str) {
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            if (openConnection instanceof HttpURLConnection) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                final int responseCode = httpURLConnection.getResponseCode();
                final String responseMessage = httpURLConnection.getResponseMessage();
                if (responseCode != 200) {
                    $form().runOnUiThread(new Runnable() {
                        public final void run() {
                            MapFeatureContainerBase.this.LoadError(str, responseCode, responseMessage);
                        }
                    });
                    httpURLConnection.disconnect();
                    return null;
                }
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (MalformedURLException unused) {
            $form().runOnUiThread(new Runnable() {
                public final void run() {
                    MapFeatureContainerBase.this.LoadError(str, -1, MapFeatureContainerBase.ERROR_MALFORMED_URL);
                }
            });
            return null;
        } catch (IOException unused2) {
            $form().runOnUiThread(new Runnable() {
                public final void run() {
                    MapFeatureContainerBase.this.LoadError(str, -2, MapFeatureContainerBase.ERROR_IO_EXCEPTION);
                }
            });
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void processGeoJSON(final String str, String str2) throws JSONException {
        String geoJSONType = GeoJSONUtil.getGeoJSONType(str2, "type");
        if (GEOJSON_FEATURECOLLECTION.equals(geoJSONType) || GEOJSON_GEOMETRYCOLLECTION.equals(geoJSONType)) {
            final List<YailList> geoJSONFeatures = GeoJSONUtil.getGeoJSONFeatures(TAG, str2);
            $form().runOnUiThread(new Runnable() {
                public final void run() {
                    MapFeatureContainerBase.this.GotFeatures(str, YailList.makeList(geoJSONFeatures));
                }
            });
            return;
        }
        $form().runOnUiThread(new Runnable() {
            public final void run() {
                MapFeatureContainerBase.this.LoadError(str, -3, MapFeatureContainerBase.ERROR_MALFORMED_GEOJSON);
            }
        });
    }
}
