package com.netease.nimlib.m;

import com.netease.nimlib.m.d;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NtpIpcUtils.java */
/* loaded from: classes.dex */
public class e {
    public static synchronized void a(boolean z) {
        synchronized (e.class) {
            com.netease.nimlib.ipc.e.c(b(z));
        }
    }

    public static synchronized void a(short s, long j) {
        synchronized (e.class) {
            com.netease.nimlib.ipc.e.c(c(s, j));
        }
    }

    public static synchronized void b(short s, long j) {
        synchronized (e.class) {
            com.netease.nimlib.ipc.e.c(d(s, j));
        }
    }

    private static String b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("KEY_IPC_EVENT_TYPE", 0);
            jSONObject.put("KEY_NTP_REFRESH_FORCE", z);
        } catch (JSONException e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.d("NtpUtils", String.format("ipcNotifyHeartBeatJsonString JSONException %s", e));
        }
        return jSONObject.toString();
    }

    private static String c(short s, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("KEY_IPC_EVENT_TYPE", 1);
            jSONObject.put("KEY_PROTOCOL_SERIAL_ID", (int) s);
            jSONObject.put("KEY_REQUEST_SENT_TIMESTAMP", j);
        } catch (JSONException e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.d("NtpUtils", String.format("ipcRequestSentJsonString timestamp %s JSONException %s", Long.valueOf(j), e));
        }
        return jSONObject.toString();
    }

    private static String d(short s, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("KEY_IPC_EVENT_TYPE", 2);
            jSONObject.put("KEY_PROTOCOL_SERIAL_ID", (int) s);
            jSONObject.put("KEY_RESPONSE_RECEIVED_TIMESTAMP", j);
        } catch (JSONException e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.d("NtpUtils", String.format("ipcResponseReceivedJsonString timestamp %s JSONException %s", Long.valueOf(j), e));
        }
        return jSONObject.toString();
    }

    public static synchronized boolean a(d dVar, String str) {
        synchronized (e.class) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("KEY_IPC_EVENT_TYPE", -1);
                if (optInt == -1) {
                    com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s eventType invalid %s", str, Integer.valueOf(optInt)));
                    return false;
                }
                if (optInt == 0) {
                    boolean optBoolean = jSONObject.optBoolean("KEY_NTP_REFRESH_FORCE", false);
                    dVar.a((d.a) null, optBoolean);
                    if (optBoolean) {
                        dVar.e();
                    }
                    return true;
                }
                short optInt2 = (short) jSONObject.optInt("KEY_PROTOCOL_SERIAL_ID", -1);
                if (optInt2 <= -1) {
                    com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s serial invalid %s", str, Short.valueOf(optInt2)));
                    return false;
                }
                if (optInt == 1) {
                    long optLong = jSONObject.optLong("KEY_REQUEST_SENT_TIMESTAMP", -1L);
                    if (optLong <= -1) {
                        com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s requestSentTimestamp invalid %s", str, Long.valueOf(optLong)));
                        return false;
                    }
                    dVar.a(optInt2, optLong);
                    return true;
                }
                if (optInt == 2) {
                    long optLong2 = jSONObject.optLong("KEY_RESPONSE_RECEIVED_TIMESTAMP", -1L);
                    if (optLong2 <= -1) {
                        com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s responseReceivedTimestamp invalid %s", str, Long.valueOf(optLong2)));
                        return false;
                    }
                    dVar.b(optInt2, optLong2);
                    return true;
                }
                com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s no match case", str));
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e("NtpUtils", String.format("handleNtpIpcEvent %s %s", str, e), e);
                return false;
            }
        }
    }
}
