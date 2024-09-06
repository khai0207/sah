package com.netease.nimlib.amazonaws.services.s3.model.analytics;

/* loaded from: classes.dex */
public interface AnalyticsPredicateVisitor {
    void visit(AnalyticsAndOperator analyticsAndOperator);

    void visit(AnalyticsPrefixPredicate analyticsPrefixPredicate);

    void visit(AnalyticsTagPredicate analyticsTagPredicate);
}
