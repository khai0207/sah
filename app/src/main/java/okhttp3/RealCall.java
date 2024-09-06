package okhttp3;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Platform;
import okhttp3.internal.http.HttpEngine;

/* loaded from: classes.dex */
final class RealCall implements Call {
    volatile boolean canceled;
    private final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;

    protected RealCall(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient;
        this.originalRequest = request;
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        try {
            this.client.dispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    Object tag() {
        return this.originalRequest.tag();
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        enqueue(callback, false);
    }

    void enqueue(Callback callback, boolean z) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.dispatcher().enqueue(new AsyncCall(callback, z));
    }

    @Override // okhttp3.Call
    public void cancel() {
        this.canceled = true;
        HttpEngine httpEngine = this.engine;
        if (httpEngine != null) {
            httpEngine.cancel();
        }
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.canceled;
    }

    /* loaded from: classes.dex */
    final class AsyncCall extends NamedRunnable {
        private final boolean forWebSocket;
        private final Callback responseCallback;

        private AsyncCall(Callback callback, boolean z) {
            super("OkHttp %s", RealCall.this.redactedUrl().toString());
            this.responseCallback = callback;
            this.forWebSocket = z;
        }

        String host() {
            return RealCall.this.originalRequest.url().host();
        }

        Request request() {
            return RealCall.this.originalRequest;
        }

        Object tag() {
            return RealCall.this.originalRequest.tag();
        }

        void cancel() {
            RealCall.this.cancel();
        }

        RealCall get() {
            return RealCall.this;
        }

        @Override // okhttp3.internal.NamedRunnable
        protected void execute() {
            IOException e;
            Response responseWithInterceptorChain;
            boolean z = true;
            try {
                try {
                    responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain(this.forWebSocket);
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (RealCall.this.canceled) {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } else {
                        this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                    }
                } catch (IOException e3) {
                    e = e3;
                    if (z) {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } else {
                        this.responseCallback.onFailure(RealCall.this, e);
                    }
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toLoggableString() {
        return (this.canceled ? "canceled call" : NotificationCompat.CATEGORY_CALL) + " to " + redactedUrl();
    }

    HttpUrl redactedUrl() {
        return this.originalRequest.url().resolve("/...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response getResponseWithInterceptorChain(boolean z) throws IOException {
        return new ApplicationInterceptorChain(0, this.originalRequest, z).proceed(this.originalRequest);
    }

    /* loaded from: classes.dex */
    class ApplicationInterceptorChain implements Interceptor.Chain {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;

        @Override // okhttp3.Interceptor.Chain
        public Connection connection() {
            return null;
        }

        ApplicationInterceptorChain(int i, Request request, boolean z) {
            this.index = i;
            this.request = request;
            this.forWebSocket = z;
        }

        @Override // okhttp3.Interceptor.Chain
        public Request request() {
            return this.request;
        }

        @Override // okhttp3.Interceptor.Chain
        public Response proceed(Request request) throws IOException {
            if (this.index < RealCall.this.client.interceptors().size()) {
                ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(this.index + 1, request, this.forWebSocket);
                Interceptor interceptor = RealCall.this.client.interceptors().get(this.index);
                Response intercept = interceptor.intercept(applicationInterceptorChain);
                if (intercept != null) {
                    return intercept;
                }
                throw new NullPointerException("application interceptor " + interceptor + " returned null");
            }
            return RealCall.this.getResponse(request, this.forWebSocket);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    okhttp3.Response getResponse(okhttp3.Request r13, boolean r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.RealCall.getResponse(okhttp3.Request, boolean):okhttp3.Response");
    }
}
