package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.http.HttpResponseHandler;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.S3ResponseMetadata;
import com.netease.nimlib.amazonaws.services.s3.model.ObjectMetadata;
import com.netease.nimlib.amazonaws.util.DateUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class AbstractS3ResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {
    private static final Set<String> IGNORED_HEADERS;
    private static final Log log = LogFactory.getLog((Class<?>) S3MetadataResponseHandler.class);

    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    static {
        HashSet hashSet = new HashSet();
        IGNORED_HEADERS = hashSet;
        hashSet.add("Date");
        IGNORED_HEADERS.add(Headers.SERVER);
        IGNORED_HEADERS.add(Headers.REQUEST_ID);
        IGNORED_HEADERS.add(Headers.EXTENDED_REQUEST_ID);
        IGNORED_HEADERS.add(Headers.CLOUD_FRONT_ID);
        IGNORED_HEADERS.add(Headers.CONNECTION);
    }

    protected AmazonWebServiceResponse<T> parseResponseMetadata(HttpResponse httpResponse) {
        AmazonWebServiceResponse<T> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
        String str = httpResponse.getHeaders().get(Headers.REQUEST_ID);
        String str2 = httpResponse.getHeaders().get(Headers.EXTENDED_REQUEST_ID);
        String str3 = httpResponse.getHeaders().get(Headers.CLOUD_FRONT_ID);
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseMetadata.AWS_REQUEST_ID, str);
        hashMap.put(S3ResponseMetadata.HOST_ID, str2);
        hashMap.put(S3ResponseMetadata.CLOUD_FRONT_ID, str3);
        amazonWebServiceResponse.setResponseMetadata(new S3ResponseMetadata(hashMap));
        return amazonWebServiceResponse;
    }

    protected void populateObjectMetadata(HttpResponse httpResponse, ObjectMetadata objectMetadata) {
        for (Map.Entry<String, String> entry : httpResponse.getHeaders().entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(Headers.S3_USER_METADATA_PREFIX)) {
                objectMetadata.addUserMetadata(key.substring(11), entry.getValue());
            } else if (IGNORED_HEADERS.contains(key)) {
                log.debug(String.format("%s is ignored.", key));
            } else if (key.equalsIgnoreCase(Headers.LAST_MODIFIED)) {
                try {
                    objectMetadata.setHeader(key, ServiceUtils.parseRfc822Date(entry.getValue()));
                } catch (Exception e) {
                    log.warn("Unable to parse last modified date: " + entry.getValue(), e);
                }
            } else if (key.equalsIgnoreCase("Content-Length")) {
                try {
                    objectMetadata.setHeader(key, Long.valueOf(Long.parseLong(entry.getValue())));
                } catch (NumberFormatException e2) {
                    log.warn("Unable to parse content length: " + entry.getValue(), e2);
                }
            } else if (key.equalsIgnoreCase("ETag")) {
                objectMetadata.setHeader(key, ServiceUtils.removeQuotes(entry.getValue()));
            } else if (key.equalsIgnoreCase(Headers.EXPIRES)) {
                try {
                    objectMetadata.setHttpExpiresDate(DateUtils.parseRFC822Date(entry.getValue()));
                } catch (Exception e3) {
                    log.warn("Unable to parse http expiration date: " + entry.getValue(), e3);
                }
            } else if (key.equalsIgnoreCase(Headers.EXPIRATION)) {
                new ObjectExpirationHeaderHandler().handle((ObjectExpirationHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.RESTORE)) {
                new ObjectRestoreHeaderHandler().handle((ObjectRestoreHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.REQUESTER_CHARGED_HEADER)) {
                new S3RequesterChargedHeaderHandler().handle((S3RequesterChargedHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.S3_PARTS_COUNT)) {
                try {
                    objectMetadata.setHeader(key, Integer.valueOf(Integer.parseInt(entry.getValue())));
                } catch (NumberFormatException e4) {
                    throw new AmazonClientException("Unable to parse part count. Header x-amz-mp-parts-count has corrupted data" + e4.getMessage(), e4);
                }
            } else {
                objectMetadata.setHeader(key, entry.getValue());
            }
        }
    }
}
