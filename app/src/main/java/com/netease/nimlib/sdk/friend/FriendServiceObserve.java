package com.netease.nimlib.sdk.friend;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.friend.model.BlackListChangedNotify;
import com.netease.nimlib.sdk.friend.model.FriendChangedNotify;
import com.netease.nimlib.sdk.friend.model.MuteListChangedNotify;

@d
/* loaded from: classes.dex */
public interface FriendServiceObserve {
    void observeBlackListChangedNotify(Observer<BlackListChangedNotify> observer, boolean z);

    void observeFriendChangedNotify(Observer<FriendChangedNotify> observer, boolean z);

    void observeMuteListChangedNotify(Observer<MuteListChangedNotify> observer, boolean z);
}
