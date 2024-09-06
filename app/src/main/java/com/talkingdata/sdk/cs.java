package com.talkingdata.sdk;

import android.os.Process;
import java.util.Properties;
import java.util.zip.CRC32;

/* compiled from: td */
/* loaded from: classes.dex */
public class cs extends Properties implements Comparable {
    private String a;
    private byte[] b;
    private int c;
    private int d;
    private CRC32 e;

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static final class a {
        public static final String a = "rcs32";
        public static final String b = "length";
        public static final String c = "data";
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static final class b {
        public static final String a = "Type";
    }

    public cs(String str) {
        this.a = str;
    }

    public cs(byte[] bArr) {
        this(a(), bArr);
    }

    private cs(String str, byte[] bArr) {
        this(str);
        this.e = new CRC32();
        writeData(bArr);
    }

    public static String a() {
        return System.currentTimeMillis() + "_" + Long.toString(Process.myPid());
    }

    public String b() {
        return this.a;
    }

    public byte[] c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int a(String str, int i) {
        String str2 = (String) setProperty(str, String.valueOf(i));
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public byte[] a(String str, byte[] bArr) {
        String str2 = (String) setProperty(str, a(bArr));
        if (str2 == null) {
            return null;
        }
        return c(str2);
    }

    public int a(String str) {
        return Integer.valueOf(super.getProperty(str)).intValue();
    }

    public byte[] b(String str) {
        return c(super.getProperty(str));
    }

    public void writeData(byte[] bArr) {
        this.b = bArr;
        this.d = bArr.length;
        this.e.reset();
        this.e.update(bArr);
        this.c = (int) this.e.getValue();
    }

    public byte[] c(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    public String a(byte[] bArr) {
        return new String(bArr);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(cs csVar) {
        return b().compareTo(csVar.b());
    }
}
