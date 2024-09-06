package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.sdk.StatusCode;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatRoomStatusChangeData implements Serializable {
    public final String roomId;
    public final StatusCode status;

    public ChatRoomStatusChangeData(StatusCode statusCode, String str) {
        this.status = statusCode;
        this.roomId = str;
    }
}
