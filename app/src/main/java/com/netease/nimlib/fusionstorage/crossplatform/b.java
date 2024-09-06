package com.netease.nimlib.fusionstorage.crossplatform;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Credential;
import java.util.List;

/* compiled from: CredentialCache.java */
/* loaded from: classes.dex */
public class b {
    private int a;
    private List<Credential> b;

    public b(int i, List<Credential> list) {
        this.a = i;
        this.b = list;
    }

    public List<Credential> a() {
        return this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CredentialCache{policyVersion=");
        sb.append(this.a);
        sb.append(", credentials(size)=");
        List<Credential> list = this.b;
        sb.append(list == null ? Constants.NULL_VERSION_ID : Integer.valueOf(list.size()));
        sb.append('}');
        return sb.toString();
    }

    public boolean b() {
        List<Credential> list;
        return (this.a < 0 || (list = this.b) == null || list.isEmpty()) ? false : true;
    }
}
