package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.log.b.a;
import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.session.j;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ChatRoomNotificationAttachment extends NotificationAttachment {
    private static final String TAG_EXTENSION = "ext";
    private static final String TAG_OPERATOR = "operator";
    private static final String TAG_OPERATOR_NICK = "opeNick";
    private static final String TAG_TAGS = "tags";
    private static final String TAG_TARGET = "target";
    private static final String TAG_TARGET_NICK = "tarNick";
    private Map<String, Object> extension;
    private String operator;
    private String operatorNick;
    private ArrayList<String> tags;
    private ArrayList<String> targetNicks;
    private ArrayList<String> targets;

    public ArrayList<String> getTargets() {
        return this.targets;
    }

    public ArrayList<String> getTargetNicks() {
        return this.targetNicks;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getOperatorNick() {
        return this.operatorNick;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.NotificationAttachment
    public void parse(JSONObject jSONObject) {
        JSONArray b;
        if (jSONObject.has(TAG_TARGET)) {
            JSONArray h = k.h(jSONObject, TAG_TARGET);
            this.targets = new ArrayList<>(h.length());
            for (int i = 0; i < h.length(); i++) {
                this.targets.add(k.b(h, i));
            }
        }
        if (jSONObject.has(TAG_TARGET_NICK)) {
            JSONArray h2 = k.h(jSONObject, TAG_TARGET_NICK);
            this.targetNicks = new ArrayList<>(h2.length());
            for (int i2 = 0; i2 < h2.length(); i2++) {
                this.targetNicks.add(k.b(h2, i2));
            }
        }
        if (jSONObject.has(TAG_OPERATOR)) {
            this.operator = k.e(jSONObject, TAG_OPERATOR);
        }
        if (jSONObject.has(TAG_OPERATOR_NICK)) {
            this.operatorNick = k.e(jSONObject, TAG_OPERATOR_NICK);
        }
        if (jSONObject.has("ext")) {
            this.extension = j.c(k.e(jSONObject, "ext"));
        }
        if (!jSONObject.has("tags") || (b = k.b(k.e(jSONObject, "tags"))) == null) {
            return;
        }
        this.tags = new ArrayList<>(b.length());
        for (int i3 = 0; i3 < b.length(); i3++) {
            this.tags.add(k.b(b, i3));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChatRoomNotificationAttachment{targets=");
        ArrayList<String> arrayList = this.targets;
        String str = Constants.NULL_VERSION_ID;
        sb.append(arrayList == null ? Constants.NULL_VERSION_ID : arrayList.toString());
        sb.append(", targetNicks=");
        ArrayList<String> arrayList2 = this.targetNicks;
        sb.append(arrayList2 == null ? Constants.NULL_VERSION_ID : a.a((CharSequence) arrayList2.toString()));
        sb.append(", operator='");
        sb.append(this.operator);
        sb.append('\'');
        sb.append(", operatorNick='");
        sb.append(a.a((CharSequence) this.operatorNick));
        sb.append('\'');
        sb.append(", extension=");
        sb.append(this.extension);
        sb.append(", tags=");
        ArrayList<String> arrayList3 = this.tags;
        if (arrayList3 != null) {
            str = arrayList3.toString();
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
