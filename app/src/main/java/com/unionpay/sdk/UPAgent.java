package com.unionpay.sdk;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.util.Log;
import java.util.Map;

/* loaded from: classes.dex */
public class UPAgent {
    public static boolean LOG_ON = true;

    public static final String getDeviceId(Context context) {
        try {
            return r.d(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static final int getNFCStatus() {
        NfcAdapter defaultAdapter;
        if (ad.c == null || (defaultAdapter = ((NfcManager) ad.c.getSystemService("nfc")).getDefaultAdapter()) == null) {
            return 0;
        }
        if (defaultAdapter.isEnabled()) {
            return (aw.a(19) && ad.c.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) ? 3 : 2;
        }
        return 1;
    }

    public static final void init(Context context) {
        try {
            r.a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void init(Context context, String str, String str2) {
        try {
            r.a(context, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onError(Context context, Throwable th) {
        try {
            r.a(context, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static final void onEvent(Context context, String str) {
        onEvent(context, str, "", null);
    }

    public static final void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, null);
    }

    public static final void onEvent(Context context, String str, String str2, Map map) {
        try {
            r.a(context, str, str2, map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onPageEnd(Context context, String str) {
        try {
            r.b(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onPageStart(Context context, String str) {
        try {
            r.a(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onPause(Context context) {
        try {
            r.c(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onResume(Context context) {
        try {
            r.b(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void onResume(Context context, String str, String str2) {
        try {
            r.b(context, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void removeGlobalKV(String str) {
        if (LOG_ON && str != null) {
            Log.i("unionpayLog", "removeGlobalKV# key:" + str);
        }
        ad.a.remove(str);
    }

    public static final void setAdditionalVersionNameAndCode(String str, long j) {
        try {
            r.a(str, j);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final void setAgentOption(int i) {
        if (LOG_ON) {
            Log.i("unionpayLog", "AgentOption.AO_Current = " + i);
        }
        AgentOption.a = i;
        if (i == 3) {
            ar.c().d();
            if (LOG_ON) {
                Log.i("unionpayLog", "Cleared local cache.");
            }
        }
    }

    public static final void setGlobalKV(String str, Object obj) {
        if (LOG_ON && str != null && obj != null) {
            Log.i("unionpayLog", "setGlobalKV# key:" + str + " value:" + obj.toString());
        }
        ad.a.put(str, obj);
    }

    public static final void setReportUncaughtExceptions(boolean z) {
        try {
            ad.b = z;
            if (LOG_ON) {
                Log.i("unionpayLog", "[PreSettings] setReportUncaughtExceptions: " + z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
