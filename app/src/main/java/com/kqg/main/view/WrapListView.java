package com.kqg.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/* loaded from: classes.dex */
public class WrapListView extends ListView {
    private int mWidth;

    public WrapListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WrapListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mWidth = 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredHeight = getMeasuredHeight();
        measureChildren(i, i2);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            this.mWidth = Math.max(this.mWidth, getChildAt(i3).getMeasuredWidth());
        }
        setMeasuredDimension(this.mWidth, measuredHeight);
    }

    protected void setListWidth(int i) {
        this.mWidth = i;
    }
}
