package com.netease.nimlib.amazonaws.services.securitytoken;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.regions.Region;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithSAMLRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithSAMLResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.DecodeAuthorizationMessageRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.DecodeAuthorizationMessageResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetAccessKeyInfoRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetAccessKeyInfoResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetCallerIdentityResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetFederationTokenResult;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.netease.nimlib.amazonaws.services.securitytoken.model.GetSessionTokenResult;

/* loaded from: classes.dex */
public interface AWSSecurityTokenService {
    AssumeRoleResult assumeRole(AssumeRoleRequest assumeRoleRequest) throws AmazonClientException, AmazonServiceException;

    AssumeRoleWithSAMLResult assumeRoleWithSAML(AssumeRoleWithSAMLRequest assumeRoleWithSAMLRequest) throws AmazonClientException, AmazonServiceException;

    AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentity(AssumeRoleWithWebIdentityRequest assumeRoleWithWebIdentityRequest) throws AmazonClientException, AmazonServiceException;

    DecodeAuthorizationMessageResult decodeAuthorizationMessage(DecodeAuthorizationMessageRequest decodeAuthorizationMessageRequest) throws AmazonClientException, AmazonServiceException;

    GetAccessKeyInfoResult getAccessKeyInfo(GetAccessKeyInfoRequest getAccessKeyInfoRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetCallerIdentityResult getCallerIdentity() throws AmazonClientException, AmazonServiceException;

    GetCallerIdentityResult getCallerIdentity(GetCallerIdentityRequest getCallerIdentityRequest) throws AmazonClientException, AmazonServiceException;

    GetFederationTokenResult getFederationToken(GetFederationTokenRequest getFederationTokenRequest) throws AmazonClientException, AmazonServiceException;

    GetSessionTokenResult getSessionToken() throws AmazonClientException, AmazonServiceException;

    GetSessionTokenResult getSessionToken(GetSessionTokenRequest getSessionTokenRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();
}
