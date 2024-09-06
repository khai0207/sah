package com.netease.nimlib.team;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.MsgDBHelperB;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class TeamDBHelper {
    private static final String MEMBER_COLUMNS = "tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids";
    private static final String TAG = "TeamDBHelper";
    private static final String TEAM_COLUMNS = "id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute";

    public static void saveTeam(d dVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(dVar);
        saveTeams(arrayList);
    }

    public static void saveTeams(List<d> list) {
        saveTeams(list, com.netease.nimlib.database.c.b());
    }

    public static void saveTeams(List<d> list, String str) {
        String str2 = "INSERT OR REPLACE INTO " + str + " (" + TEAM_COLUMNS + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getId()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getName()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getCreator()));
            sb.append("','");
            sb.append(dVar.getType().getValue());
            sb.append("','");
            sb.append(dVar.a());
            sb.append("','");
            sb.append(dVar.b());
            sb.append("','");
            sb.append(dVar.getMemberCount());
            sb.append("','");
            sb.append(dVar.d());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getIntroduce()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getAnnouncement()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.f()));
            sb.append("','");
            sb.append(dVar.c());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getExtension()));
            sb.append("','");
            sb.append(dVar.getCreateTime());
            sb.append("','");
            sb.append(dVar.getVerifyType().getValue());
            sb.append("','");
            sb.append(dVar.e());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getExtServer()));
            sb.append("','");
            sb.append(dVar.g());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(dVar.getIcon()));
            sb.append("','");
            sb.append(dVar.getTeamBeInviteMode().getValue());
            sb.append("','");
            sb.append(dVar.getTeamInviteMode().getValue());
            sb.append("','");
            sb.append(dVar.getTeamUpdateMode().getValue());
            sb.append("','");
            sb.append(dVar.getTeamExtensionUpdateMode().getValue());
            sb.append("','");
            sb.append(dVar.getMuteMode().getValue());
            sb.append("'");
            if (sb.length() > 10000) {
                database().a(str2 + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            database().a(str2 + ((Object) sb));
        }
    }

    public static void deleteTeam(String str) {
        deleteTeam(str, com.netease.nimlib.database.c.b());
    }

    public static void deleteTeam(String str, String str2) {
        database().a("UPDATE " + str2 + " set valid_flag='0' where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        StringBuilder sb = new StringBuilder();
        sb.append("delete ");
        sb.append(str2);
        sb.append(" id = ");
        sb.append(str);
        com.netease.nimlib.log.b.d(TAG, sb.toString());
    }

    public static void clearAllTeams() {
        clearAllTeams(com.netease.nimlib.database.c.b());
    }

    public static void clearAllTeams(String str) {
        database().a("DELETE FROM " + str);
        com.netease.nimlib.log.b.d(TAG, "clear all teams");
    }

    public static void quitTeam(String str) {
        quitTeam(str, com.netease.nimlib.database.c.b());
    }

    public static void quitTeam(String str, String str2) {
        database().a("UPDATE " + str2 + " set member_flag='0' where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        StringBuilder sb = new StringBuilder();
        sb.append("quit team id = ");
        sb.append(str);
        com.netease.nimlib.log.b.d(TAG, sb.toString());
    }

    public static void updateTeamMemberTimeTag(String str, long j) {
        updateTeamMemberTimeTag(com.netease.nimlib.database.c.b(), str, j);
    }

    public static void updateTeamMemberTimeTag(String str, String str2, long j) {
        database().a("UPDATE " + str + " set member_tt='" + j + "' where id='" + com.netease.nimlib.database.a.c.a(str2) + "'");
    }

    public static ArrayList<Team> queryAllTeams() {
        return queryAllTeams(com.netease.nimlib.database.c.b());
    }

    public static ArrayList<Team> queryAllTeams(String str) {
        ArrayList<Team> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from " + str + " where valid_flag='1' and member_flag='1'");
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(teamFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public static List<String> queryAllTeamId() {
        return queryAllTeamId(com.netease.nimlib.database.c.b());
    }

    public static List<String> queryAllTeamId(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor b = database().b("SELECT id from " + str);
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(b.getString(0));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public static ArrayList<Team> queryTeamListByType(TeamTypeEnum teamTypeEnum) {
        ArrayList<Team> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from team where valid_flag='1' and member_flag='1' and type='" + teamTypeEnum.getValue() + "'");
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(teamFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public static int queryTeamCount() {
        Cursor b = database().b("SELECT COUNT(*) from team where valid_flag='1' and member_flag='1'");
        if (b != null) {
            r1 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r1;
    }

    public static int queryTeamCountByType(TeamTypeEnum teamTypeEnum) {
        Cursor b = database().b("SELECT COUNT(*) from team where valid_flag='1' and member_flag='1' and type='" + teamTypeEnum.getValue() + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static d queryTeam(String str) {
        return queryTeam(str, com.netease.nimlib.database.c.b());
    }

    public static d queryTeam(String str, String str2) {
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from " + str2 + " where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? teamFromCursor(b) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r3;
    }

    public static String queryTeamName(String str) {
        Cursor b = database().b("SELECT name from team where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getString(0) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static int queryTeamType(String str) {
        Cursor b = database().b("SELECT type from team where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static ArrayList<Team> queryTeamListById(List<String> list) {
        return queryTeamListById(list, com.netease.nimlib.database.c.b());
    }

    public static ArrayList<Team> queryTeamListById(List<String> list, String str) {
        ArrayList<Team> arrayList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(TEAM_COLUMNS);
            sb.append(" from ");
            sb.append(str);
            sb.append(" where id in ('");
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                sb.append(com.netease.nimlib.database.a.c.a(it.next()));
                sb.append("','");
            }
            sb.replace(sb.length() - 2, sb.length(), ")");
            Cursor b = database().b(sb.toString());
            if (b != null) {
                while (b.moveToNext()) {
                    arrayList.add(teamFromCursor(b));
                }
                if (!b.isClosed()) {
                    b.close();
                }
            }
        }
        return arrayList;
    }

    public static Set<String> getExistTeamIdById(Set<String> set) {
        HashSet hashSet = new HashSet();
        if (com.netease.nimlib.o.f.c((Collection) set)) {
            return hashSet;
        }
        int size = set.size();
        com.netease.nimlib.log.b.L("getExistTeamIdById idSet = " + set);
        if (size <= 200) {
            getExistTeamIdById(set, hashSet);
        } else {
            int i = size / 200;
            int i2 = size % 200;
            ArrayList arrayList = new ArrayList(set);
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i3 * 200;
                int i5 = i4 + 200;
                List subList = arrayList.subList(i4, i5);
                com.netease.nimlib.log.b.a("getExistTeamIdById for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
                getExistTeamIdById(subList, hashSet);
            }
            if (i2 > 0) {
                int i6 = i * 200;
                int i7 = i2 + i6;
                List subList2 = arrayList.subList(i6, i7);
                com.netease.nimlib.log.b.a("getExistTeamIdById lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
                getExistTeamIdById(subList2, hashSet);
            }
        }
        com.netease.nimlib.log.b.L("getExistTeamIdById result = " + hashSet);
        return hashSet;
    }

    private static Set<String> getExistTeamIdById(Collection<String> collection, Set<String> set) {
        if (collection != null && !collection.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT id from ");
            sb.append(com.netease.nimlib.database.c.b());
            sb.append(" where id in ('");
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                sb.append(com.netease.nimlib.database.a.c.a(it.next()));
                sb.append("','");
            }
            sb.replace(sb.length() - 2, sb.length(), ")");
            com.netease.nimlib.log.b.L("getExistTeamIdById sql = " + ((Object) sb));
            Cursor b = database().b(sb.toString());
            if (b != null) {
                while (b.moveToNext()) {
                    set.add(b.getString(0));
                }
                if (!b.isClosed()) {
                    b.close();
                }
            }
        }
        return set;
    }

    public static long queryMemberTimetag(String str) {
        Cursor b = database().b("SELECT member_tt from team where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getLong(0) : 0L;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static ArrayList<String> queryTeamIdByName(String str) {
        String str2 = "SELECT id from team where name='" + com.netease.nimlib.database.a.c.a(str) + "'";
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor b = database().b(str2);
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(b.getString(0));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    private static d teamFromCursor(Cursor cursor) {
        d dVar = new d();
        dVar.a(cursor.getString(0));
        dVar.b(cursor.getString(1));
        dVar.c(cursor.getString(2));
        dVar.a(cursor.getInt(3));
        dVar.b(cursor.getInt(4));
        dVar.c(cursor.getInt(5));
        dVar.d(cursor.getInt(6));
        dVar.b(cursor.getLong(7));
        dVar.d(cursor.getString(8));
        dVar.e(cursor.getString(9));
        dVar.f(cursor.getString(10));
        dVar.a(cursor.getLong(11));
        dVar.setExtension(cursor.getString(12));
        dVar.c(cursor.getLong(13));
        dVar.e(cursor.getInt(14));
        dVar.f(cursor.getInt(15));
        dVar.g(cursor.getString(16));
        dVar.d(cursor.getLong(17));
        dVar.h(cursor.getString(18));
        dVar.h(cursor.getInt(19));
        dVar.g(cursor.getInt(20));
        dVar.i(cursor.getInt(21));
        dVar.j(cursor.getInt(22));
        dVar.k(cursor.getInt(23));
        d.a(dVar, getMemberBits(dVar.getId()));
        return dVar;
    }

    public static Set<String> queryMemberAccountList(String str) {
        HashSet hashSet = new HashSet();
        Cursor b = database().b("SELECT account from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' ORDER BY join_time ASC");
        if (b != null) {
            while (b.moveToNext()) {
                hashSet.add(b.getString(0));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        com.netease.nimlib.log.b.d(TAG, "query member account list , tid = " + str + " , account size = " + hashSet.size());
        return hashSet;
    }

    public static ArrayList<g> queryMemberList(String str) {
        ArrayList<g> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' ORDER BY join_time ASC");
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(memberFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        com.netease.nimlib.log.b.d(TAG, "query member list , tid = " + str + " , member size = " + arrayList.size());
        return arrayList;
    }

    public static int queryMemberCount(String str) {
        Cursor b = database().b("SELECT COUNT(*) from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static ArrayList<g> queryMemberListByAccids(String str, List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList<g> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' and account in (");
        boolean z = true;
        for (String str2 : list) {
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append("'");
            sb.append(com.netease.nimlib.database.a.c.a(str2));
            sb.append("'");
        }
        sb.append(")");
        Cursor b = database().b(sb.toString());
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(memberFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        com.netease.nimlib.log.b.d(TAG, "query member list , tid = " + str + " , member size = " + arrayList.size());
        return arrayList;
    }

    public static g queryTeamMember(String str, String str2) {
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? memberFromCursor(b) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r3;
    }

    public static List<g> queryMemberListByServerTeamMembers(List<g> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            int size = list.size();
            com.netease.nimlib.log.b.L("queryMemberListByServerTeamMembers memberList.size = " + size);
            if (size <= 100) {
                queryMemberListByServerTeamMembers(list, arrayList);
            } else {
                int i = size / 100;
                int i2 = size % 100;
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = i3 * 100;
                    int i5 = i4 + 100;
                    List<g> subList = list.subList(i4, i5);
                    com.netease.nimlib.log.b.a("queryMemberListByServerTeamMembers for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
                    queryMemberListByServerTeamMembers(subList, arrayList);
                }
                if (i2 > 0) {
                    int i6 = i * 100;
                    int i7 = i2 + i6;
                    List<g> subList2 = list.subList(i6, i7);
                    com.netease.nimlib.log.b.a("queryMemberListByServerTeamMembers lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
                    queryMemberListByServerTeamMembers(subList2, arrayList);
                }
            }
            com.netease.nimlib.log.b.d(TAG, "queryMemberListByServerTeamMembers , result size = " + arrayList.size());
        }
        return arrayList;
    }

    private static List<g> queryMemberListByServerTeamMembers(List<g> list, List<g> list2) {
        if (list != null && list.size() != 0) {
            String[] strArr = new String[list.size() * 2];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    sb.append(" OR ");
                }
                sb.append("(tid = ? AND account = ?)");
                g gVar = list.get(i);
                int i2 = i * 2;
                strArr[i2] = gVar.getTid();
                strArr[i2 + 1] = gVar.getAccount();
            }
            String str = "SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from tuser where " + ((CharSequence) sb);
            com.netease.nimlib.log.b.L("queryMemberListByServerTeamMembers sql = " + str);
            Cursor a = database().a(str, strArr);
            if (a != null) {
                while (a.moveToNext()) {
                    list2.add(memberFromCursor(a));
                }
                if (!a.isClosed()) {
                    a.close();
                }
            }
            com.netease.nimlib.log.b.a("queryMemberListByServerTeamMembers result", list2);
        }
        return list2;
    }

    public static ArrayList<TeamMember> queryMutedMemberList(String str) {
        ArrayList<TeamMember> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' and mute='1'");
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(memberFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public static void saveTeamMember(g gVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(gVar);
        saveTeamMembers(arrayList);
    }

    public static void saveTeamMembers(List<g> list) {
        if (MsgDBHelper.abTestSelected) {
            com.netease.nimlib.log.b.d(TAG, "saveTeamMembersB abTestSelected: " + MsgDBHelper.abTestSelected);
            saveTeamMembersB(list);
            return;
        }
        com.netease.nimlib.log.b.d(TAG, "saveTeamMembersA abTestSelected: " + MsgDBHelper.abTestSelected);
        saveTeamMembersA(list);
    }

    public static void saveTeamMembersA(List<g> list) {
        StringBuilder sb = new StringBuilder();
        for (g gVar : list) {
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(gVar.getTid()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(gVar.getAccount()));
            sb.append("','");
            sb.append(gVar.getType().getValue());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(gVar.getTeamNick()));
            sb.append("','");
            sb.append(gVar.a());
            sb.append("','");
            sb.append(gVar.getJoinTime());
            sb.append("','");
            sb.append(gVar.b());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(gVar.d()));
            sb.append("','");
            sb.append(gVar.isMute() ? 1 : 0);
            sb.append("','");
            sb.append(gVar.getInvitorAccid());
            sb.append("','");
            sb.append(gVar.c());
            sb.append("'");
            if (sb.length() > 10000) {
                database().a("INSERT OR REPLACE INTO tuser (tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids)" + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            database().a("INSERT OR REPLACE INTO tuser (tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids)" + ((Object) sb));
        }
        com.netease.nimlib.log.b.d(TAG, "save team members: count=" + list.size());
    }

    public static void saveTeamMembersB(List<g> list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 200;
            insertBatchTeamMembers(list.subList(i, Math.min(i2, size)));
            i = i2;
        }
        com.netease.nimlib.log.b.d(TAG, "save team members: count=" + list.size());
    }

    private static void insertBatchTeamMembers(List<g> list) {
        try {
            try {
                database().f();
                for (g gVar : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("tid", gVar.getTid());
                    contentValues.put("account", gVar.getAccount());
                    contentValues.put("type", Integer.valueOf(gVar.getType().getValue()));
                    contentValues.put("nick", gVar.getTeamNick());
                    contentValues.put("bits", Long.valueOf(gVar.a()));
                    contentValues.put("join_time", Long.valueOf(gVar.getJoinTime()));
                    contentValues.put("valid", Integer.valueOf(gVar.b()));
                    contentValues.put("custom", gVar.d());
                    contentValues.put("mute", Integer.valueOf(gVar.isMute() ? 1 : 0));
                    contentValues.put("invitor_accid", gVar.getInvitorAccid());
                    contentValues.put("follow_account_ids", gVar.c());
                    database().b("tuser", null, contentValues);
                }
                database().h();
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e(TAG, "insertBatchTeamMembers " + com.netease.nimlib.o.f.f(list), e);
            }
        } finally {
            database().g();
        }
    }

    public static long getMemberBits(String str) {
        Cursor b = database().b("SELECT bits FROM tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(com.netease.nimlib.c.n()) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getLong(0) : 0L;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static TeamMemberType getMemberType(String str, String str2) {
        Cursor b = database().b("SELECT type FROM tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return TeamMemberType.typeOfValue(r3);
    }

    public static void clearTeamMembers(String str) {
        l.a(str, 0L);
        database().a("update tuser set valid='0' where tid='" + com.netease.nimlib.database.a.c.a(str) + "'");
        StringBuilder sb = new StringBuilder();
        sb.append("clear team  member, tid is ");
        sb.append(str);
        com.netease.nimlib.log.b.d(TAG, sb.toString());
    }

    public static int clearTeamMembers(boolean z, List<String> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tuser WHERE ");
        if (!z) {
            sb.append("account!='");
            sb.append(com.netease.nimlib.database.a.c.a(com.netease.nimlib.c.n()));
            sb.append("' AND ");
        }
        sb.append("tid in ('");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(com.netease.nimlib.database.a.c.a(it.next()));
            sb.append("','");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        String n = com.netease.nimlib.c.n();
        String j = database().j();
        if (n == null || !n.equals(j)) {
            com.netease.nimlib.log.b.d(TAG, "clearTeamMembers, account not match, db uid=" + j + ", cache uid=" + n);
            return -1;
        }
        com.netease.nimlib.log.b.d(TAG, "clearTeamMembers, account match, db uid=" + j + ", cache uid=" + n);
        database().a(sb.toString());
        com.netease.nimlib.log.b.d(TAG, String.format("clear team members, tidList: %s", com.netease.nimlib.o.f.f(list)));
        return 0;
    }

    public static void deleteTeamMember(String str, String str2) {
        database().a("update tuser set valid='0' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        com.netease.nimlib.log.b.d(TAG, String.format("delete team member tid: %s, account: %s", str, str2));
    }

    public static void deleteTeamMemberDirect(String str, List<g> list) {
        String str2 = "DELETE FROM tuser WHERE tid='" + com.netease.nimlib.database.a.c.a(str) + "' AND (";
        StringBuilder sb = new StringBuilder();
        for (g gVar : list) {
            if (sb.length() != 0) {
                sb.append(" OR account='");
            } else {
                sb.append(" account='");
            }
            sb.append(com.netease.nimlib.database.a.c.a(gVar.getAccount()) + "'");
            if (sb.length() > 10000) {
                sb.append(")");
                database().a(str2 + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            sb.append(")");
            database().a(str2 + ((Object) sb));
        }
        com.netease.nimlib.log.b.d(TAG, String.format("delete team member direct tid: %s, memberList: %s", str, com.netease.nimlib.o.f.f(list)));
    }

    public static void refreshAllTeamMembers(String str, List<g> list, List<g> list2) {
        database().f();
        try {
            saveTeamMembers(list);
            deleteTeamMemberDirect(str, list2);
            database().h();
        } catch (Throwable unused) {
        }
        database().g();
    }

    public static void muteTeamMember(String str, String str2, boolean z) {
        database().a("update tuser set mute='" + (z ? 1 : 0) + "' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        com.netease.nimlib.log.b.d(TAG, String.format("mute team member, tid=%s, account=%s, mute=%s", str, str2, Boolean.valueOf(z)));
    }

    public static void updateTeamMemberInvitor(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        database().f();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                database().a("update tuser set invitor_accid='" + com.netease.nimlib.database.a.c.a(entry.getValue()) + "' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(entry.getKey()) + "'");
            }
            database().h();
        } catch (Throwable unused) {
        }
        database().g();
    }

    private static g memberFromCursor(Cursor cursor) {
        g gVar = new g();
        gVar.a(cursor.getString(0));
        gVar.b(cursor.getString(1));
        gVar.a(cursor.getInt(2));
        gVar.c(cursor.getString(3));
        gVar.a(cursor.getLong(4));
        gVar.b(cursor.getLong(5));
        gVar.b(cursor.getInt(6));
        gVar.f(cursor.getString(7));
        gVar.c(cursor.getInt(8));
        gVar.d(cursor.getString(9));
        gVar.e(cursor.getString(10));
        return gVar;
    }

    private static final com.netease.nimlib.database.b database() {
        return com.netease.nimlib.database.f.a().f();
    }

    public static List<Team> searchTeamsByKeyword(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from " + com.netease.nimlib.database.c.b() + " where valid_flag='1' and member_flag='1' and name like " + com.netease.nimlib.database.a.c.b(str));
        if (b != null) {
            while (b.moveToNext()) {
                arrayList.add(teamFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0104, code lost:
    
        if (r8.isClosed() != false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.netease.nimlib.sdk.team.model.TeamMember> queryMemberListByTypes(java.lang.String r7, java.util.Set<com.netease.nimlib.sdk.team.constant.TeamMemberType> r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.team.TeamDBHelper.queryMemberListByTypes(java.lang.String, java.util.Set, int, int, boolean):java.util.List");
    }

    public static String getTeamMemberJsonStringByAccids(String str, List<String> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return new JSONArray().toString();
        }
        String str2 = "SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids FROM tuser WHERE tid = ? AND account IN (" + MsgDBHelperB.repeatPlaceholders(list.size()) + ")";
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.addAll(list);
        Cursor a = database().a(str2, (String[]) arrayList.toArray(new String[0]));
        if (a == null) {
            return new JSONArray().toString();
        }
        JSONArray fromCursorToJsonArray = MsgDBHelper.fromCursorToJsonArray(a);
        a.close();
        return fromCursorToJsonArray.toString();
    }

    public static boolean isMyTeam(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor b = database().b("SELECT COUNT(*) from " + com.netease.nimlib.database.c.b() + " where id='" + com.netease.nimlib.database.a.c.a(str) + "' and valid_flag='1' and member_flag='1'");
        if (b != null) {
            i = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        } else {
            i = 0;
        }
        return i > 0;
    }
}
