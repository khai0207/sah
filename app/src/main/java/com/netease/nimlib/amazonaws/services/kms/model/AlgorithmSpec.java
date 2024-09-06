package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AlgorithmSpec {
    RSAES_PKCS1_V1_5("RSAES_PKCS1_V1_5"),
    RSAES_OAEP_SHA_1("RSAES_OAEP_SHA_1"),
    RSAES_OAEP_SHA_256("RSAES_OAEP_SHA_256"),
    RSA_AES_KEY_WRAP_SHA_1("RSA_AES_KEY_WRAP_SHA_1"),
    RSA_AES_KEY_WRAP_SHA_256("RSA_AES_KEY_WRAP_SHA_256");

    private static final Map<String, AlgorithmSpec> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("RSAES_PKCS1_V1_5", RSAES_PKCS1_V1_5);
        enumMap.put("RSAES_OAEP_SHA_1", RSAES_OAEP_SHA_1);
        enumMap.put("RSAES_OAEP_SHA_256", RSAES_OAEP_SHA_256);
        enumMap.put("RSA_AES_KEY_WRAP_SHA_1", RSA_AES_KEY_WRAP_SHA_1);
        enumMap.put("RSA_AES_KEY_WRAP_SHA_256", RSA_AES_KEY_WRAP_SHA_256);
    }

    AlgorithmSpec(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AlgorithmSpec fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
