package com.netease.nimlib.amazonaws.util;

import com.netease.nimlib.amazonaws.logging.LogFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class Md5Utils {
    private static final int FOURTEEN = 14;
    private static final int SIXTEEN_K = 16384;

    public static byte[] computeMD5Hash(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                return messageDigest.digest();
            } finally {
                try {
                    bufferedInputStream.close();
                } catch (Exception e) {
                    LogFactory.getLog((Class<?>) Md5Utils.class).debug("Unable to close input stream of hash candidate: " + e);
                }
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static String md5AsBase64(InputStream inputStream) throws IOException {
        return Base64.encodeAsString(computeMD5Hash(inputStream));
    }

    public static byte[] computeMD5Hash(byte[] bArr) {
        try {
            return MessageDigest.getInstance("MD5").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String md5AsBase64(byte[] bArr) {
        return Base64.encodeAsString(computeMD5Hash(bArr));
    }

    public static byte[] computeMD5Hash(File file) throws IOException {
        return computeMD5Hash(new FileInputStream(file));
    }

    public static String md5AsBase64(File file) throws IOException {
        return Base64.encodeAsString(computeMD5Hash(file));
    }
}
