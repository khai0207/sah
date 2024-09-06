package com.netease.nimlib.sdk.generic;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.generic.param.GenericTypeAPICallParam;
import com.netease.nimlib.sdk.generic.result.GenericTypeAPICallResult;

/* loaded from: classes.dex */
public interface CustomizedAPIService {
    InvocationFuture<GenericTypeAPICallResult> invokeAPI(GenericTypeAPICallParam genericTypeAPICallParam);
}
