package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.netease.nimlib.amazonaws.event.ProgressEvent;
import com.netease.nimlib.amazonaws.event.ProgressListener;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.netease.nimlib.c;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
class TransferStatusUpdater {
    static final String TEMP_FILE_PREFIX = "aws-s3-d861b25a-1edf-11eb-adc1-0242ac120002";
    private static TransferDBUtil dbUtil;
    private static TransferStatusUpdater transferStatusUpdater;
    private Context context;
    private final Handler mainHandler;
    private final Map<Integer, TransferRecord> transfers;
    private static final Log LOGGER = LogFactory.getLog((Class<?>) TransferStatusUpdater.class);
    private static final HashSet<TransferState> STATES_NOT_TO_NOTIFY = new HashSet<>(Arrays.asList(TransferState.PART_COMPLETED, TransferState.PENDING_CANCEL, TransferState.PENDING_PAUSE, TransferState.PENDING_NETWORK_DISCONNECT));
    static final Map<Integer, List<TransferListener>> LISTENERS = new ConcurrentHashMap<Integer, List<TransferListener>>() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater.1
        AnonymousClass1() {
        }
    };

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends ConcurrentHashMap<Integer, List<TransferListener>> {
        AnonymousClass1() {
        }
    }

    TransferStatusUpdater(TransferDBUtil transferDBUtil, Context context) {
        dbUtil = transferDBUtil;
        this.context = context;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.transfers = new ConcurrentHashMap();
    }

    public static synchronized TransferStatusUpdater getInstance(Context context) {
        TransferStatusUpdater transferStatusUpdater2;
        synchronized (TransferStatusUpdater.class) {
            if (transferStatusUpdater == null) {
                dbUtil = new TransferDBUtil(context);
                transferStatusUpdater = new TransferStatusUpdater(dbUtil, context);
            }
            transferStatusUpdater2 = transferStatusUpdater;
        }
        return transferStatusUpdater2;
    }

    synchronized Map<Integer, TransferRecord> getTransfers() {
        return Collections.unmodifiableMap(this.transfers);
    }

    synchronized void addTransfer(TransferRecord transferRecord) {
        this.transfers.put(Integer.valueOf(transferRecord.id), transferRecord);
    }

    synchronized TransferRecord getTransfer(int i) {
        return this.transfers.get(Integer.valueOf(i));
    }

    synchronized void removeTransfer(int i) {
        synchronized (LISTENERS) {
            LISTENERS.remove(Integer.valueOf(i));
        }
        this.transfers.remove(Integer.valueOf(i));
    }

    synchronized void removeTransferRecordFromDB(int i) {
        TransferRecord transferById = dbUtil.getTransferById(i);
        if (transferById != null) {
            String str = transferById.file;
            if (new File(str).getName().startsWith(TEMP_FILE_PREFIX)) {
                new File(str).delete();
            }
        }
        S3ClientReference.remove(Integer.valueOf(i));
        dbUtil.deleteTransferRecords(i);
    }

    synchronized void updateState(int i, TransferState transferState) {
        boolean contains = STATES_NOT_TO_NOTIFY.contains(transferState);
        boolean z = TransferState.PENDING_PAUSE == transferState && !TransferNetworkLossHandler.getInstance(c.e()).isNetworkConnected();
        boolean z2 = contains && !z;
        LOGGER.info(String.format("updateState %s %s %s %s", Integer.valueOf(i), transferState, Boolean.valueOf(z2), Boolean.valueOf(z)));
        TransferRecord transferRecord = this.transfers.get(Integer.valueOf(i));
        if (transferRecord == null) {
            if (dbUtil.updateState(i, transferState) == 0) {
                LOGGER.warn("Failed to update the status of transfer " + i);
            }
        } else {
            z2 |= transferState.equals(transferRecord.state);
            transferRecord.state = transferState;
            if (dbUtil.updateTransferRecord(transferRecord) == 0) {
                LOGGER.warn("Failed to update the status of transfer " + i);
            }
        }
        if (z2) {
            return;
        }
        if (TransferState.COMPLETED.equals(transferState)) {
            removeTransferRecordFromDB(i);
        }
        synchronized (LISTENERS) {
            List<TransferListener> list = LISTENERS.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                for (TransferListener transferListener : list) {
                    if (!(transferListener instanceof TransferObserver.TransferStatusListener)) {
                        this.mainHandler.post(new Runnable() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater.2
                            final /* synthetic */ int val$id;
                            final /* synthetic */ TransferListener val$l;
                            final /* synthetic */ TransferState val$newState;

                            AnonymousClass2(TransferListener transferListener2, int i2, TransferState transferState2) {
                                r2 = transferListener2;
                                r3 = i2;
                                r4 = transferState2;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r2.onStateChanged(r3, r4);
                            }
                        });
                    } else if (!z) {
                        transferListener2.onStateChanged(i2, transferState2);
                    }
                }
                if (TransferState.isFinalState(transferState2)) {
                    list.clear();
                }
            }
        }
    }

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ int val$id;
        final /* synthetic */ TransferListener val$l;
        final /* synthetic */ TransferState val$newState;

        AnonymousClass2(TransferListener transferListener2, int i2, TransferState transferState2) {
            r2 = transferListener2;
            r3 = i2;
            r4 = transferState2;
        }

        @Override // java.lang.Runnable
        public void run() {
            r2.onStateChanged(r3, r4);
        }
    }

    synchronized void updateProgress(int i, long j, long j2, boolean z) {
        TransferRecord transferRecord = this.transfers.get(Integer.valueOf(i));
        if (transferRecord != null) {
            transferRecord.bytesCurrent = j;
            transferRecord.bytesTotal = j2;
        }
        dbUtil.updateBytesTransferred(i, j);
        if (z) {
            synchronized (LISTENERS) {
                List<TransferListener> list = LISTENERS.get(Integer.valueOf(i));
                if (list != null && !list.isEmpty()) {
                    for (Iterator<TransferListener> it = list.iterator(); it.hasNext(); it = it) {
                        this.mainHandler.post(new Runnable() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater.3
                            final /* synthetic */ long val$bytesCurrent;
                            final /* synthetic */ long val$bytesTotal;
                            final /* synthetic */ int val$id;
                            final /* synthetic */ TransferListener val$l;

                            AnonymousClass3(TransferListener transferListener, int i2, long j3, long j22) {
                                r2 = transferListener;
                                r3 = i2;
                                r4 = j3;
                                r6 = j22;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r2.onProgressChanged(r3, r4, r6);
                            }
                        });
                    }
                }
            }
        }
    }

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ long val$bytesCurrent;
        final /* synthetic */ long val$bytesTotal;
        final /* synthetic */ int val$id;
        final /* synthetic */ TransferListener val$l;

        AnonymousClass3(TransferListener transferListener, int i2, long j3, long j22) {
            r2 = transferListener;
            r3 = i2;
            r4 = j3;
            r6 = j22;
        }

        @Override // java.lang.Runnable
        public void run() {
            r2.onProgressChanged(r3, r4, r6);
        }
    }

    void throwError(int i, Exception exc) {
        synchronized (LISTENERS) {
            List<TransferListener> list = LISTENERS.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                Iterator<TransferListener> it = list.iterator();
                while (it.hasNext()) {
                    this.mainHandler.post(new Runnable() { // from class: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater.4
                        final /* synthetic */ Exception val$e;
                        final /* synthetic */ int val$id;
                        final /* synthetic */ TransferListener val$l;

                        AnonymousClass4(TransferListener transferListener, int i2, Exception exc2) {
                            r2 = transferListener;
                            r3 = i2;
                            r4 = exc2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            r2.onError(r3, r4);
                        }
                    });
                }
            }
        }
    }

    /* renamed from: com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferStatusUpdater$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ Exception val$e;
        final /* synthetic */ int val$id;
        final /* synthetic */ TransferListener val$l;

        AnonymousClass4(TransferListener transferListener, int i2, Exception exc2) {
            r2 = transferListener;
            r3 = i2;
            r4 = exc2;
        }

        @Override // java.lang.Runnable
        public void run() {
            r2.onError(r3, r4);
        }
    }

    synchronized void clear() {
        synchronized (LISTENERS) {
            LISTENERS.clear();
        }
        this.transfers.clear();
    }

    static void registerListener(int i, TransferListener transferListener) {
        if (transferListener == null) {
            throw new IllegalArgumentException("Listener can't be null");
        }
        synchronized (LISTENERS) {
            List<TransferListener> list = LISTENERS.get(Integer.valueOf(i));
            if (list == null) {
                CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                copyOnWriteArrayList.add(transferListener);
                LISTENERS.put(Integer.valueOf(i), copyOnWriteArrayList);
            } else if (!list.contains(transferListener)) {
                list.add(transferListener);
            }
        }
    }

    static void unregisterListener(int i, TransferListener transferListener) {
        if (transferListener == null) {
            throw new IllegalArgumentException("Listener can't be null");
        }
        synchronized (LISTENERS) {
            List<TransferListener> list = LISTENERS.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                list.remove(transferListener);
            }
        }
    }

    /* loaded from: classes.dex */
    private class TransferProgressListener implements ProgressListener {
        private long bytesTransferredSoFar;
        private final TransferRecord transfer;

        public TransferProgressListener(TransferRecord transferRecord) {
            this.transfer = transferRecord;
        }

        @Override // com.netease.nimlib.amazonaws.event.ProgressListener
        public synchronized void progressChanged(ProgressEvent progressEvent) {
            if (32 == progressEvent.getEventCode()) {
                TransferStatusUpdater.LOGGER.info("Reset Event triggered. Resetting the bytesCurrent to 0.");
                this.bytesTransferredSoFar = 0L;
            } else {
                long bytesTransferred = this.bytesTransferredSoFar + progressEvent.getBytesTransferred();
                this.bytesTransferredSoFar = bytesTransferred;
                if (bytesTransferred > this.transfer.bytesCurrent) {
                    this.transfer.bytesCurrent = this.bytesTransferredSoFar;
                    TransferStatusUpdater.this.updateProgress(this.transfer.id, this.transfer.bytesCurrent, this.transfer.bytesTotal, true);
                }
            }
        }
    }

    synchronized ProgressListener newProgressListener(int i) {
        TransferRecord transfer;
        transfer = getTransfer(i);
        if (transfer == null) {
            LOGGER.info("TransferStatusUpdater doesn't track the transfer: " + i);
            throw new IllegalArgumentException("transfer " + i + " doesn't exist");
        }
        LOGGER.info("Creating a new progress listener for transfer: " + i);
        return new TransferProgressListener(transfer);
    }
}
