package com.kqg.main.model;

/* loaded from: classes.dex */
public class Message {
    private Object obj;
    private int what;

    public int getWhat() {
        return this.what;
    }

    public void setWhat(int i) {
        this.what = i;
    }

    public String toString() {
        return "Message [what=" + this.what + ", obj=" + this.obj + "]";
    }

    public Message(int i) {
        this.what = i;
    }

    public Message(int i, Object obj) {
        this.what = i;
        this.obj = obj;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
