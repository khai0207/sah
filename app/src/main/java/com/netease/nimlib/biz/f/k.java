package com.netease.nimlib.biz.f;

import com.netease.nimlib.robot.RobotDBHelper;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.robot.RobotService;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: RobotServiceRemote.java */
/* loaded from: classes.dex */
public class k extends com.netease.nimlib.i.j implements RobotService {
    @Override // com.netease.nimlib.sdk.robot.RobotService
    public List<NimRobotInfo> getAllRobots() {
        return a(RobotDBHelper.queryAllRobotInfo(true));
    }

    @Override // com.netease.nimlib.sdk.robot.RobotService
    public NimRobotInfo getRobotInfo(String str) {
        return RobotDBHelper.queryRobotInfo(str);
    }

    @Override // com.netease.nimlib.sdk.robot.RobotService
    public List<NimRobotInfo> getRobotInfoList(List<String> list) {
        return a(RobotDBHelper.queryRobotInfo(list));
    }

    @Override // com.netease.nimlib.sdk.robot.RobotService
    public boolean isRobot(String str) {
        return RobotDBHelper.isRobot(str);
    }

    private List<NimRobotInfo> a(List<com.netease.nimlib.robot.a> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<com.netease.nimlib.robot.a> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @Override // com.netease.nimlib.sdk.robot.RobotService
    public InvocationFuture<List<NimRobotInfo>> pullAllRobots() {
        com.netease.nimlib.biz.d.l.a aVar = new com.netease.nimlib.biz.d.l.a(com.netease.nimlib.biz.l.i());
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(aVar);
        return null;
    }
}
