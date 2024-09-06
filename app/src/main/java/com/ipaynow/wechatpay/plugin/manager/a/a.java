package com.ipaynow.wechatpay.plugin.manager.a;

import android.app.Activity;
import android.content.Context;
import com.ipaynow.wechatpay.plugin.manager.route.dto.RequestParams;
import com.ipaynow.wechatpay.plugin.manager.route.impl.ReceivePayResult;
import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;
import com.ipaynow.wechatpay.plugin.view.f;

/* loaded from: classes.dex */
public final class a {
    private boolean aO;
    private boolean aP;
    private boolean aQ;
    private boolean aR;
    private boolean aS;
    private boolean aT;
    private boolean aU;
    private boolean aV;
    private boolean aW;
    private boolean aX;
    private f aY;
    private Activity aZ;
    private RequestParams b;
    private String ba;
    private Activity bb;
    private Context bc;
    private ReceivePayResult bd;
    private IpaynowLoading be;
    private String mhtOrderNo;

    private a() {
        this.aO = false;
        this.aP = false;
        this.aQ = false;
        this.aR = false;
        this.aS = false;
        this.aT = false;
        this.aU = false;
        this.aV = false;
        this.aW = false;
        this.aX = false;
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static a r() {
        a aVar;
        aVar = b.bf;
        return aVar;
    }

    public final Activity A() {
        return this.bb;
    }

    public final boolean B() {
        return this.aV;
    }

    public final boolean C() {
        return this.aO;
    }

    public final boolean D() {
        return this.aQ;
    }

    public final boolean E() {
        return this.aR;
    }

    public final boolean F() {
        return this.aS;
    }

    public final a G() {
        this.aT = true;
        return this;
    }

    public final a a(ReceivePayResult receivePayResult) {
        this.bd = receivePayResult;
        return this;
    }

    public final void a(Activity activity) {
        this.aZ = activity;
    }

    public final void a(f fVar) {
        this.aY = fVar;
    }

    public final void a(boolean z) {
        this.aX = z;
    }

    public final a b(Activity activity) {
        this.bb = activity;
        return this;
    }

    public final void b(IpaynowLoading ipaynowLoading) {
        this.be = ipaynowLoading;
    }

    public final void b(boolean z) {
        this.aP = z;
    }

    public final a c(Context context) {
        this.bc = context;
        return this;
    }

    public final a c(boolean z) {
        this.aW = z;
        return this;
    }

    public final void clearAll() {
        this.aO = false;
        this.aQ = false;
        this.aR = false;
        this.aT = false;
        this.aU = false;
        this.aV = false;
        this.aZ = null;
        this.b = null;
        IpaynowLoading ipaynowLoading = this.be;
        if (ipaynowLoading != null) {
            ipaynowLoading.dismiss();
        }
        System.gc();
    }

    public final a d(boolean z) {
        this.aV = z;
        return this;
    }

    public final void d(String str) {
        this.mhtOrderNo = str;
    }

    public final a e(boolean z) {
        this.aO = z;
        return this;
    }

    public final void e(String str) {
        this.ba = str;
    }

    public final a f(boolean z) {
        this.aQ = z;
        return this;
    }

    public final a g(boolean z) {
        this.aR = z;
        return this;
    }

    public final Context getContext() {
        return this.bc;
    }

    public final a h(boolean z) {
        this.aS = z;
        return this;
    }

    public final void onActivityDestroy() {
        this.bb = null;
        this.bc = null;
        this.be = null;
        this.aZ = null;
        this.ba = null;
        this.mhtOrderNo = null;
        this.bd = null;
        this.b = null;
        f fVar = this.aY;
        if (fVar != null) {
            fVar.onDestroy();
        }
        this.aY = null;
        com.ipaynow.wechatpay.plugin.manager.c.a.K().clear();
        System.gc();
    }

    public final boolean s() {
        return this.aX;
    }

    public final String t() {
        return this.mhtOrderNo;
    }

    public final String toString() {
        return "isMainThread=" + this.aO + "isAddAllPermission=" + this.aQ + "isInited=" + this.aS + "isLegalPayChannelType=" + this.aT + "isPluginSupportPayChannelType=" + this.aU + "isWechatInstalled=" + this.aV;
    }

    public final String u() {
        return this.ba;
    }

    public final Activity v() {
        return this.aZ;
    }

    public final IpaynowLoading w() {
        return this.be;
    }

    public final boolean x() {
        return this.aW;
    }

    public final boolean y() {
        return this.aP;
    }

    public final ReceivePayResult z() {
        return this.bd;
    }
}
