package com.netease.nimlib.sdk.media.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.netease.nimlib.log.b;
import java.io.File;

/* loaded from: classes.dex */
public final class AudioPlayer {
    public static final String TAG = "AudioPlayer";
    private static final int WHAT_COUNT_PLAY = 0;
    private static final int WHAT_DECODE_FAILED = 2;
    private static final int WHAT_DECODE_SUCCEED = 1;
    private AudioManager audioManager;
    private int audioStreamType;
    private String mAudioFile;
    private Handler mHandler;
    private long mIntervalTime;
    private OnPlayListener mListener;
    private MediaPlayer mPlayer;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;

    public AudioPlayer(Context context) {
        this(context, null, null);
    }

    public AudioPlayer(Context context, String str, OnPlayListener onPlayListener) {
        this.mIntervalTime = 500L;
        this.audioStreamType = 0;
        this.mHandler = new Handler() { // from class: com.netease.nimlib.sdk.media.player.AudioPlayer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    try {
                        if (AudioPlayer.this.mListener != null) {
                            AudioPlayer.this.mListener.onPlaying(AudioPlayer.this.mPlayer.getCurrentPosition());
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                        b.e(AudioPlayer.TAG, " error", th);
                    }
                    sendEmptyMessageDelayed(0, AudioPlayer.this.mIntervalTime);
                    return;
                }
                if (i == 1) {
                    AudioPlayer.this.startInner();
                } else {
                    if (i != 2) {
                        return;
                    }
                    b.f(AudioPlayer.TAG, "convert() error: " + AudioPlayer.this.mAudioFile);
                }
            }
        };
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.netease.nimlib.sdk.media.player.AudioPlayer.5
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -3) {
                    if (AudioPlayer.this.isPlaying()) {
                        AudioPlayer.this.setVolume(0.1f, 0.1f);
                    }
                } else {
                    if (i == -2) {
                        AudioPlayer.this.stop();
                        return;
                    }
                    if (i != -1) {
                        if (i == 1 && AudioPlayer.this.isPlaying()) {
                            AudioPlayer.this.setVolume(1.0f, 1.0f);
                            return;
                        }
                        return;
                    }
                    AudioPlayer.this.stop();
                }
            }
        };
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioFile = str;
        this.mListener = onPlayListener;
    }

    public void setDataSource(String str) {
        if (TextUtils.equals(str, this.mAudioFile)) {
            return;
        }
        b.s("start play audio file" + str);
        this.mAudioFile = str;
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.mListener = onPlayListener;
    }

    public OnPlayListener getOnPlayListener() {
        return this.mListener;
    }

    public void start(int i) {
        this.audioStreamType = i;
        startPlay();
    }

    public void stop() {
        if (this.mPlayer != null) {
            endPlay();
            OnPlayListener onPlayListener = this.mListener;
            if (onPlayListener != null) {
                onPlayListener.onInterrupt();
            }
        }
    }

    public boolean isPlaying() {
        try {
            if (this.mPlayer != null) {
                return this.mPlayer.isPlaying();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            b.e(TAG, "isPlaying error", th);
            return false;
        }
    }

    public long getDuration() {
        if (this.mPlayer != null) {
            try {
                return r0.getDuration();
            } catch (Throwable th) {
                th.printStackTrace();
                b.e(TAG, "getDuration error", th);
            }
        }
        return 0L;
    }

    public long getCurrentPosition() {
        if (this.mPlayer != null) {
            try {
                return r0.getCurrentPosition();
            } catch (Throwable th) {
                th.printStackTrace();
                b.e(TAG, "getCurrentPosition error", th);
            }
        }
        return 0L;
    }

    public void seekTo(int i) {
        try {
            this.mPlayer.seekTo(i);
        } catch (Throwable th) {
            th.printStackTrace();
            b.e(TAG, "seekTo error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolume(float f, float f2) {
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer == null) {
            return;
        }
        try {
            mediaPlayer.setVolume(f, f2);
        } catch (Throwable th) {
            th.printStackTrace();
            b.e(TAG, "setVolume error", th);
        }
    }

    private void startPlay() {
        b.c(TAG, "start() called");
        endPlay();
        startInner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endPlay() {
        this.audioManager.abandonAudioFocus(this.onAudioFocusChangeListener);
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (Throwable th) {
                th.printStackTrace();
                b.e(TAG, "endPlay error", th);
            }
            try {
                this.mPlayer.release();
            } catch (Throwable th2) {
                th2.printStackTrace();
                b.e(TAG, "endPlay error", th2);
            }
            this.mPlayer = null;
            this.mHandler.removeMessages(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInner() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mPlayer = mediaPlayer;
        try {
            mediaPlayer.setLooping(false);
            this.mPlayer.setAudioStreamType(this.audioStreamType);
            if (this.audioStreamType == 3) {
                this.audioManager.setSpeakerphoneOn(true);
            } else {
                this.audioManager.setSpeakerphoneOn(false);
            }
            this.audioManager.requestAudioFocus(this.onAudioFocusChangeListener, this.audioStreamType, 2);
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.netease.nimlib.sdk.media.player.AudioPlayer.2
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    b.c(AudioPlayer.TAG, "player:onPrepared");
                    AudioPlayer.this.mHandler.sendEmptyMessage(0);
                    if (AudioPlayer.this.mListener != null) {
                        AudioPlayer.this.mListener.onPrepared();
                    }
                }
            });
            this.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.netease.nimlib.sdk.media.player.AudioPlayer.3
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer2) {
                    b.c(AudioPlayer.TAG, "player:onCompletion");
                    AudioPlayer.this.endPlay();
                    if (AudioPlayer.this.mListener != null) {
                        AudioPlayer.this.mListener.onCompletion();
                    }
                }
            });
            this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.netease.nimlib.sdk.media.player.AudioPlayer.4
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                    b.f(AudioPlayer.TAG, String.format("player:onOnError what:%d extra:%d", Integer.valueOf(i), Integer.valueOf(i2)));
                    AudioPlayer.this.endPlay();
                    if (AudioPlayer.this.mListener != null) {
                        AudioPlayer.this.mListener.onError(String.format("OnErrorListener what:%d extra:%d", Integer.valueOf(i), Integer.valueOf(i2)));
                    }
                    return true;
                }
            });
            if (this.mAudioFile != null) {
                this.mPlayer.setDataSource(this.mAudioFile);
                this.mPlayer.prepare();
                this.mPlayer.start();
                b.c(TAG, "player:start ok---->" + this.mAudioFile);
                return;
            }
            if (this.mListener != null) {
                this.mListener.onError("no datasource");
            }
        } catch (Throwable th) {
            th.printStackTrace();
            b.f(TAG, "player:onOnError Exception\n" + th.toString());
            endPlay();
            OnPlayListener onPlayListener = this.mListener;
            if (onPlayListener != null) {
                onPlayListener.onError("Exception\n" + th.toString());
            }
        }
    }

    private void deleteOnExit() {
        File file = new File(this.mAudioFile);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }
}
