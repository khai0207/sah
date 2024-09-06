package com.unionpay.mobile.android.widgets;

import android.content.Context;
import com.unionpay.mobile.android.widgets.az;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class y extends az implements az.a {
    public y(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    public final /* bridge */ /* synthetic */ void a(Context context, String str) {
        super.a(context, str);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    public final /* bridge */ /* synthetic */ void a(Context context, String str, String[] strArr, Object[] objArr) {
        super.a(context, str, strArr, objArr);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    public final /* bridge */ /* synthetic */ boolean a(String str) {
        return super.a(str);
    }

    @Override // com.unionpay.mobile.android.widgets.az
    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    public String h() {
        StringBuffer stringBuffer = new StringBuffer();
        if (q() != null && a() != null) {
            stringBuffer.append("\"");
            stringBuffer.append(q());
            stringBuffer.append("\":");
            stringBuffer.append("\"");
            stringBuffer.append(a());
            stringBuffer.append("\"");
        }
        return stringBuffer.toString();
    }

    @Override // com.unionpay.mobile.android.widgets.az
    public final /* bridge */ /* synthetic */ String i() {
        return super.i();
    }
}
