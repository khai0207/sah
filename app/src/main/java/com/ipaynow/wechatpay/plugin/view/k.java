package com.ipaynow.wechatpay.plugin.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes.dex */
public class k {
    private static /* synthetic */ int[] cH;
    private TextView cA;
    private TextView cB;
    private String cD;
    private String cE;
    private int cF;
    private b ct;
    private LinearLayout cu;
    private FrameLayout cv;
    private l cw;
    private boolean cz;
    private Context mContext;
    private float cx = 0.0f;
    private int cy = Color.parseColor("#b1000000");
    private int cC = 1;
    private float ci = 10.0f;
    private boolean cG = true;

    public k(Context context) {
        this.mContext = context;
        this.cw = new l(this, context);
        a(m.SPIN_INDETERMINATE);
    }

    private static /* synthetic */ int[] ai() {
        int[] iArr = cH;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[m.valuesCustom().length];
        try {
            iArr2[m.ANNULAR_DETERMINATE.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[m.BAR_DETERMINATE.ordinal()] = 4;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[m.PIE_DETERMINATE.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[m.SPIN_INDETERMINATE.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        cH = iArr2;
        return iArr2;
    }

    public final k A(String str) {
        this.cD = str;
        TextView textView = this.cA;
        if (textView != null) {
            textView.setText(str);
        }
        return this;
    }

    public final k a(m mVar) {
        int i = ai()[mVar.ordinal()];
        this.cw.setView(i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : new c(this.mContext) : new a(this.mContext) : new n(this.mContext) : new p(this.mContext));
        return this;
    }

    public k ag() {
        l lVar;
        if (!isShowing() && (lVar = this.cw) != null) {
            lVar.show();
        }
        return this;
    }

    public final k ah() {
        this.cz = false;
        return this;
    }

    public void dismiss() {
        l lVar = this.cw;
        if (lVar == null || !lVar.isShowing()) {
            return;
        }
        this.cw.dismiss();
    }

    public boolean isShowing() {
        l lVar = this.cw;
        return lVar != null && lVar.isShowing();
    }

    public final void onDestroy() {
        this.ct = null;
        this.cu = null;
        this.cv = null;
        this.cw = null;
    }
}
