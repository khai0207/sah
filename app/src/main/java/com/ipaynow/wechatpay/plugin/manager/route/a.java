package com.ipaynow.wechatpay.plugin.manager.route;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ipaynow.wechatpay.plugin.manager.route.dto.ResponseParams;
import com.unionpay.tsmservice.data.Constant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class a {
    private a() {
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static a I() {
        a aVar;
        aVar = b.bg;
        return aVar;
    }

    public static void a(Activity activity, String str, String str2) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        r.a(false);
        if (r.A() != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("respCode", com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_FAIL.c());
            bundle.putString(Constant.KEY_ERROR_CODE, str);
            bundle.putString("respMsg", str2);
            intent.putExtras(bundle);
            if (activity != null) {
                activity.setResult(1, intent);
            } else {
                a(intent);
            }
        }
        if (r.z() != null) {
            ResponseParams responseParams = new ResponseParams();
            responseParams.respCode = com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_FAIL.c();
            responseParams.errorCode = str;
            responseParams.respMsg = str2;
            r.z().onIpaynowTransResult(responseParams);
        }
        if (activity != null) {
            activity.finish();
        }
    }

    private static void a(Intent intent) {
        try {
            Method declaredMethod = com.ipaynow.wechatpay.plugin.manager.a.a.r().A().getClass().getDeclaredMethod("onActivityResult", Integer.TYPE, Integer.TYPE, Intent.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(com.ipaynow.wechatpay.plugin.manager.a.a.r().A(), 0, 1, intent);
        } catch (IllegalAccessException unused) {
            com.ipaynow.wechatpay.plugin.e.b.d("通知接口:非法参数");
        } catch (IllegalArgumentException unused2) {
            com.ipaynow.wechatpay.plugin.e.b.d("通知接口:非法参数");
        } catch (NoSuchMethodException unused3) {
            Log.e("ipaynow", "未实现通知接口");
        } catch (InvocationTargetException unused4) {
            com.ipaynow.wechatpay.plugin.e.b.d("通知接口:非法参数");
        }
    }

    public static void b(Activity activity, String str, String str2) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        r.a(false);
        if (r.A() != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("respCode", com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_UNKNOWN.c());
            bundle.putString(Constant.KEY_ERROR_CODE, str);
            bundle.putString("respMsg", str2);
            intent.putExtras(bundle);
            if (activity != null) {
                activity.setResult(1, intent);
            } else {
                a(intent);
            }
        }
        if (r.z() != null) {
            ResponseParams responseParams = new ResponseParams();
            responseParams.respCode = com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_UNKNOWN.c();
            responseParams.errorCode = str;
            responseParams.respMsg = str2;
            r.z().onIpaynowTransResult(responseParams);
        }
        if (activity != null) {
            activity.finish();
        }
    }

    public static void c(Activity activity) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        r.a(false);
        if (r.A() != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("respCode", com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_CANCEL.c());
            bundle.putString("respMsg", Constant.CASH_LOAD_CANCEL);
            intent.putExtras(bundle);
            if (activity != null) {
                activity.setResult(1, intent);
            } else {
                a(intent);
            }
        }
        if (r.z() != null) {
            ResponseParams responseParams = new ResponseParams();
            responseParams.respCode = com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_CANCEL.c();
            responseParams.respMsg = Constant.CASH_LOAD_CANCEL;
            r.z().onIpaynowTransResult(responseParams);
        }
        if (activity != null) {
            activity.finish();
        }
    }

    public static void d(Activity activity) {
        com.ipaynow.wechatpay.plugin.manager.a.a r = com.ipaynow.wechatpay.plugin.manager.a.a.r();
        r.a(false);
        if (r.A() != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("respCode", com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_SUCCESS.c());
            intent.putExtras(bundle);
            if (activity != null) {
                activity.setResult(1, intent);
            } else {
                a(intent);
            }
        }
        if (r.z() != null) {
            ResponseParams responseParams = new ResponseParams();
            responseParams.respCode = com.ipaynow.wechatpay.plugin.c.a.CALL_MHT_SUCCESS.c();
            responseParams.respMsg = Constant.CASH_LOAD_SUCCESS;
            r.z().onIpaynowTransResult(responseParams);
        }
        if (activity != null) {
            activity.finish();
        }
    }
}
