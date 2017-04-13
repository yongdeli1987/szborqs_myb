package com.szborqs.mybook.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Administrator on 2017/4/12.
 */

public class BitmapUtil {

    public static Bitmap getDestBitmap(int destW, int destH, Bitmap src){
        Bitmap result=null;
        Matrix matrix=new Matrix();
        int srcW=src.getWidth();
        int srcH=src.getHeight();
        float scaleW=destW*1.0f/srcW;
        float scaleH=destH*1.0f/srcH;
        matrix.setScale(scaleW,scaleH);
        result=Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,false);
        return result;
    }


}
