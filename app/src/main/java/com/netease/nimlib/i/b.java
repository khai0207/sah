package com.netease.nimlib.i;

import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.event.EventSubscribeServiceObserver;
import com.netease.nimlib.sdk.event.model.Event;
import com.netease.nimlib.sdk.friend.FriendServiceObserve;
import com.netease.nimlib.sdk.friend.model.BlackListChangedNotify;
import com.netease.nimlib.sdk.friend.model.FriendChangedNotify;
import com.netease.nimlib.sdk.friend.model.MuteListChangedNotify;
import com.netease.nimlib.sdk.lifecycle.SdkLifecycleObserver;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.model.AttachmentProgress;
import com.netease.nimlib.sdk.msg.model.BroadcastMessage;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.HandleQuickCommentOption;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MessageReceipt;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.sdk.msg.model.RoamMsgHasMoreOption;
import com.netease.nimlib.sdk.msg.model.SessionMsgDeleteOption;
import com.netease.nimlib.sdk.msg.model.StickTopSessionInfo;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import com.netease.nimlib.sdk.nos.NosServiceObserve;
import com.netease.nimlib.sdk.nos.model.NosTransferInfo;
import com.netease.nimlib.sdk.nos.model.NosTransferProgress;
import com.netease.nimlib.sdk.passthrough.PassthroughServiceObserve;
import com.netease.nimlib.sdk.passthrough.model.PassthroughNotifyData;
import com.netease.nimlib.sdk.robot.RobotServiceObserve;
import com.netease.nimlib.sdk.robot.model.RobotChangedNotify;
import com.netease.nimlib.sdk.settings.SettingsServiceObserver;
import com.netease.nimlib.sdk.team.TeamServiceObserver;
import com.netease.nimlib.sdk.uinfo.UserServiceObserve;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.o;
import com.netease.nimlib.session.q;
import com.netease.nimlib.session.r;
import com.netease.nimlib.session.z;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationCenter.java */
/* loaded from: classes.dex */
public class b {
    public static final String a = TeamServiceObserver.class.getSimpleName();

    public static void a(StatusCode statusCode) {
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeOnlineStatus", statusCode);
    }

    public static void a(List<com.netease.nimlib.biz.f> list) {
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeOtherClients", list);
    }

    public static void a(LoginSyncStatus loginSyncStatus) {
        com.netease.nimlib.log.b.d("NotificationCenter", "notifyLoginSyncDataStatus " + loginSyncStatus);
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeLoginSyncDataStatus", loginSyncStatus);
    }

    public static void a(boolean z) {
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeLoginSyncTeamMembersCompleteResult", Boolean.valueOf(z));
    }

    public static void b(boolean z) {
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeLoginSyncSuperTeamMembersCompleteResult", Boolean.valueOf(z));
    }

    public static void a() {
        com.netease.nimlib.log.b.d("NotificationCenter", "notifyDataReady");
        a.a(AuthServiceObserver.class.getSimpleName() + "/observeDataReady", null);
    }

    public static void b(List<IMMessageImpl> list) {
        com.netease.nimlib.session.j.h(list);
        com.netease.nimlib.log.b.I("************ observeReceiveMessage begin ****************");
        for (IMMessageImpl iMMessageImpl : list) {
            com.netease.nimlib.log.b.a(iMMessageImpl);
            com.netease.nimlib.n.f.a().a(iMMessageImpl, 200);
        }
        com.netease.nimlib.log.b.I("************ observeReceiveMessage end ****************");
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeReceiveMessage", list);
    }

    public static void a(SystemMessage systemMessage) {
        a.a(SystemMessageObserver.class.getSimpleName() + "/observeReceiveSystemMsg", systemMessage);
    }

    public static void a(CustomNotification customNotification) {
        com.netease.nimlib.log.b.d("NotificationCenter", customNotification == null ? "notify empty custom notification" : String.format("notify custom notification from %s to [%s]%s", customNotification.getFromAccount(), customNotification.getSessionType(), customNotification.getSessionId()));
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeCustomNotification", customNotification);
    }

