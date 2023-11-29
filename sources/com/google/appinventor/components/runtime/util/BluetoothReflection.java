package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothReflection {
    private BluetoothReflection() {
    }

    public static Object getBluetoothAdapter() {
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class.forName("android.bluetooth.BluetoothAdapter"), "getDefaultAdapter"));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static boolean isBluetoothEnabled(Object obj) {
        return ((Boolean) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "isEnabled"), obj, new Object[0])).booleanValue();
    }

    public static Set getBondedDevices(Object obj) {
        return (Set) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getBondedDevices"), obj, new Object[0]);
    }

    public static boolean checkBluetoothAddress(Object obj, String str) {
        return ((Boolean) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "checkBluetoothAddress", (Class<?>[]) new Class[]{String.class}), obj, str)).booleanValue();
    }

    public static Object getRemoteDevice(Object obj, String str) throws IllegalArgumentException {
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "getRemoteDevice", (Class<?>[]) new Class[]{String.class}), obj, str);
    }

    public static Object listenUsingRfcommWithServiceRecord(Object obj, String str, UUID uuid) throws IOException {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "listenUsingRfcommWithServiceRecord", (Class<?>[]) new Class[]{String.class, UUID.class}), obj, str, uuid);
    }

    public static Object listenUsingInsecureRfcommWithServiceRecord(Object obj, String str, UUID uuid) throws IOException {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "listenUsingInsecureRfcommWithServiceRecord", (Class<?>[]) new Class[]{String.class, UUID.class}), obj, str, uuid);
    }

    public static String getBluetoothDeviceName(Object obj) {
        return (String) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getName"), obj, new Object[0]);
    }

    public static String getBluetoothDeviceAddress(Object obj) {
        return (String) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getAddress"), obj, new Object[0]);
    }

    public static boolean isBonded(Object obj) {
        return ((Integer) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getBondState"), obj, new Object[0])).intValue() == 12;
    }

    public static Object getBluetoothClass(Object obj) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getBluetoothClass"), obj, new Object[0]);
    }

    public static Object createRfcommSocketToServiceRecord(Object obj, UUID uuid) throws IOException {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "createRfcommSocketToServiceRecord", (Class<?>[]) new Class[]{UUID.class}), obj, uuid);
    }

    public static Object createInsecureRfcommSocketToServiceRecord(Object obj, UUID uuid) throws IOException {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Class) obj.getClass(), "createInsecureRfcommSocketToServiceRecord", (Class<?>[]) new Class[]{UUID.class}), obj, uuid);
    }

    public static int getDeviceClass(Object obj) {
        return ((Integer) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getDeviceClass"), obj, new Object[0])).intValue();
    }

    public static void connectToBluetoothSocket(Object obj) throws IOException {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "connect"), obj, new Object[0]);
    }

    public static InputStream getInputStream(Object obj) throws IOException {
        return (InputStream) wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getInputStream"), obj, new Object[0]);
    }

    public static OutputStream getOutputStream(Object obj) throws IOException {
        return (OutputStream) wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "getOutputStream"), obj, new Object[0]);
    }

    public static void closeBluetoothSocket(Object obj) throws IOException {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "close"), obj, new Object[0]);
    }

    public static Object accept(Object obj) throws IOException {
        return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "accept"), obj, new Object[0]);
    }

    public static void closeBluetoothServerSocket(Object obj) throws IOException {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(obj.getClass(), "close"), obj, new Object[0]);
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Method method) {
        try {
            return method.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            cause.printStackTrace();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    private static Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            cause.printStackTrace();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    private static Object B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Method method, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            cause.printStackTrace();
            if (cause instanceof IllegalArgumentException) {
                throw ((IllegalArgumentException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new RuntimeException(e2);
            }
        }
    }

    private static Object wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Method method, Object obj, Object... objArr) throws IOException {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            cause.printStackTrace();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new RuntimeException(e2);
            }
        }
    }
}
