package com.netease.nimlib.sdk;

import com.netease.nimlib.o.f;
import com.netease.nimlib.push.net.lbs.IPVersion;
import com.netease.nimlib.push.packet.asymmetric.AsymmetricType;
import com.netease.nimlib.push.packet.symmetry.SymmetryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ServerAddresses implements Serializable {
    public static final String KEY_BD_SERVER_ADDRESS = "KEY_BD_SERVER_ADDRESS";
    public static final String KEY_COMM_ENCA = "KEY_COMM_ENCA";
    public static final String KEY_DEDICATED_CLUSTE_FLAG = "KEY_DEDICATED_CLUSTE_FLAG";
    public static final String KEY_DEFAULT_LINK = "KEY_DEFAULT_LINK";
    public static final String KEY_DEFAULT_LINK_BACKUP = "KEY_DEFAULT_LINK_BACKUP";
    public static final String KEY_HANDSHAKE_TYPE = "KEY_HANDSHAKE_TYPE";
    public static final String KEY_IP_PROTOCOL_VERSION = "KEY_IP_PROTOCOL_VERSION";
    public static final String KEY_LBS = "KEY_LBS";
    public static final String KEY_LBS_BACKUP = "KEY_LBS_BACKUP";
    public static final String KEY_LINK_IPV6 = "KEY_LINK_IPV6";
    public static final String KEY_MODULE = "KEY_MODULE";
    public static final String KEY_NEGO_KEY_ENCA_KEY_PARTA = "KEY_NEGO_KEY_ENCA_KEY_PARTA";
    public static final String KEY_NEGO_KEY_ENCA_KEY_PARTB = "KEY_NEGO_KEY_ENCA_KEY_PARTB";
    public static final String KEY_NEGO_KEY_ENCA_KEY_VERSION = "KEY_NEGO_KEY_ENCA_KEY_VERSION";
    public static final String KEY_NEGO_KEY_NECA = "KEY_NEGO_KEY_NECA";
    public static final String KEY_NOS_ACCESS = "KEY_NOS_ACCESS";
    public static final String KEY_NOS_CDN_ENABLE = "KEY_NOS_CDN_ENABLE";
    public static final String KEY_NOS_DOWNLOAD = "KEY_NOS_DOWNLOAD";
    public static final String KEY_NOS_DOWNLOAD_SET = "KEY_NOS_DOWNLOAD_SET";
    public static final String KEY_NOS_DOWNLOAD_URL_FORMAT = "KEY_NOS_DOWNLOAD_URL_FORMAT";
    public static final String KEY_NOS_SUPPORT_HTTPS = "KEY_NOS_SUPPORT_HTTPS";
    public static final String KEY_NOS_UPLOAD = "KEY_NOS_UPLOAD";
    public static final String KEY_NOS_UPLOAD_DEFAULT_LINK = "KEY_NOS_UPLOAD_DEFAULT_LINK";
    public static final String KEY_NOS_UPLOAD_LBS = "KEY_NOS_UPLOAD_LBS";
    public static final String KEY_NT_SERVER_ADDRESS = "KEY_NT_SERVER_ADDRESS";
    public static final String KEY_PROBE_IPV4_URL = "KEY_PROBE_IPV4_URL";
    public static final String KEY_PROBE_IPV6_URL = "KEY_PROBE_IPV6_URL";
    public static final String KEY_PUBLIC_KEY_VERSION = "KEY_PUBLIC_KEY_VERSION";
    public static final String KEY_TEST = "KEY_TEST";
    public String bdServerAddress;
    public int dedicatedClusteFlag;
    public String defaultLink;
    public List<String> defaultLinkBackup;
    public String lbs;
    public List<String> lbsBackup;
    public String linkIpv6;
    public String module;
    public String negoKeyEncaKeyParta;
    public String negoKeyEncaKeyPartb;
    public int negoKeyEncaKeyVersion;
    public String nosAccess;
    public String nosDownload;
    public Set<String> nosDownloadSet;
    public String nosDownloadUrlFormat;
    public String nosUpload;
    public String nosUploadDefaultLink;
    public String nosUploadLbs;
    public String ntServerAddress;
    public String probeIpv4Url;
    public String probeIpv6Url;
    public int publicKeyVersion = 0;
    public boolean nosSupportHttps = true;
    public boolean test = false;
    public AsymmetricType negoKeyNeca = AsymmetricType.RSA;
    public SymmetryType commEnca = SymmetryType.RC4;
    public IPVersion ipProtocolVersion = IPVersion.IPV4;
    public NimHandshakeType handshakeType = NimHandshakeType.V1;
    public boolean nosCdnEnable = true;

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_MODULE, this.module);
            jSONObject.putOpt(KEY_PUBLIC_KEY_VERSION, Integer.valueOf(this.publicKeyVersion));
            jSONObject.putOpt(KEY_LBS, this.lbs);
            jSONObject.putOpt(KEY_LBS_BACKUP, f.a(this.lbsBackup, ";"));
            jSONObject.putOpt(KEY_DEFAULT_LINK, this.defaultLink);
            jSONObject.putOpt(KEY_DEFAULT_LINK_BACKUP, f.a(this.defaultLinkBackup, ";"));
            jSONObject.putOpt(KEY_NOS_UPLOAD_LBS, this.nosUploadLbs);
            jSONObject.putOpt(KEY_NOS_UPLOAD_DEFAULT_LINK, this.nosUploadDefaultLink);
            jSONObject.putOpt(KEY_NOS_UPLOAD, this.nosUpload);
            jSONObject.putOpt(KEY_NOS_SUPPORT_HTTPS, Boolean.valueOf(this.nosSupportHttps));
            jSONObject.putOpt(KEY_NOS_DOWNLOAD_URL_FORMAT, this.nosDownloadUrlFormat);
            jSONObject.putOpt(KEY_NOS_DOWNLOAD, this.nosDownload);
            jSONObject.putOpt(KEY_NOS_ACCESS, this.nosAccess);
            jSONObject.putOpt(KEY_NT_SERVER_ADDRESS, this.ntServerAddress);
            jSONObject.putOpt(KEY_BD_SERVER_ADDRESS, this.bdServerAddress);
            jSONObject.putOpt(KEY_TEST, Boolean.valueOf(this.test));
            jSONObject.putOpt(KEY_DEDICATED_CLUSTE_FLAG, Integer.valueOf(this.dedicatedClusteFlag));
            jSONObject.putOpt(KEY_NEGO_KEY_NECA, Integer.valueOf((this.negoKeyNeca == null ? AsymmetricType.RSA : this.negoKeyNeca).getValue()));
            jSONObject.putOpt(KEY_NEGO_KEY_ENCA_KEY_VERSION, Integer.valueOf(this.negoKeyEncaKeyVersion));
            jSONObject.putOpt(KEY_NEGO_KEY_ENCA_KEY_PARTA, this.negoKeyEncaKeyParta);
            jSONObject.putOpt(KEY_NEGO_KEY_ENCA_KEY_PARTB, this.negoKeyEncaKeyPartb);
            jSONObject.putOpt(KEY_COMM_ENCA, Integer.valueOf((this.commEnca == null ? SymmetryType.RC4 : this.commEnca).getValue()));
            jSONObject.putOpt(KEY_LINK_IPV6, this.linkIpv6);
            jSONObject.putOpt(KEY_IP_PROTOCOL_VERSION, Integer.valueOf((this.ipProtocolVersion == null ? IPVersion.IPV4 : this.ipProtocolVersion).getValue()));
            jSONObject.putOpt(KEY_PROBE_IPV4_URL, this.probeIpv4Url);
            jSONObject.putOpt(KEY_PROBE_IPV6_URL, this.probeIpv6Url);
            jSONObject.putOpt(KEY_HANDSHAKE_TYPE, Integer.valueOf((this.handshakeType == null ? NimHandshakeType.V1 : this.handshakeType).getValue()));
            jSONObject.putOpt(KEY_NOS_CDN_ENABLE, Boolean.valueOf(this.nosCdnEnable));
            jSONObject.putOpt(KEY_NOS_DOWNLOAD_SET, f.a(this.nosDownloadSet, ";"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static ServerAddresses fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ServerAddresses serverAddresses = new ServerAddresses();
        serverAddresses.module = jSONObject.optString(KEY_MODULE);
        serverAddresses.publicKeyVersion = jSONObject.optInt(KEY_PUBLIC_KEY_VERSION);
        serverAddresses.lbs = jSONObject.optString(KEY_LBS);
        serverAddresses.lbsBackup = new ArrayList(Arrays.asList(jSONObject.optString(KEY_LBS_BACKUP).split(";")));
        serverAddresses.defaultLink = jSONObject.optString(KEY_DEFAULT_LINK);
        serverAddresses.defaultLinkBackup = new ArrayList(Arrays.asList(jSONObject.optString(KEY_DEFAULT_LINK_BACKUP).split(";")));
        serverAddresses.nosUploadLbs = jSONObject.optString(KEY_NOS_UPLOAD_LBS);
        serverAddresses.nosUploadDefaultLink = jSONObject.optString(KEY_NOS_UPLOAD_DEFAULT_LINK);
        serverAddresses.nosUpload = jSONObject.optString(KEY_NOS_UPLOAD);
        serverAddresses.nosSupportHttps = jSONObject.optBoolean(KEY_NOS_SUPPORT_HTTPS);
        serverAddresses.nosDownloadUrlFormat = jSONObject.optString(KEY_NOS_DOWNLOAD_URL_FORMAT);
        serverAddresses.nosDownload = jSONObject.optString(KEY_NOS_DOWNLOAD);
        serverAddresses.nosAccess = jSONObject.optString(KEY_NOS_ACCESS);
        serverAddresses.ntServerAddress = jSONObject.optString(KEY_NT_SERVER_ADDRESS);
        serverAddresses.bdServerAddress = jSONObject.optString(KEY_BD_SERVER_ADDRESS);
        serverAddresses.test = jSONObject.optBoolean(KEY_TEST);
        serverAddresses.dedicatedClusteFlag = jSONObject.optInt(KEY_DEDICATED_CLUSTE_FLAG);
        serverAddresses.negoKeyNeca = AsymmetricType.value(jSONObject.optInt(KEY_NEGO_KEY_NECA));
        serverAddresses.negoKeyEncaKeyVersion = jSONObject.optInt(KEY_NEGO_KEY_ENCA_KEY_VERSION);
        serverAddresses.negoKeyEncaKeyParta = jSONObject.optString(KEY_NEGO_KEY_ENCA_KEY_PARTA);
        serverAddresses.negoKeyEncaKeyPartb = jSONObject.optString(KEY_NEGO_KEY_ENCA_KEY_PARTB);
        serverAddresses.commEnca = SymmetryType.value(jSONObject.optInt(KEY_COMM_ENCA));
        serverAddresses.linkIpv6 = jSONObject.optString(KEY_LINK_IPV6);
        serverAddresses.ipProtocolVersion = IPVersion.value(jSONObject.optInt(KEY_IP_PROTOCOL_VERSION));
        serverAddresses.probeIpv4Url = jSONObject.optString(KEY_PROBE_IPV4_URL);
        serverAddresses.probeIpv6Url = jSONObject.optString(KEY_PROBE_IPV6_URL);
        serverAddresses.handshakeType = NimHandshakeType.value(jSONObject.optInt(KEY_HANDSHAKE_TYPE));
        serverAddresses.nosCdnEnable = jSONObject.optBoolean(KEY_NOS_CDN_ENABLE);
        serverAddresses.nosDownloadSet = new HashSet(Arrays.asList(jSONObject.optString(KEY_NOS_DOWNLOAD_SET).split(";")));
        return serverAddresses;
    }
}
