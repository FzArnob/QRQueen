package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

public final class ResultHandlerFactory {
    private ResultHandlerFactory() {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result result) {
        return new TextResultHandler(activity, parseResult(result), result);
    }

    private static ParsedResult parseResult(Result result) {
        return ResultParser.parseResult(result);
    }
}
