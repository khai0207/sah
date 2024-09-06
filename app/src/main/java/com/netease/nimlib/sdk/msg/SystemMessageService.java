package com.netease.nimlib.sdk.msg;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import java.util.List;

/* loaded from: classes.dex */
public interface SystemMessageService {
    void clearSystemMessages();

    void clearSystemMessagesByType(List<SystemMessageType> list);

    void deleteSystemMessage(long j);

    InvocationFuture<List<SystemMessage>> querySystemMessageByType(List<SystemMessageType> list, int i, int i2);

    List<SystemMessage> querySystemMessageByTypeBlock(List<SystemMessageType> list, int i, int i2);

    InvocationFuture<List<SystemMessage>> querySystemMessageUnread();

    InvocationFuture<Integer> querySystemMessageUnreadCount();

    int querySystemMessageUnreadCountBlock();

    int querySystemMessageUnreadCountByType(List<SystemMessageType> list);

    InvocationFuture<List<SystemMessage>> querySystemMessages(int i, int i2);

    List<SystemMessage> querySystemMessagesBlock(int i, int i2);

    void resetSystemMessageUnreadCount();

    void resetSystemMessageUnreadCountByType(List<SystemMessageType> list);

    void setSystemMessageRead(long j);

    void setSystemMessageStatus(long j, SystemMessageStatus systemMessageStatus);
}
