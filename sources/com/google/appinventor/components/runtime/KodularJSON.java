package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import org.json.JSONArray;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "This is a non-visible component to edit or get data from JSON", iconName = "images/json.png", nonVisible = true, version = 1)
public class KodularJSON extends AndroidNonvisibleComponent {
    public KodularJSON(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleFunction(description = "Get text from a JSON object.")
    public String GetTextFromJSONObject(String str, String str2, String str3) {
        try {
            return new JSONObject(str).getString(str2);
        } catch (Exception e) {
            ErrorOccurred("GetTextFromJSONObject", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Get a number from a JSON Object.")
    public double GetNumberFromJSONObject(String str, String str2, double d) {
        try {
            return new JSONObject(str).getDouble(str2);
        } catch (Exception e) {
            ErrorOccurred("GetNumberFromJSONObject", e.getMessage());
            return d;
        }
    }

    @SimpleFunction(description = "Get a true or false value from a JSON Object.")
    public boolean GetBooleanFromJSONObject(String str, String str2, boolean z) {
        try {
            return new JSONObject(str).getBoolean(str2);
        } catch (Exception e) {
            ErrorOccurred("GetBooleanFromJSONObject", e.getMessage());
            return z;
        }
    }

    @SimpleFunction(description = "Get a JSON Object from a JSON Object.")
    public String GetJSONObjectFromJSONObject(String str, String str2, String str3) {
        try {
            return new JSONObject(str).getJSONObject(str2).toString();
        } catch (Exception e) {
            ErrorOccurred("GetJSONObjectFromJSONObject", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Get a JSON Array from a JSON Object.")
    public String GetJSONArrayFromJSONObject(String str, String str2, String str3) {
        try {
            return new JSONObject(str).getJSONArray(str2).toString();
        } catch (Exception e) {
            ErrorOccurred("GetJSONArrayFromJSONObject", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Add a JSON Object to a JSON Object.")
    public String AddJSONObjectToJSONObject(String str, String str2, String str3, String str4) {
        try {
            return new JSONObject(str).put(str3, new JSONObject(str2)).toString();
        } catch (Exception e) {
            ErrorOccurred("AddJSONObjectToJSONObject", e.getMessage());
            return str4;
        }
    }

    @SimpleFunction(description = "Add Text to a JSON Object.")
    public String AddTextToJSONObject(String str, String str2, String str3, String str4) {
        try {
            return new JSONObject(str).put(str3, str2).toString();
        } catch (Exception e) {
            ErrorOccurred("AddTextToJSONObject", e.getMessage());
            return str4;
        }
    }

    @SimpleFunction(description = "Add a Number to a JSON Object.")
    public String AddNumberToJSONObject(String str, double d, String str2, String str3) {
        try {
            return new JSONObject(str).put(str2, d).toString();
        } catch (Exception e) {
            ErrorOccurred("AddNumberToJSONObject", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Add True or False to a JSON Object.")
    public String AddBooleanToJSONObject(String str, boolean z, String str2, String str3) {
        try {
            return new JSONObject(str).put(str2, z).toString();
        } catch (Exception e) {
            ErrorOccurred("AddBooleanToJSONObject", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Get a JSON Object from a JSON Array.")
    public String GetJSONObjectFromJSONAray(String str, int i, String str2) {
        try {
            return new JSONArray(str).getJSONObject(i).toString();
        } catch (Exception e) {
            ErrorOccurred("GetJSONObjectFromJSONAray", e.getMessage());
            return str2;
        }
    }

    @SimpleFunction(description = "Get Text from a JSON Array.")
    public String GetTextFromJSONAray(String str, int i, String str2) {
        try {
            return new JSONArray(str).getString(i).toString();
        } catch (Exception e) {
            ErrorOccurred("GetTextFromJSONAray", e.getMessage());
            return str2;
        }
    }

    @SimpleFunction(description = "Get a Number from a JSON Array.")
    public double GetNumberFromJSONAray(String str, int i, double d) {
        try {
            return new JSONArray(str).getDouble(i);
        } catch (Exception e) {
            ErrorOccurred("GetNumberFromJSONAray", e.getMessage());
            return d;
        }
    }

    @SimpleFunction(description = "Get a True or False from a JSON Array.")
    public boolean GetBooleanFromJSONAray(String str, int i, boolean z) {
        try {
            return new JSONArray(str).getBoolean(i);
        } catch (Exception e) {
            ErrorOccurred("GetBooleanFromJSONAray", e.getMessage());
            return z;
        }
    }

    @SimpleFunction(description = "Get the length of a JSON Array.")
    public int GetLengthOfJSONArray(String str, int i) {
        try {
            return new JSONArray(str).length();
        } catch (Exception e) {
            ErrorOccurred("GetLengthOfJSONArray", e.getMessage());
            return i;
        }
    }

    @SimpleFunction(description = "Add a JSON Array to a JSON Array.")
    public String AddJSONArrayToJSONArray(String str, String str2, String str3) {
        try {
            return new JSONArray(str).put(new JSONArray(str2)).toString();
        } catch (Exception e) {
            ErrorOccurred("AddJSONArrayToJSONArray", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Add a JSON Object to a JSON Array.")
    public String AddJSONObjectToJSONArray(String str, String str2, String str3) {
        try {
            return new JSONArray(str).put(new JSONObject(str2)).toString();
        } catch (Exception e) {
            ErrorOccurred("AddJSONObjectToJSONArray", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Add Text to a JSON Array.")
    public String AddTextToJSONArray(String str, String str2, String str3) {
        try {
            return new JSONArray(str).put(str2).toString();
        } catch (Exception e) {
            ErrorOccurred("AddTextToJSONArray", e.getMessage());
            return str3;
        }
    }

    @SimpleFunction(description = "Add a Number to a JSON Array.")
    public String AddNumberToJSONArray(String str, double d, String str2) {
        try {
            return new JSONArray(str).put(d).toString();
        } catch (Exception e) {
            ErrorOccurred("AddNumberToJSONArray", e.getMessage());
            return str2;
        }
    }

    @SimpleFunction(description = "Add True or False to a JSON Array.")
    public String AddBooleanToJSONArray(String str, boolean z, String str2) {
        try {
            return new JSONArray(str).put(z).toString();
        } catch (Exception e) {
            ErrorOccurred("AddBooleanToJSONArray", e.getMessage());
            return str2;
        }
    }

    @SimpleEvent(description = "Triggers when there is a JSON error.")
    public void ErrorOccurred(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "ErrorOccurred", str, str2);
    }
}
