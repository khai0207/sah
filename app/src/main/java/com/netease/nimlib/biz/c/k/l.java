package com.netease.nimlib.biz.c.k;

import android.os.SystemClock;
import android.text.TextUtils;
import com.netease.nimlib.biz.e.l.t;
import com.netease.nimlib.biz.e.l.u;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.NotificationType;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.model.LeaveTeamAttachment;
import com.netease.nimlib.sdk.team.model.MemberChangeAttachment;
import com.netease.nimlib.sdk.team.model.MuteMemberAttachment;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.j;
import com.netease.nimlib.team.TeamDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: TeamTalkNotifyHandler.java */
/* loaded from: classes.dex */
public class l extends com.netease.nimlib.biz.c.i {
    @Override // com.netease.nimlib.biz.c.a
    public void a(com.netease.nimlib.biz.e.a aVar) {
        List<com.netease.nimlib.push.packet.b.c> a;
        j.a aVar2;
        int d;
        long p = aVar.j() != null ? aVar.j().p() : 0L;
        int s = aVar.s();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (aVar instanceof u) {
            a = new ArrayList<>();
            a.add(((u) aVar).a());
        } else if (!(aVar instanceof t)) {
            return;
        } else {
            a = ((t) aVar).a();
        }
        ArrayList arrayList = null;
        int i = 1;
        if (com.netease.nimlib.c.i().enableTeamMsgAck && aVar.j().j() == 102) {
            arrayList = new ArrayList(a.size());
            for (com.netease.nimlib.push.packet.b.c cVar : a) {
                if (cVar.f(112) && (d = cVar.d(112)) >= 0) {
                    arrayList.add(new TeamMessageReceipt(new TeamMsgAckInfo(cVar.c(i), cVar.c(11), 0, d)));
                }
                i = 1;
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        Iterator<com.netease.nimlib.push.packet.b.c> it = a.iterator();
        while (it.hasNext()) {
            IMMessageImpl b = com.netease.nimlib.session.g.b(it.next(), false);
            if (b == null) {
                com.netease.nimlib.log.b.N("TeamTalkNotifyHandler toMessage null");
            } else {
                arrayList3.add(b);
            }
        }
        if (arrayList3.isEmpty()) {
            return;
        }
        com.netease.nimlib.session.a.c.a().a((IMMessageImpl) arrayList3.get(arrayList3.size() - 1));
        j.a a2 = com.netease.nimlib.session.j.a(arrayList3, com.netease.nimlib.session.j.g(arrayList3));
        MsgDBHelper.saveMessages(a2.b);
        com.netease.nimlib.session.j.b(a);
        Iterator it2 = arrayList3.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            IMMessageImpl iMMessageImpl = (IMMessageImpl) it2.next();
            if (iMMessageImpl.getMsgType() == MsgTypeEnum.notification) {
                com.netease.nimlib.session.j.i(iMMessageImpl);
                i(iMMessageImpl);
                z |= a(iMMessageImpl);
            }
        }
        if (a2.a()) {
            String a3 = a(a);
            Iterator<IMMessageImpl> it3 = a2.b.iterator();
            while (it3.hasNext()) {
                com.netease.nimlib.n.f.a().a(it3.next(), p, s, elapsedRealtime);
                a3 = a3;
                a2 = a2;
            }
            aVar2 = a2;
            com.netease.nimlib.i.b.a(com.netease.nimlib.session.g.a((ArrayList<IMMessageImpl>) aVar2.b, a3));
        } else {
            aVar2 = a2;
        }
        if (aVar2.b()) {
            com.netease.nimlib.n.f.a().a(aVar2.a, p, s, elapsedRealtime);
            com.netease.nimlib.session.j.f(aVar2.a);
        }
        if (z) {
            com.netease.nimlib.i.b.b(TeamDBHelper.queryTeam(((IMMessageImpl) arrayList3.get(0)).getSessionId()));
        }
        if (arrayList2 == null || arrayList2.isEmpty()) {
            return;
        }
        com.netease.nimlib.team.h.c().e(arrayList2);
        com.netease.nimlib.i.b.d(arrayList2);
    }

    protected String a(List<com.netease.nimlib.push.packet.b.c> list) {
        Iterator<com.netease.nimlib.push.packet.b.c> it = list.iterator();
        String str = null;
        while (it.hasNext()) {
            str = it.next().c(6);
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        return str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected boolean a(IMMessageImpl iMMessageImpl) {
        NotificationAttachment notificationAttachment = (NotificationAttachment) iMMessageImpl.getAttachment();
        if (notificationAttachment == null) {
            return false;
        }
        switch (AnonymousClass1.a[notificationAttachment.getType().ordinal()]) {
            case 1:
            case 2:
                c(iMMessageImpl);
                return false;
            case 3:
                return d(iMMessageImpl);
            case 4:
                b(iMMessageImpl);
                return true;
            case 5:
                e(iMMessageImpl);
                return false;
            case 6:
                f(iMMessageImpl);
                return false;
            case 7:
                h(iMMessageImpl);
                return false;
            case 8:
                g(iMMessageImpl);
                return false;
            default:
                return false;
        }
    }

    /* compiled from: TeamTalkNotifyHandler.java */
    /* renamed from: com.netease.nimlib.biz.c.k.l$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationType.values().length];
            a = iArr;
            try {
                iArr[NotificationType.InviteMember.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationType.PassTeamApply.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationType.KickMember.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[NotificationType.DismissTeam.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[NotificationType.UpdateTeam.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[NotificationType.LeaveTeam.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[NotificationType.TransferOwner.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[NotificationType.AcceptInvite.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[NotificationType.AddTeamManager.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[NotificationType.RemoveTeamManager.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    protected void b(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.team.c.a(iMMessageImpl.getSessionId(), true, false);
    }

    protected void c(IMMessageImpl iMMessageImpl) {
        try {
            com.netease.nimlib.team.d a = com.netease.nimlib.team.d.a(com.netease.nimlib.team.c.b(new JSONObject(iMMessageImpl.getAttachStr(false)).getJSONObject("data").getJSONObject("tinfo")));
            a.f(1);
            a.b(iMMessageImpl.getTime());
            com.netease.nimlib.team.c.a(a);
        } catch (Exception e) {
            com.netease.nimlib.log.b.d("team", "save team info by notify error: " + e.getMessage());
        }
    }

    protected boolean d(IMMessageImpl iMMessageImpl) {
        boolean z;
        String sessionId = iMMessageImpl.getSessionId();
        MemberChangeAttachment memberChangeAttachment = (MemberChangeAttachment) iMMessageImpl.getAttachment();
        Iterator<String> it = memberChangeAttachment.getTargets().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (it.next().equals(com.netease.nimlib.c.n())) {
                z = true;
                break;
            }
        }
        if (z) {
            com.netease.nimlib.team.c.a(sessionId, false, false);
            return true;
        }
        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(sessionId);
        if (queryTeam != null) {
            queryTeam.d(queryTeam.getMemberCount() - memberChangeAttachment.getTargets().size());
            queryTeam.b(iMMessageImpl.getTime());
            com.netease.nimlib.team.c.a(queryTeam);
        }
        return false;
    }

    protected void e(IMMessageImpl iMMessageImpl) {
        try {
            com.netease.nimlib.team.c.a(iMMessageImpl.getSessionId(), com.netease.nimlib.team.c.b(new JSONObject(iMMessageImpl.getAttachStr(false)).getJSONObject("data").getJSONObject("tinfo")));
        } catch (Exception e) {
            com.netease.nimlib.log.b.d("team", "update team info by notify error: " + e.getMessage());
        }
    }

    protected void f(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(iMMessageImpl.getSessionId());
        if (queryTeam != null) {
            queryTeam.d(queryTeam.getMemberCount() - 1);
            queryTeam.b(iMMessageImpl.getTime());
            if (iMMessageImpl.getFromAccount().equals(com.netease.nimlib.c.n())) {
                queryTeam.f(0);
            }
            com.netease.nimlib.team.c.a(queryTeam);
        }
    }

    protected void g(IMMessageImpl iMMessageImpl) {
        if (!iMMessageImpl.getFromAccount().equals(com.netease.nimlib.c.n())) {
            com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(iMMessageImpl.getSessionId());
            if (queryTeam != null) {
                queryTeam.d(queryTeam.getMemberCount() + 1);
                queryTeam.b(iMMessageImpl.getTime());
                com.netease.nimlib.team.c.a(queryTeam);
                return;
            }
            return;
        }
        c(iMMessageImpl);
    }

    protected void h(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.team.d queryTeam = TeamDBHelper.queryTeam(iMMessageImpl.getSessionId());
        if (queryTeam == null || iMMessageImpl.getAttachment() == null) {
            return;
        }
        queryTeam.c(((MemberChangeAttachment) iMMessageImpl.getAttachment()).getTargets().get(0));
        queryTeam.b(iMMessageImpl.getTime());
        queryTeam.f(1);
        com.netease.nimlib.team.c.a(queryTeam);
    }

    protected void i(IMMessageImpl iMMessageImpl) {
        String sessionId = iMMessageImpl.getSessionId();
        long time = iMMessageImpl.getTime();
        if (iMMessageImpl.getAttachment() instanceof LeaveTeamAttachment) {
            TeamDBHelper.updateTeamMemberTimeTag(iMMessageImpl.getSessionId(), iMMessageImpl.getTime());
            com.netease.nimlib.team.c.a(iMMessageImpl.getSessionId(), iMMessageImpl.getFromAccount());
            return;
        }
        if (iMMessageImpl.getAttachment() instanceof MuteMemberAttachment) {
            MuteMemberAttachment muteMemberAttachment = (MuteMemberAttachment) iMMessageImpl.getAttachment();
            String str = muteMemberAttachment.getTargets().get(0);
            TeamDBHelper.updateTeamMemberTimeTag(iMMessageImpl.getSessionId(), iMMessageImpl.getTime());
            com.netease.nimlib.team.c.a(sessionId, str, muteMemberAttachment.isMute());
            return;
        }
        if (iMMessageImpl.getAttachment() instanceof MemberChangeAttachment) {
            TeamDBHelper.updateTeamMemberTimeTag(iMMessageImpl.getSessionId(), iMMessageImpl.getTime());
            MemberChangeAttachment memberChangeAttachment = (MemberChangeAttachment) iMMessageImpl.getAttachment();
            int i = AnonymousClass1.a[memberChangeAttachment.getType().ordinal()];
            if (i == 1 || i == 2) {
                a(sessionId, time, memberChangeAttachment, iMMessageImpl.getFromAccount());
                return;
            }
            if (i != 3) {
                switch (i) {
                    case 7:
                        a(sessionId, iMMessageImpl.getFromAccount(), TeamMemberType.Normal);
                        a(sessionId, memberChangeAttachment.getTargets().get(0), TeamMemberType.Owner);
                        return;
                    case 8:
                        ArrayList<String> targets = memberChangeAttachment.getTargets();
                        b(sessionId, time, iMMessageImpl.getFromAccount(), (targets == null || targets.size() <= 0) ? null : targets.get(0));
                        return;
                    case 9:
                        a(sessionId, memberChangeAttachment.getTargets(), TeamMemberType.Manager);
                        return;
                    case 10:
                        a(sessionId, memberChangeAttachment.getTargets(), TeamMemberType.Normal);
                        return;
                    default:
                        return;
                }
            }
            a(sessionId, memberChangeAttachment.getTargets());
        }
    }

    protected void a(String str, ArrayList<String> arrayList) {
        com.netease.nimlib.team.c.a(str, arrayList);
    }

    private void b(String str, long j, String str2, String str3) {
        com.netease.nimlib.team.c.a(a(str, j, str2, str3));
    }

    private void a(String str, long j, MemberChangeAttachment memberChangeAttachment, String str2) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = memberChangeAttachment.getTargets().iterator();
        while (it.hasNext()) {
            arrayList.add(a(str, j, it.next(), str2));
        }
        com.netease.nimlib.team.c.a((ArrayList<com.netease.nimlib.team.g>) arrayList);
    }

    protected com.netease.nimlib.team.g a(String str, long j, String str2, String str3) {
        com.netease.nimlib.team.g gVar = new com.netease.nimlib.team.g();
        gVar.a(str);
        gVar.b(str2);
        gVar.a(TeamMemberType.Normal);
        gVar.b(1);
        gVar.b(j);
        gVar.d(str3);
        if (str2.equals(com.netease.nimlib.c.n())) {
            com.netease.nimlib.biz.d.k.h hVar = new com.netease.nimlib.biz.d.k.h();
            hVar.a(str);
            hVar.a(com.netease.nimlib.biz.l.d(str));
            com.netease.nimlib.biz.i.a().a(hVar);
        }
        return gVar;
    }

    private void a(String str, String str2, TeamMemberType teamMemberType) {
        com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, str2);
        if (queryTeamMember != null) {
            queryTeamMember.a(teamMemberType);
            com.netease.nimlib.team.c.a(queryTeamMember);
        }
    }

    private void a(String str, List<String> list, TeamMemberType teamMemberType) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            com.netease.nimlib.team.g queryTeamMember = TeamDBHelper.queryTeamMember(str, it.next());
            if (queryTeamMember != null) {
                queryTeamMember.a(teamMemberType);
                arrayList.add(queryTeamMember);
            }
        }
        com.netease.nimlib.team.c.a((ArrayList<com.netease.nimlib.team.g>) arrayList);
    }
}
