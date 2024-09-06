package com.netease.nimlib.session;

import android.database.Cursor;
import android.util.Pair;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class MsgDBHelperUtils extends MsgDBHelperCursorTransfer {

    /* loaded from: classes.dex */
    interface IGetString<T> {
        String getString(T t);
    }

    static boolean isSameRecentContact(RecentContact recentContact, RecentContact recentContact2) {
        String contactId = recentContact.getContactId();
        String contactId2 = recentContact2.getContactId();
        return contactId == null ? contactId2 == null && recentContact.getSessionType() == recentContact2.getSessionType() : contactId.equals(contactId2) && recentContact.getSessionType() == recentContact2.getSessionType();
    }

    static String buildTypeSql(List<SystemMessageType> list) {
        StringBuilder sb = new StringBuilder();
        for (SystemMessageType systemMessageType : list) {
            sb.append("'");
            sb.append(systemMessageType.getValue());
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static String buildSessionIdsSql(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("'");
            sb.append(str);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static Pair<List<String>, List<String>> parseBitmap(byte[] bArr, List<String> list) {
        if (bArr == null) {
            return null;
        }
        boolean[] a = com.netease.nimlib.o.d.a(bArr);
        if (list == null || a == null || list.size() > a.length) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (a[i]) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    static <T> String toString(Collection<T> collection, IGetString<T> iGetString) {
        if (com.netease.nimlib.o.b.a((Collection) collection)) {
            return "";
        }
        if (iGetString == null) {
            iGetString = new IGetString() { // from class: com.netease.nimlib.session.-$$Lambda$E6tOhOSh2J7jc0HCiZ2msS4S9uo
                @Override // com.netease.nimlib.session.MsgDBHelperUtils.IGetString
                public final String getString(Object obj) {
                    return obj.toString();
                }
            };
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            sb.append(", ");
            sb.append(iGetString.getString(t));
        }
        return sb.substring(2);
    }

    static String dbPath(String str) {
        return com.netease.nimlib.a.a + "/" + str + "/msg.db";
    }

    static com.netease.nimlib.database.d database() {
        return com.netease.nimlib.database.f.a().g();
    }

    static Cursor rawQuery(String str) {
        try {
            return database().a(str, (String[]) null);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("rawQuery error", th);
            return null;
        }
    }

    static Cursor rawQuery(String str, String[] strArr) {
        try {
            return database().a(str, strArr);
        } catch (Throwable th) {
            com.netease.nimlib.log.b.d("raw query with selectionArgs error", th);
            return null;
        }
    }
}
