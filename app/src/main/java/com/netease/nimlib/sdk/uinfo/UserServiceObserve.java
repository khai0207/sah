package com.netease.nimlib.sdk.uinfo;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface UserServiceObserve {
    void observeUserInfoUpdate(Observer<List<NimUserInfo>> observer, boolean z);
}
