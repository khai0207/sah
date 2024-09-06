package com.kqg.main.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/* loaded from: classes.dex */
public class MyCountDownTimerForButton extends CountDownTimer {
    TextView tv_get_vrification_code;

    public MyCountDownTimerForButton(long j, long j2, Button button) {
        super(j, j2);
        this.tv_get_vrification_code = button;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.tv_get_vrification_code.setClickable(false);
        this.tv_get_vrification_code.setText((j / 1000) + "秒");
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.tv_get_vrification_code.setClickable(true);
        this.tv_get_vrification_code.setText(UiUtils.getResString("find_password_get_code_des"));
    }
}
