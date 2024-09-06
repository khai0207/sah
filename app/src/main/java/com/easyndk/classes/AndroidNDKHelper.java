package com.easyndk.classes;

import android.os.Handler;
import android.os.Message;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AndroidNDKHelper {
    private static Handler NDKHelperHandler = null;
    private static final int __MSG_FROM_CPP__ = 5;
    private static Object callHandler;

    private static native void CPPNativeCallHandler(String str, String str2);

    public static void SetNDKReciever(Object obj) {
        callHandler = obj;
        NDKHelperHandler = new Handler() { // from class: com.easyndk.classes.AndroidNDKHelper.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 5) {
                    return;
                }
                try {
                    NDKMessage nDKMessage = (NDKMessage) message.obj;
                    nDKMessage.methodToCall.invoke(AndroidNDKHelper.callHandler, nDKMessage.methodParams);
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

    public static void SendMessageWithParameters(String str, JSONObject jSONObject) {
        try {
            CPPNativeCallHandler(str, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RecieveCppMessage(String str, String str2) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        try {
            Method method = callHandler.getClass().getMethod(str, JSONObject.class);
            NDKMessage nDKMessage = new NDKMessage();
            nDKMessage.methodToCall = method;
            nDKMessage.methodParams = jSONObject;
            Message message = new Message();
            message.what = 5;
            message.obj = nDKMessage;
            NDKHelperHandler.sendMessage(message);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        }
    }
}
