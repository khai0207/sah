package com.netease.nimlib.sdk.msg;

import android.util.Pair;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.migration.processor.IMsgExportProcessor;
import com.netease.nimlib.sdk.migration.processor.IMsgImportProcessor;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;
import com.netease.nimlib.sdk.msg.constant.DeleteTypeEnum;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CollectInfo;
import com.netease.nimlib.sdk.msg.model.CollectInfoPage;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyResult;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.LocalAntiSpamResult;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.sdk.msg.model.MsgFullKeywordSearchConfig;
import com.netease.nimlib.sdk.msg.model.MsgPinDbOption;
import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOptionWrapper;
import com.netease.nimlib.sdk.msg.model.MsgSearchOption;
import com.netease.nimlib.sdk.msg.model.MsgTimingFullKeywordSearchConfig;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.QueryMySessionOption;
import com.netease.nimlib.sdk.msg.model.QueryThreadTalkHistoryOption;
import com.netease.nimlib.sdk.msg.model.QuickCommentOptionWrapper;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RecentSessionList;
import com.netease.nimlib.sdk.msg.model.SessionAckInfo;
import com.netease.nimlib.sdk.msg.model.ShowNotificationWhenRevokeFilter;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.ThreadTalkHistory;
import com.netease.nimlib.sdk.search.model.MsgIndexRecord;
import com.netease.nimlib.sdk.team.model.IMMessageFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public interface MsgService {
    public static final String MSG_CHATTING_ACCOUNT_ALL = "all";
    public static final String MSG_CHATTING_ACCOUNT_NONE = null;

    InvocationFuture<CollectInfo> addCollect(int i, String str, String str2, String str3);

    InvocationFuture<Long> addMsgPin(IMMessage iMMessage, String str);

    InvocationFuture<Void> addQuickComment(IMMessage iMMessage, long j, String str);

    InvocationFuture<Long> addQuickComment(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map);

    InvocationFuture<StickTopSessionInfo> addStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2);

    InvocationFuture<Void> cancelUploadAttachment(IMMessage iMMessage);

    LocalAntiSpamResult checkLocalAntiSpam(String str, String str2);

    void clearAllUnreadCount();

    void clearChattingHistory(String str, SessionTypeEnum sessionTypeEnum);

    void clearChattingHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z);

    InvocationFuture<Void> clearMsgDatabase(boolean z);

    void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum);

    void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z);

    void clearServerHistory(String str, SessionTypeEnum sessionTypeEnum, boolean z, String str2);

    void clearServerHistory(String str, boolean z);

    InvocationFuture<List<SessionAckInfo>> clearUnreadCount(SessionTypeEnum sessionTypeEnum);

    InvocationFuture<Void> clearUnreadCount(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<List<SessionAckInfo>> clearUnreadCount(List<Pair<String, SessionTypeEnum>> list);

    RecentContact createEmptyRecentContact(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, boolean z);

    RecentContact createEmptyRecentContact(String str, SessionTypeEnum sessionTypeEnum, long j, long j2, boolean z, boolean z2);

    void deleteChattingHistory(IMMessage iMMessage);

    void deleteChattingHistory(IMMessage iMMessage, boolean z);

    void deleteChattingHistory(List<IMMessage> list, boolean z);

    InvocationFuture<Long> deleteMsgSelf(IMMessage iMMessage, String str);

    InvocationFuture<Long> deleteMsgSelf(List<IMMessage> list, String str);

    InvocationFuture<Void> deleteMySession(String[] strArr);

    void deleteRangeHistory(String str, SessionTypeEnum sessionTypeEnum, long j, long j2);

    InvocationFuture<Void> deleteRecentContact(String str, SessionTypeEnum sessionTypeEnum, DeleteTypeEnum deleteTypeEnum, boolean z);

    void deleteRecentContact(RecentContact recentContact);

    void deleteRecentContact2(String str, SessionTypeEnum sessionTypeEnum);

    void deleteRoamMsgHasMoreTag(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<Void> deleteRoamingRecentContact(String str, SessionTypeEnum sessionTypeEnum);

    AbortableFuture<Void> downloadAttachment(IMMessage iMMessage, boolean z);

    boolean everBeenDeleted(IMMessage iMMessage);

    AbortableFuture<Void> exportAllMessage(IMsgExportProcessor iMsgExportProcessor, boolean z);

    InvocationFuture<GetMessagesDynamicallyResult> getMessagesDynamically(GetMessagesDynamicallyParam getMessagesDynamicallyParam);

    int getTotalUnreadCount();

    int getTotalUnreadCount(boolean z);

    int getUnreadCountBySessionType(SessionTypeEnum sessionTypeEnum);

    AbortableFuture<Void> importAllMessage(IMsgImportProcessor iMsgImportProcessor, boolean z);

    InvocationFuture<Void> importRecentSessions(List<Pair<String, SessionTypeEnum>> list);

    InvocationFuture<Void> insertLocalMessage(IMMessage iMMessage, String str);

    boolean isStickTopSession(String str, SessionTypeEnum sessionTypeEnum);

    void migrateMessages(String str, String str2, boolean z);

    InvocationFuture<List<IMMessage>> pullHistoryById(List<MessageKey> list, boolean z);

    InvocationFuture<List<IMMessage>> pullMessageHistory(IMMessage iMMessage, int i, boolean z);

    InvocationFuture<List<IMMessage>> pullMessageHistory(IMMessage iMMessage, int i, boolean z, boolean z2);

    InvocationFuture<List<IMMessage>> pullMessageHistory(MsgFullKeywordSearchConfig msgFullKeywordSearchConfig);

    InvocationFuture<List<IMMessage>> pullMessageHistoryEx(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, boolean z);

    InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr);

    InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z);

    InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2);

    InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2, IMMessageFilter iMMessageFilter);

    InvocationFuture<List<IMMessage>> pullMessageHistoryExType(IMMessage iMMessage, long j, int i, QueryDirectionEnum queryDirectionEnum, MsgTypeEnum[] msgTypeEnumArr, boolean z, boolean z2, IMMessageFilter iMMessageFilter, boolean z3);

    InvocationFuture<List<IMMessage>> pullMessageHistoryOrderByTime(MsgTimingFullKeywordSearchConfig msgTimingFullKeywordSearchConfig);

    InvocationFuture<CollectInfoPage> queryCollect(int i);

    InvocationFuture<CollectInfoPage> queryCollect(CollectInfo collectInfo, long j, int i, QueryDirectionEnum queryDirectionEnum);

    InvocationFuture<CollectInfoPage> queryCollect(CollectInfo collectInfo, long j, int i, QueryDirectionEnum queryDirectionEnum, int i2, boolean z);

    IMMessage queryLastMessage(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<List<IMMessage>> queryMessageList(String str, SessionTypeEnum sessionTypeEnum, long j, int i);

    List<IMMessage> queryMessageListByServerIdBlock(List<String> list);

    InvocationFuture<List<IMMessage>> queryMessageListBySubtype(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2);

    List<IMMessage> queryMessageListBySubtypeBlock(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i, int i2);

    InvocationFuture<List<IMMessage>> queryMessageListByType(MsgTypeEnum msgTypeEnum, IMMessage iMMessage, int i);

    InvocationFuture<List<IMMessage>> queryMessageListByType(MsgTypeEnum msgTypeEnum, Long l, int i);

    @Deprecated
    InvocationFuture<List<IMMessage>> queryMessageListByTypes(List<MsgTypeEnum> list, IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z);

    InvocationFuture<List<IMMessage>> queryMessageListByTypesV2(List<MsgTypeEnum> list, IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i, boolean z);

    InvocationFuture<List<IMMessage>> queryMessageListByUuid(List<String> list);

    List<IMMessage> queryMessageListByUuidBlock(List<String> list);

    InvocationFuture<List<IMMessage>> queryMessageListEx(IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i, boolean z);

    List<IMMessage> queryMessageListExBlock(IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i, boolean z);

    InvocationFuture<List<IMMessage>> queryMessageListExTime(IMMessage iMMessage, long j, QueryDirectionEnum queryDirectionEnum, int i);

    List<MsgPinDbOption> queryMsgPinBlock(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<RecentSession> queryMySession(String str);

    InvocationFuture<RecentSessionList> queryMySessionList(long j, Long l, Integer num, Integer num2, Integer num3);

    InvocationFuture<RecentSessionList> queryMySessionList(QueryMySessionOption queryMySessionOption);

    InvocationFuture<List<QuickCommentOptionWrapper>> queryQuickComment(List<IMMessage> list);

    RecentContact queryRecentContact(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<List<RecentContact>> queryRecentContacts();

    InvocationFuture<List<RecentContact>> queryRecentContacts(int i);

    InvocationFuture<List<RecentContact>> queryRecentContacts(MsgTypeEnum msgTypeEnum);

    InvocationFuture<List<RecentContact>> queryRecentContacts(RecentContact recentContact, QueryDirectionEnum queryDirectionEnum, int i);

    InvocationFuture<List<RecentContact>> queryRecentContacts(Set<MsgTypeEnum> set);

    List<RecentContact> queryRecentContactsBlock();

    List<RecentContact> queryRecentContactsBlock(int i);

    List<RecentContact> queryRecentContactsBlock(MsgTypeEnum msgTypeEnum);

    List<RecentContact> queryRecentContactsBlock(RecentContact recentContact, QueryDirectionEnum queryDirectionEnum, int i);

    List<RecentContact> queryRecentContactsBlock(Set<MsgTypeEnum> set);

    int queryReplyCountInThreadTalkBlock(IMMessage iMMessage);

    InvocationFuture<Long> queryRoamMsgHasMoreTagServerId(String str, SessionTypeEnum sessionTypeEnum);

    long queryRoamMsgHasMoreTagServerIdBlock(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<Long> queryRoamMsgHasMoreTime(String str, SessionTypeEnum sessionTypeEnum);

    long queryRoamMsgHasMoreTimeBlock(String str, SessionTypeEnum sessionTypeEnum);

    List<StickTopSessionInfo> queryStickTopSessionBlock();

    InvocationFuture<ThreadTalkHistory> queryThreadTalkHistory(IMMessage iMMessage, long j, long j2, int i, QueryDirectionEnum queryDirectionEnum, boolean z);

    InvocationFuture<ThreadTalkHistory> queryThreadTalkHistory(MessageKey messageKey, QueryThreadTalkHistoryOption queryThreadTalkHistoryOption);

    InvocationFuture<List<IMMessage>> queryUnreadMessageList(String str, SessionTypeEnum sessionTypeEnum);

    List<IMMessage> queryUnreadMessageListBlock(String str, SessionTypeEnum sessionTypeEnum);

    void registerCustomAttachmentParser(MsgAttachmentParser msgAttachmentParser);

    void registerIMMessageFilter(IMMessageFilter iMMessageFilter);

    void registerShouldShowNotificationWhenRevokeFilter(ShowNotificationWhenRevokeFilter showNotificationWhenRevokeFilter);

    InvocationFuture<Integer> removeCollect(List<Pair<Long, Long>> list);

    InvocationFuture<Long> removeMsgPin(IMMessage iMMessage, String str);

    InvocationFuture<Void> removeQuickComment(IMMessage iMMessage, long j, String str);

    InvocationFuture<Long> removeQuickComment(IMMessage iMMessage, long j, String str, boolean z, boolean z2, String str2, String str3, Map<String, Object> map);

    InvocationFuture<Void> removeStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2);

    InvocationFuture<Void> replyMessage(IMMessage iMMessage, IMMessage iMMessage2, boolean z);

    InvocationFuture<Void> revokeMessage(IMMessage iMMessage);

    InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z);

    InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z, String str2);

    InvocationFuture<Void> revokeMessage(IMMessage iMMessage, String str, Map<String, Object> map, boolean z, String str2, String str3);

    InvocationFuture<Void> revokeMessageEx(IMMessage iMMessage, String str, Map<String, Object> map);

    InvocationFuture<Void> saveMessageToLocal(IMMessage iMMessage, boolean z);

    InvocationFuture<Void> saveMessageToLocalEx(IMMessage iMMessage, boolean z, long j);

    InvocationFuture<List<IMMessage>> searchAllMessage(MsgSearchOption msgSearchOption);

    InvocationFuture<List<IMMessage>> searchAllMessageHistory(String str, List<String> list, long j, int i);

    InvocationFuture<List<MsgIndexRecord>> searchAllSession(String str, int i);

    List<MsgIndexRecord> searchAllSessionBlock(String str, int i);

    InvocationFuture<List<IMMessage>> searchMessage(SessionTypeEnum sessionTypeEnum, String str, MsgSearchOption msgSearchOption);

    InvocationFuture<List<IMMessage>> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, int i);

    InvocationFuture<List<IMMessage>> searchMessageHistory(String str, List<String> list, IMMessage iMMessage, QueryDirectionEnum queryDirectionEnum, int i);

    InvocationFuture<ArrayList<IMMessage>> searchRoamingMsg(String str, long j, long j2, String str2, int i, boolean z);

    InvocationFuture<List<MsgIndexRecord>> searchSession(String str, SessionTypeEnum sessionTypeEnum, String str2);

    List<MsgIndexRecord> searchSessionBlock(String str, SessionTypeEnum sessionTypeEnum, String str2);

    InvocationFuture<Void> sendCustomNotification(CustomNotification customNotification);

    AbortableFuture<FileAttachment> sendFile(FileAttachment fileAttachment);

    InvocationFuture<Void> sendMessage(IMMessage iMMessage, boolean z);

    InvocationFuture<Void> sendMessageReceipt(String str, IMMessage iMMessage);

    void setChattingAccount(String str, SessionTypeEnum sessionTypeEnum);

    InvocationFuture<MsgPinSyncResponseOptionWrapper> syncMsgPin(SessionTypeEnum sessionTypeEnum, String str, long j);

    AbortableFuture<String> transVoiceToText(String str, String str2, long j);

    AbortableFuture<String> transVoiceToTextAtScene(String str, String str2, long j, String str3);

    AbortableFuture<String> transVoiceToTextEnableForce(String str, String str2, long j, String str3, boolean z);

    InvocationFuture<CollectInfo> updateCollect(long j, long j2, String str);

    InvocationFuture<CollectInfo> updateCollect(CollectInfo collectInfo, String str);

    void updateIMMessage(IMMessage iMMessage);

    void updateIMMessageStatus(IMMessage iMMessage);

    InvocationFuture<Long> updateMsgPin(IMMessage iMMessage, String str);

    InvocationFuture<Void> updateMySession(String str, String str2);

    void updateRecent(RecentContact recentContact);

    void updateRecentAndNotify(RecentContact recentContact);

    void updateRecentByMessage(IMMessage iMMessage, boolean z);

    void updateRoamMsgHasMoreTag(IMMessage iMMessage);

    InvocationFuture<StickTopSessionInfo> updateStickTopSession(String str, SessionTypeEnum sessionTypeEnum, String str2);
}
