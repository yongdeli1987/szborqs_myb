package com.szborqs.mybook.custom;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.szborqs.mybook.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * description
 *
 * @Author Administrator
 * @Time 2016/11/3 9:20
 */

public class MyToast {
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private Timer timer;
    private View mView;
    private int time;

    private MyToast(Context context, String text, int time) {
        timer = new Timer();
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.custom_toast, null);
        TextView contentText = (TextView) mView.findViewById(R.id.contentText);
        contentText.setText(text);
        this.time = time;
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.CENTER;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static MyToast makeText(Context context, String text, int time) {
        MyToast toastCustom = new MyToast(context, text, time);
        return toastCustom;
    }

    public void show() {
        windowManager.addView(mView, params);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                windowManager.removeView(mView);
                timer.cancel();
            }
        }, time);
    }

}
