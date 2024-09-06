package com.netease.nimlib.sdk.uinfo.model;

import com.netease.nimlib.sdk.uinfo.constant.GenderEnum;
import java.util.Map;

/* loaded from: classes.dex */
public interface NimUserInfo extends UserInfo {
    String getBirthday();

    String getEmail();

    String getExtension();

    Map<String, Object> getExtensionMap();

    GenderEnum getGenderEnum();

    String getMobile();

    String getSignature();
}
