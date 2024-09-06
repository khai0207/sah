package com.netease.nimlib.abtest;

import android.content.Context;
import android.os.Build;
import com.alipay.sdk.m.l.e;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.n.b.g;
import com.netease.nimlib.n.d;
import com.netease.nimlib.net.a.d.b;
import com.netease.nimlib.network.h;
import com.netease.nimlib.o.f;
import com.netease.nimlib.o.y;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.talkingdata.sdk.df;
import com.unionpay.sdk.OttoBus;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ABTestManager.java */
/* loaded from: classes.dex */
public class c {
    private static c a = new c();
    private a b = new a();

    public static c a() {
        return a;
    }

    public void a(Context context) {
        try {
            if (NIMUtil.isMainProcess(context)) {
                this.b.a();
                h();
                com.netease.nimlib.log.b.M("pullABTest start");
                if (g()) {
                    com.netease.nimlib.net.a.d.b.a().a(context);
                    HashMap hashMap = new HashMap();
                    hashMap.put("Content-Type", "application/json");
                    String x = x();
                    com.netease.nimlib.log.b.M("pullABTest body = " + x);
                    com.netease.nimlib.net.a.d.b.a().a("https://abt-online.netease.im/v1/api/abt/client/getExperimentInfo", hashMap, x, false, g.AB_TEST, new b.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$PNoSsit6oIj10SHEtv4U66iEHKo
                        public /* synthetic */ $$Lambda$c$PNoSsit6oIj10SHEtv4U66iEHKo() {
                        }

                        @Override // com.netease.nimlib.net.a.d.b.a
                        public final void onResponse(String str, int i, Throwable th) {
                            c.this.a(str, i, th);
                        }
                    });
                } else {
                    com.netease.nimlib.log.b.M("pullABTest time <= the set interval");
                }
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.M("pullABTest exception = " + th);
        }
    }

    public /* synthetic */ void a(String str, int i, Throwable th) {
        com.netease.nimlib.log.b.M("pullABTest result = " + i + ",response = " + str);
        if (i != 200 || str == null) {
            return;
        }
        com.netease.nimlib.biz.c.a(y.a());
        a(str);
    }

    private long f() {
        int c = c("abtest_expire", OttoBus.DEFAULT_IDENTIFIER, "expire_time");
        int c2 = c("abtest_expire", "real_time", "expire_time");
        if (c == 0) {
            c = c2 != 0 ? c2 : 0;
        }
        return c * com.umeng.analytics.a.i;
    }

    private boolean g() {
        long a2 = y.a() - com.netease.nimlib.biz.c.a();
        long j = com.netease.nimlib.biz.c.b() ? com.umeng.analytics.a.h : 0L;
        long f = f();
        if (a2 < 0) {
            return true;
        }
        if (j > 0) {
            if (a2 > j) {
                com.netease.nimlib.log.b.M("pullABTest time delta > intervalFromIM，delta = " + a2 + ", intervalFromIM = " + j);
                return true;
            }
            com.netease.nimlib.log.b.M("pullABTest time delta <= intervalFromIM，delta = " + a2 + ", intervalFromIM = " + j);
            return false;
        }
        if (f <= 0) {
            com.netease.nimlib.log.b.M("pullABTest time not configured in IM & ABTest, pull real time, delta = " + a2);
            return true;
        }
        if (a2 > f) {
            com.netease.nimlib.log.b.M("pullABTest time delta > intervalFromAB，delta = " + a2 + ", intervalFromAB = " + f);
            return true;
        }
        com.netease.nimlib.log.b.M("pullABTest time delta <= intervalFromAB，delta = " + a2 + ", intervalFromAB = " + f);
        return false;
    }

