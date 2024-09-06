package com.netease.nimlib.apm.event.db;

import android.content.Context;
import com.netease.nimlib.database.plain.c;

/* compiled from: EventDatabase.java */
/* loaded from: classes.dex */
public class a extends com.netease.nimlib.database.a.b {
    public a(Context context, String str, boolean z) throws InstantiationException, IllegalAccessException {
        super((com.netease.nimlib.database.a.a) (z ? com.netease.nimlib.database.encrypt.b.class : c.class).newInstance());
        a(context, "event.db", str, b.a(), 2);
    }
}
