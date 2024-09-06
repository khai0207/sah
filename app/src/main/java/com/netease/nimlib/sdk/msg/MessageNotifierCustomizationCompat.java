package com.netease.nimlib.sdk.msg;

import com.netease.nimlib.sdk.msg.model.IMMessage;

/* loaded from: classes.dex */
public abstract class MessageNotifierCustomizationCompat implements MessageNotifierCustomization {
    @Override // com.netease.nimlib.sdk.msg.MessageNotifierCustomization
    public final String makeNotifyContent(String str, IMMessage iMMessage) {
        return null;
    }

    public abstract CharSequence makeNotifyContentChars(String str, IMMessage iMMessage);

    @Override // com.netease.nimlib.sdk.msg.MessageNotifierCustomization
    public final String makeTicker(String str, IMMessage iMMessage) {
        return null;
    }

    public abstract CharSequence makeTickerChars(String str, IMMessage iMMessage);
}
