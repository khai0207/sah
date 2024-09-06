package com.netease.nimlib.l.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.netease.nimlib.l.a.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationCompatApi24.java */
/* loaded from: classes.dex */
class f {

    /* compiled from: NotificationCompatApi24.java */
    /* loaded from: classes.dex */
    public static class a implements com.netease.nimlib.l.a.a, b {
        private Notification.Builder a;
        private int b;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, CharSequence charSequence4, boolean z4, String str, ArrayList<String> arrayList, Bundle bundle, int i5, int i6, Notification notification2, String str2, boolean z5, String str3, CharSequence[] charSequenceArr, RemoteViews remoteViews2, RemoteViews remoteViews3, RemoteViews remoteViews4, int i7) {
            PendingIntent pendingIntent3;
            boolean z6 = true;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setShowWhen(z2).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
            } else {
                pendingIntent3 = pendingIntent2;
                z6 = false;
            }
            Notification.Builder remoteInputHistory = deleteIntent.setFullScreenIntent(pendingIntent3, z6).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z3).setPriority(i4).setProgress(i2, i3, z).setLocalOnly(z4).setExtras(bundle).setGroup(str2).setGroupSummary(z5).setSortKey(str3).setCategory(str).setColor(i5).setVisibility(i6).setPublicVersion(notification2).setRemoteInputHistory(charSequenceArr);
            this.a = remoteInputHistory;
            if (remoteViews2 != null) {
                remoteInputHistory.setCustomContentView(remoteViews2);
            }
            if (remoteViews3 != null) {
                this.a.setCustomBigContentView(remoteViews3);
            }
            if (remoteViews4 != null) {
                this.a.setCustomHeadsUpContentView(remoteViews4);
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                this.a.addPerson(it.next());
            }
            this.b = i7;
        }

        @Override // com.netease.nimlib.l.a.a
        public void a(h.a aVar) {
            f.a(this.a, aVar);
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification.Builder a() {
            return this.a;
        }

        @Override // com.netease.nimlib.l.a.b
        public Notification b() {
            Notification build = this.a.build();
            if (this.b != 0) {
                if (build.getGroup() != null && (build.flags & 512) != 0 && this.b == 2) {
                    a(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.b == 1) {
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

    public static void a(b bVar, CharSequence charSequence, CharSequence charSequence2, List<CharSequence> list, List<Long> list2, List<CharSequence> list3, List<String> list4, List<Uri> list5) {
        if (charSequence == null) {
            charSequence = "";
        }
        Notification.MessagingStyle conversationTitle = new Notification.MessagingStyle(charSequence).setConversationTitle(charSequence2);
        for (int i = 0; i < list.size(); i++) {
            Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message(list.get(i), list2.get(i).longValue(), list3.get(i));
            if (list4.get(i) != null) {
                message.setData(list4.get(i), list5.get(i));
            }
            conversationTitle.addMessage(message);
        }
        conversationTitle.setBuilder(bVar.a());
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
        builder2.setAllowGeneratedReplies(aVar.e());
        builder.addAction(builder2.build());
    }
}
