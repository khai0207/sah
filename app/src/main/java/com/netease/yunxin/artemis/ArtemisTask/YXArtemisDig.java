package com.netease.yunxin.artemis.ArtemisTask;

import android.content.Context;
import com.alipay.sdk.m.h.c;
import com.netease.yunxin.artemis.Artemis.YXArtemisRunTaskCallback;
import com.netease.yunxin.artemis.Artemis.b;
import com.netease.yunxin.artemis.Artemis.d;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YXArtemisDig extends b {
    private String hostname;
    private Context mContext;
    private String mResult;

    private static native int initialize_native(Context context);

    public native String dig(String str);

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void finishTask() {
    }

    public YXArtemisDig(String str, String str2, String str3, int i, JSONObject jSONObject, String str4, String str5, int i2, String str6, Context context, String str7, YXArtemisRunTaskCallback yXArtemisRunTaskCallback) {
        super(str, str2, str3, i, jSONObject, str4, str5, i2, str6, str7, yXArtemisRunTaskCallback);
        this.hostname = "";
        this.mContext = context;
        try {
            this.hostname = jSONObject.getString("hostname");
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
            String replace = this.mResult.replace("\n", "");
            this.mResult = replace;
            for (String str : replace.split(",")) {
                String[] split = str.split(":");
                try {
                    if (split[0].equals(c.e)) {
                        jSONObject.put(c.e, split.length > 1 ? split[1] : "");
                    } else if (split[0].equals("ttl")) {
                        jSONObject.put("ttl", Integer.parseInt(split.length > 1 ? split[1] : "0"));
                    } else if (split[0].equals("type")) {
                        jSONObject.put("type", split.length > 1 ? split[1] : "");
                    } else if (split[0].equals("rr_data")) {
                        jSONObject.put("rr_data", split.length > 1 ? split[1] : "");
                    }
                } catch (Throwable unused2) {
                }
            }
            d.a().a(this, this.mResult, jSONObject.toString(), this.mReportAddr);
        }
    }

    public static boolean initialize(Context context) {
        initialize_native(context);
        return true;
    }

    @Override // com.netease.yunxin.artemis.Artemis.b
    public void taskRun() {
        initialize(this.mContext);
        this.mResult = dig(this.hostname);
    }
}
