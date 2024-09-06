package com.netease.nimlib.amazonaws.services.s3.model.transform;

import com.netease.nimlib.amazonaws.services.s3.model.QueueConfiguration;
import com.netease.nimlib.amazonaws.transform.SimpleTypeStaxUnmarshallers;
import com.netease.nimlib.amazonaws.transform.StaxUnmarshallerContext;

/* loaded from: classes.dex */
class QueueConfigurationStaxUnmarshaller extends NotificationConfigurationStaxUnmarshaller<QueueConfiguration> {
    private static QueueConfigurationStaxUnmarshaller instance = new QueueConfigurationStaxUnmarshaller();

    public static QueueConfigurationStaxUnmarshaller getInstance() {
        return instance;
    }

    private QueueConfigurationStaxUnmarshaller() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netease.nimlib.amazonaws.services.s3.model.transform.NotificationConfigurationStaxUnmarshaller
    public boolean handleXmlEvent(QueueConfiguration queueConfiguration, StaxUnmarshallerContext staxUnmarshallerContext, int i) throws Exception {
        if (!staxUnmarshallerContext.testExpression("Queue", i)) {
            return false;
        }
        queueConfiguration.setQueueARN(SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller.getInstance().unmarshall(staxUnmarshallerContext));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netease.nimlib.amazonaws.services.s3.model.transform.NotificationConfigurationStaxUnmarshaller
    public QueueConfiguration createConfiguration() {
        return new QueueConfiguration();
    }
}
