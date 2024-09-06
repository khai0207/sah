package com.netease.nimlib.sdk.msg;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.io.Serializable;

/* loaded from: classes.dex */
public interface MessageNotifierCustomization extends Serializable {
    String makeCategory(IMMessage iMMessage);

    String makeNotifyContent(String str, IMMessage iMMessage);

    String makeRevokeMsgTip(String str, IMMessage iMMessage);

    String makeTicker(String str, IMMessage iMMessage);
}
