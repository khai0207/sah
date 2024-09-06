package com.android.pc.util;

import android.content.Context;
import com.android.pc.ioc.core.kernel.KernelLang;
import dalvik.system.DexFile;
import java.io.IOException;
import java.util.Enumeration;

/* loaded from: classes.dex */
public class DalvikHelper {
    public static void doScannerFilter(Context context, KernelLang.CallbackBreak<String> callbackBreak, KernelLang.FilterTemplate<String> filterTemplate) {
        try {
            Enumeration<String> entries = new DexFile(context.getPackageCodePath()).entries();
            while (entries.hasMoreElements()) {
                String nextElement = entries.nextElement();
                if (filterTemplate == null || filterTemplate.doWith(nextElement)) {
                    callbackBreak.doWith(nextElement);
                }
            }
        } catch (KernelLang.BreakException unused) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doScannerFilter(Context context, String str, KernelLang.CallbackTemplate<String> callbackTemplate, KernelLang.FilterTemplate<String> filterTemplate) {
        try {
            Enumeration<String> entries = new DexFile(context.getPackageCodePath()).entries();
            while (entries.hasMoreElements()) {
                String nextElement = entries.nextElement();
                if (nextElement.startsWith(str) && (filterTemplate == null || filterTemplate.doWith(nextElement))) {
                    callbackTemplate.doWith(nextElement);
                }
            }
        } catch (KernelLang.BreakException unused) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
