package com.google.zxing.client.android.common.executor;

import android.os.AsyncTask;

public final class DefaultAsyncTaskExecInterface implements AsyncTaskExecInterface {
    public <T> void execute(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        asyncTask.execute(tArr);
    }
}
