package com.netease.nimlib.j.d;

import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.i.k;
import com.netease.nimlib.net.a.b.a;
import com.netease.nimlib.sdk.migration.model.MigrationConstant;
import com.netease.nimlib.sdk.migration.processor.IMsgExportProcessor;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: MigrationExportTask.java */
/* loaded from: classes.dex */
public class b extends a {
    private final String f;
    private IMsgExportProcessor g;
    private HashMap<String, Object> h;
    private String i;
    private a.c j;

    public b(IMsgExportProcessor iMsgExportProcessor, k kVar, HashMap<String, Object> hashMap, String str, boolean z) {
        super(kVar, iMsgExportProcessor, "MigrationExportTask", z);
        this.f = "MigrationExportTask";
        this.g = iMsgExportProcessor;
        this.h = hashMap;
        this.i = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a) {
            return;
        }
        int countAllMessage = MsgDBHelper.countAllMessage();
        if (countAllMessage <= 0) {
            a(-100);
            return;
        }
        try {
            b(countAllMessage);
        } catch (Exception e) {
            a(e, "process un know  err", -1);
        }
    }

    @Override // com.netease.nimlib.j.d.a
    public void a() {
        super.a();
        if (this.j != null) {
            com.netease.nimlib.net.a.b.a.a().a(this.j);
            this.j = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00cf, code lost:
    
        r13 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r18) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.j.d.b.b(int):void");
    }

    private int a(ArrayList<IMMessage> arrayList, BufferedWriter bufferedWriter) throws IOException {
        int i = 0;
        try {
            Iterator<IMMessage> it = arrayList.iterator();
            while (it.hasNext()) {
                String a = com.netease.nimlib.j.a.a(it.next());
                if (!TextUtils.isEmpty(a)) {
                    bufferedWriter.write(a);
                    bufferedWriter.newLine();
                    i++;
                }
            }
            return i;
        } catch (IOException e) {
            a(e, "write message to file err", MigrationConstant.EXPORT_ERR_LOCAL_FORMAT);
            throw e;
        }
    }

    private void a(int i, File file, File file2) throws Exception {
        String b = new com.netease.nimlib.j.a.b(i).b();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                            try {
                                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                                try {
                                    bufferedWriter.write(b);
                                    bufferedWriter.newLine();
                                    while (true) {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine == null) {
                                            bufferedWriter.flush();
                                            com.netease.nimlib.log.b.d("MigrationExportTask", "after append index info , coast total time  =  " + (System.currentTimeMillis() - this.c) + " , index info = " + b);
                                            outputStreamWriter.close();
                                            fileOutputStream.close();
                                            bufferedReader.close();
                                            inputStreamReader.close();
                                            fileInputStream.close();
                                            return;
                                        }
                                        bufferedWriter.write(readLine);
                                        bufferedWriter.newLine();
                                    }
                                } finally {
                                }
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            a(e, "append index info err ", MigrationConstant.EXPORT_ERR_LOCAL_FORMAT);
            throw e;
        }
    }

    private File b(File file) throws Exception {
        try {
            if (this.a) {
                return null;
            }
            File zip = this.g.zip(file);
            if (a(zip)) {
                throw new IllegalStateException("zip file err ,  file not exist or len is 0 ");
            }
            com.netease.nimlib.log.b.d("MigrationExportTask", "after zip , coast total time  =  " + (System.currentTimeMillis() - this.c) + " , origin len = " + file.length() + " , zip len = " + zip.length());
            this.e.add(zip);
            return zip;
        } catch (Exception e) {
            a(e, "custom zip file err", MigrationConstant.EXPORT_ERR_USER_CUSTOM_ZIP);
            throw e;
        }
    }

    private File c(File file) throws Exception {
        try {
            if (this.a) {
                return null;
            }
            File encrypt = this.g.encrypt(file);
            if (a(encrypt)) {
                throw new IllegalStateException("encrypt file err , file not exist or len is 0 ");
            }
            com.netease.nimlib.log.b.d("MigrationExportTask", "after encrypt , coast total time  =  " + (System.currentTimeMillis() - this.c) + ", encrypt len = " + encrypt.length());
            this.e.add(encrypt);
            return encrypt;
        } catch (Exception e) {
            a(e, "custom zip file err", MigrationConstant.EXPORT_ERR_USER_CUSTOM_ENCRYPT);
            throw e;
        }
    }

    private void d(File file) {
        if (this.a) {
            return;
        }
        this.j = com.netease.nimlib.net.a.b.a.a().a(file.getAbsolutePath(), null, this.d, NimNosSceneKeyConstant.NIM_SYSTEM_NOS_SCENE, true, new com.netease.nimlib.net.a.b.c() { // from class: com.netease.nimlib.j.d.b.1
            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj) {
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, long j, long j2) {
                int i = (int) ((j * 100) / j2);
                if (i >= 95) {
                    i = 95;
                }
                b.this.a(i, 2, false);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, String str) {
                com.netease.nimlib.log.b.d("MigrationExportTask", "after upload file  , coast total time  =  " + (System.currentTimeMillis() - b.this.c));
                b.this.a(str);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj, int i, String str) {
                b.this.a(MigrationConstant.EXPORT_ERR_UPLOAD_FILE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.a) {
            return;
        }
        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.j.b.a(this.g.secretKey(), str, this.h, this.i)) { // from class: com.netease.nimlib.j.d.b.2
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                com.netease.nimlib.log.b.d("MigrationExportTask", "[final] after send request  , coast total time  =  " + (System.currentTimeMillis() - b.this.c) + " , result = " + ((int) aVar.r()));
                b.this.a(aVar.r());
            }
        });
    }

    private ArrayList<IMMessage> a(ArrayList<IMMessage> arrayList) {
        Iterator<IMMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            IMMessageImpl iMMessageImpl = (IMMessageImpl) it.next();
            int value = iMMessageImpl.getMsgType().getValue();
            if (iMMessageImpl.getStatus() != MsgStatusEnum.success || (value != 0 && value != 1 && value != 2 && value != 3 && value != 6 && value != 100)) {
                it.remove();
            }
        }
        return arrayList;
    }
}
