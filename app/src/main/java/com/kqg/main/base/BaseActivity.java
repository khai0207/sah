package com.kqg.main.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectBefore;
import com.android.pc.ioc.inject.InjectInit;
import com.android.pc.ioc.view.listener.OnClick;
import com.kqg.main.activity.ForceBindPhone;
import com.kqg.main.base.UiHandler;
import com.kqg.main.constant.KV;
import com.kqg.main.model.AsyncMessage;
import com.kqg.main.model.BackGroundMessage;
import com.kqg.main.model.MainThreadMessage;
import com.kqg.main.utils.LogUtil;
import com.kqg.main.utils.UiUtils;
import com.kqg.main.utils.ValidatorUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class BaseActivity extends Activity implements UiHandler.IHandler, EventBusHandler {
    private static KaiQiGuSdk sdk;
    private boolean inBackGround;
    private boolean notSwallowKeyDown;
    private OnClick onClick;
    protected static UiHandler handler = new UiHandler(Looper.getMainLooper());
    private static List<Activity> activies = new ArrayList();
    private static List<Activity> bindActivies = new ArrayList();

    protected void afterOnCreate() {
    }

    protected void beforeOnCreate() {
    }

    public BaseActivity() {
        handler.setHandler(this);
        this.onClick = new OnClick();
        KaiQiGuSdk kaiQiGuSdk = KaiQiGuSdk.getInstance();
        sdk = kaiQiGuSdk;
        kaiQiGuSdk.setHandler(handler);
        this.inBackGround = false;
        this.notSwallowKeyDown = true;
    }

    public void resumeSetHandler() {
        if (handler.getHandler() != this) {
            handler.setHandler(this);
        }
        UiHandler uiHandler = sdk.handler;
        UiHandler uiHandler2 = handler;
        if (uiHandler != uiHandler2) {
            sdk.setHandler(uiHandler2);
        }
    }

    protected void registRemoveList() {
        activies.add(this);
    }

    protected void registBindRemoveList() {
        bindActivies.add(this);
    }

    public boolean isNotSwallowKeyDown() {
        return this.notSwallowKeyDown;
    }

    public void setNotSwallowKeyDown(boolean z) {
        this.notSwallowKeyDown = z;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        sdk.onDestroy(this);
        EventBus.getDefault().unregister(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.inBackGround = true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        resumeSetHandler();
        this.inBackGround = false;
    }

    @InjectBefore
    private void before() {
        LogUtil.log("@InjectBefore");
        beforeOnCreate();
    }

    @InjectInit
    private void init() {
        LogUtil.log("@InjectInit");
        afterOnCreate();
    }

    protected void registOnClick(View view, String str) {
        this.onClick.listener(view, this, str);
    }

    protected void registOnClicks(String str, View... viewArr) {
        for (View view : viewArr) {
            registOnClick(view, str);
        }
    }

    protected void setView(String str) {
        Log.v("kqgsdk", "setView --> " + str);
        setContentView(UiUtils.getLayOut(str));
    }

    public View getView(String str) {
        return findViewById(UiUtils.getId(str));
    }

    private void doLoginNext(Message message) {
        if (KaiQiGuSdk.isNeedCer) {
            finishAllActivities();
            sdk.enterCertification();
        } else {
            if (message.obj != null) {
                finishAllActivities();
            }
            sdk.loginCallBack(message);
        }
    }

    private void openForceBindPhone() {
        startActivity(new Intent(this, (Class<?>) ForceBindPhone.class));
    }

    public void handleMessage(Message message) {
        UiUtils.hideLoadingDialog();
        int i = message.what;
        if (i == 0 || i == 1 || i == 2) {
            sdk.payInforCallBack(message);
            return;
        }
        if (i == 1040) {
            doLoginNext(message);
            return;
        }
        if (i == 9024) {
            UiUtils.showImageToast(message.obj.toString());
            return;
        }
        if (i != 10001) {
            if (i != 20000) {
                switch (i) {
                    case 1001:
                        Bundle data = message.getData();
                        if (message.obj != null) {
                            String string = data.getString("byPassword");
                            if (string != null && string.equals("1")) {
                                if (data.containsKey("phone")) {
                                    if (ValidatorUtils.validatorPhone(message.getData().getString("phone").trim())) {
                                        doLoginNext(message);
                                        return;
                                    } else {
                                        openForceBindPhone();
                                        return;
                                    }
                                }
                                openForceBindPhone();
                                return;
                            }
                            doLoginNext(message);
                            return;
                        }
                        doLoginNext(message);
                        return;
                    case 1002:
                        break;
                    case 1003:
                        finishAllActivities();
                        return;
                    default:
                        switch (i) {
                            case KV.EVENT_PAY_WEIXIN_RESULT /* 36000 */:
                                sdk.wxPayResultBack(message);
                                return;
                            case KV.EVENT_PAY_ALI_RESULT /* 36001 */:
                                sdk.aliPayResultBack(message);
                                return;
                            default:
                                return;
                        }
                }
            } else {
                finishAllActivities();
                return;
            }
        }
        doLoginNext(message);
    }

    public void finishAllActivities() {
        int size = activies.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activies.get(i);
            Log.e("DZ", "关闭的activity是。。。。。" + activity);
            activity.finish();
        }
        activies.clear();
    }

    public void finishBindActivities() {
        int size = bindActivies.size();
        for (int i = 0; i < size; i++) {
            bindActivies.get(i).finish();
        }
        bindActivies.clear();
    }

    private static void postMessage(Object obj) {
        EventBus.getDefault().post(obj);
    }

    protected static void postMessageOnCurrentThread(com.kqg.main.model.Message message) {
        postMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void postMessageOnBackgroundThread(BackGroundMessage backGroundMessage) {
        if (this.inBackGround) {
            return;
        }
        UiUtils.showLoadingDialog(this);
        postMessage(backGroundMessage);
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEvent(com.kqg.main.model.Message message) {
        if (this.inBackGround) {
            return;
        }
        sdk.setAct(this);
        sdk.onEvent(message);
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventMainThread(MainThreadMessage mainThreadMessage) {
        if (this.inBackGround) {
            return;
        }
        sdk.setAct(this);
        sdk.onEventMainThread(mainThreadMessage);
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventBackgroundThread(BackGroundMessage backGroundMessage) {
        if (this.inBackGround) {
            return;
        }
        sdk.setAct(this);
        sdk.onEventBackgroundThread(backGroundMessage);
    }

    @Override // com.kqg.main.base.EventBusHandler
    public void onEventAsync(AsyncMessage asyncMessage) {
        if (this.inBackGround) {
            return;
        }
        sdk.setAct(this);
        sdk.onEventAsync(asyncMessage);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.notSwallowKeyDown) {
                return super.onKeyDown(i, keyEvent);
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
