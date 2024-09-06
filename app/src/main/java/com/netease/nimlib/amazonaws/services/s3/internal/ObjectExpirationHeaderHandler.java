package com.netease.nimlib.amazonaws.services.s3.internal;

import com.netease.nimlib.amazonaws.http.HttpResponse;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import com.netease.nimlib.amazonaws.services.s3.internal.ObjectExpirationResult;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ObjectExpirationHeaderHandler<T extends ObjectExpirationResult> implements HeaderHandler<T> {
    private static final Pattern DATE_PATTERN = Pattern.compile("expiry-date=\"(.*?)\"");
    private static final Pattern RULE_PATTERN = Pattern.compile("rule-id=\"(.*?)\"");
    private static final Log log = LogFactory.getLog((Class<?>) ObjectExpirationHeaderHandler.class);

    @Override // com.netease.nimlib.amazonaws.services.s3.internal.HeaderHandler
    public void handle(T t, HttpResponse httpResponse) {
        String str = httpResponse.getHeaders().get(Headers.EXPIRATION);
        if (str != null) {
            t.setExpirationTime(parseDate(str));
            t.setExpirationTimeRuleId(parseRuleId(str));
        }
    }

    private String parseRuleId(String str) {
        Matcher matcher = RULE_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private Date parseDate(String str) {
        Matcher matcher = DATE_PATTERN.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        try {
            return ServiceUtils.parseRfc822Date(matcher.group(1));
        } catch (Exception e) {
            log.warn("Error parsing expiry-date from x-amz-expiration header.", e);
            return null;
        }
    }
}
