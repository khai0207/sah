package com.netease.nimlib.biz.f;

import android.text.TextUtils;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.constant.UserInfoFieldEnum;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.netease.nimlib.user.UserInfoDBHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: UserServiceRemote.java */
/* loaded from: classes.dex */
public class o extends com.netease.nimlib.i.j implements UserService {
    private final String a = "^[+\\-\\(\\)\\d]+$";
    private final String b = "^\\S+@\\S+$";
    private final String c = "^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<List<NimUserInfo>> fetchUserInfo(List<String> list) {
        if (list == null) {
            return null;
        }
        if (list.size() > 150) {
            throw new IllegalArgumentException("fetch user count exceeds SDK limit 150!");
        }
        com.netease.nimlib.user.c.a(list, b());
        return null;
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public List<NimUserInfo> getUserInfoList(List<String> list) {
        ArrayList<com.netease.nimlib.user.b> queryUserInfo = UserInfoDBHelper.queryUserInfo(list);
        ArrayList arrayList = new ArrayList(queryUserInfo.size());
        arrayList.addAll(queryUserInfo);
        return arrayList;
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public NimUserInfo getUserInfo(String str) {
        return UserInfoDBHelper.queryUserInfo(str);
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public List<NimUserInfo> getAllUserInfo() {
        List<com.netease.nimlib.user.b> queryAllUserInfo = UserInfoDBHelper.queryAllUserInfo();
        ArrayList arrayList = new ArrayList(queryAllUserInfo.size());
        arrayList.addAll(queryAllUserInfo);
        return arrayList;
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<Void> updateUserInfo(Map<UserInfoFieldEnum, Object> map) {
        return updateUserInfo(map, null);
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<Void> updateUserInfo(Map<UserInfoFieldEnum, Object> map, AntiSpamConfig antiSpamConfig) {
        com.netease.nimlib.biz.d.l.h hVar;
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        for (Map.Entry<UserInfoFieldEnum, Object> entry : map.entrySet()) {
            if (entry.getKey() == UserInfoFieldEnum.EXTEND && (entry.getValue() instanceof Map)) {
                entry.setValue(com.netease.nimlib.session.j.a((Map) entry.getValue()));
            }
            if (!entry.getKey().getFieldType().isInstance(entry.getValue())) {
                throw new IllegalArgumentException("incompatible field data type");
            }
            if (entry.getKey() == UserInfoFieldEnum.undefined) {
                throw new IllegalArgumentException("undefined userInfo field");
            }
            if (entry.getKey() == UserInfoFieldEnum.MOBILE) {
                if (!a((String) entry.getValue(), "^[+\\-\\(\\)\\d]+$")) {
                    throw new IllegalArgumentException("mobile format error");
                }
            } else if (entry.getKey() == UserInfoFieldEnum.EMAIL) {
                if (!a((String) entry.getValue(), "^\\S+@\\S+$")) {
                    throw new IllegalArgumentException("mail format error");
                }
            } else if (entry.getKey() == UserInfoFieldEnum.BIRTHDAY && !a((String) entry.getValue(), "^(\\d{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
                throw new IllegalArgumentException("birthday format error");
            }
            cVar.a(1, com.netease.nimlib.c.n());
            if (entry.getKey().getFieldType() == String.class) {
                cVar.a(entry.getKey().getValue(), (String) entry.getValue());
            } else if (entry.getKey().getFieldType() == Integer.class) {
                cVar.a(entry.getKey().getValue(), ((Integer) entry.getValue()).intValue());
            }
        }
        if (antiSpamConfig == null) {
            hVar = new com.netease.nimlib.biz.d.l.h(cVar);
        } else {
            com.netease.nimlib.push.packet.b.c cVar2 = new com.netease.nimlib.push.packet.b.c();
            String antiSpamBusinessId = antiSpamConfig.getAntiSpamBusinessId();
            if (!TextUtils.isEmpty(antiSpamBusinessId)) {
                cVar2.a(1, antiSpamBusinessId);
            }
            hVar = new com.netease.nimlib.biz.d.l.h(cVar, cVar2);
        }
        hVar.a(b());
        com.netease.nimlib.biz.i.a().a(hVar);
        return null;
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return Pattern.compile(str2).matcher(str.trim()).find();
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<Void> setUserState(int i) {
        com.netease.nimlib.c.a(Integer.valueOf(i));
        final com.netease.nimlib.i.k b = b();
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.h.d(i)) { // from class: com.netease.nimlib.biz.f.o.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                b.a(aVar.r()).b();
            }
        });
        return null;
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<List<NimUserInfo>> searchUserInfosByKeyword(String str) {
        b().b(UserInfoDBHelper.searchUserInfosByKeyword(str)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.uinfo.UserService
    public InvocationFuture<List<String>> searchAccountByName(String str) {
        b().b(UserInfoDBHelper.queryAccountByName(str)).b();
        return null;
    }
}
