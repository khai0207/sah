package com.netease.nimlib.amazonaws.services.s3.model;

/* loaded from: classes.dex */
public class RequestPaymentConfiguration {
    private Payer payer;

    /* loaded from: classes.dex */
    public enum Payer {
        Requester,
        BucketOwner
    }

    public RequestPaymentConfiguration(Payer payer) {
        this.payer = payer;
    }

    public Payer getPayer() {
        return this.payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
