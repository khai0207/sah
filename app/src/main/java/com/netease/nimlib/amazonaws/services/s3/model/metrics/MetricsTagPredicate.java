package com.netease.nimlib.amazonaws.services.s3.model.metrics;

import com.netease.nimlib.amazonaws.services.s3.model.Tag;

/* loaded from: classes.dex */
public final class MetricsTagPredicate extends MetricsFilterPredicate {
    private final Tag tag;

    public MetricsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.metrics.MetricsFilterPredicate
    public void accept(MetricsPredicateVisitor metricsPredicateVisitor) {
        metricsPredicateVisitor.visit(this);
    }
}
