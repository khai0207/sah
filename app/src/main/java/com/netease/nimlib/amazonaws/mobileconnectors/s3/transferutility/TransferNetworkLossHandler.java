package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.services.s3.AmazonS3;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class TransferNetworkLossHandler extends BroadcastReceiver {
    private static final Log LOGGER = LogFactory.getLog((Class<?>) TransferNetworkLossHandler.class);
    private static TransferNetworkLossHandler transferNetworkLossHandler;
    final ConnectivityManager connManager;
    private TransferDBUtil dbUtil;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable networkCheckRunnable = new Runnable() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler.1
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (TransferRecord transferRecord : TransferNetworkLossHandler.this.updater.getTransfers().values()) {
                AmazonS3 amazonS3 = S3ClientReference.get(Integer.valueOf(transferRecord.id));
                if (amazonS3 != null && transferRecord != null && transferRecord.state == TransferState.WAITING_FOR_NETWORK) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long lastActiveTime = transferRecord.getLastActiveTime();
                    long j = currentTimeMillis - lastActiveTime;
                    TransferNetworkLossHandler.LOGGER.info(String.format("networkCheckRunnable now %s %s interval %s default %s: %s", Long.valueOf(currentTimeMillis), Long.valueOf(lastActiveTime), Long.valueOf(j), Long.valueOf(TransferUtilityOptions.getDefaultCheckTimeInterval()), transferRecord));
                    double d = j;
                    double defaultCheckTimeInterval = TransferUtilityOptions.getDefaultCheckTimeInterval() * 2;
                    Double.isNaN(defaultCheckTimeInterval);
                    if (d > defaultCheckTimeInterval * 0.95d) {
                        TransferRecord transfer = TransferNetworkLossHandler.this.updater.getTransfer(transferRecord.id);
                        TransferNetworkLossHandler.LOGGER.info(String.format("networkCheckRunnable cancel: %s %s", transferRecord, transfer));
                        if (transfer != null) {
                            transfer.pause(amazonS3, TransferNetworkLossHandler.this.updater);
                        }
                    }
                }
            }
            TransferNetworkLossHandler.this.mainHandler.postDelayed(TransferNetworkLossHandler.this.networkCheckRunnable, TransferUtilityOptions.getDefaultCheckTimeInterval());
        }
    };
    TransferStatusUpdater updater;

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (TransferRecord transferRecord : TransferNetworkLossHandler.this.updater.getTransfers().values()) {
                AmazonS3 amazonS3 = S3ClientReference.get(Integer.valueOf(transferRecord.id));
                if (amazonS3 != null && transferRecord != null && transferRecord.state == TransferState.WAITING_FOR_NETWORK) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long lastActiveTime = transferRecord.getLastActiveTime();
                    long j = currentTimeMillis - lastActiveTime;
                    TransferNetworkLossHandler.LOGGER.info(String.format("networkCheckRunnable now %s %s interval %s default %s: %s", Long.valueOf(currentTimeMillis), Long.valueOf(lastActiveTime), Long.valueOf(j), Long.valueOf(TransferUtilityOptions.getDefaultCheckTimeInterval()), transferRecord));
                    double d = j;
                    double defaultCheckTimeInterval = TransferUtilityOptions.getDefaultCheckTimeInterval() * 2;
                    Double.isNaN(defaultCheckTimeInterval);
                    if (d > defaultCheckTimeInterval * 0.95d) {
                        TransferRecord transfer = TransferNetworkLossHandler.this.updater.getTransfer(transferRecord.id);
                        TransferNetworkLossHandler.LOGGER.info(String.format("networkCheckRunnable cancel: %s %s", transferRecord, transfer));
                        if (transfer != null) {
                            transfer.pause(amazonS3, TransferNetworkLossHandler.this.updater);
                        }
                    }
                }
            }
            TransferNetworkLossHandler.this.mainHandler.postDelayed(TransferNetworkLossHandler.this.networkCheckRunnable, TransferUtilityOptions.getDefaultCheckTimeInterval());
        }
    }

    public synchronized void checkAndStartTimer() {
        this.mainHandler.removeCallbacks(this.networkCheckRunnable);
        if (isNetworkConnected()) {
            for (TransferRecord transferRecord : this.updater.getTransfers().values()) {
                if (S3ClientReference.get(Integer.valueOf(transferRecord.id)) != null && transferRecord != null) {
                    transferRecord.setLastActiveTime();
                }
            }
        } else {
            this.mainHandler.postDelayed(this.networkCheckRunnable, TransferUtilityOptions.getDefaultCheckTimeInterval());
        }
    }

    private TransferNetworkLossHandler(Context context) {
        this.connManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.dbUtil = new TransferDBUtil(context);
        this.updater = TransferStatusUpdater.getInstance(context);
        checkAndStartTimer();
    }

    public static synchronized TransferNetworkLossHandler getInstance(Context context) {
        TransferNetworkLossHandler transferNetworkLossHandler2;
        synchronized (TransferNetworkLossHandler.class) {
            if (transferNetworkLossHandler == null) {
                transferNetworkLossHandler = new TransferNetworkLossHandler(context);
            }
            transferNetworkLossHandler2 = transferNetworkLossHandler;
        }
        return transferNetworkLossHandler2;
    }

    public static synchronized TransferNetworkLossHandler getInstance() throws TransferUtilityException {
        TransferNetworkLossHandler transferNetworkLossHandler2;
        synchronized (TransferNetworkLossHandler.class) {
            if (transferNetworkLossHandler == null) {
                LOGGER.error("TransferNetworkLossHandler is not created. Please call `TransferNetworkLossHandler.getInstance(Context)` to instantiate it before retrieving");
                throw new TransferUtilityException("TransferNetworkLossHandler is not created. Please call `TransferNetworkLossHandler.getInstance(Context)` to instantiate it before retrieving");
            }
            transferNetworkLossHandler2 = transferNetworkLossHandler;
        }
        return transferNetworkLossHandler2;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            LOGGER.info("Network connectivity changed detected.");
            boolean isNetworkConnected = isNetworkConnected();
            LOGGER.info("Network connected: " + isNetworkConnected);
            new Thread(new Runnable() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler.2
                AnonymousClass2() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (TransferNetworkLossHandler.this.isNetworkConnected()) {
                        TransferNetworkLossHandler.this.resumeAllTransfersOnNetworkAvailability();
                    } else {
                        TransferNetworkLossHandler.this.pauseAllTransfersDueToNetworkInterruption();
                    }
                    TransferNetworkLossHandler.this.checkAndStartTimer();
                }
            }).start();
        }
    }

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TransferNetworkLossHandler.this.isNetworkConnected()) {
                TransferNetworkLossHandler.this.resumeAllTransfersOnNetworkAvailability();
            } else {
                TransferNetworkLossHandler.this.pauseAllTransfersDueToNetworkInterruption();
            }
            TransferNetworkLossHandler.this.checkAndStartTimer();
        }
    }

    boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo = this.connManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public synchronized void resumeAllTransfersOnNetworkAvailability() {
        TransferRecord transfer;
        int i = 0;
        TransferState[] transferStateArr = {TransferState.WAITING_FOR_NETWORK};
        LOGGER.debug("Loading transfers from database...");
        Cursor cursor = null;
        ArrayList<Integer> arrayList = new ArrayList();
        try {
            cursor = this.dbUtil.queryTransfersWithTypeAndStates(TransferType.ANY, transferStateArr);
            while (cursor.moveToNext()) {
                int i2 = cursor.getInt(cursor.getColumnIndexOrThrow(TransferTable.COLUMN_ID));
                if (this.updater.getTransfer(i2) == null) {
                    TransferRecord transferRecord = new TransferRecord(i2);
                    transferRecord.updateFromDB(cursor);
                    this.updater.addTransfer(transferRecord);
                    i++;
                }
                arrayList.add(Integer.valueOf(i2));
            }
            try {
                for (Integer num : arrayList) {
                    AmazonS3 amazonS3 = S3ClientReference.get(num);
                    if (amazonS3 != null && (transfer = this.updater.getTransfer(num.intValue())) != null && !transfer.isRunning()) {
                        transfer.start(amazonS3, this.dbUtil, this.updater, this.connManager);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error in resuming the transfers." + e.getMessage());
            }
            LOGGER.debug(i + " transfers are loaded from database.");
        } finally {
            if (cursor != null) {
                LOGGER.debug("Closing the cursor for resumeAllTransfers");
                cursor.close();
            }
        }
    }

    public synchronized void pauseAllTransfersDueToNetworkInterruption() {
        for (TransferRecord transferRecord : this.updater.getTransfers().values()) {
            AmazonS3 amazonS3 = S3ClientReference.get(Integer.valueOf(transferRecord.id));
            if (amazonS3 != null && transferRecord != null && transferRecord.pauseIfRequiredForNetworkInterruption(amazonS3, this.updater, this.connManager)) {
                this.updater.updateState(transferRecord.id, TransferState.WAITING_FOR_NETWORK);
            }
        }
    }
}
