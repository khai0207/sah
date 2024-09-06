package com.netease.nimlib.amazonaws.services.securitytoken.model.transform;

import com.netease.nimlib.amazonaws.services.securitytoken.model.ProvidedContext;
import com.netease.nimlib.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;

/* loaded from: classes.dex */
class ProvidedContextStaxUnmarshaller implements Unmarshaller<ProvidedContext, StaxUnmarshallerContext> {
    private static ProvidedContextStaxUnmarshaller instance;

    ProvidedContextStaxUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public ProvidedContext unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        ProvidedContext providedContext = new ProvidedContext();
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i += 2;
        }
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                break;
            }
            if (nextEvent == 2) {
                if (staxUnmarshallerContext.testExpression("ProviderArn", i)) {
                    providedContext.setProviderArn(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                } else if (staxUnmarshallerContext.testExpression("ContextAssertion", i)) {
                    providedContext.setContextAssertion(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                break;
            }
        }
        return providedContext;
    }

    public static ProvidedContextStaxUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ProvidedContextStaxUnmarshaller();
        }
        return instance;
    }
}
