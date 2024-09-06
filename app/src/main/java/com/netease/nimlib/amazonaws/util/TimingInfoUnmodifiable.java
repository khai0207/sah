package com.netease.nimlib.amazonaws.util;

/* loaded from: classes.dex */
final class TimingInfoUnmodifiable extends TimingInfo {
    TimingInfoUnmodifiable(Long l, long j, Long l2) {
        super(l, j, l2);
    }

    @Override // com.netease.nimlib.amazonaws.util.TimingInfo
    public void setEndTime(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // com.netease.nimlib.amazonaws.util.TimingInfo
    public void setEndTimeNano(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // com.netease.nimlib.amazonaws.util.TimingInfo
    public TimingInfo endTiming() {
        throw new UnsupportedOperationException();
    }
}
