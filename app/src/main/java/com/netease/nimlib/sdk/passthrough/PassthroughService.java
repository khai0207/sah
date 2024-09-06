package com.netease.nimlib.sdk.passthrough;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.passthrough.model.PassthroughProxyData;

/* loaded from: classes.dex */
public interface PassthroughService {
    InvocationFuture<PassthroughProxyData> httpProxy(PassthroughProxyData passthroughProxyData);
}
