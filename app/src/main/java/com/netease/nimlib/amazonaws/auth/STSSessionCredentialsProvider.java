package com.netease.nimlib.amazonaws.auth;

import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Credentials;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import java.util.Date;

/* loaded from: classes.dex */
public class STSSessionCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    private final AWSSecurityTokenService securityTokenService;
    private AWSSessionCredentials sessionCredentials;
    private Date sessionCredentialsExpiration;

    public STSSessionCredentialsProvider(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public STSSessionCredentialsProvider(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentials, clientConfiguration);
    }

    public STSSessionCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider) {
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentialsProvider);
    }

    public STSSessionCredentialsProvider(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
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
        Credentials credentials = this.securityTokenService.getSessionToken(new GetSessionTokenRequest().withDurationSeconds(3600)).getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
        this.sessionCredentialsExpiration = credentials.getExpiration();
    }

    private boolean needsNewSession() {
        return this.sessionCredentials == null || this.sessionCredentialsExpiration.getTime() - System.currentTimeMillis() < KV.GET_CODE_INTERVAL;
    }
}
