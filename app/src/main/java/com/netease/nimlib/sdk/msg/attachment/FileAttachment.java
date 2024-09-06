package com.netease.nimlib.sdk.msg.attachment;

import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import com.netease.nimlib.o.b.b;
import com.netease.nimlib.o.b.c;
import com.netease.nimlib.o.k;
import com.netease.nimlib.o.m;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.util.UriUtils;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FileAttachment implements MsgAttachment {
    private static final String KEY_EXPIRE = "expire";
    private static final String KEY_EXT = "ext";
    private static final String KEY_FORCE_UPLOAD = "force_upload";
    private static final String KEY_MD5 = "md5";
    private static final String KEY_NAME = "name";
    private static final String KEY_PATH = "path";
    private static final String KEY_SCENE = "sen";
    private static final String KEY_SIZE = "size";
    private static final String KEY_URL = "url";
    protected String displayName;
    private long expire;
    protected String extension;
    protected String md5;
    protected String path;
    protected long size;
    protected String url;
    protected String nosTokenSceneKey = NimNosSceneKeyConstant.NIM_DEFAULT_IM;
    protected boolean forceUpload = false;
    private boolean isUri = false;

    protected void load(JSONObject jSONObject) {
    }

    protected void save(JSONObject jSONObject) {
    }

    public FileAttachment() {
    }

    public FileAttachment(String str) {
        fromJson(str);
    }

    public String getPath() {
        String pathForSave = getPathForSave();
        if (new File(pathForSave).exists()) {
            return pathForSave;
        }
        return null;
    }

    public String getPathForSave() {
        if (!this.isUri && !TextUtils.isEmpty(this.path)) {
            return this.path;
        }
        return c.a(getFileName(), storageType());
    }

    public String getThumbPath() {
        String thumbPathForSave = getThumbPathForSave();
        if (new File(thumbPathForSave).exists()) {
            return thumbPathForSave;
        }
        return null;
    }

    public String getThumbPathForSave() {
        int lastIndexOf;
        String fileName = getFileName();
        if (!TextUtils.isEmpty(fileName) && (lastIndexOf = fileName.lastIndexOf(".")) >= 0 && lastIndexOf < fileName.length() - 1) {
            fileName = fileName.substring(0, lastIndexOf);
        }
        return c.a(fileName, b.TYPE_THUMB_IMAGE);
    }

    public void setPath(String str) {
        this.path = str;
        this.isUri = UriUtils.isFileOrContentUri(str);
    }

    public boolean setUri(Uri uri) {
        if (uri == null || !UriUtils.isFileOrContentUri(uri)) {
            return false;
        }
        this.isUri = true;
        this.path = uri.toString();
        return true;
    }

    public Uri getUri() {
        if (this.isUri) {
            return UriUtils.string2Uri(this.path);
        }
        return null;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public String getFileName() {
        if (this.isUri) {
            return UriUtils.getFileNameFromUri(com.netease.nimlib.c.e(), UriUtils.string2Uri(this.path));
        }
        if (!TextUtils.isEmpty(this.path)) {
            return w.d(this.path);
        }
        if (TextUtils.isEmpty(this.md5)) {
            return m.a(this.url);
        }
        return this.md5;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public String getNosTokenSceneKey() {
        return this.nosTokenSceneKey;
    }

    public void setNosTokenSceneKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.nosTokenSceneKey = str;
    }

    public long getExpire() {
        return this.expire;
    }

    public boolean isForceUpload() {
        return this.forceUpload;
    }

    public void setForceUpload(boolean z) {
        this.forceUpload = z;
    }

    protected b storageType() {
        return b.TYPE_FILE;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachment
    public String toJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            try {
                if (!TextUtils.isEmpty(this.path)) {
                    jSONObject.put(KEY_PATH, this.path);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(this.md5)) {
            jSONObject.put(KEY_MD5, this.md5);
        }
        if (!TextUtils.isEmpty(this.displayName)) {
            jSONObject.put("name", this.displayName);
        }
        jSONObject.put("url", this.url);
        jSONObject.put(KEY_SIZE, this.size);
        if (!TextUtils.isEmpty(this.extension)) {
            jSONObject.put("ext", this.extension);
        }
        if (!TextUtils.isEmpty(this.nosTokenSceneKey)) {
            jSONObject.put(KEY_SCENE, this.nosTokenSceneKey);
        }
        if (this.expire > 0) {
            jSONObject.put(KEY_EXPIRE, this.expire);
        }
        jSONObject.put(KEY_FORCE_UPLOAD, this.forceUpload);
        save(jSONObject);
        return jSONObject.toString();
    }

    private void fromJson(String str) {
        JSONObject a = k.a(str);
        if (a == null) {
            return;
        }
        String e = k.e(a, KEY_PATH);
        this.path = e;
        if (UriUtils.isFileOrContentUri(UriUtils.string2Uri(e))) {
            this.isUri = true;
        }
        this.md5 = k.e(a, KEY_MD5);
        this.url = k.e(a, "url");
        this.displayName = k.e(a, "name");
        this.size = k.b(a, KEY_SIZE);
        this.extension = k.e(a, "ext");
        setNosTokenSceneKey(k.e(a, KEY_SCENE));
        this.forceUpload = k.c(a, KEY_FORCE_UPLOAD);
        this.expire = k.b(a, KEY_EXPIRE);
        load(a);
    }
}
