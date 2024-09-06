package com.kqg.main.utils;

import android.widget.TextView;
import com.android.pc.ioc.verification.Rule;
import com.android.pc.ioc.verification.Rules;
import com.android.pc.ioc.verification.Validator;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ValidatorUtils {
    public static final String REGEX_MOBILE = "^((1[3-9][0-9]))\\d{8}$";
    private static final String REGEX_PASSWORD = "^[0-9a-zA-Z]*$";
    private static final String REGEX_PHONE = "^[0-9]*$";
    private static final String REGEX_USERNAME = "^[0-9_a-zA-Z]*";

    private static void validator(TextView textView, String str, String str2, int i, int i2, Validator.ValidationListener validationListener) {
        String trim = textView.getText().toString().trim();
        Rule<TextView> regex = Rules.regex(str, str2, true);
        if ((trim.length() < i || trim.length() > i2) && validationListener != null) {
            validationListener.onValidationFailed(textView, regex);
            return;
        }
        Validator validator = new Validator(validationListener);
        validator.put(textView, regex);
        validator.setValidationListener(validationListener);
        validator.validate();
    }

    public static void validatorUserName(TextView textView, String str, int i, int i2, Validator.ValidationListener validationListener) {
        validator(textView, str, REGEX_USERNAME, i, i2, validationListener);
    }

    public static void validatorUserPassword(TextView textView, String str, int i, int i2, Validator.ValidationListener validationListener) {
        validator(textView, str, REGEX_PASSWORD, i, i2, validationListener);
    }

    public static void validatorPhone(TextView textView, String str, int i, int i2, Validator.ValidationListener validationListener) {
        validator(textView, str, REGEX_PHONE, i, i2, validationListener);
    }

    public static void validatorPhoneCode(TextView textView, String str, int i, int i2, Validator.ValidationListener validationListener) {
        validator(textView, str, REGEX_PHONE, i, i2, validationListener);
    }

    public static boolean validatorEmptyString(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean validatorPhone(String str) {
        Pattern.compile(REGEX_MOBILE).matcher(str);
        return true;
    }

    public static boolean checkIsMd5Pwd(String str) {
        return str.length() == 32;
    }
}
