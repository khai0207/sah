package com.netease.nimlib.amazonaws.auth;

import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.regions.Region;
import com.netease.nimlib.amazonaws.regions.Regions;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;

/* loaded from: classes.dex */
public abstract class AWSAbstractCognitoDeveloperIdentityProvider extends AWSAbstractCognitoIdentityProvider {
    @Override // com.netease.nimlib.amazonaws.auth.AWSAbstractCognitoIdentityProvider
    public abstract String getProviderName();

    @Deprecated
    public AWSAbstractCognitoDeveloperIdentityProvider(String str, String str2) {
        this(str, str2, new ClientConfiguration());
    }

    public AWSAbstractCognitoDeveloperIdentityProvider(String str, String str2, Regions regions) {
        this(str, str2, new ClientConfiguration(), regions);
    }

    @Deprecated
    public AWSAbstractCognitoDeveloperIdentityProvider(String str, String str2, ClientConfiguration clientConfiguration) {
        this(str, str2, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public AWSAbstractCognitoDeveloperIdentityProvider(String str, String str2, ClientConfiguration clientConfiguration, Regions regions) {
        this(str, str2, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
        this.cib.setRegion(Region.getRegion(regions));
    }

    public AWSAbstractCognitoDeveloperIdentityProvider(String str, String str2, AmazonCognitoIdentity amazonCognitoIdentity) {
        super(str, str2, amazonCognitoIdentity);
    }
}
