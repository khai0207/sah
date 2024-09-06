package u.aly;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

/* compiled from: UError.java */
/* loaded from: classes.dex */
public class ah extends av implements r {
    public ah() {
        a(System.currentTimeMillis());
        a(aw.LEGIT);
    }

    public ah(String str) {
        this();
        a(str);
    }

    public ah(Throwable th) {
        this();
        a(a(th));
    }

    public ah a(boolean z) {
        a(z ? aw.LEGIT : aw.ALIEN);
        return this;
    }

    private String a(Throwable th) {
        String str = null;
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            str = stringWriter.toString();
            printWriter.close();
            stringWriter.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    @Override // u.aly.r
    public void a(bp bpVar, String str) {
        be beVar;
        if (bpVar.s() > 0) {
            Iterator<be> it = bpVar.u().iterator();
            while (it.hasNext()) {
                beVar = it.next();
                if (str.equals(beVar.c())) {
                    break;
                }
            }
        }
        beVar = null;
        if (beVar == null) {
            beVar = new be();
            beVar.a(str);
            bpVar.a(beVar);
        }
        beVar.a(this);
    }
}
