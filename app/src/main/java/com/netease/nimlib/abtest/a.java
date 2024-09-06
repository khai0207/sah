package com.netease.nimlib.abtest;

import android.text.TextUtils;
import com.netease.nimlib.abtest.db.ABTestDBHelper;
import com.netease.nimlib.d.e;
import com.netease.nimlib.o.f;
import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* compiled from: ABTestCache.java */
/* loaded from: classes.dex */
public class a {
    private Map<String, com.netease.nimlib.abtest.a.b> a = new HashMap();

    public void a() {
        com.netease.nimlib.log.b.M("ABTestCache loadData ");
        try {
            ABTestDBHelper.getInstance().open(com.netease.nimlib.c.e());
            List<com.netease.nimlib.abtest.a.b> allExperimentOrderByTime = ABTestDBHelper.getInstance().getAllExperimentOrderByTime(SearchOrderEnum.DESC);
            if (allExperimentOrderByTime == null) {
                com.netease.nimlib.log.b.M("ABTestCache loadData abTestExperiment = null");
                return;
            }
            for (com.netease.nimlib.abtest.a.b bVar : allExperimentOrderByTime) {
                this.a.put(b(bVar.b(), bVar.c()), bVar);
            }
            if (e.a()) {
                JSONArray jSONArray = new JSONArray();
                Iterator<com.netease.nimlib.abtest.a.b> it = allExperimentOrderByTime.iterator();
                while (it.hasNext()) {
                    jSONArray.put(com.netease.nimlib.abtest.a.b.b(it.next()));
                }
                com.netease.nimlib.log.b.M("ABTestCache loadData = " + jSONArray);
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.M("ABTestCache loadData error = " + e);
        }
    }

    public void a(List<com.netease.nimlib.abtest.a.b> list) {
        com.netease.nimlib.log.b.M("ABTestCache saveData ");
        try {
            ABTestDBHelper.getInstance().open(com.netease.nimlib.c.e());
            ABTestDBHelper.getInstance().clearExperiment();
            ABTestDBHelper.getInstance().saveExperiment(list);
            if (e.a()) {
                JSONArray jSONArray = new JSONArray();
                Iterator<com.netease.nimlib.abtest.a.b> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(com.netease.nimlib.abtest.a.b.b(it.next()));
                }
                com.netease.nimlib.log.b.M("ABTestCache saveData = " + jSONArray);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.M("ABTestCache saveData error = " + th);
        }
    }

    public com.netease.nimlib.abtest.a.b a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return this.a.get(b(str, str2));
    }

    public com.netease.nimlib.abtest.a.c a(String str, String str2, String str3) {
        com.netease.nimlib.abtest.a.b bVar;
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || (bVar = this.a.get(b(str, str2))) == null) {
                return null;
            }
            List<com.netease.nimlib.abtest.a.c> d = bVar.d();
            if (f.c((Collection) d)) {
                return null;
            }
            for (com.netease.nimlib.abtest.a.c cVar : d) {
                if (str3.equals(cVar.e())) {
                    return cVar;
                }
            }
            return null;
        } catch (Exception e) {
            com.netease.nimlib.log.b.M("ABTestCache abTestForExperiment error = " + e);
            return null;
        }
    }

    private String b(String str, String str2) {
        return str + "_" + str2;
    }
}
