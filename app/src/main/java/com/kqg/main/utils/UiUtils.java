package com.kqg.main.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.kqg.main.R;
import com.kqg.main.base.KaiQiGuSdk;
import com.kqg.main.callback.InstallOrUpdateCallBack;
import com.unionpay.tsmservice.data.Constant;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class UiUtils {
    private static Dialog dialog;

    public static Context getContext() {
        return KaiQiGuSdk.getInstance().getCtx();
    }

    public static void toast(Object obj) {
        if (obj != null) {
            Toast.makeText(getContext(), obj.toString(), 0).show();
        }
    }

    public static void showImageToast(String str) {
        Context ctx = KaiQiGuSdk.getInstance().getCtx();
        View inflate = LayoutInflater.from(ctx).inflate(R.layout.view_toast_custom, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_message)).setText(str);
        Toast toast = new Toast(ctx);
        toast.setGravity(17, 0, 0);
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }

    public static int getIdentifier(String str, String str2) {
        return getContext().getResources().getIdentifier(str, str2, getContext().getPackageName());
    }

    public static int getId(String str) {
        return getIdentifier(str, "id");
    }

    public static String getResString(String str) {
        return getContext().getString(getIdentifier(str, "string"));
    }

    public static int getLayOut(String str) {
        return getIdentifier(str, "layout");
    }

    public static int getAnim(String str) {
        return getIdentifier(str, "anim");
    }

    public static int getStyleable(String str) {
        return ((Integer) getResourceId(str, "styleable")).intValue();
    }

    public static String getByte(String[] strArr, String str) {
        for (String str2 : strArr) {
            String[] split = str2.split("=");
            if (split[0].equals(str)) {
                return split[1];
            }
        }
        return "";
    }

    public static int[] getStyleableArray(String str) {
        return (int[]) getResourceId(str, "styleable");
    }

    private static Object getResourceId(String str, String str2) {
        try {
            for (Class<?> cls : Class.forName(getContext().getPackageName() + ".R").getClasses()) {
                if (cls.getSimpleName().equals(str2)) {
                    for (Field field : cls.getFields()) {
                        if (field.getName().equals(str)) {
                            return field.get(null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Dialog createLoadingDialog(Activity activity, String str) {
        View inflate = LayoutInflater.from(activity).inflate(getLayOut("loading_dialog"), (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(getId("dialog_view"));
        ImageView imageView = (ImageView) inflate.findViewById(getId("img"));
        TextView textView = (TextView) inflate.findViewById(getId("tipTextView"));
        if (str != null) {
            textView.setText(str);
        }
        imageView.startAnimation(AnimationUtils.loadAnimation(activity, getAnim("loading_animation")));
        Dialog dialog2 = new Dialog(activity, getLayOut("loading_dialog"));
        dialog2.setCancelable(false);
        dialog2.setContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        return dialog2;
    }

    public static void showLoadingDialog(Activity activity) {
        if (dialog == null) {
            dialog = createLoadingDialog(activity, null);
        }
        dialog.show();
    }

    public static void hideLoadingDialog() {
        Dialog dialog2 = dialog;
        if (dialog2 != null) {
            dialog2.dismiss();
            dialog = null;
        }
    }

    public static int getDrawable(String str) {
        return getIdentifier(str, "drawable");
    }

    public static Dialog showProgressDialog(Activity activity, String str) {
        Dialog createLoadingDialog = createLoadingDialog(activity, str);
        createLoadingDialog.show();
        return createLoadingDialog;
    }

    public static void needInstallOrUpdateUpPlugin(Activity activity, final InstallOrUpdateCallBack installOrUpdateCallBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getResString("tip"));
        builder.setMessage(getResString("uppay_install"));
        builder.setNegativeButton(getResString("sure"), new DialogInterface.OnClickListener() { // from class: com.kqg.main.utils.UiUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                InstallOrUpdateCallBack installOrUpdateCallBack2 = InstallOrUpdateCallBack.this;
                if (installOrUpdateCallBack2 != null) {
                    installOrUpdateCallBack2.onFinishedCallBack();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(getResString(Constant.CASH_LOAD_CANCEL), new DialogInterface.OnClickListener() { // from class: com.kqg.main.utils.UiUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
