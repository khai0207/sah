package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.auth.AWSCredentialsProvider;
import com.netease.nimlib.amazonaws.internal.ReleasableInputStream;
import com.netease.nimlib.amazonaws.internal.ResettableInputStream;
import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.services.kms.AWSKMSClient;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.netease.nimlib.amazonaws.services.kms.model.GenerateDataKeyResult;
import com.netease.nimlib.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.internal.InputSubstream;
import com.netease.nimlib.amazonaws.services.s3.internal.S3Direct;
import com.netease.nimlib.amazonaws.services.s3.internal.crypto.MultipartUploadCryptoContext;
import com.netease.nimlib.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.AbstractPutObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartResult;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoConfiguration;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoMode;
import com.netease.nimlib.amazonaws.services.s3.model.CryptoStorageMode;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterials;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterialsFactory;
import com.netease.nimlib.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.netease.nimlib.amazonaws.services.s3.model.GetObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.InstructionFileId;
import com.netease.nimlib.amazonaws.services.s3.model.MaterialsDescriptionProvider;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.amazonaws.services.s3.model.PutInstructionFileRequest;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectResult;
import com.netease.nimlib.amazonaws.services.s3.model.S3DataSource;
import com.netease.nimlib.amazonaws.services.s3.model.S3Object;
import com.netease.nimlib.amazonaws.services.s3.model.S3ObjectId;
import com.netease.nimlib.amazonaws.services.s3.model.UploadObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartResult;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import com.netease.nimlib.amazonaws.util.BinaryUtils;
import com.netease.nimlib.amazonaws.util.IOUtils;
import com.netease.nimlib.amazonaws.util.LengthCheckInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Deprecated
/* loaded from: classes.dex */
public abstract class S3CryptoModuleBase<T extends MultipartUploadCryptoContext> extends S3CryptoModule<T> {
    protected static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final boolean IS_MULTI_PART = true;
    private static final int MAX_RETRY_COUNT = 9;
    protected final ContentCryptoScheme contentCryptoScheme;
    protected final CryptoConfiguration cryptoConfig;
    protected final S3CryptoScheme cryptoScheme;
    protected final EncryptionMaterialsProvider kekMaterialsProvider;
    protected final AWSKMSClient kms;
    protected final Log log = LogFactory.getLog(getClass());
    protected final Map<String, T> multipartUploadContexts = Collections.synchronizedMap(new HashMap());
    protected final S3Direct s3;

    abstract CipherLite cipherLiteForNextPart(T t);

    protected abstract long ciphertextLength(long j);

    abstract long computeLastPartSize(UploadPartRequest uploadPartRequest);

    abstract T newUploadContext(InitiateMultipartUploadRequest initiateMultipartUploadRequest, ContentCryptoMaterial contentCryptoMaterial);

    protected void securityCheck(ContentCryptoMaterial contentCryptoMaterial, S3ObjectWrapper s3ObjectWrapper) {
    }

    abstract void updateUploadContext(T t, SdkFilterInputStream sdkFilterInputStream);

    abstract <I extends CipherLiteInputStream> SdkFilterInputStream wrapForMultipart(I i, long j);

    protected S3CryptoModuleBase(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        if (!cryptoConfiguration.isReadOnly()) {
            throw new IllegalArgumentException("The crypto configuration parameter is required to be read-only");
        }
        this.kekMaterialsProvider = encryptionMaterialsProvider;
        this.s3 = s3Direct;
        this.cryptoConfig = cryptoConfiguration;
        S3CryptoScheme from = S3CryptoScheme.from(cryptoConfiguration.getCryptoMode());
        this.cryptoScheme = from;
        this.contentCryptoScheme = from.getContentCryptoScheme();
        this.kms = aWSKMSClient;
    }

