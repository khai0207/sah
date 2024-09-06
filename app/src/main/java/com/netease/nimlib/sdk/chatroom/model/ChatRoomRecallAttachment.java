package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomRecallAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_MSG_ID = "msgId";
    private static final String TAG_MSG_TIME = "msgTime";
    private long msgTime;
    private String msgUuId;

    public long getMsgTime() {
        return this.msgTime;
    }

    public String getMsgUuid() {
        return this.msgUuId;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_MSG_TIME)) {
            this.msgTime = k.b(jSONObject, TAG_MSG_TIME);
        }
        if (jSONObject.has(TAG_MSG_ID)) {
            this.msgUuId = k.e(jSONObject, TAG_MSG_ID);
        }
    }
}
