package com.kqg.main.activity;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class RecordClickSpan extends ClickableSpan {
    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        textPaint.setUnderlineText(false);
    }
}
