package com.netease.nimlib.sdk.uinfo;

import android.graphics.Bitmap;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;

/* loaded from: classes.dex */
public interface UserInfoProvider {
    Bitmap getAvatarForMessageNotifier(SessionTypeEnum sessionTypeEnum, String str);

    String getDisplayNameForMessageNotifier(String str, String str2, SessionTypeEnum sessionTypeEnum);

    String getDisplayTitleForMessageNotifier(IMMessage iMMessage);

    UserInfo getUserInfo(String str);
}
