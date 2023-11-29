package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class GingerbreadOpenCameraInterface implements OpenCameraInterface {
    private static final String TAG = "GingerbreadOpenCamera";

    public Camera open() {
        try {
            Class<?> cls = Class.forName("android.hardware.Camera");
            int intValue = ((Integer) invokeStaticMethod(getMethod(cls, "getNumberOfCameras"))).intValue();
            if (intValue == 0) {
                Log.w(TAG, "No cameras!");
                return null;
            }
            Class<?> cls2 = Class.forName("android.hardware.Camera$CameraInfo");
            Field field = cls2.getField("facing");
            int i = 0;
            while (true) {
                if (i >= intValue) {
                    break;
                }
                Object newInstance = cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
                cls.getMethod("getCameraInfo", new Class[]{Integer.TYPE, cls2}).invoke((Object) null, new Object[]{Integer.valueOf(i), newInstance});
                if (field.getInt(newInstance) == cls2.getDeclaredField("CAMERA_FACING_BACK").getInt(cls2)) {
                    break;
                }
                i++;
            }
            if (i < intValue) {
                Log.i(TAG, "Opening camera #" + i);
            } else {
                Log.i(TAG, "No camera facing back; returning camera #0");
                i = 0;
            }
            return (Camera) getMethod(cls, "open", Integer.TYPE).invoke((Object) null, new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Method getMethod(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getMethod(Class cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object invokeStaticMethod(Method method) {
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

    private static Object invokeMethod(Method method, Object obj, Object... objArr) {
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

    private static Object invokeMethodThrowsIllegalArgumentException(Method method, Object obj, Object... objArr) throws IllegalArgumentException {
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

    private static Object invokeMethodThrowsIOException(Method method, Object obj, Object... objArr) throws IOException {
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
