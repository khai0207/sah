package com.netease.nimlib.session;

import android.database.Cursor;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.SystemMessage;

/* loaded from: classes.dex */
public class MsgDBHelperCursorTransfer {
    static q recentFromCursor(Cursor cursor) {
        q qVar = new q();
        try {
            qVar.a(cursor.getString(0));
            qVar.b(cursor.getString(1));
            qVar.c(cursor.getString(2));
            qVar.setMsgStatus(MsgStatusEnum.statusOfValue(cursor.getInt(3)));
            qVar.a(cursor.getInt(4));
            qVar.d(cursor.getString(5));
            qVar.a(cursor.getLong(6));
            qVar.a(SessionTypeEnum.typeOfValue(cursor.getInt(7)));
            qVar.setTag(cursor.getLong(8));
            qVar.b(cursor.getInt(9));
            qVar.e(cursor.getString(10));
            qVar.f(cursor.getString(11));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qVar;
    }

    static SystemMessage systemMsgFromCursor(Cursor cursor) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setMessageId(cursor.getLong(0));
        systemMessage.setTargetId(cursor.getString(1));
        systemMessage.setFromAccount(cursor.getString(2));
        systemMessage.setType(cursor.getInt(3));
        systemMessage.setTime(cursor.getLong(4));
        systemMessage.setStatus(SystemMessageStatus.statusOfValue(cursor.getInt(5)));
        systemMessage.setContent(cursor.getString(6));
        systemMessage.setAttach(cursor.getString(7));
        systemMessage.setUnread(cursor.getInt(8) == 1);
        systemMessage.setAttachObject(com.netease.nimlib.team.c.b(systemMessage.getAttach()));
        return systemMessage;
    }

    static f readReceiptFromCursor(Cursor cursor) {
        return new f(cursor.getString(0), cursor.getLong(1), cursor.getLong(2));
    }

    static MessageReceipt readSendReceiptRecordFromCursor(Cursor cursor) {
        return new MessageReceipt(cursor.getString(0), cursor.getLong(1));
    }

    static com.netease.nimlib.session.a.d sessionReliableInfoFromCursor(Cursor cursor) {
        long j = cursor.getLong(0);
        com.netease.nimlib.session.a.d dVar = new com.netease.nimlib.session.a.d(Long.valueOf(j), cursor.getString(1), SessionTypeEnum.typeOfValue(cursor.getInt(2)));
        dVar.a(cursor.getLong(3), cursor.getLong(4), cursor.getString(5));
        dVar.b(cursor.getLong(6), cursor.getLong(7), cursor.getString(8));
        return dVar;
    }
}
