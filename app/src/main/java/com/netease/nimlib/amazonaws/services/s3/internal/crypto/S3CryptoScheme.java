package com.netease.nimlib.amazonaws.services.s3.internal.crypto;

import com.netease.nimlib.amazonaws.services.s3.model.CryptoMode;
import java.security.SecureRandom;

@Deprecated
/* loaded from: classes.dex */
final class S3CryptoScheme {
    static final String AES = "AES";
    static final String RSA = "RSA";
    private static final SecureRandom SRAND = new SecureRandom();
    private final ContentCryptoScheme contentCryptoScheme;
    private final S3KeyWrapScheme kwScheme;

    S3CryptoScheme(ContentCryptoScheme contentCryptoScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = new S3KeyWrapScheme();
    }

    private S3CryptoScheme(ContentCryptoScheme contentCryptoScheme, S3KeyWrapScheme s3KeyWrapScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = s3KeyWrapScheme;
    }

    SecureRandom getSecureRandom() {
        return SRAND;
    }

    ContentCryptoScheme getContentCryptoScheme() {
        return this.contentCryptoScheme;
    }

    S3KeyWrapScheme getKeyWrapScheme() {
        return this.kwScheme;
    }

    static boolean isAesGcm(String str) {
        return ContentCryptoScheme.AES_GCM.getCipherAlgorithm().equals(str);
    }

    /* renamed from: com.netease.nimlib.amazonaws.services.s3.internal.crypto.S3CryptoScheme$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode;

        static {
            int[] iArr = new int[CryptoMode.values().length];
            $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode = iArr;
            try {
                iArr[CryptoMode.EncryptionOnly.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[CryptoMode.AuthenticatedEncryption.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[CryptoMode.StrictAuthenticatedEncryption.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static S3CryptoScheme from(CryptoMode cryptoMode) {
        int i = AnonymousClass1.$SwitchMap$com$netease$nimlib$amazonaws$services$s3$model$CryptoMode[cryptoMode.ordinal()];
        if (i == 1) {
            return new S3CryptoScheme(ContentCryptoScheme.AES_CBC, S3KeyWrapScheme.NONE);
        }
        if (i == 2 || i == 3) {
            return new S3CryptoScheme(ContentCryptoScheme.AES_GCM, new S3KeyWrapScheme());
        }
        throw new IllegalStateException();
    }
}
