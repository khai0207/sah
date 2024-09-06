package com.easyext.classes;

import android.os.Handler;
import android.os.Message;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AndroidEXTHelper {
    private static Handler EXTHelperHandler = null;
    private static final int __MSG_FROM_CPP__ = 5;
    private static Object callHandler;

    private static native void CPPNativeCallHandlerEXT(String str, String str2);

    public static void SetEXTRecieverEXT(Object obj) {
        callHandler = obj;
        EXTHelperHandler = new Handler() { // from class: com.easyext.classes.AndroidEXTHelper.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 5) {
                    return;
                }
                try {
                    EXTMessage eXTMessage = (EXTMessage) message.obj;
                    eXTMessage.methodToCall.invoke(AndroidEXTHelper.callHandler, eXTMessage.methodParams);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
        };
    }

    public static void SendMessageWithParametersEXT(String str, JSONObject jSONObject) {
        try {
            CPPNativeCallHandlerEXT(str, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RecieveCppMessageEXT(String str, String str2) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        try {
            Method method = callHandler.getClass().getMethod(str, JSONObject.class);
            EXTMessage eXTMessage = new EXTMessage();
            eXTMessage.methodToCall = method;
            eXTMessage.methodParams = jSONObject;
            Message message = new Message();
            message.what = 5;
            message.obj = eXTMessage;
            EXTHelperHandler.sendMessage(message);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        }
    }
}
