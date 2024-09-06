package com.android.pc.ioc.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface InjectView {
    public static final int DOWN = 2;
    public static final int DOWN_CLOSE = 4;
    public static final int DOWN_OPEN = 6;
    public static final int PULL = 1;
    public static final int PULL_CLOSE = 3;
    public static final int PULL_OPEN = 5;

    InjectBinder[] binders() default {};

    boolean down() default false;

    String load() default "";

    boolean pull() default false;

    int value() default -1;
}
