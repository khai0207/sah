package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.d;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisIcmp extends b {
    private int count;
    private String hostname;
    private String mResult;

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    public native String pingIcmp(String str, int i);

    public YXArtemisIcmp(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, Context context, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        super(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, yXArtemisRunTaskCallback);
        try {
            this.hostname = jSONObject.getString("hostname");
            int i3 = jSONObject.getInt("count");
            this.count = i3;
            int min = Math.min(i3, 10);
            this.count = min;
            this.count = Math.max(min, 3);
        } catch (Throwable unused) {
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
        if (this.mResult != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("hostname", this.hostname);
            } catch (Throwable unused) {
            }
            if (this.mResult.equals("task_failed")) {
                d.a().a(this, this.mResult, jSONObject.toString(), this.mReportAddr);
                return;
            }
            for (String str : this.mResult.split(",")) {
                String[] split = str.split(":");
                try {
                    if (split[0].equals("total_send")) {
                        jSONObject.put("total_send", Integer.parseInt(split[1]));
                    } else if (split[0].equals("total_recv")) {
                        jSONObject.put("total_recv", Integer.parseInt(split[1]));
                    } else if (split[0].equals("packet_loss")) {
                        jSONObject.put("packet_loss", Double.parseDouble(split[1]));
                    } else if (split[0].equals("cost_time")) {
                        jSONObject.put("cost_time", Integer.parseInt(split[1]));
                    } else if (split[0].equals("rtt_min")) {
                        jSONObject.put("rtt_min", Double.parseDouble(split[1]));
                    } else if (split[0].equals("rtt_avg")) {
                        jSONObject.put("rtt_avg", Double.parseDouble(split[1]));
                    } else if (split[0].equals("rtt_max")) {
                        jSONObject.put("rtt_max", Double.parseDouble(split[1]));
                    }
                } catch (Throwable unused2) {
                }
            }
            d.a().a(this, this.mResult, jSONObject.toString(), this.mReportAddr);
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        this.mResult = pingIcmp(this.hostname, this.count);
    }
}
