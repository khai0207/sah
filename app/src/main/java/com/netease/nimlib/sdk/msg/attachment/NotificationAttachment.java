package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.sdk.msg.constant.NotificationType;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class NotificationAttachment implements MsgAttachment {
    private NotificationType type;

    public final void fromJson(String str) {
    }

    public abstract void parse(JSONObject jSONObject);

    @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachment
    public String toJson(boolean z) {
        return null;
    }

    public NotificationType getType() {
        return this.type;
    }

    public void setType(NotificationType notificationType) {
        this.type = notificationType;
    }
}
