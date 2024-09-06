package okhttp3.internal.framed;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
public final class FramedStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    private final FramedConnection connection;
    private final int id;
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final FramedDataSink sink;
    private final FramedDataSource source;
    long unacknowledgedBytesRead = 0;
    private final StreamTimeout readTimeout = new StreamTimeout();
    private final StreamTimeout writeTimeout = new StreamTimeout();
    private ErrorCode errorCode = null;

    FramedStream(int i, FramedConnection framedConnection, boolean z, boolean z2, List<Header> list) {
        if (framedConnection == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.id = i;
        this.connection = framedConnection;
        this.bytesLeftInWriteWindow = framedConnection.peerSettings.getInitialWindowSize(65536);
        this.source = new FramedDataSource(framedConnection.okHttpSettings.getInitialWindowSize(65536));
        this.sink = new FramedDataSink();
        this.source.finished = z2;
        this.sink.finished = z;
        this.requestHeaders = list;
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed)) {
            if (this.responseHeaders != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public FramedConnection getConnection() {
        return this.connection;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public synchronized List<Header> getResponseHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.responseHeaders == null && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (this.responseHeaders == null) {
            throw new StreamResetException(this.errorCode);
        }
        return this.responseHeaders;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void reply(List<Header> list, boolean z) throws IOException {
        boolean z2 = false;
        synchronized (this) {
            try {
                if (list == null) {
                    throw new NullPointerException("responseHeaders == null");
                }
                if (this.responseHeaders != null) {
                    throw new IllegalStateException("reply already sent");
                }
                this.responseHeaders = list;
                if (!z) {
                    this.sink.finished = true;
                    z2 = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.connection.writeSynReply(this.id, z2, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (this.responseHeaders == null && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    void receiveHeaders(List<Header> list, HeadersMode headersMode) {
        ErrorCode errorCode = null;
        boolean z = true;
        synchronized (this) {
            if (this.responseHeaders == null) {
                if (headersMode.failIfHeadersAbsent()) {
                    errorCode = ErrorCode.PROTOCOL_ERROR;
                } else {
                    this.responseHeaders = list;
                    z = isOpen();
                    notifyAll();
                }
            } else if (headersMode.failIfHeadersPresent()) {
                errorCode = ErrorCode.STREAM_IN_USE;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (errorCode != null) {
            closeLater(errorCode);
        } else {
            if (z) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, i);
    }

    void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (isOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    /* loaded from: classes.dex */
    private final class FramedDataSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;

        /* synthetic */ FramedDataSource(FramedStream framedStream, long j, AnonymousClass1 anonymousClass1) {
            this(j);
        }

        private FramedDataSource(long j) {
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
            this.maxByteCount = j;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (FramedStream.this) {
                waitUntilReadable();
                checkNotClosed();
                if (this.readBuffer.size() == 0) {
                    return -1L;
                }
                long read = this.readBuffer.read(buffer, Math.min(j, this.readBuffer.size()));
                FramedStream.this.unacknowledgedBytesRead += read;
                if (FramedStream.this.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                    FramedStream.this.connection.writeWindowUpdateLater(FramedStream.this.id, FramedStream.this.unacknowledgedBytesRead);
                    FramedStream.this.unacknowledgedBytesRead = 0L;
                }
                synchronized (FramedStream.this.connection) {
                    FramedStream.this.connection.unacknowledgedBytesRead += read;
                    if (FramedStream.this.connection.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                        FramedStream.this.connection.writeWindowUpdateLater(0, FramedStream.this.connection.unacknowledgedBytesRead);
                        FramedStream.this.connection.unacknowledgedBytesRead = 0L;
                    }
                }
                return read;
            }
        }

        private void waitUntilReadable() throws IOException {
            FramedStream.this.readTimeout.enter();
            while (this.readBuffer.size() == 0 && !this.finished && !this.closed && FramedStream.this.errorCode == null) {
                try {
                    FramedStream.this.waitForIo();
                } finally {
                    FramedStream.this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
        }

        void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (FramedStream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size() + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    FramedStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.skip(j);
                    return;
                }
                long read = bufferedSource.read(this.receiveBuffer, j);
                if (read == -1) {
                    throw new EOFException();
                }
                j -= read;
                synchronized (FramedStream.this) {
                    if (this.readBuffer.size() != 0) {
                        z2 = false;
                    }
                    this.readBuffer.writeAll(this.receiveBuffer);
                    if (z2) {
                        FramedStream.this.notifyAll();
                    }
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return FramedStream.this.readTimeout;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                this.closed = true;
                this.readBuffer.clear();
                FramedStream.this.notifyAll();
            }
            FramedStream.this.cancelStreamIfNecessary();
        }

        private void checkNotClosed() throws IOException {
            if (!this.closed) {
                if (FramedStream.this.errorCode != null) {
                    throw new StreamResetException(FramedStream.this.errorCode);
                }
                return;
            }
            throw new IOException("stream closed");
        }
    }

    public void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean isOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            isOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else {
            if (isOpen) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    /* loaded from: classes.dex */
    final class FramedDataSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer = new Buffer();

        FramedDataSink() {
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size() >= EMIT_BUFFER_SIZE) {
                emitDataFrame(false);
            }
        }

        private void emitDataFrame(boolean z) throws IOException {
            long min;
            synchronized (FramedStream.this) {
                FramedStream.this.writeTimeout.enter();
                while (FramedStream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && FramedStream.this.errorCode == null) {
                    try {
                        FramedStream.this.waitForIo();
                    } finally {
                    }
                }
                FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
                FramedStream.this.checkOutNotClosed();
                min = Math.min(FramedStream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                FramedStream.this.bytesLeftInWriteWindow -= min;
            }
            FramedStream.this.writeTimeout.enter();
            try {
                FramedStream.this.connection.writeData(FramedStream.this.id, z && min == this.sendBuffer.size(), this.sendBuffer, min);
            } finally {
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (FramedStream.this) {
                FramedStream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitDataFrame(false);
                FramedStream.this.connection.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return FramedStream.this.writeTimeout;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                if (this.closed) {
                    return;
                }
                if (!FramedStream.this.sink.finished) {
                    if (this.sendBuffer.size() > 0) {
                        while (this.sendBuffer.size() > 0) {
                            emitDataFrame(true);
                        }
                    } else {
                        FramedStream.this.connection.writeData(FramedStream.this.id, true, null, 0L);
                    }
                }
                synchronized (FramedStream.this) {
                    this.closed = true;
                }
                FramedStream.this.connection.flush();
                FramedStream.this.cancelStreamIfNecessary();
            }
        }
    }

    void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void checkOutNotClosed() throws IOException {
        if (!this.sink.closed) {
            if (this.sink.finished) {
                throw new IOException("stream finished");
            }
            if (this.errorCode != null) {
                throw new StreamResetException(this.errorCode);
            }
            return;
        }
        throw new IOException("stream closed");
    }

    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    /* loaded from: classes.dex */
    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override // okio.AsyncTimeout
        protected void timedOut() {
            FramedStream.this.closeLater(ErrorCode.CANCEL);
        }

        @Override // okio.AsyncTimeout
        protected IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }
}
