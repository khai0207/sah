package com.netease.nimlib.amazonaws.auth;

import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Credentials;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetSessionTokenRequest;

@Deprecated
/* loaded from: classes.dex */
public class STSSessionCredentials implements AWSRefreshableSessionCredentials {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    private final AWSSecurityTokenService securityTokenService;
    private Credentials sessionCredentials;
    private final int sessionDurationSeconds;

    public STSSessionCredentials(AWSCredentials aWSCredentials) {
        this(aWSCredentials, 3600);
    }

    public STSSessionCredentials(AWSCredentials aWSCredentials, int i) {
        this.securityTokenService = new AWSSecurityTokenServiceClient(aWSCredentials);
        this.sessionDurationSeconds = i;
    }

    public STSSessionCredentials(AWSSecurityTokenService aWSSecurityTokenService) {
        this(aWSSecurityTokenService, 3600);
    }

    public STSSessionCredentials(AWSSecurityTokenService aWSSecurityTokenService, int i) {
        this.securityTokenService = aWSSecurityTokenService;
        this.sessionDurationSeconds = i;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public synchronized String getAWSAccessKeyId() {
        return getSessionCredentials().getAccessKeyId();
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCredentials
    public synchronized String getAWSSecretKey() {
        return getSessionCredentials().getSecretAccessKey();
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSSessionCredentials
    public synchronized String getSessionToken() {
        return getSessionCredentials().getSessionToken();
    }

    public synchronized AWSSessionCredentials getImmutableCredentials() {
        Credentials sessionCredentials;
        sessionCredentials = getSessionCredentials();
        return new BasicSessionCredentials(sessionCredentials.getAccessKeyId(), sessionCredentials.getSecretAccessKey(), sessionCredentials.getSessionToken());
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSRefreshableSessionCredentials
    public synchronized void refreshCredentials() {
        this.sessionCredentials = this.securityTokenService.getSessionToken(new GetSessionTokenRequest().withDurationSeconds(Integer.valueOf(this.sessionDurationSeconds))).getCredentials();
    }

    private synchronized Credentials getSessionCredentials() {
        if (needsNewSession()) {
            refreshCredentials();
        }
        return this.sessionCredentials;
    }

    private boolean needsNewSession() {
        Credentials credentials = this.sessionCredentials;
        return credentials == null || credentials.getExpiration().getTime() - System.currentTimeMillis() < KV.GET_CODE_INTERVAL;
    }
}
