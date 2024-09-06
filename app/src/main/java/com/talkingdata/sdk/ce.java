package com.talkingdata.sdk;

import com.android.pc.ioc.internet.FastHttp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public class ce {
    private static List a = new ArrayList();

    public static void postSDKError(Throwable th) {
        try {
            String c = bh.c(th.getMessage());
            if (a.contains(c)) {
                return;
            }
            cn cnVar = new cn();
            cnVar.b = "sdk";
            cnVar.c = "error";
            TreeMap treeMap = new TreeMap();
            treeMap.put("type", th.toString());
            treeMap.put("error", th.getMessage());
            treeMap.put("stack", a(th));
            cnVar.d = treeMap;
            cnVar.a = a.e;
            bk.a().post(cnVar);
            a.add(c);
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z, Map map) {
        if (map != null) {
            try {
                if (map.isEmpty()) {
                    return;
                }
                if (z && String.valueOf(map.get("targetUrl")).contains(aa.h)) {
                    return;
                }
                an.dForInternal(map.toString());
                cn cnVar = new cn();
                cnVar.b = "sdk";
                cnVar.c = z ? "send_ok" : "send_fail";
                cnVar.d = map;
                cnVar.a = a.e;
                bk.a().post(cnVar);
            } catch (Throwable unused) {
            }
        }
    }

    private static final String a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.toString());
        sb.append(FastHttp.LINEND);
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i = 50;
            if (stackTrace.length <= 50) {
                i = stackTrace.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("\t");
                sb.append(stackTrace[i2]);
                sb.append(FastHttp.LINEND);
            }
            Throwable cause = th.getCause();
            if (cause != null) {
                a(sb, stackTrace, cause, 1);
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    private static final void a(StringBuilder sb, StackTraceElement[] stackTraceElementArr, Throwable th, int i) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            for (int length2 = stackTraceElementArr.length - 1; length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2]); length2--) {
                length--;
            }
            if (length > 50) {
                length = 50;
            }
            sb.append("Caused by : ");
            sb.append(th);
            sb.append(FastHttp.LINEND);
            for (int i2 = 0; i2 <= length; i2++) {
                sb.append("\t");
                sb.append(stackTrace[i2]);
                sb.append(FastHttp.LINEND);
            }
            if (i < 5 && th.getCause() != null) {
                a(sb, stackTrace, th, i + 1);
            }
        } catch (Throwable unused) {
        }
    }
}
