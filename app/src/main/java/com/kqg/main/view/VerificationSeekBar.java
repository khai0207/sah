package com.kqg.main.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import androidx.core.view.ViewCompat;
import com.kqg.main.R;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class VerificationSeekBar extends SeekBar {
    onEndListener endListener;
    private int index;
    private boolean isMoveMax;
    private boolean k;
    private Paint mPaint;
    private Rect mProgressTextRect;
    private String registerName;

    /* loaded from: classes.dex */
    public interface onEndListener {
        void onEnd(String str);

        void resetView(String str);
    }

    public VerificationSeekBar(Context context) {
        super(context);
        this.index = 150;
        this.k = true;
        this.registerName = null;
        this.isMoveMax = false;
        this.mPaint = new Paint();
        this.mProgressTextRect = new Rect();
        this.endListener = null;
        initListener();
    }

    public VerificationSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.index = 150;
        this.k = true;
        this.registerName = null;
        this.isMoveMax = false;
        this.mPaint = new Paint();
        this.mProgressTextRect = new Rect();
        this.endListener = null;
        initListener();
    }

    public VerificationSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.index = 150;
        this.k = true;
        this.registerName = null;
        this.isMoveMax = false;
        this.mPaint = new Paint();
        this.mProgressTextRect = new Rect();
        this.endListener = null;
        initListener();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        if (motionEvent.getAction() == 0) {
            this.k = true;
            if (x - this.index > 20) {
                this.k = false;
                return true;
            }
        }
        if (motionEvent.getAction() == 2 && !this.k) {
            return true;
        }
        if (motionEvent.getAction() != 1 || this.k) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private void initListener() {
        TextPaint textPaint = new TextPaint();
        this.mPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mPaint.setColor(Color.parseColor("#00574B"));
        this.mPaint.setTextSize(SizeUtils.sp2px(18.0f));
        setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.kqg.main.view.VerificationSeekBar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (Build.VERSION.SDK_INT >= 16) {
                    if (i == 100) {
                        seekBar.getThumb().getBounds();
                        Drawable drawable = VerificationSeekBar.this.getResources().getDrawable(R.drawable.sdk_huakuai03);
                        seekBar.setThumb(drawable);
                        seekBar.setThumbOffset(drawable.getMinimumWidth());
                        VerificationSeekBar.this.isMoveMax = true;
                        return;
                    }
                    if (i >= 90) {
                        if (VerificationSeekBar.this.isMoveMax) {
                            Drawable drawable2 = VerificationSeekBar.this.getResources().getDrawable(R.drawable.sdk_huakuai03);
                            seekBar.setThumb(drawable2);
                            seekBar.setThumbOffset(drawable2.getMinimumWidth());
                            return;
                        } else {
                            seekBar.getThumb().getBounds();
                            Drawable drawable3 = VerificationSeekBar.this.getResources().getDrawable(R.drawable.sdk_huakuai03);
                            seekBar.setThumb(drawable3);
                            seekBar.setThumbOffset(drawable3.getMinimumWidth());
                            VerificationSeekBar.this.isMoveMax = true;
                            return;
                        }
                    }
                    if (VerificationSeekBar.this.isMoveMax) {
                        VerificationSeekBar.this.isMoveMax = false;
                        seekBar.setThumb(VerificationSeekBar.this.getResources().getDrawable(R.drawable.sdk_huakuai02));
                        seekBar.setThumbOffset(0);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar == this) {
                    if (seekBar.getProgress() >= 90) {
                        if (VerificationSeekBar.this.endListener != null) {
                            VerificationSeekBar.this.endListener.onEnd(VerificationSeekBar.this.registerName);
                        }
                        VerificationSeekBar.this.setVisible(4);
                    }
                    seekBar.setProgress(0);
                }
            }
        });
    }

    public void setOnEndListener(onEndListener onendlistener) {
        this.endListener = onendlistener;
    }

    public void setVisible(int i) {
        setVisibility(i);
        if (i == 4 || i == 8) {
            onEndListener onendlistener = this.endListener;
            if (onendlistener != null) {
                onendlistener.resetView(this.registerName);
            }
            this.registerName = null;
        }
    }

    public void setRegisterName(String str) {
        this.registerName = str;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getProgress() < 90) {
            this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            String resString = UiUtils.getResString("VerificationSeekBar_1");
            this.mPaint.getTextBounds(resString, 0, resString.length(), this.mProgressTextRect);
            canvas.drawText(resString, (getWidth() / 2) - (this.mProgressTextRect.width() / 2), (getHeight() / 2) + (this.mProgressTextRect.height() / 2), this.mPaint);
        } else {
            this.mPaint.setColor(-1);
            String resString2 = UiUtils.getResString("VerificationSeekBar_2");
            this.mPaint.getTextBounds(resString2, 0, resString2.length(), this.mProgressTextRect);
            canvas.drawText(resString2, (getWidth() / 2) - (this.mProgressTextRect.width() / 2), (getHeight() / 2) + (this.mProgressTextRect.height() / 2), this.mPaint);
        }
        if (getProgress() < 90) {
            getThumb().draw(canvas);
        }
    }
}
