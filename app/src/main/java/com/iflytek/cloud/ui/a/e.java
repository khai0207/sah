package com.iflytek.cloud.ui.a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.iflytek.cloud.ui.a.c;

/* loaded from: classes.dex */
public class e extends LinearLayout {
    private c.a a;
    protected boolean b;
    protected boolean c;

    public e(Context context) {
        super(context);
        this.a = null;
        this.b = true;
        this.c = true;
    }

    protected static boolean a(ViewGroup viewGroup) {
        try {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                childAt.setOnClickListener(null);
                childAt.setBackgroundDrawable(null);
            }
            viewGroup.removeAllViews();
            viewGroup.setOnClickListener(null);
            viewGroup.setBackgroundDrawable(null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(c.a aVar) {
        this.a = aVar;
    }

    protected void b() {
    }

    protected void c() {
    }

    protected boolean d() {
        try {
            a(this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void e() {
        Intent intent = new Intent();
        intent.setAction(Build.VERSION.SDK_INT > 10 ? "android.settings.SETTINGS" : "android.settings.WIRELESS_SETTINGS");
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    public void f() {
        c.a aVar = this.a;
        if (aVar != null) {
            aVar.a();
        }
    }
}
