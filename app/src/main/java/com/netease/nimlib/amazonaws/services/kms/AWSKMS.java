package com.netease.nimlib.amazonaws.services.kms;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.regions.Region;
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

/* loaded from: classes.dex */
public interface AWSKMS {
    CancelKeyDeletionResult cancelKeyDeletion(CancelKeyDeletionRequest cancelKeyDeletionRequest) throws AmazonClientException, AmazonServiceException;

    ConnectCustomKeyStoreResult connectCustomKeyStore(ConnectCustomKeyStoreRequest connectCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void createAlias(CreateAliasRequest createAliasRequest) throws AmazonClientException, AmazonServiceException;

    CreateCustomKeyStoreResult createCustomKeyStore(CreateCustomKeyStoreRequest createCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    CreateGrantResult createGrant(CreateGrantRequest createGrantRequest) throws AmazonClientException, AmazonServiceException;

    CreateKeyResult createKey() throws AmazonClientException, AmazonServiceException;

    CreateKeyResult createKey(CreateKeyRequest createKeyRequest) throws AmazonClientException, AmazonServiceException;

    DecryptResult decrypt(DecryptRequest decryptRequest) throws AmazonClientException, AmazonServiceException;

    void deleteAlias(DeleteAliasRequest deleteAliasRequest) throws AmazonClientException, AmazonServiceException;

    DeleteCustomKeyStoreResult deleteCustomKeyStore(DeleteCustomKeyStoreRequest deleteCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void deleteImportedKeyMaterial(DeleteImportedKeyMaterialRequest deleteImportedKeyMaterialRequest) throws AmazonClientException, AmazonServiceException;

    DescribeCustomKeyStoresResult describeCustomKeyStores(DescribeCustomKeyStoresRequest describeCustomKeyStoresRequest) throws AmazonClientException, AmazonServiceException;

    DescribeKeyResult describeKey(DescribeKeyRequest describeKeyRequest) throws AmazonClientException, AmazonServiceException;

    void disableKey(DisableKeyRequest disableKeyRequest) throws AmazonClientException, AmazonServiceException;

    void disableKeyRotation(DisableKeyRotationRequest disableKeyRotationRequest) throws AmazonClientException, AmazonServiceException;

    DisconnectCustomKeyStoreResult disconnectCustomKeyStore(DisconnectCustomKeyStoreRequest disconnectCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void enableKey(EnableKeyRequest enableKeyRequest) throws AmazonClientException, AmazonServiceException;

    void enableKeyRotation(EnableKeyRotationRequest enableKeyRotationRequest) throws AmazonClientException, AmazonServiceException;

    EncryptResult encrypt(EncryptRequest encryptRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyResult generateDataKey(GenerateDataKeyRequest generateDataKeyRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyPairResult generateDataKeyPair(GenerateDataKeyPairRequest generateDataKeyPairRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyPairWithoutPlaintextResult generateDataKeyPairWithoutPlaintext(GenerateDataKeyPairWithoutPlaintextRequest generateDataKeyPairWithoutPlaintextRequest) throws AmazonClientException, AmazonServiceException;

    GenerateDataKeyWithoutPlaintextResult generateDataKeyWithoutPlaintext(GenerateDataKeyWithoutPlaintextRequest generateDataKeyWithoutPlaintextRequest) throws AmazonClientException, AmazonServiceException;

    GenerateMacResult generateMac(GenerateMacRequest generateMacRequest) throws AmazonClientException, AmazonServiceException;

    GenerateRandomResult generateRandom() throws AmazonClientException, AmazonServiceException;

    GenerateRandomResult generateRandom(GenerateRandomRequest generateRandomRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetKeyPolicyResult getKeyPolicy(GetKeyPolicyRequest getKeyPolicyRequest) throws AmazonClientException, AmazonServiceException;

    GetKeyRotationStatusResult getKeyRotationStatus(GetKeyRotationStatusRequest getKeyRotationStatusRequest) throws AmazonClientException, AmazonServiceException;

    GetParametersForImportResult getParametersForImport(GetParametersForImportRequest getParametersForImportRequest) throws AmazonClientException, AmazonServiceException;

    GetPublicKeyResult getPublicKey(GetPublicKeyRequest getPublicKeyRequest) throws AmazonClientException, AmazonServiceException;

    ImportKeyMaterialResult importKeyMaterial(ImportKeyMaterialRequest importKeyMaterialRequest) throws AmazonClientException, AmazonServiceException;

    ListAliasesResult listAliases() throws AmazonClientException, AmazonServiceException;

    ListAliasesResult listAliases(ListAliasesRequest listAliasesRequest) throws AmazonClientException, AmazonServiceException;

    ListGrantsResult listGrants(ListGrantsRequest listGrantsRequest) throws AmazonClientException, AmazonServiceException;

    ListKeyPoliciesResult listKeyPolicies(ListKeyPoliciesRequest listKeyPoliciesRequest) throws AmazonClientException, AmazonServiceException;

    ListKeysResult listKeys() throws AmazonClientException, AmazonServiceException;

    ListKeysResult listKeys(ListKeysRequest listKeysRequest) throws AmazonClientException, AmazonServiceException;

    ListResourceTagsResult listResourceTags(ListResourceTagsRequest listResourceTagsRequest) throws AmazonClientException, AmazonServiceException;

    ListRetirableGrantsResult listRetirableGrants(ListRetirableGrantsRequest listRetirableGrantsRequest) throws AmazonClientException, AmazonServiceException;

    void putKeyPolicy(PutKeyPolicyRequest putKeyPolicyRequest) throws AmazonClientException, AmazonServiceException;

    ReEncryptResult reEncrypt(ReEncryptRequest reEncryptRequest) throws AmazonClientException, AmazonServiceException;

    ReplicateKeyResult replicateKey(ReplicateKeyRequest replicateKeyRequest) throws AmazonClientException, AmazonServiceException;

    void retireGrant() throws AmazonClientException, AmazonServiceException;

    void retireGrant(RetireGrantRequest retireGrantRequest) throws AmazonClientException, AmazonServiceException;

    void revokeGrant(RevokeGrantRequest revokeGrantRequest) throws AmazonClientException, AmazonServiceException;

    ScheduleKeyDeletionResult scheduleKeyDeletion(ScheduleKeyDeletionRequest scheduleKeyDeletionRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    SignResult sign(SignRequest signRequest) throws AmazonClientException, AmazonServiceException;

    void tagResource(TagResourceRequest tagResourceRequest) throws AmazonClientException, AmazonServiceException;

    void untagResource(UntagResourceRequest untagResourceRequest) throws AmazonClientException, AmazonServiceException;

    void updateAlias(UpdateAliasRequest updateAliasRequest) throws AmazonClientException, AmazonServiceException;

    UpdateCustomKeyStoreResult updateCustomKeyStore(UpdateCustomKeyStoreRequest updateCustomKeyStoreRequest) throws AmazonClientException, AmazonServiceException;

    void updateKeyDescription(UpdateKeyDescriptionRequest updateKeyDescriptionRequest) throws AmazonClientException, AmazonServiceException;

    void updatePrimaryRegion(UpdatePrimaryRegionRequest updatePrimaryRegionRequest) throws AmazonClientException, AmazonServiceException;

    VerifyResult verify(VerifyRequest verifyRequest) throws AmazonClientException, AmazonServiceException;

    VerifyMacResult verifyMac(VerifyMacRequest verifyMacRequest) throws AmazonClientException, AmazonServiceException;
}
