package com.android.pc.ioc.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* loaded from: classes.dex */
public abstract class DisplayerLister {
    public void failLoader(ImageView imageView) {
    }

    public void finishLoader(Bitmap bitmap, ImageView imageView) {
    }

    public void progressLoader(int i, ImageView imageView) {
    }

    public void startLoader(ImageView imageView) {
    }
}
