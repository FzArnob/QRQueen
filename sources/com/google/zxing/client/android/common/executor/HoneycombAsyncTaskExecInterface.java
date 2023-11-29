package com.google.zxing.client.android.common.executor;

import android.os.AsyncTask;
import java.lang.reflect.Field;

public final class HoneycombAsyncTaskExecInterface implements AsyncTaskExecInterface {
    public <T> void execute(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        try {
            Field field = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
            asyncTask.getClass().getMethod("executeOnExecutor", new Class[0]).invoke(asyncTask, new Object[]{field, tArr});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
