package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.o.k;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MuteMemberAttachment extends MemberChangeAttachment {
    private static final String TAG_MUTE = "mute";
    private boolean mute;

    @Override // com.netease.nimlib.sdk.team.model.MemberChangeAttachment, com.netease.nimlib.sdk.msg.attachment.NotificationAttachmentWithExtension, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_MUTE)) {
            this.mute = k.a(jSONObject, TAG_MUTE) == 1;
        }
    }

    public boolean isMute() {
        return this.mute;
    }
}
