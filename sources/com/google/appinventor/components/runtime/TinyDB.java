package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "TinyDB is a non-visible component that stores data for an app. <p> Apps created with Kodular are initialized each time they run: If an app sets the value of a variable and the user then quits the app, the value of that variable will not be remembered the next time the app is run. In contrast, TinyDB is a <em> persistent </em> data store for the app, that is, the data stored there will be available each time the app is run. An example might be a game that saves the high score and retrieves it each time the game is played. </<p> <p> Data items are strings stored under <em>tags</em> . To store a data item, you specify the tag it should be stored under.  Subsequently, you can retrieve the data that was stored under a given tag. </p><p> There is only one data store per app. Even if you have multiple TinyDB components, they will use the same data store. To get the effect of separate stores, use different keys. Also each app has its own data store. You cannot use TinyDB to pass data between two different apps on the phone, although you <em>can</em> use TinyDb to shares data between the different screens of a multi-screen app. </p> <p>When you are developing apps using the AI Companion, all the apps using that companion will share the same TinyDb.  That sharing will disappear once the apps are packaged.  But, during development, you should be careful to clear the TinyDb each time you start working on a new app.</p>", iconName = "images/tinyDB.png", nonVisible = true, version = 2)
public class TinyDB extends AndroidNonvisibleComponent implements Component, Deleteable {
    public static final String DEFAULT_NAMESPACE = "TinyDB1";
    private Context context;
    private SharedPreferences hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs;

    public TinyDB(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Namespace(DEFAULT_NAMESPACE);
    }

    @DesignerProperty(defaultValue = "TinyDB1", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Namespace for storing data.")
    public void Namespace(String str) {
        this.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs = str;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.context.getSharedPreferences(str, 0);
    }

    @SimpleProperty(description = "Namespace for storing data.")
    public String Namespace() {
        return this.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs;
    }

    @SimpleFunction
    public void StoreValue(String str, Object obj) {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        try {
            edit.putString(str, JsonUtil.getJsonRepresentation(obj));
            edit.commit();
        } catch (JSONException unused) {
            throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
        }
    }

    @SimpleFunction
    public Object GetValue(String str, Object obj) {
        try {
            String string = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getString(str, "");
            return string.length() == 0 ? obj : JsonUtil.getObjectFromJson(string, true);
        } catch (JSONException unused) {
            throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Creation Error.");
        }
    }

    @SimpleFunction
    public Object GetTags() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAll().keySet());
        Collections.sort(arrayList);
        return arrayList;
    }

    @SimpleFunction
    public void ClearAll() {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        edit.clear();
        edit.commit();
    }

    @SimpleFunction
    public void ClearTag(String str) {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        edit.remove(str);
        edit.commit();
    }

    public void onDelete() {
        SharedPreferences.Editor edit = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.edit();
        edit.clear();
        edit.commit();
    }

    @SimpleFunction(description = "Retrieves all data entries of TinyDB in form of Dictionaries")
    public YailDictionary GetEntries() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAll().keySet());
        Collections.sort(arrayList);
        YailDictionary yailDictionary = new YailDictionary();
        for (String str : arrayList) {
            yailDictionary.put(str, GetValue(str, ""));
        }
        return yailDictionary;
    }
}
