package com.netease.nimlib.amazonaws.http;

import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.internal.CRC32MismatchException;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.transform.JsonUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import com.netease.nimlib.amazonaws.transform.VoidJsonUnmarshaller;
import com.netease.nimlib.amazonaws.util.CRC32ChecksumCalculatingInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;
import com.netease.nimlib.amazonaws.util.json.JsonUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/* loaded from: classes.dex */
public class JsonResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {
    private static final Log log = LogFactory.getLog("com.amazonaws.request");
    public boolean needsConnectionLeftOpen = false;
    private Unmarshaller<T, JsonUnmarshallerContext> responseUnmarshaller;

    @Deprecated
    protected void registerAdditionalMetadataExpressions(JsonUnmarshallerContext jsonUnmarshallerContext) {
    }

    public JsonResponseHandler(Unmarshaller<T, JsonUnmarshallerContext> unmarshaller) {
        this.responseUnmarshaller = unmarshaller;
        if (unmarshaller == null) {
            this.responseUnmarshaller = new VoidJsonUnmarshaller();
        }
    }

    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<T> handle(HttpResponse httpResponse) throws Exception {
        log.trace("Parsing service response JSON");
        String str = httpResponse.getHeaders().get("x-amz-crc32");
        InputStream rawContent = httpResponse.getRawContent();
        if (rawContent == null) {
            rawContent = new ByteArrayInputStream("{}".getBytes(StringUtils.UTF8));
        }
        log.debug("CRC32Checksum = " + str);
        log.debug("content encoding = " + httpResponse.getHeaders().get("Content-Encoding"));
        boolean equals = "gzip".equals(httpResponse.getHeaders().get("Content-Encoding"));
        CRC32ChecksumCalculatingInputStream cRC32ChecksumCalculatingInputStream = null;
        if (str != null) {
            cRC32ChecksumCalculatingInputStream = new CRC32ChecksumCalculatingInputStream(rawContent);
            rawContent = cRC32ChecksumCalculatingInputStream;
        }
        if (equals) {
            rawContent = new GZIPInputStream(rawContent);
        }
        AwsJsonReader jsonReader = JsonUtils.getJsonReader(new InputStreamReader(rawContent, StringUtils.UTF8));
        try {
            AmazonWebServiceResponse<T> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
            T unmarshall = this.responseUnmarshaller.unmarshall(new JsonUnmarshallerContext(jsonReader, httpResponse));
            if (cRC32ChecksumCalculatingInputStream != null) {
                if (cRC32ChecksumCalculatingInputStream.getCRC32Checksum() != Long.parseLong(str)) {
                    throw new CRC32MismatchException("Client calculated crc32 checksum didn't match that calculated by server side");
                }
            }
            amazonWebServiceResponse.setResult(unmarshall);
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseMetadata.AWS_REQUEST_ID, httpResponse.getHeaders().get("x-amzn-RequestId"));
            amazonWebServiceResponse.setResponseMetadata(new ResponseMetadata(hashMap));
            log.trace("Done parsing service response");
            return amazonWebServiceResponse;
        } finally {
            if (!this.needsConnectionLeftOpen) {
                try {
                    jsonReader.close();
                } catch (IOException e) {
                    log.warn("Error closing json parser", e);
                }
            }
        }
    }

    @Override // com.netease.nimlib.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return this.needsConnectionLeftOpen;
    }
}
