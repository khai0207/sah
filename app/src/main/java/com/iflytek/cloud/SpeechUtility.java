package com.iflytek.cloud;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.iflytek.cloud.a.d.d;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import com.iflytek.speech.SpeechComponent;
import com.iflytek.speech.UtilityConfig;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SpeechUtility extends com.iflytek.cloud.a.d.d {
    public static final String TAG_RESOURCE_CONTENT = "tag_rescontent";
    public static final String TAG_RESOURCE_RESULT = "result";
    public static final String TAG_RESOURCE_RET = "ret";
    private static SpeechUtility c;
    protected d.a a;
    private Context f;
    private ArrayList<SpeechComponent> d = new ArrayList<>();
    private int e = -1;
    private boolean g = false;
    private a h = null;

    /* loaded from: classes.dex */
    private class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(SpeechUtility speechUtility, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            String concat = "package:".concat(UtilityConfig.COMPONENT_PKG);
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) && concat.equals(dataString) && SpeechUtility.getUtility() != null) {
                SpeechUtility.getUtility().checkServiceInstalled();
            }
        }
    }

    private SpeechUtility(Context context, String str) {
        d.a aVar;
        this.f = null;
        this.a = d.a.AUTO;
        this.f = context.getApplicationContext();
        super.setParameter("params", str);
        MSC.loadLibrary(this.b.b("lib_name", SpeechConstant.MODE_MSC));
        if (MSC.isLoaded()) {
            try {
                MSC.DebugLog(Setting.a);
            } catch (UnsatisfiedLinkError unused) {
            }
        }
        setParameter("params", str);
        String parameter = getParameter(SpeechConstant.ENGINE_MODE);
        try {
            if (!SpeechConstant.MODE_MSC.equals(parameter)) {
                aVar = SpeechConstant.MODE_PLUS.equals(parameter) ? d.a.PLUS : aVar;
                b();
                d();
                e();
                com.iflytek.common.a.a(context, SpeechConstant.APPID, this.b.e(SpeechConstant.APPID));
                com.iflytek.common.a.a(context);
                com.iflytek.common.a.a(false);
                return;
            }
            aVar = d.a.MSC;
            com.iflytek.common.a.a(context, SpeechConstant.APPID, this.b.e(SpeechConstant.APPID));
            com.iflytek.common.a.a(context);
            com.iflytek.common.a.a(false);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        this.a = aVar;
        b();
        d();
        e();
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PackageManager packageManager = this.f.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 224);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            return;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            SpeechComponent b = b(resolveInfo.serviceInfo.packageName);
            if (b != null) {
                try {
                    for (String str2 : resolveInfo.serviceInfo.metaData.getString(UtilityConfig.METADATA_KEY_ENGINE_TYPE).split(",")) {
                        b.addEngine(str2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean a(Context context) {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        com.iflytek.cloud.a.g.a.a.a("process name:" + runningAppProcessInfo.processName);
                        if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).packageName.equals(runningAppProcessInfo.processName)) {
                            com.iflytek.cloud.a.g.a.a.a("process name:" + runningAppProcessInfo.processName + "is own process");
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int b() {
        if (!MSC.isLoaded()) {
            return 21002;
        }
        com.iflytek.cloud.a.g.a.a.a("SpeechUtility start login");
        SpeechError a2 = new com.iflytek.cloud.a.e.b(this.f, this.b).a(this.b.e("usr"), this.b.e("pwd"));
        if (a2 == null) {
            return 0;
        }
        return a2.getErrorCode();
    }

    private SpeechComponent b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        boolean z = false;
        Iterator<SpeechComponent> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (str.equals(it.next().getPackageName())) {
                z = true;
                break;
            }
        }
        if (z) {
            return null;
        }
        SpeechComponent speechComponent = new SpeechComponent(str);
        this.d.add(speechComponent);
        return speechComponent;
    }

    private boolean c() {
        if (!MSC.isLoaded()) {
            return false;
        }
        com.iflytek.cloud.a.g.a.b.a("QMSPLogOut", null);
        if (MSC.isLoaded()) {
            return com.iflytek.cloud.a.e.a.a();
        }
        return true;
    }

    private boolean c(String str) {
        PackageManager packageManager = this.f.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        return packageManager.queryIntentActivities(intent, 1).size() > 0;
    }

    public static SpeechUtility createUtility(Context context, String str) {
        if (c == null) {
            com.iflytek.cloud.b.a aVar = new com.iflytek.cloud.b.a();
            aVar.b(str);
            if (aVar.a(SpeechConstant.FORCE_LOGIN, false) || a(context.getApplicationContext())) {
                c = new SpeechUtility(context, str);
            } else {
                com.iflytek.cloud.a.g.a.a.b("init failed, please call this method in your main process!");
                c = null;
            }
        }
        return c;
    }

    private void d() {
        if (checkServiceInstalled()) {
            a(UtilityConfig.ACTION_SPEECH_RECOGNIZER);
            a(UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
            a(UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
            a(UtilityConfig.ACTION_TEXT_UNDERSTANDER);
            a(UtilityConfig.ACTION_SPEECH_WAKEUP);
        }
    }

    private void e() {
        this.h = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        this.f.registerReceiver(this.h, intentFilter);
    }

    public static SpeechUtility getUtility() {
        return c;
    }

    protected boolean a() {
        try {
            return this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean checkServiceInstalled() {
        boolean z = false;
        int i = -1;
        try {
            PackageInfo packageInfo = this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
            if (packageInfo != null) {
                z = true;
                i = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (z != this.g || this.e != i) {
            this.g = z;
            this.e = i;
            if (SpeechRecognizer.getRecognizer() != null) {
                SpeechRecognizer.getRecognizer().a(this.f);
            }
            if (SpeechSynthesizer.getSynthesizer() != null) {
                SpeechSynthesizer.getSynthesizer().a(this.f);
            }
            if (SpeechUnderstander.getUnderstander() != null) {
                SpeechUnderstander.getUnderstander().a(this.f);
            }
            if (TextUnderstander.getTextUnderstander() != null) {
                TextUnderstander.getTextUnderstander().a(this.f);
            }
        }
        return z;
    }

    public boolean destroy() {
        boolean c2 = c != null ? c() : true;
        a aVar = this.h;
        if (aVar != null) {
            this.f.unregisterReceiver(aVar);
            this.h = null;
        }
        if (c2) {
            c = null;
            com.iflytek.cloud.a.g.a.a.a(" SpeechUtility destory success,mInstance=null");
        }
        return c2;
    }

    public String getComponentUrl() {
        StringBuffer stringBuffer = new StringBuffer(UtilityConfig.COMPONENT_URL);
        UtilityConfig.appendHttpParam(stringBuffer, TransferTable.COLUMN_KEY, URLEncoder.encode(Base64.encodeToString(UtilityConfig.getComponentUrlParam(this.f).getBytes(), 0)));
        UtilityConfig.appendHttpParam(stringBuffer, "version", "1.0");
        return stringBuffer.toString();
    }

    public d.a getEngineMode() {
        return this.a;
    }

    @Override // com.iflytek.cloud.a.d.d
    public String getParameter(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.b.g(str)) {
            return super.getParameter(str);
        }
        if (str.equals("tts") || str.equals("asr") || str.equals("all") || str.equals("ivw")) {
            try {
                return getPlusLocalInfo(str);
            } catch (Exception unused) {
                return "{ret:20004}";
            }
        }
        if (!MSC.isLoaded()) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes("utf-8");
            MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
            if (MSC.QMSPGetParam(bytes, mSCSessionInfo) == 0) {
                return new String(mSCSessionInfo.buffer, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public String getPlusLocalInfo(String str) throws JSONException {
        int i;
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (!checkServiceInstalled()) {
            i = 21001;
        } else if (getServiceVersion() < 97 || (10000 <= getServiceVersion() && getServiceVersion() <= 11000)) {
            i = ErrorCode.ERROR_VERSION_LOWER;
        } else {
            Cursor query = this.f.getContentResolver().query(Uri.parse("content://com.iflytek.speechcloud.providers.LocalResourceProvider"), null, str, null, null);
            int columnIndex = query.getColumnIndex(TAG_RESOURCE_CONTENT);
            if (query == null || !query.moveToFirst()) {
                str2 = "";
            } else {
                str2 = query.getString(columnIndex);
                Log.v("SpeechUtility", str2);
            }
            if (query != null) {
                query.close();
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(TAG_RESOURCE_RET, 0);
                jSONObject.put("result", new JSONObject(str2));
                return jSONObject.toString();
            }
            i = ErrorCode.ERROR_INVALID_RESULT;
        }
        jSONObject.put(TAG_RESOURCE_RET, i);
        return jSONObject.toString();
    }

    public int getServiceVersion() {
        if (this.e < 0) {
            try {
                PackageInfo packageInfo = this.f.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
                if (packageInfo != null) {
                    this.e = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return this.e;
    }

    public int openEngineSettings(String str) {
        String str2 = UtilityConfig.COMPONENT_PKG;
        try {
            Intent intent = new Intent();
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            if ("tts".equals(str) && c(UtilityConfig.SETTINGS_ACTION_TTS)) {
                str2 = UtilityConfig.SETTINGS_ACTION_TTS;
            } else if ("asr".equals(str) && c(UtilityConfig.SETTINGS_ACTION_ASR)) {
                str2 = UtilityConfig.SETTINGS_ACTION_ASR;
            } else if (c(UtilityConfig.SETTINGS_ACTION_MAIN)) {
                str2 = UtilityConfig.SETTINGS_ACTION_MAIN;
            }
            intent.setAction(str2);
            intent.addFlags(268435456);
            this.f.startActivity(intent);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 21002;
        }
    }

    public String[] queryAvailableEngines() {
        this.d.clear();
        d();
        ArrayList arrayList = new ArrayList();
        Iterator<SpeechComponent> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().getEngines());
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    @Override // com.iflytek.cloud.a.d.d
    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        super.setParameter(str, str2);
        if (!MSC.isLoaded() || "params".equals(str)) {
            return true;
        }
        try {
            return MSC.QMSPSetParam(str.getBytes("utf-8"), str2.getBytes("utf-8")) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
