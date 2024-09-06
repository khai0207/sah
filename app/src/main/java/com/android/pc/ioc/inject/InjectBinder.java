package com.android.pc.ioc.inject;

import com.android.pc.ioc.view.listener.OnListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface InjectBinder {
    Class<? extends OnListener>[] listeners();

    String method();
}
