package com.netease.nimlib.sdk;

import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;

/* loaded from: classes.dex */
public final class NIMChatRoomSDK {
    private NIMChatRoomSDK() {
    }

    public static ChatRoomServiceObserver getChatRoomServiceObserve() {
        return (ChatRoomServiceObserver) NIMClient.getService(ChatRoomServiceObserver.class);
    }

    public static ChatRoomService getChatRoomService() {
        return (ChatRoomService) NIMClient.getService(ChatRoomService.class);
    }

    public static void main(String[] strArr) {
        System.out.println("Hello, NIM Android SDK!");
    }
}
