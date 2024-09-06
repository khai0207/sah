package com.netease.nimlib.superteam;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.superteam.SuperTeam;
import com.netease.nimlib.sdk.superteam.SuperTeamMember;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class SuperTeamDBHelper {
    private static final String MEMBER_COLUMNS = "tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids";
    private static final String TAG = "SuperTeamDBHelper";
    private static final String TEAM_COLUMNS = "id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute";

    public static void saveSuperTeam(b bVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bVar);
        saveTeams(arrayList);
    }

    public static void saveTeams(List<b> list) {
        saveTeams(list, com.netease.nimlib.database.c.a());
    }

    public static void saveTeams(List<b> list, String str) {
        String str2 = "INSERT OR REPLACE INTO " + str + " (" + TEAM_COLUMNS + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            b bVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getId()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getName()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getCreator()));
            sb.append("','");
            sb.append(bVar.getType().getValue());
            sb.append("','");
            sb.append(bVar.a());
            sb.append("','");
            sb.append(bVar.b());
            sb.append("','");
            sb.append(bVar.getMemberCount());
            sb.append("','");
            sb.append(bVar.d());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getIntroduce()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getAnnouncement()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.f()));
            sb.append("','");
            sb.append(bVar.c());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getExtension()));
            sb.append("','");
            sb.append(bVar.getCreateTime());
            sb.append("','");
            sb.append(bVar.getVerifyType().getValue());
            sb.append("','");
            sb.append(bVar.e());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getExtServer()));
            sb.append("','");
            sb.append(bVar.g());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getIcon()));
            sb.append("','");
            sb.append(bVar.getTeamBeInviteMode().getValue());
            sb.append("','");
            sb.append(bVar.getTeamInviteMode().getValue());
            sb.append("','");
            sb.append(bVar.getTeamUpdateMode().getValue());
            sb.append("','");
            sb.append(bVar.getTeamExtensionUpdateMode().getValue());
            sb.append("','");
            sb.append(bVar.getMuteMode().getValue());
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

    public static ArrayList<SuperTeam> queryAllSuperTeams() {
        return queryAllTeams(com.netease.nimlib.database.c.a());
    }

    public static b querySuperTeam(String str) {
        return queryTeam(str, com.netease.nimlib.database.c.a());
    }

    public static b queryTeam(String str, String str2) {
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from " + str2 + " where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? teamFromCursor(b) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r3;
    }

    public static void updateTeamMemberTimeTag(String str, long j) {
        updateTeamMemberTimeTag(com.netease.nimlib.database.c.a(), str, j);
    }

    public static void updateTeamMemberTimeTag(String str, String str2, long j) {
        database().a("UPDATE " + str + " set member_tt='" + j + "' where id='" + com.netease.nimlib.database.a.c.a(str2) + "'");
    }

    private static b teamFromCursor(Cursor cursor) {
        b bVar = new b();
        bVar.a(cursor.getString(0));
        bVar.b(cursor.getString(1));
        bVar.c(cursor.getString(2));
        bVar.a(cursor.getInt(3));
        bVar.b(cursor.getInt(4));
        bVar.c(cursor.getInt(5));
        bVar.d(cursor.getInt(6));
        bVar.b(cursor.getLong(7));
        bVar.d(cursor.getString(8));
        bVar.e(cursor.getString(9));
        bVar.f(cursor.getString(10));
        bVar.a(cursor.getLong(11));
        bVar.setExtension(cursor.getString(12));
        bVar.c(cursor.getLong(13));
        bVar.e(cursor.getInt(14));
        bVar.f(cursor.getInt(15));
        bVar.g(cursor.getString(16));
        bVar.d(cursor.getLong(17));
        bVar.h(cursor.getString(18));
        bVar.h(cursor.getInt(19));
        bVar.g(cursor.getInt(20));
        bVar.i(cursor.getInt(21));
        bVar.j(cursor.getInt(22));
        bVar.k(cursor.getInt(23));
        b.a(bVar, getMemberBits(bVar.getId()));
        return bVar;
    }

    public static ArrayList<SuperTeam> queryAllTeams() {
        return queryAllTeams(com.netease.nimlib.database.c.a());
    }

    public static ArrayList<SuperTeam> queryAllTeams(String str) {
        ArrayList<SuperTeam> arrayList = new ArrayList<>();
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

    public static String queryTeamName(String str) {
        Cursor b = database().b("SELECT name from " + com.netease.nimlib.database.c.a() + " where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getString(0) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static ArrayList<SuperTeam> querySuperTeamListById(List<String> list) {
        return queryTeamListById(list, com.netease.nimlib.database.c.a());
    }

    public static ArrayList<SuperTeam> queryTeamListById(List<String> list, String str) {
        ArrayList<SuperTeam> arrayList = new ArrayList<>();
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

    public static void deleteSuperTeam(String str) {
        deleteTeam(str, com.netease.nimlib.database.c.a());
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

    public static void quitSuperTeam(String str) {
        quitTeam(str, com.netease.nimlib.database.c.a());
    }

    public static void quitTeam(String str, String str2) {
        database().a("UPDATE " + str2 + " set member_flag='0' where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        StringBuilder sb = new StringBuilder();
        sb.append("quit super id = ");
        sb.append(str);
        com.netease.nimlib.log.b.d(TAG, sb.toString());
    }

    public static void clearAllSuperTeams() {
        clearAllTeams(com.netease.nimlib.database.c.a());
    }

    public static void clearAllTeams(String str) {
        database().a("DELETE FROM " + str);
        com.netease.nimlib.log.b.d(TAG, "clear all teams");
    }

    public static List<String> queryAllSuperTeamId() {
        return queryAllTeamId(com.netease.nimlib.database.c.a());
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

    public static long queryMemberTimetag(String str) {
        Cursor b = database().b("SELECT member_tt from " + com.netease.nimlib.database.c.a() + " where id='" + com.netease.nimlib.database.a.c.a(str) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getLong(0) : 0L;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static Set<String> queryMemberAccountList(String str) {
        HashSet hashSet = new HashSet();
        Cursor b = database().b("SELECT account from super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' ORDER BY join_time ASC");
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

    public static void clearTeamMembers(String str) {
        l.b(str, 0L);
        database().a("update super_tuser set valid='0' where tid='" + com.netease.nimlib.database.a.c.a(str) + "'");
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
        sb.append("DELETE FROM super_tuser WHERE ");
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
            com.netease.nimlib.log.b.d(TAG, "clearSuperTeamMembers, account not match, db uid=" + j + ", cache uid=" + n);
            return -1;
        }
        com.netease.nimlib.log.b.d(TAG, "clearSuperTeamMembers, account match, db uid=" + j + ", cache uid=" + n);
        database().a(sb.toString());
        com.netease.nimlib.log.b.d(TAG, String.format("clear super team members, tidList: %s", f.f(list)));
        return 0;
    }

    public static long getMemberBits(String str) {
        Cursor b = database().b("SELECT bits FROM super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(com.netease.nimlib.c.n()) + "'");
        if (b != null) {
            r0 = b.moveToNext() ? b.getLong(0) : 0L;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static List<c> queryMemberListByServerTeamMembers(List<c> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            int size = list.size();
            com.netease.nimlib.log.b.L("superTeam queryMemberListByServerTeamMembers memberList.size = " + size);
            if (size <= 100) {
                queryMemberListByServerTeamMembers(list, arrayList);
            } else {
                int i = size / 100;
                int i2 = size % 100;
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = i3 * 100;
                    int i5 = i4 + 100;
                    List<c> subList = list.subList(i4, i5);
                    com.netease.nimlib.log.b.a("superTeam queryMemberListByServerTeamMembers for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
                    queryMemberListByServerTeamMembers(subList, arrayList);
                }
                if (i2 > 0) {
                    int i6 = i * 100;
                    int i7 = i2 + i6;
                    List<c> subList2 = list.subList(i6, i7);
                    com.netease.nimlib.log.b.a("superTeam queryMemberListByServerTeamMembers lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
                    queryMemberListByServerTeamMembers(subList2, arrayList);
                }
            }
            com.netease.nimlib.log.b.d(TAG, "superTeam queryMemberListByServerTeamMembers , result size = " + arrayList.size());
        }
        return arrayList;
    }

    private static List<c> queryMemberListByServerTeamMembers(List<c> list, List<c> list2) {
        if (list != null && list.size() != 0) {
            String[] strArr = new String[list.size() * 2];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    sb.append(" OR ");
                }
                sb.append("(tid = ? AND account = ?)");
                c cVar = list.get(i);
                int i2 = i * 2;
                strArr[i2] = cVar.getTid();
                strArr[i2 + 1] = cVar.getAccount();
            }
            String str = "SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from super_tuser where " + ((CharSequence) sb);
            com.netease.nimlib.log.b.L("superTeam queryMemberListByServerTeamMembers sql = " + str);
            Cursor a = database().a(str, strArr);
            if (a != null) {
                while (a.moveToNext()) {
                    list2.add(memberFromCursor(a));
                }
                if (!a.isClosed()) {
                    a.close();
                }
            }
            com.netease.nimlib.log.b.a("superTeam queryMemberListByServerTeamMembers result", list2);
        }
        return list2;
    }

    public static c queryTeamMember(String str, String str2) {
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? memberFromCursor(b) : null;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r3;
    }

    public static ArrayList<c> queryTeamMembers(String str, ArrayList<String> arrayList) {
        ArrayList<c> arrayList2 = new ArrayList<>(arrayList != null ? arrayList.size() : 0);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(MEMBER_COLUMNS);
        sb.append(" from super_tuser where tid='");
        sb.append(com.netease.nimlib.database.a.c.a(str));
        sb.append("' and account in('");
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(com.netease.nimlib.database.a.c.a(it.next()));
            sb.append("','");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        Cursor b = database().b(sb.toString());
        if (b != null) {
            while (b.moveToNext()) {
                arrayList2.add(memberFromCursor(b));
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList2;
    }

    public static ArrayList<c> queryMemberList(String str) {
        ArrayList<c> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' ORDER BY join_time ASC");
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

    public static ArrayList<c> queryMemberList(String str, int i, int i2) {
        ArrayList<c> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' ORDER BY join_time ASC limit " + i2 + " offset " + i);
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

    public static ArrayList<SuperTeamMember> queryMutedMemberList(String str) {
        ArrayList<SuperTeamMember> arrayList = new ArrayList<>();
        Cursor b = database().b("SELECT tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids from super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and valid='1' and mute='1'");
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

    public static void saveTeamMember(c cVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cVar);
        saveTeamMembers(arrayList);
    }

    public static void saveTeamMembersB(List<c> list) {
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
        com.netease.nimlib.log.b.d(TAG, "save super team members: count=" + list.size());
    }

    private static void insertBatchTeamMembers(List<c> list) {
        try {
            try {
                database().f();
                for (c cVar : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("tid", cVar.getTid());
                    contentValues.put("account", cVar.getAccount());
                    contentValues.put("type", Integer.valueOf(cVar.getType().getValue()));
                    contentValues.put("nick", cVar.getTeamNick());
                    contentValues.put("bits", Long.valueOf(cVar.a()));
                    contentValues.put("join_time", Long.valueOf(cVar.getJoinTime()));
                    contentValues.put("valid", Integer.valueOf(cVar.b()));
                    contentValues.put("custom", cVar.getExtension());
                    contentValues.put("mute", Integer.valueOf(cVar.isMute() ? 1 : 0));
                    contentValues.put("invitor_accid", cVar.getInvitorAccid());
                    contentValues.put("follow_account_ids", cVar.c());
                    database().b("super_tuser", null, contentValues);
                }
                database().h();
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e(TAG, "insertBatchTeamMembers " + f.f(list), e);
            }
        } finally {
            database().g();
        }
    }

    private static void saveTeamMembersA(List<c> list) {
        StringBuilder sb = new StringBuilder();
        for (c cVar : list) {
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(cVar.getTid()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(cVar.getAccount()));
            sb.append("','");
            sb.append(cVar.getType().getValue());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(cVar.getTeamNick()));
            sb.append("','");
            sb.append(cVar.a());
            sb.append("','");
            sb.append(cVar.getJoinTime());
            sb.append("','");
            sb.append(cVar.b());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(cVar.getExtension()));
            sb.append("','");
            sb.append(cVar.isMute() ? 1 : 0);
            sb.append("','");
            sb.append(cVar.getInvitorAccid());
            sb.append("','");
            sb.append(cVar.c());
            sb.append("'");
            if (sb.length() > 10000) {
                database().a("INSERT OR REPLACE INTO super_tuser (tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids)" + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            database().a("INSERT OR REPLACE INTO super_tuser (tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids)" + ((Object) sb));
        }
    }

    public static void saveTeamMembers(List<c> list) {
        if (MsgDBHelper.abTestSelected) {
            com.netease.nimlib.log.b.d(TAG, "saveTeamMembersB abTestSelected: " + MsgDBHelper.abTestSelected);
            saveTeamMembersB(list);
            return;
        }
        com.netease.nimlib.log.b.d(TAG, "saveTeamMembersA abTestSelected: " + MsgDBHelper.abTestSelected);
        saveTeamMembersA(list);
    }

    public static void refreshAllTeamMembers(String str, List<c> list, List<c> list2) {
        database().f();
        try {
            saveTeamMembers(list);
            deleteTeamMemberDirect(str, list2);
            database().h();
        } catch (Throwable unused) {
        }
        database().g();
    }

    public static void deleteTeamMember(String str, String str2) {
        database().a("update super_tuser set valid='0' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
    }

    public static void deleteTeamMemberDirect(String str, List<c> list) {
        String str2 = "DELETE FROM super_tuser WHERE tid='" + com.netease.nimlib.database.a.c.a(str) + "' AND (";
        StringBuilder sb = new StringBuilder();
        for (c cVar : list) {
            if (sb.length() != 0) {
                sb.append(" OR account='");
            } else {
                sb.append(" account='");
            }
            sb.append(com.netease.nimlib.database.a.c.a(cVar.getAccount()) + "'");
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
    }

    public static TeamMemberType getMemberType(String str, String str2) {
        Cursor b = database().b("SELECT type FROM super_tuser where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
        if (b != null) {
            r3 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return TeamMemberType.typeOfValue(r3);
    }

    public static void muteTeamMember(String str, String str2, boolean z) {
        database().a("update super_tuser set mute='" + (z ? 1 : 0) + "' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(str2) + "'");
    }

    public static void muteTeamMembers(String str, ArrayList<String> arrayList, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("update super_tuser set mute='");
        sb.append(z ? 1 : 0);
        sb.append("' where tid='");
        sb.append(com.netease.nimlib.database.a.c.a(str));
        sb.append("' and account in ('");
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(com.netease.nimlib.database.a.c.a(it.next()));
            sb.append("','");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        database().a(sb.toString());
    }

    public static void updateTeamMemberInvitor(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        database().f();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                database().a("update super_tuser set invitor_accid='" + com.netease.nimlib.database.a.c.a(entry.getValue()) + "' where tid='" + com.netease.nimlib.database.a.c.a(str) + "' and account='" + com.netease.nimlib.database.a.c.a(entry.getKey()) + "'");
            }
            database().h();
        } catch (Throwable unused) {
        }
        database().g();
    }

    private static c memberFromCursor(Cursor cursor) {
        c cVar = new c();
        cVar.a(cursor.getString(0));
        cVar.b(cursor.getString(1));
        cVar.a(cursor.getInt(2));
        cVar.c(cursor.getString(3));
        cVar.a(cursor.getLong(4));
        cVar.b(cursor.getLong(5));
        cVar.b(cursor.getInt(6));
        cVar.f(cursor.getString(7));
        cVar.c(cursor.getInt(8));
        cVar.d(cursor.getString(9));
        cVar.e(cursor.getString(10));
        return cVar;
    }

    private static final com.netease.nimlib.database.b database() {
        return com.netease.nimlib.database.f.a().f();
    }

    public static List<SuperTeam> searchTeamsByKeyword(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor b = database().b("SELECT id, name, creator, type, level, valid_flag, count, member_tt, introduce, announcement, config, timetag, extension, create_time, join_mode, member_flag, ext_server, bits, icon, be_invite_mode, invite_mode, update_tinfo_mode, update_custom_mode, all_mute from " + com.netease.nimlib.database.c.a() + " where valid_flag='1' and member_flag='1' and name like " + com.netease.nimlib.database.a.c.b(str));
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

    public static ArrayList<String> queryTeamIdByName(String str) {
        String str2 = "SELECT id from " + com.netease.nimlib.database.c.a() + " where name='" + com.netease.nimlib.database.a.c.a(str) + "'";
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

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a9, code lost:
    
        if (r5.isClosed() != false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.netease.nimlib.sdk.superteam.SuperTeamMember> queryMemberListByKeyword(java.lang.String r4, java.lang.String r5, int r6, int r7, boolean r8) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT "
            r1.append(r2)
            java.lang.String r2 = "tid, account, type, nick, bits, join_time, valid, custom, mute, invitor_accid, follow_account_ids"
            r1.append(r2)
            java.lang.String r2 = " from super_tuser where "
            r1.append(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 != 0) goto L30
            java.lang.String r2 = "tid='"
            r1.append(r2)
            java.lang.String r2 = com.netease.nimlib.database.a.c.a(r4)
            r1.append(r2)
            java.lang.String r2 = "' and "
            r1.append(r2)
        L30:
            java.lang.String r2 = "valid='1' and (nick like "
            r1.append(r2)
            java.lang.String r2 = com.netease.nimlib.database.a.c.b(r5)
            r1.append(r2)
            java.lang.String r2 = " or account like "
            r1.append(r2)
            java.lang.String r5 = com.netease.nimlib.database.a.c.b(r5)
            r1.append(r5)
            java.lang.String r5 = ") ORDER BY join_time "
            r1.append(r5)
            java.lang.String r5 = "DESC"
            java.lang.String r2 = "ASC"
            if (r8 == 0) goto L55
            r3 = r5
            goto L56
        L55:
            r3 = r2
        L56:
            r1.append(r3)
            java.lang.String r3 = ",account "
            r1.append(r3)
            if (r8 == 0) goto L61
            goto L62
        L61:
            r5 = r2
        L62:
            r1.append(r5)
            java.lang.String r5 = " limit "
            r1.append(r5)
            r1.append(r7)
            java.lang.String r5 = " offset "
            r1.append(r5)
            r1.append(r6)
            com.netease.nimlib.database.b r5 = database()
            java.lang.String r6 = r1.toString()
            android.database.Cursor r5 = r5.b(r6)
            java.lang.String r6 = "SuperTeamDBHelper"
            if (r5 == 0) goto Lb6
        L85:
            boolean r7 = r5.moveToNext()     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            if (r7 == 0) goto L93
            com.netease.nimlib.superteam.c r7 = memberFromCursor(r5)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            r0.add(r7)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            goto L85
        L93:
            boolean r7 = r5.isClosed()
            if (r7 != 0) goto Lb6
        L99:
            r5.close()
            goto Lb6
        L9d:
            r4 = move-exception
            goto Lac
        L9f:
            r7 = move-exception
            java.lang.String r8 = "queryMemberListByKeyword exception"
            com.netease.nimlib.log.b.e(r6, r8, r7)     // Catch: java.lang.Throwable -> L9d
            boolean r7 = r5.isClosed()
            if (r7 != 0) goto Lb6
            goto L99
        Lac:
            boolean r6 = r5.isClosed()
            if (r6 != 0) goto Lb5
            r5.close()
        Lb5:
            throw r4
        Lb6:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "queryMemberListByKeyword, tid = "
            r5.append(r7)
            r5.append(r4)
            java.lang.String r4 = " , member size = "
            r5.append(r4)
            int r4 = r0.size()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.netease.nimlib.log.b.d(r6, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.superteam.SuperTeamDBHelper.queryMemberListByKeyword(java.lang.String, java.lang.String, int, int, boolean):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0104, code lost:
    
        if (r8.isClosed() != false) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.netease.nimlib.sdk.superteam.SuperTeamMember> queryMemberListByTypes(java.lang.String r7, java.util.Set<com.netease.nimlib.sdk.team.constant.TeamMemberType> r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.superteam.SuperTeamDBHelper.queryMemberListByTypes(java.lang.String, java.util.Set, int, int, boolean):java.util.List");
    }

    public static boolean isMyTeam(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor b = database().b("SELECT COUNT(*) from " + com.netease.nimlib.database.c.a() + " where id='" + com.netease.nimlib.database.a.c.a(str) + "' and valid_flag='1' and member_flag='1'");
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
