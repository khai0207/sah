package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.SDKGlobalConfiguration;

@Deprecated
/* loaded from: classes.dex */
public class SystemPropertiesCredentialsProvider implements AWSCredentialsProvider {
    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        if (System.getProperty(SDKGlobalConfiguration.ACCESS_KEY_SYSTEM_PROPERTY) != null && System.getProperty(SDKGlobalConfiguration.SECRET_KEY_SYSTEM_PROPERTY) != null) {
            return new BasicAWSCredentials(System.getProperty(SDKGlobalConfiguration.ACCESS_KEY_SYSTEM_PROPERTY), System.getProperty(SDKGlobalConfiguration.SECRET_KEY_SYSTEM_PROPERTY));
        }
        throw new AmazonClientException("Unable to load AWS credentials from Java system properties (aws.accessKeyId and aws.secretKey)");
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
