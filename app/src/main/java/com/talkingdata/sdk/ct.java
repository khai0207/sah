package com.talkingdata.sdk;

import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CRC32;

/* compiled from: td */
/* loaded from: classes.dex */
public class ct {
    private static final String b = "OperationManager";
    private static final int c = 6;
    private static ct d;
    Lock a = new ReentrantLock();
    private ExecutorService e;
    private cs f;
    private HashMap g;
    private CRC32 h;
    private Map i;
    private Map j;

    public static ct a() {
        synchronized (ct.class) {
            if (d == null) {
                d = new ct();
            }
        }
        return d;
    }

    private ct() {
        c();
        this.f = null;
        this.g = new HashMap();
        for (com.talkingdata.sdk.a aVar : com.talkingdata.sdk.a.f) {
            this.g.put(Integer.valueOf(aVar.c()), new TreeSet());
        }
        this.e = Executors.newSingleThreadExecutor();
        this.h = new CRC32();
    }

    private void c() {
        File filesDir = ab.f.getFilesDir();
        this.i = new HashMap();
        this.j = new HashMap();
        try {
            for (com.talkingdata.sdk.a aVar : com.talkingdata.sdk.a.f) {
                File file = new File(filesDir, "td_database" + aVar.c() + db.c);
                if (!file.exists()) {
                    file.mkdir();
                }
                this.i.put(new Integer(aVar.c()), new RandomAccessFile(new File(filesDir, "Lock" + aVar.c()), "rw"));
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void b() {
        File filesDir = ab.f.getFilesDir();
        try {
            for (com.talkingdata.sdk.a aVar : com.talkingdata.sdk.a.f) {
                File file = new File(filesDir, "td_database" + aVar.c() + db.c);
                if (file.exists()) {
                    Iterator it = d(file).iterator();
                    while (it.hasNext()) {
                        ((File) it.next()).delete();
                    }
                }
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public void getFileLock(com.talkingdata.sdk.a aVar) {
        try {
            this.a.lock();
            this.j.put(new Integer(aVar.c()), ((RandomAccessFile) this.i.get(new Integer(aVar.c()))).getChannel().lock());
        } catch (Throwable unused) {
        }
    }

    public void releaseFileLock(com.talkingdata.sdk.a aVar) {
        try {
            if (this.j.get(new Integer(aVar.c())) != null) {
                ((FileLock) this.j.get(new Integer(aVar.c()))).release();
                this.a.unlock();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(File file) {
        try {
            if (c(file) > 6) {
                b(file);
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private void b(File file) {
        try {
            if (file.isDirectory()) {
                b((File) d(file).get(0));
            } else {
                file.delete();
            }
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    private int c(File file) {
        if (file == null) {
            return 0;
        }
        try {
            if (!file.isDirectory()) {
                return 0;
            }
            long j = 0;
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    j += file2.length();
                }
            }
            return (int) (j / 1048576);
        } catch (Throwable th) {
            ce.postSDKError(th);
            return 0;
        }
    }

    private List d(File file) {
        List asList = Arrays.asList(file.listFiles());
        try {
            Collections.sort(asList, new cu(this));
        } catch (Throwable unused) {
        }
        return asList;
    }

    public synchronized void a(cs csVar, cn cnVar) {
        this.e.execute(new d(csVar, cnVar));
    }

    public synchronized void a(cs csVar, com.talkingdata.sdk.a aVar) {
        if (aVar != null && csVar != null) {
            try {
                ((TreeSet) this.g.get(Integer.valueOf(aVar.c()))).add(csVar);
            } finally {
            }
        }
    }

    public synchronized List a(com.talkingdata.sdk.a aVar, int i) {
        LinkedList linkedList;
        cs csVar;
        RandomAccessFile randomAccessFile;
        linkedList = new LinkedList();
        File file = new File(ab.f.getFilesDir(), "td_database" + aVar.c() + db.c);
        if (file.exists()) {
            String[] list = file.list();
            if (list != null && list.length > 0) {
                if (list.length < i) {
                    i = list.length;
                }
                RandomAccessFile randomAccessFile2 = null;
                FileLock fileLock = null;
                cs csVar2 = null;
                for (int i2 = 0; i2 < i; i2++) {
                    try {
                        File file2 = new File(file, list[i2]);
                        csVar = new cs(list[i2]);
                        try {
                            randomAccessFile = new RandomAccessFile(file2, "rw");
                        } catch (Throwable unused) {
                        }
                    } catch (Throwable unused2) {
                    }
                    try {
                        fileLock = randomAccessFile.getChannel().tryLock();
                        if (fileLock == null) {
                            randomAccessFile.close();
                            if (fileLock != null) {
                                try {
                                    fileLock.release();
                                    fileLock = null;
                                } catch (Throwable unused3) {
                                    randomAccessFile2 = randomAccessFile;
                                }
                            }
                        } else {
                            randomAccessFile.seek(1L);
                            int readInt = randomAccessFile.readInt();
                            byte[] bArr = new byte[randomAccessFile.readInt()];
                            randomAccessFile.readFully(bArr);
                            this.h.reset();
                            this.h.update(bArr);
                            if (readInt == ((int) this.h.getValue())) {
                                linkedList.add(bArr);
                                a(csVar, aVar);
                            } else {
                                this.e.execute(new b(csVar, aVar));
                            }
                            if (fileLock != null) {
                                fileLock.release();
                                fileLock = null;
                            }
                        }
                        randomAccessFile.close();
                        randomAccessFile2 = null;
                        csVar2 = csVar;
                    } catch (Throwable unused4) {
                        randomAccessFile2 = randomAccessFile;
                        csVar2 = csVar;
                        try {
                            this.e.execute(new b(csVar2, aVar));
                            if (fileLock != null) {
                                try {
                                    fileLock.release();
                                    fileLock = null;
                                } catch (Throwable unused5) {
                                }
                            }
                            if (randomAccessFile2 != null) {
                                randomAccessFile2 = null;
                            }
                        } finally {
                            if (fileLock != null) {
                                try {
                                    fileLock.release();
                                } catch (Throwable unused6) {
                                }
                            }
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                            }
                        }
                    }
                }
            }
        } else {
            an.iForDeveloper("operationFolder is not exists: " + file);
        }
        return linkedList;
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class c implements Runnable {
        private final String b;
        private ct c;

        public c(String str, ct ctVar) {
            this.b = str;
            this.c = ctVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(this.b);
                if (!file.exists()) {
                    Log.i(ct.b, "folder path is not exists:" + this.b);
                    return;
                }
                String[] list = file.list();
                if (list == null || list.length <= 0) {
                    return;
                }
                for (String str : list) {
                    if (str != null) {
                        str.length();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class d implements Runnable {
        private final String b;
        private final cs c;

        public d(cs csVar, cn cnVar) {
            this.b = ab.f.getFilesDir() + File.separator + "td_database" + cnVar.a.c() + db.c;
            this.c = csVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(this.b);
                if (!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                ct.this.a(file);
                File file2 = new File(this.b + File.separator + this.c.b());
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                randomAccessFile.seek(1L);
                randomAccessFile.writeInt(this.c.d());
                randomAccessFile.writeInt(this.c.e());
                randomAccessFile.write(this.c.c());
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
    }

    public void confirmRead(com.talkingdata.sdk.a aVar) {
        this.e.execute(new a(aVar));
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class a implements Runnable {
        private final String b;
        private final TreeSet c;
        private final com.talkingdata.sdk.a d;

        public a(com.talkingdata.sdk.a aVar) {
            this.b = ab.f.getFilesDir() + File.separator + "td_database" + aVar.c() + db.c;
            this.c = (TreeSet) ct.this.g.get(Integer.valueOf(aVar.c()));
            this.d = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.c != null) {
                    this.c.isEmpty();
                    while (!this.c.isEmpty()) {
                        cs csVar = (cs) this.c.pollFirst();
                        if (csVar != null) {
                            File file = new File(this.b + File.separator + csVar.b());
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    }
                    this.c.clear();
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class b implements Runnable {
        private final String b;
        private final cs c;

        public b(cs csVar, com.talkingdata.sdk.a aVar) {
            this.b = ab.f.getFilesDir().getAbsolutePath() + File.separator + "td_database" + aVar.c() + db.c;
            this.c = csVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(this.b + File.separator + this.c.b());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
    }
}
