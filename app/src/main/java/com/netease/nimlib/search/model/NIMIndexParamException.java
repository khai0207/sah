package com.netease.nimlib.search.model;

/* loaded from: classes.dex */
public class NIMIndexParamException extends NIMIndexException {
    private static final long serialVersionUID = 3374596591328907630L;

    @Override // com.netease.nimlib.search.model.NIMIndexException
    public boolean isBusy() {
        return false;
    }

    public NIMIndexParamException(String str) {
        super(str);
    }
}
