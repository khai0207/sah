package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomTempMuteAddAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_MUTE_DURATION = "muteDuration";
    private long muteDuration;

    public long getMuteDuration() {
        return this.muteDuration;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_MUTE_DURATION)) {
            this.muteDuration = k.b(jSONObject, TAG_MUTE_DURATION);
        }
    }
}
