package com.netease.nimlib.friend;

import android.text.TextUtils;
import com.netease.nimlib.o.k;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.friend.model.Friend;
import com.netease.nimlib.sdk.friend.model.FriendChangedNotify;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: FriendHelper.java */
/* loaded from: classes.dex */
public class a {
    public static void a(String str, String str2) {
        b queryFriend = FriendDBHelper.queryFriend(str);
        if (queryFriend == null) {
            queryFriend = b.a(str);
        } else {
            queryFriend.a((Integer) 1);
            queryFriend.b((Integer) 1);
        }
        queryFriend.e(str2);
        FriendDBHelper.saveFriend(queryFriend);
        com.netease.nimlib.i.b.a(new FriendChangedNotify(queryFriend, (String) null));
    }

    public static void a(String str) {
        FriendDBHelper.deleteFriendInfo(str);
        com.netease.nimlib.i.b.a(new FriendChangedNotify((Friend) null, str));
    }

    public static void b(String str) {
        a(str, false);
    }

    public static void a(String str, boolean z) {
        FriendDBHelper.deleteFriend(str, z);
        com.netease.nimlib.i.b.a(new FriendChangedNotify((Friend) null, str));
    }

    public static void a(c cVar) {
        b queryFriend;
        if (cVar == null || cVar.a() == 0 || (queryFriend = FriendDBHelper.queryFriend(cVar.c(4))) == null) {
            return;
        }
        for (int i = 0; i < cVar.a(); i++) {
            int a = cVar.a(i);
            switch (a) {
                case 4:
                    queryFriend.b(cVar.c(a));
                    break;
                case 5:
                    queryFriend.a(Integer.valueOf(cVar.d(i)));
                    break;
                case 6:
                    queryFriend.b(Integer.valueOf(cVar.d(i)));
                    break;
                case 7:
                    queryFriend.a(Byte.valueOf((byte) cVar.d(i)));
                    break;
                case 8:
                    queryFriend.c(cVar.c(a));
                    break;
                case 9:
                    queryFriend.b(Long.valueOf(cVar.e(i)));
                    break;
                case 10:
                    queryFriend.d(cVar.c(a));
                    break;
                case 11:
                    queryFriend.c(Long.valueOf(cVar.e(a)));
                    break;
                case 12:
                    queryFriend.a(Long.valueOf(cVar.e(a)));
                    break;
                case 13:
                    queryFriend.e(cVar.c(a));
                    break;
            }
        }
        FriendDBHelper.saveFriend(queryFriend);
        com.netease.nimlib.i.b.a(new FriendChangedNotify(queryFriend, (String) null));
    }

    public static String a(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            try {
                return new JSONObject(map).toString();
            } catch (Exception e) {
                com.netease.nimlib.log.b.f("FriendHelper", "FriendHelper getJsonStringFromMap exception, e=" + e.getMessage());
            }
        }
        return null;
    }

    public static Map<String, Object> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject a = k.a(str);
            Iterator<String> keys = a.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, a.get(next));
            }
            return hashMap;
        } catch (Exception e) {
            com.netease.nimlib.log.b.f("FriendHelper", "FriendHelper getMapFromJsonString exception, e=" + e.getMessage());
            return null;
        }
    }
}
