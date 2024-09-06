package com.netease.nimlib.sdk.nos;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.nos.model.NosThumbParam;
import java.io.File;

/* loaded from: classes.dex */
public interface NosService {
    String convertDownloadUrlToCDNUrl(String str);

    AbortableFuture<Void> download(String str, NosThumbParam nosThumbParam, String str2);

    AbortableFuture<Void> downloadFileSecure(String str, String str2);

    InvocationFuture<String> getOriginUrlFromShortUrl(String str);

    InvocationFuture<String> getOriginUrlFromShortUrl(String str, String str2);

    AbortableFuture<String> upload(File file, String str);

    AbortableFuture<String> uploadAtScene(File file, String str, String str2);

    AbortableFuture<String> uploadEnableForce(File file, String str, String str2, boolean z);
}
