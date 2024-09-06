package com.netease.nimlib.l;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.msg.constant.NotificationExtraTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: UnfoldedNotification.java */
/* loaded from: classes.dex */
public class j extends g {
    private final Map<Long, String> b;

    @Override // com.netease.nimlib.l.g
    public void a(Notification notification, int i) {
    }

    public j(Context context) {
        super(context);
        this.b = new HashMap();
    }

    @Override // com.netease.nimlib.l.g
    public CharSequence a(IMMessage iMMessage, String str, Map<String, IMMessage> map, boolean z) {
        if (z) {
            return a().status_bar_hidden_message_content;
        }
        return a(iMMessage, str);
    }

    @Override // com.netease.nimlib.l.g
    public String a(IMMessage iMMessage, int i, Map<String, IMMessage> map, String str, boolean z) {
        return !z ? str : b();
    }

    @Override // com.netease.nimlib.l.g
    public int a(IMMessage iMMessage) {
        if (iMMessage != null) {
            this.b.put(Long.valueOf(iMMessage.getServerId()), iMMessage.getUuid());
        }
        return c(iMMessage);
    }

    private int c(IMMessage iMMessage) {
        if (iMMessage != null) {
            return a(iMMessage.getServerId());
        }
        return 0;
    }

    private int a(long j) {
        return Math.abs((int) j);
    }

    @Override // com.netease.nimlib.l.g
    public PendingIntent a(Map<String, IMMessage> map) {
        IMMessageImpl iMMessageImpl;
        Intent intent = new Intent();
        intent.setComponent(d.b());
        if (map == null || map.size() <= 0) {
            iMMessageImpl = null;
        } else {
            iMMessageImpl = (IMMessageImpl) map.values().toArray()[0];
            ArrayList arrayList = new ArrayList();
            arrayList.add(iMMessageImpl);
            StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
            if (AnonymousClass1.a[((statusBarNotificationConfig == null || statusBarNotificationConfig.notificationExtraType == null) ? NotificationExtraTypeEnum.MESSAGE : statusBarNotificationConfig.notificationExtraType).ordinal()] == 1) {
                intent.putExtra(NimIntent.EXTRA_NOTIFY_SESSION_CONTENT, a(arrayList));
            } else {
                intent.putExtra(NimIntent.EXTRA_NOTIFY_CONTENT, arrayList);
            }
        }
        intent.addFlags(603979776);
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 23) {
            com.netease.nimlib.log.b.d("UnfoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE");
            return PendingIntent.getActivity(this.a, c(iMMessageImpl), intent, 201326592);
        }
        com.netease.nimlib.log.b.d("UnfoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_UPDATE_CURRENT");
        return PendingIntent.getActivity(this.a, c(iMMessageImpl), intent, 134217728);
    }

    /* compiled from: UnfoldedNotification.java */
    /* renamed from: com.netease.nimlib.l.j$1, reason: invalid class name */
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
        Set<Long> keySet = this.b.keySet();
        if (com.netease.nimlib.o.f.c((Collection) keySet)) {
            com.netease.nimlib.log.b.d("UnfoldedNotification", "clearNotifications with no notification");
            return;
        }
        Iterator<Long> it = keySet.iterator();
        while (it.hasNext()) {
            notificationManager.cancel(h.MESSAGE.a(), a(it.next().longValue()));
            it.remove();
        }
        com.netease.nimlib.log.b.d("UnfoldedNotification", "clearNotifications");
    }

    @Override // com.netease.nimlib.l.g
    public void a(NotificationManager notificationManager, i iVar) {
        int a = iVar.a();
        if (a == 0) {
            a(notificationManager);
            return;
        }
        if (a != 2) {
            return;
        }
        if (com.netease.nimlib.o.f.c((Collection) this.b.keySet())) {
            com.netease.nimlib.log.b.d("UnfoldedNotification", "remove notification with no notification");
            return;
        }
        final List<SessionAckInfo> b = iVar.b();
        if (com.netease.nimlib.o.f.c((Collection) b)) {
            return;
        }
        Iterator it = com.netease.nimlib.o.f.d(MsgDBHelper.queryMsgListByUuid(com.netease.nimlib.o.f.b(this.b.values(), true, new f.a() { // from class: com.netease.nimlib.l.-$$Lambda$LkB-nF4dc2XGB2yHvsljXeoGsxk
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return String.valueOf((String) obj);
            }
        })), new f.a() { // from class: com.netease.nimlib.l.-$$Lambda$j$vCZoxslz6LXSDDXzV7zjWUfdVtA
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a2;
                a2 = j.a(b, (IMMessage) obj);
                return a2;
            }
        }).iterator();
        while (it.hasNext()) {
            long serverId = ((IMMessage) it.next()).getServerId();
            notificationManager.cancel(h.MESSAGE.a(), a(serverId));
            this.b.remove(Long.valueOf(serverId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(List list, IMMessage iMMessage) {
        if (iMMessage == null) {
            return false;
        }
        final String sessionId = iMMessage.getSessionId();
        final SessionTypeEnum sessionType = iMMessage.getSessionType();
        if (TextUtils.isEmpty(sessionId) || sessionType == null) {
            return false;
        }
        final long time = iMMessage.getTime();
        return Boolean.valueOf(com.netease.nimlib.o.f.b(list, new f.a() { // from class: com.netease.nimlib.l.-$$Lambda$j$kTV69BdIk-cZzXYMjAKigtPfT_0
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a;
                a = j.a(sessionId, sessionType, time, (SessionAckInfo) obj);
                return a;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(String str, SessionTypeEnum sessionTypeEnum, long j, SessionAckInfo sessionAckInfo) {
        return Boolean.valueOf(sessionAckInfo != null && str.equals(sessionAckInfo.getSessionId()) && sessionTypeEnum == sessionAckInfo.getSessionType() && j <= sessionAckInfo.getTime());
    }
}
