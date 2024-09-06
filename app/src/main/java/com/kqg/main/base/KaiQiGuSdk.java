package com.kqg.main.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.m.g.b;
import com.alipay.sdk.m.h.c;
import com.alipay.sdk.m.o.a;
import com.alipay.sdk.m.q.k;
import com.android.pc.ioc.internet.FastHttp;
import com.android.pc.ioc.internet.InternetConfig;
import com.android.pc.ioc.internet.ResponseEntity;
import com.android.pc.util.Handler_System;
import com.android.pc.util.MD5;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechEvent;
import com.ipaynow.wechatpay.plugin.utils.MerchantTools;
import com.kqg.main.activity.AccountManagerActivity;
import com.kqg.main.activity.AgreeContentActivity;
import com.kqg.main.activity.BindActivity;
import com.kqg.main.activity.BindToEnterPassword;
import com.kqg.main.activity.CertificationActivity;
import com.kqg.main.activity.CheckAppPermissionsActivity;
import com.kqg.main.activity.FindPasswordByEmail;
import com.kqg.main.activity.FindPasswordByPhone;
import com.kqg.main.activity.ForgetPwdActivity;
import com.kqg.main.activity.LoginActivity;
import com.kqg.main.activity.PayConfirmActivity;
import com.kqg.main.activity.PayResultActivity;
import com.kqg.main.activity.PaySelectActivity;
import com.kqg.main.activity.QuickLoginActivity;
import com.kqg.main.activity.QuickRegistActivity;
import com.kqg.main.activity.QuitGame;
import com.kqg.main.activity.RegisterActivity;
import com.kqg.main.activity.WelcomeActivity;
import com.kqg.main.callback.InstallOrUpdateCallBack;
import com.kqg.main.callback.OnInitCallBackListener;
import com.kqg.main.callback.OnLoginCallBackListener;
import com.kqg.main.callback.OnPayCallBackListener;
import com.kqg.main.callback.OnQuitGameBackListener;
import com.kqg.main.constant.KV;
import com.kqg.main.model.ActivityResult;
import com.kqg.main.model.AliPayInfor;
import com.kqg.main.model.AsyncMessage;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.CommonEntity;
import com.kqg.main.model.Login;
import com.kqg.main.model.MainThreadMessage;
import com.kqg.main.model.Message;
import com.kqg.main.model.PayInfor;
import com.kqg.main.model.PayResult;
import com.kqg.main.model.PaySelectTypeManager;
import com.kqg.main.model.UpPayInfor;
import com.kqg.main.model.User;
import com.kqg.main.model.UserManager;
import com.kqg.main.model.WxPayInfor;
import com.kqg.main.utils.LogUtil;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import com.kqg.main.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.unionpay.tsmservice.data.Constant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class KaiQiGuSdk implements EventBusHandler {
    public static String MAIN_URI = "http://app.vn.hi365.com/";
    public static String bindEmailNumber = "";
    public static String bindPhoneNumber = "";
    public static boolean checkCert = false;
    private static KaiQiGuSdk instance = null;
    public static boolean isNeedCer = false;
    public static String isNeedCertifica = "1";
    private static boolean isNeedLogin = false;
    private Activity act;
    private Context ctx;
    private boolean debug = false;
    public UiHandler handler;
    private ConfigInfor infor;
    private OnInitCallBackListener initListener;
    public OnLoginCallBackListener loginListener;
    private Message msg1;
    public OnPayCallBackListener payListener;
    private OnQuitGameBackListener quitListener;

    /* loaded from: classes.dex */
    interface OnCertificationListener {
        void onFail(String str);

        void onSuccess();
    }

    /* loaded from: classes.dex */
    interface onNetBack {
        void onBack(JSONObject jSONObject);
    }

    private void findPwdByHelper() {
    }

    private boolean verify(String str, String str2, String str3) {
        return false;
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventAsync(AsyncMessage asyncMessage) {
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventMainThread(MainThreadMessage mainThreadMessage) {
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean z, String str) {
        this.debug = z;
        if (str != null && !"".equals(str)) {
            MAIN_URI = str;
        } else if (this.debug) {
            UiUtils.showImageToast("当前使用的测试地址,记得喊服务器关闭1111验证码自动通过");
            MAIN_URI = "https://app-t.cn.hi365.com/";
        } else {
            MAIN_URI = "http://app.vn.hi365.com/";
        }
    }

    public Activity getAct() {
        return this.act;
    }

    public ConfigInfor getCfg() {
        return this.infor;
    }

    public void setAct(Activity activity) {
        this.act = activity;
    }

    public Context getCtx() {
        return this.ctx;
    }

    public void setCtx(Context context) {
        this.ctx = context;
    }

    public String getAppId() {
        return this.infor.getAppId();
    }

    public void initCfg(ConfigInfor configInfor) {
        this.infor = configInfor;
        if (configInfor == null) {
            throw new NullPointerException("ConfigInfor  is null !");
        }
        setCtx(configInfor.getCtx());
        checkInfor();
    }

    public void fillDeviceInfor() {
        ConfigInfor configInfor = this.infor;
        if (configInfor != null) {
            configInfor.fillDeviceInfor();
        }
    }

    public String addDeviceInfor() {
        return this.infor.appendDeviceInfor();
    }

    private KaiQiGuSdk() {
    }

    private void checkInfor() {
        if (ValidatorUtils.validatorEmptyString(this.infor.getAppId()) || this.infor.getAppKey() == 0) {
            throw new RuntimeException("appId is null or appKey is 0 error !");
        }
        if (this.ctx == null) {
            throw new RuntimeException("ctx is null !");
        }
    }

    public void doInit(Activity activity, OnInitCallBackListener onInitCallBackListener) {
        setAct(activity);
        checkInfor();
        this.act.startActivity(new Intent(this.act, (Class<?>) CheckAppPermissionsActivity.class));
        this.initListener = onInitCallBackListener;
    }

    public void doSendInit(Activity activity, OnInitCallBackListener onInitCallBackListener) {
        setAct(activity);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.1
            final /* synthetic */ OnInitCallBackListener val$listener;
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass1(LinkedHashMap linkedHashMap, OnInitCallBackListener onInitCallBackListener2) {
                r2 = linkedHashMap;
                r3 = onInitCallBackListener2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$1$1 */
            /* loaded from: classes.dex */
            class C00231 implements onNetBack {
                C00231() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (r3 != null) {
                        r3.onInitBack(200);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.CHANNEL_ENTER, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.1.1
                    C00231() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (r3 != null) {
                            r3.onInitBack(200);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ OnInitCallBackListener val$listener;
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass1(LinkedHashMap linkedHashMap, OnInitCallBackListener onInitCallBackListener2) {
            r2 = linkedHashMap;
            r3 = onInitCallBackListener2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$1$1 */
        /* loaded from: classes.dex */
        class C00231 implements onNetBack {
            C00231() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (r3 != null) {
                    r3.onInitBack(200);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.CHANNEL_ENTER, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.1.1
                C00231() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (r3 != null) {
                        r3.onInitBack(200);
                    }
                }
            }, true);
        }
    }

    public static KaiQiGuSdk getInstance() {
        if (instance == null) {
            instance = new KaiQiGuSdk();
        }
        return instance;
    }

    public void goToLogin() {
        this.act.startActivity(new Intent(this.act, (Class<?>) LoginActivity.class));
    }

    public void doLogin(Activity activity, OnLoginCallBackListener onLoginCallBackListener) {
        setAct(activity);
        checkInfor();
        if (UserManager.getInstance().hasNativeUserData()) {
            this.act.startActivity(new Intent(this.act, (Class<?>) LoginActivity.class));
        } else {
            goToLogin();
        }
        this.loginListener = onLoginCallBackListener;
    }

    public void doExit(Activity activity, OnQuitGameBackListener onQuitGameBackListener) {
        setAct(activity);
        this.act.startActivity(new Intent(this.act, (Class<?>) QuitGame.class));
        this.quitListener = onQuitGameBackListener;
    }

    private void getOrderNum(Message message, String str, boolean z, LinkedHashMap<String, String> linkedHashMap) {
        int what = message.getWhat();
        PayInfor payInfor = (PayInfor) message.getObj();
        UiUtils.showLoadingDialog(this.act);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.2
            final /* synthetic */ PayInfor val$infor;
            final /* synthetic */ boolean val$isGet;
            final /* synthetic */ LinkedHashMap val$params;
            final /* synthetic */ int val$type;
            final /* synthetic */ String val$url;

            AnonymousClass2(String str2, LinkedHashMap linkedHashMap2, int what2, PayInfor payInfor2, boolean z2) {
                r2 = str2;
                r3 = linkedHashMap2;
                r4 = what2;
                r5 = payInfor2;
                r6 = z2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$2$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    android.os.Message message = new android.os.Message();
                    message.what = r4;
                    if (jSONObject != null) {
                        try {
                            if (jSONObject.has("notifyUrl")) {
                                r5.setNotifyUrl(jSONObject.getString("notifyUrl"));
                            }
                            if (jSONObject.has("mhtOrderStartTime")) {
                                r5.setMhtOrderStartTime(jSONObject.getLong("mhtOrderStartTime"));
                            }
                            if (jSONObject.has("mhtOrderNo")) {
                                r5.setMhtOrderNo(jSONObject.getString("mhtOrderNo"));
                            }
                            if (jSONObject.has("mhtOrderTimeOut")) {
                                r5.setMhtOrderTimeOut(jSONObject.getInt("mhtOrderTimeOut"));
                            }
                            if (jSONObject.has("tn")) {
                                r5.setMhtOrderNo(jSONObject.getString("tn"));
                            }
                            if (jSONObject.has("res")) {
                                r5.setRes(jSONObject.getString("res"));
                            }
                            if (jSONObject.has("order_no")) {
                                r5.setMhtOrderNo(jSONObject.getString("order_no"));
                            }
                            message.obj = r5;
                        } catch (JSONException unused) {
                        }
                    }
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(r2, r3, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.2.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = r4;
                        if (jSONObject != null) {
                            try {
                                if (jSONObject.has("notifyUrl")) {
                                    r5.setNotifyUrl(jSONObject.getString("notifyUrl"));
                                }
                                if (jSONObject.has("mhtOrderStartTime")) {
                                    r5.setMhtOrderStartTime(jSONObject.getLong("mhtOrderStartTime"));
                                }
                                if (jSONObject.has("mhtOrderNo")) {
                                    r5.setMhtOrderNo(jSONObject.getString("mhtOrderNo"));
                                }
                                if (jSONObject.has("mhtOrderTimeOut")) {
                                    r5.setMhtOrderTimeOut(jSONObject.getInt("mhtOrderTimeOut"));
                                }
                                if (jSONObject.has("tn")) {
                                    r5.setMhtOrderNo(jSONObject.getString("tn"));
                                }
                                if (jSONObject.has("res")) {
                                    r5.setRes(jSONObject.getString("res"));
                                }
                                if (jSONObject.has("order_no")) {
                                    r5.setMhtOrderNo(jSONObject.getString("order_no"));
                                }
                                message2.obj = r5;
                            } catch (JSONException unused) {
                            }
                        }
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }, r6);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ PayInfor val$infor;
        final /* synthetic */ boolean val$isGet;
        final /* synthetic */ LinkedHashMap val$params;
        final /* synthetic */ int val$type;
        final /* synthetic */ String val$url;

        AnonymousClass2(String str2, LinkedHashMap linkedHashMap2, int what2, PayInfor payInfor2, boolean z2) {
            r2 = str2;
            r3 = linkedHashMap2;
            r4 = what2;
            r5 = payInfor2;
            r6 = z2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$2$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                android.os.Message message2 = new android.os.Message();
                message2.what = r4;
                if (jSONObject != null) {
                    try {
                        if (jSONObject.has("notifyUrl")) {
                            r5.setNotifyUrl(jSONObject.getString("notifyUrl"));
                        }
                        if (jSONObject.has("mhtOrderStartTime")) {
                            r5.setMhtOrderStartTime(jSONObject.getLong("mhtOrderStartTime"));
                        }
                        if (jSONObject.has("mhtOrderNo")) {
                            r5.setMhtOrderNo(jSONObject.getString("mhtOrderNo"));
                        }
                        if (jSONObject.has("mhtOrderTimeOut")) {
                            r5.setMhtOrderTimeOut(jSONObject.getInt("mhtOrderTimeOut"));
                        }
                        if (jSONObject.has("tn")) {
                            r5.setMhtOrderNo(jSONObject.getString("tn"));
                        }
                        if (jSONObject.has("res")) {
                            r5.setRes(jSONObject.getString("res"));
                        }
                        if (jSONObject.has("order_no")) {
                            r5.setMhtOrderNo(jSONObject.getString("order_no"));
                        }
                        message2.obj = r5;
                    } catch (JSONException unused) {
                    }
                }
                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(r2, r3, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.2.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = r4;
                    if (jSONObject != null) {
                        try {
                            if (jSONObject.has("notifyUrl")) {
                                r5.setNotifyUrl(jSONObject.getString("notifyUrl"));
                            }
                            if (jSONObject.has("mhtOrderStartTime")) {
                                r5.setMhtOrderStartTime(jSONObject.getLong("mhtOrderStartTime"));
                            }
                            if (jSONObject.has("mhtOrderNo")) {
                                r5.setMhtOrderNo(jSONObject.getString("mhtOrderNo"));
                            }
                            if (jSONObject.has("mhtOrderTimeOut")) {
                                r5.setMhtOrderTimeOut(jSONObject.getInt("mhtOrderTimeOut"));
                            }
                            if (jSONObject.has("tn")) {
                                r5.setMhtOrderNo(jSONObject.getString("tn"));
                            }
                            if (jSONObject.has("res")) {
                                r5.setRes(jSONObject.getString("res"));
                            }
                            if (jSONObject.has("order_no")) {
                                r5.setMhtOrderNo(jSONObject.getString("order_no"));
                            }
                            message2.obj = r5;
                        } catch (JSONException unused) {
                        }
                    }
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }, r6);
        }
    }

    private void getWxOrderNum(Message message) {
        PayInfor payInfor = (PayInfor) message.getObj();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("username", UserManager.getInstance().getCurrentUser().getUsername());
        linkedHashMap.put("game_uid", payInfor.getRoleId());
        linkedHashMap.put("server_id", payInfor.getZoneId());
        linkedHashMap.put("product_id", payInfor.getBillId());
        linkedHashMap.put("itemsort", payInfor.getItemSort());
        linkedHashMap.put("itemid", payInfor.getItemId());
        linkedHashMap.put("product_name", payInfor.getMhtOrderName());
        linkedHashMap.put("amount", payInfor.getMhtOrderAmt() + "");
        linkedHashMap.put(SpeechConstant.APPID, getInstance().getAppId());
        getOrderNum(message, KV.CREATE_PAY_ORDER_WX, false, linkedHashMap);
    }

    private void getUpOrderNum(Message message) {
        PayInfor payInfor = (PayInfor) message.getObj();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("username", UserManager.getInstance().getCurrentUser().getUsername());
        linkedHashMap.put("game_uid", payInfor.getRoleId());
        linkedHashMap.put("server_id", payInfor.getZoneId());
        linkedHashMap.put("product_id", payInfor.getBillId());
        linkedHashMap.put("itemsort", payInfor.getItemSort());
        linkedHashMap.put("itemid", payInfor.getItemId());
        linkedHashMap.put("product_name", payInfor.getMhtOrderName());
        linkedHashMap.put("amount", payInfor.getMhtOrderAmt() + "");
        linkedHashMap.put(SpeechConstant.APPID, getInstance().getAppId());
        getOrderNum(message, KV.CREATE_PAY_ORDER_UP, false, linkedHashMap);
    }

    private void getAliOrderNum(Message message) {
        PayInfor payInfor = (PayInfor) message.getObj();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("username", UserManager.getInstance().getCurrentUser().getUsername());
        linkedHashMap.put("game_uid", payInfor.getRoleId());
        linkedHashMap.put("server_id", payInfor.getZoneId());
        linkedHashMap.put("product_id", payInfor.getBillId());
        linkedHashMap.put("itemsort", payInfor.getItemSort());
        linkedHashMap.put("itemid", payInfor.getItemId());
        linkedHashMap.put("product_name", payInfor.getMhtOrderName());
        linkedHashMap.put("amount", payInfor.getMhtOrderAmt() + "");
        linkedHashMap.put(SpeechConstant.APPID, getInstance().getAppId());
        getOrderNum(message, KV.CREATE_PAY_ORDER_ALI, false, linkedHashMap);
    }

    public void doPay(Activity activity, PayInfor payInfor, OnPayCallBackListener onPayCallBackListener) {
        User currentUser = UserManager.getInstance().getCurrentUser();
        if (this.handler == null) {
            UiUtils.toast(UiUtils.getResString("init_error"));
            return;
        }
        if (currentUser == null) {
            UiUtils.toast(UiUtils.getResString("login_error"));
            return;
        }
        if (PaySelectTypeManager.isNoPayTypeSet()) {
            UiUtils.toast(UiUtils.getResString("no_paytype_warn"));
            return;
        }
        this.act = activity;
        this.payListener = onPayCallBackListener;
        Intent intent = new Intent(this.act, (Class<?>) PaySelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PayInfor", payInfor);
        intent.putExtras(bundle);
        this.act.startActivity(intent);
    }

    private void doRegister() {
        this.act.startActivity(new Intent(this.act, (Class<?>) RegisterActivity.class));
    }

    private void doQuickRegister() {
        this.act.startActivity(new Intent(this.act, (Class<?>) QuickRegistActivity.class));
    }

    private void doQuickLogin() {
        this.act.startActivity(new Intent(this.act, (Class<?>) QuickLoginActivity.class));
    }

    public void doCertification(Message message) {
        this.act.startActivity(new Intent(this.act, (Class<?>) CertificationActivity.class));
    }

    public void enterCertification() {
        doCertification(this.msg1);
    }

    private void checkCertification(String str, OnCertificationListener onCertificationListener) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", str);
        new Thread() { // from class: com.kqg.main.base.KaiQiGuSdk.3
            final /* synthetic */ OnCertificationListener val$listener;
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass3(LinkedHashMap linkedHashMap2, OnCertificationListener onCertificationListener2) {
                r2 = linkedHashMap2;
                r3 = onCertificationListener2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$3$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.has("valid_state")) {
                            if (jSONObject.optString("valid_state").equals("1")) {
                                r3.onSuccess();
                                return;
                            } else {
                                r3.onFail("实名认证失败");
                                return;
                            }
                        }
                        r3.onFail("用户信息错误，请重新提交");
                        return;
                    }
                    r3.onFail("网络错误，请检查网络");
                }
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequestpay(KV.CHECK_CERTIFICATION, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.3.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            if (jSONObject.has("valid_state")) {
                                if (jSONObject.optString("valid_state").equals("1")) {
                                    r3.onSuccess();
                                    return;
                                } else {
                                    r3.onFail("实名认证失败");
                                    return;
                                }
                            }
                            r3.onFail("用户信息错误，请重新提交");
                            return;
                        }
                        r3.onFail("网络错误，请检查网络");
                    }
                }, false);
                super.run();
            }
        }.start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 extends Thread {
        final /* synthetic */ OnCertificationListener val$listener;
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass3(LinkedHashMap linkedHashMap2, OnCertificationListener onCertificationListener2) {
            r2 = linkedHashMap2;
            r3 = onCertificationListener2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$3$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    if (jSONObject.has("valid_state")) {
                        if (jSONObject.optString("valid_state").equals("1")) {
                            r3.onSuccess();
                            return;
                        } else {
                            r3.onFail("实名认证失败");
                            return;
                        }
                    }
                    r3.onFail("用户信息错误，请重新提交");
                    return;
                }
                r3.onFail("网络错误，请检查网络");
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequestpay(KV.CHECK_CERTIFICATION, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.3.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (jSONObject.has("valid_state")) {
                            if (jSONObject.optString("valid_state").equals("1")) {
                                r3.onSuccess();
                                return;
                            } else {
                                r3.onFail("实名认证失败");
                                return;
                            }
                        }
                        r3.onFail("用户信息错误，请重新提交");
                        return;
                    }
                    r3.onFail("网络错误，请检查网络");
                }
            }, false);
            super.run();
        }
    }

    private void goCertification() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] certificationData = CertificationActivity.getCertificationData();
        User currentUser = UserManager.getInstance().getCurrentUser();
        currentUser.getUsername();
        linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, currentUser.getSession_id());
        linkedHashMap.put(c.e, certificationData[0]);
        linkedHashMap.put("idcard", certificationData[1]);
        new Thread() { // from class: com.kqg.main.base.KaiQiGuSdk.4
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass4(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$4$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    new android.os.Message();
                    if (jSONObject != null) {
                        if (jSONObject.has("age_level")) {
                            try {
                                UserManager.getInstance().getCurrentUser().setAge(String.valueOf(jSONObject.getInt("age_level")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        KaiQiGuSdk.isNeedCer = false;
                        android.os.Message message = new android.os.Message();
                        message.what = 20000;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        KaiQiGuSdk.this.loginCallBack1();
                    }
                }
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequestpay(KV.ACCOUNT_CERTIFICATION, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.4.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        new android.os.Message();
                        if (jSONObject != null) {
                            if (jSONObject.has("age_level")) {
                                try {
                                    UserManager.getInstance().getCurrentUser().setAge(String.valueOf(jSONObject.getInt("age_level")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            KaiQiGuSdk.isNeedCer = false;
                            android.os.Message message = new android.os.Message();
                            message.what = 20000;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                            KaiQiGuSdk.this.loginCallBack1();
                        }
                    }
                }, true);
                super.run();
            }
        }.start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 extends Thread {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass4(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$4$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                new android.os.Message();
                if (jSONObject != null) {
                    if (jSONObject.has("age_level")) {
                        try {
                            UserManager.getInstance().getCurrentUser().setAge(String.valueOf(jSONObject.getInt("age_level")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    KaiQiGuSdk.isNeedCer = false;
                    android.os.Message message = new android.os.Message();
                    message.what = 20000;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    KaiQiGuSdk.this.loginCallBack1();
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequestpay(KV.ACCOUNT_CERTIFICATION, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.4.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    new android.os.Message();
                    if (jSONObject != null) {
                        if (jSONObject.has("age_level")) {
                            try {
                                UserManager.getInstance().getCurrentUser().setAge(String.valueOf(jSONObject.getInt("age_level")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        KaiQiGuSdk.isNeedCer = false;
                        android.os.Message message = new android.os.Message();
                        message.what = 20000;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        KaiQiGuSdk.this.loginCallBack1();
                    }
                }
            }, true);
            super.run();
        }
    }

    private void forceBindPhone(CommonEntity commonEntity) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        User currentUser = UserManager.getInstance().getCurrentUser();
        JSONObject json = commonEntity.getJson();
        try {
            linkedHashMap.put("phone", getPhone(json.getString("phone")));
            linkedHashMap.put("code", json.getString("code"));
            linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, json.getString(SpeechEvent.KEY_EVENT_SESSION_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new Thread() { // from class: com.kqg.main.base.KaiQiGuSdk.5
            final /* synthetic */ LinkedHashMap val$params;
            final /* synthetic */ User val$user;

            AnonymousClass5(LinkedHashMap linkedHashMap2, User currentUser2) {
                r2 = linkedHashMap2;
                r3 = currentUser2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$5$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = KV.EVENT_FORCE_BIND_PHONE;
                        message.obj = r3;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequestpay(KV.FORCE_BIND_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.5.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message = new android.os.Message();
                            message.what = KV.EVENT_FORCE_BIND_PHONE;
                            message.obj = r3;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        }
                    }
                }, true);
                super.run();
            }
        }.start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 extends Thread {
        final /* synthetic */ LinkedHashMap val$params;
        final /* synthetic */ User val$user;

        AnonymousClass5(LinkedHashMap linkedHashMap2, User currentUser2) {
            r2 = linkedHashMap2;
            r3 = currentUser2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$5$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message = new android.os.Message();
                    message.what = KV.EVENT_FORCE_BIND_PHONE;
                    message.obj = r3;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequestpay(KV.FORCE_BIND_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.5.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = KV.EVENT_FORCE_BIND_PHONE;
                        message.obj = r3;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }, true);
            super.run();
        }
    }

    private void checkCode(CommonEntity commonEntity) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        UserManager.getInstance().getCurrentUser();
        JSONObject json = commonEntity.getJson();
        try {
            if (json.has("phone")) {
                linkedHashMap.put("phone", getPhone(json.getString("phone")));
                linkedHashMap.put("account", getPhone(json.getString("phone")));
            }
            if (json.has("code")) {
                linkedHashMap.put("code", json.getString("code"));
            }
            if (json.has("newpassword")) {
                linkedHashMap.put("newpassword", json.getString("newpassword"));
            }
            if (json.has(SpeechEvent.KEY_EVENT_SESSION_ID)) {
                linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, json.getString(SpeechEvent.KEY_EVENT_SESSION_ID));
            }
            new Thread() { // from class: com.kqg.main.base.KaiQiGuSdk.6
                final /* synthetic */ String val$finalIsPost;
                final /* synthetic */ String val$finalUrl;
                final /* synthetic */ LinkedHashMap val$params;
                final /* synthetic */ int val$what;

                AnonymousClass6(String str, LinkedHashMap linkedHashMap2, int i, String str2) {
                    r2 = str;
                    r3 = linkedHashMap2;
                    r4 = i;
                    r5 = str2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$6$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message = new android.os.Message();
                            message.what = r4;
                            message.obj = jSONObject;
                            String str = r5;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        }
                    }
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequestpay(r2, r3, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.6.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject) {
                            if (jSONObject != null) {
                                android.os.Message message = new android.os.Message();
                                message.what = r4;
                                message.obj = jSONObject;
                                String str = r5;
                                KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                            }
                        }
                    }, true);
                    super.run();
                }
            }.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$6 */
    /* loaded from: classes.dex */
    class AnonymousClass6 extends Thread {
        final /* synthetic */ String val$finalIsPost;
        final /* synthetic */ String val$finalUrl;
        final /* synthetic */ LinkedHashMap val$params;
        final /* synthetic */ int val$what;

        AnonymousClass6(String str, LinkedHashMap linkedHashMap2, int i, String str2) {
            r2 = str;
            r3 = linkedHashMap2;
            r4 = i;
            r5 = str2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$6$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message = new android.os.Message();
                    message.what = r4;
                    message.obj = jSONObject;
                    String str = r5;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequestpay(r2, r3, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.6.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = r4;
                        message.obj = jSONObject;
                        String str = r5;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }, true);
            super.run();
        }
    }

    private String getPhone(String str) {
        if (str.length() == 9) {
            return "84" + str;
        }
        if (str.length() == 10) {
            return "84" + str.substring(1);
        }
        str.length();
        return str;
    }

    private void loginBackWithPassword(CommonEntity commonEntity) {
        User data = ((Login) commonEntity).getData();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        String username = data.getUsername();
        Log.e("haha", username);
        linkedHashMap.put("username", username);
        linkedHashMap.put("password", data.getPassword());
        isNeedCer = false;
        doNetRequestpay(KV.ACCOUNT_LOGIN, linkedHashMap, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.7
            AnonymousClass7() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                android.os.Message message = new android.os.Message();
                message.what = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("byPassword", "1");
                if (jSONObject != null) {
                    User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1001);
                    LogUtil.logE(userByJsonData);
                    UserManager.getInstance().saveUser(userByJsonData);
                    UserManager.getInstance().setCurrentUser(userByJsonData);
                    UserManager.getInstance().setFirstUser(userByJsonData);
                    message.obj = userByJsonData;
                    if (jSONObject.has("phone")) {
                        try {
                            String string = jSONObject.getString("phone");
                            if (!string.equals("")) {
                                bundle.putString("phone", string);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (jSONObject.has("need_real_name")) {
                        try {
                            if (jSONObject.getInt("need_real_name") == 1) {
                                String optString = jSONObject.optString("username");
                                Log.e("KaiQiGuSDK", jSONObject.toString());
                                KaiQiGuSdk.this.msg1 = new Message(1, optString);
                                KaiQiGuSdk.isNeedCer = true;
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                message.setData(bundle);
                KaiQiGuSdk.this.sendEventResultMessageToUi(message);
            }
        }, false);
        isNeedLogin = true;
        isNeedCertifica = "1";
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$7 */
    /* loaded from: classes.dex */
    class AnonymousClass7 implements onNetBack {
        AnonymousClass7() {
        }

        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
        public void onBack(JSONObject jSONObject) {
            android.os.Message message = new android.os.Message();
            message.what = 1001;
            Bundle bundle = new Bundle();
            bundle.putString("byPassword", "1");
            if (jSONObject != null) {
                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1001);
                LogUtil.logE(userByJsonData);
                UserManager.getInstance().saveUser(userByJsonData);
                UserManager.getInstance().setCurrentUser(userByJsonData);
                UserManager.getInstance().setFirstUser(userByJsonData);
                message.obj = userByJsonData;
                if (jSONObject.has("phone")) {
                    try {
                        String string = jSONObject.getString("phone");
                        if (!string.equals("")) {
                            bundle.putString("phone", string);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (jSONObject.has("need_real_name")) {
                    try {
                        if (jSONObject.getInt("need_real_name") == 1) {
                            String optString = jSONObject.optString("username");
                            Log.e("KaiQiGuSDK", jSONObject.toString());
                            KaiQiGuSdk.this.msg1 = new Message(1, optString);
                            KaiQiGuSdk.isNeedCer = true;
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            message.setData(bundle);
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    private void loginBackWithPhone(CommonEntity commonEntity) {
        try {
            JSONObject jSONObject = new JSONObject(commonEntity.getMsg());
            String phone = getPhone(jSONObject.getString("phone"));
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("account", phone);
            linkedHashMap.put("phone", phone);
            linkedHashMap.put("code", jSONObject.getString("code"));
            isNeedCer = false;
            doNetRequestpay(KV.ACCOUNT_LOGIN_WITH_PHONE_CODE, linkedHashMap, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.8
                AnonymousClass8() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    User userById;
                    android.os.Message message = new android.os.Message();
                    message.what = 1001;
                    Bundle bundle = new Bundle();
                    bundle.putString("byPhone", "1");
                    if (jSONObject2 != null) {
                        try {
                            if (!jSONObject2.has("password") && (userById = UserManager.getInstance().getUserById(jSONObject2.getString("uid"))) != null) {
                                jSONObject2.put("password", userById.getPassword());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                        LogUtil.logE(userByJsonData);
                        UserManager.getInstance().saveUser(userByJsonData);
                        UserManager.getInstance().setCurrentUser(userByJsonData);
                        UserManager.getInstance().setFirstUser(userByJsonData);
                        message.obj = userByJsonData;
                        if (jSONObject2.has("need_real_name")) {
                            try {
                                if (jSONObject2.getInt("need_real_name") == 1) {
                                    String optString = jSONObject2.optString("username");
                                    Log.e("KaiQiGuSDK", jSONObject2.toString());
                                    KaiQiGuSdk.this.msg1 = new Message(1, optString);
                                    KaiQiGuSdk.isNeedCer = true;
                                }
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    message.setData(bundle);
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                }
            }, true);
            isNeedLogin = true;
            isNeedCertifica = "1";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$8 */
    /* loaded from: classes.dex */
    class AnonymousClass8 implements onNetBack {
        AnonymousClass8() {
        }

        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
        public void onBack(JSONObject jSONObject2) {
            User userById;
            android.os.Message message = new android.os.Message();
            message.what = 1001;
            Bundle bundle = new Bundle();
            bundle.putString("byPhone", "1");
            if (jSONObject2 != null) {
                try {
                    if (!jSONObject2.has("password") && (userById = UserManager.getInstance().getUserById(jSONObject2.getString("uid"))) != null) {
                        jSONObject2.put("password", userById.getPassword());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                LogUtil.logE(userByJsonData);
                UserManager.getInstance().saveUser(userByJsonData);
                UserManager.getInstance().setCurrentUser(userByJsonData);
                UserManager.getInstance().setFirstUser(userByJsonData);
                message.obj = userByJsonData;
                if (jSONObject2.has("need_real_name")) {
                    try {
                        if (jSONObject2.getInt("need_real_name") == 1) {
                            String optString = jSONObject2.optString("username");
                            Log.e("KaiQiGuSDK", jSONObject2.toString());
                            KaiQiGuSdk.this.msg1 = new Message(1, optString);
                            KaiQiGuSdk.isNeedCer = true;
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            message.setData(bundle);
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    private void findPwdByEmail() {
        this.act.startActivity(new Intent(this.act, (Class<?>) FindPasswordByEmail.class));
    }

    private void openAccountManagerActivity() {
        this.act.startActivity(new Intent(this.act, (Class<?>) AccountManagerActivity.class));
    }

    private void findPwdByPhone() {
        this.act.startActivity(new Intent(this.act, (Class<?>) FindPasswordByPhone.class));
    }

    private void showFindPwdView() {
        this.act.startActivity(new Intent(this.act, (Class<?>) ForgetPwdActivity.class));
    }

    private void doGetCode(Message message) {
        String str;
        String str2 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("phone");
        } catch (JSONException e) {
            e = e;
            str = "";
        }
        try {
            str2 = jSONObject.getString("userName");
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("username", str2);
            linkedHashMap.put("phone", str);
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.9
                final /* synthetic */ LinkedHashMap val$params;

                AnonymousClass9(LinkedHashMap linkedHashMap2) {
                    r2 = linkedHashMap2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$9$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message = new android.os.Message();
                            message.what = 1020;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.9.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            if (jSONObject2 != null) {
                                android.os.Message message2 = new android.os.Message();
                                message2.what = 1020;
                                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                            }
                        }
                    }, true);
                }
            }).start();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("username", str2);
        linkedHashMap2.put("phone", str);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.9
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass9(LinkedHashMap linkedHashMap22) {
                r2 = linkedHashMap22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$9$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1020;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.9.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        if (jSONObject2 != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = 1020;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$9 */
    /* loaded from: classes.dex */
    class AnonymousClass9 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass9(LinkedHashMap linkedHashMap22) {
            r2 = linkedHashMap22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$9$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                if (jSONObject2 != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1020;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.9.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1020;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void doGetCodeByMail(Message message) {
        String str;
        String str2 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("email");
        } catch (JSONException e) {
            e = e;
            str = "";
        }
        try {
            str2 = jSONObject.getString("username");
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("username", str2);
            linkedHashMap.put("email", str);
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.10
                final /* synthetic */ LinkedHashMap val$params;

                AnonymousClass10(LinkedHashMap linkedHashMap2) {
                    r2 = linkedHashMap2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$10$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject);
                        if (jSONObject != null) {
                            android.os.Message message = new android.os.Message();
                            message.what = 1020;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS_EMAIL, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.10.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject2);
                            if (jSONObject2 != null) {
                                android.os.Message message2 = new android.os.Message();
                                message2.what = 1020;
                                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                            }
                        }
                    }, true);
                }
            }).start();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("username", str2);
        linkedHashMap2.put("email", str);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.10
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass10(LinkedHashMap linkedHashMap22) {
                r2 = linkedHashMap22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$10$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject2);
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1020;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS_EMAIL, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.10.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject2);
                        if (jSONObject2 != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = 1020;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$10 */
    /* loaded from: classes.dex */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass10(LinkedHashMap linkedHashMap22) {
            r2 = linkedHashMap22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$10$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject2);
                if (jSONObject2 != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1020;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_SMS_EMAIL, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.10.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    Log.e("DZ", "发送验证码成功的消息回来了obj。。。。。" + jSONObject2);
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1020;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void goFindPasswordByPhone(Message message) {
        String str;
        String str2;
        String str3 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("userName");
            try {
                str2 = jSONObject.getString("password");
            } catch (JSONException e) {
                e = e;
                str2 = "";
            }
        } catch (JSONException e2) {
            e = e2;
            str = "";
            str2 = str;
        }
        try {
            str3 = jSONObject.getString("code");
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("username", str);
            linkedHashMap.put("code", str3);
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.11
                final /* synthetic */ LinkedHashMap val$params;
                final /* synthetic */ String val$password;
                final /* synthetic */ String val$userName1;

                AnonymousClass11(LinkedHashMap linkedHashMap2, String str4, String str22) {
                    r2 = linkedHashMap2;
                    r3 = str4;
                    r4 = str22;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$11$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                            KaiQiGuSdk.this.findPassword(r3, r4);
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.PWD_PHONE_CHECK, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.11.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            if (jSONObject2 != null) {
                                Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                                KaiQiGuSdk.this.findPassword(r3, r4);
                            }
                        }
                    }, true);
                }
            }).start();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("username", str4);
        linkedHashMap2.put("code", str3);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.11
            final /* synthetic */ LinkedHashMap val$params;
            final /* synthetic */ String val$password;
            final /* synthetic */ String val$userName1;

            AnonymousClass11(LinkedHashMap linkedHashMap22, String str4, String str22) {
                r2 = linkedHashMap22;
                r3 = str4;
                r4 = str22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$11$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                        KaiQiGuSdk.this.findPassword(r3, r4);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.PWD_PHONE_CHECK, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.11.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        if (jSONObject2 != null) {
                            Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                            KaiQiGuSdk.this.findPassword(r3, r4);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$11 */
    /* loaded from: classes.dex */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;
        final /* synthetic */ String val$password;
        final /* synthetic */ String val$userName1;

        AnonymousClass11(LinkedHashMap linkedHashMap22, String str4, String str22) {
            r2 = linkedHashMap22;
            r3 = str4;
            r4 = str22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$11$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                if (jSONObject2 != null) {
                    Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                    KaiQiGuSdk.this.findPassword(r3, r4);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.PWD_PHONE_CHECK, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.11.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        Log.e("DZ", "核对验证码成功。。。。。。。。。。");
                        KaiQiGuSdk.this.findPassword(r3, r4);
                    }
                }
            }, true);
        }
    }

    public void findPassword(String str, String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", str);
        linkedHashMap.put("password", MD5.Md5(str2));
        linkedHashMap.put("type", "0");
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.12
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass12(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$12$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        Log.e("DZ", "修改密码成功。。。。。。。。。。");
                        UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                        android.os.Message message = new android.os.Message();
                        message.what = 1021;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        KaiQiGuSdk.this.goToLogin();
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_PASSWORD_CHANGE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.12.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            Log.e("DZ", "修改密码成功。。。。。。。。。。");
                            UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                            android.os.Message message = new android.os.Message();
                            message.what = 1021;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                            KaiQiGuSdk.this.goToLogin();
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$12 */
    /* loaded from: classes.dex */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass12(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$12$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    Log.e("DZ", "修改密码成功。。。。。。。。。。");
                    UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                    android.os.Message message = new android.os.Message();
                    message.what = 1021;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    KaiQiGuSdk.this.goToLogin();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_PASSWORD_CHANGE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.12.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        Log.e("DZ", "修改密码成功。。。。。。。。。。");
                        UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                        android.os.Message message = new android.os.Message();
                        message.what = 1021;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        KaiQiGuSdk.this.goToLogin();
                    }
                }
            }, true);
        }
    }

    private void findPasswordEmail(Message message) {
        String str;
        String str2;
        String str3 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("userName");
            try {
                str2 = jSONObject.getString("password");
            } catch (JSONException e) {
                e = e;
                str2 = "";
            }
        } catch (JSONException e2) {
            e = e2;
            str = "";
            str2 = str;
        }
        try {
            str3 = jSONObject.getString("code");
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("username", str);
            linkedHashMap.put("new_password", MD5.Md5(str2));
            linkedHashMap.put("code", str3);
            linkedHashMap.put("type", "1");
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.13
                final /* synthetic */ LinkedHashMap val$params;

                AnonymousClass13(LinkedHashMap linkedHashMap2) {
                    r2 = linkedHashMap2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$13$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            Log.e("DZ", "修改密码成功。。。。。。。。。。");
                            UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                            android.os.Message message = new android.os.Message();
                            message.what = 1021;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                            KaiQiGuSdk.this.goToLogin();
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_PASSWORD_CHANGE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.13.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            if (jSONObject2 != null) {
                                Log.e("DZ", "修改密码成功。。。。。。。。。。");
                                UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                                android.os.Message message2 = new android.os.Message();
                                message2.what = 1021;
                                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                                KaiQiGuSdk.this.goToLogin();
                            }
                        }
                    }, false);
                }
            }).start();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("username", str);
        linkedHashMap2.put("new_password", MD5.Md5(str2));
        linkedHashMap2.put("code", str3);
        linkedHashMap2.put("type", "1");
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.13
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass13(LinkedHashMap linkedHashMap22) {
                r2 = linkedHashMap22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$13$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        Log.e("DZ", "修改密码成功。。。。。。。。。。");
                        UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1021;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        KaiQiGuSdk.this.goToLogin();
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_PASSWORD_CHANGE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.13.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        if (jSONObject2 != null) {
                            Log.e("DZ", "修改密码成功。。。。。。。。。。");
                            UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                            android.os.Message message2 = new android.os.Message();
                            message2.what = 1021;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                            KaiQiGuSdk.this.goToLogin();
                        }
                    }
                }, false);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$13 */
    /* loaded from: classes.dex */
    class AnonymousClass13 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass13(LinkedHashMap linkedHashMap22) {
            r2 = linkedHashMap22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$13$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                if (jSONObject2 != null) {
                    Log.e("DZ", "修改密码成功。。。。。。。。。。");
                    UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1021;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    KaiQiGuSdk.this.goToLogin();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_PASSWORD_CHANGE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.13.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        Log.e("DZ", "修改密码成功。。。。。。。。。。");
                        UserManager.getInstance().removeUser(QuickLoginActivity.getUser1());
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1021;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        KaiQiGuSdk.this.goToLogin();
                    }
                }
            }, false);
        }
    }

    private void doChangePhone() {
        String session_id = UserManager.getInstance().getCurrentUser().getSession_id();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, session_id);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.14
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass14(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$14$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                }

                AnonymousClass1() {
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_CHANGE_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.14.1
                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                    }

                    AnonymousClass1() {
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$14 */
    /* loaded from: classes.dex */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass14(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$14$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
            }

            AnonymousClass1() {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_CHANGE_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.14.1
                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                }

                AnonymousClass1() {
                }
            }, true);
        }
    }

    private void goBindAccountByPhone(Message message) {
        String str;
        try {
            str = ((JSONObject) message.getObj()).getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        String session_id = UserManager.getInstance().getCurrentUser().getSession_id();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("code", str);
        linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, session_id);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.15
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass15(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$15$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = 1024;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_BIND_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.15.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = 1024;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$15 */
    /* loaded from: classes.dex */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass15(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$15$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1024;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_BIND_PHONE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.15.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1024;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void goBindAccountByEmail(Message message) {
        String str;
        String str2 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("email");
        } catch (JSONException e) {
            e = e;
            str = "";
        }
        try {
            str2 = jSONObject.getString("seccode");
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            String username = UserManager.getInstance().getCurrentUser().getUsername();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("email", str);
            linkedHashMap.put("seccode", str2);
            linkedHashMap.put("username", username);
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.16
                final /* synthetic */ LinkedHashMap val$params;

                AnonymousClass16(LinkedHashMap linkedHashMap2) {
                    r2 = linkedHashMap2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$16$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message = new android.os.Message();
                            message.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_EMAIL_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.16.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            if (jSONObject2 != null) {
                                android.os.Message message2 = new android.os.Message();
                                message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                            }
                        }
                    }, true);
                }
            }).start();
        }
        String username2 = UserManager.getInstance().getCurrentUser().getUsername();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("email", str);
        linkedHashMap2.put("seccode", str2);
        linkedHashMap2.put("username", username2);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.16
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass16(LinkedHashMap linkedHashMap22) {
                r2 = linkedHashMap22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$16$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_EMAIL_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.16.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        if (jSONObject2 != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$16 */
    /* loaded from: classes.dex */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass16(LinkedHashMap linkedHashMap22) {
            r2 = linkedHashMap22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$16$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                if (jSONObject2 != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_EMAIL_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.16.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void goEnterPassword() {
        this.act.startActivity(new Intent(this.act, (Class<?>) BindToEnterPassword.class));
    }

    private void bindRequestLogin(Message message) {
        String str;
        String str2 = "";
        JSONObject jSONObject = (JSONObject) message.getObj();
        try {
            str = jSONObject.getString("userName");
        } catch (JSONException e) {
            e = e;
            str = "";
        }
        try {
            str2 = jSONObject.getString("password");
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("username", getPhone(str));
            linkedHashMap.put("password", str2);
            linkedHashMap.put(SpeechConstant.APPID, this.infor.getAppId());
            new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.17
                final /* synthetic */ LinkedHashMap val$params;

                AnonymousClass17(LinkedHashMap linkedHashMap2) {
                    r2 = linkedHashMap2;
                }

                /* renamed from: com.kqg.main.base.KaiQiGuSdk$17$1 */
                /* loaded from: classes.dex */
                class AnonymousClass1 implements onNetBack {
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1001);
                            LogUtil.logE(userByJsonData);
                            UserManager.getInstance().saveUser(userByJsonData);
                            UserManager.getInstance().setCurrentUser(userByJsonData);
                            Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                            try {
                                String string = jSONObject.getString("phone");
                                String optString = jSONObject.optString("email");
                                Log.e("DZ", "返回的phone是。。。。。。" + string);
                                KaiQiGuSdk.bindPhoneNumber = string;
                                KaiQiGuSdk.bindEmailNumber = optString;
                                KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_LOGIN, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.17.1
                        AnonymousClass1() {
                        }

                        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                        public void onBack(JSONObject jSONObject2) {
                            if (jSONObject2 != null) {
                                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                                LogUtil.logE(userByJsonData);
                                UserManager.getInstance().saveUser(userByJsonData);
                                UserManager.getInstance().setCurrentUser(userByJsonData);
                                Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                                try {
                                    String string = jSONObject2.getString("phone");
                                    String optString = jSONObject2.optString("email");
                                    Log.e("DZ", "返回的phone是。。。。。。" + string);
                                    KaiQiGuSdk.bindPhoneNumber = string;
                                    KaiQiGuSdk.bindEmailNumber = optString;
                                    KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }, false);
                }
            }).start();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("username", getPhone(str));
        linkedHashMap2.put("password", str2);
        linkedHashMap2.put(SpeechConstant.APPID, this.infor.getAppId());
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.17
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass17(LinkedHashMap linkedHashMap22) {
                r2 = linkedHashMap22;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$17$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                        LogUtil.logE(userByJsonData);
                        UserManager.getInstance().saveUser(userByJsonData);
                        UserManager.getInstance().setCurrentUser(userByJsonData);
                        Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                        try {
                            String string = jSONObject2.getString("phone");
                            String optString = jSONObject2.optString("email");
                            Log.e("DZ", "返回的phone是。。。。。。" + string);
                            KaiQiGuSdk.bindPhoneNumber = string;
                            KaiQiGuSdk.bindEmailNumber = optString;
                            KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_LOGIN, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.17.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject2) {
                        if (jSONObject2 != null) {
                            User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                            LogUtil.logE(userByJsonData);
                            UserManager.getInstance().saveUser(userByJsonData);
                            UserManager.getInstance().setCurrentUser(userByJsonData);
                            Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                            try {
                                String string = jSONObject2.getString("phone");
                                String optString = jSONObject2.optString("email");
                                Log.e("DZ", "返回的phone是。。。。。。" + string);
                                KaiQiGuSdk.bindPhoneNumber = string;
                                KaiQiGuSdk.bindEmailNumber = optString;
                                KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }, false);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$17 */
    /* loaded from: classes.dex */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass17(LinkedHashMap linkedHashMap22) {
            r2 = linkedHashMap22;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$17$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject2) {
                if (jSONObject2 != null) {
                    User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                    LogUtil.logE(userByJsonData);
                    UserManager.getInstance().saveUser(userByJsonData);
                    UserManager.getInstance().setCurrentUser(userByJsonData);
                    Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                    try {
                        String string = jSONObject2.getString("phone");
                        String optString = jSONObject2.optString("email");
                        Log.e("DZ", "返回的phone是。。。。。。" + string);
                        KaiQiGuSdk.bindPhoneNumber = string;
                        KaiQiGuSdk.bindEmailNumber = optString;
                        KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_LOGIN, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.17.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject2) {
                    if (jSONObject2 != null) {
                        User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject2, 1001);
                        LogUtil.logE(userByJsonData);
                        UserManager.getInstance().saveUser(userByJsonData);
                        UserManager.getInstance().setCurrentUser(userByJsonData);
                        Log.e("DZ", "user = .........." + userByJsonData.getSession_id());
                        try {
                            String string = jSONObject2.getString("phone");
                            String optString = jSONObject2.optString("email");
                            Log.e("DZ", "返回的phone是。。。。。。" + string);
                            KaiQiGuSdk.bindPhoneNumber = string;
                            KaiQiGuSdk.bindEmailNumber = optString;
                            KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }, false);
        }
    }

    private void bindPhoneGetCode(Message message) {
        String str;
        try {
            str = getPhone(((JSONObject) message.getObj()).getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        String session_id = UserManager.getInstance().getCurrentUser().getSession_id();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, session_id);
        linkedHashMap.put("phone", str);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.18
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass18(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$18$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = 1044;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.BIND_PHONE_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.18.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = 1044;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$18 */
    /* loaded from: classes.dex */
    class AnonymousClass18 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass18(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$18$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1044;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.BIND_PHONE_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.18.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1044;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void bindEmailGetCode(Message message) {
        String str;
        try {
            str = ((JSONObject) message.getObj()).getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        String username = UserManager.getInstance().getCurrentUser().getUsername();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", username);
        linkedHashMap.put("email", str);
        linkedHashMap.put("password", "0");
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.19
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass19(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$19$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_EMAIL, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.19.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            android.os.Message message2 = new android.os.Message();
                            message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE;
                            KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$19 */
    /* loaded from: classes.dex */
    class AnonymousClass19 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass19(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$19$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.ACCOUNT_SEND_EMAIL, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.19.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = KV.EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }

    private void changePhoneCheck(Message message) {
        String str;
        try {
            str = ((JSONObject) message.getObj()).getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        String session_id = UserManager.getInstance().getCurrentUser().getSession_id();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(SpeechEvent.KEY_EVENT_SESSION_ID, session_id);
        linkedHashMap.put("code", str);
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.20
            final /* synthetic */ LinkedHashMap val$params;

            AnonymousClass20(LinkedHashMap linkedHashMap2) {
                r2 = linkedHashMap2;
            }

            /* renamed from: com.kqg.main.base.KaiQiGuSdk$20$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements onNetBack {
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                KaiQiGuSdk.this.doNetRequest(KV.BIND_PHONE_CHECK, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.20.1
                    AnonymousClass1() {
                    }

                    @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                    public void onBack(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                        }
                    }
                }, true);
            }
        }).start();
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$20 */
    /* loaded from: classes.dex */
    class AnonymousClass20 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass20(LinkedHashMap linkedHashMap2) {
            r2 = linkedHashMap2;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$20$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.BIND_PHONE_CHECK, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.20.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        KaiQiGuSdk.this.act.startActivity(new Intent(KaiQiGuSdk.this.act, (Class<?>) BindActivity.class));
                    }
                }
            }, true);
        }
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEvent(Message message) {
        int what = message.getWhat();
        if (what == 0) {
            getWxOrderNum(message);
            return;
        }
        if (what == 1) {
            getAliOrderNum(message);
            return;
        }
        if (what == 2) {
            getUpOrderNum(message);
            return;
        }
        if (what == 1003) {
            doQuickLogin();
            return;
        }
        if (what == 1004) {
            doCertification(message);
            return;
        }
        if (what == 1015) {
            doChangePhone();
            return;
        }
        if (what == 1016) {
            changePhoneCheck(message);
            return;
        }
        if (what == 1022) {
            doRegister();
            return;
        }
        if (what == 1023) {
            doQuickRegister();
            return;
        }
        if (what == 4011) {
            doCancelQuit();
            return;
        }
        if (what == 4012) {
            doQuitGame();
            return;
        }
        switch (what) {
            case 200:
                doInitOver();
                return;
            case 1008:
                loginCallBack1();
                return;
            case KV.EVENT_BIND_EMAIL_GET_CODE1 /* 1031 */:
                bindEmailGetCode(message);
                return;
            case KV.EVENT_BIND_ACCOUNT_BY_EMAIL /* 1034 */:
                goBindAccountByEmail(message);
                return;
            case KV.EVENT_OPEN_ARREEMENT /* 1049 */:
                openAgreement(message);
                return;
            case KV.INIT_ERROR /* 2001 */:
                doInitError();
                return;
            case KV.EVENT_FIND_PASSWORD_BY_PHONE /* 4005 */:
                goFindPasswordByPhone(message);
                return;
            case KV.EVENT_BIND_ACCOUNT_BY_PHONE /* 4009 */:
                goBindAccountByPhone(message);
                return;
            case KV.EVENT_SHOW_AGREE_CONTENT /* 6000 */:
                showAgreeContent();
                return;
            case 10000:
                showBindAccount(message);
                return;
            case 15001:
                showSelectPay(message);
                return;
            case KV.EVENT_PAY_UP_RESULT /* 36002 */:
                onUpPayResult(message);
                return;
            default:
                switch (what) {
                    case 1011:
                        goEnterPassword();
                        return;
                    case 1012:
                        bindRequestLogin(message);
                        return;
                    case 1013:
                        bindPhoneGetCode(message);
                        return;
                    default:
                        switch (what) {
                            case KV.EVENT_FIND_PSD_GET_CODE_EMAIL /* 1036 */:
                                doGetCodeByMail(message);
                                return;
                            case KV.EVENT_FIND_PASSWORD_BY_EMAIL /* 1037 */:
                                findPasswordEmail(message);
                                return;
                            case KV.EVENT_OPEN_ACCOUNT_MANAGER_ACTIVITY /* 1038 */:
                                openAccountManagerActivity();
                                return;
                            default:
                                switch (what) {
                                    case 4000:
                                        showFindPwdView();
                                        return;
                                    case 4001:
                                        findPwdByPhone();
                                        return;
                                    case KV.EVENT_FIND_PWD_BY_HELPER /* 4002 */:
                                        findPwdByHelper();
                                        return;
                                    case KV.EVENT_FINDE_PWD_BY_EMAIL /* 4003 */:
                                        findPwdByEmail();
                                        return;
                                    default:
                                        switch (what) {
                                            case 18000:
                                                startWxPay(message);
                                                return;
                                            case KV.EVENT_PAY_ALI /* 18001 */:
                                                startAliPay(message);
                                                return;
                                            case KV.EVENT_PAY_UP /* 18002 */:
                                                startUpPay(message);
                                                return;
                                            default:
                                                return;
                                        }
                                }
                        }
                }
        }
    }

    private void openAgreement(Message message) {
        Bundle bundle = (Bundle) message.getObj();
        Intent intent = new Intent(this.act, (Class<?>) AgreeContentActivity.class);
        intent.putExtra("bundle", bundle);
        this.act.startActivity(intent);
    }

    private void doCancelQuit() {
        this.quitListener.onQuitCancle();
    }

    private void doQuitGame() {
        this.quitListener.onQuitGame();
    }

    private void onUpPayResult(Message message) {
        ActivityResult activityResult = (ActivityResult) message.getObj();
        Intent data = activityResult.getData();
        PayResult payResult = new PayResult();
        String string = data.getExtras().getString("pay_result");
        if (string.equalsIgnoreCase(Constant.CASH_LOAD_SUCCESS)) {
            payResult.setSuccess(true);
        } else if (string.equalsIgnoreCase(Constant.CASH_LOAD_FAIL)) {
            payResult.setFail(true);
        } else if (string.equalsIgnoreCase(Constant.CASH_LOAD_CANCEL)) {
            payResult.setCancel(true);
        }
        android.os.Message message2 = new android.os.Message();
        message2.what = 20000;
        sendEventResultMessageToUi(message2);
        if (payResult.isSuccess()) {
            Intent intent = new Intent(this.act, (Class<?>) PayResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("PayInfor", (PayInfor) activityResult.getAtt());
            intent.putExtras(bundle);
            this.act.startActivity(intent);
            this.payListener.onPaySuccess();
            return;
        }
        this.payListener.onPayFail(payResult);
    }

    private void startUpPay(Message message) {
        int startPay;
        UpPayInfor upPayInfor = (UpPayInfor) message.getObj();
        if (this.debug) {
            startPay = UPPayAssistEx.startPay(this.act, null, null, upPayInfor.getMhtOrderNo(), "01");
        } else {
            startPay = UPPayAssistEx.startPay(this.act, null, null, upPayInfor.getMhtOrderNo(), "00");
        }
        if (startPay == 2 || startPay == -1) {
            UiUtils.needInstallOrUpdateUpPlugin(this.act, new InstallOrUpdateCallBack() { // from class: com.kqg.main.base.KaiQiGuSdk.21
                AnonymousClass21() {
                }

                @Override // com.kqg.main.callback.InstallOrUpdateCallBack
                public void onFinishedCallBack() {
                    UPPayAssistEx.installUPPayPlugin(KaiQiGuSdk.this.act);
                }
            });
        }
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$21 */
    /* loaded from: classes.dex */
    class AnonymousClass21 implements InstallOrUpdateCallBack {
        AnonymousClass21() {
        }

        @Override // com.kqg.main.callback.InstallOrUpdateCallBack
        public void onFinishedCallBack() {
            UPPayAssistEx.installUPPayPlugin(KaiQiGuSdk.this.act);
        }
    }

    public void aliPayResultBack(android.os.Message message) {
        AliPayInfor aliPayInfor = (AliPayInfor) message.obj;
        String str = aliPayInfor.alipayResult.get(k.a);
        String str2 = aliPayInfor.alipayResult.get(k.b);
        PayResult payResult = new PayResult();
        if (TextUtils.equals(str, "9000")) {
            payResult.setSuccess(true);
        } else {
            payResult.setMes(str2);
            payResult.setSuccess(false);
            if (TextUtils.equals(str, "8000")) {
                payResult.setOnProcess(true);
            }
        }
        android.os.Message message2 = new android.os.Message();
        message2.what = 20000;
        sendEventResultMessageToUi(message2);
        if (payResult.isSuccess()) {
            this.payListener.onPaySuccess();
        } else if (payResult.isOnProcess()) {
            this.payListener.onProcess();
        } else {
            this.payListener.onPayFail(payResult);
        }
    }

    public void wxPayResultBack(android.os.Message message) {
        int intValue = ((Integer) message.obj).intValue();
        PayResult payResult = new PayResult();
        if (intValue == 0) {
            payResult.setSuccess(true);
        } else {
            payResult.setMes("");
            payResult.setSuccess(false);
        }
        android.os.Message message2 = new android.os.Message();
        message2.what = 20000;
        sendEventResultMessageToUi(message2);
        if (payResult.isSuccess()) {
            this.payListener.onPaySuccess();
        } else if (payResult.isOnProcess()) {
            this.payListener.onProcess();
        } else {
            this.payListener.onPayFail(payResult);
        }
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$22 */
    /* loaded from: classes.dex */
    class AnonymousClass22 implements Runnable {
        final /* synthetic */ Message val$message;

        AnonymousClass22(Message message) {
            r2 = message;
        }

        @Override // java.lang.Runnable
        public void run() {
            AliPayInfor aliPayInfor = (AliPayInfor) r2.getObj();
            Map<String, String> payV2 = new PayTask(KaiQiGuSdk.this.act).payV2(aliPayInfor.getRes(), true);
            android.os.Message message = new android.os.Message();
            message.what = KV.EVENT_PAY_ALI_RESULT;
            aliPayInfor.alipayResult = payV2;
            message.obj = aliPayInfor;
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    private void startAliPay(Message message) {
        new Thread(new Runnable() { // from class: com.kqg.main.base.KaiQiGuSdk.22
            final /* synthetic */ Message val$message;

            AnonymousClass22(Message message2) {
                r2 = message2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AliPayInfor aliPayInfor = (AliPayInfor) r2.getObj();
                Map<String, String> payV2 = new PayTask(KaiQiGuSdk.this.act).payV2(aliPayInfor.getRes(), true);
                android.os.Message message2 = new android.os.Message();
                message2.what = KV.EVENT_PAY_ALI_RESULT;
                aliPayInfor.alipayResult = payV2;
                message2.obj = aliPayInfor;
                KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
            }
        }).start();
    }

    public void onPayResultBack(Message message, PayInfor payInfor) {
        android.os.Message message2 = new android.os.Message();
        message2.what = 20000;
        sendEventResultMessageToUi(message2);
        PayResult payResult = (PayResult) message.getObj();
        if (payResult.isSuccess()) {
            Intent intent = new Intent(this.act, (Class<?>) PayResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("PayInfor", payInfor);
            intent.putExtras(bundle);
            this.act.startActivity(intent);
            this.payListener.onPaySuccess();
            return;
        }
        this.payListener.onPayFail(payResult);
    }

    private void startWxPay(Message message) {
        WxPayInfor wxPayInfor = (WxPayInfor) message.getObj();
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.act, WXPayEntryActivity.wx_app_id, true);
        createWXAPI.registerApp(WXPayEntryActivity.wx_app_id);
        new AsyncTask<String, Integer, String>() { // from class: com.kqg.main.base.KaiQiGuSdk.1GetMessage
            final /* synthetic */ Dialog val$dialog;
            final /* synthetic */ IWXAPI val$msgApi;
            final /* synthetic */ WxPayInfor val$payInfor;

            C1GetMessage(WxPayInfor wxPayInfor2, Dialog dialog, IWXAPI createWXAPI2) {
                r2 = wxPayInfor2;
                r3 = dialog;
                r4 = createWXAPI2;
            }

            @Override // android.os.AsyncTask
            public String doInBackground(String... strArr) {
                String[] split = new StringBuffer(r2.getPreSignStr()).toString().split(a.l);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(b.v0, UiUtils.getByte(split, "mhtOrderNo"));
                linkedHashMap.put("product_name", UiUtils.getByte(split, "mhtOrderName"));
                linkedHashMap.put("amount", UiUtils.getByte(split, "mhtOrderAmt"));
                ResponseEntity responseEntity = FastHttp.get(KV.WEIXIN_Prepayment_URL, (LinkedHashMap<String, String>) linkedHashMap);
                String str = null;
                try {
                    JSONObject jSONObject = new JSONObject(responseEntity.getContentAsString());
                    str = jSONObject.toString();
                    Log.e("Tag", "json=" + jSONObject.toString());
                    return str;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return str;
                }
            }

            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                super.onPostExecute((C1GetMessage) str);
                LogUtil.logE("收到的请求信息:" + str);
                Log.e("Tag", "res=" + str);
                r3.dismiss();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (str == null || !jSONObject.optString("status").equals("0")) {
                        return;
                    }
                    JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
                    if (jSONObject2.optString(FontsContractCompat.Columns.RESULT_CODE).equals(com.alipay.sdk.m.a0.c.p)) {
                        String optString = jSONObject2.optString(com.alipay.sdk.m.p.a.k);
                        Log.e("Tag", "time=" + optString);
                        PayReq payReq = new PayReq();
                        String optString2 = jSONObject2.optString(SpeechConstant.APPID);
                        String optString3 = jSONObject2.optString("mch_id");
                        String optString4 = jSONObject2.optString("prepay_id");
                        String optString5 = jSONObject2.optString("nonce_str");
                        String optString6 = jSONObject2.optString("sign");
                        payReq.appId = optString2;
                        payReq.partnerId = optString3;
                        payReq.prepayId = optString4;
                        payReq.packageValue = "Sign=WXPay";
                        payReq.nonceStr = optString5;
                        payReq.timeStamp = optString;
                        payReq.sign = optString6;
                        Log.e("Tag", "appid=" + optString2 + "partnerId=" + optString3 + "prepayId=" + optString4 + "packageValue=" + payReq.packageValue + "nonceStr=" + optString5 + "timeStamp=" + optString + "sign=" + optString6);
                        StringBuilder sb = new StringBuilder();
                        sb.append("sign=");
                        sb.append(jSONObject2.optString("sign"));
                        Log.e("Tag", sb.toString());
                        r4.sendReq(payReq);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("resData=");
                        sb2.append(jSONObject2.toString());
                        sb2.append(" request=");
                        sb2.append(payReq.toString());
                        Log.e("Tag2", sb2.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(MerchantTools.urlEncode(wxPayInfor2.getPreSignStr()));
        BaseApplication.getInstance().setPayInfor(wxPayInfor2);
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$1GetMessage */
    /* loaded from: classes.dex */
    class C1GetMessage extends AsyncTask<String, Integer, String> {
        final /* synthetic */ Dialog val$dialog;
        final /* synthetic */ IWXAPI val$msgApi;
        final /* synthetic */ WxPayInfor val$payInfor;

        C1GetMessage(WxPayInfor wxPayInfor2, Dialog dialog, IWXAPI createWXAPI2) {
            r2 = wxPayInfor2;
            r3 = dialog;
            r4 = createWXAPI2;
        }

        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            String[] split = new StringBuffer(r2.getPreSignStr()).toString().split(a.l);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(b.v0, UiUtils.getByte(split, "mhtOrderNo"));
            linkedHashMap.put("product_name", UiUtils.getByte(split, "mhtOrderName"));
            linkedHashMap.put("amount", UiUtils.getByte(split, "mhtOrderAmt"));
            ResponseEntity responseEntity = FastHttp.get(KV.WEIXIN_Prepayment_URL, (LinkedHashMap<String, String>) linkedHashMap);
            String str = null;
            try {
                JSONObject jSONObject = new JSONObject(responseEntity.getContentAsString());
                str = jSONObject.toString();
                Log.e("Tag", "json=" + jSONObject.toString());
                return str;
            } catch (JSONException e) {
                e.printStackTrace();
                return str;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((C1GetMessage) str);
            LogUtil.logE("收到的请求信息:" + str);
            Log.e("Tag", "res=" + str);
            r3.dismiss();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (str == null || !jSONObject.optString("status").equals("0")) {
                    return;
                }
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
                if (jSONObject2.optString(FontsContractCompat.Columns.RESULT_CODE).equals(com.alipay.sdk.m.a0.c.p)) {
                    String optString = jSONObject2.optString(com.alipay.sdk.m.p.a.k);
                    Log.e("Tag", "time=" + optString);
                    PayReq payReq = new PayReq();
                    String optString2 = jSONObject2.optString(SpeechConstant.APPID);
                    String optString3 = jSONObject2.optString("mch_id");
                    String optString4 = jSONObject2.optString("prepay_id");
                    String optString5 = jSONObject2.optString("nonce_str");
                    String optString6 = jSONObject2.optString("sign");
                    payReq.appId = optString2;
                    payReq.partnerId = optString3;
                    payReq.prepayId = optString4;
                    payReq.packageValue = "Sign=WXPay";
                    payReq.nonceStr = optString5;
                    payReq.timeStamp = optString;
                    payReq.sign = optString6;
                    Log.e("Tag", "appid=" + optString2 + "partnerId=" + optString3 + "prepayId=" + optString4 + "packageValue=" + payReq.packageValue + "nonceStr=" + optString5 + "timeStamp=" + optString + "sign=" + optString6);
                    StringBuilder sb = new StringBuilder();
                    sb.append("sign=");
                    sb.append(jSONObject2.optString("sign"));
                    Log.e("Tag", sb.toString());
                    r4.sendReq(payReq);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("resData=");
                    sb2.append(jSONObject2.toString());
                    sb2.append(" request=");
                    sb2.append(payReq.toString());
                    Log.e("Tag2", sb2.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showSelectPay(Message message) {
        PayInfor payInfor = (PayInfor) message.getObj();
        Intent intent = new Intent(this.act, (Class<?>) PaySelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PayInfor", payInfor);
        intent.putExtras(bundle);
        this.act.startActivity(intent);
    }

    private void showBindAccount(Message message) {
        this.act.startActivity(new Intent(this.act, (Class<?>) BindActivity.class));
    }

    private void showAgreeContent() {
        this.act.startActivity(new Intent(this.act, (Class<?>) AgreeContentActivity.class));
    }

    private String getMetaDataValue(String str) {
        Object obj = null;
        try {
            ApplicationInfo applicationInfo = ((ContextWrapper) this.ctx).getPackageManager().getApplicationInfo(this.ctx.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("getMetadata", str + "not found");
        }
        return obj == null ? "" : obj.toString();
    }

    private void doInitError() {
        OnInitCallBackListener onInitCallBackListener = this.initListener;
        if (onInitCallBackListener != null) {
            onInitCallBackListener.onInitBack(KV.INIT_ERROR);
        }
    }

    private void doInitOver() {
        OnInitCallBackListener onInitCallBackListener = this.initListener;
        if (onInitCallBackListener != null) {
            onInitCallBackListener.onInitBack(200);
        }
        try {
            String metaDataValue = getMetaDataValue("startActivity");
            if (metaDataValue != "") {
                this.act.startActivity(new Intent(this.act, Class.forName(metaDataValue)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventBackgroundThread(BackGroundMessage backGroundMessage) {
        int what = backGroundMessage.getWhat();
        if (what == 1002) {
            registerAccount(backGroundMessage);
            return;
        }
        if (what == 1005) {
            goCertification();
            return;
        }
        if (what == 4004) {
            getPhoneCode(backGroundMessage.getEntity());
            return;
        }
        if (what == 8001) {
            getGuestInfor(backGroundMessage);
            return;
        }
        if (what == 10001) {
            bindAccount(backGroundMessage);
            return;
        }
        switch (what) {
            case KV.EVENT_GET_PHONE_CODE_TO_LOGIN /* 1039 */:
                getPhoneCode(backGroundMessage.getEntity());
                return;
            case KV.EVENT_FORCE_BIND_PHONE /* 1040 */:
                forceBindPhone(backGroundMessage.getEntity());
                return;
            case KV.EVENT_LOGIN_WITH_PASSWORD /* 1041 */:
                loginBackWithPassword(backGroundMessage.getEntity());
                return;
            case KV.EVENT_LOGIN_WITH_PHONE /* 1042 */:
                loginBackWithPhone(backGroundMessage.getEntity());
                return;
            case KV.EVENT_CHECK_CODE /* 1043 */:
                checkCode(backGroundMessage.getEntity());
                return;
            default:
                return;
        }
    }

    private void bindAccount(BackGroundMessage backGroundMessage) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("deviceid", Handler_System.getDeviceId(this.act));
        User data = ((Login) backGroundMessage.getEntity()).getData();
        linkedHashMap.put("username", data.getUsername());
        linkedHashMap.put("password", data.getPassword());
        doNetRequest(KV.QUICK_REGISTER_BIND, linkedHashMap, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.23
            AnonymousClass23() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                android.os.Message message = new android.os.Message();
                message.what = 10001;
                if (jSONObject != null) {
                    User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 10001);
                    UserManager.getInstance().updateUser(userByJsonData);
                    UserManager.getInstance().deleteGuestUser();
                    message.obj = userByJsonData;
                }
                KaiQiGuSdk.this.sendEventResultMessageToUi(message);
            }
        }, true);
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$23 */
    /* loaded from: classes.dex */
    class AnonymousClass23 implements onNetBack {
        AnonymousClass23() {
        }

        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
        public void onBack(JSONObject jSONObject) {
            android.os.Message message = new android.os.Message();
            message.what = 10001;
            if (jSONObject != null) {
                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 10001);
                UserManager.getInstance().updateUser(userByJsonData);
                UserManager.getInstance().deleteGuestUser();
                message.obj = userByJsonData;
            }
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    private void getGuestInfor(BackGroundMessage backGroundMessage) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("deviceid", Handler_System.getDeviceId(this.act));
        doNetRequest(KV.ACCOUNT_QUICK_REGISTER, linkedHashMap, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.24
            AnonymousClass24() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                android.os.Message message = new android.os.Message();
                message.what = 1003;
                if (jSONObject != null) {
                    User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1002);
                    UserManager.getInstance().saveGuest(userByJsonData);
                    message.obj = userByJsonData;
                }
                KaiQiGuSdk.this.sendEventResultMessageToUi(message);
            }
        }, true);
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$24 */
    /* loaded from: classes.dex */
    class AnonymousClass24 implements onNetBack {
        AnonymousClass24() {
        }

        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
        public void onBack(JSONObject jSONObject) {
            android.os.Message message = new android.os.Message();
            message.what = 1003;
            if (jSONObject != null) {
                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1002);
                UserManager.getInstance().saveGuest(userByJsonData);
                message.obj = userByJsonData;
            }
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    public void sendCommonMessageToUi(String str) {
        android.os.Message message = new android.os.Message();
        message.what = KV.EVENT_COMMON_WARN;
        message.obj = str;
        this.handler.sendMessage(message);
    }

    public void sendEventResultMessageToUi(android.os.Message message) {
        this.handler.sendMessage(message);
    }

    private void doNetRequest1(String str, LinkedHashMap<String, String> linkedHashMap, onNetBack onnetback) {
        InternetConfig internetConfig = new InternetConfig();
        internetConfig.setCharset("utf-8");
        internetConfig.setTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
        if (Handler_System.isNetworkAvailable(this.act)) {
            ResponseEntity responseEntity = FastHttp.get(str, linkedHashMap, internetConfig);
            int status = responseEntity.getStatus();
            if (status != 0) {
                if (status == 1) {
                    sendCommonMessageToUi(UiUtils.getResString("net_error_input"));
                    return;
                }
                return;
            }
            try {
                String contentAsString = responseEntity.getContentAsString();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("status", 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("mhtOrderNo", contentAsString);
                jSONObject.put("data", jSONObject2);
                int i = jSONObject.getInt("status");
                JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                if (i != 0 && jSONObject3.has("message")) {
                    sendCommonMessageToUi(jSONObject3.getString("message"));
                }
                if (i == 0) {
                    onnetback.onBack(jSONObject3);
                    return;
                } else if (i == 1) {
                    onnetback.onBack(null);
                    return;
                } else {
                    onnetback.onBack(null);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                onnetback.onBack(null);
                return;
            }
        }
        sendCommonMessageToUi(UiUtils.getResString("net_error_input"));
    }

    public void doNetRequest(String str, LinkedHashMap<String, String> linkedHashMap, onNetBack onnetback, boolean z) {
        ResponseEntity post;
        InternetConfig internetConfig = new InternetConfig();
        internetConfig.setCharset("utf-8");
        internetConfig.setTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
        internetConfig.setHttps(true);
        if (z) {
            post = FastHttp.get(str, linkedHashMap, internetConfig);
        } else {
            post = FastHttp.post(str, linkedHashMap, internetConfig);
        }
        Log.e("DZ", "网络请求返回的值是......" + post.toString());
        int status = post.getStatus();
        if (status != 0) {
            if (status == 1) {
                sendCommonMessageToUi(UiUtils.getResString("net_error_input"));
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(post.getContentAsString());
            Log.e("doNetRequest", post.getContentAsString());
            int i = jSONObject.getInt("status");
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (i != 0 && jSONObject2.has("message")) {
                sendCommonMessageToUi(jSONObject2.getString("message"));
            }
            if (i == 0) {
                onnetback.onBack(jSONObject2);
            } else if (i == 1) {
                onnetback.onBack(null);
            } else {
                onnetback.onBack(null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onnetback.onBack(null);
        }
    }

    public void doNetRequestpay(String str, LinkedHashMap<String, String> linkedHashMap, onNetBack onnetback, boolean z) {
        ResponseEntity post;
        InternetConfig internetConfig = new InternetConfig();
        internetConfig.setCharset("utf-8");
        internetConfig.setTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
        internetConfig.setHttps(true);
        if (z) {
            post = FastHttp.get(str, linkedHashMap, internetConfig);
        } else {
            post = FastHttp.post(str, linkedHashMap, internetConfig);
        }
        int status = post.getStatus();
        if (status != 0) {
            if (status == 1) {
                sendCommonMessageToUi(UiUtils.getResString("net_error_input"));
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(post.getContentAsString());
            Log.e("doNetRequest", post.getContentAsString());
            int i = jSONObject.getInt("status");
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (i != 0 && jSONObject2.has("message")) {
                sendCommonMessageToUi(jSONObject2.getString("message"));
            }
            if (i == 0) {
                onnetback.onBack(jSONObject2);
            } else if (i == 1) {
                onnetback.onBack(null);
            } else {
                onnetback.onBack(null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onnetback.onBack(null);
        }
    }

    private void welcomeUser(User user) {
        Intent intent = new Intent(this.act, (Class<?>) WelcomeActivity.class);
        intent.putExtra("user", user);
        this.act.startActivity(intent);
    }

    private void registerAccount(BackGroundMessage backGroundMessage) {
        User data = ((Login) backGroundMessage.getEntity()).getData();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("username", data.getUsername());
        linkedHashMap.put("password", data.getPassword());
        isNeedCer = false;
        doNetRequest(KV.ACCOUNT_REGISTER, linkedHashMap, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.25
            AnonymousClass25() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                android.os.Message message = new android.os.Message();
                message.what = 1002;
                if (jSONObject != null) {
                    User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1002);
                    UserManager.getInstance().saveUser(userByJsonData);
                    UserManager.getInstance().setCurrentUser(userByJsonData);
                    message.obj = userByJsonData;
                    if (jSONObject.has("authentication")) {
                        KaiQiGuSdk.isNeedCer = true;
                        try {
                            KaiQiGuSdk.this.msg1 = new Message(1, jSONObject.getString("username"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                KaiQiGuSdk.this.sendEventResultMessageToUi(message);
            }
        }, true);
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$25 */
    /* loaded from: classes.dex */
    class AnonymousClass25 implements onNetBack {
        AnonymousClass25() {
        }

        @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
        public void onBack(JSONObject jSONObject) {
            android.os.Message message = new android.os.Message();
            message.what = 1002;
            if (jSONObject != null) {
                User userByJsonData = UserManager.getInstance().getUserByJsonData(jSONObject, 1002);
                UserManager.getInstance().saveUser(userByJsonData);
                UserManager.getInstance().setCurrentUser(userByJsonData);
                message.obj = userByJsonData;
                if (jSONObject.has("authentication")) {
                    KaiQiGuSdk.isNeedCer = true;
                    try {
                        KaiQiGuSdk.this.msg1 = new Message(1, jSONObject.getString("username"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            KaiQiGuSdk.this.sendEventResultMessageToUi(message);
        }
    }

    public void setHandler(UiHandler uiHandler) {
        this.handler = uiHandler;
    }

    public void loginCallBack1() {
        User currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            this.loginListener.onLoginSuccess(currentUser);
            welcomeUser(currentUser);
            this.loginListener = null;
        }
    }

    public void loginCallBack(android.os.Message message) {
        if (message.obj != null) {
            User user = (User) message.obj;
            UserManager.getInstance().setCurrentUser(user);
            OnLoginCallBackListener onLoginCallBackListener = this.loginListener;
            if (onLoginCallBackListener != null) {
                onLoginCallBackListener.onLoginSuccess(user);
            }
            welcomeUser(user);
            this.loginListener = null;
        }
    }

    public void payInforCallBack(android.os.Message message) {
        if (message.obj != null) {
            int i = message.what + 18000;
            PayInfor payInfor = (PayInfor) message.obj;
            Intent intent = new Intent(this.act, (Class<?>) PayConfirmActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("what", i);
            bundle.putSerializable("PayInfor", payInfor);
            intent.putExtras(bundle);
            this.act.startActivity(intent);
        }
    }

    public void onDestroy(BaseActivity baseActivity) {
        baseActivity.getClass().getSimpleName();
        UserManager.getInstance().hasNativeUserData();
    }

    public void KVexit(Activity activity) {
        activity.finish();
    }

    public static boolean getNeedlogin() {
        return isNeedLogin;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getPhoneCode(com.kqg.main.model.CommonEntity r8) {
        /*
            r7 = this;
            java.lang.String r0 = "phone"
            java.lang.String r1 = "session_id"
            java.lang.String r2 = "type"
            java.lang.String r3 = ""
            org.json.JSONObject r8 = r8.getJson()
            java.lang.String r4 = "haha"
            java.lang.String r5 = "hehe"
            android.util.Log.e(r4, r5)
            java.lang.String r5 = r8.getString(r2)     // Catch: org.json.JSONException -> L36
            android.util.Log.e(r4, r5)     // Catch: org.json.JSONException -> L36
            java.lang.String r4 = r8.getString(r0)     // Catch: org.json.JSONException -> L36
            java.lang.String r4 = r7.getPhone(r4)     // Catch: org.json.JSONException -> L33
            java.lang.String r5 = r8.getString(r2)     // Catch: org.json.JSONException -> L33
            boolean r6 = r8.has(r1)     // Catch: org.json.JSONException -> L31
            if (r6 == 0) goto L3c
            java.lang.String r8 = r8.getString(r1)     // Catch: org.json.JSONException -> L31
            goto L3d
        L31:
            r8 = move-exception
            goto L39
        L33:
            r8 = move-exception
            r5 = r3
            goto L39
        L36:
            r8 = move-exception
            r4 = r3
            r5 = r4
        L39:
            r8.printStackTrace()
        L3c:
            r8 = r3
        L3d:
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
            r6.put(r0, r4)
            r6.put(r2, r5)
            java.lang.String r0 = "lang"
            java.lang.String r2 = "vn"
            r6.put(r0, r2)
            boolean r0 = r8.equals(r3)
            if (r0 != 0) goto L58
            r6.put(r1, r8)
        L58:
            java.lang.Thread r8 = new java.lang.Thread
            com.kqg.main.base.KaiQiGuSdk$26 r0 = new com.kqg.main.base.KaiQiGuSdk$26
            r0.<init>()
            r8.<init>(r0)
            r8.run()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kqg.main.base.KaiQiGuSdk.getPhoneCode(com.kqg.main.model.CommonEntity):void");
    }

    /* renamed from: com.kqg.main.base.KaiQiGuSdk$26 */
    /* loaded from: classes.dex */
    class AnonymousClass26 implements Runnable {
        final /* synthetic */ LinkedHashMap val$params;

        AnonymousClass26(LinkedHashMap linkedHashMap) {
            r2 = linkedHashMap;
        }

        /* renamed from: com.kqg.main.base.KaiQiGuSdk$26$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements onNetBack {
            AnonymousClass1() {
            }

            @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
            public void onBack(JSONObject jSONObject) {
                if (jSONObject != null) {
                    android.os.Message message = new android.os.Message();
                    message.what = 1020;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                } else {
                    android.os.Message message2 = new android.os.Message();
                    message2.what = 1025;
                    KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            KaiQiGuSdk.this.doNetRequest(KV.GET_MOBILE_CODE, r2, new onNetBack() { // from class: com.kqg.main.base.KaiQiGuSdk.26.1
                AnonymousClass1() {
                }

                @Override // com.kqg.main.base.KaiQiGuSdk.onNetBack
                public void onBack(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        android.os.Message message = new android.os.Message();
                        message.what = 1020;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message);
                    } else {
                        android.os.Message message2 = new android.os.Message();
                        message2.what = 1025;
                        KaiQiGuSdk.this.sendEventResultMessageToUi(message2);
                    }
                }
            }, true);
        }
    }
}
