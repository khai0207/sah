package com.android.pc.ioc.core.kernel;

import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class KernelCharset {
    public static final Charset DEFAULT;
    public static final Charset UTF8;

    static {
        Charset forName = Charset.forName("UTF-8");
        UTF8 = forName;
        DEFAULT = forName;
    }
}
