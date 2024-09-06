package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum XksProxyConnectivityType {
    PUBLIC_ENDPOINT("PUBLIC_ENDPOINT"),
    VPC_ENDPOINT_SERVICE("VPC_ENDPOINT_SERVICE");

    private static final Map<String, XksProxyConnectivityType> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("PUBLIC_ENDPOINT", PUBLIC_ENDPOINT);
        enumMap.put("VPC_ENDPOINT_SERVICE", VPC_ENDPOINT_SERVICE);
    }

    XksProxyConnectivityType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static XksProxyConnectivityType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
