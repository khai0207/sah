package com.UCMobile.PayPlugin;

import android.view.SurfaceHolder;

/* loaded from: classes.dex */
final class a implements SurfaceHolder.Callback {
    final /* synthetic */ PluginSurfaceView a;

    a(PluginSurfaceView pluginSurfaceView) {
        this.a = pluginSurfaceView;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Object obj;
        boolean z;
        int i4;
        obj = this.a.g;
        synchronized (obj) {
            z = this.a.f;
            if (z) {
                PluginSurfaceView pluginSurfaceView = this.a;
                i4 = this.a.e;
                pluginSurfaceView.nativeSurfaceChanged(i4, i, i2, i3);
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        Object obj;
        boolean z;
        int i;
        obj = this.a.g;
        synchronized (obj) {
            z = this.a.f;
            if (z) {
                PluginSurfaceView pluginSurfaceView = this.a;
                i = this.a.e;
                pluginSurfaceView.nativeSurfaceCreated(i);
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Object obj;
        boolean z;
        int i;
        obj = this.a.g;
        synchronized (obj) {
            z = this.a.f;
            if (z) {
                PluginSurfaceView pluginSurfaceView = this.a;
                i = this.a.e;
                pluginSurfaceView.nativeSurfaceDestroyed(i);
            }
        }
    }
}
