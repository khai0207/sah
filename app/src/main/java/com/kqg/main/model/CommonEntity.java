package com.kqg.main.model;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class CommonEntity {
    private JSONObject json;
    private String msg;
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setJson(JSONObject jSONObject) {
        this.json = jSONObject;
    }

    public JSONObject getJson() {
        return this.json;
    }

    public String toString() {
        return "CommonEntity [status=" + this.status + ", msg=" + this.msg + "]";
    }
}
