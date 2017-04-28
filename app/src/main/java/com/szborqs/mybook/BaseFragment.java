package com.szborqs.mybook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szborqs.mybook.custom.MyToast;


public class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected ImageView rightImg;
    protected TextView titleText;
    protected TextView leftText;
    protected TextView rightText;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    protected void initTitle(View view) {
        /*rightImg = (ImageView) view.findViewById(R.id.rightImg);
        titleText = (TextView) view.findViewById(R.id.titleText);
        leftText = (TextView) view.findViewById(R.id.leftText);
        rightText = (TextView) view.findViewById(R.id.rightText);
        *//*String returnString=getIntent().getStringExtra("returnString");
        if(returnString==null||returnString.equals("")){
            returnString=getResources().getString(R.string.back);
        }
        leftText.setText(returnString);*/
    }

    protected void showToast(String content) {
        MyToast.makeText(mActivity, content, 1000).show();
    }


}
