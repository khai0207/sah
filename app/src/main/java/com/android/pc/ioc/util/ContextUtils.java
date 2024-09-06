package com.android.pc.ioc.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.inject.InjectBefore;
import com.android.pc.ioc.inject.InjectHttp;
import com.android.pc.ioc.inject.InjectHttpErr;
import com.android.pc.ioc.inject.InjectHttpOk;
import com.android.pc.ioc.inject.InjectOnNewIntent;
import com.android.pc.ioc.inject.InjectPause;
import com.android.pc.ioc.inject.InjectRestart;
import com.android.pc.ioc.inject.InjectResume;
import com.android.pc.ioc.inject.InjectStart;
import com.android.pc.ioc.inject.InjectStop;
import com.android.pc.ioc.invoker.InjectHttps;
import com.android.pc.ioc.invoker.InjectInvoker;
import com.android.pc.ioc.invoker.InjectMethods;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes.dex */
public class ContextUtils {
    public static final int ID_NONE = -1;
    public static final int ID_ZERO = 0;
    private static final Map<Class<?>, InjectInvoker> all_inject_layers = new HashMap();
    private static final Map<Class<?>, ArrayList<InjectInvoker>> all_inject_views = new HashMap();
    private static final Map<Class<?>, ArrayList<InjectInvoker>> orther_inject_invokes = new HashMap();
    private static final Map<Class<?>, HashMap<Class<?>, ArrayList<InjectInvoker>>> lift_InjectInvokes = new HashMap();
    private static final Map<Class<?>, HashMap<Integer, ArrayList<InjectInvoker>>> http_InjectInvokes = new HashMap();
    private static final Map<Class<?>, HashMap<Integer, ArrayList<InjectInvoker>>> http_InjectInvokes_OK = new HashMap();
    private static final Map<Class<?>, HashMap<Integer, ArrayList<InjectInvoker>>> http_InjectInvokes_Err = new HashMap();
    private static final Map<Class<?>, Boolean> inject_status = new HashMap();
    private static HashSet<Class<?>> classes = new HashSet<Class<?>>() { // from class: com.android.pc.ioc.util.ContextUtils.1
        private static final long serialVersionUID = -2816879839908314497L;

        {
            add(Drawable.class);
            add(String.class);
        }
    };

    public static ArrayList<InjectInvoker> getContextInvokers(Class<?> cls, Class<?> cls2) {
        return lift_InjectInvokes.get(cls).get(cls2);
    }

    public static ArrayList<InjectInvoker> getHttpAllInvokers(Class<?> cls, int i) {
        if (!http_InjectInvokes.containsKey(cls)) {
            return null;
        }
        HashMap<Integer, ArrayList<InjectInvoker>> hashMap = http_InjectInvokes.get(cls);
        if (hashMap.containsKey(Integer.valueOf(i))) {
            return hashMap.get(Integer.valueOf(i));
        }
        return null;
    }

    public static ArrayList<InjectInvoker> getHttpOkInvokers(Class<?> cls, int i) {
        if (!http_InjectInvokes_OK.containsKey(cls)) {
            return null;
        }
        HashMap<Integer, ArrayList<InjectInvoker>> hashMap = http_InjectInvokes_OK.get(cls);
        if (hashMap.containsKey(Integer.valueOf(i))) {
            return hashMap.get(Integer.valueOf(i));
        }
        return null;
    }

    public static ArrayList<InjectInvoker> getHttpErrInvokers(Class<?> cls, int i) {
        if (!http_InjectInvokes_Err.containsKey(cls)) {
            return null;
        }
        HashMap<Integer, ArrayList<InjectInvoker>> hashMap = http_InjectInvokes_Err.get(cls);
        if (hashMap.containsKey(Integer.valueOf(i))) {
            return hashMap.get(Integer.valueOf(i));
        }
        return null;
    }

