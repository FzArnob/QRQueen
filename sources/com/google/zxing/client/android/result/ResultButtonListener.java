package com.google.zxing.client.android.result;

import android.view.View;

public final class ResultButtonListener implements View.OnClickListener {
    private final int index;
    private final ResultHandler resultHandler;

    public ResultButtonListener(ResultHandler resultHandler2, int i) {
        this.resultHandler = resultHandler2;
        this.index = i;
    }

    public void onClick(View view) {
        this.resultHandler.handleButtonPress(this.index);
    }
}
