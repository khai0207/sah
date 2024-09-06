package com.netease.nim.highavailable;

import android.content.Context;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import com.netease.nim.highavailable.enums.HAvailableBusinessType;
import com.netease.nimlib.n.b.k;
import com.netease.nimlib.n.e;
import com.netease.nimlib.o.x;

/* loaded from: classes.dex */
public class HighAvailableObject {
    private static final String LIB_NAME = "high_available_android";
    private static final String TAG = "HighAvailableObject";
    private static Context context = null;
    private static HighAvailableExceptionCallback exceptionCallback = null;
    private static boolean isLoadLibSuccess = false;
    private static HighAvailableLogCallback logCallback;
    private static HighAvailableObjectNativeCallback startEnvironmentCallback;
    private static HighAvailableVoidCallback voidCallback;
    private final int environmentHandle;
    private HighAvailableFCSService fcsService;
    private HighAvailableLBSService lbsService;
    private final long nativePtr;
    private HighAvailableNetworkCommunicator networkCommunicator;

    private static native long nativeCreateHAvailableObject(String str, String str2, int i, int i2, int i3);

    private static native void nativeExitHAvailableEnvironment();

    private native long nativeGetBusinessService(int i, int i2);

    private static native long nativeGetHAvailableNetworkCommunicator();

    private static native long nativeGetHAvailableObject(int i);

    private native int nativeGetHandle(long j);

    private native void nativeReleaseBusinessService(int i, int i2);

    private static native void nativeReleaseHAvailableObject(int i);

    private static native void nativeStartHAvailableEnvironment(boolean z, int i, String str, String str2, String str3, boolean z2, HighAvailableObjectNativeCallback highAvailableObjectNativeCallback);

    private native void nativeUpdateBusinessToken(int i, String str);

