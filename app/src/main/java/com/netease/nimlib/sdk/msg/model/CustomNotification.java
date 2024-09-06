package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CustomNotification implements Serializable {
    private NIMAntiSpamOption antiSpamOption;
    private String apnsText;
    private CustomNotificationConfig config;
    private String content;
    private String env;
    private String fromAccount;
    private Map<String, Object> pushPayload;
    private boolean sendToOnlineUserOnly = true;
    private String sessionId;
    private SessionTypeEnum sessionType;
    private long time;

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public SessionTypeEnum getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(SessionTypeEnum sessionTypeEnum) {
        this.sessionType = sessionTypeEnum;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public boolean isSendToOnlineUserOnly() {
        return this.sendToOnlineUserOnly;
    }

    public void setSendToOnlineUserOnly(boolean z) {
        this.sendToOnlineUserOnly = z;
    }

    public String getApnsText() {
        return this.apnsText;
    }

    public void setApnsText(String str) {
        this.apnsText = str;
    }

    public Map<String, Object> getPushPayload() {
        return this.pushPayload;
    }

    public void setPushPayload(Map<String, Object> map) {
        this.pushPayload = map;
    }

    public CustomNotificationConfig getConfig() {
        return this.config;
    }

    public void setConfig(CustomNotificationConfig customNotificationConfig) {
        this.config = customNotificationConfig;
    }

    public NIMAntiSpamOption getNIMAntiSpamOption() {
        return this.antiSpamOption;
    }

    public void setNIMAntiSpamOption(NIMAntiSpamOption nIMAntiSpamOption) {
        this.antiSpamOption = nIMAntiSpamOption;
    }

    public String getEnv() {
        return this.env;
    }

    public void setEnv(String str) {
        this.env = str;
    }

    public JSONObject toJsonObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("sessionId", this.sessionId);
            jSONObject.putOpt("sessionType", this.sessionType);
            jSONObject.putOpt("fromAccount", this.fromAccount);
            jSONObject.putOpt("time", Long.valueOf(this.time));
            jSONObject.putOpt("content", this.content);
            jSONObject.putOpt("sendToOnlineUserOnly", Boolean.valueOf(this.sendToOnlineUserOnly));
            jSONObject.putOpt("apnsText", this.apnsText);
            jSONObject.putOpt("pushPayload", this.pushPayload);
            jSONObject.putOpt("config", this.config);
            jSONObject.putOpt("antiSpamOption", this.antiSpamOption);
            jSONObject.putOpt("env", this.env);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
