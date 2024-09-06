package com.netease.nimlib.sdk.msg.model;

import com.netease.nimlib.o.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class TeamMsgAckInfo {
    private List<String> ackAccountList;
    private int ackCount;
    private String msgId;
    private String newReaderAccount;
    private String teamId;
    private List<String> unAckAccountList;
    private int unAckCount;

    public TeamMsgAckInfo(String str, String str2, int i, int i2) {
        this(str, str2, i, i2, null);
    }

    public TeamMsgAckInfo(String str, String str2, int i, int i2, String str3) {
        this.teamId = str;
        this.msgId = str2;
        this.ackCount = i;
        this.unAckCount = i2;
        this.newReaderAccount = str3;
    }

    public TeamMsgAckInfo(String str, String str2, List<String> list, List<String> list2) {
        this.teamId = str;
        this.msgId = str2;
        this.ackAccountList = list;
        this.unAckAccountList = list2;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public int getAckCount() {
        List<String> list = this.ackAccountList;
        if (list != null) {
            return list.size();
        }
        return this.ackCount;
    }

    public int getUnAckCount() {
        List<String> list = this.unAckAccountList;
        if (list != null) {
            return list.size();
        }
        return this.unAckCount;
    }

    public String getNewReaderAccount() {
        return this.newReaderAccount;
    }

    public List<String> getAckAccountList() {
        return this.ackAccountList;
    }

    public List<String> getUnAckAccountList() {
        return this.unAckAccountList;
    }

    public TeamMsgAckInfo newInstanceFromPartOfAccount(final Set<String> set) {
        if (f.c((Collection) this.ackAccountList) && f.c((Collection) this.unAckAccountList)) {
            return null;
        }
        if (f.c((Collection) set)) {
            return new TeamMsgAckInfo(this.teamId, this.msgId, new ArrayList(0), new ArrayList(0));
        }
        List<String> list = this.ackAccountList;
        set.getClass();
        List d = f.d(list, new f.a() { // from class: com.netease.nimlib.sdk.msg.model.-$$Lambda$rnl3tRnVS3n-KbjGGChkH1MQPKM
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return Boolean.valueOf(set.contains((String) obj));
            }
        });
        List<String> list2 = this.unAckAccountList;
        set.getClass();
        return new TeamMsgAckInfo(this.teamId, this.msgId, (List<String>) d, (List<String>) f.d(list2, new f.a() { // from class: com.netease.nimlib.sdk.msg.model.-$$Lambda$rnl3tRnVS3n-KbjGGChkH1MQPKM
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return Boolean.valueOf(set.contains((String) obj));
            }
        }));
    }
}
