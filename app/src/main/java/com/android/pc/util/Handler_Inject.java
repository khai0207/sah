package com.android.pc.util;

import android.view.View;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.inject.InjectBefore;
import com.android.pc.ioc.invoker.InjectInvoker;
import com.android.pc.ioc.util.ContextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Handler_Inject {
    public static void injectFragment(Object obj, View view) {
        long currentTimeMillis = System.currentTimeMillis();
        ContextUtils.getCreateInvokers(obj.getClass());
        ArrayList<InjectInvoker> contextInvokers = ContextUtils.getContextInvokers(obj.getClass(), InjectBefore.class);
        if (contextInvokers != null) {
            try {
                Iterator<InjectInvoker> it = contextInvokers.iterator();
                while (it.hasNext()) {
                    it.next().invoke(obj, new Object[0]);
                }
            } catch (Exception e) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + "  里面出错了 请检查\n" + stringWriter.toString());
            }
        }
        Iterator<InjectInvoker> it2 = ContextUtils.getViewInvokers(obj.getClass(), view, null).iterator();
        while (it2.hasNext()) {
            it2.next().invoke(obj, new Object[0]);
        }
        Ioc.getIoc().getLogger().d(obj.getClass() + " UI加载耗时 " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static void injectOrther(Object obj, View view) {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<InjectInvoker> it = ContextUtils.getViewInvokers(obj.getClass(), view, null).iterator();
        while (it.hasNext()) {
            it.next().invoke(obj, new Object[0]);
        }
        Ioc.getIoc().getLogger().d(obj.getClass() + " UI加载耗时 " + (System.currentTimeMillis() - currentTimeMillis));
    }
}
