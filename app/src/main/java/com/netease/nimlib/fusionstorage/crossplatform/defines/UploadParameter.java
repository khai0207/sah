package com.netease.nimlib.fusionstorage.crossplatform.defines;

/* loaded from: classes.dex */
public class UploadParameter {
    private Credential credential;
    private String downloadUrl;
    private boolean pickedUp;
    private UploadConfig uploadConfig;
    private int uploadMode;

    public UploadParameter(boolean z, Credential credential, String str, int i, UploadConfig uploadConfig) {
        this.pickedUp = z;
        this.credential = credential;
        this.downloadUrl = str;
        this.uploadMode = i;
        this.uploadConfig = uploadConfig;
    }

    public boolean isPickedUp() {
        return this.pickedUp;
    }

    public void setPickedUp(boolean z) {
        this.pickedUp = z;
    }

    public Credential getCredential() {
        return this.credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public int getUploadMode() {
        return this.uploadMode;
    }

    public void setUploadMode(int i) {
        this.uploadMode = i;
    }

    public UploadConfig getUploadConfig() {
        return this.uploadConfig;
    }

    public void setUploadConfig(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    public String toString() {
        return "UploadParameter{pickedUp=" + this.pickedUp + ", credential=" + this.credential + ", downloadUrl='" + this.downloadUrl + "', uploadMode=" + this.uploadMode + ", uploadConfig=" + this.uploadConfig + '}';
    }
}
