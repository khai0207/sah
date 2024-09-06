package com.netease.nimlib.o;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.netease.nimlib.sdk.ServerAddresses;
import java.util.UUID;

/* compiled from: StringUtil.java */
/* loaded from: classes.dex */
public class w {
    public static String b(String str) {
        return str == null ? "" : str;
    }

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }

    public static boolean b(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }

    public static String a(String str) {
        if (a((CharSequence) str)) {
            return null;
        }
        return str;
    }

    public static String a() {
        return UUID.randomUUID().toString();
    }

    public static String b() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return "";
        }
        int i = lastIndexOf + 1;
        for (int i2 = i; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && (charAt < '0' || charAt > '9'))) {
                return "";
            }
        }
        return str.substring(i, str.length());
    }

    public static String d(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String c = c(str.toLowerCase());
        String mimeTypeFromExtension = TextUtils.isEmpty(c) ? "" : MimeTypeMap.getSingleton().getMimeTypeFromExtension(c);
        return (a((CharSequence) mimeTypeFromExtension) && str.endsWith("aac")) ? "audio/aac" : mimeTypeFromExtension;
    }

    public static boolean f(String str) {
        return !TextUtils.isEmpty(str) && str.split("/").length == 2;
    }

    public static String a(ServerAddresses serverAddresses) {
        if (serverAddresses == null) {
            return "ServerConfig: null";
        }
        return "ServerAddresses{module='" + serverAddresses.module + "', publicKeyVersion=" + serverAddresses.publicKeyVersion + ", lbs='" + serverAddresses.lbs + "', lbsBackup=" + serverAddresses.lbsBackup + ", defaultLink='" + serverAddresses.defaultLink + "', defaultLinkBackup=" + serverAddresses.defaultLinkBackup + ", nosUploadLbs='" + serverAddresses.nosUploadLbs + "', nosUploadDefaultLink='" + serverAddresses.nosUploadDefaultLink + "', nosUpload='" + serverAddresses.nosUpload + "', nosSupportHttps=" + serverAddresses.nosSupportHttps + ", nosDownloadUrlFormat='" + serverAddresses.nosDownloadUrlFormat + "', nosDownload='" + serverAddresses.nosDownload + "', nosAccess='" + serverAddresses.nosAccess + "', ntServerAddress='" + serverAddresses.ntServerAddress + "', bdServerAddress='" + serverAddresses.bdServerAddress + "', test=" + serverAddresses.test + ", dedicatedClusteFlag=" + serverAddresses.dedicatedClusteFlag + ", negoKeyNeca=" + serverAddresses.negoKeyNeca + ", negoKeyEncaKeyVersion=" + serverAddresses.negoKeyEncaKeyVersion + ", negoKeyEncaKeyParta='" + serverAddresses.negoKeyEncaKeyParta + "', negoKeyEncaKeyPartb='" + serverAddresses.negoKeyEncaKeyPartb + "', commEnca=" + serverAddresses.commEnca + ", linkIpv6='" + serverAddresses.linkIpv6 + "', ipProtocolVersion=" + serverAddresses.ipProtocolVersion + ", probeIpv4Url='" + serverAddresses.probeIpv4Url + "', probeIpv6Url='" + serverAddresses.probeIpv6Url + "', handshakeType=" + serverAddresses.handshakeType + ", nosCdnEnable=" + serverAddresses.nosCdnEnable + ", nosDownloadSet=" + serverAddresses.nosDownloadSet + '}';
    }

    public static String g(String str) {
        int lastIndexOf;
        return (str == null || str.isEmpty() || (lastIndexOf = str.lastIndexOf(46)) == -1) ? str : str.substring(lastIndexOf + 1);
    }
}
