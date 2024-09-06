package com.components;

import android.app.Activity;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface StatisticsInterface {
    void buyProduct(Activity activity, JSONObject jSONObject);

    void checkUpdate(Activity activity, JSONObject jSONObject);

    void initChannel(Activity activity, String str);

    void isLogined(Activity activity, JSONObject jSONObject);

    void logIn(Activity activity, JSONObject jSONObject);

    void logOut(Activity activity, JSONObject jSONObject);

    void logToBI(Activity activity, JSONObject jSONObject);

    void onCoinChangedToBi(Activity activity, JSONObject jSONObject);

    void onEventToBi(Activity activity, JSONObject jSONObject);

    void onPause(Activity activity);

    void onResume(Activity activity);

    void onUserLevelChangeToBi(Activity activity, JSONObject jSONObject);

    void payBeginToBi(Activity activity, JSONObject jSONObject);

    void paySuccessToBi(Activity activity, JSONObject jSONObject);

    void sdkInit(Activity activity, JSONObject jSONObject);

    void sdkSwitchAccount(Activity activity, JSONObject jSONObject);

    void showGameCenter(Activity activity, JSONObject jSONObject);
}
