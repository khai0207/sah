package com.netease.nimlib.sdk.msg.model;

import android.text.TextUtils;
import com.netease.nimlib.log.b;
import com.netease.nimlib.sdk.msg.constant.SystemMessageStatus;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SystemMessage implements Serializable {
    private static final String TAG = "SystemMessage";
    private String attach;
    private Object attachObject;
    private String content;
    private String customInfo;
    private String fromAccount;
    private long messageId;
    private SystemMessageStatus status;
    private String targetId;
    private long time;
    private SystemMessageType type;
    private boolean unread;

    public long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(long j) {
        this.messageId = j;
    }

    public SystemMessageType getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = SystemMessageType.typeOfValue(i);
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public SystemMessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(SystemMessageStatus systemMessageStatus) {
        this.status = systemMessageStatus;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getAttach() {
        return this.attach;
    }

    public void setAttach(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.customInfo = new JSONObject(str).optString("attach");
            } catch (JSONException e) {
                e.printStackTrace();
                b.e(TAG, "get custom info err , attach = " + str, e);
            }
        }
        this.attach = str;
    }

    public Object getAttachObject() {
        return this.attachObject;
    }

    public void setAttachObject(Object obj) {
        this.attachObject = obj;
    }

    public boolean isUnread() {
        return this.unread;
    }

    public void setUnread(boolean z) {
        this.unread = z;
    }

    public String getCustomInfo() {
        return this.customInfo;
    }
}
