package com.android.pc.ioc.db.table;

import java.util.Date;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DbModel {
    private HashMap<String, String> dataMap = new HashMap<>();

    public String getString(String str) {
        return this.dataMap.get(str);
    }

    public int getInt(String str) {
        return Integer.valueOf(getString(str)).intValue();
    }

    public boolean getBoolean(String str) {
        return ColumnUtils.convert2Boolean(getString(str)).booleanValue();
    }

    public double getDouble(String str) {
        return Double.valueOf(getString(str)).doubleValue();
    }

    public float getFloat(String str) {
        return Float.valueOf(getString(str)).floatValue();
    }

    public long getLong(String str) {
        return Long.valueOf(getString(str)).longValue();
    }

    public Date getDate(String str) {
        return new Date(Long.valueOf(getString(str)).longValue());
    }

    public java.sql.Date getSqlDate(String str) {
        return new java.sql.Date(Long.valueOf(getString(str)).longValue());
    }

    public void add(String str, String str2) {
        this.dataMap.put(str, str2);
    }

    public HashMap<String, String> getDataMap() {
        return this.dataMap;
    }
}
