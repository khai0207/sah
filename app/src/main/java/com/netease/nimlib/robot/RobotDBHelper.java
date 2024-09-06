package com.netease.nimlib.robot;

import android.database.Cursor;
import android.text.TextUtils;
import com.netease.nimlib.database.a.c;
import com.netease.nimlib.database.b;
import com.netease.nimlib.database.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class RobotDBHelper {
    private static final String TABLE_NAME = "robot";

    private static String robotInfoColumnStr() {
        return "account,name,icon,intro,createtime,updatetime,botid";
    }

    public static void saveRobotInfo(List<a> list) {
        String str = "INSERT OR REPLACE INTO robot (" + robotInfoColumnStr() + ")";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            a aVar = list.get(i);
            if (sb.length() == 0) {
                sb.append(" select '");
            } else {
                sb.append(" union select '");
            }
            sb.append(c.a(aVar.getAccount()));
            sb.append("','");
            sb.append(c.a(aVar.getName()));
            sb.append("','");
            sb.append(c.a(aVar.getAvatar()));
            sb.append("','");
            sb.append(c.a(aVar.getIntroduce()));
            sb.append("','");
            sb.append(aVar.a());
            sb.append("','");
            sb.append(aVar.b());
            sb.append("','");
            sb.append(c.a(aVar.getBotId()));
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

    public static List<a> queryAllRobotInfo(boolean z) {
        ArrayList<a> queryRobotInfoBySQL = queryRobotInfoBySQL(String.format("SELECT %s FROM %s", robotInfoColumnStr(), TABLE_NAME));
        ArrayList arrayList = new ArrayList(queryRobotInfoBySQL.size());
        Iterator<a> it = queryRobotInfoBySQL.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || !TextUtils.isEmpty(next.getBotId())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static a queryRobotInfo(String str) {
        ArrayList<a> queryRobotInfoBySQL = queryRobotInfoBySQL(String.format("SELECT %s FROM %s where account='%s'", robotInfoColumnStr(), TABLE_NAME, str));
        if (queryRobotInfoBySQL == null || queryRobotInfoBySQL.size() != 1) {
            return null;
        }
        return queryRobotInfoBySQL.get(0);
    }

    public static ArrayList<a> queryRobotInfo(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("'");
            sb.append(str);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return queryRobotInfoBySQL(String.format("SELECT %s FROM %s where account in (%s)", robotInfoColumnStr(), TABLE_NAME, sb.toString()));
    }

    public static boolean isRobot(String str) {
        int i;
        Cursor b = database().b(String.format("SELECT count(*) FROM %s where account='%s'", TABLE_NAME, str));
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

    public static void clearAll() {
        database().a(String.format("DELETE from %s", TABLE_NAME));
    }

    private static ArrayList<a> queryRobotInfoBySQL(String str) {
        Cursor b = database().b(str);
        if (b == null) {
            return new ArrayList<>();
        }
        ArrayList<a> arrayList = new ArrayList<>(b.getCount());
        while (b.moveToNext()) {
            a aVar = new a();
            aVar.a(b.getString(0));
            aVar.c(b.getString(1));
            aVar.d(b.getString(2));
            aVar.e(b.getString(3));
            aVar.a(b.getLong(4));
            aVar.b(b.getLong(5));
            aVar.b(b.getString(6));
            arrayList.add(aVar);
        }
        if (!b.isClosed()) {
            b.close();
        }
        return arrayList;
    }

    private static b database() {
        return f.a().f();
    }
}
