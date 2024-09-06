package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import com.netease.nimlib.amazonaws.services.kms.AWSKMSClient;
import com.netease.nimlib.amazonaws.services.s3.internal.S3Direct;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoConfiguration;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoMode;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.netease.nimlib.amazonaws.services.s3.model.GetObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.amazonaws.services.s3.model.S3Object;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import java.io.File;

@Deprecated
/* loaded from: classes.dex */
class S3CryptoModuleEO extends S3CryptoModuleBase<MultipartUploadCbcContext> {
    S3CryptoModuleEO(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        super(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, cryptoConfiguration);
        if (cryptoConfiguration.getCryptoMode() != CryptoMode.EncryptionOnly) {
            throw new IllegalArgumentException();
        }
    }

    S3CryptoModuleEO(S3Direct s3Direct, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this(null, s3Direct, new DefaultAWSCredentialsProviderChain(), encryptionMaterialsProvider, cryptoConfiguration);
    }

    S3CryptoModuleEO(AWSKMSClient aWSKMSClient, S3Direct s3Direct, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this(aWSKMSClient, s3Direct, new DefaultAWSCredentialsProviderChain(), encryptionMaterialsProvider, cryptoConfiguration);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public S3Object getObjectSecurely(GetObjectRequest getObjectRequest) {
        throw new IllegalStateException();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File file) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final MultipartUploadCbcContext newUploadContext(InitiateMultipartUploadRequest initiateMultipartUploadRequest, ContentCryptoMaterial contentCryptoMaterial) {
        MultipartUploadCbcContext multipartUploadCbcContext = new MultipartUploadCbcContext(initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey(), contentCryptoMaterial);
        multipartUploadCbcContext.setNextInitializationVector(contentCryptoMaterial.getCipherLite().getIV());
        return multipartUploadCbcContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final void updateUploadContext(MultipartUploadCbcContext multipartUploadCbcContext, SdkFilterInputStream sdkFilterInputStream) {
        multipartUploadCbcContext.setNextInitializationVector(((ByteRangeCapturingInputStream) sdkFilterInputStream).getBlock());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final ByteRangeCapturingInputStream wrapForMultipart(CipherLiteInputStream cipherLiteInputStream, long j) {
        return new ByteRangeCapturingInputStream(cipherLiteInputStream, j - this.contentCryptoScheme.getBlockSizeInBytes(), j);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    final long computeLastPartSize(UploadPartRequest uploadPartRequest) {
        long partSize;
        if (uploadPartRequest.getFile() != null) {
            if (uploadPartRequest.getPartSize() > 0) {
                partSize = uploadPartRequest.getPartSize();
            } else {
                partSize = uploadPartRequest.getFile().length();
            }
        } else {
            if (uploadPartRequest.getInputStream() == null) {
                return -1L;
            }
            partSize = uploadPartRequest.getPartSize();
        }
        long blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        return partSize + (blockSizeInBytes - (partSize % blockSizeInBytes));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final CipherLite cipherLiteForNextPart(MultipartUploadCbcContext multipartUploadCbcContext) {
        return multipartUploadCbcContext.getCipherLite().createUsingIV(multipartUploadCbcContext.getNextInitializationVector());
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    protected final long ciphertextLength(long j) {
        long blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        return j + (blockSizeInBytes - (j % blockSizeInBytes));
    }
}
