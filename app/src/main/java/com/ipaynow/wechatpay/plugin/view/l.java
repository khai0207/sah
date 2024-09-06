package com.ipaynow.wechatpay.plugin.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: classes.dex */
final class l extends Dialog {
    private g cI;
    private j cJ;
    private View cK;
    final /* synthetic */ k cL;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(k kVar, Context context) {
        super(context);
        this.cL = kVar;
    }

    @Override // android.app.Dialog
    protected final void onCreate(Bundle bundle) {
        b bVar;
        b bVar2;
        b bVar3;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        FrameLayout frameLayout;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        b bVar4;
        LinearLayout linearLayout4;
        LinearLayout linearLayout5;
        FrameLayout frameLayout2;
        LinearLayout linearLayout6;
        TextView textView7;
        LinearLayout linearLayout7;
        TextView textView8;
        b bVar5;
        float f;
        boolean z;
        b bVar6;
        int i;
        b bVar7;
        float f2;
        FrameLayout frameLayout3;
        String str;
        String str2;
        TextView textView9;
        String str3;
        TextView textView10;
        TextView textView11;
        String str4;
        TextView textView12;
        int i2;
        int i3;
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.cL.ct = new b(getContext());
        bVar = this.cL.ct;
        bVar.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        bVar2 = this.cL.ct;
        bVar2.setPadding(o.a(getContext(), 16.0f), o.a(getContext(), 16.0f), o.a(getContext(), 16.0f), o.a(getContext(), 16.0f));
        bVar3 = this.cL.ct;
        bVar3.setBackgroundColor(0);
        this.cL.cu = new LinearLayout(getContext());
        linearLayout = this.cL.cu;
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        linearLayout2 = this.cL.cu;
        linearLayout2.setOrientation(1);
        linearLayout3 = this.cL.cu;
        linearLayout3.setGravity(1);
        this.cL.cv = new FrameLayout(getContext());
        frameLayout = this.cL.cv;
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.cL.cA = new TextView(getContext());
        textView = this.cL.cA;
        textView.setVisibility(8);
        textView2 = this.cL.cA;
        textView2.setTextSize(2, 16.0f);
        textView3 = this.cL.cA;
        textView3.setTextColor(-1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = o.a(getContext(), 8.0f);
        layoutParams.bottomMargin = o.a(getContext(), 8.0f);
        this.cL.cB = new TextView(getContext());
        textView4 = this.cL.cB;
        textView4.setVisibility(8);
        textView5 = this.cL.cA;
        textView5.setTextSize(2, 13.0f);
        textView6 = this.cL.cA;
        textView6.setTextColor(-1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        bVar4 = this.cL.ct;
        linearLayout4 = this.cL.cu;
        bVar4.addView(linearLayout4);
        linearLayout5 = this.cL.cu;
        frameLayout2 = this.cL.cv;
        linearLayout5.addView(frameLayout2);
        linearLayout6 = this.cL.cu;
        textView7 = this.cL.cA;
        linearLayout6.addView(textView7, layoutParams);
        linearLayout7 = this.cL.cu;
        textView8 = this.cL.cB;
        linearLayout7.addView(textView8, layoutParams2);
        bVar5 = this.cL.ct;
        setContentView(bVar5);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.addFlags(2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        f = this.cL.cx;
        attributes.dimAmount = f;
        window.setAttributes(attributes);
        setCanceledOnTouchOutside(false);
        z = this.cL.cz;
        setCancelable(z);
        bVar6 = this.cL.ct;
        i = this.cL.cy;
        bVar6.b(i);
        bVar7 = this.cL.ct;
        f2 = this.cL.ci;
        bVar7.setCornerRadius(f2);
        ViewGroup.LayoutParams layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
        frameLayout3 = this.cL.cv;
        frameLayout3.addView(this.cK, layoutParams3);
        g gVar = this.cI;
        if (gVar != null) {
            i3 = this.cL.cF;
            gVar.setMax(i3);
        }
        j jVar = this.cJ;
        if (jVar != null) {
            i2 = this.cL.cC;
            jVar.a(i2);
        }
        str = this.cL.cD;
        if (str != null) {
            textView11 = this.cL.cA;
            str4 = this.cL.cD;
            textView11.setText(str4);
            textView12 = this.cL.cA;
            textView12.setVisibility(0);
        }
        str2 = this.cL.cE;
        if (str2 != null) {
            textView9 = this.cL.cB;
            str3 = this.cL.cE;
            textView9.setText(str3);
            textView10 = this.cL.cB;
            textView10.setVisibility(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setView(View view) {
        if (view != 0) {
            if (view instanceof g) {
                this.cI = (g) view;
            }
            if (view instanceof j) {
                this.cJ = (j) view;
            }
            this.cK = view;
        }
    }
}
