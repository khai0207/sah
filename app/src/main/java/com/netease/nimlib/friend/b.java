package com.netease.nimlib.friend;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.friend.constant.FriendRelationship;
import com.netease.nimlib.sdk.friend.constant.FriendSource;
import com.netease.nimlib.sdk.friend.model.Friend;
import java.util.Map;

/* compiled from: FriendImpl.java */
/* loaded from: classes.dex */
public class b implements Friend {
    private String a;
    private Integer b;
    private Integer c;
    private Byte d;
    private String e;
    private Long f;
    private Map<String, Object> g;
    private String h;
    private Long i;
    private Long j;
    private String k;

    public static final b a(String str) {
        b bVar = new b();
        bVar.b(str);
        bVar.a((Integer) 1);
        bVar.b((Integer) 1);
        bVar.a((Byte) (byte) 0);
        return bVar;
    }

    public static final b a(c cVar) {
        b bVar = new b();
        bVar.b(cVar.c(4));
        bVar.a(Integer.valueOf(cVar.d(5)));
        bVar.b(Integer.valueOf(cVar.d(6)));
        bVar.a(Byte.valueOf((byte) cVar.d(7)));
        bVar.c(cVar.c(8));
        bVar.b(Long.valueOf(cVar.e(9)));
        bVar.d(cVar.c(10));
        bVar.c(Long.valueOf(cVar.e(11)));
        bVar.a(Long.valueOf(cVar.e(12)));
        bVar.e(cVar.c(13));
        return bVar;
    }

    @Override // com.netease.nimlib.sdk.friend.model.Friend
    public String getAccount() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.friend.model.Friend
    public String getAlias() {
        return this.e;
    }

    @Override // com.netease.nimlib.sdk.friend.model.Friend
    public Map<String, Object> getExtension() {
        return this.g;
    }

    @Override // com.netease.nimlib.sdk.friend.model.Friend
    public String getServerExtension() {
        return this.k;
    }

    public void a(Long l) {
        this.j = l;
    }

    public void b(String str) {
        this.a = str;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public void b(Integer num) {
        this.c = num;
    }

    public void a(Byte b) {
        this.d = b;
    }

    public void b(Long l) {
        this.f = l;
    }

    public void c(Long l) {
        this.i = l;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.h = str;
        this.g = a.c(str);
    }

    public void e(String str) {
        this.k = str;
    }

    public FriendRelationship a() {
        return FriendRelationship.RelationshipOfValue(this.b.intValue());
    }

    public FriendSource b() {
        return FriendSource.friendSourceOfValue(this.d.byteValue());
    }

    public Integer c() {
        return this.b;
    }

    public Integer d() {
        return this.c;
    }

    public Long e() {
        return this.f;
    }

    public Long f() {
        return this.i;
    }

    public Long g() {
        return this.j;
    }

    public String h() {
        return this.h;
    }

    public static String a(b bVar) {
        if (bVar == null) {
            return "FriendImpl{null}";
        }
        return "FriendImpl{account='" + bVar.a + "', flag=" + bVar.b + ", beFlag=" + bVar.c + ", alias='" + bVar.e + "', bits=" + bVar.f + ", createTime=" + bVar.i + ", updateTime=" + bVar.j + ", serverExtension='" + bVar.k + "'}";
    }
}
