package com.netease.nimlib.amazonaws.services.s3.model.lifecycle;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class LifecycleFilterPredicate implements Serializable {
    public abstract void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor);
}
