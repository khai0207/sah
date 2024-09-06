package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

@Deprecated
/* loaded from: classes.dex */
class AesCtr extends ContentCryptoScheme {
    private static final int DEFAULT_IV_LENGTH_IN_BYTES = 16;
    private static final int SUPPORTED_IV_LENGTH = 12;

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getCipherAlgorithm() {
        return "AES/CTR/NoPadding";
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getIVLengthInBytes() {
        return 16;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    long getMaxPlaintextSize() {
        return -1L;
    }

    AesCtr() {
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    String getKeyGeneratorAlgorithm() {
        return AES_GCM.getKeyGeneratorAlgorithm();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getKeyLengthInBits() {
        return AES_GCM.getKeyLengthInBits();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    int getBlockSizeInBytes() {
        return AES_GCM.getBlockSizeInBytes();
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.ContentCryptoScheme
    byte[] adjustIV(byte[] bArr, long j) {
        if (bArr.length != 12) {
            throw new UnsupportedOperationException();
        }
        int blockSizeInBytes = getBlockSizeInBytes();
        long j2 = blockSizeInBytes;
        long j3 = j / j2;
        if (j2 * j3 != j) {
            throw new IllegalArgumentException("Expecting byteOffset to be multiple of 16, but got blockOffset=" + j3 + ", blockSize=" + blockSizeInBytes + ", byteOffset=" + j);
        }
        return incrementBlocks(computeJ0(bArr), j3);
    }

    private byte[] computeJ0(byte[] bArr) {
        int blockSizeInBytes = getBlockSizeInBytes();
        byte[] bArr2 = new byte[blockSizeInBytes];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[blockSizeInBytes - 1] = 1;
        return incrementBlocks(bArr2, 1L);
    }
}
