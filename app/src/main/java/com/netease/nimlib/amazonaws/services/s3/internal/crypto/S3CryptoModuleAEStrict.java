package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.services.kms.AWSKMSClient;
import com.netease.nimlib.amazonaws.services.s3.internal.S3Direct;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoConfiguration;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoMode;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterialsProvider;

@Deprecated
/* loaded from: classes.dex */
class S3CryptoModuleAEStrict extends S3CryptoModuleAE {
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleAE
    protected final boolean isStrict() {
        return true;
    }

    S3CryptoModuleAEStrict(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        super(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, cryptoConfiguration);
        if (cryptoConfiguration.getCryptoMode() != CryptoMode.StrictAuthenticatedEncryption) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    protected void securityCheck(ContentCryptoMaterial contentCryptoMaterial, S3ObjectWrapper s3ObjectWrapper) {
        if (ContentCryptoScheme.AES_GCM.equals(contentCryptoMaterial.getContentCryptoScheme())) {
            return;
        }
        throw new SecurityException("S3 object [bucket: " + s3ObjectWrapper.getBucketName() + ", key: " + s3ObjectWrapper.getKey() + "] not encrypted using authenticated encryption");
    }
}
