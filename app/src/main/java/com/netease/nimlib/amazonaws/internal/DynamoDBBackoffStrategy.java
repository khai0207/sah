package com.netease.nimlib.amazonaws.internal;

import androidx.appcompat.widget.ActivityChooserView;

/* loaded from: classes.dex */
public class DynamoDBBackoffStrategy extends CustomBackoffStrategy {
    public static final CustomBackoffStrategy DEFAULT = new DynamoDBBackoffStrategy();

    @Override // com.netease.nimlib.amazonaws.internal.CustomBackoffStrategy
    public int getBackoffPeriod(int i) {
        if (i <= 0) {
            return 0;
        }
        int pow = ((int) Math.pow(2.0d, i - 1)) * 50;
        return pow < 0 ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : pow;
    }
}
