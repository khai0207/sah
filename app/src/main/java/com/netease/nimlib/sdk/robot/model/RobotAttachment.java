package com.netease.nimlib.sdk.robot.model;

import com.netease.nimlib.o.k;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class RobotAttachment implements MsgAttachment {
    private static final String TAG_CLIENT_MSG_ID = "clientMsgId";
    private static final String TAG_PARAM = "param";
    private static final String TAG_REQUEST_CONTENT = "content";
    private static final String TAG_REQUEST_PARAMS = "params";
    private static final String TAG_REQUEST_TARGET = "target";
    private static final String TAG_REQUEST_TYPE = "type";
    private static final String TAG_ROBOT_ACCID = "robotAccid";
    private static final String TAG_ROBOT_MSG = "robotMsg";
    private static final String TAG_ROBOT_SEND = "msgOut";
    private String fromRobotAccount;
    private boolean isRobotSend = true;
    private String requestContent;
    private String requestParams;
    private String requestTarget;
    private String requestType;
    private String response;
    private String responseForMessageId;

    public RobotAttachment() {
    }

    public RobotAttachment(String str) {
        fromJson(str);
    }

    public void initSend(String str, String str2, String str3, String str4, String str5) {
        this.fromRobotAccount = str;
        this.requestType = str2;
        this.requestContent = str3;
        this.requestTarget = str4;
        this.requestParams = str5;
        this.isRobotSend = false;
    }

    private void fromJson(String str) {
        JSONObject a = k.a(str);
        this.isRobotSend = k.c(a, TAG_ROBOT_SEND);
        this.fromRobotAccount = k.e(a, TAG_ROBOT_ACCID);
        this.responseForMessageId = k.e(a, TAG_CLIENT_MSG_ID);
        JSONObject g = k.g(a, TAG_ROBOT_MSG);
        if (g != null) {
            this.response = g.toString();
        }
        JSONObject g2 = k.g(a, TAG_PARAM);
        if (g2 != null) {
            this.requestType = k.e(g2, "type");
            this.requestContent = k.e(g2, TAG_REQUEST_CONTENT);
            this.requestTarget = k.e(g2, TAG_REQUEST_TARGET);
            this.requestParams = k.e(g2, "params");
        }
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachment
    public String toJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (z) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", this.requestType);
                jSONObject2.put(TAG_REQUEST_CONTENT, this.requestContent);
                jSONObject2.put(TAG_REQUEST_TARGET, this.requestTarget);
                jSONObject2.put("params", this.requestParams);
                jSONObject.put(TAG_PARAM, jSONObject2);
            } else {
                jSONObject.put(TAG_ROBOT_MSG, this.response);
                jSONObject.put(TAG_CLIENT_MSG_ID, this.responseForMessageId);
            }
            jSONObject.put(TAG_ROBOT_ACCID, this.fromRobotAccount);
            jSONObject.put(TAG_ROBOT_SEND, this.isRobotSend);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public boolean isRobotSend() {
        return this.isRobotSend;
    }

    public void setRobotSend(boolean z) {
        this.isRobotSend = z;
    }

    public String getFromRobotAccount() {
        return this.fromRobotAccount;
    }

    public void setFromRobotAccount(String str) {
        this.fromRobotAccount = str;
    }

    public String getResponseForMessageId() {
        return this.responseForMessageId;
    }

    public void setResponseForMessageId(String str) {
        this.responseForMessageId = str;
    }

    public String getResponse() {
        return this.response;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public String getRequestContent() {
        return this.requestContent;
    }

    public void setRequestContent(String str) {
        this.requestContent = str;
    }

    public String getRequestTarget() {
        return this.requestTarget;
    }

    public void setRequestTarget(String str) {
        this.requestTarget = str;
    }

    public String getRequestParams() {
        return this.requestParams;
    }

    public void setRequestParams(String str) {
        this.requestParams = str;
    }
}
