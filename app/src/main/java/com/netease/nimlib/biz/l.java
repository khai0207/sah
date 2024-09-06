package com.netease.nimlib.biz;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.netease.nimlib.o.w;
import com.netease.nimlib.qchat.model.y;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.qchat.enums.QChatPushMsgType;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: UserPreferences.java */
/* loaded from: classes.dex */
public class l {
    public static void a(String str, String str2, int i) {
        com.netease.nimlib.log.b.d("UserPreferences", "saveSqlCipherVersion: dbName=" + str2 + ", version=" + i);
        SharedPreferences.Editor edit = l(str).edit();
        StringBuilder sb = new StringBuilder();
        sb.append("sqlcipher_version_");
        sb.append(str2);
        edit.putInt(sb.toString(), i);
        edit.apply();
    }

    public static int a(String str, String str2) {
        return l(str).getInt("sqlcipher_version_" + str2, 0);
    }

    public static void a(String str) {
        SyncCrossProcessDBHelper.saveStringValue("k_grow_device_token", str);
    }

    public static String a() {
        return SyncCrossProcessDBHelper.queryStringValue("k_grow_device_token", "");
    }

    public static void a(boolean z) {
        b("k_qchat_push_enable", z);
    }

    public static boolean b() {
        return a("k_qchat_push_enable", true);
    }

    public static void a(y yVar) {
        a("k_qchat_dnd_push_config_tag", yVar);
    }

    public static y c() {
        return n("k_qchat_dnd_push_config_tag");
    }

    public static void a(long j) {
        c("k_qchat_dndpush_config_tt", j);
    }

    public static long d() {
        return p("k_qchat_dndpush_config_tt");
    }

    public static void b(long j) {
        f("k_app_gray_invalid_time", j);
    }

    public static long e() {
        return r("k_app_gray_invalid_time");
    }

    public static void b(boolean z) {
        b("k_mix_store_enable", z);
    }

    public static boolean f() {
        return a("k_mix_store_enable", false);
    }

    public static void c(boolean z) {
        b("k_app_status_back", z);
    }

    public static boolean g() {
        return a("k_app_status_back", true);
    }

