package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import org.json.JSONObject;

/* compiled from: MsgJsonHelper.java */
/* loaded from: classes.dex */
public class m {
    public static MessageRobotInfo a(String str) {
        JSONObject a = com.netease.nimlib.o.k.a(str);
        if (a == null) {
            return null;
        }
        return new MessageRobotInfo(a.has("function") ? a.optString("function") : null, a.has("topic") ? a.optString("topic") : null, a.has("customContent") ? a.optString("customContent") : null, a.has("account") ? a.optString("account") : null);
    }

    public static String a(MessageRobotInfo messageRobotInfo) {
        if (messageRobotInfo == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("function", messageRobotInfo.getFunction());
            jSONObject.put("topic", messageRobotInfo.getTopic());
            jSONObject.put("customContent", messageRobotInfo.getCustomContent());
            jSONObject.put("account", messageRobotInfo.getAccount());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.e("MsgJsonHelper", "toJsonString " + messageRobotInfo, e);
            return "";
        }
    }
}
