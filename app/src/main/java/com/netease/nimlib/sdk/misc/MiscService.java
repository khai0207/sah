package com.netease.nimlib.sdk.misc;

import com.netease.nimlib.sdk.InvocationFuture;
import java.util.List;

/* loaded from: classes.dex */
public interface MiscService {
    InvocationFuture<Void> clearDirCache(List<DirCacheFileType> list, long j, long j2);

    InvocationFuture<String> getSdkLogUpload(boolean z, String str, String str2);

    InvocationFuture<Long> getServerTime();

    InvocationFuture<Long> getSizeOfDirCache(List<DirCacheFileType> list, long j, long j2);

    InvocationFuture<String> zipLogs();
}
