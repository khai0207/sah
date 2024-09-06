package com.ipaynow.wechatpay.plugin.d.a;

import android.util.Base64;
import com.alipay.sdk.m.j.d;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/* loaded from: classes.dex */
public final class a {
    public static boolean a(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(d.a).generatePublic(new X509EncodedKeySpec(Base64.decode(str3, 0)));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(generatePublic);
            signature.update(str.getBytes("UTF-8"));
            return signature.verify(Base64.decode(str2, 0));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
