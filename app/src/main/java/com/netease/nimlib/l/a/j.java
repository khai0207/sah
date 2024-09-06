package com.netease.nimlib.l.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatExtras;
import androidx.core.app.NotificationManagerCompat;
import com.netease.nimlib.l.a.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: NotificationCompatKitKat.java */
/* loaded from: classes.dex */
class j {

    /* compiled from: NotificationCompatKitKat.java */
    /* loaded from: classes.dex */
    public static class a implements com.netease.nimlib.l.a.a, b {
        private Notification.Builder a;
        private Bundle b;
        private List<Bundle> c = new ArrayList();
        private RemoteViews d;
        private RemoteViews e;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, CharSequence charSequence4, boolean z4, ArrayList<String> arrayList, Bundle bundle, String str, boolean z5, String str2, RemoteViews remoteViews2, RemoteViews remoteViews3) {
            PendingIntent pendingIntent3;
            boolean z6 = false;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z2).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
                z6 = true;
            } else {
                pendingIntent3 = pendingIntent2;
            }
            this.a = deleteIntent.setFullScreenIntent(pendingIntent3, z6).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z3).setPriority(i4).setProgress(i2, i3, z);
            Bundle bundle2 = new Bundle();
            this.b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.b.putStringArray(NotificationCompat.EXTRA_PEOPLE, (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            if (z4) {
                this.b.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
            }
            if (str != null) {
                this.b.putString(NotificationCompatExtras.EXTRA_GROUP_KEY, str);
                if (z5) {
                    this.b.putBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY, true);
                } else {
                    this.b.putBoolean(NotificationManagerCompat.EXTRA_USE_SIDE_CHANNEL, true);
                }
            }
            if (str2 != null) {
                this.b.putString(NotificationCompatExtras.EXTRA_SORT_KEY, str2);
            }
            this.d = remoteViews2;
            this.e = remoteViews3;
        }

        @Override // com.netease.nimlib.l.a.a
        public void a(h.a aVar) {
            this.c.add(i.a(this.a, aVar));
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification.Builder a() {
            return this.a;
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification b() {
            SparseArray<Bundle> a = i.a(this.c);
            if (a != null) {
                this.b.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, a);
            }
            this.a.setExtras(this.b);
            Notification build = this.a.build();
            RemoteViews remoteViews = this.d;
            if (remoteViews != null) {
                build.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.e;
            if (remoteViews2 != null) {
                build.bigContentView = remoteViews2;
            }
            return build;
        }
    }
}
