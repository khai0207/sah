package com.android.pc.ioc.image;

import android.content.Context;
import com.android.pc.ioc.image.ImageCache;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ImageLoadManager {
    private static final int MESSAGE_CLEAR = 0;
    private static final int MESSAGE_INIT_DISK_CACHE = 1;
    private static ImageLoadManager loadManager;
    private Coding coding;
    private ImageCache mImageCache;
    private ImageCache.ImageCacheParams mImageCacheParams;

    /* loaded from: classes.dex */
    public interface Coding {
        byte[] decodeJPG(long j, InputStream inputStream);

        byte[] decodePNG(long j, InputStream inputStream);
    }

    public static ImageLoadManager instance() {
        if (loadManager == null) {
            loadManager = new ImageLoadManager();
        }
        return loadManager;
    }

    public ImageCache getmImageCache() {
        return this.mImageCache;
    }

    public void setmImageCache(ImageCache imageCache) {
        this.mImageCache = imageCache;
    }

    public void addImageCache(ImageCache.ImageCacheParams imageCacheParams) {
        this.mImageCacheParams = imageCacheParams;
        this.mImageCache = ImageCache.getInstance(imageCacheParams);
        new CacheAsyncTask().execute(1);
    }

    public void addImageCache(Context context, String str) {
        ImageCache.ImageCacheParams imageCacheParams = new ImageCache.ImageCacheParams(context, str);
        this.mImageCacheParams = imageCacheParams;
        this.mImageCache = ImageCache.getInstance(imageCacheParams);
        new CacheAsyncTask().execute(1);
    }

    /* loaded from: classes.dex */
    protected class CacheAsyncTask extends AsyncTask<Object, Void, Void> {
        protected CacheAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.image.AsyncTask
        public Void doInBackground(Object... objArr) {
            int intValue = ((Integer) objArr[0]).intValue();
            if (intValue == 0) {
                ImageLoadManager.this.clearCacheInternal();
                return null;
            }
            if (intValue != 1) {
                return null;
            }
            ImageLoadManager.this.initDiskCacheInternal();
            return null;
        }
    }

    protected void initDiskCacheInternal() {
        ImageCache imageCache = this.mImageCache;
        if (imageCache != null) {
            imageCache.initDiskCache();
        }
    }

    protected void clearCacheInternal() {
        ImageCache imageCache = this.mImageCache;
        if (imageCache != null) {
            imageCache.clearCache();
        }
    }

    public void clearCache() {
        new CacheAsyncTask().execute(0);
    }

    public Coding getCoding() {
        return this.coding;
    }

    public void setCoding(Coding coding) {
        this.coding = coding;
    }
}
