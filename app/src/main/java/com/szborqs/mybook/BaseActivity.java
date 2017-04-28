package com.szborqs.mybook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szborqs.mybook.custom.MyToast;

public class BaseActivity extends FragmentActivity {

    public static BaseActivity instance;
    protected BaseActivity mActivity;
    protected ImageView rightImg;
    protected TextView titleText;
    protected TextView leftText;
    protected TextView rightText;
    protected RelativeLayout searchLayout;
    protected EditText keywordsEdit;
    protected boolean hasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        instance = this;

    }

    protected void initTitle() {
        /*rightImg = (ImageView) findViewById(R.id.rightImg);
        titleText = (TextView) findViewById(R.id.titleText);
        leftText = (TextView) findViewById(R.id.leftText);
        rightText = (TextView) findViewById(R.id.rightText);
        searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
        keywordsEdit = (EditText) findViewById(R.id.keywordsEdit);

        String returnString = getIntent().getStringExtra("returnString");
        if (returnString == null || returnString.equals("")) {
            returnString = getResources().getString(R.string.back);
        }
        leftText.setText(returnString);*/

    }

    protected void goBack() {
        mActivity.finish();
        mActivity.overridePendingTransition(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
    }

    protected void showToast(String content) {
        MyToast.makeText(mActivity, content, 1000).show();
    }

}
