package com.netease.nimlib.amazonaws.transform;

import com.netease.nimlib.amazonaws.AmazonServiceException;
import com.netease.nimlib.amazonaws.util.XpathUtils;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class StandardErrorUnmarshaller extends AbstractErrorUnmarshaller<Node> {
    public StandardErrorUnmarshaller() {
    }

    protected StandardErrorUnmarshaller(Class<? extends AmazonServiceException> cls) {
        super(cls);
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(Node node) throws Exception {
        String parseErrorCode = parseErrorCode(node);
        String asString = XpathUtils.asString("ErrorResponse/Error/Type", node);
        String asString2 = XpathUtils.asString("ErrorResponse/RequestId", node);
        AmazonServiceException newException = newException(XpathUtils.asString("ErrorResponse/Error/Message", node));
        newException.setErrorCode(parseErrorCode);
        newException.setRequestId(asString2);
        if (asString == null) {
            newException.setErrorType(AmazonServiceException.ErrorType.Unknown);
        } else if ("Receiver".equalsIgnoreCase(asString)) {
            newException.setErrorType(AmazonServiceException.ErrorType.Service);
        } else if ("Sender".equalsIgnoreCase(asString)) {
            newException.setErrorType(AmazonServiceException.ErrorType.Client);
        }
        return newException;
    }

    public String parseErrorCode(Node node) throws Exception {
        return XpathUtils.asString("ErrorResponse/Error/Code", node);
    }

    public String getErrorPropertyPath(String str) {
        return "ErrorResponse/Error/" + str;
    }
}
