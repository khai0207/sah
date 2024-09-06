package com.android.pc.ioc.inject;

import com.android.pc.ioc.view.listener.OnListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface InjectListener {
    int[] ids();

    Class<? extends OnListener>[] listeners();
}
