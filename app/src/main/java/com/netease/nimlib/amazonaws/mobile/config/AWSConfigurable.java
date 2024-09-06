package com.netease.nimlib.amazonaws.mobile.config;

import android.content.Context;
import com.netease.nimlib.amazonaws.ClientConfiguration;

/* loaded from: classes.dex */
public interface AWSConfigurable {
    AWSConfigurable initialize(Context context) throws Exception;

    AWSConfigurable initialize(Context context, AWSConfiguration aWSConfiguration) throws Exception;

    AWSConfigurable initialize(Context context, AWSConfiguration aWSConfiguration, ClientConfiguration clientConfiguration) throws Exception;
}
