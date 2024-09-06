package com.android.pc.ioc.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface InjectLayer {
    boolean isFull() default false;

    boolean isTitle() default false;

    int parent() default -1;

    int value() default 0;
}
