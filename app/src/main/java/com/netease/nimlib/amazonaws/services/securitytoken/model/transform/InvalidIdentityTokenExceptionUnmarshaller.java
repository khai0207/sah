package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.services.securitytoken.model.InvalidIdentityTokenException;
import com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class InvalidIdentityTokenExceptionUnmarshaller extends StandardErrorUnmarshaller {
    public InvalidIdentityTokenExceptionUnmarshaller() {
        super(InvalidIdentityTokenException.class);
    }

    @Override // com.netease.nimlib.amazonaws.transform.StandardErrorUnmarshaller, com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        if (parseErrorCode == null || !parseErrorCode.equals("InvalidIdentityToken")) {
            return null;
        }
        return (InvalidIdentityTokenException) super.unmarshall(node);
    }
}
