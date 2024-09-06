package com.components;

import android.app.Activity;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface PlatformInterface {
    void addToBlackList(Activity activity, JSONObject jSONObject);

    void biRecord(Activity activity, JSONObject jSONObject);

    void buyProduct(Activity activity, JSONObject jSONObject);

    void checkUpdate(Activity activity, JSONObject jSONObject);

    void createNewPlayer(Activity activity, JSONObject jSONObject);

    void enterChatRoom(Activity activity, JSONObject jSONObject);

    void enterMainGame(Activity activity, JSONObject jSONObject);

    void enterUserCenter(Activity activity, JSONObject jSONObject);

    void exitCharRoom(Activity activity, JSONObject jSONObject);

    void exitGame(Activity activity, JSONObject jSONObject);

    void exitGameByBackKey(Activity activity);

    void getPlatForm(Activity activity, JSONObject jSONObject);

    String getPlatformId();

    void getUserName(Activity activity, JSONObject jSONObject);

    void hideStartLoadingView();

    void init(Activity activity);

    void initPlatformSDK(Activity activity);

    void initPlatformSDKSuccess(Activity activity);

    void isLoginChat(Activity activity, JSONObject jSONObject);

    void isLogined(Activity activity, JSONObject jSONObject);

    void logIn(Activity activity, JSONObject jSONObject);

    void logInResult(Activity activity, JSONObject jSONObject);

    void logOut(Activity activity, JSONObject jSONObject);

    void loginChat(Activity activity, JSONObject jSONObject);

    void needShowSDKBtn(Activity activity, JSONObject jSONObject);

    void onCharge(Activity activity, JSONObject jSONObject);

    void onLogin(Activity activity, JSONObject jSONObject);

    void onLogout(Activity activity, JSONObject jSONObject);

    void paseVideo(Activity activity, JSONObject jSONObject);

    void platformExit(Activity activity, JSONObject jSONObject);

    void platformName(Activity activity, JSONObject jSONObject);

    void playVideo(Activity activity, JSONObject jSONObject);

    void queryMsg(Activity activity, JSONObject jSONObject);

    void queryRoomMsg(Activity activity, JSONObject jSONObject);

    void queryTeamMsg(Activity activity, JSONObject jSONObject);

    void removeFromBlackList(Activity activity, JSONObject jSONObject);

    void resumeVideo(Activity activity, JSONObject jSONObject);

    void sdkInit(Activity activity, JSONObject jSONObject);

    void sdkInitSuccessCallBack(Activity activity);

    void sdkSwitchAccount(Activity activity, JSONObject jSONObject);

    void sendAllData(Activity activity, JSONObject jSONObject);

    void sendInfo(Activity activity, JSONObject jSONObject);

    void sendMsg(Activity activity, JSONObject jSONObject);

    void sendRoomMsg(Activity activity, JSONObject jSONObject);

    void setGameServer(Activity activity, JSONObject jSONObject);

    void showFloatBar(Activity activity, JSONObject jSONObject);

    void showGameCenter(Activity activity, JSONObject jSONObject);

    void showStartLoadingView(Activity activity);
}
