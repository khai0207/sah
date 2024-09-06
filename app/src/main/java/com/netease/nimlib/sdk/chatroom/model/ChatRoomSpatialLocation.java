package com.netease.nimlib.sdk.chatroom.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatRoomSpatialLocation implements Serializable {
    private Double distance;
    private Double x;
    private Double y;
    private Double z;

    public ChatRoomSpatialLocation() {
    }

    public ChatRoomSpatialLocation(Double d, Double d2, Double d3, Double d4) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.distance = d4;
    }

    public Double getX() {
        return this.x;
    }

    public void setX(Double d) {
        this.x = d;
    }

    public Double getY() {
        return this.y;
    }

    public void setY(Double d) {
        this.y = d;
    }

    public Double getZ() {
        return this.z;
    }

    public void setZ(Double d) {
        this.z = d;
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }
}
