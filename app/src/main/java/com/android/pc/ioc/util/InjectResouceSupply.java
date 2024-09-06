package com.android.pc.ioc.util;

import android.graphics.drawable.Drawable;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.core.kernel.KernelClass;

/* loaded from: classes.dex */
public class InjectResouceSupply {
    public static InjectResouceType<?>[] injectResouceTypes = {new InjectResouceType<String>() { // from class: com.android.pc.ioc.util.InjectResouceSupply.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.util.InjectResouceSupply.InjectResouceType
        public String getResouce(int i) {
            return Ioc.getIoc().getApplication().getResources().getString(i);
        }
    }, new InjectResouceType<String[]>() { // from class: com.android.pc.ioc.util.InjectResouceSupply.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.util.InjectResouceSupply.InjectResouceType
        public String[] getResouce(int i) {
            return Ioc.getIoc().getApplication().getResources().getStringArray(i);
        }
    }, new InjectResouceType<Drawable>() { // from class: com.android.pc.ioc.util.InjectResouceSupply.3
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.android.pc.ioc.util.InjectResouceSupply.InjectResouceType
        public Drawable getResouce(int i) {
            return Ioc.getIoc().getApplication().getResources().getDrawable(i);
        }
    }};

    /* loaded from: classes.dex */
    public static abstract class InjectResouceType<T> {
        private Class<?> type = KernelClass.componentClass(getClass());

        protected abstract T getResouce(int i);

        public T getResouce(int i, String str) {
            int resouceId = InjectViewUtils.getResouceId(i, this.type.getSimpleName(), str);
            if (resouceId == 0) {
                return null;
            }
            return getResouce(resouceId);
        }
    }
}
