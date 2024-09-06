package com.netease.nimlib.sdk.friend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FriendChangedNotify implements Serializable {
    private List<Friend> updatedFriends = new ArrayList();
    private List<String> deletedFriends = new ArrayList();

    public FriendChangedNotify(List<Friend> list, List<String> list2) {
        if (list != null && !list.isEmpty()) {
            this.updatedFriends.addAll(list);
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        this.deletedFriends.addAll(list2);
    }

    public FriendChangedNotify(Friend friend, String str) {
        if (friend != null) {
            this.updatedFriends.add(friend);
        }
        if (str != null) {
            this.deletedFriends.add(str);
        }
    }

    public List<Friend> getAddedOrUpdatedFriends() {
        return this.updatedFriends;
    }

    public List<String> getDeletedFriends() {
        return this.deletedFriends;
    }
}
