package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/* loaded from: classes.dex */
public final class r {
    private Context bc;
    private Toast cW;
    private String cX;
    private int cY;
    private int cZ;
    private int duration;
    private int gravity;
    private View view;

    public r(Context context) {
        this.bc = context;
    }

    public final r B(String str) {
        if (str == null) {
            com.ipaynow.wechatpay.plugin.e.b.c("text为null");
        }
        this.cX = str;
        return this;
    }

    public final r aj() {
        this.duration = 1;
        return this;
    }

    public final Toast ak() {
        if (this.bc == null) {
            com.ipaynow.wechatpay.plugin.e.b.c("Context为空");
        }
        if (this.view == null) {
            return Toast.makeText(this.bc, this.cX, this.duration);
        }
        Toast toast = new Toast(this.bc);
        this.cW = toast;
        toast.setDuration(this.duration);
        this.cW.setText(this.cX);
        this.cW.setView(this.view);
        this.cW.setGravity(this.gravity, this.cY, this.cZ);
        return this.cW;
    }
}
