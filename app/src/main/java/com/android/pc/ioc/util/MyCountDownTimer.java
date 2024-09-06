package com.android.pc.ioc.util;

import android.os.CountDownTimer;
import com.android.pc.ioc.event.EventBus;

/* loaded from: classes.dex */
public class MyCountDownTimer extends CountDownTimer {
    public MyCountDownTimer(long j, long j2) {
        super(j, j2);
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        System.out.println("倒计时停止");
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        EventBus.getDefault().post(new TimeEntity());
    }
}
