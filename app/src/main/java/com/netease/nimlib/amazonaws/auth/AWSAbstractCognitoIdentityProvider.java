package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.regions.Region;
import com.netease.nimlib.amazonaws.regions.Regions;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AWSAbstractCognitoIdentityProvider implements AWSCognitoIdentityProvider {
    private final String accountId;
    protected final AmazonCognitoIdentity cib;
    protected String identityId;
    private final String identityPoolId;
    protected List<IdentityChangedListener> listeners;
    protected Map<String, String> loginsMap;
    protected String token;

    public abstract String getProviderName();

    protected String getUserAgent() {
        return "";
    }

    public AWSAbstractCognitoIdentityProvider(String str, String str2, AmazonCognitoIdentity amazonCognitoIdentity) {
        this.accountId = str;
        this.identityPoolId = str2;
        this.loginsMap = new HashMap();
        this.listeners = new ArrayList();
        this.cib = amazonCognitoIdentity;
    }

    @Deprecated
    public AWSAbstractCognitoIdentityProvider(String str, String str2, ClientConfiguration clientConfiguration) {
        this(str, str2, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public AWSAbstractCognitoIdentityProvider(String str, String str2, ClientConfiguration clientConfiguration, Regions regions) {
        this(str, str2, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
        this.cib.setRegion(Region.getRegion(regions));
    }

    @Deprecated
    public AWSAbstractCognitoIdentityProvider(String str, String str2) {
        this(str, str2, new ClientConfiguration());
    }

    public AWSAbstractCognitoIdentityProvider(String str, String str2, Regions regions) {
        this(str, str2, new ClientConfiguration(), regions);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public String getIdentityId() {
        if (this.identityId == null) {
            GetIdRequest withLogins = new GetIdRequest().withAccountId(getAccountId()).withIdentityPoolId(getIdentityPoolId()).withLogins(this.loginsMap);
            appendUserAgent(withLogins, getUserAgent());
            GetIdResult id = this.cib.getId(withLogins);
            if (id.getIdentityId() != null) {
                identityChanged(id.getIdentityId());
            }
        }
        return this.identityId;
    }

    protected void setIdentityId(String str) {
        this.identityId = str;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSIdentityProvider
    public String getToken() {
        if (this.token == null) {
            GetOpenIdTokenRequest withLogins = new GetOpenIdTokenRequest().withIdentityId(getIdentityId()).withLogins(this.loginsMap);
            appendUserAgent(withLogins, getUserAgent());
            GetOpenIdTokenResult openIdToken = this.cib.getOpenIdToken(withLogins);
            if (!openIdToken.getIdentityId().equals(getIdentityId())) {
                identityChanged(openIdToken.getIdentityId());
            }
            this.token = openIdToken.getToken();
        }
        return this.token;
    }

    protected void setToken(String str) {
        this.token = str;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public Map<String, String> getLogins() {
        return this.loginsMap;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public void setLogins(Map<String, String> map) {
        this.loginsMap = map;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public boolean isAuthenticated() {
        Map<String, String> map = this.loginsMap;
        return map != null && map.size() > 0;
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public void unregisterIdentityChangedListener(IdentityChangedListener identityChangedListener) {
        this.listeners.remove(identityChangedListener);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public void registerIdentityChangedListener(IdentityChangedListener identityChangedListener) {
        this.listeners.add(identityChangedListener);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public void identityChanged(String str) {
        String str2 = this.identityId;
        if (str2 == null || !str2.equals(str)) {
            String str3 = this.identityId;
            this.identityId = str;
            Iterator<IdentityChangedListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().identityChanged(str3, this.identityId);
            }
        }
    }

    protected void appendUserAgent(AmazonWebServiceRequest amazonWebServiceRequest, String str) {
        amazonWebServiceRequest.getRequestClientOptions().appendUserAgent(str);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSCognitoIdentityProvider
    public void clearListeners() {
        this.listeners.clear();
    }

    protected void update(String str, String str2) {
        String str3 = this.identityId;
        if (str3 == null || !str3.equals(str)) {
            identityChanged(str);
        }
        String str4 = this.token;
        if (str4 == null || !str4.equals(str2)) {
            this.token = str2;
        }
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSIdentityProvider
    public String refresh() {
        getIdentityId();
        String token = getToken();
        update(getIdentityId(), token);
        return token;
    }
}
