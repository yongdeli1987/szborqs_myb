package com.szborqs.mybook.custom;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.szborqs.mybook.R;
import com.szborqs.mybook.util.BitmapUtil;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyPageView extends View {

    private Context mContext;

    public MyPageView(Context context) {
        super(context);
    }

    public MyPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.graphite);
        Bitmap dest= BitmapUtil.getDestBitmap(getWidth(),getHeight(),bitmap);
        canvas.drawBitmap(dest,0,0,paint);
        super.onDraw(canvas);
    }
}
