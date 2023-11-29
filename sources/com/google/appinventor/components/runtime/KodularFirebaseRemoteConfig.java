package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Remote Config", iconName = "images/firebaseDB.png", nonVisible = true, version = 2)
@UsesLibraries({"firebase-abt.jar", "firebase-abt.aar", "firebase-analytics.jar", "firebase-analytics.aar", "firebase-annotations.jar", "firebase-common.jar", "firebase-common.aar", "firebase-components.jar", "firebase-components.aar", "firebase-config.jar", "firebase-config.aar", "firebase-installations.jar", "firebase-installations.aar", "firebase-installations-interop.jar", "firebase-installations-interop.aar", "firebase-measurement-connector.jar", "firebase-measurement-connector.aar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-measurement.jar", "play-services-measurement.aar", "play-services-measurement-api.jar", "play-services-measurement-api.aar", "play-services-measurement-base.jar", "play-services-measurement-base.aar", "play-services-measurement-impl.jar", "play-services-measurement-impl.aar", "play-services-measurement-sdk.jar", "play-services-measurement-sdk.aar", "play-services-measurement-sdk-api.jar", "play-services-measurement-sdk-api.aar", "play-services-stats.jar", "play-services-stats.aar", "play-services-tasks.jar", "play-services-tasks.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK", "com.google.android.c2dm.permission.RECEIVE", "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE"})
public class KodularFirebaseRemoteConfig extends AndroidNonvisibleComponent {
    private Activity activity;
    private Context context;
    private FirebaseRemoteConfig hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private long vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = 43200;

    public KodularFirebaseRemoteConfig(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = FirebaseRemoteConfig.getInstance();
    }

    @SimpleFunction(description = "Fetches parameter values for your app")
    public void Fetch() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.fetchAndActivate().addOnCompleteListener(this.activity, new OnCompleteListener<Boolean>() {
            public final void onComplete(Task<Boolean> task) {
                if (task.isSuccessful()) {
                    KodularFirebaseRemoteConfig.this.FetchSuccess();
                } else {
                    KodularFirebaseRemoteConfig.this.FetchFailed();
                }
            }
        });
    }

    @SimpleEvent(description = "Triggers when the fetch was successful")
    public void FetchSuccess() {
        EventDispatcher.dispatchEvent(this, "FetchSuccess", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the fetch failed")
    public void FetchFailed() {
        EventDispatcher.dispatchEvent(this, "FetchFailed", new Object[0]);
    }

    @SimpleFunction(description = "Gets a text value corresponding to the specified key")
    public String GetText(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getString(str);
    }

    @SimpleFunction(description = "Gets a number value corresponding to the specified key")
    public double GetNumber(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getDouble(str);
    }

    @SimpleFunction(description = "Gets a boolean value corresponding to the specified key")
    public boolean GetBoolean(String str) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBoolean(str);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Time how long the data keeps on the device in ms")
    public long CacheExpiration() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    }

    @DesignerProperty(defaultValue = "43200", editorType = "non_negative_integer")
    @SimpleProperty
    public void CacheExpiration(long j) {
        if (j < 3600) {
            j = 3600;
        }
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = j;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq).build());
    }
}
