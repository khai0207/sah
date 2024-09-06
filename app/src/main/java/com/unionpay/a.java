package com.unionpay;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.unionpay.utils.UPUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
final class a extends BroadcastReceiver {
    a() {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String path;
        String str;
        Context context2;
        String str2;
        String str3;
        String str4;
        BroadcastReceiver broadcastReceiver;
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        long c = UPUtils.c(context, "id");
        if (c == longExtra) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(c);
            if (uriForDownloadedFile != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(uriForDownloadedFile.getPath()));
                    String absolutePath = context.getFilesDir().getAbsolutePath();
                    if (absolutePath != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(absolutePath);
                        sb.append(File.separator);
                        str3 = UPPayAssistEx.A;
                        sb.append(str3);
                        path = sb.toString();
                        str4 = UPPayAssistEx.A;
                        FileOutputStream openFileOutput = context.openFileOutput(str4, 1);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            } else {
                                openFileOutput.write(bArr, 0, read);
                            }
                        }
                        openFileOutput.close();
                        fileInputStream.close();
                    } else {
                        path = "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    path = uriForDownloadedFile.getPath();
                }
                try {
                    String b = com.unionpay.utils.b.b(path);
                    str = UPPayAssistEx.y;
                    if (str.equalsIgnoreCase(b)) {
                        context2 = UPPayAssistEx.t;
                        if (!UPPayAssistEx.checkInstalled(context2)) {
                            Uri parse = Uri.parse("file:" + path);
                            str2 = UPPayAssistEx.F;
                            intent2.setDataAndType(parse, str2);
                            intent2.addFlags(268435456);
                            context.startActivity(intent2);
                        }
                    } else {
                        com.unionpay.utils.b.c(uriForDownloadedFile.getPath());
                    }
                } catch (FileNotFoundException unused) {
                }
                com.unionpay.utils.g.b("uppay", "downloadFileUri" + uriForDownloadedFile);
            }
            broadcastReceiver = UPPayAssistEx.K;
            context.unregisterReceiver(broadcastReceiver);
            UPPayAssistEx.f();
        }
    }
}
