package com.talkingdata.sdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: td */
/* loaded from: classes.dex */
public class bu {
    private int a;
    private long b;
    private List c;
    private Map d;

    public int a() {
        return this.a;
    }

    public void setTimestamp(int i) {
        this.a = i;
    }

    public long b() {
        return this.b;
    }

    public void setPoiId(long j) {
        this.b = j;
    }

    public List c() {
        return this.c;
    }

    public void setBsslist(List list) {
        this.c = list;
    }

    public Map a(boolean z) {
        if (this.d == null || z) {
            this.d = new HashMap();
            for (bq bqVar : this.c) {
                this.d.put(bqVar.b(), bqVar);
            }
        }
        return this.d;
    }

    public bu d() {
        bu buVar = new bu();
        buVar.setTimestamp(this.a);
        buVar.setPoiId(this.b);
        LinkedList linkedList = new LinkedList();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            linkedList.add(((bq) it.next()).f());
        }
        buVar.setBsslist(linkedList);
        return buVar;
    }
}
