package com.netease.nimlib.biz;

import android.text.TextUtils;
import com.netease.nimlib.biz.e.a;
import com.netease.nimlib.sdk.ResponseCode;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProtocolFrequencyController.java */
/* loaded from: classes.dex */
public class g {
    private ConcurrentHashMap<String, a> a = new ConcurrentHashMap<>();

    /* compiled from: ProtocolFrequencyController.java */
    /* loaded from: classes.dex */
    public static class b {
        public static final g a = new g();
    }

    /* compiled from: ProtocolFrequencyController.java */
    /* loaded from: classes.dex */
    public interface c {
        void a(a.C0029a c0029a);
    }

    /* compiled from: ProtocolFrequencyController.java */
    /* loaded from: classes.dex */
    class a {
        long a;
        long b;

        a(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }

    private String a(com.netease.nimlib.push.packet.a aVar, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append((int) aVar.i());
        sb.append("_");
        sb.append((int) aVar.j());
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = "_" + str;
        }
        sb.append(str2);
        return sb.toString();
    }

    private String b(byte b2, byte b3, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append((int) b2);
        sb.append("_");
        sb.append((int) b3);
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = "_" + str;
        }
        sb.append(str2);
        return sb.toString();
    }

    private String a(com.netease.nimlib.biz.d.a aVar, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append((int) aVar.b());
        sb.append("_");
        sb.append((int) aVar.c());
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = "_" + str;
        }
        sb.append(str2);
        return sb.toString();
    }

    public void a(a.C0029a c0029a, String str) {
        if (c0029a.a.l() != 416 || c0029a.b == null) {
            return;
        }
        String a2 = a(c0029a.a, str);
        long h = c0029a.b.h() * 1000;
        long currentTimeMillis = System.currentTimeMillis();
        this.a.put(a2, new a(h, currentTimeMillis));
        com.netease.nimlib.log.b.d("PFC", "add protocol frequency control, key=" + a2 + ", limit time=" + h + ", startTime=" + currentTimeMillis);
    }

    public void a(a.C0029a c0029a) {
        a(c0029a, (String) null);
    }

    public void a(byte b2, byte b3, String str) {
        a(b2, b3, 300L, str);
    }

    public void a(byte b2, byte b3, long j, String str) {
        if (b2 > 0 && b3 > 0 && j > 0) {
            String b4 = b(b2, b3, str);
            if (this.a.containsKey(b4)) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            this.a.put(b4, new a(j, currentTimeMillis));
            com.netease.nimlib.log.b.d("PFC", "set frequency control, key=" + b4 + ", limit time=" + j + ", startTime=" + currentTimeMillis);
        }
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar, String str, c cVar) {
        String a2 = a(aVar, str);
        boolean z = true;
        if (this.a.containsKey(a2)) {
            a aVar2 = this.a.get(a2);
            long currentTimeMillis = aVar2.a - (System.currentTimeMillis() - aVar2.b);
            if (currentTimeMillis < 0) {
                this.a.remove(a2);
                com.netease.nimlib.log.b.d("PFC", "remove protocol frequency control, key=" + a2);
                return true;
            }
            a.C0029a a3 = a.C0029a.a(aVar.i(), ResponseCode.RES_EFREQUENTLY);
            z = false;
            if (cVar != null) {
                cVar.a(a3);
            } else {
                i.a().a(a3, false);
            }
            com.netease.nimlib.log.b.d("PFC", "do protocol frequency control, key=" + a2 + ", remain limit time=" + currentTimeMillis);
        }
        return z;
    }

    public boolean a(com.netease.nimlib.biz.d.a aVar) {
        return a(aVar, (String) null, (c) null);
    }

    public void a() {
        this.a.clear();
    }

    public static g b() {
        return b.a;
    }
}
