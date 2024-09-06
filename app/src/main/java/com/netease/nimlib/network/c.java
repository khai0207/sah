package com.netease.nimlib.network;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.unionpay.tsmservice.data.Constant;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.json.JSONObject;

/* compiled from: NetworkCheckTask.java */
/* loaded from: classes.dex */
public class c implements Runnable {
    private volatile boolean a = false;
    private volatile boolean b = false;
    private b c;
    private String d;
    private Pair<String, Integer> e;
    private int f;
    private int g;

    public c(String str, int i, String str2, int i2, int i3, b bVar) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 1;
        this.g = 3;
        this.d = str;
        this.e = new Pair<>(str2, Integer.valueOf(i2));
        if (i > 1) {
            this.f = i;
        }
        if (i3 > 3) {
            this.g = i3;
        }
        this.c = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b) {
            return;
        }
        this.a = true;
        try {
            c();
        } catch (Throwable th) {
            com.netease.nimlib.log.b.c("NetworkCheckTask", "checkNetwork error", th);
        }
        this.a = false;
    }

    public boolean a() {
        return this.a;
    }

    public void b() {
        this.b = true;
        this.c = null;
        this.a = false;
    }

    private void c() {
        boolean a = a(this.d);
        Pair<String, Integer> pair = this.e;
        if (pair == null) {
            a(a);
            return;
        }
        if (!a) {
            a = a((String) pair.first, ((Integer) this.e.second).intValue());
        }
        a(a);
    }

    private void a(boolean z) {
        b bVar = this.c;
        if (bVar != null) {
            bVar.onNetworkCheckResult(z);
        }
    }

    private boolean a(String str, int i) {
        boolean z;
        if (i == 0) {
            i = 80;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.netease.nimlib.log.b.d("NetworkCheckTask", "telnet start host: " + str + " port: " + i);
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(str, i), this.g * 1000);
            z = socket.isConnected();
        } catch (Throwable th) {
            try {
                com.netease.nimlib.log.b.e("NetworkCheckTask", "telnet error", th);
                try {
                    socket.close();
                } catch (Throwable th2) {
                    com.netease.nimlib.log.b.e("NetworkCheckTask", "telnet close error", th2);
                }
                z = false;
            } finally {
                try {
                    socket.close();
                } catch (Throwable th3) {
                    com.netease.nimlib.log.b.e("NetworkCheckTask", "telnet close error", th3);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("telnet connect ");
        sb.append(str);
        sb.append(":");
        sb.append(i);
        sb.append(z ? " success" : " failed!!!");
        sb.append(" cost: ");
        sb.append(SystemClock.elapsedRealtime() - elapsedRealtime);
        com.netease.nimlib.log.b.d("NetworkCheckTask", sb.toString());
        return z;
    }

    private boolean a(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.netease.nimlib.log.b.d("NetworkCheckTask", "ping start host: " + str);
        StringBuilder sb = new StringBuilder();
        JSONObject jSONObject = null;
        try {
            Process start = new ProcessBuilder("/system/bin/ping", "-c", "1", "-w", this.f + "", str).start();
            int waitFor = start.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + "\n");
            }
            if (waitFor == 0) {
                try {
                    sb.append("ping " + str + " success\n");
                    z = true;
                } catch (Throwable th) {
                    th = th;
                    z = true;
                    com.netease.nimlib.log.b.e("NetworkCheckTask", "ping host error", th);
                    com.netease.nimlib.log.b.d("NetworkCheckTask", "ping result: " + z + " cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " content = " + jSONObject);
                    return z;
                }
            } else {
                sb.append("ping " + str + " failed\n");
            }
            jSONObject = b(sb.toString());
        } catch (Throwable th2) {
            th = th2;
        }
        com.netease.nimlib.log.b.d("NetworkCheckTask", "ping result: " + z + " cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " content = " + jSONObject);
        return z;
    }

    private JSONObject b(String str) {
        int indexOf;
        int indexOf2;
        JSONObject jSONObject = new JSONObject();
        try {
            if (str.contains("0% packet loss")) {
                int indexOf3 = str.indexOf("/mdev = ");
                if (indexOf3 == -1 || (indexOf2 = str.indexOf(" ms\n", indexOf3)) == -1) {
                    return null;
                }
                String[] split = str.substring(indexOf3 + 8, indexOf2).split("/");
                if (split.length != 4) {
                    return null;
                }
                jSONObject.put("min", Double.valueOf(split[0]));
                jSONObject.put("avg", Double.valueOf(split[1]));
                jSONObject.put("max", Double.valueOf(split[2]));
                jSONObject.put("mdev", Double.valueOf(split[3]));
                jSONObject.put("loss", 0);
            } else if (str.contains("100% packet loss")) {
                jSONObject.put("loss", 100);
            } else {
                if (str.contains("% packet loss")) {
                    int indexOf4 = str.indexOf("/mdev = ");
                    if (indexOf4 == -1 || (indexOf = str.indexOf(" ms\n", indexOf4)) == -1) {
                        return null;
                    }
                    String substring = str.substring(indexOf4 + 8, indexOf);
                    String[] split2 = substring.split("/");
                    if (split2.length != 4) {
                        return null;
                    }
                    int indexOf5 = substring.indexOf("% packet loss");
                    int indexOf6 = substring.indexOf("received");
                    if (indexOf5 != -1 && indexOf6 != -1) {
                        String trim = substring.substring(indexOf6 + 10, indexOf5).trim();
                        jSONObject.put("min", Double.valueOf(split2[0]));
                        jSONObject.put("avg", Double.valueOf(split2[1]));
                        jSONObject.put("max", Double.valueOf(split2[2]));
                        jSONObject.put("mdev", Double.valueOf(split2[3]));
                        jSONObject.put("loss", Integer.valueOf(trim));
                    }
                    return null;
                }
                if (str.contains("ping") && str.contains(Constant.CASH_LOAD_SUCCESS)) {
                    jSONObject.put("result", "ping success");
                } else if (str.contains(com.alipay.sdk.m.q.g.j)) {
                    jSONObject.put("result", com.alipay.sdk.m.q.g.j);
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetworkCheckTask", "getPingStatus error", th);
        }
        return jSONObject;
    }
}
