package okhttp3.internal.http;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.RouteDatabase;
import okhttp3.internal.Util;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.StreamResetException;
import okhttp3.internal.io.RealConnection;

/* loaded from: classes.dex */
public final class StreamAllocation {
    public final Address address;
    private boolean canceled;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private int refusedStreamCount;
    private boolean released;
    private Route route;
    private final RouteSelector routeSelector;
    private HttpStream stream;

    public StreamAllocation(ConnectionPool connectionPool, Address address) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.routeSelector = new RouteSelector(address, routeDatabase());
    }

    public HttpStream newStream(int i, int i2, int i3, boolean z, boolean z2) throws RouteException, IOException {
        HttpStream http1xStream;
        try {
            RealConnection findHealthyConnection = findHealthyConnection(i, i2, i3, z, z2);
            if (findHealthyConnection.framedConnection != null) {
                http1xStream = new Http2xStream(this, findHealthyConnection.framedConnection);
            } else {
                findHealthyConnection.socket().setSoTimeout(i2);
                findHealthyConnection.source.timeout().timeout(i2, TimeUnit.MILLISECONDS);
                findHealthyConnection.sink.timeout().timeout(i3, TimeUnit.MILLISECONDS);
                http1xStream = new Http1xStream(this, findHealthyConnection.source, findHealthyConnection.sink);
            }
            synchronized (this.connectionPool) {
                this.stream = http1xStream;
            }
            return http1xStream;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws IOException, RouteException {
        while (true) {
            RealConnection findConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                if (findConnection.successCount == 0) {
                    return findConnection;
                }
                if (findConnection.isHealthy(z2)) {
                    return findConnection;
                }
                noNewStreams();
            }
        }
    }

    private RealConnection findConnection(int i, int i2, int i3, boolean z) throws IOException, RouteException {
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
            if (this.stream != null) {
                throw new IllegalStateException("stream != null");
            }
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            RealConnection realConnection = this.connection;
            if (realConnection != null && !realConnection.noNewStreams) {
                return realConnection;
            }
            RealConnection realConnection2 = Internal.instance.get(this.connectionPool, this.address, this);
            if (realConnection2 != null) {
                this.connection = realConnection2;
                return realConnection2;
            }
            Route route = this.route;
            if (route == null) {
                route = this.routeSelector.next();
                synchronized (this.connectionPool) {
                    this.route = route;
                    this.refusedStreamCount = 0;
                }
            }
            RealConnection realConnection3 = new RealConnection(route);
            acquire(realConnection3);
            synchronized (this.connectionPool) {
                Internal.instance.put(this.connectionPool, realConnection3);
                this.connection = realConnection3;
                if (this.canceled) {
                    throw new IOException("Canceled");
                }
            }
            realConnection3.connect(i, i2, i3, this.address.connectionSpecs(), z);
            routeDatabase().connected(realConnection3.route());
            return realConnection3;
        }
    }

    public void streamFinished(boolean z, HttpStream httpStream) {
        synchronized (this.connectionPool) {
            if (httpStream != null) {
                if (httpStream == this.stream) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                }
            }
            throw new IllegalStateException("expected " + this.stream + " but was " + httpStream);
        }
        deallocate(z, false, true);
    }

    public HttpStream stream() {
        HttpStream httpStream;
        synchronized (this.connectionPool) {
            httpStream = this.stream;
        }
        return httpStream;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        deallocate(false, true, false);
    }

    public void noNewStreams() {
        deallocate(true, false, false);
    }

    private void deallocate(boolean z, boolean z2, boolean z3) {
        RealConnection realConnection;
        RealConnection realConnection2;
        synchronized (this.connectionPool) {
            realConnection = null;
            if (z3) {
                try {
                    this.stream = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z2) {
                this.released = true;
            }
            if (this.connection != null) {
                if (z) {
                    this.connection.noNewStreams = true;
                }
                if (this.stream == null && (this.released || this.connection.noNewStreams)) {
                    release(this.connection);
                    if (this.connection.allocations.isEmpty()) {
                        this.connection.idleAtNanos = System.nanoTime();
                        if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                            realConnection2 = this.connection;
                            this.connection = null;
                            realConnection = realConnection2;
                        }
                    }
                    realConnection2 = null;
                    this.connection = null;
                    realConnection = realConnection2;
                }
            }
        }
        if (realConnection != null) {
            Util.closeQuietly(realConnection.socket());
        }
    }

    public void cancel() {
        HttpStream httpStream;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpStream = this.stream;
            realConnection = this.connection;
        }
        if (httpStream != null) {
            httpStream.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public void streamFailed(IOException iOException) {
        boolean z;
        synchronized (this.connectionPool) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.refusedStreamCount++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                    this.route = null;
                    z = true;
                }
                z = false;
            } else {
                if (this.connection != null && !this.connection.isMultiplexed()) {
                    if (this.connection.successCount == 0) {
                        if (this.route != null && iOException != null) {
                            this.routeSelector.connectFailed(this.route, iOException);
                        }
                        this.route = null;
                    }
                    z = true;
                }
                z = false;
            }
        }
        deallocate(z, false, true);
    }

    public void acquire(RealConnection realConnection) {
        realConnection.allocations.add(new WeakReference(this));
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public boolean hasMoreRoutes() {
        return this.route != null || this.routeSelector.hasNext();
    }

    public String toString() {
        return this.address.toString();
    }
}
