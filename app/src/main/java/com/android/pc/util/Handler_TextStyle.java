package com.android.pc.util;

import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/* loaded from: classes.dex */
public class Handler_TextStyle {
    private SpannableString spannableString;

    public Handler_TextStyle() {
    }

    public Handler_TextStyle(String str) {
        this.spannableString = new SpannableString(str);
    }

    public void setString(String str) {
        this.spannableString = new SpannableString(str);
    }

    public SpannableString getSpannableString() {
        return this.spannableString;
    }

    public Handler_TextStyle setAbsoluteSize(int i, int i2, int i3, boolean z) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(i, z), i2, i3, 33);
        return this;
    }

    public Handler_TextStyle setRelativeSize(float f, int i, int i2) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new RelativeSizeSpan(f), i, i2, 33);
        return this;
    }

    public Handler_TextStyle setForegroundColor(int i, int i2, int i3) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new ForegroundColorSpan(i), i2, i3, 33);
        return this;
    }

    public Handler_TextStyle setBackgroundColor(int i, int i2, int i3) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new BackgroundColorSpan(i), i2, i3, 33);
        return this;
    }

    public static void setFakeBold(TextView textView, boolean z) {
        textView.getPaint().setFakeBoldText(z);
    }

    public Handler_TextStyle setUnderlineSpan(int i, int i2) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new UnderlineSpan(), i, i2, 33);
        return this;
    }

    public Handler_TextStyle setStrikethroughSpan(int i, int i2) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new StrikethroughSpan(), i, i2, 33);
        return this;
    }

    public Handler_TextStyle setSubscriptSpan(int i, int i2) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new SubscriptSpan(), i, i2, 33);
        return this;
    }

    public Handler_TextStyle setSuperscriptSpan(int i, int i2) {
        SpannableString spannableString = this.spannableString;
        if (spannableString == null) {
            return this;
        }
        spannableString.setSpan(new SuperscriptSpan(), i, i2, 33);
        return this;
    }
}
