package com.netease.nimlib.search.model;

/* loaded from: classes.dex */
public class NIMIndexBusyException extends NIMIndexException {
    private static final long serialVersionUID = 3374596591328907630L;

    @Override // com.netease.nimlib.search.model.NIMIndexException
    public boolean isBusy() {
        return true;
    }

    public NIMIndexBusyException(String str) {
        super(str);
    }
}
