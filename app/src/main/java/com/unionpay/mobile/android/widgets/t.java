package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class t extends LinearLayout implements View.OnClickListener {
    private Context a;
    private EditText b;
    private ImageView c;
    private boolean d;
    private b e;
    private a f;
    private int g;
    private Drawable h;
    private TextView i;
    private LinearLayout j;
    private View.OnClickListener k;
    private TextWatcher l;
    private View.OnFocusChangeListener m;

    /* loaded from: classes.dex */
    public interface a {
        void a(boolean z);
    }

    /* loaded from: classes.dex */
    public interface b extends a {
        void a_();

        void e();
    }

    public t(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = true;
        this.e = null;
        this.f = null;
        this.i = null;
        this.k = new u(this);
        this.l = new v(this);
        this.m = new w(this);
        this.a = context;
        setOrientation(0);
        this.g = com.unionpay.mobile.android.global.a.n;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setFocusable(true);
        TextView textView = new TextView(this.a);
        textView.setPadding(com.unionpay.mobile.android.utils.f.a(this.a, 10.0f), 0, 0, 0);
        textView.setEms(4);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.gravity = 19;
        addView(textView, layoutParams);
        textView.setGravity(19);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-13421773);
        this.i = textView;
        textView.setVisibility(8);
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setGravity(21);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 19;
        addView(relativeLayout, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setGravity(21);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.rightMargin = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        linearLayout.setVisibility(8);
        relativeLayout.addView(linearLayout, layoutParams3);
        this.j = linearLayout;
        RelativeLayout relativeLayout2 = new RelativeLayout(this.a);
        relativeLayout2.setGravity(16);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(0, linearLayout.getId());
        layoutParams4.rightMargin = com.unionpay.mobile.android.utils.f.a(this.a, 10.0f);
        relativeLayout.addView(relativeLayout2, layoutParams4);
        ImageView imageView = new ImageView(this.a);
        this.c = imageView;
        imageView.setId(imageView.hashCode());
        this.c.setBackgroundDrawable(this.h);
        this.c.setOnClickListener(this.k);
        this.c.setVisibility(8);
        ImageView imageView2 = this.c;
        imageView2.setId(imageView2.hashCode());
        this.c.setAdjustViewBounds(true);
        int a2 = com.unionpay.mobile.android.utils.f.a(this.a, 30.0f);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams5.addRule(11, -1);
        layoutParams5.addRule(15, -1);
        relativeLayout2.addView(this.c, layoutParams5);
        EditText editText = new EditText(this.a);
        this.b = editText;
        editText.setSingleLine();
        this.b.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.b.setTextColor(-10066330);
        this.b.setHintTextColor(-6710887);
        this.b.setBackgroundDrawable(null);
        this.b.setGravity(16);
        this.b.setPadding(com.unionpay.mobile.android.utils.f.a(this.a, 10.0f), 0, 0, 0);
        this.b.addTextChangedListener(this.l);
        if (this.d) {
            this.b.setOnFocusChangeListener(this.m);
        }
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams6.addRule(0, this.c.getId());
        layoutParams6.addRule(15, -1);
        layoutParams6.addRule(9, -1);
        relativeLayout2.addView(this.b, layoutParams6);
    }

    static /* synthetic */ boolean b(t tVar) {
        return (tVar.b == null || tVar.b().length() == 0 || !tVar.d) ? false : true;
    }

    static /* synthetic */ void g() {
    }

    public final TextView a(String str) {
        if (this.i != null && !TextUtils.isEmpty(str)) {
            this.i.setVisibility(0);
            this.i.setText(str);
        }
        return this.i;
    }

    public final t a(Drawable drawable) {
        if (drawable != null) {
            this.c.setBackgroundDrawable(drawable);
        }
        return this;
    }

    public final void a() {
        this.d = false;
        EditText editText = this.b;
        if (editText != null) {
            editText.setKeyListener(null);
            this.b.setFocusable(false);
            ImageView imageView = this.c;
            if (imageView == null || imageView.getVisibility() != 0) {
                return;
            }
            this.c.setVisibility(8);
        }
    }

    public final void a(int i) {
        EditText editText = this.b;
        if (editText != null) {
            editText.setInputType(i);
        }
    }

    public final void a(InputFilter inputFilter) {
        InputFilter[] inputFilterArr = {inputFilter};
        EditText editText = this.b;
        if (editText == null) {
            return;
        }
        InputFilter[] filters = editText.getFilters();
        if (filters == null) {
            this.b.setFilters(inputFilterArr);
            return;
        }
        InputFilter[] inputFilterArr2 = new InputFilter[filters.length + 1];
        System.arraycopy(filters, 0, inputFilterArr2, 0, filters.length);
        System.arraycopy(inputFilterArr, 0, inputFilterArr2, filters.length, 1);
        this.b.setFilters(inputFilterArr2);
    }

    public final void a(TextWatcher textWatcher) {
        EditText editText = this.b;
        if (editText == null || textWatcher == null) {
            return;
        }
        editText.addTextChangedListener(textWatcher);
    }

    public final void a(View view, LinearLayout.LayoutParams layoutParams) {
        LinearLayout linearLayout;
        if (view == null || (linearLayout = this.j) == null) {
            return;
        }
        linearLayout.addView(view, layoutParams);
        this.j.setVisibility(0);
    }

    public final void a(TextView.OnEditorActionListener onEditorActionListener) {
        EditText editText = this.b;
        if (editText == null || !this.d) {
            return;
        }
        editText.setOnEditorActionListener(onEditorActionListener);
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final void a(b bVar) {
        this.e = bVar;
        EditText editText = this.b;
        if (editText != null) {
            editText.setOnClickListener(this);
        }
    }

    public final String b() {
        EditText editText = this.b;
        if (editText != null) {
            return editText.getText().toString();
        }
        return null;
    }

    public final void b(int i) {
        EditText editText = this.b;
        if (editText != null) {
            editText.setSelection(i);
        }
    }

    public final void b(String str) {
        EditText editText = this.b;
        if (editText == null || str == null) {
            return;
        }
        editText.setHint(str);
    }

    public final Editable c() {
        EditText editText = this.b;
        if (editText != null) {
            return editText.getText();
        }
        return null;
    }

    public final void c(String str) {
        EditText editText = this.b;
        if (editText == null || str == null) {
            return;
        }
        editText.setText(str);
    }

    public final void d() {
        EditText editText = this.b;
        if (editText != null) {
            editText.setLongClickable(false);
        }
    }

    public final void e() {
        EditText editText = this.b;
        if (editText == null) {
            return;
        }
        editText.setText("");
        b bVar = this.e;
        if (bVar != null) {
            bVar.e();
        }
    }

    public final void f() {
        ((Activity) this.a).getWindow().setSoftInputMode(3);
        int i = Build.VERSION.SDK_INT;
        String str = i >= 16 ? "setShowSoftInputOnFocus" : i >= 14 ? "setSoftInputShownOnFocus" : null;
        if (str == null) {
            this.b.setInputType(0);
            return;
        }
        try {
            Method method = EditText.class.getMethod(str, Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(this.b, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            this.b.setInputType(0);
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.a_();
        }
    }

    @Override // android.view.View
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        EditText editText = this.b;
        if (editText != null) {
            editText.setOnTouchListener(onTouchListener);
        }
    }
}
