package com.netease.nimlib.friend;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.netease.nimlib.database.a.c;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.friend.constant.FriendRelationship;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FriendDBHelper {
    private static final String TABLE_NAME = "friend";
    private static final String TAG = "FriendDBHelper";

    private static String friendColumnStr() {
        return "account,flag,beflag,source,alias,bits,ex,createtime,updatetime,serverex";
    }

    public static void saveFriend(b bVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bVar);
        saveFriends(arrayList);
    }

    public static void saveFriends(List<b> list) {
        String str = "INSERT OR REPLACE INTO friend (" + friendColumnStr() + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            b bVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(c.a(bVar.getAccount()));
            sb.append("','");
            sb.append(bVar.c());
            sb.append("','");
            sb.append(bVar.d());
            sb.append("','");
            sb.append((int) bVar.b().getValue());
            sb.append("','");
            sb.append(c.a(bVar.getAlias()));
            sb.append("','");
            sb.append(bVar.e());
            sb.append("','");
            sb.append(c.a(bVar.h()));
            sb.append("','");
            sb.append(bVar.f());
            sb.append("','");
            sb.append(bVar.g());
            sb.append("','");
            sb.append(c.a(bVar.getServerExtension()));
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

    public static boolean isMyFriend(String str) {
        b queryFriend = queryFriend(str);
        com.netease.nimlib.log.b.v(String.format("isMyFriend, account=%s, friend=%s", str, b.a(queryFriend)));
        return (queryFriend == null || queryFriend.a() == FriendRelationship.NOT_FRIEND) ? false : true;
    }

    public static b queryFriend(String str) {
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.log.b.v("queryFriend canceled, account is " + str);
            return null;
        }
        if (com.netease.nimlib.log.b.a.a()) {
            Log.i(TAG, "queryFriend abTestSelected: " + MsgDBHelper.abTestSelected);
        }
        if (com.netease.nimlib.biz.a.h()) {
            return queryFriendB(str);
        }
        return queryFriendA(str);
    }

    public static b queryFriendA(String str) {
        ArrayList<b> queryFriends = queryFriends(String.format("SELECT %s FROM %s where account='%s'", friendColumnStr(), TABLE_NAME, str));
        if (queryFriends == null || queryFriends.size() != 1) {
            return null;
        }
        return queryFriends.get(0);
    }

    public static b queryFriendB(String str) {
        ArrayList<b> queryFriends = queryFriends(String.format("SELECT %s FROM %s where account=?", friendColumnStr(), TABLE_NAME), new String[]{str});
        if (f.e(queryFriends) == 1) {
            return queryFriends.get(0);
        }
        return null;
    }

    public static ArrayList<b> getFriends() {
        return queryFriends(String.format("SELECT * FROM %s where flag!='%s'", TABLE_NAME, 0));
    }

    public static ArrayList<String> getFriendAccounts() {
        Cursor b = database().b(String.format("SELECT account FROM %s where flag!='%s'", TABLE_NAME, 0));
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

    public static void deleteFriendInfo(String str) {
        database().a(String.format("DELETE from %s where account='%s'", TABLE_NAME, str));
    }

    public static void deleteFriend(String str, boolean z) {
        String format;
        if (z) {
            format = String.format("UPDATE %s SET flag='0',beflag='0',alias='' where account='%s'", TABLE_NAME, str);
        } else {
            format = String.format("UPDATE %s SET flag='0',beflag='0' where account='%s'", TABLE_NAME, str);
        }
        database().a(format);
    }

    public static void clearAll() {
        database().a(String.format("DELETE from %s", TABLE_NAME));
    }

    private static ArrayList<b> queryFriends(String str) {
        return queryFriends(str, null);
    }

    private static ArrayList<b> queryFriends(String str, String[] strArr) {
        Cursor a = database().a(str, strArr);
        if (a == null) {
            return new ArrayList<>();
        }
        ArrayList<b> arrayList = new ArrayList<>(a.getCount());
        while (a.moveToNext()) {
            b bVar = new b();
            bVar.b(a.getString(0));
            bVar.a(Integer.valueOf(a.getInt(1)));
            bVar.b(Integer.valueOf(a.getInt(2)));
            bVar.a(Byte.valueOf((byte) a.getInt(3)));
            bVar.c(a.getString(4));
            bVar.b(Long.valueOf(a.getLong(5)));
            bVar.d(a.getString(6));
            bVar.c(Long.valueOf(a.getLong(7)));
            bVar.a(Long.valueOf(a.getLong(8)));
            bVar.e(a.getString(9));
            arrayList.add(bVar);
        }
        if (!a.isClosed()) {
            a.close();
        }
        return arrayList;
    }

    private static com.netease.nimlib.database.b database() {
        return com.netease.nimlib.database.f.a().f();
    }

    public static List<b> searchFriendsByKeyword(String str) {
        return queryFriends(String.format("SELECT * FROM %s where flag!='%s' and alias like %s", TABLE_NAME, 0, c.b(str)));
    }

    public static ArrayList<String> searchAccountByAlias(String str) {
        String format = String.format("SELECT account FROM %s where alias='%s'", TABLE_NAME, str);
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
}
