package com.szborqs.mybook.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.szborqs.mybook.R;

/**
 * description
 *
 * @Author Administrator
 * @Time 2016/10/29 10:57
 */

public class CustomLoadingDialog extends Dialog {

    private static CustomLoadingDialog mDialog = null;
    private Context mContext = null;
    private Animation operatingAnim;
    private ImageView loadingImg;
    private TextView loadingText;

    public CustomLoadingDialog(Context context) {
        super(context);
        mContext = context;
    }

    public CustomLoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomLoadingDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    public static CustomLoadingDialog createDialog(Context context) {
        mDialog = new CustomLoadingDialog(context, R.style.loading_dialog);
        mDialog.setContentView(R.layout.custom_loading_dialog);

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setAnimation();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0.0f;//窗体以外区域亮度o

        return mDialog;
    }

    private void setAnimation() {
        loadingImg = (ImageView) mDialog
                .findViewById(R.id.loadingImg);
        operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.anim_progress_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
    }

    public void setText(String text) {
        loadingText = (TextView) mDialog
                .findViewById(R.id.loadingText);
        loadingText.setText(text);
    }

    private void startAnimation() {
        loadingImg.startAnimation(operatingAnim);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialog == null) {
            return;
        }
        mDialog.startAnimation();

    }

}
