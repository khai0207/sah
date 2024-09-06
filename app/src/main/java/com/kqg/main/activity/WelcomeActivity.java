package com.kqg.main.activity;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.model.User;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class WelcomeActivity extends BaseActivity {
    private TextView welcome_tip_text;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_welcome");
        setNotSwallowKeyDown(false);
        this.welcome_tip_text = (TextView) getView("welcome_tip_text");
        User user = (User) getIntent().getSerializableExtra("user");
        this.welcome_tip_text.setText(UiUtils.getResString("welcome_tip") + user.getUsername());
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
        translateAnimation.setDuration(1000L);
        this.welcome_tip_text.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.kqg.main.activity.WelcomeActivity.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                WelcomeActivity.handler.postDelayed(new Runnable() { // from class: com.kqg.main.activity.WelcomeActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WelcomeActivity.this.finish();
                    }
                }, 1000L);
            }
        });
        translateAnimation.startNow();
    }
}
