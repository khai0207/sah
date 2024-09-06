package com.android.pc.ioc.internet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class ResponseEntity implements Serializable {
    private static final long serialVersionUID = 4691805279783501287L;
    private InternetConfig config;
    private String content;
    private Map<String, String> cookies;
    private int key;
    private Map<String, String> params;
    private int status = -1;
    private String url;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUrl() {
        return this.url;
    }

    public ResponseEntity setUrl(String str) {
        this.url = str;
        return this;
    }

    public String getContentAsString() {
        return this.content;
    }

    public void setContent(String str, boolean z) {
        this.content = str;
        if (z) {
            for (String str2 : this.params.keySet()) {
                this.url += str2 + this.params.get(str2);
            }
            HttpCache.setUrlCache(str, this.url);
        }
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public ResponseEntity setCookies(Map<String, String> map) {
        this.cookies = map;
        return this;
    }

    public ResponseEntity cookie(String str, String str2) {
        if (this.cookies == null) {
            this.cookies = new HashMap();
        }
        this.cookies.put(str, str2);
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    public String makeCookie() {
        Map<String, String> map = this.cookies;
        if (map == null || map.size() == 0) {
            return null;
        }
        Iterator<String> it = this.cookies.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            String next = it.next();
            String str = this.cookies.get(next);
            sb.append(next);
            sb.append("=");
            sb.append(str);
            if (it.hasNext()) {
                sb.append("; ");
            }
        }
        return sb.toString();
    }

    public String toString() {
        return "ResponseEntity [status=" + this.status + ", url=" + this.url + ", content=" + this.content + ", cookies=" + this.cookies + ", params=" + this.params + ", key=" + this.key + "]";
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int i) {
        this.key = i;
    }

    public InternetConfig getConfig() {
        return this.config;
    }

    public void setConfig(InternetConfig internetConfig) {
        this.config = internetConfig;
    }
}
