package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.annotations.SerializedName;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ANY' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public abstract class TransferNetworkConnectionType {
    private static final /* synthetic */ TransferNetworkConnectionType[] $VALUES;

    @SerializedName("ANY")
    public static final TransferNetworkConnectionType ANY;
    private static final Log LOGGER;
    private static final Map<String, TransferNetworkConnectionType> MAP;

    @SerializedName("MOBILE")
    public static final TransferNetworkConnectionType MOBILE;

    @SerializedName("WIFI")
    public static final TransferNetworkConnectionType WIFI = new TransferNetworkConnectionType("WIFI", 1) { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.2
        @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
        }
    };

    protected abstract boolean verify(NetworkInfo networkInfo);

    private TransferNetworkConnectionType(String str, int i) {
    }

    public static TransferNetworkConnectionType valueOf(String str) {
        return (TransferNetworkConnectionType) Enum.valueOf(TransferNetworkConnectionType.class, str);
    }

    public static TransferNetworkConnectionType[] values() {
        return (TransferNetworkConnectionType[]) $VALUES.clone();
    }

    static {
        int i = 0;
        ANY = new TransferNetworkConnectionType("ANY", i) { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.1
            @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
            protected boolean verify(NetworkInfo networkInfo) {
                return networkInfo != null && networkInfo.isConnected();
            }
        };
        TransferNetworkConnectionType transferNetworkConnectionType = new TransferNetworkConnectionType("MOBILE", 2) { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.3
            @Override // com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
            protected boolean verify(NetworkInfo networkInfo) {
                return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 0;
            }
        };
        MOBILE = transferNetworkConnectionType;
        $VALUES = new TransferNetworkConnectionType[]{ANY, WIFI, transferNetworkConnectionType};
        MAP = new HashMap();
        TransferNetworkConnectionType[] values = values();
        int length = values.length;
        while (i < length) {
            TransferNetworkConnectionType transferNetworkConnectionType2 = values[i];
            MAP.put(transferNetworkConnectionType2.toString(), transferNetworkConnectionType2);
            i++;
        }
        LOGGER = LogFactory.getLog((Class<?>) TransferNetworkConnectionType.class);
    }

    boolean isConnected(ConnectivityManager connectivityManager) {
        return verify(connectivityManager.getActiveNetworkInfo());
    }

    public static TransferNetworkConnectionType getConnectionType(String str) {
        if (MAP.containsKey(str)) {
            return MAP.get(str);
        }
        LOGGER.error("Unknown connection type " + str + " transfer will have connection type set to ANY.");
        return ANY;
    }
}
