package com.kqg.main.model;

/* loaded from: classes.dex */
public class UpPayInfor extends PayInfor {
    private static final long serialVersionUID = -1760308993293183847L;
    private String serverMode;

    public UpPayInfor(PayInfor payInfor) {
        super(payInfor);
        this.serverMode = "00";
    }

    public String getServerMode() {
        return this.serverMode;
    }

    public void setServerMode(String str) {
        this.serverMode = str;
    }
}
