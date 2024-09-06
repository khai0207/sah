package com.netease.nimlib.push.c;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: QuickConnectUtils.java */
/* loaded from: classes.dex */
public class d {
    public static InetSocketAddress a(final String str, final int i, long j, TimeUnit timeUnit) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new Callable<InetSocketAddress>() { // from class: com.netease.nimlib.push.c.d.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public InetSocketAddress call() throws InterruptedException {
                return new InetSocketAddress(str, i);
            }
        });
        try {
            return (InetSocketAddress) submit.get(j, timeUnit);
        } catch (TimeoutException unused) {
            com.netease.nimlib.log.b.d("QuickConnectUtils", "DNS解析超时，尝试取消任务...");
            submit.cancel(true);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            newSingleThreadExecutor.shutdownNow();
        }
    }

    public static boolean a(String str) {
        try {
            return InetAddress.getByName(str) instanceof Inet4Address;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("QuickConnectUtils", String.format("isIpV4Ip %s", str), e);
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            return InetAddress.getByName(str) instanceof Inet6Address;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e("QuickConnectUtils", String.format("isIpV6Ip %s", str), e);
            return false;
        }
    }
}
