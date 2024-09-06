package u.aly;

/* compiled from: TField.java */
/* loaded from: classes.dex */
public class ct {
    public final String a;
    public final byte b;
    public final short c;

    public ct() {
        this("", (byte) 0, (short) 0);
    }

    public ct(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + ">";
    }

    public boolean a(ct ctVar) {
        return this.b == ctVar.b && this.c == ctVar.c;
    }
}
