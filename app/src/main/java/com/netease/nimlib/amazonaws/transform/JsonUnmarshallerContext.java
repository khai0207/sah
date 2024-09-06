package com.netease.nimlib.amazonaws.transform;

import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.util.json.AwsJsonReader;

/* loaded from: classes.dex */
public class JsonUnmarshallerContext {
    private final HttpResponse httpResponse;
    private final AwsJsonReader reader;

    public JsonUnmarshallerContext(AwsJsonReader awsJsonReader) {
        this(awsJsonReader, null);
    }

    public JsonUnmarshallerContext(AwsJsonReader awsJsonReader, HttpResponse httpResponse) {
        this.reader = awsJsonReader;
        this.httpResponse = httpResponse;
    }

    public AwsJsonReader getReader() {
        return this.reader;
    }

    public String getHeader(String str) {
        HttpResponse httpResponse = this.httpResponse;
        if (httpResponse == null) {
            return null;
        }
        return httpResponse.getHeaders().get(str);
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }
}
