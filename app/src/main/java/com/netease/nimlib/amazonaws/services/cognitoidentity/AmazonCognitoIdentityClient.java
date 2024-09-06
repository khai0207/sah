package com.netease.nimlib.amazonaws.services.cognitoidentity;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceClient;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.Response;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.netease.nimlib.amazonaws.handlers.HandlerChainFactory;
import com.netease.nimlib.amazonaws.http.ExecutionContext;
import com.netease.nimlib.amazonaws.http.HttpClient;
import com.netease.nimlib.amazonaws.http.HttpResponseHandler;
import com.netease.nimlib.amazonaws.http.JsonErrorResponseHandler;
import com.netease.nimlib.amazonaws.http.JsonResponseHandler;
import com.netease.nimlib.amazonaws.http.UrlHttpClient;
import com.netease.nimlib.amazonaws.internal.StaticCredentialsProvider;
import com.netease.nimlib.amazonaws.metrics.RequestMetricCollector;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.CreateIdentityPoolRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.CreateIdentityPoolResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DeleteIdentitiesRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DeleteIdentitiesResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DeleteIdentityPoolRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DescribeIdentityPoolRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DescribeIdentityPoolResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DescribeIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.DescribeIdentityResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetPrincipalTagAttributeMapRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.GetPrincipalTagAttributeMapResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListIdentitiesRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListIdentitiesResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListIdentityPoolsRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListIdentityPoolsResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListTagsForResourceRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.ListTagsForResourceResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.LookupDeveloperIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.LookupDeveloperIdentityResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.SetIdentityPoolRolesRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.SetPrincipalTagAttributeMapRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.SetPrincipalTagAttributeMapResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.TagResourceRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.TagResourceResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UnlinkDeveloperIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UnlinkIdentityRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UntagResourceRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UntagResourceResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UpdateIdentityPoolRequest;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.UpdateIdentityPoolResult;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ConcurrentModificationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.CreateIdentityPoolRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.CreateIdentityPoolResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DeleteIdentitiesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DeleteIdentitiesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DeleteIdentityPoolRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityPoolRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityPoolResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.DeveloperUserAlreadyRegisteredExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ExternalServiceExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetIdRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetIdResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetIdentityPoolRolesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetIdentityPoolRolesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenForDeveloperIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenForDeveloperIdentityResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetPrincipalTagAttributeMapRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.GetPrincipalTagAttributeMapResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.InternalErrorExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.InvalidIdentityPoolConfigurationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.InvalidParameterExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.LimitExceededExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListIdentitiesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListIdentitiesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListIdentityPoolsRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListIdentityPoolsResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListTagsForResourceRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ListTagsForResourceResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.LookupDeveloperIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.LookupDeveloperIdentityResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.MergeDeveloperIdentitiesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.MergeDeveloperIdentitiesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.NotAuthorizedExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ResourceConflictExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.SetIdentityPoolRolesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.SetPrincipalTagAttributeMapRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.SetPrincipalTagAttributeMapResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.TagResourceRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.TagResourceResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.TooManyRequestsExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UnlinkDeveloperIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UnlinkIdentityRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UntagResourceRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UntagResourceResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UpdateIdentityPoolRequestMarshaller;
import com.netease.nimlib.amazonaws.services.cognitoidentity.model.transform.UpdateIdentityPoolResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AmazonCognitoIdentityClient extends AmazonWebServiceClient implements AmazonCognitoIdentity {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AmazonCognitoIdentityClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AmazonCognitoIdentityClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        ArrayList arrayList = new ArrayList();
        this.jsonErrorUnmarshallers = arrayList;
        arrayList.add(new ConcurrentModificationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DeveloperUserAlreadyRegisteredExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ExternalServiceExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalErrorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidIdentityPoolConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidParameterExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotAuthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TooManyRequestsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("cognito-identity.us-east-1.amazonaws.com");
        this.endpointPrefix = "cognito-identity";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/cognitoidentity/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/cognitoidentity/request.handler2s"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public CreateIdentityPoolResult createIdentityPool(CreateIdentityPoolRequest createIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<CreateIdentityPoolRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateIdentityPoolRequestMarshaller().marshall(createIdentityPoolRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new CreateIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                    CreateIdentityPoolResult createIdentityPoolResult = (CreateIdentityPoolResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createIdentityPoolResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = createIdentityPoolRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DeleteIdentitiesResult deleteIdentities(DeleteIdentitiesRequest deleteIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DeleteIdentitiesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteIdentitiesRequestMarshaller().marshall(deleteIdentitiesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DeleteIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                    DeleteIdentitiesResult deleteIdentitiesResult = (DeleteIdentitiesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return deleteIdentitiesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = deleteIdentitiesRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.cognitoidentity.model.DeleteIdentityPoolRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void deleteIdentityPool(DeleteIdentityPoolRequest deleteIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteIdentityPoolRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteIdentityPoolRequestMarshaller().marshall((DeleteIdentityPoolRequest) deleteIdentityPoolRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                deleteIdentityPoolRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteIdentityPoolRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteIdentityPoolRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DescribeIdentityResult describeIdentity(DescribeIdentityRequest describeIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DescribeIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeIdentityRequestMarshaller().marshall(describeIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DescribeIdentityResultJsonUnmarshaller()), createExecutionContext);
                    DescribeIdentityResult describeIdentityResult = (DescribeIdentityResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeIdentityResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = describeIdentityRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DescribeIdentityPoolResult describeIdentityPool(DescribeIdentityPoolRequest describeIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DescribeIdentityPoolRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeIdentityPoolRequestMarshaller().marshall(describeIdentityPoolRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DescribeIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                    DescribeIdentityPoolResult describeIdentityPoolResult = (DescribeIdentityPoolResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeIdentityPoolResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = describeIdentityPoolRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetCredentialsForIdentityResult getCredentialsForIdentity(GetCredentialsForIdentityRequest getCredentialsForIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetCredentialsForIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getCredentialsForIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetCredentialsForIdentityRequestMarshaller().marshall(getCredentialsForIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetCredentialsForIdentityResultJsonUnmarshaller()), createExecutionContext);
                    GetCredentialsForIdentityResult getCredentialsForIdentityResult = (GetCredentialsForIdentityResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getCredentialsForIdentityResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getCredentialsForIdentityRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetIdResult getId(GetIdRequest getIdRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetIdRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getIdRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetIdRequestMarshaller().marshall(getIdRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetIdResultJsonUnmarshaller()), createExecutionContext);
                    GetIdResult getIdResult = (GetIdResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getIdResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getIdRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetIdentityPoolRolesResult getIdentityPoolRoles(GetIdentityPoolRolesRequest getIdentityPoolRolesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetIdentityPoolRolesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getIdentityPoolRolesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetIdentityPoolRolesRequestMarshaller().marshall(getIdentityPoolRolesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetIdentityPoolRolesResultJsonUnmarshaller()), createExecutionContext);
                    GetIdentityPoolRolesResult getIdentityPoolRolesResult = (GetIdentityPoolRolesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getIdentityPoolRolesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getIdentityPoolRolesRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetOpenIdTokenResult getOpenIdToken(GetOpenIdTokenRequest getOpenIdTokenRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetOpenIdTokenRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getOpenIdTokenRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetOpenIdTokenRequestMarshaller().marshall(getOpenIdTokenRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetOpenIdTokenResultJsonUnmarshaller()), createExecutionContext);
                    GetOpenIdTokenResult getOpenIdTokenResult = (GetOpenIdTokenResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getOpenIdTokenResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getOpenIdTokenRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentity(GetOpenIdTokenForDeveloperIdentityRequest getOpenIdTokenForDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetOpenIdTokenForDeveloperIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getOpenIdTokenForDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetOpenIdTokenForDeveloperIdentityRequestMarshaller().marshall(getOpenIdTokenForDeveloperIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetOpenIdTokenForDeveloperIdentityResultJsonUnmarshaller()), createExecutionContext);
                    GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentityResult = (GetOpenIdTokenForDeveloperIdentityResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getOpenIdTokenForDeveloperIdentityResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getOpenIdTokenForDeveloperIdentityRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetPrincipalTagAttributeMapResult getPrincipalTagAttributeMap(GetPrincipalTagAttributeMapRequest getPrincipalTagAttributeMapRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetPrincipalTagAttributeMapRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getPrincipalTagAttributeMapRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetPrincipalTagAttributeMapRequestMarshaller().marshall(getPrincipalTagAttributeMapRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetPrincipalTagAttributeMapResultJsonUnmarshaller()), createExecutionContext);
                    GetPrincipalTagAttributeMapResult getPrincipalTagAttributeMapResult = (GetPrincipalTagAttributeMapResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getPrincipalTagAttributeMapResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getPrincipalTagAttributeMapRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public ListIdentitiesResult listIdentities(ListIdentitiesRequest listIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListIdentitiesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListIdentitiesRequestMarshaller().marshall(listIdentitiesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                    ListIdentitiesResult listIdentitiesResult = (ListIdentitiesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listIdentitiesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listIdentitiesRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public ListIdentityPoolsResult listIdentityPools(ListIdentityPoolsRequest listIdentityPoolsRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListIdentityPoolsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listIdentityPoolsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListIdentityPoolsRequestMarshaller().marshall(listIdentityPoolsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListIdentityPoolsResultJsonUnmarshaller()), createExecutionContext);
                    ListIdentityPoolsResult listIdentityPoolsResult = (ListIdentityPoolsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listIdentityPoolsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listIdentityPoolsRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public ListTagsForResourceResult listTagsForResource(ListTagsForResourceRequest listTagsForResourceRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListTagsForResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listTagsForResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListTagsForResourceRequestMarshaller().marshall(listTagsForResourceRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListTagsForResourceResultJsonUnmarshaller()), createExecutionContext);
                    ListTagsForResourceResult listTagsForResourceResult = (ListTagsForResourceResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listTagsForResourceResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listTagsForResourceRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public LookupDeveloperIdentityResult lookupDeveloperIdentity(LookupDeveloperIdentityRequest lookupDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<LookupDeveloperIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(lookupDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new LookupDeveloperIdentityRequestMarshaller().marshall(lookupDeveloperIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new LookupDeveloperIdentityResultJsonUnmarshaller()), createExecutionContext);
                    LookupDeveloperIdentityResult lookupDeveloperIdentityResult = (LookupDeveloperIdentityResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return lookupDeveloperIdentityResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = lookupDeveloperIdentityRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public MergeDeveloperIdentitiesResult mergeDeveloperIdentities(MergeDeveloperIdentitiesRequest mergeDeveloperIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<MergeDeveloperIdentitiesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(mergeDeveloperIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new MergeDeveloperIdentitiesRequestMarshaller().marshall(mergeDeveloperIdentitiesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new MergeDeveloperIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                    MergeDeveloperIdentitiesResult mergeDeveloperIdentitiesResult = (MergeDeveloperIdentitiesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return mergeDeveloperIdentitiesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = mergeDeveloperIdentitiesRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.cognitoidentity.model.SetIdentityPoolRolesRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void setIdentityPoolRoles(SetIdentityPoolRolesRequest setIdentityPoolRolesRequest) throws AmazonServiceException, AmazonClientException {
        Request<SetIdentityPoolRolesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(setIdentityPoolRolesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new SetIdentityPoolRolesRequestMarshaller().marshall((SetIdentityPoolRolesRequest) setIdentityPoolRolesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                setIdentityPoolRolesRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, setIdentityPoolRolesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setIdentityPoolRolesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public SetPrincipalTagAttributeMapResult setPrincipalTagAttributeMap(SetPrincipalTagAttributeMapRequest setPrincipalTagAttributeMapRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<SetPrincipalTagAttributeMapRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(setPrincipalTagAttributeMapRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new SetPrincipalTagAttributeMapRequestMarshaller().marshall(setPrincipalTagAttributeMapRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new SetPrincipalTagAttributeMapResultJsonUnmarshaller()), createExecutionContext);
                    SetPrincipalTagAttributeMapResult setPrincipalTagAttributeMapResult = (SetPrincipalTagAttributeMapResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return setPrincipalTagAttributeMapResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = setPrincipalTagAttributeMapRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public TagResourceResult tagResource(TagResourceRequest tagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<TagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(tagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new TagResourceRequestMarshaller().marshall(tagResourceRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new TagResourceResultJsonUnmarshaller()), createExecutionContext);
                    TagResourceResult tagResourceResult = (TagResourceResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return tagResourceResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = tagResourceRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.cognitoidentity.model.UnlinkDeveloperIdentityRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void unlinkDeveloperIdentity(UnlinkDeveloperIdentityRequest unlinkDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Request<UnlinkDeveloperIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(unlinkDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UnlinkDeveloperIdentityRequestMarshaller().marshall((UnlinkDeveloperIdentityRequest) unlinkDeveloperIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                unlinkDeveloperIdentityRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, unlinkDeveloperIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, unlinkDeveloperIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.cognitoidentity.model.UnlinkIdentityRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void unlinkIdentity(UnlinkIdentityRequest unlinkIdentityRequest) throws AmazonServiceException, AmazonClientException {
        Request<UnlinkIdentityRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(unlinkIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UnlinkIdentityRequestMarshaller().marshall((UnlinkIdentityRequest) unlinkIdentityRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                unlinkIdentityRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, unlinkIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, unlinkIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<UntagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(untagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UntagResourceRequestMarshaller().marshall(untagResourceRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new UntagResourceResultJsonUnmarshaller()), createExecutionContext);
                    UntagResourceResult untagResourceResult = (UntagResourceResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return untagResourceResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = untagResourceRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public UpdateIdentityPoolResult updateIdentityPool(UpdateIdentityPoolRequest updateIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<UpdateIdentityPoolRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateIdentityPoolRequestMarshaller().marshall(updateIdentityPoolRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new UpdateIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                    UpdateIdentityPoolResult updateIdentityPoolResult = (UpdateIdentityPoolResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return updateIdentityPoolResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = updateIdentityPoolRequest;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, request, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, request, response, true);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> httpResponseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
        try {
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(credentials);
            return this.client.execute(request, httpResponseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            throw th;
        }
    }
}
