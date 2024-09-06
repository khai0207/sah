package com.android.pc.ioc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.util.TimeEntity;
import com.android.pc.util.Handler_Time;

/* loaded from: classes.dex */
public class TimeTextView extends TextView {
    EventBus eventBus;
    private Finish finish;
    private long limitTime;
    private long startTime;

    /* loaded from: classes.dex */
    public interface Finish {
        void finished(TextView textView);
    }

    public TimeTextView(Context context) {
        super(context);
        this.eventBus = EventBus.getDefault();
        this.startTime = 0L;
        this.limitTime = 0L;
    }

    public TimeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.eventBus = EventBus.getDefault();
        this.startTime = 0L;
        this.limitTime = 0L;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.eventBus.register(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.eventBus.unregister(this);
    }

    public void setLimtTime(long j, long j2) {
        this.startTime = j;
        this.limitTime = j2;
        showTime();
    }

    public void setEndTime(long j, long j2) {
        this.startTime = j;
        this.limitTime = j2 - j;
        showTime();
    }

    public void onEventMainThread(TimeEntity timeEntity) {
        showTime();
    }

    private void showTime() {
        long j = this.startTime;
        if (j != 0) {
            long j2 = this.limitTime;
            if (j2 == 0) {
                return;
            }
            long currentTimeMillis = (j + j2) - System.currentTimeMillis();
            if (currentTimeMillis < 0) {
                Finish finish = this.finish;
                if (finish != null) {
                    finish.finished(this);
                }
                setText("时间已到");
                return;
            }
            setText(Handler_Time.formatDuring(currentTimeMillis));
        }
    }

    public Finish getFinish() {
        return this.finish;
    }

    public void setFinish(Finish finish) {
        this.finish = finish;
    }
}
