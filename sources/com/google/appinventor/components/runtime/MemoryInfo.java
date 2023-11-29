package com.google.appinventor.components.runtime;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.DeviceStorage;
import com.google.appinventor.components.runtime.util.QUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", iconName = "images/devicetools.png", nonVisible = true, version = 1)
public class MemoryInfo extends AndroidNonvisibleComponent implements Component {
    private final ComponentContainer container;
    private final Context context;
    private final long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = 1048576;

    public MemoryInfo(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.container = componentContainer;
    }

    @SimpleFunction(description = "Total RAM size in Gigabytes.")
    public float MemoryTotal() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_TOTAL);
    }

    @SimpleFunction(description = "Total free RAM size in Gigabytes.")
    public float MemoryFree() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-memory in Gigabytes.")
    public float MemoryUsed() {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage.MEMORY_USED);
    }

    @SimpleFunction(description = "Total external storage size in Gigabytes.")
    public float ExternalStorageTotal() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Available size of external storage in Gigabytes.")
    public float ExternalStorageAvailable() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-external-storage in Gigabytes.")
    public float ExternalStorageUsed() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage.EXTERNAL_STORAGE_USED);
    }

    @SimpleFunction(description = "Total size of internal storage in Gigabytes.")
    public float InternalStorageTotal() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_TOTAL);
    }

    @SimpleFunction(description = "Size of available internal storage in Gigabytes.")
    public float InternalStorageAvailable() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_AVAILABLE);
    }

    @SimpleFunction(description = "Size of used-internal-storage in Gigabytes.")
    public float InternalStorageUsed() {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage.INTERNAL_STORAGE_USED);
    }

    private float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(DeviceStorage deviceStorage) {
        float f;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0.0f;
        }
        StatFs statFs = new StatFs(QUtil.getExternalStorageDir(this.context).getPath());
        long blockSize = (long) statFs.getBlockSize();
        long blockCount = (((long) statFs.getBlockCount()) * blockSize) / 1048576;
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * blockSize) / 1048576;
        long j = blockCount - availableBlocks;
        int i = AnonymousClass1.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 1) {
            f = (float) blockCount;
        } else if (i == 2) {
            f = (float) availableBlocks;
        } else if (i != 3) {
            return 0.0f;
        } else {
            f = (float) j;
        }
        return f / 1000.0f;
    }

    /* renamed from: com.google.appinventor.components.runtime.MemoryInfo$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.appinventor.components.runtime.util.DeviceStorage[] r0 = com.google.appinventor.components.runtime.util.DeviceStorage.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r0
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_TOTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.EXTERNAL_STORAGE_USED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_TOTAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.INTERNAL_STORAGE_USED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_TOTAL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.appinventor.components.runtime.util.DeviceStorage r1 = com.google.appinventor.components.runtime.util.DeviceStorage.MEMORY_USED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MemoryInfo.AnonymousClass1.<clinit>():void");
        }
    }

    private static float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(DeviceStorage deviceStorage) {
        float f;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = (long) statFs.getBlockSize();
        long availableBlocks = (((long) statFs.getAvailableBlocks()) * blockSize) / 1048576;
        long blockCount = (((long) statFs.getBlockCount()) * blockSize) / 1048576;
        long j = blockCount - availableBlocks;
        int i = AnonymousClass1.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 4) {
            f = (float) blockCount;
        } else if (i == 5) {
            f = (float) availableBlocks;
        } else if (i != 6) {
            return 0.0f;
        } else {
            f = (float) j;
        }
        return f / 1000.0f;
    }

    private float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(DeviceStorage deviceStorage) {
        float f;
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem / 1048576;
        long j2 = memoryInfo.totalMem / 1048576;
        long j3 = j2 - j;
        int i = AnonymousClass1.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T[deviceStorage.ordinal()];
        if (i == 7) {
            f = (float) j2;
        } else if (i == 8) {
            f = (float) j;
        } else if (i != 9) {
            return 0.0f;
        } else {
            f = (float) j3;
        }
        return f / 1000.0f;
    }
}