    public static void getCreateInvokers(Class<?> cls) {
        if (lift_InjectInvokes.get(cls) != null) {
            return;
        }
        lift_InjectInvokes.put(cls, new HashMap<>());
        http_InjectInvokes.put(cls, new HashMap<>());
        http_InjectInvokes_OK.put(cls, new HashMap<>());
        http_InjectInvokes_Err.put(cls, new HashMap<>());
        for (Class<?> cls2 = cls; cls2 != null && cls2 != Object.class && !cls2.getName().equals("android.app.Activity") && !cls2.getName().equals("androidx.fragment.app.FragmentActivity") && !cls2.getName().equals("androidx.fragment.app.Fragment") && !cls2.getName().equals("android.app.Fragment") && !cls2.getName().startsWith("com.bytedance"); cls2 = cls2.getSuperclass()) {
            HashMap<Class<?>, ArrayList<InjectInvoker>> hashMap = lift_InjectInvokes.get(cls);
            HashMap<Integer, ArrayList<InjectInvoker>> hashMap2 = http_InjectInvokes.get(cls);
            HashMap<Integer, ArrayList<InjectInvoker>> hashMap3 = http_InjectInvokes_OK.get(cls);
            HashMap<Integer, ArrayList<InjectInvoker>> hashMap4 = http_InjectInvokes_Err.get(cls);
            for (Method method : cls2.getDeclaredMethods()) {
                if (method.getAnnotation(InjectBefore.class) != null) {
                    if (!hashMap.containsKey(InjectBefore.class)) {
                        hashMap.put(InjectBefore.class, new ArrayList<>());
                    }
                    hashMap.get(InjectBefore.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectOnNewIntent.class) != null) {
                    if (!hashMap.containsKey(InjectOnNewIntent.class)) {
                        hashMap.put(InjectOnNewIntent.class, new ArrayList<>());
                    }
                    hashMap.get(InjectOnNewIntent.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectPause.class) != null) {
                    if (!hashMap.containsKey(InjectPause.class)) {
                        hashMap.put(InjectPause.class, new ArrayList<>());
                    }
                    hashMap.get(InjectPause.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectResume.class) != null) {
                    if (!hashMap.containsKey(InjectResume.class)) {
                        hashMap.put(InjectResume.class, new ArrayList<>());
                    }
                    hashMap.get(InjectResume.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectRestart.class) != null) {
                    if (!hashMap.containsKey(InjectRestart.class)) {
                        hashMap.put(InjectRestart.class, new ArrayList<>());
                    }
                    hashMap.get(InjectRestart.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectStart.class) != null) {
                    if (!hashMap.containsKey(InjectStart.class)) {
                        hashMap.put(InjectStart.class, new ArrayList<>());
                    }
                    hashMap.get(InjectStart.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectStop.class) != null) {
                    if (!hashMap.containsKey(InjectStop.class)) {
                        hashMap.put(InjectStop.class, new ArrayList<>());
                    }
                    hashMap.get(InjectStop.class).add(new InjectMethods(method, null, null, null));
                } else if (method.getAnnotation(InjectHttp.class) != null) {
                    int[] value = ((InjectHttp) method.getAnnotation(InjectHttp.class)).value();
                    for (int i = 0; i < value.length; i++) {
                        if (!hashMap2.containsKey(Integer.valueOf(value[i]))) {
                            hashMap2.put(Integer.valueOf(value[i]), new ArrayList<>());
                        }
                        hashMap2.get(Integer.valueOf(value[i])).add(new InjectHttps(method));
                    }
                } else if (method.getAnnotation(InjectHttpOk.class) != null) {
                    int[] value2 = ((InjectHttpOk) method.getAnnotation(InjectHttpOk.class)).value();
                    for (int i2 = 0; i2 < value2.length; i2++) {
                        if (!hashMap3.containsKey(Integer.valueOf(value2[i2]))) {
                            hashMap3.put(Integer.valueOf(value2[i2]), new ArrayList<>());
                        }
                        hashMap3.get(Integer.valueOf(value2[i2])).add(new InjectHttps(method));
                    }
                } else if (method.getAnnotation(InjectHttpErr.class) != null) {
                    int[] value3 = ((InjectHttpErr) method.getAnnotation(InjectHttpErr.class)).value();
                    for (int i3 = 0; i3 < value3.length; i3++) {
                        if (!hashMap4.containsKey(Integer.valueOf(value3[i3]))) {
                            hashMap4.put(Integer.valueOf(value3[i3]), new ArrayList<>());
                        }
                        hashMap4.get(Integer.valueOf(value3[i3])).add(new InjectHttps(method));
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x04d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.android.pc.ioc.invoker.InjectInvoker> getViewInvokers(java.lang.Class<?> r39, java.lang.Object r40, java.lang.Class<?> r41) {
        /*
            Method dump skipped, instructions count: 1428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.util.ContextUtils.getViewInvokers(java.lang.Class, java.lang.Object, java.lang.Class):java.util.ArrayList");
    }

    public static void getFactoryProvider() {
        Class<?>[] clsArr;
        try {
            ActivityInfo[] activityInfoArr = Ioc.getIoc().getApplication().getPackageManager().getPackageInfo(Ioc.getIoc().getApplication().getPackageName(), 1).activities;
            clsArr = new Class[activityInfoArr.length];
            for (int i = 0; i < activityInfoArr.length; i++) {
                try {
                    try {
                        clsArr[i] = Class.forName(activityInfoArr[i].name);
                    } catch (ClassNotFoundException unused) {
                    }
                } catch (PackageManager.NameNotFoundException unused2) {
                }
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            clsArr = null;
        }
        if (clsArr == null) {
            return;
        }
        for (Class<?> cls : clsArr) {
            inject_status.put(cls, true);
            getCreateInvokers(cls);
            getViewInvokers(cls, null, Activity.class);
            inject_status.remove(cls);
        }
    }

    public static boolean checkInjectStatus(Class<?> cls) {
        return !inject_status.containsKey(cls);
    }
}
