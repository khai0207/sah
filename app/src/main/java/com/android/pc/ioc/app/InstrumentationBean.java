package com.android.pc.ioc.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import com.android.pc.ioc.inject.InjectBefore;
import com.android.pc.ioc.inject.InjectOnNewIntent;
import com.android.pc.ioc.inject.InjectPause;
import com.android.pc.ioc.inject.InjectRestart;
import com.android.pc.ioc.inject.InjectResume;
import com.android.pc.ioc.inject.InjectStart;
import com.android.pc.ioc.inject.InjectStop;
import com.android.pc.ioc.invoker.InjectInvoker;
import com.android.pc.ioc.invoker.InjectLayers;
import com.android.pc.ioc.util.ContextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class InstrumentationBean extends Instrumentation {
    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ContextUtils.getCreateInvokers(activity.getClass());
            inject(activity, InjectBefore.class, null);
            Ioc.getIoc().getLogger().d(activity.getClass() + " 遍历生命周期和网络请求注解耗时 " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            long currentTimeMillis2 = System.currentTimeMillis();
            ArrayList<InjectInvoker> viewInvokers = ContextUtils.getViewInvokers(activity.getClass(), activity, Activity.class);
            Ioc.getIoc().getLogger().d(activity.getClass() + " 遍历所有View注解耗时 " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
            if (viewInvokers.size() > 0 && viewInvokers.get(0).getClass() == InjectLayers.class) {
                InjectLayers injectLayers = (InjectLayers) viewInvokers.get(0);
                if (injectLayers.isFull()) {
                    activity.getWindow().setFlags(1024, 1024);
                }
                if (injectLayers.isTile()) {
                    activity.requestWindowFeature(1);
                }
            }
            super.callActivityOnCreate(activity, bundle);
            long currentTimeMillis3 = System.currentTimeMillis();
            int size = viewInvokers.size();
            for (int i = 0; i < size; i++) {
                viewInvokers.get(i).invoke(activity, new Object[0]);
            }
            Ioc.getIoc().getLogger().d(activity.getClass() + " 遍历UI绑定耗时 " + (System.currentTimeMillis() - currentTimeMillis3) + "ms");
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            Ioc.getIoc().getLogger().e(activity.getClass().getSimpleName() + " 方法 OnCreate里面出错了 请检查\n" + stringWriter.toString());
        }
    }

    private void inject(Activity activity, Class<?> cls, Intent intent) {
        ArrayList<InjectInvoker> contextInvokers = ContextUtils.getContextInvokers(activity.getClass(), cls);
        if (contextInvokers == null) {
            return;
        }
        try {
            Iterator<InjectInvoker> it = contextInvokers.iterator();
            while (it.hasNext()) {
                InjectInvoker next = it.next();
                if (intent != null) {
                    next.invoke(activity, intent);
                } else {
                    next.invoke(activity, new Object[0]);
                }
            }
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            Ioc.getIoc().getLogger().e(activity.getClass().getSimpleName() + "  里面出错了 请检查\n" + stringWriter.toString());
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        super.callActivityOnNewIntent(activity, intent);
        inject(activity, InjectOnNewIntent.class, intent);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
        inject(activity, InjectPause.class, null);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        inject(activity, InjectResume.class, null);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        inject(activity, InjectRestart.class, null);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        super.callActivityOnStart(activity);
        inject(activity, InjectStart.class, null);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        super.callActivityOnStop(activity);
        inject(activity, InjectStop.class, null);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable th) {
        return super.onException(obj, th);
    }
}
