package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.ITsmService;
import com.unionpay.tsmservice.data.Amount;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.request.AppDataUpdateRequestParams;
import com.unionpay.tsmservice.request.AppDeleteRequestParams;
import com.unionpay.tsmservice.request.AppDownloadApplyRequestParams;
import com.unionpay.tsmservice.request.AppDownloadRequestParams;
import com.unionpay.tsmservice.request.AppLockRequestParams;
import com.unionpay.tsmservice.request.AppUnlockRequestParams;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.ECashTopUpRequestParams;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.ExecuteCmdRequestParams;
import com.unionpay.tsmservice.request.GetAccountBalanceRequestParams;
import com.unionpay.tsmservice.request.GetAccountInfoRequestParams;
import com.unionpay.tsmservice.request.GetAppDetailRequestParams;
import com.unionpay.tsmservice.request.GetAppListRequestParams;
import com.unionpay.tsmservice.request.GetAppStatusRequestParams;
import com.unionpay.tsmservice.request.GetAssociatedAppRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoRequestParams;
import com.unionpay.tsmservice.request.GetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.unionpay.tsmservice.request.GetTransRecordRequestParams;
import com.unionpay.tsmservice.request.HideAppApplyRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class UPTsmAddon {
    private static UPTsmAddon a;
    private static ArrayList<UPTsmConnectionListener> b;
    private Context c;
    private ServiceConnection d = null;
    private ITsmService e = null;
    private boolean f = false;
    private HashMap<String, ITsmCallback> g = new HashMap<>();
    private HashMap<String, ITsmCallback> h = new HashMap<>();
    private HashMap<String, ITsmCallback> i = new HashMap<>();
    private HashMap<String, ITsmCallback> j = new HashMap<>();
    private HashMap<String, ITsmCallback> k = new HashMap<>();
    private HashMap<String, ITsmCallback> l = new HashMap<>();
    private HashMap<String, ITsmCallback> m = new HashMap<>();
    private HashMap<String, ITsmCallback> n = new HashMap<>();
    private HashMap<String, ITsmCallback> o = new HashMap<>();
    private HashMap<String, ITsmCallback> p = new HashMap<>();
    private HashMap<String, ITsmCallback> q = new HashMap<>();
    private HashMap<String, ITsmCallback> r = new HashMap<>();
    private HashMap<String, ITsmCallback> s = new HashMap<>();
    private HashMap<String, ITsmCallback> t = new HashMap<>();

    /* renamed from: u */
    private HashMap<String, ITsmCallback> f63u = new HashMap<>();
    private HashMap<String, ITsmCallback> v = new HashMap<>();
    private HashMap<String, ITsmCallback> w = new HashMap<>();
    private HashMap<String, ITsmCallback> x = new HashMap<>();
    private HashMap<String, ITsmCallback> y = new HashMap<>();
    private HashMap<String, ITsmCallback> z = new HashMap<>();
    private HashMap<String, ITsmCallback> A = new HashMap<>();
    private HashMap<String, ITsmCallback> B = new HashMap<>();
    private HashMap<String, ITsmCallback> C = new HashMap<>();
    private HashMap<String, ITsmCallback> D = new HashMap<>();
    private HashMap<String, ITsmCallback> E = new HashMap<>();
    private HashMap<String, ITsmCallback> F = new HashMap<>();
    private HashMap<String, ITsmCallback> G = new HashMap<>();
    private HashMap<String, ITsmCallback> H = new HashMap<>();
    private HashMap<String, ITsmCallback> I = new HashMap<>();
    private final Handler.Callback K = new Handler.Callback() { // from class: com.unionpay.tsmservice.UPTsmAddon.1
        AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public final synchronized boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                UPTsmAddon.a();
                return true;
            }
            if (i != 1) {
                return false;
            }
            UPTsmAddon.b();
            return true;
        }
    };
    private final Handler L = new Handler(Looper.getMainLooper(), this.K);
    private int[] J = new int[31];

    /* renamed from: com.unionpay.tsmservice.UPTsmAddon$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements Handler.Callback {
        AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public final synchronized boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                UPTsmAddon.a();
                return true;
            }
            if (i != 1) {
                return false;
            }
            UPTsmAddon.b();
            return true;
        }
    }

    /* renamed from: com.unionpay.tsmservice.UPTsmAddon$2 */
    /* loaded from: classes.dex */
    final class AnonymousClass2 implements ServiceConnection {
        AnonymousClass2() {
        }

        @Override // android.content.ServiceConnection
        public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            UPTsmAddon.this.f = true;
            UPTsmAddon.this.e = ITsmService.Stub.asInterface(iBinder);
            UPTsmAddon.this.L.sendEmptyMessage(0);
        }

        @Override // android.content.ServiceConnection
        public final synchronized void onServiceDisconnected(ComponentName componentName) {
            UPTsmAddon.this.f = false;
            UPTsmAddon.this.e = null;
            UPTsmAddon.this.L.sendEmptyMessage(1);
        }
    }

    /* loaded from: classes.dex */
    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    /* loaded from: classes.dex */
    private class a extends ITsmCallback.Stub {
        private int b;
        private int c;

        private a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        /* synthetic */ a(UPTsmAddon uPTsmAddon, int i, int i2, byte b) {
            this(i, i2);
        }

        @Override // com.unionpay.tsmservice.ITsmCallback
        public final void onError(String str, String str2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_ERROR_CODE, str);
            bundle.putString(Constant.KEY_ERROR_DESC, str2);
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                UPTsmAddon.this.J[this.b] = 0;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x01c1  */
        /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
        @Override // com.unionpay.tsmservice.ITsmCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onResult(android.os.Bundle r9) throws android.os.RemoteException {
            /*
                Method dump skipped, instructions count: 480
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.a.onResult(android.os.Bundle):void");
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        a = null;
        b = null;
    }

    private UPTsmAddon(Context context) {
        this.c = null;
        this.c = context;
        if (!a(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i, RequestParams requestParams, ITsmCallback iTsmCallback) throws RemoteException {
        return new SessionKeyReExchange(a, i, requestParams, iTsmCallback).reExchangeKey();
    }

    private static int a(int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        return new SessionKeyReExchange(a, i, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    static /* synthetic */ HashMap a(UPTsmAddon uPTsmAddon, int i) {
        switch (i) {
            case 0:
                return uPTsmAddon.g;
            case 1:
                return uPTsmAddon.h;
            case 2:
                return uPTsmAddon.j;
            case 3:
                return uPTsmAddon.i;
            case 4:
                return uPTsmAddon.l;
            case 5:
                return uPTsmAddon.k;
            case 6:
                return uPTsmAddon.y;
            case 7:
                return uPTsmAddon.s;
            case 8:
                return uPTsmAddon.t;
            case 9:
                return uPTsmAddon.m;
            case 10:
                return uPTsmAddon.r;
            case 11:
                return uPTsmAddon.p;
            case 12:
                return uPTsmAddon.x;
            case 13:
                return uPTsmAddon.w;
            case 14:
                return uPTsmAddon.C;
            case 15:
                return uPTsmAddon.n;
            case 16:
                return uPTsmAddon.A;
            case 17:
                return uPTsmAddon.B;
            case 18:
                return uPTsmAddon.o;
            case 19:
                return uPTsmAddon.q;
            case 20:
                return uPTsmAddon.f63u;
            case 21:
                return uPTsmAddon.D;
            case 22:
                return uPTsmAddon.v;
            case 23:
                return uPTsmAddon.z;
            case 24:
                return uPTsmAddon.E;
            case 25:
                return uPTsmAddon.F;
            case 26:
            case 27:
            default:
                return null;
            case 28:
                return uPTsmAddon.G;
            case 29:
                return uPTsmAddon.H;
            case 30:
                return uPTsmAddon.I;
        }
    }

    private static HashMap<String, String> a(HashMap<String, String> hashMap) {
        String str;
        if (hashMap == null) {
            return new HashMap<>();
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str2 : hashMap.keySet()) {
            if (str2 != null && (str = hashMap.get(str2)) != null) {
                hashMap2.put(new String(str2), new String(str));
            }
        }
        return hashMap2;
    }

    static /* synthetic */ void a() {
        ArrayList<UPTsmConnectionListener> arrayList = b;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<UPTsmConnectionListener> it = b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener next = it.next();
            if (next != null) {
                next.onTsmConnected();
            }
        }
    }

    static /* synthetic */ void a(ITsmCallback iTsmCallback, Bundle bundle) {
        if (iTsmCallback != null) {
            try {
                String string = bundle.getString(Constant.KEY_ERROR_CODE);
                if ("10000".equals(string)) {
                    iTsmCallback.onResult(bundle);
                } else {
                    iTsmCallback.onError(string, bundle.getString(Constant.KEY_ERROR_DESC));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean a(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ void b() {
        ArrayList<UPTsmConnectionListener> arrayList = b;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<UPTsmConnectionListener> it = b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener next = it.next();
            if (next != null) {
                next.onTsmDisconnected();
            }
        }
    }

    private static boolean b(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String c(String str) {
        try {
            return IUPJniInterface.eMG(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String d(String str) {
        try {
            return IUPJniInterface.dMG(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    public static UPTsmAddon getInstance(Context context) {
        if (context == null) {
            return null;
        }
        if (a == null) {
            a = new UPTsmAddon(context.getApplicationContext());
        }
        if (b == null) {
            b = new ArrayList<>();
        }
        return a;
    }

    public void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    public synchronized int appDataUpdate(AppDataUpdateRequestParams appDataUpdateRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i;
        if (appDataUpdateRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                AppDataUpdateRequestParams appDataUpdateRequestParams2 = new AppDataUpdateRequestParams();
                String reserve = appDataUpdateRequestParams.getReserve();
                AppID appID = appDataUpdateRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    appDataUpdateRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appDataUpdateRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int appDataUpdate = this.e.appDataUpdate(appDataUpdateRequestParams2, new a(this, 18, this.J[18], (byte) 0), iTsmProgressCallback);
                if (-2 == appDataUpdate) {
                    return a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDataUpdate == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.o;
                    int[] iArr = this.J;
                    int i2 = iArr[18];
                    iArr[18] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDataUpdate;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int appDelete(AppDeleteRequestParams appDeleteRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i;
        if (appDeleteRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                AppDeleteRequestParams appDeleteRequestParams2 = new AppDeleteRequestParams();
                String reserve = appDeleteRequestParams.getReserve();
                AppID appID = appDeleteRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    appDeleteRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appDeleteRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                HashMap hashMap = (HashMap) appDeleteRequestParams.getParams();
                if (hashMap == null) {
                    int appDelete = this.e.appDelete(appDeleteRequestParams2, new a(this, 17, this.J[17], (byte) 0), iTsmProgressCallback);
                    if (-2 == appDelete) {
                        return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    if (appDelete == 0) {
                        HashMap<String, ITsmCallback> hashMap2 = this.B;
                        int[] iArr = this.J;
                        int i2 = iArr[17];
                        iArr[17] = i2 + 1;
                        hashMap2.put(String.valueOf(i2), iTsmCallback);
                    }
                    return appDelete;
                }
                HashMap<String, String> a2 = a((HashMap<String, String>) hashMap);
                String str = a2.get(Constant.KEY_CARD_HOLDER_NAME);
                String str2 = a2.get(Constant.KEY_ID_TYPE);
                String str3 = a2.get(Constant.KEY_ID_NO);
                String str4 = a2.get(Constant.KEY_PAN);
                String str5 = a2.get(Constant.KEY_PIN);
                String str6 = a2.get(Constant.KEY_EXPIRY_DATE);
                String str7 = a2.get(Constant.KEY_CVN2);
                String str8 = a2.get(Constant.KEY_PHONE_NUMBER);
                String str9 = a2.get(Constant.KEY_SMS_AUTH_CODE);
                String str10 = a2.get(Constant.KEY_BALANCE);
                String str11 = a2.get(Constant.KEY_CARD_TYPE);
                if (!TextUtils.isEmpty(str)) {
                    a2.put(Constant.KEY_CARD_HOLDER_NAME, c(str));
                }
                if (!TextUtils.isEmpty(str2)) {
                    a2.put(Constant.KEY_ID_TYPE, c(str2));
                }
                if (!TextUtils.isEmpty(str3)) {
                    a2.put(Constant.KEY_ID_NO, c(str3));
                }
                if (!TextUtils.isEmpty(str4)) {
                    a2.put(Constant.KEY_PAN, c(str4));
                }
                if (!TextUtils.isEmpty(str5)) {
                    a2.put(Constant.KEY_PIN, str5);
                }
                if (!TextUtils.isEmpty(str6)) {
                    a2.put(Constant.KEY_EXPIRY_DATE, c(str6));
                }
                if (!TextUtils.isEmpty(str7)) {
                    a2.put(Constant.KEY_CVN2, c(str7));
                }
                if (!TextUtils.isEmpty(str8)) {
                    a2.put(Constant.KEY_PHONE_NUMBER, c(str8));
                }
                if (!TextUtils.isEmpty(str9)) {
                    a2.put(Constant.KEY_SMS_AUTH_CODE, c(str9));
                }
                if (!TextUtils.isEmpty(str10)) {
                    a2.put(Constant.KEY_BALANCE, c(str10));
                }
                if (!TextUtils.isEmpty(str11)) {
                    a2.put(Constant.KEY_CARD_TYPE, c(str11));
                }
                appDeleteRequestParams2.setParams(a2);
                int appDelete2 = this.e.appDelete(appDeleteRequestParams2, new a(this, 17, this.J[17], (byte) 0), iTsmProgressCallback);
                if (-2 == appDelete2) {
                    return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDelete2 == 0) {
                    HashMap<String, ITsmCallback> hashMap3 = this.B;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[17];
                    iArr2[17] = i3 + 1;
                    hashMap3.put(String.valueOf(i3), iTsmCallback);
                }
                return appDelete2;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int appDownload(AppDownloadRequestParams appDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i;
        if (appDownloadRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                AppDownloadRequestParams appDownloadRequestParams2 = new AppDownloadRequestParams();
                String reserve = appDownloadRequestParams.getReserve();
                AppID appID = appDownloadRequestParams.getAppID();
                String appName = appDownloadRequestParams.getAppName();
                if (!TextUtils.isEmpty(reserve)) {
                    appDownloadRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appDownloadRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                if (!TextUtils.isEmpty(appName)) {
                    appDownloadRequestParams2.setAppName(c(appName));
                }
                int appDownload = this.e.appDownload(appDownloadRequestParams2, new a(this, 16, this.J[16], (byte) 0), iTsmProgressCallback);
                if (-2 == appDownload) {
                    return a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDownload == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.A;
                    int[] iArr = this.J;
                    int i2 = iArr[16];
                    iArr[16] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDownload;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int appDownloadApply(AppDownloadApplyRequestParams appDownloadApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (appDownloadApplyRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(15, appDownloadApplyRequestParams, iTsmCallback);
                }
                AppDownloadApplyRequestParams appDownloadApplyRequestParams2 = new AppDownloadApplyRequestParams();
                String reserve = appDownloadApplyRequestParams.getReserve();
                AppID appID = appDownloadApplyRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    appDownloadApplyRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appDownloadApplyRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                HashMap hashMap = (HashMap) appDownloadApplyRequestParams.getParams();
                if (hashMap == null) {
                    int appDownloadApply = this.e.appDownloadApply(appDownloadApplyRequestParams2, new a(this, 15, this.J[15], (byte) 0));
                    if (-2 == appDownloadApply) {
                        return a(15, appDownloadApplyRequestParams, iTsmCallback);
                    }
                    if (appDownloadApply == 0) {
                        HashMap<String, ITsmCallback> hashMap2 = this.n;
                        int[] iArr = this.J;
                        int i2 = iArr[15];
                        iArr[15] = i2 + 1;
                        hashMap2.put(String.valueOf(i2), iTsmCallback);
                    }
                    return appDownloadApply;
                }
                HashMap<String, String> a2 = a((HashMap<String, String>) hashMap);
                String str = a2.get(Constant.KEY_ACCOUNT_LIMIT);
                String str2 = a2.get(Constant.KEY_ACCOUNT_TYPE);
                String str3 = a2.get(Constant.KEY_CARD_HOLDER_NAME);
                String str4 = a2.get(Constant.KEY_ID_TYPE);
                String str5 = a2.get(Constant.KEY_ID_NO);
                String str6 = a2.get(Constant.KEY_PAN);
                String str7 = a2.get(Constant.KEY_PIN);
                String str8 = a2.get(Constant.KEY_EXPIRY_DATE);
                String str9 = a2.get(Constant.KEY_CVN2);
                String str10 = a2.get(Constant.KEY_PHONE_NUMBER);
                String str11 = a2.get(Constant.KEY_SMS_AUTH_CODE);
                String str12 = a2.get(Constant.KEY_CARD_TYPE);
                if (!TextUtils.isEmpty(str)) {
                    a2.put(Constant.KEY_ACCOUNT_LIMIT, c(str));
                }
                if (!TextUtils.isEmpty(str2)) {
                    a2.put(Constant.KEY_ACCOUNT_TYPE, c(str2));
                }
                if (!TextUtils.isEmpty(str3)) {
                    a2.put(Constant.KEY_CARD_HOLDER_NAME, c(str3));
                }
                if (!TextUtils.isEmpty(str4)) {
                    a2.put(Constant.KEY_ID_TYPE, c(str4));
                }
                if (!TextUtils.isEmpty(str5)) {
                    a2.put(Constant.KEY_ID_NO, c(str5));
                }
                if (!TextUtils.isEmpty(str6)) {
                    a2.put(Constant.KEY_PAN, c(str6));
                }
                if (!TextUtils.isEmpty(str7)) {
                    a2.put(Constant.KEY_PIN, str7);
                }
                if (!TextUtils.isEmpty(str8)) {
                    a2.put(Constant.KEY_EXPIRY_DATE, c(str8));
                }
                if (!TextUtils.isEmpty(str9)) {
                    a2.put(Constant.KEY_CVN2, c(str9));
                }
                if (!TextUtils.isEmpty(str10)) {
                    a2.put(Constant.KEY_PHONE_NUMBER, c(str10));
                }
                if (!TextUtils.isEmpty(str11)) {
                    a2.put(Constant.KEY_SMS_AUTH_CODE, c(str11));
                }
                if (!TextUtils.isEmpty(str12)) {
                    a2.put(Constant.KEY_CARD_TYPE, c(str12));
                }
                appDownloadApplyRequestParams2.setParams(a2);
                int appDownloadApply2 = this.e.appDownloadApply(appDownloadApplyRequestParams2, new a(this, 15, this.J[15], (byte) 0));
                if (-2 == appDownloadApply2) {
                    return a(15, appDownloadApplyRequestParams, iTsmCallback);
                }
                if (appDownloadApply2 == 0) {
                    HashMap<String, ITsmCallback> hashMap3 = this.n;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[15];
                    iArr2[15] = i3 + 1;
                    hashMap3.put(String.valueOf(i3), iTsmCallback);
                }
                return appDownloadApply2;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int appLock(AppLockRequestParams appLockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (appLockRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(26, appLockRequestParams, iTsmCallback);
                }
                AppLockRequestParams appLockRequestParams2 = new AppLockRequestParams();
                String reserve = appLockRequestParams.getReserve();
                AppID appID = appLockRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    appLockRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appLockRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int appLock = this.e.appLock(appLockRequestParams2, iTsmCallback);
                if (-2 != appLock) {
                    return appLock;
                }
                return a(26, appLockRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public synchronized int appUnlock(AppUnlockRequestParams appUnlockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (appUnlockRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(27, appUnlockRequestParams, iTsmCallback);
                }
                AppUnlockRequestParams appUnlockRequestParams2 = new AppUnlockRequestParams();
                String reserve = appUnlockRequestParams.getReserve();
                AppID appID = appUnlockRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    appUnlockRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        appUnlockRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int appUnlock = this.e.appUnlock(appUnlockRequestParams2, iTsmCallback);
                if (-2 != appUnlock) {
                    return appUnlock;
                }
                return a(27, appUnlockRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public void bind() {
        if (this.d == null) {
            this.d = new ServiceConnection() { // from class: com.unionpay.tsmservice.UPTsmAddon.2
                AnonymousClass2() {
                }

                @Override // android.content.ServiceConnection
                public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    UPTsmAddon.this.f = true;
                    UPTsmAddon.this.e = ITsmService.Stub.asInterface(iBinder);
                    UPTsmAddon.this.L.sendEmptyMessage(0);
                }

                @Override // android.content.ServiceConnection
                public final synchronized void onServiceDisconnected(ComponentName componentName) {
                    UPTsmAddon.this.f = false;
                    UPTsmAddon.this.e = null;
                    UPTsmAddon.this.L.sendEmptyMessage(1);
                }
            };
        }
        if (this.f) {
            return;
        }
        Intent intent = new Intent("com.unionpay.tsmservice.UPTsmService");
        intent.setPackage("com.unionpay.tsmservice");
        this.c.bindService(intent, this.d, 1);
    }

    public synchronized int checkSSamsungPay(CheckSSamsungPayRequestParams checkSSamsungPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (checkSSamsungPayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(29, checkSSamsungPayRequestParams, iTsmCallback);
                }
                CheckSSamsungPayRequestParams checkSSamsungPayRequestParams2 = new CheckSSamsungPayRequestParams();
                if (checkSSamsungPayRequestParams != null) {
                    String reserve = checkSSamsungPayRequestParams.getReserve();
                    if (!TextUtils.isEmpty(reserve)) {
                        checkSSamsungPayRequestParams2.setReserve(c(reserve));
                    }
                }
                int checkSSamsungPay = this.e.checkSSamsungPay(checkSSamsungPayRequestParams2, new a(this, 29, this.J[29], (byte) 0));
                if (-2 == checkSSamsungPay) {
                    return a(29, checkSSamsungPayRequestParams, iTsmCallback);
                }
                if (checkSSamsungPay == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.H;
                    int[] iArr = this.J;
                    int i2 = iArr[29];
                    iArr[29] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return checkSSamsungPay;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int closeChannel(CloseChannelRequestParams closeChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (closeChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String channel = closeChannelRequestParams.getChannel();
        if (TextUtils.isEmpty(channel)) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(21, closeChannelRequestParams, iTsmCallback);
        }
        String c = c(channel);
        CloseChannelRequestParams closeChannelRequestParams2 = new CloseChannelRequestParams();
        closeChannelRequestParams2.setChannel(c);
        String reserve = closeChannelRequestParams.getReserve();
        if (!TextUtils.isEmpty(reserve)) {
            closeChannelRequestParams2.setReserve(c(reserve));
        }
        int closeChannel = this.e.closeChannel(closeChannelRequestParams2, new a(this, 21, this.J[21], (byte) 0));
        if (-2 == closeChannel) {
            return a(21, closeChannelRequestParams, iTsmCallback);
        }
        if (closeChannel == 0) {
            HashMap<String, ITsmCallback> hashMap = this.D;
            int[] iArr = this.J;
            int i = iArr[21];
            iArr[21] = i + 1;
            hashMap.put(String.valueOf(i), iTsmCallback);
        }
        return closeChannel;
    }

    public synchronized int eCashTopUp(ECashTopUpRequestParams eCashTopUpRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (eCashTopUpRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(19, eCashTopUpRequestParams, iTsmCallback);
                }
                ECashTopUpRequestParams eCashTopUpRequestParams2 = new ECashTopUpRequestParams();
                String reserve = eCashTopUpRequestParams.getReserve();
                AppID appID = eCashTopUpRequestParams.getAppID();
                String type = eCashTopUpRequestParams.getType();
                String amount = eCashTopUpRequestParams.getAmount();
                if (!TextUtils.isEmpty(reserve)) {
                    eCashTopUpRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        eCashTopUpRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                String encrpytPin = eCashTopUpRequestParams.getEncrpytPin();
                if (!TextUtils.isEmpty(encrpytPin)) {
                    eCashTopUpRequestParams2.setEncrpytPin(encrpytPin);
                }
                if (!TextUtils.isEmpty(type)) {
                    eCashTopUpRequestParams2.setType(c(type));
                }
                if (!TextUtils.isEmpty(amount)) {
                    eCashTopUpRequestParams2.setAmount(c(amount));
                }
                int eCashTopUp = this.e.eCashTopUp(eCashTopUpRequestParams2, new a(this, 19, this.J[19], (byte) 0));
                if (-2 == eCashTopUp) {
                    return a(19, eCashTopUpRequestParams, iTsmCallback);
                }
                if (eCashTopUp == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.q;
                    int[] iArr = this.J;
                    int i2 = iArr[19];
                    iArr[19] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return eCashTopUp;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (encryptDataRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(23, encryptDataRequestParams, iTsmCallback);
        }
        EncryptDataRequestParams encryptDataRequestParams2 = new EncryptDataRequestParams();
        String reserve = encryptDataRequestParams.getReserve();
        ArrayList arrayList = (ArrayList) encryptDataRequestParams.getData();
        if (!TextUtils.isEmpty(reserve)) {
            encryptDataRequestParams2.setReserve(c(reserve));
        }
        if (arrayList != null) {
            int size = arrayList.size();
            if (size == 0) {
                return -3;
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < size; i++) {
                String str = (String) arrayList.get(i);
                if (!TextUtils.isEmpty(str)) {
                    arrayList2.add(c(str));
                }
            }
            encryptDataRequestParams2.setData(arrayList2);
        }
        int encryptData = this.e.encryptData(encryptDataRequestParams2, new a(this, 23, this.J[23], (byte) 0));
        if (-2 == encryptData) {
            return a(23, encryptDataRequestParams, iTsmCallback);
        }
        if (encryptData == 0) {
            HashMap<String, ITsmCallback> hashMap = this.z;
            int[] iArr = this.J;
            int i2 = iArr[23];
            iArr[23] = i2 + 1;
            hashMap.put(String.valueOf(i2), iTsmCallback);
        }
        return encryptData;
    }

    public int exchangeKey(String str, String[] strArr) throws RemoteException {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.e;
        if (iTsmService != null) {
            return iTsmService.exchangeKey(str, strArr);
        }
        return -1;
    }

    public synchronized int executeCmd(ExecuteCmdRequestParams executeCmdRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i;
        if (executeCmdRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                ExecuteCmdRequestParams executeCmdRequestParams2 = new ExecuteCmdRequestParams();
                String reserve = executeCmdRequestParams.getReserve();
                String ssid = executeCmdRequestParams.getSsid();
                String sign = executeCmdRequestParams.getSign();
                if (!TextUtils.isEmpty(reserve)) {
                    executeCmdRequestParams2.setReserve(c(reserve));
                }
                if (!TextUtils.isEmpty(ssid)) {
                    executeCmdRequestParams2.setSsid(c(ssid));
                }
                if (!TextUtils.isEmpty(sign)) {
                    executeCmdRequestParams2.setSign(c(sign));
                }
                int executeCmd = this.e.executeCmd(executeCmdRequestParams2, new a(this, 25, this.J[25], (byte) 0), iTsmProgressCallback);
                if (-2 == executeCmd) {
                    return a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (executeCmd == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.F;
                    int[] iArr = this.J;
                    int i2 = iArr[25];
                    iArr[25] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return executeCmd;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAccountBalance(GetAccountBalanceRequestParams getAccountBalanceRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getAccountBalanceRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(8, getAccountBalanceRequestParams, iTsmCallback);
                }
                GetAccountBalanceRequestParams getAccountBalanceRequestParams2 = new GetAccountBalanceRequestParams();
                String reserve = getAccountBalanceRequestParams.getReserve();
                AppID appID = getAccountBalanceRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    getAccountBalanceRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getAccountBalanceRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                String encryptPin = getAccountBalanceRequestParams.getEncryptPin();
                if (!TextUtils.isEmpty(encryptPin)) {
                    getAccountBalanceRequestParams2.setEncryptPin(encryptPin);
                }
                int accountBalance = this.e.getAccountBalance(getAccountBalanceRequestParams2, new a(this, 8, this.J[8], (byte) 0));
                if (-2 == accountBalance) {
                    return a(8, getAccountBalanceRequestParams, iTsmCallback);
                }
                if (accountBalance == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.t;
                    int[] iArr = this.J;
                    int i2 = iArr[8];
                    iArr[8] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return accountBalance;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAccountInfo(GetAccountInfoRequestParams getAccountInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getAccountInfoRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(7, getAccountInfoRequestParams, iTsmCallback);
                }
                GetAccountInfoRequestParams getAccountInfoRequestParams2 = new GetAccountInfoRequestParams();
                String reserve = getAccountInfoRequestParams.getReserve();
                AppID appID = getAccountInfoRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    getAccountInfoRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getAccountInfoRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int accountInfo = this.e.getAccountInfo(getAccountInfoRequestParams2, new a(this, 7, this.J[7], (byte) 0));
                if (-2 == accountInfo) {
                    return a(7, getAccountInfoRequestParams, iTsmCallback);
                }
                if (accountInfo == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.s;
                    int[] iArr = this.J;
                    int i2 = iArr[7];
                    iArr[7] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return accountInfo;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAppDetail(GetAppDetailRequestParams getAppDetailRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getAppDetailRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(4, getAppDetailRequestParams, iTsmCallback);
                }
                GetAppDetailRequestParams getAppDetailRequestParams2 = new GetAppDetailRequestParams();
                String reserve = getAppDetailRequestParams.getReserve();
                AppID appID = getAppDetailRequestParams.getAppID();
                String transType = getAppDetailRequestParams.getTransType();
                if (!TextUtils.isEmpty(reserve)) {
                    getAppDetailRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getAppDetailRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                if (!TextUtils.isEmpty(transType)) {
                    getAppDetailRequestParams2.setTransType(c(transType));
                }
                int appDetail = this.e.getAppDetail(getAppDetailRequestParams2, new a(this, 4, this.J[4], (byte) 0));
                if (-2 == appDetail) {
                    return a(4, getAppDetailRequestParams, iTsmCallback);
                }
                if (appDetail == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.l;
                    int[] iArr = this.J;
                    int i2 = iArr[4];
                    iArr[4] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appDetail;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAppList(GetAppListRequestParams getAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getAppListRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(2, getAppListRequestParams, iTsmCallback);
                }
                GetAppListRequestParams getAppListRequestParams2 = new GetAppListRequestParams();
                String reserve = getAppListRequestParams.getReserve();
                String keyword = getAppListRequestParams.getKeyword();
                String[] status = getAppListRequestParams.getStatus();
                if (!TextUtils.isEmpty(reserve)) {
                    getAppListRequestParams2.setReserve(c(reserve));
                }
                if (!TextUtils.isEmpty(keyword)) {
                    getAppListRequestParams2.setKeyword(c(keyword));
                }
                if (status != null) {
                    int length = status.length;
                    String[] strArr = new String[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        if (!TextUtils.isEmpty(status[i2])) {
                            strArr[i2] = c(status[i2]);
                        }
                    }
                    getAppListRequestParams2.setStatus(strArr);
                }
                int appList = this.e.getAppList(getAppListRequestParams2, new a(this, 2, this.J[2], (byte) 0));
                if (-2 == appList) {
                    return a(2, getAppListRequestParams, iTsmCallback);
                }
                if (appList == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.j;
                    int[] iArr = this.J;
                    int i3 = iArr[2];
                    iArr[2] = i3 + 1;
                    hashMap.put(String.valueOf(i3), iTsmCallback);
                }
                return appList;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAppStatus(GetAppStatusRequestParams getAppStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getAppStatusRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(5, getAppStatusRequestParams, iTsmCallback);
                }
                GetAppStatusRequestParams getAppStatusRequestParams2 = new GetAppStatusRequestParams();
                String reserve = getAppStatusRequestParams.getReserve();
                AppID appID = getAppStatusRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    getAppStatusRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getAppStatusRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int appStatus = this.e.getAppStatus(getAppStatusRequestParams2, new a(this, 5, this.J[5], (byte) 0));
                if (-2 == appStatus) {
                    return a(5, getAppStatusRequestParams, iTsmCallback);
                }
                if (appStatus == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.k;
                    int[] iArr = this.J;
                    int i2 = iArr[5];
                    iArr[5] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return appStatus;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getAssociatedApp(GetAssociatedAppRequestParams getAssociatedAppRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (getAssociatedAppRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String encryptPan = getAssociatedAppRequestParams.getEncryptPan();
        if (TextUtils.isEmpty(encryptPan)) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(1, getAssociatedAppRequestParams, iTsmCallback);
        }
        GetAssociatedAppRequestParams getAssociatedAppRequestParams2 = new GetAssociatedAppRequestParams();
        String reserve = getAssociatedAppRequestParams.getReserve();
        if (!TextUtils.isEmpty(reserve)) {
            getAssociatedAppRequestParams2.setReserve(c(reserve));
        }
        getAssociatedAppRequestParams2.setEncryptPan(c(encryptPan));
        int associatedApp = this.e.getAssociatedApp(getAssociatedAppRequestParams2, new a(this, 1, this.J[1], (byte) 0));
        if (-2 == associatedApp) {
            return a(1, getAssociatedAppRequestParams, iTsmCallback);
        }
        if (associatedApp == 0) {
            HashMap<String, ITsmCallback> hashMap = this.h;
            int[] iArr = this.J;
            int i = iArr[1];
            iArr[1] = i + 1;
            hashMap.put(String.valueOf(i), iTsmCallback);
        }
        return associatedApp;
    }

    public synchronized int getCardInfo(GetCardInfoRequestParams getCardInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (getCardInfoRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String[] appAID = getCardInfoRequestParams.getAppAID();
        int length = appAID.length;
        if (appAID != null && length != 0) {
            int i = 0;
            while (i < length && appAID[i] == null) {
                i++;
            }
            if (i == length) {
                return -3;
            }
            if (this.e == null) {
                return -1;
            }
            if (!b(this.c.getPackageName())) {
                return a(6, getCardInfoRequestParams, iTsmCallback);
            }
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                if (appAID[i2] == null) {
                    strArr[i2] = appAID[i2];
                } else {
                    strArr[i2] = c(appAID[i2]);
                }
            }
            GetCardInfoRequestParams getCardInfoRequestParams2 = new GetCardInfoRequestParams();
            getCardInfoRequestParams2.setAppAID(strArr);
            String reserve = getCardInfoRequestParams.getReserve();
            if (!TextUtils.isEmpty(reserve)) {
                getCardInfoRequestParams2.setReserve(c(reserve));
            }
            int cardInfo = this.e.getCardInfo(getCardInfoRequestParams2, new a(this, 6, this.J[6], (byte) 0));
            if (-2 == cardInfo) {
                return a(6, getCardInfoRequestParams, iTsmCallback);
            }
            if (cardInfo == 0) {
                HashMap<String, ITsmCallback> hashMap = this.y;
                int[] iArr = this.J;
                int i3 = iArr[6];
                iArr[6] = i3 + 1;
                hashMap.put(String.valueOf(i3), iTsmCallback);
            }
            return cardInfo;
        }
        return -3;
    }

    public synchronized int getCardInfoBySamsungPay(GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getCardInfoBySpayRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(28, getCardInfoBySpayRequestParams, iTsmCallback);
                }
                GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams2 = new GetCardInfoBySpayRequestParams();
                String reserve = getCardInfoBySpayRequestParams.getReserve();
                if (!TextUtils.isEmpty(reserve)) {
                    getCardInfoBySpayRequestParams2.setReserve(c(reserve));
                }
                Amount amount = getCardInfoBySpayRequestParams.getAmount();
                if (amount != null) {
                    String currencyType = amount.getCurrencyType();
                    String productPrice = amount.getProductPrice();
                    Amount amount2 = new Amount();
                    if (!TextUtils.isEmpty(currencyType)) {
                        amount2.setCurrencyType(c(currencyType));
                    }
                    if (!TextUtils.isEmpty(productPrice)) {
                        amount2.setProductPrice(c(productPrice));
                    }
                    getCardInfoBySpayRequestParams2.setAmount(amount2);
                }
                this.G.put(String.valueOf(this.J[28]), iTsmCallback);
                ITsmService iTsmService = this.e;
                int[] iArr = this.J;
                int i2 = iArr[28];
                iArr[28] = i2 + 1;
                int cardInfoBySamsungPay = iTsmService.getCardInfoBySamsungPay(getCardInfoBySpayRequestParams2, new a(this, 28, i2, (byte) 0));
                if (cardInfoBySamsungPay != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.G;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[28] - 1;
                    iArr2[28] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 != cardInfoBySamsungPay) {
                    return cardInfoBySamsungPay;
                }
                return a(28, getCardInfoBySpayRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public Context getContext() {
        return this.c;
    }

    public synchronized int getDefaultCard(GetDefaultCardRequestParams getDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(13, getDefaultCardRequestParams, iTsmCallback);
                }
                GetDefaultCardRequestParams getDefaultCardRequestParams2 = new GetDefaultCardRequestParams();
                if (getDefaultCardRequestParams != null) {
                    String reserve = getDefaultCardRequestParams.getReserve();
                    if (!TextUtils.isEmpty(reserve)) {
                        getDefaultCardRequestParams2.setReserve(c(reserve));
                    }
                }
                int defaultCard = this.e.getDefaultCard(getDefaultCardRequestParams2, new a(this, 13, this.J[13], (byte) 0));
                if (-2 == defaultCard) {
                    return a(13, getDefaultCardRequestParams, iTsmCallback);
                }
                if (defaultCard == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.w;
                    int[] iArr = this.J;
                    int i2 = iArr[13];
                    iArr[13] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return defaultCard;
            }
            i = -1;
        }
        return i;
    }

    public int getListenerCount() {
        ArrayList<UPTsmConnectionListener> arrayList = b;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getPubKey(int i, String[] strArr) throws RemoteException {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.e;
        if (iTsmService != null) {
            return iTsmService.getPubKey(i, strArr);
        }
        return -1;
    }

    public synchronized int getSEAppList(GetSeAppListRequestParams getSeAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(3, getSeAppListRequestParams, iTsmCallback);
                }
                GetSeAppListRequestParams getSeAppListRequestParams2 = new GetSeAppListRequestParams();
                if (getSeAppListRequestParams != null) {
                    String reserve = getSeAppListRequestParams.getReserve();
                    if (!TextUtils.isEmpty(reserve)) {
                        getSeAppListRequestParams2.setReserve(c(reserve));
                    }
                }
                int sEAppList = this.e.getSEAppList(getSeAppListRequestParams2, new a(this, 3, this.J[3], (byte) 0));
                if (-2 == sEAppList) {
                    return a(3, getSeAppListRequestParams, iTsmCallback);
                }
                if (sEAppList == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.i;
                    int[] iArr = this.J;
                    int i2 = iArr[3];
                    iArr[3] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return sEAppList;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getSMSAuthCode(GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getSMSAuthCodeRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(11, getSMSAuthCodeRequestParams, iTsmCallback);
                }
                GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams2 = new GetSMSAuthCodeRequestParams();
                String reserve = getSMSAuthCodeRequestParams.getReserve();
                AppID appID = getSMSAuthCodeRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    getSMSAuthCodeRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getSMSAuthCodeRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                String pan = getSMSAuthCodeRequestParams.getPan();
                String msisdn = getSMSAuthCodeRequestParams.getMsisdn();
                if (!TextUtils.isEmpty(pan)) {
                    getSMSAuthCodeRequestParams2.setPan(c(pan));
                }
                if (!TextUtils.isEmpty(msisdn)) {
                    getSMSAuthCodeRequestParams2.setMsisdn(c(msisdn));
                }
                int sMSAuthCode = this.e.getSMSAuthCode(getSMSAuthCodeRequestParams2, new a(this, 11, this.J[11], (byte) 0));
                if (-2 == sMSAuthCode) {
                    return a(11, getSMSAuthCodeRequestParams, iTsmCallback);
                }
                if (sMSAuthCode == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.p;
                    int[] iArr = this.J;
                    int i2 = iArr[11];
                    iArr[11] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return sMSAuthCode;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getSeId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(12, getSeIdRequestParams, iTsmCallback);
                }
                GetSeIdRequestParams getSeIdRequestParams2 = new GetSeIdRequestParams();
                if (getSeIdRequestParams != null) {
                    String reserve = getSeIdRequestParams.getReserve();
                    if (!TextUtils.isEmpty(reserve)) {
                        getSeIdRequestParams2.setReserve(c(reserve));
                    }
                }
                this.x.put(String.valueOf(this.J[12]), iTsmCallback);
                ITsmService iTsmService = this.e;
                int[] iArr = this.J;
                int i2 = iArr[12];
                iArr[12] = i2 + 1;
                int sEId = iTsmService.getSEId(getSeIdRequestParams2, new a(this, 12, i2, (byte) 0));
                if (sEId != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.x;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[12] - 1;
                    iArr2[12] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 != sEId) {
                    return sEId;
                }
                return a(12, getSeIdRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getTransElements(GetTransElementsRequestParams getTransElementsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getTransElementsRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(9, getTransElementsRequestParams, iTsmCallback);
                }
                GetTransElementsRequestParams getTransElementsRequestParams2 = new GetTransElementsRequestParams();
                String reserve = getTransElementsRequestParams.getReserve();
                AppID appID = getTransElementsRequestParams.getAppID();
                String transType = getTransElementsRequestParams.getTransType();
                if (!TextUtils.isEmpty(reserve)) {
                    getTransElementsRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getTransElementsRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                if (!TextUtils.isEmpty(transType)) {
                    getTransElementsRequestParams2.setTransType(c(transType));
                }
                int transElements = this.e.getTransElements(getTransElementsRequestParams2, new a(this, 9, this.J[9], (byte) 0));
                if (-2 == transElements) {
                    return a(9, getTransElementsRequestParams, iTsmCallback);
                }
                if (transElements == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.m;
                    int[] iArr = this.J;
                    int i2 = iArr[9];
                    iArr[9] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return transElements;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int getTransRecord(GetTransRecordRequestParams getTransRecordRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (getTransRecordRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(10, getTransRecordRequestParams, iTsmCallback);
                }
                GetTransRecordRequestParams getTransRecordRequestParams2 = new GetTransRecordRequestParams();
                String reserve = getTransRecordRequestParams.getReserve();
                AppID appID = getTransRecordRequestParams.getAppID();
                if (!TextUtils.isEmpty(reserve)) {
                    getTransRecordRequestParams2.setReserve(c(reserve));
                }
                if (appID != null) {
                    String appAid = appID.getAppAid();
                    String appVersion = appID.getAppVersion();
                    if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                        getTransRecordRequestParams2.setAppID(new AppID(c(appAid), c(appVersion)));
                    }
                }
                int transRecord = this.e.getTransRecord(getTransRecordRequestParams2, new a(this, 10, this.J[10], (byte) 0));
                if (-2 == transRecord) {
                    return a(10, getTransRecordRequestParams, iTsmCallback);
                }
                if (transRecord == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.r;
                    int[] iArr = this.J;
                    int i2 = iArr[10];
                    iArr[10] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return transRecord;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int hideAppApply(HideAppApplyRequestParams hideAppApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (hideAppApplyRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String applyId = hideAppApplyRequestParams.getApplyId();
        if (TextUtils.isEmpty(applyId)) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(24, hideAppApplyRequestParams, iTsmCallback);
        }
        String c = c(applyId);
        HideAppApplyRequestParams hideAppApplyRequestParams2 = new HideAppApplyRequestParams();
        hideAppApplyRequestParams2.setApplyId(c);
        String reserve = hideAppApplyRequestParams.getReserve();
        if (!TextUtils.isEmpty(reserve)) {
            hideAppApplyRequestParams2.setReserve(c(reserve));
        }
        int hideAppApply = this.e.hideAppApply(hideAppApplyRequestParams2, new a(this, 24, this.J[24], (byte) 0));
        if (-2 == hideAppApply) {
            return a(24, hideAppApplyRequestParams, iTsmCallback);
        }
        if (hideAppApply == 0) {
            HashMap<String, ITsmCallback> hashMap = this.E;
            int[] iArr = this.J;
            int i = iArr[24];
            iArr[24] = i + 1;
            hashMap.put(String.valueOf(i), iTsmCallback);
        }
        return hideAppApply;
    }

    public synchronized int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(0, initRequestParams, iTsmCallback);
                }
                InitRequestParams initRequestParams2 = new InitRequestParams();
                if (initRequestParams != null) {
                    String reserve = initRequestParams.getReserve();
                    String signature = initRequestParams.getSignature();
                    if (!TextUtils.isEmpty(reserve)) {
                        initRequestParams2.setReserve(c(reserve));
                    }
                    if (!TextUtils.isEmpty(signature)) {
                        initRequestParams2.setSignature(c(signature));
                    }
                }
                this.g.put(String.valueOf(this.J[0]), iTsmCallback);
                ITsmService iTsmService = this.e;
                int[] iArr = this.J;
                int i2 = iArr[0];
                iArr[0] = i2 + 1;
                int init = iTsmService.init(initRequestParams2, new a(this, 0, i2, (byte) 0));
                if (init != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.g;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[0] - 1;
                    iArr2[0] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 != init) {
                    return init;
                }
                return a(0, initRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public boolean isConnected() {
        return this.f;
    }

    public synchronized int openChannel(OpenChannelRequestParams openChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (openChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = openChannelRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(20, openChannelRequestParams, iTsmCallback);
        }
        String c = c(appAID);
        OpenChannelRequestParams openChannelRequestParams2 = new OpenChannelRequestParams();
        openChannelRequestParams2.setAppAID(c);
        String reserve = openChannelRequestParams.getReserve();
        if (!TextUtils.isEmpty(reserve)) {
            openChannelRequestParams2.setReserve(c(reserve));
        }
        int openChannel = this.e.openChannel(openChannelRequestParams2, new a(this, 20, this.J[20], (byte) 0));
        if (-2 == openChannel) {
            return a(20, openChannelRequestParams, iTsmCallback);
        }
        if (openChannel == 0) {
            HashMap<String, ITsmCallback> hashMap = this.f63u;
            int[] iArr = this.J;
            int i = iArr[20];
            iArr[20] = i + 1;
            hashMap.put(String.valueOf(i), iTsmCallback);
        }
        return openChannel;
    }

    public void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    public synchronized int sendApdu(SendApduRequestParams sendApduRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (sendApduRequestParams == null || iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(22, sendApduRequestParams, iTsmCallback);
                }
                SendApduRequestParams sendApduRequestParams2 = new SendApduRequestParams();
                String reserve = sendApduRequestParams.getReserve();
                String channel = sendApduRequestParams.getChannel();
                String hexApdu = sendApduRequestParams.getHexApdu();
                if (!TextUtils.isEmpty(reserve)) {
                    sendApduRequestParams2.setReserve(c(reserve));
                }
                if (!TextUtils.isEmpty(channel)) {
                    sendApduRequestParams2.setChannel(c(channel));
                }
                if (!TextUtils.isEmpty(hexApdu)) {
                    sendApduRequestParams2.setHexApdu(c(hexApdu));
                }
                int sendApdu = this.e.sendApdu(sendApduRequestParams2, new a(this, 22, this.J[22], (byte) 0));
                if (-2 == sendApdu) {
                    return a(22, sendApduRequestParams, iTsmCallback);
                }
                if (sendApdu == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.v;
                    int[] iArr = this.J;
                    int i2 = iArr[22];
                    iArr[22] = i2 + 1;
                    hashMap.put(String.valueOf(i2), iTsmCallback);
                }
                return sendApdu;
            }
            i = -1;
        }
        return i;
    }

    public synchronized int setDefaultCard(SetDefaultCardRequestParams setDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (setDefaultCardRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = setDefaultCardRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        if (!b(this.c.getPackageName())) {
            return a(14, setDefaultCardRequestParams, iTsmCallback);
        }
        String c = c(appAID);
        SetDefaultCardRequestParams setDefaultCardRequestParams2 = new SetDefaultCardRequestParams();
        setDefaultCardRequestParams2.setAppAID(c);
        String reserve = setDefaultCardRequestParams.getReserve();
        if (!TextUtils.isEmpty(reserve)) {
            setDefaultCardRequestParams2.setReserve(c(reserve));
        }
        int defaultCard = this.e.setDefaultCard(setDefaultCardRequestParams2, new a(this, 14, this.J[14], (byte) 0));
        if (-2 == defaultCard) {
            return a(14, setDefaultCardRequestParams, iTsmCallback);
        }
        if (defaultCard == 0) {
            HashMap<String, ITsmCallback> hashMap = this.C;
            int[] iArr = this.J;
            int i = iArr[14];
            iArr[14] = i + 1;
            hashMap.put(String.valueOf(i), iTsmCallback);
        }
        return defaultCard;
    }

    public synchronized int setSamsungDefaultWallet(SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i;
        if (iTsmCallback == null) {
            i = -3;
        } else {
            if (this.e != null) {
                if (!b(this.c.getPackageName())) {
                    return a(30, setSamsungDefWalletRequestParams, iTsmCallback);
                }
                SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams2 = new SetSamsungDefWalletRequestParams();
                if (setSamsungDefWalletRequestParams != null) {
                    String reserve = setSamsungDefWalletRequestParams.getReserve();
                    if (!TextUtils.isEmpty(reserve)) {
                        setSamsungDefWalletRequestParams2.setReserve(c(reserve));
                    }
                }
                this.I.put(String.valueOf(this.J[30]), iTsmCallback);
                ITsmService iTsmService = this.e;
                int[] iArr = this.J;
                int i2 = iArr[30];
                iArr[30] = i2 + 1;
                int samsungDefaultWallet = iTsmService.setSamsungDefaultWallet(setSamsungDefWalletRequestParams2, new a(this, 30, i2, (byte) 0));
                if (samsungDefaultWallet != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.I;
                    int[] iArr2 = this.J;
                    int i3 = iArr2[30] - 1;
                    iArr2[30] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 != samsungDefaultWallet) {
                    return samsungDefaultWallet;
                }
                return a(30, setSamsungDefWalletRequestParams, iTsmCallback);
            }
            i = -1;
        }
        return i;
    }

    public void unbind() {
        ServiceConnection serviceConnection = this.d;
        if (serviceConnection == null || !this.f) {
            return;
        }
        this.c.unbindService(serviceConnection);
        this.f = false;
    }
}