    protected S3CryptoModuleBase(S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this.kekMaterialsProvider = encryptionMaterialsProvider;
        this.s3 = s3Direct;
        this.cryptoConfig = cryptoConfiguration;
        S3CryptoScheme from = S3CryptoScheme.from(cryptoConfiguration.getCryptoMode());
        this.cryptoScheme = from;
        this.contentCryptoScheme = from.getContentCryptoScheme();
        this.kms = null;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest) {
        appendUserAgent(putObjectRequest, AmazonS3EncryptionClient.USER_AGENT);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
            return putObjectUsingInstructionFile(putObjectRequest);
        }
        return putObjectUsingMetadata(putObjectRequest);
    }

    private PutObjectResult putObjectUsingMetadata(PutObjectRequest putObjectRequest) {
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(putObjectRequest);
        File file = putObjectRequest.getFile();
        InputStream inputStream = putObjectRequest.getInputStream();
        PutObjectRequest putObjectRequest2 = (PutObjectRequest) wrapWithCipher(putObjectRequest, createContentCryptoMaterial);
        putObjectRequest.setMetadata(updateMetadataWithContentCryptoMaterial(putObjectRequest.getMetadata(), putObjectRequest.getFile(), createContentCryptoMaterial));
        try {
            return this.s3.putObject(putObjectRequest2);
        } finally {
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
        }
    }

    private PutObjectResult putObjectUsingInstructionFile(PutObjectRequest putObjectRequest) {
        File file = putObjectRequest.getFile();
        InputStream inputStream = putObjectRequest.getInputStream();
        PutObjectRequest withInputStream = putObjectRequest.mo6clone().withFile((File) null).withInputStream((InputStream) null);
        withInputStream.setKey(withInputStream.getKey() + ".instruction");
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(putObjectRequest);
        PutObjectRequest putObjectRequest2 = (PutObjectRequest) wrapWithCipher(putObjectRequest, createContentCryptoMaterial);
        try {
            PutObjectResult putObject = this.s3.putObject(putObjectRequest2);
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
            this.s3.putObject(updateInstructionPutRequest(withInputStream, createContentCryptoMaterial));
            return putObject;
        } catch (Throwable th) {
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
            throw th;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final void abortMultipartUploadSecurely(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        this.s3.abortMultipartUpload(abortMultipartUploadRequest);
        this.multipartUploadContexts.remove(abortMultipartUploadRequest.getUploadId());
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest) {
        T t = this.multipartUploadContexts.get(copyPartRequest.getUploadId());
        CopyPartResult copyPart = this.s3.copyPart(copyPartRequest);
        if (t != null && !t.hasFinalPartBeenSeen()) {
            t.setHasFinalPartBeenSeen(true);
        }
        return copyPart;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        appendUserAgent(initiateMultipartUploadRequest, AmazonS3EncryptionClient.USER_AGENT);
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(initiateMultipartUploadRequest);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.ObjectMetadata) {
            ObjectMetadata objectMetadata = initiateMultipartUploadRequest.getObjectMetadata();
            if (objectMetadata == null) {
                objectMetadata = new ObjectMetadata();
            }
            initiateMultipartUploadRequest.setObjectMetadata(updateMetadataWithContentCryptoMaterial(objectMetadata, null, createContentCryptoMaterial));
        }
        InitiateMultipartUploadResult initiateMultipartUpload = this.s3.initiateMultipartUpload(initiateMultipartUploadRequest);
        T newUploadContext = newUploadContext(initiateMultipartUploadRequest, createContentCryptoMaterial);
        if (initiateMultipartUploadRequest instanceof MaterialsDescriptionProvider) {
            newUploadContext.setMaterialsDescription(((MaterialsDescriptionProvider) initiateMultipartUploadRequest).getMaterialsDescription());
        }
        this.multipartUploadContexts.put(initiateMultipartUpload.getUploadId(), newUploadContext);
        return initiateMultipartUpload;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public UploadPartResult uploadPartSecurely(UploadPartRequest uploadPartRequest) {
        CipherLiteInputStream newMultipartS3CipherInputStream;
        appendUserAgent(uploadPartRequest, AmazonS3EncryptionClient.USER_AGENT);
        int blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        boolean isLastPart = uploadPartRequest.isLastPart();
        String uploadId = uploadPartRequest.getUploadId();
        long partSize = uploadPartRequest.getPartSize();
        boolean z = 0 == partSize % ((long) blockSizeInBytes);
        if (!isLastPart && !z) {
            throw new AmazonClientException("Invalid part size: part sizes for encrypted multipart uploads must be multiples of the cipher block size (" + blockSizeInBytes + ") with the exception of the last part.");
        }
        T t = this.multipartUploadContexts.get(uploadId);
        if (t == null) {
            throw new AmazonClientException("No client-side information available on upload ID " + uploadId);
        }
        t.beginPartUpload(uploadPartRequest.getPartNumber());
        CipherLite cipherLiteForNextPart = cipherLiteForNextPart(t);
        File file = uploadPartRequest.getFile();
        InputStream inputStream = uploadPartRequest.getInputStream();
        CipherLiteInputStream cipherLiteInputStream = null;
        try {
            newMultipartS3CipherInputStream = newMultipartS3CipherInputStream(uploadPartRequest, cipherLiteForNextPart);
        } catch (Throwable th) {
            th = th;
        }
        try {
            SdkFilterInputStream wrapForMultipart = wrapForMultipart(newMultipartS3CipherInputStream, partSize);
            uploadPartRequest.setInputStream(wrapForMultipart);
            uploadPartRequest.setFile(null);
            uploadPartRequest.setFileOffset(0L);
            if (isLastPart) {
                long computeLastPartSize = computeLastPartSize(uploadPartRequest);
                if (computeLastPartSize > -1) {
                    uploadPartRequest.setPartSize(computeLastPartSize);
                }
                if (t.hasFinalPartBeenSeen()) {
                    throw new AmazonClientException("This part was specified as the last part in a multipart upload, but a previous part was already marked as the last part.  Only the last part of the upload should be marked as the last part.");
                }
            }
            UploadPartResult uploadPart = this.s3.uploadPart(uploadPartRequest);
            S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, wrapForMultipart, this.log);
            t.endPartUpload();
            if (isLastPart) {
                t.setHasFinalPartBeenSeen(true);
            }
            updateUploadContext(t, wrapForMultipart);
            return uploadPart;
        } catch (Throwable th2) {
            th = th2;
            cipherLiteInputStream = newMultipartS3CipherInputStream;
            S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, cipherLiteInputStream, this.log);
            t.endPartUpload();
            throw th;
        }
    }

    protected final CipherLiteInputStream newMultipartS3CipherInputStream(UploadPartRequest uploadPartRequest, CipherLite cipherLite) {
        InputStream resettableInputStream;
        File file = uploadPartRequest.getFile();
        InputStream inputStream = uploadPartRequest.getInputStream();
        InputSubstream inputSubstream = null;
        try {
            if (file != null) {
                resettableInputStream = new ResettableInputStream(file);
            } else {
                if (inputStream == null) {
                    throw new IllegalArgumentException("A File or InputStream must be specified when uploading part");
                }
                resettableInputStream = inputStream;
            }
            InputSubstream inputSubstream2 = new InputSubstream(resettableInputStream, uploadPartRequest.getFileOffset(), uploadPartRequest.getPartSize(), uploadPartRequest.isLastPart());
            try {
                if (cipherLite.markSupported()) {
                    return new CipherLiteInputStream(inputSubstream2, cipherLite, 2048, true, uploadPartRequest.isLastPart());
                }
                return new RenewableCipherLiteInputStream(inputSubstream2, cipherLite, 2048, true, uploadPartRequest.isLastPart());
            } catch (Exception e) {
                e = e;
                inputSubstream = inputSubstream2;
                S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, inputSubstream, this.log);
                throw new AmazonClientException("Unable to create cipher input stream", e);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest completeMultipartUploadRequest) {
        appendUserAgent(completeMultipartUploadRequest, AmazonS3EncryptionClient.USER_AGENT);
        String uploadId = completeMultipartUploadRequest.getUploadId();
        T t = this.multipartUploadContexts.get(uploadId);
        if (t != null && !t.hasFinalPartBeenSeen()) {
            throw new AmazonClientException("Unable to complete an encrypted multipart upload without being told which part was the last.  Without knowing which part was the last, the encrypted data in Amazon S3 is incomplete and corrupt.");
        }
        CompleteMultipartUploadResult completeMultipartUpload = this.s3.completeMultipartUpload(completeMultipartUploadRequest);
        if (t != null && this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
            this.s3.putObject(createInstructionPutRequest(t.getBucketName(), t.getKey(), t.getContentCryptoMaterial()));
        }
        this.multipartUploadContexts.remove(uploadId);
        return completeMultipartUpload;
    }

    protected final ObjectMetadata updateMetadataWithContentCryptoMaterial(ObjectMetadata objectMetadata, File file, ContentCryptoMaterial contentCryptoMaterial) {
        if (objectMetadata == null) {
            objectMetadata = new ObjectMetadata();
        }
        if (file != null) {
            objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(file));
        }
        return contentCryptoMaterial.toObjectMetadata(objectMetadata, this.cryptoConfig.getCryptoMode());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ContentCryptoMaterial createContentCryptoMaterial(AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials;
        if ((amazonWebServiceRequest instanceof EncryptionMaterialsFactory) && (encryptionMaterials = ((EncryptionMaterialsFactory) amazonWebServiceRequest).getEncryptionMaterials()) != null) {
            return buildContentCryptoMaterial(encryptionMaterials, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
        }
        if (amazonWebServiceRequest instanceof MaterialsDescriptionProvider) {
            Map<String, String> materialsDescription = ((MaterialsDescriptionProvider) amazonWebServiceRequest).getMaterialsDescription();
            ContentCryptoMaterial newContentCryptoMaterial = newContentCryptoMaterial(this.kekMaterialsProvider, materialsDescription, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
            if (newContentCryptoMaterial != null) {
                return newContentCryptoMaterial;
            }
            if (materialsDescription != null && !this.kekMaterialsProvider.getEncryptionMaterials().isKMSEnabled()) {
                throw new AmazonClientException("No material available from the encryption material provider for description " + materialsDescription);
            }
        }
        return newContentCryptoMaterial(this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
    }

    private ContentCryptoMaterial newContentCryptoMaterial(EncryptionMaterialsProvider encryptionMaterialsProvider, Map<String, String> map, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials = encryptionMaterialsProvider.getEncryptionMaterials(map);
        if (encryptionMaterials == null) {
            return null;
        }
        return buildContentCryptoMaterial(encryptionMaterials, provider, amazonWebServiceRequest);
    }

    private ContentCryptoMaterial newContentCryptoMaterial(EncryptionMaterialsProvider encryptionMaterialsProvider, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials = encryptionMaterialsProvider.getEncryptionMaterials();
        if (encryptionMaterials == null) {
            throw new AmazonClientException("No material available from the encryption material provider");
        }
        return buildContentCryptoMaterial(encryptionMaterials, provider, amazonWebServiceRequest);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final void putLocalObjectSecurely(UploadObjectRequest uploadObjectRequest, String str, OutputStream outputStream) throws IOException {
        UploadObjectRequest mo6clone = uploadObjectRequest.mo6clone();
        File file = mo6clone.getFile();
        InputStream inputStream = mo6clone.getInputStream();
        T t = this.multipartUploadContexts.get(str);
        UploadObjectRequest uploadObjectRequest2 = (UploadObjectRequest) wrapWithCipher(mo6clone, t.getContentCryptoMaterial());
        try {
            IOUtils.copy(uploadObjectRequest2.getInputStream(), outputStream);
            t.setHasFinalPartBeenSeen(true);
        } finally {
            S3DataSource.Utils.cleanupDataSource(uploadObjectRequest2, file, inputStream, uploadObjectRequest2.getInputStream(), this.log);
            IOUtils.closeQuietly(outputStream, this.log);
        }
    }

    private ContentCryptoMaterial buildContentCryptoMaterial(EncryptionMaterials encryptionMaterials, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        byte[] bArr = new byte[this.contentCryptoScheme.getIVLengthInBytes()];
        this.cryptoScheme.getSecureRandom().nextBytes(bArr);
        if (encryptionMaterials.isKMSEnabled()) {
            Map<String, String> mergeMaterialDescriptions = ContentCryptoMaterial.mergeMaterialDescriptions(encryptionMaterials, amazonWebServiceRequest);
            GenerateDataKeyRequest withKeySpec = new GenerateDataKeyRequest().withEncryptionContext(mergeMaterialDescriptions).withKeyId(encryptionMaterials.getCustomerMasterKeyId()).withKeySpec(this.contentCryptoScheme.getKeySpec());
            withKeySpec.withGeneralProgressListener(amazonWebServiceRequest.getGeneralProgressListener()).withRequestMetricCollector(amazonWebServiceRequest.getRequestMetricCollector());
            GenerateDataKeyResult generateDataKey = this.kms.generateDataKey(withKeySpec);
            return ContentCryptoMaterial.wrap(new SecretKeySpec(BinaryUtils.copyAllBytesFrom(generateDataKey.getPlaintext()), this.contentCryptoScheme.getKeyGeneratorAlgorithm()), bArr, this.contentCryptoScheme, provider, new KMSSecuredCEK(BinaryUtils.copyAllBytesFrom(generateDataKey.getCiphertextBlob()), mergeMaterialDescriptions));
        }
        return ContentCryptoMaterial.create(generateCEK(encryptionMaterials, provider), bArr, encryptionMaterials, this.cryptoScheme, provider, this.kms, amazonWebServiceRequest);
    }

    protected final SecretKey generateCEK(EncryptionMaterials encryptionMaterials, Provider provider) {
        KeyGenerator keyGenerator;
        boolean z;
        String keyGeneratorAlgorithm = this.contentCryptoScheme.getKeyGeneratorAlgorithm();
        try {
            if (provider == null) {
                keyGenerator = KeyGenerator.getInstance(keyGeneratorAlgorithm);
            } else {
                keyGenerator = KeyGenerator.getInstance(keyGeneratorAlgorithm, provider);
            }
            keyGenerator.init(this.contentCryptoScheme.getKeyLengthInBits(), this.cryptoScheme.getSecureRandom());
            KeyPair keyPair = encryptionMaterials.getKeyPair();
            if (keyPair == null || this.cryptoScheme.getKeyWrapScheme().getKeyWrapAlgorithm(keyPair.getPublic(), provider) != null) {
                z = false;
            } else {
                Provider provider2 = keyGenerator.getProvider();
                z = "BC".equals(provider2 == null ? null : provider2.getName());
            }
            SecretKey generateKey = keyGenerator.generateKey();
            if (z && generateKey.getEncoded()[0] == 0) {
                for (int i = 0; i < 9; i++) {
                    SecretKey generateKey2 = keyGenerator.generateKey();
                    if (generateKey2.getEncoded()[0] != 0) {
                        return generateKey2;
                    }
                }
                throw new AmazonClientException("Failed to generate secret key");
            }
            return generateKey;
        } catch (NoSuchAlgorithmException e) {
            throw new AmazonClientException("Unable to generate envelope symmetric key:" + e.getMessage(), e);
        }
    }

    protected final <R extends AbstractPutObjectRequest> R wrapWithCipher(R r, ContentCryptoMaterial contentCryptoMaterial) {
        ObjectMetadata metadata = r.getMetadata();
        if (metadata == null) {
            metadata = new ObjectMetadata();
        }
        if (metadata.getContentMD5() != null) {
            metadata.addUserMetadata(Headers.UNENCRYPTED_CONTENT_MD5, metadata.getContentMD5());
        }
        metadata.setContentMD5(null);
        long plaintextLength = plaintextLength(r, metadata);
        if (plaintextLength >= 0) {
            metadata.addUserMetadata(Headers.UNENCRYPTED_CONTENT_LENGTH, Long.toString(plaintextLength));
            metadata.setContentLength(ciphertextLength(plaintextLength));
        }
        r.setMetadata(metadata);
        r.setInputStream(newS3CipherLiteInputStream(r, contentCryptoMaterial, plaintextLength));
        r.setFile(null);
        return r;
    }

    private CipherLiteInputStream newS3CipherLiteInputStream(AbstractPutObjectRequest abstractPutObjectRequest, ContentCryptoMaterial contentCryptoMaterial, long j) {
        File file = abstractPutObjectRequest.getFile();
        InputStream inputStream = abstractPutObjectRequest.getInputStream();
        FilterInputStream filterInputStream = null;
        try {
            if (file != null) {
                filterInputStream = new ResettableInputStream(file);
            } else if (inputStream != null) {
                filterInputStream = ReleasableInputStream.wrap(inputStream);
            }
            if (j > -1) {
                filterInputStream = new LengthCheckInputStream(filterInputStream, j, false);
            }
            CipherLite cipherLite = contentCryptoMaterial.getCipherLite();
            if (cipherLite.markSupported()) {
                return new CipherLiteInputStream(filterInputStream, cipherLite, 2048);
            }
            return new RenewableCipherLiteInputStream(filterInputStream, cipherLite, 2048);
        } catch (Exception e) {
            S3DataSource.Utils.cleanupDataSource(abstractPutObjectRequest, file, inputStream, null, this.log);
            throw new AmazonClientException("Unable to create cipher input stream", e);
        }
    }

    protected final long plaintextLength(AbstractPutObjectRequest abstractPutObjectRequest, ObjectMetadata objectMetadata) {
        if (abstractPutObjectRequest.getFile() != null) {
            return abstractPutObjectRequest.getFile().length();
        }
        if (abstractPutObjectRequest.getInputStream() == null || objectMetadata.getRawMetadataValue("Content-Length") == null) {
            return -1L;
        }
        return objectMetadata.getContentLength();
    }

    public final S3CryptoScheme getS3CryptoScheme() {
        return this.cryptoScheme;
    }

    protected final PutObjectRequest updateInstructionPutRequest(PutObjectRequest putObjectRequest, ContentCryptoMaterial contentCryptoMaterial) {
        byte[] bytes = contentCryptoMaterial.toJsonString(this.cryptoConfig.getCryptoMode()).getBytes(StringUtils.UTF8);
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        if (metadata == null) {
            metadata = new ObjectMetadata();
            putObjectRequest.setMetadata(metadata);
        }
        metadata.setContentLength(bytes.length);
        metadata.addUserMetadata(Headers.CRYPTO_INSTRUCTION_FILE, "");
        putObjectRequest.setMetadata(metadata);
        putObjectRequest.setInputStream(new ByteArrayInputStream(bytes));
        return putObjectRequest;
    }

    protected final PutObjectRequest createInstructionPutRequest(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(contentCryptoMaterial.toJsonString(this.cryptoConfig.getCryptoMode()).getBytes(StringUtils.UTF8));
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(r7.length);
        objectMetadata.addUserMetadata(Headers.CRYPTO_INSTRUCTION_FILE, "");
        InstructionFileId instructionFileId = new S3ObjectId(str, str2).instructionFileId();
        return new PutObjectRequest(instructionFileId.getBucket(), instructionFileId.getKey(), byteArrayInputStream, objectMetadata);
    }

    final <X extends AmazonWebServiceRequest> X appendUserAgent(X x, String str) {
        x.getRequestClientOptions().appendUserAgent(str);
        return x;
    }

    final S3ObjectWrapper fetchInstructionFile(S3ObjectId s3ObjectId, String str) {
        try {
            S3Object object = this.s3.getObject(createInstructionGetRequest(s3ObjectId, str));
            if (object == null) {
                return null;
            }
            return new S3ObjectWrapper(object, s3ObjectId);
        } catch (AmazonServiceException e) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Unable to retrieve instruction file : " + e.getMessage());
            }
            return null;
        }
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final PutObjectResult putInstructionFileSecurely(PutInstructionFileRequest putInstructionFileRequest) {
        ContentCryptoMaterial recreate;
        S3ObjectId s3ObjectId = putInstructionFileRequest.getS3ObjectId();
        GetObjectRequest getObjectRequest = new GetObjectRequest(s3ObjectId);
        appendUserAgent(getObjectRequest, AmazonS3EncryptionClient.USER_AGENT);
        S3Object object = this.s3.getObject(getObjectRequest);
        IOUtils.closeQuietly(object, this.log);
        if (object == null) {
            throw new IllegalArgumentException("The specified S3 object (" + s3ObjectId + ") doesn't exist.");
        }
        S3ObjectWrapper s3ObjectWrapper = new S3ObjectWrapper(object, s3ObjectId);
        try {
            ContentCryptoMaterial contentCryptoMaterialOf = contentCryptoMaterialOf(s3ObjectWrapper);
            if (ContentCryptoScheme.AES_GCM.equals(contentCryptoMaterialOf.getContentCryptoScheme()) && this.cryptoConfig.getCryptoMode() == CryptoMode.EncryptionOnly) {
                throw new SecurityException("Lowering the protection of encryption material is not allowed");
            }
            securityCheck(contentCryptoMaterialOf, s3ObjectWrapper);
            EncryptionMaterials encryptionMaterials = putInstructionFileRequest.getEncryptionMaterials();
            if (encryptionMaterials == null) {
                recreate = contentCryptoMaterialOf.recreate(putInstructionFileRequest.getMaterialsDescription(), this.kekMaterialsProvider, this.cryptoScheme, this.cryptoConfig.getCryptoProvider(), this.kms, putInstructionFileRequest);
            } else {
                recreate = contentCryptoMaterialOf.recreate(encryptionMaterials, this.kekMaterialsProvider, this.cryptoScheme, this.cryptoConfig.getCryptoProvider(), this.kms, putInstructionFileRequest);
            }
            return this.s3.putObject(updateInstructionPutRequest(putInstructionFileRequest.createPutObjectRequest(object), recreate));
        } catch (Error e) {
            IOUtils.closeQuietly(object, this.log);
            throw e;
        } catch (RuntimeException e2) {
            IOUtils.closeQuietly(object, this.log);
            throw e2;
        }
    }

    private ContentCryptoMaterial contentCryptoMaterialOf(S3ObjectWrapper s3ObjectWrapper) {
        if (s3ObjectWrapper.hasEncryptionInfo()) {
            return ContentCryptoMaterial.fromObjectMetadata(s3ObjectWrapper.getObjectMetadata(), this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), false, this.kms);
        }
        S3ObjectWrapper fetchInstructionFile = fetchInstructionFile(s3ObjectWrapper.getS3ObjectId(), null);
        if (fetchInstructionFile == null) {
            throw new IllegalArgumentException("S3 object is not encrypted: " + s3ObjectWrapper);
        }
        if (!fetchInstructionFile.isInstructionFile()) {
            throw new AmazonClientException("Invalid instruction file for S3 object: " + s3ObjectWrapper);
        }
        return ccmFromJson(fetchInstructionFile.toJsonString());
    }

    private ContentCryptoMaterial ccmFromJson(String str) {
        return ContentCryptoMaterial.fromInstructionFile(Collections.unmodifiableMap(JsonUtils.jsonToMap(str)), this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), false, this.kms);
    }

    final GetObjectRequest createInstructionGetRequest(S3ObjectId s3ObjectId) {
        return createInstructionGetRequest(s3ObjectId, null);
    }

    final GetObjectRequest createInstructionGetRequest(S3ObjectId s3ObjectId, String str) {
        return new GetObjectRequest(s3ObjectId.instructionFileId(str));
    }

    static long[] getAdjustedCryptoRange(long[] jArr) {
        if (jArr == null || jArr[0] > jArr[1]) {
            return null;
        }
        return new long[]{getCipherBlockLowerBound(jArr[0]), getCipherBlockUpperBound(jArr[1])};
    }

    private static long getCipherBlockLowerBound(long j) {
        long j2 = (j - (j % 16)) - 16;
        if (j2 < 0) {
            return 0L;
        }
        return j2;
    }

    private static long getCipherBlockUpperBound(long j) {
        long j2 = j + (16 - (j % 16)) + 16;
        if (j2 < 0) {
            return Long.MAX_VALUE;
        }
        return j2;
    }
}
