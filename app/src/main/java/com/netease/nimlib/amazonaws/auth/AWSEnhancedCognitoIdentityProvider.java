package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;

/* loaded from: classes.dex */
public final class AWSEnhancedCognitoIdentityProvider extends AWSAbstractCognitoIdentityProvider {
    @Override // com.netease.nimlib.amazonaws.auth.AWSAbstractCognitoIdentityProvider
    public String getProviderName() {
        return "Cognito";
    }

    public AWSEnhancedCognitoIdentityProvider(String str, String str2) {
        this(str, str2, new ClientConfiguration());
    }

    public AWSEnhancedCognitoIdentityProvider(String str, String str2, ClientConfiguration clientConfiguration) {
        this(str, str2, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public AWSEnhancedCognitoIdentityProvider(String str, String str2, AmazonCognitoIdentity amazonCognitoIdentity) {
        super(str, str2, amazonCognitoIdentity);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AWSAbstractCognitoIdentityProvider, com.netease.nimlib.amazonaws.auth.AWSIdentityProvider
    public String refresh() {
        getIdentityId();
        return null;
    }
}
