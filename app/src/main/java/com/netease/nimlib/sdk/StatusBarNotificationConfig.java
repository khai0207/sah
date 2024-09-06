package com.netease.nimlib.sdk;

import android.app.Activity;
import com.netease.nimlib.sdk.msg.constant.NotificationExtraTypeEnum;
import java.io.Serializable;

/* loaded from: classes.dex */
public class StatusBarNotificationConfig implements Serializable {
    public String customTitleWhenTeamNameEmpty;
    public String downTimeBegin;
    public String downTimeEnd;
    public int notificationColor;
    public Class<? extends Activity> notificationEntrance;
    public int notificationSmallIconId;
    public String notificationSound;
    public boolean ring = true;
    public boolean vibrate = true;
    public int ledARGB = -1;
    public int ledOnMs = -1;
    public int ledOffMs = -1;
    public boolean hideContent = false;
    public boolean downTimeToggle = false;
    public boolean downTimeEnableNotification = true;
    public boolean titleOnlyShowAppName = false;

    @Deprecated
    public boolean notificationFolded = true;
    public NotificationFoldStyle notificationFoldStyle = NotificationFoldStyle.ALL;
    public boolean showBadge = true;
    public NotificationExtraTypeEnum notificationExtraType = NotificationExtraTypeEnum.MESSAGE;
    public StatusBarNotificationFilter notificationFilter = null;
    public NimPermissionRequester postNotificationsRequester = null;
}
