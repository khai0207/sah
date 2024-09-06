package com.netease.nimlib.search.model;

/* loaded from: classes.dex */
public class NIMIndexException extends Exception {
    private static final long serialVersionUID = -4045601267315586789L;

    public boolean isBusy() {
        return false;
    }

    public NIMIndexException(String str) {
        super(str);
    }
}
