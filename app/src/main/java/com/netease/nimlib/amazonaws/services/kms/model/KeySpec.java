package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum KeySpec {
    RSA_2048("RSA_2048"),
    RSA_3072("RSA_3072"),
    RSA_4096("RSA_4096"),
    ECC_NIST_P256("ECC_NIST_P256"),
    ECC_NIST_P384("ECC_NIST_P384"),
    ECC_NIST_P521("ECC_NIST_P521"),
    ECC_SECG_P256K1("ECC_SECG_P256K1"),
    SYMMETRIC_DEFAULT("SYMMETRIC_DEFAULT"),
    HMAC_224("HMAC_224"),
    HMAC_256("HMAC_256"),
    HMAC_384("HMAC_384"),
    HMAC_512("HMAC_512"),
    SM2("SM2");

    private static final Map<String, KeySpec> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("RSA_2048", RSA_2048);
        enumMap.put("RSA_3072", RSA_3072);
        enumMap.put("RSA_4096", RSA_4096);
        enumMap.put("ECC_NIST_P256", ECC_NIST_P256);
        enumMap.put("ECC_NIST_P384", ECC_NIST_P384);
        enumMap.put("ECC_NIST_P521", ECC_NIST_P521);
        enumMap.put("ECC_SECG_P256K1", ECC_SECG_P256K1);
        enumMap.put("SYMMETRIC_DEFAULT", SYMMETRIC_DEFAULT);
        enumMap.put("HMAC_224", HMAC_224);
        enumMap.put("HMAC_256", HMAC_256);
        enumMap.put("HMAC_384", HMAC_384);
        enumMap.put("HMAC_512", HMAC_512);
        enumMap.put("SM2", SM2);
    }

    KeySpec(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static KeySpec fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
