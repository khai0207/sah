package com.android.pc.ioc.verification;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import com.android.pc.ioc.verification.annotation.Checked;
import com.android.pc.ioc.verification.annotation.ConfirmPassword;
import com.android.pc.ioc.verification.annotation.Email;
import com.android.pc.ioc.verification.annotation.IpAddress;
import com.android.pc.ioc.verification.annotation.NumberRule;
import com.android.pc.ioc.verification.annotation.Password;
import com.android.pc.ioc.verification.annotation.Regex;
import com.android.pc.ioc.verification.annotation.Required;
import com.android.pc.ioc.verification.annotation.TextRule;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: classes.dex */
class AnnotationToRuleConverter {
    static final String TAG = AnnotationToRuleConverter.class.getSimpleName();
    static final String WARN_CHECKABLE = "%s - @%s can only be applied to Checkable, its implementations and subclasses.";
    static final String WARN_TEXT = "%s - @%s can only be applied to TextView and its subclasses.";

    AnnotationToRuleConverter() {
    }

    public static Rule<?> getRule(Field field, View view, Annotation annotation) {
        Class<?> cls = annotation.getClass();
        if (Required.class.isAssignableFrom(cls)) {
            return getRequiredRule(field, view, (Required) annotation);
        }
        if (Checked.class.isAssignableFrom(cls)) {
            return getCheckedRule(field, view, (Checked) annotation);
        }
        if (TextRule.class.isAssignableFrom(cls)) {
            return getTextRule(field, view, (TextRule) annotation);
        }
        if (Regex.class.isAssignableFrom(cls)) {
            return getRegexRule(field, view, (Regex) annotation);
        }
        if (NumberRule.class.isAssignableFrom(cls)) {
            return getNumberRule(field, view, (NumberRule) annotation);
        }
        if (Password.class.isAssignableFrom(cls)) {
            return getPasswordRule(field, view, (Password) annotation);
        }
        if (Email.class.isAssignableFrom(cls)) {
            return getEmailRule(field, view, (Email) annotation);
        }
        if (IpAddress.class.isAssignableFrom(cls)) {
            return getIpAddressRule(field, view, (IpAddress) annotation);
        }
        return null;
    }

    public static Rule<?> getRule(Field field, View view, Annotation annotation, Object... objArr) {
        if (ConfirmPassword.class.isAssignableFrom(annotation.getClass())) {
            return getConfirmPasswordRule(field, view, (ConfirmPassword) annotation, (TextView) objArr[0]);
        }
        if (objArr == null || objArr.length == 0) {
            return getRule(field, view, annotation);
        }
        return null;
    }

