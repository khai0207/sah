package com.ipaynow.wechatpay.plugin.api;

import android.content.Intent;
import android.os.Bundle;
import com.ipaynow.wechatpay.plugin.inner_plugin.wechat_plugin.activity.WeChatNotifyActivity;
import com.ipaynow.wechatpay.plugin.utils.e;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a extends com.ipaynow.wechatpay.plugin.b.a {
    public final void a() {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        try {
            r.a(true);
            r.d(this.b.mhtOrderNo);
            JSONObject jSONObject = new JSONObject();
            e.a(this.b, jSONObject);
            String jSONObject2 = jSONObject.toString();
            com.ipaynow.wechatpay.plugin.manager.a.a r2 = com.ipaynow.wechatpay.plugin.manager.a.a.r();
            Intent intent = new Intent(r2.getContext(), (Class<?>) WeChatNotifyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("requestParams", jSONObject2);
            bundle.putBoolean("PARAM_START_PLUGIN_INTERNAL_ACTIVITIE", true);
            intent.putExtras(bundle);
            if (r2.A() != null) {
                r2.A().startActivityForResult(intent, 0);
            } else {
                r2.getContext().startActivity(intent);
            }
            com.ipaynow.wechatpay.plugin.manager.a.a.r().b(false);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread();
            com.ipaynow.wechatpay.plugin.d.b.a.a(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0179 A[Catch: Exception -> 0x02ca, TryCatch #0 {Exception -> 0x02ca, blocks: (B:3:0x000b, B:5:0x0012, B:10:0x0039, B:12:0x003f, B:14:0x004f, B:16:0x0070, B:18:0x0079, B:20:0x0083, B:21:0x008d, B:24:0x0098, B:26:0x009e, B:28:0x00ba, B:30:0x00be, B:32:0x00cb, B:34:0x00e9, B:36:0x00ed, B:37:0x00f1, B:39:0x00f5, B:41:0x0113, B:43:0x0119, B:47:0x0179, B:49:0x0125, B:52:0x012e, B:55:0x0137, B:58:0x0140, B:61:0x0149, B:64:0x0152, B:67:0x015b, B:70:0x0164, B:73:0x016d, B:77:0x0195, B:79:0x025d, B:81:0x0266, B:83:0x0270, B:85:0x028a, B:87:0x0293, B:89:0x02a5, B:91:0x02c6, B:103:0x0258, B:95:0x019b, B:97:0x01c1, B:99:0x01c7, B:100:0x01e2, B:102:0x01db), top: B:2:0x000b, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.content.Context r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 735
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ipaynow.wechatpay.plugin.api.a.a(android.content.Context, java.lang.Object):boolean");
    }
}
