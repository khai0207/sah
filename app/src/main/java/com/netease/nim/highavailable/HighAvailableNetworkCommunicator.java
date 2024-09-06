package com.netease.nim.highavailable;

/* loaded from: classes.dex */
public class HighAvailableNetworkCommunicator {
    private final int environmentHandle;
    private final long nativePtr;
    private HighAvailableNetworkCallback networkCallback;
    private HighAvailableNetworkNativeCallback networkNativeCallback;

    private native void nativeNotifyConnectionTypeChanged(int i, int i2);

    private native void nativeSetConnectionTypeQuery(int i, HighAvailableNetworkNativeCallback highAvailableNetworkNativeCallback);

    public HighAvailableNetworkCommunicator(long j, int i) {
        this.nativePtr = j;
        this.environmentHandle = i;
    }

    protected long getNativePtr() {
        return this.nativePtr;
    }

    protected int getEnvironmentHandle() {
        return this.environmentHandle;
    }

    public void setConnectionTypeQuery(HighAvailableNetworkCallback highAvailableNetworkCallback) {
        this.networkCallback = highAvailableNetworkCallback;
        if (this.networkNativeCallback == null) {
            this.networkNativeCallback = new HighAvailableNetworkNativeCallback() { // from class: com.netease.nim.highavailable.HighAvailableNetworkCommunicator.1
                @Override // com.netease.nim.highavailable.HighAvailableNetworkNativeCallback
                public int queryConnectionType() {
                    if (HighAvailableNetworkCommunicator.this.networkCallback == null) {
                        return 0;
                    }
                    return HighAvailableNetworkCommunicator.this.networkCallback.queryConnectionType();
                }
            };
        }
        nativeSetConnectionTypeQuery(this.environmentHandle, this.networkNativeCallback);
    }

    public void notifyConnectionTypeChanged(ConnectionType connectionType) {
        nativeNotifyConnectionTypeChanged(this.environmentHandle, connectionType.ordinal());
    }

    /* loaded from: classes.dex */
    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_NONE,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_XG;

        public static ConnectionType getConnectionType(int i) {
            for (ConnectionType connectionType : values()) {
                if (connectionType.ordinal() == i) {
                    return connectionType;
                }
            }
            return CONNECTION_UNKNOWN;
        }
    }
}
