package com.ipaynow.wechatpay.plugin.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PreSignMessageUtil {
    public String appId = null;
    public String mhtOrderNo = null;
    public String mhtOrderName = null;
    public String mhtOrderType = null;
    public String mhtCurrencyType = null;
    public String mhtOrderAmt = null;
    public String mhtOrderDetail = null;
    public String mhtOrderTimeOut = null;
    public String mhtOrderStartTime = null;
    public String notifyUrl = null;
    public String mhtCharset = null;
    public String payChannelType = null;
    public String mhtReserved = null;
    private String outputType = null;
    public String mhtSubAppId = null;
    public String mhtLimitPay = null;
    public String consumerId = null;
    public String consumerName = null;

    private static String createLinkString(HashMap hashMap) {
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = (String) hashMap.get(str);
            int size = arrayList.size() - 1;
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            if (i != size) {
                sb.append(com.alipay.sdk.m.o.a.l);
            }
        }
        return sb.toString();
    }

    private boolean optionalParamsFilter(String str) {
        return com.ipaynow.wechatpay.plugin.c.c.v.contains(str);
    }

    public String generatePreSignMessage() {
        com.ipaynow.wechatpay.plugin.manager.a.a.r().b(true);
        HashMap hashMap = new HashMap();
        try {
            for (Field field : PreSignMessageUtil.class.getDeclaredFields()) {
                String name = field.getName();
                if (!g.y((String) field.get(this))) {
                    hashMap.put(field.getName(), (String) field.get(this));
                } else if (!optionalParamsFilter(name)) {
                    com.ipaynow.wechatpay.plugin.manager.a.a.r().g(false);
                    return "缺少必填参数=====" + name + "=====";
                }
            }
            String createLinkString = createLinkString(hashMap);
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(true);
            return createLinkString;
        } catch (IllegalAccessException unused) {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(false);
            return "";
        } catch (IllegalArgumentException unused2) {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().g(false);
            return "";
        }
    }
}
