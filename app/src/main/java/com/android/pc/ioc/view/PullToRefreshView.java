package com.android.pc.ioc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.pc.ioc.event.EventBus;
import com.android.pc.util.Handler_Bitmap;
import com.android.pc.util.Handler_System;
import com.tencent.mm.opensdk.modelmsg.WXVideoFileObject;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class PullToRefreshView extends LinearLayout {
    private static final int PULL_DOWN_STATE = 1;
    private static final int PULL_NONE_STATE = -1;
    private static final int PULL_TO_REFRESH = 2;
    private static final int PULL_UP_STATE = 0;
    private static final int REFRESHING = 4;
    private static final int RELEASE_TO_REFRESH = 3;
    private Bitmap downBitmap;
    private EventBus eventBus;
    private boolean isFooter;
    private boolean isGet;
    private boolean isHeader;
    private AdapterView<?> mAdapterView;
    private RotateAnimation mFlipAnimation;
    private ImageView mFooterImageView;
    private ProgressBar mFooterProgressBar;
    private int mFooterState;
    private TextView mFooterTextView;
    private RelativeLayout mFooterView;
    private int mFooterViewHeight;
    private ImageView mHeaderImageView;
    private ProgressBar mHeaderProgressBar;
    private int mHeaderState;
    private TextView mHeaderTextView;
    private TextView mHeaderUpdateTextView;
    private RelativeLayout mHeaderView;
    private int mHeaderViewHeight;
    private int mLastMotionY;
    private OnFooterRefreshListener mOnFooterRefreshListener;
    private OnHeaderRefreshListener mOnHeaderRefreshListener;
    private int mPullState;
    private RotateAnimation mReverseFlipAnimation;
    private ScrollView mScrollView;
    private PullToRefreshManager manager;
    private Bitmap upBitmap;

    /* loaded from: classes.dex */
    public interface OnFooterRefreshListener {
        void onFooterRefresh(PullToRefreshView pullToRefreshView);
    }

    /* loaded from: classes.dex */
    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(PullToRefreshView pullToRefreshView);
    }

    public PullToRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPullState = -1;
        this.isGet = false;
        this.isHeader = true;
        this.isFooter = true;
        this.eventBus = EventBus.getDefault();
        init();
    }

    public PullToRefreshView(Context context) {
        super(context);
        this.mPullState = -1;
        this.isGet = false;
        this.isHeader = true;
        this.isFooter = true;
        this.eventBus = EventBus.getDefault();
        init();
    }

    private void init() {
        this.manager = PullToRefreshManager.getInstance();
        setOrientation(1);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.mFlipAnimation = rotateAnimation;
        rotateAnimation.setInterpolator(new LinearInterpolator());
        this.mFlipAnimation.setDuration(250L);
        this.mFlipAnimation.setFillAfter(true);
        RotateAnimation rotateAnimation2 = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.mReverseFlipAnimation = rotateAnimation2;
        rotateAnimation2.setInterpolator(new LinearInterpolator());
        this.mReverseFlipAnimation.setDuration(250L);
        this.mReverseFlipAnimation.setFillAfter(true);
        addHeaderView();
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        super.addView(view);
    }

    private void addHeaderView() {
        try {
            InputStream open = getResources().getAssets().open("down.png");
            InputStream open2 = getResources().getAssets().open("up.png");
            this.downBitmap = BitmapFactory.decodeStream(open);
            this.upBitmap = BitmapFactory.decodeStream(open2);
        } catch (IOException unused) {
        }
        float padRoate = Handler_System.getPadRoate();
        this.downBitmap = Handler_Bitmap.scaleImg(this.downBitmap, (int) (r1.getWidth() * padRoate), (int) (this.downBitmap.getHeight() * padRoate));
        this.upBitmap = Handler_Bitmap.scaleImg(this.upBitmap, (int) (r1.getWidth() * padRoate), (int) (this.upBitmap.getHeight() * padRoate));
        this.mHeaderView = new RelativeLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (100.0f * padRoate));
        int i = (int) (15.0f * padRoate);
        layoutParams.bottomMargin = i;
        layoutParams.topMargin = i;
        layoutParams.gravity = 17;
        this.mHeaderView.setLayoutParams(layoutParams);
        ProgressBar progressBar = new ProgressBar(getContext());
        this.mHeaderProgressBar = progressBar;
        progressBar.setIndeterminate(false);
        this.mHeaderProgressBar.setVisibility(8);
        int i2 = (int) (40.0f * padRoate);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i2);
        int i3 = (int) (30.0f * padRoate);
        layoutParams2.leftMargin = i3;
        int i4 = (int) (padRoate * 20.0f);
        layoutParams2.rightMargin = i4;
        layoutParams2.addRule(15);
        this.mHeaderProgressBar.setLayoutParams(layoutParams2);
        this.mHeaderView.addView(this.mHeaderProgressBar);
        this.mHeaderImageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = i3;
        layoutParams3.rightMargin = i4;
        layoutParams3.addRule(15);
        this.mHeaderImageView.setLayoutParams(layoutParams3);
        this.mHeaderImageView.setImageBitmap(this.downBitmap);
        this.mHeaderView.addView(this.mHeaderImageView);
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(15);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams4);
        TextView textView = new TextView(getContext());
        this.mHeaderTextView = textView;
        textView.setGravity(17);
        this.mHeaderTextView.setText(this.manager.getPull_label());
        this.mHeaderTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mHeaderTextView.setTypeface(Typeface.DEFAULT_BOLD, 2);
        this.mHeaderTextView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        linearLayout.addView(this.mHeaderTextView);
        TextView textView2 = new TextView(getContext());
        this.mHeaderUpdateTextView = textView2;
        textView2.setGravity(17);
        this.mHeaderUpdateTextView.setText(this.manager.getUpdateTime());
        this.mHeaderUpdateTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mHeaderUpdateTextView.setVisibility(8);
        this.mHeaderUpdateTextView.setTextSize(10.0f);
        this.mHeaderUpdateTextView.setLayoutParams(new RelativeLayout.LayoutParams(-1, 40));
        linearLayout.addView(this.mHeaderUpdateTextView);
        this.mHeaderView.addView(linearLayout);
        measureView(this.mHeaderView);
        this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
        layoutParams5.topMargin = -this.mHeaderViewHeight;
        addView(this.mHeaderView, layoutParams5);
    }

    private void addFooterView() {
        this.mFooterView = new RelativeLayout(getContext());
        float padRoate = Handler_System.getPadRoate();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = (int) (15.0f * padRoate);
        int i = (int) (10.0f * padRoate);
        layoutParams.topMargin = i;
        layoutParams.gravity = 17;
        this.mFooterView.setLayoutParams(layoutParams);
        ProgressBar progressBar = new ProgressBar(getContext());
        this.mFooterProgressBar = progressBar;
        progressBar.setIndeterminate(false);
        this.mFooterProgressBar.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        int i2 = (int) (30.0f * padRoate);
        layoutParams2.leftMargin = i2;
        layoutParams2.topMargin = i;
        int i3 = (int) (padRoate * 20.0f);
        layoutParams2.rightMargin = i3;
        this.mFooterProgressBar.setLayoutParams(layoutParams2);
        this.mFooterView.addView(this.mFooterProgressBar);
        this.mFooterImageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = i2;
        layoutParams3.rightMargin = i3;
        this.mFooterImageView.setLayoutParams(layoutParams3);
        this.mFooterImageView.setImageBitmap(this.upBitmap);
        this.mFooterView.addView(this.mFooterImageView);
        LinearLayout linearLayout = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        linearLayout.setOrientation(1);
        layoutParams4.addRule(13);
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams4);
        TextView textView = new TextView(getContext());
        this.mFooterTextView = textView;
        textView.setGravity(17);
        this.mFooterTextView.setText(this.manager.getFooter_pull_label());
        this.mFooterTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mFooterTextView.setTypeface(Typeface.DEFAULT_BOLD, 2);
        this.mFooterTextView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        linearLayout.addView(this.mFooterTextView);
        this.mFooterView.addView(linearLayout);
        measureView(this.mFooterView);
        this.mFooterViewHeight = this.mFooterView.getMeasuredHeight();
        addView(this.mFooterView, new LinearLayout.LayoutParams(-1, this.mFooterViewHeight));
    }

    public int getmHeaderState() {
        return this.mHeaderState;
    }

    public void setmHeaderState(int i) {
        this.mHeaderState = i;
    }

    public int getmFooterState() {
        return this.mFooterState;
    }

    public void setmFooterState(int i) {
        this.mFooterState = i;
    }

    public void onFooter() {
        addFooterView();
        initContentAdapterView();
        if (getChildAt(0) == null) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.pc.ioc.view.PullToRefreshView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (PullToRefreshView.this.isGet) {
                    return true;
                }
                PullToRefreshView.this.isGet = true;
                if (PullToRefreshView.this.mAdapterView != null) {
                    int i = 0;
                    if (LinearLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.mAdapterView.getLayoutParams().getClass())) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) PullToRefreshView.this.mAdapterView.getLayoutParams();
                        layoutParams.height = PullToRefreshView.this.mAdapterView.getHeight();
                        if (layoutParams.height <= 2) {
                            layoutParams.height = PullToRefreshView.this.getHeight();
                        }
                        PullToRefreshView.this.mAdapterView.setLayoutParams(layoutParams);
                        i = layoutParams.height;
                    }
                    if (LinearLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.getLayoutParams().getClass())) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) PullToRefreshView.this.getLayoutParams();
                        if (i != 0) {
                            layoutParams2.height = i;
                        }
                        PullToRefreshView.this.setLayoutParams(layoutParams2);
                    }
                    if (AbsoluteLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.mAdapterView.getLayoutParams().getClass())) {
                        AbsoluteLayout.LayoutParams layoutParams3 = (AbsoluteLayout.LayoutParams) PullToRefreshView.this.mAdapterView.getLayoutParams();
                        layoutParams3.height = PullToRefreshView.this.mAdapterView.getHeight();
                        if (layoutParams3.height <= 2) {
                            layoutParams3.height = PullToRefreshView.this.getHeight();
                        }
                        PullToRefreshView.this.mAdapterView.setLayoutParams(layoutParams3);
                        i = layoutParams3.height;
                    }
                    if (AbsoluteLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.getLayoutParams().getClass())) {
                        AbsoluteLayout.LayoutParams layoutParams4 = (AbsoluteLayout.LayoutParams) PullToRefreshView.this.getLayoutParams();
                        if (i != 0) {
                            layoutParams4.height = i;
                        }
                        PullToRefreshView.this.setLayoutParams(layoutParams4);
                    }
                    if (RelativeLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.mAdapterView.getLayoutParams().getClass())) {
                        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) PullToRefreshView.this.mAdapterView.getLayoutParams();
                        layoutParams5.height = PullToRefreshView.this.mAdapterView.getHeight();
                        if (layoutParams5.height <= 2) {
                            layoutParams5.height = PullToRefreshView.this.getHeight();
                        }
                        PullToRefreshView.this.mAdapterView.setLayoutParams(layoutParams5);
                        i = layoutParams5.height;
                    }
                    if (RelativeLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.getLayoutParams().getClass())) {
                        RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) PullToRefreshView.this.getLayoutParams();
                        if (i != 0) {
                            layoutParams6.height = i;
                        }
                        PullToRefreshView.this.setLayoutParams(layoutParams6);
                    }
                    if (FrameLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.mAdapterView.getLayoutParams().getClass())) {
                        FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) PullToRefreshView.this.mAdapterView.getLayoutParams();
                        layoutParams7.height = PullToRefreshView.this.mAdapterView.getHeight();
                        if (layoutParams7.height <= 2) {
                            layoutParams7.height = PullToRefreshView.this.getHeight();
                        }
                        PullToRefreshView.this.mAdapterView.setLayoutParams(layoutParams7);
                        i = layoutParams7.height;
                    }
                    if (FrameLayout.LayoutParams.class.isAssignableFrom(PullToRefreshView.this.getLayoutParams().getClass())) {
                        FrameLayout.LayoutParams layoutParams8 = (FrameLayout.LayoutParams) PullToRefreshView.this.getLayoutParams();
                        if (i != 0) {
                            layoutParams8.height = i;
                        }
                        PullToRefreshView.this.setLayoutParams(layoutParams8);
                    }
                }
                return true;
            }
        });
    }

    private void initContentAdapterView() {
        int childCount = getChildCount();
        if (childCount < 3) {
            throw new IllegalArgumentException("This layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AdapterView) {
                this.mAdapterView = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.mScrollView = (ScrollView) childAt;
            }
        }
        if (this.mAdapterView == null && this.mScrollView == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }

    @Override // android.view.ViewGroup
    protected void measureChild(View view, int i, int i2) {
        super.measureChild(view, i, i2);
    }

    private void measureView(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, WXVideoFileObject.FILE_SIZE_LIMIT);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action != 0) {
            return action == 2 && isRefreshViewScroll(rawY - this.mLastMotionY);
        }
        this.mLastMotionY = rawY;
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        if (r1 != 3) goto L25;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r5.getRawY()
            int r0 = (int) r0
            int r1 = r5.getAction()
            r2 = 1
            if (r1 == r2) goto L27
            r3 = 2
            if (r1 == r3) goto L13
            r0 = 3
            if (r1 == r0) goto L27
            goto L51
        L13:
            int r1 = r4.mLastMotionY
            int r1 = r0 - r1
            int r3 = r4.mPullState
            if (r3 != r2) goto L1f
            r4.headerPrepareToRefresh(r1)
            goto L24
        L1f:
            if (r3 != 0) goto L24
            r4.footerPrepareToRefresh(r1)
        L24:
            r4.mLastMotionY = r0
            goto L51
        L27:
            int r0 = r4.getHeaderTopMargin()
            int r1 = r4.mPullState
            if (r1 != r2) goto L3c
            if (r0 < 0) goto L35
            r4.headerRefreshing()
            goto L51
        L35:
            int r0 = r4.mHeaderViewHeight
            int r0 = -r0
            r4.setHeaderTopMargin(r0)
            goto L51
        L3c:
            if (r1 != 0) goto L51
            int r0 = java.lang.Math.abs(r0)
            int r1 = r4.mHeaderViewHeight
            int r2 = r4.mFooterViewHeight
            int r2 = r2 + r1
            if (r0 < r2) goto L4d
            r4.footerRefreshing()
            goto L51
        L4d:
            int r0 = -r1
            r4.setHeaderTopMargin(r0)
        L51:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.ioc.view.PullToRefreshView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean isRefreshViewScroll(int i) {
        if (this.mHeaderState != 4 && this.mFooterState != 4) {
            AdapterView<?> adapterView = this.mAdapterView;
            if (adapterView != null) {
                if (i > 0) {
                    if (!this.isHeader) {
                        this.mPullState = -1;
                        return false;
                    }
                    View childAt = adapterView.getChildAt(0);
                    if (childAt == null) {
                        return false;
                    }
                    if (this.mAdapterView.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                        this.mPullState = 1;
                        return true;
                    }
                    int top = childAt.getTop();
                    int paddingTop = this.mAdapterView.getPaddingTop();
                    if (this.mAdapterView.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                        this.mPullState = 1;
                        return true;
                    }
                } else if (i < 0) {
                    if (!this.isFooter) {
                        this.mPullState = -1;
                        return false;
                    }
                    View childAt2 = adapterView.getChildAt(adapterView.getChildCount() - 1);
                    if (childAt2 == null) {
                        return false;
                    }
                    if (childAt2.getBottom() <= getHeight() && this.mAdapterView.getLastVisiblePosition() == this.mAdapterView.getCount() - 1) {
                        this.mPullState = 0;
                        return true;
                    }
                }
            }
            ScrollView scrollView = this.mScrollView;
            if (scrollView != null) {
                View childAt3 = scrollView.getChildAt(0);
                if (i > 0 && this.mScrollView.getScrollY() == 0) {
                    if (!this.isHeader) {
                        this.mPullState = -1;
                        return false;
                    }
                    this.mPullState = 1;
                    return true;
                }
                if (i < 0 && childAt3.getMeasuredHeight() <= getHeight() + this.mScrollView.getScrollY()) {
                    if (!this.isFooter) {
                        this.mPullState = -1;
                        return false;
                    }
                    this.mPullState = 0;
                    return true;
                }
            }
        }
        return false;
    }

    private void headerPrepareToRefresh(int i) {
        int changingHeaderViewTopMargin = changingHeaderViewTopMargin(i);
        if (changingHeaderViewTopMargin >= 0 && this.mHeaderState != 3) {
            this.mHeaderTextView.setText(this.manager.getRelease_label());
            this.mHeaderUpdateTextView.setVisibility(0);
            this.mHeaderImageView.clearAnimation();
            this.mHeaderImageView.startAnimation(this.mFlipAnimation);
            this.mHeaderState = 3;
            return;
        }
        if (changingHeaderViewTopMargin >= 0 || changingHeaderViewTopMargin <= (-this.mHeaderViewHeight)) {
            return;
        }
        this.mHeaderImageView.clearAnimation();
        this.mHeaderImageView.startAnimation(this.mFlipAnimation);
        this.mHeaderTextView.setText(this.manager.getRelease_label());
        this.mHeaderState = 2;
    }

    private void footerPrepareToRefresh(int i) {
        int changingHeaderViewTopMargin = changingHeaderViewTopMargin(i);
        if (Math.abs(changingHeaderViewTopMargin) >= this.mHeaderViewHeight + this.mFooterViewHeight && this.mFooterState != 3) {
            this.mFooterTextView.setText(this.manager.getFooter_refreshing_label());
            this.mFooterImageView.clearAnimation();
            this.mFooterImageView.startAnimation(this.mFlipAnimation);
            this.mFooterState = 3;
            return;
        }
        if (Math.abs(changingHeaderViewTopMargin) < this.mHeaderViewHeight + this.mFooterViewHeight) {
            this.mFooterImageView.clearAnimation();
            this.mFooterImageView.startAnimation(this.mFlipAnimation);
            this.mFooterTextView.setText(this.manager.getFooter_pull_label());
            this.mFooterState = 2;
        }
    }

    private int changingHeaderViewTopMargin(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        float f = layoutParams.topMargin + (i * 0.5f);
        if (i > 0 && this.mPullState == 0 && Math.abs(layoutParams.topMargin) <= this.mHeaderViewHeight) {
            return layoutParams.topMargin;
        }
        if (i < 0 && this.mPullState == 1 && Math.abs(layoutParams.topMargin) >= this.mHeaderViewHeight) {
            return layoutParams.topMargin;
        }
        layoutParams.topMargin = (int) f;
        this.mHeaderView.setLayoutParams(layoutParams);
        return layoutParams.topMargin;
    }

    private void headerRefreshing() {
        this.mHeaderState = 4;
        setHeaderTopMargin(0);
        this.mHeaderImageView.setVisibility(8);
        this.mHeaderImageView.clearAnimation();
        this.mHeaderImageView.setImageDrawable(null);
        this.mHeaderProgressBar.setVisibility(0);
        this.mHeaderTextView.setText(this.manager.getRefreshing_label());
        OnHeaderRefreshListener onHeaderRefreshListener = this.mOnHeaderRefreshListener;
        if (onHeaderRefreshListener != null) {
            onHeaderRefreshListener.onHeaderRefresh(this);
        }
    }

    private void footerRefreshing() {
        this.mFooterState = 4;
        setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
        this.mFooterImageView.setVisibility(8);
        this.mFooterImageView.clearAnimation();
        this.mFooterImageView.setImageDrawable(null);
        this.mFooterProgressBar.setVisibility(0);
        this.mFooterTextView.setText(this.manager.getRefreshing_label());
        OnFooterRefreshListener onFooterRefreshListener = this.mOnFooterRefreshListener;
        if (onFooterRefreshListener != null) {
            onFooterRefreshListener.onFooterRefresh(this);
        }
    }

    private void setHeaderTopMargin(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        layoutParams.topMargin = i;
        this.mHeaderView.setLayoutParams(layoutParams);
    }

    public void onHeaderRefreshComplete() {
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mHeaderImageView.setVisibility(0);
        this.mHeaderImageView.setImageBitmap(this.downBitmap);
        this.mHeaderTextView.setText(this.manager.getRelease_label());
        this.mHeaderProgressBar.setVisibility(8);
        this.mHeaderState = 2;
    }

    public void onHeaderRefreshComplete(CharSequence charSequence) {
        setLastUpdated(charSequence);
        onHeaderRefreshComplete();
    }

    public void onFooterRefreshComplete() {
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mFooterImageView.setVisibility(0);
        this.mFooterImageView.setImageBitmap(this.upBitmap);
        this.mFooterTextView.setText(this.manager.getFooter_pull_label());
        this.mFooterProgressBar.setVisibility(8);
        this.mFooterState = 2;
    }

    public void setLastUpdated(CharSequence charSequence) {
        if (charSequence != null) {
            this.mHeaderUpdateTextView.setVisibility(0);
            this.mHeaderUpdateTextView.setText(charSequence);
        } else {
            this.mHeaderUpdateTextView.setVisibility(8);
        }
    }

    private int getHeaderTopMargin() {
        return ((LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams()).topMargin;
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener onHeaderRefreshListener) {
        this.mOnHeaderRefreshListener = onHeaderRefreshListener;
    }

    public void setOnFooterRefreshListener(OnFooterRefreshListener onFooterRefreshListener) {
        this.mOnFooterRefreshListener = onFooterRefreshListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.eventBus.unregister(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.eventBus.register(this);
        } else if (i == 4) {
            this.eventBus.unregister(this);
        } else {
            if (i != 8) {
                return;
            }
            this.eventBus.unregister(this);
        }
    }

    public AdapterView<?> getAdapterView() {
        return this.mAdapterView;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean z) {
        this.isHeader = z;
    }

    public boolean isFooter() {
        return this.isFooter;
    }

    public void setFooter(boolean z) {
        this.isFooter = z;
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [android.widget.Adapter] */
    public void onEventMainThread(RefershEntity refershEntity) {
        AdapterView<?> adapterView;
        switch (refershEntity.getType()) {
            case 1:
                onFooterRefreshComplete();
                if (this.manager.getLimit() == 0 || (adapterView = this.mAdapterView) == null) {
                    return;
                }
                if (adapterView.getAdapter().getCount() % this.manager.getLimit() != 0) {
                    setFooter(false);
                    return;
                } else {
                    setFooter(true);
                    return;
                }
            case 2:
                onHeaderRefreshComplete();
                return;
            case 3:
                setFooter(false);
                return;
            case 4:
                setHeader(false);
                return;
            case 5:
                setFooter(true);
                return;
            case 6:
                setHeader(true);
                return;
            default:
                return;
        }
    }
}
