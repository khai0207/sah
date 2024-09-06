package com.netease.nimlib.log;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.o.ab;
import com.netease.nimlib.o.b.c;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.w;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.util.NIMUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: LogHelper.java */
/* loaded from: classes.dex */
public class a {
    private static boolean a = false;

    public static String a(boolean z) {
        String str;
        if (a) {
            return null;
        }
        a = true;
        ArrayList arrayList = new ArrayList();
        String b = b(z);
        if (TextUtils.isEmpty(b)) {
            b = c(z);
        }
        if (!TextUtils.isEmpty(b) && new File(b).exists()) {
            arrayList.add(b);
        }
        String a2 = a(z, c.b("rts_net.log", com.netease.nimlib.o.b.b.TYPE_LOG));
        if (!TextUtils.isEmpty(a2) && new File(a2).exists()) {
            arrayList.add(a2);
        }
        String a3 = a(z, c.b("nrtc_engine.log", com.netease.nimlib.o.b.b.TYPE_LOG));
        if (!TextUtils.isEmpty(a3) && new File(a3).exists()) {
            arrayList.add(a3);
        }
        String a4 = a(z, c.b("nrtc_detect.log", com.netease.nimlib.o.b.b.TYPE_LOG));
        if (!TextUtils.isEmpty(a4) && new File(a4).exists()) {
            arrayList.add(a4);
        }
        String a5 = a(z, c.b("nrtc_sdk.log", com.netease.nimlib.o.b.b.TYPE_LOG));
        if (!TextUtils.isEmpty(a5) && new File(a5).exists()) {
            arrayList.add(a5);
        }
        a(c.b(com.netease.nimlib.o.b.b.TYPE_LOG) + "high_available", arrayList);
        String str2 = "";
        String str3 = com.netease.nimlib.c.i() != null ? com.netease.nimlib.c.i().sdkStorageRootPath : "";
        String nimDefaultCacheDir = com.netease.nimlib.c.e() != null ? NIMUtil.getNimDefaultCacheDir(com.netease.nimlib.c.e()) : "";
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        } else if (!TextUtils.isEmpty(nimDefaultCacheDir)) {
            str2 = nimDefaultCacheDir;
        }
        if (!w.a((CharSequence) str2)) {
            if (str2.endsWith("/")) {
                str = str2 + "extra_log";
            } else {
                str = str2 + "/extra_log";
            }
            a(str, arrayList);
        }
        String b2 = b(str2, arrayList);
        if (z) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                new File((String) it.next()).delete();
            }
        }
        a = false;
        return b2;
    }

    private static void a(String str, List<String> list) {
        File[] listFiles;
        if (w.a((CharSequence) str)) {
            return;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory() || file.listFiles() == null || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            try {
                list.add(file2.getCanonicalPath());
                b.d("LogHelper", String.format("extra log: %s", file2.getName()));
            } catch (IOException e) {
                e.printStackTrace();
                b.e("LogHelper", String.format("extra log: %s", file2.getName()), e);
            }
        }
    }

    private static String b(String str, List<String> list) {
        String a2 = c.a(w.b(), com.netease.nimlib.o.b.b.TYPE_LOG);
        try {
            ab.a(str, list, a2);
        } catch (Exception e) {
            b.e("LogHelper", "zip or upload error", e);
        }
        return a2;
    }

    private static String b(boolean z) {
        String a2 = a();
        if (!z) {
            return a2;
        }
        if (TextUtils.isEmpty(a2)) {
            b.d("LogHelper", "get sdk mlog path error");
            return null;
        }
        return a(a2, c.a("sdk_" + System.currentTimeMillis() + "_nim.mlog", com.netease.nimlib.o.b.b.TYPE_LOG), true);
    }

    private static String c(boolean z) {
        String b = c.b("nim_sdk.log", com.netease.nimlib.o.b.b.TYPE_LOG);
        if (!z) {
            return b;
        }
        if (TextUtils.isEmpty(b)) {
            b.d("LogHelper", "get sdk log path error");
            return null;
        }
        return a(b, c.a("sdk_" + System.currentTimeMillis() + "_nim.log", com.netease.nimlib.o.b.b.TYPE_LOG), true);
    }

    private static String a(boolean z, String str) {
        if (!z) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            b.d("LogHelper", "get sdk log path error");
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            b.d("LogHelper", "sdk log not found , path : " + str);
            return null;
        }
        return a(str, c.a("sdk_" + System.currentTimeMillis() + "_" + file.getName(), com.netease.nimlib.o.b.b.TYPE_LOG), false);
    }

    private static String a() {
        if (b.b()) {
            String b = c.b("nim_sdk_ui.mlog", com.netease.nimlib.o.b.b.TYPE_LOG);
            String b2 = c.b("nim_sdk_push.mlog", com.netease.nimlib.o.b.b.TYPE_LOG);
            String a2 = c.a("nim_sdk.mlog", com.netease.nimlib.o.b.b.TYPE_LOG);
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(a2)) {
                if (TextUtils.isEmpty(b2)) {
                    return b;
                }
                com.netease.nimlib.log.d.a.a(b, b2, a2);
                return a2;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0089 A[Catch: IOException -> 0x008d, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x008d, blocks: (B:56:0x004b, B:14:0x0089), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x008e -> B:15:0x0091). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            r0 = 0
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5e
            if (r8 == 0) goto Lf
            java.lang.String r8 = b()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L57
            r1.write(r8)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L57
        Lf:
            java.io.FileReader r8 = new java.io.FileReader     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L57
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L57
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r0.<init>(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            long r2 = r0.length()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            android.content.Context r6 = com.netease.nimlib.c.e()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            int r6 = com.netease.nimlib.o.p.a(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r0 = 1
            if (r6 == r0) goto L33
            r4 = 8388608(0x800000, double:4.144523E-317)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L33
            long r2 = r2 - r4
            r8.skip(r2)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
        L33:
            r6 = 4096(0x1000, float:5.74E-42)
            char[] r6 = new char[r6]     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
        L37:
            int r0 = r8.read(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r2 = -1
            if (r0 == r2) goto L43
            r2 = 0
            r1.write(r6, r2, r0)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            goto L37
        L43:
            r1.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r6 = move-exception
            r6.printStackTrace()
        L4b:
            r8.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L4f:
            r6 = move-exception
            goto L55
        L51:
            r6 = move-exception
            goto L59
        L53:
            r6 = move-exception
            r8 = r0
        L55:
            r0 = r1
            goto L93
        L57:
            r6 = move-exception
            r8 = r0
        L59:
            r0 = r1
            goto L60
        L5b:
            r6 = move-exception
            r8 = r0
            goto L93
        L5e:
            r6 = move-exception
            r8 = r0
        L60:
            java.lang.String r1 = "LogHelper"
            java.lang.String r2 = "prepare file to upload error"
            com.netease.nimlib.log.b.d(r1, r2)     // Catch: java.lang.Throwable -> L92
            com.netease.nimlib.n.b.i r1 = com.netease.nimlib.n.b.i.kRead     // Catch: java.lang.Throwable -> L92
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L92
            r2.<init>()     // Catch: java.lang.Throwable -> L92
            java.lang.String r3 = "LogHelper#tempLog exception = "
            r2.append(r3)     // Catch: java.lang.Throwable -> L92
            r2.append(r6)     // Catch: java.lang.Throwable -> L92
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L92
            com.netease.nimlib.n.e.a(r1, r7, r6)     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L87
            r0.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r6 = move-exception
            r6.printStackTrace()
        L87:
            if (r8 == 0) goto L91
            r8.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r6 = move-exception
            r6.printStackTrace()
        L91:
            return r7
        L92:
            r6 = move-exception
        L93:
            if (r0 == 0) goto L9d
            r0.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r7 = move-exception
            r7.printStackTrace()
        L9d:
            if (r8 == 0) goto La7
            r8.close()     // Catch: java.io.IOException -> La3
            goto La7
        La3:
            r7 = move-exception
            r7.printStackTrace()
        La7:
            goto La9
        La8:
            throw r6
        La9:
            goto La8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.log.a.a(java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static String b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("   time: ", y.b());
        linkedHashMap.put(" device: ", com.netease.nimlib.p.a.a() + " " + com.netease.nimlib.p.a.b());
        linkedHashMap.put("android: ", Build.VERSION.RELEASE);
        linkedHashMap.put(" system: ", Build.DISPLAY);
        linkedHashMap.put("    ram: ", String.valueOf(c()));
        linkedHashMap.put("   disk: ", d());
        linkedHashMap.put("network: ", p.g(com.netease.nimlib.c.e()));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (entry != null) {
                sb.append((String) entry.getKey());
                sb.append((String) entry.getValue());
                sb.append(System.getProperty("line.separator"));
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("========================");
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    private static long c() {
        ActivityManager activityManager = (ActivityManager) com.netease.nimlib.c.e().getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    private static String d() {
        long[] e = e();
        long j = e[0];
        long j2 = e[1];
        if (j <= 0) {
            return "--";
        }
        return String.format(Locale.US, "%.01f%% [%s]", Float.valueOf((((float) j2) * 100.0f) / ((float) j)), a(j));
    }

    private static long[] e() {
        long[] jArr = new long[2];
        if ("mounted".equals(Environment.getExternalStorageState())) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                long blockSizeLong = statFs.getBlockSizeLong();
                long blockCountLong = statFs.getBlockCountLong();
                long availableBlocksLong = statFs.getAvailableBlocksLong();
                jArr[0] = blockCountLong * blockSizeLong;
                jArr[1] = blockSizeLong * availableBlocksLong;
            } else {
                long blockSize = statFs.getBlockSize();
                long blockCount = statFs.getBlockCount();
                long availableBlocks = statFs.getAvailableBlocks();
                jArr[0] = blockCount * blockSize;
                jArr[1] = blockSize * availableBlocks;
            }
        }
        return jArr;
    }

    private static String a(long j) {
        return j >= Constants.GB ? String.format(Locale.US, "%.02f GB", Float.valueOf(((float) j) / 1.0737418E9f)) : j >= 1048576 ? String.format(Locale.US, "%.02f MB", Float.valueOf(((float) j) / 1048576.0f)) : String.format(Locale.US, "%.02f KB", Float.valueOf(((float) j) / 1024.0f));
    }
}
