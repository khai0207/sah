package com.netease.nimlib.amazonaws.services.s3.model.transform;

import com.netease.nimlib.amazonaws.services.s3.model.TopicConfiguration;
import com.netease.nimlib.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;

/* loaded from: classes.dex */
class TopicConfigurationStaxUnmarshaller extends NotificationConfigurationStaxUnmarshaller<TopicConfiguration> {
    private static TopicConfigurationStaxUnmarshaller instance = new TopicConfigurationStaxUnmarshaller();

    public static TopicConfigurationStaxUnmarshaller getInstance() {
        return instance;
    }

    private TopicConfigurationStaxUnmarshaller() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netease.nimlib.amazonaws.services.s3.model.transform.NotificationConfigurationStaxUnmarshaller
    public boolean handleXmlEvent(TopicConfiguration topicConfiguration, StaxUnmarshallerContext staxUnmarshallerContext, int i) throws Exception {
        if (!staxUnmarshallerContext.testExpression("Topic", i)) {
            return false;
        }
        topicConfiguration.setTopicARN(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netease.nimlib.amazonaws.services.s3.model.transform.NotificationConfigurationStaxUnmarshaller
    public TopicConfiguration createConfiguration() {
        return new TopicConfiguration();
    }
}
