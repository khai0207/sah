package com.netease.nimlib.l;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import com.netease.nimlib.o.s;
import com.netease.nimlib.o.z;
import com.netease.nimlib.plugin.interact.IMixPushInteract;
import com.netease.nimlib.sdk.NimPermissionRequester;
import com.netease.nimlib.sdk.NimPermissionResultCallback;
import com.netease.nimlib.sdk.NotificationFoldStyle;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.StatusBarNotificationFilter;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.session.IMMessageImpl;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MessageNotifier.java */
/* loaded from: classes.dex */
public class d {
    private static d h;
    private ComponentName b;
    private int c;
    private f i;
    protected Context a = com.netease.nimlib.c.e();
    private Map<String, IMMessage> d = new LinkedHashMap();
    private int e = -1;
    private int f = -1;
    private Calendar g = Calendar.getInstance();

    private d() {
        l();
        k();
        this.i = new f();
    }

    private static d c() {
        if (h == null) {
            h = new d();
        }
        return h;
    }

    private static boolean a(IMMessage iMMessage) {
        if (iMMessage.getDirect() == MsgDirectionEnum.Out) {
            com.netease.nimlib.log.b.k("MessageNotifier needPush message.getDirect() == MsgDirectionEnum.Out");
            return false;
        }
        if (iMMessage.getConfig() != null && !iMMessage.getConfig().enablePush) {
            com.netease.nimlib.log.b.k("MessageNotifier needPush getConfig().enablePush false");
            return false;
        }
        if (iMMessage.getMsgType() != MsgTypeEnum.notification) {
            return true;
        }
        com.netease.nimlib.log.b.k("MessageNotifier needPush type == MsgTypeEnum.notification");
        return false;
    }

