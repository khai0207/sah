package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PolicyDescriptorType;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
class PolicyDescriptorTypeStaxMarshaller {
    private static PolicyDescriptorTypeStaxMarshaller instance;

    PolicyDescriptorTypeStaxMarshaller() {
    }

    public void marshall(PolicyDescriptorType policyDescriptorType, Request<?> request, String str) {
        if (policyDescriptorType.getArn() != null) {
            request.addParameter(str + "arn", StringUtils.fromString(policyDescriptorType.getArn()));
        }
    }

    public static PolicyDescriptorTypeStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyDescriptorTypeStaxMarshaller();
        }
        return instance;
    }
}
