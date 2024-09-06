package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import java.io.InputStream;
import java.util.Map;

/* loaded from: classes.dex */
public class S3XmlResponseHandler<T> extends AbstractS3ResponseHandler<T> {
    private static final Log log = LogFactory.getLog("com.amazonaws.request");
    private Map<String, String> responseHeaders;
    private Unmarshaller<T, InputStream> responseUnmarshaller;

    public S3XmlResponseHandler(Unmarshaller<T, InputStream> unmarshaller) {
        this.responseUnmarshaller = unmarshaller;
    }

    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<T> handle(HttpResponse httpResponse) throws Exception {
        AmazonWebServiceResponse<T> parseResponseMetadata = parseResponseMetadata(httpResponse);
        this.responseHeaders = httpResponse.getHeaders();
        if (this.responseUnmarshaller != null) {
            log.trace("Beginning to parse service response XML");
            T unmarshall = this.responseUnmarshaller.unmarshall(httpResponse.getContent());
            log.trace("Done parsing service response XML");
            parseResponseMetadata.setResult(unmarshall);
        }
        return parseResponseMetadata;
    }

    public Map<String, String> getResponseHeaders() {
        return this.responseHeaders;
    }
}
