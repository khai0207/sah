package okhttp3.internal.http;

import com.alipay.sdk.m.j.a;
import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.InternalCache;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.CacheStrategy;
import okhttp3.internal.io.RealConnection;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class HttpEngine {
    private static final ResponseBody EMPTY_BODY = new ResponseBody() { // from class: okhttp3.internal.http.HttpEngine.1
        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return 0L;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return new Buffer();
        }
    };
    public static final int MAX_FOLLOW_UPS = 20;
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    private final boolean callerWritesRequestBody;
    final OkHttpClient client;
    private final boolean forWebSocket;
    private HttpStream httpStream;
    private Request networkRequest;
    private final Response priorResponse;
    private Sink requestBodyOut;
    long sentRequestMillis = -1;
    private CacheRequest storeRequest;
    public final StreamAllocation streamAllocation;
    private boolean transparentGzip;
    private final Request userRequest;
    private Response userResponse;

    /* renamed from: okhttp3.internal.http.HttpEngine$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends ResponseBody {
        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return 0L;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return new Buffer();
        }
    }

    public HttpEngine(OkHttpClient okHttpClient, Request request, boolean z, boolean z2, boolean z3, StreamAllocation streamAllocation, RetryableSink retryableSink, Response response) {
        this.client = okHttpClient;
        this.userRequest = request;
        this.bufferRequestBody = z;
        this.callerWritesRequestBody = z2;
        this.forWebSocket = z3;
        this.streamAllocation = streamAllocation == null ? new StreamAllocation(okHttpClient.connectionPool(), createAddress(okHttpClient, request)) : streamAllocation;
        this.requestBodyOut = retryableSink;
        this.priorResponse = response;
    }

    public void sendRequest() throws RequestException, RouteException, IOException {
        if (this.cacheStrategy != null) {
            return;
        }
        if (this.httpStream != null) {
            throw new IllegalStateException();
        }
        Request networkRequest = networkRequest(this.userRequest);
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        Response response = internalCache != null ? internalCache.get(networkRequest) : null;
        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), networkRequest, response).get();
        this.cacheStrategy = cacheStrategy;
        this.networkRequest = cacheStrategy.networkRequest;
        this.cacheResponse = this.cacheStrategy.cacheResponse;
        if (internalCache != null) {
            internalCache.trackResponse(this.cacheStrategy);
        }
        if (response != null && this.cacheResponse == null) {
            Util.closeQuietly(response.body());
        }
        if (this.networkRequest == null && this.cacheResponse == null) {
            this.userResponse = new Response.Builder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            return;
        }
        if (this.networkRequest == null) {
            Response build = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).build();
            this.userResponse = build;
            this.userResponse = unzip(build);
            return;
        }
        try {
            HttpStream connect = connect();
            this.httpStream = connect;
            connect.setHttpEngine(this);
            if (writeRequestHeadersEagerly()) {
                long contentLength = OkHeaders.contentLength(networkRequest);
                if (!this.bufferRequestBody) {
                    this.httpStream.writeRequestHeaders(this.networkRequest);
                    this.requestBodyOut = this.httpStream.createRequestBody(this.networkRequest, contentLength);
                } else {
                    if (contentLength > 2147483647L) {
                        throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                    }
                    if (contentLength != -1) {
                        this.httpStream.writeRequestHeaders(this.networkRequest);
                        this.requestBodyOut = new RetryableSink((int) contentLength);
                    } else {
                        this.requestBodyOut = new RetryableSink();
                    }
                }
            }
        } catch (Throwable th) {
            if (response != null) {
                Util.closeQuietly(response.body());
            }
            throw th;
        }
    }

    private boolean writeRequestHeadersEagerly() {
        return this.callerWritesRequestBody && permitsRequestBody(this.networkRequest) && this.requestBodyOut == null;
    }

    private HttpStream connect() throws RouteException, RequestException, IOException {
        return this.streamAllocation.newStream(this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis(), this.client.retryOnConnectionFailure(), !this.networkRequest.method().equals("GET"));
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis != -1) {
            throw new IllegalStateException();
        }
        this.sentRequestMillis = System.currentTimeMillis();
    }

    boolean permitsRequestBody(Request request) {
        return HttpMethod.permitsRequestBody(request.method());
    }

    public Sink getRequestBody() {
        if (this.cacheStrategy == null) {
            throw new IllegalStateException();
        }
        return this.requestBodyOut;
    }

    public BufferedSink getBufferedRequestBody() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            return bufferedSink;
        }
        Sink requestBody = getRequestBody();
        if (requestBody == null) {
            return null;
        }
        BufferedSink buffer = Okio.buffer(requestBody);
        this.bufferedRequestBody = buffer;
        return buffer;
    }

    public boolean hasResponse() {
        return this.userResponse != null;
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public Connection getConnection() {
        return this.streamAllocation.connection();
    }

    public HttpEngine recover(IOException iOException, boolean z, Sink sink) {
        this.streamAllocation.streamFailed(iOException);
        if (!this.client.retryOnConnectionFailure()) {
            return null;
        }
        if ((sink != null && !(sink instanceof RetryableSink)) || !isRecoverable(iOException, z) || !this.streamAllocation.hasMoreRoutes()) {
            return null;
        }
        return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, close(), (RetryableSink) sink, this.priorResponse);
    }

    public HttpEngine recover(IOException iOException, boolean z) {
        return recover(iOException, z, this.requestBodyOut);
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private void maybeCache() throws IOException {
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache == null) {
            return;
        }
        if (!CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
            if (HttpMethod.invalidatesCache(this.networkRequest.method())) {
                try {
                    internalCache.remove(this.networkRequest);
                    return;
                } catch (IOException unused) {
                    return;
                }
            }
            return;
        }
        this.storeRequest = internalCache.put(this.userResponse);
    }

    public void releaseStreamAllocation() throws IOException {
        this.streamAllocation.release();
    }

    public void cancel() {
        this.streamAllocation.cancel();
    }

    public StreamAllocation close() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            Util.closeQuietly(bufferedSink);
        } else {
            Sink sink = this.requestBodyOut;
            if (sink != null) {
                Util.closeQuietly(sink);
            }
        }
        Response response = this.userResponse;
        if (response != null) {
            Util.closeQuietly(response.body());
        } else {
            this.streamAllocation.streamFailed(null);
        }
        return this.streamAllocation;
    }

    private Response unzip(Response response) throws IOException {
        if (!this.transparentGzip || !"gzip".equalsIgnoreCase(this.userResponse.header("Content-Encoding")) || response.body() == null) {
            return response;
        }
        GzipSource gzipSource = new GzipSource(response.body().source());
        Headers build = response.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build();
        return response.newBuilder().headers(build).body(new RealResponseBody(build, Okio.buffer(gzipSource))).build();
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int code = response.code();
        return (((code >= 100 && code < 200) || code == 204 || code == 304) && OkHeaders.contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) ? false : true;
    }

    private Request networkRequest(Request request) throws IOException {
        Request.Builder newBuilder = request.newBuilder();
        if (request.header(HttpHeader.HOST) == null) {
            newBuilder.header(HttpHeader.HOST, Util.hostHeader(request.url(), false));
        }
        if (request.header(com.netease.nimlib.amazonaws.services.s3.Headers.CONNECTION) == null) {
            newBuilder.header(com.netease.nimlib.amazonaws.services.s3.Headers.CONNECTION, "Keep-Alive");
        }
        if (request.header("Accept-Encoding") == null) {
            this.transparentGzip = true;
            newBuilder.header("Accept-Encoding", "gzip");
        }
        List<Cookie> loadForRequest = this.client.cookieJar().loadForRequest(request.url());
        if (!loadForRequest.isEmpty()) {
            newBuilder.header("Cookie", cookieHeader(loadForRequest));
        }
        if (request.header(HttpHeader.USER_AGENT) == null) {
            newBuilder.header(HttpHeader.USER_AGENT, Version.userAgent());
        }
        return newBuilder.build();
    }

    private String cookieHeader(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i);
            sb.append(cookie.name());
            sb.append(a.h);
            sb.append(cookie.value());
        }
        return sb.toString();
    }

    public void readResponse() throws IOException {
        Response readNetworkResponse;
        if (this.userResponse != null) {
            return;
        }
        if (this.networkRequest == null && this.cacheResponse == null) {
            throw new IllegalStateException("call sendRequest() first!");
        }
        Request request = this.networkRequest;
        if (request == null) {
            return;
        }
        if (this.forWebSocket) {
            this.httpStream.writeRequestHeaders(request);
            readNetworkResponse = readNetworkResponse();
        } else if (!this.callerWritesRequestBody) {
            readNetworkResponse = new NetworkInterceptorChain(0, request, this.streamAllocation.connection()).proceed(this.networkRequest);
        } else {
            BufferedSink bufferedSink = this.bufferedRequestBody;
            if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
                this.bufferedRequestBody.emit();
            }
            if (this.sentRequestMillis == -1) {
                if (OkHeaders.contentLength(this.networkRequest) == -1) {
                    Sink sink = this.requestBodyOut;
                    if (sink instanceof RetryableSink) {
                        this.networkRequest = this.networkRequest.newBuilder().header("Content-Length", Long.toString(((RetryableSink) sink).contentLength())).build();
                    }
                }
                this.httpStream.writeRequestHeaders(this.networkRequest);
            }
            Sink sink2 = this.requestBodyOut;
            if (sink2 != null) {
                BufferedSink bufferedSink2 = this.bufferedRequestBody;
                if (bufferedSink2 != null) {
                    bufferedSink2.close();
                } else {
                    sink2.close();
                }
                Sink sink3 = this.requestBodyOut;
                if (sink3 instanceof RetryableSink) {
                    this.httpStream.writeRequestBody((RetryableSink) sink3);
                }
            }
            readNetworkResponse = readNetworkResponse();
        }
        receiveHeaders(readNetworkResponse.headers());
        Response response = this.cacheResponse;
        if (response != null) {
            if (validate(response, readNetworkResponse)) {
                this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).headers(combine(this.cacheResponse.headers(), readNetworkResponse.headers())).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(readNetworkResponse)).build();
                readNetworkResponse.body().close();
                releaseStreamAllocation();
                InternalCache internalCache = Internal.instance.internalCache(this.client);
                internalCache.trackConditionalCacheHit();
                internalCache.update(this.cacheResponse, this.userResponse);
                this.userResponse = unzip(this.userResponse);
                return;
            }
            Util.closeQuietly(this.cacheResponse.body());
        }
        Response build = readNetworkResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(readNetworkResponse)).build();
        this.userResponse = build;
        if (hasBody(build)) {
            maybeCache();
            this.userResponse = unzip(cacheWritingResponse(this.storeRequest, this.userResponse));
        }
    }

    /* loaded from: classes.dex */
    class NetworkInterceptorChain implements Interceptor.Chain {
        private int calls;
        private final Connection connection;
        private final int index;
        private final Request request;

        NetworkInterceptorChain(int i, Request request, Connection connection) {
            this.index = i;
            this.request = request;
            this.connection = connection;
        }

        @Override // okhttp3.Interceptor.Chain
        public Connection connection() {
            return this.connection;
        }

        @Override // okhttp3.Interceptor.Chain
        public Request request() {
            return this.request;
        }

        @Override // okhttp3.Interceptor.Chain
        public Response proceed(Request request) throws IOException {
            this.calls++;
            if (this.index > 0) {
                Interceptor interceptor = HttpEngine.this.client.networkInterceptors().get(this.index - 1);
                Address address = connection().route().address();
                if (!request.url().host().equals(address.url().host()) || request.url().port() != address.url().port()) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must retain the same host and port");
                }
                if (this.calls > 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                }
            }
            if (this.index >= HttpEngine.this.client.networkInterceptors().size()) {
                HttpEngine.this.httpStream.writeRequestHeaders(request);
                HttpEngine.this.networkRequest = request;
                if (HttpEngine.this.permitsRequestBody(request) && request.body() != null) {
                    BufferedSink buffer = Okio.buffer(HttpEngine.this.httpStream.createRequestBody(request, request.body().contentLength()));
                    request.body().writeTo(buffer);
                    buffer.close();
                }
                Response readNetworkResponse = HttpEngine.this.readNetworkResponse();
                int code = readNetworkResponse.code();
                if ((code != 204 && code != 205) || readNetworkResponse.body().contentLength() <= 0) {
                    return readNetworkResponse;
                }
                throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + readNetworkResponse.body().contentLength());
            }
            NetworkInterceptorChain networkInterceptorChain = new NetworkInterceptorChain(this.index + 1, request, this.connection);
            Interceptor interceptor2 = HttpEngine.this.client.networkInterceptors().get(this.index);
            Response intercept = interceptor2.intercept(networkInterceptorChain);
            if (networkInterceptorChain.calls != 1) {
                throw new IllegalStateException("network interceptor " + interceptor2 + " must call proceed() exactly once");
            }
            if (intercept != null) {
                return intercept;
            }
            throw new NullPointerException("network interceptor " + interceptor2 + " returned null");
        }
    }

    public Response readNetworkResponse() throws IOException {
        this.httpStream.finishRequest();
        Response build = this.httpStream.readResponseHeaders().request(this.networkRequest).handshake(this.streamAllocation.connection().handshake()).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        if (!this.forWebSocket) {
            build = build.newBuilder().body(this.httpStream.openResponseBody(build)).build();
        }
        if ("close".equalsIgnoreCase(build.request().header(com.netease.nimlib.amazonaws.services.s3.Headers.CONNECTION)) || "close".equalsIgnoreCase(build.header(com.netease.nimlib.amazonaws.services.s3.Headers.CONNECTION))) {
            this.streamAllocation.noNewStreams();
        }
        return build;
    }

    private Response cacheWritingResponse(CacheRequest cacheRequest, Response response) throws IOException {
        Sink body;
        return (cacheRequest == null || (body = cacheRequest.body()) == null) ? response : response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer(new Source() { // from class: okhttp3.internal.http.HttpEngine.2
            boolean cacheRequestClosed;
            final /* synthetic */ BufferedSink val$cacheBody;
            final /* synthetic */ CacheRequest val$cacheRequest;
            final /* synthetic */ BufferedSource val$source;

            AnonymousClass2(BufferedSource bufferedSource, CacheRequest cacheRequest2, BufferedSink bufferedSink) {
                r2 = bufferedSource;
                r3 = cacheRequest2;
                r4 = bufferedSink;
            }

            @Override // okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                try {
                    long read = r2.read(buffer, j);
                    if (read == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            r4.close();
                        }
                        return -1L;
                    }
                    buffer.copyTo(r4.buffer(), buffer.size() - read, read);
                    r4.emitCompleteSegments();
                    return read;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        r3.abort();
                    }
                    throw e;
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return r2.timeout();
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    r3.abort();
                }
                r2.close();
            }
        }))).build();
    }

    /* renamed from: okhttp3.internal.http.HttpEngine$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Source {
        boolean cacheRequestClosed;
        final /* synthetic */ BufferedSink val$cacheBody;
        final /* synthetic */ CacheRequest val$cacheRequest;
        final /* synthetic */ BufferedSource val$source;

        AnonymousClass2(BufferedSource bufferedSource, CacheRequest cacheRequest2, BufferedSink bufferedSink) {
            r2 = bufferedSource;
            r3 = cacheRequest2;
            r4 = bufferedSink;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long read = r2.read(buffer, j);
                if (read == -1) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        r4.close();
                    }
                    return -1L;
                }
                buffer.copyTo(r4.buffer(), buffer.size() - read, read);
                r4.emitCompleteSegments();
                return read;
            } catch (IOException e) {
                if (!this.cacheRequestClosed) {
                    this.cacheRequestClosed = true;
                    r3.abort();
                }
                throw e;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return r2.timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.cacheRequestClosed = true;
                r3.abort();
            }
            r2.close();
        }
    }

    private static boolean validate(Response response, Response response2) {
        Date date;
        if (response2.code() == 304) {
            return true;
        }
        Date date2 = response.headers().getDate(com.netease.nimlib.amazonaws.services.s3.Headers.LAST_MODIFIED);
        return (date2 == null || (date = response2.headers().getDate(com.netease.nimlib.amazonaws.services.s3.Headers.LAST_MODIFIED)) == null || date.getTime() >= date2.getTime()) ? false : true;
    }

    private static Headers combine(Headers headers, Headers headers2) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if ((!"Warning".equalsIgnoreCase(name) || !value.startsWith("1")) && (!OkHeaders.isEndToEnd(name) || headers2.get(name) == null)) {
                builder.add(name, value);
            }
        }
        int size2 = headers2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String name2 = headers2.name(i2);
            if (!"Content-Length".equalsIgnoreCase(name2) && OkHeaders.isEndToEnd(name2)) {
                builder.add(name2, headers2.value(i2));
            }
        }
        return builder.build();
    }

    public void receiveHeaders(Headers headers) throws IOException {
        if (this.client.cookieJar() == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> parseAll = Cookie.parseAll(this.userRequest.url(), headers);
        if (parseAll.isEmpty()) {
            return;
        }
        this.client.cookieJar().saveFromResponse(this.userRequest.url(), parseAll);
    }

    public Request followUpRequest() throws IOException {
        String header;
        HttpUrl resolve;
        Proxy proxy;
        if (this.userResponse == null) {
            throw new IllegalStateException();
        }
        RealConnection connection = this.streamAllocation.connection();
        Route route = connection != null ? connection.route() : null;
        int code = this.userResponse.code();
        String method = this.userRequest.method();
        if (code == 307 || code == 308) {
            if (!method.equals("GET") && !method.equals("HEAD")) {
                return null;
            }
        } else {
            if (code == 401) {
                return this.client.authenticator().authenticate(route, this.userResponse);
            }
            if (code == 407) {
                if (route != null) {
                    proxy = route.proxy();
                } else {
                    proxy = this.client.proxy();
                }
                if (proxy.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route, this.userResponse);
            }
            if (code == 408) {
                Sink sink = this.requestBodyOut;
                boolean z = sink == null || (sink instanceof RetryableSink);
                if (!this.callerWritesRequestBody || z) {
                    return this.userRequest;
                }
                return null;
            }
            switch (code) {
                case KV.LOGIN_OK /* 300 */:
                case Constants.BUCKET_REDIRECT_STATUS_CODE /* 301 */:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.client.followRedirects() || (header = this.userResponse.header(HttpHeader.LOCATION)) == null || (resolve = this.userRequest.url().resolve(header)) == null) {
            return null;
        }
        if (!resolve.scheme().equals(this.userRequest.url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder newBuilder = this.userRequest.newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            if (HttpMethod.redirectsToGet(method)) {
                newBuilder.method("GET", null);
            } else {
                newBuilder.method(method, null);
            }
            newBuilder.removeHeader("Transfer-Encoding");
            newBuilder.removeHeader("Content-Length");
            newBuilder.removeHeader("Content-Type");
        }
        if (!sameConnection(resolve)) {
            newBuilder.removeHeader(HttpHeader.AUTHORIZATION);
        }
        return newBuilder.url(resolve).build();
    }

    public boolean sameConnection(HttpUrl httpUrl) {
        HttpUrl url = this.userRequest.url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }

    private static Address createAddress(OkHttpClient okHttpClient, Request request) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (request.isHttps()) {
            SSLSocketFactory sslSocketFactory = okHttpClient.sslSocketFactory();
            hostnameVerifier = okHttpClient.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = okHttpClient.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(request.url().host(), request.url().port(), okHttpClient.dns(), okHttpClient.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.proxyAuthenticator(), okHttpClient.proxy(), okHttpClient.protocols(), okHttpClient.connectionSpecs(), okHttpClient.proxySelector());
    }
}
