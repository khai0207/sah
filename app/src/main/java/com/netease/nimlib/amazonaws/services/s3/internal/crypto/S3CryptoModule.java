package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.services.s3.internal.crypto.MultipartUploadContext;
import com.netease.nimlib.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartResult;
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
public abstract class S3CryptoModule<T extends MultipartUploadContext> {
    public abstract void abortMultipartUploadSecurely(AbortMultipartUploadRequest abortMultipartUploadRequest);

    public abstract CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest completeMultipartUploadRequest);

    public abstract CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest);

    public abstract ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File file);

    public abstract S3Object getObjectSecurely(GetObjectRequest getObjectRequest);

    public abstract InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest initiateMultipartUploadRequest);

    public abstract PutObjectResult putInstructionFileSecurely(PutInstructionFileRequest putInstructionFileRequest);

    public abstract void putLocalObjectSecurely(UploadObjectRequest uploadObjectRequest, String str, OutputStream outputStream) throws IOException;

    public abstract PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest);

    public abstract UploadPartResult uploadPartSecurely(UploadPartRequest uploadPartRequest);
}
