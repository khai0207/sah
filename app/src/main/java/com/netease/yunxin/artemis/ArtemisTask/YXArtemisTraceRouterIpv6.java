package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.d;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisTraceRouterIpv6 extends b {
    private int datalen;
    private String hostname;
    private String mResult;
    private int maxttl;
    private int nFlag;
    private int nprobes;
    private int port;
    private String route;
    private int tos;
    private int waittime;

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    public native String tracerouteIpv6(String str, int i, int i2, int i3, int i4, String str2, int i5, int i6, int i7);

    public YXArtemisTraceRouterIpv6(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, Context context, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        super(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, yXArtemisRunTaskCallback);
        try {
            this.hostname = jSONObject.getString("hostname");
            this.maxttl = jSONObject.getInt("max_ttl");
            this.nFlag = jSONObject.getInt("nflag");
            this.port = jSONObject.getInt("port");
            this.nprobes = jSONObject.getInt("nprobes");
            this.route = jSONObject.getString("route");
            this.tos = jSONObject.getInt("tos");
            this.waittime = jSONObject.getInt("waittime");
            this.datalen = jSONObject.getInt("datalen");
        } catch (Throwable unused) {
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void aggregateResult() {
        if (this.mResult != null) {
            JSONArray jSONArray = new JSONArray();
            try {
                jSONArray.put(new JSONObject().put("hostname", this.hostname));
                jSONArray.put(new JSONObject().put("port", this.port));
            } catch (Throwable unused) {
            }
            if (this.mResult.equals("task_failed")) {
                d.a().a(this, this.mResult, jSONArray.toString(), this.mReportAddr);
                return;
            }
            String[] split = this.mResult.split("ttl");
            int length = split.length;
            char c = 0;
            int i = 0;
            while (i < length) {
                String str = split[i];
                if (!str.equals("")) {
                    JSONObject jSONObject = new JSONObject();
                    String[] split2 = str.split(",");
                    int length2 = split2.length;
                    int i2 = 0;
                    while (i2 < length2) {
                        String[] split3 = split2[i2].split(":");
                        try {
                            if (split3[c].equals("")) {
                                jSONObject.put("ttl", Integer.parseInt(split3.length > 1 ? split3[1] : "0"));
                            } else if (split3[0].equals("addr")) {
                                jSONObject.put("addr", split3.length > 1 ? split3[1] : "");
                            } else if (split3[0].equals("time")) {
                                jSONObject.put("time", Double.parseDouble(split3.length > 1 ? split3[1] : "0"));
                            }
                        } catch (Throwable unused2) {
                        }
                        i2++;
                        c = 0;
                    }
                    jSONArray.put(jSONObject.toString());
                }
                i++;
                c = 0;
            }
            d.a().a(this, this.mResult, jSONArray.toString(), this.mReportAddr);
        }
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        this.mResult = tracerouteIpv6(this.hostname, this.maxttl, this.nFlag, this.port, this.nprobes, this.route, this.tos, this.waittime, this.datalen);
    }
}
