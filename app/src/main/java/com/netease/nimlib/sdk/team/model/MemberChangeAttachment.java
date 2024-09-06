package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachmentWithExtension;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MemberChangeAttachment extends NotificationAttachmentWithExtension {
    private static final String TAG_ACCOUNT = "id";
    private static final String TAG_ACCOUNTS = "ids";
    private ArrayList<String> targets;

    public ArrayList<String> getTargets() {
        return this.targets;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.NotificationAttachmentWithExtension, com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject.has(TAG_ACCOUNTS)) {
            JSONArray h = k.h(jSONObject, TAG_ACCOUNTS);
            this.targets = new ArrayList<>(h.length());
            for (int i = 0; i < h.length(); i++) {
                this.targets.add(k.b(h, i));
            }
            return;
        }
        if (jSONObject.has(TAG_ACCOUNT)) {
            ArrayList<String> arrayList = new ArrayList<>(1);
            this.targets = arrayList;
            arrayList.add(k.e(jSONObject, TAG_ACCOUNT));
        }
    }
}
