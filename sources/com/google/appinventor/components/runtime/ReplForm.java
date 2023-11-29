package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.QUtil;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.WebRTCNativeMgr;
import dalvik.system.DexClassLoader;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kawa.standard.Scheme;

public class ReplForm extends Form {
    /* access modifiers changed from: package-private */
    public static final String LOG_TAG = "ReplForm";
    public static ReplForm topform;
    private String IcqbZ1XwOARXF8EHuumMwNxYO8BwpgyR4LeWhkJKdlM2Ka3G59guM7muYO6MGvD4;
    private boolean P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = false;
    private boolean boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = false;
    private List<String> f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    private boolean hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0 = false;
    private SchemeInterface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new SchemeInterface();

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppInvHTTPD f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private WebRTCNativeMgr f249hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB;
    Object sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = null;
    private boolean showOptionsMenu = true;
    String xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = null;

    public boolean isRepl() {
        return true;
    }

    public ReplForm() {
        topform = this;
    }

    public class SchemeInterface {

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        Language f250hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Scheme.getInstance("scheme");

        public SchemeInterface() {
            ModuleExp.mustNeverCompile();
        }

        public void eval(final String str) {
            ReplForm.this.runOnUiThread(new Runnable() {
                public final void run() {
                    try {
                        SchemeInterface.wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0();
                        if (str.equals("#DONE#")) {
                            ReplForm.this.finish();
                        } else {
                            SchemeInterface.this.f250hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.eval(str);
                        }
                    } catch (Throwable th) {
                        Log.e(ReplForm.LOG_TAG, "Exception in scheme processing", th);
                    }
                }
            });
        }

