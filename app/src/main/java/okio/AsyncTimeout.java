package okio;

import com.netease.nimlib.search.model.NIMIndexRecord;
import java.io.IOException;
import java.io.InterruptedIOException;

/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static AsyncTimeout head;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;

    protected void timedOut() {
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    private static synchronized void scheduleTimeout(AsyncTimeout asyncTimeout, long j, boolean z) {
        synchronized (AsyncTimeout.class) {
            if (head == null) {
                head = new AsyncTimeout();
                new Watchdog().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                asyncTimeout.timeoutAt = Math.min(j, asyncTimeout.deadlineNanoTime() - nanoTime) + nanoTime;
            } else if (j != 0) {
                asyncTimeout.timeoutAt = j + nanoTime;
            } else if (z) {
                asyncTimeout.timeoutAt = asyncTimeout.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long remainingNanos = asyncTimeout.remainingNanos(nanoTime);
            AsyncTimeout asyncTimeout2 = head;
            while (asyncTimeout2.next != null && remainingNanos >= asyncTimeout2.next.remainingNanos(nanoTime)) {
                asyncTimeout2 = asyncTimeout2.next;
            }
            asyncTimeout.next = asyncTimeout2.next;
            asyncTimeout2.next = asyncTimeout;
            if (asyncTimeout2 == head) {
                AsyncTimeout.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    private static synchronized boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
        synchronized (AsyncTimeout.class) {
            for (AsyncTimeout asyncTimeout2 = head; asyncTimeout2 != null; asyncTimeout2 = asyncTimeout2.next) {
                if (asyncTimeout2.next == asyncTimeout) {
                    asyncTimeout2.next = asyncTimeout.next;
                    asyncTimeout.next = null;
                    return false;
                }
            }
            return true;
        }
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    /* renamed from: okio.AsyncTimeout$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Sink {
        final /* synthetic */ Sink val$sink;

        AnonymousClass1(Sink sink) {
            r2 = sink;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            Util.checkOffsetAndCount(buffer.size, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                Segment segment = buffer.head;
                while (true) {
                    if (j2 >= NIMIndexRecord.TYPE_MSG) {
                        break;
                    }
                    j2 += buffer.head.limit - buffer.head.pos;
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    segment = segment.next;
                }
                AsyncTimeout.this.enter();
                try {
                    try {
                        r2.write(buffer, j2);
                        j -= j2;
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            AsyncTimeout.this.enter();
            try {
                try {
                    r2.flush();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                }
            } catch (Throwable th) {
                AsyncTimeout.this.exit(false);
                throw th;
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            AsyncTimeout.this.enter();
            try {
                try {
                    r2.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                }
            } catch (Throwable th) {
                AsyncTimeout.this.exit(false);
                throw th;
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return AsyncTimeout.this;
        }

        public String toString() {
            return "AsyncTimeout.sink(" + r2 + ")";
        }
    }

    public final Sink sink(Sink sink) {
        return new Sink() { // from class: okio.AsyncTimeout.1
            final /* synthetic */ Sink val$sink;

            AnonymousClass1(Sink sink2) {
                r2 = sink2;
            }

            @Override // okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                Util.checkOffsetAndCount(buffer.size, 0L, j);
                while (true) {
                    long j2 = 0;
                    if (j <= 0) {
                        return;
                    }
                    Segment segment = buffer.head;
                    while (true) {
                        if (j2 >= NIMIndexRecord.TYPE_MSG) {
                            break;
                        }
                        j2 += buffer.head.limit - buffer.head.pos;
                        if (j2 >= j) {
                            j2 = j;
                            break;
                        }
                        segment = segment.next;
                    }
                    AsyncTimeout.this.enter();
                    try {
                        try {
                            r2.write(buffer, j2);
                            j -= j2;
                            AsyncTimeout.this.exit(true);
                        } catch (IOException e) {
                            throw AsyncTimeout.this.exit(e);
                        }
                    } catch (Throwable th) {
                        AsyncTimeout.this.exit(false);
                        throw th;
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        r2.flush();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        r2.close();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + r2 + ")";
            }
        };
    }

    /* renamed from: okio.AsyncTimeout$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Source {
        final /* synthetic */ Source val$source;

        AnonymousClass2(Source source) {
            r2 = source;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            AsyncTimeout.this.enter();
            try {
                try {
                    long read = r2.read(buffer, j);
                    AsyncTimeout.this.exit(true);
                    return read;
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                }
            } catch (Throwable th) {
                AsyncTimeout.this.exit(false);
                throw th;
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                try {
                    r2.close();
                    AsyncTimeout.this.exit(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.exit(e);
                }
            } catch (Throwable th) {
                AsyncTimeout.this.exit(false);
                throw th;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return AsyncTimeout.this;
        }

        public String toString() {
            return "AsyncTimeout.source(" + r2 + ")";
        }
    }

    public final Source source(Source source) {
        return new Source() { // from class: okio.AsyncTimeout.2
            final /* synthetic */ Source val$source;

            AnonymousClass2(Source source2) {
                r2 = source2;
            }

            @Override // okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    try {
                        long read = r2.read(buffer, j);
                        AsyncTimeout.this.exit(true);
                        return read;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    try {
                        r2.close();
                        AsyncTimeout.this.exit(true);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + r2 + ")";
            }
        };
    }

    final void exit(boolean z) throws IOException {
        if (exit() && z) {
            throw newTimeoutException(null);
        }
    }

    final IOException exit(IOException iOException) throws IOException {
        return !exit() ? iOException : newTimeoutException(iOException);
    }

    protected IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* loaded from: classes.dex */
    private static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    AsyncTimeout awaitTimeout = AsyncTimeout.awaitTimeout();
                    if (awaitTimeout != null) {
                        awaitTimeout.timedOut();
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    static synchronized AsyncTimeout awaitTimeout() throws InterruptedException {
        synchronized (AsyncTimeout.class) {
            AsyncTimeout asyncTimeout = head.next;
            if (asyncTimeout == null) {
                AsyncTimeout.class.wait();
                return null;
            }
            long remainingNanos = asyncTimeout.remainingNanos(System.nanoTime());
            if (remainingNanos > 0) {
                long j = remainingNanos / 1000000;
                Long.signum(j);
                AsyncTimeout.class.wait(j, (int) (remainingNanos - (1000000 * j)));
                return null;
            }
            head.next = asyncTimeout.next;
            asyncTimeout.next = null;
            return asyncTimeout;
        }
    }
}
