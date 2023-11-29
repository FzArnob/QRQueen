package com.google.appinventor.components.runtime.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MultiDex {
    private static final String AZ85BVaQs0kLUu2CXyiWDXG9VqpTCCfp4NXnNFkUHgydy0WmV0oqvVulykJ4p0wv = ("code_cache" + File.separator + "secondary-dexes");
    private static final boolean HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(System.getProperty("java.vm.version"));
    private static final Set<String> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new HashSet();

    private MultiDex() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d0, code lost:
        android.util.Log.i("MultiDex", "install done");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d7, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean install(android.content.Context r7, boolean r8) {
        /*
            java.util.Set<java.lang.String> r0 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO
            r0.clear()
            java.lang.String r1 = "MultiDex"
            java.lang.String r2 = "install: doIt = "
            java.lang.String r3 = java.lang.String.valueOf(r8)
            java.lang.String r2 = r2.concat(r3)
            android.util.Log.i(r1, r2)
            boolean r1 = HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l
            r2 = 1
            if (r1 == 0) goto L_0x0021
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "VM has multidex support, MultiDex support library is disabled."
            android.util.Log.i(r7, r8)
            return r2
        L_0x0021:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 4
            if (r1 < r3) goto L_0x0112
            android.content.pm.ApplicationInfo r1 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.content.Context) r7)     // Catch:{ Exception -> 0x00ed }
            if (r1 != 0) goto L_0x0034
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "applicationInfo is null, returning"
            android.util.Log.d(r7, r8)     // Catch:{ Exception -> 0x00ed }
            return r2
        L_0x0034:
            monitor-enter(r0)     // Catch:{ Exception -> 0x00ed }
            java.lang.String r3 = r1.sourceDir     // Catch:{ all -> 0x00ea }
            boolean r4 = r0.contains(r3)     // Catch:{ all -> 0x00ea }
            if (r4 == 0) goto L_0x003f
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r2
        L_0x003f:
            r0.add(r3)     // Catch:{ all -> 0x00ea }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00ea }
            r4 = 20
            if (r3 <= r4) goto L_0x0070
            java.lang.String r3 = "MultiDex"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            java.lang.String r5 = "MultiDex is not guaranteed to work in SDK version "
            r4.<init>(r5)     // Catch:{ all -> 0x00ea }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00ea }
            r4.append(r5)     // Catch:{ all -> 0x00ea }
            java.lang.String r5 = ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\""
            r4.append(r5)     // Catch:{ all -> 0x00ea }
            java.lang.String r5 = "java.vm.version"
            java.lang.String r5 = java.lang.System.getProperty(r5)     // Catch:{ all -> 0x00ea }
            r4.append(r5)     // Catch:{ all -> 0x00ea }
            java.lang.String r5 = "\""
            r4.append(r5)     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x00ea }
        L_0x0070:
            java.lang.ClassLoader r3 = r7.getClassLoader()     // Catch:{ RuntimeException -> 0x00e0 }
            if (r3 != 0) goto L_0x007f
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "Context class loader is null. Must be running in test mode. Skip patching."
            android.util.Log.e(r7, r8)     // Catch:{ all -> 0x00ea }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r2
        L_0x007f:
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.content.Context) r7)     // Catch:{ all -> 0x0083 }
            goto L_0x008b
        L_0x0083:
            r4 = move-exception
            java.lang.String r5 = "MultiDex"
            java.lang.String r6 = "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning."
            android.util.Log.w(r5, r6, r4)     // Catch:{ all -> 0x00ea }
        L_0x008b:
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x00ea }
            java.lang.String r5 = r1.dataDir     // Catch:{ all -> 0x00ea }
            java.lang.String r6 = AZ85BVaQs0kLUu2CXyiWDXG9VqpTCCfp4NXnNFkUHgydy0WmV0oqvVulykJ4p0wv     // Catch:{ all -> 0x00ea }
            r4.<init>(r5, r6)     // Catch:{ all -> 0x00ea }
            r5 = 0
            if (r8 != 0) goto L_0x00a6
            boolean r8 = com.google.appinventor.components.runtime.multidex.a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.content.Context) r7, (android.content.pm.ApplicationInfo) r1)     // Catch:{ all -> 0x00ea }
            if (r8 == 0) goto L_0x00a6
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "Returning because of mustLoad"
            android.util.Log.d(r7, r8)     // Catch:{ all -> 0x00ea }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r5
        L_0x00a6:
            java.lang.String r8 = "MultiDex"
            java.lang.String r6 = "Proceeding with installation..."
            android.util.Log.d(r8, r6)     // Catch:{ all -> 0x00ea }
            java.util.List r8 = com.google.appinventor.components.runtime.multidex.a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.content.Context) r7, (android.content.pm.ApplicationInfo) r1, (java.io.File) r4, (boolean) r5)     // Catch:{ all -> 0x00ea }
            boolean r5 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.util.List<java.io.File>) r8)     // Catch:{ all -> 0x00ea }
            if (r5 == 0) goto L_0x00bb
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.ClassLoader) r3, (java.io.File) r4, (java.util.List<java.io.File>) r8)     // Catch:{ all -> 0x00ea }
            goto L_0x00cf
        L_0x00bb:
            java.lang.String r8 = "MultiDex"
            java.lang.String r5 = "Files were not valid zip files.  Forcing a reload."
            android.util.Log.w(r8, r5)     // Catch:{ all -> 0x00ea }
            java.util.List r7 = com.google.appinventor.components.runtime.multidex.a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.content.Context) r7, (android.content.pm.ApplicationInfo) r1, (java.io.File) r4, (boolean) r2)     // Catch:{ all -> 0x00ea }
            boolean r8 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.util.List<java.io.File>) r7)     // Catch:{ all -> 0x00ea }
            if (r8 == 0) goto L_0x00d8
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.ClassLoader) r3, (java.io.File) r4, (java.util.List<java.io.File>) r7)     // Catch:{ all -> 0x00ea }
        L_0x00cf:
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "install done"
            android.util.Log.i(r7, r8)
            return r2
        L_0x00d8:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x00ea }
            java.lang.String r8 = "Zip files were not valid."
            r7.<init>(r8)     // Catch:{ all -> 0x00ea }
            throw r7     // Catch:{ all -> 0x00ea }
        L_0x00e0:
            r7 = move-exception
            java.lang.String r8 = "MultiDex"
            java.lang.String r1 = "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching."
            android.util.Log.w(r8, r1, r7)     // Catch:{ all -> 0x00ea }
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            return r2
        L_0x00ea:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ea }
            throw r7     // Catch:{ Exception -> 0x00ed }
        L_0x00ed:
            r7 = move-exception
            java.lang.String r8 = "MultiDex"
            java.lang.String r0 = "Multidex installation failure"
            android.util.Log.e(r8, r0, r7)
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Multi dex installation failed ("
            r0.<init>(r1)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = ")."
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r8.<init>(r7)
            throw r8
        L_0x0112:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Multi dex installation failed. SDK "
            r8.<init>(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r8.append(r0)
            java.lang.String r0 = " is unsupported. Min SDK version is 4."
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.multidex.MultiDex.install(android.content.Context, boolean):boolean");
    }

    private static ApplicationInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            if (packageManager == null || packageName == null) {
                return null;
            }
            return packageManager.getApplicationInfo(packageName, 128);
        } catch (RuntimeException e) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    private static boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
        boolean z = false;
        if (str != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
            if (matcher.matches()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    if (parseInt > 2 || (parseInt == 2 && parseInt2 > 0)) {
                        z = true;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        StringBuilder sb = new StringBuilder("VM with version ");
        sb.append(str);
        sb.append(z ? " has multidex support" : " does not have multidex support");
        Log.i("MultiDex", sb.toString());
        return z;
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, File file, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        if (!list.isEmpty()) {
            a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader, list, file);
        }
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(List<File> list) {
        for (File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME : list) {
            if (!a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static Field hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str) throws NoSuchFieldException {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    /* access modifiers changed from: private */
    public static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static void m5hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) throws Exception {
        File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + file.getPath() + ").");
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
                return;
            }
            for (File file2 : listFiles) {
                Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                if (!file2.delete()) {
                    Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
                } else {
                    Log.i("MultiDex", "Deleted old file " + file2.getPath());
                }
            }
            if (!file.delete()) {
                Log.w("MultiDex", "Failed to delete secondary dex dir " + file.getPath());
                return;
            }
            Log.i("MultiDex", "Deleted old secondary dex dir " + file.getPath());
        }
    }

    static final class a {
        static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ClassLoader classLoader, List<File> list, File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            IOException[] iOExceptionArr;
            Object obj = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list);
            MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj, "dexElements", (Object[]) MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class}).invoke(obj, new Object[]{arrayList2, file, arrayList}));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Log.w("MultiDex", "Exception in makeDexElement", (IOException) it.next());
                }
                Field B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = MultiDex.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(classLoader, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(classLoader);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[(arrayList.size() + iOExceptionArr2.length)];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.set(classLoader, iOExceptionArr);
            }
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj, str);
        Object[] objArr2 = (Object[]) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.set(obj, objArr3);
    }
}
