package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import u.aly.cs;

/* compiled from: TDeserializer.java */
/* loaded from: classes.dex */
public class cc {
    private final cy a;
    private final dl b;

    public cc() {
        this(new cs.a());
    }

    public cc(da daVar) {
        dl dlVar = new dl();
        this.b = dlVar;
        this.a = daVar.a(dlVar);
    }

    public void a(bz bzVar, byte[] bArr) throws cf {
        try {
            this.b.a(bArr);
            bzVar.a(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(bz bzVar, String str, String str2) throws cf {
        try {
            try {
                a(bzVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new cf("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.a.B();
        }
    }

    public void a(bz bzVar, byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        try {
            try {
                if (j(bArr, cgVar, cgVarArr) != null) {
                    bzVar.a(this.a);
                }
            } catch (Exception e) {
                throw new cf(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Boolean) a((byte) 2, bArr, cgVar, cgVarArr);
    }

    public Byte b(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Byte) a((byte) 3, bArr, cgVar, cgVarArr);
    }

    public Double c(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Double) a((byte) 4, bArr, cgVar, cgVarArr);
    }

    public Short d(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Short) a((byte) 6, bArr, cgVar, cgVarArr);
    }

    public Integer e(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Integer) a((byte) 8, bArr, cgVar, cgVarArr);
    }

    public Long f(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (Long) a((byte) 10, bArr, cgVar, cgVarArr);
    }

    public String g(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (String) a((byte) 11, bArr, cgVar, cgVarArr);
    }

    public ByteBuffer h(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        return (ByteBuffer) a((byte) 100, bArr, cgVar, cgVarArr);
    }

    public Short i(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        Short sh;
        try {
            try {
                if (j(bArr, cgVar, cgVarArr) != null) {
                    this.a.j();
                    sh = Short.valueOf(this.a.l().c);
                } else {
                    sh = null;
                }
                return sh;
            } catch (Exception e) {
                throw new cf(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        Object obj;
        try {
            try {
                ct j = j(bArr, cgVar, cgVarArr);
                if (j != null) {
                    if (b != 2) {
                        if (b != 3) {
                            if (b != 4) {
                                if (b != 6) {
                                    if (b != 8) {
                                        if (b != 100) {
                                            if (b == 10) {
                                                if (j.b == 10) {
                                                    obj = Long.valueOf(this.a.x());
                                                }
                                            } else if (b == 11 && j.b == 11) {
                                                obj = this.a.z();
                                            }
                                        } else if (j.b == 11) {
                                            obj = this.a.A();
                                        }
                                    } else if (j.b == 8) {
                                        obj = Integer.valueOf(this.a.w());
                                    }
                                } else if (j.b == 6) {
                                    obj = Short.valueOf(this.a.v());
                                }
                            } else if (j.b == 4) {
                                obj = Double.valueOf(this.a.y());
                            }
                        } else if (j.b == 3) {
                            obj = Byte.valueOf(this.a.u());
                        }
                    } else if (j.b == 2) {
                        obj = Boolean.valueOf(this.a.t());
                    }
                    return obj;
                }
                obj = null;
                return obj;
            } catch (Exception e) {
                throw new cf(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private ct j(byte[] bArr, cg cgVar, cg... cgVarArr) throws cf {
        this.b.a(bArr);
        int length = cgVarArr.length + 1;
        cg[] cgVarArr2 = new cg[length];
        int i = 0;
        cgVarArr2[0] = cgVar;
        int i2 = 0;
        while (i2 < cgVarArr.length) {
            int i3 = i2 + 1;
            cgVarArr2[i3] = cgVarArr[i2];
            i2 = i3;
        }
        this.a.j();
        ct ctVar = null;
        while (i < length) {
            ctVar = this.a.l();
            if (ctVar.b == 0 || ctVar.c > cgVarArr2[i].a()) {
                return null;
            }
            if (ctVar.c != cgVarArr2[i].a()) {
                db.a(this.a, ctVar.b);
                this.a.m();
            } else {
                i++;
                if (i < length) {
                    this.a.j();
                }
            }
        }
        return ctVar;
    }

    public void a(bz bzVar, String str) throws cf {
        a(bzVar, str.getBytes());
    }
}
