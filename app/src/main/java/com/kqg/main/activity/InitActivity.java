package com.kqg.main.activity;

import android.view.animation.Animation;
import android.widget.ImageView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.model.Message;

/* loaded from: classes.dex */
public class InitActivity extends BaseActivity {
    private ImageView loading_bg;
    private ImageView loading_tip;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_start");
        postMessageOnCurrentThread(new Message(200));
        finish();
    }

    /* loaded from: classes.dex */
    private class onAimation implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private onAimation() {
        }
    }
}
