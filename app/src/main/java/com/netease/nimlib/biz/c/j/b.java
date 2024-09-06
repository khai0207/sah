package com.netease.nimlib.biz.c.j;

import com.netease.nimlib.biz.c.i;
import com.netease.nimlib.biz.d.j.f;
import com.netease.nimlib.biz.d.j.l;
import com.netease.nimlib.biz.d.j.p;
import com.netease.nimlib.biz.e.k.m;
import com.netease.nimlib.biz.e.k.t;
import com.netease.nimlib.biz.e.k.u;
import com.netease.nimlib.sdk.msg.model.MessageKey;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.g;
import com.netease.nimlib.session.n;
import com.netease.nimlib.session.o;
import java.util.ArrayList;

/* compiled from: MsgPinResponseHandler.java */
/* loaded from: classes.dex */
public class b extends i {
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    @Override // com.netease.nimlib.biz.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.netease.nimlib.biz.e.a r2) {
        /*
            r1 = this;
            boolean r0 = r2.n()
            if (r0 != 0) goto Lb
            r0 = 0
            r1.a(r2, r0)
            return
        Lb:
            byte r0 = r2.q()
            switch(r0) {
                case 15: goto L3a;
                case 16: goto L34;
                case 17: goto L2e;
                case 18: goto L28;
                case 19: goto L22;
                case 20: goto L1c;
                case 21: goto L16;
                default: goto L12;
            }
        L12:
            switch(r0) {
                case 115: goto L28;
                case 116: goto L22;
                case 117: goto L1c;
                default: goto L15;
            }
        L15:
            goto L3f
        L16:
            com.netease.nimlib.biz.e.k.g r2 = (com.netease.nimlib.biz.e.k.g) r2
            r1.a(r2)
            goto L3f
        L1c:
            com.netease.nimlib.biz.e.k.m r2 = (com.netease.nimlib.biz.e.k.m) r2
            r1.a(r2)
            goto L3f
        L22:
            com.netease.nimlib.biz.e.k.t r2 = (com.netease.nimlib.biz.e.k.t) r2
            r1.a(r2)
            goto L3f
        L28:
            com.netease.nimlib.biz.e.k.b r2 = (com.netease.nimlib.biz.e.k.b) r2
            r1.a(r2)
            goto L3f
        L2e:
            com.netease.nimlib.biz.e.k.n r2 = (com.netease.nimlib.biz.e.k.n) r2
            r1.a(r2)
            goto L3f
        L34:
            com.netease.nimlib.biz.e.k.u r2 = (com.netease.nimlib.biz.e.k.u) r2
            r1.a(r2)
            goto L3f
        L3a:
            com.netease.nimlib.biz.e.k.c r2 = (com.netease.nimlib.biz.e.k.c) r2
            r1.a(r2)
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.biz.c.j.b.a(com.netease.nimlib.biz.e.a):void");
    }

    private void a(com.netease.nimlib.biz.e.k.c cVar) {
        long a = cVar.a();
        com.netease.nimlib.biz.d.j.b bVar = (com.netease.nimlib.biz.d.j.b) b(cVar);
        o oVar = new o(bVar.d(), new n(com.netease.nimlib.c.n(), bVar.e(), a, a));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(oVar);
        MsgDBHelper.saveMsgPin(arrayList);
        a(cVar, Long.valueOf(a));
    }

    private void a(u uVar) {
        long a = uVar.a();
        p pVar = (p) b(uVar);
        MessageKey d = pVar.d();
        String e = pVar.e();
        if (d != null) {
            MsgDBHelper.updateMsgPin(d.getUuid(), g.a(d), e, a);
        }
        a(uVar, Long.valueOf(a));
    }

    private void a(com.netease.nimlib.biz.e.k.n nVar) {
        MessageKey d = ((l) b(nVar)).d();
        if (d != null) {
            MsgDBHelper.deleteMsgPin(d.getUuid(), g.a(d));
        }
        a(nVar, Long.valueOf(nVar.a()));
    }

    private void a(com.netease.nimlib.biz.e.k.b bVar) {
        o oVar = new o(bVar.a(), bVar.b());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(oVar);
        MsgDBHelper.saveMsgPin(arrayList);
        com.netease.nimlib.i.b.a(oVar);
    }

    private void a(t tVar) {
        o oVar = new o(tVar.a(), tVar.b());
        MessageKey key = oVar.getKey();
        n pinOption = oVar.getPinOption();
        if (key != null && pinOption != null) {
            MsgDBHelper.updateMsgPin(key.getUuid(), g.a(key), pinOption.getExt(), pinOption.getUpdateTime());
        }
        com.netease.nimlib.i.b.b(oVar);
    }

    private void a(m mVar) {
        o oVar = new o(mVar.a(), mVar.b());
        MessageKey key = oVar.getKey();
        if (key != null) {
            MsgDBHelper.deleteMsgPin(key.getUuid(), g.a(key));
        }
        com.netease.nimlib.i.b.c(oVar);
    }

    private void a(com.netease.nimlib.biz.e.k.g gVar) {
        long a = gVar.a();
        boolean b = gVar.b();
        ArrayList<o> c = gVar.c();
        f fVar = (f) b(gVar);
        if (b) {
            MsgDBHelper.deleteMsgPin(fVar.d());
            MsgDBHelper.saveMsgPin(c);
        }
        a(gVar, new com.netease.nimlib.session.p(a, b, c));
    }
}
