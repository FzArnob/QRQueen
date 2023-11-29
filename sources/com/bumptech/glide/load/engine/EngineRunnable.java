package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.executor.Prioritized;
import com.bumptech.glide.request.ResourceCallback;

class EngineRunnable implements Runnable, Prioritized {
    private static final String TAG = "EngineRunnable";
    private final DecodeJob<?, ?, ?> decodeJob;
    private volatile boolean isCancelled;
    private final EngineRunnableManager manager;
    private final Priority priority;
    private Stage stage = Stage.CACHE;

    interface EngineRunnableManager extends ResourceCallback {
        void submitForSource(EngineRunnable engineRunnable);
    }

    private enum Stage {
        CACHE,
        SOURCE
    }

    public EngineRunnable(EngineRunnableManager engineRunnableManager, DecodeJob<?, ?, ?> decodeJob2, Priority priority2) {
        this.manager = engineRunnableManager;
        this.decodeJob = decodeJob2;
        this.priority = priority2;
    }

    public void cancel() {
        this.isCancelled = true;
        this.decodeJob.cancel();
    }

    public void run() {
        Exception exc;
        if (!this.isCancelled) {
            Resource<?> resource = null;
            try {
                resource = decode();
                exc = null;
            } catch (OutOfMemoryError e) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Out Of Memory Error decoding", e);
                }
                exc = new ErrorWrappingGlideException(e);
            } catch (Exception e2) {
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Exception decoding", e2);
                }
                exc = e2;
            }
            if (this.isCancelled) {
                if (resource != null) {
                    resource.recycle();
                }
            } else if (resource == null) {
                onLoadFailed(exc);
            } else {
                onLoadComplete(resource);
            }
        }
    }

    private boolean isDecodingFromCache() {
        return this.stage == Stage.CACHE;
    }

    private void onLoadComplete(Resource resource) {
        this.manager.onResourceReady(resource);
    }

    private void onLoadFailed(Exception exc) {
        if (isDecodingFromCache()) {
            this.stage = Stage.SOURCE;
            this.manager.submitForSource(this);
            return;
        }
        this.manager.onException(exc);
    }

    private Resource<?> decode() throws Exception {
        if (isDecodingFromCache()) {
            return decodeFromCache();
        }
        return decodeFromSource();
    }

    private Resource<?> decodeFromCache() throws Exception {
        Resource<?> resource;
        try {
            resource = this.decodeJob.decodeResultFromCache();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception decoding result from cache: " + e);
            }
            resource = null;
        }
        return resource == null ? this.decodeJob.decodeSourceFromCache() : resource;
    }

    private Resource<?> decodeFromSource() throws Exception {
        return this.decodeJob.decodeFromSource();
    }

    public int getPriority() {
        return this.priority.ordinal();
    }
}
