package com.netease.nimlib;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.SecondTimeoutConfig;
import com.netease.nimlib.sdk.ServerAddresses;
import com.netease.nimlib.sdk.mixpush.MixPushConfig;
import com.netease.nimlib.sdk.msg.model.CaptureDeviceInfoConfig;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SDKOptionsInitPush.java */
/* loaded from: classes.dex */
public class f implements Serializable {
    public static final f a = new f();
    public SecondTimeoutConfig K;
    public String L;
    public String b;
    public String d;
    public String e;
    public ServerAddresses j;
    public MixPushConfig r;
    public boolean v;
    public String x;
    public boolean c = false;
    public boolean f = true;
    public int g = 350;
    public boolean h = false;
    public boolean i = true;
    public boolean k = true;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;
    public boolean s = true;
    public boolean t = true;

    /* renamed from: u, reason: collision with root package name */
    public boolean f23u = false;
    public boolean w = true;
    public boolean y = false;
    public long z = 2000;
    public boolean A = false;
    public String B = "";
    public boolean C = false;
    public boolean D = false;
    public int E = PathInterpolatorCompat.MAX_NUM_POINTS;
    public boolean F = false;
    public int G = -1;
    public boolean H = false;
    public boolean I = false;
    public CaptureDeviceInfoConfig J = new CaptureDeviceInfoConfig(true, true, true);
    public boolean M = false;
    public boolean N = false;

    public static f a(SDKOptions sDKOptions) {
        if (sDKOptions == null) {
            return null;
        }
        f fVar = new f();
        fVar.b = sDKOptions.appKey;
        fVar.c = sDKOptions.useAssetServerAddressConfig;
        fVar.d = sDKOptions.sdkStorageRootPath;
        fVar.f = sDKOptions.preloadAttach;
        fVar.g = sDKOptions.thumbnailSize;
        fVar.h = sDKOptions.sessionReadAck;
        fVar.i = sDKOptions.improveSDKProcessPriority;
        fVar.j = sDKOptions.serverConfig;
        fVar.k = sDKOptions.preLoadServers;
        fVar.l = sDKOptions.teamNotificationMessageMarkUnread;
        fVar.m = sDKOptions.useXLog;
        fVar.n = sDKOptions.animatedImageThumbnailEnabled;
        fVar.o = sDKOptions.asyncInitSDK;
        fVar.p = sDKOptions.reducedIM;
        fVar.q = sDKOptions.checkManifestConfig;
        fVar.r = sDKOptions.mixPushConfig;
        fVar.s = sDKOptions.enableBackOffReconnectStrategy;
        fVar.t = sDKOptions.enableLBSOptimize;
        fVar.f23u = sDKOptions.enableTeamMsgAck;
        fVar.v = sDKOptions.shouldConsiderRevokedMessageUnreadCount;
        fVar.x = sDKOptions.loginCustomTag;
        fVar.y = sDKOptions.disableAwake;
        fVar.z = sDKOptions.fetchServerTimeInterval;
        fVar.A = sDKOptions.reportImLog;
        fVar.B = sDKOptions.customPushContentType;
        fVar.C = sDKOptions.notifyStickTopSession;
        fVar.D = sDKOptions.enableForegroundService;
        fVar.E = sDKOptions.cdnRequestDataInterval;
        fVar.F = sDKOptions.rollbackSQLCipher;
        fVar.G = sDKOptions.coreProcessStartTimeout;
        fVar.H = sDKOptions.clearTimeTagAtBeginning;
        fVar.I = sDKOptions.enableDatabaseBackup;
        fVar.J = sDKOptions.captureDeviceInfoConfig;
        fVar.K = sDKOptions.secondTimeoutForSendMessage;
        fVar.L = sDKOptions.flutterSdkVersion;
        fVar.N = sDKOptions.enableLoseConnection;
        fVar.M = sDKOptions.consoleLogEnabled;
        return fVar;
    }

