package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.o.b.b;
import com.netease.nimlib.o.k;
import com.netease.nimlib.session.j;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class VideoAttachment extends FileAttachment {
    private static final String KEY_DURATION = "dur";
    private static final String KEY_HEIGHT = "h";
    private static final String KEY_WIDTH = "w";
    private long duration;
    private int height;
    private int width;

    public VideoAttachment() {
    }

    public VideoAttachment(String str) {
        super(str);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getThumbUrl() {
        return j.a(this, getUrl());
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected b storageType() {
        return b.TYPE_VIDEO;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void save(JSONObject jSONObject) {
        k.a(jSONObject, KEY_WIDTH, this.width);
        k.a(jSONObject, KEY_HEIGHT, this.height);
        k.a(jSONObject, KEY_DURATION, this.duration);
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void load(JSONObject jSONObject) {
        this.width = k.a(jSONObject, KEY_WIDTH);
        this.height = k.a(jSONObject, KEY_HEIGHT);
        this.duration = k.a(jSONObject, KEY_DURATION);
    }
}
