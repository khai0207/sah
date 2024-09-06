package com.netease.nimlib.log.b;

import android.util.Base64;
import com.netease.nimlib.c;
import com.netease.nimlib.log.b;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.misc.model.LogDesensitizationConfig;
import java.nio.charset.StandardCharsets;

/* compiled from: LogDesensitizationConfigHelper.java */
/* loaded from: classes.dex */
public class a {
    public static String a(String str, LogDesensitizationConfig logDesensitizationConfig) {
        return (logDesensitizationConfig == null || !logDesensitizationConfig.isHideDownloadUrl()) ? str : a(str);
    }

    public static String a(CharSequence charSequence) {
        return String.valueOf(charSequence);
    }

    public static String a(String str) {
        int i;
        if (w.a((CharSequence) str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (char c : new String(Base64.encode(str.getBytes(StandardCharsets.UTF_8), 0), StandardCharsets.UTF_8).toCharArray()) {
                int i2 = 65;
                if (c < 'A' || c > 'Z') {
                    i2 = 97;
                    if (c >= 'a' && c <= 'z') {
                        i = c + 'A';
                    }
                    sb.append(c);
                } else {
                    i = c + 'a';
                }
                c = (char) (i - i2);
                sb.append(c);
            }
        } catch (Throwable th) {
            b.e("LogDesensitizationConfigHelper", "encodeString Exception", th);
        }
        return sb.toString();
    }

    public static boolean a() {
        return c.O() && c.i().consoleLogEnabled;
    }
}
