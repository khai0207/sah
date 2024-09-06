package com.netease.nimlib.g.a;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import org.json.JSONObject;

/* compiled from: SessionAckTag.java */
/* loaded from: classes.dex */
public class a {
    private SessionTypeEnum a;
    private String b;
    private long c;

    public a() {
    }

    public a(SessionTypeEnum sessionTypeEnum, String str, Long l) {
        this.a = sessionTypeEnum;
        this.b = str;
        this.c = l.longValue();
    }

    public void a(SessionTypeEnum sessionTypeEnum) {
        this.a = sessionTypeEnum;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(long j) {
        this.c = j;
    }

    public c a() {
        c cVar = new c();
        if (!TextUtils.isEmpty(this.b) && this.a != null) {
            cVar.a(1, SessionTypeEnum.Team == this.a ? 1 : 0);
            cVar.a(2, this.b);
            cVar.a(3, this.c);
        }
        return cVar;
    }

    public static a a(c cVar) {
        if (cVar == null) {
            return null;
        }
        a aVar = new a();
        if (cVar.f(1)) {
            aVar.a(SessionTypeEnum.typeOfValue(cVar.d(1)));
        }
        if (cVar.f(2)) {
            aVar.a(cVar.c(2));
        }
        aVar.a(cVar.e(3));
        return aVar;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.a != null) {
                jSONObject.put("type", this.a.getValue());
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("target", this.b);
            }
            jSONObject.put(com.alipay.sdk.m.p.a.k, this.c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static a a(JSONObject jSONObject) {
        SessionTypeEnum sessionTypeEnum;
        if (jSONObject == null) {
            return null;
        }
        try {
            if (!jSONObject.has("type") || !jSONObject.has("target") || !jSONObject.has(com.alipay.sdk.m.p.a.k)) {
                return null;
            }
            int i = jSONObject.getInt("type");
            if (SessionTypeEnum.P2P.getValue() == i) {
                sessionTypeEnum = SessionTypeEnum.P2P;
            } else {
                if (SessionTypeEnum.Team.getValue() != i) {
                    return null;
                }
                sessionTypeEnum = SessionTypeEnum.Team;
            }
            String optString = jSONObject.optString("target");
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            return new a(sessionTypeEnum, optString, Long.valueOf(jSONObject.optLong(com.alipay.sdk.m.p.a.k)));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
