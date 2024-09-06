package com.netease.nimlib.sdk.team;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.constant.TeamMessageNotifyTypeEnum;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.CreateTeamResult;
import com.netease.nimlib.sdk.team.model.NIMTeamMemberRoleTypeSearchOption;
import com.netease.nimlib.sdk.team.model.NIMTeamMemberSearchResult;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamInfoResult;
import com.netease.nimlib.sdk.team.model.TeamMember;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public interface TeamService {
    InvocationFuture<Void> acceptInvite(String str, String str2);

    InvocationFuture<List<TeamMember>> addManagers(String str, List<String> list);

    InvocationFuture<List<String>> addMembers(String str, List<String> list);

    InvocationFuture<List<String>> addMembersEx(String str, List<String> list, String str2, String str3);

    InvocationFuture<Void> addTeamMembersFollow(String str, List<String> list);

    InvocationFuture<Team> applyJoinTeam(String str, String str2);

    InvocationFuture<CreateTeamResult> createTeam(Map<TeamFieldEnum, Serializable> map, TeamTypeEnum teamTypeEnum, String str, List<String> list);

    InvocationFuture<CreateTeamResult> createTeam(Map<TeamFieldEnum, Serializable> map, TeamTypeEnum teamTypeEnum, String str, List<String> list, AntiSpamConfig antiSpamConfig);

    InvocationFuture<Void> declineInvite(String str, String str2, String str3);

    InvocationFuture<Void> dismissTeam(String str);

    InvocationFuture<TeamMsgAckInfo> fetchTeamMessageReceiptDetail(IMMessage iMMessage);

    InvocationFuture<TeamMsgAckInfo> fetchTeamMessageReceiptDetail(IMMessage iMMessage, Set<String> set);

    InvocationFuture<Map<String, String>> getMemberInvitor(String str, List<String> list);

    InvocationFuture<NIMTeamMemberSearchResult> getTeamMemberList(String str, NIMTeamMemberRoleTypeSearchOption nIMTeamMemberRoleTypeSearchOption);

    InvocationFuture<Void> muteAllTeamMember(String str, boolean z);

    InvocationFuture<Void> muteTeam(String str, TeamMessageNotifyTypeEnum teamMessageNotifyTypeEnum);

    InvocationFuture<Void> muteTeamMember(String str, String str2, boolean z);

    InvocationFuture<Void> passApply(String str, String str2);

    InvocationFuture<List<TeamMember>> queryMemberList(String str);

    List<TeamMember> queryMutedTeamMembers(String str);

    InvocationFuture<Team> queryTeam(String str);

    Team queryTeamBlock(String str);

    int queryTeamCountBlock();

    int queryTeamCountByTypeBlock(TeamTypeEnum teamTypeEnum);

    InvocationFuture<List<Team>> queryTeamList();

    List<Team> queryTeamListBlock();

    InvocationFuture<List<Team>> queryTeamListById(List<String> list);

    List<Team> queryTeamListByIdBlock(List<String> list);

    InvocationFuture<List<Team>> queryTeamListByType(TeamTypeEnum teamTypeEnum);

    List<Team> queryTeamListByTypeBlock(TeamTypeEnum teamTypeEnum);

    InvocationFuture<TeamMember> queryTeamMember(String str, String str2);

    TeamMember queryTeamMemberBlock(String str, String str2);

    TeamMsgAckInfo queryTeamMessageReceiptDetailBlock(IMMessage iMMessage);

    TeamMsgAckInfo queryTeamMessageReceiptDetailBlock(IMMessage iMMessage, Set<String> set);

    InvocationFuture<Void> quitTeam(String str);

    void refreshTeamMessageReceipt(List<IMMessage> list);

    InvocationFuture<Void> rejectApply(String str, String str2, String str3);

    InvocationFuture<List<TeamMember>> removeManagers(String str, List<String> list);

    InvocationFuture<Void> removeMember(String str, String str2);

    InvocationFuture<Void> removeMembers(String str, List<String> list);

    InvocationFuture<Void> removeTeamMembersFollow(String str, List<String> list);

    InvocationFuture<Team> searchTeam(String str);

    InvocationFuture<TeamInfoResult> searchTeam(List<Long> list);

    InvocationFuture<List<String>> searchTeamIdByName(String str);

    InvocationFuture<List<IMMessage>> searchTeamMsgByKeyword(long j, long j2, long j3, String str, int i, boolean z);

    InvocationFuture<List<Team>> searchTeamsByKeyword(String str);

    InvocationFuture<Void> sendTeamMessageReceipt(IMMessage iMMessage);

    InvocationFuture<List<TeamMember>> transferTeam(String str, String str2, boolean z);

    InvocationFuture<Void> updateMemberNick(String str, String str2, String str3);

    InvocationFuture<Void> updateMyMemberExtension(String str, Map<String, Object> map);

    InvocationFuture<Void> updateMyTeamNick(String str, String str2);

    InvocationFuture<Void> updateName(String str, String str2);

    InvocationFuture<Void> updateTeam(String str, TeamFieldEnum teamFieldEnum, Serializable serializable);

    InvocationFuture<Void> updateTeamFields(String str, Map<TeamFieldEnum, Serializable> map);

    InvocationFuture<Void> updateTeamFields(String str, Map<TeamFieldEnum, Serializable> map, AntiSpamConfig antiSpamConfig);
}
