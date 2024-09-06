package com.netease.nimlib.push.packet.asymmetric;

import android.content.Context;
import com.netease.nimlib.o.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* compiled from: RSAAsymmetric.java */
/* loaded from: classes.dex */
public class e extends a {
    private final AsymmetricType d;
    private final int e;
    private int f;

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    protected String c() {
        return "nim/rsa/";
    }

    public e(Context context, AsymmetricType asymmetricType) {
        super(context);
        this.d = asymmetricType == null ? AsymmetricType.RSA : asymmetricType;
        int i = AnonymousClass1.a[this.d.ordinal()];
        if (i == 1 || i == 2) {
            this.e = this.f - 66;
        } else {
            this.e = this.f - 11;
        }
    }

    /* compiled from: RSAAsymmetric.java */
    /* renamed from: com.netease.nimlib.push.packet.asymmetric.e$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[AsymmetricType.values().length];
            a = iArr;
            try {
                iArr[AsymmetricType.RSA_OAEP_256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AsymmetricType.RSA_OAEP_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[AsymmetricType.RSA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
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
            if ((fileInputStream == null || !a((InputStream) fileInputStream, true)) && !a(this.a.getAssets().open(e()), true)) {
                com.netease.nimlib.log.b.O("load public key from assets failed!!");
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("core", "load public key error", e);
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
                this.c = KeyFactory.getInstance(com.alipay.sdk.m.j.d.a).generatePublic(new X509EncodedKeySpec(bArr2));
                RSAPublicKey rSAPublicKey = (RSAPublicKey) this.c;
                this.f = (rSAPublicKey.getModulus().toByteArray().length / 128) * 128;
                if (!z || a(rSAPublicKey.getModulus().toString(16), rSAPublicKey.getPublicExponent().toString(16), j)) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                    return true;
                }
                g();
                this.c = null;
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
                return false;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException unused4) {
            }
            return false;
        }
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    protected String e() {
        return com.netease.nimlib.d.e.c() ? "bbgsvirgin4" : "nim/rsa/r.jks";
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    public void a(int i, String str, String str2, long j) {
        if (str == null || !a(str, str2, j)) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] encoded = KeyFactory.getInstance(com.alipay.sdk.m.j.d.a).generatePublic(new RSAPublicKeySpec(new BigInteger(str, 16), new BigInteger(str2, 16))).getEncoded();
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
            } catch (Exception unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.netease.nimlib.push.packet.asymmetric.a
    public byte[] a(PublicKey publicKey, byte[] bArr, int i, int i2) {
        if (bArr != null && i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
            try {
                Cipher a = a(publicKey, this.d);
                if (a == null) {
                    return null;
                }
                byte[] bArr2 = new byte[(((i2 - 1) / this.e) * 128) + 128];
                int i3 = 0;
                while (i < i2) {
                    int min = Math.min(i2 - (this.e * i3), this.e);
                    byte[] doFinal = a.doFinal(bArr, i, min);
                    System.arraycopy(doFinal, 0, bArr2, i3 * 128, doFinal.length);
                    i3++;
                    i += min;
                }
                return bArr2;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private Cipher a(PublicKey publicKey, AsymmetricType asymmetricType) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        if (asymmetricType == null) {
            return null;
        }
        int i = AnonymousClass1.a[asymmetricType.ordinal()];
        if (i == 1) {
            String a = g.a("UlNBL0VDQi9PQUVQUGFkZGluZw==");
            com.netease.nimlib.log.b.I("RSAAsymmetric#createAndInitCipher RSA_OAEP_256 transformation = " + a);
            Cipher cipher = Cipher.getInstance(a);
            cipher.init(1, publicKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT));
            return cipher;
        }
        if (i == 2) {
            String a2 = g.a("UlNBL0VDQi9PQUVQV2l0aFNIQS0xQW5kTUdGMVBhZGRpbmc=");
            com.netease.nimlib.log.b.I("RSAAsymmetric#createAndInitCipher RSA_OAEP_1 transformation = " + a2);
            Cipher cipher2 = Cipher.getInstance(a2);
            cipher2.init(1, publicKey);
            return cipher2;
        }
        if (i == 3) {
            String a3 = g.a("UlNBL0VDQi9QS0NTMVBhZGRpbmc=");
            com.netease.nimlib.log.b.I("RSAAsymmetric#createAndInitCipher RSA transformation = " + a3);
            Cipher cipher3 = Cipher.getInstance(a3);
            cipher3.init(1, publicKey);
            return cipher3;
        }
        com.netease.nimlib.log.b.O("unsupported RSA type, type=" + asymmetricType.name());
        return null;
    }
}
