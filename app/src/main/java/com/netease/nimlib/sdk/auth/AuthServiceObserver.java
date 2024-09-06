package com.netease.nimlib.sdk.auth;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface AuthServiceObserver {
    void observeDataReady(Observer<Void> observer, boolean z);

    void observeLoginSyncDataStatus(Observer<LoginSyncStatus> observer, boolean z);

    void observeLoginSyncSuperTeamMembersCompleteResult(Observer<Boolean> observer, boolean z);

    void observeLoginSyncTeamMembersCompleteResult(Observer<Boolean> observer, boolean z);

    void observeOnlineStatus(Observer<StatusCode> observer, boolean z);

    void observeOtherClients(Observer<List<OnlineClient>> observer, boolean z);
}
