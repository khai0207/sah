package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

@Deprecated
/* loaded from: classes.dex */
class AesGcm extends ContentCryptoScheme {
    private static final int DEFAULT_BLOCK_SIZE_IN_BYTES = 16;
    private static final int DEFAULT_IV_LENGTH_IN_BYTES = 12;
    private static final int DEFAULT_KEY_LENGTH_IN_BITS = 256;
    private static final int DEFAULT_TAG_LENGTH_IN_BITS = 128;

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getBlockSizeInBytes() {
        return 16;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getCipherAlgorithm() {
        return "AES/GCM/NoPadding";
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getIVLengthInBytes() {
        return 12;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getKeyGeneratorAlgorithm() {
        return JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getKeyLengthInBits() {
        return 256;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    long getMaxPlaintextSize() {
        return 68719476704L;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getSpecificCipherProvider() {
        return "BC";
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getTagLengthInBits() {
        return 128;
    }

    AesGcm() {
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    CipherLite createAuxillaryCipher(SecretKey secretKey, byte[] bArr, int i, Provider provider, long j) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return AES_CTR.createCipherLite(secretKey, AES_CTR.adjustIV(bArr, j), i, provider);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    protected CipherLite newCipherLite(Cipher cipher, SecretKey secretKey, int i) {
        return new GCMCipherLite(cipher, secretKey, i);
    }
}
