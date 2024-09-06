package com.netease.nimlib.o;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/* compiled from: FileStore.java */
/* loaded from: classes.dex */
public class h {
    public static long a(List<String> list, long j, long j2) {
        a aVar = new a() { // from class: com.netease.nimlib.o.h.1
            @Override // com.netease.nimlib.o.h.a
            protected void a(File file) {
                this.a += file.length();
            }
        };
        aVar.a(list, j, j2);
        return aVar.a();
    }

    public static void b(List<String> list, long j, long j2) {
        new a() { // from class: com.netease.nimlib.o.h.2
            @Override // com.netease.nimlib.o.h.a
            protected void a(File file) {
                file.delete();
            }
        }.a(list, j, j2);
    }

    /* compiled from: FileStore.java */
    /* loaded from: classes.dex */
    private static abstract class a {
        protected long a;

        protected abstract void a(File file);

        private a() {
        }

        public long a() {
            return this.a;
        }

        public void a(List<String> list, long j, long j2) {
            if (list == null) {
                return;
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                File[] listFiles = new File(it.next()).listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        long lastModified = file.lastModified();
                        if ((j == 0 || lastModified >= j) && (j2 == 0 || lastModified <= j2)) {
                            a(file);
                        }
                    }
                }
            }
        }
    }
}
