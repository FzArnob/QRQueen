package com.google.appinventor.common.version;

import org.jose4j.jws.AlgorithmIdentifiers;

public final class GitBuildId {
    public static final String ANT_BUILD_DATE = "November 12 2023";
    public static final String GIT_BUILD_FINGERPRINT = "312e42af44b7e6d214070c4207de1bb831cc66a5";
    public static final String GIT_BUILD_VERSION = "1.5C.1-Fenix";

    public static String getDate() {
        return ANT_BUILD_DATE;
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getVersion() {
        return GIT_BUILD_VERSION != "" ? GIT_BUILD_VERSION : AlgorithmIdentifiers.NONE;
    }

    private GitBuildId() {
    }
}
