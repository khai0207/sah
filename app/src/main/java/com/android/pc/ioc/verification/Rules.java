package com.android.pc.ioc.verification;

import android.text.TextUtils;
import android.view.View;
import android.widget.Checkable;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class Rules {
    public static final String EMPTY_STRING = "";
    public static final String REGEX_DECIMAL = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
    public static final String REGEX_EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_INTEGER = "\\d+";
    public static final String REGEX_IP_ADDRESS = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static Rule<TextView> required(String str, final boolean z) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.1
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                return !TextUtils.isEmpty(Rules.getText(textView, z));
            }
        };
    }

    public static Rule<TextView> regex(String str, final String str2, final boolean z) {
        if (str2 == null) {
            throw new IllegalArgumentException("'regex' cannot be null");
        }
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.2
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, z);
                if (text != null) {
                    return text.matches(str2);
                }
                return false;
            }
        };
    }

    public static Rule<TextView> minLength(String str, final int i, final boolean z) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.3
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, z);
                return text != null && text.length() >= i;
            }
        };
    }

    public static Rule<TextView> maxLength(String str, final int i, final boolean z) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.4
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, z);
                return text != null && text.length() <= i;
            }
        };
    }

    public static Rule<TextView> eq(String str, final TextView textView) {
        if (textView == null) {
            throw new IllegalArgumentException("'anotherTextView' cannot be null");
        }
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.5
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView2) {
                return textView2.getText().toString().equals(textView.getText().toString());
            }
        };
    }

    public static Rule<TextView> eq(String str, String str2) {
        return eq(str, str2, false, false);
    }

    public static Rule<TextView> eq(String str, final String str2, final boolean z, final boolean z2) {
        if (str2 == null) {
            str2 = "";
        }
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.6
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, z2);
                if (text != null) {
                    return z ? text.equalsIgnoreCase(str2) : text.equals(str2);
                }
                return false;
            }
        };
    }

    public static Rule<TextView> eq(String str, int i) {
        return eq(str, i);
    }

    public static Rule<TextView> gt(String str, int i) {
        return gt(str, i);
    }

    public static Rule<TextView> lt(String str, int i) {
        return lt(str, i);
    }

    public static Rule<TextView> eq(String str, final long j) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.7
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_INTEGER) && Long.parseLong(text) == j;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String str, final long j) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.8
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_INTEGER) && Long.parseLong(text) > j;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String str, final long j) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.9
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_INTEGER) && Long.parseLong(text) < j;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> eq(String str, final float f) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.10
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Float.parseFloat(text) == f;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String str, final float f) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.11
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Float.parseFloat(text) > f;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String str, final float f) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.12
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Float.parseFloat(text) < f;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> eq(String str, final double d) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.13
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Double.parseDouble(text) == d;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String str, final double d) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.14
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Double.parseDouble(text) > d;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String str, final double d) {
        return new Rule<TextView>(str) { // from class: com.android.pc.ioc.verification.Rules.15
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(TextView textView) {
                String text = Rules.getText(textView, true);
                if (text != null) {
                    return text.matches(Rules.REGEX_DECIMAL) && Double.parseDouble(text) < d;
                }
                return false;
            }
        };
    }

    public static Rule<Checkable> checked(String str, final boolean z) {
        return new Rule<Checkable>(str) { // from class: com.android.pc.ioc.verification.Rules.16
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(Checkable checkable) {
                return checkable.isChecked() == z;
            }
        };
    }

    public static Rule<Spinner> spinnerEq(String str, final String str2, final boolean z, final boolean z2) {
        return new Rule<Spinner>(str) { // from class: com.android.pc.ioc.verification.Rules.17
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(Spinner spinner) {
                Object selectedItem = spinner.getSelectedItem();
                if (str2 == null && selectedItem == null) {
                    return true;
                }
                if (str2 == null || selectedItem == null) {
                    return false;
                }
                String obj = selectedItem.toString();
                if (z2) {
                    obj = obj.trim();
                }
                return z ? obj.equalsIgnoreCase(str2) : obj.equals(str2);
            }
        };
    }

    public static Rule<Spinner> spinnerEq(String str, final int i) {
        return new Rule<Spinner>(str) { // from class: com.android.pc.ioc.verification.Rules.18
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(Spinner spinner) {
                return spinner.getSelectedItemPosition() == i;
            }
        };
    }

    public static Rule<View> and(String str, final Rule<?>... ruleArr) {
        return new Rule<View>(str) { // from class: com.android.pc.ioc.verification.Rules.19
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(View view) {
                boolean z = true;
                for (Rule rule : ruleArr) {
                    if (rule != null) {
                        z &= rule.isValid(view);
                    }
                    if (!z) {
                        break;
                    }
                }
                return z;
            }
        };
    }

    public static Rule<View> or(String str, final Rule<?>... ruleArr) {
        return new Rule<View>(str) { // from class: com.android.pc.ioc.verification.Rules.20
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(View view) {
                boolean z = false;
                for (Rule rule : ruleArr) {
                    if (rule != null) {
                        z |= rule.isValid(view);
                    }
                    if (z) {
                        break;
                    }
                }
                return z;
            }
        };
    }

    public static Rule<View> compositeAnd(String str, final LinkedHashMap<View, Rule<?>> linkedHashMap) {
        return new Rule<View>(str) { // from class: com.android.pc.ioc.verification.Rules.21
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(View view) {
                Iterator it = linkedHashMap.keySet().iterator();
                boolean z = true;
                while (it.hasNext()) {
                    z &= ((Rule) linkedHashMap.get((View) it.next())).isValid(view);
                    if (!z) {
                        break;
                    }
                }
                return z;
            }
        };
    }

    public static Rule<View> compositeOr(String str, final LinkedHashMap<View, Rule<?>> linkedHashMap) {
        return new Rule<View>(str) { // from class: com.android.pc.ioc.verification.Rules.22
            @Override // com.android.pc.ioc.verification.Rule
            public boolean isValid(View view) {
                boolean z = false;
                for (View view2 : linkedHashMap.keySet()) {
                    z |= ((Rule) linkedHashMap.get(view2)).isValid(view2);
                    if (z) {
                        break;
                    }
                }
                return z;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getText(TextView textView, boolean z) {
        CharSequence charSequence;
        if (textView != null) {
            charSequence = textView.getText();
            if (z) {
                charSequence = charSequence.toString().trim();
            }
        } else {
            charSequence = null;
        }
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
