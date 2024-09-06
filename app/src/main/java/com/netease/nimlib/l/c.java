package com.netease.nimlib.l;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.msg.constant.NotificationExtraTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.session.w;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: FoldedNotification.java */
/* loaded from: classes.dex */
public class c extends g {
    private static SessionAckInfo b;
    private Boolean c;

    public c(Context context) {
        super(context);
    }

    @Override // com.netease.nimlib.l.g
    public CharSequence a(IMMessage iMMessage, String str, Map<String, IMMessage> map, boolean z) {
        if (map == null) {
            return "";
        }
        if (map.size() != 1) {
            return String.format(a().status_bar_multi_messages_incoming, Integer.valueOf(map.size()));
        }
        if (z) {
            return a().status_bar_hidden_message_content;
        }
        return a(iMMessage, str);
    }

    @Override // com.netease.nimlib.l.g
    public String a(IMMessage iMMessage, int i, Map<String, IMMessage> map, String str, boolean z) {
        if (map != null && map.size() == 1 && !z) {
            int min = Math.min(i, 99);
            if (min <= 1) {
                return str;
            }
            return ((str + "(") + min) + ")";
        }
        return b();
    }

    @Override // com.netease.nimlib.l.g
    public int a(IMMessage iMMessage) {
        b = new w(iMMessage.getSessionType(), iMMessage.getSessionId(), iMMessage.getTime());
        return 0;
    }

    @Override // com.netease.nimlib.l.g
    public PendingIntent a(Map<String, IMMessage> map) {
        Intent intent = new Intent();
        intent.setComponent(d.b());
        ArrayList arrayList = new ArrayList(map.values());
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        if (AnonymousClass1.a[((statusBarNotificationConfig == null || statusBarNotificationConfig.notificationExtraType == null) ? NotificationExtraTypeEnum.MESSAGE : statusBarNotificationConfig.notificationExtraType).ordinal()] == 1) {
            intent.putExtra(NimIntent.EXTRA_NOTIFY_SESSION_CONTENT, a(arrayList));
        } else {
            intent.putExtra(NimIntent.EXTRA_NOTIFY_CONTENT, arrayList);
        }
        intent.addFlags(603979776);
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 23) {
            com.netease.nimlib.log.b.d("FoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE");
            return PendingIntent.getActivity(this.a, h.MESSAGE.c(), intent, 335544320);
        }
        com.netease.nimlib.log.b.d("FoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_CANCEL_CURRENT");
        return PendingIntent.getActivity(this.a, h.MESSAGE.c(), intent, 268435456);
    }

    /* compiled from: FoldedNotification.java */
    /* renamed from: com.netease.nimlib.l.c$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationExtraTypeEnum.values().length];
            a = iArr;
            try {
                iArr[NotificationExtraTypeEnum.JSON_ARR_STR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationExtraTypeEnum.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void a(NotificationManager notificationManager) {
        notificationManager.cancel(h.MESSAGE.a(), 0);
        b = null;
    }

    @Override // com.netease.nimlib.l.g
    public void a(NotificationManager notificationManager, i iVar) {
        if (iVar.a() != 0) {
            return;
        }
        a(notificationManager);
    }

    @Override // com.netease.nimlib.l.g
    public void a(Notification notification, int i) {
        Boolean bool = this.c;
        if (bool == null || bool.booleanValue()) {
            try {
                Object obj = notification.getClass().getDeclaredField("extraNotification").get(notification);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i));
                this.c = true;
            } catch (Exception unused) {
                this.c = false;
            }
        }
    }
}
