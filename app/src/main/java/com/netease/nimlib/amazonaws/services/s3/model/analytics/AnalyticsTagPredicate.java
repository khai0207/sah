package com.netease.nimlib.amazonaws.services.s3.model.analytics;

import com.netease.nimlib.amazonaws.services.s3.model.Tag;

/* loaded from: classes.dex */
public final class AnalyticsTagPredicate extends AnalyticsFilterPredicate {
    private final Tag tag;

    public AnalyticsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.analytics.AnalyticsFilterPredicate
    public void accept(AnalyticsPredicateVisitor analyticsPredicateVisitor) {
        analyticsPredicateVisitor.visit(this);
    }
}
