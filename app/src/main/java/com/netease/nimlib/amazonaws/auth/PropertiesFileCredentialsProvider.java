package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.AmazonClientException;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class PropertiesFileCredentialsProvider implements AWSCredentialsProvider {
    private final String credentialsFilePath;

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }

    public PropertiesFileCredentialsProvider(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Credentials file path cannot be null");
        }
        this.credentialsFilePath = str;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        try {
            return new PropertiesCredentials(new File(this.credentialsFilePath));
        } catch (IOException e) {
            throw new AmazonClientException("Unable to load AWS credentials from the " + this.credentialsFilePath + " file", e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.credentialsFilePath + ")";
    }
}
