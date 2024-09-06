package com.netease.nimlib.amazonaws;

import com.netease.nimlib.amazonaws.internal.config.HttpClientConfig;
import com.netease.nimlib.amazonaws.internal.config.InternalConfig;

/* loaded from: classes.dex */
enum ServiceNameFactory {
    ;

    static String getServiceName(String str) {
        HttpClientConfig httpClientConfig = InternalConfig.Factory.getInternalConfig().getHttpClientConfig(str);
        if (httpClientConfig == null) {
            return null;
        }
        return httpClientConfig.getServiceName();
    }
}
