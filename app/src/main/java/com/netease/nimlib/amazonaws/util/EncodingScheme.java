package com.netease.nimlib.amazonaws.util;

/* loaded from: classes.dex */
public interface EncodingScheme {
    byte[] decode(String str);

    String encodeAsString(byte[] bArr);
}
