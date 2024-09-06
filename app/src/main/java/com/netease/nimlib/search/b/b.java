package com.netease.nimlib.search.b;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: QueryHit.java */
/* loaded from: classes.dex */
public final class b {

    /* compiled from: QueryHit.java */
    /* renamed from: com.netease.nimlib.search.b.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0059b {
        public static final b a = new b();
    }

    /* compiled from: QueryHit.java */
    /* loaded from: classes.dex */
    public static class a {
        public int a;
        public int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public String toString() {
            return "[" + this.a + "," + this.b + "]";
        }

        /* compiled from: QueryHit.java */
        /* renamed from: com.netease.nimlib.search.b.b$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0058a implements Comparator<a> {
            C0058a() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(a aVar, a aVar2) {
                return aVar.a - aVar2.a;
            }
        }
    }

    public ArrayList<a> a(String str, String str2, boolean z, boolean z2) {
        ArrayList<a> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return arrayList;
        }
        String lowerCase = str.toLowerCase(Locale.CHINA);
        Iterator<String> it = com.netease.nimlib.search.b.a.a().a(str2.toLowerCase(Locale.CHINA), z2).iterator();
        while (it.hasNext()) {
            String next = it.next();
            int a2 = com.netease.nimlib.search.b.a.a().a(next.charAt(0));
            int i = -1;
            while (true) {
                i = lowerCase.indexOf(next, i + 1);
                if (i != -1) {
                    if (!z || a2 != 0 || i <= 0 || a2 != com.netease.nimlib.search.b.a.a().a(lowerCase.charAt(i - 1))) {
                        arrayList.add(new a(i, (next.length() + i) - 1));
                    }
                }
            }
        }
        return a(arrayList);
    }

    private ArrayList<a> a(ArrayList<a> arrayList) {
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator<a> it = arrayList.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            a next = it.next();
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                a aVar = (a) it2.next();
                if (a(next, aVar)) {
                    aVar.a = Math.min(next.a, aVar.a);
                    aVar.b = Math.max(next.b, aVar.b);
                    z = true;
                    break;
                }
            }
            if (!z) {
                arrayList2.add(next);
            }
        }
        Collections.sort(arrayList2, new a.C0058a());
        ArrayList<a> arrayList3 = new ArrayList<>(arrayList2.size());
        if (arrayList2.size() <= 1) {
            arrayList3.addAll(arrayList2);
        } else {
            a aVar2 = (a) arrayList2.get(0);
            for (int i = 1; i < arrayList2.size(); i++) {
                a aVar3 = (a) arrayList2.get(i);
                if (aVar2.b + 1 == aVar3.a) {
                    aVar2.b = aVar3.b;
                } else {
                    arrayList3.add(new a(aVar2.a, aVar2.b));
                    aVar2.a = aVar3.a;
                    aVar2.b = aVar3.b;
                }
                if (i == arrayList2.size() - 1) {
                    arrayList3.add(new a(aVar2.a, aVar2.b));
                }
            }
        }
        return arrayList3;
    }

    private boolean a(a aVar, a aVar2) {
        a aVar3 = aVar.a <= aVar2.a ? aVar : aVar2;
        if (aVar3 == aVar) {
            aVar = aVar2;
        }
        return aVar3.b >= aVar.a;
    }

    public static b a() {
        return C0059b.a;
    }
}
