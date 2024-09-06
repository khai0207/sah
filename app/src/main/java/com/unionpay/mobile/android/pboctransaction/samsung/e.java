package com.unionpay.mobile.android.pboctransaction.samsung;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.SeAppListItem;
import com.unionpay.tsmservice.data.VirtualCardInfo;
import com.unionpay.tsmservice.result.CheckSSamsungPayResult;
import com.unionpay.tsmservice.result.GetCardInfoBySpayResult;
import com.unionpay.tsmservice.result.GetSeAppListResult;
import com.unionpay.tsmservice.result.InitResult;
import com.unionpay.tsmservice.result.OpenChannelResult;
import com.unionpay.tsmservice.result.SendApduResult;

/* loaded from: classes.dex */
public final class e extends ITsmCallback.Stub {
    private int a;
    private Handler b;

    public e(int i, Handler handler) {
        this.a = i;
        this.b = handler;
    }

    @Override // com.unionpay.tsmservice.ITsmCallback
    public final void onError(String str, String str2) throws RemoteException {
        Log.e("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        if (this.a == 1016) {
            f.f = false;
            f.e = true;
        }
        Handler handler = this.b;
        handler.sendMessage(Message.obtain(handler, 1, this.a, 0, str));
    }

    @Override // com.unionpay.tsmservice.ITsmCallback
    public final void onResult(Bundle bundle) throws RemoteException {
        int i = this.a;
        if (i == 1000) {
            bundle.setClassLoader(InitResult.class.getClassLoader());
            Handler handler = this.b;
            handler.sendMessage(Message.obtain(handler, 1000, bundle));
            return;
        }
        switch (i) {
            case 1011:
                bundle.setClassLoader(OpenChannelResult.class.getClassLoader());
                OpenChannelResult openChannelResult = (OpenChannelResult) bundle.get("result");
                String channel = openChannelResult.getChannel();
                String outHexApdu = openChannelResult.getOutHexApdu();
                Bundle bundle2 = new Bundle();
                bundle2.putString(Constant.KEY_CHANNEL, channel);
                bundle2.putString("apdu", outHexApdu);
                Handler handler2 = this.b;
                handler2.sendMessage(Message.obtain(handler2, 1011, bundle2));
                return;
            case 1012:
                bundle.setClassLoader(SendApduResult.class.getClassLoader());
                String outHexApdu2 = ((SendApduResult) bundle.get("result")).getOutHexApdu();
                Handler handler3 = this.b;
                handler3.sendMessage(Message.obtain(handler3, 1012, outHexApdu2));
                return;
            case 1013:
                Handler handler4 = this.b;
                handler4.sendMessage(Message.obtain(handler4, 1013, ""));
                return;
            case 1014:
                bundle.setClassLoader(GetSeAppListResult.class.getClassLoader());
                SeAppListItem[] seAppList = ((GetSeAppListResult) bundle.get("result")).getSeAppList();
                Handler handler5 = this.b;
                handler5.sendMessage(Message.obtain(handler5, 1014, seAppList));
                return;
            case 1015:
                bundle.setClassLoader(GetCardInfoBySpayResult.class.getClassLoader());
                VirtualCardInfo virtualCardInfo = ((GetCardInfoBySpayResult) bundle.get("result")).getVirtualCardInfo();
                Handler handler6 = this.b;
                handler6.sendMessage(Message.obtain(handler6, 1015, virtualCardInfo));
                return;
            case 1016:
                j.c("uppay-spay", "check spay support callback");
                bundle.setClassLoader(CheckSSamsungPayResult.class.getClassLoader());
                bundle.get("result");
                f.f = true;
                f.e = true;
                Handler handler7 = this.b;
                handler7.sendMessage(Message.obtain(handler7, 1016, "1"));
                return;
            default:
                return;
        }
    }
}
