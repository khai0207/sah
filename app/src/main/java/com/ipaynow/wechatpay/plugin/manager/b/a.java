package com.ipaynow.wechatpay.plugin.manager.b;

import com.ipaynow.wechatpay.plugin.utils.g;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static String a(String str, String str2, com.ipaynow.wechatpay.plugin.g.b.a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", str);
            jSONObject.put("appIp", aVar.ad());
            jSONObject.put("appName", aVar.Z());
            jSONObject.put("appPackage", aVar.aa());
            jSONObject.put("appSignature", aVar.ac());
            jSONObject.put(Constant.KEY_APP_VERSION, aVar.ab());
            jSONObject.put("channelType", str2);
            jSONObject.put("mobileType", aVar.X());
            jSONObject.put("networkType", aVar.W());
            jSONObject.put("phoneDeviceinfo", aVar.S());
            jSONObject.put("phoneMacaddr", aVar.R());
            jSONObject.put("phoneOsVersion", aVar.T());
            jSONObject.put("phoneSystem", aVar.P());
            jSONObject.put("phoneUniquekey", aVar.Q());
            jSONObject.put("pluginType", str2);
            jSONObject.put("pluginVersion", "2.0.0");
            jSONObject.put("rootFlag", aVar.Y());
            jSONObject.put("screenDensity", aVar.V());
            jSONObject.put("screenResolution", aVar.U());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public static String a(HashMap hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String str2 = (String) hashMap.get(str);
            if (!Constant.KEY_SIGNATURE.equals(str) && !Constants.NULL_VERSION_ID.equals(str2) && !g.y(str2)) {
                sb.append(String.valueOf(str) + "=" + str2 + com.alipay.sdk.m.o.a.l);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("funcode", "SK003");
            jSONObject.put("version", "1.0");
            jSONObject.put("appId", str);
            jSONObject.put("mhtOrderNo", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String e(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("funcode", "SK002");
            jSONObject.put("version", "1.0");
            jSONObject.put("mhtOrderNo", com.ipaynow.wechatpay.plugin.manager.a.a.r().t());
            jSONObject.put("mhtCharset", "UTF-8");
            jSONObject.put("exceptionInfo", str2);
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new String();
        }
    }

    public static String f(String str) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("funcode", "SK001");
            jSONObject.put("version", "1.0");
            jSONObject.put("deviceType", "01");
            jSONObject.put("deviceInfo", r.u());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap g(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return hashMap;
        }
    }
}
