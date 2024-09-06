package com.netease.nimlib.amazonaws.services.s3.model.transform;

import com.netease.nimlib.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.netease.nimlib.amazonaws.services.s3.model.NotificationConfiguration;
import com.netease.nimlib.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;
import com.netease.nimlib.amazonaws.transform.Unmarshaller;
import java.util.AbstractMap;
import java.util.Map;

/* loaded from: classes.dex */
abstract class NotificationConfigurationStaxUnmarshaller<T extends NotificationConfiguration> implements Unmarshaller<Map.Entry<String, NotificationConfiguration>, StaxUnmarshallerContext> {
    protected abstract T createConfiguration();

    protected abstract boolean handleXmlEvent(T t, StaxUnmarshallerContext staxUnmarshallerContext, int i) throws Exception;

    NotificationConfigurationStaxUnmarshaller() {
    }

    @Override // com.netease.nimlib.amazonaws.transform.Unmarshaller
    public Map.Entry<String, NotificationConfiguration> unmarshall(StaxUnmarshallerContext staxUnmarshallerContext) throws Exception {
        int currentDepth = staxUnmarshallerContext.getCurrentDepth();
        int i = currentDepth + 1;
        if (staxUnmarshallerContext.isStartOfDocument()) {
            i++;
        }
        T createConfiguration = createConfiguration();
        String str = null;
        while (true) {
            int nextEvent = staxUnmarshallerContext.nextEvent();
            if (nextEvent == 1) {
                return null;
            }
            if (nextEvent == 2) {
                if (!handleXmlEvent(createConfiguration, staxUnmarshallerContext, i)) {
                    if (staxUnmarshallerContext.testExpression(JsonDocumentFields.POLICY_ID, i)) {
                        str = SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext);
                    } else if (staxUnmarshallerContext.testExpression("Event", i)) {
                        createConfiguration.addEvent(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                    } else if (staxUnmarshallerContext.testExpression("Filter", i)) {
                        createConfiguration.setFilter(FilterStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
                    }
                }
            } else if (nextEvent == 3 && staxUnmarshallerContext.getCurrentDepth() < currentDepth) {
                return new AbstractMap.SimpleEntry(str, createConfiguration);
            }
        }
    }
}
