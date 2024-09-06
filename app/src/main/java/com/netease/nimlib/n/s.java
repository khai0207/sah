package com.netease.nimlib.n;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.SDKOptions;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.List;
import org.json.JSONObject;

/* compiled from: UILoginEventManager.java */
/* loaded from: classes.dex */
public class s {
    private final Handler a = com.netease.nimlib.c.b.a.c().a("event_thread");
    private com.netease.nimlib.n.e.e b;

    /* compiled from: UILoginEventManager.java */
    /* loaded from: classes.dex */
    private static class a {
        private static final s a = new s();
    }

    public static s a() {
        return a.a;
    }

    public void a(final String str) {
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$s$y6_qf-X19MfwfCVYhbKjQZYqcEY
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str) {
        try {
            com.netease.nimlib.n.e.e eVar = new com.netease.nimlib.n.e.e();
            eVar.a(com.netease.nimlib.n.f.a.a());
            eVar.a(str);
            eVar.c("manual_login");
            eVar.d(w.b());
            eVar.a(c(eVar));
            this.b = eVar;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "startTrackLoginEvent Exception", th);
        }
    }

    public void a(final String str, final String str2) {
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$s$qeQGigadyX94nf2ZorB6uoU5B8k
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b(str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2) {
        try {
            if (C$r8$backportedMethods$utility$Objects$2$equals.equals(str, str2)) {
                return;
            }
            com.netease.nimlib.apm.a.a(this.b, com.netease.nimlib.n.c.i.a(0, "switch_key", str, "currentAppKey = " + str2 + ",oldAppKey = " + str, 0L, true));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "updateSwitchKey Exception", th);
        }
    }

    public void a(final com.netease.nimlib.n.c.i iVar) {
        if (iVar == null) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$s$kC2NhtnKsvF8bZf_5ZFAsiqQB8k
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b(iVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(com.netease.nimlib.n.c.i iVar) {
        try {
            com.netease.nimlib.apm.a.a(this.b, iVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "receivePushEventExtension Exception", th);
        }
    }

    public void a(final com.netease.nimlib.n.e.e eVar) {
        if (eVar == null) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$s$4XIz3ChsHcZsTVExejpeKuBrfi0
            @Override // java.lang.Runnable
            public final void run() {
                s.this.d(eVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(com.netease.nimlib.n.e.e eVar) {
        try {
            if (eVar.s()) {
                b(eVar);
                com.netease.nimlib.apm.a.a("login", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) eVar);
            } else {
                com.netease.nimlib.apm.a.a(this.b, eVar);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "receivePushLoginEvent Exception", th);
        }
    }

    private void a(com.netease.nimlib.n.e.e eVar, int i) {
        if (eVar == null) {
            return;
        }
        try {
            com.netease.nimlib.n.c.i g = com.netease.nimlib.n.c.i.g();
            g.a(i == 200);
            g.a(i);
            g.c("2_2");
            if (i != 200) {
                Context e = com.netease.nimlib.c.e();
                if (e != null) {
                    g.d(String.format("UI Process login response error, isNetAvailable: %s isNetworkConnected: %s", Boolean.valueOf(com.netease.nimlib.o.p.b(e)), Boolean.valueOf(com.netease.nimlib.network.f.a(e))));
                } else {
                    g.d("UI Process login response error, context is null");
                }
            }
            g.b("protocol");
            g.a(eVar.b());
            g.b(c(eVar));
            List<com.netease.nimlib.n.c.i> l = eVar.l();
            if (com.netease.nimlib.o.f.d(l)) {
                for (com.netease.nimlib.n.c.i iVar : l) {
                    if (TextUtils.equals(iVar.e(), "protocol")) {
                        com.netease.nimlib.log.b.e("UILoginEventManager", String.format("loginResponse skip as exist old %s new %s", iVar, g));
                        return;
                    }
                }
            }
            com.netease.nimlib.apm.a.a(eVar, g);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "loginResponseFailed Exception", th);
        }
    }

    public void a(final int i) {
        this.a.post(new Runnable() { // from class: com.netease.nimlib.n.-$$Lambda$s$eDO3Le-EbmukkZL38BFlH-t_ESQ
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i) {
        com.netease.nimlib.n.e.e eVar = this.b;
        this.b = null;
        boolean z = i == 200;
        if (eVar == null) {
            return;
        }
        try {
            eVar.b(z);
            eVar.b(c(eVar));
            a(eVar, i);
            b(eVar);
            com.netease.nimlib.apm.a.a("login", (com.netease.nimlib.apm.b.b<? extends com.netease.nimlib.apm.b.a>) eVar);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "stopTrackLoginEvent Exception", th);
        }
    }

    private void b(com.netease.nimlib.n.e.e eVar) {
        if (eVar == null) {
            return;
        }
        if (com.netease.nimlib.c.H()) {
            com.netease.nimlib.apm.a.a(eVar, com.netease.nimlib.n.c.i.a(0, "lazy_init", null, "lazy init", 0L, true));
        }
        com.netease.nimlib.apm.a.a(eVar, com.netease.nimlib.n.c.i.a(0, "conf_init", null, b(), 0L, true));
    }

    private String b() {
        SDKOptions i = com.netease.nimlib.c.i();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(com.alipay.sdk.m.h.b.h, i.appKey);
            jSONObject.putOpt("use_asset_server_address_config", Boolean.valueOf(i.useAssetServerAddressConfig));
            jSONObject.putOpt("sdk_storage_root_path", i.sdkStorageRootPath);
            jSONObject.putOpt("database_encrypt_key", com.netease.nimlib.log.b.a.a(i.databaseEncryptKey));
            jSONObject.putOpt("preload_attach", Boolean.valueOf(i.preloadAttach));
            jSONObject.putOpt("thumbnail_size", Integer.valueOf(i.thumbnailSize));
            jSONObject.putOpt("session_read_ack", Boolean.valueOf(i.sessionReadAck));
            jSONObject.putOpt("improve_sdk_process_priority", Boolean.valueOf(i.improveSDKProcessPriority));
            JSONObject jSONObject2 = null;
            jSONObject.putOpt("server_config", i.serverConfig == null ? null : i.serverConfig.toJson());
            jSONObject.putOpt("pre_load_servers", Boolean.valueOf(i.preLoadServers));
            jSONObject.putOpt("team_notification_message_mark_unread", Boolean.valueOf(i.teamNotificationMessageMarkUnread));
            jSONObject.putOpt("use_x_log", Boolean.valueOf(i.useXLog));
            jSONObject.putOpt("animated_image_thumbnail_enabled", Boolean.valueOf(i.animatedImageThumbnailEnabled));
            jSONObject.putOpt("async_init_sdk", Boolean.valueOf(i.asyncInitSDK));
            jSONObject.putOpt("reduced_im", Boolean.valueOf(i.reducedIM));
            jSONObject.putOpt("check_manifest_config", Boolean.valueOf(i.checkManifestConfig));
            jSONObject.putOpt("mix_push_config", i.mixPushConfig == null ? null : i.mixPushConfig.toJson());
            jSONObject.putOpt("enable_back_off_reconnect_strategy", Boolean.valueOf(i.enableBackOffReconnectStrategy));
            jSONObject.putOpt("enable_lbs_optimize", Boolean.valueOf(i.enableLBSOptimize));
            jSONObject.putOpt("enable_team_msg_ack", Boolean.valueOf(i.enableTeamMsgAck));
            jSONObject.putOpt("should_consider_revoked_message_unread_count", Boolean.valueOf(i.shouldConsiderRevokedMessageUnreadCount));
            jSONObject.putOpt("use_nt_server", Boolean.valueOf(i.useNtServer));
            jSONObject.putOpt("login_custom_tag", i.loginCustomTag);
            jSONObject.putOpt("disable_awake", Boolean.valueOf(i.disableAwake));
            jSONObject.putOpt("fetch_server_time_interval", Long.valueOf(i.fetchServerTimeInterval));
            jSONObject.putOpt("report_im_log", Boolean.valueOf(i.reportImLog));
            jSONObject.putOpt("custom_push_content_type", i.customPushContentType);
            jSONObject.putOpt("notify_stick_top_session", Boolean.valueOf(i.notifyStickTopSession));
            jSONObject.putOpt("enable_foreground_service", Boolean.valueOf(i.enableForegroundService));
            jSONObject.putOpt("cdn_request_data_interval", Integer.valueOf(i.cdnRequestDataInterval));
            jSONObject.putOpt("rollback_sql_cipher", Boolean.valueOf(i.rollbackSQLCipher));
            jSONObject.putOpt("core_process_start_timeout", Integer.valueOf(i.coreProcessStartTimeout));
            jSONObject.putOpt("clear_time_tag_at_beginning", Boolean.valueOf(i.clearTimeTagAtBeginning));
            jSONObject.putOpt("enable_database_backup", Boolean.valueOf(i.enableDatabaseBackup));
            jSONObject.putOpt("capture_device_info_config", i.captureDeviceInfoConfig == null ? null : i.captureDeviceInfoConfig.toJson());
            if (i.secondTimeoutForSendMessage != null) {
                jSONObject2 = i.secondTimeoutForSendMessage.toJson();
            }
            jSONObject.putOpt("second_timeout_for_send_message", jSONObject2);
            jSONObject.putOpt("flutter_sdk_version", i.flutterSdkVersion);
            jSONObject.putOpt("enable_lose_connection", Boolean.valueOf(i.enableLoseConnection));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("UILoginEventManager", "getSDKOptionsJson Exception", th);
        }
        return jSONObject.toString();
    }

    private long c(com.netease.nimlib.n.e.e eVar) {
        return com.netease.nimlib.n.f.a.a(eVar.a());
    }
}
