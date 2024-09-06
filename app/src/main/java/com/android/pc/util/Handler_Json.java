package com.android.pc.util;

import com.android.pc.ioc.app.Ioc;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class Handler_Json {
    private static Map<Class<?>, ArrayList<fieldEntity>> method_map = new HashMap();
    static HashSet<Class> classes = new HashSet<Class>() { // from class: com.android.pc.util.Handler_Json.1
        {
            add(Object.class);
            add(Double.class);
            add(Float.class);
            add(Integer.class);
            add(Long.class);
            add(String.class);
            add(Integer.TYPE);
            add(Boolean.TYPE);
        }
    };

    private static Object JsonToHashMap(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Object nextValue = new JSONTokener(str).nextValue();
            if (nextValue instanceof JSONArray) {
                JSONArray jSONArray = new JSONArray(str);
                ArrayList arrayList = new ArrayList();
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(JsonToCollection(jSONArray.getString(i)));
                    }
                    return arrayList;
                }
                return Boolean.valueOf(arrayList.add(str));
            }
            if (!(nextValue instanceof JSONObject)) {
                return str;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    linkedHashMap.put(next, JsonToCollection(jSONObject.getString(next)));
                }
            }
            return linkedHashMap;
        } catch (JSONException unused) {
            Ioc.getIoc().getLogger().d("错误字符串：" + str);
            return str;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:38|(6:40|(1:42)(1:57)|43|(1:45)|46|(1:48))(2:58|(5:60|(2:61|(8:63|(1:65)|66|(1:68)|69|(1:71)|72|73)(0))|50|51|52)(2:74|75))|49|50|51|52) */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0189, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x018a, code lost:
    
        r0.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object JsonToBean(java.lang.String r16, java.lang.Object r17) {
        /*
            Method dump skipped, instructions count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.util.Handler_Json.JsonToBean(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private static void getMethod(Class<?> cls) {
        for (Class<?> cls2 = cls; cls2 != null && cls2 != Object.class; cls2 = cls2.getSuperclass()) {
            if (method_map.get(cls2) != null && method_map.get(cls2).size() > 0) {
                return;
            }
        }
        while (cls != null && !classes.contains(cls)) {
            ArrayList<fieldEntity> arrayList = new ArrayList<>();
            for (Field field : cls.getDeclaredFields()) {
                Type genericType = field.getGenericType();
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (genericType instanceof ParameterizedType) {
                        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                        if (actualTypeArguments.length > 0) {
                            Type type = actualTypeArguments[0];
                            if (!classes.contains(field.getType())) {
                                Class cls3 = (Class) type;
                                getMethod(cls3);
                                arrayList.add(new fieldEntity(field, cls3));
                            } else {
                                arrayList.add(new fieldEntity(field, null));
                            }
                        }
                    } else if (!classes.contains(field.getType())) {
                        Class cls4 = (Class) genericType;
                        getMethod(cls4);
                        arrayList.add(new fieldEntity(field, cls4));
                    } else {
                        arrayList.add(new fieldEntity(field, null));
                    }
                }
            }
            method_map.put(cls, arrayList);
            cls = cls.getSuperclass();
        }
    }

    public static <T> T JsonToBean(Class<?> cls, String str) {
        getMethod(cls);
        try {
            return (T) JsonToBean(str, cls.newInstance());
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T> T JsonToCollection(String str) {
        try {
            return (T) JsonToHashMap(str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class fieldEntity {
        public Class<?> clazz;
        public Field field;

        public fieldEntity(Field field, Class<?> cls) {
            this.field = field;
            this.clazz = cls;
        }

        public String toString() {
            return "fieldEntity [field=" + this.field.getName() + ", clazz=" + this.clazz + "]";
        }
    }
}
