package com.kqg.main.activity;

import android.view.View;
import android.widget.Button;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.constant.KV;
import com.kqg.main.model.Message;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class QuitGame extends BaseActivity {
    private Button cancel_quit;
    private Button quit_game;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_quit_game");
        this.cancel_quit = (Button) getView("cancel_quit");
        Button button = (Button) getView("quit_game");
        this.quit_game = button;
        registOnClicks("click", this.cancel_quit, button);
        registRemoveList();
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("cancel_quit")) {
            finish();
            postMessageOnCurrentThread(new Message(KV.EVENT_CANCEL_QUIT));
        } else if (id == UiUtils.getId("quit_game")) {
            finish();
            postMessageOnCurrentThread(new Message(KV.EVENT_QUIT_GAME));
        }
    }
}
