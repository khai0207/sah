package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

@Deprecated
/* loaded from: classes.dex */
class AesCbc extends ContentCryptoScheme {
    private static final int DEFAULT_BLOCK_SIZE_IN_BYTES = 16;
    private static final int DEFAULT_IV_LENGTH_IN_BYTES = 16;
    private static final int DEFAULT_KEY_LENGTH_IN_BITS = 256;

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getBlockSizeInBytes() {
        return 16;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getCipherAlgorithm() {
        return JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getIVLengthInBytes() {
        return 16;
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
        return 4503599627370496L;
    }

    AesCbc() {
    }
}
