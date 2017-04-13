package com.szborqs.mybook;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.szborqs.mybook.custom.MyPageView;

public class MainActivity extends FragmentActivity {

    private MyPageView myPageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        myPageView=(MyPageView)findViewById(R.id.myPageView);
    }
}
