package com.kaiqigu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NetUtil {
    private static final String TAG = "NetUtil";

    public static boolean isConnnected(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    Log.e(TAG, "the net is ok");
                    return true;
                }
            }
        }
        Toast.makeText(context, "��������ʧ��", 0).show();
        return false;
    }

    public static JSONObject getResponseForGet(String str, Context context) {
        if (isConnnected(context)) {
            return getResponseForGet(str);
        }
        return null;
    }

    public static JSONObject getResponseForGet(String str) {
        return getRespose(new HttpGet(str));
    }

    public static JSONObject getResponseForPost(String str, List<NameValuePair> list, Context context) {
        if (isConnnected(context)) {
            return getResponseForPost(str, list);
        }
        return null;
    }

    public static JSONObject getResponseForPost(String str, List<NameValuePair> list) {
        if (str != null && "" != str) {
            HttpPost httpPost = new HttpPost(str);
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                return getRespose(httpPost);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject getRespose(HttpUriRequest httpUriRequest) {
        try {
            HttpResponse execute = new DefaultHttpClient().execute(httpUriRequest);
            if (200 != execute.getStatusLine().getStatusCode()) {
                return null;
            }
            String entityUtils = EntityUtils.toString(execute.getEntity());
            Log.i(TAG, "results=" + entityUtils);
            return new JSONObject(entityUtils);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
