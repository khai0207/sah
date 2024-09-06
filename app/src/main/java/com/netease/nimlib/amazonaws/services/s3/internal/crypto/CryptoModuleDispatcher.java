package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.services.kms.AWSKMSClient;
import com.netease.nimlib.amazonaws.services.s3.internal.S3Direct;
import com.netease.nimlib.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartResult;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoConfiguration;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoMode;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.netease.nimlib.amazonaws.services.s3.model.GetObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.amazonaws.services.s3.model.PutInstructionFileRequest;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectResult;
import com.netease.nimlib.amazonaws.services.s3.model.S3Object;
import com.netease.nimlib.amazonaws.services.s3.model.UploadObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes.dex */
public class CryptoModuleDispatcher extends S3CryptoModule<MultipartUploadContext> {
    private final S3CryptoModuleAE ae;
    private final CryptoMode defaultCryptoMode;
    private final S3CryptoModuleEO eo;

    public CryptoModuleDispatcher(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        CryptoConfiguration mo8clone = cryptoConfiguration.mo8clone();
        if (mo8clone.getCryptoMode() == null) {
            mo8clone.setCryptoMode(CryptoMode.EncryptionOnly);
        }
        CryptoConfiguration readOnly = mo8clone.readOnly();
        this.defaultCryptoMode = readOnly.getCryptoMode();
        int i = AnonymousClass1.$SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[this.defaultCryptoMode.ordinal()];
        if (i == 1) {
            this.ae = new S3CryptoModuleAEStrict(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
            this.eo = null;
        } else if (i == 2) {
            this.ae = new S3CryptoModuleAE(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
            this.eo = null;
        } else {
            if (i == 3) {
                this.eo = new S3CryptoModuleEO(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, readOnly);
                CryptoConfiguration mo8clone2 = readOnly.mo8clone();
                try {
                    mo8clone2.setCryptoMode(CryptoMode.AuthenticatedEncryption);
                } catch (UnsupportedOperationException unused) {
                }
                this.ae = new S3CryptoModuleAE(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, mo8clone2.readOnly());
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: com.netease.nimlib.amazonaws.services.s3.internal.crypto.CryptoModuleDispatcher$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode;

        static {
            int[] iArr = new int[CryptoMode.values().length];
            $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode = iArr;
            try {
                iArr[CryptoMode.StrictAuthenticatedEncryption.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[CryptoMode.AuthenticatedEncryption.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[CryptoMode.EncryptionOnly.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.putObjectSecurely(putObjectRequest);
        }
        return this.ae.putObjectSecurely(putObjectRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public S3Object getObjectSecurely(GetObjectRequest getObjectRequest) {
        return this.ae.getObjectSecurely(getObjectRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File file) {
        return this.ae.getObjectSecurely(getObjectRequest, file);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest completeMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.completeMultipartUploadSecurely(completeMultipartUploadRequest);
        }
        return this.ae.completeMultipartUploadSecurely(completeMultipartUploadRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public void abortMultipartUploadSecurely(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            this.eo.abortMultipartUploadSecurely(abortMultipartUploadRequest);
        } else {
            this.ae.abortMultipartUploadSecurely(abortMultipartUploadRequest);
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.initiateMultipartUploadSecurely(initiateMultipartUploadRequest);
        }
        return this.ae.initiateMultipartUploadSecurely(initiateMultipartUploadRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public UploadPartResult uploadPartSecurely(UploadPartRequest uploadPartRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.uploadPartSecurely(uploadPartRequest);
        }
        return this.ae.uploadPartSecurely(uploadPartRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.copyPartSecurely(copyPartRequest);
        }
        return this.ae.copyPartSecurely(copyPartRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public PutObjectResult putInstructionFileSecurely(PutInstructionFileRequest putInstructionFileRequest) {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            return this.eo.putInstructionFileSecurely(putInstructionFileRequest);
        }
        return this.ae.putInstructionFileSecurely(putInstructionFileRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public void putLocalObjectSecurely(UploadObjectRequest uploadObjectRequest, String str, OutputStream outputStream) throws IOException {
        if (this.defaultCryptoMode == CryptoMode.EncryptionOnly) {
            this.eo.putLocalObjectSecurely(uploadObjectRequest, str, outputStream);
        } else {
            this.ae.putLocalObjectSecurely(uploadObjectRequest, str, outputStream);
        }
    }
}
