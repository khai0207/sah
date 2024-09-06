package com.android.pc.ioc.view.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.core.kernel.KernelString;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class OnTextChanged extends OnListener implements TextWatcher {
    private String afterMethod;
    private Method afterTargetMethod;
    private String beforeMethod;
    private Method beforeTargetMethod;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.beforeMethod != null) {
            this.beforeTargetMethod = KernelReflect.assignableMethod(this.target.getClass(), this.beforeMethod, 0, true, KernelClass.parameterTypes(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
            this.beforeMethod = null;
        }
        if (this.beforeTargetMethod != null) {
            KernelReflect.invoke(this.target, this.beforeTargetMethod, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.afterMethod != null) {
            this.afterTargetMethod = KernelReflect.assignableMethod(this.target.getClass(), this.afterMethod, 0, true, KernelClass.parameterTypes(editable));
            this.afterMethod = null;
        }
        if (this.afterTargetMethod != null) {
            KernelReflect.invoke(this.target, this.afterTargetMethod, editable);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Override // com.android.pc.ioc.view.listener.OnListener
    protected void listener(View view) {
        if (view instanceof TextView) {
            String[] split = this.method.split(",");
            if (split.length > 1) {
                this.method = split[0];
                this.beforeMethod = split[1];
                if (split.length > 2) {
                    this.afterMethod = split[2];
                }
            } else {
                this.beforeMethod = "none" + KernelString.uncapitalize(this.method);
                this.beforeMethod = "after" + KernelString.uncapitalize(this.method);
            }
            ((TextView) view).addTextChangedListener(this);
            return;
        }
        Ioc.getIoc().getLogger().e(view.getClass() + " 无法设置OnTextChanged 请检查InjectMethod的参数\n");
    }
}
