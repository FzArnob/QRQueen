package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.MediaController;

public class CustomMediaController extends MediaController implements View.OnTouchListener {
    private int UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5 = 3000;
    private View wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public CustomMediaController(Context context) {
        super(context);
    }

    public void show(int i) {
        setVisibility(0);
        super.show(i);
    }

    public void show() {
        setVisibility(0);
        super.show();
    }

    public boolean addTo(ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams) {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            Log.e("CustomMediaController.addTo", "MediaController not available in fullscreen.");
            return false;
        }
        ((ViewGroup) parent).removeView(this);
        viewGroup.addView(this, layoutParams);
        return true;
    }

    public void setAnchorView(View view) {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = view;
        view.setOnTouchListener(this);
        super.setAnchorView(view);
    }

    public void hide() {
        super.hide();
        setVisibility(4);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view != this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
            return false;
        }
        show(this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5);
        return false;
    }
}
