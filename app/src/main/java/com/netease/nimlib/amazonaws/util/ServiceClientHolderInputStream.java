package com.netease.nimlib.amazonaws.util;

import com.netease.nimlib.amazonaws.AmazonWebServiceClient;
import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ServiceClientHolderInputStream extends SdkFilterInputStream {
    private AmazonWebServiceClient client;

    public ServiceClientHolderInputStream(InputStream inputStream, AmazonWebServiceClient amazonWebServiceClient) {
        super(inputStream);
        this.client = amazonWebServiceClient;
    }
}
