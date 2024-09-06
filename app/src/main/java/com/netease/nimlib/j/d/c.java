package com.netease.nimlib.j.d;

import android.text.TextUtils;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.i.k;
import com.netease.nimlib.net.a.a.e;
import com.netease.nimlib.net.a.a.f;
import com.netease.nimlib.net.a.a.g;
import com.netease.nimlib.sdk.migration.model.IHistoryRecord;
import com.netease.nimlib.sdk.migration.model.MigrationConstant;
import com.netease.nimlib.sdk.migration.processor.IMsgImportProcessor;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: MigrationImportTask.java */
/* loaded from: classes.dex */
public class c extends a {
    private IMsgImportProcessor f;
    private e g;
    private com.netease.nimlib.j.a.a h;

    public c(k kVar, IMsgImportProcessor iMsgImportProcessor, boolean z) {
        super(kVar, iMsgImportProcessor, "MigrationImportTask", z);
        this.f = iMsgImportProcessor;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a) {
            return;
        }
        c();
    }

    @Override // com.netease.nimlib.j.d.a
    public void a() {
        super.a();
        if (this.g != null) {
            if (com.netease.nimlib.biz.b.e.d().a()) {
                com.netease.nimlib.biz.b.e.d().a(this.g);
            } else {
                g.a().b(this.g);
            }
            this.g = null;
        }
    }

    private void c() {
        this.c = System.currentTimeMillis();
        com.netease.nimlib.log.b.d("MigrationImportTask", "start process , start time = " + this.c);
        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.j.b.b()) { // from class: com.netease.nimlib.j.d.c.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    c.this.a((com.netease.nimlib.j.c.a) aVar);
                } else {
                    c.this.a(aVar.r());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.netease.nimlib.j.c.a aVar) {
        if (this.a) {
            return;
        }
        ArrayList<IHistoryRecord> a = aVar.a();
        if (com.netease.nimlib.o.b.a((Collection) a)) {
            a(MigrationConstant.IMPORT_ERR_RECORD_EMPTY);
            return;
        }
        this.h = (com.netease.nimlib.j.a.a) a.get(0);
        com.netease.nimlib.log.b.d("MigrationImportTask", "after request , total coast time = " + (System.currentTimeMillis() - this.c));
        a(this.h.getUrl());
    }

    private void a(String str) {
        f fVar = new f() { // from class: com.netease.nimlib.j.d.c.2
            long a = 1;

            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(e eVar) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(e eVar) {
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(e eVar) {
                com.netease.nimlib.log.b.d("MigrationImportTask", "after download file  , total coast time = " + (System.currentTimeMillis() - c.this.c));
                c.this.d();
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(e eVar, String str2) {
                c.this.a(MigrationConstant.IMPORT_ERR_DOWN_FILE);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(e eVar, long j) {
                int i = (int) ((j * 100) / this.a);
                c cVar = c.this;
                if (i >= 100) {
                    i = 100;
                }
                cVar.a(i, 3, true);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(e eVar, long j) {
                this.a = j;
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(e eVar, String str2) {
                onFail(eVar, str2);
            }
        };
        if (this.b.exists()) {
            this.b.delete();
        }
        if (com.netease.nimlib.biz.b.e.d().a()) {
            this.g = com.netease.nimlib.biz.b.e.d().a(str, this.b.getPath(), fVar);
        } else {
            this.g = new e(str, this.b.getPath(), fVar);
            g.a().a(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        com.netease.nimlib.j.b.a().a(new Runnable() { // from class: com.netease.nimlib.j.d.c.3
            @Override // java.lang.Runnable
            public void run() {
                c.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.a) {
            return;
        }
        File file = null;
        try {
            file = this.f.decrypt(this.b, this.h.getSecretKey());
        } catch (Exception e) {
            a(e, "decrypt file err", MigrationConstant.IMPORT_ERR_CUSTOM_DECRYPT);
        }
        if (file == null || a(file)) {
            a(new IllegalStateException("decrypt err , file not exist or len is 0"), "decrypt file err", MigrationConstant.IMPORT_ERR_CUSTOM_DECRYPT);
            return;
        }
        this.e.add(file);
        com.netease.nimlib.log.b.d("MigrationImportTask", "after decrypt , total coast time = " + (System.currentTimeMillis() - this.c) + ", origin file len = " + this.b.length() + " , decrypt len =" + file.length());
        try {
            file = this.f.unzip(file);
        } catch (Exception e2) {
            a(e2, "unzip file err", MigrationConstant.IMPORT_ERR_CUSTOM_UNZIP);
        }
        if (a(file)) {
            a(new IllegalStateException("unzip err , file not exist or len is 0  "), "unzip file err", MigrationConstant.IMPORT_ERR_CUSTOM_UNZIP);
            return;
        }
        com.netease.nimlib.log.b.d("MigrationImportTask", "after unzip , total coast time = " + (System.currentTimeMillis() - this.c) + " , unzip len =" + file.length());
        this.e.add(file);
        try {
            b(file);
        } catch (Exception e3) {
            a(e3, "read file or save db err", MigrationConstant.IMPORT_ERR_FILE_FORMAT);
        }
    }

    private void b(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    String readLine = bufferedReader.readLine();
                    com.netease.nimlib.log.b.d("MigrationImportTask", "after parse index , total coast time = " + (System.currentTimeMillis() - this.c) + ", index info = " + readLine);
                    if (TextUtils.isEmpty(readLine)) {
                        a(MigrationConstant.IMPORT_ERR_FILE_FORMAT);
                        bufferedReader.close();
                        inputStreamReader.close();
                        fileInputStream.close();
                        return;
                    }
                    com.netease.nimlib.j.a.b bVar = new com.netease.nimlib.j.a.b(readLine);
                    if (bVar.a()) {
                        a(MigrationConstant.IMPORT_ERR_FILE_FORMAT);
                        bufferedReader.close();
                        inputStreamReader.close();
                        fileInputStream.close();
                        return;
                    }
                    int c = bVar.c();
                    ArrayList<IMMessageImpl> arrayList = new ArrayList<>(100);
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = bufferedReader.readLine();
                        if (readLine2 == null) {
                            if (!arrayList.isEmpty()) {
                                i += a(arrayList);
                                a(c, i2, i3);
                            }
                            if (i2 == c) {
                                a(200);
                            } else {
                                a(MigrationConstant.IMPORT_ERR_PART_SUCCESS);
                            }
                            com.netease.nimlib.log.b.d("MigrationImportTask", "process file done , total coast time = " + (System.currentTimeMillis() - this.c) + ", totalCount = " + c + " , reallyCount = " + i2 + " ,  saveCount = " + i);
                            bufferedReader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                            return;
                        }
                        if (this.a) {
                            bufferedReader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                            return;
                        } else {
                            IMMessageImpl a = com.netease.nimlib.j.a.a(readLine2);
                            if (a != null) {
                                i2++;
                                arrayList.add(a);
                            }
                            if (arrayList.size() >= 100) {
                                i += a(arrayList);
                                i3 = a(c, i2, i3);
                            }
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th2;
            }
        }
    }

    private int a(ArrayList<IMMessageImpl> arrayList) {
        Iterator<IMMessageImpl> it = arrayList.iterator();
        while (it.hasNext()) {
            if (MsgDBHelper.queryMessageIdByUuid(it.next().getUuid()) != 0) {
                it.remove();
            }
        }
        int size = arrayList.size();
        MsgDBHelper.saveMessages(arrayList);
        arrayList.clear();
        return size;
    }

    private int a(int i, int i2, int i3) {
        int i4 = (i2 * 100) / i;
        if (i4 - i3 > 5 || i4 >= 100) {
            i3 = i4 < 100 ? i4 : 100;
            a(i3, 4, false);
        }
        return i3;
    }
}
