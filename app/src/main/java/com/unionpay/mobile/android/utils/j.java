package com.unionpay.mobile.android.utils;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public final class j {
    private static boolean a = false;
    private static int b = Integer.MAX_VALUE;

    private static int a(int i, String str, String str2) {
        int i2 = 0;
        if (str != null && str2 != null) {
            if (i == 2) {
                i2 = Log.v(str, str2);
            } else if (i == 3) {
                i2 = Log.d(str, str2);
            } else if (i == 4) {
                i2 = Log.i(str, str2);
            } else if (i == 5) {
                i2 = Log.w(str, str2);
            } else if (i == 6) {
                i2 = Log.e(str, str2);
            }
            if (a) {
                String str3 = "[ ERROR ] " + str + ":" + str2;
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "upmp_log.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write((str3 + "\n").getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return i2;
    }

    public static int a(String str, String str2) {
        if (b > 3) {
            return 0;
        }
        a(3, str, str2);
        return 0;
    }

    public static int b(String str, String str2) {
        if (b > 4) {
            return 0;
        }
        a(4, str, str2);
        return 0;
    }

    public static int c(String str, String str2) {
        if (b <= 6) {
            return a(6, str, str2);
        }
        return 0;
    }
}
