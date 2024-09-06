package com.android.pc.ioc.verification;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class Validator {
    static final boolean DEBUG = false;
    static final String TAG = Validator.class.getSimpleName();
    private boolean mAnnotationsProcessed;
    private AsyncTask<Void, Void, ViewRulePair> mAsyncValidationTask;
    private Object mController;
    private Map<String, Object> mProperties;
    private ValidationListener mValidationListener;
    private List<ViewRulePair> mViewsAndRules;

    /* loaded from: classes.dex */
    public interface ValidationListener {
        void onValidationFailed(View view, Rule<?> rule);

        void onValidationSucceeded();
    }

    private Validator() {
        this.mAnnotationsProcessed = false;
        this.mViewsAndRules = new ArrayList();
        this.mProperties = new HashMap();
    }

    public Validator(Object obj) {
        this();
        if (obj == null) {
            throw new IllegalArgumentException("'controller' cannot be null");
        }
        this.mController = obj;
    }

    public void put(View view, Rule<?> rule) {
        if (rule == null) {
            throw new IllegalArgumentException("'rule' cannot be null");
        }
        this.mViewsAndRules.add(new ViewRulePair(view, rule));
    }

    public void put(View view, List<Rule<?>> list) {
        if (list == null) {
            throw new IllegalArgumentException("'rules' cannot be null");
        }
        Iterator<Rule<?>> it = list.iterator();
        while (it.hasNext()) {
            put(view, it.next());
        }
    }

    public void put(Rule<?> rule) {
        put((View) null, rule);
    }

    public synchronized void validate() {
        if (this.mValidationListener == null) {
            throw new IllegalStateException("Set a " + ValidationListener.class.getSimpleName() + " before attempting to validate.");
        }
        ViewRulePair validateAllRules = validateAllRules();
        if (validateAllRules == null) {
            this.mValidationListener.onValidationSucceeded();
        } else {
            this.mValidationListener.onValidationFailed(validateAllRules.view, validateAllRules.rule);
        }
    }

    public void validateAsync() {
        if (this.mValidationListener == null) {
            throw new IllegalStateException("Set a " + ValidationListener.class.getSimpleName() + " before attempting to validate.");
        }
        AsyncTask<Void, Void, ViewRulePair> asyncTask = this.mAsyncValidationTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mAsyncValidationTask = null;
        }
        AsyncTask<Void, Void, ViewRulePair> asyncTask2 = new AsyncTask<Void, Void, ViewRulePair>() { // from class: com.android.pc.ioc.verification.Validator.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public ViewRulePair doInBackground(Void... voidArr) {
                return Validator.this.validateAllRules();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(ViewRulePair viewRulePair) {
                if (viewRulePair == null) {
                    Validator.this.mValidationListener.onValidationSucceeded();
                } else {
                    Validator.this.mValidationListener.onValidationFailed(viewRulePair.view, viewRulePair.rule);
                }
                Validator.this.mAsyncValidationTask = null;
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                Validator.this.mAsyncValidationTask = null;
            }
        };
        this.mAsyncValidationTask = asyncTask2;
        asyncTask2.execute((Void[]) null);
    }

    public boolean isValidating() {
        AsyncTask<Void, Void, ViewRulePair> asyncTask = this.mAsyncValidationTask;
        return (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) ? false : true;
    }

    public boolean cancelAsync() {
        AsyncTask<Void, Void, ViewRulePair> asyncTask = this.mAsyncValidationTask;
        if (asyncTask == null) {
            return false;
        }
        boolean cancel = asyncTask.cancel(true);
        this.mAsyncValidationTask = null;
        return cancel;
    }

    public ValidationListener getValidationListener() {
        return this.mValidationListener;
    }

    public void setValidationListener(ValidationListener validationListener) {
        this.mValidationListener = validationListener;
    }

    public void setProperty(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
        this.mProperties.put(str, obj);
    }

    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
        return this.mProperties.get(str);
    }

    public Object removeProperty(String str) {
        if (str != null) {
            return this.mProperties.remove(str);
        }
        return null;
    }

    public boolean containsProperty(String str) {
        if (str != null) {
            return this.mProperties.containsKey(str);
        }
        return false;
    }

    public void removeAllProperties() {
        this.mProperties.clear();
    }

    public void removeRulesFor(View view) {
        if (view == null) {
            throw new IllegalArgumentException("'view' cannot be null");
        }
        int i = 0;
        while (i < this.mViewsAndRules.size()) {
            if (this.mViewsAndRules.get(i).view == view) {
                this.mViewsAndRules.remove(i);
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewRulePair validateAllRules() {
        if (!this.mAnnotationsProcessed) {
            createRulesFromAnnotations(getSaripaarAnnotatedFields());
            this.mAnnotationsProcessed = true;
        }
        if (this.mViewsAndRules.size() == 0) {
            Log.i(TAG, "No rules found. Passing validation by default.");
            return null;
        }
        for (ViewRulePair viewRulePair : this.mViewsAndRules) {
            if (viewRulePair != null && (viewRulePair.view == null || (viewRulePair.view.isShown() && viewRulePair.view.isEnabled()))) {
                if (!viewRulePair.rule.isValid(viewRulePair.view)) {
                    return viewRulePair;
                }
            }
        }
        return null;
    }

    private void createRulesFromAnnotations(List<AnnotationFieldPair> list) {
        ViewRulePair viewAndRule;
        TextView textView = null;
        TextView textView2 = null;
        for (AnnotationFieldPair annotationFieldPair : list) {
            if (annotationFieldPair.annotation.annotationType().equals(Password.class)) {
                if (textView == null) {
                    textView = (TextView) getView(annotationFieldPair.field);
                } else {
                    throw new IllegalStateException("You cannot annotate two fields in the same Activity with @Password.");
                }
            }
            if (annotationFieldPair.annotation.annotationType().equals(ConfirmPassword.class)) {
                if (textView == null) {
                    throw new IllegalStateException("A @Password annotated field is required before you can use @ConfirmPassword.");
                }
                if (textView2 != null) {
                    throw new IllegalStateException("You cannot annotate two fields in the same Activity with @ConfirmPassword.");
                }
                if (textView2 == null) {
                    textView2 = (TextView) getView(annotationFieldPair.field);
                }
            }
            if (annotationFieldPair.annotation.annotationType().equals(ConfirmPassword.class)) {
                viewAndRule = getViewAndRule(annotationFieldPair.field, annotationFieldPair.annotation, textView);
            } else {
                viewAndRule = getViewAndRule(annotationFieldPair.field, annotationFieldPair.annotation, new Object[0]);
            }
            if (viewAndRule != null) {
                this.mViewsAndRules.add(viewAndRule);
            }
        }
    }

    private ViewRulePair getViewAndRule(Field field, Annotation annotation, Object... objArr) {
        Rule<?> rule;
        View view = getView(field);
        if (view == null) {
            Log.w(TAG, String.format("Your %s - %s is null. Please check your field assignment(s).", field.getType().getSimpleName(), field.getName()));
            return null;
        }
        if (objArr != null && objArr.length > 0) {
            rule = AnnotationToRuleConverter.getRule(field, view, annotation, objArr);
        } else {
            rule = AnnotationToRuleConverter.getRule(field, view, annotation);
        }
        if (rule != null) {
            return new ViewRulePair(view, rule);
        }
        return null;
    }

    private View getView(Field field) {
        try {
            field.setAccessible(true);
            return (View) field.get(this.mController);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private List<AnnotationFieldPair> getSaripaarAnnotatedFields() {
        ArrayList arrayList = new ArrayList();
        for (Field field : getViewFieldsWithAnnotations()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (isSaripaarAnnotation(annotation)) {
                    arrayList.add(new AnnotationFieldPair(annotation, field));
                }
            }
        }
        Collections.sort(arrayList, new AnnotationFieldPairCompartor());
        return arrayList;
    }

    private List<Field> getViewFieldsWithAnnotations() {
        ArrayList arrayList = new ArrayList();
        for (Field field : getAllViewFields()) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length != 0) {
                arrayList.add(field);
            }
        }
        return arrayList;
    }

    private List<Field> getAllViewFields() {
        Class<? super Object> cls;
        ArrayList arrayList = new ArrayList();
        Object obj = this.mController;
        if (obj != null) {
            arrayList.addAll(getDeclaredViewFields(obj.getClass()));
            cls = this.mController.getClass().getSuperclass();
        } else {
            cls = null;
        }
        while (cls != null && !cls.equals(Object.class)) {
            List<Field> declaredViewFields = getDeclaredViewFields(cls);
            if (declaredViewFields.size() > 0) {
                arrayList.addAll(declaredViewFields);
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    private List<Field> getDeclaredViewFields(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        for (Field field : cls.getDeclaredFields()) {
            if (View.class.isAssignableFrom(field.getType())) {
                arrayList.add(field);
            }
        }
        return arrayList;
    }

    private boolean isSaripaarAnnotation(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        return annotationType.equals(Checked.class) || annotationType.equals(ConfirmPassword.class) || annotationType.equals(Email.class) || annotationType.equals(IpAddress.class) || annotationType.equals(NumberRule.class) || annotationType.equals(Password.class) || annotationType.equals(Regex.class) || annotationType.equals(Required.class) || annotationType.equals(TextRule.class);
    }

    /* loaded from: classes.dex */
    private class ViewRulePair {
        public Rule rule;
        public View view;

        public ViewRulePair(View view, Rule<?> rule) {
            this.view = view;
            this.rule = rule;
        }
    }

    /* loaded from: classes.dex */
    private class AnnotationFieldPair {
        public Annotation annotation;
        public Field field;

        public AnnotationFieldPair(Annotation annotation, Field field) {
            this.annotation = annotation;
            this.field = field;
        }
    }

    /* loaded from: classes.dex */
    private class AnnotationFieldPairCompartor implements Comparator<AnnotationFieldPair> {
        private AnnotationFieldPairCompartor() {
        }

        @Override // java.util.Comparator
        public int compare(AnnotationFieldPair annotationFieldPair, AnnotationFieldPair annotationFieldPair2) {
            int annotationOrder = getAnnotationOrder(annotationFieldPair.annotation);
            int annotationOrder2 = getAnnotationOrder(annotationFieldPair2.annotation);
            if (annotationOrder < annotationOrder2) {
                return -1;
            }
            return annotationOrder == annotationOrder2 ? 0 : 1;
        }

        private int getAnnotationOrder(Annotation annotation) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.equals(Checked.class)) {
                return ((Checked) annotation).order();
            }
            if (annotationType.equals(ConfirmPassword.class)) {
                return ((ConfirmPassword) annotation).order();
            }
            if (annotationType.equals(Email.class)) {
                return ((Email) annotation).order();
            }
            if (annotationType.equals(IpAddress.class)) {
                return ((IpAddress) annotation).order();
            }
            if (annotationType.equals(NumberRule.class)) {
                return ((NumberRule) annotation).order();
            }
            if (annotationType.equals(Password.class)) {
                return ((Password) annotation).order();
            }
            if (annotationType.equals(Regex.class)) {
                return ((Regex) annotation).order();
            }
            if (annotationType.equals(Required.class)) {
                return ((Required) annotation).order();
            }
            if (annotationType.equals(TextRule.class)) {
                return ((TextRule) annotation).order();
            }
            throw new IllegalArgumentException(String.format("%s is not a Saripaar annotation", annotationType.getName()));
        }
    }
}
