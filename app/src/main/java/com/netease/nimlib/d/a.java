package com.netease.nimlib.d;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.netease.nimlib.o.k;
import com.netease.nimlib.push.net.lbs.IPVersion;
import com.netease.nimlib.push.packet.asymmetric.AsymmetricType;
import com.netease.nimlib.push.packet.symmetry.SymmetryType;
import com.netease.nimlib.sdk.NimHandshakeType;
import com.netease.nimlib.sdk.ServerAddresses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.json.JSONObject;

/* compiled from: PrivatizationConfig.java */
/* loaded from: classes.dex */
public class a {
    private static String a;

    public static ServerAddresses a() {
        return d();
    }

    public static String b() {
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.StringBuffer] */
    /* JADX WARN: Type inference failed for: r0v8 */
    private static String c() {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        ?? r0 = "server.conf";
        AssetManager assets = com.netease.nimlib.c.e().getAssets();
        BufferedReader bufferedReader = null;
        try {
            try {
            } catch (IOException e) {
                e = e;
                stringBuffer = null;
            }
            if (!Arrays.asList(assets.list("")).contains("server.conf")) {
                return null;
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(assets.open("server.conf")));
            try {
                try {
                    stringBuffer2 = new StringBuffer();
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer2.append(readLine);
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            stringBuffer = stringBuffer2;
                            e.printStackTrace();
                            r0 = stringBuffer;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                r0 = stringBuffer;
                            }
                            return r0.toString();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    stringBuffer2 = null;
                }
                if (TextUtils.isEmpty(stringBuffer2)) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                    return null;
                }
                bufferedReader2.close();
                r0 = stringBuffer2;
                return r0.toString();
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static ServerAddresses d() {
        ServerAddresses serverAddresses = new ServerAddresses();
        String c = c();
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        JSONObject a2 = k.a(c);
        if (a2 == null) {
            return serverAddresses;
        }
        serverAddresses.handshakeType = NimHandshakeType.value(a2.optInt("hand_shake_type", NimHandshakeType.V1.getValue()));
        serverAddresses.module = a2.optString("module");
        serverAddresses.publicKeyVersion = a2.optInt("version", 0);
        serverAddresses.lbs = a2.optString("lbs");
        serverAddresses.defaultLink = a2.optString("link");
        serverAddresses.nosUploadLbs = a2.optString("nos_lbs");
        serverAddresses.nosUploadDefaultLink = a2.optString("nos_uploader");
        serverAddresses.nosUpload = a2.optString("nos_uploader_host");
        serverAddresses.nosSupportHttps = a2.optBoolean("https_enabled");
        serverAddresses.nosDownloadUrlFormat = a2.optString("nos_downloader");
        serverAddresses.nosDownload = a2.optString("nos_accelerate_host");
        serverAddresses.nosDownloadSet = com.netease.nimlib.o.f.a(a2.optJSONArray("nos_accelerate_host_list"));
        serverAddresses.nosCdnEnable = a2.optBoolean("nos_cdn_enable");
        serverAddresses.nosAccess = a2.optString("nos_accelerate");
        serverAddresses.ntServerAddress = a2.optString("nt_server");
        serverAddresses.dedicatedClusteFlag = a2.optInt("dedicated_cluste_flag");
        serverAddresses.negoKeyNeca = AsymmetricType.value(a2.optInt("nego_key_neca", AsymmetricType.RSA.getValue()));
        serverAddresses.negoKeyEncaKeyVersion = a2.optInt("nego_key_enca_key_version");
        serverAddresses.negoKeyEncaKeyParta = a2.optString("nego_key_enca_key_parta");
        serverAddresses.negoKeyEncaKeyPartb = a2.optString("nego_key_enca_key_partb");
        serverAddresses.commEnca = SymmetryType.value(a2.optInt("comm_enca", SymmetryType.RC4.getValue()));
        serverAddresses.linkIpv6 = a2.optString("link_ipv6");
        serverAddresses.ipProtocolVersion = IPVersion.value(a2.optInt("ip_protocol_version", IPVersion.IPV4.getValue()));
        serverAddresses.probeIpv4Url = a2.optString("probe_ipv4_url");
        serverAddresses.probeIpv6Url = a2.optString("probe_ipv6_url");
        a = a2.optString(com.alipay.sdk.m.o.a.p);
        a(serverAddresses);
        return serverAddresses;
    }

    private static void a(ServerAddresses serverAddresses) {
        serverAddresses.module = TextUtils.isEmpty(serverAddresses.module) ? null : serverAddresses.module;
        serverAddresses.lbs = TextUtils.isEmpty(serverAddresses.lbs) ? null : serverAddresses.lbs;
        serverAddresses.defaultLink = TextUtils.isEmpty(serverAddresses.defaultLink) ? null : serverAddresses.defaultLink;
        serverAddresses.nosUploadLbs = TextUtils.isEmpty(serverAddresses.nosUploadLbs) ? null : serverAddresses.nosUploadLbs;
        serverAddresses.nosUploadDefaultLink = TextUtils.isEmpty(serverAddresses.nosUploadDefaultLink) ? null : serverAddresses.nosUploadDefaultLink;
        serverAddresses.nosUpload = TextUtils.isEmpty(serverAddresses.nosUpload) ? null : serverAddresses.nosUpload;
        serverAddresses.nosDownloadUrlFormat = TextUtils.isEmpty(serverAddresses.nosDownloadUrlFormat) ? null : serverAddresses.nosDownloadUrlFormat;
        serverAddresses.nosDownload = TextUtils.isEmpty(serverAddresses.nosDownload) ? null : serverAddresses.nosDownload;
        serverAddresses.nosAccess = TextUtils.isEmpty(serverAddresses.nosAccess) ? null : serverAddresses.nosAccess;
        serverAddresses.ntServerAddress = TextUtils.isEmpty(serverAddresses.ntServerAddress) ? null : serverAddresses.ntServerAddress;
        serverAddresses.negoKeyEncaKeyParta = TextUtils.isEmpty(serverAddresses.negoKeyEncaKeyParta) ? null : serverAddresses.negoKeyEncaKeyParta;
        serverAddresses.negoKeyEncaKeyPartb = TextUtils.isEmpty(serverAddresses.negoKeyEncaKeyPartb) ? null : serverAddresses.negoKeyEncaKeyPartb;
        serverAddresses.linkIpv6 = TextUtils.isEmpty(serverAddresses.linkIpv6) ? null : serverAddresses.linkIpv6;
        serverAddresses.probeIpv4Url = TextUtils.isEmpty(serverAddresses.probeIpv4Url) ? null : serverAddresses.probeIpv4Url;
        serverAddresses.probeIpv6Url = TextUtils.isEmpty(serverAddresses.probeIpv6Url) ? null : serverAddresses.probeIpv6Url;
        a = TextUtils.isEmpty(a) ? null : a;
    }
}
