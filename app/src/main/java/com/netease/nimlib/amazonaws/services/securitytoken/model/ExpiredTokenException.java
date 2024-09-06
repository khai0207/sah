package com.netease.nimlib.amazonaws.services.securitytoken.model;

import com.netease.nimlib.amazonaws.AmazonServiceException;

/* loaded from: classes.dex */
public class ExpiredTokenException extends AmazonServiceException {
    private static final long serialVersionUID = 1;

    public ExpiredTokenException(String str) {
        super(str);
    }
}
