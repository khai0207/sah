package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SystemMessageServiceRemote.java */
/* loaded from: classes.dex */
public class m extends com.netease.nimlib.i.j implements SystemMessageService {
    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public InvocationFuture<List<SystemMessage>> querySystemMessages(int i, int i2) {
        ArrayList<SystemMessage> querySystemMessages = MsgDBHelper.querySystemMessages(i, i2);
        com.netease.nimlib.session.j.a(querySystemMessages);
        com.netease.nimlib.session.j.b(querySystemMessages);
        b().b(querySystemMessages).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public List<SystemMessage> querySystemMessagesBlock(int i, int i2) {
        ArrayList<SystemMessage> querySystemMessages = MsgDBHelper.querySystemMessages(i, i2);
        com.netease.nimlib.session.j.a(querySystemMessages);
        com.netease.nimlib.session.j.b(querySystemMessages);
        return querySystemMessages;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public InvocationFuture<List<SystemMessage>> querySystemMessageByType(List<SystemMessageType> list, int i, int i2) {
        ArrayList<SystemMessage> querySystemMessage = MsgDBHelper.querySystemMessage(list, i, i2);
        com.netease.nimlib.session.j.a(querySystemMessage);
        com.netease.nimlib.session.j.b(querySystemMessage);
        b().b(querySystemMessage).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public List<SystemMessage> querySystemMessageByTypeBlock(List<SystemMessageType> list, int i, int i2) {
        ArrayList<SystemMessage> querySystemMessage = MsgDBHelper.querySystemMessage(list, i, i2);
        com.netease.nimlib.session.j.a(querySystemMessage);
        com.netease.nimlib.session.j.b(querySystemMessage);
        return querySystemMessage;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public InvocationFuture<List<SystemMessage>> querySystemMessageUnread() {
        ArrayList<SystemMessage> querySystemMessageUnread = MsgDBHelper.querySystemMessageUnread();
        com.netease.nimlib.session.j.a(querySystemMessageUnread);
        com.netease.nimlib.session.j.b(querySystemMessageUnread);
        b().b(querySystemMessageUnread).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void setSystemMessageStatus(long j, SystemMessageStatus systemMessageStatus) {
        MsgDBHelper.setSystemMessageStatus(j, systemMessageStatus);
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public InvocationFuture<Integer> querySystemMessageUnreadCount() {
        b().b(Integer.valueOf(querySystemMessageUnreadCountBlock())).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public int querySystemMessageUnreadCountBlock() {
        return MsgDBHelper.querySystemMessageUnreadNum();
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public int querySystemMessageUnreadCountByType(List<SystemMessageType> list) {
        return MsgDBHelper.querySystemMessageUnreadNum(list);
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void resetSystemMessageUnreadCount() {
        MsgDBHelper.setAllSystemMessageRead();
        com.netease.nimlib.i.b.a(0);
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void resetSystemMessageUnreadCountByType(List<SystemMessageType> list) {
        MsgDBHelper.setAllSystemMessageRead(list);
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void setSystemMessageRead(long j) {
        MsgDBHelper.setSystemMessageRead(j);
        com.netease.nimlib.i.b.a(querySystemMessageUnreadCountBlock());
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void deleteSystemMessage(long j) {
        MsgDBHelper.deleteSystemMessage(j);
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void clearSystemMessages() {
        MsgDBHelper.clearSystemMessages();
    }

    @Override // com.netease.nimlib.sdk.msg.SystemMessageService
    public void clearSystemMessagesByType(List<SystemMessageType> list) {
        MsgDBHelper.clearSystemMessages(list);
    }
}
