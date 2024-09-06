package com.netease.nimlib.b;

import android.text.TextUtils;
import com.alipay.sdk.m.q.h;
import com.netease.nimlib.b.d;
import com.netease.nimlib.sdk.msg.model.LocalAntiSpamResult;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: AntiSpamMatcher.java */
/* loaded from: classes.dex */
public class b {
    private List<Pattern> a = new ArrayList();
    private int b;

    public b(int i, List<d.a> list) {
        this.b = i;
        StringBuilder sb = new StringBuilder(1152);
        try {
            for (d.a aVar : list) {
                if (aVar.c() == i) {
                    String a = aVar.a();
                    if (aVar.b() == 2) {
                        a(a, this.a);
                    } else if (aVar.b() == 1) {
                        if (sb.length() >= 1024) {
                            a(sb, this.a);
                            sb = new StringBuilder(1152);
                        }
                        if (!TextUtils.isEmpty(a)) {
                            sb.append("(");
                            sb.append(a(a));
                            sb.append(")|");
                        }
                    }
                }
            }
            a(sb, this.a);
        } catch (Exception e) {
            com.netease.nimlib.log.b.N("load Thesaurus exception " + e);
        }
    }

    public static String a(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*").replace("+", "\\+").replace("|", "\\|").replace("{", "\\{").replace(h.d, "\\}").replace("(", "\\(").replace(")", "\\)").replace("^", "\\^").replace("$", "\\$").replace("[", "\\[").replace("]", "\\]").replace("?", "\\?").replace(",", "\\,").replace(".", "\\.").replace(com.alipay.sdk.m.o.a.l, "\\&");
    }

    private void a(StringBuilder sb, List<Pattern> list) {
        int length = sb.length();
        if (length == 0) {
            return;
        }
        int i = length - 1;
        if (sb.charAt(i) == '|') {
            sb.deleteCharAt(i);
        }
        try {
            list.add(Pattern.compile(sb.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.delete(0, sb.length());
    }

    private void a(String str, List<Pattern> list) {
        if (str.length() == 0) {
            return;
        }
        try {
            list.add(Pattern.compile(str));
        } catch (Exception e) {
            com.netease.nimlib.log.b.N("thesaurus pattern compile exception " + e);
        }
    }

    public LocalAntiSpamResult a(String str, String str2) {
        if (str == null) {
            return null;
        }
        boolean z = false;
        for (Pattern pattern : this.a) {
            boolean find = pattern.matcher(str).find();
            if (find) {
                if (str2 == null) {
                    return new LocalAntiSpamResult(this.b, str);
                }
                str = str.replaceAll(pattern.pattern(), str2);
            }
            z |= find;
        }
        if (z) {
            return new LocalAntiSpamResult(this.b, str);
        }
        return null;
    }
}
