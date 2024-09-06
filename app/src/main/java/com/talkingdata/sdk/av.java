package com.talkingdata.sdk;

import java.io.File;
import java.io.FileFilter;

/* compiled from: td */
/* loaded from: classes.dex */
final class av implements FileFilter {
    av() {
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (file != null) {
            try {
                String name = file.getName();
                if (name != null && name.startsWith("cpu")) {
                    for (int i = 3; i < name.length(); i++) {
                        if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
