package com.netease.nimlib.team;

import androidx.appcompat.widget.ActivityChooserView;
import com.kqg.main.constant.KV;
import com.netease.nimlib.sdk.msg.model.TeamMessageReceipt;
import java.util.HashMap;
import java.util.List;

/* compiled from: TeamMsgReceiptNotifier.java */
/* loaded from: classes.dex */
public class j {
    private b a = new b();

    /* compiled from: TeamMsgReceiptNotifier.java */
    /* loaded from: classes.dex */
    private static class a {
        static final j a = new j();
    }

    public void a(List<TeamMessageReceipt> list) {
        this.a.b(list);
    }

    public void a() {
        this.a.a();
    }

    /* compiled from: TeamMsgReceiptNotifier.java */
    /* loaded from: classes.dex */
    private class b extends com.netease.nimlib.c.a.a<TeamMessageReceipt> {
        private b() {
            super(KV.LOGIN_OK, "NIM_TEAM_MSG_RECEIPT_NOTIFIER", ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        }

        @Override // com.netease.nimlib.c.a.a
        public void a(List<TeamMessageReceipt> list) {
            if (list == null) {
                return;
            }
            HashMap hashMap = new HashMap(list.size());
            for (TeamMessageReceipt teamMessageReceipt : list) {
                TeamMessageReceipt teamMessageReceipt2 = (TeamMessageReceipt) hashMap.get(teamMessageReceipt.getMsgId());
                if (teamMessageReceipt2 == null) {
                    hashMap.put(teamMessageReceipt.getMsgId(), teamMessageReceipt);
                } else if (teamMessageReceipt.getAckCount() > teamMessageReceipt2.getAckCount()) {
                    hashMap.put(teamMessageReceipt.getMsgId(), teamMessageReceipt);
                }
            }
            list.clear();
            list.addAll(hashMap.values());
            h.c().e(list);
            com.netease.nimlib.i.b.d(list);
        }
    }

    public static j b() {
        return a.a;
    }
}
