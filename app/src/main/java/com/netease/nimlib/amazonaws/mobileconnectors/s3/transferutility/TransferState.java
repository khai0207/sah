package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum TransferState {
    WAITING,
    IN_PROGRESS,
    PAUSED,
    RESUMED_WAITING,
    COMPLETED,
    CANCELED,
    FAILED,
    WAITING_FOR_NETWORK,
    PART_COMPLETED,
    PENDING_CANCEL,
    PENDING_PAUSE,
    PENDING_NETWORK_DISCONNECT,
    UNKNOWN;

    private static final Log LOGGER;
    private static final Map<String, TransferState> MAP = new HashMap();

    static {
        for (TransferState transferState : values()) {
            MAP.put(transferState.toString(), transferState);
        }
        LOGGER = LogFactory.getLog((Class<?>) TransferState.class);
    }

    public static TransferState getState(String str) {
        if (MAP.containsKey(str)) {
            return MAP.get(str);
        }
        LOGGER.error("Unknown state " + str + " transfer will be have state set to UNKNOWN.");
        return UNKNOWN;
    }

    protected static boolean isFinalState(TransferState transferState) {
        return COMPLETED.equals(transferState) || FAILED.equals(transferState) || CANCELED.equals(transferState);
    }
}
