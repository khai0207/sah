package com.netease.nimlib.amazonaws.http;

import com.netease.nimlib.amazonaws.AmazonWebServiceClient;
import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.Signer;
import com.netease.nimlib.amazonaws.handlers.RequestHandler2;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;
import com.netease.nimlib.amazonaws.util.AWSRequestMetricsFullSupport;
import java.net.URI;
import java.util.List;

/* loaded from: classes.dex */
public class ExecutionContext {
    private final AmazonWebServiceClient awsClient;
    private final AWSRequestMetrics awsRequestMetrics;
    private String contextUserAgent;
    private AWSCredentials credentials;
    private final List<RequestHandler2> requestHandler2s;

    public void setSigner(Signer signer) {
    }

    @Deprecated
    public ExecutionContext(boolean z) {
        this(null, z, null);
    }

    public ExecutionContext() {
        this(null, false, null);
    }

    public ExecutionContext(List<RequestHandler2> list, boolean z, AmazonWebServiceClient amazonWebServiceClient) {
        this.requestHandler2s = list;
        this.awsRequestMetrics = z ? new AWSRequestMetricsFullSupport() : new AWSRequestMetrics();
        this.awsClient = amazonWebServiceClient;
    }

    public String getContextUserAgent() {
        return this.contextUserAgent;
    }

    public void setContextUserAgent(String str) {
        this.contextUserAgent = str;
    }

    public List<RequestHandler2> getRequestHandler2s() {
        return this.requestHandler2s;
    }

    @Deprecated
    public AWSRequestMetrics getAwsRequestMetrics() {
        return this.awsRequestMetrics;
    }

    public Signer getSignerByURI(URI uri) {
        AmazonWebServiceClient amazonWebServiceClient = this.awsClient;
        if (amazonWebServiceClient == null) {
            return null;
        }
        return amazonWebServiceClient.getSignerByURI(uri);
    }

    public AWSCredentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(AWSCredentials aWSCredentials) {
        this.credentials = aWSCredentials;
    }
}
