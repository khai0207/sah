package com.netease.nimlib.biz.c.i;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.biz.e.j.ad;
import com.netease.nimlib.biz.e.j.ae;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.RevokeType;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.r;
import com.netease.nimlib.session.s;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SessionServiceResponseHandler.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        if (!aVar.n()) {
            a(aVar, (Object) null);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.k) {
            a((com.netease.nimlib.biz.e.j.k) aVar);
            return;
        }
        if (aVar instanceof com.netease.nimlib.biz.e.j.l) {
            a((com.netease.nimlib.biz.e.j.l) aVar);
            return;
        }
        if (aVar instanceof ae) {
            a((ae) aVar);
        } else if (aVar instanceof com.netease.nimlib.biz.e.j.h) {
            a((com.netease.nimlib.biz.e.j.h) aVar);
        } else if (aVar instanceof ad) {
            a((ad) aVar);
        }
    }

    private void a(com.netease.nimlib.biz.e.j.k kVar) {
        boolean equals = "1".equals(kVar.a().c(5));
        ArrayList<com.netease.nimlib.push.packet.b.c> b = kVar.b();
        ArrayList arrayList = new ArrayList(b.size());
        String str = "";
        RevokeMsgNotification revokeMsgNotification = null;
        for (com.netease.nimlib.push.packet.b.c cVar : b) {
            String c = cVar.c(1);
            long parseLong = Long.parseLong(cVar.c(2));
            String c2 = cVar.c(3);
            int d = cVar.d(5);
            if (d == 0) {
                str = cVar.c(4);
            } else if (d == 1) {
                revokeMsgNotification = a(a(c), cVar.c(4));
            }
            arrayList.add(new r(c, parseLong, c2, str, d, revokeMsgNotification));
        }
        a(kVar, new s(equals, arrayList));
    }

    private void a(com.netease.nimlib.biz.e.j.l lVar) {
        String a = lVar.a();
        long b = lVar.b();
        String c = lVar.c();
        String d = lVar.d();
        int e = lVar.e();
        String str = "";
        RevokeMsgNotification revokeMsgNotification = null;
        if (e == 0) {
            str = d;
        } else if (e == 1) {
            revokeMsgNotification = a(a(a), d);
        }
        a(lVar, new r(a, b, c, str, e, revokeMsgNotification));
    }

    private void a(ae aeVar) {
        ((com.netease.nimlib.i.k) b(aeVar).j()).a(aeVar.r()).b();
    }

    private void a(com.netease.nimlib.biz.e.j.h hVar) {
        ((com.netease.nimlib.i.k) b(hVar).j()).a(hVar.r()).b();
    }

    private void a(ad adVar) {
        RevokeMsgNotification revokeMsgNotification;
        String str;
        String a = adVar.a();
        long b = adVar.b();
        String c = adVar.c();
        String d = adVar.d();
        int e = adVar.e();
        if (e == 0) {
            revokeMsgNotification = null;
            str = d;
        } else if (e == 1) {
            str = "";
            revokeMsgNotification = a(a(a), d);
        } else {
            revokeMsgNotification = null;
            str = "";
        }
        com.netease.nimlib.i.b.a(new r(a, b, c, str, e, revokeMsgNotification));
    }

    private RevokeMsgNotification a(Pair<SessionTypeEnum, String> pair, String str) {
        IMMessageImpl iMMessageImpl;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(String.valueOf(10));
            if (TextUtils.isEmpty(optString) || (iMMessageImpl = (IMMessageImpl) MsgDBHelper.queryMessageByUuid(optString)) == null) {
                iMMessageImpl = (IMMessageImpl) MessageBuilder.createEmptyMessage((String) pair.second, (SessionTypeEnum) pair.first, jSONObject.optLong(String.valueOf(14)));
                iMMessageImpl.setFromAccount(jSONObject.optString(String.valueOf(3)));
                iMMessageImpl.setContent(jSONObject.optString(String.valueOf(4)));
                iMMessageImpl.setPushPayloadStr(jSONObject.optString(String.valueOf(9)));
                iMMessageImpl.setUuid(optString);
                iMMessageImpl.setServerId(jSONObject.optLong(String.valueOf(11)));
            }
            return new RevokeMsgNotification(iMMessageImpl, "", jSONObject.optString(String.valueOf(16)), jSONObject.optString(String.valueOf(8)), 0, RevokeType.typeOfValue(jSONObject.optInt(String.valueOf(1))), "");
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
    
        if (r1.equals("team") == false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.util.Pair<com.netease.nimlib.sdk.msg.constant.SessionTypeEnum, java.lang.String> a(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            java.lang.String r1 = ""
            r2 = 0
            if (r0 == 0) goto Lf
            android.util.Pair r9 = new android.util.Pair
            r9.<init>(r2, r1)
            return r9
        Lf:
            java.lang.String r0 = "\\|"
            java.lang.String[] r9 = r9.split(r0)
            int r0 = r9.length
            r3 = 2
            if (r0 >= r3) goto L1f
            android.util.Pair r9 = new android.util.Pair
            r9.<init>(r2, r1)
            return r9
        L1f:
            r0 = 0
            r1 = r9[r0]
            r4 = -1
            int r5 = r1.hashCode()
            r6 = -1718157151(0xffffffff999700a1, float:-1.5613288E-23)
            r7 = 1
            if (r5 == r6) goto L4b
            r6 = 109294(0x1aaee, float:1.53154E-40)
            if (r5 == r6) goto L41
            r6 = 3555933(0x36425d, float:4.982923E-39)
            if (r5 == r6) goto L38
            goto L55
        L38:
            java.lang.String r5 = "team"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L55
            goto L56
        L41:
            java.lang.String r0 = "p2p"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L55
            r0 = 2
            goto L56
        L4b:
            java.lang.String r0 = "super_team"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L55
            r0 = 1
            goto L56
        L55:
            r0 = -1
        L56:
            if (r0 == 0) goto L63
            if (r0 == r7) goto L60
            if (r0 == r3) goto L5d
            goto L65
        L5d:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.P2P
            goto L65
        L60:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.SUPER_TEAM
            goto L65
        L63:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r2 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.Team
        L65:
            android.util.Pair r0 = new android.util.Pair
            r9 = r9[r7]
            r0.<init>(r2, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.c.i.l.a(java.lang.String):android.util.Pair");
    }
}
