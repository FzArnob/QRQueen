package com.microsoft.appcenter.utils;

import android.util.Log;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppCenterLog {
    public static final String LOG_TAG = "AppCenter";
    public static final int NONE = 8;
    private static Logger mCustomLogger = null;
    private static int sLogLevel = 7;

    public static void setLogger(Logger logger) {
        mCustomLogger = logger;
    }

    public static int getLogLevel() {
        return sLogLevel;
    }

    public static void setLogLevel(int i) {
        sLogLevel = i;
    }

    public static void verbose(String str, String str2) {
        if (sLogLevel <= 2) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.ALL, getMessageWithTag(str, str2));
            } else {
                Log.v(str, str2);
            }
        }
    }

    public static void verbose(String str, String str2, Throwable th) {
        if (sLogLevel <= 2) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.ALL, getMessageWithTag(str, str2), th);
            } else {
                Log.v(str, str2, th);
            }
        }
    }

    public static void debug(String str, String str2) {
        if (sLogLevel <= 3) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.FINE, getMessageWithTag(str, str2));
            } else {
                Log.d(str, str2);
            }
        }
    }

    public static void debug(String str, String str2, Throwable th) {
        if (sLogLevel <= 3) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.FINE, getMessageWithTag(str, str2), th);
            } else {
                Log.d(str, str2, th);
            }
        }
    }

    public static void info(String str, String str2) {
        if (sLogLevel <= 4) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.INFO, getMessageWithTag(str, str2));
            } else {
                Log.i(str, str2);
            }
        }
    }

    public static void info(String str, String str2, Throwable th) {
        if (sLogLevel <= 4) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.INFO, getMessageWithTag(str, str2), th);
            } else {
                Log.i(str, str2, th);
            }
        }
    }

    public static void warn(String str, String str2) {
        if (sLogLevel <= 5) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.WARNING, getMessageWithTag(str, str2));
            } else {
                Log.w(str, str2);
            }
        }
    }

    public static void warn(String str, String str2, Throwable th) {
        if (sLogLevel <= 5) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.WARNING, getMessageWithTag(str, str2), th);
            } else {
                Log.w(str, str2, th);
            }
        }
    }

    public static void error(String str, String str2) {
        if (sLogLevel <= 6) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.SEVERE, getMessageWithTag(str, str2));
            } else {
                Log.e(str, str2);
            }
        }
    }

    public static void error(String str, String str2, Throwable th) {
        if (sLogLevel <= 6) {
            Logger logger = mCustomLogger;
            if (logger != null) {
                logger.log(Level.SEVERE, getMessageWithTag(str, str2), th);
            } else {
                Log.e(str, str2, th);
            }
        }
    }

    public static void logAssert(String str, String str2) {
        if (sLogLevel <= 7) {
            Log.println(7, str, str2);
        }
    }

    public static void logAssert(String str, String str2, Throwable th) {
        if (sLogLevel <= 7) {
            Log.println(7, str, str2 + "\n" + Log.getStackTraceString(th));
        }
    }

    private static String getMessageWithTag(String str, String str2) {
        return String.format("%s: %s", new Object[]{str, str2});
    }
}
