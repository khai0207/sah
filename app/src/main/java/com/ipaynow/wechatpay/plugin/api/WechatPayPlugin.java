package com.ipaynow.wechatpay.plugin.api;

import android.app.Activity;
import android.content.Context;
import com.ipaynow.wechatpay.plugin.manager.route.dto.RequestParams;
import com.ipaynow.wechatpay.plugin.manager.route.impl.ReceivePayResult;
import com.ipaynow.wechatpay.plugin.utils.g;
import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;
import com.ipaynow.wechatpay.plugin.view.f;
import com.ipaynow.wechatpay.plugin.view.r;

/* loaded from: classes.dex */
public class WechatPayPlugin {
    private IpaynowLoading loading = null;

    public static WechatPayPlugin getInstance() {
        WechatPayPlugin wechatPayPlugin;
        wechatPayPlugin = b.a;
        return wechatPayPlugin;
    }

    public IpaynowLoading getDefaultLoading() {
        return new f(com.ipaynow.wechatpay.plugin.manager.a.a.r().getContext());
    }

    public WechatPayPlugin init(Context context) {
        if (context == null) {
            com.ipaynow.wechatpay.plugin.manager.a.a.r().h(false);
            throw new RuntimeException("context cannot be null");
        }
        com.ipaynow.wechatpay.plugin.manager.a.a.r().h(true);
        com.ipaynow.wechatpay.plugin.manager.a.a.r().c(context);
        return this;
    }

    public void onActivityDestroy() {
        com.ipaynow.wechatpay.plugin.manager.a.a.r().onActivityDestroy();
        this.loading = null;
    }

    public void pay(RequestParams requestParams) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        try {
            if (r.s()) {
                return;
            }
            if (requestParams == null) {
                new r(r.getContext()).B("请传入插件支付参数").aj().ak().show();
                if (this.loading != null) {
                    this.loading.dismiss();
                    return;
                }
                return;
            }
            a aVar = new a();
            if (aVar.a(r.getContext(), requestParams)) {
                aVar.a();
            } else if (this.loading != null) {
                this.loading.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(e);
        }
    }

    public void pay(String str) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        try {
            if (r.s()) {
                return;
            }
            if (g.y(str)) {
                new r(r.getContext()).B("请传入插件支付参数").aj().ak().show();
                if (this.loading != null) {
                    this.loading.dismiss();
                    return;
                }
                return;
            }
            a aVar = new a();
            if (aVar.a(r.getContext(), str)) {
                aVar.a();
            } else if (this.loading != null) {
                this.loading.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(e);
        }
    }

    public WechatPayPlugin setCallResultActivity(Activity activity) {
        com.ipaynow.wechatpay.plugin.manager.a.a.r().b(activity);
        return this;
    }

    public WechatPayPlugin setCallResultReceiver(ReceivePayResult receivePayResult) {
        com.ipaynow.wechatpay.plugin.manager.a.a.r().a(receivePayResult);
        return this;
    }

    public WechatPayPlugin setCustomDialog(IpaynowLoading ipaynowLoading) {
        if (ipaynowLoading != null) {
            this.loading = ipaynowLoading;
        }
        com.ipaynow.wechatpay.plugin.manager.a.a.r().b(this.loading);
        return this;
    }

    public WechatPayPlugin setShowConfirmDialog(boolean z) {
        com.ipaynow.wechatpay.plugin.c.f.y = z;
        return this;
    }
}
