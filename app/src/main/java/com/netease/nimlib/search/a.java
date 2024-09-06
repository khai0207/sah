package com.netease.nimlib.search;

import android.database.Cursor;
import com.netease.nimlib.database.a.c;
import com.netease.nimlib.database.d;
import com.netease.nimlib.database.f;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.search.model.NIMIndexRecord;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MsgDBSearch.java */
/* loaded from: classes.dex */
public class a {
    public static List<NIMIndexRecord> a(String str, int i) {
        String[] a = a(str);
        if (a == null) {
            return null;
        }
        if (a.length < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select msgtype,messageid,sessiontype,id,time,content,count(*) from msghistory");
        sb.append(" where content like ");
        sb.append(c.b(a[0]));
        if (a.length > 1) {
            for (int i2 = 1; i2 < a.length; i2++) {
                sb.append(" and content like ");
                sb.append(c.b(a[i2]));
            }
        }
        sb.append(" group by id having max(time)");
        sb.append(" order by time desc");
        sb.append(" limit ");
        sb.append(i);
        return b(sb.toString());
    }

    public static List<NIMIndexRecord> a(SessionTypeEnum sessionTypeEnum, String str, String str2, int i) {
        String[] a = a(str2);
        if (a == null) {
            return null;
        }
        if (a.length < 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("select msgtype,messageid,sessiontype,id,time,content,1 from msghistory");
        sb.append(" where id='");
        sb.append(c.a(str));
        sb.append("'");
        sb.append(" and sessiontype='");
        sb.append(sessionTypeEnum.getValue());
        sb.append("'");
        sb.append(" and content like ");
        sb.append(c.b(a[0]));
        if (a.length > 1) {
            for (int i2 = 1; i2 < a.length; i2++) {
                sb.append(" and content like ");
                sb.append(c.b(a[i2]));
            }
        }
        sb.append(" order by time desc");
        sb.append(" limit ");
        sb.append(i);
        return b(sb.toString());
    }

    private static String[] a(String str) {
        return str.trim().split(" ");
    }

    private static ArrayList<NIMIndexRecord> b(String str) {
        Cursor b = a().b(str);
        if (b == null) {
            return new ArrayList<>();
        }
        ArrayList<NIMIndexRecord> arrayList = new ArrayList<>();
        while (b.moveToNext()) {
            NIMIndexRecord nIMIndexRecord = new NIMIndexRecord();
            nIMIndexRecord.type = NIMIndexRecord.TYPE_MSG;
            nIMIndexRecord.subtype = b.getInt(0);
            nIMIndexRecord.dataid = b.getLong(1);
            nIMIndexRecord.id = com.netease.nimlib.search.a.a.a(SessionTypeEnum.typeOfValue(b.getInt(2)), b.getString(3));
            nIMIndexRecord.time = b.getLong(4);
            nIMIndexRecord.content = b.getString(5);
            nIMIndexRecord.count = b.getInt(6);
            arrayList.add(nIMIndexRecord);
        }
        if (!b.isClosed()) {
            b.close();
        }
        return arrayList;
    }

    private static d a() {
        return f.a().g();
    }
}
