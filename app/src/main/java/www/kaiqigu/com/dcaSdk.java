package www.kaiqigu.com;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.alipay.sdk.m.h.c;
import com.alipay.sdk.m.o.a;
import com.components.PlatformHelper;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechEvent;
import com.kqg.main.base.ConfigInfor;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.callback.OnInitCallBackListener;
import com.kqg.main.callback.OnLoginCallBackListener;
import com.kqg.main.model.User;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.ChatRoomMessageBuilder;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomStatusChangeData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.lifecycle.SdkLifecycleObserver;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.NotificationType;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.sdk.msg.model.RecentSession;
import com.netease.nimlib.sdk.msg.model.RevokeMsgNotification;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.team.model.TeamMember;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class dcaSdk extends PlatformHelper {
    private static final String TAG = "yuenan---";
    public static Application application;
    static FrameLayout m_webLayout;
    static LinearLayout topLayout;
    private Activity act;
    private String uid;
    private boolean isLogined = false;
    final String platform_id = "kvgames";
    private String openid = "cjyxkv";
    private String orderid = "";
    private String wdjOrderId = "";
    private String appkey = "ogxyntaity7gc22x24t9rc9zmkojw2ir";
    private JSONObject userInfo = null;
    private String roomID = "";
    private String accID = "";
    private Boolean isLoginSuccess = false;
    private Boolean isInitSuccess = false;
    private Activity gAct = null;
    Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() { // from class: www.kaiqigu.com.dcaSdk.7
        @Override // com.netease.nimlib.sdk.Observer
        public void onEvent(List<IMMessage> list) {
            Log.i(dcaSdk.TAG, "消息没收到通过这里下发 " + list.size());
            final JSONObject jSONObject = new JSONObject();
            final ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                JSONObject jSONObject2 = new JSONObject();
                IMMessage iMMessage = list.get(i);
                if (iMMessage.getSessionType() == SessionTypeEnum.P2P || iMMessage.getSessionType() == SessionTypeEnum.Team) {
                    String fromAccount = iMMessage.getFromAccount();
                    String content = iMMessage.getContent();
                    iMMessage.getFromNick();
                    int i3 = iMMessage.getSessionType() == SessionTypeEnum.Team ? 2 : 0;
                    long time = iMMessage.getTime();
                    Log.i(dcaSdk.TAG, "account" + fromAccount);
                    NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                    if (userInfo == null) {
                        Log.i(dcaSdk.TAG, "查不到");
                        arrayList2.add(fromAccount);
                        break;
                    }
                    String extension = userInfo.getExtension();
                    try {
                        jSONObject2.put("content", content);
                        jSONObject2.put("type", i3);
                        jSONObject2.put("time", time);
                        jSONObject2.put("account", fromAccount);
                        jSONObject2.put(RecentSession.KEY_EXT, extension);
                        arrayList.add(i2, jSONObject2);
                        i2++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            if (arrayList2.size() == 0) {
                Log.i(dcaSdk.TAG, arrayList.toString());
                try {
                    jSONObject.put("data", arrayList);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                dcaSdk.this.dispatchMessageToProxy("incomingMessageObserver", jSONObject);
            }
            ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList2).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.7.1
                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onException(Throwable th) {
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onFailed(int i4) {
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onSuccess(List<NimUserInfo> list2) {
                    for (int i4 = 0; i4 < list2.size(); i4++) {
                        NimUserInfo nimUserInfo = list2.get(i4);
                        String account = nimUserInfo.getAccount();
                        String extension2 = nimUserInfo.getExtension();
                        Log.i(dcaSdk.TAG, "查到用户名" + extension2);
                        for (int i5 = 0; i5 < arrayList.size(); i5++) {
                            try {
                                if (account == ((JSONObject) arrayList.get(i5)).getString("account")) {
                                    ((JSONObject) arrayList.get(i5)).put(RecentSession.KEY_EXT, extension2);
                                }
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    try {
                        jSONObject.put("data", arrayList);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    dcaSdk.this.dispatchMessageToProxy("incomingMessageObserver", jSONObject);
                }
            });
        }
    };
    Observer<RevokeMsgNotification> revokeMessageObserver = new Observer<RevokeMsgNotification>() { // from class: www.kaiqigu.com.dcaSdk.8
        @Override // com.netease.nimlib.sdk.Observer
        public void onEvent(RevokeMsgNotification revokeMsgNotification) {
            Log.i(dcaSdk.TAG, "消息撤回");
            IMMessage message = revokeMsgNotification.getMessage();
            if (message.getSessionType() == SessionTypeEnum.ChatRoom) {
                Log.i(dcaSdk.TAG, "聊天室消息被撤回");
            } else if (message.getSessionType() == SessionTypeEnum.Team) {
                Log.i(dcaSdk.TAG, "工会消息被撤回");
            }
        }
    };
    private Observer<IMMessage> statusObserver = new Observer<IMMessage>() { // from class: www.kaiqigu.com.dcaSdk.10
        @Override // com.netease.nimlib.sdk.Observer
        public void onEvent(IMMessage iMMessage) {
            final int i = iMMessage.getSessionType() == SessionTypeEnum.Team ? 2 : 0;
            final String content = iMMessage.getContent();
            Log.i(dcaSdk.TAG, "收到消息" + content);
            final String fromAccount = iMMessage.getFromAccount();
            final long time = iMMessage.getTime();
            NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
            if (userInfo == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(fromAccount);
                ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.10.1
                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onException(Throwable th) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onFailed(int i2) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onSuccess(List<NimUserInfo> list) {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            String extension = list.get(i2).getExtension();
                            Log.i(dcaSdk.TAG, "查到用户名22" + extension);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("content", content);
                                jSONObject.put("account", fromAccount);
                                jSONObject.put("time", time);
                                jSONObject.put(RecentSession.KEY_EXT, extension);
                                jSONObject.put("type", i);
                                if (content.length() > 0) {
                                    dcaSdk.this.dispatchMessageToProxy("chatSendMsg", jSONObject);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                return;
            }
            Log.i(dcaSdk.TAG, fromAccount + "-发的消息");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("content", content);
                jSONObject.put("account", fromAccount);
                jSONObject.put("time", time);
                jSONObject.put(RecentSession.KEY_EXT, userInfo.getExtension());
                jSONObject.put("type", i);
                if (content.length() > 0) {
                    dcaSdk.this.dispatchMessageToProxy("chatSendMsg", jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void pay() {
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void exitGameByBackKey(Activity activity) {
    }

    public String getChannelId() {
        return "kvgames";
    }

    @Override // com.components.PlatformHelper
    public String getPlatFormName() {
        return "kvgames";
    }

    @Override // com.components.PlatformHelper
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.components.PlatformHelper
    public void onConfigurationChanged(Activity activity, Configuration configuration) {
    }

    @Override // com.components.PlatformHelper
    public void onDestroy(Activity activity) {
    }

    @Override // com.components.PlatformHelper
    public void onNewIntent(Activity activity, Intent intent) {
    }

    @Override // com.components.PlatformHelper
    public void onPause(Activity activity) {
    }

    @Override // com.components.PlatformHelper
    public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
    }

    @Override // com.components.PlatformHelper
    public void onRestart(Activity activity) {
    }

    @Override // com.components.PlatformHelper
    public void onResume(Activity activity) {
    }

    @Override // com.components.PlatformHelper
    public void onStart(Activity activity) {
    }

    @Override // com.components.PlatformHelper
    public void onStop(Activity activity) {
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void sendInfo(Activity activity, JSONObject jSONObject) {
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void init(Activity activity) {
        Log.i(TAG, "init");
        NIMClient.config(activity, null, null);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", true);
            jSONObject.put("noplatform", false);
            this.isInitSuccess = true;
            dispatchMessageToProxy("sdkInit", jSONObject);
        } catch (Exception unused) {
        }
    }

    private void sdkInitBacktoLua(Activity activity) {
        this.gAct = activity;
        Log.d(TAG, "kqgsdk 初始化开始");
        ConfigInfor configInfor = new ConfigInfor();
        configInfor.setAppId(this.openid);
        configInfor.setAppKey(11);
        configInfor.setPublicKey("");
        configInfor.setCtx(activity);
        KaiQiGuSdk.getInstance().setDebug(false, null);
        KaiQiGuSdk.getInstance().initCfg(configInfor);
        KaiQiGuSdk.getInstance().doInit(activity, new OnInitCallBackListener() { // from class: www.kaiqigu.com.dcaSdk.1
            @Override // com.kqg.main.callback.OnInitCallBackListener
            public void onInitBack(int i) {
                Log.e("OnInitCallBackListener", "code:" + i);
                if (i != 200) {
                    dcaSdk.this.gAct.finish();
                    dcaSdk.this.gAct.finishAffinity();
                    Process.killProcess(Process.myPid());
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("init", true);
                    jSONObject.put("isUseCustomLogin", true);
                    jSONObject.put("isShowCenter", true);
                    jSONObject.put("isShowQuitBtn", false);
                    jSONObject.put("logoutType", "0");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dcaSdk.this.dispatchMessageToProxy("sdkInit", jSONObject);
            }
        });
        NIMClient.initSDK();
        ((SdkLifecycleObserver) NIMClient.getService(SdkLifecycleObserver.class)).observeMainProcessInitCompleteResult(new Observer<Boolean>() { // from class: www.kaiqigu.com.dcaSdk.2
            @Override // com.netease.nimlib.sdk.Observer
            public void onEvent(Boolean bool) {
                if (bool == null || !bool.booleanValue()) {
                    return;
                }
                Log.i(dcaSdk.TAG, "收到消息");
            }
        }, true);
        ((AuthServiceObserver) NIMClient.getService(AuthServiceObserver.class)).observeOnlineStatus(new Observer<StatusCode>() { // from class: www.kaiqigu.com.dcaSdk.3
            @Override // com.netease.nimlib.sdk.Observer
            public void onEvent(StatusCode statusCode) {
                Log.i(dcaSdk.TAG, statusCode.getDesc());
                statusCode.wontAutoLogin();
            }
        }, true);
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void sdkInit(Activity activity, JSONObject jSONObject) {
        if (this.isInitSuccess.booleanValue()) {
            sdkInitBacktoLua(activity);
        }
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void logIn(Activity activity, JSONObject jSONObject) {
        Log.d(TAG, "kqgsdk 登录开始");
        KaiQiGuSdk.getInstance().doLogin(activity, new OnLoginCallBackListener() { // from class: www.kaiqigu.com.dcaSdk.4
            @Override // com.kqg.main.callback.OnLoginCallBackListener
            public void onLoginSuccess(User user) {
                dcaSdk.this.isLoginSuccess = true;
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(SpeechEvent.KEY_EVENT_SESSION_ID, user.getSession_id());
                        jSONObject2.put("token", user.getUid());
                        jSONObject2.put(a.p, dcaSdk.this.appkey);
                        jSONObject2.put("platform", "kvgames");
                        jSONObject2.put("result", true);
                        jSONObject2.put("needverify", true);
                        jSONObject2.put("userID", user.getUid());
                        jSONObject2.put("age", "30");
                        dcaSdk.this.dispatchMessageToProxy("logIn", jSONObject2);
                        dcaSdk.this.isLogined = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.kqg.main.callback.OnLoginCallBackListener
            public void onLoginCancel() {
                Log.d(dcaSdk.TAG, "kqgsdk 登录取消");
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", false);
                    jSONObject2.put("msg", "用户取消登录");
                    dcaSdk.this.dispatchMessageToProxy("logIn", jSONObject2);
                    dcaSdk.this.isLogined = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void addToBlackList(Activity activity, JSONObject jSONObject) {
        String str;
        try {
            str = jSONObject.getString("accid");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        ((FriendService) NIMClient.getService(FriendService.class)).addToBlackList(str).setCallback(new RequestCallback<Void>() { // from class: www.kaiqigu.com.dcaSdk.5
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(Void r4) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", true);
                    dcaSdk.this.dispatchMessageToProxy("addToBlackList", jSONObject2);
                    dcaSdk.this.isLogined = true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", false);
                    dcaSdk.this.dispatchMessageToProxy("addToBlackList", jSONObject2);
                    dcaSdk.this.isLogined = true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void removeFromBlackList(Activity activity, JSONObject jSONObject) {
        String str;
        try {
            str = jSONObject.getString("accid");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        ((FriendService) NIMClient.getService(FriendService.class)).removeFromBlackList(str).setCallback(new RequestCallback<Void>() { // from class: www.kaiqigu.com.dcaSdk.6
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(Void r4) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", true);
                    dcaSdk.this.dispatchMessageToProxy("removeFromBlackList", jSONObject2);
                    dcaSdk.this.isLogined = true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", false);
                    dcaSdk.this.dispatchMessageToProxy("removeFromBlackList", jSONObject2);
                    dcaSdk.this.isLogined = true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void isLoginChat(Activity activity, JSONObject jSONObject) {
        boolean z = NIMClient.getStatus() == StatusCode.LOGINED;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("result", z);
            dispatchMessageToProxy("isLoginChat", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void loginChat(Activity activity, JSONObject jSONObject) {
        String str;
        String str2;
        String str3 = "";
        try {
            str = jSONObject.getString("accid");
            try {
                str2 = jSONObject.getString("token");
            } catch (JSONException e) {
                e = e;
                str2 = "";
            }
        } catch (JSONException e2) {
            e = e2;
            str = "";
            str2 = str;
        }
        try {
            str3 = jSONObject.getString("roomid");
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            this.roomID = str3;
            this.accID = str;
            LoginInfo loginInfo = new LoginInfo(str, str2);
            ((AuthService) NIMClient.getService(AuthService.class)).login(loginInfo).setCallback(new RequestCallback<LoginInfo>() { // from class: www.kaiqigu.com.dcaSdk.9
                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onException(Throwable th) {
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onSuccess(LoginInfo loginInfo2) {
                    Log.i(dcaSdk.TAG, "聊天登录成功");
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("result", true);
                        dcaSdk.this.dispatchMessageToProxy("loginChat", jSONObject2);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    ((MsgServiceObserve) NIMClient.getService(MsgServiceObserve.class)).observeReceiveMessage(dcaSdk.this.incomingMessageObserver, true);
                    ((MsgServiceObserve) NIMClient.getService(MsgServiceObserve.class)).observeMsgStatus(dcaSdk.this.statusObserver, true);
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onFailed(int i) {
                    JSONObject jSONObject2 = new JSONObject();
                    Log.e(dcaSdk.TAG, "登录失败 " + i);
                    try {
                        jSONObject2.put("result", false);
                        dcaSdk.this.dispatchMessageToProxy("loginChat", jSONObject2);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                }
            });
            charRoomStatus();
            chatRoomObserver();
        }
        this.roomID = str3;
        this.accID = str;
        LoginInfo loginInfo2 = new LoginInfo(str, str2);
        ((AuthService) NIMClient.getService(AuthService.class)).login(loginInfo2).setCallback(new RequestCallback<LoginInfo>() { // from class: www.kaiqigu.com.dcaSdk.9
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(LoginInfo loginInfo22) {
                Log.i(dcaSdk.TAG, "聊天登录成功");
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", true);
                    dcaSdk.this.dispatchMessageToProxy("loginChat", jSONObject2);
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
                ((MsgServiceObserve) NIMClient.getService(MsgServiceObserve.class)).observeReceiveMessage(dcaSdk.this.incomingMessageObserver, true);
                ((MsgServiceObserve) NIMClient.getService(MsgServiceObserve.class)).observeMsgStatus(dcaSdk.this.statusObserver, true);
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                JSONObject jSONObject2 = new JSONObject();
                Log.e(dcaSdk.TAG, "登录失败 " + i);
                try {
                    jSONObject2.put("result", false);
                    dcaSdk.this.dispatchMessageToProxy("loginChat", jSONObject2);
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
        });
        charRoomStatus();
        chatRoomObserver();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendMsg(android.app.Activity r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            java.lang.String r7 = ""
            r0 = 0
            java.lang.String r1 = "account"
            java.lang.String r1 = r8.getString(r1)     // Catch: org.json.JSONException -> L24
            java.lang.String r2 = "type"
            int r2 = r8.getInt(r2)     // Catch: org.json.JSONException -> L21
            java.lang.String r3 = "text"
            java.lang.String r3 = r8.getString(r3)     // Catch: org.json.JSONException -> L1e
            java.lang.String r4 = "teamID"
            java.lang.String r7 = r8.getString(r4)     // Catch: org.json.JSONException -> L1c
            goto L2b
        L1c:
            r8 = move-exception
            goto L28
        L1e:
            r8 = move-exception
            r3 = r7
            goto L28
        L21:
            r8 = move-exception
            r3 = r7
            goto L27
        L24:
            r8 = move-exception
            r1 = r7
            r3 = r1
        L27:
            r2 = 0
        L28:
            r8.printStackTrace()
        L2b:
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r8 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.P2P
            r4 = 2
            java.lang.String r5 = "yuenan---"
            if (r2 != r4) goto L49
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r8 = com.netease.nimlib.sdk.msg.constant.SessionTypeEnum.Team
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "发送群组消息"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r5, r1)
            goto L5e
        L49:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "发送好友消息"
            r7.append(r2)
            r7.append(r3)
            java.lang.String r7 = r7.toString()
            android.util.Log.i(r5, r7)
            r7 = r1
        L5e:
            java.lang.Class<com.netease.nimlib.sdk.uinfo.UserService> r1 = com.netease.nimlib.sdk.uinfo.UserService.class
            java.lang.Object r1 = com.netease.nimlib.sdk.NIMClient.getService(r1)
            com.netease.nimlib.sdk.uinfo.UserService r1 = (com.netease.nimlib.sdk.uinfo.UserService) r1
            java.lang.String r2 = r6.accID
            r1.getUserInfo(r2)
            com.netease.nimlib.sdk.msg.model.IMMessage r7 = com.netease.nimlib.sdk.msg.MessageBuilder.createTextMessage(r7, r8, r3)
            java.lang.Class<com.netease.nimlib.sdk.msg.MsgService> r8 = com.netease.nimlib.sdk.msg.MsgService.class
            java.lang.Object r8 = com.netease.nimlib.sdk.NIMClient.getService(r8)
            com.netease.nimlib.sdk.msg.MsgService r8 = (com.netease.nimlib.sdk.msg.MsgService) r8
            com.netease.nimlib.sdk.InvocationFuture r7 = r8.sendMessage(r7, r0)
            www.kaiqigu.com.dcaSdk$11 r8 = new www.kaiqigu.com.dcaSdk$11
            r8.<init>()
            r7.setCallback(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: www.kaiqigu.com.dcaSdk.sendMsg(android.app.Activity, org.json.JSONObject):void");
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void queryMsg(Activity activity, JSONObject jSONObject) {
        String str;
        Log.i(TAG, "查询好友的聊天记录");
        try {
            str = jSONObject.getString("accid");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        MessageBuilder.createEmptyMessage(str, SessionTypeEnum.P2P, System.currentTimeMillis());
        long currentTimeMillis = System.currentTimeMillis();
        IMMessage createEmptyMessage = MessageBuilder.createEmptyMessage(str, SessionTypeEnum.P2P, currentTimeMillis);
        ((MsgService) NIMClient.getService(MsgService.class)).pullMessageHistoryEx(createEmptyMessage, currentTimeMillis - 1728000000, 50, QueryDirectionEnum.QUERY_OLD, false).setCallback(new RequestCallback<List<IMMessage>>() { // from class: www.kaiqigu.com.dcaSdk.12
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(List<IMMessage> list) {
                Log.i(dcaSdk.TAG, "群组消息" + list.size());
                final JSONObject jSONObject2 = new JSONObject();
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Log.i(dcaSdk.TAG, "haha" + list.size());
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    IMMessage iMMessage = list.get(i);
                    String fromAccount = iMMessage.getFromAccount();
                    String content = iMMessage.getContent();
                    iMMessage.getFromNick();
                    long time = iMMessage.getTime();
                    Log.i(dcaSdk.TAG, "account" + fromAccount);
                    NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                    if (userInfo == null) {
                        Log.i(dcaSdk.TAG, "查不到");
                        arrayList2.add(fromAccount);
                        break;
                    }
                    String extension = userInfo.getExtension();
                    Log.i(dcaSdk.TAG, "ext--" + extension);
                    Log.i(dcaSdk.TAG, "获取聊天室历史消息" + content);
                    try {
                        jSONObject3.put("content", content);
                        jSONObject3.put("time", time);
                        jSONObject3.put("account", fromAccount);
                        jSONObject3.put(RecentSession.KEY_EXT, extension);
                        arrayList.add(i2, jSONObject3);
                        i2++;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    i++;
                }
                if (arrayList2.size() == 0) {
                    try {
                        jSONObject2.put("data", arrayList);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                    dcaSdk.this.dispatchMessageToProxy("queryMsg", jSONObject2);
                }
                ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList2).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.12.1
                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onException(Throwable th) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onFailed(int i3) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onSuccess(List<NimUserInfo> list2) {
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            NimUserInfo nimUserInfo = list2.get(i3);
                            String account = nimUserInfo.getAccount();
                            String extension2 = nimUserInfo.getExtension();
                            Log.i(dcaSdk.TAG, "查到用户名" + extension2);
                            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                try {
                                    if (account == ((JSONObject) arrayList.get(i4)).getString("account")) {
                                        ((JSONObject) arrayList.get(i4)).put(RecentSession.KEY_EXT, extension2);
                                    }
                                } catch (JSONException e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                        try {
                            jSONObject2.put("data", arrayList);
                        } catch (JSONException e5) {
                            e5.printStackTrace();
                        }
                        dcaSdk.this.dispatchMessageToProxy("queryMsg", jSONObject2);
                    }
                });
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
                Log.i(dcaSdk.TAG, "查询失败" + i);
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void queryTeamMsg(Activity activity, JSONObject jSONObject) {
        int i;
        String str = "";
        try {
            str = jSONObject.getString("teamID");
            i = jSONObject.getInt("type");
        } catch (JSONException e) {
            e.printStackTrace();
            i = 0;
        }
        if (i == 2) {
            Log.i(TAG, "查询群组聊天记录 " + str);
            SessionTypeEnum sessionTypeEnum = SessionTypeEnum.Team;
        } else {
            Log.i(TAG, "查询好友聊天记录 " + str);
            SessionTypeEnum sessionTypeEnum2 = SessionTypeEnum.P2P;
        }
        ((TeamService) NIMClient.getService(TeamService.class)).queryTeamList().setCallback(new AnonymousClass13(str));
    }

    /* renamed from: www.kaiqigu.com.dcaSdk$13, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass13 implements RequestCallback<List<Team>> {
        final /* synthetic */ String val$finalTeamID;

        @Override // com.netease.nimlib.sdk.RequestCallback
        public void onException(Throwable th) {
        }

        AnonymousClass13(String str) {
            this.val$finalTeamID = str;
        }

        @Override // com.netease.nimlib.sdk.RequestCallback
        public void onSuccess(List<Team> list) {
            long currentTimeMillis = System.currentTimeMillis();
            IMMessage createEmptyMessage = MessageBuilder.createEmptyMessage(this.val$finalTeamID, SessionTypeEnum.Team, currentTimeMillis);
            ((MsgService) NIMClient.getService(MsgService.class)).pullMessageHistoryEx(createEmptyMessage, currentTimeMillis - 864000000, 50, QueryDirectionEnum.QUERY_OLD, false).setCallback(new RequestCallback<List<IMMessage>>() { // from class: www.kaiqigu.com.dcaSdk.13.1
                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onException(Throwable th) {
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onSuccess(List<IMMessage> list2) {
                    Log.i(dcaSdk.TAG, "群组消息" + list2.size());
                    final JSONObject jSONObject = new JSONObject();
                    final ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    Log.i(dcaSdk.TAG, "haha" + list2.size());
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i >= list2.size()) {
                            break;
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        IMMessage iMMessage = list2.get(i);
                        String fromAccount = iMMessage.getFromAccount();
                        String content = iMMessage.getContent();
                        iMMessage.getFromNick();
                        long time = iMMessage.getTime();
                        Log.i(dcaSdk.TAG, "account" + fromAccount);
                        NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                        if (userInfo == null) {
                            Log.i(dcaSdk.TAG, "查不到");
                            arrayList2.add(fromAccount);
                            break;
                        }
                        String extension = userInfo.getExtension();
                        Log.i(dcaSdk.TAG, "ext--" + extension);
                        Log.i(dcaSdk.TAG, "获取聊天室历史消息" + content);
                        try {
                            jSONObject2.put("content", content);
                            jSONObject2.put("time", time);
                            jSONObject2.put("account", fromAccount);
                            jSONObject2.put(RecentSession.KEY_EXT, extension);
                            arrayList.add(i2, jSONObject2);
                            i2++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    if (arrayList2.size() == 0) {
                        try {
                            jSONObject.put("data", arrayList);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        dcaSdk.this.dispatchMessageToProxy("queryTeamMsg", jSONObject);
                    }
                    ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList2).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.13.1.1
                        @Override // com.netease.nimlib.sdk.RequestCallback
                        public void onException(Throwable th) {
                        }

                        @Override // com.netease.nimlib.sdk.RequestCallback
                        public void onFailed(int i3) {
                        }

                        @Override // com.netease.nimlib.sdk.RequestCallback
                        public void onSuccess(List<NimUserInfo> list3) {
                            for (int i3 = 0; i3 < list3.size(); i3++) {
                                NimUserInfo nimUserInfo = list3.get(i3);
                                String account = nimUserInfo.getAccount();
                                String extension2 = nimUserInfo.getExtension();
                                Log.i(dcaSdk.TAG, "查到用户名" + extension2);
                                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                    try {
                                        if (account == ((JSONObject) arrayList.get(i4)).getString("account")) {
                                            ((JSONObject) arrayList.get(i4)).put(RecentSession.KEY_EXT, extension2);
                                        }
                                    } catch (JSONException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            }
                            try {
                                jSONObject.put("data", arrayList);
                            } catch (JSONException e4) {
                                e4.printStackTrace();
                            }
                            dcaSdk.this.dispatchMessageToProxy("queryTeamMsg", jSONObject);
                        }
                    });
                }

                @Override // com.netease.nimlib.sdk.RequestCallback
                public void onFailed(int i) {
                    Log.i(dcaSdk.TAG, "查询失败" + i);
                }
            });
        }

        /* renamed from: www.kaiqigu.com.dcaSdk$13$2, reason: invalid class name */
        /* loaded from: classes.dex */
        class AnonymousClass2 implements RequestCallback<Team> {
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
            }

            AnonymousClass2() {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(Team team) {
                long currentTimeMillis = System.currentTimeMillis();
                IMMessage createEmptyMessage = MessageBuilder.createEmptyMessage(AnonymousClass13.this.val$finalTeamID, SessionTypeEnum.Team, currentTimeMillis);
                ((MsgService) NIMClient.getService(MsgService.class)).pullMessageHistoryEx(createEmptyMessage, currentTimeMillis - 864000000, 50, QueryDirectionEnum.QUERY_OLD, false).setCallback(new RequestCallback<List<IMMessage>>() { // from class: www.kaiqigu.com.dcaSdk.13.2.1
                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onException(Throwable th) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onSuccess(List<IMMessage> list) {
                        Log.i(dcaSdk.TAG, "群组消息" + list.size());
                        final JSONObject jSONObject = new JSONObject();
                        final ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        Log.i(dcaSdk.TAG, "haha" + list.size());
                        int i = 0;
                        int i2 = 0;
                        while (true) {
                            if (i >= list.size()) {
                                break;
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            IMMessage iMMessage = list.get(i);
                            String fromAccount = iMMessage.getFromAccount();
                            String content = iMMessage.getContent();
                            iMMessage.getFromNick();
                            long time = iMMessage.getTime();
                            Log.i(dcaSdk.TAG, "account" + fromAccount);
                            NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                            if (userInfo == null) {
                                Log.i(dcaSdk.TAG, "查不到");
                                arrayList2.add(fromAccount);
                                break;
                            }
                            String extension = userInfo.getExtension();
                            Log.i(dcaSdk.TAG, "ext--" + extension);
                            Log.i(dcaSdk.TAG, "获取聊天室历史消息" + content);
                            try {
                                jSONObject2.put("content", content);
                                jSONObject2.put("time", time);
                                jSONObject2.put("account", fromAccount);
                                jSONObject2.put(RecentSession.KEY_EXT, extension);
                                arrayList.add(i2, jSONObject2);
                                i2++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i++;
                        }
                        if (arrayList2.size() == 0) {
                            try {
                                jSONObject.put("data", arrayList);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            dcaSdk.this.dispatchMessageToProxy("queryTeamMsg", jSONObject);
                        }
                        ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList2).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.13.2.1.1
                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onException(Throwable th) {
                            }

                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onFailed(int i3) {
                            }

                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onSuccess(List<NimUserInfo> list2) {
                                for (int i3 = 0; i3 < list2.size(); i3++) {
                                    NimUserInfo nimUserInfo = list2.get(i3);
                                    String account = nimUserInfo.getAccount();
                                    String extension2 = nimUserInfo.getExtension();
                                    Log.i(dcaSdk.TAG, "查到用户名" + extension2);
                                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                        try {
                                            if (account == ((JSONObject) arrayList.get(i4)).getString("account")) {
                                                ((JSONObject) arrayList.get(i4)).put(RecentSession.KEY_EXT, extension2);
                                            }
                                        } catch (JSONException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                }
                                try {
                                    jSONObject.put("data", arrayList);
                                } catch (JSONException e4) {
                                    e4.printStackTrace();
                                }
                                dcaSdk.this.dispatchMessageToProxy("queryTeamMsg", jSONObject);
                            }
                        });
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onFailed(int i) {
                        Log.i(dcaSdk.TAG, "查询失败" + i);
                    }
                });
            }
        }

        @Override // com.netease.nimlib.sdk.RequestCallback
        public void onFailed(int i) {
            ((TeamService) NIMClient.getService(TeamService.class)).applyJoinTeam(this.val$finalTeamID, null).setCallback(new AnonymousClass2());
        }
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void queryRoomMsg(Activity activity, JSONObject jSONObject) {
        String str = this.roomID;
        Log.i(TAG, "聊天室ID" + str);
        ((ChatRoomService) NIMClient.getService(ChatRoomService.class)).pullMessageHistoryEx(str, System.currentTimeMillis(), 100, QueryDirectionEnum.QUERY_OLD).setCallback(new RequestCallback<List<ChatRoomMessage>>() { // from class: www.kaiqigu.com.dcaSdk.14
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(List<ChatRoomMessage> list) {
                AnonymousClass14 anonymousClass14;
                Log.i(dcaSdk.TAG, "获取聊天室历史消息" + list.size());
                final JSONObject jSONObject2 = new JSONObject();
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    ChatRoomMessage chatRoomMessage = list.get(i);
                    String fromAccount = chatRoomMessage.getFromAccount();
                    String content = chatRoomMessage.getContent();
                    chatRoomMessage.getFromNick();
                    long time = chatRoomMessage.getTime();
                    Log.i(dcaSdk.TAG, "account" + fromAccount);
                    NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                    if (userInfo == null) {
                        Log.i(dcaSdk.TAG, "查不到");
                        arrayList2.add(fromAccount);
                        break;
                    }
                    String extension = userInfo.getExtension();
                    Log.i(dcaSdk.TAG, "ext--" + extension);
                    Log.i(dcaSdk.TAG, "获取聊天室历史消息" + content);
                    try {
                        jSONObject3.put("content", content);
                        jSONObject3.put("time", time);
                        jSONObject3.put("account", fromAccount);
                        jSONObject3.put(RecentSession.KEY_EXT, extension);
                        arrayList.add(i2, jSONObject3);
                        i2++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                if (arrayList2.size() == 0) {
                    try {
                        jSONObject2.put("data", arrayList);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    anonymousClass14 = this;
                    dcaSdk.this.dispatchMessageToProxy("queryRoomMsg", jSONObject2);
                } else {
                    anonymousClass14 = this;
                }
                ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList2).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.14.1
                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onException(Throwable th) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onFailed(int i3) {
                    }

                    @Override // com.netease.nimlib.sdk.RequestCallback
                    public void onSuccess(List<NimUserInfo> list2) {
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            NimUserInfo nimUserInfo = list2.get(i3);
                            String account = nimUserInfo.getAccount();
                            String extension2 = nimUserInfo.getExtension();
                            Log.i(dcaSdk.TAG, "查到用户名" + extension2);
                            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                try {
                                    if (account == ((JSONObject) arrayList.get(i4)).getString("account")) {
                                        ((JSONObject) arrayList.get(i4)).put(RecentSession.KEY_EXT, extension2);
                                    }
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        try {
                            jSONObject2.put("data", arrayList);
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                        }
                        dcaSdk.this.dispatchMessageToProxy("queryRoomMsg", jSONObject2);
                    }
                });
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void enterChatRoom(Activity activity, JSONObject jSONObject) {
        Log.i(TAG, "enterChatRoom");
        EnterChatRoomData enterChatRoomData = new EnterChatRoomData(this.roomID);
        enterChatRoomData.setLoginAuthType(0);
        enterChatRoomData.setLoginExt("设置登录自定义字段");
        ((ChatRoomService) NIMClient.getService(ChatRoomService.class)).enterChatRoomEx(enterChatRoomData, 1);
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void exitCharRoom(Activity activity, JSONObject jSONObject) {
        Log.i(TAG, "exitCharRoom");
        ((ChatRoomService) NIMClient.getService(ChatRoomService.class)).exitChatRoom(this.roomID);
    }

    public void charRoomStatus() {
        final String str = this.roomID;
        ((ChatRoomServiceObserver) NIMClient.getService(ChatRoomServiceObserver.class)).observeOnlineStatus(new Observer<ChatRoomStatusChangeData>() { // from class: www.kaiqigu.com.dcaSdk.15
            @Override // com.netease.nimlib.sdk.Observer
            public void onEvent(ChatRoomStatusChangeData chatRoomStatusChangeData) {
                if (chatRoomStatusChangeData.roomId.equals(str)) {
                    if (chatRoomStatusChangeData.status == StatusCode.CONNECTING) {
                        Log.i(dcaSdk.TAG, "聊天室 连接中...");
                        return;
                    }
                    if (chatRoomStatusChangeData.status == StatusCode.LOGINING) {
                        Log.i(dcaSdk.TAG, "聊天室 登录中...");
                        return;
                    }
                    if (chatRoomStatusChangeData.status == StatusCode.LOGINED) {
                        Log.i(dcaSdk.TAG, "聊天室 已登陆");
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("result", true);
                            dcaSdk.this.dispatchMessageToProxy("enterChatRoom", jSONObject);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (chatRoomStatusChangeData.status == StatusCode.UNLOGIN) {
                        Log.i(dcaSdk.TAG, "聊天室 登出的状态");
                    } else if (chatRoomStatusChangeData.status == StatusCode.NET_BROKEN) {
                        Log.i(dcaSdk.TAG, "聊天室 当前网络不可用");
                    } else {
                        Log.i(dcaSdk.TAG, "聊天室 错误");
                    }
                }
            }
        }, true);
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void sendRoomMsg(Activity activity, JSONObject jSONObject) {
        final String str;
        try {
            str = jSONObject.getString(SpeechConstant.TEXT);
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        Log.i(TAG, "sendRoomMsg  " + str);
        ChatRoomMessage createChatRoomTextMessage = ChatRoomMessageBuilder.createChatRoomTextMessage(this.roomID, str);
        final String str2 = this.accID;
        final NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(this.accID);
        ((ChatRoomService) NIMClient.getService(ChatRoomService.class)).sendMessage(createChatRoomTextMessage, false).setCallback(new RequestCallback<Void>() { // from class: www.kaiqigu.com.dcaSdk.16
            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onException(Throwable th) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onFailed(int i) {
            }

            @Override // com.netease.nimlib.sdk.RequestCallback
            public void onSuccess(Void r5) {
                Log.i(dcaSdk.TAG, "聊天室消息发送成功");
                JSONObject jSONObject2 = new JSONObject();
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    jSONObject2.put("content", str);
                    jSONObject2.put(c.e, "我");
                    jSONObject2.put(RecentSession.KEY_EXT, userInfo.getExtension());
                    jSONObject2.put("account", str2);
                    jSONObject2.put("time", currentTimeMillis);
                    dcaSdk.this.dispatchMessageToProxy("chatRoomObserver", jSONObject2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public void chatRoomObserver() {
        ((ChatRoomServiceObserver) NIMClient.getService(ChatRoomServiceObserver.class)).observeReceiveMessage(new Observer<List<ChatRoomMessage>>() { // from class: www.kaiqigu.com.dcaSdk.17
            @Override // com.netease.nimlib.sdk.Observer
            public void onEvent(List<ChatRoomMessage> list) {
                for (int i = 0; i < list.size(); i++) {
                    ChatRoomMessage chatRoomMessage = list.get(i);
                    if (chatRoomMessage.getMsgType() == MsgTypeEnum.notification) {
                        Log.i(dcaSdk.TAG, "系统消息需要刷新聊天室");
                        NotificationType type = ((NotificationAttachment) chatRoomMessage.getAttachment()).getType();
                        if (type == NotificationType.ChatRoomMemberExit || type == NotificationType.ChatRoomMemberIn) {
                            return;
                        }
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put(TransferService.INTENT_KEY_NOTIFICATION, true);
                            dcaSdk.this.dispatchMessageToProxy("chatRoomObserver", jSONObject);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    final String content = chatRoomMessage.getContent();
                    Log.i(dcaSdk.TAG, "聊天室消息:" + content);
                    final String fromAccount = chatRoomMessage.getFromAccount();
                    final long time = chatRoomMessage.getTime();
                    NimUserInfo userInfo = ((UserService) NIMClient.getService(UserService.class)).getUserInfo(fromAccount);
                    if (userInfo == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(fromAccount);
                        ((UserService) NIMClient.getService(UserService.class)).fetchUserInfo(arrayList).setCallback(new RequestCallback<List<NimUserInfo>>() { // from class: www.kaiqigu.com.dcaSdk.17.1
                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onException(Throwable th) {
                            }

                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onFailed(int i2) {
                            }

                            @Override // com.netease.nimlib.sdk.RequestCallback
                            public void onSuccess(List<NimUserInfo> list2) {
                                for (int i2 = 0; i2 < list2.size(); i2++) {
                                    String extension = list2.get(i2).getExtension();
                                    Log.i(dcaSdk.TAG, "查到用户名22" + extension);
                                    JSONObject jSONObject2 = new JSONObject();
                                    try {
                                        jSONObject2.put(TransferService.INTENT_KEY_NOTIFICATION, false);
                                        jSONObject2.put("content", content);
                                        jSONObject2.put(c.e, fromAccount);
                                        jSONObject2.put("time", time);
                                        jSONObject2.put(RecentSession.KEY_EXT, extension);
                                        dcaSdk.this.dispatchMessageToProxy("chatRoomObserver", jSONObject2);
                                    } catch (JSONException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        });
                    } else {
                        Log.i(dcaSdk.TAG, fromAccount + "-发的消息");
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put(TransferService.INTENT_KEY_NOTIFICATION, false);
                            jSONObject2.put("content", content);
                            jSONObject2.put(c.e, fromAccount);
                            jSONObject2.put("time", time);
                            jSONObject2.put(RecentSession.KEY_EXT, userInfo.getExtension());
                            dcaSdk.this.dispatchMessageToProxy("chatRoomObserver", jSONObject2);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }, true);
    }

    public void queryTeamMemberBlock() {
        ((TeamService) NIMClient.getService(TeamService.class)).queryTeamMember("teamId", "vn_test_vn28729077").setCallback(new RequestCallbackWrapper<TeamMember>() { // from class: www.kaiqigu.com.dcaSdk.18
            @Override // com.netease.nimlib.sdk.RequestCallbackWrapper
            public void onResult(int i, TeamMember teamMember, Throwable th) {
            }
        });
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void isLogined(Activity activity, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("isLogined", this.isLoginSuccess);
            dispatchMessageToProxy("isLogined", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int getServerId(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return Integer.parseInt(stringBuffer.toString());
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void buyProduct(Activity activity, JSONObject jSONObject) {
        String str;
        Log.e(TAG, "kqgsdk 调用支付");
        try {
            str = jSONObject.getString("upayurl");
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.gAct.startActivity(intent);
    }

    private void handleBuyResult(int i, String str) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i);
            jSONObject.put("errmsg", str);
            Handler handler = null;
            handler.postDelayed(new Runnable() { // from class: www.kaiqigu.com.dcaSdk.19
                @Override // java.lang.Runnable
                public void run() {
                    dcaSdk.this.dispatchMessageToProxy("buyProduct", jSONObject);
                }
            }, 1000L);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.components.PlatformHelper, com.components.PlatformInterface
    public void logOut(Activity activity, JSONObject jSONObject) {
        ((AuthService) NIMClient.getService(AuthService.class)).logout();
    }
}
