package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.mGeneratedAdapter = generatedAdapter;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.mGeneratedAdapter.callMethods(lifecycleOwner, event, false, (MethodCallsLogger) null);
        this.mGeneratedAdapter.callMethods(lifecycleOwner, event, true, (MethodCallsLogger) null);
    }
}
