package u.aly;

import java.util.BitSet;

/* compiled from: TTupleProtocol.java */
/* loaded from: classes.dex */
public final class de extends cs {

    /* compiled from: TTupleProtocol.java */
    /* loaded from: classes.dex */
    public static class a implements da {
        @Override // u.aly.da
        public cy a(dm dmVar) {
            return new de(dmVar);
        }
    }

    public de(dm dmVar) {
        super(dmVar);
    }

    @Override // u.aly.cy
    public Class<? extends dg> D() {
        return dj.class;
    }

    public void a(BitSet bitSet, int i) throws cf {
        for (byte b : b(bitSet, i)) {
            a(b);
        }
    }

    public BitSet b(int i) throws cf {
        double d = i;
        Double.isNaN(d);
        int ceil = (int) Math.ceil(d / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = u();
        }
        return a(bArr);
    }

    public static BitSet a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] b(BitSet bitSet, int i) {
        double d = i;
        Double.isNaN(d);
        int ceil = (int) Math.ceil(d / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int i3 = (ceil - (i2 / 8)) - 1;
                bArr[i3] = (byte) ((1 << (i2 % 8)) | bArr[i3]);
            }
        }
        return bArr;
    }
}
