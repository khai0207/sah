package com.netease.nimlib.sdk;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface Observer<T> extends Serializable {
    void onEvent(T t);
}
