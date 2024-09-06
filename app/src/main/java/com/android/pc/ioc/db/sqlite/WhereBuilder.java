package com.android.pc.ioc.db.sqlite;

import android.text.TextUtils;
import com.android.pc.ioc.db.table.ColumnUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class WhereBuilder {
    private List<String> whereItems = new ArrayList();

    private WhereBuilder() {
    }

    public static WhereBuilder b() {
        return new WhereBuilder();
    }

    public static WhereBuilder b(String str, String str2, Object obj) {
        WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.appendCondition(null, str, str2, obj);
        return whereBuilder;
    }

    public WhereBuilder append(String str, String str2, Object obj) {
        appendCondition(this.whereItems.size() == 0 ? null : "AND", str, str2, obj);
        return this;
    }

    public WhereBuilder appendOR(String str, String str2, Object obj) {
        appendCondition(this.whereItems.size() == 0 ? null : "OR", str, str2, obj);
        return this;
    }

    public WhereBuilder append(String str) {
        this.whereItems.add(str);
        return this;
    }

    public String toString() {
        List<String> list = this.whereItems;
        if (list == null || list.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.whereItems.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    private void appendCondition(String str, String str2, String str3, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(" " + str + " ");
        }
        if ("!=".equals(str3)) {
            str3 = "<>";
        } else if ("==".equals(str3)) {
            str3 = "=";
        }
        if (obj == null) {
            if ("=".equals(str3)) {
                sb.append(str2);
                sb.append(" IS NULL");
            } else if ("<>".equals(str3)) {
                sb.append(str2);
                sb.append(" IS NOT NULL");
            } else {
                sb.append(str2);
                sb.append(" " + str3 + " NULL");
            }
        } else {
            sb.append(str2);
            sb.append(" " + str3 + " ");
            Object convert2DbColumnValueIfNeeded = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
            if ("TEXT".equals(ColumnUtils.fieldType2DbType(convert2DbColumnValueIfNeeded.getClass()))) {
                String obj2 = convert2DbColumnValueIfNeeded.toString();
                if (obj2.indexOf(39) != -1) {
                    obj2 = obj2.replace("'", "''");
                }
                sb.append("'" + obj2 + "'");
            } else {
                sb.append(convert2DbColumnValueIfNeeded);
            }
        }
        this.whereItems.add(sb.toString());
    }
}
