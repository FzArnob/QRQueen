package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.common.HorizontalAlignment;
import com.google.appinventor.components.common.Permission;
import com.google.appinventor.components.common.ScreenAnimation;
import com.google.appinventor.components.common.ScreenOrientation;
import com.google.appinventor.components.common.VerticalAlignment;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.multidex.MultiDex;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.google.appinventor.components.runtime.util.KodularCompanionUtil;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.PermissionUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "Top-level component containing all other components in the program", showOnPalette = false, version = 44)
@UsesLibraries({"jose4j.jar", "slf4j-api.jar", "appcenter-analytics.jar", "appcenter-analytics.aar", "appcenter-crashes.jar", "appcenter-crashes.aar", "appcenter.jar", "appcenter.aar"})
@SimpleObject
@UsesAssets(fileNames = "Roboto-Thin.ttf, Roboto-Regular.ttf, fontawesome-webfont.ttf, fa-regular-400.ttf, fa-solid-900.ttf, fa-brands-400.ttf, MaterialIcons-Regular.ttf")
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"})
public class Form extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener, Component, ComponentContainer, HandlesEventDispatching {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String APPINVENTOR_URL_SCHEME = "appinventor";
    private static final String ARGUMENT_NAME = "APP_INVENTOR_START";
    public static final String ASSETS_PREFIX = "file:///android_asset/";
    private static final int DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_ACCENT_COLOR);
    private static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    private static final int DEFAULT_PRIMARY_COLOR_DARK = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_PRIMARY_DARK_COLOR);
    private static final String LOG_TAG = "Form";
    public static final int MAX_PERMISSION_NONCE = 65535;
    private static final String RESULT_NAME = "APP_INVENTOR_RESULT";
    private static final int SWITCH_FORM_REQUEST_CODE = 1;
    private static boolean _initialized = false;
    protected static Form activeForm;
    private static boolean applicationIsBeingClosed;
    private static long minimumToastWait = 10000000000L;
    private static int nextRequestCode = 2;
    /* access modifiers changed from: private */
    public static boolean sCompatibilityMode;
    /* access modifiers changed from: private */
    public static boolean screenInitialized;
    private static boolean showListsAsJson = false;
    private String aboutScreen;
    private String aboutScreenTitle;
    private int aboutThisAppBackgroundColor = Component.COLOR_DARK_GRAY;
    private boolean aboutThisAppLightTheme;
    private int accentColor = DEFAULT_ACCENT_COLOR;
    /* access modifiers changed from: private */
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private final HashMap<Integer, ActivityResultListener> activityResultMap = Maps.newHashMap();
    private final Map<Integer, Set<ActivityResultListener>> activityResultMultiMap = Maps.newHashMap();
    private AlignmentUtil alignmentSetter;
    private KodularAnalyticsUtil analyticsUtil;
    protected final Handler androidUIHandler = new Handler();
    private AppBarLayout appBarLayout;
    private String appId = "";
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private String backgroundImagePath = "";
    private ScreenAnimation closeAnimType = ScreenAnimation.Default;
    private float compatScalingFactor;
    public CoordinatorLayout coordinatorLayout;
    private Menu customActionBarIcon;
    private Menu customMenu;
    private FileScope defaultFileScope = FileScope.App;
    private float deviceDensity;
    private ArrayList<PercentStorageRecord> dimChanges = new ArrayList<>();
    private int drawerArrowIconColor = 0;
    /* access modifiers changed from: private */
    public DrawerLayout drawerLayout;
    private int fontTypeface = 0;
    private int formHeight;
    protected String formName;
    private int formWidth;
    /* access modifiers changed from: private */
    public FrameLayout frameLayout;
    private FullScreenVideoUtil fullScreenVideoUtil;
    public boolean highQuality = true;
    private HorizontalAlignment horizontalAlignment;
    /* access modifiers changed from: private */
    public boolean isCompanion;
    /* access modifiers changed from: private */
    public boolean isDrawerOpenBackup;
    private boolean keepScreenOn = false;
    private boolean keyboardReallyShown = false;
    private boolean keyboardShown;
    private long lastToastTime = (System.nanoTime() - minimumToastWait);
    private Object layoutBackup = null;
    /* access modifiers changed from: private */
    public boolean lockedMenu;
    private DrawerLayout.LayoutParams lp;
    private boolean navigationBarLight;
    private int navigationIconColor;
    private String nextFormName;
    private final Set<OnClearListener> onClearListeners = Sets.newHashSet();
    private Bundle onCreateBundle = null;
    private final Set<OnCreateListener> onCreateListeners = Sets.newHashSet();
    private final Set<OnCreateOptionsMenuListener> onCreateOptionsMenuListeners = Sets.newHashSet();
    private final Set<OnDestroyListener> onDestroyListeners = Sets.newHashSet();
    /* access modifiers changed from: private */
    public final Set<OnInitializeListener> onInitializeListeners = Sets.newHashSet();
    private final Set<OnNewIntentListener> onNewIntentListeners = Sets.newHashSet();
    private final Set<OnOptionsItemSelectedListener> onOptionsItemSelectedListeners = Sets.newHashSet();
    private final Set<OnOrientationChangeListener> onOrientationChangeListeners = Sets.newHashSet();
    private final Set<OnPauseListener> onPauseListeners = Sets.newHashSet();
    private final Set<OnResumeListener> onResumeListeners = Sets.newHashSet();
    private final Set<OnStopListener> onStopListeners = Sets.newHashSet();
    private ScreenAnimation openAnimType = ScreenAnimation.Default;
    private int optionsMenuIconColor;
    /* access modifiers changed from: private */
    public final HashMap<Integer, PermissionResultHandler> permissionHandlers = Maps.newHashMap();
    /* access modifiers changed from: private */
    public final Random permissionRandom = new Random();
    private final Set<String> permissions = new HashSet();
    private int primaryColor = DEFAULT_PRIMARY_COLOR;
    private int primaryColorDark = DEFAULT_PRIMARY_COLOR_DARK;
    private ProgressDialog progress;
    protected String receiveSharedValue = "";
    /* access modifiers changed from: private */
    public int receiveSharedValueType = 0;
    private ScaledFrameLayout scaleLayout;
    private boolean scrollable;
    private boolean showNavBar;
    private boolean showOptionsMenu;
    public boolean showStatusBar;
    public boolean showTitle = true;
    private boolean splashEnabled = true;
    protected String startupValue;
    private boolean stateBackButton;
    private int statusbarColor;
    private boolean statusbarLight;
    /* access modifiers changed from: private */
    public int titleBarColor;
    private int titleBarTextColor;
    /* access modifiers changed from: private */
    public Toolbar toolbar;
    private int toolbarIconColor;
    /* access modifiers changed from: private */
    public String toolbarSubTitle = "";
    /* access modifiers changed from: private */
    public String toolbarTitle;
    private VerticalAlignment verticalAlignment;
    private LinearLayout viewLayout;
    private String yandexTranslateTagline = "";

    public Activity $context() {
        return this;
    }

    public Form $form() {
        return this;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "This is the display name of the installed application in the phone.If the AppName is blank, it will be set to the name of the project when the project is built.", userVisible = false)
    public void AppName(String str) {
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(userVisible = false)
    public void Icon(String str) {
    }

    @DesignerProperty(defaultValue = "21", editorType = "min_sdk", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void MinSdk(int i) {
    }

    @DesignerProperty(defaultValue = "", editorType = "app_package_name", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom PackageName for the app. Min 8 chars, max 35 chars, min 3 words, max 5 words, no special chars, only ASCII a-z and dots for separators", userVisible = false)
    public void PackageName(String str) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void RTLSupport(boolean z) {
    }

    @DesignerProperty(defaultValue = "", editorType = "receive_types")
    @SimpleProperty(userVisible = false)
    public void ReceiveSharedText(String str) {
    }

    @DesignerProperty(editorType = "image_asset", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void SplashIcon(String str) {
    }

    @DesignerProperty(defaultValue = "AppTheme", editorType = "theme")
    @SimpleProperty(description = "Sets the theme used by the application.", userVisible = false)
    public void Theme(String str) {
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "A URL to use to populate the Tutorial Sidebar while editing a project. Used as a teaching aid.", userVisible = false)
    public void TutorialURL(String str) {
    }

    @DesignerProperty(defaultValue = "1", editorType = "non_negative_integer")
    @SimpleProperty(description = "An integer value which must be incremented each time a new Android Application Package File (APK) is created for the Google Play Store.", userVisible = false)
    public void VersionCode(int i) {
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "string")
    @SimpleProperty(description = "A string which can be changed to allow Google Play Store users to distinguish between different versions of the App.", userVisible = false)
    public void VersionName(String str) {
    }

    public int VisibilityHelper(boolean z) {
        if (z) {
            return 0;
        }
        return InputDeviceCompat.SOURCE_TOUCHSCREEN;
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this;
    }

    public boolean isRepl() {
        return false;
    }

    public static class PercentStorageRecord {
        AndroidViewComponent B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        int ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR;
        Dim hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        public enum Dim {
            HEIGHT,
            WIDTH
        }

        public PercentStorageRecord(AndroidViewComponent androidViewComponent, int i, Dim dim) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = androidViewComponent;
            this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = i;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = dim;
        }
    }

    static class a extends AsyncTask<Form, Void, Boolean> {
        private Form wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = ((Form[]) objArr)[0];
            Log.d(Form.LOG_TAG, "Doing Full MultiDex Install");
            MultiDex.install(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, true);
            return Boolean.TRUE;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.onCreateFinish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        this.onCreateBundle = bundle;
        for (OnCreateListener onCreate : this.onCreateListeners) {
            onCreate.onCreate();
        }
        String name = getClass().getName();
        this.formName = name.substring(name.lastIndexOf(46) + 1);
        this.onCreateBundle = bundle;
        activeForm = this;
        if (this instanceof ReplForm) {
            this.isCompanion = true;
        }
        this.deviceDensity = getResources().getDisplayMetrics().density;
        this.compatScalingFactor = ScreenDensityUtil.computeCompatibleScaling(this);
        LinearLayout linearLayout = new LinearLayout(this);
        this.viewLayout = linearLayout;
        this.alignmentSetter = new AlignmentUtil(linearLayout);
        this.progress = null;
        if (_initialized || !this.formName.equals("Screen1")) {
            Log.d(LOG_TAG, "NO MULTI: _initialized = " + _initialized + " formName = " + this.formName);
            _initialized = true;
            onCreateFinish();
            return;
        }
        Log.d(LOG_TAG, "MULTI: _initialized = " + _initialized + " formName = " + this.formName);
        _initialized = true;
        if (ReplApplication.installed) {
            Log.d(LOG_TAG, "MultiDex already installed.");
            onCreateFinish();
            return;
        }
        ProgressDialog show = ProgressDialog.show(this, "Please Wait...", "Installation Finishing");
        this.progress = show;
        show.show();
        new a((byte) 0).execute(new Form[]{this});
    }

    /* access modifiers changed from: package-private */
    public void onCreateFinish() {
        Uri uri;
        Log.d(LOG_TAG, "onCreateFinish called " + System.currentTimeMillis());
        ProgressDialog progressDialog = this.progress;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        populatePermissions();
        defaultPropertyValues();
        Intent intent = null;
        try {
            intent = getIntent();
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
        if (intent != null && intent.hasExtra(ARGUMENT_NAME)) {
            this.startupValue = intent.getStringExtra(ARGUMENT_NAME);
        }
        if (!(intent == null || intent.getType() == null)) {
            if (intent.getType().contains("audio/")) {
                Uri uri2 = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                if (uri2 != null) {
                    this.receiveSharedValue = uri2.getPath();
                    this.receiveSharedValueType = 1;
                }
            } else if (intent.getType().contains("image/")) {
                Uri uri3 = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                if (uri3 != null) {
                    this.receiveSharedValue = uri3.getPath();
                    this.receiveSharedValueType = 2;
                }
            } else if (intent.getType().contains("text/")) {
                String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
                if (stringExtra != null) {
                    this.receiveSharedValue = String.valueOf(stringExtra);
                    this.receiveSharedValueType = 3;
                }
            } else if (intent.getType().contains("video/") && (uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM")) != null) {
                this.receiveSharedValue = uri.getPath();
                this.receiveSharedValueType = 4;
            }
        }
        this.fullScreenVideoUtil = new FullScreenVideoUtil(this, this.androidUIHandler);
        getWindow().setSoftInputMode(getWindow().getAttributes().softInputMode | 16);
        $define();
        Initialize();
    }

    private void populatePermissions() {
        try {
            Collections.addAll(this.permissions, getPackageManager().getPackageInfo(getPackageName(), 4096).requestedPermissions);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception while attempting to learn permissions.", e);
        }
    }

    public Bundle getOnCreateBundle() {
        return this.onCreateBundle;
    }

    private void defaultPropertyValues() {
        Scrollable(false);
        Sizing("Responsive");
        AboutScreen("");
        AboutScreenTitle("About this application");
        AboutScreenBackgroundColor(this.aboutThisAppBackgroundColor);
        AboutScreenLightTheme(this.aboutThisAppLightTheme);
        BackgroundImage("");
        BackgroundColor(-1);
        AlignHorizontal(1);
        AlignVertical(1);
        Title("");
        TitleBarSubTitle("");
        ShowStatusBar(true);
        ShowNavBar(true);
        TitleVisible(true);
        ShowListsAsJson(false);
        NavigationBarColor(-16777216);
        ShowOptionsMenu(true);
        ScreenOrientation("unspecified");
        SplashEnabled(true);
        AccentColor(DEFAULT_ACCENT_COLOR);
        PrimaryColor(DEFAULT_PRIMARY_COLOR);
        PrimaryColorDark(DEFAULT_PRIMARY_COLOR_DARK);
        Theme(ComponentConstants.DEFAULT_THEME);
        TitleBarTypefaceImport("");
        HighQuality(false);
        KeepScreenOn(false);
        RTLSupport(false);
        OpenScreenAnimationAbstract(ScreenAnimation.Default);
        CloseScreenAnimationAbstract(ScreenAnimation.Default);
        DefaultFileScope(FileScope.App);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ActionBarDrawerToggle actionBarDrawerToggle2 = this.actionBarDrawerToggle;
        if (actionBarDrawerToggle2 != null) {
            actionBarDrawerToggle2.syncState();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarDrawerToggle actionBarDrawerToggle2 = this.actionBarDrawerToggle;
        if (actionBarDrawerToggle2 != null) {
            actionBarDrawerToggle2.onConfigurationChanged(configuration);
        }
        Log.d(LOG_TAG, "onConfigurationChanged() called");
        final int i = configuration.orientation;
        if (i == 2 || i == 1) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    boolean z = true;
                    if (Form.this.frameLayout == null || (i != 2 ? Form.this.frameLayout.getHeight() < Form.this.frameLayout.getWidth() : Form.this.frameLayout.getWidth() < Form.this.frameLayout.getHeight())) {
                        z = false;
                    }
                    if (z) {
                        Form.this.recomputeLayout();
                        final FrameLayout access$100 = Form.this.frameLayout;
                        Form.this.androidUIHandler.postDelayed(new Runnable() {
                            public final void run() {
                                FrameLayout frameLayout = access$100;
                                if (frameLayout != null) {
                                    frameLayout.invalidate();
                                }
                            }
                        }, 66);
                        if (Form.this.isDrawerOpenBackup) {
                            Form.this.SideMenuOpen();
                        }
                        if (Form.this.lockedMenu) {
                            Form.this.LockSideMenu();
                        }
                        Form form = Form.this;
                        form.TitleVisible(form.showTitle);
                        Form form2 = Form.this;
                        form2.Title(form2.toolbarTitle);
                        Form form3 = Form.this;
                        form3.TitleBarSubTitle(form3.toolbarSubTitle);
                        KodularCompanionUtil.toolbarColor(Form.this.toolbar, Form.this.isCompanion, Form.this.titleBarColor);
                        Form.this.ScreenOrientationChanged();
                        Form.this.invalidateOptionsMenu();
                        return;
                    }
                    Form.this.androidUIHandler.post(this);
                }
            });
        }
    }

    public void onGlobalLayout() {
        int height = this.scaleLayout.getRootView().getHeight();
        float height2 = ((float) (height - this.scaleLayout.getHeight())) / ((float) height);
        Log.d(LOG_TAG, "onGlobalLayout(): diffPercent = ".concat(String.valueOf(height2)));
        boolean z = false;
        if (((double) height2) < 0.25d) {
            Log.d(LOG_TAG, "keyboard hidden!");
            if (this.keyboardShown) {
                this.keyboardShown = false;
                if (sCompatibilityMode) {
                    this.scaleLayout.setScale(this.compatScalingFactor);
                    this.scaleLayout.invalidate();
                }
            }
        } else {
            Log.d(LOG_TAG, "keyboard shown!");
            this.keyboardShown = true;
            ScaledFrameLayout scaledFrameLayout = this.scaleLayout;
            if (scaledFrameLayout != null) {
                scaledFrameLayout.setScale(1.0f);
                this.scaleLayout.invalidate();
            }
        }
        Rect rect = new Rect();
        this.frameLayout.getWindowVisibleDisplayFrame(rect);
        int height3 = this.frameLayout.getRootView().getHeight();
        if (((double) (height3 - rect.bottom)) > ((double) height3) * 0.15d) {
            z = true;
        }
        this.keyboardReallyShown = z;
        KeyboardVisiblityChanged(z);
    }

    @SimpleEvent(description = "Event will be invoked if the keyboard was visible or invisible.")
    public void KeyboardVisiblityChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "KeyboardVisiblityChanged", Boolean.valueOf(z));
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout2 = this.drawerLayout;
        if (drawerLayout2 != null && drawerLayout2.isDrawerOpen((int) GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
        } else if (!BackPressed()) {
            AnimationUtil.ApplyCloseScreenAnimation((Activity) this, this.closeAnimType);
            super.onBackPressed();
        }
    }

    @SimpleEvent(description = "Device back button pressed.")
    public boolean BackPressed() {
        return EventDispatcher.dispatchEvent(this, "BackPressed", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.i(LOG_TAG, "Form " + this.formName + " got onActivityResult, requestCode = " + i + ", resultCode = " + i2);
        if (i == 1) {
            OtherScreenClosed(this.nextFormName, decodeJSONStringForForm((intent == null || !intent.hasExtra(RESULT_NAME)) ? "" : intent.getStringExtra(RESULT_NAME), "other screen closed"));
            return;
        }
        ActivityResultListener activityResultListener = this.activityResultMap.get(Integer.valueOf(i));
        if (activityResultListener != null) {
            activityResultListener.resultReturned(i, i2, intent);
        }
        Set set = this.activityResultMultiMap.get(Integer.valueOf(i));
        if (set != null) {
            for (ActivityResultListener resultReturned : (ActivityResultListener[]) set.toArray(new ActivityResultListener[0])) {
                resultReturned.resultReturned(i, i2, intent);
            }
        }
    }

    private static Object decodeJSONStringForForm(String str, String str2) {
        Object obj;
        Log.i(LOG_TAG, "decodeJSONStringForForm -- decoding JSON representation:".concat(String.valueOf(str)));
        try {
            obj = JsonUtil.getObjectFromJson(str, true);
            try {
                Log.i(LOG_TAG, "decodeJSONStringForForm -- got decoded JSON:" + obj.toString());
            } catch (JSONException unused) {
            }
        } catch (JSONException unused2) {
            obj = "";
            Form form = activeForm;
            form.dispatchErrorOccurredEvent(form, str2, ErrorMessages.ERROR_SCREEN_BAD_VALUE_RECEIVED, str);
            return obj;
        }
        return obj;
    }

    public int registerForActivityResult(ActivityResultListener activityResultListener) {
        int generateNewRequestCode = generateNewRequestCode();
        this.activityResultMap.put(Integer.valueOf(generateNewRequestCode), activityResultListener);
        return generateNewRequestCode;
    }

    public void registerForActivityResult(ActivityResultListener activityResultListener, int i) {
        Set set = this.activityResultMultiMap.get(Integer.valueOf(i));
        if (set == null) {
            set = Sets.newHashSet();
            this.activityResultMultiMap.put(Integer.valueOf(i), set);
        }
        set.add(activityResultListener);
    }

    public void unregisterForActivityResult(ActivityResultListener activityResultListener) {
        ArrayList<Integer> newArrayList = Lists.newArrayList();
        for (Map.Entry next : this.activityResultMap.entrySet()) {
            if (activityResultListener.equals(next.getValue())) {
                newArrayList.add(next.getKey());
            }
        }
        for (Integer remove : newArrayList) {
            this.activityResultMap.remove(remove);
        }
        Iterator<Map.Entry<Integer, Set<ActivityResultListener>>> it = this.activityResultMultiMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next2 = it.next();
            ((Set) next2.getValue()).remove(activityResultListener);
            if (((Set) next2.getValue()).size() == 0) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ReplayFormOrientation() {
        Log.d(LOG_TAG, "ReplayFormOrientation()");
        ArrayList arrayList = (ArrayList) this.dimChanges.clone();
        this.dimChanges.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            PercentStorageRecord percentStorageRecord = (PercentStorageRecord) arrayList.get(i);
            if (percentStorageRecord.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == PercentStorageRecord.Dim.HEIGHT) {
                percentStorageRecord.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Height(percentStorageRecord.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR);
            } else {
                percentStorageRecord.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Width(percentStorageRecord.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR);
            }
        }
    }

    public void registerPercentLength(AndroidViewComponent androidViewComponent, int i, PercentStorageRecord.Dim dim) {
        this.dimChanges.add(new PercentStorageRecord(androidViewComponent, i, dim));
    }

    private static int generateNewRequestCode() {
        int i = nextRequestCode;
        nextRequestCode = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "Form " + this.formName + " got onResume");
        activeForm = this;
        if (applicationIsBeingClosed) {
            closeApplication();
            return;
        }
        OnAppResume();
        for (OnResumeListener onResume : this.onResumeListeners) {
            onResume.onResume();
        }
    }

    public void registerForOnResume(OnResumeListener onResumeListener) {
        this.onResumeListeners.add(onResumeListener);
    }

    public void registerForOnInitialize(OnInitializeListener onInitializeListener) {
        this.onInitializeListeners.add(onInitializeListener);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(LOG_TAG, "Form " + this.formName + " got onNewIntent " + intent);
        for (OnNewIntentListener onNewIntent : this.onNewIntentListeners) {
            onNewIntent.onNewIntent(intent);
        }
    }

    public void registerForOnNewIntent(OnNewIntentListener onNewIntentListener) {
        this.onNewIntentListeners.add(onNewIntentListener);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "Form " + this.formName + " got onPause");
        OnAppPause();
        for (OnPauseListener onPause : this.onPauseListeners) {
            onPause.onPause();
        }
    }

    public void registerForOnPause(OnPauseListener onPauseListener) {
        this.onPauseListeners.add(onPauseListener);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "Form " + this.formName + " got onStop");
        OnAppStop();
        for (OnStopListener onStop : this.onStopListeners) {
            onStop.onStop();
        }
    }

    public void registerForOnStop(OnStopListener onStopListener) {
        this.onStopListeners.add(onStopListener);
    }

    public void registerForOnClear(OnClearListener onClearListener) {
        this.onClearListeners.add(onClearListener);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.i(LOG_TAG, "Form " + this.formName + " got onDestroy");
        EventDispatcher.removeDispatchDelegate(this);
        for (OnDestroyListener onDestroy : this.onDestroyListeners) {
            onDestroy.onDestroy();
        }
        super.onDestroy();
    }

    public void registerForOnDestroy(OnDestroyListener onDestroyListener) {
        this.onDestroyListeners.add(onDestroyListener);
    }

    public void registerForOnCreateOptionsMenu(OnCreateOptionsMenuListener onCreateOptionsMenuListener) {
        this.onCreateOptionsMenuListeners.add(onCreateOptionsMenuListener);
    }

    public void registerForOnOptionsItemSelected(OnOptionsItemSelectedListener onOptionsItemSelectedListener) {
        this.onOptionsItemSelectedListeners.add(onOptionsItemSelectedListener);
    }

    public void registerForOnOrientationChangeListener(OnOrientationChangeListener onOrientationChangeListener) {
        this.onOrientationChangeListeners.add(onOrientationChangeListener);
    }

    public void registerForOnCreateListener(OnCreateListener onCreateListener) {
        this.onCreateListeners.add(onCreateListener);
    }

    public Dialog onCreateDialog(int i) {
        if (i != 189) {
            return super.onCreateDialog(i);
        }
        return this.fullScreenVideoUtil.createFullScreenVideoDialog();
    }

    public void onPrepareDialog(int i, Dialog dialog) {
        if (i != 189) {
            super.onPrepareDialog(i, dialog);
        } else {
            this.fullScreenVideoUtil.prepareFullScreenVideoDialog(dialog);
        }
    }

    /* access modifiers changed from: protected */
    public void $define() {
        throw new UnsupportedOperationException();
    }

    public boolean canDispatchEvent(Component component, String str) {
        boolean z = screenInitialized || (component == this && str.equals("Initialize"));
        if (z) {
            activeForm = this;
        }
        return z;
    }

    public boolean dispatchEvent(Component component, String str, String str2, Object[] objArr) {
        throw new UnsupportedOperationException();
    }

    public void dispatchGenericEvent(Component component, String str, boolean z, Object[] objArr) {
        throw new UnsupportedOperationException();
    }

    @SimpleEvent(description = "Screen starting")
    public void Initialize() {
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                if (Form.this.frameLayout == null || Form.this.frameLayout.getWidth() == 0 || Form.this.frameLayout.getHeight() == 0) {
                    Form.this.androidUIHandler.post(this);
                    return;
                }
                if (Form.activeForm instanceof ReplForm) {
                    boolean unused = Form.this.isCompanion = true;
                }
                EventDispatcher.dispatchEvent(Form.this, "Initialize", new Object[0]);
                if (Form.sCompatibilityMode) {
                    Form.this.Sizing("Fixed");
                } else {
                    Form.this.Sizing("Responsive");
                }
                boolean unused2 = Form.screenInitialized = true;
                Log.i(Form.LOG_TAG, "receiveSharedValueType: " + Form.this.receiveSharedValueType);
                Log.i(Form.LOG_TAG, "receiveSharedValue: " + Form.this.receiveSharedValue);
                Form form = Form.this;
                form.GotReceivedShared(form.receiveSharedValueType, Form.this.receiveSharedValue);
                for (OnInitializeListener onInitialize : Form.this.onInitializeListeners) {
                    onInitialize.onInitialize();
                }
                if (Form.activeForm instanceof ReplForm) {
                    ReplForm replForm = (ReplForm) Form.activeForm;
                    String str = ReplForm.LOG_TAG;
                    Log.d(str, "HandleReturnValues() Called, replResult = " + replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
                    if (replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt != null) {
                        replForm.OtherScreenClosed(replForm.xphF7LaxLBhBSMXngqFaYY3J3HcWUabJuEaZGB2R7V3S4be2YKLOC6pwY1axtLQT, replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
                        Log.d(ReplForm.LOG_TAG, "Called OtherScreenClosed");
                        replForm.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = null;
                    }
                }
                if (Form.this.formName.equals("Screen1")) {
                    KodularAnalyticsUtil.init(Form.this.getApplication(), Form.this.$context(), Form.this);
                } else {
                    KodularAnalyticsUtil.screenOpen(Form.this.formName, Form.this);
                }
            }
        });
    }

    @SimpleEvent(description = "Screen orientation changed")
    public void ScreenOrientationChanged() {
        for (OnOrientationChangeListener onOrientationChange : this.onOrientationChangeListeners) {
            onOrientationChange.onOrientationChange();
        }
        EventDispatcher.dispatchEvent(this, "ScreenOrientationChanged", new Object[0]);
    }

    @SimpleEvent(description = "Event raised when an error occurs. Only some errors will raise this condition.  For those errors, the system will show a notification by default.  You can use this event handler to prescribe an error behavior different than the default.")
    public void ErrorOccurred(Component component, String str, int i, String str2) {
        String name = component.getClass().getName();
        String substring = name.substring(name.lastIndexOf(".") + 1);
        Log.e(LOG_TAG, "Form " + this.formName + " ErrorOccurred, errorNumber = " + i + ", componentType = " + substring + ", functionName = " + str + ", messages = " + str2);
        KodularAnalyticsUtil.logError(substring, str, i, str2, this);
        if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", component, str, Integer.valueOf(i), str2) && screenInitialized) {
            try {
                Notifier notifier = new Notifier(this);
                notifier.ShowAlert("Error " + i + ": " + str2);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    public void ErrorOccurredDialog(Component component, String str, int i, String str2, String str3, String str4) {
        String name = component.getClass().getName();
        String substring = name.substring(name.lastIndexOf(".") + 1);
        Log.e(LOG_TAG, "Form " + this.formName + " ErrorOccurred, errorNumber = " + i + ", componentType = " + substring + ", functionName = " + str + ", messages = " + str2);
        if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", component, str, Integer.valueOf(i), str2) && screenInitialized) {
            try {
                Notifier notifier = new Notifier(this);
                notifier.ShowMessageDialog("Error " + i + ": " + str2, str3, str4);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    public void dispatchErrorOccurredEvent(Component component, String str, int i, Object... objArr) {
        final int i2 = i;
        final Object[] objArr2 = objArr;
        final Component component2 = component;
        final String str2 = str;
        runOnUiThread(new Runnable() {
            public final void run() {
                Form.this.ErrorOccurred(component2, str2, i2, ErrorMessages.formatMessage(i2, objArr2));
            }
        });
    }

    public void dispatchErrorOccurredEventDialog(Component component, String str, int i, Object... objArr) {
        final int i2 = i;
        final Object[] objArr2 = objArr;
        final Component component2 = component;
        final String str2 = str;
        runOnUiThread(new Runnable() {
            public final void run() {
                String formatMessage = ErrorMessages.formatMessage(i2, objArr2);
                Form form = Form.this;
                Component component = component2;
                String str = str2;
                int i = i2;
                form.ErrorOccurredDialog(component, str, i, formatMessage, "Error in " + str2, "Dismiss");
            }
        });
    }

    public void runtimeFormErrorOccurredEvent(String str, int i, String str2) {
        Log.d("FORM_RUNTIME_ERROR", "functionName is ".concat(String.valueOf(str)));
        Log.d("FORM_RUNTIME_ERROR", "errorNumber is ".concat(String.valueOf(i)));
        Log.d("FORM_RUNTIME_ERROR", "message is ".concat(String.valueOf(str2)));
        dispatchErrorOccurredEvent(activeForm, str, i, str2);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "When checked, there will be a vertical scrollbar on the screen, and the height of the application can exceed the physical height of the device. When unchecked, the application height is constrained to the height of the device.")
    public boolean Scrollable() {
        return this.scrollable;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Scrollable(boolean z) {
        if (this.scrollable != z || this.frameLayout == null) {
            this.scrollable = z;
            recomputeLayout();
            TitleVisible(this.showTitle);
            for (OnOrientationChangeListener onOrientationChange : this.onOrientationChangeListeners) {
                onOrientationChange.onOrientationChange();
            }
        }
    }

    /* access modifiers changed from: private */
    public void recomputeLayout() {
        Log.d(LOG_TAG, "recomputeLayout called");
        FrameLayout frameLayout2 = this.frameLayout;
        if (frameLayout2 != null) {
            frameLayout2.removeAllViews();
        }
        DrawerLayout drawerLayout2 = new DrawerLayout(this);
        this.drawerLayout = drawerLayout2;
        drawerLayout2.setDrawerLockMode(1);
        this.drawerLayout.addDrawerListener((DrawerLayout.DrawerListener) null);
        this.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            public final void onDrawerSlide(View view, float f) {
            }

            public final void onDrawerStateChanged(int i) {
            }

            public final void onDrawerOpened(View view) {
                boolean unused = Form.this.isDrawerOpenBackup = true;
                Form.this.SideMenuOpened();
            }

            public final void onDrawerClosed(View view) {
                boolean unused = Form.this.isDrawerOpenBackup = false;
                Form.this.SideMenuClosed();
            }
        });
        CoordinatorLayout coordinatorLayout2 = new CoordinatorLayout(this);
        this.coordinatorLayout = coordinatorLayout2;
        coordinatorLayout2.setLayoutParams(new CoordinatorLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        AppBarLayout appBarLayout2 = new AppBarLayout(this);
        this.appBarLayout = appBarLayout2;
        appBarLayout2.setLayoutParams(new AppBarLayout.LayoutParams(-1, -2));
        this.toolbar = new Toolbar(this);
        TypedArray obtainStyledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(new int[]{16843499});
        obtainStyledAttributes.recycle();
        this.toolbar.setLayoutParams(new Toolbar.LayoutParams(-1, (int) obtainStyledAttributes.getDimension(0, 0.0f)));
        this.toolbar.setBackground(new ColorDrawable(KodularResourcesUtil.getColor(getApplicationContext(), "colorPrimary")));
        UpdateTitlebarItemColor(this.toolbarIconColor);
        this.appBarLayout.addView(this.toolbar);
        linearLayout.addView(this.appBarLayout);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                if (Form.this.drawerLayout == null || Form.this.actionBarDrawerToggle == null || Form.this.lockedMenu) {
                    Form.this.TitlebarBackButtonClicked();
                } else if (Form.this.drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
                    Form.this.drawerLayout.closeDrawer((int) GravityCompat.START);
                } else {
                    Form.this.drawerLayout.openDrawer((int) GravityCompat.START);
                }
            }
        });
        if (this.scrollable) {
            this.frameLayout = new ScrollView(this);
            if (Build.VERSION.SDK_INT >= 24) {
                ((ScrollView) this.frameLayout).setFillViewport(true);
            }
        } else {
            this.frameLayout = new FrameLayout(this);
        }
        this.frameLayout.addView(this.viewLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
        this.frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.addView(this.frameLayout);
        this.coordinatorLayout.addView(linearLayout);
        setBackground(this.frameLayout);
        this.drawerLayout.addView(this.coordinatorLayout, new ViewGroup.LayoutParams(-1, -1));
        Object obj = this.layoutBackup;
        if (obj != null) {
            if (obj instanceof AndroidViewComponent) {
                SideMenu((AndroidViewComponent) obj);
            } else if (obj instanceof MakeroidSideMenuLayout) {
                SideMenuLayout((MakeroidSideMenuLayout) obj);
            }
        }
        Log.d(LOG_TAG, "About to create a new ScaledFrameLayout");
        ScaledFrameLayout scaledFrameLayout = new ScaledFrameLayout(this);
        this.scaleLayout = scaledFrameLayout;
        scaledFrameLayout.addView(this.drawerLayout, new ViewGroup.LayoutParams(-1, -1));
        setContentView((View) this.scaleLayout);
        this.frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.scaleLayout.requestLayout();
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                if (Form.this.frameLayout == null || Form.this.frameLayout.getWidth() == 0 || Form.this.frameLayout.getHeight() == 0) {
                    Form.this.androidUIHandler.post(this);
                    return;
                }
                if (Form.sCompatibilityMode) {
                    Form.this.Sizing("Fixed");
                } else {
                    Form.this.Sizing("Responsive");
                }
                Form.this.ReplayFormOrientation();
                Form.this.frameLayout.requestLayout();
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        setBackground(this.frameLayout);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    public String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    public void BackgroundImage(@Asset String str) {
        if (str == null) {
            str = "";
        }
        this.backgroundImagePath = str;
        try {
            this.backgroundDrawable = MediaUtil.getBitmapDrawable(this, str);
        } catch (Exception unused) {
            Log.e(LOG_TAG, "Unable to load " + this.backgroundImagePath);
            this.backgroundDrawable = null;
        }
        setBackground(this.frameLayout);
    }

    @DesignerProperty(defaultValue = "App", editorType = "file_scope")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void DefaultFileScope(FileScope fileScope) {
        this.defaultFileScope = fileScope;
    }

    public FileScope DefaultFileScope() {
        return this.defaultFileScope;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The caption for the form, which apears in the title bar")
    public String Title() {
        if (getSupportActionBar() == null) {
            return getTitle().toString();
        }
        if (getSupportActionBar().getTitle() != null) {
            return getSupportActionBar().getTitle().toString();
        }
        return getTitle().toString();
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Title(String str) {
        setTitle(str);
        this.toolbarTitle = str;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) str);
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void AppId(String str) {
        this.appId = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Information about the screen.  It appears when \"About this Application\" is selected from the system menu. Use it to inform people about your app.  In multiple screen apps, each screen has its own AboutScreen info.")
    public String AboutScreen() {
        return this.aboutScreen;
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty
    public void AboutScreen(String str) {
        while (str.contains("<!--")) {
            str = str.replace("<!--", "");
        }
        while (str.contains("<!-")) {
            str = str.replace("<!-", "");
        }
        this.aboutScreen = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The title bar is the top gray bar on the screen. This property reports whether the title bar is visible.")
    public boolean TitleVisible() {
        return this.showTitle;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void TitleVisible(boolean z) {
        if (getSupportActionBar() != null) {
            if (z) {
                this.appBarLayout.setVisibility(0);
            } else {
                this.appBarLayout.setVisibility(8);
            }
            this.showTitle = z;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The status bar is the topmost bar on the screen. This property reports whether the status bar is visible.")
    public boolean ShowStatusBar() {
        return this.showStatusBar;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ShowStatusBar(boolean z) {
        if (z != this.showStatusBar) {
            if (z) {
                getWindow().addFlags(2048);
                getWindow().clearFlags(1024);
            } else {
                getWindow().addFlags(1024);
                getWindow().clearFlags(2048);
            }
            this.showStatusBar = z;
        }
    }

    @Options(ScreenOrientation.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The requested screen orientation, specified as a text value.  Commonly used values are landscape, portrait, sensor, user and unspecified.  See the Android developer documentation for ActivityInfo.Screen_Orientation for the complete list of possible settings.")
    public String ScreenOrientation() {
        return ScreenOrientationAbstract().toUnderlyingValue();
    }

    public ScreenOrientation ScreenOrientationAbstract() {
        switch (getRequestedOrientation()) {
            case -1:
                return ScreenOrientation.Unspecified;
            case 0:
                return ScreenOrientation.Landscape;
            case 1:
                return ScreenOrientation.Portrait;
            case 2:
                return ScreenOrientation.User;
            case 3:
                return ScreenOrientation.Behind;
            case 4:
                return ScreenOrientation.Sensor;
            case 5:
                return ScreenOrientation.NoSensor;
            case 6:
                return ScreenOrientation.SensorLandscape;
            case 7:
                return ScreenOrientation.SensorPortrait;
            case 8:
                return ScreenOrientation.ReverseLandscape;
            case 9:
                return ScreenOrientation.ReversePortrait;
            case 10:
                return ScreenOrientation.FullSensor;
            default:
                return ScreenOrientation.Unspecified;
        }
    }

    public void ScreenOrientationAbstract(ScreenOrientation screenOrientation) {
        setRequestedOrientation(screenOrientation.getOrientationConstant());
    }

    @DesignerProperty(defaultValue = "unspecified", editorType = "screen_orientation")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void ScreenOrientation(@Options(ScreenOrientation.class) String str) {
        ScreenOrientation fromUnderlyingValue = ScreenOrientation.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            dispatchErrorOccurredEvent(this, "ScreenOrientation", ErrorMessages.ERROR_INVALID_SCREEN_ORIENTATION, str);
            return;
        }
        ScreenOrientationAbstract(fromUnderlyingValue);
    }

    @Options(HorizontalAlignment.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the screen are aligned  horizontally. The choices are: 1 = left aligned, 3 = horizontally centered,  2 = right aligned.")
    public int AlignHorizontal() {
        return this.horizontalAlignment.toUnderlyingValue().intValue();
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(@Options(HorizontalAlignment.class) int i) {
        HorizontalAlignment fromUnderlyingValue = HorizontalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i));
            return;
        }
        AlignHorizontalAbstract(fromUnderlyingValue);
    }

    public HorizontalAlignment AlignHorizontalAbstract() {
        return this.horizontalAlignment;
    }

    public void AlignHorizontalAbstract(HorizontalAlignment horizontalAlignment2) {
        this.alignmentSetter.setHorizontalAlignment(horizontalAlignment2);
        this.horizontalAlignment = horizontalAlignment2;
    }

    @Options(VerticalAlignment.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom. Vertical alignment has no effect if the screen is scrollable.")
    public int AlignVertical() {
        return AlignVerticalAbstract().toUnderlyingValue().intValue();
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(@Options(VerticalAlignment.class) int i) {
        VerticalAlignment fromUnderlyingValue = VerticalAlignment.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i));
            return;
        }
        AlignVerticalAbstract(fromUnderlyingValue);
    }

    public VerticalAlignment AlignVerticalAbstract() {
        return this.verticalAlignment;
    }

    public void AlignVerticalAbstract(VerticalAlignment verticalAlignment2) {
        this.alignmentSetter.setVerticalAlignment(verticalAlignment2);
        this.verticalAlignment = verticalAlignment2;
    }

    @Options(ScreenAnimation.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for switching to another screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String OpenScreenAnimation() {
        ScreenAnimation screenAnimation = this.openAnimType;
        if (screenAnimation != null) {
            return screenAnimation.toUnderlyingValue();
        }
        return null;
    }

    @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
    @SimpleProperty
    public void OpenScreenAnimation(@Options(ScreenAnimation.class) String str) {
        ScreenAnimation fromUnderlyingValue = ScreenAnimation.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, str);
            return;
        }
        OpenScreenAnimationAbstract(fromUnderlyingValue);
    }

    public ScreenAnimation OpenScreenAnimationAbstract() {
        return this.openAnimType;
    }

    public void OpenScreenAnimationAbstract(ScreenAnimation screenAnimation) {
        this.openAnimType = screenAnimation;
    }

    @Options(ScreenAnimation.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for closing current screen and returning  to the previous screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String CloseScreenAnimation() {
        if (this.closeAnimType != null) {
            return CloseScreenAnimationAbstract().toUnderlyingValue();
        }
        return null;
    }

    @DesignerProperty(defaultValue = "default", editorType = "screen_animation")
    @SimpleProperty
    public void CloseScreenAnimation(@Options(ScreenAnimation.class) String str) {
        ScreenAnimation fromUnderlyingValue = ScreenAnimation.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, str);
            return;
        }
        CloseScreenAnimationAbstract(fromUnderlyingValue);
    }

    public ScreenAnimation CloseScreenAnimationAbstract() {
        return this.closeAnimType;
    }

    public void CloseScreenAnimationAbstract(ScreenAnimation screenAnimation) {
        this.closeAnimType = screenAnimation;
    }

    @DesignerProperty(defaultValue = "Responsive", editorType = "sizing")
    @SimpleProperty(description = "If set to fixed,  screen layouts will be created for a single fixed-size screen and autoscaled. If set to responsive, screen layouts will use the actual resolution of the device.  See the documentation on responsive design in App Inventor for more information. This property appears on Screen1 only and controls the sizing for all screens in the app.", userVisible = false)
    public void Sizing(String str) {
        Log.d(LOG_TAG, "Sizing(" + str + ")");
        this.formWidth = (int) (((float) getResources().getDisplayMetrics().widthPixels) / this.deviceDensity);
        this.formHeight = (int) (((float) getResources().getDisplayMetrics().heightPixels) / this.deviceDensity);
        if (str.equals("Fixed")) {
            sCompatibilityMode = true;
            float f = this.compatScalingFactor;
            this.formWidth = (int) (((float) this.formWidth) / f);
            this.formHeight = (int) (((float) this.formHeight) / f);
        } else {
            sCompatibilityMode = false;
        }
        this.scaleLayout.setScale(sCompatibilityMode ? this.compatScalingFactor : 1.0f);
        FrameLayout frameLayout2 = this.frameLayout;
        if (frameLayout2 != null) {
            frameLayout2.invalidate();
        }
        Log.d(LOG_TAG, "formWidth = " + this.formWidth + " formHeight = " + this.formHeight);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If false, lists will be converted to strings using Lisp notation, i.e., as symbols separated by spaces, e.g., (a 1 b2 (c d). If true, lists will appear as in Json or Python, e.g.  [\"a\", 1, \"b\", 2, [\"c\", \"d\"]].  This property appears only in Screen 1, and the value for Screen 1 determines the behavior for all screens. The property defaults to \"false\" meaning that the App Inventor programmer must explicitly set it to \"true\" if JSON/Python syntax is desired. At some point in the future we will alter the system so that new projects are created with this property set to \"true\" by default. Existing projects will not be impacted. The App Inventor programmer can also set it back to \"false\" in newer projects if desired. ", userVisible = false)
    public void ShowListsAsJson(boolean z) {
        showListsAsJson = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public boolean ShowListsAsJson() {
        return showListsAsJson;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen width (x-size).")
    public int Width() {
        Log.d(LOG_TAG, "Form.Width = " + this.formWidth);
        return this.formWidth;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen height (y-size).")
    public int Height() {
        Log.d(LOG_TAG, "Form.Height = " + this.formHeight);
        return this.formHeight;
    }

    public static void switchForm(String str) {
        Form form = activeForm;
        if (form != null) {
            form.startNewForm(str, (Object) null);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void switchFormWithStartValue(String str, Object obj) {
        Log.i(LOG_TAG, "Open another screen with start value:".concat(String.valueOf(str)));
        Form form = activeForm;
        if (form != null) {
            form.startNewForm(str, obj);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    /* access modifiers changed from: protected */
    public void startNewForm(String str, Object obj) {
        String str2;
        Log.i(LOG_TAG, "startNewForm:".concat(String.valueOf(str)));
        Intent intent = new Intent();
        Log.i(LOG_TAG, "Trying to get package name...");
        String packageName = getPackageName();
        Log.i(LOG_TAG, "Package Name is ".concat(String.valueOf(packageName)));
        PackageManager packageManager = getPackageManager();
        String replaceAll = packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll("Screen1", str);
        Log.i(LOG_TAG, "Setting Intent Class to ".concat(String.valueOf(replaceAll)));
        intent.setClassName(this, replaceAll);
        String str3 = obj == null ? "open another screen" : "open another screen with start value";
        if (obj != null) {
            Log.i(LOG_TAG, "StartNewForm about to JSON encode:".concat(String.valueOf(obj)));
            str2 = jsonEncodeForForm(obj, str3);
            Log.i(LOG_TAG, "StartNewForm got JSON encoding:".concat(String.valueOf(str2)));
        } else {
            str2 = "";
        }
        intent.putExtra(ARGUMENT_NAME, str2);
        this.nextFormName = str;
        Log.i(LOG_TAG, "about to start new form".concat(String.valueOf(replaceAll)));
        try {
            Log.i(LOG_TAG, "startNewForm starting activity:".concat(String.valueOf(intent)));
            startActivityForResult(intent, 1);
            AnimationUtil.ApplyOpenScreenAnimation((Activity) this, this.openAnimType);
        } catch (ActivityNotFoundException unused) {
            dispatchErrorOccurredEvent(this, str3, ErrorMessages.ERROR_SCREEN_NOT_FOUND, replaceAll);
        }
    }

    public String getKodularPackageName() {
        String packageName = getPackageName();
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll(".Screen1", "");
        } catch (NullPointerException unused) {
            return packageName;
        }
    }

    public boolean isCustomPackage() {
        return !getPackageName().equals(getKodularPackageName());
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
    }

    public String getInstalledFrom() {
        String installerPackageName = $context().getPackageManager().getInstallerPackageName($context().getPackageName());
        if (installerPackageName == null) {
            return "Developer";
        }
        installerPackageName.hashCode();
        char c = 65535;
        switch (installerPackageName.hashCode()) {
            case -1859733809:
                if (installerPackageName.equals("com.amazon.venezia")) {
                    c = 0;
                    break;
                }
                break;
            case -1225090538:
                if (installerPackageName.equals("com.sec.android.app.samsungapps")) {
                    c = 1;
                    break;
                }
                break;
            case -1046965711:
                if (installerPackageName.equals("com.android.vending")) {
                    c = 2;
                    break;
                }
                break;
            case 565251532:
                if (installerPackageName.equals("com.google.android.feedback")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "Amazon Appstore";
            case 1:
                return "Samsung App Store";
            case 2:
            case 3:
                return "Google Play";
            default:
                return "Unknown";
        }
    }

    public boolean isInstalledThruStore() {
        return !getInstalledFrom().equals("Unknown");
    }

    public String getAppId() {
        return this.appId;
    }

    protected static String jsonEncodeForForm(Object obj, String str) {
        String str2;
        Log.i(LOG_TAG, "jsonEncodeForForm -- creating JSON representation:" + obj.toString());
        try {
            str2 = JsonUtil.getJsonRepresentation(obj);
            try {
                Log.i(LOG_TAG, "jsonEncodeForForm -- got JSON representation:".concat(String.valueOf(str2)));
            } catch (JSONException unused) {
            }
        } catch (JSONException unused2) {
            str2 = "";
            Form form = activeForm;
            form.dispatchErrorOccurredEvent(form, str, ErrorMessages.ERROR_SCREEN_BAD_VALUE_FOR_SENDING, obj.toString());
            return str2;
        }
        return str2;
    }

    @SimpleEvent(description = "Event raised when another screen has closed and control has returned to this screen.")
    public void OtherScreenClosed(String str, Object obj) {
        Log.i(LOG_TAG, "Form " + this.formName + " OtherScreenClosed, otherScreenName = " + str + ", result = " + obj.toString());
        EventDispatcher.dispatchEvent(this, "OtherScreenClosed", str, obj);
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public float deviceDensity() {
        return this.deviceDensity;
    }

    public float compatScalingFactor() {
        return this.compatScalingFactor;
    }

    public void setChildWidth(final AndroidViewComponent androidViewComponent, final int i) {
        int Width = Width();
        if (Width == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    System.err.println("(Form)Width not stable yet... trying again");
                    Form.this.setChildWidth(androidViewComponent, i);
                }
            }, 100);
        }
        PrintStream printStream = System.err;
        printStream.println("Form.setChildWidth(): width = " + i + " parent Width = " + Width + " child = " + androidViewComponent);
        if (i <= -1000) {
            i = (Width * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastWidth(i);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent.getView(), i);
    }

    public void setChildHeight(final AndroidViewComponent androidViewComponent, final int i) {
        if (Height() == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    System.err.println("(Form)Height not stable yet... trying again");
                    Form.this.setChildHeight(androidViewComponent, i);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Height() * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastHeight(i);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent.getView(), i);
    }

    public static Form getActiveForm() {
        return activeForm;
    }

    public static String getStartText() {
        Form form = activeForm;
        if (form != null) {
            return form.startupValue;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static Object getStartValue() {
        Form form = activeForm;
        if (form != null) {
            return decodeJSONStringForForm(form.startupValue, "get start value");
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void finishActivity() {
        Form form = activeForm;
        if (form != null) {
            form.closeForm((Intent) null);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void finishActivityWithResult(Object obj) {
        Form form = activeForm;
        if (form == null) {
            throw new IllegalStateException("activeForm is null");
        } else if (form instanceof ReplForm) {
            ((ReplForm) form).setResult(obj);
            activeForm.closeForm((Intent) null);
        } else {
            String jsonEncodeForForm = jsonEncodeForForm(obj, "close screen with value");
            Intent intent = new Intent();
            intent.putExtra(RESULT_NAME, jsonEncodeForForm);
            activeForm.closeForm(intent);
        }
    }

    public static void finishActivityWithTextResult(String str) {
        if (activeForm != null) {
            Intent intent = new Intent();
            intent.putExtra(RESULT_NAME, str);
            activeForm.closeForm(intent);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    /* access modifiers changed from: protected */
    public void closeForm(Intent intent) {
        if (intent != null) {
            setResult(-1, intent);
        }
        finish();
        AnimationUtil.ApplyCloseScreenAnimation((Activity) this, this.closeAnimType);
    }

    public static void finishApplication() {
        Form form = activeForm;
        if (form != null) {
            form.closeApplicationFromBlocks();
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    /* access modifiers changed from: protected */
    public void closeApplicationFromBlocks() {
        closeApplication();
    }

    private void closeApplication() {
        applicationIsBeingClosed = true;
        finish();
        if (this.formName.equals("Screen1")) {
            System.exit(0);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        this.customMenu = menu;
        this.customActionBarIcon = menu;
        addAboutInfoToMenu(menu);
        MenuInitialize();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.showOptionsMenu;
    }

    @SimpleEvent(description = "Event to detect when the menu has loaded. Set here your blocks like TitleBarIcon or AddMenuItem.")
    public void MenuInitialize() {
        EventDispatcher.dispatchEvent(this, "MenuInitialize", new Object[0]);
    }

    @DesignerProperty(defaultValue = "About this application", editorType = "string")
    @SimpleProperty(description = "Define the title of the about application option.")
    public void AboutScreenTitle(String str) {
        if (str == null || str.isEmpty()) {
            this.aboutScreenTitle = "About this application";
        } else {
            this.aboutScreenTitle = str;
        }
    }

    public void addAboutInfoToMenu(Menu menu) {
        menu.add(0, 0, 2, this.aboutScreenTitle).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public final boolean onMenuItemClick(MenuItem menuItem) {
                Form.this.showAboutApplicationNotification();
                return true;
            }
        });
    }

    @SimpleFunction(description = "Add a new item to the menu. Use the 'make a list' block.")
    public void AddMenuItem(YailList yailList) {
        String[] stringArray = yailList.toStringArray();
        if (this.customMenu != null) {
            for (String add : stringArray) {
                this.customMenu.add(0, 0, 0, add).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        Form.this.MenuItemSelected(menuItem.getTitle().toString());
                        return true;
                    }
                });
            }
        }
    }

    @SimpleFunction(description = "Add a new item with a icon on the left side to the menu. This function does not use the make a list block. If you want more items with icon then use this block again.")
    public void AddMenuItemWithIcon(String str, String str2) {
        BitmapDrawable bitmapDrawable;
        if (this.customMenu != null) {
            try {
                bitmapDrawable = MediaUtil.getBitmapDrawable(this, str2);
            } catch (Exception e) {
                Log.e(LOG_TAG, "AddMenuItemWithIcon: ".concat(String.valueOf(e)));
                bitmapDrawable = null;
            }
            if (bitmapDrawable != null) {
                this.customMenu.add(0, 0, 0, menuIconWithText(bitmapDrawable, str)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        Form.this.MenuItemSelected(menuItem.getTitle().toString());
                        return true;
                    }
                });
            }
        }
    }

    private CharSequence menuIconWithText(Drawable drawable, String str) {
        drawable.setBounds(0, 0, 75, 75);
        SpannableString spannableString = new SpannableString("       ".concat(String.valueOf(str)));
        spannableString.setSpan(new ImageSpan(drawable, 1), 0, 1, 33);
        return spannableString;
    }

    @SimpleEvent(description = "Event to detect when a menu item has been selected.")
    public void MenuItemSelected(String str) {
        EventDispatcher.dispatchEvent(this, "MenuItemSelected", str);
    }

    @SimpleFunction(description = "Reset the menu back to its default")
    public void ResetMenu() {
        Menu menu = this.customMenu;
        if (menu != null) {
            menu.clear();
            addAboutInfoToMenu(this.customMenu);
            super.onPrepareOptionsMenu(this.customMenu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ActionBarDrawerToggle actionBarDrawerToggle2 = this.actionBarDrawerToggle;
        if (actionBarDrawerToggle2 != null && actionBarDrawerToggle2.onOptionsItemSelected(menuItem)) {
            return true;
        }
        for (OnOptionsItemSelectedListener onOptionsItemSelected : this.onOptionsItemSelectedListeners) {
            if (onOptionsItemSelected.onOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    @SimpleEvent(description = "Event to detect when a menu item has been selected.")
    public void TitlebarBackButtonClicked() {
        EventDispatcher.dispatchEvent(this, "TitlebarBackButtonClicked", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void setYandexTranslateTagline() {
        this.yandexTranslateTagline = "<p><small>Language translation powered by Yandex.Translate</small></p>";
    }

    /* access modifiers changed from: private */
    public void showAboutApplicationNotification() {
        try {
            Notifier.aboutThisApp(this, (this.aboutScreen + "<p><small><em>Created with Kodular<br><a href=\"https://www.kodular.io\">kodular.io</a></em></small></p>" + this.yandexTranslateTagline).replaceAll("\\n", "<br>"), this.aboutScreenTitle, getString(17039370), this.aboutThisAppBackgroundColor, this.aboutThisAppLightTheme);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Show the dialog which shows when pressing the \"About This Application\" button in the menu.")
    public void ShowAboutApplication() {
        showAboutApplicationNotification();
    }

    public void clear() {
        this.viewLayout.getLayoutManager().removeAllViews();
        FrameLayout frameLayout2 = this.frameLayout;
        if (frameLayout2 != null) {
            frameLayout2.removeAllViews();
            this.frameLayout = null;
        }
        defaultPropertyValues();
        this.onStopListeners.clear();
        this.onNewIntentListeners.clear();
        this.onResumeListeners.clear();
        this.onPauseListeners.clear();
        this.onDestroyListeners.clear();
        this.onInitializeListeners.clear();
        this.onCreateOptionsMenuListeners.clear();
        this.onOptionsItemSelectedListeners.clear();
        this.onOrientationChangeListeners.clear();
        this.onCreateListeners.clear();
        screenInitialized = false;
        for (OnClearListener onClear : this.onClearListeners) {
            onClear.onClear();
        }
        this.onClearListeners.clear();
        System.err.println("Form.clear() About to do moby GC!");
        System.gc();
        this.dimChanges.clear();
    }

    public void deleteComponent(Object obj) {
        if (obj instanceof OnStopListener) {
            this.onStopListeners.remove(obj);
        }
        if (obj instanceof OnNewIntentListener) {
            this.onNewIntentListeners.remove(obj);
        }
        if (obj instanceof OnResumeListener) {
            this.onResumeListeners.remove(obj);
        }
        if (obj instanceof OnPauseListener) {
            this.onPauseListeners.remove(obj);
        }
        if (obj instanceof OnDestroyListener) {
            this.onDestroyListeners.remove(obj);
        }
        if (obj instanceof OnInitializeListener) {
            this.onInitializeListeners.remove(obj);
        }
        if (obj instanceof OnCreateOptionsMenuListener) {
            this.onCreateOptionsMenuListeners.remove(obj);
        }
        if (obj instanceof OnOptionsItemSelectedListener) {
            this.onOptionsItemSelectedListeners.remove(obj);
        }
        if (obj instanceof Deleteable) {
            ((Deleteable) obj).onDelete();
        }
        if (obj instanceof OnOrientationChangeListener) {
            this.onOrientationChangeListeners.remove(obj);
        }
        if (obj instanceof OnCreateListener) {
            this.onCreateListeners.remove(obj);
        }
    }

    public void dontGrabTouchEventsForComponent() {
        this.frameLayout.requestDisallowInterceptTouchEvent(true);
    }

    /* access modifiers changed from: protected */
    public boolean toastAllowed() {
        long nanoTime = System.nanoTime();
        if (nanoTime <= this.lastToastTime + minimumToastWait) {
            return false;
        }
        this.lastToastTime = nanoTime;
        return true;
    }

    public void callInitialize(Object obj) throws Throwable {
        try {
            OnCompanionRefresh();
            Method method = obj.getClass().getMethod("Initialize", (Class[]) null);
            try {
                Log.i(LOG_TAG, "calling Initialize method for Object " + obj.toString());
                method.invoke(obj, (Object[]) null);
            } catch (InvocationTargetException e) {
                Log.i(LOG_TAG, "invoke exception: " + e.getMessage());
                throw e.getTargetException();
            }
        } catch (SecurityException e2) {
            Log.i(LOG_TAG, "Security exception " + e2.getMessage());
        } catch (NoSuchMethodException unused) {
        }
    }

    public synchronized Bundle fullScreenVideoAction(int i, VideoPlayer videoPlayer, Object obj) {
        return this.fullScreenVideoUtil.performAction(i, videoPlayer, obj);
    }

    private void setBackground(View view) {
        Drawable drawable = this.backgroundDrawable;
        int i = -1;
        if (this.backgroundImagePath.equals("") || drawable == null) {
            int i2 = this.backgroundColor;
            if (i2 != 0) {
                i = i2;
            }
            drawable = new ColorDrawable(i);
        } else {
            if (this.backgroundDrawable.getConstantState() != null) {
                drawable = this.backgroundDrawable.getConstantState().newDrawable();
            }
            int i3 = this.backgroundColor;
            if (i3 != 0) {
                i = i3;
            }
            drawable.setColorFilter(i, PorterDuff.Mode.DST_OVER);
        }
        ViewUtil.setBackgroundImage(view, drawable);
        view.invalidate();
    }

    public static boolean getCompatibilityMode() {
        return sCompatibilityMode;
    }

    @SimpleFunction(description = "Move task to back. That means it will minimize your current app.")
    public void MoveTaskToBack() {
        moveTaskToBack(true);
    }

    @SimpleProperty(description = "Set title bar color")
    public void TitleBarColor(int i) {
        this.titleBarColor = i;
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            int i2 = this.titleBarColor;
            if (i2 == 0) {
                i2 = -1;
            }
            supportActionBar.setBackgroundDrawable(new ColorDrawable(i2));
        }
    }

    @SimpleProperty
    public int TitleBarColor() {
        return this.titleBarColor;
    }

    @SimpleProperty(description = "Set status bar color. This will work starting from API Level 21 (Android Lollipop")
    public void StatusBarColor(int i) {
        this.statusbarColor = i;
        if (i == 0) {
            i = 0;
        }
        KodularHelper.setStatusBarColor(this, i);
    }

    @SimpleProperty
    public int StatusBarColor() {
        return this.statusbarColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Set navigation bar color. This will work starting from API Level 21 (Android Lollipop)")
    public void NavigationBarColor(int i) {
        KodularHelper.setNavigationBarColor(this, i);
    }

    @SimpleProperty
    public int NavigationBarColor() {
        return getWindow().getNavigationBarColor();
    }

    @SimpleFunction(description = "This block will returns the version name")
    public String VersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return "0";
        }
    }

    @SimpleFunction(description = "This block will returns the version code")
    public int VersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return 0;
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the TitleBar's subtitle.")
    public void TitleBarSubTitle(String str) {
        this.toolbarSubTitle = str;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle((CharSequence) TextViewUtil.fromHtml(str));
        }
    }

    @SimpleProperty(description = "Get the TitleBar's subtitle.")
    public String TitleBarSubTitle() {
        if (getSupportActionBar() != null && getSupportActionBar().getSubtitle() != null) {
            return getSupportActionBar().getSubtitle().toString();
        }
        String str = this.formName;
        return str != null ? str : "Screen1";
    }

    @SimpleFunction(description = "Show the keyboard")
    public void ShowKeyboard() {
        ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(2, 1);
    }

    @SimpleFunction(description = "Hide the keyboard.")
    public void HideKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = this.frameLayout;
        }
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    @SimpleProperty(description = "Returns the status of the keyboard. If the keyboard is visible then the result is true.")
    public boolean isKeyboardVisible() {
        return this.keyboardReallyShown;
    }

    @SimpleFunction(description = "Add a new action icon to the TitleBar. You will see a toast message on a long click with your choosen name. Add this block to the \"MenuInitialize\" event.")
    public void AddTitleBarIcon(final String str, final String str2) {
        BitmapDrawable bitmapDrawable;
        Menu menu;
        try {
            bitmapDrawable = MediaUtil.getBitmapDrawable(this, str);
        } catch (Exception e) {
            Log.e(LOG_TAG, "AddTitleBarIcon: ".concat(String.valueOf(e)));
            bitmapDrawable = null;
        }
        if (bitmapDrawable != null && (menu = this.customActionBarIcon) != null && menu.size() < 3) {
            MenuItem add = this.customActionBarIcon.add(0, 24072017, 0, str2);
            add.setShowAsAction(2);
            add.setIcon(bitmapDrawable);
            add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    Form.this.TitleBarIconSelected(str, str2);
                    return true;
                }
            });
        }
    }

    @SimpleEvent(description = "The event returns the 'icon' or 'name' of the selected icon.")
    public void TitleBarIconSelected(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "TitleBarIconSelected", str, str2);
    }

    @SimpleFunction(description = "Remove all added action icons from the TitleBar.")
    public void RemoveTitleBarIcons() {
        Menu menu = this.customActionBarIcon;
        if (menu != null) {
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                menu.removeItem(24072017);
            }
        }
    }

    @SimpleProperty(description = "Set a custom color for the TitleBar text.")
    public void TitlebarTextColor(int i) {
        this.titleBarTextColor = i;
        titleBarTextColorHelper(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the text color from the TitleBar.")
    public int TitlebarTextColor() {
        return this.titleBarTextColor;
    }

    @SimpleProperty(description = "If true it will show in the TitleBar a back button only if no side menu was added. If a side menu was added it will remove the ‘hamburger’-menu icon but the side menu can still be opened.")
    public void ShowTitlebarBackButton(boolean z) {
        this.stateBackButton = z;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(z);
        }
        UpdateTitlebarItemColor(this.toolbarIconColor);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the state of titlebar back button.")
    public boolean ShowTitlebarBackButton() {
        return this.stateBackButton;
    }

    @SimpleEvent(description = "When the activity enters the Resumed state, it comes to the foreground, and then the system invokes this event.")
    public void OnAppResume() {
        EventDispatcher.dispatchEvent(this, "OnAppResume", new Object[0]);
    }

    @SimpleEvent(description = "The system calls this method as the first indication that the user is leaving your activity (though it does not always mean the activity is being destroyed).")
    public void OnAppPause() {
        EventDispatcher.dispatchEvent(this, "OnAppPause", new Object[0]);
    }

    @SimpleEvent(description = "When your activity is no longer visible to the user, it has entered the Stopped state, and the system invokes this event.")
    public void OnAppStop() {
        EventDispatcher.dispatchEvent(this, "OnAppStop", new Object[0]);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void ShowOptionsMenu(boolean z) {
        this.showOptionsMenu = z;
    }

    @SimpleFunction(description = "Sets information describing the task with this activity for presentation inside the Recents System UI. You will see the settings if the device API is >= 21 and you minimize the app.")
    public void TaskDescription(String str, int i) {
        KodularHelper.setAppBackgroundTaskInfo(this, str, i);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "experimental")
    @SimpleProperty
    public void ShowNavBar(final boolean z) {
        this.showNavBar = z;
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(VisibilityHelper(z));
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            public final void onSystemUiVisibilityChange(int i) {
                decorView.setSystemUiVisibility(Form.this.VisibilityHelper(z));
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Show/Hide Navigation Bar")
    public boolean ShowNavBar() {
        return this.showNavBar;
    }

    @SimpleFunction(description = "Remove a first created side menu. This block will be usefull if you need to update a side menu dynamically. You can use this block too to test a side menu in the companion. Add then this block above of the 'Side Menu' block.")
    public void RemoveSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            try {
                this.drawerLayout.removeViewAt(1);
                this.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                this.layoutBackup = null;
                this.actionBarDrawerToggle = null;
                UpdateTitlebarItemColor(this.toolbarIconColor);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
    }

    @SimpleFunction(description = "Use this block to lock the side menu. This means the user can not open the side menu until the side menu  unlock block is used.")
    public void LockSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            this.lockedMenu = true;
            this.drawerLayout.setDrawerLockMode(1);
            this.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @SimpleFunction(description = "Use this block to unlock the side menu. This means the user can now open again the side menu.")
    public void UnlockSideMenu() {
        if (this.drawerLayout != null && this.actionBarDrawerToggle != null && getSupportActionBar() != null) {
            this.lockedMenu = false;
            this.drawerLayout.setDrawerLockMode(0);
            this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @SimpleFunction(description = "Create a Side Menu. Set to \"layout\" your layout that will be then your side menu. Use as example a vertical arrangement. Your choosen layout will be then removed from the screen and only visible in the side menu.\"Information\": This block works on companion only if you add a side menu on button click.Don’t add it in companion on \"screen initialize event\". Else the companion will crash.Do NOT use this block with the Side Menu Layout component")
    public void SideMenu(AndroidViewComponent androidViewComponent) {
        ViewGroup viewGroup = (ViewGroup) androidViewComponent.getView();
        try {
            this.layoutBackup = androidViewComponent;
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
            DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(KodularUnitUtil.DpToPixels((Context) this, 296), -1);
            this.lp = layoutParams;
            layoutParams.gravity = GravityCompat.START;
            viewGroup.setLayoutParams(this.lp);
            viewGroup.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            DrawerLayout drawerLayout2 = this.drawerLayout;
            if (drawerLayout2 != null) {
                drawerLayout2.addView(viewGroup);
                this.drawerLayout.setDrawerLockMode(0);
                this.drawerLayout.invalidate();
                AddDrawerSync();
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, e.getMessage());
            this.layoutBackup = null;
        }
    }

    public void SideMenuLayout(MakeroidSideMenuLayout makeroidSideMenuLayout) {
        ViewGroup viewGroup = (ViewGroup) makeroidSideMenuLayout.getView();
        this.layoutBackup = makeroidSideMenuLayout;
        if (viewGroup.getParent() != null) {
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
        }
        DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(KodularUnitUtil.DpToPixels((Context) this, 296), -1);
        this.lp = layoutParams;
        layoutParams.gravity = GravityCompat.START;
        viewGroup.setLayoutParams(this.lp);
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        DrawerLayout drawerLayout2 = this.drawerLayout;
        if (drawerLayout2 != null) {
            drawerLayout2.addView(viewGroup);
            this.drawerLayout.setDrawerLockMode(0);
            this.drawerLayout.invalidate();
            AddDrawerSync();
        }
    }

    public void AddDrawerSync() {
        AnonymousClass9 r0 = new ActionBarDrawerToggle(this, this.drawerLayout) {
            public final void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public final void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };
        this.actionBarDrawerToggle = r0;
        r0.syncState();
        this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        DrawerLayout drawerLayout2 = this.drawerLayout;
        if (drawerLayout2 != null) {
            drawerLayout2.addDrawerListener(this.actionBarDrawerToggle);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        UpdateTitlebarItemColor(this.toolbarIconColor);
    }

    @SimpleFunction(description = "If you had set your side menu then you can use this block to open it as example with a button click.")
    public void SideMenuOpen() {
        DrawerLayout drawerLayout2;
        if (this.actionBarDrawerToggle != null && (drawerLayout2 = this.drawerLayout) != null) {
            drawerLayout2.openDrawer((int) GravityCompat.START);
        }
    }

    @SimpleFunction(description = "If you had set your side menu then you can use this block to close it as example with a button click.")
    public void SideMenuClose() {
        DrawerLayout drawerLayout2;
        if (this.actionBarDrawerToggle != null && (drawerLayout2 = this.drawerLayout) != null) {
            drawerLayout2.closeDrawer((int) GravityCompat.START);
        }
    }

    @SimpleProperty(description = "Returns true if a side menu is current open. Else it will return false.")
    public boolean IsSideMenuOpen() {
        DrawerLayout drawerLayout2 = this.drawerLayout;
        if (drawerLayout2 != null) {
            return drawerLayout2.isDrawerOpen((int) GravityCompat.START);
        }
        return false;
    }

    @SimpleProperty(description = "Returns true if a side menu is added to the screen.")
    public boolean IsSideMenuAdded() {
        return (this.actionBarDrawerToggle == null || this.drawerLayout == null) ? false : true;
    }

    @SimpleEvent(description = "Event will be invoked if the side menu was opened.")
    public void SideMenuOpened() {
        EventDispatcher.dispatchEvent(this, "SideMenuOpened", new Object[0]);
    }

    @SimpleEvent(description = "Event will be invoked if the side menu was closed.")
    public void SideMenuClosed() {
        EventDispatcher.dispatchEvent(this, "SideMenuClosed", new Object[0]);
    }

    @SimpleProperty(description = "This option tells the system to use dark statusbar icons, useful for lighter colored status bars. Works only for devices with API >= 23.")
    public void StatusbarLightIcons(boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.w(LOG_TAG, "Sorry, statusbar light icons is not available for API Level < 23");
            return;
        }
        this.statusbarLight = z;
        if (z && NavigationBarLightIcons()) {
            getWindow().getDecorView().setSystemUiVisibility(-2147475440);
        } else if (z) {
            getWindow().getDecorView().setSystemUiVisibility(8192);
        } else if (NavigationBarLightIcons()) {
            NavigationBarLightIcons(true);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    @SimpleProperty
    public boolean StatusbarLightIcons() {
        return this.statusbarLight;
    }

    @SimpleProperty(description = "This option tells the system to use dark navigation bar icons, useful for lighter colored navigation bars. Works only for devices with API >= 26.")
    public void NavigationBarLightIcons(boolean z) {
        if (Build.VERSION.SDK_INT < 26) {
            Log.w(LOG_TAG, "Sorry, navigation bar light icons is not available for API Level < 26");
            return;
        }
        this.navigationBarLight = z;
        if (z && StatusbarLightIcons()) {
            getWindow().getDecorView().setSystemUiVisibility(-2147475440);
        } else if (z) {
            getWindow().getDecorView().setSystemUiVisibility(-2147483632);
        } else if (StatusbarLightIcons()) {
            StatusbarLightIcons(true);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    @SimpleProperty
    public boolean NavigationBarLightIcons() {
        return this.navigationBarLight;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for Material UI elements, such as the ActionBar.", userVisible = false)
    public void PrimaryColor(int i) {
        ActionBar supportActionBar = getSupportActionBar();
        int i2 = i == 0 ? DEFAULT_PRIMARY_COLOR : i;
        this.titleBarColor = i2;
        if (supportActionBar != null && i2 != this.primaryColor) {
            this.primaryColor = i2;
            this.titleBarColor = i;
            supportActionBar.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    @SimpleProperty
    public int PrimaryColor() {
        return this.primaryColor;
    }

    @DesignerProperty(defaultValue = "&HFF303F9F", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the primary color used for darker elements in Material UI.", userVisible = false)
    public void PrimaryColorDark(int i) {
        this.primaryColorDark = i;
    }

    @SimpleProperty
    public int PrimaryColorDark() {
        return this.primaryColorDark;
    }

    @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is the accent color used for highlights and other user interface accents.", userVisible = false)
    public void AccentColor(int i) {
        this.accentColor = i;
    }

    @SimpleProperty
    public int AccentColor() {
        return this.accentColor;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If set to true the user will see a splash screen while the app is loading the content.", userVisible = false)
    public void SplashEnabled(boolean z) {
        this.splashEnabled = z;
    }

    @SimpleProperty
    public boolean SplashEnabled() {
        return this.splashEnabled;
    }

    @SimpleFunction(description = "Returns true if ALL needed app permissions were granted, else false.")
    public boolean ArePermissionsGranted() {
        return PermissionUtil.arePermissionsGranted(this);
    }

    @SimpleFunction(description = "Opens the settings screen of the app. Useful if 'Are Permissions Granted' has returned false.")
    public void OpenAppSettings() {
        PermissionUtil.appSettings(this);
    }

    @SimpleFunction(description = "Opens the app's system settings page. This works only for devices with android 6+.")
    public void OpenSystemWriteSettings() {
        PermissionUtil.appSystemSettings(this);
    }

    @SimpleFunction(description = "Returns true if the app can write system settings, else it returns false. It will return true automatic for devices with android version below 6 (API 23).")
    public boolean CanWriteSystemSettings() {
        return PermissionUtil.writeSystemSettings(this);
    }

    public String getAssetPathForExtension(Component component, String str) throws FileNotFoundException {
        String name = component.getClass().getPackage().getName();
        return ASSETS_PREFIX + name + InternalZipConstants.ZIP_FILE_SEPARATOR + str;
    }

    public InputStream openAssetForExtension(Component component, String str) throws IOException {
        return openAssetInternal(getAssetPathForExtension(component, str));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int AboutScreenBackgroundColor() {
        return this.aboutThisAppBackgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty
    public void AboutScreenBackgroundColor(int i) {
        this.aboutThisAppBackgroundColor = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean AboutScreenLightTheme() {
        return this.aboutThisAppLightTheme;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void AboutScreenLightTheme(boolean z) {
        this.aboutThisAppLightTheme = z;
    }

    public void removeElevation() {
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setElevation(0.0f);
            }
            AppBarLayout appBarLayout2 = this.appBarLayout;
            if (appBarLayout2 != null) {
                appBarLayout2.setOutlineProvider((ViewOutlineProvider) null);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    public void addElevation() {
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setElevation(5.0f);
            }
            AppBarLayout appBarLayout2 = this.appBarLayout;
            if (appBarLayout2 != null) {
                appBarLayout2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void TitleBarFontTypeface(int i) {
        this.fontTypeface = i;
        if (getSupportActionBar() != null) {
            TextViewUtil.setContext(this);
            titleBarFontHelper("", i, false);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int TitleBarFontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void TitleBarTypefaceImport(String str) {
        if (getSupportActionBar() != null && str != null && !str.isEmpty()) {
            titleBarFontHelper(str, 0, true);
        }
    }

    private void titleBarFontHelper(String str, int i, boolean z) {
        TextViewUtil.setContext(this);
        for (int i2 = 0; i2 < this.toolbar.getChildCount(); i2++) {
            View childAt = this.toolbar.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText().equals(this.toolbar.getTitle()) || textView.getText().equals(this.toolbar.getSubtitle())) {
                    if (z) {
                        TextViewUtil.setCustomFontTypeface($form(), textView, str, false, false);
                    } else {
                        TextViewUtil.setFontTypeface(textView, i, false, false);
                    }
                }
            }
        }
    }

    private void titleBarTextColorHelper(int i) {
        for (int i2 = 0; i2 < this.toolbar.getChildCount(); i2++) {
            View childAt = this.toolbar.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText().equals(this.toolbar.getTitle())) {
                    textView.setTextColor(i);
                }
            }
        }
    }

    private void UpdateTitlebarItemColor(int i) {
        this.toolbarIconColor = i;
        if (i == 0) {
            i = KodularResourcesUtil.getColor(this, "colorToolbar");
        }
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 != null) {
            if (toolbar2.getOverflowIcon() != null) {
                this.toolbar.getOverflowIcon().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
            }
            if (this.toolbar.getNavigationIcon() != null) {
                this.toolbar.getNavigationIcon().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
            }
            ActionBarDrawerToggle actionBarDrawerToggle2 = this.actionBarDrawerToggle;
            if (actionBarDrawerToggle2 != null) {
                actionBarDrawerToggle2.getDrawerArrowDrawable().setColor(i);
            }
        }
        this.optionsMenuIconColor = i;
        this.navigationIconColor = i;
        this.drawerArrowIconColor = i;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void HighQuality(boolean z) {
        this.highQuality = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set to true, pictures will be loaded in high quality.")
    public boolean HighQuality() {
        return this.highQuality;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This block will return true, if you are running your project current in the companion application. Else it will return false.")
    public boolean IsCompanion() {
        return this.isCompanion;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Keep the device's screen turned on and bright.")
    public void KeepScreenOn(boolean z) {
        this.keepScreenOn = z;
        if (z) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
    }

    @SimpleProperty(description = "Returns true if keep screen on is set to enabled, else it returns false.")
    public boolean KeepScreenOn() {
        return this.keepScreenOn;
    }

    private void OnCompanionRefresh() {
        KodularCompanionUtil.drawerLayoutFix(this.drawerLayout, IsSideMenuAdded(), this.isCompanion);
        KodularCompanionUtil.toolbarColor(this.toolbar, this.isCompanion, this.titleBarColor);
        KodularCompanionUtil.statusBarColor(this, this.isCompanion, this.primaryColorDark);
        Object obj = this.layoutBackup;
        if (obj != null && (obj instanceof MakeroidSideMenuLayout)) {
            SideMenuLayout((MakeroidSideMenuLayout) obj);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionResultHandler permissionResultHandler = this.permissionHandlers.get(Integer.valueOf(i));
        if (permissionResultHandler == null) {
            Log.e(LOG_TAG, "Received permission response which we cannot match.");
            return;
        }
        if (iArr.length <= 0) {
            Log.d(LOG_TAG, "onRequestPermissionsResult: grantResults.length = " + iArr.length + " requestCode = " + i);
        } else if (iArr[0] == 0) {
            permissionResultHandler.HandlePermissionResponse(strArr[0], true);
        } else {
            permissionResultHandler.HandlePermissionResponse(strArr[0], false);
        }
        this.permissionHandlers.remove(Integer.valueOf(i));
    }

    @SimpleEvent
    public void PermissionDenied(Component component, String str, @Options(Permission.class) String str2) {
        if (str2.startsWith("android.permission.")) {
            str2 = str2.replace("android.permission.", "");
        }
        if (!EventDispatcher.dispatchEvent(this, "PermissionDenied", component, str, str2)) {
            dispatchErrorOccurredEvent(component, str, ErrorMessages.ERROR_PERMISSION_DENIED, str2);
        }
    }

    @SimpleEvent
    public void PermissionGranted(@Options(Permission.class) String str) {
        if (str.startsWith("android.permission.")) {
            str = str.replace("android.permission.", "");
        }
        EventDispatcher.dispatchEvent(this, "PermissionGranted", str);
    }

    @SimpleFunction
    public void AskForPermission(@Options(Permission.class) String str) {
        if (!str.contains(".")) {
            str = "android.permission.".concat(String.valueOf(str));
        }
        askPermission(str, new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    Form.this.PermissionGranted(str);
                    return;
                }
                Form form = Form.this;
                form.PermissionDenied(form, "RequestPermission", str);
            }
        });
    }

    public void AskForPermissionAbstract(Permission permission) {
        AskForPermission(permission.toUnderlyingValue());
    }

    public boolean isDeniedPermission(String str) {
        return Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, str) == -1;
    }

    public void assertPermission(String str) {
        if (isDeniedPermission(str)) {
            throw new PermissionException(str);
        }
    }

    public void askPermission(final String str, final PermissionResultHandler permissionResultHandler) {
        if (!isDeniedPermission(str)) {
            permissionResultHandler.HandlePermissionResponse(str, true);
        } else {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    int nextInt = Form.this.permissionRandom.nextInt(65535);
                    Log.d(Form.LOG_TAG, "askPermission: permission = " + str + " requestCode = " + nextInt);
                    Form.this.permissionHandlers.put(Integer.valueOf(nextInt), permissionResultHandler);
                    ActivityCompat.requestPermissions(this, new String[]{str}, nextInt);
                }
            });
        }
    }

    public void askPermission(final BulkPermissionRequest bulkPermissionRequest) {
        final List<String> permissions2 = bulkPermissionRequest.getPermissions();
        Iterator<String> it = permissions2.iterator();
        while (it.hasNext()) {
            if (!isDeniedPermission(it.next())) {
                it.remove();
            }
        }
        if (permissions2.size() == 0) {
            bulkPermissionRequest.onGranted();
        } else {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    final Iterator it = permissions2.iterator();
                    Form.this.askPermission((String) it.next(), new PermissionResultHandler() {
                        private List<String> mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = new ArrayList();

                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (!z) {
                                this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.add(str);
                            }
                            if (it.hasNext()) {
                                Form.this.askPermission((String) it.next(), this);
                            } else if (this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.size() == 0) {
                                bulkPermissionRequest.onGranted();
                            } else {
                                bulkPermissionRequest.onDenied((String[]) this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT.toArray(new String[0]));
                            }
                        }
                    });
                }
            });
        }
    }

    public void dispatchPermissionDeniedEvent(Component component, String str, PermissionException permissionException) {
        permissionException.printStackTrace();
        dispatchPermissionDeniedEvent(component, str, permissionException.getPermissionNeeded());
    }

    public void dispatchPermissionDeniedEvent(final Component component, final String str, final String str2) {
        runOnUiThread(new Runnable() {
            public final void run() {
                Form.this.PermissionDenied(component, str, str2);
            }
        });
    }

    public boolean doesAppDeclarePermission(String str) {
        return this.permissions.contains(str);
    }

    public String getAssetPath(String str) {
        return ASSETS_PREFIX.concat(String.valueOf(str));
    }

    public String getCachePath(String str) {
        return "file://" + new File(getCacheDir(), str).getAbsolutePath();
    }

    public String getDefaultPath(String str) {
        return FileUtil.resolveFileName(this, str, this.defaultFileScope);
    }

    public String getPrivatePath(String str) {
        return "file://" + new File(getFilesDir(), str).getAbsolutePath();
    }

    public InputStream openAsset(String str) throws IOException {
        return openAssetInternal(getAssetPath(str));
    }

    /* access modifiers changed from: package-private */
    public InputStream openAssetInternal(String str) throws IOException {
        if (str.startsWith(ASSETS_PREFIX)) {
            return getAssets().open(str.substring(22));
        }
        if (str.startsWith("file:")) {
            return FileUtil.openFile(this, URI.create(str));
        }
        return FileUtil.openFile(this, str);
    }

    @SimpleEvent(description = "Event to detect that a user shared content to your app throw the sharing dialog of any other app. Type stand for integer. 0 = nothing shared, 1 = audio, 2 = image, 3 = text or 4 = video")
    public void GotReceivedShared(int i, String str) {
        EventDispatcher.dispatchEvent(this, "GotReceivedShared", Integer.valueOf(i), str);
    }

    @SimpleProperty(description = "Set the options menu icon color.")
    public void OptionsMenuIconColor(int i) {
        Toolbar toolbar2 = this.toolbar;
        if (!(toolbar2 == null || toolbar2.getOverflowIcon() == null)) {
            try {
                this.toolbar.getOverflowIcon().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.optionsMenuIconColor = i;
    }

    @SimpleProperty(description = "Get the options menu icon color.")
    public int OptionsMenuIconColor() {
        return this.optionsMenuIconColor;
    }

    @SimpleProperty(description = "Set the navigation icon color.")
    public void NavigationIconColor(int i) {
        Toolbar toolbar2 = this.toolbar;
        if (!(toolbar2 == null || toolbar2.getNavigationIcon() == null)) {
            try {
                this.toolbar.getNavigationIcon().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.navigationIconColor = i;
    }

    @SimpleProperty(description = "Get the navigation icon color.")
    public int NavigationIconColor() {
        return this.navigationIconColor;
    }

    @SimpleProperty(description = "Set the drawer arrow icon color.")
    public void DrawerArrowIconColor(int i) {
        ActionBarDrawerToggle actionBarDrawerToggle2 = this.actionBarDrawerToggle;
        if (actionBarDrawerToggle2 != null) {
            try {
                actionBarDrawerToggle2.getDrawerArrowDrawable().setColor(i);
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        this.drawerArrowIconColor = i;
    }

    @SimpleProperty(description = "Get the drawer arrow icon color.")
    public int DrawerArrowIconColor() {
        return this.drawerArrowIconColor;
    }
}
