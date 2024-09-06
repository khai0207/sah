package com.netease.nimlib.sdk.robot;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.robot.model.RobotChangedNotify;

@d
/* loaded from: classes.dex */
public interface RobotServiceObserve {
    void observeRobotChangedNotify(Observer<RobotChangedNotify> observer, boolean z);
}
