package com.netease.nim.highavailable;

import android.text.TextUtils;
import com.netease.nim.highavailable.enums.HAvailableAuthState;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class HighAvailableLBSService {
    private static final int LBS_ADDR_LENGTH = 512;
    private static final int LBS_BACKUP_ADDR_COUNT = 5;
    private static final int LINK_ADDR_COUNT = 10;
    private static final int LINK_ADDR_LENGTH = 128;
    private static final int LINK_IPV6_ADDR_LENGTH = 128;
    private static final String TAG = "HighAvailableLBSService";
    private final int environmentHandle;
    private HighAvailableLBSLinkAddressCb getLinkAddressCallback;
    private HighAvailableLBSLinkAddressNativeCb getLinkAddressNativeCallback;
    private boolean isInited;
    private HighAvailableLBSCallback lbsCallback;
    private final HighAvailableLBSNativeCallback nativeCallback = new HighAvailableLBSNativeCallback() { // from class: com.netease.nim.highavailable.HighAvailableLBSService.1
        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public void onInitCallback(boolean z) {
            LogUtils.i(HighAvailableLBSService.TAG, "onInitCallback result " + z);
            HighAvailableLBSService.this.isInited = z;
            if (HighAvailableLBSService.this.lbsCallback != null) {
                HighAvailableLBSService.this.lbsCallback.onInitCallback(z);
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public String getAccid() {
            String accid = HighAvailableLBSService.this.lbsCallback != null ? HighAvailableLBSService.this.lbsCallback.getAccid() : "";
            LogUtils.i(HighAvailableLBSService.TAG, "getAccid = " + accid);
            return accid;
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public void onUpdate(int i, String str) {
            LogUtils.i(HighAvailableLBSService.TAG, String.format("onUpdate code = %d,response = %s", Integer.valueOf(i), str));
            if (HighAvailableLBSService.this.lbsCallback != null) {
                HighAvailableLBSService.this.lbsCallback.onUpdate(i, str);
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public void onRequestError(int i, String str, String str2, String str3, long j, long j2) {
            LogUtils.i(HighAvailableLBSService.TAG, String.format("onRequestError errorCode = %d,url = %s", Integer.valueOf(i), str));
            if (HighAvailableLBSService.this.lbsCallback != null) {
                HighAvailableLBSService.this.lbsCallback.onRequestError(i, str, str2, str3, j, j2);
            }
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public int getAuthState() {
            HAvailableAuthState hAvailableAuthState = HAvailableAuthState.kAuthState_UnLogin;
            if (HighAvailableLBSService.this.lbsCallback != null) {
                hAvailableAuthState = HighAvailableLBSService.this.lbsCallback.getAuthState();
            }
            LogUtils.i(HighAvailableLBSService.TAG, "getAuthState = " + hAvailableAuthState);
            return hAvailableAuthState.ordinal();
        }

        @Override // com.netease.nim.highavailable.HighAvailableLBSNativeCallback
        public void onSingleRequestTrackerReport(String str) {
            LogUtils.i(HighAvailableLBSService.TAG, "onSingleRequestTrackerReport json = " + str);
            if (HighAvailableLBSService.this.lbsCallback != null) {
                HighAvailableLBSService.this.lbsCallback.onSingleRequestTrackerReport(str);
            }
        }
    };
    private final long nativePtr;

    private native boolean nativeGetCurrentLinkAddress(int i, HighAvailableLinkAddress highAvailableLinkAddress);

    private native String nativeGetDesc(int i);

    private native String nativeGetLBSResponse(int i, boolean z);

    private native boolean nativeGetLinkAddress(int i, int i2, HighAvailableLBSLinkAddressNativeCb highAvailableLBSLinkAddressNativeCb);

    private native int nativeGetLinkCount(int i);

    private native void nativeGetMessageDelayStaticsInfo(int i, MessageDelayStaticsInfo messageDelayStaticsInfo);

    private native String nativeGetNOSDL(int i);

    private native String[] nativeGetNOSDLList(int i);

    private native String[] nativeGetNOSList(int i);

    private native String nativeGetName(int i);

    private native void nativeInit(int i, boolean z, String str, String[] strArr, String str2, String str3, String[] strArr2, int i2, int i3);

    private native void nativeNotifyAddressSucceed(int i, String str);

    private native void nativeRegisterCallback(int i, HighAvailableLBSNativeCallback highAvailableLBSNativeCallback);

    private native void nativeUpdate(int i, int i2);

    protected HighAvailableLBSService(long j, int i) {
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

    public boolean isInit() {
        return this.isInited;
    }

    public void init(HighAvailableLBSSettings highAvailableLBSSettings, HighAvailableLBSCallback highAvailableLBSCallback) {
        LogUtils.i(TAG, "init IN ");
        nativeRegisterCallback(this.environmentHandle, this.nativeCallback);
        initLbs(highAvailableLBSSettings, highAvailableLBSCallback);
        LogUtils.i(TAG, "init OUT ");
    }

    public void update(AddressFamily addressFamily) {
        LogUtils.i(TAG, "update IN ");
        nativeUpdate(this.environmentHandle, addressFamily.ordinal());
        LogUtils.i(TAG, "update OUT ");
    }

    public String getLBSResponse(boolean z) {
        String nativeGetLBSResponse = nativeGetLBSResponse(this.environmentHandle, z);
        LogUtils.i(TAG, String.format("nativeGetLBSResponse = %s", nativeGetLBSResponse));
        return nativeGetLBSResponse;
    }

    public boolean getLinkAddress(AddressFamily addressFamily, HighAvailableLBSLinkAddressCb highAvailableLBSLinkAddressCb) {
        LogUtils.i(TAG, "getLinkAddress IN ");
        this.getLinkAddressCallback = highAvailableLBSLinkAddressCb;
        if (this.getLinkAddressNativeCallback == null) {
            this.getLinkAddressNativeCallback = new HighAvailableLBSLinkAddressNativeCb() { // from class: com.netease.nim.highavailable.HighAvailableLBSService.2
                @Override // com.netease.nim.highavailable.HighAvailableLBSLinkAddressNativeCb
                public void onGetLinkAddressCallBack(String str, String str2, int i, int i2) {
                    LogUtils.i(HighAvailableLBSService.TAG, String.format("getLinkAddress onGetLinkAddressCallBack sn = %s,ip = %s,port = %s,addressFamily = %s", str, str2, Integer.valueOf(i), Integer.valueOf(i2)));
                    if (HighAvailableLBSService.this.getLinkAddressCallback != null) {
                        HighAvailableLBSService.this.getLinkAddressCallback.onGetLinkAddressCallBack(new HighAvailableLinkAddress(str, str2, i, i2));
                    } else {
                        LogUtils.i(HighAvailableLBSService.TAG, "getLinkAddressCallback is null");
                    }
                }
            };
        }
        boolean nativeGetLinkAddress = nativeGetLinkAddress(this.environmentHandle, addressFamily.ordinal(), this.getLinkAddressNativeCallback);
        LogUtils.i(TAG, String.format("nativeGetLinkAddress = %s", Boolean.valueOf(nativeGetLinkAddress)));
        LogUtils.i(TAG, "getLinkAddress OUT ");
        return nativeGetLinkAddress;
    }

    public String getNosDL() {
        String nativeGetNOSDL = nativeGetNOSDL(this.environmentHandle);
        LogUtils.i(TAG, String.format("nativeGetNOSDL = %s", nativeGetNOSDL));
        return nativeGetNOSDL;
    }

    public String[] getNosDLList() {
        String[] nativeGetNOSDLList = nativeGetNOSDLList(this.environmentHandle);
        LogUtils.i(TAG, "nativeGetNOSDLList = " + Arrays.toString(nativeGetNOSDLList));
        return nativeGetNOSDLList;
    }

    public String[] getNosList() {
        String[] nativeGetNOSList = nativeGetNOSList(this.environmentHandle);
        LogUtils.i(TAG, "nativeGetNOSList = " + Arrays.toString(nativeGetNOSList));
        return nativeGetNOSList;
    }

    public String getName() {
        String nativeGetName = nativeGetName(this.environmentHandle);
        LogUtils.i(TAG, String.format("nativeGetName = %s", nativeGetName));
        return nativeGetName;
    }

    public String getDesc() {
        String nativeGetDesc = nativeGetDesc(this.environmentHandle);
        LogUtils.i(TAG, String.format("nativeGetDesc = %s", nativeGetDesc));
        return nativeGetDesc;
    }

    public int getLinkCount() {
        int nativeGetLinkCount = nativeGetLinkCount(this.environmentHandle);
        LogUtils.i(TAG, String.format("nativeGetLinkCount %s", Integer.valueOf(nativeGetLinkCount)));
        return nativeGetLinkCount;
    }

    public HighAvailableLinkAddress getCurrentLinkAddress() {
        HighAvailableLinkAddress highAvailableLinkAddress = new HighAvailableLinkAddress();
        LogUtils.i(TAG, String.format("getCurrentLinkAddress = %s", Boolean.valueOf(nativeGetCurrentLinkAddress(this.environmentHandle, highAvailableLinkAddress))));
        return highAvailableLinkAddress;
    }

    public void notifyAddressSucceed(String str) {
        LogUtils.i(TAG, String.format("notifyAddressSucceed, sn = %s", str));
        nativeNotifyAddressSucceed(this.environmentHandle, str);
    }

    private HighAvailableLBSSettings fixSettings(HighAvailableLBSSettings highAvailableLBSSettings) {
        HighAvailableLBSSettings highAvailableLBSSettings2 = new HighAvailableLBSSettings();
        highAvailableLBSSettings2.usingPrivate = highAvailableLBSSettings.usingPrivate;
        if (TextUtils.isEmpty(highAvailableLBSSettings.lbsMain)) {
            throw new IllegalArgumentException("lbsMain is empty");
        }
        if (highAvailableLBSSettings.lbsMain.length() > 512) {
            throw new IllegalArgumentException(String.format("lbsMain %s is too long", highAvailableLBSSettings.lbsMain));
        }
        highAvailableLBSSettings2.lbsMain = highAvailableLBSSettings.lbsMain;
        highAvailableLBSSettings2.lbsBackup = new LinkedList();
        if (highAvailableLBSSettings.lbsBackup != null) {
            for (String str : highAvailableLBSSettings.lbsBackup) {
                if (!TextUtils.isEmpty(str) && str.length() <= 512) {
                    highAvailableLBSSettings2.lbsBackup.add(str);
                    if (highAvailableLBSSettings2.lbsBackup.size() >= 5) {
                        break;
                    }
                }
            }
        }
        if (TextUtils.isEmpty(highAvailableLBSSettings.defaultLink)) {
            throw new IllegalArgumentException("defaultLink is empty");
        }
        if (highAvailableLBSSettings.defaultLink.length() > 128) {
            throw new IllegalArgumentException(String.format("defaultLink %s is too long", highAvailableLBSSettings.defaultLink));
        }
        highAvailableLBSSettings2.defaultLink = highAvailableLBSSettings.defaultLink;
        if (highAvailableLBSSettings.defaultLinkIpv6.length() > 128) {
            throw new IllegalArgumentException(String.format("defaultLinkIpv6 %s is too long", highAvailableLBSSettings.defaultLinkIpv6));
        }
        highAvailableLBSSettings2.defaultLinkIpv6 = highAvailableLBSSettings.defaultLinkIpv6;
        highAvailableLBSSettings2.linkList = new LinkedList();
        if (highAvailableLBSSettings.linkList != null) {
            for (String str2 : highAvailableLBSSettings.linkList) {
                if (!TextUtils.isEmpty(str2) && str2.length() <= 128) {
                    highAvailableLBSSettings2.linkList.add(str2);
                    if (highAvailableLBSSettings2.linkList.size() >= 10) {
                        break;
                    }
                }
            }
        }
        if (highAvailableLBSSettings.addressFamily == null) {
            throw new IllegalArgumentException("addressFamily is null");
        }
        highAvailableLBSSettings2.addressFamily = highAvailableLBSSettings.addressFamily;
        if (highAvailableLBSSettings.linkVersionType == null) {
            throw new IllegalArgumentException("linkVersionType is null");
        }
        highAvailableLBSSettings2.linkVersionType = highAvailableLBSSettings.linkVersionType;
        return highAvailableLBSSettings2;
    }

    private void initLbs(HighAvailableLBSSettings highAvailableLBSSettings, HighAvailableLBSCallback highAvailableLBSCallback) {
        this.lbsCallback = highAvailableLBSCallback;
        HighAvailableLBSSettings fixSettings = fixSettings(highAvailableLBSSettings);
        String[] strArr = new String[fixSettings.lbsBackup.size()];
        fixSettings.lbsBackup.toArray(strArr);
        String[] strArr2 = new String[fixSettings.linkList.size()];
        fixSettings.linkList.toArray(strArr2);
        nativeInit(this.environmentHandle, fixSettings.usingPrivate, fixSettings.lbsMain, strArr, fixSettings.defaultLink, fixSettings.defaultLinkIpv6, strArr2, fixSettings.addressFamily.ordinal(), fixSettings.linkVersionType.getValue());
        LogUtils.i(TAG, String.format("init %s", Boolean.valueOf(this.isInited)));
    }

    /* loaded from: classes.dex */
    public static class HighAvailableLBSSettings {
        private AddressFamily addressFamily;
        private String defaultLink;
        private String defaultLinkIpv6;
        private List<String> lbsBackup;
        private String lbsMain;
        private List<String> linkList;
        private LinkVersionType linkVersionType;
        private boolean usingPrivate;

        private HighAvailableLBSSettings() {
            this(false, "", "", AddressFamily.kIPV4);
        }

        public HighAvailableLBSSettings(boolean z, String str, String str2, AddressFamily addressFamily) {
            this(z, str, null, str2, "", null, addressFamily, LinkVersionType.Normal);
        }

        public HighAvailableLBSSettings(boolean z, String str, List<String> list, String str2, String str3, List<String> list2, AddressFamily addressFamily, LinkVersionType linkVersionType) {
            this.usingPrivate = z;
            this.lbsMain = str;
            this.lbsBackup = list;
            this.defaultLink = str2;
            this.defaultLinkIpv6 = str3;
            this.linkList = list2;
            this.addressFamily = addressFamily;
            this.linkVersionType = linkVersionType;
        }
    }

    /* loaded from: classes.dex */
    public enum AddressFamily {
        kUnknown,
        kIPV4,
        kIPV6;

        public static AddressFamily getAddressFamily(int i) {
            for (AddressFamily addressFamily : values()) {
                if (addressFamily.ordinal() == i) {
                    return addressFamily;
                }
            }
            return kUnknown;
        }
    }

    /* loaded from: classes.dex */
    public enum LinkVersionType {
        Normal(0),
        Mix(1);

        private int value;

        LinkVersionType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public static class HighAvailableLinkAddress {
        private int addressFamily;
        private String ip;
        private int port;
        private String sn;

        public HighAvailableLinkAddress() {
        }

        public HighAvailableLinkAddress(String str, String str2, int i, int i2) {
            this.sn = str;
            this.ip = str2;
            this.port = i;
            this.addressFamily = i2;
        }

        public String toString() {
            return "HighAvailableLinkAddress{sn='" + this.sn + "', ip='" + this.ip + "', port=" + this.port + ", addressFamily=" + this.addressFamily + '}';
        }

        public String getSn() {
            return this.sn;
        }

        public void setSn(String str) {
            this.sn = str;
        }

        public void setIp(String str) {
            this.ip = str;
        }

        public void setPort(int i) {
            this.port = i;
        }

        public void setAddressFamily(int i) {
            this.addressFamily = i;
        }

        public String getIp() {
            return this.ip;
        }

        public int getPort() {
            return this.port;
        }

        public AddressFamily getAddressFamily() {
            return AddressFamily.getAddressFamily(this.addressFamily);
        }

        public String getLinkAddress() {
            return this.ip + ":" + this.port;
        }
    }

    /* loaded from: classes.dex */
    public static class MessageDelayStaticsInfo {
        private String api;
        private String desc;
        private boolean enable;
        private String host;

        public boolean isEnable() {
            return this.enable;
        }

        public String getHost() {
            return this.host;
        }

        public String getApi() {
            return this.api;
        }

        public String getDesc() {
            return this.desc;
        }

        public String toString() {
            return "MessageDelayStaticsInfo{enable=" + this.enable + ", host='" + this.host + "', api='" + this.api + "', desc='" + this.desc + "'}";
        }
    }
}
