package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public class ThumbUserSettings {
    private Integer thumbQuality;
    private ThumbSize thumbSize;
    private Float videoFrameOffset;
    private String videoFrameType;

    public ThumbUserSettings(ThumbSize thumbSize, Integer num, String str, Float f) {
        this.thumbSize = thumbSize;
        this.thumbQuality = num;
        this.videoFrameType = str;
        this.videoFrameOffset = f;
    }

    public ThumbSize getThumbSize() {
        return this.thumbSize;
    }

    public void setThumbSize(ThumbSize thumbSize) {
        this.thumbSize = thumbSize;
    }

    public Integer getThumbQuality() {
        return this.thumbQuality;
    }

    public void setThumbQuality(Integer num) {
        this.thumbQuality = num;
    }

    public String getVideoFrameType() {
        return this.videoFrameType;
    }

    public void setVideoFrameType(String str) {
        this.videoFrameType = str;
    }

    public Float getVideoFrameOffset() {
        return this.videoFrameOffset;
    }

    public void setVideoFrameOffset(Float f) {
        this.videoFrameOffset = f;
    }
}
