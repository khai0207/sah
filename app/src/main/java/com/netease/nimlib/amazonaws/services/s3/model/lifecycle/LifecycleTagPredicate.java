package com.netease.nimlib.amazonaws.services.s3.model.lifecycle;

import com.netease.nimlib.amazonaws.services.s3.model.Tag;

/* loaded from: classes.dex */
public final class LifecycleTagPredicate extends LifecycleFilterPredicate {
    private final Tag tag;

    public LifecycleTagPredicate(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.lifecycle.LifecycleFilterPredicate
    public void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor) {
        lifecyclePredicateVisitor.visit(this);
    }
}
