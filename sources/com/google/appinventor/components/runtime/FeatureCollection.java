package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MAPS, description = "A FeatureColletion contains one or more map features as a group. Any events fired on a feature in the collection will also trigger the corresponding event on the collection object. FeatureCollections can be loaded from external resources as a means of populating a Map with content.", version = 2)
public class FeatureCollection extends MapFeatureContainerBase implements MapFactory.MapFeatureCollection {
    private String W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = "";
    private Map map;

    public View getView() {
        return null;
    }

    public FeatureCollection(MapFactory.MapFeatureContainer mapFeatureContainer) {
        super(mapFeatureContainer);
        this.map = mapFeatureContainer.getMap();
    }

    @DesignerProperty
    @SimpleProperty(description = "Loads a collection of features from the given string. If the string is not valid GeoJSON, the ErrorLoadingFeatureCollection error will be run with url = <string>.")
    public void FeaturesFromGeoJSON(String str) {
        try {
            processGeoJSON("<string>", str);
        } catch (JSONException e) {
            Form $form = $form();
            $form.dispatchErrorOccurredEvent(this, "FeaturesFromGeoJSON", ErrorMessages.ERROR_INVALID_GEOJSON, e.getMessage());
        }
    }

    @SimpleEvent(description = "A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.")
    public void GotFeatures(String str, YailList yailList) {
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = str;
        super.GotFeatures(str, yailList);
    }

    @DesignerProperty(editorType = "geojson_type")
    public void Source(String str) {
        this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the source URL used to populate the feature collection. If the feature collection was not loaded from a URL, this will be the empty string.")
    public String Source() {
        return this.W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getMap().getController().isFeatureCollectionVisible(this);
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        getMap().getController().setFeatureCollectionVisible(this, z);
    }

    public Map getMap() {
        return this.map;
    }
}