    public static SDKOptions a(f fVar) {
        if (fVar == null) {
            return null;
        }
        SDKOptions sDKOptions = new SDKOptions();
        sDKOptions.appKey = fVar.b;
        sDKOptions.useAssetServerAddressConfig = fVar.c;
        sDKOptions.sdkStorageRootPath = fVar.d;
        sDKOptions.preloadAttach = fVar.f;
        sDKOptions.thumbnailSize = fVar.g;
        sDKOptions.sessionReadAck = fVar.h;
        sDKOptions.improveSDKProcessPriority = fVar.i;
        sDKOptions.serverConfig = fVar.j;
        sDKOptions.preLoadServers = fVar.k;
        sDKOptions.teamNotificationMessageMarkUnread = fVar.l;
        sDKOptions.useXLog = fVar.m;
        sDKOptions.animatedImageThumbnailEnabled = fVar.n;
        sDKOptions.asyncInitSDK = fVar.o;
        sDKOptions.reducedIM = fVar.p;
        sDKOptions.checkManifestConfig = fVar.q;
        sDKOptions.mixPushConfig = fVar.r;
        sDKOptions.enableBackOffReconnectStrategy = fVar.s;
        sDKOptions.enableLBSOptimize = fVar.t;
        sDKOptions.enableTeamMsgAck = fVar.f23u;
        sDKOptions.shouldConsiderRevokedMessageUnreadCount = fVar.v;
        sDKOptions.loginCustomTag = fVar.x;
        sDKOptions.disableAwake = fVar.y;
        sDKOptions.fetchServerTimeInterval = fVar.z;
        sDKOptions.reportImLog = fVar.A;
        sDKOptions.customPushContentType = fVar.B;
        sDKOptions.notifyStickTopSession = fVar.C;
        sDKOptions.enableForegroundService = fVar.D;
        sDKOptions.cdnRequestDataInterval = fVar.E;
        sDKOptions.rollbackSQLCipher = fVar.F;
        sDKOptions.coreProcessStartTimeout = fVar.G;
        sDKOptions.clearTimeTagAtBeginning = fVar.H;
        sDKOptions.enableDatabaseBackup = fVar.I;
        sDKOptions.captureDeviceInfoConfig = fVar.J;
        sDKOptions.secondTimeoutForSendMessage = fVar.K;
        sDKOptions.flutterSdkVersion = fVar.L;
        sDKOptions.enableLoseConnection = fVar.N;
        sDKOptions.consoleLogEnabled = fVar.M;
        return sDKOptions;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(com.alipay.sdk.m.h.b.h, this.b);
            jSONObject.putOpt("use_asset_server_address_config", Boolean.valueOf(this.c));
            jSONObject.putOpt("sdk_storage_root_path", this.d);
            jSONObject.putOpt("database_encrypt_key", this.e);
            jSONObject.putOpt("preload_attach", Boolean.valueOf(this.f));
            jSONObject.putOpt("thumbnail_size", Integer.valueOf(this.g));
            jSONObject.putOpt("session_read_ack", Boolean.valueOf(this.h));
            jSONObject.putOpt("improve_sdk_process_priority", Boolean.valueOf(this.i));
            JSONObject jSONObject2 = null;
            jSONObject.putOpt("server_config", this.j == null ? null : this.j.toJson());
            jSONObject.putOpt("pre_load_servers", Boolean.valueOf(this.k));
            jSONObject.putOpt("team_notification_message_mark_unread", Boolean.valueOf(this.l));
            jSONObject.putOpt("use_x_log", Boolean.valueOf(this.m));
            jSONObject.putOpt("animated_image_thumbnail_enabled", Boolean.valueOf(this.n));
            jSONObject.putOpt("async_init_sdk", Boolean.valueOf(this.o));
            jSONObject.putOpt("reduced_im", Boolean.valueOf(this.p));
            jSONObject.putOpt("check_manifest_config", Boolean.valueOf(this.q));
            jSONObject.putOpt("mix_push_config", this.r == null ? null : this.r.toJson());
            jSONObject.putOpt("enable_back_off_reconnect_strategy", Boolean.valueOf(this.s));
            jSONObject.putOpt("enable_lbs_optimize", Boolean.valueOf(this.t));
            jSONObject.putOpt("enable_team_msg_ack", Boolean.valueOf(this.f23u));
            jSONObject.putOpt("should_consider_revoked_message_unread_count", Boolean.valueOf(this.v));
            jSONObject.putOpt("use_nt_server", Boolean.valueOf(this.w));
            jSONObject.putOpt("login_custom_tag", this.x);
            jSONObject.putOpt("disable_awake", Boolean.valueOf(this.y));
            jSONObject.putOpt("fetch_server_time_interval", Long.valueOf(this.z));
            jSONObject.putOpt("report_im_log", Boolean.valueOf(this.A));
            jSONObject.putOpt("custom_push_content_type", this.B);
            jSONObject.putOpt("notify_stick_top_session", Boolean.valueOf(this.C));
            jSONObject.putOpt("enable_foreground_service", Boolean.valueOf(this.D));
            jSONObject.putOpt("cdn_request_data_interval", Integer.valueOf(this.E));
            jSONObject.putOpt("rollback_sql_cipher", Boolean.valueOf(this.F));
            jSONObject.putOpt("core_process_start_timeout", Integer.valueOf(this.G));
            jSONObject.putOpt("clear_time_tag_at_beginning", Boolean.valueOf(this.H));
            jSONObject.putOpt("enable_database_backup", Boolean.valueOf(this.I));
            jSONObject.putOpt("capture_device_info_config", this.J == null ? null : this.J.toJson());
            if (this.K != null) {
                jSONObject2 = this.K.toJson();
            }
            jSONObject.putOpt("second_timeout_for_send_message", jSONObject2);
            jSONObject.putOpt("flutter_sdk_version", this.L);
            jSONObject.putOpt("enable_lose_connection", Boolean.valueOf(this.N));
            jSONObject.putOpt("console_log_enabled", Boolean.valueOf(this.M));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static f a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        f fVar = new f();
        fVar.b = jSONObject.optString(com.alipay.sdk.m.h.b.h, null);
        fVar.c = jSONObject.optBoolean("use_asset_server_address_config", false);
        fVar.d = jSONObject.optString("sdk_storage_root_path", null);
        fVar.e = jSONObject.optString("database_encrypt_key", null);
        fVar.f = jSONObject.optBoolean("preload_attach", true);
        fVar.g = jSONObject.optInt("thumbnail_size", 350);
        fVar.h = jSONObject.optBoolean("session_read_ack", false);
        fVar.i = jSONObject.optBoolean("improve_sdk_process_priority", true);
        fVar.j = ServerAddresses.fromJson(jSONObject.optJSONObject("server_config"));
        fVar.k = jSONObject.optBoolean("pre_load_servers", true);
        fVar.l = jSONObject.optBoolean("team_notification_message_mark_unread", false);
        fVar.m = jSONObject.optBoolean("use_x_log", false);
        fVar.n = jSONObject.optBoolean("animated_image_thumbnail_enabled", false);
        fVar.o = jSONObject.optBoolean("async_init_sdk", false);
        fVar.p = jSONObject.optBoolean("reduced_im", false);
        fVar.q = jSONObject.optBoolean("check_manifest_config", false);
        fVar.r = MixPushConfig.fromJson(jSONObject.optJSONObject("mix_push_config"));
        fVar.s = jSONObject.optBoolean("enable_back_off_reconnect_strategy", true);
        fVar.t = jSONObject.optBoolean("enable_lbs_optimize", true);
        fVar.f23u = jSONObject.optBoolean("enable_team_msg_ack", false);
        fVar.v = jSONObject.optBoolean("should_consider_revoked_message_unread_count", false);
        fVar.w = jSONObject.optBoolean("use_nt_server", true);
        fVar.x = jSONObject.optString("login_custom_tag", null);
        fVar.y = jSONObject.optBoolean("disable_awake", false);
        fVar.z = jSONObject.optLong("fetch_server_time_interval", 2000L);
        fVar.A = jSONObject.optBoolean("report_im_log", false);
        fVar.B = jSONObject.optString("custom_push_content_type", "");
        fVar.C = jSONObject.optBoolean("notify_stick_top_session", false);
        fVar.D = jSONObject.optBoolean("enable_foreground_service", false);
        fVar.E = jSONObject.optInt("cdn_request_data_interval", PathInterpolatorCompat.MAX_NUM_POINTS);
        fVar.F = jSONObject.optBoolean("rollback_sql_cipher", false);
        fVar.G = jSONObject.optInt("core_process_start_timeout", -1);
        fVar.H = jSONObject.optBoolean("clear_time_tag_at_beginning", false);
        fVar.I = jSONObject.optBoolean("enable_database_backup", false);
        fVar.J = CaptureDeviceInfoConfig.fromJson(jSONObject.optJSONObject("capture_device_info_config"));
        fVar.K = SecondTimeoutConfig.fromJson(jSONObject.optJSONObject("second_timeout_for_send_message"));
        fVar.L = jSONObject.optString("flutter_sdk_version");
        fVar.N = jSONObject.optBoolean("enable_lose_connection");
        fVar.M = jSONObject.optBoolean("console_log_enabled");
        return fVar;
    }
}
