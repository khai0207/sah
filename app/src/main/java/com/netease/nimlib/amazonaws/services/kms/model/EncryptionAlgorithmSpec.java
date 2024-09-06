package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum EncryptionAlgorithmSpec {
    SYMMETRIC_DEFAULT("SYMMETRIC_DEFAULT"),
    RSAES_OAEP_SHA_1("RSAES_OAEP_SHA_1"),
    RSAES_OAEP_SHA_256("RSAES_OAEP_SHA_256"),
    SM2PKE("SM2PKE");

    private static final Map<String, EncryptionAlgorithmSpec> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("SYMMETRIC_DEFAULT", SYMMETRIC_DEFAULT);
        enumMap.put("RSAES_OAEP_SHA_1", RSAES_OAEP_SHA_1);
        enumMap.put("RSAES_OAEP_SHA_256", RSAES_OAEP_SHA_256);
        enumMap.put("SM2PKE", SM2PKE);
    }

    EncryptionAlgorithmSpec(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static EncryptionAlgorithmSpec fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
