package com.szborqs.mybook;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.szborqs.mybook.main.library.view.BookLibraryFragment;
import com.szborqs.mybook.main.me.view.MeFragment;
import com.szborqs.mybook.main.shelf.view.BookShelfFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private List<Fragment> fragmentsList;
    private TextView[] layouts;//
    private int currentTemp = -1;




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
                    setFocus(0);
                    break;
                case R.id.bookLibraryText:// 轨迹
                    setFocus(1);
                    break;
                case R.id.meText:// 我
                    setFocus(2);
                    break;
            }

        }
    };

    private void initView() {
        layouts = new TextView[3];
        layouts[0] = (TextView) findViewById(R.id.bookShelfText);// 车辆
        layouts[1] = (TextView) findViewById(R.id.bookLibraryText);// 轨迹
        layouts[2] = (TextView) findViewById(R.id.meText);// 我
        layouts[0].setOnClickListener(clickListener);
        layouts[1].setOnClickListener(clickListener);
        layouts[2].setOnClickListener(clickListener);
        fragmentsList = new ArrayList<Fragment>();

        fragmentsList.add(new BookShelfFragment());// 车辆
        fragmentsList.add(new BookLibraryFragment());// 车友
        fragmentsList.add(new MeFragment());// 我
        setFocus(0);
    }

    private void setFocus(int temp) {
        if (currentTemp == temp) {
            return;
        }
        for (int i = 0; i < layouts.length; i++) {
            if (i == temp) {
                currentTemp = temp;
                layouts[i].setSelected(true);
            } else {
                layouts[i].setSelected(false);
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        for (int i = 0; i < fragmentsList.size(); i++) {
            if (fragmentsList.get(i).isAdded()) {
                if (currentTemp == i) {
                    transaction.show(fragmentsList.get(i));
                } else {
                    transaction.hide(fragmentsList.get(i));
                }
            } else {
                if (currentTemp == i) {
                    transaction.add(R.id.fragment, fragmentsList.get(i));
                }

            }
        }
        transaction.commit();
    }


}
