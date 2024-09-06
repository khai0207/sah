package com.netease.nimlib.push.packet.a.c.a;

import java.io.ByteArrayOutputStream;

/* compiled from: Hex.java */
/* loaded from: classes.dex */
public class c {
    private static final b a = new d();

    public static byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new a("exception decoding Hex string: " + e.getMessage(), e);
        }
    }
}
