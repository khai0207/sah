package com.netease.nimlib.amazonaws.http;

import com.google.gson.Gson;
import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.AmazonWebServiceRequest;
import com.netease.nimlib.amazonaws.AmazonWebServiceResponse;
import com.netease.nimlib.amazonaws.ClientConfiguration;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.RequestClientOptions;
import com.netease.nimlib.amazonaws.Response;
import com.netease.nimlib.amazonaws.ResponseMetadata;
import com.netease.nimlib.amazonaws.handlers.CredentialsRequestHandler;
import com.netease.nimlib.amazonaws.handlers.RequestHandler2;
import com.netease.nimlib.amazonaws.internal.CRC32MismatchException;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.metrics.RequestMetricCollector;
import com.netease.nimlib.amazonaws.retry.RetryPolicy;
import com.netease.nimlib.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.netease.nimlib.amazonaws.services.s3.model.UploadPartRequest;
import com.netease.nimlib.amazonaws.util.AWSRequestMetrics;
import com.netease.nimlib.amazonaws.util.DateUtils;
import com.netease.nimlib.amazonaws.util.TimingInfo;
import com.netease.nimlib.amazonaws.util.URIBuilder;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.n.b.j;
import com.netease.nimlib.n.e;
import com.netease.nimlib.o.w;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AmazonHttpClient {
    private static final String HEADER_SDK_RETRY_INFO = "aws-sdk-retry";
    private static final String HEADER_SDK_TRANSACTION_ID = "aws-sdk-invocation-id";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final int HTTP_STATUS_MULTIPLE_CHOICES = 300;
    private static final int HTTP_STATUS_OK = 200;
    private static final int HTTP_STATUS_REQ_TOO_LONG = 413;
    private static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    private static final int HTTP_STATUS_TEMP_REDIRECT = 307;
    private static final long TIME_MILLISEC = 1000;
    final ClientConfiguration config;
    final HttpClient httpClient;
    private final HttpRequestFactory requestFactory;
    private final RequestMetricCollector requestMetricCollector;
    private static final Log REQUEST_LOG = LogFactory.getLog("com.amazonaws.request");
    static final Log log = LogFactory.getLog((Class<?>) AmazonHttpClient.class);
    private static final Gson gson = new Gson();

    @Deprecated
    public ResponseMetadata getResponseMetadataForRequest(AmazonWebServiceRequest amazonWebServiceRequest) {
        return null;
    }

    public AmazonHttpClient(ClientConfiguration clientConfiguration) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration), requestMetricCollector);
    }

    public AmazonHttpClient(ClientConfiguration clientConfiguration, HttpClient httpClient) {
        this.requestFactory = new HttpRequestFactory();
        this.config = clientConfiguration;
        this.httpClient = httpClient;
        this.requestMetricCollector = null;
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration clientConfiguration, HttpClient httpClient, RequestMetricCollector requestMetricCollector) {
        this.requestFactory = new HttpRequestFactory();
        this.config = clientConfiguration;
        this.httpClient = httpClient;
        this.requestMetricCollector = requestMetricCollector;
    }

    public <T> Response<T> execute(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> httpResponseHandler, HttpResponseHandler<AmazonServiceException> httpResponseHandler2, ExecutionContext executionContext) {
        if (request.getHostPrefix() != null) {
            try {
                URI endpoint = request.getEndpoint();
                request.setEndpoint(URIBuilder.builder(endpoint).host(request.getHostPrefix() + endpoint.getHost()).build());
            } catch (URISyntaxException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed to prepend host prefix: " + e.getMessage(), e);
                }
            }
        }
        if (executionContext == null) {
            throw new AmazonClientException("Internal SDK Error: No execution context parameter specified.");
        }
        List<RequestHandler2> requestHandler2s = requestHandler2s(request, executionContext);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        Response<T> response = null;
        try {
            response = executeHelper(request, httpResponseHandler, httpResponseHandler2, executionContext);
            afterResponse(request, requestHandler2s, response, awsRequestMetrics.getTimingInfo().endTiming());
            return response;
        } catch (AmazonClientException e2) {
            afterError(request, response, requestHandler2s, e2);
            throw e2;
        }
    }

    void afterError(Request<?> request, Response<?> response, List<RequestHandler2> list, AmazonClientException amazonClientException) {
        Iterator<RequestHandler2> it = list.iterator();
        while (it.hasNext()) {
            it.next().afterError(request, response, amazonClientException);
        }
    }

    <T> void afterResponse(Request<?> request, List<RequestHandler2> list, Response<T> response, TimingInfo timingInfo) {
        Iterator<RequestHandler2> it = list.iterator();
        while (it.hasNext()) {
            it.next().afterResponse(request, response);
        }
    }

    List<RequestHandler2> requestHandler2s(Request<?> request, ExecutionContext executionContext) {
        List<RequestHandler2> requestHandler2s = executionContext.getRequestHandler2s();
        if (requestHandler2s == null) {
            return Collections.emptyList();
        }
        for (RequestHandler2 requestHandler2 : requestHandler2s) {
            if (requestHandler2 instanceof CredentialsRequestHandler) {
                ((CredentialsRequestHandler) requestHandler2).setCredentials(executionContext.getCredentials());
            }
            requestHandler2.beforeRequest(request);
        }
        return requestHandler2s;
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x01c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x015f A[Catch: all -> 0x043e, Error -> 0x0444, RuntimeException -> 0x045d, IOException -> 0x0476, TryCatch #4 {IOException -> 0x0476, blocks: (B:287:0x0116, B:289:0x0121, B:291:0x0127, B:26:0x0142, B:28:0x015f, B:31:0x016b, B:34:0x0173, B:37:0x017a, B:38:0x0180, B:69:0x0181, B:71:0x0189, B:72:0x01a3), top: B:286:0x0116 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x048e A[Catch: all -> 0x043e, TRY_ENTER, TryCatch #13 {all -> 0x043e, blocks: (B:283:0x0103, B:287:0x0116, B:289:0x0121, B:291:0x0127, B:26:0x0142, B:28:0x015f, B:31:0x016b, B:34:0x0173, B:37:0x017a, B:38:0x0180, B:69:0x0181, B:71:0x0189, B:72:0x01a3, B:74:0x01ab, B:44:0x0484, B:47:0x048e, B:48:0x04a6, B:50:0x04ea, B:65:0x052f, B:66:0x0532, B:124:0x0449, B:125:0x045c, B:128:0x0462, B:129:0x0475, B:273:0x041f, B:274:0x0424, B:294:0x012c, B:295:0x0131), top: B:282:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x04ea A[Catch: all -> 0x043e, TRY_LEAVE, TryCatch #13 {all -> 0x043e, blocks: (B:283:0x0103, B:287:0x0116, B:289:0x0121, B:291:0x0127, B:26:0x0142, B:28:0x015f, B:31:0x016b, B:34:0x0173, B:37:0x017a, B:38:0x0180, B:69:0x0181, B:71:0x0189, B:72:0x01a3, B:74:0x01ab, B:44:0x0484, B:47:0x048e, B:48:0x04a6, B:50:0x04ea, B:65:0x052f, B:66:0x0532, B:124:0x0449, B:125:0x045c, B:128:0x0462, B:129:0x0475, B:273:0x041f, B:274:0x0424, B:294:0x012c, B:295:0x0131), top: B:282:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0524 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0189 A[Catch: all -> 0x043e, Error -> 0x0444, RuntimeException -> 0x045d, IOException -> 0x0476, TryCatch #4 {IOException -> 0x0476, blocks: (B:287:0x0116, B:289:0x0121, B:291:0x0127, B:26:0x0142, B:28:0x015f, B:31:0x016b, B:34:0x0173, B:37:0x017a, B:38:0x0180, B:69:0x0181, B:71:0x0189, B:72:0x01a3), top: B:286:0x0116 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    <T> com.netease.nimlib.amazonaws.Response<T> executeHelper(com.netease.nimlib.amazonaws.Request<?> r31, com.netease.nimlib.amazonaws.http.HttpResponseHandler<com.netease.nimlib.amazonaws.AmazonWebServiceResponse<T>> r32, com.netease.nimlib.amazonaws.http.HttpResponseHandler<com.netease.nimlib.amazonaws.AmazonServiceException> r33, com.netease.nimlib.amazonaws.http.ExecutionContext r34) {
        /*
            Method dump skipped, instructions count: 1358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.amazonaws.http.AmazonHttpClient.executeHelper(com.netease.nimlib.amazonaws.Request, com.netease.nimlib.amazonaws.http.HttpResponseHandler, com.netease.nimlib.amazonaws.http.HttpResponseHandler, com.netease.nimlib.amazonaws.http.ExecutionContext):com.netease.nimlib.amazonaws.Response");
    }

    private static String getHttpProcessId(Request<?> request, String str) {
        if (request.getOriginalRequest() instanceof InitiateMultipartUploadRequest) {
            return "InitiateMultipartUploadRequest: " + str;
        }
        if (request.getOriginalRequest() instanceof UploadPartRequest) {
            return ((UploadPartRequest) request.getOriginalRequest()).getUploadId();
        }
        if (request.getOriginalRequest() instanceof CompleteMultipartUploadRequest) {
            return ((CompleteMultipartUploadRequest) request.getOriginalRequest()).getUploadId();
        }
        return request.getOriginalRequest() instanceof AbortMultipartUploadRequest ? ((AbortMultipartUploadRequest) request.getOriginalRequest()).getUploadId() : str;
    }

    public static String reportStart(String str) {
        String b = w.b();
        log.debug(String.format("reportStart: %s %s", b, str));
        e.a(b, g.FCS_NEXT);
        return b;
    }

    public static void reportFailed(String str, String str2, Throwable th, Request<?> request, HttpResponse httpResponse, JSONObject jSONObject) {
        j jVar;
        String str3;
        String str4;
        j jVar2;
        log.debug(String.format("reportFailed: %s %s %s %s", str, str2, th, request));
        if (request == null) {
            str3 = "";
            str4 = str3;
            jVar2 = j.kPost;
        } else {
            String format = String.format("%s %s", request.getEndpoint(), request.getEncodedUriResourcePath());
            String json = gson.toJson(request.getHeaders());
            if (request.getHttpMethod() == HttpMethodName.GET) {
                jVar = j.kGet;
            } else if (request.getHttpMethod() == HttpMethodName.POST) {
                jVar = j.kPost;
            } else if (request.getHttpMethod() == HttpMethodName.PUT) {
                jVar = j.kPut;
            } else if (request.getHttpMethod() == HttpMethodName.DELETE) {
                jVar = j.kDelete;
            } else {
                jVar = j.kPost;
            }
            str3 = format;
            str4 = json;
            jVar2 = jVar;
        }
        if (request != null) {
            try {
                jSONObject.put("HTTPMethod", request.getHttpMethod());
            } catch (Throwable th2) {
                log.error("reportFailed catch json Throwable", th2);
            }
        }
        jSONObject.put("error", th);
        e.a(str, str2, jVar2, str3, httpResponse != null ? httpResponse.getStatusCode() : -1, str4, null, jSONObject.toString());
    }

    public static void reportSucceed(String str, String str2) {
        log.debug(String.format("reportSucceed: %s %s", str, str2));
        e.a(str2);
    }

    private <T extends Throwable> T handleUnexpectedFailure(T t, AWSRequestMetrics aWSRequestMetrics) {
        aWSRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
        aWSRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, t);
        return t;
    }

    void resetRequestAfterError(Request<?> request, Exception exc) {
        if (request.getContent() == null) {
            return;
        }
        if (!request.getContent().markSupported()) {
            throw new AmazonClientException("Encountered an exception and stream is not resettable", exc);
        }
        try {
            request.getContent().reset();
        } catch (IOException unused) {
            throw new AmazonClientException("Encountered an exception and couldn't reset the stream to retry", exc);
        }
    }

    void setUserAgent(Request<?> request) {
        RequestClientOptions requestClientOptions;
        String clientMarker;
        String str = ClientConfiguration.DEFAULT_USER_AGENT;
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && (requestClientOptions = originalRequest.getRequestClientOptions()) != null && (clientMarker = requestClientOptions.getClientMarker(RequestClientOptions.Marker.USER_AGENT)) != null) {
            str = createUserAgentString(str, clientMarker);
        }
        if (!ClientConfiguration.DEFAULT_USER_AGENT.equals(this.config.getUserAgent())) {
            str = createUserAgentString(str, this.config.getUserAgent());
        }
        if (this.config.getUserAgentOverride() != null) {
            str = this.config.getUserAgentOverride();
        }
        request.addHeader("User-Agent", str);
    }

    static String createUserAgentString(String str, String str2) {
        if (str.contains(str2)) {
            return str;
        }
        return str.trim() + " " + str2.trim();
    }

    public void shutdown() {
        this.httpClient.shutdown();
    }

    private boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, InputStream inputStream, AmazonClientException amazonClientException, int i, RetryPolicy retryPolicy) {
        int i2 = i - 1;
        int maxErrorRetry = this.config.getMaxErrorRetry();
        if (maxErrorRetry < 0 || !retryPolicy.isMaxErrorRetryInClientConfigHonored()) {
            maxErrorRetry = retryPolicy.getMaxErrorRetry();
        }
        if (i2 >= maxErrorRetry) {
            return false;
        }
        if (inputStream != null && !inputStream.markSupported()) {
            if (log.isDebugEnabled()) {
                log.debug("Content not repeatable");
            }
            return false;
        }
        return retryPolicy.getRetryCondition().shouldRetry(amazonWebServiceRequest, amazonClientException, i2);
    }

    private static boolean isTemporaryRedirect(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusCode();
        String str = httpResponse.getHeaders().get(HttpHeader.LOCATION);
        return (statusCode != 307 || str == null || str.isEmpty()) ? false : true;
    }

    private boolean isRequestSuccessful(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusCode();
        return statusCode >= 200 && statusCode < 300;
    }

    <T> T handleResponse(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> httpResponseHandler, HttpResponse httpResponse, ExecutionContext executionContext) throws IOException {
        try {
            AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
            awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ResponseProcessingTime);
            try {
                AmazonWebServiceResponse<T> handle = httpResponseHandler.handle(httpResponse);
                if (handle == null) {
                    throw new RuntimeException("Unable to unmarshall response metadata. Response Code: " + httpResponse.getStatusCode() + ", Response Text: " + httpResponse.getStatusText());
                }
                if (REQUEST_LOG.isDebugEnabled()) {
                    REQUEST_LOG.debug("Received successful response: " + httpResponse.getStatusCode() + ", AWS Request ID: " + handle.getRequestId());
                }
                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, handle.getRequestId());
                return handle.getResult();
            } finally {
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ResponseProcessingTime);
            }
        } catch (CRC32MismatchException e) {
            throw e;
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new AmazonClientException("Unable to unmarshall response (" + e3.getMessage() + "). Response Code: " + httpResponse.getStatusCode() + ", Response Text: " + httpResponse.getStatusText(), e3);
        }
    }

    AmazonServiceException handleErrorResponse(Request<?> request, HttpResponseHandler<AmazonServiceException> httpResponseHandler, HttpResponse httpResponse) throws IOException {
        AmazonServiceException amazonServiceException;
        int statusCode = httpResponse.getStatusCode();
        try {
            amazonServiceException = httpResponseHandler.handle(httpResponse);
            REQUEST_LOG.debug("Received error response: " + amazonServiceException.toString());
        } catch (Exception e) {
            if (statusCode == HTTP_STATUS_REQ_TOO_LONG) {
                amazonServiceException = new AmazonServiceException("Request entity too large");
                amazonServiceException.setServiceName(request.getServiceName());
                amazonServiceException.setStatusCode(HTTP_STATUS_REQ_TOO_LONG);
                amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Client);
                amazonServiceException.setErrorCode("Request entity too large");
            } else if (statusCode == HTTP_STATUS_SERVICE_UNAVAILABLE && "Service Unavailable".equalsIgnoreCase(httpResponse.getStatusText())) {
                amazonServiceException = new AmazonServiceException("Service unavailable");
                amazonServiceException.setServiceName(request.getServiceName());
                amazonServiceException.setStatusCode(HTTP_STATUS_SERVICE_UNAVAILABLE);
                amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Service);
                amazonServiceException.setErrorCode("Service unavailable");
            } else {
                if (e instanceof IOException) {
                    throw ((IOException) e);
                }
                throw new AmazonClientException("Unable to unmarshall error response (" + e.getMessage() + "). Response Code: " + statusCode + ", Response Text: " + httpResponse.getStatusText() + ", Response Headers: " + httpResponse.getHeaders(), e);
            }
        }
        amazonServiceException.setStatusCode(statusCode);
        amazonServiceException.setServiceName(request.getServiceName());
        amazonServiceException.fillInStackTrace();
        return amazonServiceException;
    }

    private long pauseBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i, RetryPolicy retryPolicy) {
        int i2 = (i - 1) - 1;
        long delayBeforeNextRetry = retryPolicy.getBackoffStrategy().delayBeforeNextRetry(amazonWebServiceRequest, amazonClientException, i2);
        if (log.isDebugEnabled()) {
            log.debug("Retriable error detected, will retry in " + delayBeforeNextRetry + "ms, attempt number: " + i2);
        }
        try {
            Thread.sleep(delayBeforeNextRetry);
            return delayBeforeNextRetry;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AmazonClientException(e.getMessage(), e);
        }
    }

    private String getServerDateFromException(String str) {
        int indexOf;
        int indexOf2 = str.indexOf("(");
        if (str.contains(" + 15")) {
            indexOf = str.indexOf(" + 15");
        } else {
            indexOf = str.indexOf(" - 15");
        }
        return str.substring(indexOf2 + 1, indexOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    long parseClockSkewOffset(HttpResponse httpResponse, AmazonServiceException amazonServiceException) {
        Date parseRFC822Date;
        Date date = new Date();
        String str = httpResponse.getHeaders().get("Date");
        try {
            if (str != 0) {
                try {
                    if (!str.isEmpty()) {
                        parseRFC822Date = DateUtils.parseRFC822Date(str);
                        long time = date.getTime() - parseRFC822Date.getTime();
                        str = 1000;
                        return time / TIME_MILLISEC;
                    }
                } catch (RuntimeException e) {
                    e = e;
                    str = 0;
                    log.warn("Unable to parse clock skew offset from response: " + str, e);
                    return 0L;
                }
            }
            parseRFC822Date = DateUtils.parseCompressedISO8601Date(getServerDateFromException(amazonServiceException.getMessage()));
            long time2 = date.getTime() - parseRFC822Date.getTime();
            str = 1000;
            return time2 / TIME_MILLISEC;
        } catch (RuntimeException e2) {
            e = e2;
        }
    }

    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    public RequestMetricCollector getRequestMetricCollector() {
        return this.requestMetricCollector;
    }
}
