package com.netease.nimlib.session;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOption;

/* compiled from: MsgPinSyncResponseOptionImpl.java */
/* loaded from: classes.dex */
public class o implements MsgPinSyncResponseOption {
    private final MessageKey a;
    private final n b;

    public o(SessionTypeEnum sessionTypeEnum, String str, String str2, long j, long j2, String str3, String str4, String str5, long j3, long j4) {
        this(new MessageKey(sessionTypeEnum, str, str2, j, j2, str3), new n(str4, str5, j3, j4));
    }

    public o(MessageKey messageKey, n nVar) {
        this.a = messageKey;
        this.b = nVar;
    }

    public static o a(com.netease.nimlib.push.packet.b.c cVar) {
        return new o(SessionTypeEnum.typeOfValue(cVar.d(1)), cVar.c(2), cVar.c(3), cVar.e(4), cVar.e(5), cVar.c(6), cVar.c(7), cVar.c(8), cVar.e(9), cVar.e(10));
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOption
    public MessageKey getKey() {
        return this.a;
    }

    @Override // com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOption
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public n getPinOption() {
        return this.b;
    }
}
