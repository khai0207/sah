package com.netease.nimlib.sdk.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.netease.nimlib.log.b;

/* loaded from: classes.dex */
public class UriUtils {
    private static final String TAG = "UriUtils";

    private UriUtils() {
    }

    public static Uri string2Uri(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Uri.parse(str);
        } catch (Throwable th) {
            b.e(TAG, "stringToUri failed，uriString = " + str, th);
            return null;
        }
    }

    public static boolean isFileUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return TransferTable.COLUMN_FILE.equals(Uri.parse(str).getScheme());
        } catch (Throwable th) {
            b.e(TAG, "File URI not valid", th);
            return false;
        }
    }

    public static boolean isFileUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        return TransferTable.COLUMN_FILE.equals(uri.getScheme());
    }

    public static boolean isContentUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return "content".equals(Uri.parse(str).getScheme());
        } catch (Throwable th) {
            b.e(TAG, "Content URI not valid", th);
            return false;
        }
    }

    public static boolean isContentUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        return "content".equals(uri.getScheme());
    }

    public static boolean isFileOrContentUri(String str) {
        boolean z;
        boolean z2;
        Uri parse;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            parse = Uri.parse(str);
            z = TransferTable.COLUMN_FILE.equals(parse.getScheme());
        } catch (Throwable th) {
            th = th;
            z = false;
        }
        try {
            z2 = "content".equals(parse.getScheme());
        } catch (Throwable th2) {
            th = th2;
            b.e(TAG, "Content URI not valid", th);
            z2 = false;
            if (z) {
            }
        }
        return !z || z2;
    }

    public static boolean isFileOrContentUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        return TransferTable.COLUMN_FILE.equals(uri.getScheme()) || "content".equals(uri.getScheme());
    }

    public static String getFileExtensionFromUri(Context context, Uri uri) {
        String path;
        if (context == null || uri == null) {
            return "";
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
        if (extensionFromMimeType == null && (path = uri.getPath()) != null) {
            extensionFromMimeType = MimeTypeMap.getFileExtensionFromUrl(path);
        }
        return TextUtils.isEmpty(extensionFromMimeType) ? "" : extensionFromMimeType;
    }

    public static long getFileSizeFromUri(Context context, Uri uri) {
        if (context != null && uri != null) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            r0 = query.moveToFirst() ? query.getLong(query.getColumnIndex("_size")) : 0L;
            query.close();
        }
        return r0;
    }

    public static boolean isFileExists(Context context, Uri uri) {
        if (context != null && uri != null) {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (query != null && query.moveToFirst()) {
                query.close();
                return true;
            }
            if (query != null) {
                query.close();
            }
        }
        return false;
    }

    public static Bitmap generateVideoThumbnail(Context context, Uri uri, int i, int i2) {
        StringBuilder sb;
        Bitmap bitmap = null;
        if (context == null || uri == null) {
            return null;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, uri);
            bitmap = mediaMetadataRetriever.getFrameAtTime(0L, 2);
            if (bitmap != null) {
                bitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
            }
        } catch (Throwable th) {
            try {
                b.e(TAG, "generateVideoThumbnail error:" + th, th);
                try {
                    mediaMetadataRetriever.release();
                } catch (Throwable th2) {
                    th = th2;
                    sb = new StringBuilder();
                    sb.append("MediaMetadataRetriever release error:");
                    sb.append(th);
                    b.e(TAG, sb.toString(), th);
                    return bitmap;
                }
            } catch (Throwable th3) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Throwable th4) {
                    b.e(TAG, "MediaMetadataRetriever release error:" + th4, th4);
                }
                throw th3;
            }
        }
        try {
            mediaMetadataRetriever.release();
        } catch (Throwable th5) {
            th = th5;
            sb = new StringBuilder();
            sb.append("MediaMetadataRetriever release error:");
            sb.append(th);
            b.e(TAG, sb.toString(), th);
            return bitmap;
        }
        return bitmap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004f, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004c, code lost:
    
        if (r9 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFileNameFromUri(android.content.Context r9, android.net.Uri r10) {
        /*
            java.lang.String r0 = "_display_name"
            r1 = 0
            if (r9 == 0) goto L57
            if (r10 != 0) goto L8
            goto L57
        L8:
            r2 = 1
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L34
            r2 = 0
            r5[r2] = r0     // Catch: java.lang.Throwable -> L34
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L34
            r6 = 0
            r7 = 0
            r8 = 0
            r4 = r10
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L34
            if (r9 == 0) goto L2e
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L2c
            if (r10 == 0) goto L2e
            int r10 = r9.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L2c
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> L2c
            r1 = r10
            goto L2e
        L2c:
            r10 = move-exception
            goto L36
        L2e:
            if (r9 == 0) goto L4f
        L30:
            r9.close()
            goto L4f
        L34:
            r10 = move-exception
            r9 = r1
        L36:
            java.lang.String r0 = "UriUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r2.<init>()     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "getFileNameFromUri error:"
            r2.append(r3)     // Catch: java.lang.Throwable -> L50
            r2.append(r10)     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L50
            com.netease.nimlib.log.b.e(r0, r2, r10)     // Catch: java.lang.Throwable -> L50
            if (r9 == 0) goto L4f
            goto L30
        L4f:
            return r1
        L50:
            r10 = move-exception
            if (r9 == 0) goto L56
            r9.close()
        L56:
            throw r10
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.sdk.util.UriUtils.getFileNameFromUri(android.content.Context, android.net.Uri):java.lang.String");
    }
}
