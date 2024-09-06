package com.netease.nimlib.fusionstorage.plugin;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netease.nim.highavailable.FCSUploadCallback;
import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nim.highavailable.enums.HAvailableFCSUploadPluginTag;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.auth.BasicSessionCredentials;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferUtilityOptions;
import com.netease.nimlib.amazonaws.regions.Region;
import com.netease.nimlib.amazonaws.services.s3.AmazonS3Client;
import com.netease.nimlib.amazonaws.services.s3.S3ClientOptions;
import com.netease.nimlib.amazonaws.services.s3.model.AmazonS3Exception;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.biz.b.c;
import com.netease.nimlib.biz.b.f;
import com.netease.nimlib.biz.k;
import com.netease.nimlib.fusionstorage.crossplatform.StorageListener;
import com.netease.nimlib.fusionstorage.crossplatform.StorageManager;
import com.netease.nimlib.fusionstorage.crossplatform.d;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Credential;
import com.netease.nimlib.fusionstorage.crossplatform.defines.DownloadParameter;
import com.netease.nimlib.fusionstorage.crossplatform.defines.StorageProvider;
import com.netease.nimlib.fusionstorage.crossplatform.defines.UploadConfig;
import com.netease.nimlib.fusionstorage.crossplatform.defines.UploadParameter;
import com.netease.nimlib.fusionstorage.plugin.defines.PendingResumeTransferIdCacheItem;
import com.netease.nimlib.i.j;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.net.a.a.e;
import com.netease.nimlib.sdk.FcsDownloadAuthStrategy;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.nos.NosService;
import defpackage.C$r8$backportedMethods$utility$String$2$joinArray;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Plugin implements c, com.netease.nimlib.plugin.a {
    private final LruCache<String, TransferUtility> b = new LruCache<>(3);
    private final Map<Integer, WeakReference<TransferUtility>> c = Collections.synchronizedMap(new HashMap());
    private final Map<String, PendingResumeTransferIdCacheItem> d = Collections.synchronizedMap(new HashMap());
    private boolean e = true;
    private final Map<f, b> f = Collections.synchronizedMap(new HashMap());
    private final Gson a = new Gson();

    @Override // com.netease.nimlib.plugin.a
    public MsgAttachment a(int i, JSONObject jSONObject) {
        return null;
    }

    @Override // com.netease.nimlib.plugin.a
    public Map<Class<?>, Class<? extends j>> a() {
        return null;
    }

    @Override // com.netease.nimlib.plugin.a
    public void a(Context context) {
    }

    @Override // com.netease.nimlib.plugin.a
    public Map<Class<? extends com.netease.nimlib.biz.e.a>, com.netease.nimlib.biz.c.a> b() {
        return null;
    }

    @Override // com.netease.nimlib.plugin.a
    public void b(Context context) {
    }

    public Plugin() {
        d();
    }

    private Handler c() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            com.netease.nimlib.log.b.d("Plugin_FCS", String.format("getHandler use myLooper: %s %s", myLooper, Thread.currentThread()));
            return new Handler(myLooper);
        }
        com.netease.nimlib.log.b.d("Plugin_FCS", String.format("getHandler use mainLooper: %s", Thread.currentThread()));
        return new Handler(Looper.getMainLooper());
    }

    private void d() {
        try {
            String string = k.b().getString("pendingResumeTransferIdCache", "");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            Map<? extends String, ? extends PendingResumeTransferIdCacheItem> map = (Map) this.a.fromJson(string, new TypeToken<Map<String, PendingResumeTransferIdCacheItem>>() { // from class: com.netease.nimlib.fusionstorage.plugin.Plugin.1
            }.getType());
            if (map != null) {
                this.d.putAll(map);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("Plugin_FCS", "initPendingResumeTransferIdCache catch Exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            k.b().edit().putString("pendingResumeTransferIdCache", new Gson().toJson(new HashMap(this.d))).apply();
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("Plugin_FCS", "savePendingResumeTransferIdCache catch Exception", e);
        }
    }

    private void f() {
        if (this.e) {
            TransferNetworkLossHandler transferNetworkLossHandler = TransferNetworkLossHandler.getInstance(com.netease.nimlib.c.e());
            try {
                com.netease.nimlib.log.b.d("Plugin_FCS", "Registering the network receiver");
                com.netease.nimlib.c.e().registerReceiver(transferNetworkLossHandler, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.e = false;
            } catch (IllegalArgumentException e) {
                com.netease.nimlib.log.b.e("Plugin_FCS", "Ignoring the exception trying to register the receiver for connectivity change.", e);
            } catch (IllegalStateException e2) {
                com.netease.nimlib.log.b.e("Plugin_FCS", "Ignoring the leak in registering the receiver.", e2);
            } catch (Exception e3) {
                com.netease.nimlib.log.b.e("Plugin_FCS", "registerReceiver transferNetworkLossHandler Exception", e3);
            }
        }
    }

    @Override // com.netease.nimlib.biz.b.c
    public void a(com.netease.nimlib.c.a<Boolean> aVar, String str) {
        f();
        StorageManager a2 = StorageManager.a(str, true);
        StorageListener storageListener = new StorageListener(a2);
        a2.a(storageListener);
        com.netease.nimlib.fusionstorage.crossplatform.c loadPolicyCache = storageListener.loadPolicyCache();
        if (loadPolicyCache != null && loadPolicyCache.e()) {
            a2.a(loadPolicyCache.a(), loadPolicyCache.b(), loadPolicyCache.c(), loadPolicyCache.d(), true);
            if (com.netease.nimlib.c.C() != null) {
                for (Map.Entry<String, Long> entry : com.netease.nimlib.c.C().getNosTokenScene().entrySet()) {
                    String key = entry.getKey();
                    long longValue = entry.getValue().longValue();
                    com.netease.nimlib.fusionstorage.crossplatform.b loadCredentialCache = storageListener.loadCredentialCache(StorageProvider.STORAGE_PROVIDER_AWS_S3.getValue(), key);
                    if (loadCredentialCache != null && loadCredentialCache.b()) {
                        a2.a(StorageProvider.STORAGE_PROVIDER_AWS_S3.getValue(), key, loadCredentialCache.a(), true);
                    } else {
                        d.a(StorageProvider.STORAGE_PROVIDER_AWS_S3.getValue(), Integer.valueOf(loadPolicyCache.c()), key, longValue, new WeakReference(a2), new WeakReference(storageListener));
                    }
                }
            }
        } else {
            d.a(new WeakReference(a2));
        }
        aVar.onCallback(true);
    }

    @Override // com.netease.nimlib.biz.b.c
    public void a(String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar) {
        f();
        String g = com.netease.nimlib.c.g();
        StorageManager a2 = StorageManager.a(g);
        if (a2 == null) {
            com.netease.nimlib.log.b.e("Plugin_FCS", String.format("upload getInstance %s: %s", g, a2));
            fVar.a(-1L);
            if (fCSUploadCallback != null) {
                fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
                return;
            }
            return;
        }
        a(a2, str, str2, str3, i, str4, z, fCSUploadCallback, fVar, 1);
    }

    /* loaded from: classes.dex */
    private class b implements Runnable {
        private final StorageManager b;
        private final String c;
        private final String d;
        private final String e;
        private final int f;
        private final String g;
        private final boolean h;
        private final FCSUploadCallback i;
        private final f j;
        private final int k;
        private final Handler l;

        private b(StorageManager storageManager, String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar, int i2, Handler handler) {
            this.b = storageManager;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = i;
            this.g = str4;
            this.h = z;
            this.i = fCSUploadCallback;
            this.j = fVar;
            this.k = i2;
            this.l = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            Plugin.this.a(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
            Plugin.this.f.remove(this.j);
        }

        public void a() {
            this.l.removeCallbacks(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(StorageManager storageManager, String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback, f fVar, int i2) {
        String str5;
        String str6;
        int i3;
        UploadParameter b2 = storageManager.b(str3);
        if (b2 == null || !b2.isPickedUp() || (b2.getCredential() == null && TextUtils.isEmpty(b2.getDownloadUrl()))) {
            if (i2 >= 4) {
                com.netease.nimlib.log.b.e("Plugin_FCS", String.format("pickUpCredential to skip: %s", b2));
                fVar.a(-1L);
                if (fCSUploadCallback != null) {
                    fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
                    return;
                }
                return;
            }
            com.netease.nimlib.log.b.e("Plugin_FCS", String.format("pickUpCredential to wait: %s", b2));
            Handler c = c();
            b bVar = new b(storageManager, str, str2, str3, i, str4, z, fCSUploadCallback, fVar, i2 + 1, c);
            this.f.put(fVar, bVar);
            c.postDelayed(bVar, 1000L);
            return;
        }
        String a2 = a(str, str2, str3, i, str4);
        Credential credential = b2.getCredential();
        TransferUtility a3 = a(b2);
        if (a3 != null) {
            PendingResumeTransferIdCacheItem pendingResumeTransferIdCacheItem = this.d.get(a2);
            if (pendingResumeTransferIdCacheItem != null) {
                int transferId = pendingResumeTransferIdCacheItem.getTransferId();
                UploadParameter uploadParameter = pendingResumeTransferIdCacheItem.getUploadParameter();
                if (a3.resume(transferId) != null) {
                    fVar.a(a(storageManager, a2, r13, uploadParameter, a3, fCSUploadCallback));
                    return;
                } else {
                    str5 = a2;
                    com.netease.nimlib.log.b.e("Plugin_FCS", "upload resume transferObserver null");
                }
            } else {
                str5 = a2;
            }
            ObjectMetadata objectMetadata = new ObjectMetadata();
            if (!TextUtils.isEmpty(str2)) {
                com.netease.nimlib.log.b.d("Plugin_FCS", String.format("setContentType: %s", str2));
                objectMetadata.setContentType(str2);
            }
            objectMetadata.setHeader("token", credential.getToken());
            try {
                str6 = "Plugin_FCS";
                i3 = 7;
            } catch (Throwable th) {
                th = th;
                str6 = "Plugin_FCS";
                i3 = 7;
            }
            try {
                fVar.a(a(storageManager, str5, a3.upload(credential.getBucketName(), credential.getObjectName(), new File(str), objectMetadata), b2, a3, fCSUploadCallback));
                return;
            } catch (Throwable th2) {
                th = th2;
                Object[] objArr = new Object[i3];
                objArr[0] = str;
                objArr[1] = str2;
                objArr[2] = str3;
                objArr[3] = Integer.valueOf(i);
                objArr[4] = str4;
                objArr[5] = Boolean.valueOf(z);
                objArr[6] = fCSUploadCallback;
                com.netease.nimlib.log.b.e(str6, String.format("upload %s %s %s %s %s %s %s ", objArr), th);
                fVar.a(-1L);
                if (fCSUploadCallback != null) {
                    fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
                    return;
                }
                return;
            }
        }
        com.netease.nimlib.log.b.e("Plugin_FCS", String.format("upload getTransferUtility is null %s %s %s %s %s %s %s ", str, str2, str3, Integer.valueOf(i), str4, Boolean.valueOf(z), fCSUploadCallback));
        fVar.a(-1L);
        if (fCSUploadCallback != null) {
            fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
        }
    }

    /* loaded from: classes.dex */
    private static abstract class a implements TransferListener {
        private final Map<Integer, Exception> a;

        private a() {
            this.a = new HashMap();
        }

        @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferListener
        public void onError(int i, Exception exc) {
            this.a.put(Integer.valueOf(i), exc);
        }

        public Map<Integer, Exception> a() {
            return this.a;
        }
    }

    private int a(final StorageManager storageManager, final String str, final TransferObserver transferObserver, final UploadParameter uploadParameter, TransferUtility transferUtility, final FCSUploadCallback fCSUploadCallback) {
        transferObserver.setTransferListener(new a() { // from class: com.netease.nimlib.fusionstorage.plugin.Plugin.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferListener
            public void onStateChanged(int i, TransferState transferState) {
                com.netease.nimlib.log.b.d("Plugin_FCS", String.format("upload onStateChanged %s %s", Integer.valueOf(i), transferState));
                if (TransferState.COMPLETED.equals(transferState) || TransferState.FAILED.equals(transferState) || TransferState.CANCELED.equals(transferState) || TransferState.PENDING_PAUSE.equals(transferState)) {
                    Plugin.this.c.remove(Integer.valueOf(i));
                }
                if (transferState == TransferState.COMPLETED || transferState == TransferState.CANCELED) {
                    Plugin.this.d.remove(str);
                    Plugin.this.e();
                }
                if (transferState == TransferState.FAILED) {
                    storageManager.a(0);
                }
                if (fCSUploadCallback != null) {
                    if (transferState == TransferState.IN_PROGRESS) {
                        fCSUploadCallback.onUploadResume(transferObserver.getBytesTransferred(), transferObserver.getBytesTotal(), HAvailableFCSUploadPluginTag.kUploadPluginTagAWSS3);
                    }
                    if (transferState == TransferState.COMPLETED) {
                        fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kOK, 200, uploadParameter.getDownloadUrl());
                        return;
                    }
                    if (transferState == TransferState.CANCELED || transferState == TransferState.PENDING_PAUSE) {
                        fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kCancel, 0, uploadParameter.getDownloadUrl());
                        return;
                    }
                    if (transferState == TransferState.FAILED) {
                        Exception exc = a().get(Integer.valueOf(i));
                        if (exc instanceof AmazonS3Exception) {
                            fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, ((AmazonS3Exception) exc).getStatusCode(), "");
                        } else {
                            fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.kError, 0, "");
                        }
                    }
                }
            }

            @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferListener
            public void onProgressChanged(int i, long j, long j2) {
                com.netease.nimlib.log.b.d("Plugin_FCS", String.format("upload onProgressChanged %s %s %s", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2)));
                FCSUploadCallback fCSUploadCallback2 = fCSUploadCallback;
                if (fCSUploadCallback2 != null) {
                    fCSUploadCallback2.onUploadProgress(j, j2);
                }
            }

            @Override // com.netease.nimlib.fusionstorage.plugin.Plugin.a, com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferListener
            public void onError(int i, Exception exc) {
                com.netease.nimlib.log.b.e("Plugin_FCS", String.format("upload onError %s %s", Integer.valueOf(i), exc), exc);
            }
        });
        int id = transferObserver.getId();
        this.c.put(Integer.valueOf(id), new WeakReference<>(transferUtility));
        this.d.put(str, new PendingResumeTransferIdCacheItem(id, uploadParameter));
        e();
        return id;
    }

    private TransferUtility a(Credential credential, boolean z) {
        return this.b.get(b(credential, z));
    }

    private TransferUtility a(UploadParameter uploadParameter) {
        final Credential credential;
        UploadConfig uploadConfig;
        if (uploadParameter == null || (credential = uploadParameter.getCredential()) == null || (uploadConfig = uploadParameter.getUploadConfig()) == null) {
            return null;
        }
        boolean z = uploadParameter.getUploadMode() == 1 && com.netease.nimlib.o.f.d(uploadParameter.getUploadConfig().getEndpoints());
        TransferUtility a2 = a(credential, z);
        if (a2 != null) {
            return a2;
        }
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        if (uploadConfig.getRetryStrategy() != null) {
            clientConfiguration.setMaxErrorRetry(uploadConfig.getRetryStrategy().getRetryCount());
        }
        AmazonS3Client amazonS3Client = new AmazonS3Client(new AWSCredentialsProvider() { // from class: com.netease.nimlib.fusionstorage.plugin.Plugin.3
            @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
            public void refresh() {
            }

            @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
            public AWSCredentials getCredentials() {
                return new BasicSessionCredentials(credential.getAccessKeyId(), credential.getSecretAccessKey(), credential.getSessionToken());
            }
        }, Region.getRegion(credential.getRegion()), clientConfiguration);
        if (z) {
            amazonS3Client.setEndpoint(uploadParameter.getUploadConfig().getEndpoints().get(0));
            amazonS3Client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).setPayloadSigningEnabled(false).build());
        } else {
            amazonS3Client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(false).setPayloadSigningEnabled(true).build());
        }
        TransferUtilityOptions transferUtilityOptions = new TransferUtilityOptions();
        transferUtilityOptions.setMinimumUploadPartSizeInMB(5);
        TransferUtility build = TransferUtility.builder().context(com.netease.nimlib.c.e()).s3Client(amazonS3Client).transferUtilityOptions(transferUtilityOptions).build();
        this.b.put(b(credential, z), build);
        return build;
    }

    @Override // com.netease.nimlib.biz.b.c
    public void a(f fVar) {
        TransferUtility transferUtility;
        if (fVar == null) {
            com.netease.nimlib.log.b.e("Plugin_FCS", "cancelUploadTask skip as null");
            return;
        }
        b remove = this.f.remove(fVar);
        if (remove != null) {
            remove.a();
        }
        int a2 = (int) fVar.a();
        WeakReference<TransferUtility> weakReference = this.c.get(Integer.valueOf(a2));
        if (weakReference == null || (transferUtility = weakReference.get()) == null) {
            return;
        }
        transferUtility.cancel(a2);
    }

    @Override // com.netease.nimlib.biz.b.c
    public void a(final HAvailableFCSDownloadType hAvailableFCSDownloadType, final int i, final int i2, final com.netease.nimlib.biz.b.d dVar) {
        if (dVar == null) {
            com.netease.nimlib.log.b.e("Plugin_FCS", String.format("download downloadInfo %s", dVar));
            return;
        }
        final com.netease.nimlib.net.a.a.f l = dVar.l();
        com.netease.nimlib.log.b.d("Plugin_FCS", String.format("download downloadListener %s", l));
        String g = com.netease.nimlib.c.g();
        final StorageManager a2 = StorageManager.a(g);
        if (a2 == null) {
            com.netease.nimlib.log.b.e("Plugin_FCS", String.format("download getInstance %s: %s", g, a2));
            if (l != null) {
                l.onFail(dVar, String.format("getInstance %s null", g));
                return;
            }
            return;
        }
        ((NosService) NIMClient.getService(NosService.class)).getOriginUrlFromShortUrl(dVar.f()).setCallback(new RequestCallback<String>() { // from class: com.netease.nimlib.fusionstorage.plugin.Plugin.4
            @Override // com.netease.nimlib.sdk.RequestCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                Plugin.b(a2, hAvailableFCSDownloadType, i, i2, dVar, str);
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i3) {
                com.netease.nimlib.log.b.f("Plugin_FCS", String.format("onFailed %s %s", Integer.valueOf(i3), l));
                com.netease.nimlib.net.a.a.f fVar = l;
                if (fVar != null) {
                    fVar.onFail(dVar, String.format("onFailed %s", Integer.valueOf(i3)));
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
                com.netease.nimlib.log.b.e("Plugin_FCS", String.format("onException %s", l), th);
                com.netease.nimlib.net.a.a.f fVar = l;
                if (fVar != null) {
                    fVar.onFail(dVar, String.format("onException %s", th));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final StorageManager storageManager, HAvailableFCSDownloadType hAvailableFCSDownloadType, int i, int i2, com.netease.nimlib.biz.b.d dVar, String str) {
        Pair<String, String> authRefer;
        DownloadParameter a2 = storageManager.a(str, com.netease.nimlib.fusionstorage.crossplatform.a.a(hAvailableFCSDownloadType));
        if (a2 == null) {
            com.netease.nimlib.net.a.a.f l = dVar.l();
            if (l != null) {
                l.onFail(dVar, "GetDownloadURL null");
                return;
            }
            return;
        }
        if (com.netease.nimlib.log.b.a()) {
            com.netease.nimlib.log.b.c("Plugin_FCS", String.format("downloadAfterGetOriginUrl %s", a2));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("appKey", com.netease.nimlib.c.g());
        hashMap.put("uid", com.netease.nimlib.c.n());
        if (a2.needAuth()) {
            int type = a2.getType();
            if (type == 1) {
                FcsDownloadAuthStrategy E = com.netease.nimlib.c.E();
                if (E != null && (authRefer = E.getAuthRefer()) != null) {
                    hashMap.put("nim-mixstore-refer", authRefer.first);
                    hashMap.put("nim-mixstore-ua", authRefer.second);
                }
            } else if (type != 2 && type != 3 && type != 4) {
                com.netease.nimlib.log.b.e("Plugin_FCS", String.format("download %s", Integer.valueOf(a2.getType())));
            }
        }
        final com.netease.nimlib.net.a.a.f l2 = dVar.l();
        dVar.a(a2.getDownloadUrl(), false, hashMap, a2.getDownloadRetryCount(), a2.getDownloadRetryInterval(), new com.netease.nimlib.net.a.a.f() { // from class: com.netease.nimlib.fusionstorage.plugin.Plugin.5
            @Override // com.netease.nimlib.net.a.a.f
            public void onStart(e eVar) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onStart(eVar);
                }
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onOK(e eVar) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onOK(eVar);
                }
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onFail(e eVar, String str2) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onFail(eVar, str2);
                }
                storageManager.a(1);
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onCancel(e eVar) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onCancel(eVar);
                }
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onProgress(e eVar, long j) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onProgress(eVar, j);
                }
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onGetLength(e eVar, long j) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onGetLength(eVar, j);
                }
            }

            @Override // com.netease.nimlib.net.a.a.f
            public void onExpire(e eVar, String str2) {
                com.netease.nimlib.net.a.a.f fVar = com.netease.nimlib.net.a.a.f.this;
                if (fVar != null) {
                    fVar.onExpire(eVar, str2);
                }
            }
        }, g.FCS_NEXT);
        com.netease.nimlib.net.a.a.g.a().a(dVar);
    }

    @Override // com.netease.nimlib.plugin.a
    public void c(Context context) {
        StorageManager a2 = StorageManager.a(com.netease.nimlib.c.g(), false);
        if (a2 == null) {
            return;
        }
        a2.b();
    }

    @Override // com.netease.nimlib.plugin.a
    public void d(Context context) {
        Iterator it = new HashMap(this.f).entrySet().iterator();
        while (it.hasNext()) {
            ((b) ((Map.Entry) it.next()).getValue()).a();
        }
        this.f.clear();
    }

    private static String a(String str, String str2, String str3, int i, String str4) {
        return C$r8$backportedMethods$utility$String$2$joinArray.join("#", new CharSequence[]{str, str2, str3, String.valueOf(i), str4});
    }

    private static String b(Credential credential, boolean z) {
        return credential == null ? "" : C$r8$backportedMethods$utility$String$2$joinArray.join("#", new CharSequence[]{credential.getAccessKeyId(), credential.getSecretAccessKey(), credential.getSessionToken(), String.valueOf(z)});
    }
}
