package com.netease.nimlib.amazonaws.http;

import android.os.Build;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class TLS12SocketFactory extends SSLSocketFactory {
    private final SSLSocketFactory delegate;
    private LoggingHandshakeCompletedListener handshakeCompletedListener;
    private static final Object contextLock = new Object();
    private static final String[] SUPPORTED_PROTOCOLS = {"TLSv1", "TLSv1.1", "TLSv1.2"};
    private static SSLContext sslContext = null;

    public static TLS12SocketFactory createTLS12SocketFactory() {
        return createTLS12SocketFactory(null);
    }

    public static TLS12SocketFactory createTLS12SocketFactory(SSLContext sSLContext) {
        if (Build.VERSION.SDK_INT >= 21) {
            return null;
        }
        try {
            return new TLS12SocketFactory(sSLContext);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void fixTLSPre21(HttpsURLConnection httpsURLConnection) {
        fixTLSPre21(httpsURLConnection, createTLS12SocketFactory());
    }

    public static void fixTLSPre21(HttpsURLConnection httpsURLConnection, TLS12SocketFactory tLS12SocketFactory) {
        if (Build.VERSION.SDK_INT >= 21 || tLS12SocketFactory == null) {
            return;
        }
        try {
            httpsURLConnection.setSSLSocketFactory(tLS12SocketFactory);
        } catch (Exception unused) {
        }
    }

    private TLS12SocketFactory(SSLContext sSLContext) throws KeyManagementException, NoSuchAlgorithmException {
        if (sSLContext != null) {
            this.delegate = sSLContext.getSocketFactory();
        } else {
            synchronized (contextLock) {
                if (sslContext == null) {
                    SSLContext sSLContext2 = SSLContext.getInstance("TLS");
                    sslContext = sSLContext2;
                    sSLContext2.init(null, null, null);
                }
            }
            this.delegate = sslContext.getSocketFactory();
        }
        this.handshakeCompletedListener = new LoggingHandshakeCompletedListener();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket();
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket(socket, str, i, z);
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket(str, i);
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket(str, i, inetAddress, i2);
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket(inetAddress, i);
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.delegate.createSocket(inetAddress, i, inetAddress2, i2);
        sSLSocket.addHandshakeCompletedListener(this.handshakeCompletedListener);
        return updateTLSProtocols(sSLSocket);
    }

    private Socket updateTLSProtocols(Socket socket) {
        if (socket instanceof SSLSocket) {
            try {
                ((SSLSocket) socket).setEnabledProtocols(SUPPORTED_PROTOCOLS);
            } catch (Exception unused) {
            }
        }
        return socket;
    }
}
