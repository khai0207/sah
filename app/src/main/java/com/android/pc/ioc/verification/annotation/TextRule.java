package com.android.pc.ioc.verification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface TextRule {
    int maxLength() default Integer.MAX_VALUE;

    String message() default "";

    int messageResId() default 0;

    int minLength() default 0;

    int order();

    boolean trim() default true;
}
