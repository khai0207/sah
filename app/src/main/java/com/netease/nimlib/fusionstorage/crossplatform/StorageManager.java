package com.netease.nimlib.fusionstorage.crossplatform;

import android.text.TextUtils;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Credential;
import com.netease.nimlib.fusionstorage.crossplatform.defines.DownloadParameter;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Policy;
import com.netease.nimlib.fusionstorage.crossplatform.defines.StorageSettings;
import com.netease.nimlib.fusionstorage.crossplatform.defines.ThumbSize;
import com.netease.nimlib.fusionstorage.crossplatform.defines.ThumbUserSettings;
import com.netease.nimlib.fusionstorage.crossplatform.defines.UploadParameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class StorageManager {
    private static boolean c = false;
    private static final Map<String, StorageManager> d;
    private static boolean e;
    public final String a;
    private final String b;
    private long f;
    private final ScheduledExecutorService g = Executors.newSingleThreadScheduledExecutor();
    private final Map<Long, ScheduledFuture<?>> h = new ConcurrentHashMap();

    private native void disposeNative(long j);

    private native DownloadParameter getDownloadURLNative(long j, String str, int i);

    private native long initNative(StorageSettings storageSettings);

    private native void notifyCheckState(long j);

    private native void notifyFailureNative(long j, int i);

    private native UploadParameter pickUpCredentialNative(long j, String str);

    private native void setListenerNative(long j, IStorageListener iStorageListener);

    private native void setTimerProviderNative(long j, ITimerProvider iTimerProvider);

    private native void setTimestampProviderNative(long j, ITimestampProvider iTimestampProvider);

    /* JADX INFO: Access modifiers changed from: private */
    public native void triggerTimerNative(long j, long j2, long j3);

    private native void updateCredentialNative(long j, int i, String str, List<Credential> list, boolean z);

    private native void updatePolicyNative(long j, List<Policy> list, int i, int i2, long j2, boolean z);

    static {
        try {
            System.loadLibrary("fusionstorage");
            c = true;
        } catch (UnsatisfiedLinkError e2) {
            com.netease.nimlib.log.b.f("StorageManager", "Failed to load native library: " + e2.getMessage());
        }
        d = new HashMap();
        e = false;
    }

    public static synchronized StorageManager a(String str) {
        StorageManager a;
        synchronized (StorageManager.class) {
            a = a(str, false);
        }
        return a;
    }

    public static synchronized StorageManager a(String str, boolean z) {
        StorageManager storageManager;
        synchronized (StorageManager.class) {
            if (!e) {
                str = "SINGLE_INSTANCE_KEY";
            }
            storageManager = d.get(str);
            if (storageManager == null && z) {
                storageManager = new StorageManager(str, new StorageSettings(new ThumbUserSettings(new ThumbSize(com.netease.nimlib.c.i().thumbnailSize, com.netease.nimlib.c.i().thumbnailSize), null, null, null), null, new ArrayList(com.netease.nimlib.c.C().getNosTokenScene().keySet())));
                d.put(str, storageManager);
            }
        }
        return storageManager;
    }

    private StorageManager(String str, StorageSettings storageSettings) {
        this.a = str;
        if (TextUtils.equals("SINGLE_INSTANCE_KEY", str)) {
            this.b = "StorageManager";
        } else {
            this.b = "StorageManager_" + str;
        }
        com.netease.nimlib.log.b.d(this.b, String.format("constructor %s %s: %s", Boolean.valueOf(c), Long.valueOf(this.f), storageSettings));
        if (c) {
            long initNative = initNative(storageSettings);
            this.f = initNative;
            if (initNative != 0) {
                setTimerProviderNative(initNative, new ITimerProvider() { // from class: com.netease.nimlib.fusionstorage.crossplatform.StorageManager.1
                    public void onTimer(final long j, final int i, final long j2) {
                        com.netease.nimlib.log.b.d(StorageManager.this.b, String.format("onTimer: %s %s %s", Long.valueOf(j), Integer.valueOf(i), Long.valueOf(j2)));
                        if (i > 0) {
                            StorageManager.this.h.put(Long.valueOf(j), StorageManager.this.g.schedule(new Runnable() { // from class: com.netease.nimlib.fusionstorage.crossplatform.StorageManager.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    com.netease.nimlib.log.b.d(StorageManager.this.b, String.format("onTimer run: %s %s %s", Long.valueOf(j), Integer.valueOf(i), Long.valueOf(j2)));
                                    StorageManager.this.h.remove(Long.valueOf(j));
                                    StorageManager.this.triggerTimerNative(StorageManager.this.f, j, j2);
                                }
                            }, i, TimeUnit.MILLISECONDS));
                        }
                    }

                    public void onRemoveTimer(long j) {
                        ScheduledFuture scheduledFuture = (ScheduledFuture) StorageManager.this.h.remove(Long.valueOf(j));
                        com.netease.nimlib.log.b.d(StorageManager.this.b, String.format("onRemoveTimer: %s %s", Long.valueOf(j), scheduledFuture));
                        if (scheduledFuture != null) {
                            scheduledFuture.cancel(true);
                        }
                    }
                });
                setTimestampProviderNative(this.f, new ITimestampProvider() { // from class: com.netease.nimlib.fusionstorage.crossplatform.StorageManager.2
                    public long getTimestamp() {
                        long a = com.netease.nimlib.n.f.a.a(true);
                        com.netease.nimlib.log.b.d(StorageManager.this.b, String.format("getTimestamp: %s", Long.valueOf(a)));
                        return a;
                    }
                });
            }
        }
    }

    protected void finalize() throws Throwable {
        com.netease.nimlib.log.b.d(this.b, String.format("finalize %s %s", Boolean.valueOf(c), Long.valueOf(this.f)));
        a();
        super.finalize();
    }

    public void a() {
        com.netease.nimlib.log.b.d(this.b, String.format("dispose %s %s", Boolean.valueOf(c), Long.valueOf(this.f)));
        if (c) {
            disposeNative(this.f);
            this.f = 0L;
        }
    }

    public void a(IStorageListener iStorageListener) {
        com.netease.nimlib.log.b.d(this.b, String.format("setListener %s %s: %s", Boolean.valueOf(c), Long.valueOf(this.f), iStorageListener));
        if (c) {
            long j = this.f;
            if (j != 0) {
                setListenerNative(j, iStorageListener);
            }
        }
    }

    public void a(List<Policy> list, int i, int i2, long j, boolean z) {
        com.netease.nimlib.log.b.d(this.b, String.format("updatePolicy %s %s: %s %s %s %s %s", Boolean.valueOf(c), Long.valueOf(this.f), list, Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Boolean.valueOf(z)));
        if (c) {
            long j2 = this.f;
            if (j2 != 0) {
                updatePolicyNative(j2, list, i, i2, j, z);
            }
        }
    }

    public void a(int i, String str, List<Credential> list, boolean z) {
        com.netease.nimlib.log.b.d(this.b, String.format("updateCredential %s %s: %s %s %s %s", Boolean.valueOf(c), Long.valueOf(this.f), Integer.valueOf(i), str, list, Boolean.valueOf(z)));
        if (c) {
            long j = this.f;
            if (j != 0) {
                updateCredentialNative(j, i, str, list, z);
            }
        }
    }

    public UploadParameter b(String str) {
        com.netease.nimlib.log.b.d(this.b, String.format("pickUpCredential %s %s: %s", Boolean.valueOf(c), Long.valueOf(this.f), str));
        if (!c) {
            return null;
        }
        long j = this.f;
        if (j == 0) {
            return null;
        }
        return pickUpCredentialNative(j, str);
    }

    public DownloadParameter a(String str, int i) {
        com.netease.nimlib.log.b.d(this.b, String.format("getDownloadUrl %s %s: %s %s", Boolean.valueOf(c), Long.valueOf(this.f), str, Integer.valueOf(i)));
        if (!c) {
            return null;
        }
        long j = this.f;
        if (j == 0) {
            return null;
        }
        return getDownloadURLNative(j, str, i);
    }

    public void a(int i) {
        com.netease.nimlib.log.b.d(this.b, String.format("notifyFailure %s %s: %s", Boolean.valueOf(c), Long.valueOf(this.f), Integer.valueOf(i)));
        if (c) {
            long j = this.f;
            if (j == 0) {
                return;
            }
            notifyFailureNative(j, i);
        }
    }

    public void b() {
        com.netease.nimlib.log.b.d(this.b, String.format("checkState %s %s", Boolean.valueOf(c), Long.valueOf(this.f)));
        if (c) {
            long j = this.f;
            if (j == 0) {
                return;
            }
            notifyCheckState(j);
        }
    }
}
