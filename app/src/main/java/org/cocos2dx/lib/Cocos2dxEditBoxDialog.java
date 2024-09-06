package org.cocos2dx.lib;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.InputDeviceCompat;

/* loaded from: classes.dex */
public class Cocos2dxEditBoxDialog extends Dialog {
    private final int kEditBoxInputFlagInitialCapsAllCharacters;
    private final int kEditBoxInputFlagInitialCapsSentence;
    private final int kEditBoxInputFlagInitialCapsWord;
    private final int kEditBoxInputFlagPassword;
    private final int kEditBoxInputFlagSensitive;
    private final int kEditBoxInputModeAny;
    private final int kEditBoxInputModeDecimal;
    private final int kEditBoxInputModeEmailAddr;
    private final int kEditBoxInputModeNumeric;
    private final int kEditBoxInputModePhoneNumber;
    private final int kEditBoxInputModeSingleLine;
    private final int kEditBoxInputModeUrl;
    private final int kKeyboardReturnTypeDefault;
    private final int kKeyboardReturnTypeDone;
    private final int kKeyboardReturnTypeGo;
    private final int kKeyboardReturnTypeSearch;
    private final int kKeyboardReturnTypeSend;
    private EditText mInputEditText;
    private final int mInputFlag;
    private int mInputFlagConstraints;
    private final int mInputMode;
    private int mInputModeContraints;
    private boolean mIsMultiline;
    private final int mMaxLength;
    private final String mMessage;
    private final int mReturnType;
    private TextView mTextViewTitle;
    private final String mTitle;

    public Cocos2dxEditBoxDialog(Context context, String str, String str2, int i, int i2, int i3, int i4) {
        super(context, R.style.Theme.Translucent.NoTitleBar.Fullscreen);
        this.kEditBoxInputModeAny = 0;
        this.kEditBoxInputModeEmailAddr = 1;
        this.kEditBoxInputModeNumeric = 2;
        this.kEditBoxInputModePhoneNumber = 3;
        this.kEditBoxInputModeUrl = 4;
        this.kEditBoxInputModeDecimal = 5;
        this.kEditBoxInputModeSingleLine = 6;
        this.kEditBoxInputFlagPassword = 0;
        this.kEditBoxInputFlagSensitive = 1;
        this.kEditBoxInputFlagInitialCapsWord = 2;
        this.kEditBoxInputFlagInitialCapsSentence = 3;
        this.kEditBoxInputFlagInitialCapsAllCharacters = 4;
        this.kKeyboardReturnTypeDefault = 0;
        this.kKeyboardReturnTypeDone = 1;
        this.kKeyboardReturnTypeSend = 2;
        this.kKeyboardReturnTypeSearch = 3;
        this.kKeyboardReturnTypeGo = 4;
        this.mTitle = str;
        this.mMessage = str2;
        this.mInputMode = i;
        this.mInputFlag = i2;
        this.mReturnType = i3;
        this.mMaxLength = i4;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(Integer.MIN_VALUE));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        this.mTextViewTitle = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        int convertDipsToPixels = convertDipsToPixels(10.0f);
        layoutParams2.rightMargin = convertDipsToPixels;
        layoutParams2.leftMargin = convertDipsToPixels;
        this.mTextViewTitle.setTextSize(1, 20.0f);
        linearLayout.addView(this.mTextViewTitle, layoutParams2);
        this.mInputEditText = new EditText(getContext());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        int convertDipsToPixels2 = convertDipsToPixels(10.0f);
        layoutParams3.rightMargin = convertDipsToPixels2;
        layoutParams3.leftMargin = convertDipsToPixels2;
        linearLayout.addView(this.mInputEditText, layoutParams3);
        setContentView(linearLayout, layoutParams);
        getWindow().addFlags(1024);
        this.mTextViewTitle.setText(this.mTitle);
        this.mInputEditText.setText(this.mMessage);
        this.mInputEditText.setImeOptions(this.mInputEditText.getImeOptions() | 268435456);
        int imeOptions = this.mInputEditText.getImeOptions();
        switch (this.mInputMode) {
            case 0:
                this.mInputModeContraints = 131073;
                break;
            case 1:
                this.mInputModeContraints = 33;
                break;
            case 2:
                this.mInputModeContraints = InputDeviceCompat.SOURCE_TOUCHSCREEN;
                break;
            case 3:
                this.mInputModeContraints = 3;
                break;
            case 4:
                this.mInputModeContraints = 17;
                break;
            case 5:
                this.mInputModeContraints = 12290;
                break;
            case 6:
                this.mInputModeContraints = 1;
                break;
        }
        if (this.mIsMultiline) {
            this.mInputModeContraints |= 131072;
        }
        this.mInputEditText.setInputType(this.mInputModeContraints | this.mInputFlagConstraints);
        int i = this.mInputFlag;
        if (i == 0) {
            this.mInputFlagConstraints = 129;
        } else if (i == 1) {
            this.mInputFlagConstraints = 524288;
        } else if (i == 2) {
            this.mInputFlagConstraints = 8192;
        } else if (i == 3) {
            this.mInputFlagConstraints = 16384;
        } else if (i == 4) {
            this.mInputFlagConstraints = 4096;
        }
        this.mInputEditText.setInputType(this.mInputFlagConstraints | this.mInputModeContraints);
        int i2 = this.mReturnType;
        if (i2 == 0) {
            this.mInputEditText.setImeOptions(imeOptions | 1);
        } else if (i2 == 1) {
            this.mInputEditText.setImeOptions(imeOptions | 6);
        } else if (i2 == 2) {
            this.mInputEditText.setImeOptions(imeOptions | 4);
        } else if (i2 == 3) {
            this.mInputEditText.setImeOptions(imeOptions | 3);
        } else if (i2 == 4) {
            this.mInputEditText.setImeOptions(imeOptions | 2);
        } else {
            this.mInputEditText.setImeOptions(imeOptions | 1);
        }
        if (this.mMaxLength > 0) {
            this.mInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mMaxLength)});
        }
        new Handler().postDelayed(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxEditBoxDialog.1
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxEditBoxDialog.this.mInputEditText.requestFocus();
                Cocos2dxEditBoxDialog.this.mInputEditText.setSelection(Cocos2dxEditBoxDialog.this.mInputEditText.length());
                Cocos2dxEditBoxDialog.this.openKeyboard();
            }
        }, 200L);
        this.mInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: org.cocos2dx.lib.Cocos2dxEditBoxDialog.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
                if (i3 == 0 && (i3 != 0 || keyEvent == null || keyEvent.getAction() != 0)) {
                    return false;
                }
                Cocos2dxHelper.setEditTextDialogResult(Cocos2dxEditBoxDialog.this.mInputEditText.getText().toString());
                Cocos2dxEditBoxDialog.this.closeKeyboard();
                Cocos2dxEditBoxDialog.this.dismiss();
                return true;
            }
        });
    }

    private int convertDipsToPixels(float f) {
        return Math.round(f * getContext().getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openKeyboard() {
        ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this.mInputEditText, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeKeyboard() {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
    }
}