    private void a(String str) {
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONObject("data").getJSONObject("abtInfo").getJSONArray("experiments");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(com.netease.nimlib.abtest.a.b.a(jSONArray.getJSONObject(i)));
            }
            this.b.a(arrayList);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.M("pullABTest parseResponse failed,exception = " + th);
            this.b.a();
        }
    }

    private void h() {
        v();
        t();
        u();
        q();
        r();
        s();
        w();
        p();
        o();
        n();
        m();
        n();
        k();
        l();
        j();
        i();
    }

    private void i() {
        boolean b = b("FCS_NEXT_Android", "FCS_NEXT_ENABLE", "enabled");
        com.netease.nimlib.log.b.d("ABTestManager", "processFcsNext = " + b);
        b.g(b);
    }

    private void j() {
        boolean b = b("quick_link_Android", "quick_link", "enabled");
        com.netease.nimlib.log.b.d("ABTestManager", "processQuickLink = " + b);
        if (b) {
            int c = c("quick_link_Android", "quick_link", "timeout");
            int c2 = c("quick_link_Android", "quick_link", "connectTimeout");
            boolean b2 = b("quick_link_Android", "quick_link", "longTimeoutAtFirst");
            int c3 = c("quick_link_Android", "quick_link", "reuseTTL");
            b.f(true);
            b.a(c);
            b.b(c2);
            b.e(b2);
            b.c(c3);
            return;
        }
        b.f(false);
    }

    private void k() {
        try {
            String[] strArr = {"receive_message_with_unexpected_long_down_time_closed", "receive_message_with_unexpected_long_down_time__5000_5000"};
            com.netease.nimlib.abtest.a.b bVar = null;
            for (int i = 0; i < 2; i++) {
                bVar = a("receive_message_with_unexpected_long_down_time", strArr[i]);
                if (bVar != null) {
                    break;
                }
            }
            boolean[] zArr = {false};
            long[] jArr = {0};
            long[] jArr2 = {0};
            if (bVar != null) {
                f.f(bVar.d(), new f.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$l093SGkckDW7x_dBWlfQ4tawpEs
                    private final /* synthetic */ boolean[] f$0;
                    private final /* synthetic */ long[] f$1;
                    private final /* synthetic */ long[] f$2;

                    public /* synthetic */ $$Lambda$c$l093SGkckDW7x_dBWlfQ4tawpEs(boolean[] zArr2, long[] jArr3, long[] jArr22) {
                        r1 = zArr2;
                        r2 = jArr3;
                        r3 = jArr22;
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a2;
                        a2 = c.a(r1, r2, r3, (com.netease.nimlib.abtest.a.c) obj);
                        return a2;
                    }
                });
            }
            boolean z = zArr2[0];
            long j = jArr3[0];
            long j2 = jArr22[0];
            com.netease.nimlib.log.b.d("ABTestManager", "processReceiveMessageWithUnexpectedLongDownTime, enabled = " + z + ", dtUpLimit = " + j + ", keepOnFgUpDownLimit = " + j2);
            b.a(z);
            b.a(j);
            b.c(j2);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processReceiveMessageWithUnexpectedLongDownTime failed,exception = " + e, e);
        }
    }

    public static /* synthetic */ Boolean a(boolean[] zArr, long[] jArr, long[] jArr2, com.netease.nimlib.abtest.a.c cVar) {
        if ("enabled".equals(cVar.e())) {
            zArr[0] = cVar.c().booleanValue();
        } else if ("dt_up_limit".equals(cVar.e())) {
            jArr[0] = cVar.b().longValue();
        } else if ("keep_on_fg_up_down_limit".equals(cVar.e())) {
            jArr2[0] = cVar.b().longValue();
        }
        return true;
    }

    private void l() {
        try {
            String[] strArr = {"aos_heart_beat_term_closed", "aos_heart_beat_term_opened_30"};
            com.netease.nimlib.abtest.a.b bVar = null;
            for (int i = 0; i < 2; i++) {
                bVar = a("aos_heart_beat_term", strArr[i]);
                if (bVar != null) {
                    break;
                }
            }
            boolean[] zArr = {false};
            long[] jArr = {0};
            if (bVar != null) {
                f.f(bVar.d(), new f.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$vzAozJsCaRUqYQ1S-ft1eObXNG8
                    private final /* synthetic */ boolean[] f$0;
                    private final /* synthetic */ long[] f$1;

                    public /* synthetic */ $$Lambda$c$vzAozJsCaRUqYQ1Sft1eObXNG8(boolean[] zArr2, long[] jArr2) {
                        r1 = zArr2;
                        r2 = jArr2;
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean b;
                        b = c.b(r1, r2, (com.netease.nimlib.abtest.a.c) obj);
                        return b;
                    }
                });
            }
            boolean z = zArr2[0];
            long j = jArr2[0];
            com.netease.nimlib.log.b.d("ABTestManager", "processAosHeartBeatTerm, enabled = " + z + ", maxDelay = " + j);
            b.b(z);
            b.e(j);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processAosHeartBeatTerm failed,exception = " + e, e);
        }
    }

    public static /* synthetic */ Boolean b(boolean[] zArr, long[] jArr, com.netease.nimlib.abtest.a.c cVar) {
        if ("enabled".equals(cVar.e())) {
            zArr[0] = cVar.c().booleanValue();
        } else if ("interval".equals(cVar.e())) {
            jArr[0] = cVar.b().longValue();
        }
        return true;
    }

    private void m() {
        try {
            boolean b = b("UI_Process_Status_Transition_Control", "open_flow", "open");
            com.netease.nimlib.log.b.d("ABTestManager", "abTestProcess processUiProcessStatusTransitionControl open = " + b);
            i.a().c(b);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processUiProcessStatusTransitionControl failed,exception = " + e, e);
        }
    }

    private void n() {
        try {
            String[] strArr = {"receive_message_down_time_reliable_closed", "receive_message_down_time_reliable__no_upload__max_delay", "receive_message_down_time_reliable__upload__max_delay"};
            com.netease.nimlib.abtest.a.b bVar = null;
            for (int i = 0; i < 3; i++) {
                bVar = a("receive_message_down_time_reliable", strArr[i]);
                if (bVar != null) {
                    break;
                }
            }
            boolean[] zArr = {false};
            long[] jArr = {0};
            boolean[] zArr2 = {false};
            if (bVar != null) {
                f.f(bVar.d(), new f.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$mr9x6XES2qhC7urTMfVzcwZeRs4
                    private final /* synthetic */ boolean[] f$0;
                    private final /* synthetic */ long[] f$1;
                    private final /* synthetic */ boolean[] f$2;

                    public /* synthetic */ $$Lambda$c$mr9x6XES2qhC7urTMfVzcwZeRs4(boolean[] zArr3, long[] jArr2, boolean[] zArr22) {
                        r1 = zArr3;
                        r2 = jArr2;
                        r3 = zArr22;
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a2;
                        a2 = c.a(r1, r2, r3, (com.netease.nimlib.abtest.a.c) obj);
                        return a2;
                    }
                });
            }
            boolean z = zArr3[0];
            long j = jArr2[0];
            boolean z2 = zArr22[0];
            com.netease.nimlib.log.b.d("ABTestManager", "processReceiveMessageDownTimeReliable, enabled = " + z + ", maxDelay = " + j + ", uploadQs = " + z2);
            b.c(z);
            b.f(j);
            b.d(z2);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processReceiveMessageDownTimeReliable failed,exception = " + e, e);
        }
    }

    public static /* synthetic */ Boolean a(boolean[] zArr, long[] jArr, boolean[] zArr2, com.netease.nimlib.abtest.a.c cVar) {
        if ("enabled".equals(cVar.e())) {
            zArr[0] = cVar.c().booleanValue();
        } else if ("max_delay".equals(cVar.e())) {
            jArr[0] = cVar.b().longValue();
        } else if ("upload_qs".equals(cVar.e())) {
            zArr2[0] = cVar.c().booleanValue();
        }
        return true;
    }

    public boolean b() {
        return b("real_reachability_aos", "open_flow", "open");
    }

    public boolean c() {
        return b("link_keep_exception_report", "report_open", "open");
    }

    private void o() {
        try {
            com.netease.nimlib.abtest.a.b a2 = a("real_reachability_aos", "open_flow");
            if (a2 != null) {
                com.netease.nimlib.abtest.a.a aVar = new com.netease.nimlib.abtest.a.a();
                f.f(a2.d(), new f.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$C6c55GQG3ApvanITwr4GjCb8dws
                    public /* synthetic */ $$Lambda$c$C6c55GQG3ApvanITwr4GjCb8dws() {
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a3;
                        a3 = c.a(com.netease.nimlib.abtest.a.a.this, (com.netease.nimlib.abtest.a.c) obj);
                        return a3;
                    }
                });
                h.a().a(aVar);
            } else {
                h.a().a((com.netease.nimlib.abtest.a.a) null);
            }
        } catch (Throwable th) {
            h.a().a((com.netease.nimlib.abtest.a.a) null);
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processRealReachability failed,exception = " + th, th);
        }
    }

    public static /* synthetic */ Boolean a(com.netease.nimlib.abtest.a.a aVar, com.netease.nimlib.abtest.a.c cVar) {
        if ("open".equals(cVar.e())) {
            aVar.a(Boolean.TRUE.equals(cVar.c()));
        } else if ("ping_address".equals(cVar.e())) {
            aVar.a(cVar.d());
        } else if ("telent".equals(cVar.e())) {
            aVar.b(cVar.d());
        } else if ("auto_check_min".equals(cVar.e())) {
            aVar.a(cVar.a().intValue());
        } else if ("ping_timeout".equals(cVar.e())) {
            aVar.b(cVar.a().intValue());
        } else if ("telent_timeout".equals(cVar.e())) {
            aVar.c(cVar.a().intValue());
        }
        return true;
    }

    private void p() {
        try {
            String[] strArr = {"exception_context_disk_info_closed", "exception_context_disk_info_no_frequency_control", "exception_context_disk_info_frequency_control_1h", "exception_context_disk_info_frequency_control_5s"};
            com.netease.nimlib.abtest.a.b bVar = null;
            for (int i = 0; i < 4; i++) {
                bVar = a("exception_context_disk_info", strArr[i]);
                if (bVar != null) {
                    break;
                }
            }
            boolean[] zArr = {false};
            long[] jArr = {0};
            if (bVar != null) {
                f.f(bVar.d(), new f.a() { // from class: com.netease.nimlib.abtest.-$$Lambda$c$fhTe9c5JddEo0IzqJ2wbozaQHlg
                    private final /* synthetic */ boolean[] f$0;
                    private final /* synthetic */ long[] f$1;

                    public /* synthetic */ $$Lambda$c$fhTe9c5JddEo0IzqJ2wbozaQHlg(boolean[] zArr2, long[] jArr2) {
                        r1 = zArr2;
                        r2 = jArr2;
                    }

                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a2;
                        a2 = c.a(r1, r2, (com.netease.nimlib.abtest.a.c) obj);
                        return a2;
                    }
                });
            }
            boolean z = zArr2[0];
            long j = jArr2[0];
            com.netease.nimlib.log.b.d("ABTestManager", "processGetDiskInfoForExceptionReportOpen ExceptionContextDiskInfo, enabled = " + z + ", frequencyControl = " + j);
            com.netease.nimlib.biz.a.b(z);
            com.netease.nimlib.biz.a.a(j);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processGetDiskInfoForExceptionReportOpen failed,exception = " + e, e);
        }
    }

    public static /* synthetic */ Boolean a(boolean[] zArr, long[] jArr, com.netease.nimlib.abtest.a.c cVar) {
        if ("enabled".equals(cVar.e())) {
            zArr[0] = cVar.c().booleanValue();
        } else if ("frequency_control".equals(cVar.e())) {
            jArr[0] = cVar.b().longValue();
        }
        return true;
    }

    private void q() {
        try {
            boolean b = b("database_function_aos", "open_flow", "open");
            com.netease.nimlib.log.b.d("ABTestManager", "processDatabaseFunctionTransformationOpen SCHEME_DATABON_TRANSFORMATION_OPEN = " + b);
            com.netease.nimlib.biz.a.c(b);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processDatabaseFunctionTransformationOpen failed,exception = " + e, e);
        }
    }

    private void r() {
        try {
            boolean b = b("database_message_parameterized_aos", "open_flow", "open");
            com.netease.nimlib.log.b.d("ABTestManager", "processDatabaseMessageParameterizedEnable SCHEME_DATABASE_MESSAGE_PARAMETERIZED_OPEN_KEY = " + b);
            com.netease.nimlib.biz.a.d(b);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processDatabaseMessageParameterizedEnable failed,exception = " + e, e);
        }
    }

    private void s() {
        try {
            boolean b = b("exception_database_transform_string_error_20231225", "exception_database_transform_string_error_20231225_enabled", "enabled");
            com.netease.nimlib.log.b.d("ABTestManager", "processDatabaseMessageParameterizedEnable SCHEME_DATABASE_MESSAGE_PARAMETERIZED_OPEN_KEY = " + b);
            com.netease.nimlib.biz.a.e(b);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processDatabaseMessageParameterizedEnable failed,exception = " + e, e);
        }
    }

    private void t() {
        try {
            boolean b = b("api_trace_collection", "open_flow", "open");
            com.netease.nimlib.log.b.d("ABTestManager", "processApiTraceOpen apiTraceOpen = " + b);
            com.netease.nimlib.n.a.a().a(b);
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processApiTraceOpen failed,exception = " + e, e);
        }
    }

    private void u() {
        try {
            boolean b = b("database_api_trace_with_threshold_android", "open_flow", "open");
            int c = c("database_api_trace_with_threshold_android", "open_flow", "threshold");
            com.netease.nimlib.log.b.d("ABTestManager", String.format("processDatabaseTraceOpen apiTraceOpen = %s %s", Boolean.valueOf(b), Integer.valueOf(c)));
            d.a().a(b);
            if (c > 0) {
                d.a().a(c);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processDatabaseTraceOpen failed,exception = " + e, e);
        }
    }

    private void v() {
        try {
            boolean b = b("yidun_test", "yidun_open", "open");
            com.netease.nimlib.log.b.d("ABTestManager", "processEidOpen isEidOpen = " + b);
            com.netease.nimlib.biz.a.a(b);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processEidOpen failed,exception = " + th, th);
        }
    }

    public com.netease.nimlib.abtest.a.b a(String str, String str2) {
        return this.b.a(str, str2);
    }

    private void w() {
        try {
            if (a("exception_report_filter", "filter_open", "open") == null && a("exception_report_filter", "filter_close", "open") == null) {
                com.netease.nimlib.log.b.d("ABTestManager", "processReportFilterOpen reportFilterOpen is not set, use default value 'true'");
                com.netease.nimlib.n.d.a.a(true);
            } else {
                boolean b = b("exception_report_filter", "filter_open", "open");
                com.netease.nimlib.log.b.d("ABTestManager", "processReportFilterOpen reportFilterOpen = " + b);
                com.netease.nimlib.n.d.a.a(b);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess processReportFilterOpen failed,exception = " + th, th);
        }
    }

    public String d() {
        try {
            if (a("http_exception_trigger_artemis", "http_exception_trigger_artemis_default", "open") != null) {
                return "http_exception_trigger_artemis_default";
            }
            if (a("http_exception_trigger_artemis", "http_exception_trigger_artemis_high_freq", "open") != null) {
                return "http_exception_trigger_artemis_high_freq";
            }
            if (a("http_exception_trigger_artemis", "http_exception_trigger_artemis_low_freq", "open") != null) {
                return "http_exception_trigger_artemis_low_freq";
            }
            if (a("http_exception_trigger_artemis", "http_exception_trigger_artemis_close", "open") != null) {
                return "http_exception_trigger_artemis_close";
            }
            return null;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess getHttpExceptionTriggerArtemisScheme failed,exception = " + th, th);
            return null;
        }
    }

    public String e() {
        try {
            if (a("udp_ping_detect_android", "tcp_exception_trigger_artemis_default", "open") != null) {
                return "tcp_exception_trigger_artemis_default";
            }
            if (a("udp_ping_detect_android", "tcp_exception_trigger_artemis_close", "open") != null) {
                return "tcp_exception_trigger_artemis_close";
            }
            return null;
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestProcess getTcpExceptionTriggerArtemisScheme failed, exception = " + th, th);
            return null;
        }
    }

    public com.netease.nimlib.abtest.a.c a(String str, String str2, String str3) {
        return this.b.a(str, str2, str3);
    }

    public boolean b(String str, String str2, String str3) {
        Boolean c;
        boolean z = false;
        try {
            com.netease.nimlib.abtest.a.c a2 = a(str, str2, str3);
            boolean booleanValue = (a2 == null || (c = a2.c()) == null) ? false : c.booleanValue();
            try {
                com.netease.nimlib.log.b.M(String.format("abTestGetValOfBoolean, experimentKey:%s -> schemeKey:%s -> %s:%s", str, str2, str3, Boolean.valueOf(booleanValue)));
                return booleanValue;
            } catch (Throwable th) {
                boolean z2 = booleanValue;
                th = th;
                z = z2;
                com.netease.nimlib.log.b.e("ABTestManager", "abTestGetValOfBoolean failed,experimentKey = " + str + ",schemeKey = " + str2 + ",key = " + str3, th);
                return z;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int c(String str, String str2, String str3) {
        Integer a2;
        int i = 0;
        try {
            com.netease.nimlib.abtest.a.c a3 = a(str, str2, str3);
            int intValue = (a3 == null || (a2 = a3.a()) == null) ? 0 : a2.intValue();
            try {
                com.netease.nimlib.log.b.M(String.format("abTestGetValOfInt, experimentKey:%s -> schemeKey:%s -> %s:%s", str, str2, str3, Integer.valueOf(intValue)));
                return intValue;
            } catch (Throwable th) {
                int i2 = intValue;
                th = th;
                i = i2;
                com.netease.nimlib.log.b.e("ABTestManager", "abTestGetValOfInt failed,experimentKey = " + str + ",schemeKey = " + str2 + ",key = " + str3, th);
                return i;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String d(String str, String str2, String str3) {
        try {
            com.netease.nimlib.abtest.a.c a2 = a(str, str2, str3);
            r0 = a2 != null ? a2.d() : null;
            com.netease.nimlib.log.b.M(String.format("abTestGetValOfString, experimentKey:%s -> schemeKey:%s -> %s:%s", str, str2, str3, r0));
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("ABTestManager", "abTestGetValOfString failed,experimentKey = " + str + ",schemeKey = " + str2 + ",key = " + str3, th);
        }
        return r0;
    }

    private String x() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("useLocalCache", true);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("projectKey", "im_sdk_abtest_all");
            jSONObject2.put("appKey", com.netease.nimlib.c.g());
            jSONObject2.put("userId", com.netease.nimlib.c.n());
            jSONObject2.put("sdkVersion", "9.17.0");
            jSONObject2.put(df.c, com.netease.nimlib.push.b.c());
            jSONObject2.put("manufacturer", com.netease.nimlib.p.a.a());
            jSONObject2.put("board", Build.BOARD);
            jSONObject2.put("model", com.netease.nimlib.p.a.b());
            jSONObject2.put(e.p, Build.DEVICE);
            jSONObject2.put("osType", "Android");
            jSONObject2.put("osVersion", Build.VERSION.RELEASE);
            jSONObject.put("clientInfo", jSONObject2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }
}
