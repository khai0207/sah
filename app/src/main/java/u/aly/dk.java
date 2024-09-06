package u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport.java */
/* loaded from: classes.dex */
public class dk extends dm {
    protected InputStream a;
    protected OutputStream b;

    @Override // u.aly.dm
    public boolean a() {
        return true;
    }

    @Override // u.aly.dm
    public void b() throws dn {
    }

    protected dk() {
        this.a = null;
        this.b = null;
    }

    public dk(InputStream inputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
    }

    public dk(OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.b = outputStream;
    }

    public dk(InputStream inputStream, OutputStream outputStream) {
        this.a = null;
        this.b = null;
        this.a = inputStream;
        this.b = outputStream;
    }

    @Override // u.aly.dm
    public void c() {
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        OutputStream outputStream = this.b;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    @Override // u.aly.dm
    public int a(byte[] bArr, int i, int i2) throws dn {
        InputStream inputStream = this.a;
        if (inputStream == null) {
            throw new dn(1, "Cannot read from null inputStream");
        }
        try {
            int read = inputStream.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new dn(4);
        } catch (IOException e) {
            throw new dn(0, e);
        }
    }

    @Override // u.aly.dm
    public void b(byte[] bArr, int i, int i2) throws dn {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dn(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e) {
            throw new dn(0, e);
        }
    }

    @Override // u.aly.dm
    public void d() throws dn {
        OutputStream outputStream = this.b;
        if (outputStream == null) {
            throw new dn(1, "Cannot flush null outputStream");
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            throw new dn(0, e);
        }
    }
}
