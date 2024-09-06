package com.android.pc.ioc.view.listener;

import android.view.View;
import android.widget.AdapterView;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.core.kernel.KernelString;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class OnItemSelected extends OnListener implements AdapterView.OnItemSelectedListener {
    private String noneMethod;
    private Method noneTargetMethod;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == null || view == null) {
            Ioc.getIoc().getLogger().d(" 无法调用OnItemSelected(如果没有对程序造成影响请忽略，这是个未找出问题的错误)\n");
        } else {
            invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        if (this.noneMethod != null) {
            this.noneTargetMethod = KernelReflect.assignableMethod(this.target.getClass(), this.noneMethod, 0, true, KernelClass.parameterTypes(adapterView));
            this.noneMethod = null;
        }
        if (this.noneTargetMethod != null) {
            KernelReflect.invoke(this.target, this.noneTargetMethod, adapterView);
        }
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof AdapterView) {
            String[] split = this.method.split(",");
            if (split.length == 2) {
                this.method = split[0];
                this.noneMethod = split[1];
            } else {
                this.noneMethod = "none" + KernelString.uncapitalize(this.method);
            }
            ((AdapterView) view).setOnItemSelectedListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置OnItemSelected 请检查InjectMethod的参数\n");
    }
}
