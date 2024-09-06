package com.netease.nimlib.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.netease.nimlib.o.f;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.MsgDBHelperB;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class UserInfoDBHelper {
    private static final String TABLE_NAME = "uinfo";
    private static final String TAG = "UserInfoDBHelper";

    private static String userInfoColumnStr() {
        return "account,name,icon,sign,gender,email,birth,mobile,ex,updatetime";
    }

    public static void saveUserInfo(b bVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bVar);
        saveUserInfo(arrayList);
    }

    public static void saveUserInfo(List<b> list) {
        if (MsgDBHelper.abTestSelected) {
            if (com.netease.nimlib.log.b.a.a()) {
                Log.i(TAG, "saveUserInfoB abTestSelected: " + MsgDBHelper.abTestSelected);
            }
            saveUserInfoB(list);
            return;
        }
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i(TAG, "saveUserInfoA abTestSelected: " + MsgDBHelper.abTestSelected);
        }
        saveUserInfoA(list);
    }

    public static void saveUserInfoA(List<b> list) {
        String str = "INSERT OR REPLACE INTO uinfo (" + userInfoColumnStr() + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            b bVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getAccount()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getName()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getAvatar()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getSignature()));
            sb.append("','");
            sb.append(bVar.a());
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getEmail()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getBirthday()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getMobile()));
            sb.append("','");
            sb.append(com.netease.nimlib.database.a.c.a(bVar.getExtension()));
            sb.append("','");
            sb.append(bVar.b());
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

    public static void saveUserInfoB(List<b> list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 200;
            insertBatchUsers(list.subList(i, Math.min(i2, size)));
            i = i2;
        }
    }

    private static void insertBatchUsers(List<b> list) {
        try {
            try {
                database().f();
                for (b bVar : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("account", bVar.getAccount());
                    contentValues.put(com.alipay.sdk.m.h.c.e, bVar.getName());
                    contentValues.put("icon", bVar.getAvatar());
                    contentValues.put("sign", bVar.getSignature());
                    contentValues.put("gender", bVar.a());
                    contentValues.put("email", bVar.getEmail());
                    contentValues.put("birth", bVar.getBirthday());
                    contentValues.put("mobile", bVar.getMobile());
                    contentValues.put("ex", bVar.getExtension());
                    contentValues.put("updatetime", Long.valueOf(bVar.b()));
                    database().c(TABLE_NAME, null, contentValues);
                }
                database().h();
            } catch (Exception e) {
                e.printStackTrace();
                com.netease.nimlib.log.b.e(TAG, "insertBatchUsers " + f.f(list), e);
            }
        } finally {
            database().g();
        }
    }

    public static List<b> queryAllUserInfo() {
        return queryUserInfoBySQL(String.format("SELECT %s FROM %s", userInfoColumnStr(), TABLE_NAME));
    }

    public static b queryUserInfo(String str) {
        ArrayList<b> queryUserInfoBySQL = queryUserInfoBySQL(String.format("SELECT %s FROM %s where account='%s'", userInfoColumnStr(), TABLE_NAME, com.netease.nimlib.database.a.c.a(str)));
        if (queryUserInfoBySQL == null || queryUserInfoBySQL.size() != 1) {
            return null;
        }
        return queryUserInfoBySQL.get(0);
    }

    public static ArrayList<String> queryAccountByName(String str) {
        String format = String.format("SELECT account FROM %s where name='%s'", TABLE_NAME, com.netease.nimlib.database.a.c.a(str));
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor b = database().b(format);
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

    public static void updateTimeTag(String str, long j) {
        database().a(String.format("UPDATE %s set updatetime='%s' where account='%s'", TABLE_NAME, Long.valueOf(j), com.netease.nimlib.database.a.c.a(str)));
    }

    public static long getUpdateTimeTag(String str) {
        Cursor b = database().b(String.format("SELECT updatetime from %s where account='%s'", TABLE_NAME, com.netease.nimlib.database.a.c.a(str)));
        if (b != null) {
            r0 = b.moveToNext() ? b.getLong(0) : 0L;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r0;
    }

    public static Map<String, Long> getUpdateTimeTags(Set<String> set) {
        HashMap hashMap = new HashMap();
        if (f.c((Collection) set)) {
            com.netease.nimlib.log.b.L("getUpdateTimeTags accounts is empty");
            return hashMap;
        }
        int size = set.size();
        com.netease.nimlib.log.b.L("getUpdateTimeTags accounts = " + set);
        if (size <= 200) {
            getUpdateTimeTags(set, hashMap);
        } else {
            int i = size / 200;
            int i2 = size % 200;
            ArrayList arrayList = new ArrayList(set);
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i3 * 200;
                int i5 = i4 + 200;
                List subList = arrayList.subList(i4, i5);
                com.netease.nimlib.log.b.a("getUpdateTimeTags for i = %d,fromIndex = %d,toIndex = %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
                getUpdateTimeTags(subList, hashMap);
            }
            if (i2 > 0) {
                int i6 = i * 200;
                int i7 = i2 + i6;
                List subList2 = arrayList.subList(i6, i7);
                com.netease.nimlib.log.b.a("getUpdateTimeTags lastFromIndex = %d,lastToIndex = %d", Integer.valueOf(i6), Integer.valueOf(i7));
                getUpdateTimeTags(subList2, hashMap);
            }
        }
        com.netease.nimlib.log.b.L("getUpdateTimeTags result = " + hashMap);
        return hashMap;
    }

    private static Map<String, Long> getUpdateTimeTags(Collection<String> collection, Map<String, Long> map) {
        com.netease.nimlib.log.b.L("getUpdateTimeTags IN");
        if (collection == null || collection.size() == 0) {
            com.netease.nimlib.log.b.L("getUpdateTimeTags OUT size = 0");
            return map;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("in ('");
        boolean z = true;
        for (String str : collection) {
            if (z) {
                sb.append(str);
                sb.append("'");
                z = false;
            } else {
                sb.append(", '");
                sb.append(str);
                sb.append("'");
            }
        }
        sb.append(")");
        String format = String.format("SELECT account, updatetime from %s where account %s", TABLE_NAME, sb.toString());
        com.netease.nimlib.log.b.L("getUpdateTimeTags sql = " + format);
        Cursor b = database().b(format);
        if (b == null) {
            return map;
        }
        while (b.moveToNext()) {
            map.put(b.getString(0), Long.valueOf(b.getLong(1)));
        }
        if (!b.isClosed()) {
            b.close();
        }
        return map;
    }

    public static ArrayList<b> queryUserInfo(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("'");
            sb.append(str);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return queryUserInfoBySQL(String.format("SELECT %s FROM %s where account in (%s)", userInfoColumnStr(), TABLE_NAME, sb.toString()));
    }

    public static ArrayList<b> getAllUserInfo() {
        return queryUserInfoBySQL(String.format("SELECT * FROM %s", TABLE_NAME));
    }

    public static void deleteUserInfo(String str) {
        database().a(String.format("DELETE FROM %s where account='%s'", TABLE_NAME, str));
    }

    private static ArrayList<b> queryUserInfoBySQL(String str) {
        Cursor b = database().b(str);
        if (b == null) {
            return new ArrayList<>();
        }
        ArrayList<b> arrayList = new ArrayList<>(b.getCount());
        while (b.moveToNext()) {
            b bVar = new b();
            bVar.a(b.getString(0));
            bVar.b(b.getString(1));
            bVar.c(b.getString(2));
            bVar.d(b.getString(3));
            bVar.a(Integer.valueOf(b.getInt(4)));
            bVar.e(b.getString(5));
            bVar.f(b.getString(6));
            bVar.g(b.getString(7));
            bVar.h(b.getString(8));
            bVar.a(b.getLong(9));
            arrayList.add(bVar);
        }
        if (!b.isClosed()) {
            b.close();
        }
        return arrayList;
    }

    private static com.netease.nimlib.database.b database() {
        return com.netease.nimlib.database.f.a().f();
    }

    public static List<b> searchUserInfosByKeyword(String str) {
        return queryUserInfoBySQL(String.format("SELECT %s FROM %s where name like %s", userInfoColumnStr(), TABLE_NAME, com.netease.nimlib.database.a.c.b(str)));
    }

    public static String getUserInfoJsonStringByAccids(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new JSONArray().toString();
        }
        Cursor a = database().a("SELECT " + userInfoColumnStr() + " FROM " + TABLE_NAME + " WHERE account IN (" + MsgDBHelperB.repeatPlaceholders(list.size()) + ")", (String[]) list.toArray(new String[0]));
        if (a == null) {
            return new JSONArray().toString();
        }
        JSONArray fromCursorToJsonArray = MsgDBHelper.fromCursorToJsonArray(a);
        a.close();
        return fromCursorToJsonArray.toString();
    }
}
