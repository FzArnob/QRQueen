package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class MakeroidNonvisibleExtension implements Component {
    protected final Form form;

    protected MakeroidNonvisibleExtension(Form form2) {
        this.form = form2;
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this.form;
    }
}
