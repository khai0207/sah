package com.android.pc.ioc.util;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;
import com.android.pc.ioc.core.kernel.KernelObject;
import com.android.pc.ioc.core.kernel.KernelString;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class InjectViewUtils {
    public static final int ID_NONE = -1;
    public static final int ID_ZERO = 0;
    private static Class<?> R_Resouce_Class;
    private static final Map<String, Object> Type_Map_Resouce_Class = new HashMap();
    public static InjectExcutor[] Inject_Excutors = {new InjectExcutor<Activity>() { // from class: com.android.pc.ioc.util.InjectViewUtils.1
        @Override // com.android.pc.ioc.util.InjectExcutor
        public void setContentView(Activity activity, int i) {
            try {
                activity.setContentView(i);
            } catch (Exception e) {
                Ioc.getIoc().getLogger().e(activity.getClass().getSimpleName() + " setContentView() 出错 请检查InjectLayer的布局\n");
                e.printStackTrace();
            }
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View loadView(Activity activity, int i) {
            return activity.getLayoutInflater().inflate(i, (ViewGroup) null);
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(Activity activity, int i) {
            try {
                return activity.findViewById(i);
            } catch (Exception e) {
                Ioc.getIoc().getLogger().e(activity.getClass().getSimpleName() + " findViewById() 出错 请检查InjectView的参数\n");
                e.printStackTrace();
                return null;
            }
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(int i) {
            if (this.object != null) {
                return ((View) this.object).findViewById(i);
            }
            return null;
        }
    }, new InjectExcutor<View>() { // from class: com.android.pc.ioc.util.InjectViewUtils.2
        @Override // com.android.pc.ioc.util.InjectExcutor
        public void setContentView(View view, int i) {
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View loadView(View view, int i) {
            return LayoutInflater.from(view.getContext()).inflate(i, (ViewGroup) null);
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(View view, int i) {
            return view.findViewById(i);
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(int i) {
            if (this.object != null) {
                return ((View) this.object).findViewById(i);
            }
            return null;
        }
    }, new InjectExcutor<Dialog>() { // from class: com.android.pc.ioc.util.InjectViewUtils.3
        @Override // com.android.pc.ioc.util.InjectExcutor
        public void setContentView(Dialog dialog, int i) {
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View loadView(Dialog dialog, int i) {
            return dialog.getLayoutInflater().inflate(i, (ViewGroup) null);
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(Dialog dialog, int i) {
            return dialog.findViewById(i);
        }

        @Override // com.android.pc.ioc.util.InjectExcutor
        public View findViewById(int i) {
            if (this.object != null) {
                return ((View) this.object).findViewById(i);
            }
            return null;
        }
    }};

    public static void setApplication(Application application) {
        R_Resouce_Class = KernelClass.forName(application.getPackageName() + ".R");
    }

    public static Integer getResouceId(String str, String str2) {
        Object obj = Type_Map_Resouce_Class.get(str);
        if (obj == null) {
            synchronized (str) {
                obj = Type_Map_Resouce_Class.get(str);
                if (obj == null) {
                    obj = KernelClass.forName(R_Resouce_Class.getName() + "$" + KernelString.capitalize(str));
                }
                if (obj != null) {
                    Type_Map_Resouce_Class.put(str, obj);
                }
            }
        }
        if (obj == null) {
            return null;
        }
        return (Integer) KernelObject.get(obj, str2);
    }

    public static int getResouceId(int i, String str, String str2) {
        if (i != -1) {
            return i;
        }
        Integer resouceId = getResouceId(str, str2);
        if (resouceId == null) {
            return 0;
        }
        return resouceId.intValue();
    }
}
