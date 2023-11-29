package com.google.appinventor.common.version;

public final class AppInventorFeatures {

    public enum TieredFeature {
        MONETIZATION_EXTENSIONS,
        NON_GAM_MONETIZATION_COMPONENTS,
        UNLIMITED_ASSETS,
        UNLIMITED_EXTENSIONS,
        UNLIMITED_PROJECTS,
        WEB_BUILDS
    }

    public static boolean allowMultiScreenApplications() {
        return true;
    }

    public static boolean hasDebuggingView() {
        return true;
    }

    public static boolean hasYailGenerationOption() {
        return true;
    }

    public static boolean requireOneLogin() {
        return false;
    }

    public static boolean restrictTieredFeatures() {
        return true;
    }

    public static boolean sendBugReports() {
        return true;
    }

    public static boolean showInternalComponentsCategory() {
        return false;
    }

    public static boolean showSplashScreen() {
        return true;
    }

    public static boolean showSurveySplashScreen() {
        return false;
    }

    public static boolean takeScreenShots() {
        return false;
    }

    public static boolean trackClientEvents() {
        return true;
    }

    private AppInventorFeatures() {
    }
}
