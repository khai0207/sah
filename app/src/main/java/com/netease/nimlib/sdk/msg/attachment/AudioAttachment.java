package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.o.b.b;
import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AudioAttachment extends FileAttachment {
    private static final String KEY_DURATION = "dur";
    private boolean autoTransform;
    private long duration;
    private String text;

    public AudioAttachment() {
    }

    public AudioAttachment(String str) {
        super(str);
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public boolean getAutoTransform() {
        return this.autoTransform;
    }

    public void setAutoTransform(boolean z) {
        this.autoTransform = z;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected b storageType() {
        return b.TYPE_AUDIO;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void save(JSONObject jSONObject) {
        k.a(jSONObject, KEY_DURATION, this.duration);
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void load(JSONObject jSONObject) {
        this.duration = k.a(jSONObject, KEY_DURATION);
    }
}
