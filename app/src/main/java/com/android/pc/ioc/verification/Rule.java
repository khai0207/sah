package com.android.pc.ioc.verification;

/* loaded from: classes.dex */
public abstract class Rule<T> {
    private String mFailureMessage;

    public abstract boolean isValid(T t);

    private Rule() {
    }

    public Rule(String str) {
        this.mFailureMessage = str;
    }

    public String getFailureMessage() {
        return this.mFailureMessage;
    }

    public void setFailureMessage(String str) {
        this.mFailureMessage = str;
    }
}
