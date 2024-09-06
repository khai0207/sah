package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.auth.AWSCredentials;
import com.netease.nimlib.amazonaws.auth.AWSSessionCredentials;
import com.netease.nimlib.amazonaws.auth.AbstractAWSSigner;
import com.netease.nimlib.amazonaws.auth.SigningAlgorithm;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.util.HttpUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class S3Signer extends AbstractAWSSigner {
    private static final Log log = LogFactory.getLog((Class<?>) S3Signer.class);
    private final Set<String> additionalQueryParamsToSign;
    private final String httpVerb;
    private final String resourcePath;

    public S3Signer() {
        this.httpVerb = null;
        this.resourcePath = null;
        this.additionalQueryParamsToSign = null;
    }

    public S3Signer(String str, String str2) {
        this(str, str2, null);
    }

    public S3Signer(String str, String str2, Collection<String> collection) {
        if (str2 == null) {
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        }
        this.httpVerb = str;
        this.resourcePath = str2;
        this.additionalQueryParamsToSign = collection == null ? null : Collections.unmodifiableSet(new HashSet(collection));
    }

    void sign(Request<?> request, AWSCredentials aWSCredentials, Date date) {
        if (this.resourcePath == null) {
            throw new UnsupportedOperationException("Cannot sign a request using a dummy S3Signer instance with no resource path");
        }
        if (aWSCredentials == null || aWSCredentials.getAWSSecretKey() == null) {
            log.debug("Canonical string will not be signed, as no AWS Secret Key was provided");
            return;
        }
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        String appendUri = HttpUtils.appendUri(request.getEndpoint().getPath(), this.resourcePath, true);
        Date signatureDate = getSignatureDate(getTimeOffset(request));
        if (date == null) {
            date = signatureDate;
        }
        request.addHeader("Date", ServiceUtils.formatRfc822Date(date));
        String makeS3CanonicalString = RestUtils.makeS3CanonicalString(this.httpVerb, appendUri, request, null, this.additionalQueryParamsToSign);
        log.debug("Calculated string to sign:\n\"" + makeS3CanonicalString + "\"");
        request.addHeader(HttpHeader.AUTHORIZATION, "AWS " + sanitizeCredentials.getAWSAccessKeyId() + ":" + super.signAndBase64Encode(makeS3CanonicalString, sanitizeCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1));
    }

    @Override // com.netease.nimlib.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        sign(request, aWSCredentials, (Date) null);
    }

    @Override // com.netease.nimlib.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addHeader(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }
}
