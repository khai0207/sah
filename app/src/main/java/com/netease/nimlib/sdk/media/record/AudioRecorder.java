package com.netease.nimlib.sdk.media.record;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.netease.nimlib.log.b;
import com.netease.nimlib.o.p;
import com.netease.nimlib.o.w;
import com.netease.share.media.a;
import com.netease.share.media.c;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class AudioRecorder {
    public static final int DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND = 120;
    private static final int MSG_END_RECORD = 3;
    private static final int MSG_START_RECORD = 1;
    private static final int MSG_STOP_RECORD = 2;
    private static final int RECORD_CANCELED = 5;
    private static final int RECORD_FAILED = 1;
    private static final int RECORD_READY = 2;
    private static final int RECORD_START = 3;
    private static final int RECORD_SUCCESS = 4;
    private static final String TAG = "AudioRecordManager";
    private File audioFile;
    private AudioManager audioManager;
    private IAudioRecordCallback cb;
    private Context context;
    private HandlerThread handlerThread;
    private a mAudioRecorder;
    private RecordHandler mHandler;
    private int maxDuration;
    private RecordType recordType;
    private int networkClass = 0;
    private AtomicBoolean isRecording = new AtomicBoolean(false);
    private AtomicBoolean cancelRecord = new AtomicBoolean(false);
    private Handler mEventHandler = new Handler(Looper.getMainLooper());
    private c infoListener = new c() { // from class: com.netease.nimlib.sdk.media.record.AudioRecorder.3
        @Override // com.netease.share.media.c
        public void onInfo(int i, int i2, int i3) {
            if (i2 == 2) {
                AudioRecorder.this.handleEndRecord(false, 0);
            } else if (i2 == 1) {
                AudioRecorder.this.mHandler.post(new Runnable() { // from class: com.netease.nimlib.sdk.media.record.AudioRecorder.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioRecorder.this.isRecording.set(false);
                    }
                });
                AudioRecorder.this.handleReachedMaxRecordTime(i3);
            }
        }
    };

    /* loaded from: classes.dex */
    private class RecordHandler extends Handler {
        public RecordHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                AudioRecorder.this.onStartRecord();
                return;
            }
            if (i == 2) {
                AudioRecorder.this.onCompleteRecord(((Boolean) message.obj).booleanValue());
            } else {
                if (i != 3) {
                    return;
                }
                AudioRecorder.this.onHandleEndRecord(((Boolean) message.obj).booleanValue(), message.arg1);
            }
        }
    }

    public AudioRecorder(Context context, RecordType recordType, int i, IAudioRecordCallback iAudioRecordCallback) {
        this.context = context.getApplicationContext();
        this.recordType = recordType;
        if (i <= 0) {
            this.maxDuration = 120;
        } else {
            this.maxDuration = i;
        }
        this.cb = iAudioRecordCallback;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        HandlerThread handlerThread = new HandlerThread("audio_recorder");
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new RecordHandler(this.handlerThread.getLooper());
    }

    public void startRecord() {
        this.mHandler.removeMessages(1);
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartRecord() {
        this.audioManager.requestAudioFocus(null, 0, 2);
        if (this.isRecording.get()) {
            b.s("AudioRecordManager startRecord false, as current state is isRecording");
            callBackRecordState(1);
            return;
        }
        if (!com.netease.nimlib.o.b.c.a(com.netease.nimlib.o.b.b.TYPE_AUDIO)) {
            b.s("AudioRecordManager startRecord false, as has no enough space to write");
            callBackRecordState(1);
            return;
        }
        int outputFormat = this.recordType.getOutputFormat();
        String a = com.netease.nimlib.o.b.c.a(com.netease.nimlib.c.e(), w.a() + outputFormat, com.netease.nimlib.o.b.b.TYPE_AUDIO);
        if (TextUtils.isEmpty(a)) {
            b.s("AudioRecordManager startRecord false, as outputFilePath is empty");
            callBackRecordState(1);
            return;
        }
        String str = a + this.recordType.getFileSuffix();
        this.audioFile = new File(str);
        this.cancelRecord.set(false);
        try {
            a aVar = new a(this.context, str, this.maxDuration * 1000);
            this.mAudioRecorder = aVar;
            aVar.b(outputFormat);
            int j = p.j(this.context);
            this.networkClass = j;
            if (j == 2) {
                this.mAudioRecorder.a(22050);
            } else if (j == 1) {
                this.mAudioRecorder.a(16000);
            }
            this.mAudioRecorder.a(this.infoListener);
            if (!this.cancelRecord.get()) {
                callBackRecordState(2);
                if (this.mAudioRecorder.c()) {
                    this.isRecording.set(true);
                    callBackRecordState(3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onCompleteRecord(false);
        }
        if (this.isRecording.get()) {
            return;
        }
        callBackRecordState(1);
    }

    public void completeRecord(boolean z) {
        Message obtainMessage = this.mHandler.obtainMessage(2);
        obtainMessage.obj = Boolean.valueOf(z);
        obtainMessage.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCompleteRecord(boolean z) {
        if (this.isRecording.get()) {
            this.cancelRecord.set(z);
            this.audioManager.abandonAudioFocus(null);
            try {
                if (this.mAudioRecorder != null) {
                    this.mAudioRecorder.b();
                    onHandleEndRecord(true, this.mAudioRecorder.d());
                    this.mAudioRecorder = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void destroyAudioRecorder() {
        RecordHandler recordHandler = this.mHandler;
        if (recordHandler != null) {
            recordHandler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread == null || !handlerThread.isAlive()) {
            return;
        }
        this.handlerThread.getLooper().quit();
    }

    public boolean isRecording() {
        return this.isRecording.get();
    }

    public void handleEndRecord(boolean z, int i) {
        Message obtainMessage = this.mHandler.obtainMessage(3);
        obtainMessage.obj = Boolean.valueOf(z);
        obtainMessage.arg1 = i;
        obtainMessage.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHandleEndRecord(boolean z, final int i) {
        if (this.cancelRecord.get()) {
            com.netease.nimlib.net.a.c.a.c(this.audioFile.getAbsolutePath());
            callBackRecordState(5);
        } else if (!z) {
            com.netease.nimlib.net.a.c.a.c(this.audioFile.getAbsolutePath());
            callBackRecordState(1);
        } else {
            File file = this.audioFile;
            if (file == null || !file.exists() || this.audioFile.length() <= 0) {
                callBackRecordState(1);
            } else {
                this.mEventHandler.post(new Runnable() { // from class: com.netease.nimlib.sdk.media.record.AudioRecorder.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioRecorder.this.cb.onRecordSuccess(AudioRecorder.this.audioFile, i, AudioRecorder.this.recordType);
                    }
                });
            }
        }
        this.isRecording.set(false);
    }

    private void callBackRecordState(final int i) {
        this.mEventHandler.post(new Runnable() { // from class: com.netease.nimlib.sdk.media.record.AudioRecorder.2
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                if (i2 == 1) {
                    AudioRecorder.this.cb.onRecordFail();
                    return;
                }
                if (i2 == 2) {
                    AudioRecorder.this.cb.onRecordReady();
                } else if (i2 == 3) {
                    AudioRecorder.this.cb.onRecordStart(AudioRecorder.this.audioFile, AudioRecorder.this.recordType);
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    AudioRecorder.this.cb.onRecordCancel();
                }
            }
        });
    }

    public int getCurrentRecordMaxAmplitude() {
        a aVar = this.mAudioRecorder;
        if (aVar != null) {
            return aVar.a();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReachedMaxRecordTime(int i) {
        this.cb.onRecordReachedMaxTime(i);
    }
}
