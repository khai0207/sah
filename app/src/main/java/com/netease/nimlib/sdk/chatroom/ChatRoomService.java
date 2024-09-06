package com.netease.nimlib.sdk.chatroom;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.chatroom.constant.ChatRoomModeEnum;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMemberUpdate;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomSpatialLocation;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomUpdateInfo;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.netease.nimlib.sdk.chatroom.model.GetMessagesByTagsParam;
import com.netease.nimlib.sdk.chatroom.model.MemberOption;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;
import com.netease.nimlib.sdk.util.Entry;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface ChatRoomService {
    InvocationFuture<List<String>> batchUpdateQueue(String str, List<Entry<String, String>> list, boolean z, Map<String, Object> map);

    AbortableFuture<Void> downloadAttachment(ChatRoomMessage chatRoomMessage, boolean z);

    InvocationFuture<Void> dropQueue(String str);

    AbortableFuture<EnterChatRoomResultData> enterChatRoom(EnterChatRoomData enterChatRoomData);

    AbortableFuture<EnterChatRoomResultData> enterChatRoomEx(EnterChatRoomData enterChatRoomData, int i);

    void exitChatRoom(String str);

    void exitChatRooms(ChatRoomModeEnum chatRoomModeEnum);

    InvocationFuture<List<Entry<String, String>>> fetchQueue(String str);

    InvocationFuture<ChatRoomInfo> fetchRoomInfo(String str);

    InvocationFuture<List<ChatRoomMember>> fetchRoomMembers(String str, MemberQueryType memberQueryType, long j, int i);

    InvocationFuture<List<ChatRoomMember>> fetchRoomMembersByIds(String str, List<String> list);

    InvocationFuture<List<ChatRoomMember>> fetchRoomMembersByTag(String str, String str2, long j, int i);

    int getEnterErrorCode(String str);

    InvocationFuture<List<ChatRoomMessage>> getMessagesByTags(GetMessagesByTagsParam getMessagesByTagsParam);

    InvocationFuture<Void> kickMember(String str, String str2, Map<String, Object> map);

    InvocationFuture<ChatRoomMember> markChatRoomBlackList(boolean z, MemberOption memberOption);

    InvocationFuture<ChatRoomMember> markChatRoomManager(boolean z, MemberOption memberOption);

    InvocationFuture<ChatRoomMember> markChatRoomMutedList(boolean z, MemberOption memberOption);

    InvocationFuture<Void> markChatRoomTempMute(boolean z, long j, MemberOption memberOption);

    InvocationFuture<Void> markChatRoomTempMuteByTag(String str, String str2, boolean z, long j, Map<String, Object> map, String str3);

    InvocationFuture<ChatRoomMember> markNormalMember(boolean z, MemberOption memberOption);

    InvocationFuture<Entry<String, String>> pollQueue(String str, String str2);

    InvocationFuture<List<NimRobotInfo>> pullAllRobots(String str);

    InvocationFuture<List<ChatRoomMessage>> pullMessageHistory(String str, long j, int i);

    InvocationFuture<List<ChatRoomMessage>> pullMessageHistoryEx(String str, long j, int i, QueryDirectionEnum queryDirectionEnum);

    InvocationFuture<List<ChatRoomMessage>> pullMessageHistoryExType(String str, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr);

    InvocationFuture<Long> queryTagMembersCount(String str, String str2);

    InvocationFuture<Void> sendMessage(ChatRoomMessage chatRoomMessage, boolean z);

    InvocationFuture<Void> updateChatRoomTags(String str, ChatRoomTagsInfo chatRoomTagsInfo);

    InvocationFuture<Void> updateLocation(String str, ChatRoomSpatialLocation chatRoomSpatialLocation);

    InvocationFuture<Void> updateMyRoomRole(String str, ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, Map<String, Object> map);

    InvocationFuture<Void> updateMyRoomRole(String str, ChatRoomMemberUpdate chatRoomMemberUpdate, boolean z, Map<String, Object> map, AntiSpamConfig antiSpamConfig);

    InvocationFuture<Void> updateQueue(String str, String str2, String str3);

    InvocationFuture<Void> updateQueue(String str, String str2, String str3, boolean z, String str4);

    InvocationFuture<Void> updateQueueEx(String str, String str2, String str3, boolean z);

    InvocationFuture<Void> updateRoomInfo(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, Map<String, Object> map);

    InvocationFuture<Void> updateRoomInfo(String str, ChatRoomUpdateInfo chatRoomUpdateInfo, boolean z, Map<String, Object> map, AntiSpamConfig antiSpamConfig);
}
