package com.kqg.main.model;

/* loaded from: classes.dex */
public class PayResult {
    private boolean cancel;
    private int code;
    private boolean fail;
    private boolean failUnKnown;
    private String mes;
    private boolean onProcess;
    private boolean success;

    public boolean isOnProcess() {
        return this.onProcess;
    }

    public void setOnProcess(boolean z) {
        this.onProcess = z;
    }

    public boolean isCancel() {
        return this.cancel;
    }

    public void setCancel(boolean z) {
        this.cancel = z;
    }

    public boolean isFail() {
        return this.fail;
    }

    public void setFail(boolean z) {
        this.fail = z;
    }

    public boolean isFailUnKnown() {
        return this.failUnKnown;
    }

    public void setFailUnKnown(boolean z) {
        this.failUnKnown = z;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMes() {
        return this.mes;
    }

    public void setMes(String str) {
        this.mes = str;
    }

    public String toString() {
        return "PayResult [code=" + this.code + ", success=" + this.success + ", mes=" + this.mes + "]";
    }
}
