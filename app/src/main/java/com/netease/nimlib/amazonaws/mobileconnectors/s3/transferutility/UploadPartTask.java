package com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility;

import com.netease.nimlib.amazonaws.AbortedException;
import com.netease.nimlib.amazonaws.event.ProgressEvent;
import com.netease.nimlib.amazonaws.event.ProgressListener;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.UploadTask;
import com.netease.nimlib.amazonaws.services.s3.AmazonS3;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartResult;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class UploadPartTask implements Callable<Boolean> {
    private static final Log LOGGER = LogFactory.getLog((Class<?>) UploadPartTask.class);
    private static final int RETRY_COUNT = 3;
    private final TransferDBUtil dbUtil;
    private final AmazonS3 s3;
    private final UploadPartRequest uploadPartRequest;
    private final UploadTask.UploadPartTaskMetadata uploadPartTaskMetadata;
    private final UploadPartTaskProgressListener uploadPartTaskProgressListener;

    public UploadPartTask(UploadTask.UploadPartTaskMetadata uploadPartTaskMetadata, UploadTask.UploadTaskProgressListener uploadTaskProgressListener, UploadPartRequest uploadPartRequest, AmazonS3 amazonS3, TransferDBUtil transferDBUtil) {
        this.uploadPartTaskMetadata = uploadPartTaskMetadata;
        this.uploadPartTaskProgressListener = new UploadPartTaskProgressListener(uploadTaskProgressListener);
        this.uploadPartRequest = uploadPartRequest;
        this.s3 = amazonS3;
        this.dbUtil = transferDBUtil;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Boolean call() throws Exception {
        this.uploadPartTaskMetadata.state = TransferState.IN_PROGRESS;
        this.uploadPartRequest.setGeneralProgressListener(this.uploadPartTaskProgressListener);
        boolean z = true;
        int i = 1;
        while (true) {
            try {
                UploadPartResult uploadPart = this.s3.uploadPart(this.uploadPartRequest);
                setTaskState(TransferState.PART_COMPLETED);
                this.dbUtil.updateETag(this.uploadPartRequest.getId(), uploadPart.getETag());
                return Boolean.valueOf(z);
            } catch (AbortedException unused) {
                LOGGER.debug("Upload part aborted.");
                resetProgress();
                return false;
            } catch (Exception e) {
                LOGGER.error("Unexpected error occurred: " + e);
                resetProgress();
                try {
                    if (TransferNetworkLossHandler.getInstance() != null && !TransferNetworkLossHandler.getInstance().isNetworkConnected()) {
                        LOGGER.info("Thread: [" + Thread.currentThread().getId() + "]: Network wasn't available.");
                        this.uploadPartTaskMetadata.state = TransferState.WAITING_FOR_NETWORK;
                        this.dbUtil.updateState(this.uploadPartRequest.getId(), TransferState.WAITING_FOR_NETWORK);
                        LOGGER.info("Network Connection Interrupted: Moving the TransferState to WAITING_FOR_NETWORK");
                        return false;
                    }
                } catch (TransferUtilityException e2) {
                    LOGGER.error("TransferUtilityException: [" + e2 + "]");
                }
                if (i >= 3) {
                    setTaskState(TransferState.FAILED);
                    LOGGER.error("Encountered error uploading part ", e);
                    throw e;
                }
                long exponentialBackoffWithJitter = exponentialBackoffWithJitter(i);
                LOGGER.info("Retrying in " + exponentialBackoffWithJitter + " ms.");
                TimeUnit.MILLISECONDS.sleep(exponentialBackoffWithJitter);
                LOGGER.debug("Retry attempt: " + i, e);
                i++;
            }
        }
    }

    private void setTaskState(TransferState transferState) {
        this.uploadPartTaskMetadata.state = transferState;
        this.dbUtil.updateState(this.uploadPartRequest.getId(), transferState);
    }

    private void resetProgress() {
        ProgressEvent progressEvent = new ProgressEvent(0L);
        progressEvent.setEventCode(32);
        this.uploadPartTaskProgressListener.progressChanged(progressEvent);
    }

    /* loaded from: classes.dex */
    private class UploadPartTaskProgressListener implements ProgressListener {
        private long bytesTransferredSoFar;
        private final UploadTask.UploadTaskProgressListener uploadTaskProgressListener;

        public UploadPartTaskProgressListener(UploadTask.UploadTaskProgressListener uploadTaskProgressListener) {
            this.uploadTaskProgressListener = uploadTaskProgressListener;
        }

        @Override // com.netease.nimlib.amazonaws.event.ProgressListener
        public void progressChanged(ProgressEvent progressEvent) {
            if (32 == progressEvent.getEventCode()) {
                UploadPartTask.LOGGER.debug("Reset Event triggered. Resetting the bytesCurrent to 0.");
                this.bytesTransferredSoFar = 0L;
            } else {
                this.bytesTransferredSoFar += progressEvent.getBytesTransferred();
            }
            this.uploadTaskProgressListener.onProgressChanged(UploadPartTask.this.uploadPartRequest.getPartNumber(), this.bytesTransferredSoFar);
        }
    }

    private long exponentialBackoffWithJitter(int i) {
        return ((1 << i) * 1000) + ((long) (Math.random() * 1000.0d));
    }
}
