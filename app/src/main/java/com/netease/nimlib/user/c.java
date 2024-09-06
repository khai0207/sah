package com.netease.nimlib.user;

import android.text.TextUtils;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.biz.l;
import com.netease.nimlib.i.k;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.model.BlackListChangedNotify;
import com.netease.nimlib.sdk.friend.model.MuteListChangedNotify;
import com.netease.nimlib.session.t;
import com.netease.nimlib.session.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: UserHelper.java */
/* loaded from: classes.dex */
public class c {
    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        a(arrayList);
    }

    public static void a(List<String> list) {
        a(list, (k) null);
    }

    public static void a(List<String> list, k kVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() <= 500) {
            com.netease.nimlib.biz.d.l.b bVar = new com.netease.nimlib.biz.d.l.b(list);
            bVar.a(kVar);
            i.a().a(bVar);
            return;
        }
        b(list, new ArrayList(list.size()), new RequestCallback<ArrayList<b>>() { // from class: com.netease.nimlib.user.c.1
            AnonymousClass1() {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            /* renamed from: a */
            public void onSuccess(ArrayList<b> arrayList) {
                k kVar2 = k.this;
                if (kVar2 != null) {
                    kVar2.b(arrayList).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                k kVar2 = k.this;
                if (kVar2 != null) {
                    kVar2.a(i).b();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
                k kVar2 = k.this;
                if (kVar2 != null) {
                    kVar2.a(th).b();
                }
            }
        });
    }

    /* compiled from: UserHelper.java */
    /* renamed from: com.netease.nimlib.user.c$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements RequestCallback<ArrayList<b>> {
        AnonymousClass1() {
        }

        @Override // com.netease.nimlib.sdk.RequestCallback
        /* renamed from: a */
        public void onSuccess(ArrayList<b> arrayList) {
            k kVar2 = k.this;
            if (kVar2 != null) {
                kVar2.b(arrayList).b();
            }
        }

        @Override // com.netease.nimlib.sdk.RequestCallback
        public void onFailed(int i) {
            k kVar2 = k.this;
            if (kVar2 != null) {
                kVar2.a(i).b();
            }
        }

        @Override // com.netease.nimlib.sdk.RequestCallback
        public void onException(Throwable th) {
            k kVar2 = k.this;
            if (kVar2 != null) {
                kVar2.a(th).b();
            }
        }
    }

    public static void a(List<String> list, RequestCallback<ArrayList<b>> requestCallback) {
        b(list, new ArrayList(list.size()), requestCallback);
    }

    public static void b(List<String> list, ArrayList<b> arrayList, RequestCallback<ArrayList<b>> requestCallback) {
        if (list == null || list.isEmpty()) {
            return;
        }
        boolean z = list.size() > 500;
        i.a().a(new com.netease.nimlib.biz.g.b(new com.netease.nimlib.biz.d.l.b(list.subList(0, z ? 500 : list.size()))) { // from class: com.netease.nimlib.user.c.2
            final /* synthetic */ ArrayList a;
            final /* synthetic */ boolean b;
            final /* synthetic */ List c;
            final /* synthetic */ RequestCallback d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(com.netease.nimlib.biz.d.a aVar, ArrayList arrayList2, boolean z2, List list2, RequestCallback requestCallback2) {
                super(aVar);
                r2 = arrayList2;
                r3 = z2;
                r4 = list2;
                r5 = requestCallback2;
            }

            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                if (aVar.n()) {
                    ArrayList b = c.b((com.netease.nimlib.biz.e.m.b) aVar);
                    if (b != null && !b.isEmpty()) {
                        r2.addAll(b);
                    }
                    if (r3) {
                        List list2 = r4;
                        c.b(list2.subList(500, list2.size()), r2, r5);
                        return;
                    } else {
                        RequestCallback requestCallback2 = r5;
                        if (requestCallback2 != null) {
                            requestCallback2.onSuccess(r2);
                            return;
                        }
                        return;
                    }
                }
                RequestCallback requestCallback3 = r5;
                if (requestCallback3 != null) {
                    requestCallback3.onFailed(aVar.r());
                }
            }
        });
    }

    /* compiled from: UserHelper.java */
    /* renamed from: com.netease.nimlib.user.c$2 */
    /* loaded from: classes.dex */
    static class AnonymousClass2 extends com.netease.nimlib.biz.g.b {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ boolean b;
        final /* synthetic */ List c;
        final /* synthetic */ RequestCallback d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(com.netease.nimlib.biz.d.a aVar, ArrayList arrayList2, boolean z2, List list2, RequestCallback requestCallback2) {
            super(aVar);
            r2 = arrayList2;
            r3 = z2;
            r4 = list2;
            r5 = requestCallback2;
        }

        @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
        public void a(com.netease.nimlib.biz.e.a aVar) {
            if (aVar.n()) {
                ArrayList b = c.b((com.netease.nimlib.biz.e.m.b) aVar);
                if (b != null && !b.isEmpty()) {
                    r2.addAll(b);
                }
                if (r3) {
                    List list2 = r4;
                    c.b(list2.subList(500, list2.size()), r2, r5);
                    return;
                } else {
                    RequestCallback requestCallback2 = r5;
                    if (requestCallback2 != null) {
                        requestCallback2.onSuccess(r2);
                        return;
                    }
                    return;
                }
            }
            RequestCallback requestCallback3 = r5;
            if (requestCallback3 != null) {
                requestCallback3.onFailed(aVar.r());
            }
        }
    }

    public static ArrayList<b> b(com.netease.nimlib.biz.e.m.b bVar) {
        if (!bVar.n()) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        Iterator<com.netease.nimlib.push.packet.b.c> it = bVar.a().iterator();
        while (it.hasNext()) {
            arrayList.add(b.a(it.next()));
        }
        return arrayList;
    }

    public static void a(String str, boolean z) {
        UserDBHelper.markBlackList(str, z);
        com.netease.nimlib.i.b.a(z ? new BlackListChangedNotify(str, (String) null) : new BlackListChangedNotify((String) null, str));
    }

    public static void b(String str, boolean z) {
        UserDBHelper.markMute(str, z);
        com.netease.nimlib.i.b.a(new MuteListChangedNotify(str, z));
    }

    public static boolean b(String str) {
        d userSpecialTag = UserDBHelper.getUserSpecialTag(str);
        if (userSpecialTag == null) {
            return false;
        }
        return userSpecialTag.c();
    }

    public static boolean c(String str) {
        d userSpecialTag = UserDBHelper.getUserSpecialTag(str);
        return userSpecialTag == null || !userSpecialTag.b();
    }

    public static void a(com.netease.nimlib.push.packet.b.c cVar) {
        b queryUserInfo = UserInfoDBHelper.queryUserInfo(com.netease.nimlib.c.n());
        if (queryUserInfo == null) {
            return;
        }
        for (int i = 0; i < cVar.a(); i++) {
            int a = cVar.a(i);
            if (a != 13) {
                switch (a) {
                    case 3:
                        queryUserInfo.b(cVar.b(i));
                        u.c().a(queryUserInfo.getAccount(), queryUserInfo.getName());
                        break;
                    case 4:
                        queryUserInfo.c(cVar.b(i));
                        break;
                    case 5:
                        queryUserInfo.d(cVar.b(i));
                        break;
                    case 6:
                        queryUserInfo.a(Integer.valueOf(cVar.b(i)));
                        break;
                    case 7:
                        queryUserInfo.e(cVar.b(i));
                        break;
                    case 8:
                        queryUserInfo.f(cVar.b(i));
                        break;
                    case 9:
                        queryUserInfo.g(cVar.b(i));
                        break;
                    case 10:
                        queryUserInfo.h(cVar.b(i));
                        break;
                }
            } else {
                queryUserInfo.a(Long.valueOf(cVar.b(i)).longValue());
                t.c().a(queryUserInfo.b());
            }
        }
        a(queryUserInfo);
    }

    public static void a(b bVar) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(bVar);
        b(arrayList);
    }

    public static void b(List<b> list) {
        UserInfoDBHelper.saveUserInfo(list);
        com.netease.nimlib.i.b.m(list);
    }

    public static boolean d(String str) {
        return (TextUtils.isEmpty(str) || str.equals(com.netease.nimlib.c.n()) || UserInfoDBHelper.getUpdateTimeTag(str) > 0) ? false : true;
    }

    public static void b(com.netease.nimlib.push.packet.b.c cVar) {
        boolean z = true;
        for (int i = 0; i < cVar.a(); i++) {
            if (cVar.a(i) == 1) {
                z = Integer.parseInt(cVar.b(i)) == 1;
                l.e(z);
            }
        }
        com.netease.nimlib.i.b.c(z);
    }
}
