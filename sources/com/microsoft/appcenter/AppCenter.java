package com.microsoft.appcenter;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.microsoft.appcenter.channel.Channel;
import com.microsoft.appcenter.channel.DefaultChannel;
import com.microsoft.appcenter.channel.OneCollectorChannelListener;
import com.microsoft.appcenter.http.HttpClient;
import com.microsoft.appcenter.http.HttpUtils;
import com.microsoft.appcenter.ingestion.Ingestion;
import com.microsoft.appcenter.ingestion.models.StartServiceLog;
import com.microsoft.appcenter.ingestion.models.WrapperSdk;
import com.microsoft.appcenter.ingestion.models.json.DefaultLogSerializer;
import com.microsoft.appcenter.ingestion.models.json.LogFactory;
import com.microsoft.appcenter.ingestion.models.json.LogSerializer;
import com.microsoft.appcenter.ingestion.models.json.StartServiceLogFactory;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.ApplicationLifecycleListener;
import com.microsoft.appcenter.utils.DeviceInfoHelper;
import com.microsoft.appcenter.utils.IdHelper;
import com.microsoft.appcenter.utils.InstrumentationRegistryHelper;
import com.microsoft.appcenter.utils.NetworkStateHelper;
import com.microsoft.appcenter.utils.PrefStorageConstants;
import com.microsoft.appcenter.utils.async.AppCenterFuture;
import com.microsoft.appcenter.utils.async.DefaultAppCenterFuture;
import com.microsoft.appcenter.utils.context.SessionContext;
import com.microsoft.appcenter.utils.context.UserIdContext;
import com.microsoft.appcenter.utils.storage.FileManager;
import com.microsoft.appcenter.utils.storage.SharedPreferencesManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class AppCenter {
    static final String APP_SECRET_KEY = "appsecret";
    static final String CORE_GROUP = "group_core";
    static final long DEFAULT_MAX_STORAGE_SIZE_IN_BYTES = 10485760;
    static final String KEY_VALUE_DELIMITER = "=";
    public static final String LOG_TAG = "AppCenter";
    static final long MINIMUM_STORAGE_SIZE = 24576;
    static final String PAIR_DELIMITER = ";";
    static final String RUNNING_IN_APP_CENTER = "RUNNING_IN_APP_CENTER";
    static final String TRANSMISSION_TARGET_TOKEN_KEY = "target";
    private static final String TRUE_ENVIRONMENT_STRING = "1";
    private static AppCenter sInstance;
    private Boolean mAllowedNetworkRequests;
    private AppCenterHandler mAppCenterHandler;
    /* access modifiers changed from: private */
    public String mAppSecret;
    private Application mApplication;
    private ApplicationLifecycleListener mApplicationLifecycleListener;
    /* access modifiers changed from: private */
    public Channel mChannel;
    private boolean mConfiguredFromApp;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mLogLevelConfigured;
    private LogSerializer mLogSerializer;
    private String mLogUrl;
    private long mMaxStorageSizeInBytes = DEFAULT_MAX_STORAGE_SIZE_IN_BYTES;
    /* access modifiers changed from: private */
    public OneCollectorChannelListener mOneCollectorChannelListener;
    private Set<AppCenterService> mServices;
    private Set<AppCenterService> mServicesStartedFromLibrary;
    private DefaultAppCenterFuture<Boolean> mSetMaxStorageSizeFuture;
    private final List<String> mStartedServicesNamesToLog = new ArrayList();
    private String mTransmissionTargetToken;
    private UncaughtExceptionHandler mUncaughtExceptionHandler;

    public static String getSdkVersion() {
        return "5.0.0";
    }

    public static synchronized AppCenter getInstance() {
        AppCenter appCenter;
        synchronized (AppCenter.class) {
            if (sInstance == null) {
                sInstance = new AppCenter();
            }
            appCenter = sInstance;
        }
        return appCenter;
    }

    static synchronized void unsetInstance() {
        synchronized (AppCenter.class) {
            sInstance = null;
            NetworkStateHelper.unsetInstance();
        }
    }

    public static void setWrapperSdk(WrapperSdk wrapperSdk) {
        getInstance().setInstanceWrapperSdk(wrapperSdk);
    }

    public static int getLogLevel() {
        return AppCenterLog.getLogLevel();
    }

    public static void setLogLevel(int i) {
        getInstance().setInstanceLogLevel(i);
    }

    public static void setLogUrl(String str) {
        getInstance().setInstanceLogUrl(str);
    }

    public static void setCountryCode(String str) {
        DeviceInfoHelper.setCountryCode(str);
    }

    public static boolean isConfigured() {
        return getInstance().isInstanceConfigured();
    }

    public static boolean isRunningInAppCenterTestCloud() {
        try {
            return TRUE_ENVIRONMENT_STRING.equals(InstrumentationRegistryHelper.getArguments().getString(RUNNING_IN_APP_CENTER));
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public static void configure(Application application, String str) {
        getInstance().configureInstanceWithRequiredAppSecret(application, str);
    }

    public static void configure(Application application) {
        getInstance().configureInstance(application, (String) null, true);
    }

    @SafeVarargs
    public static void start(Class<? extends AppCenterService>... clsArr) {
        getInstance().startServices(true, clsArr);
    }

    @SafeVarargs
    public static void start(Application application, String str, Class<? extends AppCenterService>... clsArr) {
        getInstance().configureAndStartServices(application, str, clsArr);
    }

    @SafeVarargs
    public static void start(Application application, Class<? extends AppCenterService>... clsArr) {
        getInstance().configureAndStartServices(application, (String) null, true, clsArr);
    }

    @SafeVarargs
    public static void startFromLibrary(Context context, Class<? extends AppCenterService>... clsArr) {
        getInstance().startInstanceFromLibrary(context, clsArr);
    }

    public static void setLogger(Logger logger) {
        AppCenterLog.setLogger(logger);
    }

    public static void setNetworkRequestsAllowed(boolean z) {
        getInstance().setInstanceNetworkRequestsAllowed(z);
    }

    public static boolean isNetworkRequestsAllowed() {
        return getInstance().isInstanceNetworkRequestsAllowed();
    }

    public static AppCenterFuture<Boolean> isEnabled() {
        return getInstance().isInstanceEnabledAsync();
    }

    public static AppCenterFuture<Void> setEnabled(boolean z) {
        return getInstance().setInstanceEnabledAsync(z);
    }

    public static AppCenterFuture<UUID> getInstallId() {
        return getInstance().getInstanceInstallIdAsync();
    }

    public static AppCenterFuture<Boolean> setMaxStorageSize(long j) {
        return getInstance().setInstanceMaxStorageSizeAsync(j);
    }

    private synchronized void setInstanceUserId(String str) {
        if (!this.mConfiguredFromApp) {
            AppCenterLog.error("AppCenter", "AppCenter must be configured from application, libraries cannot use call setUserId.");
            return;
        }
        String str2 = this.mAppSecret;
        if (str2 == null && this.mTransmissionTargetToken == null) {
            AppCenterLog.error("AppCenter", "AppCenter must be configured with a secret from application to call setUserId.");
            return;
        }
        if (str != null) {
            if (str2 != null) {
                if (!UserIdContext.checkUserIdValidForAppCenter(str)) {
                    return;
                }
            }
            if (this.mTransmissionTargetToken != null && !UserIdContext.checkUserIdValidForOneCollector(str)) {
                return;
            }
        }
        UserIdContext.getInstance().setUserId(str);
    }

    private synchronized boolean checkPrecondition() {
        if (isInstanceConfigured()) {
            return true;
        }
        AppCenterLog.error("AppCenter", "App Center hasn't been configured. You need to call AppCenter.start with appSecret or AppCenter.configure first.");
        return false;
    }

    private synchronized void setInstanceWrapperSdk(WrapperSdk wrapperSdk) {
        DeviceInfoHelper.setWrapperSdk(wrapperSdk);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                public void run() {
                    AppCenter.this.mChannel.invalidateDeviceCache();
                }
            });
        }
    }

    private synchronized void setInstanceLogLevel(int i) {
        this.mLogLevelConfigured = true;
        AppCenterLog.setLogLevel(i);
    }

    private synchronized void setInstanceLogUrl(final String str) {
        this.mLogUrl = str;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                public void run() {
                    if (AppCenter.this.mAppSecret != null) {
                        AppCenterLog.info("AppCenter", "The log url of App Center endpoint has been changed to " + str);
                        AppCenter.this.mChannel.setLogUrl(str);
                        return;
                    }
                    AppCenterLog.info("AppCenter", "The log url of One Collector endpoint has been changed to " + str);
                    AppCenter.this.mOneCollectorChannelListener.setLogUrl(str);
                }
            });
        }
    }

    private synchronized void setInstanceNetworkRequestsAllowed(boolean z) {
        if (!isConfigured()) {
            this.mAllowedNetworkRequests = Boolean.valueOf(z);
        } else if (isInstanceNetworkRequestsAllowed() == z) {
            StringBuilder sb = new StringBuilder();
            sb.append("Network requests are already ");
            sb.append(z ? "allowed" : "forbidden");
            AppCenterLog.info("AppCenter", sb.toString());
        } else {
            SharedPreferencesManager.putBoolean(PrefStorageConstants.ALLOWED_NETWORK_REQUEST, z);
            Channel channel = this.mChannel;
            if (channel != null) {
                channel.setNetworkRequests(z);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Set network requests ");
            sb2.append(z ? "allowed" : "forbidden");
            AppCenterLog.info("AppCenter", sb2.toString());
        }
    }

    private synchronized boolean isInstanceNetworkRequestsAllowed() {
        Boolean bool = this.mAllowedNetworkRequests;
        boolean booleanValue = bool == null ? true : bool.booleanValue();
        if (!isConfigured()) {
            return booleanValue;
        }
        return SharedPreferencesManager.getBoolean(PrefStorageConstants.ALLOWED_NETWORK_REQUEST, booleanValue);
    }

    private synchronized AppCenterFuture<Boolean> setInstanceMaxStorageSizeAsync(long j) {
        DefaultAppCenterFuture<Boolean> defaultAppCenterFuture = new DefaultAppCenterFuture<>();
        if (this.mConfiguredFromApp) {
            AppCenterLog.error("AppCenter", "setMaxStorageSize may not be called after App Center has been configured.");
            defaultAppCenterFuture.complete(false);
            return defaultAppCenterFuture;
        } else if (j < MINIMUM_STORAGE_SIZE) {
            AppCenterLog.error("AppCenter", "Maximum storage size must be at least 24576 bytes.");
            defaultAppCenterFuture.complete(false);
            return defaultAppCenterFuture;
        } else if (this.mSetMaxStorageSizeFuture != null) {
            AppCenterLog.error("AppCenter", "setMaxStorageSize may only be called once per app launch.");
            defaultAppCenterFuture.complete(false);
            return defaultAppCenterFuture;
        } else {
            this.mMaxStorageSizeInBytes = j;
            this.mSetMaxStorageSizeFuture = defaultAppCenterFuture;
            return defaultAppCenterFuture;
        }
    }

    private synchronized boolean isInstanceConfigured() {
        return this.mApplication != null;
    }

    private void configureInstanceWithRequiredAppSecret(Application application, String str) {
        if (str == null || str.isEmpty()) {
            AppCenterLog.error("AppCenter", "appSecret may not be null or empty.");
        } else {
            configureInstance(application, str, true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0045, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean configureInstance(android.app.Application r4, java.lang.String r5, final boolean r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            if (r4 != 0) goto L_0x000d
            java.lang.String r4 = "AppCenter"
            java.lang.String r5 = "Application context may not be null."
            com.microsoft.appcenter.utils.AppCenterLog.error(r4, r5)     // Catch:{ all -> 0x00aa }
            monitor-exit(r3)
            return r0
        L_0x000d:
            boolean r1 = r3.mLogLevelConfigured     // Catch:{ all -> 0x00aa }
            if (r1 != 0) goto L_0x001f
            android.content.pm.ApplicationInfo r1 = r4.getApplicationInfo()     // Catch:{ all -> 0x00aa }
            int r1 = r1.flags     // Catch:{ all -> 0x00aa }
            r2 = 2
            r1 = r1 & r2
            if (r1 != r2) goto L_0x001f
            r1 = 5
            com.microsoft.appcenter.utils.AppCenterLog.setLogLevel(r1)     // Catch:{ all -> 0x00aa }
        L_0x001f:
            java.lang.String r1 = r3.mAppSecret     // Catch:{ all -> 0x00aa }
            if (r6 == 0) goto L_0x002b
            boolean r5 = r3.configureSecretString(r5)     // Catch:{ all -> 0x00aa }
            if (r5 != 0) goto L_0x002b
            monitor-exit(r3)
            return r0
        L_0x002b:
            android.os.Handler r5 = r3.mHandler     // Catch:{ all -> 0x00aa }
            r0 = 1
            if (r5 == 0) goto L_0x0046
            java.lang.String r4 = r3.mAppSecret     // Catch:{ all -> 0x00aa }
            if (r4 == 0) goto L_0x0044
            boolean r4 = r4.equals(r1)     // Catch:{ all -> 0x00aa }
            if (r4 != 0) goto L_0x0044
            android.os.Handler r4 = r3.mHandler     // Catch:{ all -> 0x00aa }
            com.microsoft.appcenter.AppCenter$3 r5 = new com.microsoft.appcenter.AppCenter$3     // Catch:{ all -> 0x00aa }
            r5.<init>()     // Catch:{ all -> 0x00aa }
            r4.post(r5)     // Catch:{ all -> 0x00aa }
        L_0x0044:
            monitor-exit(r3)
            return r0
        L_0x0046:
            r3.mApplication = r4     // Catch:{ all -> 0x00aa }
            android.content.Context r4 = com.microsoft.appcenter.ApplicationContextUtils.getApplicationContext(r4)     // Catch:{ all -> 0x00aa }
            r3.mContext = r4     // Catch:{ all -> 0x00aa }
            boolean r4 = com.microsoft.appcenter.ApplicationContextUtils.isDeviceProtectedStorage(r4)     // Catch:{ all -> 0x00aa }
            if (r4 == 0) goto L_0x005b
            java.lang.String r4 = "AppCenter"
            java.lang.String r5 = "A user is locked, credential-protected private app data storage is not available.\nApp Center will use device-protected data storage that available without user authentication.\nPlease note that it's a separate storage, all settings and pending logs won't be shared with regular storage."
            com.microsoft.appcenter.utils.AppCenterLog.warn(r4, r5)     // Catch:{ all -> 0x00aa }
        L_0x005b:
            android.os.HandlerThread r4 = new android.os.HandlerThread     // Catch:{ all -> 0x00aa }
            java.lang.String r5 = "AppCenter.Looper"
            r4.<init>(r5)     // Catch:{ all -> 0x00aa }
            r3.mHandlerThread = r4     // Catch:{ all -> 0x00aa }
            r4.start()     // Catch:{ all -> 0x00aa }
            android.os.Handler r4 = new android.os.Handler     // Catch:{ all -> 0x00aa }
            android.os.HandlerThread r5 = r3.mHandlerThread     // Catch:{ all -> 0x00aa }
            android.os.Looper r5 = r5.getLooper()     // Catch:{ all -> 0x00aa }
            r4.<init>(r5)     // Catch:{ all -> 0x00aa }
            r3.mHandler = r4     // Catch:{ all -> 0x00aa }
            com.microsoft.appcenter.AppCenter$4 r4 = new com.microsoft.appcenter.AppCenter$4     // Catch:{ all -> 0x00aa }
            r4.<init>()     // Catch:{ all -> 0x00aa }
            r3.mAppCenterHandler = r4     // Catch:{ all -> 0x00aa }
            com.microsoft.appcenter.utils.ApplicationLifecycleListener r4 = new com.microsoft.appcenter.utils.ApplicationLifecycleListener     // Catch:{ all -> 0x00aa }
            android.os.Handler r5 = r3.mHandler     // Catch:{ all -> 0x00aa }
            r4.<init>(r5)     // Catch:{ all -> 0x00aa }
            r3.mApplicationLifecycleListener = r4     // Catch:{ all -> 0x00aa }
            android.app.Application r5 = r3.mApplication     // Catch:{ all -> 0x00aa }
            r5.registerActivityLifecycleCallbacks(r4)     // Catch:{ all -> 0x00aa }
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ all -> 0x00aa }
            r4.<init>()     // Catch:{ all -> 0x00aa }
            r3.mServices = r4     // Catch:{ all -> 0x00aa }
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ all -> 0x00aa }
            r4.<init>()     // Catch:{ all -> 0x00aa }
            r3.mServicesStartedFromLibrary = r4     // Catch:{ all -> 0x00aa }
            android.os.Handler r4 = r3.mHandler     // Catch:{ all -> 0x00aa }
            com.microsoft.appcenter.AppCenter$5 r5 = new com.microsoft.appcenter.AppCenter$5     // Catch:{ all -> 0x00aa }
            r5.<init>(r6)     // Catch:{ all -> 0x00aa }
            r4.post(r5)     // Catch:{ all -> 0x00aa }
            java.lang.String r4 = "AppCenter"
            java.lang.String r5 = "App Center SDK configured successfully."
            com.microsoft.appcenter.utils.AppCenterLog.info(r4, r5)     // Catch:{ all -> 0x00aa }
            monitor-exit(r3)
            return r0
        L_0x00aa:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.AppCenter.configureInstance(android.app.Application, java.lang.String, boolean):boolean");
    }

    private boolean configureSecretString(String str) {
        if (this.mConfiguredFromApp) {
            AppCenterLog.warn("AppCenter", "App Center may only be configured once.");
            return false;
        }
        this.mConfiguredFromApp = true;
        if (str != null) {
            for (String split : str.split(PAIR_DELIMITER)) {
                String[] split2 = split.split(KEY_VALUE_DELIMITER, -1);
                String str2 = split2[0];
                if (split2.length == 1) {
                    if (!str2.isEmpty()) {
                        this.mAppSecret = str2;
                    }
                } else if (!split2[1].isEmpty()) {
                    String str3 = split2[1];
                    if (APP_SECRET_KEY.equals(str2)) {
                        this.mAppSecret = str3;
                    } else if (TRANSMISSION_TARGET_TOKEN_KEY.equals(str2)) {
                        this.mTransmissionTargetToken = str3;
                    }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public synchronized void handlerAppCenterOperation(final Runnable runnable, final Runnable runnable2) {
        if (checkPrecondition()) {
            AnonymousClass6 r0 = new Runnable() {
                public void run() {
                    if (AppCenter.this.isInstanceEnabled()) {
                        runnable.run();
                        return;
                    }
                    Runnable runnable = runnable2;
                    if (runnable != null) {
                        runnable.run();
                    } else {
                        AppCenterLog.error("AppCenter", "App Center SDK is disabled.");
                    }
                }
            };
            if (Thread.currentThread() == this.mHandlerThread) {
                runnable.run();
            } else {
                this.mHandler.post(r0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void finishConfiguration(boolean z) {
        Constants.loadFromContext(this.mContext);
        FileManager.initialize(this.mContext);
        SharedPreferencesManager.initialize(this.mContext);
        Boolean bool = this.mAllowedNetworkRequests;
        if (bool != null) {
            SharedPreferencesManager.putBoolean(PrefStorageConstants.ALLOWED_NETWORK_REQUEST, bool.booleanValue());
        }
        SessionContext.getInstance();
        boolean isInstanceEnabled = isInstanceEnabled();
        HttpClient httpClient = DependencyConfiguration.getHttpClient();
        if (httpClient == null) {
            httpClient = HttpUtils.createHttpClient(this.mContext);
        }
        DefaultLogSerializer defaultLogSerializer = new DefaultLogSerializer();
        this.mLogSerializer = defaultLogSerializer;
        defaultLogSerializer.addLogFactory(StartServiceLog.TYPE, new StartServiceLogFactory());
        DefaultChannel defaultChannel = new DefaultChannel(this.mContext, this.mAppSecret, this.mLogSerializer, httpClient, this.mHandler);
        this.mChannel = defaultChannel;
        if (z) {
            applyStorageMaxSize();
        } else {
            defaultChannel.setMaxStorageSize(DEFAULT_MAX_STORAGE_SIZE_IN_BYTES);
        }
        this.mChannel.setEnabled(isInstanceEnabled);
        this.mChannel.addGroup(CORE_GROUP, 50, 3000, 3, (Ingestion) null, (Channel.GroupListener) null);
        this.mOneCollectorChannelListener = new OneCollectorChannelListener(this.mChannel, this.mLogSerializer, httpClient, IdHelper.getInstallId());
        if (this.mLogUrl != null) {
            if (this.mAppSecret != null) {
                AppCenterLog.info("AppCenter", "The log url of App Center endpoint has been changed to " + this.mLogUrl);
                this.mChannel.setLogUrl(this.mLogUrl);
            } else {
                AppCenterLog.info("AppCenter", "The log url of One Collector endpoint has been changed to " + this.mLogUrl);
                this.mOneCollectorChannelListener.setLogUrl(this.mLogUrl);
            }
        }
        this.mChannel.addListener(this.mOneCollectorChannelListener);
        if (!isInstanceEnabled) {
            NetworkStateHelper.getSharedInstance(this.mContext).close();
        }
        UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler(this.mHandler, this.mChannel);
        this.mUncaughtExceptionHandler = uncaughtExceptionHandler;
        if (isInstanceEnabled) {
            uncaughtExceptionHandler.register();
        }
        AppCenterLog.debug("AppCenter", "App Center initialized.");
    }

    /* access modifiers changed from: private */
    public void applyStorageMaxSize() {
        boolean maxStorageSize = this.mChannel.setMaxStorageSize(this.mMaxStorageSizeInBytes);
        DefaultAppCenterFuture<Boolean> defaultAppCenterFuture = this.mSetMaxStorageSizeFuture;
        if (defaultAppCenterFuture != null) {
            defaultAppCenterFuture.complete(Boolean.valueOf(maxStorageSize));
        }
    }

    @SafeVarargs
    private final synchronized void startServices(final boolean z, Class<? extends AppCenterService>... clsArr) {
        if (clsArr == null) {
            AppCenterLog.error("AppCenter", "Cannot start services, services array is null. Failed to start services.");
            return;
        }
        if (!isInstanceConfigured()) {
            StringBuilder sb = new StringBuilder();
            for (Class<? extends AppCenterService> name : clsArr) {
                sb.append("\t");
                sb.append(name.getName());
                sb.append("\n");
            }
            AppCenterLog.error("AppCenter", "Cannot start services, App Center has not been configured. Failed to start the following services:\n" + sb);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (Class<? extends AppCenterService> cls : clsArr) {
            if (cls == null) {
                AppCenterLog.warn("AppCenter", "Skipping null service, please check your varargs/array does not contain any null reference.");
            } else {
                try {
                    startOrUpdateService((AppCenterService) cls.getMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]), arrayList, arrayList2, z);
                } catch (Exception e) {
                    AppCenterLog.error("AppCenter", "Failed to get service instance '" + cls.getName() + "', skipping it.", e);
                }
            }
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                AppCenter.this.finishStartServices(arrayList2, arrayList, z);
            }
        });
    }

    private void startOrUpdateService(AppCenterService appCenterService, Collection<AppCenterService> collection, Collection<AppCenterService> collection2, boolean z) {
        if (z) {
            startOrUpdateServiceFromApp(appCenterService, collection, collection2);
        } else if (!this.mServices.contains(appCenterService)) {
            startServiceFromLibrary(appCenterService, collection);
        }
    }

    private void startOrUpdateServiceFromApp(AppCenterService appCenterService, Collection<AppCenterService> collection, Collection<AppCenterService> collection2) {
        String serviceName = appCenterService.getServiceName();
        if (this.mServices.contains(appCenterService)) {
            if (this.mServicesStartedFromLibrary.remove(appCenterService)) {
                collection2.add(appCenterService);
                return;
            }
            AppCenterLog.warn("AppCenter", "App Center has already started the service with class name: " + appCenterService.getServiceName());
        } else if (this.mAppSecret != null || !appCenterService.isAppSecretRequired()) {
            startService(appCenterService, collection);
        } else {
            AppCenterLog.error("AppCenter", "App Center was started without app secret, but the service requires it; not starting service " + serviceName + ".");
        }
    }

    private void startServiceFromLibrary(AppCenterService appCenterService, Collection<AppCenterService> collection) {
        String serviceName = appCenterService.getServiceName();
        if (appCenterService.isAppSecretRequired()) {
            AppCenterLog.error("AppCenter", "This service cannot be started from a library: " + serviceName + ".");
        } else if (startService(appCenterService, collection)) {
            this.mServicesStartedFromLibrary.add(appCenterService);
        }
    }

    private boolean startService(AppCenterService appCenterService, Collection<AppCenterService> collection) {
        String serviceName = appCenterService.getServiceName();
        if (ServiceInstrumentationUtils.isServiceDisabledByInstrumentation(serviceName)) {
            AppCenterLog.debug("AppCenter", "Instrumentation variable to disable service has been set; not starting service " + serviceName + ".");
            return false;
        }
        appCenterService.onStarting(this.mAppCenterHandler);
        this.mApplicationLifecycleListener.registerApplicationLifecycleCallbacks(appCenterService);
        this.mApplication.registerActivityLifecycleCallbacks(appCenterService);
        this.mServices.add(appCenterService);
        collection.add(appCenterService);
        return true;
    }

    /* access modifiers changed from: private */
    public void finishStartServices(Iterable<AppCenterService> iterable, Iterable<AppCenterService> iterable2, boolean z) {
        for (AppCenterService next : iterable) {
            next.onConfigurationUpdated(this.mAppSecret, this.mTransmissionTargetToken);
            AppCenterLog.info("AppCenter", next.getClass().getSimpleName() + " service configuration updated.");
        }
        boolean isInstanceEnabled = isInstanceEnabled();
        for (AppCenterService next2 : iterable2) {
            Map<String, LogFactory> logFactories = next2.getLogFactories();
            if (logFactories != null) {
                for (Map.Entry next3 : logFactories.entrySet()) {
                    this.mLogSerializer.addLogFactory((String) next3.getKey(), (LogFactory) next3.getValue());
                }
            }
            if (!isInstanceEnabled && next2.isInstanceEnabled()) {
                next2.setInstanceEnabled(false);
            }
            if (z) {
                next2.onStarted(this.mContext, this.mChannel, this.mAppSecret, this.mTransmissionTargetToken, true);
                AppCenterLog.info("AppCenter", next2.getClass().getSimpleName() + " service started from application.");
            } else {
                next2.onStarted(this.mContext, this.mChannel, (String) null, (String) null, false);
                AppCenterLog.info("AppCenter", next2.getClass().getSimpleName() + " service started from library.");
            }
        }
        if (z) {
            for (AppCenterService serviceName : iterable) {
                this.mStartedServicesNamesToLog.add(serviceName.getServiceName());
            }
            for (AppCenterService serviceName2 : iterable2) {
                this.mStartedServicesNamesToLog.add(serviceName2.getServiceName());
            }
            sendStartServiceLog();
        }
    }

    private void sendStartServiceLog() {
        if (!this.mStartedServicesNamesToLog.isEmpty() && isInstanceEnabled()) {
            ArrayList arrayList = new ArrayList(this.mStartedServicesNamesToLog);
            this.mStartedServicesNamesToLog.clear();
            StartServiceLog startServiceLog = new StartServiceLog();
            startServiceLog.setServices(arrayList);
            startServiceLog.oneCollectorEnabled(Boolean.valueOf(this.mTransmissionTargetToken != null));
            this.mChannel.enqueue(startServiceLog, CORE_GROUP, 1);
        }
    }

    private synchronized void configureAndStartServices(Application application, String str, Class<? extends AppCenterService>[] clsArr) {
        if (str != null) {
            if (!str.isEmpty()) {
                configureAndStartServices(application, str, true, clsArr);
            }
        }
        AppCenterLog.error("AppCenter", "appSecret may not be null or empty.");
    }

    private synchronized void startInstanceFromLibrary(Context context, Class<? extends AppCenterService>[] clsArr) {
        Application application;
        if (context != null) {
            try {
                application = (Application) context.getApplicationContext();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            application = null;
        }
        configureAndStartServices(application, (String) null, false, clsArr);
    }

    private void configureAndStartServices(Application application, String str, boolean z, Class<? extends AppCenterService>[] clsArr) {
        if (configureInstance(application, str, z)) {
            startServices(z, clsArr);
        }
    }

    private synchronized AppCenterFuture<Boolean> isInstanceEnabledAsync() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        if (checkPrecondition()) {
            this.mAppCenterHandler.post(new Runnable() {
                public void run() {
                    defaultAppCenterFuture.complete(true);
                }
            }, new Runnable() {
                public void run() {
                    defaultAppCenterFuture.complete(false);
                }
            });
        } else {
            defaultAppCenterFuture.complete(false);
        }
        return defaultAppCenterFuture;
    }

    /* access modifiers changed from: package-private */
    public boolean isInstanceEnabled() {
        return SharedPreferencesManager.getBoolean(PrefStorageConstants.KEY_ENABLED, true);
    }

    /* access modifiers changed from: private */
    public void setInstanceEnabled(boolean z) {
        this.mChannel.setEnabled(z);
        boolean isInstanceEnabled = isInstanceEnabled();
        boolean z2 = isInstanceEnabled && !z;
        boolean z3 = !isInstanceEnabled && z;
        if (z3) {
            this.mUncaughtExceptionHandler.register();
            NetworkStateHelper.getSharedInstance(this.mContext).reopen();
        } else if (z2) {
            this.mUncaughtExceptionHandler.unregister();
            NetworkStateHelper.getSharedInstance(this.mContext).close();
        }
        String str = PrefStorageConstants.KEY_ENABLED;
        if (z) {
            SharedPreferencesManager.putBoolean(str, true);
        }
        if (!this.mStartedServicesNamesToLog.isEmpty() && z3) {
            sendStartServiceLog();
        }
        for (AppCenterService next : this.mServices) {
            if (next.isInstanceEnabled() != z) {
                next.setInstanceEnabled(z);
            }
        }
        if (!z) {
            SharedPreferencesManager.putBoolean(str, false);
        }
        if (z2) {
            AppCenterLog.info("AppCenter", "App Center has been disabled.");
        } else if (z3) {
            AppCenterLog.info("AppCenter", "App Center has been enabled.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("App Center has already been ");
            if (!z) {
                str = "disabled";
            }
            sb.append(str);
            sb.append(".");
            AppCenterLog.info("AppCenter", sb.toString());
        }
    }

    private synchronized AppCenterFuture<Void> setInstanceEnabledAsync(final boolean z) {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        if (checkPrecondition()) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AppCenter.this.setInstanceEnabled(z);
                    defaultAppCenterFuture.complete(null);
                }
            });
        } else {
            defaultAppCenterFuture.complete(null);
        }
        return defaultAppCenterFuture;
    }

    private synchronized AppCenterFuture<UUID> getInstanceInstallIdAsync() {
        final DefaultAppCenterFuture defaultAppCenterFuture;
        defaultAppCenterFuture = new DefaultAppCenterFuture();
        if (checkPrecondition()) {
            this.mAppCenterHandler.post(new Runnable() {
                public void run() {
                    defaultAppCenterFuture.complete(IdHelper.getInstallId());
                }
            }, new Runnable() {
                public void run() {
                    defaultAppCenterFuture.complete(null);
                }
            });
        } else {
            defaultAppCenterFuture.complete(null);
        }
        return defaultAppCenterFuture;
    }

    public static void setUserId(String str) {
        getInstance().setInstanceUserId(str);
    }

    /* access modifiers changed from: package-private */
    public Set<AppCenterService> getServices() {
        return this.mServices;
    }

    /* access modifiers changed from: package-private */
    public Application getApplication() {
        return this.mApplication;
    }

    /* access modifiers changed from: package-private */
    public void resetApplication() {
        this.mApplication = null;
    }

    /* access modifiers changed from: package-private */
    public AppCenterHandler getAppCenterHandler() {
        return this.mAppCenterHandler;
    }

    /* access modifiers changed from: package-private */
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.mUncaughtExceptionHandler;
    }

    public void setChannel(Channel channel) {
        this.mChannel = channel;
    }
}
