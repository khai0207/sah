package com.netease.nimlib.n.d.a;

import com.netease.nimlib.n.b.f;
import com.netease.nimlib.n.b.l;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.List;
import java.util.Map;

/* compiled from: ExceptionEventRuleModifyLinkKeep.java */
/* loaded from: classes.dex */
public class c implements com.netease.nimlib.n.d.a.a.c {
    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(com.netease.nimlib.n.e.d dVar) {
        if (C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar.f(), String.valueOf(f.kLinkKeep.a()))) {
            List<com.netease.nimlib.n.c.d> l = dVar.l();
            if (l != null && l.size() == 2) {
                com.netease.nimlib.n.c.d dVar2 = l.get(0);
                com.netease.nimlib.n.c.d dVar3 = l.get(1);
                if (dVar2 != null && dVar2.e().intValue() == l.kConnected.a() && dVar3 != null && dVar3.e().intValue() == l.kDisconnected.a()) {
                    if (!C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar3.h(), "Heartbeat-discovered link failure") && (!C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar2.c(), dVar3.c()) || !C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar2.b(), dVar3.b()))) {
                        return true;
                    }
                    if (!C$r8$backportedMethods$utility$Objects$2$equals.equals(dVar2.i(), dVar3.i())) {
                        dVar2.b(true);
                        dVar3.b(true);
                    } else {
                        dVar2.b(false);
                        dVar3.b(false);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.netease.nimlib.n.d.a.a.c
    public boolean a(Map<String, Object> map) {
        return C$r8$backportedMethods$utility$Objects$2$equals.equals(map.get("action"), String.valueOf(f.kLinkKeep.a()));
    }
}
