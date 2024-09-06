package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.services.securitytoken.model.IDPRejectedClaimException;
import com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class IDPRejectedClaimExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public IDPRejectedClaimExceptionUnmarshaller() {
        super(IDPRejectedClaimException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("IDPRejectedClaim")) {
            return null;
        }
        return (IDPRejectedClaimException) super.unmarshall(node);
    }
}
