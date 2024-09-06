package com.netease.nimlib.sdk.passthrough.model;

import com.netease.nimlib.push.packet.b.c;
import java.io.Serializable;

/* loaded from: classes.dex */
public class PassthroughProxyData implements Serializable {
    private String body;
    private String header;
    private int method;
    private String path;
    private String zone;

    /* loaded from: classes.dex */
    public interface Method {
        public static final int DELETE = 4;
        public static final int GET = 1;
        public static final int POST = 2;
        public static final int PUT = 3;
    }

    public PassthroughProxyData(String str, String str2, int i, String str3, String str4) {
        this.zone = str;
        this.path = str2;
        this.method = i;
        this.header = str3;
        this.body = str4;
    }

    public PassthroughProxyData(String str, int i, String str2, String str3) {
        this(null, str, i, str2, str3);
    }

    public PassthroughProxyData(String str, String str2, String str3) {
        this(str, 2, str2, str3);
    }

    public PassthroughProxyData(String str, String str2) {
        this(null, str, str2);
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getMethod() {
        if (this.method == 0) {
            this.method = 2;
        }
        return this.method;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public static PassthroughProxyData fromProperty(c cVar) {
        if (cVar == null) {
            return null;
        }
        return new PassthroughProxyData(cVar.c(4), cVar.c(5));
    }
}
