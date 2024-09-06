package com.netease.nimlib.biz.f;

import android.text.TextUtils;
import com.netease.nimlib.friend.FriendDBHelper;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.FriendFieldEnum;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.netease.nimlib.sdk.friend.model.Friend;
import com.netease.nimlib.user.UserDBHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: FriendServiceRemote.java */
/* loaded from: classes.dex */
public class d extends com.netease.nimlib.i.j implements FriendService {
    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> addFriend(AddFriendData addFriendData) {
        com.netease.nimlib.biz.d.b.a aVar = new com.netease.nimlib.biz.d.b.a(addFriendData.getAccount(), addFriendData.getVerifyType().getValue(), addFriendData.getMsg());
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> ackAddFriendRequest(String str, boolean z) {
        com.netease.nimlib.biz.d.b.a aVar = new com.netease.nimlib.biz.d.b.a(str, z ? (byte) 3 : (byte) 4, null);
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> deleteFriend(String str) {
        deleteFriend(str, false);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> deleteFriend(String str, boolean z) {
        com.netease.nimlib.biz.d.b.b bVar = new com.netease.nimlib.biz.d.b.b(str, z);
        bVar.a(b());
        com.netease.nimlib.biz.i.a().a(bVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public List<Friend> getFriends() {
        ArrayList<com.netease.nimlib.friend.b> friends = FriendDBHelper.getFriends();
        ArrayList arrayList = new ArrayList(friends.size());
        arrayList.addAll(friends);
        return arrayList;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public List<String> getFriendAccounts() {
        return FriendDBHelper.getFriendAccounts();
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public Friend getFriendByAccount(String str) {
        return FriendDBHelper.queryFriend(str);
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public boolean isMyFriend(String str) {
        return FriendDBHelper.isMyFriend(str);
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> updateFriendFields(String str, Map<FriendFieldEnum, Object> map) {
        a(map);
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        cVar.a(4, str);
        for (Map.Entry<FriendFieldEnum, Object> entry : map.entrySet()) {
            if (entry.getKey().getFieldType() == String.class) {
                cVar.a(entry.getKey().getValue(), (String) entry.getValue());
            } else if (entry.getKey().getFieldType() == Map.class) {
                String a = com.netease.nimlib.friend.a.a((Map<String, Object>) entry.getValue());
                if (!TextUtils.isEmpty(a)) {
                    cVar.a(entry.getKey().getValue(), a);
                }
            }
        }
        com.netease.nimlib.biz.d.b.c cVar2 = new com.netease.nimlib.biz.d.b.c(cVar);
        cVar2.a(b());
        com.netease.nimlib.biz.i.a().a(cVar2);
        return null;
    }

    private void a(Map<FriendFieldEnum, Object> map) {
        for (Map.Entry<FriendFieldEnum, Object> entry : map.entrySet()) {
            if (!entry.getKey().getFieldType().isInstance(entry.getValue())) {
                throw new IllegalArgumentException("type of FriendFieldEnum." + entry.getKey().name() + " wrong, should be " + entry.getKey().getFieldType().getName());
            }
            if (entry.getKey() == FriendFieldEnum.undefined) {
                throw new IllegalArgumentException("undefined friend field");
            }
        }
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> addToBlackList(String str) {
        com.netease.nimlib.biz.d.l.c cVar = new com.netease.nimlib.biz.d.l.c(true, str);
        cVar.a(b());
        com.netease.nimlib.biz.i.a().a(cVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> removeFromBlackList(String str) {
        com.netease.nimlib.biz.d.l.c cVar = new com.netease.nimlib.biz.d.l.c(false, str);
        cVar.a(b());
        com.netease.nimlib.biz.i.a().a(cVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public boolean isInBlackList(String str) {
        return com.netease.nimlib.user.c.b(str);
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public List<String> getBlackList() {
        return UserDBHelper.getBlackList();
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<Void> setMessageNotify(String str, boolean z) {
        com.netease.nimlib.biz.d.l.d dVar = new com.netease.nimlib.biz.d.l.d(!z, str);
        dVar.a(b());
        com.netease.nimlib.biz.i.a().a(dVar);
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public boolean isNeedMessageNotify(String str) {
        return com.netease.nimlib.user.c.c(str);
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public List<String> getMuteList() {
        return UserDBHelper.getMuteList();
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<List<Friend>> searchFriendsByKeyword(String str) {
        b().b(FriendDBHelper.searchFriendsByKeyword(str)).b();
        return null;
    }

    @Override // com.netease.nimlib.sdk.friend.FriendService
    public InvocationFuture<List<String>> searchAccountByAlias(String str) {
        b().b(FriendDBHelper.searchAccountByAlias(str)).b();
        return null;
    }
}
