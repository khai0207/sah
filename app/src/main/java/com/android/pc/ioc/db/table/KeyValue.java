package com.android.pc.ioc.db.table;

/* loaded from: classes.dex */
public class KeyValue {
    private String key;
    private Object value;

    public KeyValue(String str, Object obj) {
        this.key = str;
        this.value = ColumnUtils.convert2DbColumnValueIfNeeded(obj);
    }

    public String getKey() {
        return this.key;
    }

    public Object getValue() {
        return this.value;
    }
}
