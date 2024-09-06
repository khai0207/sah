package com.unionpay.mobile.android.pboctransaction.sdapdu;

import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class NativeSDWriter {
    public NativeSDWriter() {
        b.a();
    }

    private native boolean closeSD();

    private native boolean openSD(ArrayList<String> arrayList);

    private native String writeApdu(String str);

    public final String a(String str) {
        j.c("uppay", "[====>]" + str);
        String writeApdu = writeApdu(str);
        j.c("uppay", "[<====]" + writeApdu);
        return writeApdu;
    }

    public final boolean a() {
        j.c("uppay", "close()");
        return closeSD();
    }

    public final boolean a(ArrayList<String> arrayList) {
        boolean openSD = openSD(arrayList);
        j.c("uppay", "open(), ret=" + openSD);
        return openSD;
    }
}
