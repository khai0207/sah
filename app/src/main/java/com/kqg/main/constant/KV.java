package com.kqg.main.constant;

import com.kqg.main.base.KaiQiGuSdk;

/* loaded from: classes.dex */
public interface KV {
    public static final int CLOSE_OTHER_ACTIVIES = 20000;
    public static final String CREATE_PAY_ORDER_ALI;
    public static final String CREATE_PAY_ORDER_UP;
    public static final String DB_NAME = "kqg_db";
    public static final int ERROR_UNKNOWN = 5002;
    public static final int EVENT_BIND = 10001;
    public static final int EVENT_BIND_ACCOUNT_BY_EMAIL = 1034;
    public static final int EVENT_BIND_ACCOUNT_BY_PHONE = 4009;
    public static final int EVENT_BIND_ACCOUNT_GET_CODE = 4008;
    public static final int EVENT_BIND_EMAIL_GET_CODE1 = 1031;
    public static final int EVENT_BIND_EMAIL_GET_CODE2 = 1032;
    public static final int EVENT_BIND_PHONE = 1014;
    public static final int EVENT_BIND_PHONE_GET_CODE1 = 1013;
    public static final int EVENT_BIND_PHONE_GET_CODE2 = 1015;
    public static final int EVENT_CANCEL_QUIT = 4011;
    public static final int EVENT_CERTIFICATION = 1005;
    public static final int EVENT_CHANGE_PHONE = 4010;
    public static final int EVENT_CHANGE_PHONE_CHECK_CODE = 1016;
    public static final int EVENT_CHECK_CODE = 1043;
    public static final int EVENT_CHECK_CODE1 = 1044;
    public static final int EVENT_CHECK_CODE2 = 1045;
    public static final int EVENT_CHECK_CODE3 = 1046;
    public static final int EVENT_CHECK_CODE4 = 1047;
    public static final int EVENT_COMMON_WARN = 9024;
    public static final int EVENT_FINDE_PWD_BY_EMAIL = 4003;
    public static final int EVENT_FIND_PASSWORD_BY_EMAIL = 1037;
    public static final int EVENT_FIND_PASSWORD_BY_PHONE = 4005;
    public static final int EVENT_FIND_PSD_GET_CODE = 4004;
    public static final int EVENT_FIND_PSD_GET_CODE_EMAIL = 1036;
    public static final int EVENT_FIND_PWD_BY_HELPER = 4002;
    public static final int EVENT_FIND_PWD_BY_PHONE = 4001;
    public static final int EVENT_FORCE_BIND_PHONE = 1040;
    public static final int EVENT_GET_GUEST_ACCOUNT = 8001;
    public static final int EVENT_GET_PHONE_CODE_TO_LOGIN = 1039;
    public static final int EVENT_GO_LOGIN = 1008;
    public static final int EVENT_HANDLER_TO_BIND_ACCOUNT = 4007;
    public static final int EVENT_HANDLER_TO_FIND_PSD = 4006;
    public static final int EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_BIND = 1035;
    public static final int EVENT_HANDLE_MESSAGE_TO_BIND_EMAIL_GET_CODE = 1033;
    public static final int EVENT_HANDLE_MESSAGE_TO_BIND_PHONE_BIND = 1024;
    public static final int EVENT_HANDLE_MESSAGE_TO_BIND_PHONE_GET_CODE = 1044;
    public static final int EVENT_HANDLE_MESSAGE_TO_FIND_PSD_GET_CODE = 1020;
    public static final int EVENT_HANDLE_MESSAGE_TO_FIND_PSD_GET_CODE_NO_TOAST = 1025;
    public static final int EVENT_HANDLE_MESSAGE_TO_FIND_PSD_MODIFY = 1021;
    public static final int EVENT_LOGIN = 1001;
    public static final int EVENT_LOGIN_WITH_PASSWORD = 1041;
    public static final int EVENT_LOGIN_WITH_PHONE = 1042;
    public static final int EVENT_OPEN_ACCOUNT_MANAGER_ACTIVITY = 1038;
    public static final int EVENT_OPEN_ARREEMENT = 1049;
    public static final int EVENT_PAY_ALI = 18001;
    public static final int EVENT_PAY_ALI_RESULT = 36001;
    public static final int EVENT_PAY_RESULT = 16000;
    public static final int EVENT_PAY_UP = 18002;
    public static final int EVENT_PAY_UP_RESULT = 36002;
    public static final int EVENT_PAY_WEIXIN = 18000;
    public static final int EVENT_PAY_WEIXIN_RESULT = 36000;
    public static final int EVENT_QUICK_REGIST = 1023;
    public static final int EVENT_QUIT_GAME = 4012;
    public static final int EVENT_REGIST = 1002;
    public static final int EVENT_REQUEST_LOGIN = 1012;
    public static final int EVENT_SHOW_AGREE_CONTENT = 6000;
    public static final int EVENT_SHOW_BIND_ACCOUNT = 10000;
    public static final int EVENT_SHOW_CERTIFICATION = 1004;
    public static final int EVENT_SHOW_ENTER_PASSWORD = 1011;
    public static final int EVENT_SHOW_FORGET_PWD = 4000;
    public static final int EVENT_SHOW_QUICK_LOGIN = 1003;
    public static final int EVENT_SHOW_REGIST = 1022;
    public static final int EVENT_SHOW_SELECT_PAY = 15001;
    public static final int EVENT_UPDATE_LOGIN_ACTIVITY = 1048;
    public static final String FORMAT_TIME = "yyyy年MM月dd日 HH:mm:ss";
    public static final String GETORDERMESSAGE_URL;
    public static final long GET_CODE_INTERVAL = 60000;
    public static final String GET_MOBILE_CODE;
    public static final String HELPER_NUM = "01057256802";
    public static final int HTTP_OK = 0;
    public static final int INIT_ERROR = 2001;
    public static final int INIT_OVER = 200;
    public static final String LOGIN_BUNDLE_UID = "uids";
    public static final int LOGIN_ERROR = 3001;
    public static final int LOGIN_OK = 300;
    public static final int MAX_PASSWORD = 10;
    public static final int MAX_USERNAME = 30;
    public static final int MIN_USERNAME = 6;
    public static final int PAY_CANCEL = 16001;
    public static final int PAY_FAIL = 16400;
    public static final int PAY_INFOR = 15000;
    public static final int PAY_SUCCESS = 16200;
    public static final int PAY_TYPE_ALI = 1;
    public static final int PAY_TYPE_KUAI = 3;
    public static final int PAY_TYPE_UP = 2;
    public static final int PAY_TYPE_WX = 0;
    public static final int PAY_UNKNOW = 16500;
    public static final int PLUGIN_NEED_UPGRADE = 2;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_VALID = 0;
    public static final String PRICE_EXT = "元";
    public static final String SEPARATE = "--";
    public static final int SERVER_ERROR = 1;
    public static final int SERVER_OK = 0;
    public static final String USER_SERVICE_CONTENT_URI = "file:///android_asset/user_service_tip.html";
    public static final String WEIXIN_Prepayment_URL;
    public static final String MAIN_URI = KaiQiGuSdk.MAIN_URI;
    public static final String CHANNEL_ENTER = MAIN_URI + "channel_enter/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_REGISTER = MAIN_URI + "register_authenticate/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_LOGIN = MAIN_URI + "v2/login_account/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_LOGIN_WITH_PHONE_CODE = MAIN_URI + "v2/login_mobile/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_QUICK_REGISTER = MAIN_URI + "account_quick_register/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String QUICK_REGISTER_BIND = MAIN_URI + "quick_register_bind/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_SEND_EMAIL = MAIN_URI + "account_send_email/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_EMAIL_CODE = MAIN_URI + "account_email_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_AUTH_CODE = MAIN_URI + "account_auth_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_SEND_SMS = MAIN_URI + "pwd_phone_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_SEND_SMS_EMAIL = MAIN_URI + "pwd_email_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_PASSWORD_CHANGE = MAIN_URI + "account_password_change/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String PWD_PHONE_CHECK = MAIN_URI + "pwd_phone_check/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_BIND_PHONE = MAIN_URI + "bind_phone_check/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_BIND_PHONE_GET_CODE = MAIN_URI + "account_send_sms/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_CHANGE_PHONE = MAIN_URI + "unbind_phone_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String BIND_PHONE_CODE = MAIN_URI + "bind_phone_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String BIND_PHONE_CHECK = MAIN_URI + "unbind_phone_check/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String SEND_TEMPLATE_MESSAGE = MAIN_URI + "send_template_message/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String GET_RANDOM_AUTH_CODE = MAIN_URI + "get_random_auth_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String SEND_EMAIL = MAIN_URI + "send_email/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String ACCOUNT_CERTIFICATION = KaiQiGuSdk.MAIN_URI + "v2/real_name_auth/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String FORCE_BIND_PHONE = KaiQiGuSdk.MAIN_URI + "v2/bind_mobile/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CHANGE_PHONE_CHECK_CODE = KaiQiGuSdk.MAIN_URI + "v2/check_mobile/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CHANGE_PWD_CHECK_CODE = KaiQiGuSdk.MAIN_URI + "v2/check_mobile/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CHANGE_NEW_PHONE_CHECK_CODE = KaiQiGuSdk.MAIN_URI + "v2/change_mobile/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CHANGE_NEW_PASSWORD_CHECK_CODE = KaiQiGuSdk.MAIN_URI + "v2/change_password/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CHECK_CERTIFICATION = KaiQiGuSdk.MAIN_URI + "/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    public static final String CREATE_PAY_ORDER_WX = MAIN_URI + "v2/create_order_wechat/?" + KaiQiGuSdk.getInstance().addDeviceInfor();

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(MAIN_URI);
        sb.append("v2/wechat_unified_order_cjyxkv/?");
        WEIXIN_Prepayment_URL = sb.toString();
        CREATE_PAY_ORDER_UP = MAIN_URI + "pay_unionpay_order/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
        CREATE_PAY_ORDER_ALI = MAIN_URI + "v2/create_order_alipay/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
        GETORDERMESSAGE_URL = MAIN_URI + "v2/generate_sign_wechat/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
        GET_MOBILE_CODE = MAIN_URI + "v2/get_mobile_code/?" + KaiQiGuSdk.getInstance().addDeviceInfor();
    }
}
