package com.netease.nimlib.fusionstorage.crossplatform;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Policy;
import java.util.List;

/* compiled from: PolicyCache.java */
/* loaded from: classes.dex */
public class c {
    private List<Policy> a;
    private int b;
    private int c;
    private long d;

    public c(List<Policy> list, int i, int i2, long j) {
        this.a = list;
        this.b = i;
        this.c = i2;
        this.d = j;
    }

    public List<Policy> a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PolicyCache{policies(size)=");
        List<Policy> list = this.a;
        sb.append(list == null ? Constants.NULL_VERSION_ID : Integer.valueOf(list.size()));
        sb.append(", ttl=");
        sb.append(this.b);
        sb.append(", policyVersion=");
        sb.append(this.c);
        sb.append(", timestamp=");
        sb.append(this.d);
        sb.append('}');
        return sb.toString();
    }

    public boolean e() {
        List<Policy> list;
        return this.b > 0 && this.c >= 0 && this.d >= 0 && (list = this.a) != null && !list.isEmpty();
    }
}
