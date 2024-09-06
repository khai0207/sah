package com.talkingdata.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public class bv {
    private static int a = 2;
    private static int b = 6;
    private static int c = 6;
    private static int d = -40;
    private static int e = 4;
    private br f;

    /* compiled from: td */
    /* loaded from: classes.dex */
    class a {
        public bu a;
        public bu b;
        public double c;

        public a(bu buVar, bu buVar2, double d) {
            this.a = buVar;
            this.b = buVar2;
            this.c = d;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class b {
        public Object a;
        public Object b;

        public b(Object obj, Object obj2) {
            this.a = obj;
            this.b = obj2;
        }
    }

    public bv() {
        this(new br());
    }

    public bv(br brVar) {
        this.f = brVar;
    }

    public double a(bu buVar, bu buVar2) {
        Map a2 = buVar.a(false);
        Map a3 = buVar2.a(false);
        HashSet hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (Map.Entry entry : a2.entrySet()) {
            bq bqVar = (bq) entry.getValue();
            bq bqVar2 = (bq) a3.get(entry.getKey());
            i2 += bqVar.c();
            if (bqVar2 == null) {
                hashSet.add(bqVar);
            } else {
                i++;
                double b2 = b(bqVar.c(), bqVar2.c());
                d2 += b2;
                d3 += b2 * a(bqVar.c(), bqVar2.c());
            }
        }
        if (i == 0) {
            return 0.0d;
        }
        for (Map.Entry entry2 : a3.entrySet()) {
            i2 += ((bq) entry2.getValue()).c();
            if (!a2.containsKey(entry2.getKey())) {
                hashSet.add(entry2.getValue());
            }
        }
        int size = i2 / ((buVar.c().size() + buVar2.c().size()) - 0);
        int d4 = this.f.d();
        double d5 = size;
        Double.isNaN(d5);
        int max = Math.max(d4, (int) (d5 + 1.2d));
        Iterator it = hashSet.iterator();
        double d6 = 0.0d;
        while (it.hasNext()) {
            if (((bq) it.next()).c() > max) {
                d6 += 1.0d;
            }
        }
        double d7 = i * 2;
        Double.isNaN(d7);
        return (d3 / d2) * (1.0d - Math.pow(d6 / (d7 + d6), e));
    }

    public double a(bu buVar, List list) {
        Iterator it = list.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 = Math.max(a((bu) it.next(), buVar), d2);
        }
        return d2;
    }

    public double a(List list, List list2) {
        double d2 = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            return 0.0d;
        }
        LinkedList<a> linkedList = new LinkedList();
        b(list, list2, linkedList);
        int i = 0;
        for (a aVar : linkedList) {
            if (aVar.a != null && aVar.b != null) {
                d2 += aVar.c;
                i++;
            }
        }
        double d3 = i;
        Double.isNaN(d3);
        return d2 / d3;
    }

    public bu b(bu buVar, bu buVar2) {
        Map a2 = buVar.a(false);
        Map a3 = buVar2.a(false);
        TreeMap treeMap = new TreeMap();
        bu buVar3 = new bu();
        buVar3.setPoiId(buVar2.b());
        buVar3.setTimestamp(buVar2.a());
        LinkedList linkedList = new LinkedList();
        buVar3.setBsslist(linkedList);
        for (Map.Entry entry : a2.entrySet()) {
            bq bqVar = (bq) entry.getValue();
            bq bqVar2 = (bq) a3.get(entry.getKey());
            if (bqVar2 == null) {
                double d2 = -bqVar.c();
                while (treeMap.containsKey(Double.valueOf(d2))) {
                    d2 += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d2), bqVar);
            } else {
                linkedList.add(new bq(bqVar2.a(), bqVar2.b(), (byte) ((bqVar2.c() + bqVar.c()) / 2), bqVar2.d(), bqVar2.e()));
            }
        }
        for (Map.Entry entry2 : a3.entrySet()) {
            if (!a2.containsKey(entry2.getKey())) {
                double d3 = -((bq) entry2.getValue()).c();
                while (treeMap.containsKey(Double.valueOf(d3))) {
                    d3 += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d3), entry2.getValue());
            }
        }
        for (Map.Entry entry3 : treeMap.entrySet()) {
            byte b2 = (byte) (-((Double) entry3.getKey()).doubleValue());
            if (linkedList.size() >= this.f.c() || b2 < this.f.d()) {
                break;
            }
            linkedList.add(entry3.getValue());
        }
        return buVar3;
    }

    public double a(List list, List list2, List list3) {
        double d2 = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            list3.addAll(list);
            list3.addAll(list2);
            return 0.0d;
        }
        LinkedList<a> linkedList = new LinkedList();
        b(list, list2, linkedList);
        int i = 0;
        for (a aVar : linkedList) {
            if (aVar.a != null && aVar.b != null) {
                d2 += aVar.c;
                i++;
                list3.add(b(aVar.a, aVar.b));
            } else if (list3.size() < this.f.b()) {
                list3.add((aVar.a == null ? aVar.b : aVar.a).d());
            }
        }
        double d3 = i;
        Double.isNaN(d3);
        return d2 / d3;
    }

    public double a(int i, int i2) {
        double d2 = 0.0d;
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double d3 = (i2 + i) / 2;
        double d4 = i;
        Double.isNaN(d4);
        Double.isNaN(d3);
        double abs = Math.abs(d4 - d3);
        int i3 = a;
        if (abs > i3) {
            double d5 = i3;
            Double.isNaN(d5);
            d2 = abs - d5;
        }
        Double.isNaN(d3);
        Double.isNaN(d3);
        return Math.pow((d2 + d3) / d3, b);
    }

    public void b(List list, List list2, List list3) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bu buVar = (bu) it.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                bu buVar2 = (bu) it2.next();
                hashSet2.add(buVar2);
                arrayList.add(new a(buVar, buVar2, a(buVar, buVar2)));
            }
            hashSet.add(buVar);
        }
        Collections.sort(arrayList, new bw(this));
        list3.clear();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            a aVar = (a) it3.next();
            if (hashSet.contains(aVar.a) && hashSet2.contains(aVar.b)) {
                hashSet.remove(aVar.a);
                hashSet2.remove(aVar.b);
                list3.add(aVar);
            }
        }
        Iterator it4 = hashSet.iterator();
        while (it4.hasNext()) {
            list3.add(new a((bu) it4.next(), null, 0.0d));
        }
        Iterator it5 = hashSet2.iterator();
        while (it5.hasNext()) {
            list3.add(new a(null, (bu) it5.next(), 0.0d));
        }
    }

    public double b(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double max = Math.max(i, i2);
        int i3 = d;
        if (max >= i3) {
            return 1.0d;
        }
        Double.isNaN(max);
        double d2 = i3 + 128;
        Double.isNaN(d2);
        return Math.pow((max + 128.0d) / d2, c);
    }
}
