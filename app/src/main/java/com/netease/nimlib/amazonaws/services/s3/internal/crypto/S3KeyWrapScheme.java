package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.alipay.sdk.m.j.d;
import java.security.Key;
import java.security.Provider;

@Deprecated
/* loaded from: classes.dex */
class S3KeyWrapScheme {
    public static final String AES_WRAP = "AESWrap";
    static final S3KeyWrapScheme NONE = new S3KeyWrapScheme() { // from class: com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme.1
        @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme
        String getKeyWrapAlgorithm(Key key, Provider provider) {
            return null;
        }

        @Override // com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme
        public String toString() {
            return "NONE";
        }
    };
    public static final String RSA_ECB_OAEP_WITH_SHA256_AND_MGF1_PADDING = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    public String toString() {
        return "S3KeyWrapScheme";
    }

    S3KeyWrapScheme() {
    }

    String getKeyWrapAlgorithm(Key key, Provider provider) {
        String algorithm = key.getAlgorithm();
        if (JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM.equals(algorithm)) {
            return AES_WRAP;
        }
        if (d.a.equals(algorithm) && CryptoRuntime.isRsaKeyWrapAvailable(provider)) {
            return RSA_ECB_OAEP_WITH_SHA256_AND_MGF1_PADDING;
        }
        return null;
    }
}
