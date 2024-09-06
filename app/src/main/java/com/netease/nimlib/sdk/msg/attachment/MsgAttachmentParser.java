package com.netease.nimlib.sdk.msg.attachment;

import java.io.Serializable;

/* loaded from: classes.dex */
public interface MsgAttachmentParser extends Serializable {
    MsgAttachment parse(String str);
}
