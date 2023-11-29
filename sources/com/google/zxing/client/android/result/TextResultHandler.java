package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;

public final class TextResultHandler extends ResultHandler {
    public int getButtonCount() {
        return 0;
    }

    public int getButtonText(int i) {
        return 0;
    }

    public int getDisplayTitle() {
        return 0;
    }

    public void handleButtonPress(int i) {
    }

    public TextResultHandler(Activity activity, ParsedResult parsedResult, Result result) {
        super(activity, parsedResult, result);
    }
}
