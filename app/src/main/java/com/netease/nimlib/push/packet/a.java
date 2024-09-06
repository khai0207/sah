package com.netease.nimlib.push.packet;

import com.netease.nimlib.push.packet.b.b;
import com.netease.nimlib.push.packet.c.f;
import com.netease.nimlib.sdk.ResponseCode;

/* compiled from: PacketHeader.java */
/* loaded from: classes.dex */
public class a implements b {
    private byte a;
    private byte b;
    private short c;
    private byte d;
    private String f;
    private long g;
    private long h;
    private long i;
    private short j = ResponseCode.RES_SUCCESS;
    private int e = 0;

    public a() {
    }

    public a(byte b, byte b2) {
        this.a = b;
        this.b = b2;
    }

    public a a() {
        a aVar = new a();
        aVar.a = this.a;
        aVar.b = this.b;
        aVar.c = this.c;
        aVar.d = this.d;
        aVar.e = this.e;
        aVar.j = this.j;
        aVar.f = this.f;
        aVar.g = this.g;
        aVar.h = this.h;
        aVar.i = this.i;
        return aVar;
    }

    public void b() {
        this.j = ResponseCode.RES_SUCCESS;
        this.d = (byte) 0;
        this.e = 0;
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(com.netease.nimlib.push.packet.c.b bVar) {
        bVar.b(this.e);
        bVar.a(this.a);
        bVar.a(this.b);
        bVar.a(this.c);
        bVar.a(this.d);
        if (d()) {
            bVar.a(this.j);
        }
    }

    @Override // com.netease.nimlib.push.packet.b.b
    public void a(f fVar) {
        this.e = fVar.g();
        this.a = fVar.c();
        this.b = fVar.c();
        this.c = fVar.j();
        this.d = fVar.c();
        if (d()) {
            this.j = fVar.j();
        }
    }

    public boolean c() {
        return (this.d & 1) != 0;
    }

    public boolean d() {
        return (this.d & 2) != 0;
    }

    public void e() {
        this.d = (byte) (this.d | 1);
    }

    public void f() {
        this.d = (byte) (this.d | 2);
    }

    public void g() {
        this.d = (byte) (this.d & (-2));
    }

    public int h() {
        return d() ? 7 : 5;
    }

    public byte i() {
        return this.a;
    }

    public byte j() {
        return this.b;
    }

    public short k() {
        return this.c;
    }

    public void a(short s) {
        this.c = s;
    }

    public short l() {
        return this.j;
    }

    public void b(short s) {
        this.j = s;
        f();
    }

    public byte m() {
        return this.d;
    }

    public int n() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public String o() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public long p() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public long q() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public long r() {
        return this.i;
    }

    public void c(long j) {
        this.i = j;
    }

    public String toString() {
        return ("PacketHeader [SID " + ((int) this.a) + " , CID " + ((int) this.b) + " , SER " + ((int) this.c) + " , RES " + ((int) this.j) + " , TAG " + ((int) this.d) + " , LEN " + n()) + "]";
    }
}
