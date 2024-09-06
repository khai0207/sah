package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.log.b;
import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.constant.ChatRoomQueueChangeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomQueueBatchAddAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_ELEMENTS = "elements";
    private static final String TAG_QUEUE_CHANGE = "queueChange";
    private static final String TAG_TYPE = "_e";
    private ChatRoomQueueChangeType chatRoomQueueChangeType = null;
    private List<Map<String, String>> contentMapList = null;

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        JSONObject a;
        JSONArray b;
        super.parse(jSONObject);
        if (!jSONObject.has(TAG_QUEUE_CHANGE) || (a = k.a(k.e(jSONObject, TAG_QUEUE_CHANGE))) == null) {
            return;
        }
        if (a.has(TAG_TYPE)) {
            this.chatRoomQueueChangeType = ChatRoomQueueChangeType.valueOf(k.e(a, TAG_TYPE));
        }
        if (!a.has(TAG_ELEMENTS) || (b = k.b(k.e(a, TAG_ELEMENTS))) == null) {
            return;
        }
        int length = b.length();
        this.contentMapList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            try {
                this.contentMapList.add(k.a(b.getJSONObject(i)));
            } catch (JSONException e) {
                b.a("parse elements error", e);
                return;
            }
        }
    }

    public ChatRoomQueueChangeType getChatRoomQueueChangeType() {
        return this.chatRoomQueueChangeType;
    }

    public List<Map<String, String>> getContentMapList() {
        return this.contentMapList;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment
    public String toString() {
        return "ChatRoomQueueBatchAddAttachment{, " + super.toString() + "chatRoomQueueChangeType=" + this.chatRoomQueueChangeType + ", contentMapList=" + this.contentMapList + '}';
    }
}
