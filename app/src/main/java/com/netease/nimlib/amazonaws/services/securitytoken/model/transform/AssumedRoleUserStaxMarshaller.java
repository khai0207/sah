package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.services.securitytoken.model.AssumedRoleUser;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
class AssumedRoleUserStaxMarshaller {
    private static AssumedRoleUserStaxMarshaller instance;

    AssumedRoleUserStaxMarshaller() {
    }

    public void marshall(AssumedRoleUser assumedRoleUser, Request<?> request, String str) {
        if (assumedRoleUser.getAssumedRoleId() != null) {
            request.addParameter(str + "AssumedRoleId", StringUtils.fromString(assumedRoleUser.getAssumedRoleId()));
        }
        if (assumedRoleUser.getArn() != null) {
            request.addParameter(str + "Arn", StringUtils.fromString(assumedRoleUser.getArn()));
        }
    }

    public static AssumedRoleUserStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new AssumedRoleUserStaxMarshaller();
        }
        return instance;
    }
}
