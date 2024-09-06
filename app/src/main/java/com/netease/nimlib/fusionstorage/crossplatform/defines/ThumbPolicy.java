package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public class ThumbPolicy {
    private String imageThumb;
    private String videoFrame;

    public ThumbPolicy(String str, String str2) {
        this.imageThumb = str;
        this.videoFrame = str2;
    }

    public String getImageThumb() {
        return this.imageThumb;
    }

    public void setImageThumb(String str) {
        this.imageThumb = str;
    }

    public String getVideoFrame() {
        return this.videoFrame;
    }

    public void setVideoFrame(String str) {
        this.videoFrame = str;
    }

    public String toString() {
        return "ThumbPolicy{imageThumb='" + this.imageThumb + "', videoFrame='" + this.videoFrame + "'}";
    }
}
