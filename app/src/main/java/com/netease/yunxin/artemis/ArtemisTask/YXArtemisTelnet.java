package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.d;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisTelnet extends b {
    private String hostname;
    private int mResult;
    private String port;
    private long startMill;

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    public native int telnet(String str, String str2);

    public YXArtemisTelnet(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, Context context, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        super(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, yXArtemisRunTaskCallback);
        this.mResult = -1;
        try {
            this.hostname = jSONObject.getString("hostname");
            this.port = jSONObject.getString("port");
        } catch (Throwable unused) {
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
        new StringBuilder("telnet ").append(this.mResult);
        long currentTimeMillis = System.currentTimeMillis();
        String str = this.mResult == 0 ? "connect success!" : "task_failed";
        JSONObject jSONObject = new JSONObject();
        try {
            String str2 = "";
            jSONObject.put("hostname", this.hostname == null ? "" : this.hostname);
            if (this.port != null) {
                str2 = this.port;
            }
            jSONObject.put("port", str2);
            jSONObject.put("cost_time", currentTimeMillis - this.startMill);
        } catch (Throwable unused) {
        }
        d.a().a(this, str, jSONObject.toString(), this.mReportAddr);
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        String str;
        this.startMill = System.currentTimeMillis();
        String str2 = this.hostname;
        if (str2 == null || (str = this.port) == null) {
            return;
        }
        this.mResult = telnet(str2, str);
    }
}
