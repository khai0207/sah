package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.constant.ChatRoomQueueChangeType;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomQueueChangeAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_CONTENT = "content";
    private static final String TAG_KEY = "key";
    private static final String TAG_KEYS = "kvObject";
    private static final String TAG_QUEUE_CHANGE = "queueChange";
    private static final String TAG_TYPE = "_e";
    private ChatRoomQueueChangeType chatRoomQueueChangeType;
    private String content;
    private Map<String, String> contentMap;
    private String key;

    public ChatRoomQueueChangeType getChatRoomQueueChangeType() {
        return this.chatRoomQueueChangeType;
    }

    public String getKey() {
        return this.key;
    }

    public String getContent() {
        return this.content;
    }

    public Map<String, String> getContentMap() {
        return this.contentMap;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_QUEUE_CHANGE)) {
            JSONObject a = k.a(k.e(jSONObject, TAG_QUEUE_CHANGE));
            if (a.has(TAG_TYPE)) {
                this.chatRoomQueueChangeType = ChatRoomQueueChangeType.valueOf(k.e(a, TAG_TYPE));
            }
            if (a.has("key")) {
                this.key = k.e(a, "key");
            }
            if (a.has(TAG_CONTENT)) {
                this.content = k.e(a, TAG_CONTENT);
            }
            if (a.has(TAG_KEYS)) {
                this.contentMap = k.a(k.a(k.e(a, TAG_KEYS)));
            }
        }
    }
}
