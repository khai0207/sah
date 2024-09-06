package com.netease.nimlib.amazonaws.services.kms;

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
import com.netease.nimlib.amazonaws.services.kms.model.CancelKeyDeletionRequest;
import com.netease.nimlib.amazonaws.services.kms.model.CancelKeyDeletionResult;
import com.netease.nimlib.amazonaws.services.kms.model.ConnectCustomKeyStoreRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ConnectCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.services.kms.model.CreateAliasRequest;
import com.netease.nimlib.amazonaws.services.kms.model.CreateCustomKeyStoreRequest;
import com.netease.nimlib.amazonaws.services.kms.model.CreateCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.services.kms.model.CreateGrantRequest;
import com.netease.nimlib.amazonaws.services.kms.model.CreateGrantResult;
import com.netease.nimlib.amazonaws.services.kms.model.CreateKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.CreateKeyResult;
import com.netease.nimlib.amazonaws.services.kms.model.DecryptRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DecryptResult;
import com.netease.nimlib.amazonaws.services.kms.model.DeleteAliasRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DeleteCustomKeyStoreRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DeleteCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.services.kms.model.DeleteImportedKeyMaterialRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DescribeCustomKeyStoresRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DescribeCustomKeyStoresResult;
import com.netease.nimlib.amazonaws.services.kms.model.DescribeKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DescribeKeyResult;
import com.netease.nimlib.amazonaws.services.kms.model.DisableKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DisableKeyRotationRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DisconnectCustomKeyStoreRequest;
import com.netease.nimlib.amazonaws.services.kms.model.DisconnectCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.services.kms.model.EnableKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.EnableKeyRotationRequest;
import com.netease.nimlib.amazonaws.services.kms.model.EncryptRequest;
import com.netease.nimlib.amazonaws.services.kms.model.EncryptResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyPairRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyPairResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyPairWithoutPlaintextRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyPairWithoutPlaintextResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateMacRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateMacResult;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateRandomRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateRandomResult;
import com.netease.nimlib.amazonaws.services.kms.model.GetKeyPolicyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GetKeyPolicyResult;
import com.netease.nimlib.amazonaws.services.kms.model.GetKeyRotationStatusRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GetKeyRotationStatusResult;
import com.netease.nimlib.amazonaws.services.kms.model.GetParametersForImportRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GetParametersForImportResult;
import com.netease.nimlib.amazonaws.services.kms.model.GetPublicKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GetPublicKeyResult;
import com.netease.nimlib.amazonaws.services.kms.model.ImportKeyMaterialRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ImportKeyMaterialResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListAliasesRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListAliasesResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListGrantsRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListGrantsResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListKeyPoliciesRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListKeyPoliciesResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListKeysRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListKeysResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListResourceTagsRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListResourceTagsResult;
import com.netease.nimlib.amazonaws.services.kms.model.ListRetirableGrantsRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ListRetirableGrantsResult;
import com.netease.nimlib.amazonaws.services.kms.model.PutKeyPolicyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ReEncryptRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ReEncryptResult;
import com.netease.nimlib.amazonaws.services.kms.model.ReplicateKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ReplicateKeyResult;
import com.netease.nimlib.amazonaws.services.kms.model.RetireGrantRequest;
import com.netease.nimlib.amazonaws.services.kms.model.RevokeGrantRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ScheduleKeyDeletionRequest;
import com.netease.nimlib.amazonaws.services.kms.model.ScheduleKeyDeletionResult;
import com.netease.nimlib.amazonaws.services.kms.model.SignRequest;
import com.netease.nimlib.amazonaws.services.kms.model.SignResult;
import com.netease.nimlib.amazonaws.services.kms.model.TagResourceRequest;
import com.netease.nimlib.amazonaws.services.kms.model.UntagResourceRequest;
import com.netease.nimlib.amazonaws.services.kms.model.UpdateAliasRequest;
import com.netease.nimlib.amazonaws.services.kms.model.UpdateCustomKeyStoreRequest;
import com.netease.nimlib.amazonaws.services.kms.model.UpdateCustomKeyStoreResult;
import com.netease.nimlib.amazonaws.services.kms.model.UpdateKeyDescriptionRequest;
import com.netease.nimlib.amazonaws.services.kms.model.UpdatePrimaryRegionRequest;
import com.netease.nimlib.amazonaws.services.kms.model.VerifyMacRequest;
import com.netease.nimlib.amazonaws.services.kms.model.VerifyMacResult;
import com.netease.nimlib.amazonaws.services.kms.model.VerifyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.VerifyResult;
import com.netease.nimlib.amazonaws.services.kms.model.transform.AlreadyExistsExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CancelKeyDeletionRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CancelKeyDeletionResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CloudHsmClusterInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CloudHsmClusterInvalidConfigurationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CloudHsmClusterNotActiveExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CloudHsmClusterNotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CloudHsmClusterNotRelatedExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ConnectCustomKeyStoreRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ConnectCustomKeyStoreResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateAliasRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateCustomKeyStoreRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateCustomKeyStoreResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateGrantRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateGrantResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CreateKeyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CustomKeyStoreHasCMKsExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CustomKeyStoreInvalidStateExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CustomKeyStoreNameInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.CustomKeyStoreNotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DecryptRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DecryptResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DeleteAliasRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DeleteCustomKeyStoreRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DeleteCustomKeyStoreResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DeleteImportedKeyMaterialRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DependencyTimeoutExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DescribeCustomKeyStoresRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DescribeCustomKeyStoresResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DescribeKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DescribeKeyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DisableKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DisableKeyRotationRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DisabledExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DisconnectCustomKeyStoreRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DisconnectCustomKeyStoreResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.DryRunOperationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.EnableKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.EnableKeyRotationRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.EncryptRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.EncryptResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ExpiredImportTokenExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyPairRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyPairResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyPairWithoutPlaintextRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyPairWithoutPlaintextResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyWithoutPlaintextRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateMacRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateMacResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateRandomRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GenerateRandomResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetKeyPolicyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetKeyPolicyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetKeyRotationStatusRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetKeyRotationStatusResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetParametersForImportRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetParametersForImportResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetPublicKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.GetPublicKeyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ImportKeyMaterialRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ImportKeyMaterialResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.IncorrectKeyExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.IncorrectKeyMaterialExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.IncorrectTrustAnchorExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidAliasNameExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidArnExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidCiphertextExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidGrantIdExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidGrantTokenExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidImportTokenExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidKeyUsageExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.InvalidMarkerExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.KMSInternalExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.KMSInvalidMacExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.KMSInvalidSignatureExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.KMSInvalidStateExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.KeyUnavailableExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.LimitExceededExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListAliasesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListAliasesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListGrantsRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListGrantsResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListKeyPoliciesRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListKeyPoliciesResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListKeysRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListKeysResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListResourceTagsRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListResourceTagsResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListRetirableGrantsRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ListRetirableGrantsResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.MalformedPolicyDocumentExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.NotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.PutKeyPolicyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ReEncryptRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ReEncryptResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ReplicateKeyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ReplicateKeyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.RetireGrantRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.RevokeGrantRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ScheduleKeyDeletionRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.ScheduleKeyDeletionResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.SignRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.SignResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.TagExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.TagResourceRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UnsupportedOperationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UntagResourceRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UpdateAliasRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UpdateCustomKeyStoreRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UpdateCustomKeyStoreResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UpdateKeyDescriptionRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.UpdatePrimaryRegionRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.VerifyMacRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.VerifyMacResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.VerifyRequestMarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.VerifyResultJsonUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksKeyAlreadyInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksKeyInvalidConfigurationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksKeyNotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyIncorrectAuthenticationCredentialExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyInvalidConfigurationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyInvalidResponseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyUriEndpointInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyUriInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyUriUnreachableExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyVpcEndpointServiceInUseExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyVpcEndpointServiceInvalidConfigurationExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.services.kms.model.transform.XksProxyVpcEndpointServiceNotFoundExceptionUnmarshaller;
import com.netease.nimlib.amazonaws.transform.JsonErrorUnmarshaller;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AWSKMSClient extends AmazonWebServiceClient implements AWSKMS {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AWSKMSClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AWSKMSClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSKMSClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSKMSClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSKMSClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        ArrayList arrayList = new ArrayList();
        this.jsonErrorUnmarshallers = arrayList;
        arrayList.add(new AlreadyExistsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterInvalidConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotActiveExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CloudHsmClusterNotRelatedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreHasCMKsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreInvalidStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreNameInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CustomKeyStoreNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DependencyTimeoutExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DisabledExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DryRunOperationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ExpiredImportTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectKeyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectKeyMaterialExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IncorrectTrustAnchorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidAliasNameExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidArnExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidCiphertextExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidGrantIdExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidGrantTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidImportTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidKeyUsageExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidMarkerExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInternalExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidMacExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidSignatureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KeyUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MalformedPolicyDocumentExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TagExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnsupportedOperationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksKeyAlreadyInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksKeyInvalidConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksKeyNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyIncorrectAuthenticationCredentialExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyInvalidConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyInvalidResponseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyUriEndpointInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyUriInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyUriUnreachableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyVpcEndpointServiceInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyVpcEndpointServiceInvalidConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new XksProxyVpcEndpointServiceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("kms.us-east-1.amazonaws.com");
        this.endpointPrefix = "kms";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/kms/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/kms/request.handler2s"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public CancelKeyDeletionResult cancelKeyDeletion(CancelKeyDeletionRequest cancelKeyDeletionRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<CancelKeyDeletionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(cancelKeyDeletionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CancelKeyDeletionRequestMarshaller().marshall(cancelKeyDeletionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new CancelKeyDeletionResultJsonUnmarshaller()), createExecutionContext);
                    CancelKeyDeletionResult cancelKeyDeletionResult = (CancelKeyDeletionResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return cancelKeyDeletionResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = cancelKeyDeletionRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ConnectCustomKeyStoreResult connectCustomKeyStore(ConnectCustomKeyStoreRequest connectCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ConnectCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(connectCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ConnectCustomKeyStoreRequestMarshaller().marshall(connectCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ConnectCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    ConnectCustomKeyStoreResult connectCustomKeyStoreResult = (ConnectCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return connectCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = connectCustomKeyStoreRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.CreateAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void createAlias(CreateAliasRequest createAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<CreateAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateAliasRequestMarshaller().marshall((CreateAliasRequest) createAliasRequest);
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
                createAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public CreateCustomKeyStoreResult createCustomKeyStore(CreateCustomKeyStoreRequest createCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<CreateCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateCustomKeyStoreRequestMarshaller().marshall(createCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new CreateCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    CreateCustomKeyStoreResult createCustomKeyStoreResult = (CreateCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = createCustomKeyStoreRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public CreateGrantResult createGrant(CreateGrantRequest createGrantRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<CreateGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateGrantRequestMarshaller().marshall(createGrantRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new CreateGrantResultJsonUnmarshaller()), createExecutionContext);
                    CreateGrantResult createGrantResult = (CreateGrantResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createGrantResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = createGrantRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public CreateKeyResult createKey(CreateKeyRequest createKeyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<CreateKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(createKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new CreateKeyRequestMarshaller().marshall(createKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new CreateKeyResultJsonUnmarshaller()), createExecutionContext);
                    CreateKeyResult createKeyResult = (CreateKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return createKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = createKeyRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public DecryptResult decrypt(DecryptRequest decryptRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DecryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(decryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DecryptRequestMarshaller().marshall(decryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DecryptResultJsonUnmarshaller()), createExecutionContext);
                    DecryptResult decryptResult = (DecryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return decryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = decryptRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.DeleteAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void deleteAlias(DeleteAliasRequest deleteAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteAliasRequestMarshaller().marshall((DeleteAliasRequest) deleteAliasRequest);
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
                deleteAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public DeleteCustomKeyStoreResult deleteCustomKeyStore(DeleteCustomKeyStoreRequest deleteCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DeleteCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteCustomKeyStoreRequestMarshaller().marshall(deleteCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DeleteCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    DeleteCustomKeyStoreResult deleteCustomKeyStoreResult = (DeleteCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return deleteCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = deleteCustomKeyStoreRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.DeleteImportedKeyMaterialRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void deleteImportedKeyMaterial(DeleteImportedKeyMaterialRequest deleteImportedKeyMaterialRequest) throws AmazonServiceException, AmazonClientException {
        Request<DeleteImportedKeyMaterialRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(deleteImportedKeyMaterialRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DeleteImportedKeyMaterialRequestMarshaller().marshall((DeleteImportedKeyMaterialRequest) deleteImportedKeyMaterialRequest);
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
                deleteImportedKeyMaterialRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteImportedKeyMaterialRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteImportedKeyMaterialRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public DescribeCustomKeyStoresResult describeCustomKeyStores(DescribeCustomKeyStoresRequest describeCustomKeyStoresRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DescribeCustomKeyStoresRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeCustomKeyStoresRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeCustomKeyStoresRequestMarshaller().marshall(describeCustomKeyStoresRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DescribeCustomKeyStoresResultJsonUnmarshaller()), createExecutionContext);
                    DescribeCustomKeyStoresResult describeCustomKeyStoresResult = (DescribeCustomKeyStoresResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeCustomKeyStoresResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = describeCustomKeyStoresRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public DescribeKeyResult describeKey(DescribeKeyRequest describeKeyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DescribeKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(describeKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DescribeKeyRequestMarshaller().marshall(describeKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DescribeKeyResultJsonUnmarshaller()), createExecutionContext);
                    DescribeKeyResult describeKeyResult = (DescribeKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return describeKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = describeKeyRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.DisableKeyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void disableKey(DisableKeyRequest disableKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<DisableKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disableKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisableKeyRequestMarshaller().marshall((DisableKeyRequest) disableKeyRequest);
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
                disableKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.DisableKeyRotationRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void disableKeyRotation(DisableKeyRotationRequest disableKeyRotationRequest) throws AmazonServiceException, AmazonClientException {
        Request<DisableKeyRotationRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disableKeyRotationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisableKeyRotationRequestMarshaller().marshall((DisableKeyRotationRequest) disableKeyRotationRequest);
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
                disableKeyRotationRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableKeyRotationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableKeyRotationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public DisconnectCustomKeyStoreResult disconnectCustomKeyStore(DisconnectCustomKeyStoreRequest disconnectCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<DisconnectCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(disconnectCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new DisconnectCustomKeyStoreRequestMarshaller().marshall(disconnectCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new DisconnectCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    DisconnectCustomKeyStoreResult disconnectCustomKeyStoreResult = (DisconnectCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return disconnectCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = disconnectCustomKeyStoreRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.EnableKeyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void enableKey(EnableKeyRequest enableKeyRequest) throws AmazonServiceException, AmazonClientException {
        Request<EnableKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(enableKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EnableKeyRequestMarshaller().marshall((EnableKeyRequest) enableKeyRequest);
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
                enableKeyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableKeyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableKeyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.EnableKeyRotationRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void enableKeyRotation(EnableKeyRotationRequest enableKeyRotationRequest) throws AmazonServiceException, AmazonClientException {
        Request<EnableKeyRotationRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(enableKeyRotationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EnableKeyRotationRequestMarshaller().marshall((EnableKeyRotationRequest) enableKeyRotationRequest);
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
                enableKeyRotationRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableKeyRotationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableKeyRotationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public EncryptResult encrypt(EncryptRequest encryptRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<EncryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(encryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new EncryptRequestMarshaller().marshall(encryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new EncryptResultJsonUnmarshaller()), createExecutionContext);
                    EncryptResult encryptResult = (EncryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return encryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = encryptRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyResult generateDataKey(GenerateDataKeyRequest generateDataKeyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateDataKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyRequestMarshaller().marshall(generateDataKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyResult generateDataKeyResult = (GenerateDataKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateDataKeyRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyPairResult generateDataKeyPair(GenerateDataKeyPairRequest generateDataKeyPairRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateDataKeyPairRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyPairRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyPairRequestMarshaller().marshall(generateDataKeyPairRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyPairResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyPairResult generateDataKeyPairResult = (GenerateDataKeyPairResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyPairResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateDataKeyPairRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintext(GenerateDataKeyPairWithoutPlaintextRequest generateDataKeyPairWithoutPlaintextRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateDataKeyPairWithoutPlaintextRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyPairWithoutPlaintextRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyPairWithoutPlaintextRequestMarshaller().marshall(generateDataKeyPairWithoutPlaintextRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyPairWithoutPlaintextResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintextResult = (GenerateDataKeyPairWithoutPlaintextResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyPairWithoutPlaintextResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateDataKeyPairWithoutPlaintextRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintext(GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateDataKeyWithoutPlaintextRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateDataKeyWithoutPlaintextRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateDataKeyWithoutPlaintextRequestMarshaller().marshall(generateDataKeyWithoutPlaintextRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateDataKeyWithoutPlaintextResultJsonUnmarshaller()), createExecutionContext);
                    GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintextResult = (GenerateDataKeyWithoutPlaintextResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateDataKeyWithoutPlaintextResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateDataKeyWithoutPlaintextRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateMacResult generateMac(GenerateMacRequest generateMacRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateMacRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateMacRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateMacRequestMarshaller().marshall(generateMacRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateMacResultJsonUnmarshaller()), createExecutionContext);
                    GenerateMacResult generateMacResult = (GenerateMacResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateMacResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateMacRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateRandomResult generateRandom(GenerateRandomRequest generateRandomRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GenerateRandomRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(generateRandomRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GenerateRandomRequestMarshaller().marshall(generateRandomRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GenerateRandomResultJsonUnmarshaller()), createExecutionContext);
                    GenerateRandomResult generateRandomResult = (GenerateRandomResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return generateRandomResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = generateRandomRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GetKeyPolicyResult getKeyPolicy(GetKeyPolicyRequest getKeyPolicyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetKeyPolicyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getKeyPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetKeyPolicyRequestMarshaller().marshall(getKeyPolicyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetKeyPolicyResultJsonUnmarshaller()), createExecutionContext);
                    GetKeyPolicyResult getKeyPolicyResult = (GetKeyPolicyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getKeyPolicyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getKeyPolicyRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GetKeyRotationStatusResult getKeyRotationStatus(GetKeyRotationStatusRequest getKeyRotationStatusRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetKeyRotationStatusRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getKeyRotationStatusRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetKeyRotationStatusRequestMarshaller().marshall(getKeyRotationStatusRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetKeyRotationStatusResultJsonUnmarshaller()), createExecutionContext);
                    GetKeyRotationStatusResult getKeyRotationStatusResult = (GetKeyRotationStatusResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getKeyRotationStatusResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getKeyRotationStatusRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GetParametersForImportResult getParametersForImport(GetParametersForImportRequest getParametersForImportRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetParametersForImportRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getParametersForImportRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetParametersForImportRequestMarshaller().marshall(getParametersForImportRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetParametersForImportResultJsonUnmarshaller()), createExecutionContext);
                    GetParametersForImportResult getParametersForImportResult = (GetParametersForImportResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getParametersForImportResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getParametersForImportRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GetPublicKeyResult getPublicKey(GetPublicKeyRequest getPublicKeyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<GetPublicKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(getPublicKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new GetPublicKeyRequestMarshaller().marshall(getPublicKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new GetPublicKeyResultJsonUnmarshaller()), createExecutionContext);
                    GetPublicKeyResult getPublicKeyResult = (GetPublicKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return getPublicKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = getPublicKeyRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ImportKeyMaterialResult importKeyMaterial(ImportKeyMaterialRequest importKeyMaterialRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ImportKeyMaterialRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(importKeyMaterialRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ImportKeyMaterialRequestMarshaller().marshall(importKeyMaterialRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ImportKeyMaterialResultJsonUnmarshaller()), createExecutionContext);
                    ImportKeyMaterialResult importKeyMaterialResult = (ImportKeyMaterialResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return importKeyMaterialResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = importKeyMaterialRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListAliasesResult listAliases(ListAliasesRequest listAliasesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListAliasesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listAliasesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListAliasesRequestMarshaller().marshall(listAliasesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListAliasesResultJsonUnmarshaller()), createExecutionContext);
                    ListAliasesResult listAliasesResult = (ListAliasesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listAliasesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listAliasesRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListGrantsResult listGrants(ListGrantsRequest listGrantsRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListGrantsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listGrantsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListGrantsRequestMarshaller().marshall(listGrantsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListGrantsResultJsonUnmarshaller()), createExecutionContext);
                    ListGrantsResult listGrantsResult = (ListGrantsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listGrantsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listGrantsRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListKeyPoliciesResult listKeyPolicies(ListKeyPoliciesRequest listKeyPoliciesRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListKeyPoliciesRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listKeyPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListKeyPoliciesRequestMarshaller().marshall(listKeyPoliciesRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListKeyPoliciesResultJsonUnmarshaller()), createExecutionContext);
                    ListKeyPoliciesResult listKeyPoliciesResult = (ListKeyPoliciesResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listKeyPoliciesResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listKeyPoliciesRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListKeysResult listKeys(ListKeysRequest listKeysRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListKeysRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listKeysRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListKeysRequestMarshaller().marshall(listKeysRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListKeysResultJsonUnmarshaller()), createExecutionContext);
                    ListKeysResult listKeysResult = (ListKeysResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listKeysResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listKeysRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListResourceTagsResult listResourceTags(ListResourceTagsRequest listResourceTagsRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListResourceTagsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listResourceTagsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListResourceTagsRequestMarshaller().marshall(listResourceTagsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListResourceTagsResultJsonUnmarshaller()), createExecutionContext);
                    ListResourceTagsResult listResourceTagsResult = (ListResourceTagsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listResourceTagsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listResourceTagsRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListRetirableGrantsResult listRetirableGrants(ListRetirableGrantsRequest listRetirableGrantsRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ListRetirableGrantsRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(listRetirableGrantsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ListRetirableGrantsRequestMarshaller().marshall(listRetirableGrantsRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ListRetirableGrantsResultJsonUnmarshaller()), createExecutionContext);
                    ListRetirableGrantsResult listRetirableGrantsResult = (ListRetirableGrantsResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return listRetirableGrantsResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = listRetirableGrantsRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.PutKeyPolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void putKeyPolicy(PutKeyPolicyRequest putKeyPolicyRequest) throws AmazonServiceException, AmazonClientException {
        Request<PutKeyPolicyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(putKeyPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new PutKeyPolicyRequestMarshaller().marshall((PutKeyPolicyRequest) putKeyPolicyRequest);
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
                putKeyPolicyRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, putKeyPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, putKeyPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ReEncryptResult reEncrypt(ReEncryptRequest reEncryptRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ReEncryptRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(reEncryptRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ReEncryptRequestMarshaller().marshall(reEncryptRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ReEncryptResultJsonUnmarshaller()), createExecutionContext);
                    ReEncryptResult reEncryptResult = (ReEncryptResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return reEncryptResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = reEncryptRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ReplicateKeyResult replicateKey(ReplicateKeyRequest replicateKeyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ReplicateKeyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(replicateKeyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ReplicateKeyRequestMarshaller().marshall(replicateKeyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ReplicateKeyResultJsonUnmarshaller()), createExecutionContext);
                    ReplicateKeyResult replicateKeyResult = (ReplicateKeyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return replicateKeyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = replicateKeyRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.RetireGrantRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void retireGrant(RetireGrantRequest retireGrantRequest) throws AmazonServiceException, AmazonClientException {
        Request<RetireGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(retireGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new RetireGrantRequestMarshaller().marshall((RetireGrantRequest) retireGrantRequest);
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
                retireGrantRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, retireGrantRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, retireGrantRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.RevokeGrantRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void revokeGrant(RevokeGrantRequest revokeGrantRequest) throws AmazonServiceException, AmazonClientException {
        Request<RevokeGrantRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(revokeGrantRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new RevokeGrantRequestMarshaller().marshall((RevokeGrantRequest) revokeGrantRequest);
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
                revokeGrantRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, revokeGrantRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, revokeGrantRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ScheduleKeyDeletionResult scheduleKeyDeletion(ScheduleKeyDeletionRequest scheduleKeyDeletionRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<ScheduleKeyDeletionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(scheduleKeyDeletionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new ScheduleKeyDeletionRequestMarshaller().marshall(scheduleKeyDeletionRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new ScheduleKeyDeletionResultJsonUnmarshaller()), createExecutionContext);
                    ScheduleKeyDeletionResult scheduleKeyDeletionResult = (ScheduleKeyDeletionResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return scheduleKeyDeletionResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = scheduleKeyDeletionRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public SignResult sign(SignRequest signRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<SignRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(signRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new SignRequestMarshaller().marshall(signRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new SignResultJsonUnmarshaller()), createExecutionContext);
                    SignResult signResult = (SignResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return signResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = signRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.TagResourceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void tagResource(TagResourceRequest tagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Request<TagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(tagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new TagResourceRequestMarshaller().marshall((TagResourceRequest) tagResourceRequest);
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
                tagResourceRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.UntagResourceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void untagResource(UntagResourceRequest untagResourceRequest) throws AmazonServiceException, AmazonClientException {
        Request<UntagResourceRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(untagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UntagResourceRequestMarshaller().marshall((UntagResourceRequest) untagResourceRequest);
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
                untagResourceRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.UpdateAliasRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void updateAlias(UpdateAliasRequest updateAliasRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdateAliasRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateAliasRequestMarshaller().marshall((UpdateAliasRequest) updateAliasRequest);
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
                updateAliasRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public UpdateCustomKeyStoreResult updateCustomKeyStore(UpdateCustomKeyStoreRequest updateCustomKeyStoreRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<UpdateCustomKeyStoreRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateCustomKeyStoreRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateCustomKeyStoreRequestMarshaller().marshall(updateCustomKeyStoreRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new UpdateCustomKeyStoreResultJsonUnmarshaller()), createExecutionContext);
                    UpdateCustomKeyStoreResult updateCustomKeyStoreResult = (UpdateCustomKeyStoreResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return updateCustomKeyStoreResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = updateCustomKeyStoreRequest;
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.UpdateKeyDescriptionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void updateKeyDescription(UpdateKeyDescriptionRequest updateKeyDescriptionRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdateKeyDescriptionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updateKeyDescriptionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdateKeyDescriptionRequestMarshaller().marshall((UpdateKeyDescriptionRequest) updateKeyDescriptionRequest);
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
                updateKeyDescriptionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateKeyDescriptionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateKeyDescriptionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.netease.nimlib.amazonaws.services.kms.AWSKMSClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.netease.nimlib.amazonaws.AmazonWebServiceRequest, com.netease.nimlib.amazonaws.services.kms.model.UpdatePrimaryRegionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.netease.nimlib.amazonaws.Request] */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void updatePrimaryRegion(UpdatePrimaryRegionRequest updatePrimaryRegionRequest) throws AmazonServiceException, AmazonClientException {
        Request<UpdatePrimaryRegionRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(updatePrimaryRegionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new UpdatePrimaryRegionRequestMarshaller().marshall((UpdatePrimaryRegionRequest) updatePrimaryRegionRequest);
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
                updatePrimaryRegionRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updatePrimaryRegionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updatePrimaryRegionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public VerifyResult verify(VerifyRequest verifyRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<VerifyRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(verifyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new VerifyRequestMarshaller().marshall(verifyRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new VerifyResultJsonUnmarshaller()), createExecutionContext);
                    VerifyResult verifyResult = (VerifyResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return verifyResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = verifyRequest;
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
    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public VerifyMacResult verifyMac(VerifyMacRequest verifyMacRequest) throws AmazonServiceException, AmazonClientException {
        Response<?> response;
        Request<VerifyMacRequest> marshall;
        ExecutionContext createExecutionContext = createExecutionContext(verifyMacRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        Request<?> request = null;
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    marshall = new VerifyMacRequestMarshaller().marshall(verifyMacRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    marshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    Response<?> invoke = invoke(marshall, new JsonResponseHandler(new VerifyMacResultJsonUnmarshaller()), createExecutionContext);
                    VerifyMacResult verifyMacResult = (VerifyMacResult) invoke.getAwsResponse();
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                    endClientExecution(awsRequestMetrics, marshall, invoke, true);
                    return verifyMacResult;
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                request = verifyMacRequest;
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

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public CreateKeyResult createKey() throws AmazonServiceException, AmazonClientException {
        return createKey(new CreateKeyRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListKeysResult listKeys() throws AmazonServiceException, AmazonClientException {
        return listKeys(new ListKeysRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public ListAliasesResult listAliases() throws AmazonServiceException, AmazonClientException {
        return listAliases(new ListAliasesRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public void retireGrant() throws AmazonServiceException, AmazonClientException {
        retireGrant(new RetireGrantRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
    public GenerateRandomResult generateRandom() throws AmazonServiceException, AmazonClientException {
        return generateRandom(new GenerateRandomRequest());
    }

    @Override // com.netease.nimlib.amazonaws.services.kms.AWSKMS
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
