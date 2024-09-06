package com.kqg.main.model;

/* loaded from: classes.dex */
public class BackGroundMessage {
    private CommonEntity entity;
    private int what;

    public int getWhat() {
        return this.what;
    }

    public void setWhat(int i) {
        this.what = i;
    }

    public CommonEntity getEntity() {
        return this.entity;
    }

    public void setEntity(CommonEntity commonEntity) {
        this.entity = commonEntity;
    }

    public String toString() {
        return "BackGroundMessage [what=" + this.what + "]";
    }

    public BackGroundMessage(int i) {
        this.what = i;
    }
}
