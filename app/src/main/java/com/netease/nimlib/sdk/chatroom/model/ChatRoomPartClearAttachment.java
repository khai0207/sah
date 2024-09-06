package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.constant.ChatRoomQueueChangeType;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomPartClearAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_KEYS = "kvObject";
    private static final String TAG_QUEUE_CHANGE = "queueChange";
    private static final String TAG_TYPE = "_e";
    private Map<String, String> contentMap;

    public ChatRoomQueueChangeType getChatRoomQueueChangeType() {
        return ChatRoomQueueChangeType.PARTCLEAR;
    }

    public Map<String, String> getContentMap() {
        return this.contentMap;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        JSONObject a;
        super.parse(jSONObject);
        if (jSONObject.has(TAG_QUEUE_CHANGE) && (a = k.a(k.e(jSONObject, TAG_QUEUE_CHANGE))) != null && a.has(TAG_KEYS)) {
            this.contentMap = k.a(k.a(k.e(a, TAG_KEYS)));
        }
    }
}
