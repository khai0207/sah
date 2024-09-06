package com.netease.yunxin.artemis.ArtemisTask;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Network.a;
import com.netease.yunxin.artemis.Network.c;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/* loaded from: classes.dex */
public class YXArtemisFile extends b {
    private static final String TAG = "YXArtemisFile";
    private final int MAX_FILE_SIZE;
    private final int MIN_FILE_SIZE;
    private String bucket;
    private int fileSize;
    a handlePullTask;
    private String objectName;
    private long startMill;
    private StorageType storageType;
    private String token;
    private String tokenExpireTime;
    private String url;

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    /* loaded from: classes.dex */
    enum StorageType {
        NOS,
        AWS,
        OSS;

        /* JADX INFO: Access modifiers changed from: private */
        public String getStorageTypeString() {
            int i = AnonymousClass2.$SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType[ordinal()];
            return i != 1 ? i != 2 ? i != 3 ? "" : "oss" : "aws" : "nos";
        }
    }

    /* renamed from: com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$2, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType;

        static {
            int[] iArr = new int[StorageType.values().length];
            $SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType = iArr;
            try {
                iArr[StorageType.NOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType[StorageType.AWS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType[StorageType.OSS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a2, code lost:
    
        if (r3 == 1) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a4, code lost:
    
        if (r3 == 2) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a6, code lost:
    
        if (r3 == 3) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a9, code lost:
    
        r14.storageType = com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.StorageType.OSS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ae, code lost:
    
        r14.storageType = com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.StorageType.AWS;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public YXArtemisFile(java.lang.String r15, java.lang.String r16, java.lang.String r17, int r18, org.json.JSONObject r19, java.lang.String r20, java.lang.String r21, int r22, java.lang.String r23, android.content.Context r24, java.lang.String r25, com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback r26) {
        /*
            r14 = this;
            r12 = r14
            r13 = r19
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r10 = r25
            r11 = r26
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0 = 104857600(0x6400000, float:3.6111186E-35)
            r12.MAX_FILE_SIZE = r0
            r1 = 1
            r12.MIN_FILE_SIZE = r1
            com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$1 r2 = new com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$1
            r2.<init>()
            r12.handlePullTask = r2
            java.lang.String r2 = "url"
            java.lang.String r2 = r13.getString(r2)     // Catch: java.lang.Throwable -> Ldb
            r12.url = r2     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r3 = "http"
            boolean r2 = r2.startsWith(r3)     // Catch: java.lang.Throwable -> Ldb
            if (r2 != 0) goto L44
            java.lang.String r2 = "http://"
            java.lang.String r3 = r12.url     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = r2.concat(r3)     // Catch: java.lang.Throwable -> Ldb
            r12.url = r2     // Catch: java.lang.Throwable -> Ldb
        L44:
            java.lang.String r2 = "bucket"
            java.lang.String r2 = r13.getString(r2)     // Catch: java.lang.Throwable -> Ldb
            r12.bucket = r2     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = "objectName"
            java.lang.String r2 = r13.getString(r2)     // Catch: java.lang.Throwable -> Ldb
            r12.objectName = r2     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = "type"
            java.lang.String r2 = r13.optString(r2)     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> Ldb
            r3 = -1
            int r4 = r2.hashCode()     // Catch: java.lang.Throwable -> Ldb
            r5 = 3616(0xe20, float:5.067E-42)
            r6 = 3
            r7 = 2
            if (r4 == r5) goto L97
            r5 = 97021(0x17afd, float:1.35955E-40)
            if (r4 == r5) goto L8d
            r5 = 109266(0x1aad2, float:1.53114E-40)
            if (r4 == r5) goto L83
            r5 = 110351(0x1af0f, float:1.54635E-40)
            if (r4 == r5) goto L79
            goto La0
        L79:
            java.lang.String r4 = "oss"
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> Ldb
            if (r2 == 0) goto La0
            r3 = 3
            goto La0
        L83:
            java.lang.String r4 = "nos"
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> Ldb
            if (r2 == 0) goto La0
            r3 = 0
            goto La0
        L8d:
            java.lang.String r4 = "aws"
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> Ldb
            if (r2 == 0) goto La0
            r3 = 2
            goto La0
        L97:
            java.lang.String r4 = "s3"
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> Ldb
            if (r2 == 0) goto La0
            r3 = 1
        La0:
            if (r3 == 0) goto Lb3
            if (r3 == r1) goto Lae
            if (r3 == r7) goto Lae
            if (r3 == r6) goto La9
            goto Lb7
        La9:
            com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$StorageType r2 = com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.StorageType.OSS     // Catch: java.lang.Throwable -> Ldb
            r12.storageType = r2     // Catch: java.lang.Throwable -> Ldb
            goto Lb7
        Lae:
            com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$StorageType r2 = com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.StorageType.AWS     // Catch: java.lang.Throwable -> Ldb
            r12.storageType = r2     // Catch: java.lang.Throwable -> Ldb
            goto Lb7
        Lb3:
            com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile$StorageType r2 = com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.StorageType.NOS     // Catch: java.lang.Throwable -> Ldb
            r12.storageType = r2     // Catch: java.lang.Throwable -> Ldb
        Lb7:
            java.lang.String r2 = "file_size"
            int r2 = r13.getInt(r2)     // Catch: java.lang.Throwable -> Ldb
            r12.fileSize = r2     // Catch: java.lang.Throwable -> Ldb
            int r1 = java.lang.Math.max(r1, r2)     // Catch: java.lang.Throwable -> Ldb
            r12.fileSize = r1     // Catch: java.lang.Throwable -> Ldb
            int r0 = java.lang.Math.min(r0, r1)     // Catch: java.lang.Throwable -> Ldb
            r12.fileSize = r0     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r0 = "token"
            java.lang.String r0 = r13.getString(r0)     // Catch: java.lang.Throwable -> Ldb
            r12.token = r0     // Catch: java.lang.Throwable -> Ldb
            java.lang.String r0 = "token_expire_time"
            java.lang.String r0 = r13.getString(r0)     // Catch: java.lang.Throwable -> Ldb
            r12.tokenExpireTime = r0     // Catch: java.lang.Throwable -> Ldb
        Ldb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.yunxin.artemis.ArtemisTask.YXArtemisFile.<init>(java.lang.String, java.lang.String, java.lang.String, int, org.json.JSONObject, java.lang.String, java.lang.String, int, java.lang.String, android.content.Context, java.lang.String, com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback):void");
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        this.startMill = System.currentTimeMillis();
        if (AnonymousClass2.$SwitchMap$com$netease$yunxin$artemis$ArtemisTask$YXArtemisFile$StorageType[this.storageType.ordinal()] != 1) {
            return;
        }
        doNosUpload();
    }

    private void doNosUpload() {
        try {
            if (TextUtils.isEmpty(this.url) || TextUtils.isEmpty(this.bucket) || TextUtils.isEmpty(this.objectName) || TextUtils.isEmpty(this.token) || TextUtils.isEmpty(this.tokenExpireTime) || this.fileSize <= 0 || System.currentTimeMillis() > Long.parseLong(this.tokenExpireTime)) {
                return;
            }
            byte[] bArr = new byte[this.fileSize];
            new Random().nextBytes(bArr);
            c cVar = new c(bArr);
            String format = String.format("%s/%s/%s?offset=%s&complete=%s&context=%s&version=1.0", this.url, this.bucket, this.objectName, 0, Boolean.TRUE, null);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("x-nos-token", this.token);
            hashMap.put("Content-Type", Mimetypes.MIMETYPE_OCTET_STREAM);
            hashMap.put(Headers.CONTENT_MD5, com.netease.yunxin.artemis.a.b.a(bArr));
            hashMap.put("Content-Length", String.valueOf(this.fileSize));
            StringBuilder sb = new StringBuilder("nosupload url:");
            sb.append(format);
            sb.append(", headers:");
            sb.append(hashMap);
            sb.append(", logZipBytes:");
            sb.append(Arrays.toString(bArr));
            com.netease.yunxin.artemis.Network.b.a().b(format, cVar, this.handlePullTask, hashMap);
        } catch (Throwable unused) {
        }
    }
}
