package com.bumptech.glide.load.engine.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

public class MemorySizeCalculator {
    static final int BITMAP_POOL_TARGET_SCREENS = 4;
    static final int BYTES_PER_ARGB_8888_PIXEL = 4;
    static final float LOW_MEMORY_MAX_SIZE_MULTIPLIER = 0.33f;
    static final float MAX_SIZE_MULTIPLIER = 0.4f;
    static final int MEMORY_CACHE_TARGET_SCREENS = 2;
    private static final String TAG = "MemorySizeCalculator";
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    public MemorySizeCalculator(Context context2) {
        this(context2, (ActivityManager) context2.getSystemService("activity"), new DisplayMetricsScreenDimensions(context2.getResources().getDisplayMetrics()));
    }

    MemorySizeCalculator(Context context2, ActivityManager activityManager, ScreenDimensions screenDimensions) {
        this.context = context2;
        int maxSize = getMaxSize(activityManager);
        int widthPixels = screenDimensions.getWidthPixels() * screenDimensions.getHeightPixels() * 4;
        int i = widthPixels * 4;
        int i2 = widthPixels * 2;
        int i3 = i2 + i;
        if (i3 <= maxSize) {
            this.memoryCacheSize = i2;
            this.bitmapPoolSize = i;
        } else {
            int round = Math.round(((float) maxSize) / 6.0f);
            this.memoryCacheSize = round * 2;
            this.bitmapPoolSize = round * 4;
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculated memory cache size: ");
            sb.append(toMb(this.memoryCacheSize));
            sb.append(" pool size: ");
            sb.append(toMb(this.bitmapPoolSize));
            sb.append(" memory class limited? ");
            sb.append(i3 > maxSize);
            sb.append(" max size: ");
            sb.append(toMb(maxSize));
            sb.append(" memoryClass: ");
            sb.append(activityManager.getMemoryClass());
            sb.append(" isLowMemoryDevice: ");
            sb.append(isLowMemoryDevice(activityManager));
            Log.d(TAG, sb.toString());
        }
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    private static int getMaxSize(ActivityManager activityManager) {
        return Math.round(((float) (activityManager.getMemoryClass() * 1024 * 1024)) * (isLowMemoryDevice(activityManager) ? LOW_MEMORY_MAX_SIZE_MULTIPLIER : MAX_SIZE_MULTIPLIER));
    }

    private String toMb(int i) {
        return Formatter.formatFileSize(this.context, (long) i);
    }

    private static boolean isLowMemoryDevice(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return Build.VERSION.SDK_INT < 11;
    }

    private static class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        public DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics2) {
            this.displayMetrics = displayMetrics2;
        }

        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }

        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }
    }
}
