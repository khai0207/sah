package com.android.pc.ioc.image;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.alipay.sdk.m.h.a;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.Handler_System;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ImageWorker {
    private static final int FADE_IN_TIME = 200;
    private static final int fail = 2;
    private static final int finish = 3;
    private static final int process = 1;
    private static final int start = 0;
    protected DisplayerLister lister;
    private ImageCache mImageCache;
    protected int mImageHeight;
    protected int mImageWidth;
    private Bitmap mLoadingBitmap;
    protected Resources mResources;
    private boolean mFadeInBitmap = true;
    private boolean mExitTasksEarly = false;
    protected boolean mPauseWork = false;
    private final Object mPauseWorkLock = new Object();
    private boolean isDecode = false;
    Handler handler = new Handler() { // from class: com.android.pc.ioc.image.ImageWorker.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Entity entity = (Entity) message.obj;
            int i = message.what;
            if (i == 0) {
                entity.lister.startLoader(entity.imageView);
                return;
            }
            if (i == 1) {
                entity.lister.progressLoader(entity.process, entity.imageView);
            } else if (i == 2) {
                entity.lister.failLoader(entity.imageView);
            } else {
                if (i != 3) {
                    return;
                }
                entity.lister.finishLoader(entity.bitmap, entity.imageView);
            }
        }
    };

    protected ImageWorker(Context context) {
        this.mResources = context.getResources();
    }

    public ImageWorker(Context context, int i, int i2) {
        setImageSize(i, i2);
        this.mResources = context.getResources();
    }

    public ImageWorker(Context context, int i) {
        setImageSize(i);
        this.mResources = context.getResources();
    }

    public void useDecode(boolean z) {
        this.isDecode = z;
    }

    public boolean isDecode() {
        return this.isDecode;
    }

    public void loadImage(Object obj, ImageView imageView) {
        if (obj == null) {
            return;
        }
        if (obj.toString().startsWith(a.q)) {
            if (obj.toString().indexOf("?") != -1) {
                obj = obj + "&w=" + getW() + "&h=" + getH();
            } else {
                obj = obj + "?w=" + getW() + "&h=" + getH();
            }
        }
        ImageCache imageCache = ImageLoadManager.instance().getmImageCache();
        this.mImageCache = imageCache;
        BitmapDrawable bitmapDrawable = null;
        if (imageCache != null) {
            BitmapDrawable bitmapFromMemCache = imageCache.getBitmapFromMemCache(String.valueOf(obj));
            if (bitmapFromMemCache == null || bitmapFromMemCache.getBitmap().getConfig() != null) {
                bitmapDrawable = bitmapFromMemCache;
            } else {
                Ioc.getIoc().getLogger().d("图片解析错误-----重新下载");
            }
        }
        if (bitmapDrawable != null) {
            imageView.setImageDrawable(bitmapDrawable);
            if (bitmapDrawable.getBitmap() != null) {
                finish(bitmapDrawable.getBitmap(), imageView, this.lister);
                return;
            }
            return;
        }
        if (cancelPotentialWork(obj, imageView)) {
            BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(obj, imageView, this.lister);
            imageView.setImageDrawable(new AsyncDrawable(this.mResources, this.mLoadingBitmap, bitmapWorkerTask));
            bitmapWorkerTask.executeOnExecutor(AsyncTask.DUAL_THREAD_EXECUTOR, new Void[0]);
        }
    }

    public void loadImage(Object obj, ImageView imageView, DisplayerLister displayerLister) {
        this.lister = displayerLister;
        loadImage(obj, imageView);
    }

    public void setLoadingImage(Bitmap bitmap) {
        this.mLoadingBitmap = bitmap;
    }

    public void setLoadingImage(int i) {
        this.mLoadingBitmap = BitmapFactory.decodeResource(this.mResources, i);
    }

    public void setImageFadeIn(boolean z) {
        this.mFadeInBitmap = z;
    }

    public void setExitTasksEarly(boolean z) {
        this.mExitTasksEarly = z;
        setPauseWork(false);
    }

    /* loaded from: classes.dex */
    private class Entity {
        public Bitmap bitmap;
        public ImageView imageView;
        public DisplayerLister lister;
        public int process;

        private Entity() {
        }
    }

    protected void process(int i, ImageView imageView, DisplayerLister displayerLister) {
        if (displayerLister == null) {
            return;
        }
        Message obtainMessage = this.handler.obtainMessage();
        Entity entity = new Entity();
        entity.process = i;
        entity.imageView = imageView;
        entity.lister = displayerLister;
        obtainMessage.obj = entity;
        obtainMessage.what = i;
        obtainMessage.sendToTarget();
    }

    protected void finish(Bitmap bitmap, ImageView imageView, DisplayerLister displayerLister) {
        if (displayerLister == null) {
            return;
        }
        Message obtainMessage = this.handler.obtainMessage();
        Entity entity = new Entity();
        entity.bitmap = bitmap;
        entity.imageView = imageView;
        entity.lister = displayerLister;
        obtainMessage.obj = entity;
        obtainMessage.what = 3;
        obtainMessage.sendToTarget();
    }

    protected void fail(ImageView imageView, DisplayerLister displayerLister) {
        if (displayerLister == null) {
            return;
        }
        Message obtainMessage = this.handler.obtainMessage();
        Entity entity = new Entity();
        entity.imageView = imageView;
        entity.lister = displayerLister;
        obtainMessage.obj = entity;
        obtainMessage.what = 2;
        obtainMessage.sendToTarget();
    }

    protected void start(ImageView imageView, DisplayerLister displayerLister) {
        if (displayerLister == null) {
            return;
        }
        Message obtainMessage = this.handler.obtainMessage();
        Entity entity = new Entity();
        entity.imageView = imageView;
        entity.lister = displayerLister;
        obtainMessage.obj = entity;
        obtainMessage.what = 0;
        obtainMessage.sendToTarget();
    }

    protected ImageCache getImageCache() {
        return this.mImageCache;
    }

    public static void cancelWork(ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            bitmapWorkerTask.cancel(true);
            Object obj = bitmapWorkerTask.mData;
            Ioc.getIoc().getLogger().d("cancelWork - cancelled work for " + obj);
        }
    }

    public static boolean cancelPotentialWork(Object obj, ImageView imageView) {
        BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            Object obj2 = bitmapWorkerTask.mData;
            if (obj2 != null && obj2.equals(obj)) {
                return false;
            }
            bitmapWorkerTask.cancel(true);
            Ioc.getIoc().getLogger().d("cancelPotentialWork - cancelled work for " + obj);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AsyncDrawable) {
            return ((AsyncDrawable) drawable).getBitmapWorkerTask();
        }
        return null;
    }

    /* loaded from: classes.dex */
    private class BitmapWorkerTask extends AsyncTask<Void, Void, BitmapDrawable> {
        private final WeakReference<ImageView> imageViewReference;
        protected final DisplayerLister lister;
        private Object mData;

        public BitmapWorkerTask(Object obj, ImageView imageView, DisplayerLister displayerLister) {
            this.mData = obj;
            this.imageViewReference = new WeakReference<>(imageView);
            this.lister = displayerLister;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00a4  */
        @Override // com.android.pc.ioc.image.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.graphics.drawable.BitmapDrawable doInBackground(java.lang.Void... r6) {
            /*
                r5 = this;
                com.android.pc.ioc.app.Ioc r6 = com.android.pc.ioc.app.Ioc.getIoc()
                com.android.pc.util.Logger r6 = r6.getLogger()
                java.lang.String r0 = "doInBackground - starting work"
                r6.d(r0)
                java.lang.Object r6 = r5.mData
                java.lang.String r6 = java.lang.String.valueOf(r6)
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                java.lang.Object r0 = com.android.pc.ioc.image.ImageWorker.access$200(r0)
                monitor-enter(r0)
            L1a:
                com.android.pc.ioc.image.ImageWorker r1 = com.android.pc.ioc.image.ImageWorker.this     // Catch: java.lang.Throwable -> Lcc
                boolean r1 = r1.mPauseWork     // Catch: java.lang.Throwable -> Lcc
                if (r1 == 0) goto L30
                boolean r1 = r5.isCancelled()     // Catch: java.lang.Throwable -> Lcc
                if (r1 != 0) goto L30
                com.android.pc.ioc.image.ImageWorker r1 = com.android.pc.ioc.image.ImageWorker.this     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lcc
                java.lang.Object r1 = com.android.pc.ioc.image.ImageWorker.access$200(r1)     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lcc
                r1.wait()     // Catch: java.lang.InterruptedException -> L1a java.lang.Throwable -> Lcc
                goto L1a
            L30:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcc
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                com.android.pc.ioc.image.ImageCache r0 = com.android.pc.ioc.image.ImageWorker.access$300(r0)
                r1 = 0
                if (r0 == 0) goto L7b
                boolean r0 = r5.isCancelled()
                if (r0 != 0) goto L7b
                android.widget.ImageView r0 = r5.getAttachedImageView()
                if (r0 == 0) goto L7b
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                boolean r0 = com.android.pc.ioc.image.ImageWorker.access$400(r0)
                if (r0 != 0) goto L7b
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                com.android.pc.ioc.image.ImageCache r0 = com.android.pc.ioc.image.ImageWorker.access$300(r0)
                com.android.pc.ioc.image.ImageWorker r2 = com.android.pc.ioc.image.ImageWorker.this
                int r2 = r2.getW()
                com.android.pc.ioc.image.ImageWorker r3 = com.android.pc.ioc.image.ImageWorker.this
                int r3 = r3.getH()
                com.android.pc.ioc.image.ImageWorker r4 = com.android.pc.ioc.image.ImageWorker.this
                android.graphics.Bitmap r0 = r0.getBitmapFromDiskCache(r6, r2, r3, r4)
                if (r0 == 0) goto L7c
                android.graphics.Bitmap$Config r2 = r0.getConfig()
                if (r2 != 0) goto L7c
                com.android.pc.ioc.app.Ioc r0 = com.android.pc.ioc.app.Ioc.getIoc()
                com.android.pc.util.Logger r0 = r0.getLogger()
                java.lang.String r2 = "图片解析错误-----重新下载"
                r0.d(r2)
            L7b:
                r0 = r1
            L7c:
                if (r0 != 0) goto La2
                boolean r2 = r5.isCancelled()
                if (r2 != 0) goto La2
                android.widget.ImageView r2 = r5.getAttachedImageView()
                if (r2 == 0) goto La2
                com.android.pc.ioc.image.ImageWorker r2 = com.android.pc.ioc.image.ImageWorker.this
                boolean r2 = com.android.pc.ioc.image.ImageWorker.access$400(r2)
                if (r2 != 0) goto La2
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                java.lang.Object r2 = r5.mData
                java.lang.ref.WeakReference<android.widget.ImageView> r3 = r5.imageViewReference
                java.lang.Object r3 = r3.get()
                android.widget.ImageView r3 = (android.widget.ImageView) r3
                android.graphics.Bitmap r0 = r0.processBitmap(r2, r3)
            La2:
                if (r0 == 0) goto Lbe
                com.android.pc.ioc.image.RecyclingBitmapDrawable r1 = new com.android.pc.ioc.image.RecyclingBitmapDrawable
                com.android.pc.ioc.image.ImageWorker r2 = com.android.pc.ioc.image.ImageWorker.this
                android.content.res.Resources r2 = r2.mResources
                r1.<init>(r2, r0)
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                com.android.pc.ioc.image.ImageCache r0 = com.android.pc.ioc.image.ImageWorker.access$300(r0)
                if (r0 == 0) goto Lbe
                com.android.pc.ioc.image.ImageWorker r0 = com.android.pc.ioc.image.ImageWorker.this
                com.android.pc.ioc.image.ImageCache r0 = com.android.pc.ioc.image.ImageWorker.access$300(r0)
                r0.addBitmapToCache(r6, r1)
            Lbe:
                com.android.pc.ioc.app.Ioc r6 = com.android.pc.ioc.app.Ioc.getIoc()
                com.android.pc.util.Logger r6 = r6.getLogger()
                java.lang.String r0 = "doInBackground - finished work"
                r6.d(r0)
                return r1
            Lcc:
                r6 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcc
                goto Ld0
            Lcf:
                throw r6
            Ld0:
                goto Lcf
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.image.ImageWorker.BitmapWorkerTask.doInBackground(java.lang.Void[]):android.graphics.drawable.BitmapDrawable");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.image.AsyncTask
        public void onPostExecute(BitmapDrawable bitmapDrawable) {
            if (isCancelled() || ImageWorker.this.mExitTasksEarly) {
                bitmapDrawable = null;
            }
            ImageView attachedImageView = getAttachedImageView();
            if (bitmapDrawable != null && attachedImageView != null) {
                Ioc.getIoc().getLogger().d("onPostExecute - setting bitmap");
                ImageWorker.this.setImageDrawable(attachedImageView, bitmapDrawable);
                if (bitmapDrawable.getBitmap() != null) {
                    ImageWorker.this.finish(bitmapDrawable.getBitmap(), attachedImageView, this.lister);
                }
            }
            if (bitmapDrawable == null) {
                ImageWorker.this.fail(attachedImageView, this.lister);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.image.AsyncTask
        public void onCancelled(BitmapDrawable bitmapDrawable) {
            super.onCancelled((BitmapWorkerTask) bitmapDrawable);
            synchronized (ImageWorker.this.mPauseWorkLock) {
                ImageWorker.this.mPauseWorkLock.notifyAll();
            }
        }

        private ImageView getAttachedImageView() {
            ImageView imageView = this.imageViewReference.get();
            if (this == ImageWorker.getBitmapWorkerTask(imageView)) {
                return imageView;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.pc.ioc.image.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate((Object[]) voidArr);
        }
    }

    /* loaded from: classes.dex */
    private static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources resources, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
            super(resources, bitmap);
            this.bitmapWorkerTaskReference = new WeakReference<>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return this.bitmapWorkerTaskReference.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (this.mFadeInBitmap) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(R.color.transparent), drawable});
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(200);
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public void setPauseWork(boolean z) {
        synchronized (this.mPauseWorkLock) {
            this.mPauseWork = z;
            if (!z) {
                this.mPauseWorkLock.notifyAll();
            }
        }
    }

    public void setImageSize(int i, int i2) {
        HashMap<String, Integer> displayMetrics = Handler_System.getDisplayMetrics();
        if (i == 0) {
            i = displayMetrics.get(Handler_System.systemWidth).intValue();
        }
        if (i2 == 0) {
            i2 = displayMetrics.get(Handler_System.systemHeight).intValue();
        }
        this.mImageWidth = i;
        this.mImageHeight = i2;
    }

    public void setImageSize(int i) {
        setImageSize(i, i);
    }

    private Bitmap processBitmap(int i) {
        Ioc.getIoc().getLogger().d("图片下载开始 - " + i);
        return decodeSampledBitmapFromResource(this.mResources, i, this.mImageWidth, this.mImageHeight, getImageCache());
    }

    protected Bitmap processBitmap(Object obj, ImageView imageView) {
        return processBitmap(Integer.parseInt(String.valueOf(obj)));
    }

    public Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int i2, int i3, ImageCache imageCache) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        if (Utils.hasHoneycomb()) {
            addInBitmapOptions(options, imageCache);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:(2:7|8)|(7:13|14|15|16|(1:18)|19|(2:36|37)(2:(3:25|26|27)|35))|42|14|15|16|(0)|19|(1:21)|36|37) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0057, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0058, code lost:
    
        r4 = r9;
        r9 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x005e, code lost:
    
        r9.printStackTrace();
        r9 = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap decodeSampledBitmapFromFile(java.lang.String r9, java.lang.String r10, int r11, int r12, com.android.pc.ioc.image.ImageCache r13) {
        /*
            r8 = this;
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            com.android.pc.ioc.image.ImageLoadManager r1 = com.android.pc.ioc.image.ImageLoadManager.instance()
            com.android.pc.ioc.image.ImageLoadManager$Coding r1 = r1.getCoding()
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L63
            boolean r4 = r8.isDecode
            if (r4 != 0) goto L19
            goto L63
        L19:
            if (r4 == 0) goto L66
            if (r1 == 0) goto L66
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L5c
            r4.<init>(r10)     // Catch: java.lang.Exception -> L5c
            java.lang.String r5 = r9.toLowerCase()     // Catch: java.lang.Exception -> L5c
            java.lang.String r6 = ".jpg"
            int r5 = r5.indexOf(r6)     // Catch: java.lang.Exception -> L5c
            r6 = -1
            if (r5 != r6) goto L46
            java.lang.String r9 = r9.toLowerCase()     // Catch: java.lang.Exception -> L5c
            java.lang.String r5 = ".jpeg"
            int r9 = r9.indexOf(r5)     // Catch: java.lang.Exception -> L5c
            if (r9 == r6) goto L3c
            goto L46
        L3c:
            int r9 = r4.available()     // Catch: java.lang.Exception -> L5c
            long r5 = (long) r9     // Catch: java.lang.Exception -> L5c
            byte[] r9 = r1.decodePNG(r5, r4)     // Catch: java.lang.Exception -> L5c
            goto L4f
        L46:
            int r9 = r4.available()     // Catch: java.lang.Exception -> L5c
            long r5 = (long) r9     // Catch: java.lang.Exception -> L5c
            byte[] r9 = r1.decodeJPG(r5, r4)     // Catch: java.lang.Exception -> L5c
        L4f:
            r4.close()     // Catch: java.lang.Exception -> L57
            int r4 = r9.length     // Catch: java.lang.Exception -> L57
            android.graphics.BitmapFactory.decodeByteArray(r9, r3, r4, r0)     // Catch: java.lang.Exception -> L57
            goto L67
        L57:
            r4 = move-exception
            r7 = r4
            r4 = r9
            r9 = r7
            goto L5e
        L5c:
            r9 = move-exception
            r4 = r2
        L5e:
            r9.printStackTrace()
            r9 = r4
            goto L67
        L63:
            android.graphics.BitmapFactory.decodeFile(r10, r0)
        L66:
            r9 = r2
        L67:
            int r11 = r8.calculateInSampleSize(r0, r11, r12)
            r0.inSampleSize = r11
            boolean r11 = com.android.pc.ioc.image.Utils.hasHoneycomb()
            if (r11 == 0) goto L76
            r8.addInBitmapOptions(r0, r13)
        L76:
            r0.inJustDecodeBounds = r3
            if (r1 == 0) goto L92
            boolean r11 = r8.isDecode
            if (r11 != 0) goto L7f
            goto L92
        L7f:
            if (r11 == 0) goto L91
            if (r1 == 0) goto L91
            int r10 = r9.length     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r9, r3, r10, r0)     // Catch: java.lang.Throwable -> L89 java.lang.Exception -> L8b
            return r9
        L89:
            r9 = move-exception
            goto L90
        L8b:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L89
            goto L91
        L90:
            throw r9
        L91:
            return r2
        L92:
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeFile(r10, r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.image.ImageWorker.decodeSampledBitmapFromFile(java.lang.String, java.lang.String, int, int, com.android.pc.ioc.image.ImageCache):android.graphics.Bitmap");
    }

    public Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int i, int i2, ImageCache imageCache) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        if (Utils.hasHoneycomb()) {
            addInBitmapOptions(options, imageCache);
        }
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    private void addInBitmapOptions(BitmapFactory.Options options, ImageCache imageCache) {
        Bitmap bitmapFromReusableSet;
        options.inMutable = true;
        if (imageCache == null || (bitmapFromReusableSet = imageCache.getBitmapFromReusableSet(options)) == null) {
            return;
        }
        options.inBitmap = bitmapFromReusableSet;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
            for (long j = (i4 * i3) / i5; j > i * i2 * 2; j /= 2) {
                i5 *= 2;
            }
        }
        return i5;
    }

    protected int getW() {
        return this.mImageWidth;
    }

    protected int getH() {
        return this.mImageHeight;
    }
}
