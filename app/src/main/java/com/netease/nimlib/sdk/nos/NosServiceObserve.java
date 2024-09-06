package com.netease.nimlib.sdk.nos;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.nos.model.NosTransferInfo;
import com.netease.nimlib.sdk.nos.model.NosTransferProgress;

@d
/* loaded from: classes.dex */
public interface NosServiceObserve {
    void observeNosTransferProgress(Observer<NosTransferProgress> observer, boolean z);

    void observeNosTransferStatus(Observer<NosTransferInfo> observer, boolean z);
}
