package com.umeng.analytics.social;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: UMNetwork.java */
/* loaded from: classes.dex */
public abstract class c {
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0142: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:45:0x0142 */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static java.lang.String a(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.c.a(java.lang.String):java.lang.String");
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine + "\n");
                    } else {
                        try {
                            inputStream.close();
                            return sb.toString();
                        } catch (IOException e) {
                            b.b(com.umeng.analytics.a.e, "Caught IOException in convertStreamToString()", e);
                            return null;
                        }
                    }
                } catch (IOException e2) {
                    b.b(com.umeng.analytics.a.e, "Caught IOException in convertStreamToString()", e2);
                    try {
                        inputStream.close();
                        return null;
                    } catch (IOException e3) {
                        b.b(com.umeng.analytics.a.e, "Caught IOException in convertStreamToString()", e3);
                        return null;
                    }
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    throw th;
                } catch (IOException e4) {
                    b.b(com.umeng.analytics.a.e, "Caught IOException in convertStreamToString()", e4);
                    return null;
                }
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0146: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:39:0x0146 */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0149  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.c.a(java.lang.String, java.lang.String):java.lang.String");
    }
}
