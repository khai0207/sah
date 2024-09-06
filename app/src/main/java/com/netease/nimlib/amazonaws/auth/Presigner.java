package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.Request;
import java.util.Date;

/* loaded from: classes.dex */
public interface Presigner {
    void presignRequest(Request<?> request, AWSCredentials aWSCredentials, Date date);
}
