package com.google.appinventor.components.runtime.util;

import android.view.View;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import java.util.List;

public class KodularDynamicUtil {
    private KodularDynamicUtil() {
    }

    public static Object getObjectById(int i, List<KodularDynamicModel> list) {
        for (KodularDynamicModel next : list) {
            if (next.getId() == i) {
                return next.getObject();
            }
        }
        return null;
    }

    public static Object getViewHolderById(int i, List<KodularDynamicModel> list) {
        for (KodularDynamicModel next : list) {
            if (next.getId() == i) {
                return next.getViewHolder();
            }
        }
        return null;
    }

    public static Object getChildViewHolderById(int i, List<KodularDynamicModel> list) {
        for (KodularDynamicModel next : list) {
            if (next.getId() == i) {
                return next.getChildViewHolder();
            }
        }
        return null;
    }

    public static void removeItemById(int i, List<KodularDynamicModel> list) {
        for (KodularDynamicModel next : list) {
            if (next.getId() == i) {
                list.remove(next);
            }
        }
    }

    public static class ComponentReturnHelper extends AndroidViewComponent {
        private View B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

        public ComponentReturnHelper(View view) {
            super((ComponentContainer) null);
            setView(view);
        }

        public void setView(View view) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = view;
        }

        public View getView() {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        }
    }
}
