package com.ipaynow.wechatpay.plugin.d.c.a;

import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a implements Serializable {
    public int W;
    public int X;
    public String Y = null;
    public String respCode = null;
    public String errorCode = null;
    public String respMsg = null;
    public HashMap Z = null;

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("service_code= ");
        stringBuffer.append(this.X);
        stringBuffer.append("\nfuncode= ");
        stringBuffer.append(this.W);
        stringBuffer.append("\nstatus= ");
        stringBuffer.append(this.Y);
        stringBuffer.append("\nrespCode= ");
        stringBuffer.append(this.respCode);
        stringBuffer.append("\nerrorCode=");
        stringBuffer.append(this.errorCode);
        stringBuffer.append("\nrespMsg= ");
        stringBuffer.append(this.respMsg);
        stringBuffer.append("\n");
        stringBuffer.append(this.Z);
        return stringBuffer.toString();
    }
}
