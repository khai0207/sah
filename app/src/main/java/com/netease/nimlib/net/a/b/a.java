package com.netease.nimlib.net.a.b;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.regions.ServiceAbbreviations;
import com.netease.nimlib.biz.b.e;
import com.netease.nimlib.biz.d.d.j;
import com.netease.nimlib.n.b.h;
import com.netease.nimlib.n.i;
import com.netease.nimlib.n.o;
import com.netease.nimlib.net.a.b.c.d;
import com.netease.nimlib.o.m;
import com.netease.nimlib.o.w;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.util.NIMUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NosUploadManager.java */
/* loaded from: classes.dex */
public class a {
    private static boolean a = false;
    private static com.netease.nimlib.net.a.b.d.a b;
    private static final Set<String> k = new HashSet<String>() { // from class: com.netease.nimlib.net.a.b.a.1
        AnonymousClass1() {
            add("aac");
            add("abw");
            add("arc");
            add("avif");
            add("avi");
            add("azw");
            add("bin");
            add("bmp");
            add("bz");
            add("bz2");
            add("cda");
            add("csh");
            add("css");
            add("csv");
            add("doc");
            add("docx");
            add("eot");
            add("epub");
            add("gz");
            add("gif");
            add("htm");
            add("html");
            add("ico");
            add("ics");
            add("jar");
            add("jpeg");
            add("jpg");
            add("js");
            add("json");
            add("jsonld");
            add("mid");
            add("midi");
            add("mjs");
            add("mp3");
            add("mp4");
            add("mpeg");
            add("mpkg");
            add("odp");
            add("ods");
            add("odt");
            add("oga");
            add("ogv");
            add("ogx");
            add("opus");
            add("otf");
            add("png");
            add("pdf");
            add("php");
            add("ppt");
            add("pptx");
            add("rar");
            add("rtf");
            add("sh");
            add("svg");
            add(ServiceAbbreviations.SimpleWorkflow);
            add("tar");
            add("tif");
            add("tiff");
            add("ts");
            add("ttf");
            add("txt");
            add("vsd");
            add("wav");
            add("weba");
            add("webm");
            add("webp");
            add("woff");
            add("woff2");
            add("xhtml");
            add("xls");
            add("xlsx");
            add("xml");
            add("xul");
            add("zip");
            add("3gp");
            add("3g2");
            add("7z");
            add("amr");
        }
    };
    private com.netease.nimlib.net.a.b.b c;
    private HashMap<String, Long> d;
    private final HashMap<String, ArrayList<d>> e;
    private final HashMap<String, AtomicBoolean> f;
    private final HashMap<String, ArrayList<c>> g;
    private final Object h;
    private final Set<c> i;
    private long j;

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    /* compiled from: NosUploadManager.java */
    /* renamed from: com.netease.nimlib.net.a.b.a$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends HashSet<String> {
        AnonymousClass1() {
            add("aac");
            add("abw");
            add("arc");
            add("avif");
            add("avi");
            add("azw");
            add("bin");
            add("bmp");
            add("bz");
            add("bz2");
            add("cda");
            add("csh");
            add("css");
            add("csv");
            add("doc");
            add("docx");
            add("eot");
            add("epub");
            add("gz");
            add("gif");
            add("htm");
            add("html");
            add("ico");
            add("ics");
            add("jar");
            add("jpeg");
            add("jpg");
            add("js");
            add("json");
            add("jsonld");
            add("mid");
            add("midi");
            add("mjs");
            add("mp3");
            add("mp4");
            add("mpeg");
            add("mpkg");
            add("odp");
            add("ods");
            add("odt");
            add("oga");
            add("ogv");
            add("ogx");
            add("opus");
            add("otf");
            add("png");
            add("pdf");
            add("php");
            add("ppt");
            add("pptx");
            add("rar");
            add("rtf");
            add("sh");
            add("svg");
            add(ServiceAbbreviations.SimpleWorkflow);
            add("tar");
            add("tif");
            add("tiff");
            add("ts");
            add("ttf");
            add("txt");
            add("vsd");
            add("wav");
            add("weba");
            add("webm");
            add("webp");
            add("woff");
            add("woff2");
            add("xhtml");
            add("xls");
            add("xlsx");
            add("xml");
            add("xul");
            add("zip");
            add("3gp");
            add("3g2");
            add("7z");
            add("amr");
        }
    }

    private a() {
        this.d = new HashMap<>();
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        this.g = new HashMap<>();
        this.h = new Object();
        this.j = 0L;
        this.c = new com.netease.nimlib.net.a.b.b();
        this.i = new HashSet();
        a((String) null, (String) null);
        a = true;
    }

    public static a a() {
        return b.a;
    }

    public void b() {
        synchronized (this.i) {
            this.i.clear();
        }
        synchronized (this.e) {
            this.e.clear();
        }
        synchronized (this.f) {
            this.f.clear();
        }
        synchronized (this.g) {
            this.g.clear();
        }
        com.netease.nimlib.net.a.b.f.a.a();
        this.j = 0L;
    }

    public void a(String str, List<d> list) {
        synchronized (this.f) {
            AtomicBoolean atomicBoolean = this.f.get(str);
            if (atomicBoolean != null) {
                atomicBoolean.set(false);
            }
        }
        if (list.size() == 0) {
            b(str);
            return;
        }
        synchronized (this.h) {
            ArrayList<d> arrayList = this.e.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.e.put(str, arrayList);
            }
            arrayList.addAll(list);
            a(str);
        }
        synchronized (this.i) {
            Iterator<c> it = this.i.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (TextUtils.equals(next.i, str)) {
                    next.j = e(next.b, next.i);
                    if (next.j != null) {
                        next.run();
                        it.remove();
                    } else {
                        d(next.b, next.i);
                    }
                }
            }
        }
    }

    public c a(FileAttachment fileAttachment, Object obj, com.netease.nimlib.net.a.b.c cVar) {
        return a("", fileAttachment, obj, cVar);
    }

    public c a(String str, FileAttachment fileAttachment, Object obj, com.netease.nimlib.net.a.b.c cVar) {
        String str2;
        String c2 = w.c(fileAttachment.getPath());
        if (TextUtils.isEmpty(c2) || !k.contains(c2)) {
            if (fileAttachment instanceof AudioAttachment) {
                str2 = "audio/aac";
            } else if (fileAttachment instanceof ImageAttachment) {
                str2 = "image/jpeg";
            } else if (fileAttachment instanceof VideoAttachment) {
                str2 = "video/mp4";
            }
            return a(str, fileAttachment.getPath(), str2, fileAttachment.getMd5(), obj, fileAttachment.getNosTokenSceneKey(), fileAttachment.isForceUpload(), cVar);
        }
        str2 = "";
        return a(str, fileAttachment.getPath(), str2, fileAttachment.getMd5(), obj, fileAttachment.getNosTokenSceneKey(), fileAttachment.isForceUpload(), cVar);
    }

    public c a(String str, String str2, Object obj, String str3, boolean z, com.netease.nimlib.net.a.b.c cVar) {
        return a(null, str, "", str2, obj, str3, z, cVar);
    }

    public c a(String str, String str2, String str3, Object obj, String str4, boolean z, com.netease.nimlib.net.a.b.c cVar) {
        return a(str, str2, "", str3, obj, str4, z, cVar);
    }

    public c a(String str, String str2, String str3, String str4, Object obj, String str5, boolean z, com.netease.nimlib.net.a.b.c cVar) {
        if (TextUtils.isEmpty(str5) || !this.d.containsKey(str5)) {
            String str6 = "the [" + str5 + "] sceneKey must not null and added to NosTokenSceneConfig before use it";
            if (cVar != null) {
                cVar.a((com.netease.nimlib.net.a.b.c) obj, 5, str6);
            }
            com.netease.nimlib.log.b.f("NosUploadManager", str6);
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            String str7 = "the file " + str2 + " not exists";
            if (cVar != null) {
                cVar.a((com.netease.nimlib.net.a.b.c) obj, 6, str7);
            }
            com.netease.nimlib.log.b.f("NosUploadManager", str7);
            return null;
        }
        String b2 = TextUtils.isEmpty(str4) ? m.b(str2) : str4;
        o.a().a(b2);
        c cVar2 = new c(str, str2, str3, b2, obj, str5, cVar);
        if (e.d().a()) {
            cVar2.a(z);
            return cVar2;
        }
        long length = file.length();
        if (length < this.j || z) {
            b(cVar2);
        } else {
            synchronized (this.g) {
                ArrayList<c> arrayList = this.g.get(b2);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.g.put(b2, arrayList);
                }
                arrayList.add(cVar2);
            }
            i.a().c(b2);
            a(str, new com.netease.nimlib.biz.d.d.b(b2, length));
        }
        return cVar2;
    }

    public void a(c cVar) {
        synchronized (this.g) {
            Iterator<String> it = this.g.keySet().iterator();
            while (it.hasNext()) {
                if (this.g.get(it.next()).remove(cVar)) {
                    if (cVar.g != null) {
                        cVar.g.a(cVar.f);
                    }
                    return;
                }
            }
            synchronized (this.i) {
                if (!this.i.remove(cVar)) {
                    cVar.a();
                } else {
                    if (cVar.g != null) {
                        cVar.g.a(cVar.f);
                    }
                }
            }
        }
    }

    public static com.netease.nimlib.net.a.b.d.a c() {
        if (b == null) {
            b = new com.netease.nimlib.net.a.b.d.a();
        }
        return b;
    }

    public void a(com.netease.nimlib.biz.e.d.a aVar, String str) {
        ArrayList<c> remove;
        synchronized (this.g) {
            remove = this.g.remove(str);
        }
        if (NIMUtil.isEmpty(remove)) {
            return;
        }
        String a2 = aVar.a();
        if (!aVar.n()) {
            i.a().a(str, aVar.r());
        }
        if (TextUtils.isEmpty(a2)) {
            a(remove);
        } else {
            a(remove, a2);
        }
        if (aVar.n()) {
            this.j = aVar.b();
        }
    }

    public void d() {
        synchronized (this.h) {
            HashMap<String, Long> nosTokenScene = com.netease.nimlib.c.C().getNosTokenScene();
            Set<Map.Entry<String, Long>> entrySet = nosTokenScene.entrySet();
            HashMap<String, Long> hashMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : entrySet) {
                String key = entry.getKey();
                Long value = entry.getValue();
                if (value.equals(this.d.get(key))) {
                    this.d.remove(key);
                } else {
                    hashMap.put(key, value);
                }
            }
            Iterator<String> it = this.d.keySet().iterator();
            while (it.hasNext()) {
                this.e.remove(it.next());
            }
            this.d = nosTokenScene;
            a(hashMap);
        }
    }

    private void a(String str, String str2) {
        if (this.d.isEmpty()) {
            HashMap<String, Long> nosTokenScene = com.netease.nimlib.c.C().getNosTokenScene();
            this.d = nosTokenScene;
            Set<Map.Entry<String, Long>> entrySet = nosTokenScene.entrySet();
            synchronized (this.h) {
                for (Map.Entry<String, Long> entry : entrySet) {
                    String key = entry.getKey();
                    this.e.put(key, d.g(this.c.a(key, entry.getValue())));
                    a(str, str2, key);
                }
            }
            return;
        }
        Set<Map.Entry<String, Long>> entrySet2 = this.d.entrySet();
        synchronized (this.h) {
            Iterator<Map.Entry<String, Long>> it = entrySet2.iterator();
            while (it.hasNext()) {
                a(str, str2, it.next().getKey());
            }
        }
    }

    public void b(String str, String str2) {
        synchronized (this.h) {
            this.e.remove(str2);
        }
        if (!TextUtils.isEmpty(str)) {
            IChatRoomInteract iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class);
            if (iChatRoomInteract != null) {
                d(iChatRoomInteract.getRoomIdByAppKey(str), str2);
                return;
            } else {
                d(null, str2);
                return;
            }
        }
        d(null, str2);
    }

    private void a(HashMap<String, Long> hashMap) {
        for (Map.Entry<String, Long> entry : hashMap.entrySet()) {
            a((String) null, entry.getKey(), entry.getValue().longValue());
        }
    }

    private void a(String str, String str2, long j) {
        synchronized (this.f) {
            AtomicBoolean atomicBoolean = this.f.get(str2);
            if (atomicBoolean == null) {
                atomicBoolean = new AtomicBoolean(false);
                this.f.put(str2, atomicBoolean);
            }
            if (atomicBoolean.compareAndSet(false, true)) {
                com.netease.nimlib.log.b.d("NosUploadManager", "request nos tokens");
                a(str, new j(30, str2, j));
            }
        }
    }

    private void a(String str) {
        ArrayList<d> arrayList = this.e.get(str);
        this.c.a(str, this.d.get(str), d.a(arrayList));
    }

    private void c(String str, String str2) {
        a(com.netease.nimlib.c.d(str), str, str2);
    }

    private void a(String str, String str2, String str3) {
        ArrayList<d> arrayList = this.e.get(str3);
        if (TextUtils.isEmpty(str)) {
            if (arrayList == null || arrayList.size() <= 10) {
                d(str2, str3);
                return;
            }
            return;
        }
        int i = 0;
        if (arrayList != null) {
            Iterator<d> it = arrayList.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().f(), str)) {
                    i++;
                }
            }
        }
        if (i <= 10) {
            d(str2, str3);
        }
    }

    private void d(String str, String str2) {
        Long l = this.d.get(str2);
        if (l == null) {
            return;
        }
        a(str, str2, l.longValue());
    }

    private d e(String str, String str2) {
        synchronized (this.h) {
            c(str, str2);
            ArrayList<d> arrayList = this.e.get(str2);
            d dVar = null;
            if (NIMUtil.isEmpty(arrayList)) {
                return null;
            }
            String d = com.netease.nimlib.c.d(str);
            if (!TextUtils.isEmpty(d)) {
                Iterator<d> it = arrayList.iterator();
                while (it.hasNext()) {
                    d next = it.next();
                    if (TextUtils.equals(next.f(), d)) {
                        it.remove();
                        dVar = next;
                    }
                }
            } else {
                dVar = arrayList.remove(0);
                a(str2);
            }
            return dVar;
        }
    }

    private void b(String str) {
        if (NIMUtil.isEmpty(this.e.get(str)) && this.i.size() != 0) {
            synchronized (this.i) {
                Iterator<c> it = this.i.iterator();
                while (it.hasNext()) {
                    c next = it.next();
                    if (str.equals(next.i)) {
                        if (next.g != null) {
                            next.g.a((com.netease.nimlib.net.a.b.c) next.f, 408, (String) null);
                        }
                        it.remove();
                    }
                }
            }
        }
    }

    private void a(String str, com.netease.nimlib.biz.d.a aVar) {
        if (TextUtils.isEmpty(str)) {
            com.netease.nimlib.biz.i.a().a(aVar);
            return;
        }
        IChatRoomInteract iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class);
        if (c(str)) {
            iChatRoomInteract.sendRequest(str, aVar);
        } else {
            com.netease.nimlib.biz.i.a().a(aVar);
        }
    }

    private boolean c(String str) {
        IChatRoomInteract iChatRoomInteract;
        if (TextUtils.isEmpty(str) || (iChatRoomInteract = (IChatRoomInteract) com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class)) == null) {
            return false;
        }
        return iChatRoomInteract.independent(str);
    }

    private void b(c cVar) {
        d e = e(cVar.b, cVar.i);
        if (e == null) {
            synchronized (this.i) {
                this.i.add(cVar);
            }
        } else {
            cVar.j = e;
            cVar.run();
        }
    }

    private void a(ArrayList<c> arrayList) {
        Iterator<c> it = arrayList.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    private void a(ArrayList<c> arrayList, String str) {
        Iterator<c> it = arrayList.iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (next.g != null) {
                next.g.a(next.f, str);
            }
        }
    }

    public static boolean e() {
        return a;
    }

    /* compiled from: NosUploadManager.java */
    /* loaded from: classes.dex */
    private static class b {
        private static a a = new a();
    }

