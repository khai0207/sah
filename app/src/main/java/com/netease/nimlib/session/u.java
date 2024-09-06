package com.netease.nimlib.session;

import android.text.TextUtils;
import android.util.Pair;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: SenderNickCache.java */
/* loaded from: classes.dex */
public class u {
    private Map<String, String> a = new ConcurrentHashMap();

    /* compiled from: SenderNickCache.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final u a = new u();
    }

    public void a() {
        this.a.clear();
        Map<String, String> queryAllSenderNick = MsgDBHelper.queryAllSenderNick();
        if (queryAllSenderNick != null && !queryAllSenderNick.isEmpty()) {
            for (Map.Entry<String, String> entry : queryAllSenderNick.entrySet()) {
                this.a.put(entry.getKey(), entry.getValue());
            }
        }
        b();
        com.netease.nimlib.log.b.c("SenderNickCache", "SenderNickCache init, cache size=" + this.a.size());
    }

    public void b() {
        com.netease.nimlib.user.b queryUserInfo = UserInfoDBHelper.queryUserInfo(com.netease.nimlib.c.n());
        if (queryUserInfo != null) {
            a(queryUserInfo.getAccount(), queryUserInfo.getName());
        }
    }

    public String a(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || sessionTypeEnum == null) {
            return null;
        }
        if (sessionTypeEnum != SessionTypeEnum.P2P) {
            String str3 = this.a.get(b(str, sessionTypeEnum, str2));
            if (str3 != null) {
                return str3;
            }
            String str4 = this.a.get(a(sessionTypeEnum, str2));
            if (str4 != null) {
                return str4;
            }
        } else {
            String str5 = this.a.get(a(sessionTypeEnum, str2));
            if (str5 != null) {
                return str5;
            }
        }
        return this.a.get(str2);
    }

    public void a(String str, String str2) {
        if (b(str, str2)) {
            com.netease.nimlib.log.b.c("SenderNickCache", "old updateSenderNick account = " + str + ",nick = " + com.netease.nimlib.log.b.a.a((CharSequence) str2));
            MsgDBHelper.saveSenderNick(str, str2);
            this.a.put(str, str2);
        }
    }

    public void a(List<IMMessageImpl> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        HashMap hashMap = new HashMap(list.size() * 2);
        for (IMMessageImpl iMMessageImpl : list) {
            Pair<String, String> a2 = a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), iMMessageImpl.getFromAccount(), iMMessageImpl.getMsgFromNick(), false);
            if (a2 != null) {
                hashMap.put(a2.first, a2.second);
            }
        }
        if (hashMap.size() != 0) {
            MsgDBHelper.saveSenderNickMap(hashMap);
        }
    }

    public Pair<String, String> a(String str, SessionTypeEnum sessionTypeEnum, String str2, String str3, boolean z) {
        if (str2 != null && str3 != null && str != null && sessionTypeEnum != null) {
            String a2 = a(sessionTypeEnum, str2);
            String str4 = this.a.get(a2);
            if (sessionTypeEnum == SessionTypeEnum.P2P) {
                if (str2.equals(com.netease.nimlib.c.n())) {
                    return null;
                }
                if (str4 != null && str4.equals(str3)) {
                    return null;
                }
                com.netease.nimlib.log.b.c("SenderNickCache", "1 new updateSenderNick sessionType = " + sessionTypeEnum + ",key = " + a2 + ",nick = " + com.netease.nimlib.log.b.a.a((CharSequence) str3));
                if (z) {
                    MsgDBHelper.saveSenderNick(a2, str3);
                }
                this.a.put(a2, str3);
                return new Pair<>(a2, str3);
            }
            String b = b(str, sessionTypeEnum, str2);
            String str5 = this.a.get(b);
            if (str5 != null) {
                if (str5.equals(str3)) {
                    return null;
                }
                com.netease.nimlib.log.b.c("SenderNickCache", "3 new updateSenderNick sessionType = " + sessionTypeEnum + ",key = " + b + ",nick = " + com.netease.nimlib.log.b.a.a((CharSequence) str3));
                if (z) {
                    MsgDBHelper.saveSenderNick(b, str3);
                }
                this.a.put(b, str3);
                return new Pair<>(b, str3);
            }
            String a3 = a(sessionTypeEnum, str2);
            String str6 = this.a.get(a3);
            if (str6 == null) {
                com.netease.nimlib.log.b.c("SenderNickCache", "4 new updateSenderNick sessionType = " + sessionTypeEnum + ",key = " + a3 + ",nick = " + com.netease.nimlib.log.b.a.a((CharSequence) str3));
                if (z) {
                    MsgDBHelper.saveSenderNick(a3, str3);
                }
                this.a.put(a3, str3);
                return new Pair<>(a3, str3);
            }
            if (!str6.equals(str3)) {
                com.netease.nimlib.log.b.c("SenderNickCache", "5 new updateSenderNick sessionType = " + sessionTypeEnum + ",key = " + b + ",nick = " + com.netease.nimlib.log.b.a.a((CharSequence) str3));
                if (z) {
                    MsgDBHelper.saveSenderNick(b, str3);
                }
                this.a.put(b, str3);
                return new Pair<>(b, str3);
            }
        }
        return null;
    }

    private String b(String str, SessionTypeEnum sessionTypeEnum, String str2) {
        return sessionTypeEnum.name() + "_" + str + "_" + str2;
    }

    private String a(SessionTypeEnum sessionTypeEnum, String str) {
        return sessionTypeEnum.name() + "_" + str;
    }

    private boolean b(String str, String str2) {
        if (str == null) {
            return false;
        }
        if (str2 == null) {
            str2 = "";
        }
        if (this.a.containsKey(str)) {
            return !this.a.get(str).equals(str2);
        }
        return true;
    }

    public static u c() {
        return a.a;
    }
}
