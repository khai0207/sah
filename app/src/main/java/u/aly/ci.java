package u.aly;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import u.aly.cs;

/* compiled from: TSerializer.java */
/* loaded from: classes.dex */
public class ci {
    private final ByteArrayOutputStream a;
    private final dk b;
    private cy c;

    public ci() {
        this(new cs.a());
    }

    public ci(da daVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a = byteArrayOutputStream;
        dk dkVar = new dk(byteArrayOutputStream);
        this.b = dkVar;
        this.c = daVar.a(dkVar);
    }

    public byte[] a(bz bzVar) throws cf {
        this.a.reset();
        bzVar.b(this.c);
        return this.a.toByteArray();
    }

    public String a(bz bzVar, String str) throws cf {
        try {
            return new String(a(bzVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new cf("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(bz bzVar) throws cf {
        return new String(a(bzVar));
    }
}
