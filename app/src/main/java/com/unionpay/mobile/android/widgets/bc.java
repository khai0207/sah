package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.kqg.main.constant.KV;
import com.unionpay.mobile.android.widgets.t;
import java.math.BigDecimal;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bc extends z implements t.a {
    private static final String n = bc.class.getSimpleName();
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;

    /* loaded from: classes.dex */
    public class a implements InputFilter {
        private t b;
        private BigDecimal c;
        private BigDecimal d;

        public a(t tVar, BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            this.b = null;
            this.c = null;
            this.d = null;
            this.b = tVar;
            this.c = bigDecimal;
            this.d = bigDecimal2;
            if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                c(this.c.toString());
            }
        }

        private static int a(String str) {
            if (str == null || !b(str)) {
                return 0;
            }
            return (str.length() - str.indexOf(".")) - 1;
        }

        private CharSequence a(String str, boolean z, boolean z2) {
            String bigDecimal = this.d.toString();
            String bigDecimal2 = this.c.toString();
            try {
                BigDecimal bigDecimal3 = new BigDecimal(str);
                bigDecimal3.setScale(2, 6);
                if (z && z2 && bigDecimal3.compareTo(this.c) == -1) {
                    c(bigDecimal2);
                }
                if (bigDecimal3.compareTo(this.d) != 1) {
                    return null;
                }
                c(bigDecimal);
                return null;
            } catch (Exception unused) {
                return "";
            }
        }

        private static boolean b(String str) {
            return str != null && str.contains(".");
        }

        private void c(String str) {
            this.b.c(str);
            Selection.setSelection(this.b.c(), this.b.b().length());
        }

        @Override // android.text.InputFilter
        public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (charSequence != null && charSequence.equals("")) {
                return a(spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + ((Object) spanned.subSequence(i4, spanned.length())), true, i4 - i3 != 1);
            }
            int i5 = i2 - i;
            boolean b = b(spanned.toString());
            if (1 != i5) {
                boolean b2 = b(charSequence.toString());
                if (b2 && b) {
                    return "";
                }
                if (b2 && (a(charSequence.toString()) > 2 || spanned.toString().length() != i4)) {
                    return "";
                }
            } else {
                if (!b && spanned.length() == 1 && spanned.charAt(0) == '0') {
                    return ".";
                }
                if ('0' == charSequence.charAt(0) && i3 == 0 && i4 == 0 && spanned.length() != 0) {
                    return "";
                }
                if ('.' == charSequence.charAt(0)) {
                    if (spanned.length() == 0) {
                        return "";
                    }
                    if (spanned.length() != 0 && spanned.length() - i3 > 2) {
                        return "";
                    }
                }
                if (b && i3 > spanned.toString().indexOf(".") && a(spanned.toString()) > 2 - i5) {
                    return "";
                }
            }
            String str = spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + ((Object) spanned.subSequence(i4, spanned.length()));
            return a(str, false, a(str) == 2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v13, types: [int] */
    /* JADX WARN: Type inference failed for: r5v15, types: [java.math.BigDecimal] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    public bc(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.r = com.unionpay.mobile.android.utils.i.a(jSONObject, "point");
        this.q = com.unionpay.mobile.android.utils.i.a(jSONObject, "max_use_point");
        this.p = com.unionpay.mobile.android.utils.i.a(jSONObject, "min_use_point");
        this.s = com.unionpay.mobile.android.utils.i.a(jSONObject, "ratio");
        this.o = com.unionpay.mobile.android.utils.i.a(jSONObject, "ordr_amnt");
        this.b.a(8194);
        this.b.a(DigitsKeyListener.getInstance("0123456789."));
        int i2 = 6;
        i2 = 6;
        i2 = 6;
        try {
            try {
                bigDecimal = new BigDecimal(this.p);
            } catch (Exception unused) {
                bigDecimal = BigDecimal.ZERO;
            }
            try {
                BigDecimal scale = bigDecimal.setScale(2, 6);
                try {
                    bigDecimal2 = new BigDecimal(this.q);
                } catch (Exception unused2) {
                    bigDecimal2 = new BigDecimal(3.4028234663852886E38d);
                }
                i2 = bigDecimal2.setScale(2, 6);
                this.b.a(new a(this.b, scale, i2));
                a((String) null, (String) null);
                if (t() == null || t().length() == 0) {
                    x();
                    c(String.format(com.unionpay.mobile.android.languages.c.bD.ay, this.r, this.s));
                }
                this.b.a(this);
            } finally {
            }
        } finally {
        }
    }

    private void a(String str, String str2) {
        SpannableString spannableString;
        if (s() == null || s().length() == 0) {
            w();
            if (str == null) {
                String format = String.format(com.unionpay.mobile.android.languages.c.bD.aw, this.q);
                spannableString = new SpannableString(format);
                spannableString.setSpan(new ForegroundColorSpan(-15958150), 0, format.length(), 0);
            } else {
                String format2 = String.format(com.unionpay.mobile.android.languages.c.bD.aw + str, this.q, str2);
                SpannableString spannableString2 = new SpannableString(format2);
                spannableString2.setSpan(new ForegroundColorSpan(-15958150), 0, format2.length() - (str2 + KV.PRICE_EXT).length(), 0);
                spannableString2.setSpan(new ForegroundColorSpan(-44462), format2.length() - (str2 + KV.PRICE_EXT).length(), format2.length(), 0);
                spannableString = spannableString2;
            }
            y();
            a(spannableString, TextView.BufferType.SPANNABLE);
        }
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final String a() {
        String a2 = super.a();
        if (a2 != null && a2.length() != 0) {
            try {
                BigDecimal bigDecimal = new BigDecimal(a2);
                bigDecimal.setScale(2, 6);
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                    return bigDecimal.toString();
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // com.unionpay.mobile.android.widgets.z
    public final void a(Editable editable) {
        super.a(editable);
        if (s() == null || s().length() == 0) {
            try {
                a(com.unionpay.mobile.android.languages.c.bD.ax, new BigDecimal(this.o).setScale(2, 6).subtract(new BigDecimal(a()).setScale(2, 6).multiply(new BigDecimal(this.s).setScale(2, 6)).setScale(2, 6)).toString());
            } catch (Exception unused) {
                a((String) null, (String) null);
            }
        }
    }

    @Override // com.unionpay.mobile.android.widgets.t.a
    public final void a(boolean z) {
        if (z) {
            a(this.k);
        }
    }

    @Override // com.unionpay.mobile.android.widgets.az.a
    public final boolean b() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.z, com.unionpay.mobile.android.widgets.az.a
    public final boolean c() {
        return true;
    }

    @Override // com.unionpay.mobile.android.widgets.y
    public final String h() {
        String a2 = a();
        if (a2 == null || a2.length() == 0) {
            return null;
        }
        return super.h();
    }
}
