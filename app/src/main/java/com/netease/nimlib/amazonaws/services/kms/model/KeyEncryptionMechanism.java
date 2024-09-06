package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum KeyEncryptionMechanism {
    RSAES_OAEP_SHA_256("RSAES_OAEP_SHA_256");

    private static final Map<String, KeyEncryptionMechanism> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("RSAES_OAEP_SHA_256", RSAES_OAEP_SHA_256);
    }

    KeyEncryptionMechanism(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static KeyEncryptionMechanism fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