    /* compiled from: NosUploadManager.java */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        private String b;
        private String c;
        private String d;
        private String e;
        private Object f;
        private com.netease.nimlib.net.a.b.c<Object> g;
        private com.netease.nimlib.net.a.b.f.b h;
        private String i;
        private d j;

        c(String str, String str2, String str3, String str4, Object obj, String str5, com.netease.nimlib.net.a.b.c<Object> cVar) {
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.f = obj;
            this.g = new com.netease.nimlib.net.a.b.c<Object>() { // from class: com.netease.nimlib.net.a.b.a.c.1
                final /* synthetic */ a a;
                final /* synthetic */ com.netease.nimlib.net.a.b.c b;
                final /* synthetic */ String c;

                AnonymousClass1(a aVar, com.netease.nimlib.net.a.b.c cVar2, String str42) {
                    r2 = aVar;
                    r3 = cVar2;
                    r4 = str42;
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj2, long j, long j2) {
                    com.netease.nimlib.net.a.b.c cVar2 = r3;
                    if (cVar2 == null) {
                        return;
                    }
                    cVar2.a((com.netease.nimlib.net.a.b.c) obj2, j, j2);
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj2, String str6) {
                    o.a().a(r4, h.kSucceed.a(), str6);
                    i.a().b(r4, h.kSucceed.a());
                    com.netease.nimlib.net.a.b.c cVar2 = r3;
                    if (cVar2 == null) {
                        return;
                    }
                    cVar2.a(obj2, str6);
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj2, int i, String str6) {
                    o.a().a(r4, h.kFailed.a(), null);
                    i.a().b(r4, h.kFailed.a());
                    com.netease.nimlib.net.a.b.c cVar2 = r3;
                    if (cVar2 == null) {
                        return;
                    }
                    cVar2.a((com.netease.nimlib.net.a.b.c) obj2, i, str6);
                }

                @Override // com.netease.nimlib.net.a.b.c
                public void a(Object obj2) {
                    o.a().a(r4, h.kCanceled.a(), null);
                    i.a().b(r4, h.kCanceled.a());
                    com.netease.nimlib.net.a.b.c cVar2 = r3;
                    if (cVar2 == null) {
                        return;
                    }
                    cVar2.a(obj2);
                }
            };
            this.i = str5;
            i.a().a(this.e);
        }

