package com.netease.nimlib.sdk.friend;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.friend.constant.FriendFieldEnum;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.netease.nimlib.sdk.friend.model.Friend;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface FriendService {
    InvocationFuture<Void> ackAddFriendRequest(String str, boolean z);

    InvocationFuture<Void> addFriend(AddFriendData addFriendData);

    InvocationFuture<Void> addToBlackList(String str);

    InvocationFuture<Void> deleteFriend(String str);

    InvocationFuture<Void> deleteFriend(String str, boolean z);

    List<String> getBlackList();

    List<String> getFriendAccounts();

    Friend getFriendByAccount(String str);

    List<Friend> getFriends();

    List<String> getMuteList();

    boolean isInBlackList(String str);

    boolean isMyFriend(String str);

    boolean isNeedMessageNotify(String str);

    InvocationFuture<Void> removeFromBlackList(String str);

    InvocationFuture<List<String>> searchAccountByAlias(String str);

    InvocationFuture<List<Friend>> searchFriendsByKeyword(String str);

    InvocationFuture<Void> setMessageNotify(String str, boolean z);

    InvocationFuture<Void> updateFriendFields(String str, Map<FriendFieldEnum, Object> map);
}
