package com.android.pc.ioc.image;

import java.io.File;

/* loaded from: classes.dex */
public abstract class LoaderLister {
    public void failLoader(String str) {
    }

    public abstract void finishLoader(String str, File file);

    public void progressLoader(int i) {
    }

    public void startLoader(String str) {
    }
}