        /* compiled from: NosUploadManager.java */
        /* renamed from: com.netease.nimlib.net.a.b.a$c$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements com.netease.nimlib.net.a.b.c<Object> {
            final /* synthetic */ a a;
            final /* synthetic */ com.netease.nimlib.net.a.b.c b;
            final /* synthetic */ String c;

            AnonymousClass1(a aVar, com.netease.nimlib.net.a.b.c cVar2, String str42) {
                r2 = aVar;
                r3 = cVar2;
                r4 = str42;
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj2, long j, long j2) {
                com.netease.nimlib.net.a.b.c cVar2 = r3;
                if (cVar2 == null) {
                    return;
                }
                cVar2.a((com.netease.nimlib.net.a.b.c) obj2, j, j2);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj2, String str6) {
                o.a().a(r4, h.kSucceed.a(), str6);
                i.a().b(r4, h.kSucceed.a());
                com.netease.nimlib.net.a.b.c cVar2 = r3;
                if (cVar2 == null) {
                    return;
                }
                cVar2.a(obj2, str6);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj2, int i, String str6) {
                o.a().a(r4, h.kFailed.a(), null);
                i.a().b(r4, h.kFailed.a());
                com.netease.nimlib.net.a.b.c cVar2 = r3;
                if (cVar2 == null) {
                    return;
                }
                cVar2.a((com.netease.nimlib.net.a.b.c) obj2, i, str6);
            }

