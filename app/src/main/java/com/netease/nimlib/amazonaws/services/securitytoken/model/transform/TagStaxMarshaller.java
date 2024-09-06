package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.Request;
import com.netease.nimlib.amazonaws.services.securitytoken.model.Tag;
import com.netease.nimlib.amazonaws.util.StringUtils;

/* loaded from: classes.dex */
class TagStaxMarshaller {
    private static TagStaxMarshaller instance;

    TagStaxMarshaller() {
    }

    public void marshall(Tag tag, Request<?> request, String str) {
        if (tag.getKey() != null) {
            request.addParameter(str + "Key", StringUtils.fromString(tag.getKey()));
        }
        if (tag.getValue() != null) {
            request.addParameter(str + "Value", StringUtils.fromString(tag.getValue()));
        }
    }

    public static TagStaxMarshaller getInstance() {
        if (instance == null) {
            instance = new TagStaxMarshaller();
        }
        return instance;
    }
}
