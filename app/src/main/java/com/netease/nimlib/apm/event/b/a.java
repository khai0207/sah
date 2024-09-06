package com.netease.nimlib.apm.event.b;

import android.content.Context;
import com.netease.nimlib.apm.event.db.EventDBHelper;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import java.util.List;

/* compiled from: EventManager.java */
/* loaded from: classes.dex */
public class a {
    public static boolean a(Context context) {
        return EventDBHelper.getInstance().open(context);
    }

    public static void a(com.netease.nimlib.apm.event.c.a aVar) {
        EventDBHelper.getInstance().saveEvent(aVar);
    }

    public static void a() {
        EventDBHelper.getInstance().clearEvent();
    }

    public static List<com.netease.nimlib.apm.event.c.a> b() {
        return EventDBHelper.getInstance().getAllEventOrderByTime(SearchOrderEnum.ASC);
    }

    public static List<com.netease.nimlib.apm.event.c.a> a(int i) {
        return EventDBHelper.getInstance().getRecentEvent(i);
    }

    public static void a(List<com.netease.nimlib.apm.event.c.a> list) {
        EventDBHelper.getInstance().deleteEvent(list);
    }

    public static void a(long j) {
        EventDBHelper.getInstance().deleteExpiredEvent(j);
    }

    public static int c() {
        return EventDBHelper.getInstance().getEventNum();
    }
}
