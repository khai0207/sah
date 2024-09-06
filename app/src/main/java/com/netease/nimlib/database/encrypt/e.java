package com.netease.nimlib.database.encrypt;

import net.sqlcipher.Cursor;
import net.sqlcipher.CursorWrapper;

/* compiled from: LockSafeCursor.java */
/* loaded from: classes.dex */
public class e extends CursorWrapper {
    private Cursor a;

    private e(Cursor cursor) {
        super(cursor);
        this.a = cursor;
    }

    public static e a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new e(cursor);
    }
}
