package com.android.pc.ioc.core.kernel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class KernelList {
    public static final List EMPTY_LIST = new ArrayList();
    public static final Comparator<Orderable> COMPARATOR = new Comparator<Orderable>() { // from class: com.android.pc.ioc.core.kernel.KernelList.1
        @Override // java.util.Comparator
        public int compare(Orderable orderable, Orderable orderable2) {
            return orderable.getOrder() - orderable2.getOrder();
        }
    };
    public static final Comparator<Orderable> COMPARATOR_DESC = new Comparator<Orderable>() { // from class: com.android.pc.ioc.core.kernel.KernelList.2
        @Override // java.util.Comparator
        public int compare(Orderable orderable, Orderable orderable2) {
            return orderable2.getOrder() - orderable.getOrder();
        }
    };
    public static final Comparator COMMON_COMPARATOR = new Comparator() { // from class: com.android.pc.ioc.core.kernel.KernelList.3
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return KernelList.getOrder(obj) - KernelList.getOrder(obj2);
        }
    };

    /* loaded from: classes.dex */
    public interface Orderable {
        int getOrder();
    }

    public static <T> T get(List<T> list, int i) {
        return (T) get(list, null, i);
    }

    public static <T> T get(List<T> list, T t, int i) {
        return (i < 0 || i >= list.size()) ? t : list.get(i);
    }

    public static <T extends Orderable> void addOrder(List<T> list, T t) {
        int order = t.getOrder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (order < list.get(i).getOrder()) {
                list.add(i, t);
                return;
            }
        }
        list.add(t);
    }

    public static <T extends Orderable> void addOrderOnly(List<T> list, T t) {
        int order = t.getOrder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t2 = list.get(i);
            if (t2 == t) {
                return;
            }
            if (order < t2.getOrder()) {
                list.add(i, t);
                return;
            }
        }
        list.add(t);
    }

    public static <T extends Orderable> void sortOrderable(List<T> list) {
        Collections.sort(list, COMPARATOR);
    }

    public static <T extends Orderable> void sortOrderableDesc(List<T> list) {
        Collections.sort(list, COMPARATOR_DESC);
    }

    public static int getOrder(Object obj) {
        if (obj instanceof Orderable) {
            return ((Orderable) obj).getOrder();
        }
        return 0;
    }

    public static void addOrderObject(List list, Object obj) {
        int order = getOrder(obj);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (order < getOrder(list.get(i))) {
                list.add(i, obj);
                return;
            }
        }
        list.add(obj);
    }

    public static void sortCommonObjects(List list) {
        Collections.sort(list, COMMON_COMPARATOR);
    }
}
