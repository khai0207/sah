package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CopyPartResult;
import com.netease.nimlib.amazonaws.services.s3.model.GetObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectRequest;
import com.netease.nimlib.amazonaws.services.s3.model.PutObjectResult;
import com.netease.nimlib.amazonaws.services.s3.model.S3Object;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartResult;
import java.io.File;

/* loaded from: classes.dex */
public interface S3DirectSpi {
    void abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest);

    CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest);

    CopyPartResult copyPart(CopyPartRequest copyPartRequest);

    ObjectMetadata getObject(GetObjectRequest getObjectRequest, File file);

    S3Object getObject(GetObjectRequest getObjectRequest);

    InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest);

    PutObjectResult putObject(PutObjectRequest putObjectRequest);

    UploadPartResult uploadPart(UploadPartRequest uploadPartRequest);
}
