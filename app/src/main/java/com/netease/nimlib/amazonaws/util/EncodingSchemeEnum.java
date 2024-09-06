package com.netease.nimlib.amazonaws.util;

/* loaded from: classes.dex */
public enum EncodingSchemeEnum implements EncodingScheme {
    BASE16 { // from class: com.netease.nimlib.amazonaws.util.EncodingSchemeEnum.1
        @Override // com.netease.nimlib.amazonaws.util.EncodingSchemeEnum, com.netease.nimlib.amazonaws.util.EncodingScheme
        public String encodeAsString(byte[] bArr) {
            return Base16.encodeAsString(bArr);
        }

        @Override // com.netease.nimlib.amazonaws.util.EncodingScheme
        public byte[] decode(String str) {
            return Base16.decode(str);
        }
    },
    BASE32 { // from class: com.netease.nimlib.amazonaws.util.EncodingSchemeEnum.2
        @Override // com.netease.nimlib.amazonaws.util.EncodingSchemeEnum, com.netease.nimlib.amazonaws.util.EncodingScheme
        public String encodeAsString(byte[] bArr) {
            return Base32.encodeAsString(bArr);
        }

        @Override // com.netease.nimlib.amazonaws.util.EncodingScheme
        public byte[] decode(String str) {
            return Base32.decode(str);
        }
    },
    BASE64 { // from class: com.netease.nimlib.amazonaws.util.EncodingSchemeEnum.3
        @Override // com.netease.nimlib.amazonaws.util.EncodingSchemeEnum, com.netease.nimlib.amazonaws.util.EncodingScheme
        public String encodeAsString(byte[] bArr) {
            return Base64.encodeAsString(bArr);
        }

        @Override // com.netease.nimlib.amazonaws.util.EncodingScheme
        public byte[] decode(String str) {
            return Base64.decode(str);
        }
    };

    @Override // com.netease.nimlib.amazonaws.util.EncodingScheme
    public abstract String encodeAsString(byte[] bArr);
}
