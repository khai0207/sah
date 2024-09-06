package com.netease.nimlib.plugin.interact;

import com.netease.nimlib.biz.g.c;

/* loaded from: classes.dex */
public interface IChatRoomInteract extends a {
    void addSendTask(c cVar, String str);

    String getAppKeyByRoomId(String str);

    String getRoomIdByAppKey(String str);

    boolean independent(String str);

    void sendRequest(String str, com.netease.nimlib.biz.d.a aVar);
}
