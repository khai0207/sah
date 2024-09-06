package com.android.pc.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.ResultReceiver;
import android.view.Display;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Handler_Ui {
    public static void setSelection(Spinner spinner, Object obj) {
        setSelection(spinner, obj.toString());
    }

    public static void setSelection(Spinner spinner, String str) {
        int count = spinner.getCount();
        for (int i = 0; i < count; i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(str)) {
                spinner.setSelection(i);
            }
        }
    }

    public static void hideSoftKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftkeyboard(View view) {
        showSoftkeyboard(view, null);
    }

    public static void showSoftkeyboard(View view, ResultReceiver resultReceiver) {
        if (view.getContext().getResources().getConfiguration().hardKeyboardHidden == 2) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
            if (resultReceiver != null) {
                inputMethodManager.showSoftInput(view, 1, resultReceiver);
            } else {
                inputMethodManager.showSoftInput(view, 1);
            }
        }
    }

    public static void imageLLViewReset(View view, int i, int i2, boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        HashMap<String, Integer> displayMetrics = Handler_System.getDisplayMetrics();
        int intValue = displayMetrics.get(Handler_System.systemWidth).intValue();
        int intValue2 = displayMetrics.get(Handler_System.systemHeight).intValue();
        if (z) {
            if (intValue > intValue2) {
                layoutParams.width = (int) (((i * 1.0f) / i2) * intValue2);
                layoutParams.height = intValue2;
            } else {
                layoutParams.width = intValue;
                layoutParams.height = (int) (((i2 * 1.0f) / i) * intValue);
            }
        } else if (i > intValue) {
            layoutParams.width = intValue;
            layoutParams.height = (int) (((intValue * 1.0f) / i) * i2);
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void imageRLViewReset(View view, int i, int i2, boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        HashMap<String, Integer> displayMetrics = Handler_System.getDisplayMetrics();
        int intValue = displayMetrics.get(Handler_System.systemWidth).intValue();
        int intValue2 = displayMetrics.get(Handler_System.systemHeight).intValue();
        if (z) {
            if (intValue > intValue2) {
                layoutParams.width = (int) (((i * 1.0f) / i2) * intValue2);
                layoutParams.height = intValue2;
            } else {
                layoutParams.width = intValue;
                layoutParams.height = (int) (((i2 * 1.0f) / i) * intValue);
            }
        } else if (i > intValue) {
            layoutParams.width = intValue;
            layoutParams.height = (int) (((intValue * 1.0f) / i) * i2);
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void imageFLViewReset(View view, int i, int i2, boolean z) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        HashMap<String, Integer> displayMetrics = Handler_System.getDisplayMetrics();
        int intValue = displayMetrics.get(Handler_System.systemWidth).intValue();
        int intValue2 = displayMetrics.get(Handler_System.systemHeight).intValue();
        if (z) {
            if (intValue > intValue2) {
                layoutParams.width = (int) (((i * 1.0f) / i2) * intValue2);
                layoutParams.height = intValue2;
            } else {
                layoutParams.width = intValue;
                layoutParams.height = (int) (((i2 * 1.0f) / i) * intValue);
            }
        } else if (i > intValue) {
            layoutParams.width = intValue;
            layoutParams.height = (int) (((intValue * 1.0f) / i) * i2);
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void resetRL(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * widthRoate);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * widthRoate);
            layoutParams.topMargin = (int) (layoutParams.topMargin * widthRoate);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void resetRLBack(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (layoutParams.height * widthRoate);
            layoutParams.width = (int) (layoutParams.width * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void resetLL(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * widthRoate);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * widthRoate);
            layoutParams.topMargin = (int) (layoutParams.topMargin * widthRoate);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void resetLLBack(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (layoutParams.height * widthRoate);
            layoutParams.width = (int) (layoutParams.width * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void resetFL(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * widthRoate);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * widthRoate);
            layoutParams.topMargin = (int) (layoutParams.topMargin * widthRoate);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void resetFLBack(View... viewArr) {
        float widthRoate = Handler_System.getWidthRoate();
        if (viewArr == null || widthRoate == 1.0f) {
            return;
        }
        for (View view : viewArr) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (layoutParams.height * widthRoate);
            layoutParams.width = (int) (layoutParams.width * widthRoate);
            view.setLayoutParams(layoutParams);
        }
    }

    public static Bitmap shot(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        decorView.layout(0, 0, defaultDisplay.getWidth(), defaultDisplay.getHeight());
        decorView.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(decorView.getDrawingCache());
        decorView.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static void startAnim(ImageView imageView, int i) {
        try {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageResource(i);
            AnimationSet animationSet = new AnimationSet(false);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(2000L);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setRepeatMode(1);
            rotateAnimation.setRepeatCount(-1);
            animationSet.addAnimation(rotateAnimation);
            imageView.setAnimation(animationSet);
        } catch (Exception unused) {
        }
    }

    public static void stopAnim(ImageView imageView) {
        try {
            imageView.clearAnimation();
            imageView.setImageBitmap(null);
        } catch (Exception unused) {
        }
    }
}
