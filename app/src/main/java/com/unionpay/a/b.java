package com.unionpay.a;

import android.content.Context;
import android.text.TextUtils;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public final class b implements X509TrustManager {
    private X509TrustManager a;
    private Context b;

    public b(Context context) {
        this.a = null;
        this.b = context;
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        this.a = (X509TrustManager) trustManagers[0];
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.a.checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        boolean z;
        this.a.checkServerTrusted(x509CertificateArr, str);
        boolean z2 = false;
        try {
            X500Principal issuerX500Principal = x509CertificateArr[0].getIssuerX500Principal();
            ArrayList arrayList = new ArrayList(0);
            arrayList.add(".*O=(GeoTrust Inc\\.|VeriSign\\\\, Inc\\.|Symantec Corporation).*");
            String a = com.unionpay.utils.b.a(this.b);
            if (!TextUtils.isEmpty(a)) {
                arrayList.add(a);
            }
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    z = false;
                    break;
                } else {
                    if (Pattern.compile((String) arrayList.get(i)).matcher(issuerX500Principal.getName()).matches()) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
            if (!z) {
                throw new CertificateException();
            }
            X500Principal subjectX500Principal = x509CertificateArr[0].getSubjectX500Principal();
            ArrayList arrayList2 = new ArrayList(0);
            arrayList2.add(".*CN=.*(\\.95516\\.com|\\.chinaunionpay\\.com|\\.unionpay\\.com|\\.unionpaysecure\\.com|\\.95516\\.net).*");
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList2.size()) {
                    break;
                }
                if (Pattern.compile((String) arrayList2.get(i2)).matcher(subjectX500Principal.getName()).matches()) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (!z2) {
                throw new CertificateException();
            }
        } catch (Exception unused) {
            throw new CertificateException();
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return this.a.getAcceptedIssuers();
    }
}
