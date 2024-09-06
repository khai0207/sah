package com.netease.nimlib.chatroom;

import android.util.Pair;
import java.util.HashSet;
import java.util.Set;
import u.aly.df;

/* compiled from: WhiteListOfRequest.java */
/* loaded from: classes.dex */
public class q {
    private static Set<Pair<Byte, Byte>> a;

    static {
        HashSet hashSet = new HashSet();
        a = hashSet;
        hashSet.add(new Pair((byte) 3, Byte.valueOf(df.n)));
        a.add(new Pair<>((byte) 6, (byte) 1));
        a.add(new Pair<>((byte) 6, (byte) 18));
        a.add(new Pair<>((byte) 6, (byte) 3));
        a.add(new Pair<>((byte) 6, (byte) 4));
        a.add(new Pair<>((byte) 6, (byte) 22));
    }

    public static boolean a(com.netease.nimlib.push.packet.a aVar) {
        return a.contains(new Pair(Byte.valueOf(aVar.i()), Byte.valueOf(aVar.j())));
    }
}
