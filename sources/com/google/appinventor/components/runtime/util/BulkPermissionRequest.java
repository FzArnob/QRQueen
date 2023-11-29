package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BulkPermissionRequest {
    private Component B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia;
    private String[] LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;

    public abstract void onGranted();

    protected BulkPermissionRequest(Component component, String str, String... strArr) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = component;
        this.EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia = str;
        this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = strArr;
    }

    public void onDenied(String[] strArr) {
        Form form = (Form) this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getDispatchDelegate();
        for (String dispatchPermissionDeniedEvent : strArr) {
            form.dispatchPermissionDeniedEvent(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia, dispatchPermissionDeniedEvent);
        }
    }

    public final List<String> getPermissions() {
        ArrayList arrayList = new ArrayList(this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn.length);
        Collections.addAll(arrayList, this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn);
        return arrayList;
    }
}
