package com.android.pc.ioc.image;

import android.widget.AbsListView;

/* loaded from: classes.dex */
public abstract class OnScrollLoaderListener implements AbsListView.OnScrollListener {
    private int firstVisibleItem;
    private OnStop onStop;
    private int visibleItemCount;
    private boolean scroll_stats = false;
    private boolean fling_stats = false;

    /* loaded from: classes.dex */
    public interface OnStop {
        void refer(int i, int i2);
    }

    public abstract void onScrollListener(AbsListView absListView, int i, int i2, int i3);

    public abstract void onScrollStateChange(AbsListView absListView, int i);

    public boolean isLoader() {
        return (this.scroll_stats && this.fling_stats) ? false : true;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.scroll_stats = true;
        this.firstVisibleItem = i;
        this.visibleItemCount = i2;
        onScrollListener(absListView, i, i2, i3);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        onScrollStateChange(absListView, i);
        if (i == 0) {
            this.scroll_stats = false;
            this.fling_stats = false;
            OnStop onStop = this.onStop;
            if (onStop != null) {
                onStop.refer(this.firstVisibleItem, this.visibleItemCount);
                return;
            }
            return;
        }
        if (i == 1) {
            this.scroll_stats = true;
            this.fling_stats = false;
        } else {
            if (i != 2) {
                return;
            }
            this.fling_stats = true;
        }
    }

    public OnStop getOnStop() {
        return this.onStop;
    }

    public void setOnStop(OnStop onStop) {
        this.onStop = onStop;
    }
}
