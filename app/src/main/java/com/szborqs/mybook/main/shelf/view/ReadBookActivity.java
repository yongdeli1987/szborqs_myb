package com.szborqs.mybook.main.shelf.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.szborqs.mybook.BaseActivity;
import com.szborqs.mybook.R;
import com.szborqs.mybook.custom.MyPageView;
import com.szborqs.mybook.main.library.view.BookLibraryFragment;
import com.szborqs.mybook.main.me.view.MeFragment;
import com.szborqs.mybook.service.MyService;

import java.util.ArrayList;
import java.util.List;


public class ReadBookActivity extends BaseActivity {
    private MyPageView myPageView=null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bookShelfText:// 车辆
                    //setFocus(0);
                    break;
            }

        }
    };

    private void initView() {
        myPageView=(MyPageView)findViewById(R.id.myPageView);
    }




}