    public static void c(long j) {
        if (j > i()) {
            c("k_robot_list_tt", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: robotListTimeTag =" + j);
        }
    }

    public static String h() {
        return s("k_client_ip");
    }

    public static void b(String str) {
        b("k_client_ip", str);
    }

    public static long i() {
        return p("k_robot_list_tt");
    }

    public static void d(long j) {
        c("k_latest_broadcast", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: lastBroadcastMsgId =" + j);
    }

    public static long j() {
        return p("k_latest_broadcast");
    }

    public static String k() {
        return s("k_online_broadcasts");
    }

    public static void c(String str) {
        b("k_online_broadcasts", str);
    }

    public static void e(long j) {
        c("k_session_ack_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: sessionAckListTimeTag =" + j);
    }

    public static long l() {
        return p("k_session_ack_tt");
    }

    public static void f(long j) {
        c("k_super_teamsession_ack_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: superTeamSessionAckListTimeTag =" + j);
    }

    public static long m() {
        return p("k_super_teamsession_ack_tt");
    }

    public static void d(boolean z) {
        b("k_mix_push_enable", z);
    }

    public static boolean n() {
        return a("k_mix_push_enable", true);
    }

    public static void a(String str, SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            sharedPreferences = O();
        }
        sharedPreferences.edit().putString("k_mix_push_token", str).commit();
    }

    public static void g(long j) {
        c("k_dndpush_config_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: noDisturbConfigTimeTag =" + j);
    }

    public static long o() {
        return p("k_dndpush_config_tt");
    }

    public static void h(long j) {
        c("k_revoke_msg_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: revokeMsgTimeTag =" + j);
    }

    public static long p() {
        return p("k_revoke_msg_tt");
    }

    public static void i(long j) {
        c("k_super_revoke_msg_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: superTeamRevokeMsgTimeTag =" + j);
    }

    public static long q() {
        return p("k_super_revoke_msg_tt");
    }

    public static void e(boolean z) {
        b("k_donop_config_tag", z);
    }

    public static boolean r() {
        return a("k_donop_config_tag", false);
    }

    public static void j(long j) {
        c("k_donop_config_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: dontPushConfigTimeTag =" + j);
    }

    public static long s() {
        return p("k_donop_config_tt");
    }

    public static void k(long j) {
        c("k_uinfo_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: myUserInfoTimeTag =" + j);
    }

    public static long t() {
        return p("k_uinfo_tt");
    }

    public static void l(long j) {
        c("k_friend_uinfo_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: friendInfoTimeTag =" + j);
    }

    public static long u() {
        return p("k_friend_uinfo_tt");
    }

    public static void m(long j) {
        c("k_tinfo_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: teamInfoTimeTag =" + j);
    }

    public static long v() {
        return p("k_tinfo_tt");
    }

    public static void n(long j) {
        c("k_super_tinfo_tt", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: superTeamInfoTimeTag =" + j);
    }

    public static long w() {
        return p("k_super_tinfo_tt");
    }

    public static void a(String str, long j) {
        c("k_tmember_tt_tag_" + str, j);
        com.netease.nimlib.log.b.d("UserPreferences", "save team member timetag, teamId=" + str + ", timetag=" + j);
    }

    public static long d(String str) {
        return p("k_tmember_tt_tag_" + str);
    }

    public static void b(String str, long j) {
        c("k_super_tmember_tt_tag_" + str, j);
        com.netease.nimlib.log.b.d("UserPreferences", "save super team member timetag, teamId=" + str + ", timetag=" + j);
    }

    public static long e(String str) {
        return p("k_super_tmember_tt_tag_" + str);
    }

    public static long x() {
        if (com.netease.nimlib.h.h()) {
            return p("k_roaming_msg");
        }
        return q("k_roaming_msg");
    }

    public static void o(long j) {
        if (com.netease.nimlib.c.z() || j <= x()) {
            return;
        }
        if (com.netease.nimlib.h.h()) {
            c("k_roaming_msg", j);
        } else {
            e("k_roaming_msg", j);
        }
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: roamingMsgTimeTag =" + j);
    }

    public static long y() {
        long d = d("k_friend_list", -1L);
        com.netease.nimlib.log.b.N("get friend list timetag, " + d);
        long max = Math.max(0L, d);
        com.netease.nimlib.log.b.N("get friend list return timetag, " + max);
        return max;
    }

    public static void p(long j) {
        if (j > y()) {
            c("k_friend_list", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: friendListTimeTag =" + j);
        }
    }

    public static long z() {
        return p("k_black_mute");
    }

    public static void q(long j) {
        if (j > z()) {
            c("k_black_mute", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: blackAndMuteListTimeTag =" + j);
        }
    }

    public static long A() {
        return p("k_msg_read_tt");
    }

    public static void r(long j) {
        if (j > A()) {
            c("k_msg_read_tt", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: p2pSessionMsgReadTimeTag =" + j);
        }
    }

    public static void s(long j) {
        if (j > B()) {
            c("k_my_tmember_tt", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: myTeamMemberListTimeTag =" + j);
        }
    }

    public static long B() {
        return p("k_my_tmember_tt");
    }

    public static void t(long j) {
        if (j > C()) {
            c("k_my_super_tmember_tt", j);
            com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: mySuperTeamMemberListTimeTag =" + j);
        }
    }

    public static long C() {
        return p("k_my_super_tmember_tt");
    }

    public static void a(e eVar) {
        a("k_dnd_push_config_tag", eVar);
    }

    public static e D() {
        return o("k_dnd_push_config_tag");
    }

    private static y n(String str) {
        y yVar = new y();
        String string = O().getString(str, "");
        if (TextUtils.isEmpty(string)) {
            b(yVar);
            return yVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            yVar.setNoDisturbOpen(jSONObject.getBoolean("isOpen"));
            yVar.setPushShowNoDetail(jSONObject.getBoolean("isPushShow"));
            yVar.a(jSONObject.getInt("startH"));
            yVar.b(jSONObject.getInt("startM"));
            yVar.c(jSONObject.getInt("stopH"));
            yVar.d(jSONObject.getInt("stopM"));
            yVar.a(jSONObject.getBoolean("isPushDndValid"));
            yVar.setPushMsgType(QChatPushMsgType.typeOfValue(jSONObject.optInt("pushMsgType")));
            return yVar;
        } catch (Exception e) {
            e.printStackTrace();
            b(yVar);
            return yVar;
        }
    }

    private static e o(String str) {
        e eVar = new e();
        String string = O().getString(str, "");
        if (TextUtils.isEmpty(string)) {
            b(eVar);
            return eVar;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            eVar.setOpen(jSONObject.getBoolean("isOpen"));
            eVar.b(jSONObject.getBoolean("isPushShow"));
            eVar.a(jSONObject.getInt("startH"));
            eVar.b(jSONObject.getInt("startM"));
            eVar.c(jSONObject.getInt("stopH"));
            eVar.d(jSONObject.getInt("stopM"));
            eVar.a(jSONObject.getBoolean("isPushDndValid"));
            return eVar;
        } catch (Exception e) {
            e.printStackTrace();
            b(eVar);
            return eVar;
        }
    }

    private static void b(e eVar) {
        eVar.setOpen(false);
        eVar.setStartTime("22:00");
        eVar.setStopTime("08:00");
        eVar.a(false);
    }

    private static void b(y yVar) {
        yVar.setNoDisturbOpen(false);
        yVar.setStartTime("22:00");
        yVar.setStopTime("08:00");
        yVar.a(false);
        yVar.setPushShowNoDetail(false);
        yVar.setPushMsgType(null);
    }

    private static void a(String str, e eVar) {
        SharedPreferences.Editor edit = O().edit();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isOpen", eVar.isOpen());
            jSONObject.put("isPushShow", eVar.f());
            jSONObject.put("startH", eVar.b());
            jSONObject.put("startM", eVar.c());
            jSONObject.put("stopH", eVar.d());
            jSONObject.put("stopM", eVar.e());
            jSONObject.put("isPushDndValid", eVar.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
        edit.putString(str, jSONObject.toString());
        edit.commit();
    }

    private static void a(String str, y yVar) {
        SharedPreferences.Editor edit = O().edit();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isOpen", yVar.isNoDisturbOpen());
            jSONObject.put("isPushShow", yVar.isPushShowNoDetail());
            jSONObject.put("startH", yVar.a());
            jSONObject.put("startM", yVar.b());
            jSONObject.put("stopH", yVar.c());
            jSONObject.put("stopM", yVar.d());
            jSONObject.put("isPushDndValid", yVar.e());
            QChatPushMsgType pushMsgType = yVar.getPushMsgType();
            if (pushMsgType != null) {
                jSONObject.put("pushMsgType", pushMsgType.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        edit.putString(str, jSONObject.toString());
        edit.commit();
    }

    public static void u(long j) {
        c("k_clear_all_msg_time", j);
    }

    public static long E() {
        return p("k_clear_all_msg_time");
    }

    public static void v(long j) {
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: deleteMsgSelfTimeTag =" + j);
        c("k_msg_delete_self", j);
    }

    public static long F() {
        long p = p("k_msg_delete_self");
        com.netease.nimlib.log.b.d("UserPreferences", "getMsgDeleteSelfTimeTag: " + p);
        return p;
    }

    public static void w(long j) {
        c("k_login_time", j);
    }

    public static long G() {
        return p("k_login_time");
    }

    public static void f(String str) {
        SharedPreferences.Editor edit = l(str).edit();
        edit.putBoolean("k_sqlcipher_rollback_main", true);
        edit.commit();
    }

    public static boolean H() {
        return a("k_sqlcipher_rollback_main", false);
    }

    public static void g(String str) {
        SharedPreferences.Editor edit = l(str).edit();
        edit.putBoolean("k_sqlcipher_rollback_msg", true);
        edit.commit();
    }

    public static boolean I() {
        return a("k_sqlcipher_rollback_msg", false);
    }

    public static void h(String str) {
        b("k_sqlcipher_reset_153_" + str, true);
    }

    public static boolean i(String str) {
        return a("k_sqlcipher_reset_153_" + str, false);
    }

    public static void j(String str) {
        b("k_reset_roaming_msg_time_tag" + str, true);
    }

    public static boolean k(String str) {
        return a("k_reset_roaming_msg_time_tag" + str, false);
    }

    public static void x(long j) {
        c("k_stick_top_session", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: stickTopSessionTimeTag =" + j);
    }

    public static long J() {
        return p("k_stick_top_session");
    }

    public static void y(long j) {
        c("k_session_history_msgs_delete", j);
        com.netease.nimlib.log.b.d("UserPreferences", "save sync time tags: sessionHistoryMsgDeleteTimeTag =" + j);
    }

    public static long K() {
        return p("k_session_history_msgs_delete");
    }

    public static d L() {
        String queryStringValue = SyncCrossProcessDBHelper.queryStringValue("KEY_LOGIN_INFO", "");
        if (w.a((CharSequence) queryStringValue)) {
            return null;
        }
        try {
            d dVar = new d();
            JSONObject jSONObject = new JSONObject(queryStringValue);
            dVar.a(new LoginInfo.LoginInfoBuilder(jSONObject.optString("account"), jSONObject.optString("token"), jSONObject.optInt("authType"), jSONObject.optString("loginExt")).withAppKey(jSONObject.optString("appKey")).withCustomClientType(jSONObject.optInt("customClientType")).build());
            dVar.a(jSONObject.optBoolean("isManualLogging"));
            return dVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Long> M() {
        String s = s("k_record_custom_sys_notify_by_msg_id");
        if (TextUtils.isEmpty(s)) {
            return new ArrayList<>();
        }
        try {
            ArrayList<Long> arrayList = (ArrayList) m(s);
            return arrayList == null ? new ArrayList<>() : arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void a(ArrayList<Long> arrayList) {
        b("k_record_custom_sys_notify_by_msg_id", a((Object) arrayList));
    }

    public static com.netease.nimlib.ipc.a.f N() {
        return com.netease.nimlib.ipc.a.f.a(SyncCrossProcessDBHelper.queryStringValue("k_sync_time_tag", ""));
    }

    private static boolean a(String str, boolean z) {
        return O().getBoolean(str, z);
    }

    private static void b(String str, boolean z) {
        SharedPreferences.Editor edit = O().edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    private static void c(String str, long j) {
        SharedPreferences.Editor edit = O().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    private static long p(String str) {
        return O().getLong(str, 0L);
    }

    private static long d(String str, long j) {
        return O().getLong(str, j);
    }

    private static void e(String str, long j) {
        SyncCrossProcessDBHelper.saveLongValue(str, j);
    }

    private static long q(String str) {
        return SyncCrossProcessDBHelper.queryLongValue(str, 0L);
    }

    private static void f(String str, long j) {
        SharedPreferences.Editor edit = O().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    private static long r(String str) {
        return O().getLong(str, 0L);
    }

    private static void b(String str, String str2) {
        SharedPreferences.Editor edit = O().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    private static String s(String str) {
        return O().getString(str, null);
    }

    public static SharedPreferences O() {
        return l(com.netease.nimlib.c.n());
    }

    public static SharedPreferences l(String str) {
        return com.netease.nimlib.c.e().getSharedPreferences("NIMSDK_Config_" + com.netease.nimlib.c.g() + "_" + str, 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0046 A[Catch: IOException -> 0x004a, TRY_LEAVE, TryCatch #0 {IOException -> 0x004a, blocks: (B:28:0x0041, B:30:0x0046), top: B:27:0x0041 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.Object r5) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            r2.writeObject(r5)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3f
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3f
            byte[] r3 = r0.toByteArray()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3f
            r4 = 0
            byte[] r3 = android.util.Base64.encode(r3, r4)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3f
            r5.<init>(r3)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L3f
            r0.close()     // Catch: java.io.IOException -> L23
            r2.close()     // Catch: java.io.IOException -> L23
            goto L27
        L23:
            r0 = move-exception
            r0.printStackTrace()
        L27:
            return r5
        L28:
            r5 = move-exception
            goto L2e
        L2a:
            r5 = move-exception
            goto L41
        L2c:
            r5 = move-exception
            r2 = r1
        L2e:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            r0.close()     // Catch: java.io.IOException -> L3a
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r5 = move-exception
            r5.printStackTrace()
        L3e:
            return r1
        L3f:
            r5 = move-exception
            r1 = r2
        L41:
            r0.close()     // Catch: java.io.IOException -> L4a
            if (r1 == 0) goto L4e
            r1.close()     // Catch: java.io.IOException -> L4a
            goto L4e
        L4a:
            r0 = move-exception
            r0.printStackTrace()
        L4e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.l.a(java.lang.Object):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0040 A[Catch: Exception -> 0x0044, TRY_LEAVE, TryCatch #6 {Exception -> 0x0044, blocks: (B:27:0x003b, B:29:0x0040), top: B:26:0x003b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> T m(java.lang.String r4) {
        /*
            r0 = 0
            byte[] r4 = android.util.Base64.decode(r4, r0)
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r4)
            r4 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L27
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L27
            java.lang.Object r4 = r1.readObject()     // Catch: java.lang.Exception -> L20 java.lang.Throwable -> L3a
            r0.close()     // Catch: java.lang.Exception -> L1b
            r1.close()     // Catch: java.lang.Exception -> L1b
            goto L1f
        L1b:
            r0 = move-exception
            r0.printStackTrace()
        L1f:
            return r4
        L20:
            r2 = move-exception
            goto L29
        L22:
            r1 = move-exception
            r3 = r1
            r1 = r4
            r4 = r3
            goto L3b
        L27:
            r2 = move-exception
            r1 = r4
        L29:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L3a
            r0.close()     // Catch: java.lang.Exception -> L35
            if (r1 == 0) goto L39
            r1.close()     // Catch: java.lang.Exception -> L35
            goto L39
        L35:
            r0 = move-exception
            r0.printStackTrace()
        L39:
            return r4
        L3a:
            r4 = move-exception
        L3b:
            r0.close()     // Catch: java.lang.Exception -> L44
            if (r1 == 0) goto L48
            r1.close()     // Catch: java.lang.Exception -> L44
            goto L48
        L44:
            r0 = move-exception
            r0.printStackTrace()
        L48:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.l.m(java.lang.String):java.lang.Object");
    }
}
