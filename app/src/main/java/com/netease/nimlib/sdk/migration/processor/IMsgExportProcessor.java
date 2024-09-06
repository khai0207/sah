package com.netease.nimlib.sdk.migration.processor;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface IMsgExportProcessor extends IMsgMigrationProgress {
    File encrypt(File file) throws Exception;

    ArrayList<IMMessage> filterMsg(ArrayList<IMMessage> arrayList);

    @Override // com.netease.nimlib.sdk.migration.processor.IMsgMigrationProgress
    void progressUpdate(int i, int i2);

    String secretKey();

    File zip(File file) throws Exception;
}
