package com.android.pc.ioc.update;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.RemoteViews;
import com.android.pc.ioc.app.Ioc;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import java.io.File;

/* loaded from: classes.dex */
public class NotificationHelper {
    private Class clazz;
    private int icon_id;
    private int layout_id;
    private Context mContext;
    private NotificationManager mContextNotificationManager;
    private Notification mDownProgrNotif;
    private RemoteViews mRemoteViews;
    private int progress_id;
    private int progress_txt_id;
    private PackageHelper mPackageHelper = new PackageHelper();
    private int id = Integer.valueOf((System.currentTimeMillis() + "").substring(4)).intValue();

    public NotificationHelper(Context context, int i, int i2, int i3, int i4, Class cls) {
        this.mContext = context;
        this.mContextNotificationManager = (NotificationManager) context.getSystemService(TransferService.INTENT_KEY_NOTIFICATION);
        this.layout_id = i;
        this.icon_id = i2;
        this.progress_id = i3;
        this.progress_txt_id = i4;
        this.clazz = cls;
    }

    public void initNotif() {
        Notification notification = new Notification();
        this.mDownProgrNotif = notification;
        notification.icon = R.drawable.stat_sys_download;
        this.mDownProgrNotif.flags |= 2;
        RemoteViews remoteViews = new RemoteViews(this.mPackageHelper.getPackageName(), this.layout_id);
        this.mRemoteViews = remoteViews;
        remoteViews.setImageViewResource(this.icon_id, this.mPackageHelper.getAppIcon());
        this.mDownProgrNotif.contentView = this.mRemoteViews;
        Intent intent = this.clazz != null ? new Intent(Ioc.getIoc().getApplication(), (Class<?>) this.clazz) : null;
        Notification notification2 = this.mDownProgrNotif;
        Context context = this.mContext;
        if (intent == null) {
            intent = new Intent();
        }
        notification2.contentIntent = PendingIntent.getService(context, 0, intent, 0);
    }

    private Notification getDownFinishedNotification(File file) {
        Notification notification = new Notification();
        Notification.Builder builder = new Notification.Builder(this.mContext);
        builder.setSmallIcon(R.drawable.stat_sys_download_done);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        builder.setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, 0));
        builder.build();
        this.mContextNotificationManager.notify(this.id, notification);
        return notification;
    }

    public void downShowNotification(String str) {
        Notification notification = new Notification();
        Notification.Builder builder = new Notification.Builder(this.mContext);
        builder.setSmallIcon(R.drawable.stat_sys_download_done);
        Intent intent = new Intent();
        if (this.clazz != null) {
            intent = new Intent(Ioc.getIoc().getApplication(), (Class<?>) this.clazz);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(270532608);
        }
        builder.setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, 0));
        builder.build();
        this.mContextNotificationManager.notify(this.id, notification);
        this.mContextNotificationManager.notify(this.id, new Notification());
    }

    public void downNotification(String str) {
        Notification notification = new Notification();
        Notification.Builder builder = new Notification.Builder(this.mContext);
        builder.setSmallIcon(R.drawable.stat_sys_download);
        Intent intent = this.clazz != null ? new Intent(Ioc.getIoc().getApplication(), (Class<?>) this.clazz) : null;
        Context context = this.mContext;
        if (intent == null) {
            intent = new Intent();
        }
        builder.setContentIntent(PendingIntent.getService(context, 0, intent, 0));
        builder.build();
        this.mContextNotificationManager.notify(this.id, notification);
    }

    public void cancel() {
        this.mContextNotificationManager.cancel(this.id);
    }

    public void refreshProgress(float f) {
        this.mRemoteViews.setProgressBar(this.progress_id, 100, (int) f, false);
        this.mRemoteViews.setTextViewText(this.progress_txt_id, String.format("%.1f", Float.valueOf(f)));
        this.mContextNotificationManager.notify(this.id, this.mDownProgrNotif);
    }

    public void notifyUpdateFinish(File file) {
        this.mContextNotificationManager.notify(this.id, getDownFinishedNotification(file));
    }

    /* loaded from: classes.dex */
    class PackageHelper {
        private PackageInfo info;
        private PackageManager pm;

        public PackageHelper() {
            this.info = null;
            PackageManager packageManager = Ioc.getIoc().getApplication().getPackageManager();
            this.pm = packageManager;
            try {
                this.info = packageManager.getPackageInfo(Ioc.getIoc().getApplication().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String getAppName() {
            PackageInfo packageInfo = this.info;
            return packageInfo != null ? (String) packageInfo.applicationInfo.loadLabel(this.pm) : "";
        }

        public String getPackageName() {
            PackageInfo packageInfo = this.info;
            return packageInfo != null ? packageInfo.packageName : "";
        }

        public int getAppIcon() {
            PackageInfo packageInfo = this.info;
            return packageInfo != null ? packageInfo.applicationInfo.icon : R.drawable.ic_dialog_info;
        }
    }
}
