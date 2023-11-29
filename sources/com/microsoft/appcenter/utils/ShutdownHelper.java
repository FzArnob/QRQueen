package com.microsoft.appcenter.utils;

import android.os.Process;

public class ShutdownHelper {
    ShutdownHelper() {
    }

    public static void shutdown(int i) {
        Process.killProcess(Process.myPid());
        System.exit(i);
    }
}