    private static Rule<TextView> getRequiredRule(Field field, View view, Required required) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), Required.class.getSimpleName()));
            return null;
        }
        int messageResId = required.messageResId();
        return Rules.required(messageResId != 0 ? view.getContext().getString(messageResId) : required.message(), required.trim());
    }

    private static Rule<View> getTextRule(Field field, View view, TextRule textRule) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), TextRule.class.getSimpleName()));
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int messageResId = textRule.messageResId();
        String string = messageResId != 0 ? view.getContext().getString(messageResId) : textRule.message();
        if (textRule.minLength() > 0) {
            arrayList.add(Rules.minLength(null, textRule.minLength(), textRule.trim()));
        }
        if (textRule.maxLength() != Integer.MAX_VALUE) {
            arrayList.add(Rules.maxLength(null, textRule.maxLength(), textRule.trim()));
        }
        Rule[] ruleArr = new Rule[arrayList.size()];
        arrayList.toArray(ruleArr);
        return Rules.and(string, ruleArr);
    }

    private static Rule<TextView> getRegexRule(Field field, View view, Regex regex) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), Regex.class.getSimpleName()));
            return null;
        }
        Context context = view.getContext();
        int messageResId = regex.messageResId();
        String string = messageResId != 0 ? context.getString(messageResId) : regex.message();
        int patternResId = regex.patternResId();
        return Rules.regex(string, patternResId != 0 ? view.getContext().getString(patternResId) : regex.pattern(), regex.trim());
    }

    private static Rule<View> getNumberRule(Field field, View view, NumberRule numberRule) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), NumberRule.class.getSimpleName()));
            return null;
        }
        if (numberRule.type() == null) {
            throw new IllegalArgumentException(String.format("@%s.type() cannot be null.", NumberRule.class.getSimpleName()));
        }
        ArrayList arrayList = new ArrayList();
        int messageResId = numberRule.messageResId();
        String string = messageResId != 0 ? view.getContext().getString(messageResId) : numberRule.message();
        int i = AnonymousClass1.$SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[numberRule.type().ordinal()];
        if (i == 1 || i == 2) {
            Rules.regex(null, Rules.REGEX_INTEGER, true);
        } else if (i == 3 || i == 4) {
            Rules.regex(null, Rules.REGEX_DECIMAL, true);
        }
        if (numberRule.lt() != Double.MIN_VALUE) {
            String valueOf = String.valueOf(numberRule.lt());
            double parseDouble = Double.parseDouble(valueOf);
            int i2 = AnonymousClass1.$SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[numberRule.type().ordinal()];
            if (i2 == 1) {
                arrayList.add(Rules.lt((String) null, (int) parseDouble));
            } else if (i2 == 2) {
                arrayList.add(Rules.lt((String) null, (long) parseDouble));
            } else if (i2 == 3) {
                arrayList.add(Rules.lt((String) null, Float.parseFloat(valueOf)));
            } else if (i2 == 4) {
                arrayList.add(Rules.lt((String) null, Double.parseDouble(valueOf)));
            }
        }
        if (numberRule.gt() != Double.MAX_VALUE) {
            String valueOf2 = String.valueOf(numberRule.gt());
            double parseDouble2 = Double.parseDouble(valueOf2);
            int i3 = AnonymousClass1.$SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[numberRule.type().ordinal()];
            if (i3 == 1) {
                arrayList.add(Rules.gt((String) null, (int) parseDouble2));
            } else if (i3 == 2) {
                arrayList.add(Rules.gt((String) null, (long) parseDouble2));
            } else if (i3 == 3) {
                arrayList.add(Rules.gt((String) null, Float.parseFloat(valueOf2)));
            } else if (i3 == 4) {
                arrayList.add(Rules.gt((String) null, Double.parseDouble(valueOf2)));
            }
        }
        if (numberRule.eq() != Double.MAX_VALUE) {
            String valueOf3 = String.valueOf(numberRule.eq());
            double parseDouble3 = Double.parseDouble(valueOf3);
            int i4 = AnonymousClass1.$SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[numberRule.type().ordinal()];
            if (i4 == 1) {
                arrayList.add(Rules.eq((String) null, (int) parseDouble3));
            } else if (i4 == 2) {
                arrayList.add(Rules.eq((String) null, (long) parseDouble3));
            } else if (i4 == 3) {
                arrayList.add(Rules.eq((String) null, Float.parseFloat(valueOf3)));
            } else if (i4 == 4) {
                arrayList.add(Rules.eq((String) null, Double.parseDouble(valueOf3)));
            }
        }
        Rule[] ruleArr = new Rule[arrayList.size()];
        arrayList.toArray(ruleArr);
        return Rules.and(string, ruleArr);
    }

    /* renamed from: com.android.pc.ioc.verification.AnnotationToRuleConverter$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType;

        static {
            int[] iArr = new int[NumberRule.NumberType.values().length];
            $SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType = iArr;
            try {
                iArr[NumberRule.NumberType.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[NumberRule.NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[NumberRule.NumberType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$pc$ioc$verification$annotation$NumberRule$NumberType[NumberRule.NumberType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static Rule<View> getPasswordRule(Field field, View view, Password password) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), Password.class.getSimpleName()));
            return null;
        }
        int messageResId = password.messageResId();
        String string = messageResId != 0 ? view.getContext().getString(messageResId) : password.message();
        ArrayList arrayList = new ArrayList();
        if (password.minLength() > 0) {
            arrayList.add(Rules.minLength(null, password.minLength(), password.trim()));
        }
        if (password.maxLength() != Integer.MAX_VALUE) {
            arrayList.add(Rules.maxLength(null, password.maxLength(), password.trim()));
        }
        Rule[] ruleArr = new Rule[arrayList.size()];
        arrayList.toArray(ruleArr);
        return Rules.and(string, ruleArr);
    }

    private static Rule<TextView> getConfirmPasswordRule(Field field, View view, ConfirmPassword confirmPassword, TextView textView) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), ConfirmPassword.class.getSimpleName()));
            return null;
        }
        int messageResId = confirmPassword.messageResId();
        return Rules.eq(messageResId != 0 ? view.getContext().getString(messageResId) : confirmPassword.message(), textView);
    }

    private static Rule<View> getEmailRule(Field field, View view, Email email) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), Regex.class.getSimpleName()));
            return null;
        }
        int messageResId = email.messageResId();
        String string = messageResId != 0 ? view.getContext().getString(messageResId) : email.message();
        if (email.empty()) {
            return Rules.or(string, Rules.eq((String) null, ""), Rules.regex(string, Rules.REGEX_EMAIL, true));
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Rules.required(string, true));
        arrayList.add(Rules.regex(string, Rules.REGEX_EMAIL, true));
        Rule[] ruleArr = new Rule[arrayList.size()];
        arrayList.toArray(ruleArr);
        return Rules.and(string, ruleArr);
    }

    private static Rule<View> getIpAddressRule(Field field, View view, IpAddress ipAddress) {
        if (!TextView.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_TEXT, field.getName(), IpAddress.class.getSimpleName()));
            return null;
        }
        int messageResId = ipAddress.messageResId();
        String string = messageResId != 0 ? view.getContext().getString(messageResId) : ipAddress.message();
        return Rules.or(string, Rules.eq((String) null, ""), Rules.regex(string, Rules.REGEX_IP_ADDRESS, true));
    }

    private static Rule<Checkable> getCheckedRule(Field field, View view, Checked checked) {
        if (!Checkable.class.isAssignableFrom(view.getClass())) {
            Log.w(TAG, String.format(WARN_CHECKABLE, field.getName(), Checked.class.getSimpleName()));
            return null;
        }
        int messageResId = checked.messageResId();
        return Rules.checked(messageResId != 0 ? view.getContext().getString(messageResId) : checked.message(), checked.checked());
    }
}
