package com.netease.nimlib.ipc.cp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.netease.nimlib.biz.SyncCrossProcessDBHelper;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.c;
import com.netease.nimlib.ipc.cp.b.b;
import com.netease.nimlib.ipc.cp.c.a;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AbsContentProvider extends ContentProvider {
    private Map<String, b> a = new HashMap();

    public abstract b a(Context context, String str);

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (!c.O()) {
            return null;
        }
        a.C0036a a = a(uri);
        int a2 = a.a(getContext(), uri);
        if (a2 == 1) {
            if (a(a.a()).a(a.b())) {
                return a((AbsContentProvider) a(a.a()).a(a.b(), ""));
            }
            return null;
        }
        if (a2 == 2) {
            if (a(a.a()).a(a.b())) {
                return a((AbsContentProvider) Integer.valueOf(a(a.a()).a(a.b(), false) ? 1 : 0));
            }
            return null;
        }
        if (a2 == 3) {
            if (a(a.a()).a(a.b())) {
                return a((AbsContentProvider) Integer.valueOf(a(a.a()).a(a.b(), 0)));
            }
            return null;
        }
        if (a2 != 4) {
            if (a2 != 5) {
                return null;
            }
            return a((AbsContentProvider) Float.valueOf(a(a.a()).a(a.b(), 0.0f)));
        }
        String b = a.b();
        String a3 = a.a();
        if (TextUtils.equals(a3, SyncCrossProcessDBHelper.getSpName())) {
            l.H();
            boolean I = l.I();
            boolean i = l.i(b);
            if (I && !i) {
                a(a3).b(b, 0L);
                l.h(b);
                com.netease.nimlib.log.b.v("SqlCipherResetTimetag save:" + b + " " + c.n());
            }
            if (c.i().clearTimeTagAtBeginning && !l.k(b)) {
                if (com.netease.nimlib.e.a.a(c.g())) {
                    if (b.startsWith("k_tmember_tt_tag_")) {
                        a(a3).b(b, 0L);
                        l.j(b);
                    }
                } else {
                    a(a3).b(b, 0L);
                    l.j(b);
                }
            }
        }
        long a4 = a(a.a()).a(a.b(), 0L);
        if (a4 == -1) {
            return null;
        }
        return a((AbsContentProvider) Long.valueOf(a4));
    }

    private <T> MatrixCursor a(T t) {
        MatrixCursor matrixCursor = new MatrixCursor(a.a, 1);
        matrixCursor.newRow().add(t);
        return matrixCursor;
    }

    private a.C0036a a(Uri uri) {
        if (uri == null || uri.getPathSegments().size() != 3) {
            throw new IllegalArgumentException("getKeyInfoFromUri uri is wrong : " + uri);
        }
        return new a.C0036a(uri.getPathSegments().get(1), uri.getPathSegments().get(2));
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (!c.O()) {
            return -1;
        }
        try {
            a.C0036a a = a(uri);
            switch (a.a(getContext(), uri)) {
                case 1:
                    return a(a.a(), contentValues);
                case 2:
                    b(a.a(), contentValues);
                    return 1;
                case 3:
                    c(a.a(), contentValues);
                    return 1;
                case 4:
                    d(a.a(), contentValues);
                    return 1;
                case 5:
                    e(a.a(), contentValues);
                    return 1;
                case 6:
                    f(a.a(), contentValues);
                    return 1;
                default:
                    throw new IllegalStateException("update error, as unsupported uri : " + uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (!c.O()) {
            return -1;
        }
        int a = a.a(getContext(), uri);
        if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5) {
            a.C0036a a2 = a(uri);
            a(a2.a()).b(a2.b());
            return 0;
        }
        throw new IllegalStateException("unsupported uri : " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("insert unsupported!!!");
    }

    private int a(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        return a(str).c(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsString("value"));
    }

    private void b(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        a(str).b(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsBoolean("value").booleanValue());
    }

    private void c(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        a(str).b(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsInteger("value").intValue());
    }

    private void d(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        a(str).b(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsLong("value").longValue());
    }

    private void e(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        a(str).b(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsFloat("value").floatValue());
    }

    private void f(String str, ContentValues contentValues) {
        if (contentValues == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        a(str).b(contentValues.getAsString(TransferTable.COLUMN_KEY), contentValues.getAsString("value"));
    }

    protected b a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("getDataHandler name is null!!!");
        }
        if (this.a.get(str) == null) {
            this.a.put(str, a(getContext(), str));
        }
        return this.a.get(str);
    }
}
