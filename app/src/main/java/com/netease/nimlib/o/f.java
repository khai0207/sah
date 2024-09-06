package com.netease.nimlib.o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: CollectionUtil.java */
/* loaded from: classes.dex */
public class f {

    /* compiled from: CollectionUtil.java */
    /* loaded from: classes.dex */
    public interface a<T, S> {
        S transform(T t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, R extends T> boolean a(Collection<T> collection, Collection<R> collection2) {
        if (collection == null) {
            return false;
        }
        if (c((Collection) collection2)) {
            return true;
        }
        return collection.addAll(collection2);
    }

    @SafeVarargs
    public static <T> ArrayList<T> a(T... tArr) {
        if (tArr == null || tArr.length <= 0) {
            return new ArrayList<>(0);
        }
        return new ArrayList<>(Arrays.asList(tArr));
    }

    public static <T> ArrayList<T> a(T t) {
        ArrayList<T> arrayList = new ArrayList<>(1);
        arrayList.add(t);
        return arrayList;
    }

    public static <T> HashSet<T> b(T t) {
        HashSet<T> hashSet = new HashSet<>();
        hashSet.add(t);
        return hashSet;
    }

    public static HashSet<String> a(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) == 0) {
            return new HashSet<>(0);
        }
        HashSet<String> hashSet = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            try {
                hashSet.add(jSONArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return hashSet;
    }

    public static <T> boolean a(Collection<T> collection, a<T, Boolean> aVar) {
        boolean z = false;
        if (!c((Collection) collection) && aVar != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (Boolean.TRUE.equals(aVar.transform(it.next()))) {
                    it.remove();
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0084, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        if (r5.hasNext() == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008c, code lost:
    
        if (r1 >= r7) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008e, code lost:
    
        r3.add(r5.next());
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
    
        if (r6.hasNext() == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
    
        if (r1 >= r7) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009e, code lost:
    
        r3.add(r6.next());
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a8, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> java.util.List<T> a(java.util.List<T> r5, java.util.List<T> r6, int r7, java.util.Comparator<T> r8) {
        /*
            boolean r0 = c(r5)
            r1 = 0
            if (r0 == 0) goto Ld
            boolean r0 = c(r6)
            if (r0 != 0) goto Lf
        Ld:
            if (r8 != 0) goto L15
        Lf:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r1)
            return r5
        L15:
            int r0 = e(r5)
            int r2 = e(r6)
            if (r7 > 0) goto L21
            int r7 = r0 + r2
        L21:
            int r0 = r0 + r2
            int r7 = java.lang.Math.min(r7, r0)
            boolean r0 = c(r5)
            if (r0 == 0) goto L31
            java.util.List r5 = r6.subList(r1, r7)
            return r5
        L31:
            boolean r0 = c(r6)
            if (r0 == 0) goto L3c
            java.util.List r5 = r5.subList(r1, r7)
            return r5
        L3c:
            java.util.Iterator r5 = r5.iterator()
            java.util.Iterator r6 = r6.iterator()
            java.lang.Object r0 = r5.next()
            java.lang.Object r2 = r6.next()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L51:
            int r4 = r8.compare(r0, r2)
            if (r4 > 0) goto L6e
            r3.add(r0)
            int r1 = r1 + 1
            if (r1 < r7) goto L5f
            return r3
        L5f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L6a
            java.lang.Object r0 = r5.next()
            goto L51
        L6a:
            r3.add(r2)
            goto L84
        L6e:
            r3.add(r2)
            int r1 = r1 + 1
            if (r1 < r7) goto L76
            return r3
        L76:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L81
            java.lang.Object r2 = r6.next()
            goto L51
        L81:
            r3.add(r0)
        L84:
            int r1 = r1 + 1
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L96
            if (r1 >= r7) goto L96
            java.lang.Object r8 = r5.next()
            r3.add(r8)
            goto L84
        L96:
            boolean r5 = r6.hasNext()
            if (r5 == 0) goto La8
            if (r1 >= r7) goto La8
            java.lang.Object r5 = r6.next()
            r3.add(r5)
            int r1 = r1 + 1
            goto L96
        La8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.o.f.a(java.util.List, java.util.List, int, java.util.Comparator):java.util.List");
    }

    public static <T, S> HashMap<S, ArrayList<T>> a(Collection<T> collection, boolean z, a<T, S> aVar) {
        if (c((Collection) collection) || aVar == null) {
            return new HashMap<>(0);
        }
        HashMap<S, ArrayList<T>> hashMap = new HashMap<>();
        for (T t : collection) {
            S transform = aVar.transform(t);
            if (!z || transform != null) {
                ArrayList<T> arrayList = hashMap.get(transform);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    hashMap.put(transform, arrayList);
                }
                arrayList.add(t);
            }
        }
        return hashMap;
    }

    public static <T> boolean b(Collection<T> collection, a<T, Boolean> aVar) {
        return e(collection, aVar) != null;
    }

    public static <T, S> ArrayList<S> c(Collection<T> collection, a<T, S> aVar) {
        return b(collection, false, aVar);
    }

    public static <T, S> ArrayList<S> b(Collection<T> collection, boolean z, a<T, S> aVar) {
        if (c((Collection) collection) || aVar == null) {
            return new ArrayList<>(0);
        }
        ArrayList<S> arrayList = new ArrayList<>(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            S transform = aVar.transform(it.next());
            if (!z || transform != null) {
                arrayList.add(transform);
            }
        }
        return arrayList;
    }

    public static <T, S> Set<S> c(Collection<T> collection, boolean z, a<T, S> aVar) {
        if (c((Collection) collection) || aVar == null) {
            return new HashSet(0);
        }
        HashSet hashSet = new HashSet(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            S transform = aVar.transform(it.next());
            if (!z || transform != null) {
                hashSet.add(transform);
            }
        }
        return hashSet;
    }

    public static <T> List<T> d(Collection<T> collection, a<T, Boolean> aVar) {
        if (c((Collection) collection) || aVar == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : collection) {
            if (Boolean.TRUE.equals(aVar.transform(t))) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static <T> T a(Collection<T> collection) {
        return (T) e(collection, new a() { // from class: com.netease.nimlib.o.-$$Lambda$f$hs8v0Flku70rQBlhbktk6ZNzayY
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Boolean c;
                c = f.c(obj);
                return c;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean c(Object obj) {
        return true;
    }

    public static <T> T e(Collection<T> collection, a<T, Boolean> aVar) {
        if (!c((Collection) collection) && aVar != null) {
            for (T t : collection) {
                if (Boolean.TRUE.equals(aVar.transform(t))) {
                    return t;
                }
            }
        }
        return null;
    }

    public static <T> void f(Collection<T> collection, a<T, Boolean> aVar) {
        if (c((Collection) collection) || aVar == null) {
            return;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (Boolean.FALSE.equals(aVar.transform(it.next()))) {
                return;
            }
        }
    }

    public static <T> int a(List<T> list, a<T, Boolean> aVar) {
        if (!c((Collection) list) && aVar != null) {
            ListIterator<T> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                int nextIndex = listIterator.nextIndex();
                if (Boolean.TRUE.equals(aVar.transform(listIterator.next()))) {
                    return nextIndex;
                }
            }
        }
        return -1;
    }

    public static <T> int a(List<T> list, Comparator<T> comparator) {
        if (c((Collection) list) || comparator == null) {
            return -1;
        }
        Iterator<T> it = list.iterator();
        int i = 0;
        T next = it.next();
        int i2 = 1;
        while (it.hasNext()) {
            T next2 = it.next();
            if (comparator.compare(next, next2) < 0) {
                i = i2;
                next = next2;
            }
            i2++;
        }
        return i;
    }

    public static <T> boolean b(Collection<T> collection) {
        return collection != null && collection.isEmpty();
    }

    public static <T> boolean c(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean d(Collection<T> collection) {
        return (collection == null || collection.isEmpty()) ? false : true;
    }

    public static <T> int e(Collection<T> collection) {
        return a(collection, 0);
    }

    public static <T> int a(Collection<T> collection, int i) {
        return collection == null ? i : collection.size();
    }

    public static <T> void b(List<T> list, Comparator<T> comparator) {
        Object[] array = list.toArray();
        Arrays.sort(array, comparator);
        ListIterator<T> listIterator = list.listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set(obj);
        }
    }

    public static <T> String f(Collection<T> collection) {
        return a(collection, ", ");
    }

    public static <T> String a(Collection<T> collection, String str) {
        return a(collection, str, new a() { // from class: com.netease.nimlib.o.-$$Lambda$iScwfS0l7ZSL17-9xEG0Jd0T8Rk
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return String.valueOf(obj);
            }
        });
    }

    public static <T> String a(Collection<T> collection, String str, a<T, String> aVar) {
        return a(collection, str, "", "", aVar);
    }

    public static <T> String a(Collection<T> collection, String str, String str2, String str3, a<T, String> aVar) {
        StringBuilder sb = new StringBuilder();
        if (c((Collection) collection)) {
            return str2 + str3;
        }
        for (T t : collection) {
            sb.append(str);
            sb.append(aVar.transform(t));
        }
        return str2 + sb.substring(str.length()) + str3;
    }
}
