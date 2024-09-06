package com.netease.nimlib.biz.f;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.generic.CustomizedAPIService;
import com.netease.nimlib.sdk.generic.param.GenericTypeAPICallParam;
import com.netease.nimlib.sdk.generic.result.GenericTypeAPICallResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* compiled from: CustomizedAPIServiceRemote.java */
/* loaded from: classes.dex */
public class b extends com.netease.nimlib.i.j implements CustomizedAPIService {
    protected com.netease.nimlib.biz.d.a a(com.netease.nimlib.biz.e.a aVar) {
        return com.netease.nimlib.biz.i.a().a(aVar);
    }

    protected void a(com.netease.nimlib.biz.e.a aVar, Serializable serializable) {
        a(aVar, serializable, aVar.r());
    }

    protected void a(com.netease.nimlib.biz.e.a aVar, Serializable serializable, int i) {
        com.netease.nimlib.biz.d.a a = a(aVar);
        if (a == null || a.j() == null || !(a.j() instanceof com.netease.nimlib.i.k)) {
            return;
        }
        ((com.netease.nimlib.i.k) a.j()).a(i).a(serializable).b();
    }

    @Override // com.netease.nimlib.sdk.generic.CustomizedAPIService
    public InvocationFuture<GenericTypeAPICallResult> invokeAPI(GenericTypeAPICallParam genericTypeAPICallParam) {
        if (genericTypeAPICallParam == null || !genericTypeAPICallParam.isValid()) {
            b().a(414).b();
            return null;
        }
        if ("remoteCall_BatchAckSessions".equals(genericTypeAPICallParam.getName())) {
            return a(genericTypeAPICallParam);
        }
        b().a(2).b();
        return null;
    }

    private InvocationFuture<GenericTypeAPICallResult> a(GenericTypeAPICallParam genericTypeAPICallParam) {
        JSONArray jSONArray;
        String param = genericTypeAPICallParam.getParam();
        try {
            jSONArray = new JSONArray(param);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.e("CustomizedAPIService", "param error,json = " + param, th);
            b().a(414).b();
        }
        if (jSONArray.length() == 0) {
            b().a(414).b();
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            com.netease.nimlib.g.a.a a = com.netease.nimlib.g.a.a.a(jSONArray.getJSONObject(i));
            if (a == null) {
                b().a(414).b();
                return null;
            }
            arrayList.add(a);
        }
        com.netease.nimlib.biz.d.c.a aVar = new com.netease.nimlib.biz.d.c.a(genericTypeAPICallParam.getSn(), genericTypeAPICallParam.getData(), arrayList);
        aVar.a(b());
        com.netease.nimlib.biz.i.a().a(new com.netease.nimlib.biz.g.b(aVar) { // from class: com.netease.nimlib.biz.f.b.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                super.a(aVar2);
                if (!aVar2.n()) {
                    b.this.a(aVar2, (Serializable) null);
                } else {
                    b.this.a((com.netease.nimlib.biz.d.c.a) b(), (com.netease.nimlib.biz.e.c.a) aVar2);
                }
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.netease.nimlib.biz.d.c.a aVar, com.netease.nimlib.biz.e.c.a aVar2) {
        List<com.netease.nimlib.g.a.a> a = aVar2.a();
        JSONArray jSONArray = new JSONArray();
        if (com.netease.nimlib.o.f.d(a)) {
            Iterator<com.netease.nimlib.g.a.a> it = a.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().b());
            }
        }
        a(aVar2, new GenericTypeAPICallResult(aVar.d(), aVar.e(), "remoteCall_BatchAckSessions", jSONArray.toString()));
    }
}
