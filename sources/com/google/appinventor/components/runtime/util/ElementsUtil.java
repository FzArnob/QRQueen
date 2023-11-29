package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class ElementsUtil {
    public static YailList elementsFromString(String str) {
        return str.length() > 0 ? YailList.makeList((Object[]) str.split(" *, *")) : new YailList();
    }

    public static YailList elements(YailList yailList, String str) {
        String[] stringArray = yailList.toStringArray();
        int i = 0;
        while (i < stringArray.length) {
            if (stringArray[i] instanceof String) {
                i++;
            } else {
                throw new YailRuntimeError("Items passed to " + str + " must be Strings", "Error");
            }
        }
        return yailList;
    }

    public static int selectionIndex(int i, YailList yailList) {
        if (i <= 0 || i > yailList.size()) {
            return 0;
        }
        return i;
    }

    public static String setSelectionFromIndex(int i, YailList yailList) {
        return i == 0 ? "" : yailList.getString(i - 1);
    }

    public static int setSelectedIndexFromValue(String str, YailList yailList) {
        for (int i = 0; i < yailList.size(); i++) {
            if (yailList.getString(i).equals(str)) {
                return i + 1;
            }
        }
        return 0;
    }
}
