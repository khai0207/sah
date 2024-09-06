package com.netease.nimlib.apm.event.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.netease.nimlib.o.l;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class EventDBHelper {
    private static EventDBHelper instance = new EventDBHelper();
    private final l<com.netease.nimlib.apm.event.c.a> pendingEventList = new l<>(100);
    private a eventDb = null;
    private boolean isOpen = false;

    public static EventDBHelper getInstance() {
        return instance;
    }

    public synchronized boolean open(Context context) {
        if (this.eventDb != null && this.eventDb.e()) {
            this.isOpen = true;
            return true;
        }
        try {
            a aVar = new a(context, null, false);
            this.eventDb = aVar;
            boolean d = aVar.d();
            this.isOpen = d;
            if (d && this.pendingEventList.c() > 0) {
                com.netease.nimlib.log.b.H("open event database success, insert pending event to database,size = " + this.pendingEventList.c());
                Iterator<com.netease.nimlib.apm.event.c.a> it = this.pendingEventList.b().iterator();
                while (it.hasNext()) {
                    this.eventDb.a("event_history", (String) null, toValue(it.next()));
                }
                this.pendingEventList.a();
            }
            return this.isOpen;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("db", "open event database error", e);
            this.isOpen = false;
            return false;
        }
    }

    public void saveEvent(com.netease.nimlib.apm.event.c.a aVar) {
        if (aVar == null) {
            return;
        }
        if (!this.isOpen) {
            this.pendingEventList.a(aVar);
        } else {
            this.eventDb.a("event_history", (String) null, toValue(aVar));
        }
    }

    public List<com.netease.nimlib.apm.event.c.a> getAllEventOrderByTime(SearchOrderEnum searchOrderEnum) {
        if (!this.isOpen) {
            return new ArrayList();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT event_id,id,time,content FROM event_history ORDER BY time ");
        sb.append(searchOrderEnum == SearchOrderEnum.ASC ? "ASC" : "DESC");
        Cursor b = this.eventDb.b(sb.toString());
        ArrayList arrayList = new ArrayList();
        if (b != null) {
            while (b.moveToNext()) {
                long j = b.getLong(0);
                com.netease.nimlib.apm.event.c.a aVar = new com.netease.nimlib.apm.event.c.a(b.getString(1), b.getLong(2), b.getString(3));
                aVar.a(j);
                arrayList.add(aVar);
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public List<com.netease.nimlib.apm.event.c.a> getRecentEvent(int i) {
        if (!this.isOpen) {
            return new ArrayList();
        }
        Cursor b = this.eventDb.b("SELECT event_id,id,time,content FROM event_history order by priority desc,time desc limit " + i);
        ArrayList arrayList = new ArrayList();
        if (b != null) {
            while (b.moveToNext()) {
                long j = b.getLong(0);
                com.netease.nimlib.apm.event.c.a aVar = new com.netease.nimlib.apm.event.c.a(b.getString(1), b.getLong(2), b.getString(3));
                aVar.a(j);
                arrayList.add(aVar);
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public List<com.netease.nimlib.apm.event.c.a> getOldEvent(long j) {
        if (!this.isOpen) {
            return new ArrayList();
        }
        Cursor b = this.eventDb.b("SELECT event_id,id,time,content FROM event_history where time < " + j);
        ArrayList arrayList = new ArrayList();
        if (b != null) {
            while (b.moveToNext()) {
                long j2 = b.getLong(0);
                com.netease.nimlib.apm.event.c.a aVar = new com.netease.nimlib.apm.event.c.a(b.getString(1), b.getLong(2), b.getString(3));
                aVar.a(j2);
                arrayList.add(aVar);
            }
            if (!b.isClosed()) {
                b.close();
            }
        }
        return arrayList;
    }

    public void deleteEvent(List<com.netease.nimlib.apm.event.c.a> list) {
        if (this.isOpen) {
            StringBuilder sb = new StringBuilder();
            for (com.netease.nimlib.apm.event.c.a aVar : list) {
                if (aVar.d() >= 0) {
                    sb.append(", ");
                    sb.append("'");
                    sb.append(aVar.d());
                    sb.append("'");
                }
            }
            this.eventDb.a(String.format("DELETE FROM event_history where event_id IN (%s)", sb.substring(2)));
        }
    }

    public void deleteExpiredEvent(long j) {
        if (this.isOpen) {
            this.eventDb.a("DELETE FROM event_history where time < " + j);
        }
    }

    public void clearEvent() {
        if (this.isOpen) {
            this.eventDb.a("DELETE FROM event_history");
        }
    }

    public int getEventNum() {
        if (!this.isOpen) {
            return 0;
        }
        Cursor b = this.eventDb.b("SELECT count(*) FROM event_history");
        if (b != null) {
            r1 = b.moveToNext() ? b.getInt(0) : 0;
            if (!b.isClosed()) {
                b.close();
            }
        }
        return r1;
    }

    private ContentValues toValue(com.netease.nimlib.apm.event.c.a aVar) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("id", aVar.a());
        contentValues.put("time", Long.valueOf(aVar.c()));
        contentValues.put("content", aVar.b());
        contentValues.put("priority", Long.valueOf(aVar.e()));
        return contentValues;
    }
}
