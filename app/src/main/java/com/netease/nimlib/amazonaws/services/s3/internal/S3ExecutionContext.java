package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.AmazonWebServiceClient;
import com.netease.nimlib.amazonaws.auth.Signer;
import com.netease.nimlib.amazonaws.handlers.RequestHandler2;
import com.netease.nimlib.amazonaws.http.ExecutionContext;
import java.net.URI;
import java.util.List;

/* loaded from: classes.dex */
public class S3ExecutionContext extends ExecutionContext {
    private Signer signer;

    public S3ExecutionContext(List<RequestHandler2> list, boolean z, AmazonWebServiceClient amazonWebServiceClient) {
        super(list, z, amazonWebServiceClient);
    }

    @Override // com.netease.nimlib.amazonaws.http.ExecutionContext
    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    @Override // com.netease.nimlib.amazonaws.http.ExecutionContext
    public Signer getSignerByURI(URI uri) {
        return this.signer;
    }
}
