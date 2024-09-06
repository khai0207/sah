package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.session.j;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class QuickCommentOption implements Serializable {
    private final String ext;
    private final String fromAccount;
    private final boolean needBadge;
    private final boolean needPush;
    private final String pushContent;
    private final Map<String, Object> pushPayload;
    private final String pushTitle;
    private final long replyType;
    private final long time;

    public static QuickCommentOption fromProperty(c cVar) {
        return new QuickCommentOption(cVar.c(1), cVar.e(2), cVar.e(3), cVar.c(4), cVar.d(5) == 1, cVar.d(6) == 1, cVar.c(7), cVar.c(8), j.c(cVar.c(9)));
    }

    public static QuickCommentOption fromJson(JSONObject jSONObject) {
        return new QuickCommentOption(jSONObject.optString(String.valueOf(1)), jSONObject.optLong(String.valueOf(2)), jSONObject.optLong(String.valueOf(3)), jSONObject.optString(String.valueOf(4)), jSONObject.optInt(String.valueOf(5)) == 1, jSONObject.optInt(String.valueOf(6)) == 1, jSONObject.optString(String.valueOf(7)), jSONObject.optString(String.valueOf(8)), j.c(jSONObject.optString(String.valueOf(9))));
    }

    public QuickCommentOption(String str, long j, long j2, String str2) {
        this(str, j, j2, str2, false, false, "", "", null);
    }

    public QuickCommentOption(String str, long j, long j2, String str2, boolean z, boolean z2, String str3, String str4, Map<String, Object> map) {
        this.fromAccount = str;
        this.replyType = j;
        this.time = j2;
        this.ext = str2;
        this.needPush = z;
        this.needBadge = z2;
        this.pushTitle = str3;
        this.pushContent = str4;
        this.pushPayload = map;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public long getReplyType() {
        return this.replyType;
    }

    public long getTime() {
        return this.time;
    }

    public String getExt() {
        return this.ext;
    }

    public boolean isNeedPush() {
        return this.needPush;
    }

    public boolean isNeedBadge() {
        return this.needBadge;
    }

    public String getPushTitle() {
        return this.pushTitle;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public Map<String, Object> getPushPayload() {
        return this.pushPayload;
    }
}
