package com.netease.nimlib.sdk.msg;

import com.netease.nimlib.i.d;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.model.AttachmentProgress;
import com.netease.nimlib.sdk.msg.model.BroadcastMessage;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.HandleQuickCommentOption;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.MsgPinSyncResponseOption;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.sdk.msg.model.SessionMsgDeleteOption;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import java.util.List;

@d
/* loaded from: classes.dex */
public interface MsgServiceObserve {
    void observeAddMsgPin(Observer<MsgPinSyncResponseOption> observer, boolean z);

    void observeAddQuickComment(Observer<HandleQuickCommentOption> observer, boolean z);

    void observeAddStickTopSession(Observer<StickTopSessionInfo> observer, boolean z);

    void observeAttachmentProgress(Observer<AttachmentProgress> observer, boolean z);

    void observeBroadcastMessage(Observer<BroadcastMessage> observer, boolean z);

    void observeCustomNotification(Observer<CustomNotification> observer, boolean z);

    void observeDeleteMsgSelf(Observer<IMMessage> observer, boolean z);

    void observeDeleteMsgSelfBatch(Observer<List<IMMessage>> observer, boolean z);

    void observeDeleteSessionHistoryMsgs(Observer<List<SessionMsgDeleteOption>> observer, boolean z);

    void observeMessageReceipt(Observer<List<MessageReceipt>> observer, boolean z);

    void observeMsgStatus(Observer<IMMessage> observer, boolean z);

    void observeReceiveMessage(Observer<List<IMMessage>> observer, boolean z);

    void observeRecentContact(Observer<List<RecentContact>> observer, boolean z);

    void observeRecentContactDeleted(Observer<RecentContact> observer, boolean z);

    void observeRemoveMsgPin(Observer<MsgPinSyncResponseOption> observer, boolean z);

    void observeRemoveQuickComment(Observer<HandleQuickCommentOption> observer, boolean z);

    void observeRemoveStickTopSession(Observer<StickTopSessionInfo> observer, boolean z);

    void observeRevokeMessage(Observer<RevokeMsgNotification> observer, boolean z);

    void observeRoamMsgHasMore(Observer<List<RoamMsgHasMoreOption>> observer, boolean z);

    void observeSyncStickTopSession(Observer<List<StickTopSessionInfo>> observer, boolean z);

    void observeTeamMessageReceipt(Observer<List<TeamMessageReceipt>> observer, boolean z);

    void observeUpdateMsgPin(Observer<MsgPinSyncResponseOption> observer, boolean z);

    void observeUpdateMySession(Observer<RecentSession> observer, boolean z);

    void observeUpdateStickTopSession(Observer<StickTopSessionInfo> observer, boolean z);
}
