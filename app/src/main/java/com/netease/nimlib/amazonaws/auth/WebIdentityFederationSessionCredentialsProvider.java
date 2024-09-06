package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Credentials;
import java.util.Date;

/* loaded from: classes.dex */
public class WebIdentityFederationSessionCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    public static final int DEFAULT_THRESHOLD_SECONDS = 500;
    private int refreshThreshold;
    private final String roleArn;
    private final AWSSecurityTokenService securityTokenService;
    private AWSSessionCredentials sessionCredentials;
    private Date sessionCredentialsExpiration;
    private int sessionDuration;
    private String subjectFromWIF;
    private final String wifProvider;
    private final String wifToken;

    public WebIdentityFederationSessionCredentialsProvider(String str, String str2, String str3) {
        this(str, str2, str3, new ClientConfiguration());
    }

    public WebIdentityFederationSessionCredentialsProvider(String str, String str2, String str3, ClientConfiguration clientConfiguration) {
        this(str, str2, str3, new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public WebIdentityFederationSessionCredentialsProvider(String str, String str2, String str3, AWSSecurityTokenService aWSSecurityTokenService) {
        this.securityTokenService = aWSSecurityTokenService;
        this.wifProvider = str2;
        this.wifToken = str;
        this.roleArn = str3;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
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

    public void setSessionDuration(int i) {
        this.sessionDuration = i;
    }

    public WebIdentityFederationSessionCredentialsProvider withSessionDuration(int i) {
        setSessionDuration(i);
        return this;
    }

    public int getSessionDuration() {
        return this.sessionDuration;
    }

    public void setRefreshThreshold(int i) {
        this.refreshThreshold = i;
    }

    public WebIdentityFederationSessionCredentialsProvider withRefreshThreshold(int i) {
        setRefreshThreshold(i);
        return this;
    }

    public int getRefreshThreshold() {
        return this.refreshThreshold;
    }

    public String getSubjectFromWIF() {
        return this.subjectFromWIF;
    }

    private void startSession() {
        AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentity = this.securityTokenService.assumeRoleWithWebIdentity(new AssumeRoleWithWebIdentityRequest().withWebIdentityToken(this.wifToken).withProviderId(this.wifProvider).withRoleArn(this.roleArn).withRoleSessionName("ProviderSession").withDurationSeconds(Integer.valueOf(this.sessionDuration)));
        Credentials credentials = assumeRoleWithWebIdentity.getCredentials();
        this.subjectFromWIF = assumeRoleWithWebIdentity.getSubjectFromWebIdentityToken();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
        this.sessionCredentialsExpiration = credentials.getExpiration();
    }

    private boolean needsNewSession() {
        return this.sessionCredentials == null || this.sessionCredentialsExpiration.getTime() - System.currentTimeMillis() < ((long) (this.refreshThreshold * 1000));
    }
}
