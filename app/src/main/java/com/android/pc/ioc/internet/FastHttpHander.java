package com.android.pc.ioc.internet;

import android.app.Activity;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.internet.FastHttp;
import com.android.pc.ioc.invoker.InjectInvoker;
import com.android.pc.ioc.util.ContextUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class FastHttpHander {
    public static void ajax(String str, Object obj) {
        ajax(str, (LinkedHashMap<String, String>) null, InternetConfig.defaultConfig(), obj);
    }

    public static void ajax(String str, InternetConfig internetConfig, Object obj) {
        ajax(str, (LinkedHashMap<String, String>) null, internetConfig, obj);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, Object obj) {
        ajax(str, linkedHashMap, InternetConfig.defaultConfig(), obj);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, final InternetConfig internetConfig, final Object obj) {
        internetConfig.setRequest_type(0);
        new Thread(new FastHttp.AjaxTask(str, linkedHashMap, internetConfig, new AjaxCallBack() { // from class: com.android.pc.ioc.internet.FastHttpHander.1
            @Override // com.android.pc.ioc.internet.CallBack
            public void callBack(ResponseEntity responseEntity) {
                FastHttpHander.http_inject(responseEntity, obj, internetConfig);
            }

            @Override // com.android.pc.ioc.internet.AjaxCallBack
            public boolean stop() {
                return FastHttpHander.isDestory(obj);
            }
        })).start();
    }

    public static void ajax(String str, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setRequest_type(0);
        ajax(str, (LinkedHashMap<String, String>) null, defaultConfig, ajaxTimeCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setRequest_type(0);
        ajax(str, linkedHashMap, defaultConfig, ajaxTimeCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        internetConfig.setRequest_type(0);
        new Thread(new FastHttp.TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    public static void ajaxForm(String str, Object obj) {
        ajaxForm(str, (LinkedHashMap<String, String>) null, (HashMap<String, File>) null, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxForm(String str, InternetConfig internetConfig, Object obj) {
        ajaxForm(str, (LinkedHashMap<String, String>) null, (HashMap<String, File>) null, internetConfig, obj);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, Object obj) {
        ajaxForm(str, linkedHashMap, (HashMap<String, File>) null, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, Object obj) {
        ajaxForm(str, linkedHashMap, hashMap, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, Object obj, FastHttp.Progress progress) {
        ajaxForm(str, linkedHashMap, hashMap, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, final InternetConfig internetConfig, final Object obj) {
        internetConfig.setRequest_type(4);
        internetConfig.setFiles(hashMap);
        new Thread(new FastHttp.AjaxTask(str, linkedHashMap, internetConfig, new AjaxCallBack() { // from class: com.android.pc.ioc.internet.FastHttpHander.2
            @Override // com.android.pc.ioc.internet.CallBack
            public void callBack(ResponseEntity responseEntity) {
                FastHttpHander.http_inject(responseEntity, obj, internetConfig);
            }

            @Override // com.android.pc.ioc.internet.AjaxCallBack
            public boolean stop() {
                return FastHttpHander.isDestory(obj);
            }
        })).start();
    }

    public static void ajaxGet(String str, Object obj) {
        ajaxGet(str, (LinkedHashMap<String, String>) null, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxGet(String str, InternetConfig internetConfig, Object obj) {
        ajaxGet(str, (LinkedHashMap<String, String>) null, internetConfig, obj);
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, Object obj) {
        ajaxGet(str, linkedHashMap, InternetConfig.defaultConfig(), obj);
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, final InternetConfig internetConfig, final Object obj) {
        if (internetConfig == null) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + "  的网络请求配置不能为空\n");
            return;
        }
        internetConfig.setRequest_type(1);
        new Thread(new FastHttp.AjaxTask(str, linkedHashMap, internetConfig, new AjaxCallBack() { // from class: com.android.pc.ioc.internet.FastHttpHander.3
            @Override // com.android.pc.ioc.internet.CallBack
            public void callBack(ResponseEntity responseEntity) {
                FastHttpHander.http_inject(responseEntity, obj, internetConfig);
            }

            @Override // com.android.pc.ioc.internet.AjaxCallBack
            public boolean stop() {
                return FastHttpHander.isDestory(obj);
            }
        })).start();
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setRequest_type(1);
        new Thread(new FastHttp.TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    public static void ajaxWebServer(String str, String str2, Object obj) {
        InternetConfig internetConfig = new InternetConfig();
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        ajaxWebServer(str, str2, (LinkedHashMap<String, String>) null, internetConfig, obj);
    }

    public static void ajaxWebServer(String str, String str2, InternetConfig internetConfig, Object obj) {
        ajaxWebServer(str, str2, (LinkedHashMap<String, String>) null, internetConfig, obj);
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, Object obj) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setMethod(str2);
        defaultConfig.setRequest_type(3);
        ajaxWebServer(str, str2, linkedHashMap, defaultConfig, obj);
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, final InternetConfig internetConfig, final Object obj) {
        if (internetConfig == null) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 的网络请求配置不能为空\n");
            return;
        }
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        new Thread(new FastHttp.AjaxTask(str, linkedHashMap, internetConfig, new AjaxCallBack() { // from class: com.android.pc.ioc.internet.FastHttpHander.4
            @Override // com.android.pc.ioc.internet.CallBack
            public void callBack(ResponseEntity responseEntity) {
                FastHttpHander.http_inject(responseEntity, obj, internetConfig);
            }

            @Override // com.android.pc.ioc.internet.AjaxCallBack
            public boolean stop() {
                return FastHttpHander.isDestory(obj);
            }
        })).start();
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setMethod(str2);
        defaultConfig.setRequest_type(3);
        new Thread(new FastHttp.TimeTask(str, linkedHashMap, defaultConfig, ajaxTimeCallBack)).start();
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        new Thread(new FastHttp.TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void http_inject(ResponseEntity responseEntity, Object obj, InternetConfig internetConfig) {
        ArrayList<InjectInvoker> httpOkInvokers = ContextUtils.getHttpOkInvokers(obj.getClass(), internetConfig.getKey());
        if (httpOkInvokers == null) {
            httpOkInvokers = ContextUtils.getHttpOkInvokers(obj.getClass(), -1);
        }
        ArrayList<InjectInvoker> httpErrInvokers = ContextUtils.getHttpErrInvokers(obj.getClass(), internetConfig.getKey());
        if (httpErrInvokers == null) {
            httpErrInvokers = ContextUtils.getHttpErrInvokers(obj.getClass(), -1);
        }
        ArrayList<InjectInvoker> httpAllInvokers = ContextUtils.getHttpAllInvokers(obj.getClass(), internetConfig.getKey());
        if (httpAllInvokers == null) {
            httpAllInvokers = ContextUtils.getHttpAllInvokers(obj.getClass(), -1);
        }
        if (responseEntity.getStatus() == 0) {
            if (httpOkInvokers == null && httpAllInvokers == null) {
                Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 的网络请求" + responseEntity.getUrl() + "\nkey为" + responseEntity.getKey() + "没有增加回调方法注释 请检查\n");
                return;
            }
            if (httpOkInvokers != null) {
                Iterator<InjectInvoker> it = httpOkInvokers.iterator();
                while (it.hasNext()) {
                    it.next().invoke(obj, responseEntity);
                }
                return;
            } else {
                if (httpAllInvokers != null) {
                    Iterator<InjectInvoker> it2 = httpAllInvokers.iterator();
                    while (it2.hasNext()) {
                        it2.next().invoke(obj, responseEntity);
                    }
                    return;
                }
                return;
            }
        }
        if (httpErrInvokers == null && httpAllInvokers == null) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 的网络请求" + responseEntity.getUrl() + "\nkey为" + responseEntity.getKey() + "没有增加回调方法注释 请检查\n");
            return;
        }
        if (httpErrInvokers != null) {
            Iterator<InjectInvoker> it3 = httpErrInvokers.iterator();
            while (it3.hasNext()) {
                it3.next().invoke(obj, responseEntity);
            }
        } else if (httpAllInvokers != null) {
            Iterator<InjectInvoker> it4 = httpAllInvokers.iterator();
            while (it4.hasNext()) {
                it4.next().invoke(obj, responseEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isDestory(Object obj) {
        if (Activity.class.isAssignableFrom(obj.getClass())) {
            return ((Activity) obj).isFinishing();
        }
        try {
            Class<?> cls = Class.forName("androidx.fragment.app.Fragment");
            Class<?> cls2 = Class.forName("android.app.Fragment");
            if (!cls.isAssignableFrom(obj.getClass()) && !cls2.isAssignableFrom(obj.getClass())) {
                return false;
            }
            Method method = obj.getClass().getMethod("isDetached", new Class[0]);
            Method method2 = obj.getClass().getMethod("isRemoving", new Class[0]);
            if (Boolean.valueOf(method.invoke(obj, new Object[0]).toString()).booleanValue()) {
                return Boolean.valueOf(method2.invoke(obj, new Object[0]).toString()).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
