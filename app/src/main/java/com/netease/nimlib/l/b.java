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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: ContactFoldedNotification.java */
/* loaded from: classes.dex */
public class b extends g {
    private final HashMap<String, long[]> b;

    @Override // com.netease.nimlib.l.g
    public void a(Notification notification, int i) {
    }

    public b(Context context) {
        super(context);
        this.b = new HashMap<>();
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
        if (iMMessage == null || z) {
            return b();
        }
        long[] jArr = this.b.get(a(iMMessage.getSessionId(), iMMessage.getSessionType()));
        return (jArr == null || jArr[1] < 1) ? str : String.format(Locale.getDefault(), "%s(%d)", str, Long.valueOf(jArr[1] + 1));
    }

    @Override // com.netease.nimlib.l.g
    public int a(IMMessage iMMessage) {
        String a = a(iMMessage.getSessionId(), iMMessage.getSessionType());
        long time = iMMessage.getTime();
        long[] jArr = this.b.get(a);
        if (jArr != null) {
            jArr[1] = jArr[1] + 1;
            jArr[2] = time;
            return (int) jArr[0];
        }
        int size = this.b.size();
        this.b.put(a, new long[]{size, 1, time});
        return size;
    }

    private int a(IMMessageImpl iMMessageImpl) {
        if (iMMessageImpl != null) {
            return Math.abs((int) iMMessageImpl.getServerId());
        }
        return 0;
    }

    private String a(String str, SessionTypeEnum sessionTypeEnum) {
        return String.format("%s|%s", str, sessionTypeEnum);
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
            com.netease.nimlib.log.b.d("ContactFoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE");
            return PendingIntent.getActivity(this.a, a(iMMessageImpl), intent, 201326592);
        }
        com.netease.nimlib.log.b.d("ContactFoldedNotification", "PendingIntent.getActivity Flag = PendingIntent.FLAG_UPDATE_CURRENT");
        return PendingIntent.getActivity(this.a, a(iMMessageImpl), intent, 134217728);
    }

    /* compiled from: ContactFoldedNotification.java */
    /* renamed from: com.netease.nimlib.l.b$1, reason: invalid class name */
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
        Iterator<long[]> it = this.b.values().iterator();
        while (it.hasNext()) {
            notificationManager.cancel(h.MESSAGE.a(), (int) it.next()[0]);
            it.remove();
        }
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
        HashMap<String, long[]> hashMap = this.b;
        if (hashMap == null || hashMap.isEmpty()) {
            com.netease.nimlib.log.b.d("ContactFoldedNotification", "remove notification with no notification");
            return;
        }
        List<SessionAckInfo> b = iVar.b();
        if (com.netease.nimlib.o.f.c((Collection) b)) {
            return;
        }
        for (SessionAckInfo sessionAckInfo : com.netease.nimlib.o.f.d(b, new f.a() { // from class: com.netease.nimlib.l.-$$Lambda$b$7qfCbtOl_hb83c6Rxm1MFRodSes
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean a2;
                a2 = b.this.a((SessionAckInfo) obj);
                return a2;
            }
        })) {
            String a2 = a(sessionAckInfo.getSessionId(), sessionAckInfo.getSessionType());
            long[] jArr = this.b.get(a2);
            if (jArr != null && jArr.length != 0) {
                notificationManager.cancel(h.MESSAGE.a(), (int) jArr[0]);
                this.b.remove(a2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean a(SessionAckInfo sessionAckInfo) {
        if (sessionAckInfo == null) {
            return false;
        }
        String sessionId = sessionAckInfo.getSessionId();
        SessionTypeEnum sessionType = sessionAckInfo.getSessionType();
        if (!TextUtils.isEmpty(sessionId) && sessionType != null) {
            long time = sessionAckInfo.getTime();
            long[] jArr = this.b.get(a(sessionId, sessionType));
            if (jArr != null && jArr.length >= 3) {
                return Boolean.valueOf(jArr[2] <= time);
            }
        }
        return false;
    }
}
