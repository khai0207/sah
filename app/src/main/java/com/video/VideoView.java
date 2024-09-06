package com.video;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.IOException;

/* loaded from: classes.dex */
public class VideoView extends SurfaceView implements SurfaceHolder.Callback, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnCompletionListener {
    private static final String TAG = "VideoView";
    private static VideoView videoView;
    private final Activity appActivity;
    private AssetFileDescriptor fd;
    private MediaPlayer mPlayer;
    int posttion;
    private TextView skipButton;
    private VideoViewInterface viewInterface;

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        return true;
    }

    private VideoView(Activity activity, VideoViewInterface videoViewInterface) {
        super(activity);
        this.skipButton = null;
        this.viewInterface = null;
        this.appActivity = activity;
        this.viewInterface = videoViewInterface;
        Log.i(TAG, "new VideoView");
        getHolder().addCallback(this);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    }

    public static VideoView getInstance(Activity activity, VideoViewInterface videoViewInterface) {
        if (videoView == null) {
            videoView = new VideoView(activity, videoViewInterface);
        }
        return videoView;
    }

    private void addSkipButton() {
        TextView textView = new TextView(this.appActivity);
        this.skipButton = textView;
        textView.setText("跳过 >>");
        this.skipButton.setTextColor(Color.argb(180, 255, 255, 255));
        this.skipButton.setTextSize(20.0f);
        this.skipButton.setOnClickListener(new View.OnClickListener() { // from class: com.video.VideoView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoView.this.onVideoFinish();
            }
        });
        ((ViewGroup) this.appActivity.getWindow().getDecorView()).addView(this.skipButton);
    }

    public void setVideo(AssetFileDescriptor assetFileDescriptor) {
        this.fd = assetFileDescriptor;
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mPlayer = mediaPlayer;
            mediaPlayer.setScreenOnWhilePlaying(true);
            this.mPlayer.setOnCompletionListener(this);
            this.mPlayer.setOnErrorListener(this);
            this.mPlayer.setOnInfoListener(this);
            this.mPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            this.mPlayer.prepare();
            this.posttion = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MediaPlayer getPlayer() {
        return this.mPlayer;
    }

    private void fixSzie(SurfaceHolder surfaceHolder) {
        if (this.skipButton != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.skipButton.getWidth(), this.skipButton.getHeight());
            double width = getWidth();
            Double.isNaN(width);
            layoutParams.leftMargin = (int) (width * 0.8d);
            double height = getHeight();
            Double.isNaN(height);
            layoutParams.topMargin = (int) (height * 0.86d);
            this.skipButton.setLayoutParams(layoutParams);
            this.skipButton.bringToFront();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.i(TAG, "surfaceChanged:" + i2 + "," + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surfaceCreated");
        try {
            this.mPlayer.setDisplay(surfaceHolder);
            this.mPlayer.seekTo(this.posttion);
            this.mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            onVideoFinish();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            Log.i(TAG, "surfaceDestroyed");
            if (this.mPlayer != null) {
                this.posttion = this.mPlayer.getCurrentPosition();
                this.mPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i(TAG, "onCompletion");
        onVideoFinish();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        System.out.println("play error:" + i + "," + i2);
        return false;
    }

    public void onVideoFinish() {
        Log.i(TAG, "onVideoFinish");
        try {
            if (this.mPlayer != null) {
                this.mPlayer.stop();
                this.mPlayer.release();
                this.mPlayer = null;
            }
            if (this.fd != null) {
                this.fd.close();
                this.fd = null;
            }
        } catch (Exception e) {
            Log.i(TAG, "onVideoFinish error");
            e.printStackTrace();
        }
        try {
            ViewGroup viewGroup = (ViewGroup) this.appActivity.getWindow().getDecorView();
            viewGroup.removeView(this);
            if (this.skipButton != null) {
                viewGroup.removeView(this.skipButton);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        VideoViewInterface videoViewInterface = this.viewInterface;
        if (videoViewInterface != null) {
            videoViewInterface.onComplete();
        }
    }

    public static void playVideo(String str, Activity activity, VideoViewInterface videoViewInterface) {
        playVideo(str, activity, videoViewInterface, 0);
    }

    public static void playVideo(String str, Activity activity, VideoViewInterface videoViewInterface, int i) {
        Log.e("playVideo", "--------------static-------------playVideo-------------------------");
        VideoView videoView2 = getInstance(activity, videoViewInterface);
        try {
            videoView2.setVideo(activity.getAssets().openFd(str));
            ((ViewGroup) activity.getWindow().getDecorView()).addView(videoView2);
            videoView2.setZOrderMediaOverlay(true);
            if (i == 1) {
                videoView2.addSkipButton();
            }
        } catch (IOException e) {
            e.printStackTrace();
            videoView2.onVideoFinish();
        }
    }

    public static void pauseVideo() {
        videoView.getPlayer().pause();
    }

    public static void resumeVideo() {
        videoView.getPlayer().start();
    }
}
