package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.o.b.b;
import com.netease.nimlib.o.k;
import com.netease.nimlib.session.j;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ImageAttachment extends FileAttachment {
    private static final String KEY_HEIGHT = "h";
    private static final String KEY_WIDTH = "w";
    private int height;
    private int width;

    public boolean isHdImage() {
        return false;
    }

    public ImageAttachment() {
    }

    public ImageAttachment(String str) {
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

    public String getThumbUrl() {
        return j.a(this, getUrl());
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected b storageType() {
        return b.TYPE_IMAGE;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void save(JSONObject jSONObject) {
        k.a(jSONObject, KEY_WIDTH, this.width);
        k.a(jSONObject, KEY_HEIGHT, this.height);
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.FileAttachment
    protected void load(JSONObject jSONObject) {
        this.width = k.a(jSONObject, KEY_WIDTH);
        this.height = k.a(jSONObject, KEY_HEIGHT);
    }
}
