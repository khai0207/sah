package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.push.packet.b.c;
import java.io.Serializable;

/* loaded from: classes.dex */
public class HandleQuickCommentOption implements Serializable {
    private final QuickCommentOption commentOption;
    private final MessageKey key;

    public HandleQuickCommentOption(c cVar, c cVar2) {
        this.key = cVar == null ? null : new MessageKey(cVar);
        this.commentOption = cVar2 != null ? QuickCommentOption.fromProperty(cVar2) : null;
    }

    public MessageKey getKey() {
        return this.key;
    }

    public QuickCommentOption getCommentOption() {
        return this.commentOption;
    }
}
