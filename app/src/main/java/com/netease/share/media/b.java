package com.netease.share.media;

import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: MediaUtil.java */
/* loaded from: classes.dex */
public class b {
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x002e -> B:17:0x004f). Please report as a decompilation issue!!! */
    public static boolean a(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                        try {
                            byte[] bArr = new byte[2];
                            bufferedInputStream2.read(bArr, 0, 2);
                            if ((bArr[0] & 255) == 255) {
                                if ((bArr[1] & 246) == 240) {
                                    z = true;
                                }
                            }
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            bufferedInputStream = bufferedInputStream2;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return z;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0054 -> B:26:0x0075). Please report as a decompilation issue!!! */
    public static int b(String str) {
        int i = -1;
        if (a(str)) {
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                        try {
                            byte[] bArr = new byte[3];
                            bufferedInputStream2.read(bArr, 0, 3);
                            switch ((bArr[2] >> 2) & 15) {
                                case 0:
                                    i = 96000;
                                    break;
                                case 1:
                                    i = 88200;
                                    break;
                                case 2:
                                    i = 64000;
                                    break;
                                case 3:
                                    i = 48000;
                                    break;
                                case 4:
                                    i = 44100;
                                    break;
                                case 5:
                                    i = 32000;
                                    break;
                                case 6:
                                    i = ErrorCode.ERROR_TTS_INVALID_PARA;
                                    break;
                                case 7:
                                    i = 22050;
                                    break;
                                case 8:
                                    i = 16000;
                                    break;
                                case 9:
                                    i = ErrorCode.MSP_ERROR_HTTP_BASE;
                                    break;
                                case 10:
                                    i = 11025;
                                    break;
                                case 11:
                                    i = 8000;
                                    break;
                                case 12:
                                    i = 7350;
                                    break;
                            }
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            bufferedInputStream = bufferedInputStream2;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return i;
    }
}
