package com.android.pc.ioc.db.sqlite;

import com.android.pc.ioc.db.table.ColumnUtils;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class SqlInfo {
    private LinkedList<Object> bindArgs;
    private String sql;

    public SqlInfo() {
    }

    public SqlInfo(String str) {
        this.sql = str;
    }

    public SqlInfo(String str, Object... objArr) {
        this.sql = str;
        addBindArgs(objArr);
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String str) {
        this.sql = str;
    }

    public LinkedList<Object> getBindArgs() {
        return this.bindArgs;
    }

    public Object[] getBindArgsAsArray() {
        LinkedList<Object> linkedList = this.bindArgs;
        if (linkedList != null) {
            return linkedList.toArray();
        }
        return null;
    }

    public String[] getBindArgsAsStrArray() {
        LinkedList<Object> linkedList = this.bindArgs;
        if (linkedList == null) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        for (int i = 0; i < this.bindArgs.size(); i++) {
            strArr[i] = this.bindArgs.get(i).toString();
        }
        return strArr;
    }

    public void addBindArg(Object obj) {
        if (this.bindArgs == null) {
            this.bindArgs = new LinkedList<>();
        }
        this.bindArgs.add(ColumnUtils.convert2DbColumnValueIfNeeded(obj));
    }

    public void addBindArgs(Object... objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                addBindArg(obj);
            }
        }
    }
}
