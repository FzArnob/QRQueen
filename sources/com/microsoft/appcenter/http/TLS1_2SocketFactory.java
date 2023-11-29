package com.microsoft.appcenter.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

class TLS1_2SocketFactory extends SSLSocketFactory {
    private static final String[] ENABLED_PROTOCOLS = {TLS1_2_PROTOCOL};
    private static final String TLS1_2_PROTOCOL = "TLSv1.2";
    private final SSLSocketFactory delegate;

    TLS1_2SocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext instance = SSLContext.getInstance(TLS1_2_PROTOCOL);
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            sSLSocketFactory = instance.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException unused) {
        }
        this.delegate = sSLSocketFactory == null ? HttpsURLConnection.getDefaultSSLSocketFactory() : sSLSocketFactory;
    }

    private SSLSocket forceTLS1_2(Socket socket) {
        SSLSocket sSLSocket = (SSLSocket) socket;
        sSLSocket.setEnabledProtocols(ENABLED_PROTOCOLS);
        return sSLSocket;
    }

    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public SSLSocket createSocket() throws IOException {
        return forceTLS1_2(this.delegate.createSocket());
    }

    public SSLSocket createSocket(String str, int i) throws IOException {
        return forceTLS1_2(this.delegate.createSocket(str, i));
    }

    public SSLSocket createSocket(InetAddress inetAddress, int i) throws IOException {
        return forceTLS1_2(this.delegate.createSocket(inetAddress, i));
    }

    public SSLSocket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return forceTLS1_2(this.delegate.createSocket(str, i, inetAddress, i2));
    }

    public SSLSocket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return forceTLS1_2(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public SSLSocket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return forceTLS1_2(this.delegate.createSocket(socket, str, i, z));
    }
}
