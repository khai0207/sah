package com.talkingdata.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
final class bs {
    private static int a = 2;
    private static int b = 6;
    private static int c = 6;
    private static int d = -40;
    private static int e = 4;
    private b f;

    /* compiled from: td */
    /* loaded from: classes.dex */
    class d {
        c a;
        c b;
        double c;

        d(c cVar, c cVar2, double d) {
            this.a = cVar;
            this.b = cVar2;
            this.c = d;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class e {
        Object a;
        Object b;

        e(Object obj, Object obj2) {
            this.a = obj;
            this.b = obj2;
        }
    }

    bs() {
        this(new b());
    }

    bs(b bVar) {
        this.f = bVar;
    }

    double a(c cVar, c cVar2) {
        Map a2 = cVar.a(false);
        Map a3 = cVar2.a(false);
        HashSet hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (Map.Entry entry : a2.entrySet()) {
            a aVar = (a) entry.getValue();
            a aVar2 = (a) a3.get(entry.getKey());
            i2 += aVar.c();
            if (aVar2 == null) {
                hashSet.add(aVar);
            } else {
                i++;
                double b2 = b(aVar.c(), aVar2.c());
                d2 += b2;
                d3 += b2 * a(aVar.c(), aVar2.c());
            }
        }
        if (i == 0) {
            return 0.0d;
        }
        for (Map.Entry entry2 : a3.entrySet()) {
            i2 += ((a) entry2.getValue()).c();
            if (!a2.containsKey(entry2.getKey())) {
                hashSet.add(entry2.getValue());
            }
        }
        int size = i2 / ((cVar.c().size() + cVar2.c().size()) - 0);
        int d4 = this.f.d();
        double d5 = size;
        Double.isNaN(d5);
        int max = Math.max(d4, (int) (d5 + 1.2d));
        Iterator it = hashSet.iterator();
        double d6 = 0.0d;
        while (it.hasNext()) {
            if (((a) it.next()).c() > max) {
                d6 += 1.0d;
            }
        }
        double d7 = i * 2;
        Double.isNaN(d7);
        return (d3 / d2) * (1.0d - Math.pow(d6 / (d7 + d6), e));
    }

    double a(c cVar, List list) {
        Iterator it = list.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 = Math.max(a((c) it.next(), cVar), d2);
        }
        return d2;
    }

    double a(List list, List list2) {
        double d2 = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            return 0.0d;
        }
        LinkedList<d> linkedList = new LinkedList();
        b(list, list2, linkedList);
        int i = 0;
        for (d dVar : linkedList) {
            if (dVar.a != null && dVar.b != null) {
                d2 += dVar.c;
                i++;
            }
        }
        double d3 = i;
        Double.isNaN(d3);
        return d2 / d3;
    }

    c b(c cVar, c cVar2) {
        Map a2 = cVar.a(false);
        Map a3 = cVar2.a(false);
        TreeMap treeMap = new TreeMap();
        c cVar3 = new c();
        cVar3.a(cVar2.b());
        cVar3.a(cVar2.a());
        LinkedList linkedList = new LinkedList();
        cVar3.a(linkedList);
        for (Map.Entry entry : a2.entrySet()) {
            a aVar = (a) entry.getValue();
            a aVar2 = (a) a3.get(entry.getKey());
            if (aVar2 == null) {
                double d2 = -aVar.c();
                while (treeMap.containsKey(Double.valueOf(d2))) {
                    d2 += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d2), aVar);
            } else {
                linkedList.add(new a(aVar2.a(), aVar2.b(), (byte) ((aVar2.c() + aVar.c()) / 2), aVar2.d(), aVar2.e()));
            }
        }
        for (Map.Entry entry2 : a3.entrySet()) {
            if (!a2.containsKey(entry2.getKey())) {
                double d3 = -((a) entry2.getValue()).c();
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
        return cVar3;
    }

    double a(List list, List list2, List list3) {
        double d2 = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            list3.addAll(list);
            list3.addAll(list2);
            return 0.0d;
        }
        LinkedList<d> linkedList = new LinkedList();
        b(list, list2, linkedList);
        int i = 0;
        for (d dVar : linkedList) {
            if (dVar.a != null && dVar.b != null) {
                d2 += dVar.c;
                i++;
                list3.add(b(dVar.a, dVar.b));
            } else if (list3.size() < this.f.b()) {
                list3.add((dVar.a == null ? dVar.b : dVar.a).d());
            }
        }
        double d3 = i;
        Double.isNaN(d3);
        return d2 / d3;
    }

