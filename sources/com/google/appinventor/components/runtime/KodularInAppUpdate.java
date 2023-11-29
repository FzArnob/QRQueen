package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(androidMinSdk = 21, category = ComponentCategory.EXPERIMENTAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/in-app-update/", iconName = "images/inappUpdate.png", nonVisible = true, version = 1)
@UsesLibraries({"play-core.aar", "play-core.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.FOREGROUND_SERVICE"})
public class KodularInAppUpdate extends AndroidNonvisibleComponent implements OnResumeListener {
    private ComponentContainer container;
    private Form form;
    private AppUpdateManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private InstallStateUpdatedListener f186hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Task<AppUpdateInfo> f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static /* synthetic */ int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        return 0;
    }

    public KodularInAppUpdate(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnResume(this);
        AppUpdateManager create = AppUpdateManagerFactory.create(componentContainer.$context());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = create;
        Task<AppUpdateInfo> appUpdateInfo = create.getAppUpdateInfo();
        this.f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = appUpdateInfo;
        appUpdateInfo.addOnCompleteListener(new OnCompleteListener<AppUpdateInfo>() {
            public final void onComplete(Task<AppUpdateInfo> task) {
                if (task.isSuccessful()) {
                    KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularInAppUpdate.this);
                    KodularInAppUpdate.this.Initialized();
                }
            }
        });
        Log.d("InApp Update", "Kodular InApp Update created");
    }

    @SimpleEvent(description = "Use this event to check if updates from Google Play Store are available for your app.")
    public void Initialized() {
        EventDispatcher.dispatchEvent(this, "Initialized", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when there was a update fail.")
    public void UpdateFailed() {
        EventDispatcher.dispatchEvent(this, "UpdateFailed", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update was canceled.")
    public void UpdateCanceled() {
        EventDispatcher.dispatchEvent(this, "UpdateCanceled", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is downloaded.")
    public void UpdateDownloaded() {
        EventDispatcher.dispatchEvent(this, "UpdateDownloaded", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is still in downloading progress.")
    public void UpdateDownloading() {
        EventDispatcher.dispatchEvent(this, "UpdateDownloading", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is installed.")
    public void UpdateInstalled() {
        EventDispatcher.dispatchEvent(this, "UpdateInstalled", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked when the update is still in installing progress.")
    public void UpdateInstalling() {
        EventDispatcher.dispatchEvent(this, "UpdateInstalling", new Object[0]);
    }

    @SimpleProperty(description = "Returns true if a update is available for your app.")
    public boolean IsUpdateAvailable() {
        try {
            return ((AppUpdateInfo) this.f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult()).updateAvailability() == 2;
        } catch (Exception e) {
            Log.e("InApp Update", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Start the process for immediate in-app updates. In this time your app user can NOT use your app. After the update download is done your app will be restarted automatically.")
    public void StartImmediateUpdate() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.registerListener(this.f186hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startUpdateFlowForResult((AppUpdateInfo) this.f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult(), 1, this.container.$context(), 0);
        } catch (Exception e) {
            Log.e("InApp Update", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Start the process for flexible in-app updates. The update will be done in the background. Your users can still use your app in this time. After the update download is done you must restart your app.")
    public void StartFlexibleUpdate() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.registerListener(this.f186hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startUpdateFlowForResult((AppUpdateInfo) this.f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getResult(), 0, this.container.$context(), 0);
        } catch (Exception e) {
            Log.e("InApp Update", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Use this block to install a flexible update after it was downloaded.")
    public void InstallFlexibleUpdateNow() {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.completeUpdate();
        } catch (Exception e) {
            Log.e("InApp Update", String.valueOf(e));
        }
    }

    public void onResume() {
        AppUpdateManager appUpdateManager = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (appUpdateManager != null && this.f187hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
                public final /* synthetic */ void onSuccess(Object obj) {
                    AppUpdateInfo appUpdateInfo = (AppUpdateInfo) obj;
                    if (appUpdateInfo.updateAvailability() == 3) {
                        try {
                            KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularInAppUpdate.this).startUpdateFlowForResult(appUpdateInfo, 1, KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularInAppUpdate.this).$context(), KodularInAppUpdate.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T());
                        } catch (Exception e) {
                            Log.e("InApp Update", String.valueOf(e));
                        }
                    } else if (appUpdateInfo.installStatus() == 11) {
                        KodularInAppUpdate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularInAppUpdate.this).completeUpdate();
                    }
                }
            });
        }
    }
}
