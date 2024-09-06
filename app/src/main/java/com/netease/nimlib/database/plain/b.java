package com.netease.nimlib.database.plain;

import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;

/* compiled from: LockSafeCursor.java */
/* loaded from: classes.dex */
public class b extends CursorWrapper {
    private Cursor a;

    private b(Cursor cursor) {
        super(cursor);
        this.a = cursor;
    }

    public static b a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new b(cursor);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getCount() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getCount();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return i;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getPosition() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getPosition();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return i;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean move(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.move(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return false;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToPosition(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.moveToPosition(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return false;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToFirst() {
        boolean z = false;
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.moveToFirst();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return z;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToLast() {
        boolean z = false;
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.moveToLast();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return z;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToNext() {
        boolean z = false;
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.moveToNext();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return z;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToPrevious() {
        boolean z = false;
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.moveToPrevious();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return z;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndex(String str) {
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.getColumnIndex(str);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return -1;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.getColumnIndexOrThrow(str);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return -1;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getColumnName(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getColumnName(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return null;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String[] getColumnNames() {
        for (int i = 0; i < 3; i++) {
            try {
                return this.a.getColumnNames();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return null;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnCount() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getColumnCount();
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return i;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public byte[] getBlob(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getBlob(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return null;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getString(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getString(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return null;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                this.a.copyStringToBuffer(i, charArrayBuffer);
            } catch (RuntimeException e) {
                if (a(e)) {
                    continue;
                } else if (!b(e)) {
                    throw e;
                }
            }
        }
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public short getShort(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getShort(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return (short) 0;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getInt(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getInt(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return 0;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public long getLong(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getLong(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return 0L;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public float getFloat(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getFloat(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return 0.0f;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public double getDouble(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                return this.a.getDouble(i);
            } catch (RuntimeException e) {
                if (!a(e) && !b(e)) {
                    throw e;
                }
            }
        }
        return 0.0d;
    }

    private static final boolean a(Exception exc) {
        com.netease.nimlib.log.b.e("db", "plain isSQLiteDatabaseLockedException exception", exc);
        boolean z = false;
        if (exc instanceof SQLiteException) {
            String message = exc.getMessage();
            if (!TextUtils.isEmpty(message) && message.contains("lock")) {
                z = true;
            }
            if (z) {
                com.netease.nimlib.log.b.e("db", "query locked!");
            }
        }
        return z;
    }

    private static final boolean b(Exception exc) {
        com.netease.nimlib.log.b.e("db", "plain isSQLiteDatabaseClosed exception", exc);
        boolean z = false;
        if (exc instanceof IllegalStateException) {
            String message = exc.getMessage();
            if (!TextUtils.isEmpty(message) && message.contains("closed")) {
                z = true;
            }
            if (z) {
                com.netease.nimlib.log.b.e("db", "connection pool has been closed!");
            }
        }
        return z;
    }
}
