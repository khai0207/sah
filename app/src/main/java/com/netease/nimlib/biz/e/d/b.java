package com.netease.nimlib.biz.e.d;

import android.text.TextUtils;
import org.json.JSONObject;

/* compiled from: GetAppGrayConfigResponse.java */
@com.netease.nimlib.biz.e.b(a = 6, b = {"27"})
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.biz.e.a {
    private boolean c = false;
    private long d = 0;
    private boolean e = false;
    private boolean f = false;

    @Override // com.netease.nimlib.biz.e.a
    public com.netease.nimlib.push.packet.c.f a(com.netease.nimlib.push.packet.c.f fVar) throws Exception {
        com.netease.nimlib.push.packet.b.c a = com.netease.nimlib.push.packet.c.d.a(fVar);
        com.netease.nimlib.push.packet.a j = j();
        if (j != null) {
            com.netease.nimlib.log.b.J("************ GetAppGrayConfigResponse begin ****************");
            com.netease.nimlib.log.b.a(j.i(), j.j(), "code = " + ((int) r()));
            com.netease.nimlib.log.b.a(j.i(), j.j(), "property", a);
            com.netease.nimlib.log.b.J("************ GetAppGrayConfigResponse end ****************");
        }
        String c = a.c(0);
        this.d = a.e(1);
        if (TextUtils.isEmpty(c)) {
            this.c = false;
            this.e = false;
            this.d = 0L;
            this.f = false;
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(c);
            this.c = jSONObject.optBoolean("mixStoreEnable");
            this.e = jSONObject.optBoolean("eidEnable");
            this.f = jSONObject.optBoolean("abTestIntervalFlag");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            this.c = false;
            this.e = false;
            this.d = 0L;
            this.f = false;
            return null;
        }
    }

    public boolean a() {
        return this.c;
    }

    public boolean b() {
        return this.f;
    }

    public long c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }
}