            @Override // com.netease.nimlib.net.a.b.c
            public void a(Object obj2) {
                o.a().a(r4, h.kCanceled.a(), null);
                i.a().b(r4, h.kCanceled.a());
                com.netease.nimlib.net.a.b.c cVar2 = r3;
                if (cVar2 == null) {
                    return;
                }
                cVar2.a(obj2);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            d c;
            String a = a.this.c.a(this.c);
            if (!TextUtils.isEmpty(a) && (c = a.this.c.c(this.c)) != null) {
                this.j = c;
            }
            com.netease.nimlib.net.a.b.c.e eVar = new com.netease.nimlib.net.a.b.c.e(this.j.b(), this.j.c(), this.j.d(), this.e, null);
            if (w.f(this.d)) {
                eVar.b(this.d);
            } else {
                eVar.b(w.e(this.c));
            }
            try {
                this.h = com.netease.nimlib.net.a.b.f.a.a(com.netease.nimlib.c.e(), new File(this.c), this.f, a, eVar, new C0046a(this.c, this.j, this.i, this.g));
            } catch (Exception e) {
                com.netease.nimlib.net.a.b.c<Object> cVar = this.g;
                if (cVar != null) {
                    cVar.a((com.netease.nimlib.net.a.b.c<Object>) this.f, 1000, "exception: " + e.getMessage());
                }
            }
        }

