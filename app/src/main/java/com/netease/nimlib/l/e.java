package com.netease.nimlib.l;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import com.netease.nimlib.o.aa;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.msg.NotificationChannelProvider;

/* compiled from: NotificationChannelCompat.java */
/* loaded from: classes.dex */
public class e {
    private static String a = "Instant messages channel";
    private static String b = "Instant messages notification";
    private static String c = "No disturbing instant messages channel";
    private static String d = "No disturbing instant messages notification";
    private static String e = "Just ring  channel";
    private static String f = "Just ring instant messages notification";
    private static String g = "Just vibrate  channel";
    private static String h = "Just vibrate instant messages notification";

    public static String a(Context context) {
        if (aa.b(context)) {
            return "nim_message_channel_001";
        }
        return null;
    }

    public static String b(Context context) {
        if (aa.b(context)) {
            return "nim_message_channel_003";
        }
        return null;
    }

    public static String c(Context context) {
        if (aa.b(context)) {
            return "nim_message_channel_004";
        }
        return null;
    }

    public static String d(Context context) {
        if (aa.b(context)) {
            return "nim_message_channel_002";
        }
        return null;
    }

    public static String a(Context context, boolean z, boolean z2, boolean z3, boolean z4) {
        NotificationChannelProvider notificationChannelProvider;
        if (!aa.b(context) || (notificationChannelProvider = com.netease.nimlib.c.i().notificationChannelProvider) == null) {
            return null;
        }
        com.netease.nimlib.log.b.N("before get custom channel id");
        String channelId = notificationChannelProvider.getChannelId(z, z2, z3, z4);
        com.netease.nimlib.log.b.N("get custom channel id: " + channelId);
        return w.a(channelId);
    }

    public static void e(Context context) {
        a(context, com.netease.nimlib.c.i().statusBarNotificationConfig);
    }

    public static void a(Context context, StatusBarNotificationConfig statusBarNotificationConfig) {
        if (aa.b(context)) {
            f(context);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(TransferService.INTENT_KEY_NOTIFICATION);
            if (notificationManager != null) {
                if (notificationManager.getNotificationChannel("nim_message_channel_001") == null) {
                    notificationManager.createNotificationChannel(a(statusBarNotificationConfig));
                }
                if (notificationManager.getNotificationChannel("nim_message_channel_003") == null) {
                    notificationManager.createNotificationChannel(b(statusBarNotificationConfig));
                }
                if (notificationManager.getNotificationChannel("nim_message_channel_004") == null) {
                    notificationManager.createNotificationChannel(c(statusBarNotificationConfig));
                }
                if (notificationManager.getNotificationChannel("nim_message_channel_002") == null) {
                    notificationManager.createNotificationChannel(d(statusBarNotificationConfig));
                }
            }
        }
    }

    private static void f(Context context) {
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if (language != null && language.endsWith("zh")) {
            a = "消息通知";
            b = "消息通知";
            c = "免打扰消息通知";
            d = "免打扰消息通知";
            e = "仅响铃消息通知";
            f = "仅响铃消息通知";
            g = "仅震动消息通知";
            h = "仅震动消息通知";
        }
        com.netease.nimlib.log.b.N("language setting = " + language);
    }

    private static NotificationChannel a(StatusBarNotificationConfig statusBarNotificationConfig) {
        NotificationChannel notificationChannel = new NotificationChannel("nim_message_channel_001", a, 3);
        notificationChannel.setDescription(b);
        if (statusBarNotificationConfig != null) {
            notificationChannel.enableVibration(true);
            if (!TextUtils.isEmpty(statusBarNotificationConfig.notificationSound)) {
                AudioAttributes.Builder builder = new AudioAttributes.Builder();
                builder.setUsage(8);
                notificationChannel.setSound(Uri.parse(statusBarNotificationConfig.notificationSound), builder.build());
            }
            if (statusBarNotificationConfig.ledARGB != -1) {
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(statusBarNotificationConfig.ledARGB);
            }
            notificationChannel.setShowBadge(statusBarNotificationConfig.showBadge);
        }
        com.netease.nimlib.log.b.N("create NIM message notification channel, id=nim_message_channel_001");
        return notificationChannel;
    }

    private static NotificationChannel b(StatusBarNotificationConfig statusBarNotificationConfig) {
        NotificationChannel notificationChannel = new NotificationChannel("nim_message_channel_003", e, 3);
        notificationChannel.setDescription(f);
        if (statusBarNotificationConfig != null) {
            notificationChannel.enableVibration(false);
            if (!TextUtils.isEmpty(statusBarNotificationConfig.notificationSound)) {
                AudioAttributes.Builder builder = new AudioAttributes.Builder();
                builder.setUsage(8);
                notificationChannel.setSound(Uri.parse(statusBarNotificationConfig.notificationSound), builder.build());
            }
            if (statusBarNotificationConfig.ledARGB != -1) {
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(statusBarNotificationConfig.ledARGB);
            }
            notificationChannel.setShowBadge(statusBarNotificationConfig.showBadge);
        }
        com.netease.nimlib.log.b.N("create NIM message notification channel, id=nim_message_channel_003");
        return notificationChannel;
    }

    private static NotificationChannel c(StatusBarNotificationConfig statusBarNotificationConfig) {
        NotificationChannel notificationChannel = new NotificationChannel("nim_message_channel_004", g, 3);
        notificationChannel.setDescription(h);
        if (statusBarNotificationConfig != null) {
            notificationChannel.enableVibration(true);
            notificationChannel.setSound(null, null);
            if (statusBarNotificationConfig.ledARGB != -1) {
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(statusBarNotificationConfig.ledARGB);
            }
            notificationChannel.setShowBadge(statusBarNotificationConfig.showBadge);
        }
        com.netease.nimlib.log.b.N("create NIM message notification channel, id=nim_message_channel_004");
        return notificationChannel;
    }

    private static NotificationChannel d(StatusBarNotificationConfig statusBarNotificationConfig) {
        NotificationChannel notificationChannel = new NotificationChannel("nim_message_channel_002", c, 2);
        notificationChannel.setDescription(d);
        if (statusBarNotificationConfig != null) {
            notificationChannel.enableVibration(false);
            notificationChannel.setSound(null, null);
            if (statusBarNotificationConfig.ledARGB != -1) {
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(statusBarNotificationConfig.ledARGB);
            }
            notificationChannel.setShowBadge(statusBarNotificationConfig.showBadge);
        }
        com.netease.nimlib.log.b.N("create NIM no disturbing message notification channel, id=nim_message_channel_002");
        return notificationChannel;
    }
}
