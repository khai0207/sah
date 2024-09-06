package com.netease.nimlib.amazonaws.auth;

import androidx.appcompat.widget.ActivityChooserView;
import com.netease.nimlib.amazonaws.AmazonClientException;
import com.netease.nimlib.amazonaws.internal.SdkInputStream;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.util.BinaryUtils;
import com.netease.nimlib.amazonaws.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class AwsChunkedEncodingInputStream extends SdkInputStream {
    private static final int BIT_MASK = 255;
    private static final String CHUNK_SIGNATURE_HEADER = ";chunk-signature=";
    private static final String CHUNK_STRING_TO_SIGN_PREFIX = "AWS4-HMAC-SHA256-PAYLOAD";
    private static final String CLRF = "\r\n";
    private static final int DEFAULT_BUFFER_SIZE = 262144;
    private static final int DEFAULT_CHUNK_SIZE = 131072;
    protected static final String DEFAULT_ENCODING = "UTF-8";
    private static final int SIGNATURE_LENGTH = 64;
    private final AWS4Signer aws4Signer;
    private ChunkContentIterator currentChunkIterator;
    private final String dateTime;
    private DecodedStreamBuffer decodedStreamBuffer;
    private final String headerSignature;
    private InputStream is;
    private boolean isAtStart;
    private boolean isTerminating;
    private final byte[] kSigning;
    private final String keyPath;
    private final int maxBufferSize;
    private String priorChunkSignature;
    private static final byte[] FINAL_CHUNK = new byte[0];
    private static final Log log = LogFactory.getLog((Class<?>) AwsChunkedEncodingInputStream.class);

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public AwsChunkedEncodingInputStream(InputStream inputStream, byte[] bArr, String str, String str2, String str3, AWS4Signer aWS4Signer) {
        this(inputStream, 262144, bArr, str, str2, str3, aWS4Signer);
    }

    public AwsChunkedEncodingInputStream(InputStream inputStream, int i, byte[] bArr, String str, String str2, String str3, AWS4Signer aWS4Signer) {
        this.is = null;
        this.isAtStart = true;
        this.isTerminating = false;
        if (inputStream instanceof AwsChunkedEncodingInputStream) {
            AwsChunkedEncodingInputStream awsChunkedEncodingInputStream = (AwsChunkedEncodingInputStream) inputStream;
            i = Math.max(awsChunkedEncodingInputStream.maxBufferSize, i);
            this.is = awsChunkedEncodingInputStream.is;
            this.decodedStreamBuffer = awsChunkedEncodingInputStream.decodedStreamBuffer;
        } else {
            this.is = inputStream;
            this.decodedStreamBuffer = null;
        }
        if (i < 131072) {
            throw new IllegalArgumentException("Max buffer size should not be less than chunk size");
        }
        this.maxBufferSize = i;
        this.kSigning = bArr;
        this.dateTime = str;
        this.keyPath = str2;
        this.headerSignature = str3;
        this.priorChunkSignature = str3;
        this.aws4Signer = aWS4Signer;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        if (read == -1) {
            return read;
        }
        if (log.isDebugEnabled()) {
            log.debug("One byte read from the stream.");
        }
        return bArr[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        abortIfNeeded();
        if (bArr == null) {
            throw null;
        }
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        ChunkContentIterator chunkContentIterator = this.currentChunkIterator;
        if (chunkContentIterator == null || !chunkContentIterator.hasNext()) {
            if (this.isTerminating) {
                return -1;
            }
            this.isTerminating = setUpNextChunk();
        }
        int read = this.currentChunkIterator.read(bArr, i, i2);
        if (read > 0) {
            this.isAtStart = false;
            if (log.isDebugEnabled()) {
                log.debug(read + " byte read from the stream.");
            }
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        int read;
        if (j <= 0) {
            return 0L;
        }
        int min = (int) Math.min(262144L, j);
        byte[] bArr = new byte[min];
        long j2 = j;
        while (j2 > 0 && (read = read(bArr, 0, min)) >= 0) {
            j2 -= read;
        }
        return j - j2;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        abortIfNeeded();
        if (!this.isAtStart) {
            throw new UnsupportedOperationException("Chunk-encoded stream only supports mark() at the start of the stream.");
        }
        if (this.is.markSupported()) {
            if (log.isDebugEnabled()) {
                log.debug("AwsChunkedEncodingInputStream marked at the start of the stream (will directly mark the wrapped stream since it's mark-supported).");
            }
            this.is.mark(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("AwsChunkedEncodingInputStream marked at the start of the stream (initializing the buffer since the wrapped stream is not mark-supported).");
            }
            this.decodedStreamBuffer = new DecodedStreamBuffer(this.maxBufferSize);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        abortIfNeeded();
        this.currentChunkIterator = null;
        this.priorChunkSignature = this.headerSignature;
        if (this.is.markSupported()) {
            if (log.isDebugEnabled()) {
                log.debug("AwsChunkedEncodingInputStream reset (will reset the wrapped stream because it is mark-supported).");
            }
            this.is.reset();
        } else {
            if (log.isDebugEnabled()) {
                log.debug("AwsChunkedEncodingInputStream reset (will use the buffer of the decoded stream).");
            }
            if (this.decodedStreamBuffer == null) {
                throw new IOException("Cannot reset the stream because the mark is not set.");
            }
            this.decodedStreamBuffer.startReadBuffer();
        }
        this.currentChunkIterator = null;
        this.isAtStart = true;
        this.isTerminating = false;
    }

    public static long calculateStreamContentLength(long j, boolean z) {
        if (j < 0) {
            throw new IllegalArgumentException("Nonnegative content length expected.");
        }
        long j2 = j / 131072;
        long j3 = j % 131072;
        return (j2 * calculateSignedChunkLength(131072L, z)) + (j3 > 0 ? calculateSignedChunkLength(j3, z) : 0L) + calculateSignedChunkLength(0L, z);
    }

    private static long calculateSignedChunkLength(long j, boolean z) {
        return z ? Long.toHexString(j).length() + 17 + 64 + 2 + j + 2 : j;
    }

    private boolean setUpNextChunk() throws IOException {
        byte[] bArr = new byte[131072];
        int i = 0;
        while (i < 131072) {
            DecodedStreamBuffer decodedStreamBuffer = this.decodedStreamBuffer;
            if (decodedStreamBuffer != null && decodedStreamBuffer.hasNext()) {
                bArr[i] = this.decodedStreamBuffer.next();
                i++;
            } else {
                int read = this.is.read(bArr, i, 131072 - i);
                if (read == -1) {
                    break;
                }
                DecodedStreamBuffer decodedStreamBuffer2 = this.decodedStreamBuffer;
                if (decodedStreamBuffer2 != null) {
                    decodedStreamBuffer2.buffer(bArr, i, read);
                }
                i += read;
            }
        }
        if (i == 0) {
            this.currentChunkIterator = new ChunkContentIterator(createSignedChunk(FINAL_CHUNK));
            return true;
        }
        if (i < 131072) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bArr = bArr2;
        }
        this.currentChunkIterator = new ChunkContentIterator(createSignedChunk(bArr));
        return false;
    }

    private byte[] createSignedChunk(byte[] bArr) {
        if (this.aws4Signer.isPayloadSigningEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(bArr.length));
            String hex = BinaryUtils.toHex(this.aws4Signer.sign("AWS4-HMAC-SHA256-PAYLOAD\n" + this.dateTime + "\n" + this.keyPath + "\n" + this.priorChunkSignature + "\n" + BinaryUtils.toHex(this.aws4Signer.hash("")) + "\n" + BinaryUtils.toHex(this.aws4Signer.hash(bArr)), this.kSigning, SigningAlgorithm.HmacSHA256));
            this.priorChunkSignature = hex;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(CHUNK_SIGNATURE_HEADER);
            sb2.append(hex);
            sb.append(sb2.toString());
            sb.append("\r\n");
            try {
                byte[] bytes = sb.toString().getBytes(StringUtils.UTF8);
                byte[] bytes2 = "\r\n".getBytes(StringUtils.UTF8);
                byte[] bArr2 = new byte[bytes.length + bArr.length + bytes2.length];
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
                System.arraycopy(bytes2, 0, bArr2, bytes.length + bArr.length, bytes2.length);
                return bArr2;
            } catch (Exception e) {
                throw new AmazonClientException("Unable to sign the chunked data. " + e.getMessage(), e);
            }
        }
        byte[] bArr3 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        return bArr3;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkInputStream
    protected InputStream getWrappedInputStream() {
        return this.is;
    }
}
