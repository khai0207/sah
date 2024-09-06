package com.netease.nimlib.l.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompatExtras;
import androidx.core.app.NotificationCompatJellybean;
import androidx.core.app.NotificationManagerCompat;
import com.netease.nimlib.l.a.h;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationCompatJellybean.java */
/* loaded from: classes.dex */
class i {
    private static Field b;
    private static boolean c;
    private static final Object a = new Object();
    private static final Object d = new Object();

    /* compiled from: NotificationCompatJellybean.java */
    /* loaded from: classes.dex */
    public static class a implements com.netease.nimlib.l.a.a, b {
        private Notification.Builder a;
        private final Bundle b;
        private List<Bundle> c = new ArrayList();
        private RemoteViews d;
        private RemoteViews e;

        public a(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, int i4, CharSequence charSequence4, boolean z3, Bundle bundle, String str, boolean z4, String str2, RemoteViews remoteViews2, RemoteViews remoteViews3) {
            PendingIntent pendingIntent3;
            boolean z5 = false;
            Notification.Builder deleteIntent = new Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                pendingIntent3 = pendingIntent2;
                z5 = true;
            } else {
                pendingIntent3 = pendingIntent2;
            }
            this.a = deleteIntent.setFullScreenIntent(pendingIntent3, z5).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z2).setPriority(i4).setProgress(i2, i3, z);
            Bundle bundle2 = new Bundle();
            this.b = bundle2;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            if (z3) {
                this.b.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
            }
            if (str != null) {
                this.b.putString(NotificationCompatExtras.EXTRA_GROUP_KEY, str);
                if (z4) {
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
            Notification build = this.a.build();
            Bundle a = i.a(build);
            Bundle bundle = new Bundle(this.b);
            for (String str : this.b.keySet()) {
                if (a.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            a.putAll(bundle);
            SparseArray<Bundle> a2 = i.a(this.c);
            if (a2 != null) {
                i.a(build).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, a2);
            }
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

    public static void a(b bVar, CharSequence charSequence, boolean z, CharSequence charSequence2, CharSequence charSequence3) {
        Notification.BigTextStyle bigText = new Notification.BigTextStyle(bVar.a()).setBigContentTitle(charSequence).bigText(charSequence3);
        if (z) {
            bigText.setSummaryText(charSequence2);
        }
    }

    public static void a(b bVar, CharSequence charSequence, boolean z, CharSequence charSequence2, Bitmap bitmap, Bitmap bitmap2, boolean z2) {
        Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(bVar.a()).setBigContentTitle(charSequence).bigPicture(bitmap);
        if (z2) {
            bigPicture.bigLargeIcon(bitmap2);
        }
        if (z) {
            bigPicture.setSummaryText(charSequence2);
        }
    }

    public static void a(b bVar, CharSequence charSequence, boolean z, CharSequence charSequence2, ArrayList<CharSequence> arrayList) {
        Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(bVar.a()).setBigContentTitle(charSequence);
        if (z) {
            bigContentTitle.setSummaryText(charSequence2);
        }
        Iterator<CharSequence> it = arrayList.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine(it.next());
        }
    }

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle a(Notification notification) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                try {
                    if (b == null) {
                        Field declaredField = Notification.class.getDeclaredField("extras");
                        if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                            if (com.netease.nimlib.log.b.a.a()) {
                                Log.e(NotificationCompatJellybean.TAG, "Notification.extras field is not of type Bundle");
                            }
                            c = true;
                            return null;
                        }
                        declaredField.setAccessible(true);
                        b = declaredField;
                    }
                    Bundle bundle = (Bundle) b.get(notification);
                    if (bundle == null) {
                        bundle = new Bundle();
                        b.set(notification, bundle);
                    }
                    return bundle;
                } catch (NoSuchFieldException e) {
                    if (com.netease.nimlib.log.b.a.a()) {
                        Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e);
                    }
                    c = true;
                    return null;
                }
            } catch (IllegalAccessException e2) {
                if (com.netease.nimlib.log.b.a.a()) {
                    Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e2);
                }
                c = true;
                return null;
            }
        }
    }

    public static Bundle a(Notification.Builder builder, h.a aVar) {
        builder.addAction(aVar.a(), aVar.b(), aVar.c());
        Bundle bundle = new Bundle(aVar.d());
        if (aVar.i() != null) {
            bundle.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, n.a(aVar.i()));
        }
        if (aVar.h() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", n.a(aVar.h()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.e());
        return bundle;
    }
}
