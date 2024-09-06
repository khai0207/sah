package com.kqg.main.model;

/* loaded from: classes.dex */
public class MainThreadMessage {
    private int what;

    public int getWhat() {
        return this.what;
    }

    public void setWhat(int i) {
        this.what = i;
    }

    public String toString() {
        return "MainThreadMessage [what=" + this.what + "]";
    }

    public MainThreadMessage(int i) {
        this.what = i;
    }
}
