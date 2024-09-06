package com.netease.nimlib.team;

import com.netease.nimlib.o.r;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.superteam.SuperTeamDBHelper;
import java.util.List;

/* compiled from: TeamMsgNotifyCheck.java */
/* loaded from: classes.dex */
public class i {
    private static boolean a = false;

    public static void a(boolean z) {
        a = z;
    }

    public static boolean a(IMMessage iMMessage) {
        if (iMMessage.getSessionType() == SessionTypeEnum.Team) {
            return a(iMMessage.getSessionId(), iMMessage.getFromAccount());
        }
        if (iMMessage.getSessionType() == SessionTypeEnum.SUPER_TEAM) {
            return d(iMMessage.getSessionId(), iMMessage.getFromAccount());
        }
        return false;
    }

    public static void a() {
        if (a) {
            a = false;
            r.a().a(d.class);
            r.a().a(g.class);
            r.a().a(com.netease.nimlib.superteam.b.class);
            r.a().a(com.netease.nimlib.superteam.c.class);
        }
    }

    private static boolean a(String str, String str2) {
        return a ? b(str, str2) : c(str, str2);
    }

    private static boolean b(String str, String str2) {
        List<String> followAccountIds;
        d dVar = (d) r.a().a(d.class, (Object) str);
        if (dVar == null) {
            dVar = TeamDBHelper.queryTeam(str);
            r.a().a(str, dVar);
        }
        if (dVar.getMessageNotifyType() == TeamMessageNotifyTypeEnum.All) {
            return false;
        }
        f fVar = new f(str, com.netease.nimlib.c.n());
        g gVar = (g) r.a().a(g.class, (Object) fVar);
        if (gVar == null) {
            gVar = TeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
            r.a().a(fVar, gVar);
        }
        if (gVar != null && (followAccountIds = gVar.getFollowAccountIds()) != null && followAccountIds.contains(str2)) {
            return false;
        }
        if (dVar.getMessageNotifyType() == TeamMessageNotifyTypeEnum.Mute) {
            return true;
        }
        if (dVar.getMessageNotifyType() != TeamMessageNotifyTypeEnum.Manager) {
            return false;
        }
        f fVar2 = new f(str, str2);
        g gVar2 = (g) r.a().a(g.class, (Object) fVar2);
        if (gVar2 == null) {
            gVar2 = TeamDBHelper.queryTeamMember(str, str2);
            r.a().a(fVar2, gVar2);
        }
        return (gVar2.getType() == TeamMemberType.Manager || gVar2.getType() == TeamMemberType.Owner) ? false : true;
    }

    private static boolean c(String str, String str2) {
        TeamMemberType memberType;
        List<String> followAccountIds;
        TeamMessageNotifyTypeEnum a2 = c.a(str);
        if (a2 == TeamMessageNotifyTypeEnum.All) {
            return false;
        }
        g queryTeamMember = TeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
        if (queryTeamMember != null && (followAccountIds = queryTeamMember.getFollowAccountIds()) != null && followAccountIds.contains(str2)) {
            return false;
        }
        if (a2 == TeamMessageNotifyTypeEnum.Mute) {
            return true;
        }
        return (a2 != TeamMessageNotifyTypeEnum.Manager || (memberType = TeamDBHelper.getMemberType(str, str2)) == TeamMemberType.Manager || memberType == TeamMemberType.Owner) ? false : true;
    }

    private static boolean d(String str, String str2) {
        return a ? e(str, str2) : f(str, str2);
    }

    private static boolean e(String str, String str2) {
        List<String> followAccountIds;
        com.netease.nimlib.superteam.b bVar = (com.netease.nimlib.superteam.b) r.a().a(com.netease.nimlib.superteam.b.class, (Object) str);
        if (bVar == null) {
            bVar = SuperTeamDBHelper.querySuperTeam(str);
            r.a().a(str, bVar);
        }
        if (bVar.getMessageNotifyType() == TeamMessageNotifyTypeEnum.All) {
            return false;
        }
        f fVar = new f(str, com.netease.nimlib.c.n());
        com.netease.nimlib.superteam.c cVar = (com.netease.nimlib.superteam.c) r.a().a(com.netease.nimlib.superteam.c.class, (Object) fVar);
        if (cVar == null) {
            cVar = SuperTeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
            r.a().a(fVar, cVar);
        }
        if (cVar != null && (followAccountIds = cVar.getFollowAccountIds()) != null && followAccountIds.contains(str2)) {
            return false;
        }
        if (bVar.getMessageNotifyType() == TeamMessageNotifyTypeEnum.Mute) {
            return true;
        }
        if (bVar.getMessageNotifyType() != TeamMessageNotifyTypeEnum.Manager) {
            return false;
        }
        f fVar2 = new f(str, str2);
        com.netease.nimlib.superteam.c cVar2 = (com.netease.nimlib.superteam.c) r.a().a(com.netease.nimlib.superteam.c.class, (Object) fVar2);
        if (cVar2 == null) {
            cVar2 = SuperTeamDBHelper.queryTeamMember(str, str2);
            r.a().a(fVar2, cVar2);
        }
        return (cVar2.getType() == TeamMemberType.Manager || cVar2.getType() == TeamMemberType.Owner) ? false : true;
    }

    private static boolean f(String str, String str2) {
        TeamMemberType memberType;
        List<String> followAccountIds;
        TeamMessageNotifyTypeEnum a2 = com.netease.nimlib.superteam.a.a(str);
        if (a2 == TeamMessageNotifyTypeEnum.All) {
            return false;
        }
        com.netease.nimlib.superteam.c queryTeamMember = SuperTeamDBHelper.queryTeamMember(str, com.netease.nimlib.c.n());
        if (queryTeamMember != null && (followAccountIds = queryTeamMember.getFollowAccountIds()) != null && followAccountIds.contains(str2)) {
            return false;
        }
        if (a2 == TeamMessageNotifyTypeEnum.Mute) {
            return true;
        }
        return (a2 != TeamMessageNotifyTypeEnum.Manager || (memberType = SuperTeamDBHelper.getMemberType(str, str2)) == TeamMemberType.Manager || memberType == TeamMemberType.Owner) ? false : true;
    }
}
