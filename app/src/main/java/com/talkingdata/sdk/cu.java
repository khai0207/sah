package com.talkingdata.sdk;

import java.io.File;
import java.util.Comparator;

/* compiled from: td */
/* loaded from: classes.dex */
class cu implements Comparator {
    final /* synthetic */ ct a;

    cu(ct ctVar) {
        this.a = ctVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(File file, File file2) {
        return file.getName().compareTo(file2.getName());
    }
}
