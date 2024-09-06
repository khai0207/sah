package com.android.pc.ioc.invoker;

import com.android.pc.ioc.util.InjectExcutor;

/* loaded from: classes.dex */
public class InjectPLayers extends InjectInvoker {
    private int id;
    private InjectExcutor injectExcutor;
    private boolean isFull;
    private boolean isTile;

    public InjectPLayers(int i, boolean z, boolean z2, InjectExcutor injectExcutor) {
        this.id = i;
        this.isFull = z;
        this.isTile = z2;
        this.injectExcutor = injectExcutor;
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        int i = this.id;
        if (i != -1) {
            this.injectExcutor.setContentView(obj, i);
        }
    }

    public boolean isFull() {
        return this.isFull;
    }

    public boolean isTile() {
        return this.isTile;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String toString() {
        return "InjectPLayers [id=" + this.id + ", isFull=" + this.isFull + ", isTile=" + this.isTile + ", injectExcutor=" + this.injectExcutor + "]";
    }
}
