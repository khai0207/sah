package com.unionpay.mobile.android.pboctransaction;

import android.os.Build;
import android.os.Bundle;
import com.android.pc.util.ThreeMap;
import com.iflytek.cloud.SpeechConstant;
import com.kaiqigu.cjyxyn.kvsdk.BuildConfig;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.ResultCode;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.df;

/* loaded from: classes.dex */
public final class d {
    c c;
    com.unionpay.mobile.android.fully.a d;
    private String f;
    private static Date l = new Date(System.currentTimeMillis());
    private static String m = new SimpleDateFormat("yyyyMMddhhmmss").format((java.util.Date) l);
    private static HashMap<String, String> o = new HashMap<>();
    public static String a = "A0000003330101010000000000010000";
    public static String b = "A0000003330101020001050001000000";
    private static d s = null;
    private byte g = 0;
    private byte h = 0;
    private byte i = 1;
    private boolean j = true;
    private boolean k = true;
    private String n = null;
    private final String p = "A0000003334355502D4D4F42494C45";
    private boolean q = false;
    public boolean e = false;
    private String r = "";

    public d(c cVar, com.unionpay.mobile.android.fully.a aVar, String str) {
        this.f = BuildConfig.VERSION_NAME;
        this.f = str;
        this.c = cVar;
        this.d = aVar;
    }

    private static String a(String str, String str2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (str == null) {
            return null;
        }
        byte[] a2 = e.a(str);
        int i10 = 0;
        while (i10 < a2.length) {
            int i11 = 1;
            int i12 = ((byte) (a2[i10] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i12];
            System.arraycopy(a2, i10, bArr, 0, i12);
            if (e.a(bArr, i12).compareToIgnoreCase(str2) == 0) {
                int i13 = i10 + i12;
                if (((byte) (a2[i13] & 128)) != Byte.MIN_VALUE) {
                    i4 = a2[i13];
                } else {
                    i11 = 1 + (a2[i13] & 127);
                    if (i11 != 2) {
                        if (i11 == 3) {
                            i2 = (a2[i13 + 1] & 255) << 8;
                            i3 = a2[i13 + 2];
                        } else {
                            if (i11 != 4) {
                                i = 0;
                                byte[] bArr2 = new byte[i];
                                System.arraycopy(a2, i13 + i11, bArr2, 0, i);
                                return e.a(bArr2, i);
                            }
                            i2 = ((a2[i13 + 1] & 255) << 16) | ((a2[i13 + 2] & 255) << 8);
                            i3 = a2[i13 + 3];
                        }
                        i = i2 | (i3 & 255);
                        byte[] bArr22 = new byte[i];
                        System.arraycopy(a2, i13 + i11, bArr22, 0, i);
                        return e.a(bArr22, i);
                    }
                    i4 = a2[i13 + 1];
                }
                i = i4 & 255;
                byte[] bArr222 = new byte[i];
                System.arraycopy(a2, i13 + i11, bArr222, 0, i);
                return e.a(bArr222, i);
            }
            int i14 = a2[i10] & 32;
            int i15 = i10 + i12;
            int length = a2.length;
            if (i14 != 32) {
                if (i15 >= length || ((byte) (a2[i15] & 128)) != 0) {
                    i11 = i15 < a2.length ? (a2[i15] & 127) + 1 : 0;
                    if (i11 != 2 || (i8 = i15 + 1) >= a2.length) {
                        i5 = (i11 != 3 || (i7 = i15 + 2) >= a2.length) ? (i11 != 4 || (i6 = i15 + 2) >= a2.length) ? 0 : ((a2[i6] & 255) << 8) | ((a2[i15 + 1] & 255) << 16) | (a2[i15 + 3] & 255) : (a2[i7] & 255) | ((a2[i15 + 1] & 255) << 8);
                        i11 += i5;
                    } else {
                        i9 = a2[i8];
                    }
                } else {
                    i9 = a2[i15];
                }
                i5 = i9 & 255;
                i11 += i5;
            } else if (i15 < length && ((byte) (a2[i15] & 128)) == Byte.MIN_VALUE) {
                i11 = 1 + (a2[i15] & 127);
            }
            i10 = i15 + i11;
        }
        return null;
    }

    private static String a(String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : str.toUpperCase().getBytes()) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
        }
        int length = (stringBuffer.length() / 2) + (stringBuffer.length() % 2);
        if (!z) {
            int i = length % 8;
            int i2 = i != 0 ? (8 - i) + length : length;
            byte[] bArr = new byte[i2];
            System.arraycopy(e.a(stringBuffer.toString()), 0, bArr, 0, length);
            return e.a(bArr, i2);
        }
        int i3 = length + 1;
        int i4 = i3 % 8;
        if (i4 != 0) {
            i3 += 8 - i4;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(e.a(stringBuffer.toString()), 0, bArr2, 0, length);
        bArr2[length] = Byte.MIN_VALUE;
        return e.a(bArr2, i3);
    }

