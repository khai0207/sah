package com.android.pc.ioc.invoker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.pc.ioc.util.InjectExcutor;
import com.android.pc.ioc.util.InjectViewUtils;

/* loaded from: classes.dex */
public class InjectLayers extends InjectInvoker {
    private int id;
    private InjectExcutor injectExcutor;
    private InjectPLayers injectPLayers;
    private boolean isFull;
    private boolean isTile;
    private int parent;

    public InjectLayers(int i, boolean z, boolean z2, int i2, InjectExcutor injectExcutor) {
        this.id = i;
        this.isFull = z;
        this.isTile = z2;
        this.parent = i2;
        this.injectExcutor = injectExcutor;
    }

    @Override // com.android.pc.ioc.invoker.InjectInvoker
    public void invoke(Object obj, Object... objArr) {
        int i = this.id;
        if (i == -1) {
            return;
        }
        if (this.parent == -1) {
            this.injectExcutor.setContentView(obj, i);
            return;
        }
        InjectPLayers injectPLayers = this.injectPLayers;
        if (injectPLayers != null) {
            this.injectExcutor.setContentView(obj, injectPLayers.getId());
            Activity activity = (Activity) obj;
            ViewGroup viewGroup = (ViewGroup) InjectViewUtils.Inject_Excutors[0].findViewById(activity, this.parent);
            LayoutInflater from = LayoutInflater.from(activity);
            if (LinearLayout.class.isAssignableFrom(viewGroup.getClass())) {
                viewGroup.addView(from.inflate(this.id, (ViewGroup) null), new LinearLayout.LayoutParams(-1, -1));
            }
            if (RelativeLayout.class.isAssignableFrom(viewGroup.getClass())) {
                viewGroup.addView(from.inflate(this.id, (ViewGroup) null), new RelativeLayout.LayoutParams(-1, -1));
            }
            if (AbsoluteLayout.class.isAssignableFrom(viewGroup.getClass())) {
                viewGroup.addView(from.inflate(this.id, (ViewGroup) null), new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
            }
            if (FrameLayout.class.isAssignableFrom(viewGroup.getClass())) {
                viewGroup.addView(from.inflate(this.id, (ViewGroup) null), new FrameLayout.LayoutParams(-1, -1));
            }
            try {
                if (Class.forName("android.widget.GridLayout").isAssignableFrom(viewGroup.getClass())) {
                    viewGroup.addView(from.inflate(this.id, (ViewGroup) null), new GridLayout.LayoutParams());
                }
            } catch (Exception unused) {
            }
        }
    }

    public int getParent() {
        return this.parent;
    }

    public boolean isFull() {
        return this.isFull;
    }

    public boolean isTile() {
        return this.isTile;
    }

    public InjectPLayers getInjectPLayers() {
        return this.injectPLayers;
    }

    public void setInjectPLayers(InjectPLayers injectPLayers) {
        this.injectPLayers = injectPLayers;
    }

    public String toString() {
        return "InjectLayers [id=" + this.id + "]";
    }
}
