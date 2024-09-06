package com.talkingdata.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* loaded from: classes.dex */
final class ci extends BroadcastReceiver {
    ArrayList b;
    JSONArray c;
    bu d;
    bu e;
    private WifiManager j;
    bv a = new bv();
    double f = 0.0d;
    long g = 0;
    long h = 0;
    private long i = 180000;

    public ci(WifiManager wifiManager) {
        this.j = wifiManager;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        cc.a.post(new cj(this));
    }

    public void a() {
        try {
            cn cnVar = new cn();
            cnVar.b = "env";
            cnVar.c = "wifiUpdate";
            cnVar.a = a.e;
            bk.a().post(cnVar);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
    }

    public bu b() {
        try {
            this.d = a(this.c);
        } catch (Throwable th) {
            ce.postSDKError(th);
        }
        return this.d;
    }

    public bu c() {
        try {
            ArrayList arrayList = (ArrayList) this.j.getScanResults();
            this.b = arrayList;
            if (arrayList != null) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < this.b.size(); i++) {
                        if (((ScanResult) this.b.get(i)).level >= -75) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("SSID", ((ScanResult) this.b.get(i)).SSID);
                            jSONObject.put("BSSID", ((ScanResult) this.b.get(i)).BSSID);
                            jSONObject.put("level", ((ScanResult) this.b.get(i)).level);
                            jSONArray.put(jSONObject);
                        }
                    }
                    this.c = jSONArray;
                    this.e = a(jSONArray);
                } catch (Throwable th) {
                    ce.postSDKError(th);
                }
            }
        } catch (Throwable th2) {
            ce.postSDKError(th2);
        }
        return this.e;
    }

    private bu a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(new bq(jSONObject.getString("SSID"), jSONObject.getString("BSSID"), (byte) jSONObject.getInt("level"), (byte) 0, (byte) 0));
            } catch (Throwable th) {
                ce.postSDKError(th);
            }
        }
        bu buVar = new bu();
        buVar.setBsslist(arrayList);
        return buVar;
    }
}
