package com.puravidaapps;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.ClipboardManager;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "Clipboard Extension to copy text to or paste text from the clipboard. Version 1a as of 2016-08-11 for App Inventor version nb150 and Companion version 2.38.", iconName = "https://puravidaapps.com/images/taifun16.png", nonVisible = true, version = 1)
public class TaifunClipboard extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "TaifunClipboard";
    public static final int VERSION = 1;
    private ComponentContainer container;
    private Context context;
    private boolean suppressToast;

    public TaifunClipboard(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Log.d(LOG_TAG, "TaifunClipboard Created");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "whether Success Toast should be suppressed")
    public boolean SuppressToast() {
        return this.suppressToast;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void SuppressToast(boolean z) {
        this.suppressToast = z;
    }

    @SimpleFunction(description = "Copy text to clipboard. In case SuppressToast is true, the toast message 'Text copied' will be suppressed after copying a text to the clipboard.")
    public void Copy(String str) {
        try {
            if (Build.VERSION.SDK_INT < 11) {
                ((ClipboardManager) this.context.getSystemService("clipboard")).setText(str);
            } else {
                ((android.content.ClipboardManager) this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied text", str));
            }
            Log.d(LOG_TAG, "Text copied: " + str);
            if (!this.suppressToast) {
                Toast.makeText(this.context, "Text copied.", 0).show();
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }

    @SimpleFunction(description = "Paste text from clipboard. In case SuppressToast is true, the toast message 'Text pasted' will be suppressed after pasting a text from the clipboard.")
    public String Paste() {
        if (Build.VERSION.SDK_INT < 11) {
            return ((ClipboardManager) this.context.getSystemService("clipboard")).getText().toString();
        }
        ClipData primaryClip = ((android.content.ClipboardManager) this.context.getSystemService("clipboard")).getPrimaryClip();
        if (primaryClip == null) {
            return "";
        }
        String charSequence = CoerceToText(primaryClip.getItemAt(0)).toString();
        Log.d(LOG_TAG, "Text pasted: " + charSequence);
        if (!this.suppressToast) {
            Toast.makeText(this.context, "Text pasted.", 0).show();
        }
        return charSequence;
    }

    /* JADX WARNING: type inference failed for: r8v4, types: [android.os.Bundle, java.io.FileInputStream] */
    private CharSequence CoerceToText(ClipData.Item item) {
        CharSequence text = item.getText();
        if (text != null) {
            return text;
        }
        Uri uri = item.getUri();
        if (uri != null) {
            ? r8 = 0;
            try {
                FileInputStream createInputStream = this.context.getContentResolver().openTypedAssetFileDescriptor(uri, "text/*", r8).createInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(createInputStream, "UTF-8");
                StringBuilder sb = new StringBuilder(128);
                char[] cArr = new char[8192];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    sb.append(cArr, 0, read);
                }
                String sb2 = sb.toString();
                if (createInputStream != null) {
                    try {
                        createInputStream.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, e.getMessage(), e);
                    }
                }
                return sb2;
            } catch (FileNotFoundException e2) {
                Log.d(LOG_TAG, "Unable to open content URI as text, ignoring... " + e2.getMessage(), e2);
                if (r8 != 0) {
                    try {
                        r8.close();
                    } catch (IOException e3) {
                        Log.e(LOG_TAG, e3.getMessage(), e3);
                    }
                }
                Log.d(LOG_TAG, "Couldn't open the URI as a stream, then the URI itself probably serves fairly well as a textual representation");
                return uri.toString();
            } catch (IOException e4) {
                Log.w(LOG_TAG, "Failure loading text", e4);
                String iOException = e4.toString();
                if (r8 != 0) {
                    try {
                        r8.close();
                    } catch (IOException e5) {
                        Log.e(LOG_TAG, e5.getMessage(), e5);
                    }
                }
                return iOException;
            } catch (Throwable th) {
                if (r8 != 0) {
                    try {
                        r8.close();
                    } catch (IOException e6) {
                        Log.e(LOG_TAG, e6.getMessage(), e6);
                    }
                }
                throw th;
            }
        } else {
            Intent intent = item.getIntent();
            if (intent == null) {
                return "";
            }
            Log.d(LOG_TAG, "all we have is an Intent, then we can just turn that into text");
            return intent.toUri(1);
        }
    }
}
