package com.netease.nimlib.user;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.uinfo.constant.GenderEnum;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.netease.nimlib.session.j;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NimUserInfoImpl.java */
/* loaded from: classes.dex */
public class b implements NimUserInfo {
    private String a;
    private String b;
    private String c;
    private String d;
    private Integer e;
    private String f;
    private String g;
    private String h;
    private String i;
    private long j = 0;

    public static b a(com.netease.nimlib.push.packet.b.c cVar) {
        b bVar = new b();
        bVar.a(cVar.c(1));
        bVar.b(cVar.c(3));
        bVar.c(cVar.c(4));
        bVar.d(cVar.c(5));
        bVar.a(Integer.valueOf(cVar.d(6)));
        bVar.e(cVar.c(7));
        bVar.f(cVar.c(8));
        bVar.g(cVar.c(9));
        bVar.h(cVar.c(10));
        bVar.a(cVar.e(13));
        return bVar;
    }

    public static b a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        b bVar = new b();
        String valueOf = String.valueOf(1);
        if (jSONObject.has(valueOf)) {
            bVar.a(jSONObject.getString(valueOf));
        }
        String valueOf2 = String.valueOf(3);
        if (jSONObject.has(valueOf2)) {
            bVar.b(jSONObject.getString(valueOf2));
        }
        String valueOf3 = String.valueOf(4);
        if (jSONObject.has(valueOf3)) {
            bVar.c(jSONObject.getString(valueOf3));
        }
        String valueOf4 = String.valueOf(5);
        if (jSONObject.has(valueOf4)) {
            bVar.d(jSONObject.getString(valueOf4));
        }
        String valueOf5 = String.valueOf(6);
        if (jSONObject.has(valueOf5)) {
            bVar.a(Integer.valueOf(jSONObject.getInt(valueOf5)));
        }
        String valueOf6 = String.valueOf(7);
        if (jSONObject.has(valueOf6)) {
            bVar.e(jSONObject.getString(valueOf6));
        }
        String valueOf7 = String.valueOf(8);
        if (jSONObject.has(valueOf7)) {
            bVar.f(jSONObject.getString(valueOf7));
        }
        String valueOf8 = String.valueOf(9);
        if (jSONObject.has(valueOf8)) {
            bVar.g(jSONObject.getString(valueOf8));
        }
        String valueOf9 = String.valueOf(10);
        if (jSONObject.has(valueOf9)) {
            bVar.h(jSONObject.getString(valueOf9));
        }
        String valueOf10 = String.valueOf(13);
        if (jSONObject.has(valueOf10)) {
            bVar.a(k.b(jSONObject, valueOf10));
        }
        return bVar;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getAccount() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getName() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.UserInfo
    public String getAvatar() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public String getSignature() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public GenderEnum getGenderEnum() {
        return GenderEnum.genderOfValue(this.e.intValue());
    }

    public Integer a() {
        return this.e;
    }

    public void a(Integer num) {
        this.e = num;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public String getEmail() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public String getBirthday() {
        return this.g;
    }

    public void f(String str) {
        this.g = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public String getMobile() {
        return this.h;
    }

    public void g(String str) {
        this.h = str;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public String getExtension() {
        return this.i;
    }

    public void h(String str) {
        this.i = str;
    }

    public long b() {
        return this.j;
    }

    public void a(long j) {
        this.j = j;
    }

    @Override // com.netease.nimlib.sdk.uinfo.model.NimUserInfo
    public Map<String, Object> getExtensionMap() {
        return j.c(this.i);
    }

    public String toString() {
        return "NimUserInfoImpl{accid='" + this.a + "', name='" + com.netease.nimlib.log.b.a.a((CharSequence) this.b) + "', icon='" + this.c + "', sign='" + this.d + "', gender=" + this.e + ", email='" + this.f + "', birth='" + this.g + "', mobile='" + this.h + "', ex='" + this.i + "', updateTimeTag=" + this.j + '}';
    }
}
