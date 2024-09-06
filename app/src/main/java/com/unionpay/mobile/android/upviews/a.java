package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.unionpay.mobile.android.upwidget.o;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.ac;
import com.unionpay.mobile.android.widgets.ae;
import com.unionpay.mobile.android.widgets.ag;
import com.unionpay.mobile.android.widgets.ai;
import com.unionpay.mobile.android.widgets.ao;
import com.unionpay.mobile.android.widgets.au;
import com.unionpay.mobile.android.widgets.e;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.t;
import com.unionpay.mobile.android.widgets.y;
import com.unionpay.mobile.android.widgets.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a extends LinearLayout implements o.a, ag.a, ai.a, ao.a, z.a {
    private Context a;
    private m b;
    private ArrayList<y> c;
    private long d;
    private b e;
    private boolean f;
    private boolean g;
    private JSONObject h;
    private String i;

    /* renamed from: com.unionpay.mobile.android.upviews.a$a */
    /* loaded from: classes.dex */
    public class C0071a {
        public int a = 0;
        public String b;

        C0071a(String str) {
            this.b = str;
        }

        public final void a(int i, String str) {
            this.b = str;
            this.a = i;
        }

        public final boolean a() {
            return this.a == 0;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(C0071a c0071a);

        void a(boolean z);

        void b(String str, String str2);

        void c(String str);

        void t();
    }

    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2) {
        this(context, jSONArray, j, bVar, str, z, str2, (byte) 0);
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, byte b2) {
        this(context, jSONArray, j, bVar, str, z, str2, (char) 0);
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, char c) {
        this(context, jSONArray, j, bVar, str, z, false, null, null, str2);
    }

    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, boolean z2, y yVar, JSONArray jSONArray2, String str2) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = 0L;
        this.e = null;
        this.f = false;
        this.g = true;
        this.h = null;
        this.i = "";
        this.a = context;
        this.d = j;
        this.e = bVar;
        this.f = z2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.a.f;
        layoutParams.bottomMargin = i;
        layoutParams.topMargin = i;
        setLayoutParams(layoutParams);
        setPadding(0, 0, 0, 0);
        setOrientation(1);
        setBackgroundColor(0);
        a(jSONArray, str, z, yVar, jSONArray2, str2);
    }

    public a(Context context, JSONArray jSONArray, b bVar, String str) {
        this(context, jSONArray, -1L, bVar, null, true, str);
    }

    private C0071a a(boolean z) {
        String format;
        String format2;
        C0071a c0071a = new C0071a("");
        ArrayList<y> arrayList = this.c;
        if (arrayList != null) {
            Iterator<y> it = arrayList.iterator();
            while (it.hasNext()) {
                y next = it.next();
                if (!(next instanceof ae)) {
                    if (!next.c()) {
                        format2 = String.format(com.unionpay.mobile.android.languages.c.bD.aC, next.u());
                    } else if (!next.b()) {
                        format2 = String.format(com.unionpay.mobile.android.languages.c.bD.aD, next.u());
                    }
                    c0071a.a(-1, format2);
                    break;
                }
                if (!next.c()) {
                    format = String.format(com.unionpay.mobile.android.languages.c.bD.aC, com.unionpay.mobile.android.languages.c.bD.aE);
                } else if (!next.b()) {
                    format = String.format(com.unionpay.mobile.android.languages.c.bD.aD, com.unionpay.mobile.android.languages.c.bD.aE);
                }
                c0071a.a(-1, format);
            }
        }
        if (!c0071a.a()) {
            return c0071a;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                y yVar = this.c.get(i);
                if (!(yVar instanceof ac) && ((!(yVar instanceof UPWidget) || z) && !TextUtils.isEmpty(this.c.get(i).h()) && this.c.get(i).f())) {
                    stringBuffer.append(",");
                    stringBuffer.append(this.c.get(i).h());
                }
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            stringBuffer2 = stringBuffer2.substring(1);
        }
        c0071a.a(0, stringBuffer2);
        return c0071a;
    }

    private static y a(List<y> list, String str) {
        for (y yVar : list) {
            if (yVar.q().equalsIgnoreCase(str)) {
                return yVar;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.unionpay.mobile.android.upviews.a.C0071a a() {
        /*
            r7 = this;
            com.unionpay.mobile.android.upviews.a$a r0 = new com.unionpay.mobile.android.upviews.a$a
            java.lang.String r1 = ""
            r0.<init>(r1)
            java.util.ArrayList<com.unionpay.mobile.android.widgets.y> r1 = r7.c
            java.lang.String r2 = "pin"
            com.unionpay.mobile.android.widgets.y r1 = a(r1, r2)
            if (r1 == 0) goto L52
            boolean r2 = r1.c()
            r3 = -1
            r4 = 0
            r5 = 1
            if (r2 != 0) goto L2e
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aC
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = r1.u()
            r5[r4] = r6
            java.lang.String r2 = java.lang.String.format(r2, r5)
        L2a:
            r0.a(r3, r2)
            goto L45
        L2e:
            boolean r2 = r1.b()
            if (r2 != 0) goto L45
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aD
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = r1.u()
            r5[r4] = r6
            java.lang.String r2 = java.lang.String.format(r2, r5)
            goto L2a
        L45:
            boolean r2 = r0.a()
            if (r2 != 0) goto L4c
            return r0
        L4c:
            java.lang.String r1 = r1.a()
            r0.b = r1
        L52:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a():com.unionpay.mobile.android.upviews.a$a");
    }

    public final String a(String str) {
        y a = a(this.c, str);
        String h = a != null ? a.h() : "";
        j.a("uppay", " name:" + str + ", value:" + h);
        return h;
    }

    public final void a(int i) {
        y a = a(this.c, "sms");
        if (a != null) {
            ((ao) a).a(i);
        }
    }

    public final void a(View.OnClickListener onClickListener) {
        y c = c("promotion");
        if (c == null || !(c instanceof ai)) {
            return;
        }
        ((ai) c).b(onClickListener);
    }

    public final void a(m mVar, JSONObject jSONObject) {
        this.b = mVar;
        this.h = jSONObject;
    }

    @Override // com.unionpay.mobile.android.widgets.z.a
    public final void a(t tVar, String str) {
        if (this.e != null) {
            boolean z = true;
            if (str != null && str.length() > 0) {
                ArrayList<y> arrayList = this.c;
                if (arrayList != null) {
                    Iterator<y> it = arrayList.iterator();
                    while (it.hasNext()) {
                        y next = it.next();
                        if (next instanceof z) {
                            z zVar = (z) next;
                            if (!zVar.a(tVar) && !zVar.c()) {
                                break;
                            }
                        }
                    }
                }
                z = false;
            }
            this.e.a(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008b  */
    @Override // com.unionpay.mobile.android.widgets.ao.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.unionpay.mobile.android.widgets.y r11) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a(com.unionpay.mobile.android.widgets.y):void");
    }

    @Override // com.unionpay.mobile.android.upwidget.o.a
    public final void a(String str, String str2) {
        if (this.e != null) {
            d();
            this.e.b(str, str2);
        }
    }

    @Override // com.unionpay.mobile.android.upwidget.o.a
    public final void a(String str, boolean z) {
        String str2 = "promotion".equalsIgnoreCase(str) ? "instalment" : "promotion";
        y c = c(str);
        y c2 = c(str2);
        if (c == null) {
            return;
        }
        if (!(c instanceof ai)) {
            if (c instanceof p) {
                if (z) {
                    this.e.t();
                }
                ((p) c).b(z);
                return;
            }
            return;
        }
        ((ai) c).a(z);
        if (c2 != null) {
            p pVar = (p) c2;
            if (pVar.g()) {
                Toast.makeText(this.a, this.i, 1).show();
                pVar.b(false);
            }
        }
    }

    public final void a(JSONArray jSONArray) {
        y c = c("promotion");
        if (c == null || !(c instanceof ai)) {
            return;
        }
        ((ai) c).a(jSONArray);
    }

    public final void a(JSONArray jSONArray, String str) {
        y c = c("promotion");
        if (c == null || !(c instanceof ai)) {
            return;
        }
        ((ai) c).a(jSONArray, str);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:11|12|14|15|(1:17)(2:77|(1:79)(8:80|(5:146|147|148|149|150)(9:82|(1:84)(2:86|(1:88)(7:89|(5:135|136|137|138|139)(8:91|(1:93)(2:95|(1:97)(2:98|(1:100)(2:101|(1:103)(2:104|(1:106)(2:107|(1:109)(2:110|(1:112)(2:113|(1:115)(2:116|(1:118)(2:119|(1:121)(2:122|(1:124)(7:125|(4:127|128|129|130)(1:134)|20|21|22|(9:24|(1:26)(2:65|(1:67)(2:68|(1:70)))|27|(1:31)|32|(12:34|(1:36)(2:61|(5:63|38|(2:55|(2:57|(1:60)))(3:42|(1:44)|45)|46|(3:54|51|52)))|37|38|(0)|55|(0)|46|(1:48)|54|51|52)(1:64)|50|51|52)(2:71|72)|53)))))))))))|94|20|21|22|(0)(0)|53)|133|75|76|(0)(0)|53))|85|19|20|21|22|(0)(0)|53)|140|20|21|22|(0)(0)|53))|18|19|20|21|22|(0)(0)|53|9) */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01b1, code lost:
    
        r4 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(org.json.JSONArray r24, java.lang.String r25, boolean r26, com.unionpay.mobile.android.widgets.y r27, org.json.JSONArray r28, java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 715
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a(org.json.JSONArray, java.lang.String, boolean, com.unionpay.mobile.android.widgets.y, org.json.JSONArray, java.lang.String):void");
    }

    public final void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String a = i.a(jSONObject, "instalment_empty_info");
        if (TextUtils.isEmpty(a)) {
            ((p) c("instalment")).a(true);
            ((p) c("instalment")).b(true);
            ((p) a(this.c, "instalment_policy")).a(i.d(jSONObject, "options"));
        } else {
            ((p) c("instalment")).a(false);
            ((p) c("instalment")).b(false);
            Toast.makeText(this.a, a, 1).show();
        }
    }

    public final C0071a b() {
        return a(true);
    }

    public final String b(String str) {
        y a = a(this.c, str);
        return a != null ? a.a() : "";
    }

    public final void b(View.OnClickListener onClickListener) {
        y c = c("promotion");
        if (c == null || !(c instanceof ai)) {
            return;
        }
        ((ai) c).c(onClickListener);
    }

    public final y c(String str) {
        ArrayList<y> arrayList = this.c;
        if (arrayList != null && arrayList.size() > 0 && !TextUtils.isEmpty(str)) {
            Iterator<y> it = this.c.iterator();
            while (it.hasNext()) {
                y next = it.next();
                if (next.r().equalsIgnoreCase(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public final HashMap<String, String> c() {
        if (!a(false).a()) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                y yVar = this.c.get(i);
                if (!(yVar instanceof ac) && !(yVar instanceof UPWidget) && !TextUtils.isEmpty(yVar.a())) {
                    hashMap.put(yVar.q(), yVar.a());
                }
            }
        }
        return hashMap;
    }

    public final void c(View.OnClickListener onClickListener) {
        y c = c("promotion");
        if (c == null || !(c instanceof ai)) {
            return;
        }
        ((ai) c).a(onClickListener);
    }

    public final void d(String str) {
        this.i = str;
    }

    public final boolean d() {
        boolean z;
        ArrayList<y> arrayList = this.c;
        if (arrayList != null) {
            Iterator<y> it = arrayList.iterator();
            while (it.hasNext()) {
                y next = it.next();
                if (next instanceof UPWidget) {
                    UPWidget uPWidget = (UPWidget) next;
                    if (uPWidget.j()) {
                        uPWidget.k();
                        z = true;
                        break;
                    }
                }
            }
        }
        z = false;
        ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        return z;
    }

    @Override // com.unionpay.mobile.android.widgets.ag.a
    public final void e(String str) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.c(str);
        }
    }

    public final boolean e() {
        boolean z;
        ArrayList<y> arrayList = this.c;
        if (arrayList != null) {
            Iterator<y> it = arrayList.iterator();
            while (it.hasNext()) {
                y next = it.next();
                if ((next instanceof z) && !((z) next).c()) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return !z;
    }

    public final void f() {
        ArrayList<y> arrayList = this.c;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<y> it = this.c.iterator();
        while (it.hasNext()) {
            y next = it.next();
            if ((next instanceof UPWidget) || (next instanceof e) || (next instanceof au)) {
                ((z) next).g();
            }
        }
    }

    @Override // com.unionpay.mobile.android.widgets.ai.a
    public final void g() {
        y c = c("instalment");
        if (c != null) {
            p pVar = (p) c;
            if (pVar.g()) {
                Toast.makeText(this.a, this.i, 1).show();
                pVar.b(false);
            }
        }
    }
}
