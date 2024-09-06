package com.netease.nimlib.l.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.netease.nimlib.l.a.h;
import java.util.ArrayList;

/* compiled from: NotificationCompatApi20.java */
/* loaded from: classes.dex */
class d {

    /* compiled from: NotificationCompatApi20.java */
    /* loaded from: classes.dex */
    public static class a implements com.netease.nimlib.l.a.a, b {
        private Notification.Builder a;
        private Bundle b;
        private RemoteViews c;
        private RemoteViews d;
        private int e;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, CharSequence charSequence4, boolean z4, ArrayList<String> arrayList, Bundle bundle, String str, boolean z5, String str2, RemoteViews remoteViews2, RemoteViews remoteViews3, int i5) {
            PendingIntent pendingIntent3;
            boolean z6 = true;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z2).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
            } else {
                pendingIntent3 = pendingIntent2;
                z6 = false;
            }
            this.a = deleteIntent.setFullScreenIntent(pendingIntent3, z6).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z3).setPriority(i4).setProgress(i2, i3, z).setLocalOnly(z4).setGroup(str).setGroupSummary(z5).setSortKey(str2);
            Bundle bundle2 = new Bundle();
            this.b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.b.putStringArray(NotificationCompat.EXTRA_PEOPLE, (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            this.c = remoteViews2;
            this.d = remoteViews3;
            this.e = i5;
        }

        @Override // com.netease.nimlib.l.a.a
        public void a(h.a aVar) {
            d.a(this.a, aVar);
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification.Builder a() {
            return this.a;
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification b() {
            this.a.setExtras(this.b);
            Notification build = this.a.build();
            RemoteViews remoteViews = this.c;
            if (remoteViews != null) {
                build.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.d;
            if (remoteViews2 != null) {
                build.bigContentView = remoteViews2;
            }
            if (this.e != 0) {
                if (build.getGroup() != null && (build.flags & 512) != 0 && this.e == 2) {
                    a(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.e == 1) {
                    a(build);
                }
            }
            return build;
        }

        private void a(Notification notification) {
            notification.sound = null;
            notification.vibrate = null;
            notification.defaults &= -2;
            notification.defaults &= -3;
        }
    }

    public static void a(Notification.Builder builder, h.a aVar) {
        Bundle bundle;
        Notification.Action.Builder builder2 = new Notification.Action.Builder(aVar.a(), aVar.b(), aVar.c());
        if (aVar.i() != null) {
            for (RemoteInput remoteInput : l.a(aVar.i())) {
                builder2.addRemoteInput(remoteInput);
            }
        }
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.e());
        builder2.addExtras(bundle);
        builder.addAction(builder2.build());
    }
}
