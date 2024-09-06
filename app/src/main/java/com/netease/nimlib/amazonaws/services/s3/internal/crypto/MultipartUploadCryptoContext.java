package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.AmazonClientException;

@Deprecated
/* loaded from: classes.dex */
class MultipartUploadCryptoContext extends MultipartUploadContext {
    private final ContentCryptoMaterial cekMaterial;
    private int partNumber;
    private volatile boolean partUploadInProgress;

    MultipartUploadCryptoContext(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        super(str, str2);
        this.cekMaterial = contentCryptoMaterial;
    }

    CipherLite getCipherLite() {
        return this.cekMaterial.getCipherLite();
    }

    ContentCryptoMaterial getContentCryptoMaterial() {
        return this.cekMaterial;
    }

    void beginPartUpload(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("part number must be at least 1");
        }
        if (this.partUploadInProgress) {
            throw new AmazonClientException("Parts are required to be uploaded in series");
        }
        synchronized (this) {
            if (i - this.partNumber <= 1) {
                this.partNumber = i;
                this.partUploadInProgress = true;
            } else {
                throw new AmazonClientException("Parts are required to be uploaded in series (partNumber=" + this.partNumber + ", nextPartNumber=" + i + ")");
            }
        }
    }

    void endPartUpload() {
        this.partUploadInProgress = false;
    }
}
