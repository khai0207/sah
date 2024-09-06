package com.netease.nimlib.net.a.c;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.d.g;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.ServerAddresses;
import com.netease.nimlib.sdk.misc.model.LogDesensitizationConfig;
import com.netease.nimlib.sdk.misc.model.NosConfig;
import com.talkingdata.sdk.aa;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: NosFormatUtil.java */
/* loaded from: classes.dex */
public class c {
    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.contains("{bucket}") && str.contains("{object}");
    }

    static String a(String str, String str2, String str3) {
        return str.replace("{bucket}", str2).replace("{object}", str3);
    }

    static String a(String str, String str2) {
        LogDesensitizationConfig logDesensitizationConfig = com.netease.nimlib.c.i().logDesensitizationConfig;
        com.netease.nimlib.log.b.d("NOS", String.format("to replace url, origin url is %s short url is %s", com.netease.nimlib.log.b.a.a(str, logDesensitizationConfig), com.netease.nimlib.log.b.a.a(str2, logDesensitizationConfig)));
        ServerAddresses l = com.netease.nimlib.c.l();
        if ((l == null || l.nosCdnEnable) && TextUtils.isEmpty(str2)) {
            Pair<String, Boolean> b = b(str);
            if (Boolean.TRUE.equals(b.second)) {
                com.netease.nimlib.log.b.d("NOS", String.format("replace by cdn, replaced url is %s", com.netease.nimlib.log.b.a.a((String) b.first, logDesensitizationConfig)));
                return (String) b.first;
            }
        }
        String d = d(str);
        return !str.equals(d) ? d : c(str);
    }

    private static Pair<String, Boolean> b(String str) {
        Pair<String, Boolean> pair = new Pair<>(str, false);
        NosConfig A = com.netease.nimlib.c.A();
        if (A == null || !A.isValid()) {
            com.netease.nimlib.log.b.d("NOS", "cancel replacing by cdn, download config is not valid");
            return pair;
        }
        String cdnDomain = A.getCdnDomain();
        try {
            URL url = new URL(str);
            Boolean a = a(url, A.getObjectNamePrefix());
            if (a == null) {
                com.netease.nimlib.log.b.d("NOS", "failed to replace by cdn, can not tell the place of bucket");
                return pair;
            }
            String decode = URLDecoder.decode(url.getProtocol());
            StringBuilder sb = new StringBuilder();
            sb.append(decode);
            sb.append(aa.a);
            sb.append(cdnDomain);
            String file = url.getFile();
            if (Boolean.FALSE.equals(a)) {
                int length = file.length();
                for (int i = 1; i < length; i++) {
                    if (file.charAt(i) == '/') {
                        sb.append(file.substring(i));
                        return new Pair<>(sb.toString(), true);
                    }
                }
                com.netease.nimlib.log.b.d("NOS", "failed to replace by cdn, can not separate bucket and object from the file: " + file);
                return pair;
            }
            sb.append(file);
            return new Pair<>(sb.toString(), true);
        } catch (Throwable th) {
            th.printStackTrace();
            com.netease.nimlib.log.b.e("NOS", "replace by cdn error", th);
            return pair;
        }
    }

    private static Boolean a(URL url, String str) {
        String decode = URLDecoder.decode(url.getPath());
        URLDecoder.decode(url.getAuthority());
        if (!TextUtils.isEmpty(decode) && !TextUtils.isEmpty(str)) {
            int indexOf = decode.indexOf(str);
            if (indexOf == 0 || (indexOf == 1 && decode.charAt(0) == '/')) {
                return true;
            }
            if (indexOf > 1) {
                return false;
            }
        }
        return null;
    }

    private static String c(String str) {
        String k = g.k();
        return (str.contains("nos.netease.com") && a(k)) ? b(str, "nos.netease.com", k) : str;
    }

    private static String d(String str) {
        String str2;
        ServerAddresses l = com.netease.nimlib.c.l();
        if (l == null) {
            return str;
        }
        HashSet hashSet = new HashSet();
        if (!f.c((Collection) l.nosDownloadSet)) {
            hashSet.addAll(l.nosDownloadSet);
        }
        if (!TextUtils.isEmpty(l.nosDownload)) {
            hashSet.add(l.nosDownload);
        }
        hashSet.add("nos.netease.com");
        hashSet.add("nosdn.netease.im");
        hashSet.add("nosdn.127.net");
        Iterator it = hashSet.iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = "";
                break;
            }
            str2 = (String) it.next();
            if (!TextUtils.isEmpty(str2) && str.contains(str2)) {
                break;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        String m = g.m();
        if (TextUtils.isEmpty(m)) {
            return str;
        }
        if (m.contains("{bucket}") && m.contains("{object}")) {
            return b(str, str2, m);
        }
        try {
            String substring = str.substring(0, str.indexOf("/", str.indexOf(aa.a) + 3));
            if (substring.endsWith(str2)) {
                return str.replace(substring, m);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.f("NOS", "replace host error, url=" + com.netease.nimlib.log.b.a.a(str, com.netease.nimlib.c.i().logDesensitizationConfig) + ", host=" + m);
            e.printStackTrace();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String b(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String[] r6 = b(r5, r6)
            if (r6 == 0) goto Lac
            int r0 = r6.length
            r1 = 3
            if (r0 == r1) goto Lc
            goto Lac
        Lc:
            r0 = 0
            r0 = r6[r0]
            r1 = 1
            r2 = r6[r1]
            r3 = 2
            r6 = r6[r3]
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto Lac
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto Lac
            boolean r4 = android.text.TextUtils.isEmpty(r6)
            if (r4 != 0) goto Lac
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r0 = "://"
            r4.append(r0)
            java.lang.String r0 = "{bucket}"
            java.lang.String r7 = r7.replace(r0, r2)
            java.lang.String r0 = "{object}"
            java.lang.String r6 = r7.replace(r0, r6)
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            java.lang.String r7 = "?"
            boolean r0 = r5.contains(r7)
            if (r0 == 0) goto L5b
            java.lang.String r0 = "\\?"
            java.lang.String[] r0 = r5.split(r0)
            int r2 = r0.length
            if (r2 != r3) goto L5b
            r0 = r0[r1]
            goto L5d
        L5b:
            java.lang.String r0 = ""
        L5d:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L75
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r7)
            r1.append(r0)
            java.lang.String r6 = r1.toString()
        L75:
            com.netease.nimlib.sdk.SDKOptions r7 = com.netease.nimlib.c.i()
            com.netease.nimlib.sdk.misc.model.LogDesensitizationConfig r7 = r7.logDesensitizationConfig
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "replace host for cdn, src url ="
            r0.append(r1)
            java.lang.String r5 = com.netease.nimlib.log.b.a.a(r5, r7)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.netease.nimlib.log.b.a(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "replace host for cdn, dest url ="
            r5.append(r0)
            java.lang.String r7 = com.netease.nimlib.log.b.a.a(r6, r7)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            com.netease.nimlib.log.b.a(r5)
            return r6
        Lac:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.net.a.c.c.b(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    private static String[] b(String str, String str2) {
        String substring;
        String substring2;
        try {
            URL url = new URL(str);
            String decode = URLDecoder.decode(url.getProtocol());
            String decode2 = URLDecoder.decode(url.getAuthority());
            String decode3 = URLDecoder.decode(url.getPath());
            if (decode2.startsWith(str2)) {
                int indexOf = decode3.indexOf("/", 1);
                substring = decode3.substring(1, indexOf);
                substring2 = decode3.substring(indexOf + 1);
            } else {
                int length = decode2.length();
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = decode2.charAt(i2);
                    if ((charAt <= 'z' && charAt >= 'a') || (charAt <= 'Z' && charAt >= 'A')) {
                    }
                    i = i2;
                    break;
                }
                substring = i < 0 ? "" : decode2.substring(0, i);
                substring2 = decode3.substring(1);
            }
            return new String[]{decode, substring, substring2};
        } catch (Throwable th) {
            com.netease.nimlib.log.b.a("extract protocol bucket object error, e=" + th.getMessage());
            th.printStackTrace();
            return null;
        }
    }
}