        static /* synthetic */ void wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0() {
            ClassLoader contextClassLoader = Looper.getMainLooper().getThread().getContextClassLoader();
            Thread currentThread = Thread.currentThread();
            if (currentThread.getContextClassLoader() != contextClassLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        Log.d(LOG_TAG, "onCreate");
        this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB = QUtil.getReplAssetPath(this, true);
        this.IcqbZ1XwOARXF8EHuumMwNxYO8BwpgyR4LeWhkJKdlM2Ka3G59guM7muYO6MGvD4 = this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB + "external_comps/";
        super.onCreate(bundle);
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = new ArrayList();
        processExtras(getIntent(), false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AppInvHTTPD appInvHTTPD = this.f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (appInvHTTPD != null) {
            appInvHTTPD.stop();
            this.f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
        finish();
        System.exit(0);
    }

    /* access modifiers changed from: protected */
    public void startNewForm(String str, Object obj) {
        if (obj != null) {
            this.startupValue = jsonEncodeForForm(obj, "open another screen with start value");
        }
        RetValManager.pushScreen(str, obj);
    }

    public void setFormName(String str) {
        this.formName = str;
        Log.d(LOG_TAG, "formName is now ".concat(String.valueOf(str)));
    }

    /* access modifiers changed from: protected */
    public void closeForm(Intent intent) {
        RetValManager.popScreen("Not Yet");
    }

    /* access modifiers changed from: protected */
    public void setResult(Object obj) {
        Log.d(LOG_TAG, "setResult: ".concat(String.valueOf(obj)));
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = obj;
        this.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT = this.formName;
    }

    /* access modifiers changed from: protected */
    public void closeApplicationFromBlocks() {
        runOnUiThread(new Runnable() {
            public final void run() {
                Toast.makeText(ReplForm.this, "Closing forms is not currently supported during development.", 1).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return this.showOptionsMenu;
    }

    @SimpleProperty(userVisible = false)
    public void ShowOptionsMenu(boolean z) {
        this.showOptionsMenu = z;
        super.ShowOptionsMenu(z);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(LOG_TAG, "onNewIntent Called");
        processExtras(intent, true);
    }

    /* access modifiers changed from: protected */
    public void processExtras(Intent intent, boolean z) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Log.d(LOG_TAG, "extras: ".concat(String.valueOf(extras)));
            for (String str : extras.keySet()) {
                String str2 = LOG_TAG;
                Log.d(str2, "Extra Key: " + str);
            }
        }
        if (extras != null && extras.getBoolean("rundirect")) {
            Log.d(LOG_TAG, "processExtras rundirect is true and restart is ".concat(String.valueOf(z)));
            this.hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0 = true;
            this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = true;
            if (z) {
                clear();
                AppInvHTTPD appInvHTTPD = this.f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                if (appInvHTTPD != null) {
                    appInvHTTPD.resetSeq();
                    return;
                }
                startHTTPD(true);
                AppInvHTTPD.setHmacKey("emulator");
            }
        }
    }

    public boolean isDirect() {
        return this.hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0;
    }

    public void setIsUSBrepl() {
        this.boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = true;
    }

    public void startHTTPD(boolean z) {
        try {
            if (this.f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
                File file = new File(this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB);
                if (!file.exists()) {
                    file.mkdirs();
                }
                this.f248hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AppInvHTTPD(8001, new File(this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB), z, this);
                Log.i(LOG_TAG, "started AppInvHTTPD");
            }
        } catch (IOException e) {
            String str = LOG_TAG;
            Log.e(str, "Setting up NanoHTTPD: " + e.toString());
        }
    }

    public boolean isAssetsLoaded() {
        return this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ;
    }

    public void setAssetsLoaded() {
        this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = true;
    }

    public void loadComponents(List<String> list) {
        boolean z;
        HashSet hashSet = new HashSet(list);
        File dir = activeForm.$context().getDir("componentDexs", 0);
        File file = new File(this.IcqbZ1XwOARXF8EHuumMwNxYO8BwpgyR4LeWhkJKdlM2Ka3G59guM7muYO6MGvD4);
        File file2 = new File(this.IcqbZ1XwOARXF8EHuumMwNxYO8BwpgyR4LeWhkJKdlM2Ka3G59guM7muYO6MGvD4);
        if (!file2.exists()) {
            z = file2.mkdirs();
        } else {
            z = true;
        }
        if (!z) {
            Log.d(LOG_TAG, "Unable to create components directory");
            dispatchErrorOccurredEventDialog(this, "loadComponents", ErrorMessages.ERROR_EXTENSION_ERROR, 1, FusiontablesControl.APP_NAME, "Unable to create component directory.");
            return;
        }
        ClassLoader classLoader = ReplForm.class.getClassLoader();
        StringBuilder sb = new StringBuilder();
        this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.clear();
        for (File file3 : file.listFiles()) {
            if (file3.isDirectory() && hashSet.contains(file3.getName())) {
                File file4 = new File(file3.getPath() + File.separator + "classes.jar");
                File file5 = new File(file3.getPath() + File.separator + file3.getName() + ".jar");
                file4.renameTo(file5);
                if (file5.exists() && !this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.contains(file5.getName())) {
                    Log.d(LOG_TAG, "Loading component dex " + file5.getAbsolutePath());
                    this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ.add(file5.getName());
                    sb.append(File.pathSeparatorChar);
                    sb.append(file5.getAbsolutePath());
                }
            }
        }
        DexClassLoader dexClassLoader = new DexClassLoader(sb.substring(1), dir.getAbsolutePath(), (String) null, classLoader);
        Thread.currentThread().setContextClassLoader(dexClassLoader);
        String str = LOG_TAG;
        Log.d(str, Thread.currentThread().toString());
        Log.d(str, Looper.getMainLooper().getThread().toString());
        Looper.getMainLooper().getThread().setContextClassLoader(dexClassLoader);
    }

    public static void returnRetvals(String str) {
        Log.d(LOG_TAG, "returnRetvals: ".concat(String.valueOf(str)));
        ((ReplForm) activeForm).sendToCompanion(str);
    }

    public void sendToCompanion(String str) {
        WebRTCNativeMgr webRTCNativeMgr = this.f249hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (webRTCNativeMgr == null) {
            Log.i(LOG_TAG, "No WebRTCNativeMgr!");
        } else {
            webRTCNativeMgr.send(str);
        }
    }

    public void setWebRTCMgr(WebRTCNativeMgr webRTCNativeMgr) {
        this.f249hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = webRTCNativeMgr;
    }

    public void evalScheme(String str) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.eval(str);
    }

    public String getAssetPath(String str) {
        return "file://" + this.jsy7Kz77rNyAY3JWsGLNrUoUOR8HajCK4Ef1UopjrJy4hdX7ElFJvGzBTGAF3HB + str;
    }

    public String getCachePath(String str) {
        return "file://" + new File(getExternalCacheDir(), str).getAbsolutePath();
    }

    public String getPrivatePath(String str) {
        return "file://" + new File(QUtil.getReplDataPath(this), str).getAbsolutePath();
    }

    public String getAssetPathForExtension(Component component, String str) throws FileNotFoundException {
        SimpleObject simpleObject = (SimpleObject) component.getClass().getAnnotation(SimpleObject.class);
        if (simpleObject != null && !simpleObject.external()) {
            return Form.ASSETS_PREFIX.concat(String.valueOf(str));
        }
        String name = component.getClass().getName();
        String str2 = null;
        while (true) {
            if (!name.contains(".")) {
                break;
            }
            File file = new File(this.IcqbZ1XwOARXF8EHuumMwNxYO8BwpgyR4LeWhkJKdlM2Ka3G59guM7muYO6MGvD4 + name + "/assets");
            if (file.exists() && file.isDirectory()) {
                str2 = file.getAbsolutePath();
                break;
            }
            name = name.substring(0, name.lastIndexOf(46));
        }
        if (str2 != null) {
            File file2 = new File(str2, str);
            String str3 = LOG_TAG;
            Log.d(str3, "result = " + file2.getAbsolutePath());
            if (file2.exists()) {
                return "file://" + file2.getAbsolutePath();
            }
        }
        throw new FileNotFoundException();
    }
}
