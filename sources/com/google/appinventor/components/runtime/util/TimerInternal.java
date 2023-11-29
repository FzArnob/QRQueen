package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import com.google.appinventor.components.runtime.AlarmHandler;

public final class TimerInternal implements Runnable {
    private boolean enabled;
    private Handler handler;
    private AlarmHandler hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int interval;

    public TimerInternal(AlarmHandler alarmHandler, boolean z, int i) {
        this(alarmHandler, z, i, new Handler());
    }

    public TimerInternal(AlarmHandler alarmHandler, boolean z, int i, Handler handler2) {
        this.handler = handler2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = alarmHandler;
        this.enabled = z;
        this.interval = i;
        if (z) {
            handler2.postDelayed(this, (long) i);
        }
    }

    public final int Interval() {
        return this.interval;
    }

    public final void Interval(int i) {
        this.interval = i;
        if (this.enabled) {
            this.handler.removeCallbacks(this);
            this.handler.postDelayed(this, (long) i);
        }
    }

    public final boolean Enabled() {
        return this.enabled;
    }

    public final void Enabled(boolean z) {
        if (this.enabled) {
            this.handler.removeCallbacks(this);
        }
        this.enabled = z;
        if (z) {
            this.handler.postDelayed(this, (long) this.interval);
        }
    }

    public final void run() {
        if (this.enabled) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.alarm();
            if (this.enabled) {
                this.handler.postDelayed(this, (long) this.interval);
            }
        }
    }
}
