package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ao extends z implements Handler.Callback {
    private a n;
    private TextView o;
    private Handler p;
    private int q;

    /* loaded from: classes.dex */
    public interface a {
        void a(y yVar);
    }

    public ao(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = 0;
        j();
        this.n = null;
    }

    public ao(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, i, jSONObject, str);
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = 0;
        j();
    }

    private void a(boolean z, String str) {
        this.o.setText(str);
        this.o.setEnabled(z);
    }

    private void j() {
        int i = this.a;
        int i2 = com.unionpay.mobile.android.global.a.b;
        this.b.a(new InputFilter.LengthFilter(6));
        this.b.a(2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        this.b.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(this.c);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-3419943);
        new LinearLayout.LayoutParams(1, -1);
        TextView textView = new TextView(getContext());
        this.o = textView;
        textView.setGravity(17);
        this.o.setText(com.unionpay.mobile.android.languages.c.bD.w);
        this.o.setTextColor(com.unionpay.mobile.android.utils.g.a(-10705958, -5846275, -5846275, -6710887));
        this.o.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.o.setOnClickListener(new ap(this));
        this.b.a(this.o, new LinearLayout.LayoutParams(-2, -1));
    }

    public final void a(int i) {
        this.p = new Handler(this);
        aq aqVar = new aq(this, i);
        a(false, String.format(com.unionpay.mobile.android.languages.c.bD.x, Integer.valueOf(i)));
        aqVar.start();
    }

    public final void a(a aVar) {
        this.n = aVar;
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return this.h || 6 == a().length();
    }

    @Override // com.unionpay.mobile.android.widgets.az
    protected final String d() {
        return "_input_msg";
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            this.q = message.arg1;
            if (com.unionpay.mobile.android.languages.c.bD != null) {
                a(false, String.format(com.unionpay.mobile.android.languages.c.bD.x, Integer.valueOf(message.arg1)));
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        if (com.unionpay.mobile.android.languages.c.bD != null) {
            a(true, com.unionpay.mobile.android.languages.c.bD.y);
        }
        this.p = null;
        return true;
    }
}
