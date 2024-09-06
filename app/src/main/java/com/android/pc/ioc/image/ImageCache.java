package com.android.pc.ioc.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.widget.Toast;
import com.alipay.sdk.m.h.a;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.LruCache;
import java.io.File;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ImageCache {
    private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    private static final int DEFAULT_COMPRESS_QUALITY = 70;
    private static final boolean DEFAULT_DISK_CACHE_ENABLED = true;
    private static final int DEFAULT_DISK_CACHE_SIZE = 10485760;
    private static final boolean DEFAULT_INIT_DISK_CACHE_ON_CREATE = false;
    private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
    private static final int DEFAULT_MEM_CACHE_SIZE = 5120;
    private static final int HTTP_CACHE_SIZE = 10485760;
    private static ImageCache imageCache;
    private ImageCacheParams mCacheParams;
    private final Object mDiskCacheLock = new Object();
    private boolean mDiskCacheStarting = true;
    private LruCache<String, BitmapDrawable> mMemoryCache;
    private Set<SoftReference<Bitmap>> mReusableBitmaps;

    private ImageCache(ImageCacheParams imageCacheParams) {
        init(imageCacheParams);
    }

    public static ImageCache getInstance(ImageCacheParams imageCacheParams) {
        if (imageCache == null) {
            imageCache = new ImageCache(imageCacheParams);
        }
        return imageCache;
    }

    private void init(ImageCacheParams imageCacheParams) {
        this.mCacheParams = imageCacheParams;
        if (imageCacheParams.memoryCacheEnabled) {
            Ioc.getIoc().getLogger().d("Memory cache created (size = " + this.mCacheParams.memCacheSize + ")");
            if (Utils.hasHoneycomb()) {
                this.mReusableBitmaps = Collections.synchronizedSet(new HashSet());
            }
            this.mMemoryCache = new LruCache<String, BitmapDrawable>(this.mCacheParams.memCacheSize) { // from class: com.android.pc.ioc.image.ImageCache.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.android.pc.util.LruCache
                public void entryRemoved(boolean z, String str, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
                    if (RecyclingBitmapDrawable.class.isInstance(bitmapDrawable)) {
                        ((RecyclingBitmapDrawable) bitmapDrawable).setIsCached(false);
                    } else if (Utils.hasHoneycomb()) {
                        ImageCache.this.mReusableBitmaps.add(new SoftReference(bitmapDrawable.getBitmap()));
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.android.pc.util.LruCache
                public int sizeOf(String str, BitmapDrawable bitmapDrawable) {
                    int bitmapSize = ImageCache.getBitmapSize(bitmapDrawable) / 1024;
                    if (bitmapSize == 0) {
                        return 1;
                    }
                    return bitmapSize;
                }
            };
        }
        if (imageCacheParams.initDiskCacheOnCreate) {
            initDiskCache();
        }
    }

    public ImageCacheParams getmCacheParams() {
        return this.mCacheParams;
    }

    public void initDiskCache() {
        synchronized (this.mDiskCacheLock) {
            File file = this.mCacheParams.diskCacheDir;
            if (!file.exists()) {
                file.mkdirs();
            }
            if (getUsableSpace(this.mCacheParams.diskCacheDir) < 10485760) {
                Looper.prepare();
                Toast.makeText(Ioc.getIoc().getApplication(), "存储空间不足,请检查", 1).show();
                Looper.loop();
            }
            this.mDiskCacheStarting = false;
            this.mDiskCacheLock.notifyAll();
        }
    }

    public void addBitmapToCache(String str, BitmapDrawable bitmapDrawable) {
        if (str == null || bitmapDrawable == null || this.mMemoryCache == null) {
            return;
        }
        if (RecyclingBitmapDrawable.class.isInstance(bitmapDrawable)) {
            ((RecyclingBitmapDrawable) bitmapDrawable).setIsCached(true);
        }
        this.mMemoryCache.put(str, bitmapDrawable);
    }

    public BitmapDrawable getBitmapFromMemCache(String str) {
        LruCache<String, BitmapDrawable> lruCache = this.mMemoryCache;
        if (lruCache != null) {
            return lruCache.get(str);
        }
        return null;
    }

    public Bitmap getBitmapFromDiskCache(String str, int i, int i2, ImageWorker imageWorker) {
        File file;
        if (str.startsWith(a.q)) {
            String hashKeyForDisk = hashKeyForDisk(str);
            synchronized (this.mDiskCacheLock) {
                while (this.mDiskCacheStarting) {
                    try {
                        this.mDiskCacheLock.wait();
                    } catch (InterruptedException unused) {
                    }
                }
                file = getFromFileCache(hashKeyForDisk);
            }
        } else {
            file = new File(str);
        }
        if (file == null || !file.exists()) {
            return null;
        }
        return imageWorker.decodeSampledBitmapFromFile(str, file.getPath(), i, i2, this);
    }

    protected Bitmap getBitmapFromReusableSet(BitmapFactory.Options options) {
        Set<SoftReference<Bitmap>> set = this.mReusableBitmaps;
        Bitmap bitmap = null;
        if (set != null && !set.isEmpty()) {
            synchronized (this.mReusableBitmaps) {
                Iterator<SoftReference<Bitmap>> it = this.mReusableBitmaps.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Bitmap bitmap2 = it.next().get();
                    if (bitmap2 != null && bitmap2.isMutable()) {
                        if (canUseForInBitmap(bitmap2, options)) {
                            it.remove();
                            bitmap = bitmap2;
                            break;
                        }
                    } else {
                        it.remove();
                    }
                }
            }
        }
        return bitmap;
    }

    public void clearCache() {
        LruCache<String, BitmapDrawable> lruCache = this.mMemoryCache;
        if (lruCache != null) {
            lruCache.evictAll();
            Ioc.getIoc().getLogger().d("缓存清理成功");
        }
        synchronized (this.mDiskCacheLock) {
            this.mDiskCacheStarting = true;
            File file = this.mCacheParams.diskCacheDir;
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    listFiles[i].delete();
                    listFiles[i].deleteOnExit();
                }
            }
            Ioc.getIoc().getLogger().d("本地磁盘清理成功");
            initDiskCache();
        }
    }

    /* loaded from: classes.dex */
    public static class ImageCacheParams {
        public File diskCacheDir;
        public int memCacheSize = ImageCache.DEFAULT_MEM_CACHE_SIZE;
        public int diskCacheSize = 10485760;
        public Bitmap.CompressFormat compressFormat = ImageCache.DEFAULT_COMPRESS_FORMAT;
        public int compressQuality = 70;
        public boolean memoryCacheEnabled = true;
        public boolean diskCacheEnabled = true;
        public boolean initDiskCacheOnCreate = false;

        public ImageCacheParams(Context context, String str) {
            this.diskCacheDir = ImageCache.getDiskCacheDir(context, str);
        }

        public void setMemCacheSizePercent(float f) {
            if (f < 0.01f || f > 0.8f) {
                throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.01 and 0.8 (inclusive)");
            }
            this.memCacheSize = Math.round((f * ((float) Runtime.getRuntime().maxMemory())) / 1024.0f);
        }
    }

    private static boolean canUseForInBitmap(Bitmap bitmap, BitmapFactory.Options options) {
        return !Utils.hasKitKat() ? bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight && options.inSampleSize == 1 : ((options.outWidth / options.inSampleSize) * (options.outHeight / options.inSampleSize)) * getBytesPerPixel(bitmap.getConfig()) <= bitmap.getAllocationByteCount();
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        if (config == Bitmap.Config.RGB_565 || config == Bitmap.Config.ARGB_4444) {
            return 2;
        }
        if (config == Bitmap.Config.ALPHA_8) {
        }
        return 1;
    }

    public static File getDiskCacheDir(Context context, String str) {
        return new File((("mounted".equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()) ? getExternalCacheDir(context) : context.getCacheDir()).getPath() + File.separator + str);
    }

    public static String hashKeyForDisk(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static int getBitmapSize(BitmapDrawable bitmapDrawable) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        if (Utils.hasKitKat()) {
            return bitmap.getAllocationByteCount();
        }
        if (Utils.hasHoneycombMR1()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static boolean isExternalStorageRemovable() {
        if (Utils.hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File getExternalCacheDir(Context context) {
        if (Utils.hasFroyo()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/images"));
    }

    public static File getFromFileCache(String str) {
        File file = imageCache.getmCacheParams().diskCacheDir;
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, str);
    }

    public static long getUsableSpace(File file) {
        if (Utils.hasGingerbread()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }
}
