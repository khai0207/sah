package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class AWSCredentialsProviderChain implements AWSCredentialsProvider {
    private static final Log log = LogFactory.getLog((Class<?>) AWSCredentialsProviderChain.class);
    private AWSCredentialsProvider lastUsedProvider;
    private List<AWSCredentialsProvider> credentialsProviders = new LinkedList();
    private boolean reuseLastProvider = true;

    public AWSCredentialsProviderChain(AWSCredentialsProvider... aWSCredentialsProviderArr) {
        if (aWSCredentialsProviderArr == null || aWSCredentialsProviderArr.length == 0) {
            throw new IllegalArgumentException("No credential providers specified");
        }
        for (AWSCredentialsProvider aWSCredentialsProvider : aWSCredentialsProviderArr) {
            this.credentialsProviders.add(aWSCredentialsProvider);
        }
    }

    public boolean getReuseLastProvider() {
        return this.reuseLastProvider;
    }

    public void setReuseLastProvider(boolean z) {
        this.reuseLastProvider = z;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        AWSCredentialsProvider aWSCredentialsProvider;
        if (this.reuseLastProvider && (aWSCredentialsProvider = this.lastUsedProvider) != null) {
            return aWSCredentialsProvider.getCredentials();
        }
        for (AWSCredentialsProvider aWSCredentialsProvider2 : this.credentialsProviders) {
            try {
                AWSCredentials credentials = aWSCredentialsProvider2.getCredentials();
                if (credentials.getAWSAccessKeyId() != null && credentials.getAWSSecretKey() != null) {
                    log.debug("Loading credentials from " + aWSCredentialsProvider2.toString());
                    this.lastUsedProvider = aWSCredentialsProvider2;
                    return credentials;
                }
            } catch (Exception e) {
                log.debug("Unable to load credentials from " + aWSCredentialsProvider2.toString() + ": " + e.getMessage());
            }
        }
        throw new AmazonClientException("Unable to load AWS credentials from any provider in the chain");
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
        Iterator<AWSCredentialsProvider> it = this.credentialsProviders.iterator();
        while (it.hasNext()) {
            it.next().refresh();
        }
    }
}