        public void a(boolean z) {
            com.netease.nimlib.log.b.d("HighAvailableManager", "HighAvailableManager upload md5 = " + this.e);
            this.h = e.d().a(this.c, this.d, this.i, this.e, this.f, z, this.g);
        }

        public void a() {
            com.netease.nimlib.net.a.b.f.b bVar = this.h;
            if (bVar != null) {
                bVar.b();
            }
        }
    }

    /* compiled from: NosUploadManager.java */
    /* renamed from: com.netease.nimlib.net.a.b.a$a */
    /* loaded from: classes.dex */
    private class C0046a implements com.netease.nimlib.net.a.b.c.b {
        private String b;
        private com.netease.nimlib.net.a.b.c<Object> c;
        private d d;
        private String e;

        C0046a(String str, d dVar, String str2, com.netease.nimlib.net.a.b.c<Object> cVar) {
            this.b = str;
            this.d = dVar;
            this.c = cVar;
            this.e = str2;
        }

        @Override // com.netease.nimlib.net.a.b.c.b
        public void a(Object obj, String str, String str2) {
            a.this.c.a(this.b, str2);
            a.this.c.a(this.b, this.d);
        }

        @Override // com.netease.nimlib.net.a.b.c.b
        public void a(Object obj, long j, long j2) {
            com.netease.nimlib.net.a.b.c<Object> cVar = this.c;
            if (cVar != null) {
                cVar.a((com.netease.nimlib.net.a.b.c<Object>) obj, j, j2);
            }
        }

        @Override // com.netease.nimlib.net.a.b.c.b
        public void a(com.netease.nimlib.net.a.b.c.a aVar) {
            a.this.c.b(this.b);
            a.this.c.d(this.b);
            com.netease.nimlib.net.a.b.c<Object> cVar = this.c;
            if (cVar != null) {
                cVar.a(aVar.a(), com.netease.nimlib.net.a.c.d.a(this.d, com.netease.nimlib.net.a.b.d.a.a));
            }
        }

        @Override // com.netease.nimlib.net.a.b.c.b
        public void b(com.netease.nimlib.net.a.b.c.a aVar) {
            com.netease.nimlib.net.a.b.c<Object> cVar = this.c;
            if (cVar != null) {
                cVar.a((com.netease.nimlib.net.a.b.c<Object>) aVar.a(), aVar.b(), aVar.c());
            }
            if (aVar.b() == 403) {
                a a = a.a();
                d dVar = this.d;
                a.b(dVar != null ? dVar.f() : null, this.e);
                a.this.c.b(this.b);
                a.this.c.d(this.b);
                return;
            }
            com.netease.nimlib.net.a.b.a.b.d(com.netease.nimlib.c.e());
        }

        @Override // com.netease.nimlib.net.a.b.c.b
        public void c(com.netease.nimlib.net.a.b.c.a aVar) {
            com.netease.nimlib.net.a.b.c<Object> cVar = this.c;
            if (cVar != null) {
                cVar.a(aVar.a());
            }
        }
    }
}
