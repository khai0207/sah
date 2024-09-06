package com.netease.nimlib.abtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ABTestDBHelper {
    private static ABTestDBHelper instance = new ABTestDBHelper();
    private a database = null;
    private boolean isOpen = false;

    public static ABTestDBHelper getInstance() {
        return instance;
    }

    public synchronized boolean open(Context context) {
        if (this.database != null && this.database.e()) {
            this.isOpen = true;
            return true;
        }
        try {
            a aVar = new a(context, null, false);
            this.database = aVar;
            boolean d = aVar.d();
            this.isOpen = d;
            return d;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("db", "open abtest database error", e);
            this.isOpen = false;
            return false;
        }
    }

    public void saveExperiment(com.netease.nimlib.abtest.a.b bVar) {
        if (bVar != null && this.isOpen) {
            this.database.b("ab_test_table", null, toValue(bVar));
        }
    }

    public void saveExperiment(List<com.netease.nimlib.abtest.a.b> list) {
        if (list == null || list.size() == 0 || !this.isOpen) {
            return;
        }
        this.database.f();
        try {
            Iterator<com.netease.nimlib.abtest.a.b> it = list.iterator();
            while (it.hasNext()) {
                this.database.a("ab_test_table", (String) null, toValue(it.next()));
            }
            this.database.h();
        } finally {
            this.database.g();
        }
    }

    public List<com.netease.nimlib.abtest.a.b> getAllExperimentOrderByTime(SearchOrderEnum searchOrderEnum) {
        if (!this.isOpen) {
            return new ArrayList();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ab_test_id,time,experiment_key,scheme_key,variates FROM ab_test_table ORDER BY time ");
        sb.append(searchOrderEnum == SearchOrderEnum.ASC ? "ASC" : "DESC");
        Cursor b = this.database.b(sb.toString());
        ArrayList arrayList = new ArrayList();
        if (b != null) {
            while (b.moveToNext()) {
                com.netease.nimlib.abtest.a.b bVar = new com.netease.nimlib.abtest.a.b();
                bVar.a(b.getLong(1));
                bVar.a(b.getString(2));
                bVar.b(b.getString(3));
                bVar.d(b.getString(4));
                arrayList.add(bVar);
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public void deleteExperiment(List<com.netease.nimlib.abtest.a.b> list) {
        if (this.isOpen) {
            this.database.f();
            try {
                for (com.netease.nimlib.abtest.a.b bVar : list) {
                    this.database.a(String.format("DELETE FROM ab_test_table WHERE experiment_key='%s' AND scheme_key='%s'", bVar.b(), bVar.c()));
                }
                this.database.h();
            } finally {
                this.database.g();
            }
        }
    }

    public void deleteExpiredExperiment(long j) {
        if (this.isOpen) {
            this.database.a("DELETE FROM ab_test_table where time < " + j);
        }
    }

    public void clearExperiment() {
        if (this.isOpen) {
            this.database.a("DELETE FROM ab_test_table");
        }
    }

    public int getExperimentNum() {
        if (!this.isOpen) {
            return 0;
        }
        Cursor b = this.database.b("SELECT count(*) FROM ab_test_table");
        if (b != null) {
            r1 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r1;
    }

    private ContentValues toValue(com.netease.nimlib.abtest.a.b bVar) {
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("time", Long.valueOf(bVar.a()));
        contentValues.put("experiment_key", bVar.b());
        contentValues.put("scheme_key", bVar.c());
        contentValues.put("variates", bVar.e());
        return contentValues;
    }
}
