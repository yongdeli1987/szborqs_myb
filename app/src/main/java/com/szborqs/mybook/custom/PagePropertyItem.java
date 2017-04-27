package com.szborqs.mybook.custom;

import android.content.Context;

import com.szborqs.mybook.R;
import com.szborqs.mybook.util.SharedMethod;

/**
 * Created by Administrator on 2017/4/12.
 */

public class PagePropertyItem {
    private int pWidth;
    private int pHeight;
    private float fontSize;
    private float fontHeight;
    private int fontColor;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    private int lineDivide;
    private Context mContext;
    private float sp1;
    private float dp1;

    public PagePropertyItem(Context context,int width,int height){
        pWidth=width;
        pHeight=height;
        mContext=context;
        sp1=mContext.getResources().getDimension(R.dimen.sp1);
        dp1=mContext.getResources().getDimension(R.dimen.dp1);
        fontSize=15*sp1;
        fontHeight= SharedMethod.getFontHeightByTextSize(fontSize);
        lineDivide=(int)(2*dp1);
        paddingLeft=(int)(10*dp1);
        paddingRight=(int)(10*dp1);
        paddingTop=(int)(10*dp1);
        paddingBottom=(int)(10*dp1);
        fontColor=mContext.getResources().getColor(R.color.black_333333);
    }
}
