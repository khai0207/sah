package com.netease.nimlib.sdk.migration.processor;

import java.io.File;

/* loaded from: classes.dex */
public interface IMsgImportProcessor extends IMsgMigrationProgress {
    File decrypt(File file, String str) throws Exception;

    @Override // com.netease.nimlib.sdk.migration.processor.IMsgMigrationProgress
    void progressUpdate(int i, int i2);

    File unzip(File file) throws Exception;
}
