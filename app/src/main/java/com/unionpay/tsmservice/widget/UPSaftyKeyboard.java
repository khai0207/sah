package com.unionpay.tsmservice.widget;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.os.RemoteException;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class UPSaftyKeyboard {
    private static List<Integer> ab = new ArrayList(10);
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private OnShowListener O;
    private OnHideListener P;
    private OnEditorListener Q;
    private UPTsmAddon.UPTsmConnectionListener R;
    private AudioManager S;
    private Vibrator T;
    private UPTsmAddon Z;
    private String aa;
    private Context b;
    private LinearLayout c;
    private RelativeLayout d;
    private ImageView e;
    private PopupWindow g;
    private TextView h;
    private b i;
    private Drawable k;
    private Drawable[] l;
    private Drawable[] m;
    private Drawable n;
    private Drawable o;
    private Drawable p;
    private Drawable q;
    private Drawable r;
    private Drawable s;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private a f = null;
    private boolean j = true;
    private int t = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: u, reason: collision with root package name */
    private int f64u = 40;
    private int N = 0;
    private boolean U = false;
    private boolean V = false;
    private boolean W = false;
    private boolean X = true;
    private boolean Y = false;
    private PopupWindow.OnDismissListener ac = new PopupWindow.OnDismissListener() { // from class: com.unionpay.tsmservice.widget.UPSaftyKeyboard.3
        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            if (UPSaftyKeyboard.this.P != null) {
                UPSaftyKeyboard.this.P.onHide();
            }
        }
    };
    final View.OnClickListener a = new View.OnClickListener() { // from class: com.unionpay.tsmservice.widget.UPSaftyKeyboard.4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (UPSaftyKeyboard.this.V) {
                UPSaftyKeyboard.this.T.vibrate(new long[]{0, 100}, -1);
            }
            int id = view.getId();
            if (id == 10) {
                UPSaftyKeyboard.this.hide();
                return;
            }
            if (id == 20) {
                if (UPSaftyKeyboard.this.N > 0) {
                    UPSaftyKeyboard.a();
                    UPSaftyKeyboard.f(UPSaftyKeyboard.this);
                }
            } else {
                if (UPSaftyKeyboard.this.N == 6) {
                    return;
                }
                UPSaftyKeyboard.a(Integer.toString(id));
                UPSaftyKeyboard.g(UPSaftyKeyboard.this);
            }
            if (UPSaftyKeyboard.this.Q != null) {
                UPSaftyKeyboard.this.Q.onEditorChanged(UPSaftyKeyboard.this.N);
            }
        }
    };

    /* loaded from: classes.dex */
    public interface OnEditorListener {
        void onEditorChanged(int i);
    }

    /* loaded from: classes.dex */
    public interface OnHideListener {
        void onHide();
    }

    /* loaded from: classes.dex */
    public interface OnShowListener {
        void onShow();
    }

    /* loaded from: classes.dex */
    private class a extends GridView {
        private a(Context context) {
            super(context);
        }

        /* synthetic */ a(UPSaftyKeyboard uPSaftyKeyboard, Context context, byte b) {
            this(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes.dex */
    private class b extends BaseAdapter {
        private b() {
            Collections.shuffle(UPSaftyKeyboard.ab);
        }

        /* synthetic */ b(UPSaftyKeyboard uPSaftyKeyboard, byte b) {
            this();
        }

        static /* synthetic */ void a() {
            Collections.shuffle(UPSaftyKeyboard.ab);
        }

        private void a(TextView textView) {
            textView.setBackgroundDrawable(UPSaftyKeyboard.a(new ColorDrawable(-1), new ColorDrawable(-7829368)));
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return 12;
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            List list;
            if (i == 9) {
                return UPSaftyKeyboard.this.Y ? Constant.STRING_DELETE_BUTTON : Constant.STRING_CONFIRM_BUTTON;
            }
            if (i == 11) {
                return UPSaftyKeyboard.this.Y ? Constant.STRING_CONFIRM_BUTTON : Constant.STRING_DELETE_BUTTON;
            }
            if (i == 10) {
                list = UPSaftyKeyboard.ab;
                i--;
            } else {
                list = UPSaftyKeyboard.ab;
            }
            return String.valueOf(list.get(i));
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            List list;
            if (i == 9) {
                return UPSaftyKeyboard.this.Y ? 20L : 10L;
            }
            if (i == 11) {
                return UPSaftyKeyboard.this.Y ? 10L : 20L;
            }
            if (i == 10) {
                list = UPSaftyKeyboard.ab;
                i--;
            } else {
                list = UPSaftyKeyboard.ab;
            }
            return ((Integer) list.get(i)).intValue();
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            Drawable drawable;
            if (-1 != UPSaftyKeyboard.this.x) {
                UPSaftyKeyboard uPSaftyKeyboard = UPSaftyKeyboard.this;
                uPSaftyKeyboard.K = (((((uPSaftyKeyboard.x - (UPSaftyKeyboard.this.A * 2)) - UPSaftyKeyboard.this.C) - UPSaftyKeyboard.this.E) - UPSaftyKeyboard.this.G) - UPSaftyKeyboard.this.I) / 3;
            }
            UPSaftyKeyboard uPSaftyKeyboard2 = UPSaftyKeyboard.this;
            uPSaftyKeyboard2.L = ((((((uPSaftyKeyboard2.y - UPSaftyKeyboard.this.z) - UPSaftyKeyboard.this.D) - UPSaftyKeyboard.this.F) - UPSaftyKeyboard.this.H) - UPSaftyKeyboard.this.J) - (UPSaftyKeyboard.this.B * 3)) / 4;
            ImageButton imageButton = new ImageButton(UPSaftyKeyboard.this.b);
            imageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageButton.setLayoutParams(new AbsListView.LayoutParams(UPSaftyKeyboard.this.K, UPSaftyKeyboard.this.L));
            TextView textView = new TextView(UPSaftyKeyboard.this.b);
            textView.setTextColor(UPSaftyKeyboard.this.t);
            textView.setTextSize(0, UPSaftyKeyboard.this.f64u);
            textView.setGravity(17);
            textView.setLayoutParams(new AbsListView.LayoutParams(UPSaftyKeyboard.this.K, UPSaftyKeyboard.this.L));
            if (!UPSaftyKeyboard.this.U) {
                imageButton.setSoundEffectsEnabled(false);
                textView.setSoundEffectsEnabled(false);
            }
            long itemId = getItemId(i);
            String str = (String) getItem(i);
            if (10 == itemId) {
                if (UPSaftyKeyboard.this.n == null && UPSaftyKeyboard.this.p == null) {
                    a(textView);
                    if (!UPSaftyKeyboard.this.X) {
                        return textView;
                    }
                    textView.setText(str);
                    textView.setId((int) getItemId(i));
                    textView.setOnClickListener(UPSaftyKeyboard.this.a);
                    return textView;
                }
                if (UPSaftyKeyboard.this.n != null) {
                    imageButton.setImageDrawable(UPSaftyKeyboard.this.n);
                }
                if (UPSaftyKeyboard.this.p != null) {
                    imageButton.setBackgroundDrawable(UPSaftyKeyboard.this.p.getConstantState().newDrawable());
                }
                if (!UPSaftyKeyboard.this.X) {
                    imageButton.setEnabled(false);
                    return imageButton;
                }
                imageButton.setId((int) getItemId(i));
                imageButton.setOnClickListener(UPSaftyKeyboard.this.a);
                return imageButton;
            }
            if (20 == itemId) {
                if (UPSaftyKeyboard.this.o == null && UPSaftyKeyboard.this.q == null) {
                    textView.setText(str);
                    a(textView);
                    textView.setId((int) getItemId(i));
                    textView.setOnClickListener(UPSaftyKeyboard.this.a);
                    return textView;
                }
                if (UPSaftyKeyboard.this.o != null) {
                    imageButton.setImageDrawable(UPSaftyKeyboard.this.o);
                }
                if (UPSaftyKeyboard.this.q != null) {
                    drawable = UPSaftyKeyboard.this.q;
                    imageButton.setBackgroundDrawable(drawable.getConstantState().newDrawable());
                }
            } else {
                if (UPSaftyKeyboard.this.l == null) {
                    textView.setText(String.valueOf((int) itemId));
                    textView.setBackgroundDrawable(UPSaftyKeyboard.this.s != null ? UPSaftyKeyboard.this.s.getConstantState().newDrawable() : UPSaftyKeyboard.a(new ColorDrawable(-1), new ColorDrawable(-7829368)));
                    textView.setId((int) getItemId(i));
                    textView.setOnClickListener(UPSaftyKeyboard.this.a);
                    return textView;
                }
                int i2 = (int) itemId;
                if (UPSaftyKeyboard.this.l[i2] != null) {
                    imageButton.setImageDrawable(UPSaftyKeyboard.this.l[i2].getConstantState().newDrawable());
                }
                if (UPSaftyKeyboard.this.m != null && UPSaftyKeyboard.this.m[i2] != null) {
                    drawable = UPSaftyKeyboard.this.m[i2];
                } else if (UPSaftyKeyboard.this.s != null) {
                    drawable = UPSaftyKeyboard.this.s;
                }
                imageButton.setBackgroundDrawable(drawable.getConstantState().newDrawable());
            }
            imageButton.setId((int) getItemId(i));
            imageButton.setOnClickListener(UPSaftyKeyboard.this.a);
            return imageButton;
        }
    }

    static {
        for (int i = 0; i < 10; i++) {
            ab.add(Integer.valueOf(i));
        }
    }

    public UPSaftyKeyboard(Context context) {
        this.b = null;
        this.b = context;
        c();
        a(context);
        d();
    }

    public UPSaftyKeyboard(Context context, Drawable drawable) {
        this.b = null;
        this.b = context;
        this.k = drawable;
        c();
        a(context);
        d();
    }

    static /* synthetic */ StateListDrawable a(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (drawable2 != null) {
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, drawable2);
        }
        if (drawable != null) {
            stateListDrawable.addState(new int[]{R.attr.state_enabled}, drawable);
        }
        return stateListDrawable;
    }

    static /* synthetic */ void a() {
        try {
            IUPJniInterface.dOPD();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private void a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        this.c = linearLayout;
        linearLayout.setOrientation(1);
        Drawable drawable = this.k;
        if (drawable != null) {
            this.c.setBackgroundDrawable(drawable);
        } else {
            this.c.setBackgroundColor(-7829368);
        }
        this.d = new RelativeLayout(context);
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, e() / 16));
        TextView textView = new TextView(context);
        this.h = textView;
        textView.setText("Secure Mode");
        this.h.setTextColor(-1);
        this.h.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(15);
        this.h.setLayoutParams(layoutParams);
        ImageView imageView = new ImageView(context);
        this.e = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(120, -2);
        layoutParams2.addRule(11);
        this.e.setLayoutParams(layoutParams2);
        this.e.setVisibility(8);
        this.d.addView(this.h);
        this.d.addView(this.e);
        a aVar = new a(this, context, (byte) 0);
        this.f = aVar;
        aVar.setHorizontalScrollBarEnabled(false);
        this.f.setVerticalScrollBarEnabled(false);
        this.f.setEnabled(false);
        this.f.setNumColumns(3);
        this.f.setVerticalSpacing(this.B);
        this.f.setHorizontalSpacing(this.A);
        this.f.setAdapter((ListAdapter) this.i);
        this.f.setPadding(this.G, this.H, this.I, this.J);
        this.c.addView(this.d);
        this.c.addView(this.f);
        this.c.setPadding(this.C, this.D, this.E, this.F);
        PopupWindow popupWindow = new PopupWindow(this.c, this.x, this.y);
        this.g = popupWindow;
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        this.g.setSoftInputMode(2);
        this.g.setOutsideTouchable(true);
        this.g.setFocusable(true);
        this.g.setOnDismissListener(this.ac);
    }

    static /* synthetic */ void a(String str) {
        try {
            IUPJniInterface.aPD(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private static String b(String str) {
        try {
            return IUPJniInterface.rEFP(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String c(String str) {
        try {
            return IUPJniInterface.ePB(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    private void c() {
        this.v = 0;
        this.w = 0;
        this.K = -1;
        this.L = e() / 14;
        this.z = e() / 16;
        this.A = 10;
        this.B = 10;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 10;
        this.H = 5;
        this.I = 10;
        this.J = 10;
        this.x = -1;
        this.y = (e() / 16) + ((e() / 14) * 4) + (this.B * 3) + 10 + 5;
        this.T = (Vibrator) this.b.getSystemService("vibrator");
        this.S = (AudioManager) this.b.getSystemService("audio");
        this.W = true;
        this.i = new b(this, (byte) 0);
    }

    private void d() {
        clearPwd();
        UPTsmAddon uPTsmAddon = UPTsmAddon.getInstance(this.b);
        this.Z = uPTsmAddon;
        if (!uPTsmAddon.isConnected()) {
            UPTsmAddon.UPTsmConnectionListener uPTsmConnectionListener = new UPTsmAddon.UPTsmConnectionListener() { // from class: com.unionpay.tsmservice.widget.UPSaftyKeyboard.1
                @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
                public final void onTsmConnected() {
                    try {
                        String[] strArr = new String[1];
                        UPSaftyKeyboard.this.Z.getPubKey(1001, strArr);
                        UPSaftyKeyboard.this.aa = strArr[0];
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
                public final void onTsmDisconnected() {
                }
            };
            this.R = uPTsmConnectionListener;
            this.Z.addConnectionListener(uPTsmConnectionListener);
            this.Z.bind();
            return;
        }
        try {
            String[] strArr = new String[1];
            this.Z.getPubKey(1001, strArr);
            this.aa = strArr[0];
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private int e() {
        Context context = this.b;
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return 0;
    }

    static /* synthetic */ int f(UPSaftyKeyboard uPSaftyKeyboard) {
        int i = uPSaftyKeyboard.N;
        uPSaftyKeyboard.N = i - 1;
        return i;
    }

    static /* synthetic */ int g(UPSaftyKeyboard uPSaftyKeyboard) {
        int i = uPSaftyKeyboard.N;
        uPSaftyKeyboard.N = i + 1;
        return i;
    }

    public void clearPwd() {
        try {
            IUPJniInterface.cPD();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
        this.N = 0;
    }

    public int getCurrentPinLength() {
        return this.N;
    }

    public String getEncryptPin() {
        return !TextUtils.isEmpty(this.aa) ? b(this.aa) : "";
    }

    public String getEncryptPin(String str) {
        return !TextUtils.isEmpty(str) ? c(str) : "";
    }

    public void hide() {
        PopupWindow popupWindow = this.g;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.g.dismiss();
        }
        OnHideListener onHideListener = this.P;
        if (onHideListener != null) {
            onHideListener.onHide();
        }
    }

    public void setAnimationStyle(int i) {
        this.M = i;
    }

    public void setDelKeyDrawableSelector(Drawable drawable, Drawable drawable2) {
        this.o = drawable;
        this.q = drawable2;
    }

    public void setDoneKeyDrawableSelector(Drawable drawable, Drawable drawable2) {
        this.n = drawable;
        this.p = drawable2;
    }

    public void setDoneKeyEnable(boolean z) {
        this.X = z;
    }

    public void setDoneKeyRightMode(boolean z) {
        this.Y = z;
    }

    public void setKeyAreaPadding(int i, int i2, int i3, int i4) {
        this.G = i;
        this.H = i2;
        this.I = i3;
        this.J = i4;
        this.f.setPadding(i, i2, i3, i4);
    }

    public void setKeyBoardSize(int i, int i2) {
        if (i >= 0) {
            this.x = i;
        }
        if (i2 >= 0) {
            this.y = i2;
        }
        this.c.setLayoutParams(new LinearLayout.LayoutParams(this.x, this.y));
    }

    public void setKeyboardAudio(boolean z) {
        AudioManager audioManager = this.S;
        int ringerMode = audioManager != null ? audioManager.getRingerMode() : -1;
        if (z) {
            if (this.W) {
                this.U = true;
            } else {
                this.U = false;
            }
            if (ringerMode == 0) {
                this.U = false;
            }
        }
    }

    public void setKeyboardBackground(Drawable drawable) {
        if (drawable != null) {
            this.k = drawable;
            this.c.setBackgroundDrawable(drawable);
        }
    }

    public void setKeyboardPadding(int i, int i2, int i3, int i4) {
        this.C = i;
        this.D = i2;
        this.E = i3;
        this.F = i4;
        this.c.setPadding(i, i2, i3, i4);
    }

    public void setKeyboardStartPosition(int i, int i2) {
        if (i < 0 || i2 < 0) {
            return;
        }
        this.v = i;
        this.w = i2;
        this.j = false;
    }

    public void setKeyboardVibrate(boolean z) {
        this.V = z;
    }

    public void setNumKeyBackgroud(Drawable drawable) {
        this.s = drawable;
    }

    public void setNumKeyMargin(int i, int i2) {
        if (i >= 0) {
            this.B = i;
            this.f.setVerticalSpacing(i);
        }
        if (i2 >= 0) {
            this.A = i2;
            this.f.setHorizontalSpacing(i2);
        }
    }

    public void setNumberKeyColor(int i) {
        this.t = i;
    }

    public void setNumberKeyDrawableSelector(Drawable[] drawableArr, Drawable[] drawableArr2) {
        this.l = drawableArr;
        this.m = drawableArr2;
    }

    public void setNumberKeySize(int i) {
        if (i >= 0) {
            this.f64u = i;
        }
    }

    public void setOnEditorListener(OnEditorListener onEditorListener) {
        this.Q = onEditorListener;
    }

    public void setOnHideListener(OnHideListener onHideListener) {
        this.P = onHideListener;
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.O = onShowListener;
    }

    public void setTitleBackground(Drawable drawable) {
        if (drawable != null) {
            this.d.setBackgroundDrawable(drawable);
        }
    }

    public void setTitleColor(int i) {
        this.h.setTextColor(i);
    }

    public void setTitleConfirmDrawable(Drawable drawable) {
        if (drawable != null) {
            this.e.setVisibility(0);
            this.e.setImageDrawable(drawable);
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.unionpay.tsmservice.widget.UPSaftyKeyboard.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UPSaftyKeyboard.this.hide();
                }
            });
        }
    }

    public void setTitleDrawable(Drawable drawable) {
        this.r = drawable;
        this.h.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        this.h.setCompoundDrawablePadding(10);
    }

    public void setTitleDrawablePadding(int i) {
        this.h.setCompoundDrawablePadding(i);
    }

    public void setTitleDrawableSize(int i, int i2) {
        Drawable drawable = this.r;
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i2);
            this.h.setCompoundDrawables(this.r, null, null, null);
        }
    }

    public void setTitleFont(Typeface typeface) {
        if (typeface != null) {
            this.h.setTypeface(typeface);
        }
    }

    public void setTitleHeight(int i) {
        if (i <= 0) {
            this.z = 0;
            this.c.removeView(this.d);
            this.g.setContentView(this.c);
        } else {
            this.z = i;
            this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        }
    }

    public void setTitleSize(int i) {
        if (i >= 0) {
            this.h.setTextSize(0, i);
        }
    }

    public void setTitleText(String str) {
        this.h.setText(str);
    }

    public void show(View view) {
        if (this.i != null) {
            b.a();
            this.i.notifyDataSetChanged();
        }
        this.f.requestLayout();
        this.c.invalidate();
        PopupWindow popupWindow = this.g;
        if (popupWindow != null) {
            popupWindow.setWidth(this.x);
            this.g.setHeight(this.y);
        }
        PopupWindow popupWindow2 = this.g;
        if (popupWindow2 != null) {
            popupWindow2.setAnimationStyle(this.M);
            OnShowListener onShowListener = this.O;
            if (onShowListener != null) {
                onShowListener.onShow();
            }
            if (this.j) {
                this.g.showAtLocation(view, 80, 0, 0);
            } else {
                this.g.showAtLocation(view, 51, this.v, this.w);
            }
            this.g.update();
        }
    }
}
