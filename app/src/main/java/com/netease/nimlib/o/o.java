package com.netease.nimlib.o;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.netease.nimlib.sdk.ServerAddresses;
import com.unionpay.tsmservice.data.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NetPing.java */
/* loaded from: classes.dex */
public class o {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private a b = null;

    private o() {
    }

    public static o a() {
        return new o();
    }

    public void b() {
        if (!p.b(com.netease.nimlib.c.e())) {
            com.netease.nimlib.log.b.d("NetPing", "unable to start ping, as network is unavailable");
            return;
        }
        if (a.get()) {
            com.netease.nimlib.log.b.d("NetPing", "cancel start ping, as net ping is running...");
        } else {
            if (this.b != null) {
                return;
            }
            a aVar = new a();
            this.b = aVar;
            aVar.execute(new Void[0]);
            a.set(true);
        }
    }

    public static void c() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("network info: ");
            sb.append("net_type=" + h() + ", net_available=" + p.b(com.netease.nimlib.c.e()) + ", net_connected=" + p.c(com.netease.nimlib.c.e()));
            com.netease.nimlib.log.b.d("NetPing", sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: NetPing.java */
    /* loaded from: classes.dex */
    private static class a extends AsyncTask<Void, Void, Void> {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            List<String> g = o.g();
            if (g != null && g.size() != 0) {
                com.netease.nimlib.log.b.d("NetPing", "***** Net ping start, host list size=" + g.size() + " *****");
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                for (String str : g) {
                    com.netease.nimlib.log.b.d("NetPing", "ping host " + str);
                    JSONObject e = o.e(o.d(str));
                    if (e != null) {
                        try {
                            e.put("ip", str);
                            jSONArray.put(e);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    com.netease.nimlib.log.b.d("NetPing", "ping host result=" + e);
                }
                try {
                    jSONObject.put("net_type", o.h());
                    Object obj = "true";
                    jSONObject.put("net_available", p.b(com.netease.nimlib.c.e()) ? "true" : "false");
                    if (!p.c(com.netease.nimlib.c.e())) {
                        obj = "false";
                    }
                    jSONObject.put("net_connected", obj);
                    jSONObject.put("result", jSONArray);
                    com.netease.nimlib.log.b.d("NetPing", "***** Net ping end, total result=" + jSONObject.toString() + " ***** ");
                } catch (Exception e3) {
                    e3.printStackTrace();
                    com.netease.nimlib.log.b.e("NetPing", "NetPing get network status error", e3);
                }
                o.a.set(false);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> g() {
        ArrayList arrayList = new ArrayList();
        for (String str : com.netease.nimlib.net.a.b.a.a.a().b()) {
            String c = c(str);
            if (!arrayList.contains(c)) {
                arrayList.add(c);
            }
        }
        ServerAddresses l = com.netease.nimlib.c.l();
        if (l == null) {
            arrayList.add(c(com.netease.nimlib.d.g.j()));
            arrayList.add(c(com.netease.nimlib.d.g.l()));
            arrayList.add(c("www.163.com"));
        } else {
            if (!TextUtils.isEmpty(l.nosUpload)) {
                arrayList.add(c(l.nosUpload));
            }
            if (!TextUtils.isEmpty(l.nosDownload)) {
                arrayList.add(c(l.nosDownload));
            }
        }
        return arrayList;
    }

    private static String c(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            Process start = new ProcessBuilder("/system/bin/ping", "-c", Constant.APPLY_MODE_DECIDED_BY_BANK, "-w", "15", str).start();
            if (start.waitFor() == 0) {
                sb.append("ping " + str + " success\n");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine + "\n");
                }
            } else {
                com.netease.nimlib.log.b.d("NetPing", "unable to ping host, try socket connect...");
                boolean a2 = a(str, 0);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("connect ");
                sb2.append(a2 ? Constant.CASH_LOAD_SUCCESS : com.alipay.sdk.m.q.g.j);
                sb2.append("\n");
                sb.append(sb2.toString());
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("NetPing", "ping host error", th);
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.StringBuilder] */
    private static boolean a(String str, int i) {
        boolean z;
        if (i == 0) {
            i = 80;
        }
        ?? socket = new Socket();
        try {
            try {
                socket.connect(new InetSocketAddress(str, i), 15000);
                z = socket.isConnected();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                z = false;
            }
            socket = new StringBuilder();
            socket.append("socket connect ");
            socket.append(str);
            socket.append(z ? " success" : " failed!!!");
            com.netease.nimlib.log.b.d("NetPing", socket.toString());
            return z;
        } finally {
            try {
                socket.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject e(String str) {
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
                if (str.contains("connect") && str.contains(Constant.CASH_LOAD_SUCCESS)) {
                    jSONObject.put("result", "connect success");
                } else if (str.contains(com.alipay.sdk.m.q.g.j)) {
                    jSONObject.put("result", com.alipay.sdk.m.q.g.j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.e("NetPing", "getPingStatus error", e);
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h() {
        int j = p.j(com.netease.nimlib.c.e());
        return j == 1 ? "2g" : j == 2 ? "3g" : j == 3 ? "4g" : j == 10 ? "wifi" : EnvironmentCompat.MEDIA_UNKNOWN;
    }
}
