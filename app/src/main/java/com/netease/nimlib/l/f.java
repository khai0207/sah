package com.netease.nimlib.l;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import com.netease.nimlib.l.a.c;
import com.netease.nimlib.sdk.NimStrings;
import com.netease.nimlib.sdk.NotificationFoldStyle;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomizationCompat;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.List;
import java.util.Map;

/* compiled from: NotificationShower.java */
/* loaded from: classes.dex */
class f {
    private g a;
    private NotificationFoldStyle b;
    private NotificationManager d;
    private Bitmap f;
    private long e = 0;
    private Context c = com.netease.nimlib.c.e();

    f() {
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        if (statusBarNotificationConfig != null) {
            if (statusBarNotificationConfig.notificationFoldStyle == null) {
                statusBarNotificationConfig.notificationFoldStyle = NotificationFoldStyle.ALL;
            }
            this.b = statusBarNotificationConfig.notificationFoldStyle;
            a();
        }
        this.d = (NotificationManager) this.c.getSystemService(TransferService.INTENT_KEY_NOTIFICATION);
        e.e(this.c);
    }

    /* compiled from: NotificationShower.java */
    /* renamed from: com.netease.nimlib.l.f$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationFoldStyle.values().length];
            a = iArr;
            try {
                iArr[NotificationFoldStyle.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationFoldStyle.EXPAND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationFoldStyle.CONTACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    void a() {
        int i = AnonymousClass1.a[this.b.ordinal()];
        if (i == 1) {
            this.a = new c(this.c);
        } else if (i == 2) {
            this.a = new j(this.c);
        } else {
            if (i != 3) {
                return;
            }
            this.a = new b(this.c);
        }
    }

    void a(NotificationFoldStyle notificationFoldStyle) {
        if (this.b == null && notificationFoldStyle == null) {
            notificationFoldStyle = NotificationFoldStyle.ALL;
        } else {
            if (notificationFoldStyle == this.b) {
                return;
            }
            if (notificationFoldStyle == null) {
                notificationFoldStyle = NotificationFoldStyle.ALL;
            }
        }
        com.netease.nimlib.c.i().statusBarNotificationConfig.notificationFoldStyle = notificationFoldStyle;
        this.b = notificationFoldStyle;
        b();
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0110 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0117 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void a(com.netease.nimlib.sdk.msg.model.IMMessage r18, java.util.Map<java.lang.String, com.netease.nimlib.sdk.msg.model.IMMessage> r19, java.lang.String r20, int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.l.f.a(com.netease.nimlib.sdk.msg.model.IMMessage, java.util.Map, java.lang.String, int, boolean):void");
    }

    void b() {
        g gVar = this.a;
        if (gVar != null) {
            gVar.a(this.d, new i(0));
        }
    }

    void a(List<SessionAckInfo> list) {
        i iVar = new i(2);
        iVar.a(list);
        g gVar = this.a;
        if (gVar != null) {
            gVar.a(this.d, iVar);
        }
    }

    private PendingIntent a(Map<String, IMMessage> map) {
        return this.a.a(map);
    }

    private NimStrings c() {
        return com.netease.nimlib.c.B();
    }

    private CharSequence a(IMMessage iMMessage, String str, String str2) {
        CharSequence makeTicker;
        MessageNotifierCustomization messageNotifierCustomization = com.netease.nimlib.c.i().messageNotifierCustomization;
        if (messageNotifierCustomization != null) {
            if (messageNotifierCustomization instanceof MessageNotifierCustomizationCompat) {
                makeTicker = ((MessageNotifierCustomizationCompat) messageNotifierCustomization).makeTickerChars(str, iMMessage);
            } else {
                makeTicker = messageNotifierCustomization.makeTicker(str, iMMessage);
            }
            if (!TextUtils.isEmpty(makeTicker)) {
                return makeTicker;
            }
        }
        return String.format(c().status_bar_ticker_text, str2);
    }

    private String a(IMMessage iMMessage, String str) {
        UserInfoProvider userInfoProvider = com.netease.nimlib.c.i().userInfoProvider;
        if (userInfoProvider != null) {
            String displayTitleForMessageNotifier = userInfoProvider.getDisplayTitleForMessageNotifier(iMMessage);
            if (!TextUtils.isEmpty(displayTitleForMessageNotifier)) {
                return displayTitleForMessageNotifier;
            }
        }
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        if (sessionType == SessionTypeEnum.P2P) {
            return str;
        }
        if (sessionType == SessionTypeEnum.Team || sessionType == SessionTypeEnum.SUPER_TEAM) {
            String sessionId = iMMessage.getSessionId();
            String queryTeamName = sessionType == SessionTypeEnum.Team ? TeamDBHelper.queryTeamName(sessionId) : SuperTeamDBHelper.queryTeamName(sessionId);
            if (TextUtils.isEmpty(queryTeamName)) {
                queryTeamName = a(queryTeamName);
            }
            return queryTeamName == null ? sessionId : queryTeamName;
        }
        Context context = this.c;
        return context.getString(context.getApplicationInfo().labelRes);
    }

    private String a(String str) {
        StatusBarNotificationConfig statusBarNotificationConfig = com.netease.nimlib.c.i().statusBarNotificationConfig;
        return (statusBarNotificationConfig == null || TextUtils.isEmpty(statusBarNotificationConfig.customTitleWhenTeamNameEmpty)) ? str : statusBarNotificationConfig.customTitleWhenTeamNameEmpty;
    }

    private Bitmap a(IMMessage iMMessage, Map<String, IMMessage> map) {
        UserInfoProvider userInfoProvider = com.netease.nimlib.c.i().userInfoProvider;
        if (userInfoProvider == null) {
            return null;
        }
        if ((this.a instanceof c) && map.size() > 1) {
            return null;
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.P2P) {
            return userInfoProvider.getAvatarForMessageNotifier(SessionTypeEnum.P2P, iMMessage.getFromAccount());
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.Team) {
            return userInfoProvider.getAvatarForMessageNotifier(SessionTypeEnum.Team, iMMessage.getSessionId());
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
            return userInfoProvider.getAvatarForMessageNotifier(SessionTypeEnum.SUPER_TEAM, iMMessage.getSessionId());
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.Ysf) {
            return com.netease.nimlib.c.F().getAvatarForMessageNotifier(SessionTypeEnum.Ysf, iMMessage.getFromAccount());
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.QChat) {
            return userInfoProvider.getAvatarForMessageNotifier(SessionTypeEnum.QChat, iMMessage.getFromAccount());
        }
        return null;
    }

    private void a(boolean z, boolean z2, StatusBarNotificationConfig statusBarNotificationConfig, c.d dVar) {
        boolean a = a(statusBarNotificationConfig);
        int i = a ? 0 : 4;
        if (z2) {
            i |= 2;
        }
        if (z) {
            if (statusBarNotificationConfig.notificationSound != null) {
                dVar.a(Uri.parse(statusBarNotificationConfig.notificationSound));
            } else {
                i |= 1;
            }
        }
        dVar.b(i);
        if (a) {
            a(dVar, statusBarNotificationConfig);
        }
    }

    private boolean d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.e < 1500) {
            return true;
        }
        this.e = elapsedRealtime;
        return false;
    }

    private boolean a(StatusBarNotificationConfig statusBarNotificationConfig) {
        return statusBarNotificationConfig.ledARGB != -1 && statusBarNotificationConfig.ledOnMs > 0 && statusBarNotificationConfig.ledOffMs > 0;
    }

    private void a(c.d dVar, StatusBarNotificationConfig statusBarNotificationConfig) {
        dVar.a(statusBarNotificationConfig.ledARGB, statusBarNotificationConfig.ledOnMs, statusBarNotificationConfig.ledOffMs);
    }

    private int b(StatusBarNotificationConfig statusBarNotificationConfig) {
        if (statusBarNotificationConfig.notificationSmallIconId == 0) {
            return this.c.getApplicationInfo().icon;
        }
        return statusBarNotificationConfig.notificationSmallIconId;
    }

    private Bitmap a(StatusBarNotificationConfig statusBarNotificationConfig, IMMessage iMMessage, Map<String, IMMessage> map) {
        Bitmap a = !statusBarNotificationConfig.hideContent ? a(iMMessage, map) : null;
        if (a != null) {
            return a;
        }
        Bitmap bitmap = this.f;
        if (bitmap != null) {
            return bitmap;
        }
        Drawable loadIcon = this.c.getApplicationInfo().loadIcon(this.c.getPackageManager());
        if (loadIcon == null || !(loadIcon instanceof BitmapDrawable)) {
            return null;
        }
        Bitmap bitmap2 = ((BitmapDrawable) loadIcon).getBitmap();
        this.f = bitmap2;
        return bitmap2;
    }

    private String b(IMMessage iMMessage, String str) {
        com.netease.nimlib.superteam.c queryTeamMember;
        com.netease.nimlib.team.g queryTeamMember2;
        UserInfoProvider userInfoProvider = com.netease.nimlib.c.i().userInfoProvider;
        if (userInfoProvider != null) {
            String displayNameForMessageNotifier = userInfoProvider.getDisplayNameForMessageNotifier(iMMessage.getFromAccount(), iMMessage.getSessionId(), iMMessage.getSessionType());
            if (!TextUtils.isEmpty(displayNameForMessageNotifier)) {
                return displayNameForMessageNotifier;
            }
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.Team && (queryTeamMember2 = TeamDBHelper.queryTeamMember(iMMessage.getSessionId(), iMMessage.getFromAccount())) != null && !TextUtils.isEmpty(queryTeamMember2.getTeamNick())) {
            return queryTeamMember2.getTeamNick();
        }
        if (iMMessage.getSessionType() != SessionTypeEnum.SUPER_TEAM || (queryTeamMember = SuperTeamDBHelper.queryTeamMember(iMMessage.getSessionId(), iMMessage.getFromAccount())) == null || TextUtils.isEmpty(queryTeamMember.getTeamNick())) {
            return !TextUtils.isEmpty(str) ? str : iMMessage.getFromAccount();
        }
        return queryTeamMember.getTeamNick();
    }
}
