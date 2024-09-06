package com.android.pc.ioc.invoker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.util.InjectExcutor;
import com.android.pc.ioc.view.PullToRefreshView;
import com.android.pc.ioc.view.listener.OnListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class InjectViews extends InjectInvoker implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    private ArrayList<Views> arrayList = new ArrayList<>();
    private boolean down;
    private Field field;
    private int id;
    private Class<?> inClass;
    private Field injectAllfield;
    private InjectExcutor<Activity> injectExcutor;
    private boolean isAsy;
    private PullToRefreshView mPullToRefreshView;
    Method method;
    private Object object;
    private boolean pull;

    public InjectViews(int i, InjectExcutor<Activity> injectExcutor, Field field, boolean z, boolean z2, boolean z3, Class<?> cls, Field field2) {
        this.id = i;
        this.injectExcutor = injectExcutor;
        this.field = field;
        this.isAsy = z;
        this.pull = z2;
        this.down = z3;
        this.inClass = cls;
        this.injectAllfield = field2;
    }

    public void setViews(Views views) {
        this.arrayList.add(views);
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    /* loaded from: classes.dex */
    public class Views {
        public Class[] listeners;
        public String method;

        public Views(String str, Class[] clsArr) {
            this.method = str;
            this.listeners = clsArr;
        }

        public String toString() {
            return "Views [method=" + this.method + ", listeners=" + Arrays.toString(this.listeners) + "]";
        }
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        View findViewById;
        this.object = obj;
        if (this.injectExcutor.getObject() != null) {
            findViewById = this.injectExcutor.findViewById(this.id);
        } else {
            findViewById = this.injectExcutor.findViewById((Activity) obj, this.id);
        }
        if (findViewById == null) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 对象 " + this.field.getName() + " ID:" + this.id + "不对 无法查找到view 请检查\n");
            return;
        }
        if (this.isAsy) {
            ListView.class.isAssignableFrom(findViewById.getClass());
        }
        if ((this.down || this.pull) && ListView.class.isAssignableFrom(findViewById.getClass())) {
            applyTo((ListView) findViewById);
        }
        Iterator<Views> it = this.arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Views next = it.next();
            for (Class cls : next.listeners) {
                try {
                    ((OnListener) cls.newInstance()).listener(findViewById, obj, next.method);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (findViewById == null || !this.field.getType().isAssignableFrom(findViewById.getClass())) {
            Ioc.getIoc().getLogger().e(obj.getClass().getSimpleName() + " 对象 " + this.field.getName() + "赋值不对 请检查\n");
            return;
        }
        try {
            if (this.injectAllfield == null) {
                this.field.setAccessible(true);
                this.field.set(obj, findViewById);
                return;
            }
            this.injectAllfield.setAccessible(true);
            Object obj2 = this.injectAllfield.get(this.object);
            if (obj2 == null) {
                if (this.inClass.getDeclaringClass() == null) {
                    obj2 = this.inClass.newInstance();
                } else {
                    Constructor<?>[] declaredConstructors = this.inClass.getDeclaredConstructors();
                    declaredConstructors[0].setAccessible(true);
                    obj2 = declaredConstructors[0].newInstance(this.object);
                }
                KernelReflect.set(this.object, this.injectAllfield, obj2);
            }
            this.field.setAccessible(true);
            this.field.set(obj2, findViewById);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static Map<String, String> readClassAttr(Object obj) throws Exception {
        String str = "";
        String str2 = str;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                String str3 = str + "," + field.getName();
                str2 = "a".equals(field.getName()) ? str2 + ",特殊格式哦" : str2 + "," + field.get(obj);
                str = str3;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("keys", str);
        hashMap.put("values", str2);
        return hashMap;
    }

    private void applyTo(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        this.mPullToRefreshView = new PullToRefreshView(view.getContext());
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        view.setLayoutParams(new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height));
        this.mPullToRefreshView.addView(view, 1);
        this.mPullToRefreshView.onFooter();
        viewGroup.addView(this.mPullToRefreshView, indexOfChild, layoutParams);
        this.mPullToRefreshView.setOnHeaderRefreshListener(this);
        this.mPullToRefreshView.setOnFooterRefreshListener(this);
        this.mPullToRefreshView.setFooter(this.pull);
        this.mPullToRefreshView.setHeader(this.down);
    }

    public String toString() {
        return "InjectViews [id=" + this.id + ", arrayList=" + this.arrayList + "]";
    }

    @Override // com.android.pc.ioc.view.PullToRefreshView.OnFooterRefreshListener
    public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
        Method method = this.method;
        if (method == null) {
            return;
        }
        method.setAccessible(true);
        try {
            this.method.invoke(this.object, 1);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().indexOf("wrong number of arguments") != -1) {
                Ioc.getIoc().getLogger().e(this.object.getClass().getSimpleName() + " 方法 " + this.method + "参数不对 请检查\n");
                return;
            }
            if (e instanceof InvocationTargetException) {
                Ioc.getIoc().getLogger().e(this.object.getClass().getSimpleName() + " 方法 " + this.method + "里面出错了 请检查\n");
                e.getCause().printStackTrace();
            }
        }
    }

    @Override // com.android.pc.ioc.view.PullToRefreshView.OnHeaderRefreshListener
    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
        Method method = this.method;
        if (method == null) {
            return;
        }
        method.setAccessible(true);
        try {
            this.method.invoke(this.object, 2);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().indexOf("wrong number of arguments") != -1) {
                Ioc.getIoc().getLogger().e(this.object.getClass().getSimpleName() + " 方法 " + this.method + "参数不对 请检查\n");
                return;
            }
            if (e instanceof InvocationTargetException) {
                Ioc.getIoc().getLogger().e(this.object.getClass().getSimpleName() + " 方法 " + this.method + "里面出错了 请检查\n");
                e.getCause().printStackTrace();
            }
        }
    }
}
