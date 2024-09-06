package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum KeyUsageType {
    SIGN_VERIFY("SIGN_VERIFY"),
    ENCRYPT_DECRYPT("ENCRYPT_DECRYPT"),
    GENERATE_VERIFY_MAC("GENERATE_VERIFY_MAC");

    private static final Map<String, KeyUsageType> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("SIGN_VERIFY", SIGN_VERIFY);
        enumMap.put("ENCRYPT_DECRYPT", ENCRYPT_DECRYPT);
        enumMap.put("GENERATE_VERIFY_MAC", GENERATE_VERIFY_MAC);
    }

    KeyUsageType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static KeyUsageType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
