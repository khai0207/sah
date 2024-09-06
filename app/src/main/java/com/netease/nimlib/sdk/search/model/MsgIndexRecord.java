package com.netease.nimlib.sdk.search.model;

import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.search.a.a;
import com.netease.nimlib.search.b.b;
import com.netease.nimlib.search.model.NIMIndexRecord;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MsgIndexRecord implements Comparable<MsgIndexRecord> {
    private List<RecordHitInfo> hitInfo;
    private IMMessage message;
    private final String query;
    private final NIMIndexRecord record;

    public MsgIndexRecord(NIMIndexRecord nIMIndexRecord, String str) {
        this.record = nIMIndexRecord;
        this.query = str;
    }

    public String getText() {
        return this.record.content;
    }

    public SessionTypeEnum getSessionType() {
        return a.a(this.record.id);
    }

    public String getSessionId() {
        return a.b(this.record.id);
    }

    public long getTime() {
        return this.record.time;
    }

    public int getCount() {
        return this.record.count;
    }

    public String getQuery() {
        return this.query;
    }

    public NIMIndexRecord getRecord() {
        return this.record;
    }

    public IMMessage getMessage() {
        if (this.message == null) {
            this.message = MsgDBHelper.queryMessageBySeqId(this.record.dataid);
        }
        return this.message;
    }

    public List<RecordHitInfo> getHitInfo() {
        if (this.hitInfo == null) {
            ArrayList<b.a> a = b.a().a(getText(), this.query, true, false);
            if (a == null || a.isEmpty()) {
                a = b.a().a(getText(), this.query, true, true);
            }
            this.hitInfo = new ArrayList(a.size());
            Iterator<b.a> it = a.iterator();
            while (it.hasNext()) {
                b.a next = it.next();
                this.hitInfo.add(new RecordHitInfo(next.a, next.b));
            }
        }
        return this.hitInfo;
    }

    public List<RecordHitInfo> cloneHitInfo() {
        getHitInfo();
        ArrayList arrayList = new ArrayList(this.hitInfo.size());
        for (RecordHitInfo recordHitInfo : this.hitInfo) {
            arrayList.add(new RecordHitInfo(recordHitInfo.start, recordHitInfo.end));
        }
        return arrayList;
    }

    @Override // java.lang.Comparable
    public int compareTo(MsgIndexRecord msgIndexRecord) {
        return (int) (msgIndexRecord.record.time - this.record.time);
    }

    public String toString() {
        return this.record.toString();
    }
}
