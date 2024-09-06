package com.unionpay.mobile.android.pboctransaction.samsung;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.SeAppListItem;
import com.unionpay.tsmservice.data.VirtualCardInfo;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class g implements Handler.Callback {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        int i;
        Handler handler4;
        Object obj;
        Handler handler5;
        Handler handler6;
        int i2 = message.what;
        if (i2 == 1) {
            j.c("uppay", "msg error");
            f.a(this.a, message.arg1, (String) message.obj);
        } else if (i2 == 3) {
            j.c("uppay-spay", "send apdu time out");
            f.e(this.a);
        } else if (i2 != 1000) {
            switch (i2) {
                case 1011:
                    j.c("uppay", "channel success");
                    Bundle bundle = (Bundle) message.obj;
                    this.a.q = bundle.getString(Constant.KEY_CHANNEL);
                    this.a.r = bundle.getString("apdu");
                    f.c(this.a);
                    break;
                case 1012:
                    j.c("uppay", "apdu success version 3.3.1");
                    handler = this.a.A;
                    handler.removeMessages(3);
                    this.a.t = (String) message.obj;
                    break;
                case 1013:
                    j.c("uppay", "close channel success");
                    f.d(this.a);
                    break;
                case 1014:
                    j.c("uppay", "list success");
                    handler2 = this.a.m;
                    if (handler2 != null) {
                        ArrayList arrayList = new ArrayList();
                        SeAppListItem[] seAppListItemArr = (SeAppListItem[]) message.obj;
                        if (seAppListItemArr != null && seAppListItemArr.length > 0) {
                            arrayList = new ArrayList();
                            for (int i3 = 0; i3 < seAppListItemArr.length; i3++) {
                                if (!f.d(seAppListItemArr[i3].getAppDetail().getAppID().getAppAid())) {
                                    arrayList.add(new com.unionpay.mobile.android.model.a(1, seAppListItemArr[i3].getAppDetail().getAppID().getAppAid(), "", seAppListItemArr[i3].getVirtualCardInfo().getCardNo(), 1));
                                }
                            }
                        }
                        handler3 = this.a.A;
                        i = 8;
                        obj = arrayList;
                        handler4 = handler3;
                        Message obtainMessage = handler4.obtainMessage(i, obj);
                        handler5 = this.a.m;
                        handler5.sendMessage(obtainMessage);
                        break;
                    }
                    break;
                case 1015:
                    j.c("uppay-spay", "get spay list call back");
                    VirtualCardInfo virtualCardInfo = (VirtualCardInfo) message.obj;
                    Object aVar = new com.unionpay.mobile.android.model.a(32, virtualCardInfo.getAppID().getAppAid(), "", virtualCardInfo.getCardNo(), 1);
                    handler6 = this.a.m;
                    i = 2000;
                    obj = aVar;
                    handler4 = handler6;
                    Message obtainMessage2 = handler4.obtainMessage(i, obj);
                    handler5 = this.a.m;
                    handler5.sendMessage(obtainMessage2);
                    break;
                case 1016:
                    j.c("uppay-spay", "check spay support");
                    f.f = true;
                    f.e = true;
                    break;
            }
        } else {
            j.c("uppay", "init success");
            this.a.a(true);
        }
        return false;
    }
}