    public static void a(BroadcastMessage broadcastMessage) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeBroadcastMessage", broadcastMessage);
    }

    public static void a(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.log.b.I("************ observeMsgStatus begin ****************");
        com.netease.nimlib.log.b.a(iMMessageImpl);
        com.netease.nimlib.log.b.I("************ observeMsgStatus end ****************");
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeMsgStatus", iMMessageImpl);
    }

    public static void c(List<MessageReceipt> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeMessageReceipt", list);
    }

    public static void d(List<TeamMessageReceipt> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeTeamMessageReceipt", list);
    }

    public static void a(String str, long j, long j2) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeAttachmentProgress", new AttachmentProgress(str, j, j2));
    }

    public static void a(q qVar) {
        if (qVar == null) {
            return;
        }
        e(com.netease.nimlib.o.f.a(qVar));
    }

    public static void e(List<q> list) {
        com.netease.nimlib.log.b.d("NotificationCenter", "notify recent contact list, " + com.netease.nimlib.o.f.a(list, ", ", "[", "]", $$Lambda$b$iubMhCx9fh6QVpif5aSmvggmQU.INSTANCE));
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRecentContact", list);
    }

    public static /* synthetic */ String c(q qVar) {
        return qVar.d().toString();
    }

    public static void b(q qVar) {
        Object[] objArr = new Object[1];
        objArr[0] = qVar == null ? null : qVar.d();
        com.netease.nimlib.log.b.d("NotificationCenter", String.format("notify recent contact deleted, %s", objArr));
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRecentContactDeleted", qVar);
    }

    public static void a(int i) {
        a.a(SystemMessageObserver.class.getSimpleName() + "/observeUnreadCountChange", Integer.valueOf(i));
    }

    public static void a(RevokeMsgNotification revokeMsgNotification) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRevokeMessage", revokeMsgNotification);
    }

    public static void a(r rVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeUpdateMySession", rVar);
    }

    public static void b(IMMessageImpl iMMessageImpl) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeDeleteMsgSelf", iMMessageImpl);
    }

    public static void f(List<? extends IMMessage> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeDeleteMsgSelfBatch", list);
    }

    public static void g(List<SessionMsgDeleteOption> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeDeleteSessionHistoryMsgs", list);
    }

    public static void h(List<RoamMsgHasMoreOption> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRoamMsgHasMore", list);
    }

    public static void a(HandleQuickCommentOption handleQuickCommentOption) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeAddQuickComment", handleQuickCommentOption);
    }

    public static void b(HandleQuickCommentOption handleQuickCommentOption) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRemoveQuickComment", handleQuickCommentOption);
    }

    public static void a(o oVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeAddMsgPin", oVar);
    }

    public static void b(o oVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeUpdateMsgPin", oVar);
    }

    public static void c(o oVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRemoveMsgPin", oVar);
    }

    public static void i(List<StickTopSessionInfo> list) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeSyncStickTopSession", list);
    }

    public static void a(z zVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeAddStickTopSession", zVar);
    }

    public static void b(z zVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeRemoveStickTopSession", zVar);
    }

    public static void c(z zVar) {
        a.a(MsgServiceObserve.class.getSimpleName() + "/observeUpdateStickTopSession", zVar);
    }

    public static void a(com.netease.nimlib.team.d dVar) {
        if (dVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(dVar);
        a.a(a + "/observeTeamUpdate", arrayList);
    }

    public static void j(List<com.netease.nimlib.team.d> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a.a(a + "/observeTeamUpdate", list);
    }

    public static void a(com.netease.nimlib.superteam.b bVar, String str) {
        if (bVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bVar);
        a(arrayList, str);
    }

    public static void a(List<com.netease.nimlib.superteam.b> list, String str) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a.a(str + "/observeTeamUpdate", list);
    }

    public static void b(com.netease.nimlib.team.d dVar) {
        if (dVar == null) {
            return;
        }
        a.a(a + "/observeTeamRemove", dVar);
    }

    public static void b(com.netease.nimlib.superteam.b bVar, String str) {
        if (bVar == null) {
            return;
        }
        a.a(str + "/observeTeamRemove", bVar);
    }

    public static void a(com.netease.nimlib.team.g gVar) {
        if (gVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(gVar);
        k(arrayList);
    }

    public static void k(List<com.netease.nimlib.team.g> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (com.netease.nimlib.log.b.a()) {
            com.netease.nimlib.log.b.c("NotificationCenter", "notify team member update, " + com.netease.nimlib.o.f.a(list, ", ", "[", "]", $$Lambda$ONW__1rILt1SwE_ieIyS9oaMnM.INSTANCE));
        }
        a.a(a + "/observeMemberUpdate", list);
    }

    public static void a(com.netease.nimlib.superteam.c cVar, String str) {
        if (cVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cVar);
        b(arrayList, str);
    }

    public static void b(List<com.netease.nimlib.superteam.c> list, String str) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (com.netease.nimlib.log.b.a()) {
            com.netease.nimlib.log.b.c("NotificationCenter", "notify super team member update, " + com.netease.nimlib.o.f.a(list, ", ", "[", "]", $$Lambda$xSoEZ61NcLBXOfW4WRuqcUC6ZE.INSTANCE));
        }
        a.a(str + "/observeMemberUpdate", list);
    }

    public static void a(String str, String str2) {
        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, str2);
        if (queryTeamMember == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(queryTeamMember);
        l(arrayList);
    }

    public static void l(List<com.netease.nimlib.team.g> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a.a(a + "/observeMemberRemove", list);
    }

    public static void a(String str, String str2, String str3) {
        com.netease.nimlib.superteam.c queryTeamMember = SuperTeamDBHelper.queryTeamMember(str, str2);
        if (queryTeamMember == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(queryTeamMember);
        c(arrayList, str3);
    }

    public static void c(List<com.netease.nimlib.superteam.c> list, String str) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a.a(str + "/observeMemberRemove", list);
    }

    public static void a(FriendChangedNotify friendChangedNotify) {
        a.a(FriendServiceObserve.class.getSimpleName() + "/observeFriendChangedNotify", friendChangedNotify);
    }

    public static void a(BlackListChangedNotify blackListChangedNotify) {
        a.a(FriendServiceObserve.class.getSimpleName() + "/observeBlackListChangedNotify", blackListChangedNotify);
    }

    public static void a(MuteListChangedNotify muteListChangedNotify) {
        a.a(FriendServiceObserve.class.getSimpleName() + "/observeMuteListChangedNotify", muteListChangedNotify);
    }

    public static void m(List<com.netease.nimlib.user.b> list) {
        a.a(UserServiceObserve.class.getSimpleName() + "/observeUserInfoUpdate", list);
    }

    public static void a(NosTransferInfo nosTransferInfo) {
        a.a(NosServiceObserve.class.getSimpleName() + "/observeNosTransferStatus", nosTransferInfo);
    }

    public static void b(String str, long j, long j2) {
        a.a(NosServiceObserve.class.getSimpleName() + "/observeNosTransferProgress", new NosTransferProgress(str, j, j2));
    }

    public static void c(boolean z) {
        a.a(SettingsServiceObserver.class.getSimpleName() + "/observeMultiportPushConfigNotify", Boolean.valueOf(z));
    }

    public static void a(ArrayList<Event> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            com.netease.nimlib.log.b.d("NotificationCenter", "notifyEvents, events is empty");
            return;
        }
        com.netease.nimlib.log.b.d("NotificationCenter", "notifyEvents, events size = " + arrayList.size());
        Iterator<Event> it = arrayList.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.log.b.c("NotificationCenter", "notifyEvents event = " + com.netease.nimlib.f.a.a(it.next()));
        }
        a.a(EventSubscribeServiceObserver.class.getSimpleName() + "/observeEventChanged", arrayList);
    }

    public static void a(RobotChangedNotify robotChangedNotify) {
        a.a(RobotServiceObserve.class.getSimpleName() + "/observeRobotChangedNotify", robotChangedNotify);
    }

    public static void a(PassthroughNotifyData passthroughNotifyData) {
        a.a(PassthroughServiceObserve.class.getSimpleName() + "/observePassthroughNotify", passthroughNotifyData);
    }

    public static void d(boolean z) {
        a.a(SdkLifecycleObserver.class.getSimpleName() + "/observeMainProcessInitCompleteResult", Boolean.valueOf(z));
    }
}
