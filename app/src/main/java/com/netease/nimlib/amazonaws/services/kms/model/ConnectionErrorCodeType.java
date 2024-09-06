package com.netease.nimlib.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ConnectionErrorCodeType {
    INVALID_CREDENTIALS("INVALID_CREDENTIALS"),
    CLUSTER_NOT_FOUND("CLUSTER_NOT_FOUND"),
    NETWORK_ERRORS("NETWORK_ERRORS"),
    INTERNAL_ERROR("INTERNAL_ERROR"),
    INSUFFICIENT_CLOUDHSM_HSMS("INSUFFICIENT_CLOUDHSM_HSMS"),
    USER_LOCKED_OUT("USER_LOCKED_OUT"),
    USER_NOT_FOUND("USER_NOT_FOUND"),
    USER_LOGGED_IN("USER_LOGGED_IN"),
    SUBNET_NOT_FOUND("SUBNET_NOT_FOUND"),
    INSUFFICIENT_FREE_ADDRESSES_IN_SUBNET("INSUFFICIENT_FREE_ADDRESSES_IN_SUBNET"),
    XKS_PROXY_ACCESS_DENIED("XKS_PROXY_ACCESS_DENIED"),
    XKS_PROXY_NOT_REACHABLE("XKS_PROXY_NOT_REACHABLE"),
    XKS_VPC_ENDPOINT_SERVICE_NOT_FOUND("XKS_VPC_ENDPOINT_SERVICE_NOT_FOUND"),
    XKS_PROXY_INVALID_RESPONSE("XKS_PROXY_INVALID_RESPONSE"),
    XKS_PROXY_INVALID_CONFIGURATION("XKS_PROXY_INVALID_CONFIGURATION"),
    XKS_VPC_ENDPOINT_SERVICE_INVALID_CONFIGURATION("XKS_VPC_ENDPOINT_SERVICE_INVALID_CONFIGURATION"),
    XKS_PROXY_TIMED_OUT("XKS_PROXY_TIMED_OUT"),
    XKS_PROXY_INVALID_TLS_CONFIGURATION("XKS_PROXY_INVALID_TLS_CONFIGURATION");

    private static final Map<String, ConnectionErrorCodeType> enumMap;
    private String value;

    static {
        HashMap hashMap = new HashMap();
        enumMap = hashMap;
        hashMap.put("INVALID_CREDENTIALS", INVALID_CREDENTIALS);
        enumMap.put("CLUSTER_NOT_FOUND", CLUSTER_NOT_FOUND);
        enumMap.put("NETWORK_ERRORS", NETWORK_ERRORS);
        enumMap.put("INTERNAL_ERROR", INTERNAL_ERROR);
        enumMap.put("INSUFFICIENT_CLOUDHSM_HSMS", INSUFFICIENT_CLOUDHSM_HSMS);
        enumMap.put("USER_LOCKED_OUT", USER_LOCKED_OUT);
        enumMap.put("USER_NOT_FOUND", USER_NOT_FOUND);
        enumMap.put("USER_LOGGED_IN", USER_LOGGED_IN);
        enumMap.put("SUBNET_NOT_FOUND", SUBNET_NOT_FOUND);
        enumMap.put("INSUFFICIENT_FREE_ADDRESSES_IN_SUBNET", INSUFFICIENT_FREE_ADDRESSES_IN_SUBNET);
        enumMap.put("XKS_PROXY_ACCESS_DENIED", XKS_PROXY_ACCESS_DENIED);
        enumMap.put("XKS_PROXY_NOT_REACHABLE", XKS_PROXY_NOT_REACHABLE);
        enumMap.put("XKS_VPC_ENDPOINT_SERVICE_NOT_FOUND", XKS_VPC_ENDPOINT_SERVICE_NOT_FOUND);
        enumMap.put("XKS_PROXY_INVALID_RESPONSE", XKS_PROXY_INVALID_RESPONSE);
        enumMap.put("XKS_PROXY_INVALID_CONFIGURATION", XKS_PROXY_INVALID_CONFIGURATION);
        enumMap.put("XKS_VPC_ENDPOINT_SERVICE_INVALID_CONFIGURATION", XKS_VPC_ENDPOINT_SERVICE_INVALID_CONFIGURATION);
        enumMap.put("XKS_PROXY_TIMED_OUT", XKS_PROXY_TIMED_OUT);
        enumMap.put("XKS_PROXY_INVALID_TLS_CONFIGURATION", XKS_PROXY_INVALID_TLS_CONFIGURATION);
    }

    ConnectionErrorCodeType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ConnectionErrorCodeType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
