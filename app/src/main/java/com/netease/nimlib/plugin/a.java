package com.netease.nimlib.plugin;

import android.content.Context;
import com.netease.nimlib.i.j;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: IPlugin.java */
/* loaded from: classes.dex */
public interface a {
    MsgAttachment a(int i, JSONObject jSONObject);

    Map<Class<?>, Class<? extends j>> a();

    void a(Context context);

    Map<Class<? extends com.netease.nimlib.biz.e.a>, com.netease.nimlib.biz.c.a> b();

    void b(Context context);

    void c(Context context);

    void d(Context context);
}
