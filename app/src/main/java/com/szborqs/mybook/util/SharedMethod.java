package com.szborqs.mybook.util;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.szborqs.mybook.system.MyApplication;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/4/19.
 */

public class SharedMethod {

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) MyApplication.getInstance()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 根据字体大小，获取文字高度
     *
     * @param textSize
     * @return
     */
    public static float getFontHeightByTextSize(float textSize) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float height = (fm.descent - fm.ascent);
        return Math.abs(height);
    }

    /**
     * 格式化double.
     */
    public static double formatDouble(double original, int num) {
        BigDecimal temp = new BigDecimal(original);
        double result = temp.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }


}
