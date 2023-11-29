package com.google.appinventor.components.runtime.util;

import android.util.Base64;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.FString;
import gnu.math.IntFraction;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonUtil {
    private static final String BINFILE_DIR = "/AppInventorBinaries";
    private static String LOG_TAG = "JsonUtil";

    private JsonUtil() {
    }

    public static List<String> getStringListFromJsonArray(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    @Deprecated
    public static List<Object> getListFromJsonArray(JSONArray jSONArray) throws JSONException {
        return getListFromJsonArray(jSONArray, false);
    }

    public static List<Object> getListFromJsonArray(JSONArray jSONArray, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(convertJsonItem(jSONArray.get(i), z));
        }
        return arrayList;
    }

    public static List<Object> getListFromJsonObject(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        ArrayList<String> arrayList2 = new ArrayList<>();
        while (keys.hasNext()) {
            arrayList2.add(keys.next());
        }
        Collections.sort(arrayList2);
        for (String str : arrayList2) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(str);
            arrayList3.add(convertJsonItem(jSONObject.get(str), false));
            arrayList.add(arrayList3);
        }
        return arrayList;
    }

    public static YailDictionary getDictionaryFromJsonObject(JSONObject jSONObject) throws JSONException {
        YailDictionary yailDictionary = new YailDictionary();
        TreeSet treeSet = new TreeSet();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            treeSet.add(keys.next());
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            yailDictionary.put(str, convertJsonItem(jSONObject.get(str), true));
        }
        return yailDictionary;
    }

    @Deprecated
    public static Object convertJsonItem(Object obj) throws JSONException {
        return convertJsonItem(obj, false);
    }

    public static Object convertJsonItem(Object obj, boolean z) throws JSONException {
        boolean z2;
        if (obj == null) {
            return "null";
        }
        if (obj instanceof JSONObject) {
            if (z) {
                return getDictionaryFromJsonObject((JSONObject) obj);
            }
            return getListFromJsonObject((JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            List<Object> listFromJsonArray = getListFromJsonArray((JSONArray) obj, z);
            return z ? YailList.makeList((List) listFromJsonArray) : listFromJsonArray;
        } else if (obj.equals(Boolean.FALSE) || (((z2 = obj instanceof String)) && ((String) obj).equalsIgnoreCase("false"))) {
            return Boolean.FALSE;
        } else {
            if (obj.equals(Boolean.TRUE) || (z2 && ((String) obj).equalsIgnoreCase("true"))) {
                return Boolean.TRUE;
            }
            if (obj instanceof Number) {
                return obj;
            }
            return obj.toString();
        }
    }

    public static String getJsonRepresentation(Object obj) throws JSONException {
        if (obj == null || obj.equals((Object) null)) {
            return "null";
        }
        if (obj instanceof FString) {
            return JSONObject.quote(obj.toString());
        }
        if (obj instanceof YailList) {
            return ((YailList) obj).toJSONString();
        }
        if (obj instanceof IntFraction) {
            return JSONObject.numberToString(Double.valueOf(((IntFraction) obj).doubleValue()));
        }
        if (obj instanceof Number) {
            return JSONObject.numberToString((Number) obj);
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof List) {
            obj = ((List) obj).toArray();
        }
        String str = "";
        if (obj instanceof YailDictionary) {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (Map.Entry entry : ((YailDictionary) obj).entrySet()) {
                sb.append(str);
                sb.append(JSONObject.quote(entry.getKey().toString()));
                sb.append(':');
                sb.append(getJsonRepresentation(entry.getValue()));
                str = ",";
            }
            sb.append('}');
            return sb.toString();
        } else if (!obj.getClass().isArray()) {
            return JSONObject.quote(obj.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[");
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object obj2 = objArr[i];
                sb2.append(str);
                sb2.append(getJsonRepresentation(obj2));
                i++;
                str = ",";
            }
            sb2.append("]");
            return sb2.toString();
        }
    }

    @Deprecated
    public static Object getObjectFromJson(String str) throws JSONException {
        return getObjectFromJson(str, false);
    }

    public static Object getObjectFromJson(String str, boolean z) throws JSONException {
        if (str == null || str.equals("")) {
            return "";
        }
        Object nextValue = new JSONTokener(str).nextValue();
        if (nextValue == null || nextValue.equals(JSONObject.NULL)) {
            return null;
        }
        if ((nextValue instanceof String) || (nextValue instanceof Number) || (nextValue instanceof Boolean)) {
            return nextValue;
        }
        if (nextValue instanceof JSONArray) {
            return getListFromJsonArray((JSONArray) nextValue, z);
        }
        if (!(nextValue instanceof JSONObject)) {
            throw new JSONException("Invalid JSON string.");
        } else if (z) {
            return getDictionaryFromJsonObject((JSONObject) nextValue);
        } else {
            return getListFromJsonObject((JSONObject) nextValue);
        }
    }

    @Deprecated
    public static String getJsonRepresentationIfValueFileName(Object obj) {
        Log.w(LOG_TAG, "Calling deprecated function getJsonRepresentationIfValueFileName", new IllegalAccessException());
        return getJsonRepresentationIfValueFileName(Form.getActiveForm(), obj);
    }

    public static String getJsonRepresentationIfValueFileName(Form form, Object obj) {
        List<String> list;
        try {
            if (obj instanceof String) {
                list = getStringListFromJsonArray(new JSONArray((String) obj));
            } else if (obj instanceof List) {
                list = (List) obj;
            } else {
                throw new YailRuntimeError("getJsonRepresentationIfValueFileName called on unknown type", obj.getClass().getName());
            }
            if (list.size() != 2 || !list.get(0).startsWith(".")) {
                return null;
            }
            String writeFile = writeFile(form, list.get(1), list.get(0).substring(1));
            System.out.println("Filename Written: ".concat(String.valueOf(writeFile)));
            return getJsonRepresentation(writeFile.replace("file:/", "file:///"));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSONException", e);
            return null;
        }
    }

    private static String writeFile(Form form, String str, String str2) {
        File file = new File(form.getDefaultPath(BINFILE_DIR));
        if (file.isDirectory() || file.mkdirs()) {
            Synchronizer synchronizer = new Synchronizer();
            try {
                File createTempFile = File.createTempFile("BinFile", ".".concat(String.valueOf(str2)), file);
                final String str3 = str;
                final Synchronizer synchronizer2 = synchronizer;
                new FileWriteOperation(form, form, "Write", createTempFile.getAbsolutePath().replace(form.getDefaultPath(""), ""), form.DefaultFileScope()) {
                    /* access modifiers changed from: protected */
                    public final boolean process(OutputStream outputStream) throws IOException {
                        outputStream.write(Base64.decode(str3, 0));
                        synchronizer2.wakeup(Boolean.TRUE);
                        return true;
                    }
                }.run();
                synchronizer.waitfor();
                if (synchronizer.getThrowable() != null) {
                    Throwable throwable = synchronizer.getThrowable();
                    Log.e(LOG_TAG, "Error writing content", throwable);
                    if (throwable instanceof RuntimeException) {
                        throw ((RuntimeException) throwable);
                    } else if (throwable instanceof IOException) {
                        throw ((IOException) throwable);
                    } else {
                        throw new YailRuntimeError(throwable.getMessage(), "Write");
                    }
                } else {
                    trimDirectory(20, file);
                    return createTempFile.getAbsolutePath();
                }
            } catch (IOException e) {
                throw new YailRuntimeError(e.getMessage(), "Write");
            }
        } else {
            throw new YailRuntimeError("Unable to create ".concat(String.valueOf(file)), "Write");
        }
    }

    private static void trimDirectory(int i, File file) {
        File[] listFiles = file.listFiles();
        Arrays.sort(listFiles, new Comparator<File>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
            }
        });
        int length = listFiles.length - i;
        for (int i2 = 0; i2 < length; i2++) {
            listFiles[i2].delete();
        }
    }

    public static String GetJsonAsString(String str, String str2) {
        return GetJsonAsString(str, str2, str2 + " not found");
    }

    public static String GetJsonAsString(String str, String str2, String str3) {
        try {
            return new JSONObject(str).getString(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return str3;
        }
    }

    public static boolean GetJsonAsBoolean(String str, String str2) {
        try {
            return new JSONObject(str).getBoolean(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return false;
        }
    }

    public static int GetJsonAsInt(String str, String str2) {
        try {
            return new JSONObject(str).getInt(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return 0;
        }
    }

    public static long GetJsonAsLong(String str, String str2) {
        try {
            return new JSONObject(str).getLong(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return 0;
        }
    }

    public static double GetJsonAsDouble(String str, String str2) {
        try {
            return new JSONObject(str).getDouble(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return 0.0d;
        }
    }

    public static String GetJsonFromArrayAsString(String str, String str2, int i) {
        String str3 = str2 + " not found";
        try {
            return new JSONArray(str).getJSONObject(i).getString(str2);
        } catch (JSONException e) {
            Log.e(LOG_TAG, String.valueOf(e));
            return str3;
        }
    }
}
