package com.android.pc.ioc.internet;

import android.util.Xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class XMLtoJsonUtil {
    public static String XMLtoJson(String str, String str2, String str3) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.trim().getBytes());
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(byteArrayInputStream, str3);
            boolean z = false;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    if (z) {
                        return newPullParser.nextText();
                    }
                    if ((str2 + "Response").equals(newPullParser.getName())) {
                        z = true;
                    }
                }
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
