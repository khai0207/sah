package okhttp3.internal.tls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public abstract class TrustRootIndex {
    abstract X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);

    public static TrustRootIndex get(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new AndroidTrustRootIndex(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return get(x509TrustManager.getAcceptedIssuers());
        }
    }

    public static TrustRootIndex get(X509Certificate... x509CertificateArr) {
        return new BasicTrustRootIndex(x509CertificateArr);
    }

    /* loaded from: classes.dex */
    static final class AndroidTrustRootIndex extends TrustRootIndex {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;

        AndroidTrustRootIndex(X509TrustManager x509TrustManager, Method method) {
            this.findByIssuerAndSignatureMethod = method;
            this.trustManager = x509TrustManager;
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.findByIssuerAndSignatureMethod.invoke(this.trustManager, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException unused2) {
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    static final class BasicTrustRootIndex extends TrustRootIndex {
        private final Map<X500Principal, List<X509Certificate>> subjectToCaCerts = new LinkedHashMap();

        public BasicTrustRootIndex(X509Certificate... x509CertificateArr) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                List<X509Certificate> list = this.subjectToCaCerts.get(subjectX500Principal);
                if (list == null) {
                    list = new ArrayList<>(1);
                    this.subjectToCaCerts.put(subjectX500Principal, list);
                }
                list.add(x509Certificate);
            }
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate) {
            List<X509Certificate> list = this.subjectToCaCerts.get(x509Certificate.getIssuerX500Principal());
            if (list == null) {
                return null;
            }
            for (X509Certificate x509Certificate2 : list) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return x509Certificate2;
                } catch (Exception unused) {
                }
            }
            return null;
        }
    }
}
