package com.netease.nimlib.sdk.robot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RobotChangedNotify implements Serializable {
    private List<NimRobotInfo> updatedRobots = new ArrayList();
    private List<String> deletedRobots = new ArrayList();

    public RobotChangedNotify(List<NimRobotInfo> list, List<String> list2) {
        if (list != null && !list.isEmpty()) {
            this.updatedRobots.addAll(list);
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        this.deletedRobots.addAll(list2);
    }

    public List<NimRobotInfo> getAddedOrUpdatedRobots() {
        return this.updatedRobots;
    }

    public List<String> getDeletedRobots() {
        return this.deletedRobots;
    }
}
