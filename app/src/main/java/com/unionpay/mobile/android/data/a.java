package com.unionpay.mobile.android.data;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.unionpay.mobile.android.utils.h;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a {
    public static final HashMap<String, Integer> a = new HashMap<>();

    public static SpannableString a(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        int i = -10066330;
        spannableString.setSpan(new ForegroundColorSpan(-10066330), 0, str.length(), 33);
        if (str2 == null) {
            return spannableString;
        }
        for (String str3 : str2.split(";")) {
            if (a(str3)) {
                break;
            }
            try {
                i = Color.parseColor("#" + str3);
            } catch (Exception unused) {
            }
        }
        spannableString.setSpan(new ForegroundColorSpan(i), 0, str.length(), 33);
        return spannableString;
    }

    public static final boolean a(String str) {
        if (!h.b(str)) {
            String[] strArr = {"black", "darkgray", "gray", "lightgray", "white", "red", "green", "blue", "yellow", "cyan", "magenta"};
            for (int i = 0; i < 11; i++) {
                if (strArr[i].equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
