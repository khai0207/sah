package com.netease.nimlib.l;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.netease.nimlib.sdk.NimStrings;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomizationCompat;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NotificationStyle.java */
/* loaded from: classes.dex */
public abstract class g {
    protected Context a;

    public abstract int a(IMMessage iMMessage);

    public abstract PendingIntent a(Map<String, IMMessage> map);

    public abstract CharSequence a(IMMessage iMMessage, String str, Map<String, IMMessage> map, boolean z);

    public abstract String a(IMMessage iMMessage, int i, Map<String, IMMessage> map, String str, boolean z);

    public abstract void a(Notification notification, int i);

    public abstract void a(NotificationManager notificationManager, i iVar);

    public g(Context context) {
        this.a = context;
    }

    protected CharSequence a(IMMessage iMMessage, String str) {
        CharSequence makeNotifyContent;
        SessionTypeEnum sessionType = iMMessage.getSessionType();
        if (!TextUtils.isEmpty(iMMessage.getPushContent())) {
            CustomMessageConfig config = iMMessage.getConfig();
            if ((sessionType == SessionTypeEnum.Team || sessionType == SessionTypeEnum.SUPER_TEAM || sessionType == SessionTypeEnum.QChat) && (config == null || config.enablePushNick)) {
                return str + ": " + iMMessage.getPushContent();
            }
            return iMMessage.getPushContent();
        }
        MessageNotifierCustomization messageNotifierCustomization = com.netease.nimlib.c.i().messageNotifierCustomization;
        if (messageNotifierCustomization != null) {
            if (messageNotifierCustomization instanceof MessageNotifierCustomizationCompat) {
                makeNotifyContent = ((MessageNotifierCustomizationCompat) messageNotifierCustomization).makeNotifyContentChars(str, iMMessage);
            } else {
                makeNotifyContent = messageNotifierCustomization.makeNotifyContent(str, iMMessage);
            }
            if (!TextUtils.isEmpty(makeNotifyContent)) {
                return makeNotifyContent;
            }
        }
        if (iMMessage.getMsgType() == MsgTypeEnum.text || !TextUtils.isEmpty(iMMessage.getContent())) {
            if (sessionType == SessionTypeEnum.Team || sessionType == SessionTypeEnum.SUPER_TEAM || sessionType == SessionTypeEnum.QChat) {
                return str + ": " + iMMessage.getContent();
            }
            return iMMessage.getContent();
        }
        switch (AnonymousClass1.a[iMMessage.getMsgType().ordinal()]) {
            case 1:
                return String.format(a().status_bar_image_message, str);
            case 2:
                return String.format(a().status_bar_audio_message, str);
            case 3:
                return String.format(a().status_bar_video_message, str);
            case 4:
                return String.format(a().status_bar_file_message, str);
            case 5:
                return String.format(a().status_bar_location_message, str);
            case 6:
                return String.format(a().status_bar_notification_message, str);
            case 7:
                return String.format(a().status_bar_custom_message, str);
            case 8:
                return String.format(a().status_bar_avchat_message, str);
            case 9:
                return String.format(a().status_bar_tip_message, str);
            case 10:
                return MsgTypeEnum.nrtc_netcall.getSendMessageTip();
            default:
                return String.format(a().status_bar_unsupported_message, str);
        }
    }

    /* compiled from: NotificationStyle.java */
    /* renamed from: com.netease.nimlib.l.g$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[MsgTypeEnum.values().length];
            a = iArr;
            try {
                iArr[MsgTypeEnum.image.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[MsgTypeEnum.audio.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[MsgTypeEnum.video.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[MsgTypeEnum.file.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[MsgTypeEnum.location.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[MsgTypeEnum.notification.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[MsgTypeEnum.custom.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[MsgTypeEnum.avchat.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[MsgTypeEnum.tip.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[MsgTypeEnum.nrtc_netcall.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    protected NimStrings a() {
        return com.netease.nimlib.c.B();
    }

    protected String b() {
        if (this.a.getApplicationInfo().labelRes == 0) {
            PackageManager packageManager = this.a.getPackageManager();
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = packageManager.getApplicationInfo(this.a.getApplicationInfo().packageName, 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
        }
        Context context = this.a;
        return context.getString(context.getApplicationInfo().labelRes);
    }

    protected String a(List<IMMessage> list) {
        JSONArray jSONArray = new JSONArray();
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return jSONArray.toString();
        }
        for (IMMessage iMMessage : list) {
            if (iMMessage != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("uuid", iMMessage.getUuid());
                    jSONObject.putOpt("sessionId", iMMessage.getSessionId());
                    jSONObject.putOpt("sessionType", Integer.valueOf(iMMessage.getSessionType().getValue()));
                    jSONObject.putOpt("time", Long.valueOf(iMMessage.getTime()));
                    jSONArray.put(jSONObject);
                } catch (Throwable th) {
                    com.netease.nimlib.log.b.e("NotificationStyle", "getSessionExtra error", th);
                }
            }
        }
        return jSONArray.toString();
    }

    protected String b(IMMessage iMMessage) {
        MessageNotifierCustomization messageNotifierCustomization = com.netease.nimlib.c.i().messageNotifierCustomization;
        if (messageNotifierCustomization != null) {
            return messageNotifierCustomization.makeCategory(iMMessage);
        }
        return null;
    }
}
