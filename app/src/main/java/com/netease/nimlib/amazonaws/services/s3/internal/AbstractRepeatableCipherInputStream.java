package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.internal.SdkFilterInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public abstract class AbstractRepeatableCipherInputStream<T> extends SdkFilterInputStream {
    private final T cipherFactory;
    private boolean hasBeenAccessed;
    private final InputStream unencryptedDataStream;

    protected abstract FilterInputStream createCipherInputStream(InputStream inputStream, T t);

    protected AbstractRepeatableCipherInputStream(InputStream inputStream, FilterInputStream filterInputStream, T t) {
        super(filterInputStream);
        this.unencryptedDataStream = inputStream;
        this.cipherFactory = t;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        abortIfNeeded();
        return this.unencryptedDataStream.markSupported();
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        abortIfNeeded();
        if (this.hasBeenAccessed) {
            throw new UnsupportedOperationException("Marking is only supported before your first call to read or skip.");
        }
        this.unencryptedDataStream.mark(i);
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        abortIfNeeded();
        this.unencryptedDataStream.reset();
        this.in = createCipherInputStream(this.unencryptedDataStream, this.cipherFactory);
        this.hasBeenAccessed = false;
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        this.hasBeenAccessed = true;
        return super.read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        this.hasBeenAccessed = true;
        return super.read(bArr);
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.hasBeenAccessed = true;
        return super.read(bArr, i, i2);
    }

    @Override // com.netease.nimlib.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        this.hasBeenAccessed = true;
        return super.skip(j);
    }
}