    double a(int i, int i2) {
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

    void b(List list, List list2, List list3) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                c cVar2 = (c) it2.next();
                hashSet2.add(cVar2);
                arrayList.add(new d(cVar, cVar2, a(cVar, cVar2)));
            }
            hashSet.add(cVar);
        }
        Collections.sort(arrayList, new bt(this));
        list3.clear();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            d dVar = (d) it3.next();
            if (hashSet.contains(dVar.a) && hashSet2.contains(dVar.b)) {
                hashSet.remove(dVar.a);
                hashSet2.remove(dVar.b);
                list3.add(dVar);
            }
        }
        Iterator it4 = hashSet.iterator();
        while (it4.hasNext()) {
            list3.add(new d((c) it4.next(), null, 0.0d));
        }
        Iterator it5 = hashSet2.iterator();
        while (it5.hasNext()) {
            list3.add(new d(null, (c) it5.next(), 0.0d));
        }
    }

    double b(int i, int i2) {
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

    /* compiled from: td */
    /* loaded from: classes.dex */
    class a {
        private String b;
        private String c;
        private byte d;
        private byte e;
        private byte f;

        a() {
            this.b = "";
            this.c = com.alipay.sdk.m.q.b.b;
            this.d = (byte) -127;
            this.e = (byte) 1;
            this.f = (byte) 1;
        }

        a(String str, String str2, byte b, byte b2, byte b3) {
            this.b = str;
            this.c = str2;
            this.d = b;
            this.e = b2;
            this.f = b3;
        }

        String a() {
            return this.b;
        }

        void a(String str) {
            this.b = str;
        }

        String b() {
            return this.c;
        }

        void b(String str) {
            this.c = str;
        }

        byte c() {
            return this.d;
        }

        void a(byte b) {
            this.d = b;
        }

        byte d() {
            return this.e;
        }

        void b(byte b) {
            this.e = b;
        }

        byte e() {
            return this.f;
        }

        void c(byte b) {
            this.f = b;
        }

        a f() {
            return new a(this.b, this.c, this.d, this.e, this.f);
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class b {
        static final int a = 10;
        static final int b = 3;
        static final int c = 50;
        static final int d = -85;
        private int e = 10;
        private int f = 3;
        private int g = 50;
        private int h = -85;

        b() {
        }

        int a() {
            return this.e;
        }

        void a(int i) {
            this.e = i;
        }

        int b() {
            return this.f;
        }

        void b(int i) {
            this.f = i;
        }

        int c() {
            return this.g;
        }

        void c(int i) {
            this.g = i;
        }

        int d() {
            return this.h;
        }

        void d(int i) {
            this.h = i;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    class c {
        private int b;
        private long c;
        private List d;
        private Map e;

        c() {
        }

        int a() {
            return this.b;
        }

        void a(int i) {
            this.b = i;
        }

        long b() {
            return this.c;
        }

        void a(long j) {
            this.c = j;
        }

        List c() {
            return this.d;
        }

        void a(List list) {
            this.d = list;
        }

        Map a(boolean z) {
            if (this.e == null || z) {
                this.e = new HashMap();
                for (a aVar : this.d) {
                    this.e.put(aVar.b(), aVar);
                }
            }
            return this.e;
        }

        c d() {
            c cVar = new c();
            cVar.a(this.b);
            cVar.a(this.c);
            LinkedList linkedList = new LinkedList();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                linkedList.add(((a) it.next()).f());
            }
            cVar.a(linkedList);
            return cVar;
        }
    }
}
