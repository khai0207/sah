package com.netease.nimlib.push.packet.asymmetric;

import android.content.Context;
import android.util.Pair;
import com.netease.nimlib.push.packet.a.a.b.a;
import com.netease.nimlib.push.packet.a.a.c.i;
import com.netease.nimlib.push.packet.a.a.c.k;
import com.netease.nimlib.push.packet.a.a.g;
import com.netease.nimlib.push.packet.a.b.a.h;
import com.talkingdata.sdk.aj;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/* compiled from: SM2Asymmetric.java */
/* loaded from: classes.dex */
public class f extends com.netease.nimlib.push.packet.asymmetric.a {
    private static final com.netease.nimlib.push.packet.a.b.a.a.a.a d = new com.netease.nimlib.push.packet.a.b.a.a.a.a();
    private static final BigInteger e = new BigInteger("32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7", 16);
    private static final BigInteger f;
    private static final h g;
    private static final com.netease.nimlib.push.packet.a.a.c.f h;

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    protected String c() {
        return "nim/sm2/";
    }

    static {
        BigInteger bigInteger = new BigInteger("BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0", 16);
        f = bigInteger;
        g = d.b(e, bigInteger);
        com.netease.nimlib.push.packet.a.b.a.a.a.a aVar = d;
        h = new com.netease.nimlib.push.packet.a.a.c.f(aVar, g, aVar.g(), d.h());
    }

    public f(Context context) {
        super(context);
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    protected void a() {
        f();
        h();
    }

    private void h() {
        File file = new File(b());
        try {
            FileInputStream fileInputStream = file.exists() ? new FileInputStream(file) : null;
            if ((fileInputStream == null || !a(fileInputStream, true)) && !a(this.a.getAssets().open(e()), true)) {
                com.netease.nimlib.log.b.O("load public key from assets failed!!");
            }
        } catch (Exception e2) {
            com.netease.nimlib.log.b.e("core", "load public key error", e2);
        }
        if (this.c == null) {
            file.delete();
        }
    }

    private boolean a(InputStream inputStream, boolean z) {
        byte[] bArr = new byte[256];
        try {
            try {
                int read = inputStream.read(bArr);
                long j = 0;
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                if (z) {
                    j = wrap.getLong();
                    read -= 8;
                }
                this.b = wrap.getInt();
                if (this.b < 0) {
                    return false;
                }
                byte[] bArr2 = new byte[read - 4];
                wrap.get(bArr2);
                this.c = new a(bArr2);
                if (z) {
                    a aVar = (a) this.c;
                    if (!a(aVar.b.toString(16), aVar.c.toString(16), j)) {
                        g();
                        this.c = null;
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                        }
                        return false;
                    }
                }
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
                return false;
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused4) {
            }
        }
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    protected String e() {
        return com.netease.nimlib.d.e.c() ? "bbgsvirgin4" : "nim/sm2/r.jks";
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    public void a(int i, String str, String str2, long j) {
        if (str == null || str2 == null || !a(str, str2, j)) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] encoded = new a(new BigInteger(str, 16), new BigInteger(str2, 16)).getEncoded();
                ByteBuffer allocate = ByteBuffer.allocate(encoded.length + 12);
                allocate.putLong(j);
                allocate.putInt(i);
                allocate.put(encoded);
                File file = new File(b());
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(b()));
                try {
                    fileOutputStream2.write(allocate.array(), 0, allocate.capacity());
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream2);
                } catch (Exception unused) {
                    fileOutputStream = fileOutputStream2;
                    g();
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream);
                } catch (Throwable th) {
                    fileOutputStream = fileOutputStream2;
                    th = th;
                    com.netease.nimlib.log.c.a.a.a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    public byte[] a(PublicKey publicKey, byte[] bArr, int i, int i2) {
        int i3;
        i a2;
        if (bArr == null || i < 0 || i2 < 0 || i >= bArr.length || (i3 = i2 + i) > bArr.length || (a2 = a(publicKey)) == null) {
            return null;
        }
        com.netease.nimlib.push.packet.a.a.b.a aVar = new com.netease.nimlib.push.packet.a.a.b.a(a.EnumC0056a.C1C2C3);
        aVar.a(true, (com.netease.nimlib.push.packet.a.a.a) new k(a2));
        try {
            return aVar.a(bArr, i, i3);
        } catch (g e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private i a(PublicKey publicKey) {
        if (!(publicKey instanceof a)) {
            return null;
        }
        a aVar = (a) publicKey;
        byte[] byteArray = aVar.b.toByteArray();
        byte[] byteArray2 = aVar.c.toByteArray();
        return new i(new com.netease.nimlib.push.packet.a.b.a.a.a.d(d, new com.netease.nimlib.push.packet.a.b.a.a.a.c(new BigInteger(byteArray)), new com.netease.nimlib.push.packet.a.b.a.a.a.c(new BigInteger(byteArray2))), h);
    }

    /* compiled from: SM2Asymmetric.java */
    /* loaded from: classes.dex */
    private class a implements PublicKey {
        private final BigInteger b;
        private final BigInteger c;
        private final X509EncodedKeySpec d;

        @Override // java.security.Key
        public String getAlgorithm() {
            return "SM2";
        }

        public a(BigInteger bigInteger, BigInteger bigInteger2) {
            this.b = bigInteger;
            this.c = bigInteger2;
            this.d = new X509EncodedKeySpec(new BigInteger(aj.a + a(bigInteger) + a(bigInteger2), 16).toByteArray());
        }

        public a(byte[] bArr) {
            if (bArr == null) {
                throw new NullPointerException("构造SM2PublicKey失败，buffer为空");
            }
            String bigInteger = new BigInteger(bArr).toString(16);
            if (bigInteger.length() != 129) {
                throw new IllegalArgumentException("构造SM2PublicKey失败，buffer位数不对");
            }
            Pair<BigInteger, BigInteger> a = a(bigInteger);
            this.b = (BigInteger) a.first;
            this.c = (BigInteger) a.second;
            this.d = new X509EncodedKeySpec(new BigInteger(bigInteger, 16).toByteArray());
        }

        private Pair<BigInteger, BigInteger> a(String str) {
            int length = str.length();
            return new Pair<>(new BigInteger(str.substring(1, 65), 16), new BigInteger(str.substring(length - 64, length), 16));
        }

        @Override // java.security.Key
        public String getFormat() {
            return this.d.getFormat();
        }

        @Override // java.security.Key
        public byte[] getEncoded() {
            return this.d.getEncoded();
        }

        private String a(BigInteger bigInteger) {
            if (bigInteger == null) {
                return null;
            }
            String bigInteger2 = bigInteger.toString(16);
            int length = 64 - bigInteger2.length();
            if (length < 0) {
                return null;
            }
            if (length == 0) {
                return bigInteger2;
            }
            return String.format("%0" + length + "d%s", 0, bigInteger2);
        }

        public String toString() {
            return new String(getEncoded());
        }
    }
}
