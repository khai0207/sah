package com.android.pc.ioc.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.android.pc.ioc.app.Ioc;

/* loaded from: classes.dex */
public class RecyclingBitmapDrawable extends BitmapDrawable {
    static final String TAG = "CountingBitmapDrawable";
    private int mCacheRefCount;
    private int mDisplayRefCount;
    private boolean mHasBeenDisplayed;

    public RecyclingBitmapDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        this.mCacheRefCount = 0;
        this.mDisplayRefCount = 0;
    }

    public void setIsDisplayed(boolean z) {
        synchronized (this) {
            if (z) {
                this.mDisplayRefCount++;
                this.mHasBeenDisplayed = true;
            } else {
                this.mDisplayRefCount--;
            }
        }
        checkState();
    }

    public void setIsCached(boolean z) {
        synchronized (this) {
            if (z) {
                this.mCacheRefCount++;
            } else {
                this.mCacheRefCount--;
            }
        }
        checkState();
    }

    private synchronized void checkState() {
        if (this.mCacheRefCount <= 0 && this.mDisplayRefCount <= 0 && this.mHasBeenDisplayed && hasValidBitmap()) {
            Ioc.getIoc().getLogger().d("No longer being used or cached so recycling. " + toString());
            getBitmap().recycle();
        }
    }

    private synchronized boolean hasValidBitmap() {
        boolean z;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            z = bitmap.isRecycled() ? false : true;
        }
        return z;
    }
}
