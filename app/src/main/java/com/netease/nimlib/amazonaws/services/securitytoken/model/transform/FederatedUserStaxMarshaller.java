package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.services.securitytoken.model.FederatedUser;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
class FederatedUserStaxMarshaller {
    private static FederatedUserStaxMarshaller instance;

    FederatedUserStaxMarshaller() {
    }

    public void marshall(FederatedUser federatedUser, Request<?> request, String str) {
        if (federatedUser.getFederatedUserId() != null) {
            request.addParameter(str + "FederatedUserId", StringUtils.fromString(federatedUser.getFederatedUserId()));
        }
        if (federatedUser.getArn() != null) {
            request.addParameter(str + "Arn", StringUtils.fromString(federatedUser.getArn()));
        }
    }

    public static FederatedUserStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new FederatedUserStaxMarshaller();
        }
        return instance;
    }
}
