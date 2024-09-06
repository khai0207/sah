package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomRoomMemberInAttachment extends ChatRoomNotificationAttachment {
    private static final String TAG_MUTED = "muted";
    private static final String TAG_MUTE_TEMP_TIME_LENGTH = "muteTtl";
    private static final String TAG_TEMP_MUTED = "tempMuted";
    private long muteTtl;
    private int muted;
    private int tempMuted;

    public boolean isMuted() {
        return this.muted == 1;
    }

    public boolean isTempMuted() {
        return this.tempMuted == 1;
    }

    public long getTempMutedTime() {
        return this.muteTtl;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_MUTED)) {
            this.muted = k.a(jSONObject, TAG_MUTED);
        }
        if (jSONObject.has(TAG_TEMP_MUTED)) {
            this.tempMuted = k.a(jSONObject, TAG_TEMP_MUTED);
        }
        if (jSONObject.has(TAG_MUTE_TEMP_TIME_LENGTH)) {
            this.muteTtl = k.b(jSONObject, TAG_MUTE_TEMP_TIME_LENGTH);
        }
    }
}
