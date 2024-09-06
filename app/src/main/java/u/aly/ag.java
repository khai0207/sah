package u.aly;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UEKV.java */
/* loaded from: classes.dex */
public class ag extends ax implements r {
    public ag(String str, Map<String, Object> map, long j, int i) {
        a(str);
        b(System.currentTimeMillis());
        if (map.size() > 0) {
            a(b(map));
        }
        a(i <= 0 ? 1 : i);
        if (j > 0) {
            a(j);
        }
    }

    private HashMap<String, bj> b(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        HashMap<String, bj> hashMap = new HashMap<>();
        int i = 0;
        while (i < 10 && it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            bj bjVar = new bj();
            Object value = next.getValue();
            if (value instanceof String) {
                bjVar.b((String) value);
            } else if (value instanceof Long) {
                bjVar.b(((Long) value).longValue());
            } else if (value instanceof Integer) {
                bjVar.b(((Integer) value).longValue());
            } else if (value instanceof Float) {
                bjVar.b(((Float) value).longValue());
            } else if (value instanceof Double) {
                bjVar.b(((Double) value).longValue());
            }
            if (bjVar.k()) {
                hashMap.put(next.getKey(), bjVar);
                i++;
            }
        }
        return hashMap;
    }

    public static af a(String str, String str2, Map<String, Object> map) {
        af afVar = new af();
        afVar.b = str;
        afVar.c = str2;
        afVar.d = map;
        return afVar;
    }

    public static String b(String str, String str2, Map<String, Object> map) {
        return str + str2;
    }

    @Override // u.aly.r
    public void a(bp bpVar, String str) {
        be beVar;
        if (bpVar.s() > 0) {
            Iterator<be> it = bpVar.u().iterator();
            while (it.hasNext()) {
                beVar = it.next();
                if (str.equals(beVar.c())) {
                    break;
                }
            }
        }
        beVar = null;
        if (beVar == null) {
            beVar = new be();
            beVar.a(str);
            bpVar.a(beVar);
        }
        beVar.a(this);
    }
}
