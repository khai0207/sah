package com.netease.nimlib.o;

import android.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import com.netease.nimlib.sdk.util.UriUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: BitmapDecoder.java */
/* loaded from: classes.dex */
public class c {
    public static int[] a(Context context, Uri uri) {
        if (context == null) {
            return new int[]{0, 0};
        }
        try {
            return a(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            com.netease.nimlib.log.b.a("decodeBound error uri = " + uri, e);
            return new int[]{0, 0};
        }
    }

    public static int[] a(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static int[] a(File file) {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    int[] a = a(fileInputStream2);
                    try {
                        fileInputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return a;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return new int[]{0, 0};
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void a(String str, String str2) {
        if (com.netease.nimlib.net.a.c.a.d(str2)) {
            return;
        }
        com.netease.nimlib.net.a.c.a.a(ThumbnailUtils.createVideoThumbnail(str, 1), str2, true);
    }

    public static void a(Uri uri, String str) {
        Context e;
        if (com.netease.nimlib.net.a.c.a.d(str) || (e = com.netease.nimlib.c.e()) == null) {
            return;
        }
        int dimensionPixelSize = e.getResources().getDimensionPixelSize(R.dimen.thumbnail_width);
        int dimensionPixelSize2 = e.getResources().getDimensionPixelSize(R.dimen.thumbnail_height);
        if (dimensionPixelSize == 0 || dimensionPixelSize2 == 0) {
            dimensionPixelSize = 512;
            dimensionPixelSize2 = 384;
        }
        com.netease.nimlib.net.a.c.a.a(UriUtils.generateVideoThumbnail(e, uri, dimensionPixelSize, dimensionPixelSize2), str, true);
    }
}
