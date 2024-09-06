package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

/* loaded from: classes.dex */
final class af implements TextWatcher {
    final /* synthetic */ ae a;
    private int c;
    private boolean b = true;
    private boolean d = false;

    af(ae aeVar) {
        this.a = aeVar;
    }

    private String a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = charSequence.charAt(i3);
            if (charAt != ' ') {
                i2++;
                if (i3 != 0 && (i2 & 3) == 1) {
                    stringBuffer.append(' ');
                }
            }
            if (i3 == i) {
                this.c = stringBuffer.length();
            }
            if (charAt != ' ') {
                stringBuffer.append(charAt);
            }
        }
        if (i == length) {
            this.c = stringBuffer.length();
        }
        return stringBuffer.toString();
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i2 == 1 && i3 == 0 && charSequence.charAt(i) == ' ') {
            this.d = true;
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.b) {
            if (this.d) {
                CharSequence subSequence = charSequence.subSequence(0, i - 1);
                if (i < charSequence.length()) {
                    charSequence = subSequence.toString() + ((Object) charSequence.subSequence(i, charSequence.length()));
                } else {
                    charSequence = subSequence;
                }
                i--;
                this.d = false;
            }
            this.b = false;
            this.a.b.c(a(charSequence, i + i3));
            t tVar = this.a.b;
            int i4 = this.c;
            if (i4 > 23) {
                i4 = 23;
            }
            tVar.b(i4);
            this.b = true;
        }
    }
}
