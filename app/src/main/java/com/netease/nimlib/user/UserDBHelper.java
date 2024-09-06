package com.netease.nimlib.user;

import android.database.Cursor;
import android.util.Log;
import com.netease.nimlib.o.f;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class UserDBHelper {
    private static final String TABLE_NAME = "user_tag";
    private static final String TAG = "UserDBHelper";

    private static String userTagColumnStr() {
        return "account,mute,black,createtime,updatetime";
    }

    public static d getUserSpecialTag(String str) {
        if (com.netease.nimlib.biz.a.h()) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.i(TAG, "getUserSpecialTag abTestSelected: " + MsgDBHelper.abTestSelected);
            }
            return getUserSpecialTagB(str);
        }
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i(TAG, "getUserSpecialTag abTestSelected: " + MsgDBHelper.abTestSelected);
        }
        return getUserSpecialTagA(str);
    }

    public static d getUserSpecialTagA(String str) {
        ArrayList<d> query = query(String.format("SELECT %s FROM %s where account='%s'", userTagColumnStr(), TABLE_NAME, str), null);
        if (query == null || query.size() != 1) {
            return null;
        }
        return query.get(0);
    }

    public static d getUserSpecialTagB(String str) {
        ArrayList<d> query = query(String.format("SELECT %s FROM %s where account=?", userTagColumnStr(), TABLE_NAME), new String[]{str});
        if (f.e(query) == 1) {
            return query.get(0);
        }
        return null;
    }

    public static void markBlackList(String str, boolean z) {
        d userSpecialTag = getUserSpecialTag(str);
        if (userSpecialTag == null) {
            userSpecialTag = new d(str, 0, z ? 1 : 0, 0L, 0L);
        } else {
            userSpecialTag.b(z ? 1 : 0);
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(userSpecialTag);
        saveUserSpecialTags(arrayList);
    }

    public static void markMute(String str, boolean z) {
        d userSpecialTag = getUserSpecialTag(str);
        if (userSpecialTag == null) {
            userSpecialTag = new d(str, z ? 1 : 0, 0, 0L, 0L);
        } else {
            userSpecialTag.a(z ? 1 : 0);
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(userSpecialTag);
        saveUserSpecialTags(arrayList);
    }

    public static ArrayList<String> getBlackList() {
        return getAccountList("black=1");
    }

    public static ArrayList<String> getMuteList() {
        return getAccountList("mute=1");
    }

    public static void saveUserSpecialTags(List<d> list) {
        String str = "INSERT OR REPLACE INTO user_tag (" + userTagColumnStr() + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(dVar.a()));
            sb.append("','");
            sb.append(dVar.b() ? 1 : 0);
            sb.append("','");
            sb.append(dVar.c() ? 1 : 0);
            sb.append("','");
            sb.append(dVar.d());
            sb.append("','");
            sb.append(dVar.e());
            sb.append("'");
            if (sb.length() > 10000) {
                database().a(str + ((Object) sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            database().a(str + ((Object) sb));
        }
    }

    public static void clearAll() {
        database().a(String.format("DELETE from %s", TABLE_NAME));
    }

    private static ArrayList<d> query(String str, String[] strArr) {
        Cursor a = database().a(str, strArr);
        if (a == null) {
            return new ArrayList<>();
        }
        ArrayList<d> arrayList = new ArrayList<>(a.getCount());
        while (a.moveToNext()) {
            d dVar = new d();
            dVar.a(a.getString(0));
            dVar.a(a.getInt(1));
            dVar.b(a.getInt(2));
            dVar.a(a.getLong(3));
            dVar.b(a.getLong(4));
            arrayList.add(dVar);
        }
        if (!a.isClosed()) {
            a.close();
        }
        return arrayList;
    }

    private static ArrayList<String> getAccountList(String str) {
        Cursor b = database().b(String.format("SELECT account FROM %s where %s", TABLE_NAME, str));
        if (b == null) {
            return new ArrayList<>();
        }
        ArrayList<String> arrayList = new ArrayList<>(b.getCount());
        while (b.moveToNext()) {
            arrayList.add(b.getString(0));
        }
        if (!b.isClosed()) {
            b.close();
        }
        return arrayList;
    }

    private static com.netease.nimlib.database.b database() {
        return com.netease.nimlib.database.f.a().f();
    }
}
