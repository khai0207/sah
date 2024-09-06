package com.netease.nimlib.sdk.robot;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface RobotService {
    List<NimRobotInfo> getAllRobots();

    NimRobotInfo getRobotInfo(String str);

    List<NimRobotInfo> getRobotInfoList(List<String> list);

    boolean isRobot(String str);

    InvocationFuture<List<NimRobotInfo>> pullAllRobots();
}
