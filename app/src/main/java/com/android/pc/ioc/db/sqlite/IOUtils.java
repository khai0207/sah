package com.android.pc.ioc.db.sqlite;

import android.database.Cursor;
import java.io.Closeable;

/* loaded from: classes.dex */
public class IOUtils {
    private IOUtils() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }
}
