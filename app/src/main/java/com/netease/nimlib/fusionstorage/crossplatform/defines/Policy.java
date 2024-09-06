package com.netease.nimlib.fusionstorage.crossplatform.defines;

import com.netease.nimlib.fusionstorage.crossplatform.a;
import com.netease.nimlib.log.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Policy {
    private static final String TAG = "Policy";
    private final AuthPolicy authPolicy;
    private final String cdnSchema;
    private final List<String> downloadCdnList;
    private final long invalidToken;
    private final int priority;
    private final int provider;
    private final List<String> replacedCdnList;
    private final ThumbPolicy thumbPolicy;
    private final UploadConfig uploadConfig;
    private final int uploadMode;

    public Policy(int i, String str, List<String> list, List<String> list2, long j, int i2, UploadConfig uploadConfig, ThumbPolicy thumbPolicy, AuthPolicy authPolicy, int i3) {
        this.provider = i;
        this.cdnSchema = str;
        this.replacedCdnList = list;
        this.downloadCdnList = list2;
        this.invalidToken = j;
        this.uploadMode = i2;
        this.uploadConfig = uploadConfig;
        this.thumbPolicy = thumbPolicy;
        this.authPolicy = authPolicy;
        this.priority = i3;
    }

    public int getProvider() {
        return this.provider;
    }

    public String getCdnSchema() {
        return this.cdnSchema;
    }

    public List<String> getReplacedCdnList() {
        return this.replacedCdnList;
    }

    public List<String> getDownloadCdnList() {
        return this.downloadCdnList;
    }

    public long getInvalidToken() {
        return this.invalidToken;
    }

    public int getUploadMode() {
        return this.uploadMode;
    }

    public UploadConfig getUploadConfig() {
        return this.uploadConfig;
    }

    public ThumbPolicy getThumbPolicy() {
        return this.thumbPolicy;
    }

    public AuthPolicy getAuthPolicy() {
        return this.authPolicy;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        return "Policy{provider=" + this.provider + ", cdnSchema='" + this.cdnSchema + "', replacedCdnList=" + this.replacedCdnList + ", downloadCdnList=" + this.downloadCdnList + ", invalidToken=" + this.invalidToken + ", uploadMode=" + this.uploadMode + ", uploadConfig=" + this.uploadConfig + ", thumbPolicy=" + this.thumbPolicy + ", authPolicy=" + this.authPolicy + ", priority=" + this.priority + '}';
    }

    public static Policy fromJson(String str, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("cdnSchema");
            ArrayList arrayList = new ArrayList();
            arrayList.add(jSONObject.getString("dlcdn"));
            ArrayList arrayList2 = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("dlcdns");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList2.add(jSONArray.getString(i2));
            }
            long j = jSONObject.getLong("invalidToken");
            int b = a.b(jSONObject.optInt("type", 1));
            JSONObject jSONObject2 = jSONObject.getJSONObject("uploadConfig");
            JSONArray optJSONArray = jSONObject2.optJSONArray("uploadUrl");
            ArrayList arrayList3 = new ArrayList();
            if (optJSONArray != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList3.add(optJSONArray.getString(i3));
                }
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject("retryPolicy");
            UploadConfig uploadConfig = new UploadConfig(arrayList3, new RetryStrategy(jSONObject3.getInt("retry"), jSONObject3.getInt("circuit"), jSONObject3.getBoolean("retryNext")));
            JSONObject jSONObject4 = jSONObject.getJSONObject("thumbPolicy");
            ThumbPolicy thumbPolicy = new ThumbPolicy(jSONObject4.getString("imagethumb"), jSONObject4.getString("vframe"));
            JSONObject jSONObject5 = jSONObject.getJSONObject("authPolicy");
            return new Policy(i, string, arrayList, arrayList2, j, b, uploadConfig, thumbPolicy, new AuthPolicy(jSONObject5.getBoolean("authEnable"), Integer.parseInt(jSONObject5.getString("policyType").split(",")[0])), jSONObject.getInt("priority"));
        } catch (Exception e) {
            b.e(TAG, String.format("fromJson: %s", str), e);
            return null;
        }
    }
}
