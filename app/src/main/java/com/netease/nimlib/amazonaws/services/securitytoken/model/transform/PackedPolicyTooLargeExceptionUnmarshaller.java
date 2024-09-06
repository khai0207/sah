package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.services.securitytoken.model.PackedPolicyTooLargeException;
import com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class PackedPolicyTooLargeExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public PackedPolicyTooLargeExceptionUnmarshaller() {
        super(PackedPolicyTooLargeException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("PackedPolicyTooLarge")) {
            return null;
        }
        return (PackedPolicyTooLargeException) super.unmarshall(node);
    }
}
