package com.android.pc.ioc.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ImageView;
import com.alipay.sdk.m.h.a;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.Handler_Network;
import com.android.pc.util.Handler_System;
import java.io.File;

/* loaded from: classes.dex */
public class ImageDownloader extends ImageWorker {
    public ImageDownloader(Context context, int i, int i2) {
        super(context, i, i2);
        init(context);
    }

    public ImageDownloader(Context context, int i) {
        super(context, i);
        init(context);
    }

    public ImageDownloader(Context context) {
        super(context, Handler_System.getDisplayMetrics().get(Handler_System.systemWidth).intValue());
        init(context);
    }

    private void init(Context context) {
        checkConnection(context);
    }

    private void checkConnection(Context context) {
        if (Handler_Network.isNetworkAvailable(context)) {
            return;
        }
        Ioc.getIoc().getLogger().e("网络连接失败");
    }

    private Bitmap processBitmap(String str, ImageView imageView) {
        File file;
        Ioc.getIoc().getLogger().d("图片下载开始 - " + str);
        if (str.startsWith(a.q)) {
            file = ImageCache.getFromFileCache(ImageCache.hashKeyForDisk(str));
            start(imageView, this.lister);
            downloadUrlToStream(str, file, imageView);
        } else {
            file = new File(str);
        }
        if (file == null || !file.exists()) {
            return null;
        }
        return decodeSampledBitmapFromFile(str, file.getPath(), this.mImageWidth, this.mImageHeight, getImageCache());
    }

    @Override // com.android.pc.ioc.image.ImageWorker
    protected Bitmap processBitmap(Object obj, ImageView imageView) {
        if (obj.getClass() == String.class) {
            return processBitmap(String.valueOf(obj), imageView);
        }
        if (obj.getClass() == Integer.class) {
            return super.processBitmap(Integer.valueOf(obj.toString()), imageView);
        }
        return processBitmap(String.valueOf(obj), imageView);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009b A[Catch: IOException -> 0x009e, TRY_LEAVE, TryCatch #7 {IOException -> 0x009e, blocks: (B:58:0x0096, B:52:0x009b), top: B:57:0x0096 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean downloadUrlToStream(java.lang.String r10, java.io.File r11, android.widget.ImageView r12) {
        /*
            r9 = this;
            disableConnectionReuseIfNecessary()
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5f
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5f
            java.net.URLConnection r10 = r2.openConnection()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5f
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5f
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L56
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L56
            int r11 = r10.getContentLength()     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            java.io.InputStream r0 = r10.getInputStream()     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r3]     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            r5 = 0
        L22:
            int r6 = r0.read(r4, r1, r3)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            r7 = -1
            if (r6 != r7) goto L38
            r11 = 1
            if (r10 == 0) goto L2f
            r10.disconnect()
        L2f:
            r2.close()     // Catch: java.io.IOException -> L37
            if (r0 == 0) goto L37
            r0.close()     // Catch: java.io.IOException -> L37
        L37:
            return r11
        L38:
            r2.write(r4, r1, r6)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            int r5 = r5 + r6
            if (r11 <= 0) goto L22
            int r6 = r5 * 100
            int r6 = r6 / r11
            com.android.pc.ioc.image.DisplayerLister r7 = r9.lister     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            r9.process(r6, r12, r7)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4c
            goto L22
        L47:
            r11 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L8f
        L4c:
            r11 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L62
        L51:
            r11 = move-exception
            r2 = r0
            r0 = r10
            r10 = r2
            goto L8f
        L56:
            r11 = move-exception
            r2 = r0
            r0 = r10
            r10 = r2
            goto L62
        L5b:
            r11 = move-exception
            r10 = r0
            r2 = r10
            goto L8f
        L5f:
            r11 = move-exception
            r10 = r0
            r2 = r10
        L62:
            com.android.pc.ioc.app.Ioc r12 = com.android.pc.ioc.app.Ioc.getIoc()     // Catch: java.lang.Throwable -> L8e
            com.android.pc.util.Logger r12 = r12.getLogger()     // Catch: java.lang.Throwable -> L8e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e
            r3.<init>()     // Catch: java.lang.Throwable -> L8e
            java.lang.String r4 = "图片下载出错 - Error in downloadBitmap - "
            r3.append(r4)     // Catch: java.lang.Throwable -> L8e
            r3.append(r11)     // Catch: java.lang.Throwable -> L8e
            java.lang.String r11 = r3.toString()     // Catch: java.lang.Throwable -> L8e
            r12.e(r11)     // Catch: java.lang.Throwable -> L8e
            if (r0 == 0) goto L83
            r0.disconnect()
        L83:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.io.IOException -> L8d
        L88:
            if (r10 == 0) goto L8d
            r10.close()     // Catch: java.io.IOException -> L8d
        L8d:
            return r1
        L8e:
            r11 = move-exception
        L8f:
            if (r0 == 0) goto L94
            r0.disconnect()
        L94:
            if (r2 == 0) goto L99
            r2.close()     // Catch: java.io.IOException -> L9e
        L99:
            if (r10 == 0) goto L9e
            r10.close()     // Catch: java.io.IOException -> L9e
        L9e:
            goto La0
        L9f:
            throw r11
        La0:
            goto L9f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.image.ImageDownloader.downloadUrlToStream(java.lang.String, java.io.File, android.widget.ImageView):boolean");
    }

    public static void disableConnectionReuseIfNecessary() {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }
}