    static {
        try {
            System.loadLibrary(LIB_NAME);
            isLoadLibSuccess = true;
        } catch (Throwable th) {
            isLoadLibSuccess = false;
            LogUtils.e(TAG, "load library high_available_android failed", th);
            e.a(LIB_NAME, k.kLoad, th.toString(), "load library high_available_android failed");
        }
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static Context getContext() {
        return context;
    }

    public static boolean isLoadLibSuccess() {
        return isLoadLibSuccess;
    }

    public static boolean loadLibrary() {
        boolean b = x.b(LIB_NAME);
        isLoadLibSuccess = b;
        return b;
    }

    protected HighAvailableObject(long j) {
        this.nativePtr = j;
        this.environmentHandle = nativeGetHandle(j);
    }

    private int getEnvironmentHandle() {
        return this.environmentHandle;
    }

    public HighAvailableLBSService getHighAvailableLBSService() {
        long nativeGetBusinessService = nativeGetBusinessService(this.environmentHandle, HAvailableBusinessType.BIZ_LBS.getValue());
        HighAvailableLBSService highAvailableLBSService = this.lbsService;
        if (highAvailableLBSService == null || highAvailableLBSService.getNativePtr() != nativeGetBusinessService) {
            this.lbsService = new HighAvailableLBSService(nativeGetBusinessService, this.environmentHandle);
        }
        return this.lbsService;
    }

    public HighAvailableFCSService getHighAvailableFCSService() {
        long nativeGetBusinessService = nativeGetBusinessService(this.environmentHandle, HAvailableBusinessType.BIZ_FCS.getValue());
        HighAvailableFCSService highAvailableFCSService = this.fcsService;
        if (highAvailableFCSService == null || highAvailableFCSService.getNativePtr() != nativeGetBusinessService) {
            this.fcsService = new HighAvailableFCSService(nativeGetBusinessService, this.environmentHandle);
        }
        return this.fcsService;
    }

    public HighAvailableNetworkCommunicator getHighAvailableNetworkCommunicator() {
        long nativeGetHAvailableNetworkCommunicator = nativeGetHAvailableNetworkCommunicator();
        HighAvailableNetworkCommunicator highAvailableNetworkCommunicator = this.networkCommunicator;
        if (highAvailableNetworkCommunicator == null || highAvailableNetworkCommunicator.getNativePtr() != nativeGetHAvailableNetworkCommunicator) {
            this.networkCommunicator = new HighAvailableNetworkCommunicator(nativeGetHAvailableNetworkCommunicator, this.environmentHandle);
        }
        return this.networkCommunicator;
    }

    public void releaseLBSBusinessService() {
        this.lbsService = null;
        nativeReleaseBusinessService(this.environmentHandle, HAvailableBusinessType.BIZ_LBS.getValue());
    }

    public void releaseFCSBusinessService() {
        this.fcsService = null;
        nativeReleaseBusinessService(this.environmentHandle, HAvailableBusinessType.BIZ_FCS.getValue());
    }

    public void updateAppKey(String str) {
        nativeUpdateBusinessToken(this.environmentHandle, str);
    }

    public void release() {
        nativeReleaseHAvailableObject(getEnvironmentHandle());
    }

    public static void startHighAvailableEnvironment(HighAvailableEnvironmentSettings highAvailableEnvironmentSettings, HighAvailableVoidCallback highAvailableVoidCallback) {
        startHighAvailableEnvironment(highAvailableEnvironmentSettings, null, highAvailableVoidCallback, null);
    }

    public static void startHighAvailableEnvironment(HighAvailableEnvironmentSettings highAvailableEnvironmentSettings, HighAvailableLogCallback highAvailableLogCallback, HighAvailableVoidCallback highAvailableVoidCallback, HighAvailableExceptionCallback highAvailableExceptionCallback) {
        LogUtils.i(TAG, "startHighAvailableEnvironment IN");
        voidCallback = highAvailableVoidCallback;
        logCallback = highAvailableLogCallback;
        exceptionCallback = highAvailableExceptionCallback;
        if (startEnvironmentCallback == null) {
            startEnvironmentCallback = new HighAvailableObjectNativeCallback() { // from class: com.netease.nim.highavailable.HighAvailableObject.1
                @Override // com.netease.nim.highavailable.HighAvailableObjectNativeCallback
                public void onStartEnvironmentCallBack() {
                    LogUtils.i(HighAvailableObject.TAG, "startHighAvailableEnvironment onStartEnvironmentCallBack");
                    if (HighAvailableObject.voidCallback != null) {
                        HighAvailableObject.voidCallback.onCallBack();
                    }
                    HighAvailableVoidCallback unused = HighAvailableObject.voidCallback = null;
                }

                @Override // com.netease.nim.highavailable.HighAvailableObjectNativeCallback
                public void onLog(String str) {
                    if (HighAvailableObject.logCallback != null) {
                        HighAvailableObject.logCallback.onLog(str);
                    }
                }

                @Override // com.netease.nim.highavailable.HighAvailableObjectNativeCallback
                public void onExceptionDataReport(String str) {
                    if (HighAvailableObject.exceptionCallback != null) {
                        HighAvailableObject.exceptionCallback.onExceptionDataReport(str);
                    }
                }
            };
        }
        boolean isCleartextTrafficPermitted = Build.VERSION.SDK_INT >= 28 ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() : true;
        LogUtils.i(TAG, "startHighAvailableEnvironment isSupportHttp = " + isCleartextTrafficPermitted);
        nativeStartHAvailableEnvironment(isCleartextTrafficPermitted, highAvailableEnvironmentSettings.enableHttpDns, highAvailableEnvironmentSettings.businessType, highAvailableEnvironmentSettings.appdataPath, highAvailableEnvironmentSettings.logPath, logCallback != null, startEnvironmentCallback);
        LogUtils.i(TAG, "startHighAvailableEnvironment OUT");
    }

    public static HighAvailableObject createHAvailableObject(HighAvailableObjectSettings highAvailableObjectSettings) {
        return new HighAvailableObject(nativeCreateHAvailableObject(highAvailableObjectSettings.businessToken, highAvailableObjectSettings.businessPublicVersion, highAvailableObjectSettings.internalVersion, highAvailableObjectSettings.protocolVersion, highAvailableObjectSettings.clientType));
    }

    public static void exitHAvailableEnvironment() {
        nativeExitHAvailableEnvironment();
    }
}
