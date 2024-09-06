package u.aly;

import java.io.ByteArrayOutputStream;

/* compiled from: TByteArrayOutputStream.java */
/* loaded from: classes.dex */
public class cb extends ByteArrayOutputStream {
    public cb(int i) {
        super(i);
    }

    public cb() {
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
