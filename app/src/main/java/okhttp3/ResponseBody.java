package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

/* loaded from: classes.dex */
public abstract class ResponseBody implements Closeable {
    private Reader reader;

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        BufferedSource source = source();
        try {
            byte[] readByteArray = source.readByteArray();
            Util.closeQuietly(source);
            if (contentLength == -1 || contentLength == readByteArray.length) {
                return readByteArray;
            }
            throw new IOException("Content-Length and stream length disagree");
        } catch (Throwable th) {
            Util.closeQuietly(source);
            throw th;
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(byteStream(), charset());
        this.reader = inputStreamReader;
        return inputStreamReader;
    }

    public final String string() throws IOException {
        return new String(bytes(), charset().name());
    }

    private Charset charset() {
        MediaType contentType = contentType();
        return contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.closeQuietly(source());
    }

    public static ResponseBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        Buffer writeString = new Buffer().writeString(str, charset);
        return create(mediaType, writeString.size(), writeString);
    }

    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr.length, new Buffer().write(bArr));
    }

    /* renamed from: okhttp3.ResponseBody$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends ResponseBody {
        final /* synthetic */ BufferedSource val$content;
        final /* synthetic */ long val$contentLength;

        AnonymousClass1(long j, BufferedSource bufferedSource) {
            r2 = j;
            r4 = bufferedSource;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return MediaType.this;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return r2;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return r4;
        }
    }

    public static ResponseBody create(MediaType mediaType, long j, BufferedSource bufferedSource) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        return new ResponseBody() { // from class: okhttp3.ResponseBody.1
            final /* synthetic */ BufferedSource val$content;
            final /* synthetic */ long val$contentLength;

            AnonymousClass1(long j2, BufferedSource bufferedSource2) {
                r2 = j2;
                r4 = bufferedSource2;
            }

            @Override // okhttp3.ResponseBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.ResponseBody
            public long contentLength() {
                return r2;
            }

            @Override // okhttp3.ResponseBody
            public BufferedSource source() {
                return r4;
            }
        };
    }
}
