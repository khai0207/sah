package com.netease.nimlib.database.encrypt;

import com.netease.nimlib.database.a.d;
import java.util.List;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: DatabaseRevision.java */
/* loaded from: classes.dex */
public class a {
    private final C0032a[] a;

    /* compiled from: DatabaseRevision.java */
    /* renamed from: com.netease.nimlib.database.encrypt.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0032a {
        private final com.netease.nimlib.database.a.d a;
        private final List<d.a> b;

        public C0032a(com.netease.nimlib.database.a.d dVar) {
            this.a = dVar;
            this.b = dVar.c();
        }

        void a(SQLiteDatabase sQLiteDatabase, int i) {
            int a = a(i);
            if (a < 0) {
                return;
            }
            b(sQLiteDatabase, a);
        }

        void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            int a = a(i2);
            int a2 = a(i);
            if (a == a2) {
                return;
            }
            if (a2 < 0) {
                b(sQLiteDatabase, a);
                return;
            }
            if (a2 < a) {
                if (!this.a.b()) {
                    b(sQLiteDatabase, a2, a);
                    return;
                }
                while (a2 < a) {
                    int i3 = a2 + 1;
                    b(sQLiteDatabase, a2, i3);
                    a2 = i3;
                }
            }
        }

        private int a(int i) {
            int i2 = -1;
            for (int i3 = 0; i3 < this.b.size(); i3++) {
                if (i >= this.b.get(i3).b()) {
                    i2 = i3;
                }
            }
            return i2;
        }

        private void b(SQLiteDatabase sQLiteDatabase, int i) {
            d.a aVar = this.b.get(i);
            com.netease.nimlib.log.b.c("db", "create: table " + this + " target " + aVar);
            a(sQLiteDatabase, aVar.a());
        }

        private void b(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            d.a aVar = this.b.get(i);
            d.a aVar2 = this.b.get(i2);
            com.netease.nimlib.log.b.c("db", "upgrade: table " + this + " initial " + aVar + " target " + aVar2);
            a(sQLiteDatabase, aVar2.a(aVar));
        }

        public String toString() {
            return this.a.a();
        }

        private static void a(SQLiteDatabase sQLiteDatabase, String[] strArr) {
            if (strArr != null) {
                for (String str : strArr) {
                    sQLiteDatabase.execSQL(str);
                }
            }
        }
    }

    public a(com.netease.nimlib.database.a.d[] dVarArr) {
        this.a = new C0032a[dVarArr.length];
        for (int i = 0; i < dVarArr.length; i++) {
            this.a[i] = new C0032a(dVarArr[i]);
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i) {
        for (C0032a c0032a : this.a) {
            c0032a.a(sQLiteDatabase, i);
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (C0032a c0032a : this.a) {
            c0032a.a(sQLiteDatabase, i, i2);
        }
    }
}
