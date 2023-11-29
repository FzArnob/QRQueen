package com.google.appinventor.components.runtime;

import android.util.Log;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FrameLayout implements Layout {
    private final android.widget.FrameLayout B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public ViewGroup getLayoutManager() {
        Log.i("FrameLayout", "some one just get my framelayout");
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public void add(AndroidViewComponent androidViewComponent) {
        Log.i("FrameLayout", "adding component..");
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.addView(androidViewComponent.getView(), new ViewGroup.LayoutParams(-1, -2));
    }

    public List<Object> getChildren() {
        ArrayList arrayList = new ArrayList();
        int childCount = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getChildCount();
        for (int i = 0; i < childCount; i++) {
            arrayList.add(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getChildAt(i));
        }
        return arrayList;
    }
}
