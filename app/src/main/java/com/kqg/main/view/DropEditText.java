package com.kqg.main.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.utils.UiUtils;

/* loaded from: classes.dex */
public class DropEditText extends FrameLayout implements View.OnClickListener, AdapterView.OnItemClickListener {
    private int hitColor;
    private int mDrawable;
    private int mDrawableLeft;
    private int mDrawableUn;
    private ImageView mDropImage;
    private int mDropMode;
    private EditText mEditText;
    private EditText mEditTextPassWord;
    private LinearLayout mExtendView;
    private String mHit;
    private WrapListView mPopView;
    private WrapListView mPopView_empty;
    private PopupWindow mPopup;
    private View mlistExtendButton;
    private int pos;
    private float textSize;

    public WrapListView getPopView() {
        return this.mPopView;
    }

    public WrapListView getPopViewEmpty() {
        return this.mPopView_empty;
    }

    public DropEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EditText getEditText() {
        return this.mEditText;
    }

    public int getPos() {
        return this.pos;
    }

    public void setEditTextPassWord(EditText editText) {
        this.mEditTextPassWord = editText;
    }

    public DropEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(UiUtils.getLayOut("edit_layout"), this);
        this.mPopView = (WrapListView) LayoutInflater.from(context).inflate(UiUtils.getLayOut("pop_view"), (ViewGroup) null);
        this.mPopView_empty = (WrapListView) LayoutInflater.from(context).inflate(UiUtils.getLayOut("pop_view"), (ViewGroup) null);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mExtendView = linearLayout;
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mExtendView.setBackgroundColor(R.color.white);
        this.mExtendView.setLayoutParams(layoutParams);
        View inflate = LayoutInflater.from(context).inflate(UiUtils.getLayOut("list_extend_button"), (ViewGroup) null);
        this.mlistExtendButton = inflate;
        ((Button) inflate.findViewById(UiUtils.getId("btn_other_account"))).setOnClickListener(new View.OnClickListener() { // from class: com.kqg.main.view.DropEditText.1
            AnonymousClass1() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KaiQiGuSdk.getInstance().goToLogin();
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, UiUtils.getStyleableArray("DropEditText"), i, 0);
        this.mDrawableLeft = obtainStyledAttributes.getResourceId(UiUtils.getStyleable("DropEditText_drawableRight"), UiUtils.getDrawable("ic_launcher"));
        this.mDrawable = UiUtils.getDrawable("sdk_fold_btn");
        this.mDrawableUn = UiUtils.getDrawable("sdk_unfold_btn");
        this.mDropMode = obtainStyledAttributes.getInt(UiUtils.getStyleable("DropEditText_dropMode"), 0);
        this.mHit = obtainStyledAttributes.getString(UiUtils.getStyleable("DropEditText_hint"));
        this.hitColor = obtainStyledAttributes.getColor(UiUtils.getStyleable("DropEditText_textColorHint"), -1);
        this.textSize = obtainStyledAttributes.getDimension(UiUtils.getStyleable("DropEditText_textSize"), 10.0f);
        this.pos = 0;
        obtainStyledAttributes.recycle();
    }

    /* renamed from: com.kqg.main.view.DropEditText$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            KaiQiGuSdk.getInstance().goToLogin();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEditText = (EditText) findViewById(UiUtils.getId("dropview_edit"));
        ImageView imageView = (ImageView) findViewById(UiUtils.getId("dropview_image"));
        this.mDropImage = imageView;
        imageView.setVisibility(0);
        this.mEditText.setSelectAllOnFocus(true);
        this.mDropImage.setImageResource(this.mDrawableUn);
        if (!TextUtils.isEmpty(this.mHit)) {
            this.mEditText.setHint(this.mHit);
        }
        this.mDropImage.setOnClickListener(this);
        this.mPopView.setOnItemClickListener(this);
        this.mPopView_empty.setOnItemClickListener(this);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && this.mDropMode == 0) {
            this.mPopView.setListWidth(getMeasuredWidth());
            this.mPopView_empty.setListWidth(getMeasuredWidth());
        }
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        this.mPopView.setAdapter((ListAdapter) baseAdapter);
        this.mExtendView.addView(this.mPopView);
        PopupWindow popupWindow = new PopupWindow(this.mExtendView, -2, -2);
        this.mPopup = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(R.color.transparent));
        this.mPopup.setFocusable(false);
        this.mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.kqg.main.view.DropEditText.2
            AnonymousClass2() {
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                DropEditText.this.mDropImage.setImageResource(DropEditText.this.mDrawableUn);
            }
        });
    }

    /* renamed from: com.kqg.main.view.DropEditText$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements PopupWindow.OnDismissListener {
        AnonymousClass2() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            DropEditText.this.mDropImage.setImageResource(DropEditText.this.mDrawableUn);
        }
    }

    public void setEmptyAdapter(BaseAdapter baseAdapter) {
        this.mPopView_empty.setAdapter((ListAdapter) baseAdapter);
        if (this.mPopView.getAdapter().getCount() == 0) {
            this.mPopView_empty.setVisibility(0);
        } else {
            this.mPopView_empty.setVisibility(4);
        }
        this.mExtendView.addView(this.mPopView_empty);
    }

    public void setHint(String str) {
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.setHint(str);
        }
    }

    public String getText() {
        return this.mEditText.getText().toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == UiUtils.getId("dropview_image")) {
            if (this.mPopup.isShowing()) {
                this.mDropImage.setImageResource(this.mDrawableUn);
                this.mPopup.dismiss();
            } else {
                this.mDropImage.setImageResource(this.mDrawable);
                this.mPopup.showAsDropDown(this, 0, 5);
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        WrapListView wrapListView = this.mPopView;
        if (wrapListView == null || wrapListView.getAdapter().getCount() == 0) {
            this.mPopup.dismiss();
            return;
        }
        this.mEditText.setText(this.mPopView.getAdapter().getItem(i).toString());
        this.mEditText.setTag(((WrapListViewAdapter) this.mPopView.getAdapter()).getUserId(i));
        EditText editText = this.mEditTextPassWord;
        if (editText != null) {
            editText.setText(((WrapListViewAdapter) this.mPopView.getAdapter()).getUserPassword(i));
        }
        this.mPopup.dismiss();
        this.pos = i;
    }

    public void closePopup() {
        PopupWindow popupWindow = this.mPopup;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mDropImage.setImageResource(this.mDrawableUn);
        this.mPopup.dismiss();
    }
}
