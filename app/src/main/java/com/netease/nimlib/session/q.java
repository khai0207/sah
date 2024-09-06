package com.netease.nimlib.session;

import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RecentContactImpl.java */
/* loaded from: classes.dex */
public class q implements RecentContact, RecentContactInternal {
    private String a;
    private String b;
    private String c;
    private int d;
    private MsgStatusEnum e;
    private SessionTypeEnum f;
    private String g;
    private long h;
    private long i;
    private String j;
    private int k;
    private MsgAttachment l;
    private String m;

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public String getContactId() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public String getFromAccount() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public String getFromNick() {
        return u.c().a(this.a, this.f, this.b);
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public String getRecentMessageId() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public MsgStatusEnum getMsgStatus() {
        return this.e;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public void setMsgStatus(MsgStatusEnum msgStatusEnum) {
        this.e = msgStatusEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public SessionTypeEnum getSessionType() {
        return this.f;
    }

    public void a(SessionTypeEnum sessionTypeEnum) {
        this.f = sessionTypeEnum;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public int getUnreadCount() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public String getContent() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public long getTime() {
        return this.h;
    }

    public void a(long j) {
        this.h = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public MsgAttachment getAttachment() {
        return this.l;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public void setTag(long j) {
        this.i = j;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public long getTag() {
        return this.i;
    }

    public String a() {
        return this.j;
    }

    public void e(String str) {
        this.j = str;
        if (!TextUtils.isEmpty(str)) {
            this.l = i.a().a(this.k, str);
        } else {
            this.l = null;
        }
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public MsgTypeEnum getMsgType() {
        return j.a(this.k);
    }

    public int b() {
        return this.k;
    }

    public void b(int i) {
        this.k = i;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public Map<String, Object> getExtension() {
        return j.c(this.m);
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public void setExtension(Map<String, Object> map) {
        this.m = j.a(map);
    }

    public String c() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentContact
    public boolean setLastMsg(IMMessage iMMessage) {
        if (iMMessage == null) {
            b("");
            c("");
            d("");
            setMsgStatus(MsgStatusEnum.success);
            b(MsgTypeEnum.text.getValue());
            e("");
            return true;
        }
        String sessionId = iMMessage.getSessionId();
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        if (TextUtils.isEmpty(sessionId) || !sessionId.equals(this.a) || sessionType == null || sessionType != this.f) {
            com.netease.nimlib.log.b.d("RecentContact", String.format("failed to set last msg, session not fetch: sessionId=%s, sessionType=%s", sessionId, sessionType));
            return false;
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) iMMessage;
        b(iMMessageImpl.getFromAccount());
        c(iMMessageImpl.getUuid());
        d(j.h(iMMessageImpl));
        a(iMMessageImpl.getTime());
        setMsgStatus(iMMessageImpl.getStatus());
        b(iMMessageImpl.getMsgTypeInner());
        e(iMMessageImpl.getAttachStr(false));
        return true;
    }

    @Override // com.netease.nimlib.session.RecentContactInternal
    public boolean isRemoteRead() {
        return getSessionType() == SessionTypeEnum.P2P && getTime() <= e.b().a(this.a);
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("contactId", this.a);
            jSONObject.putOpt("sessionType", this.f);
            jSONObject.putOpt("unreadCount", Integer.valueOf(this.d));
            jSONObject.putOpt("recentMessageId", this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
