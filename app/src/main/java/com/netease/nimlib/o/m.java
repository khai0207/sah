package com.netease.nimlib.o;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* compiled from: MD5.java */
/* loaded from: classes.dex */
public class m {
    public static String a(String str) {
        if (str == null || str.trim().length() < 1) {
            return null;
        }
        try {
            return a(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String a(byte[] bArr) {
        try {
            return j.a(MessageDigest.getInstance("MD5").digest(bArr));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String b(String str) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr = new byte[4096];
        String str2 = null;
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            com.netease.nimlib.n.e.a(com.netease.nimlib.n.b.i.kRead, str, "MD5#getStreamMD5 failed,exception = " + th);
                            th.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return str2;
                        } catch (Throwable th2) {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                }
                bufferedInputStream.close();
                str2 = j.a(messageDigest.digest());
                bufferedInputStream.close();
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    public static String a(Context context, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor;
        FileInputStream fileInputStream;
        MessageDigest messageDigest;
        byte[] bArr = new byte[4096];
        String str = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            } catch (Throwable th) {
                th = th;
                parcelFileDescriptor = null;
                fileInputStream = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parcelFileDescriptor == null) {
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return "";
        }
        try {
            fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        com.netease.nimlib.log.b.e("MD5", "getUriMD5 is error", th);
                        com.netease.nimlib.n.e.a(com.netease.nimlib.n.b.i.kRead, uri != null ? uri.toString() : "uri is null", "MD5#getUriMD5 failed,exception = " + th);
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (parcelFileDescriptor != null) {
                            parcelFileDescriptor.close();
                        }
                        return str;
                    } catch (Throwable th3) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                throw th3;
                            }
                        }
                        if (parcelFileDescriptor != null) {
                            parcelFileDescriptor.close();
                        }
                        throw th3;
                    }
                }
            }
            fileInputStream.close();
            str = j.a(messageDigest.digest());
            fileInputStream.close();
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
        return str;
    }
}
