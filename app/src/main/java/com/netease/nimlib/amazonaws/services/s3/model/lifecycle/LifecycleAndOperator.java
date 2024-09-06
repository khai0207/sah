package com.netease.nimlib.amazonaws.services.s3.model.lifecycle;

import java.util.List;

/* loaded from: classes.dex */
public final class LifecycleAndOperator extends LifecycleNAryOperator {
    @Override // com.netease.nimlib.amazonaws.services.s3.model.lifecycle.LifecycleNAryOperator
    public /* bridge */ /* synthetic */ List getOperands() {
        return super.getOperands();
    }

    public LifecycleAndOperator(List<LifecycleFilterPredicate> list) {
        super(list);
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.lifecycle.LifecycleFilterPredicate
    public void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor) {
        lifecyclePredicateVisitor.visit(this);
    }
}
