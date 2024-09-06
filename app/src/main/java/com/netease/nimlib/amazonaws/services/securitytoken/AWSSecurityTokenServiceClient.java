package com.netease.nimlib.amazonaws.services.securitytoken;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceClient;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.Response;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.netease.nimlib.amazonaws.handlers.HandlerChainFactory;
import com.netease.nimlib.amazonaws.http.DefaultErrorResponseHandler;
import com.netease.nimlib.amazonaws.http.ExecutionContext;
import com.netease.nimlib.amazonaws.http.HttpClient;
import com.netease.nimlib.amazonaws.http.StaxResponseHandler;
import com.netease.nimlib.amazonaws.http.UrlHttpClient;
import com.netease.nimlib.amazonaws.internal.StaticCredentialsProvider;
import com.netease.nimlib.amazonaws.metrics.RequestMetricCollector;
import com.netease.nimlib.amazonaws.regions.ServiceAbbreviations;
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
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleWithSAMLRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleWithSAMLResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleWithWebIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.AssumeRoleWithWebIdentityResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.DecodeAuthorizationMessageRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.DecodeAuthorizationMessageResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.ExpiredTokenExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetAccessKeyInfoRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetAccessKeyInfoResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetCallerIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetCallerIdentityResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetFederationTokenRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetFederationTokenResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetSessionTokenRequestMarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.GetSessionTokenResultStaxUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.IDPCommunicationErrorExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.IDPRejectedClaimExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.InvalidAuthorizationMessageExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.InvalidIdentityTokenExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.MalformedPolicyDocumentExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.PackedPolicyTooLargeExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.securitytoken.model.transform.RegionDisabledExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class AWSSecurityTokenServiceClient extends AmazonWebServiceClient implements AWSSecurityTokenService {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AWSSecurityTokenServiceClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AWSSecurityTokenServiceClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSSecurityTokenServiceClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSSecurityTokenServiceClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSSecurityTokenServiceClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSSecurityTokenServiceClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSSecurityTokenServiceClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.exceptionUnmarshallers = new ArrayList();
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSSecurityTokenServiceClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.exceptionUnmarshallers = new ArrayList();
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        this.exceptionUnmarshallers.add(new ExpiredTokenExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new IDPCommunicationErrorExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new IDPRejectedClaimExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new InvalidAuthorizationMessageExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new InvalidIdentityTokenExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new MalformedPolicyDocumentExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new PackedPolicyTooLargeExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new RegionDisabledExceptionUnmarshaller());
        this.exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        setEndpoint("sts.amazonaws.com");
        this.endpointPrefix = ServiceAbbreviations.STS;
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/securitytoken/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/securitytoken/request.handler2s"));
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public AssumeRoleResult assumeRole(AssumeRoleRequest assumeRoleRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<AssumeRoleRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(assumeRoleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<AssumeRoleRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new AssumeRoleRequestMarshaller().marshall(assumeRoleRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new AssumeRoleResultStaxUnmarshaller(), createExecutionContext);
            AssumeRoleResult assumeRoleResult = (AssumeRoleResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return assumeRoleResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public AssumeRoleWithSAMLResult assumeRoleWithSAML(AssumeRoleWithSAMLRequest assumeRoleWithSAMLRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<AssumeRoleWithSAMLRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(assumeRoleWithSAMLRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<AssumeRoleWithSAMLRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new AssumeRoleWithSAMLRequestMarshaller().marshall(assumeRoleWithSAMLRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new AssumeRoleWithSAMLResultStaxUnmarshaller(), createExecutionContext);
            AssumeRoleWithSAMLResult assumeRoleWithSAMLResult = (AssumeRoleWithSAMLResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return assumeRoleWithSAMLResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentity(AssumeRoleWithWebIdentityRequest assumeRoleWithWebIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<AssumeRoleWithWebIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(assumeRoleWithWebIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<AssumeRoleWithWebIdentityRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new AssumeRoleWithWebIdentityRequestMarshaller().marshall(assumeRoleWithWebIdentityRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new AssumeRoleWithWebIdentityResultStaxUnmarshaller(), createExecutionContext);
            AssumeRoleWithWebIdentityResult assumeRoleWithWebIdentityResult = (AssumeRoleWithWebIdentityResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return assumeRoleWithWebIdentityResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public DecodeAuthorizationMessageResult decodeAuthorizationMessage(DecodeAuthorizationMessageRequest decodeAuthorizationMessageRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DecodeAuthorizationMessageRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(decodeAuthorizationMessageRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<DecodeAuthorizationMessageRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new DecodeAuthorizationMessageRequestMarshaller().marshall(decodeAuthorizationMessageRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new DecodeAuthorizationMessageResultStaxUnmarshaller(), createExecutionContext);
            DecodeAuthorizationMessageResult decodeAuthorizationMessageResult = (DecodeAuthorizationMessageResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return decodeAuthorizationMessageResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetAccessKeyInfoResult getAccessKeyInfo(GetAccessKeyInfoRequest getAccessKeyInfoRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetAccessKeyInfoRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getAccessKeyInfoRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<GetAccessKeyInfoRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new GetAccessKeyInfoRequestMarshaller().marshall(getAccessKeyInfoRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new GetAccessKeyInfoResultStaxUnmarshaller(), createExecutionContext);
            GetAccessKeyInfoResult getAccessKeyInfoResult = (GetAccessKeyInfoResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return getAccessKeyInfoResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetCallerIdentityResult getCallerIdentity(GetCallerIdentityRequest getCallerIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetCallerIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getCallerIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<GetCallerIdentityRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new GetCallerIdentityRequestMarshaller().marshall(getCallerIdentityRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new GetCallerIdentityResultStaxUnmarshaller(), createExecutionContext);
            GetCallerIdentityResult getCallerIdentityResult = (GetCallerIdentityResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return getCallerIdentityResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetFederationTokenResult getFederationToken(GetFederationTokenRequest getFederationTokenRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetFederationTokenRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getFederationTokenRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<GetFederationTokenRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new GetFederationTokenRequestMarshaller().marshall(getFederationTokenRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new GetFederationTokenResultStaxUnmarshaller(), createExecutionContext);
            GetFederationTokenResult getFederationTokenResult = (GetFederationTokenResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return getFederationTokenResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetSessionTokenResult getSessionToken(GetSessionTokenRequest getSessionTokenRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetSessionTokenRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getSessionTokenRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<GetSessionTokenRequest> request = null;
        Response<?> response2 = null;
        try {
            marshall = new GetSessionTokenRequestMarshaller().marshall(getSessionTokenRequest);
        } catch (Throwable th) {
            th = th;
            response = null;
        }
        try {
            marshall.setAWSRequestMetrics(awsRequestMetrics);
            response2 = invoke(marshall, new GetSessionTokenResultStaxUnmarshaller(), createExecutionContext);
            GetSessionTokenResult getSessionTokenResult = (GetSessionTokenResult) response2.getAwsResponse();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, marshall, response2);
            return getSessionTokenResult;
        } catch (Throwable th2) {
            th = th2;
            Response<?> response3 = response2;
            request = marshall;
            response = response3;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetSessionTokenResult getSessionToken() throws AmazonServiceException, AmazonClientException {
        return getSessionToken(new GetSessionTokenRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    public GetCallerIdentityResult getCallerIdentity() throws AmazonServiceException, AmazonClientException {
        return getCallerIdentity(new GetCallerIdentityRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.securitytoken.AWSSecurityTokenService
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, Unmarshaller<X, StaxUnmarshallerContext> unmarshaller, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
        if (originalRequest.getRequestCredentials() != null) {
            credentials = originalRequest.getRequestCredentials();
        }
        executionContext.setCredentials(credentials);
        return this.client.execute(request, new StaxResponseHandler(unmarshaller), new DefaultErrorResponseHandler(this.exceptionUnmarshallers), executionContext);
    }
}
