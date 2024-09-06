package com.kqg.main.base;

import android.app.Application;
import com.android.pc.ioc.app.Ioc;
import com.kqg.main.model.PayInfor;

/* loaded from: classes.dex */
public class BaseApplication extends Application {
    private static final BaseApplication INSTANCE = new BaseApplication();
    private PayInfor payInfor;

    @Override // android.app.Application
    public void onCreate() {
        Ioc.getIoc().init(this);
        KaiQiGuSdk.getInstance().setCtx(getApplicationContext());
        super.onCreate();
    }

    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    public PayInfor getPayInfor() {
        return this.payInfor;
    }

    public void setPayInfor(PayInfor payInfor) {
        this.payInfor = payInfor;
    }
}
