package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.utils.j;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes.dex */
public final class a {
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        if (r2.containsKey("tn") != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.os.Bundle r2) {
        /*
            if (r2 == 0) goto L18
            java.lang.String r0 = "paydata"
            boolean r1 = r2.containsKey(r0)
            if (r1 == 0) goto Lf
        La:
            java.lang.String r2 = r2.getString(r0)
            goto L19
        Lf:
            java.lang.String r0 = "tn"
            boolean r1 = r2.containsKey(r0)
            if (r1 == 0) goto L18
            goto La
        L18:
            r2 = 0
        L19:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(android.os.Bundle):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.lang.String r4) {
        /*
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "decodePayInfo +++ \n"
            com.unionpay.mobile.android.utils.j.a(r0, r1)
            r1 = 0
            if (r4 == 0) goto Lf
            java.lang.String r4 = java.net.URLDecoder.decode(r4)
            goto L10
        Lf:
            r4 = r1
        L10:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "url deocode result:"
            r2.<init>(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.j.b(r0, r2)
            if (r4 == 0) goto L2c
            byte[] r4 = com.unionpay.mobile.android.utils.a.a(r4)     // Catch: java.io.IOException -> L28
            goto L2d
        L28:
            r4 = move-exception
            r4.printStackTrace()
        L2c:
            r4 = r1
        L2d:
            if (r4 == 0) goto L3c
            java.lang.String r2 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L38
            java.lang.String r3 = "UTF-8"
            r2.<init>(r4, r3)     // Catch: java.io.UnsupportedEncodingException -> L38
            r1 = r2
            goto L3c
        L38:
            r4 = move-exception
            r4.printStackTrace()
        L3c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "order info:"
            r4.<init>(r2)
            r4.append(r1)
            java.lang.String r2 = "\n"
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            com.unionpay.mobile.android.utils.j.a(r0, r4)
            java.lang.String r4 = "decodePayInfo --- \n"
            com.unionpay.mobile.android.utils.j.a(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0271  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Intent r9, com.unionpay.mobile.android.model.b r10) {
        /*
            Method dump skipped, instructions count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.utils.a.a(android.content.Intent, com.unionpay.mobile.android.model.b):boolean");
    }

    private static boolean a(String str, com.unionpay.mobile.android.model.b bVar) {
        boolean z = false;
        if (str != null) {
            String[] split = str.split("\\?");
            if (split.length < 2) {
                j.c("uppay", "uppay protocol params error!");
            } else {
                String str2 = split[1];
                j.a("uppay", "parseUPPayURIParams() +++ ");
                j.a("uppay", str2);
                String str3 = null;
                String str4 = null;
                for (String str5 : str2.split(com.alipay.sdk.m.o.a.l)) {
                    String[] split2 = str5.split("=");
                    if (split2.length >= 2) {
                        if (split2[0].equalsIgnoreCase("style")) {
                            str3 = split2[1];
                        } else if (split2[0].equalsIgnoreCase("paydata")) {
                            str4 = split2[1];
                        }
                    }
                }
                if (str3 != null && str3.equalsIgnoreCase("token") && str4 != null) {
                    j.a("uppay", "paydata=" + str4);
                    z = b(a(str4), bVar);
                }
                j.a("uppay", "parseUPPayURIParams() ---");
            }
        }
        return z;
    }

    private static boolean b(String str, com.unionpay.mobile.android.model.b bVar) {
        com.unionpay.mobile.android.plugin.c cVar;
        String str2;
        if (str == null || str.length() == 0) {
            return false;
        }
        bVar.D.c = "00";
        for (String str3 : str.split(",")) {
            String[] split = str3.trim().split("=");
            if (split.length >= 2) {
                if (split[0].trim().equalsIgnoreCase("tn")) {
                    bVar.b = split[1].trim();
                } else if (split[0].trim().equalsIgnoreCase("usetestmode")) {
                    if (split[1].trim().equalsIgnoreCase("true")) {
                        cVar = bVar.D;
                        str2 = "01";
                    } else if (split[1].trim().equalsIgnoreCase("test")) {
                        cVar = bVar.D;
                        str2 = "02";
                    } else if (split[1].trim().equalsIgnoreCase("inner")) {
                        cVar = bVar.D;
                        str2 = "98";
                    }
                    cVar.c = str2;
                } else if (split[0].trim().equalsIgnoreCase("ResultURL")) {
                    try {
                        bVar.m = URLDecoder.decode(split[1].trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (bVar.b == null || bVar.b.length() <= 0 || bVar.m == null || bVar.m.length() <= 0) {
            return false;
        }
        bVar.c = !"2".equalsIgnoreCase(bVar.b.substring(bVar.b.length() - 1));
        j.a("uppay", "dw.isNewTypeTn=" + bVar.c);
        return true;
    }
}
