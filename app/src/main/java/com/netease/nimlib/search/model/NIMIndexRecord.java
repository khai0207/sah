package com.netease.nimlib.search.model;

/* loaded from: classes.dex */
public class NIMIndexRecord {
    public static final long TYPE_MSG = 65536;
    public String content;
    public int count;
    public long dataid;
    public Object doc;
    public String id;
    public long subtype;
    public long time;
    public long type;

    public String toString() {
        return toBriefString() + " content " + this.content;
    }

    public String toBriefString() {
        return "type " + this.type + " subtype " + this.subtype + " dataid " + this.dataid + " id " + this.id + " time " + this.time + " count " + this.count;
    }
}
