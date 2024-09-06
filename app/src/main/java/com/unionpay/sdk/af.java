package com.unionpay.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/* loaded from: classes.dex */
final class af implements Application.ActivityLifecycleCallbacks {
    af() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (r.a) {
            al.b("Lifecycle callback onActivityPaused: ", activity.getLocalClassName());
        }
        r.b((Context) activity, activity.getLocalClassName(), false);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        if (r.a) {
            al.b("Lifecycle callback onActivityResumed: ", activity.getLocalClassName());
        }
        r.a((Context) activity, activity.getLocalClassName(), false);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
