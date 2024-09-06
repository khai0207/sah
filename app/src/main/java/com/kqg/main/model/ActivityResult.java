package com.kqg.main.model;

import android.content.Intent;

/* loaded from: classes.dex */
public class ActivityResult {
    private Object att;
    private Intent data;
    private int requestCode;
    private int resultCode;

    public Object getAtt() {
        return this.att;
    }

    public void setAtt(Object obj) {
        this.att = obj;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public void setRequestCode(int i) {
        this.requestCode = i;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public Intent getData() {
        return this.data;
    }

    public void setData(Intent intent) {
        this.data = intent;
    }

    public ActivityResult(int i, int i2, Intent intent) {
        this.requestCode = i;
        this.resultCode = i2;
        this.data = intent;
    }
}