    private static IMMessage b(List<? extends IMMessage> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (a(list.get(size))) {
                return list.get(size);
            }
        }
        return null;
    }

    public static void a(final List<? extends IMMessage> list, final String str, final int i) {
        if (!d()) {
            com.netease.nimlib.log.b.k("showIncomingMsgNotify checkConfig false");
            return;
        }
        if (Build.VERSION.SDK_INT >= 33 && !s.a(com.netease.nimlib.c.e(), "android.permission.POST_NOTIFICATIONS")) {
            com.netease.nimlib.log.b.k("showIncomingMsgNotify checkPermission POST_NOTIFICATIONS false");
            NimPermissionRequester nimPermissionRequester = com.netease.nimlib.c.i().statusBarNotificationConfig.postNotificationsRequester;
            if (nimPermissionRequester != null) {
                nimPermissionRequester.requestPermission("android.permission.POST_NOTIFICATIONS", new NimPermissionResultCallback() { // from class: com.netease.nimlib.l.d.1
                    @Override // com.netease.nimlib.sdk.NimPermissionResultCallback
                    public void onRequestPermissionsResult(String str2, boolean z) {
                        if ("android.permission.POST_NOTIFICATIONS".equals(str2) && z) {
                            d.c((List<? extends IMMessage>) list, str, i);
                        }
                    }
                });
                return;
            }
            return;
        }
        c(list, str, i);
    }

    /* compiled from: MessageNotifier.java */
    /* renamed from: com.netease.nimlib.l.d$4, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationFoldStyle.values().length];
            a = iArr;
            try {
                iArr[NotificationFoldStyle.EXPAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationFoldStyle.CONTACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationFoldStyle.ALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<? extends IMMessage> list, String str, int i) {
        NotificationFoldStyle notificationFoldStyle = com.netease.nimlib.c.i().statusBarNotificationConfig.notificationFoldStyle;
        int i2 = AnonymousClass4.a[notificationFoldStyle.ordinal()];
        if (i2 == 1 || i2 == 2) {
            com.netease.nimlib.team.i.a(list.size() > 5);
            for (int i3 = 0; i3 < list.size(); i3++) {
                IMMessage iMMessage = list.get(i3);
                if (a(iMMessage)) {
                    a(iMMessage, str, 1);
                } else {
                    com.netease.nimlib.log.b.k("showIncomingMsgNotify needPush false foldStyle %s needPush false");
                }
            }
            com.netease.nimlib.team.i.a();
            return;
        }
        com.netease.nimlib.team.i.a(false);
        IMMessage b = b(list);
        if (b != null) {
            a(b, str, i);
        } else {
            com.netease.nimlib.log.b.k(String.format("showIncomingMsgNotify pushMessage null foldStyle %s", notificationFoldStyle));
        }
    }

    private static void a(IMMessage iMMessage, String str, int i) {
        if (d()) {
            c().b(iMMessage, str, i);
        }
    }

    public static void a(RevokeMsgNotification revokeMsgNotification) {
        if (d() && com.netease.nimlib.c.w() && com.netease.nimlib.session.j.a(revokeMsgNotification)) {
            IMMessageImpl iMMessageImpl = (IMMessageImpl) revokeMsgNotification.getMessage();
            String fromNick = iMMessageImpl.getFromNick();
            IMMessageImpl iMMessageImpl2 = (IMMessageImpl) MessageBuilder.createTextMessage(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), "撤回了一条消息");
            iMMessageImpl2.setFromAccount(iMMessageImpl.getFromAccount());
            iMMessageImpl2.setServerId(iMMessageImpl.getServerId());
            MessageNotifierCustomization messageNotifierCustomization = com.netease.nimlib.c.i().messageNotifierCustomization;
            if (messageNotifierCustomization != null) {
                String makeRevokeMsgTip = messageNotifierCustomization.makeRevokeMsgTip(revokeMsgNotification.getRevokeAccount(), iMMessageImpl);
                if (!TextUtils.isEmpty(makeRevokeMsgTip)) {
                    iMMessageImpl2.setPushContent(makeRevokeMsgTip);
                    CustomMessageConfig customMessageConfig = new CustomMessageConfig();
                    customMessageConfig.enablePushNick = false;
                    iMMessageImpl2.setConfig(customMessageConfig);
                }
            }
            boolean z = com.netease.nimlib.c.i().statusBarNotificationConfig.ring;
            boolean z2 = com.netease.nimlib.c.i().statusBarNotificationConfig.vibrate;
            com.netease.nimlib.c.i().statusBarNotificationConfig.ring = false;
            com.netease.nimlib.c.i().statusBarNotificationConfig.vibrate = false;
            c().b(iMMessageImpl2, fromNick, com.netease.nimlib.c.i().shouldConsiderRevokedMessageUnreadCount ? -1 : 0);
            com.netease.nimlib.c.i().statusBarNotificationConfig.ring = z;
            com.netease.nimlib.c.i().statusBarNotificationConfig.vibrate = z2;
        }
    }

    public static void a(String str, String str2) {
        if (d()) {
            c().a(str, str2, true);
        }
    }

    public static void a() {
        if (d()) {
            c().h();
        }
    }

    public static void a(List<SessionAckInfo> list) {
        if (d()) {
            c().c(list);
        }
    }

    public static void a(NotificationFoldStyle notificationFoldStyle) {
        if (d()) {
            c().b(notificationFoldStyle);
        }
    }

    public static ComponentName b() {
        return c().b;
    }

    private static boolean d() {
        return com.netease.nimlib.c.i().statusBarNotificationConfig != null;
    }

    private boolean e() {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        return iMixPushInteract != null && iMixPushInteract.c();
    }

    private boolean f() {
        IMixPushInteract iMixPushInteract = (IMixPushInteract) com.netease.nimlib.plugin.interact.b.a().a(IMixPushInteract.class);
        return iMixPushInteract != null && iMixPushInteract.e();
    }

    private synchronized void b(final IMMessage iMMessage, final String str, final int i) {
        a(new Runnable() { // from class: com.netease.nimlib.l.-$$Lambda$d$JvBy8K8CLBMLg6X4so3AG-XMUKw
            @Override // java.lang.Runnable
            public final void run() {
                d.this.d(iMMessage, str, i);
            }
        }, "show");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d(IMMessage iMMessage, String str, int i) {
        boolean z;
        StatusBarNotificationFilter statusBarNotificationFilter;
        if (iMMessage.getSessionType() == SessionTypeEnum.QChat) {
            if (f()) {
                com.netease.nimlib.log.b.N("message has mixPushed, cancel notify");
                return;
            }
        } else if (e()) {
            com.netease.nimlib.log.b.N("message has mixPushed, cancel notify");
            return;
        }
        if (com.netease.nimlib.c.i().statusBarNotificationConfig != null && (statusBarNotificationFilter = com.netease.nimlib.c.i().statusBarNotificationConfig.notificationFilter) != null) {
            StatusBarNotificationFilter.FilterPolicy apply = statusBarNotificationFilter.apply(iMMessage);
            if (apply == StatusBarNotificationFilter.FilterPolicy.PERMIT) {
                com.netease.nimlib.log.b.k("MessageNotifier show as app PERMIT");
                a(iMMessage, str, i, j());
                return;
            } else if (apply == StatusBarNotificationFilter.FilterPolicy.DENY) {
                com.netease.nimlib.log.b.k("MessageNotifier skip as app DENY");
                return;
            }
        }
        boolean b = b(iMMessage);
        boolean v = com.netease.nimlib.c.v();
        boolean a = com.netease.nimlib.team.i.a(iMMessage);
        com.netease.nimlib.log.b.k(String.format("MessageNotifier show isForcePush %b isSbNotifyOn %b teamMute %b", Boolean.valueOf(b), Boolean.valueOf(v), Boolean.valueOf(a)));
        if (b) {
            z = true;
        } else {
            if (!v || a) {
                return;
            }
            String d = com.netease.nimlib.h.c() ^ true ? "" : com.netease.nimlib.h.d();
            boolean equals = d.equals(com.netease.nimlib.session.j.a(iMMessage.getSessionId(), iMMessage.getSessionType().getValue()));
            boolean equals2 = d.equals("all");
            boolean c = com.netease.nimlib.user.c.c(iMMessage.getSessionId());
            z = (!equals && !equals2) && c;
            com.netease.nimlib.log.b.k(String.format("MessageNotifier show equalPeer %b equalAll %b isNeedMessageNotify %b", Boolean.valueOf(equals), Boolean.valueOf(equals2), Boolean.valueOf(c)));
        }
        boolean z2 = !b && j();
        boolean g = g();
        if (z2 && !g) {
            z = false;
        }
        com.netease.nimlib.log.b.k(String.format("MessageNotifier show dontDisturb %b isDownTimeEnableNotification %b", Boolean.valueOf(z2), Boolean.valueOf(g)));
        if (z) {
            a(iMMessage, str, i, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(IMMessage iMMessage, String str, int i, boolean z) {
        Map map;
        this.c += i;
        String a = com.netease.nimlib.session.j.a(iMMessage.getSessionId(), iMMessage.getSessionType().getValue());
        this.d.put(a, iMMessage);
        this.c = Math.max(this.c, 0);
        int i2 = AnonymousClass4.a[com.netease.nimlib.c.i().statusBarNotificationConfig.notificationFoldStyle.ordinal()];
        if (i2 == 1 || i2 == 2) {
            Map linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(a, iMMessage);
            map = linkedHashMap;
        } else {
            map = this.d;
        }
        this.i.a(iMMessage, map, str, this.c, z);
    }

    private boolean g() {
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        if (statusBarNotificationConfig == null) {
            return true;
        }
        return statusBarNotificationConfig.downTimeEnableNotification;
    }

    private boolean b(IMMessage iMMessage) {
        try {
            if (iMMessage.getMemberPushOption() == null || !iMMessage.getMemberPushOption().isForcePush()) {
                return false;
            }
            if (iMMessage.getMemberPushOption().getForcePushList() != null && !iMMessage.getMemberPushOption().getForcePushList().isEmpty()) {
                if (!iMMessage.getMemberPushOption().getForcePushList().contains(com.netease.nimlib.c.n())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a(String str, String str2, boolean z) {
        if (z || this.e == -1) {
            try {
                String[] split = str.split(":");
                this.e = (Integer.parseInt(split[0]) * 100) + Integer.parseInt(split[1]);
                String[] split2 = str2.split(":");
                this.f = (Integer.parseInt(split2[0]) * 100) + Integer.parseInt(split2[1]);
            } catch (Exception unused) {
            }
        }
    }

    private synchronized void b(final NotificationFoldStyle notificationFoldStyle) {
        a(new Runnable() { // from class: com.netease.nimlib.l.-$$Lambda$d$j7uArN3faz5eB0dSeFbp_Lhq7zo
            @Override // java.lang.Runnable
            public final void run() {
                d.this.d(notificationFoldStyle);
            }
        }, "update");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d(NotificationFoldStyle notificationFoldStyle) {
        this.i.a(notificationFoldStyle);
    }

    private synchronized void h() {
        a(new Runnable() { // from class: com.netease.nimlib.l.-$$Lambda$d$s1Uq3O-pEtD6xm3dhkX3hacvbd4
            @Override // java.lang.Runnable
            public final void run() {
                d.this.n();
            }
        }, "clear");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void n() {
        this.i.b();
        this.c = 0;
        this.d.clear();
    }

    private synchronized void c(List<SessionAckInfo> list) {
        this.i.a(list);
        for (SessionAckInfo sessionAckInfo : list) {
            if (sessionAckInfo != null) {
                String sessionId = sessionAckInfo.getSessionId();
                SessionTypeEnum sessionType = sessionAckInfo.getSessionType();
                if (!TextUtils.isEmpty(sessionId) && sessionType != null) {
                    this.d.remove(com.netease.nimlib.session.j.a(sessionId, sessionType.getValue()));
                }
            }
        }
    }

    private void a(Runnable runnable, final String str) {
        z.b(runnable, 2147483647L, new z.a() { // from class: com.netease.nimlib.l.d.2
            @Override // com.netease.nimlib.o.z.a
            public void a(long j) {
                com.netease.nimlib.log.b.d("MessageNotifier", "" + str + "(cost=" + j + ")");
            }
        }).run();
    }

    private boolean j() {
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        if (statusBarNotificationConfig == null || !statusBarNotificationConfig.downTimeToggle) {
            return false;
        }
        a(statusBarNotificationConfig.downTimeBegin, statusBarNotificationConfig.downTimeEnd, false);
        if (this.e == 0 && this.f == 0) {
            return false;
        }
        this.g.setTimeInMillis(System.currentTimeMillis());
        int i = (this.g.get(11) * 100) + this.g.get(12);
        int i2 = this.e;
        int i3 = this.f;
        return i2 <= i3 ? i >= i2 && i <= i3 : i >= i2 || i <= i3;
    }

    private void k() {
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        if (statusBarNotificationConfig == null) {
            return;
        }
        Class<? extends Activity> cls = statusBarNotificationConfig.notificationEntrance;
        if (cls == null) {
            Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(this.a.getPackageName());
            this.b = launchIntentForPackage != null ? launchIntentForPackage.getComponent() : null;
        }
        if (this.b == null) {
            this.b = new ComponentName(this.a, cls);
        }
    }

    private void l() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.netease.nimlib.l.d.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                    d.this.m();
                    return;
                }
                if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                    if (((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                        return;
                    }
                    d.this.m();
                } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                    com.netease.nimlib.h.c(false);
                }
            }
        };
        com.netease.nimlib.h.c(true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        try {
            com.netease.nimlib.c.e().registerReceiver(broadcastReceiver, intentFilter);
            com.netease.nimlib.log.b.d("MessageNotifier", "registerReceiver");
        } catch (Throwable th) {
            com.netease.nimlib.log.b.c("MessageNotifier", "registerReceiver error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        com.netease.nimlib.h.c(true);
        String d = com.netease.nimlib.h.d();
        if (TextUtils.isEmpty(d) || !this.d.containsKey(d)) {
            return;
        }
        a();
    }
}
