package com.netease.nimlib.session.a;

import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.j;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.Collection;
import java.util.List;

/* compiled from: SessionReliableInfo.java */
/* loaded from: classes.dex */
public class d {
    private Long a;
    private final String b;
    private final SessionTypeEnum c;
    private long d;
    private String e;
    private long f;
    private long g;
    private String h;
    private long i;

    public static d a(GetMessagesDynamicallyParam getMessagesDynamicallyParam) {
        if (getMessagesDynamicallyParam == null || !getMessagesDynamicallyParam.isValid()) {
            return null;
        }
        d dVar = new d(getMessagesDynamicallyParam.getSessionId(), getMessagesDynamicallyParam.getSessionType());
        dVar.a(getMessagesDynamicallyParam.getFromTime(), 0L, (String) null);
        long toTime = getMessagesDynamicallyParam.getToTime();
        if (getMessagesDynamicallyParam.getDirection() == GetMessageDirectionEnum.BACKWARD || w.a((CharSequence) getMessagesDynamicallyParam.getAnchorClientId())) {
            dVar.b(toTime <= 0 ? 0L : toTime - 1, 0L, null);
        } else {
            dVar.b(toTime, 0L, null);
        }
        return dVar;
    }

    public static d a(List<? extends IMMessage> list, boolean z) {
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return null;
        }
        IMMessage iMMessage = list.get(0);
        IMMessage iMMessage2 = list.get(list.size() - 1);
        d dVar = new d(iMMessage.getSessionId(), iMMessage.getSessionType());
        if (z) {
            dVar.a(iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid());
            dVar.b(iMMessage2.getTime(), iMMessage2.getServerId(), iMMessage2.getUuid());
        } else {
            dVar.a(iMMessage2.getTime(), iMMessage2.getServerId(), iMMessage2.getUuid());
            dVar.b(iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid());
        }
        return dVar;
    }

    public static d a(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, String str2) {
        if (w.a((CharSequence) str) || sessionTypeEnum == null || j < 0 || w.a((CharSequence) str2)) {
            return null;
        }
        d dVar = new d(str, sessionTypeEnum);
        dVar.a(j, j2, str2);
        dVar.b(j, j2, str2);
        return dVar;
    }

    public static d a(String str, SessionTypeEnum sessionTypeEnum, long j) {
        if (w.a((CharSequence) str) || sessionTypeEnum == null || j < 0) {
            return null;
        }
        d dVar = new d(str, sessionTypeEnum);
        dVar.a(j, 0L, (String) null);
        dVar.b(j, 0L, null);
        return dVar;
    }

    public d(String str, SessionTypeEnum sessionTypeEnum) {
        this(null, str, sessionTypeEnum, 0L, null, 0L, 0L, null, 0L);
    }

    public d(Long l, String str, SessionTypeEnum sessionTypeEnum) {
        this(l, str, sessionTypeEnum, 0L, null, 0L, 0L, null, 0L);
    }

    private d(Long l, String str, SessionTypeEnum sessionTypeEnum, long j, String str2, long j2, long j3, String str3, long j4) {
        this.a = l;
        this.b = str;
        this.c = sessionTypeEnum;
        this.d = j;
        this.e = str2;
        this.f = j2;
        this.g = j3;
        this.h = str3;
        this.i = j4;
    }

    public void a(Long l) {
        this.a = l;
    }

    public void a(long j, long j2, String str) {
        this.f = j;
        this.d = j2;
        this.e = str;
    }

    public void b(long j, long j2, String str) {
        this.i = j;
        this.g = j2;
        this.h = str;
    }

    public boolean a() {
        return this.f > 0;
    }

    public boolean b() {
        return this.i > 0;
    }

    public boolean a(d dVar) {
        boolean z;
        boolean z2 = false;
        if (dVar == null) {
            return false;
        }
        long i = dVar.i();
        long l = dVar.l();
        if (i > 0) {
            long j = this.f;
            if (j <= i && l > 0) {
                long j2 = this.i;
                if (j2 >= l) {
                    if (j != i && j2 != l) {
                        return true;
                    }
                    if (this.f == i) {
                        z = (C$r8$backportedMethods$utility$Objects$2$equals.equals(this.e, dVar.h()) && this.d == dVar.g()) & true;
                    } else {
                        z = true;
                    }
                    if (this.i != l) {
                        return z;
                    }
                    if (C$r8$backportedMethods$utility$Objects$2$equals.equals(this.h, dVar.k()) && this.g == dVar.j()) {
                        z2 = true;
                    }
                    return z & z2;
                }
            }
        }
        return false;
    }

    public boolean b(d dVar) {
        boolean z = false;
        if (dVar == null) {
            return false;
        }
        long i = dVar.i();
        String h = dVar.h();
        long g = dVar.g();
        long l = dVar.l();
        String k = dVar.k();
        long j = dVar.j();
        if (i > 0 && l >= i) {
            long i2 = i();
            String h2 = h();
            long g2 = g();
            long l2 = l();
            String k2 = k();
            long j2 = j();
            if (l2 >= i && i2 <= l) {
                z = true;
                if (i2 == l && g2 == j && C$r8$backportedMethods$utility$Objects$2$equals.equals(h2, k)) {
                    a(i, g, h);
                    return true;
                }
                if (l2 == i && j2 == g && C$r8$backportedMethods$utility$Objects$2$equals.equals(k2, h)) {
                    b(l, j, k);
                    return true;
                }
                if (i2 > i) {
                    a(i, g, h);
                } else if (i2 == i && g2 <= 0 && w.a((CharSequence) h2)) {
                    a(i, g, h);
                }
                if (l2 < l) {
                    b(l, j, k);
                } else if (l2 == l && j2 <= 0 && w.a((CharSequence) k2)) {
                    b(l, j, k);
                }
            }
        }
        return z;
    }

    public String c() {
        return j.a(f(), e());
    }

    public Long d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public SessionTypeEnum f() {
        return this.c;
    }

    public long g() {
        return this.d;
    }

    public String h() {
        return this.e;
    }

    public long i() {
        return this.f;
    }

    public long j() {
        return this.g;
    }

    public String k() {
        return this.h;
    }

    public long l() {
        return this.i;
    }

    public String toString() {
        return "SessionReliableInfo{sessionId='" + this.b + "', sessionType=" + this.c + ", startMessageIdServer=" + this.d + ", startMessageIdClient='" + this.e + "', startTime=" + this.f + ", stopMessageIdServer=" + this.g + ", stopMessageIdClient='" + this.h + "', stopTime=" + this.i + '}';
    }
}
