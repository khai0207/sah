package com.netease.nimlib.amazonaws.auth;

import com.alipay.sdk.m.o.a;
import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.SDKGlobalConfiguration;
import com.netease.nimlib.amazonaws.internal.SdkDigestInputStream;
import com.netease.nimlib.amazonaws.util.Base64;
import com.netease.nimlib.amazonaws.util.BinaryUtils;
import com.netease.nimlib.amazonaws.util.HttpUtils;
import com.netease.nimlib.amazonaws.util.StringInputStream;
import com.netease.nimlib.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public abstract class AbstractAWSSigner implements Signer {
    private static final int BUFFER_SIZE_MULTIPLIER = 5;
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int TIME_MILLISEC = 1000;
    private static final ThreadLocal<MessageDigest> SHA256_MESSAGE_DIGEST = new ThreadLocal<MessageDigest>() { // from class: com.netease.nimlib.amazonaws.auth.AbstractAWSSigner.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new AmazonClientException("Unable to get SHA256 Function" + e.getMessage(), e);
            }
        }
    };
    public static final String EMPTY_STRING_SHA256_HEX = BinaryUtils.toHex(doHash(""));

    protected abstract void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials);

    protected String signAndBase64Encode(String str, String str2, SigningAlgorithm signingAlgorithm) {
        return signAndBase64Encode(str.getBytes(StringUtils.UTF8), str2, signingAlgorithm);
    }

    protected String signAndBase64Encode(byte[] bArr, String str, SigningAlgorithm signingAlgorithm) {
        try {
            return Base64.encodeAsString(sign(bArr, str.getBytes(StringUtils.UTF8), signingAlgorithm));
        } catch (Exception e) {
            throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    public byte[] sign(String str, byte[] bArr, SigningAlgorithm signingAlgorithm) {
        try {
            return sign(str.getBytes(StringUtils.UTF8), bArr, signingAlgorithm);
        } catch (Exception e) {
            throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    protected byte[] sign(byte[] bArr, byte[] bArr2, SigningAlgorithm signingAlgorithm) {
        try {
            Mac mac = Mac.getInstance(signingAlgorithm.toString());
            mac.init(new SecretKeySpec(bArr2, signingAlgorithm.toString()));
            return mac.doFinal(bArr);
        } catch (Exception e) {
            throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }

    public byte[] hash(String str) {
        return doHash(str);
    }

    private static byte[] doHash(String str) {
        try {
            MessageDigest messageDigestInstance = getMessageDigestInstance();
            messageDigestInstance.update(str.getBytes(StringUtils.UTF8));
            return messageDigestInstance.digest();
        } catch (Exception e) {
            throw new AmazonClientException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    protected byte[] hash(InputStream inputStream) {
        try {
            SdkDigestInputStream sdkDigestInputStream = new SdkDigestInputStream(inputStream, getMessageDigestInstance());
            do {
            } while (sdkDigestInputStream.read(new byte[1024]) > -1);
            return sdkDigestInputStream.getMessageDigest().digest();
        } catch (Exception e) {
            throw new AmazonClientException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    public byte[] hash(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new AmazonClientException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    protected String getCanonicalizedQueryString(Map<String, String> map) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            treeMap.put(HttpUtils.urlEncode(entry.getKey(), false), HttpUtils.urlEncode(entry.getValue(), false));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            sb.append((String) entry2.getKey());
            sb.append("=");
            sb.append((String) entry2.getValue());
            if (it.hasNext()) {
                sb.append(a.l);
            }
        }
        return sb.toString();
    }

    protected String getCanonicalizedQueryString(Request<?> request) {
        return HttpUtils.usePayloadForQueryParameters(request) ? "" : getCanonicalizedQueryString(request.getParameters());
    }

    protected byte[] getBinaryRequestPayload(Request<?> request) {
        if (HttpUtils.usePayloadForQueryParameters(request)) {
            String encodeParameters = HttpUtils.encodeParameters(request);
            return encodeParameters == null ? new byte[0] : encodeParameters.getBytes(StringUtils.UTF8);
        }
        return getBinaryRequestPayloadWithoutQueryParams(request);
    }

    protected String getRequestPayload(Request<?> request) {
        return newString(getBinaryRequestPayload(request));
    }

    protected String getRequestPayloadWithoutQueryParams(Request<?> request) {
        return newString(getBinaryRequestPayloadWithoutQueryParams(request));
    }

    protected byte[] getBinaryRequestPayloadWithoutQueryParams(Request<?> request) {
        InputStream binaryRequestPayloadStreamWithoutQueryParams = getBinaryRequestPayloadStreamWithoutQueryParams(request);
        try {
            binaryRequestPayloadStreamWithoutQueryParams.mark(-1);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[5120];
            while (true) {
                int read = binaryRequestPayloadStreamWithoutQueryParams.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    binaryRequestPayloadStreamWithoutQueryParams.reset();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            throw new AmazonClientException("Unable to read request payload to sign request: " + e.getMessage(), e);
        }
    }

    protected InputStream getBinaryRequestPayloadStream(Request<?> request) {
        if (HttpUtils.usePayloadForQueryParameters(request)) {
            String encodeParameters = HttpUtils.encodeParameters(request);
            if (encodeParameters == null) {
                return new ByteArrayInputStream(new byte[0]);
            }
            return new ByteArrayInputStream(encodeParameters.getBytes(StringUtils.UTF8));
        }
        return getBinaryRequestPayloadStreamWithoutQueryParams(request);
    }

    protected InputStream getBinaryRequestPayloadStreamWithoutQueryParams(Request<?> request) {
        try {
            InputStream content = request.getContent();
            if (content == null) {
                return new ByteArrayInputStream(new byte[0]);
            }
            if (content instanceof StringInputStream) {
                return content;
            }
            if (!content.markSupported()) {
                throw new AmazonClientException("Unable to read request payload to sign request.");
            }
            return request.getContent();
        } catch (Exception e) {
            throw new AmazonClientException("Unable to read request payload to sign request: " + e.getMessage(), e);
        }
    }

    protected String getCanonicalizedResourcePath(String str) {
        return getCanonicalizedResourcePath(str, true);
    }

    protected String getCanonicalizedResourcePath(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return "/";
        }
        if (z) {
            str = HttpUtils.urlEncode(str, true);
        }
        return str.startsWith("/") ? str : "/".concat(str);
    }

    protected String getCanonicalizedEndpoint(URI uri) {
        String lowerCase = StringUtils.lowerCase(uri.getHost());
        if (!HttpUtils.isUsingNonDefaultPort(uri)) {
            return lowerCase;
        }
        return lowerCase + ":" + uri.getPort();
    }

    protected AWSCredentials sanitizeCredentials(AWSCredentials aWSCredentials) {
        String aWSAccessKeyId;
        String aWSSecretKey;
        String sessionToken;
        synchronized (aWSCredentials) {
            aWSAccessKeyId = aWSCredentials.getAWSAccessKeyId();
            aWSSecretKey = aWSCredentials.getAWSSecretKey();
            sessionToken = aWSCredentials instanceof AWSSessionCredentials ? ((AWSSessionCredentials) aWSCredentials).getSessionToken() : null;
        }
        if (aWSSecretKey != null) {
            aWSSecretKey = aWSSecretKey.trim();
        }
        if (aWSAccessKeyId != null) {
            aWSAccessKeyId = aWSAccessKeyId.trim();
        }
        if (sessionToken != null) {
            sessionToken = sessionToken.trim();
        }
        if (aWSCredentials instanceof AWSSessionCredentials) {
            return new BasicSessionCredentials(aWSAccessKeyId, aWSSecretKey, sessionToken);
        }
        return new BasicAWSCredentials(aWSAccessKeyId, aWSSecretKey);
    }

    protected String newString(byte[] bArr) {
        return new String(bArr, StringUtils.UTF8);
    }

    protected Date getSignatureDate(long j) {
        Date date = new Date();
        return j != 0 ? new Date(date.getTime() - (j * 1000)) : date;
    }

    protected long getTimeOffset(Request<?> request) {
        return SDKGlobalConfiguration.getGlobalTimeOffset() != 0 ? SDKGlobalConfiguration.getGlobalTimeOffset() : request.getTimeOffset();
    }

    private static MessageDigest getMessageDigestInstance() {
        MessageDigest messageDigest = SHA256_MESSAGE_DIGEST.get();
        messageDigest.reset();
        return messageDigest;
    }
}
