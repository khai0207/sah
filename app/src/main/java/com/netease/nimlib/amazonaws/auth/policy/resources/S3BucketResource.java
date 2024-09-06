package com.netease.nimlib.amazonaws.auth.policy.resources;

import com.netease.nimlib.amazonaws.auth.policy.Resource;

/* loaded from: classes.dex */
public class S3BucketResource extends Resource {
    public S3BucketResource(String str) {
        super("arn:aws:s3:::" + str);
    }
}
