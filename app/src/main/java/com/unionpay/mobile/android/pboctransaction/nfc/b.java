package com.unionpay.mobile.android.pboctransaction.nfc;

import android.nfc.tech.IsoDep;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes.dex */
public class b {
    public static final byte[] a = {0};
    protected byte[] b;

    /* loaded from: classes.dex */
    public static final class a extends b {
        public static final byte[] c = new byte[0];
        public static final byte[] d = {111, 0};

        public a(byte[] bArr) {
            super((bArr == null || bArr.length < 2) ? d : bArr);
        }

        @Override // com.unionpay.mobile.android.pboctransaction.nfc.b
        public final byte[] a() {
            return b() ? Arrays.copyOfRange(this.b, 0, this.b.length - 2) : c;
        }

        public final boolean b() {
            byte[] bArr = this.b;
            int length = bArr.length;
            return ((short) ((bArr[length - 1] & 255) | (bArr[length + (-2)] << 8))) == -28672;
        }
    }

    /* renamed from: com.unionpay.mobile.android.pboctransaction.nfc.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0069b {
        private final IsoDep a;

        public C0069b(IsoDep isoDep) {
            this.a = isoDep;
        }

        public static String c(byte[] bArr) {
            String str = "";
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str = str + hexString.toUpperCase() + " ";
            }
            return str;
        }

        public final a a(byte... bArr) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
            allocate.put((byte) 0).put((byte) -92).put((byte) 4).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
            Log.e("PBOC Transceive", c(allocate.array()));
            a aVar = new a(b(allocate.array()));
            Log.e("PBOC receive", c(aVar.a()));
            return aVar;
        }

        public final void a() {
            try {
                this.a.connect();
            } catch (Exception unused) {
            }
        }

        public final byte[] b(byte[] bArr) {
            try {
                return this.a.transceive(bArr);
            } catch (Exception unused) {
                return a.d;
            }
        }
    }

    protected b(byte[] bArr) {
        this.b = bArr == null ? a : bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public String toString() {
        byte[] bArr = this.b;
        return c.a(bArr, bArr.length);
    }
}
