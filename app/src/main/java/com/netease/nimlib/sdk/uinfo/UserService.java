package com.netease.nimlib.sdk.uinfo;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.uinfo.constant.UserInfoFieldEnum;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface UserService {
    InvocationFuture<List<NimUserInfo>> fetchUserInfo(List<String> list);

    List<NimUserInfo> getAllUserInfo();

    NimUserInfo getUserInfo(String str);

    List<NimUserInfo> getUserInfoList(List<String> list);

    InvocationFuture<List<String>> searchAccountByName(String str);

    InvocationFuture<List<NimUserInfo>> searchUserInfosByKeyword(String str);

    InvocationFuture<Void> setUserState(int i);

    InvocationFuture<Void> updateUserInfo(Map<UserInfoFieldEnum, Object> map);

    InvocationFuture<Void> updateUserInfo(Map<UserInfoFieldEnum, Object> map, AntiSpamConfig antiSpamConfig);
}