    private String a(byte[] bArr) {
        byte b2 = bArr[0];
        byte b3 = this.g;
        bArr[0] = (byte) (b2 | b3);
        byte[] a2 = this.c.a(bArr, b3);
        int length = a2 != null ? a2.length : 0;
        if (length >= 2 && a2[length - 2] == 97) {
            byte b4 = a2[length - 1];
            byte b5 = this.g;
            a2 = this.c.a(new byte[]{b5, -64, 0, 0, b4}, b5);
            length = a2 != null ? a2.length : 0;
        }
        if (length >= 2 && a2[length - 2] == 108) {
            bArr[bArr.length - 1] = a2[length - 1];
            a2 = this.c.a(bArr, this.g);
            length = a2 != null ? a2.length : 0;
        }
        if (length > 2) {
            int i = length - 2;
            if (a2[i] == -112 && a2[length - 1] == 0) {
                return e.a(a2, i);
            }
        }
        if (length == 2 && a2[length - 2] == -112 && a2[length - 1] == 0) {
            return e.a(a2, 2);
        }
        return null;
    }

    private String a(byte[] bArr, String str) {
        bArr[bArr.length - 1] = (byte) (str.length() / 2);
        byte[] bArr2 = new byte[bArr.length + (str.length() / 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(e.a(str), 0, bArr2, bArr.length, str.length() / 2);
        return a(bArr2);
    }

    private static void a(String str, StringBuffer stringBuffer) {
        String str2 = o.get(str);
        String a2 = e.a(new byte[]{(byte) (str2.length() / 2)}, 1);
        stringBuffer.append(str);
        stringBuffer.append(a2);
        stringBuffer.append(str2);
    }

    private String b(String str) {
        c cVar = this.c;
        if (cVar instanceof com.unionpay.mobile.android.pboctransaction.samsung.f) {
            return cVar.a(str);
        }
        this.g = this.h;
        if (str == null) {
            return null;
        }
        return a(e.a("00a40400" + e.a(new byte[]{Integer.valueOf(str.length() / 2).byteValue()}) + str));
    }

    private void b(byte[] bArr) {
        int length = (m.length() / 2) + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(e.a(m), 0, bArr2, 0, m.length() / 2);
        bArr2[length - 1] = Byte.MIN_VALUE;
        bArr[bArr.length - 1] = (byte) length;
        byte[] bArr3 = new byte[bArr.length + length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, length);
        a(bArr3);
    }

    private String c(String str) {
        if (str == null || "9000".equals(str)) {
            StringBuffer stringBuffer = new StringBuffer("80A800000b8309");
            for (String str2 : i("9F7A019F02065F2A02")) {
                Iterator<String> it = o.keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        String next = it.next();
                        if (str2.compareToIgnoreCase(next) == 0) {
                            stringBuffer.append(o.get(next));
                            break;
                        }
                    }
                }
            }
            return a(e.a(stringBuffer.toString()));
        }
        j.c("uppay", "test for gongshang version 2");
        StringBuffer stringBuffer2 = new StringBuffer("");
        for (String str3 : i(a(str, "9F38"))) {
            Iterator<String> it2 = o.keySet().iterator();
            while (true) {
                if (it2.hasNext()) {
                    String next2 = it2.next();
                    if (str3.compareToIgnoreCase(next2) == 0) {
                        stringBuffer2.append(o.get(next2));
                        break;
                    }
                }
            }
        }
        byte[] a2 = e.a(stringBuffer2.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a2.length + 7);
        allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a2.length + 2)).put((byte) -125).put((byte) a2.length).put(a2);
        return a(allocate.array());
    }

    private String d(String str) {
        String a2 = a(str, "80");
        if (a2 == null) {
            return null;
        }
        int i = 4;
        o.put("82", a2.substring(0, 4));
        byte[] a3 = e.a(a2.substring(4));
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("5A");
        arrayList.add("5F34");
        arrayList.add("9F1F");
        arrayList.add("57");
        arrayList.add("5F24");
        arrayList.add("9F10");
        arrayList.add("8C");
        arrayList.add("8D");
        int i2 = 0;
        while (i2 < a3.length) {
            try {
                byte[] bArr = new byte[5];
                bArr[0] = 0;
                bArr[1] = -78;
                bArr[2] = 0;
                bArr[3] = 0;
                bArr[i] = 0;
                byte[] bArr2 = new byte[i];
                System.arraycopy(a3, i2, bArr2, 0, i);
                i2 += 4;
                byte b2 = bArr2[1];
                for (char c = 2; b2 <= bArr2[c]; c = 2) {
                    bArr[i] = 0;
                    bArr[3] = (byte) ((bArr2[0] & (-8)) | i);
                    bArr[c] = b2;
                    b2 = (byte) (b2 + 1);
                    try {
                        String a4 = a(bArr);
                        if (a4 != null) {
                            for (String str2 : arrayList) {
                                String a5 = a(a4, str2);
                                if (a5 != null) {
                                    o.put(str2, a5);
                                }
                            }
                        }
                        i = 4;
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        return o.get("8C");
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        StringBuffer stringBuffer = new StringBuffer(o.get("5F34"));
        stringBuffer.insert(0, "0");
        o.put("5F34", stringBuffer.toString());
        return o.get("8C");
    }

    private static void d() {
        String substring = m.substring(2, 8);
        long time = new Date(System.currentTimeMillis()).getTime();
        String valueOf = String.valueOf(time);
        String format = valueOf.length() < 8 ? String.format("%08d", Long.valueOf(time)) : valueOf.substring(valueOf.length() - 8, valueOf.length());
        o.put("9F26", "");
        o.put("9F27", "80");
        o.put("9F10", "");
        o.put("9F37", format);
        o.put("9F36", "");
        o.put("95", "0000000800");
        o.put("9A", substring);
        o.put("9C", "45");
        o.put("9F02", Constant.DEFAULT_BALANCE);
        o.put("5F2A", "0156");
        o.put("82", "");
        o.put("9F1A", "0156");
        o.put("9F03", Constant.DEFAULT_BALANCE);
        o.put("9F33", "A04000");
        o.put("9F34", "420300");
        o.put("9F35", "34");
        o.put("9F1E", "3030303030303030");
        o.put("84", "");
        o.put("9F09", ResultCode.ERROR_DETAIL_NETWORK);
        o.put("9F74", "");
        o.put("9F63", "");
        o.put("9F7A", "01");
        o.put("9F21", m.substring(8));
        o.put("9F4E", "0000000000000000000000000000000000000000");
        o.put("DF31", "0100000000");
        o.put("9F66", "36800000");
        o.put("DF60", "00");
    }

    private String e(String str) {
        StringBuffer stringBuffer = new StringBuffer("80AE800034");
        for (String str2 : i(str)) {
            Iterator<String> it = o.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    String next = it.next();
                    if (str2.compareToIgnoreCase(next) == 0) {
                        stringBuffer.append(o.get(next));
                        break;
                    }
                }
            }
        }
        String a2 = a(e.a(stringBuffer.toString()));
        if (a2 != null) {
            o.put("9F27", a2.substring(4, 6));
            o.put("9F36", a2.substring(6, 10));
            o.put("9F26", a2.substring(10, 26));
            o.put("9F10", a2.substring(26));
        }
        return a2;
    }

    private boolean e() {
        String str = o.get("5A");
        while (str.substring(str.length() - 1, str.length()).equalsIgnoreCase(ThreeMap.type_float)) {
            str = str.substring(0, str.length() - 1);
        }
        String f = f(str);
        if (f != null && f.length() != 0) {
            o.put("AN1", f);
            String f2 = f("00000001");
            if (f2 != null && f2.length() != 0) {
                o.put("TID", f2);
                String f3 = f(o.get("9F02"));
                if (f3 != null && f3.length() != 0) {
                    o.put("AMT", f3);
                    String f4 = f("156");
                    if (f4 != null && f4.length() != 0) {
                        o.put("CUR", f4);
                        String str2 = o.get("57");
                        while (true) {
                            if (!str2.substring(str2.length() - 1, str2.length()).equalsIgnoreCase(ThreeMap.type_float) && !str2.substring(str2.length() - 1, str2.length()).equalsIgnoreCase("F")) {
                                break;
                            }
                            str2 = str2.substring(0, str2.length() - 1);
                        }
                        String f5 = f(str2);
                        if (f5 == null || f5.length() == 0) {
                            return false;
                        }
                        o.put("TD2", f5);
                        if (o.get("5F24") != null && o.get("5F24").length() == 6) {
                            String f6 = f(o.get("5F24").substring(0, 4));
                            if (f6 == null || f6.length() == 0) {
                                return false;
                            }
                            o.put("ED", f6);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String f() {
        this.g = this.i;
        String a2 = a(new byte[]{0, -80, -126, 0, 10});
        if (a2 != null && a2.length() != 0) {
            o.put("CSN", a2);
        }
        this.g = this.h;
        return a2;
    }

    private String f(String str) {
        this.g = this.i;
        String a2 = a(str, false);
        b(new byte[]{Byte.MIN_VALUE, 26, 19, 1, 0});
        String a3 = a(new byte[]{Byte.MIN_VALUE, -6, 0, 0, 0}, a2);
        this.g = this.h;
        return a3;
    }

    private static Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private String g(String str) {
        this.g = this.i;
        byte[] bArr = {Byte.MIN_VALUE, 26, 20, 1, 0};
        byte[] bArr2 = {Byte.MIN_VALUE, -6, 0, 0, 0};
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", Integer.valueOf(str.length())));
        sb.append(str);
        String sb2 = sb.toString();
        StringBuffer stringBuffer = new StringBuffer(sb2);
        for (int i = 0; i < 16 - sb2.length(); i++) {
            stringBuffer.append("F");
        }
        b(bArr);
        String a2 = a(bArr2, stringBuffer.toString());
        if (a2 != null) {
            o.put("PIN", a2);
        }
        this.g = this.h;
        return a2;
    }

    private String h(String str) {
        this.g = this.i;
        byte[] bArr = {Byte.MIN_VALUE, -6, 1, 0, 0};
        String a2 = a(str, true);
        b(new byte[]{Byte.MIN_VALUE, 26, 21, 1, 8});
        while (a2.length() > 448) {
            bArr[2] = 3;
            a(bArr, a2.substring(0, 448).toUpperCase());
            a2 = a2.substring(448);
        }
        bArr[2] = 1;
        if (Build.VERSION.SDK_INT <= 10) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String a3 = a(bArr, a2);
        j.c("uppay", "encryptMac in resp" + a3);
        if (a3 != null) {
            o.put("MAC", a3.toUpperCase());
        }
        this.g = this.h;
        return a3 != null ? a3.toUpperCase() : a3;
    }

    private static List<String> i(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        byte[] a2 = e.a(str);
        int i = 0;
        while (i < a2.length) {
            int i2 = 1;
            int i3 = ((byte) (a2[i] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(a2, i, bArr, 0, i3);
            arrayList.add(e.a(bArr, i3));
            int i4 = i + i3;
            if (i4 < a2.length && ((byte) (a2[i4] & 128)) == Byte.MIN_VALUE) {
                i2 = 1 + (a2[i4] & 127);
            }
            i = i4 + i2;
        }
        return arrayList;
    }

    public final synchronized Bundle a(int i, String str, HashMap<String, String> hashMap, String str2) {
        j.c("uppay", "startUPCardPurchase() +++");
        Bundle g = g();
        String str3 = "";
        this.c.b();
        String a2 = a();
        if (a2 != null && a2.length() != 0) {
            j.c("uppay", " ************T1=" + m);
            o.put("PIN", str);
            String g2 = g(PreferenceUtils.decPrefData(o.get("PIN"), str2));
            if (g2 != null && g2.length() != 0) {
                j.c("uppay", " ************T2=" + m);
                String a3 = a(i, m);
                if (a3 != null && a3.length() != 0) {
                    String f = f();
                    if (f != null && f.length() != 0) {
                        String c = e.c(a3.substring(40, 60));
                        String substring = a3.substring(60, 100);
                        String substring2 = a3.substring(100, 208);
                        String substring3 = a3.substring(216, 232);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(ThreeMap.value, this.f);
                            jSONObject.put(SpeechConstant.ISV_CMD, "pay");
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject.put("params", jSONObject2);
                            jSONObject2.put("pay_type", "2");
                            jSONObject2.put("pay_mode", "1");
                            jSONObject2.put("bind", "no");
                            jSONObject2.put("carrier_tp", "1");
                            jSONObject2.put("track2_data", substring);
                            jSONObject2.put("track3_data", substring2);
                            jSONObject2.put("csn", o.get("CSN"));
                            jSONObject2.put("submit_time", m);
                            jSONObject2.put("sp_id", "8889");
                            jSONObject2.put(Constant.KEY_PIN, o.get("PIN"));
                            jSONObject2.put(Constant.KEY_PAN, c);
                            jSONObject2.put("dynamic_key_data", substring3);
                            jSONObject2.put("carrier_app_tp", "1");
                            if (hashMap != null && hashMap.keySet() != null && hashMap.keySet().size() > 0) {
                                hashMap.remove(Constant.KEY_PAN);
                                hashMap.remove(Constant.KEY_PIN);
                                for (String str4 : hashMap.keySet()) {
                                    jSONObject2.put(str4, hashMap.get(str4));
                                }
                            }
                            str3 = jSONObject.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        j.c("uppay", " ************T3=" + m);
                        g.putString("action_resp_message", this.d.a(str3));
                        this.c.c();
                        d();
                        return g;
                    }
                    this.c.c();
                    g.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
                    return g;
                }
                this.c.c();
                g.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
                return g;
            }
            this.c.c();
            g.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return g;
        }
        this.c.c();
        g.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
        return g;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x01a3 A[Catch: all -> 0x0524, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0053, B:9:0x0065, B:11:0x00ac, B:14:0x00b4, B:16:0x00d4, B:19:0x00dc, B:21:0x00fc, B:24:0x0104, B:26:0x0124, B:29:0x012b, B:31:0x014b, B:34:0x0152, B:36:0x0172, B:38:0x0199, B:40:0x01a3, B:43:0x01aa, B:45:0x01d8, B:48:0x01e0, B:50:0x01ed, B:53:0x0202, B:55:0x0278, B:57:0x0282, B:59:0x028c, B:61:0x0296, B:63:0x02a0, B:66:0x02ac, B:68:0x02c0, B:69:0x02cb, B:71:0x02d5, B:72:0x02e0, B:74:0x02ea, B:75:0x02f5, B:77:0x02ff, B:78:0x030a, B:80:0x0329, B:81:0x0334, B:83:0x0338, B:85:0x0342, B:86:0x034d, B:88:0x0357, B:89:0x0362, B:91:0x036c, B:92:0x0377, B:93:0x037d, B:95:0x0383, B:98:0x038b, B:103:0x03a0, B:105:0x03c4, B:108:0x03cc, B:110:0x0429, B:111:0x0436, B:113:0x0472, B:114:0x047f, B:116:0x0489, B:118:0x0498, B:120:0x049e, B:122:0x04a8, B:123:0x04ba, B:125:0x04c0, B:127:0x04ce, B:128:0x04d2, B:133:0x04ec, B:135:0x04fa, B:138:0x0508, B:141:0x0516, B:145:0x017c), top: B:2:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01aa A[Catch: all -> 0x0524, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0053, B:9:0x0065, B:11:0x00ac, B:14:0x00b4, B:16:0x00d4, B:19:0x00dc, B:21:0x00fc, B:24:0x0104, B:26:0x0124, B:29:0x012b, B:31:0x014b, B:34:0x0152, B:36:0x0172, B:38:0x0199, B:40:0x01a3, B:43:0x01aa, B:45:0x01d8, B:48:0x01e0, B:50:0x01ed, B:53:0x0202, B:55:0x0278, B:57:0x0282, B:59:0x028c, B:61:0x0296, B:63:0x02a0, B:66:0x02ac, B:68:0x02c0, B:69:0x02cb, B:71:0x02d5, B:72:0x02e0, B:74:0x02ea, B:75:0x02f5, B:77:0x02ff, B:78:0x030a, B:80:0x0329, B:81:0x0334, B:83:0x0338, B:85:0x0342, B:86:0x034d, B:88:0x0357, B:89:0x0362, B:91:0x036c, B:92:0x0377, B:93:0x037d, B:95:0x0383, B:98:0x038b, B:103:0x03a0, B:105:0x03c4, B:108:0x03cc, B:110:0x0429, B:111:0x0436, B:113:0x0472, B:114:0x047f, B:116:0x0489, B:118:0x0498, B:120:0x049e, B:122:0x04a8, B:123:0x04ba, B:125:0x04c0, B:127:0x04ce, B:128:0x04d2, B:133:0x04ec, B:135:0x04fa, B:138:0x0508, B:141:0x0516, B:145:0x017c), top: B:2:0x0001, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized android.os.Bundle a(com.unionpay.mobile.android.pboctransaction.AppIdentification r4, java.lang.String r5, java.lang.String r6, java.util.HashMap<java.lang.String, java.lang.String> r7, java.util.HashMap<java.lang.String, java.lang.String> r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 1321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.d.a(com.unionpay.mobile.android.pboctransaction.AppIdentification, java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap, java.lang.String):android.os.Bundle");
    }

    public final String a() {
        c cVar = this.c;
        if (cVar instanceof com.unionpay.mobile.android.pboctransaction.samsung.f) {
            return cVar.a("A0000003334355502D4D4F42494C45");
        }
        this.g = this.i;
        return a(new byte[]{0, -92, 4, 0, df.m, -96, 0, 0, 3, 51, 67, 85, 80, 45, 77, 79, 66, 73, 76, 69});
    }

    public final String a(int i, String str) {
        String hexString;
        this.g = this.i;
        if (Integer.toHexString(i).length() == 1) {
            hexString = "0" + Integer.toHexString(i);
        } else {
            hexString = Integer.toHexString(i);
        }
        return a(e.a("80F802" + hexString + "08" + str + "80"));
    }

    public final synchronized String a(AppIdentification appIdentification) {
        String a2 = appIdentification.a();
        d();
        b(a2);
        String c = c(null);
        if (c != null && c.length() != 0) {
            String d = d(c);
            if (d != null && d.length() != 0) {
                return o.get("5A");
            }
            return null;
        }
        return null;
    }

    public final String a(String str) {
        this.c.d();
        String b2 = b(str);
        this.c.d();
        return a(b2, "84");
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> b() {
        this.c.d();
        this.c.b();
        ArrayList<com.unionpay.mobile.android.model.c> a2 = this.c.a(this);
        this.c.c();
        return a2;
    }

    public final String c() {
        this.g = this.i;
        return a(e.a("80F2000102"));
    }
}
