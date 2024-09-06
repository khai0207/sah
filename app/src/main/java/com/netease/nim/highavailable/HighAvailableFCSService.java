package com.netease.nim.highavailable;

import com.netease.nim.highavailable.HighAvailableFCSService;
import com.netease.nim.highavailable.enums.HAvailableDownloadAuthType;
import com.netease.nim.highavailable.enums.HAvailableFCSChannelFunID;
import com.netease.nim.highavailable.enums.HAvailableFCSDownloadType;
import com.netease.nim.highavailable.enums.HAvailableFCSErrorCode;
import com.netease.nim.highavailable.enums.HAvailableFCSUploadPluginTag;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class HighAvailableFCSService {
    private static final int SET_UPLOAD_TAG_COUNT = 8;
    private static final String TAG = "HighAvailableFCSService";
    private static final int UPLOAD_TAG_NAME_LENGTH = 65;
    private final int environmentHandle;
    private HighAvailableFCSCallback fcsCallback;
    private boolean isInited;
    private final long nativePtr;
    private final Map<Long, FCSDownloadCallback> downloadCallbackMap = new ConcurrentHashMap();
    private final Map<Long, FCSUploadCallback> uploadCallbackMap = new ConcurrentHashMap();
    private HighAvailableFCSNativeCallback nativeCallback = new AnonymousClass1();

    /* loaded from: classes.dex */
    public enum Scheme {
        kHTTP,
        kHTTPS
    }

    private native long nativeDownload(int i, String str, String str2, int i2, int i3, int i4, int i5);

    private native void nativeInit(int i, String str, String str2, String str3, HighAvailableUploadTag[] highAvailableUploadTagArr, String str4, String str5, String str6, int i2);

    private native void nativeRegisterCallback(int i, HighAvailableFCSNativeCallback highAvailableFCSNativeCallback);

    private native void nativeSetAuthType(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetCustomAuthToken(int i, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetFcsChannelResponse(int i, int i2, int i3, long j, byte[] bArr);

    private native void nativeSetInfo(int i, String str, String str2);

    private native void nativeSetUA(int i, String str, String str2);

    private native void nativeStopDownloadTask(int i, long j);

    private native void nativeStopUploadTask(int i, long j);

    private native void nativeUnInit(int i);

    private native long nativeUpload(int i, String str, String str2, String str3, int i2, String str4);

    protected HighAvailableFCSService(long j, int i) {
        this.isInited = false;
        this.nativePtr = j;
        this.environmentHandle = i;
        this.isInited = false;
    }

    protected long getNativePtr() {
        return this.nativePtr;
    }

    protected int getEnvironmentHandle() {
        return this.environmentHandle;
    }

    /* renamed from: com.netease.nim.highavailable.HighAvailableFCSService$1, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements HighAvailableFCSNativeCallback {
        AnonymousClass1() {
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onInitCallback(boolean z) {
            HighAvailableFCSService.this.isInited = z;
            LogUtils.i(HighAvailableFCSService.TAG, "onInitCallback result = " + z);
            if (HighAvailableFCSService.this.fcsCallback == null) {
                return;
            }
            HighAvailableFCSService.this.fcsCallback.onInitCallback(z);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void fcsChannelRequest(int i, int i2, long j, byte[] bArr) {
            if (bArr == null) {
                LogUtils.i(HighAvailableFCSService.TAG, String.format("fcsChannelRequest funId = %d,code = %d,sn = %d,body = null,Thread.name = %s", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Thread.currentThread().getName()));
            } else {
                LogUtils.i(HighAvailableFCSService.TAG, String.format("fcsChannelRequest funId = %d,code = %d,sn = %d,body.length =%d,Thread.name = %s", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Integer.valueOf(bArr.length), Thread.currentThread().getName()));
            }
            if (HighAvailableFCSService.this.fcsCallback == null) {
                return;
            }
            HighAvailableFCSService.this.fcsCallback.fcsChannelRequest(HAvailableFCSChannelFunID.typeOfValue(i), i2, j, bArr, new FCSChannelResponseCallback() { // from class: com.netease.nim.highavailable.-$$Lambda$HighAvailableFCSService$1$rCZkMISoOO-CwUbg3vQyRRUoygc
                @Override // com.netease.nim.highavailable.FCSChannelResponseCallback
                public final void fcsChannelResponse(int i3, int i4, long j2, byte[] bArr2) {
                    HighAvailableFCSService.AnonymousClass1.this.lambda$fcsChannelRequest$0$HighAvailableFCSService$1(i3, i4, j2, bArr2);
                }
            });
        }

        public /* synthetic */ void lambda$fcsChannelRequest$0$HighAvailableFCSService$1(int i, int i2, long j, byte[] bArr) {
            if (bArr == null) {
                LogUtils.i(HighAvailableFCSService.TAG, String.format("fcsChannelResponse funId = %d,code = %d,sn = %d,body = null", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j)));
            } else {
                LogUtils.i(HighAvailableFCSService.TAG, String.format("fcsChannelResponse funId = %d,code = %d,sn = %d,body.length =%d", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Integer.valueOf(bArr.length)));
            }
            HighAvailableFCSService highAvailableFCSService = HighAvailableFCSService.this;
            highAvailableFCSService.nativeSetFcsChannelResponse(highAvailableFCSService.environmentHandle, i, i2, j, bArr);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void getCustomAuthToken(String str) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("getCustomAuthToken url =%s", str));
            if (HighAvailableFCSService.this.fcsCallback == null) {
                return;
            }
            HighAvailableFCSService.this.fcsCallback.getCustomAuthToken(str, new FCSCustomAuthTokenCallback() { // from class: com.netease.nim.highavailable.-$$Lambda$HighAvailableFCSService$1$ak8l1n7EUWrM1R6sI0rGFCIKRrM
                @Override // com.netease.nim.highavailable.FCSCustomAuthTokenCallback
                public final void onToken(String str2) {
                    HighAvailableFCSService.AnonymousClass1.this.lambda$getCustomAuthToken$1$HighAvailableFCSService$1(str2);
                }
            });
        }

        public /* synthetic */ void lambda$getCustomAuthToken$1$HighAvailableFCSService$1(String str) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("nativeSetCustomAuthToken token =%s", str));
            HighAvailableFCSService highAvailableFCSService = HighAvailableFCSService.this;
            highAvailableFCSService.nativeSetCustomAuthToken(highAvailableFCSService.environmentHandle, str);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onDownloadResult(long j, int i, int i2, String str) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onDownloadResult taskId = %d,resultCode = %d,httpCode = %d,filePath =%s", Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), str));
            FCSDownloadCallback fCSDownloadCallback = (FCSDownloadCallback) HighAvailableFCSService.this.downloadCallbackMap.remove(Long.valueOf(j));
            if (fCSDownloadCallback == null) {
                return;
            }
            fCSDownloadCallback.onDownloadResult(HAvailableFCSErrorCode.typeOfValue(i), i2, str);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onDownloadProgress(long j, long j2, long j3) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onDownloadProgress taskId = %d,downloadSize = %d,fileSize =%d", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
            FCSDownloadCallback fCSDownloadCallback = (FCSDownloadCallback) HighAvailableFCSService.this.downloadCallbackMap.get(Long.valueOf(j));
            if (fCSDownloadCallback == null) {
                return;
            }
            fCSDownloadCallback.onDownloadProgress(j2, j3);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onDownloadSpeed(long j, long j2) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onDownloadSpeed taskId = %d,speed = %d", Long.valueOf(j), Long.valueOf(j2)));
            FCSDownloadCallback fCSDownloadCallback = (FCSDownloadCallback) HighAvailableFCSService.this.downloadCallbackMap.get(Long.valueOf(j));
            if (fCSDownloadCallback == null) {
                return;
            }
            fCSDownloadCallback.onDownloadSpeed(j2);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onUploadResult(long j, int i, int i2, String str) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onUploadResult taskId = %d,resultCode = %d,httpCode = %d,url =%s", Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), str));
            FCSUploadCallback fCSUploadCallback = (FCSUploadCallback) HighAvailableFCSService.this.uploadCallbackMap.remove(Long.valueOf(j));
            if (fCSUploadCallback == null) {
                return;
            }
            fCSUploadCallback.onUploadResult(HAvailableFCSErrorCode.typeOfValue(i), i2, str);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onUploadResume(long j, long j2, long j3, int i) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onUploadResume taskId = %s, transferred = %s, fileSize = %s, uploadPluginTag = %s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf(i)));
            FCSUploadCallback fCSUploadCallback = (FCSUploadCallback) HighAvailableFCSService.this.uploadCallbackMap.get(Long.valueOf(j));
            if (fCSUploadCallback == null) {
                return;
            }
            fCSUploadCallback.onUploadResume(j2, j3, HAvailableFCSUploadPluginTag.typeOfValue(i));
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onUploadProgress(long j, long j2, long j3) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onUploadProgress taskId = %d,uploadSize = %d,fileSize =%d", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
            FCSUploadCallback fCSUploadCallback = (FCSUploadCallback) HighAvailableFCSService.this.uploadCallbackMap.get(Long.valueOf(j));
            if (fCSUploadCallback == null) {
                return;
            }
            fCSUploadCallback.onUploadProgress(j2, j3);
        }

        @Override // com.netease.nim.highavailable.HighAvailableFCSNativeCallback
        public void onUploadSpeed(long j, long j2) {
            LogUtils.i(HighAvailableFCSService.TAG, String.format("onUploadSpeed taskId = %d,speed = %d", Long.valueOf(j), Long.valueOf(j2)));
            FCSUploadCallback fCSUploadCallback = (FCSUploadCallback) HighAvailableFCSService.this.uploadCallbackMap.get(Long.valueOf(j));
            if (fCSUploadCallback == null) {
                return;
            }
            fCSUploadCallback.onUploadSpeed(j2);
        }
    }

    public void init(HighAvailableFCSSettings highAvailableFCSSettings, HighAvailableFCSCallback highAvailableFCSCallback) {
        nativeRegisterCallback(this.environmentHandle, this.nativeCallback);
        initFcs(highAvailableFCSSettings, highAvailableFCSCallback);
    }

    public void unInit() {
        nativeUnInit(this.environmentHandle);
    }

    public void setAuthType(HAvailableDownloadAuthType hAvailableDownloadAuthType) {
        LogUtils.i(TAG, String.format("setAuthType authType = %s", hAvailableDownloadAuthType));
        if (hAvailableDownloadAuthType == null) {
            nativeSetAuthType(this.environmentHandle, HAvailableDownloadAuthType.NULL.getValue());
        } else {
            nativeSetAuthType(this.environmentHandle, hAvailableDownloadAuthType.getValue());
        }
    }

    public void setUA(String str, String str2) {
        LogUtils.i(TAG, String.format("setUA refer = %s,ua = %s", str, str2));
        nativeSetUA(this.environmentHandle, str, str2);
    }

    public void setAppInfo(String str, String str2) {
        LogUtils.i(TAG, String.format("setInfo appkey = %s,account = %s", str, str2));
        nativeSetInfo(this.environmentHandle, str, str2);
    }

    public long download(String str, String str2, int i, HAvailableFCSDownloadType hAvailableFCSDownloadType, int i2, int i3, FCSDownloadCallback fCSDownloadCallback) {
        long nativeDownload = nativeDownload(this.environmentHandle, str, str2, i, hAvailableFCSDownloadType.getValue(), i2, i3);
        this.downloadCallbackMap.put(Long.valueOf(nativeDownload), fCSDownloadCallback);
        return nativeDownload;
    }

    public void stopDownload(long j) {
        if (this.downloadCallbackMap.containsKey(Long.valueOf(j))) {
            nativeStopDownloadTask(this.environmentHandle, j);
            this.downloadCallbackMap.remove(Long.valueOf(j));
        }
    }

    public long upload(String str, String str2, String str3, int i, String str4, boolean z, FCSUploadCallback fCSUploadCallback) {
        long nativeUpload = nativeUpload(this.environmentHandle, str, str2, str3, i, str4);
        this.uploadCallbackMap.put(Long.valueOf(nativeUpload), fCSUploadCallback);
        return nativeUpload;
    }

    public void stopUpload(long j) {
        if (this.uploadCallbackMap.containsKey(Long.valueOf(j))) {
            nativeStopUploadTask(this.environmentHandle, j);
            this.uploadCallbackMap.remove(Long.valueOf(j));
        }
    }

    /* loaded from: classes.dex */
    public static class HighAvailableUploadTag {
        private String name;
        private long ttl;

        public HighAvailableUploadTag(String str, long j) {
            this.name = str;
            this.ttl = j;
        }
    }

    /* loaded from: classes.dex */
    public static class HighAvailableFCSSettings {
        private String caFilePath;
        private List<HighAvailableUploadTag> defaultTags;
        private String nosLbsAddress;
        private String nosLbsBucketName;
        private String nosLbsVersion;
        private Scheme scheme;
        private String storageTempCache;
        private String videoCoverMime;

        public HighAvailableFCSSettings() {
        }

        public HighAvailableFCSSettings(String str, String str2, String str3, List<HighAvailableUploadTag> list, String str4, String str5, String str6, Scheme scheme) {
            this.caFilePath = str;
            this.videoCoverMime = str2;
            this.storageTempCache = str3;
            this.defaultTags = list;
            this.nosLbsAddress = str4;
            this.nosLbsVersion = str5;
            this.nosLbsBucketName = str6;
            this.scheme = scheme;
        }
    }

    private HighAvailableFCSSettings fixSettings(HighAvailableFCSSettings highAvailableFCSSettings) {
        HighAvailableFCSSettings highAvailableFCSSettings2 = new HighAvailableFCSSettings();
        if (highAvailableFCSSettings == null) {
            return highAvailableFCSSettings2;
        }
        highAvailableFCSSettings2.caFilePath = highAvailableFCSSettings.caFilePath;
        highAvailableFCSSettings2.videoCoverMime = highAvailableFCSSettings.videoCoverMime;
        highAvailableFCSSettings2.storageTempCache = highAvailableFCSSettings.storageTempCache;
        highAvailableFCSSettings2.nosLbsAddress = highAvailableFCSSettings.nosLbsAddress;
        highAvailableFCSSettings2.nosLbsVersion = highAvailableFCSSettings.nosLbsVersion;
        highAvailableFCSSettings2.nosLbsBucketName = highAvailableFCSSettings.nosLbsBucketName;
        highAvailableFCSSettings2.scheme = highAvailableFCSSettings.scheme;
        List<HighAvailableUploadTag> list = highAvailableFCSSettings.defaultTags;
        highAvailableFCSSettings2.defaultTags = new LinkedList();
        if (list == null) {
            return highAvailableFCSSettings2;
        }
        for (HighAvailableUploadTag highAvailableUploadTag : list) {
            if (highAvailableUploadTag != null && highAvailableUploadTag.name != null) {
                if (highAvailableUploadTag.name.length() < 65) {
                    highAvailableFCSSettings2.defaultTags.add(new HighAvailableUploadTag(highAvailableUploadTag.name, highAvailableUploadTag.ttl));
                    if (highAvailableFCSSettings2.defaultTags.size() >= 8) {
                        break;
                    }
                } else {
                    throw new IllegalArgumentException(String.format("defaultTag.name %s is too long", highAvailableUploadTag.name));
                }
            }
        }
        return highAvailableFCSSettings2;
    }

    private void initFcs(HighAvailableFCSSettings highAvailableFCSSettings, HighAvailableFCSCallback highAvailableFCSCallback) {
        HighAvailableUploadTag[] highAvailableUploadTagArr;
        this.fcsCallback = highAvailableFCSCallback;
        HighAvailableFCSSettings fixSettings = fixSettings(highAvailableFCSSettings);
        if (fixSettings.defaultTags != null) {
            highAvailableUploadTagArr = new HighAvailableUploadTag[fixSettings.defaultTags.size()];
            fixSettings.defaultTags.toArray(highAvailableUploadTagArr);
        } else {
            highAvailableUploadTagArr = null;
        }
        nativeInit(this.environmentHandle, fixSettings.caFilePath, fixSettings.videoCoverMime, fixSettings.storageTempCache, highAvailableUploadTagArr, fixSettings.nosLbsAddress, fixSettings.nosLbsVersion, fixSettings.nosLbsBucketName, fixSettings.scheme != null ? fixSettings.scheme.ordinal() : -1);
    }
}
