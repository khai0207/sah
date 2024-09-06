package com.netease.nimlib.sdk.msg.attachment;

import com.netease.nimlib.o.k;
import com.netease.nimlib.session.j;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class NotificationAttachmentWithExtension extends NotificationAttachment {
    private static final String TAG_ATTACH = "attach";
    protected Map<String, Object> extension;

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        if (jSONObject.has(TAG_ATTACH)) {
            this.extension = j.c(k.e(jSONObject, TAG_ATTACH));
        }
    }
}
