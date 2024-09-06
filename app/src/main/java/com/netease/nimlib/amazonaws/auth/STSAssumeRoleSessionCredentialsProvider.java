package com.netease.nimlib.amazonaws.auth;

import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Credentials;
import java.util.Date;

/* loaded from: classes.dex */
public class STSAssumeRoleSessionCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 900;
    private static final int EXPIRY_TIME_MILLIS = 60000;
    private String roleArn;
    private String roleSessionName;
    private final AWSSecurityTokenService securityTokenService;
    private AWSSessionCredentials sessionCredentials;
    private Date sessionCredentialsExpiration;

    public STSAssumeRoleSessionCredentialsProvider(String str, String str2) {
        this.roleArn = str;
        this.roleSessionName = str2;
        this.securityTokenService = new AWSSecurityTokenServiceClient();
    }

    public STSAssumeRoleSessionCredentialsProvider(AWSCredentials aWSCredentials, String str, String str2) {
        this(aWSCredentials, str, str2, new ClientConfiguration());
    }

    public STSAssumeRoleSessionCredentialsProvider(AWSCredentials aWSCredentials, String str, String str2, ClientConfiguration clientConfiguration) {
        this.roleArn = str;
        this.roleSessionName = str2;
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentials, clientConfiguration);
    }

    public STSAssumeRoleSessionCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider, String str, String str2) {
        this.roleArn = str;
        this.roleSessionName = str2;
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentialsProvider);
    }

    public STSAssumeRoleSessionCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider, String str, String str2, ClientConfiguration clientConfiguration) {
        this.roleArn = str;
        this.roleSessionName = str2;
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentialsProvider, clientConfiguration);
    }

    public void setSTSClientEndpoint(String str) {
        this.securityTokenService.setEndpoint(str);
        this.sessionCredentials = null;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        if (needsNewSession()) {
            startSession();
        }
        return this.sessionCredentials;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
        startSession();
    }

    private void startSession() {
        Credentials credentials = this.securityTokenService.assumeRole(new AssumeRoleRequest().withRoleArn(this.roleArn).withDurationSeconds(Integer.valueOf(DEFAULT_DURATION_SECONDS)).withRoleSessionName(this.roleSessionName)).getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
        this.sessionCredentialsExpiration = credentials.getExpiration();
    }

    private boolean needsNewSession() {
        return this.sessionCredentials == null || this.sessionCredentialsExpiration.getTime() - System.currentTimeMillis() < KV.GET_CODE_INTERVAL;
    }
}
