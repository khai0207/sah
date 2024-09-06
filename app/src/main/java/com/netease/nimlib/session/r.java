package com.netease.nimlib.session;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RecentSessionImpl.java */
/* loaded from: classes.dex */
public class r implements RecentSession {
    private final String a;
    private final long b;
    private final String c;
    private final String d;
    private final int e;
    private RevokeMsgNotification f;

    public r(String str, long j, String str2, String str3, int i, RevokeMsgNotification revokeMsgNotification) {
        this.a = str;
        this.b = j;
        this.c = str2;
        this.d = str3;
        this.e = i;
        this.f = revokeMsgNotification;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public String getSessionId() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public long getUpdateTime() {
        return this.b;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public String getExt() {
        return this.c;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public String getLastMsg() {
        return this.d;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public RevokeMsgNotification getRevokeNotification() {
        return this.f;
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public RecentContact toRecentContact() {
        IMMessage createFromJson;
        String lastMsg = getLastMsg();
        RevokeMsgNotification revokeNotification = getRevokeNotification();
        int lastMsgType = getLastMsgType();
        q qVar = new q();
        qVar.a(getUpdateTime());
        qVar.setExtension(a(getExt()));
        Pair<SessionTypeEnum, String> parseSessionId = parseSessionId();
        qVar.a((String) parseSessionId.second);
        qVar.a((SessionTypeEnum) parseSessionId.first);
        qVar.a(0);
        if (lastMsgType != 1 || revokeNotification == null) {
            if (lastMsgType != 0 || lastMsg == null || (createFromJson = MessageBuilder.createFromJson(lastMsg)) == null) {
                return qVar;
            }
            qVar.b(createFromJson.getFromAccount());
            qVar.c(createFromJson.getUuid());
            qVar.d(createFromJson.getContent());
            qVar.setMsgStatus(createFromJson.getStatus());
            qVar.b(createFromJson.getMsgType().getValue());
            qVar.e(createFromJson.getAttachStr());
            return qVar;
        }
        IMMessage message = revokeNotification.getMessage();
        qVar.b(revokeNotification.getRevokeAccount());
        qVar.d(this.f.getCustomInfo());
        qVar.setMsgStatus(MsgStatusEnum.success);
        qVar.b(MsgTypeEnum.text.getValue());
        if (message != null) {
            qVar.c(message.getUuid());
            qVar.e(message.getAttachStr());
            qVar.setMsgStatus(message.getStatus());
            qVar.b(message.getMsgType().getValue());
        }
        return qVar;
    }

    private Map<String, Object> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
        } catch (JSONException unused) {
            hashMap.put(RecentSession.KEY_EXT, getExt());
        }
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        if (r3.equals("team") == false) goto L26;
     */
    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.util.Pair<com.netease.nimlib.sdk.msg.constant.SessionTypeEnum, java.lang.String> parseSessionId() {
        /*
            r9 = this;
            java.lang.String r0 = r9.a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r1 = ""
            r2 = 0
            if (r0 == 0) goto L11
            android.util.Pair r0 = new android.util.Pair
            r0.<init>(r2, r1)
            return r0
        L11:
            java.lang.String r0 = r9.a
            java.lang.String r3 = "\\|"
            java.lang.String[] r0 = r0.split(r3)
            int r3 = r0.length
            r4 = 2
            if (r3 >= r4) goto L23
            android.util.Pair r0 = new android.util.Pair
            r0.<init>(r2, r1)
            return r0
        L23:
            r1 = 0
            r3 = r0[r1]
            r5 = -1
            int r6 = r3.hashCode()
            r7 = -1718157151(0xffffffff999700a1, float:-1.5613288E-23)
            r8 = 1
            if (r6 == r7) goto L4f
            r7 = 109294(0x1aaee, float:1.53154E-40)
            if (r6 == r7) goto L45
            r7 = 3555933(0x36425d, float:4.982923E-39)
            if (r6 == r7) goto L3c
            goto L59
        L3c:
            java.lang.String r6 = "team"
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L59
            goto L5a
        L45:
            java.lang.String r1 = "p2p"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L59
            r1 = 2
            goto L5a
        L4f:
            java.lang.String r1 = "super_team"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L59
            r1 = 1
            goto L5a
        L59:
            r1 = -1
        L5a:
            if (r1 == 0) goto L67
            if (r1 == r8) goto L64
            if (r1 == r4) goto L61
            goto L69
        L61:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.P2P
            goto L69
        L64:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.SUPER_TEAM
            goto L69
        L67:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.Team
        L69:
            android.util.Pair r1 = new android.util.Pair
            r0 = r0[r8]
            r1.<init>(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.r.parseSessionId():android.util.Pair");
    }

    @Override // com.netease.nimlib.sdk.msg.model.RecentSession
    public int getLastMsgType() {
        return this.e;
    }
}
