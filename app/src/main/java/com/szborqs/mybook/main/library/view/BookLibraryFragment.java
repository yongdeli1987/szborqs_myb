package com.szborqs.mybook.main.library.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szborqs.mybook.BaseFragment;
import com.szborqs.mybook.R;
import com.szborqs.mybook.main.library.adapter.ResourceMainPagerAdapter;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */

public class BookLibraryFragment extends BaseFragment {
    /** 可滑动的viewpager */
    public ViewPager mViewPager;
    /** fragment集合 */
    private ArrayList<Fragment> fragmentsList;
    /** 页卡头标 */
    private RelativeLayout[] relativeLayouts;
    /** 页卡头标 */
    private View [] lines;
    /** 页卡头标 */
    private TextView [] textViews;
    /** 当前选中页卡页标 */
    private int currentTemp = -1;
    private TypeFragment typeFragment;
    private SearchFragment searchFragment;
    private SortFragment sortFragment;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bookTypeLayout://返回
                    setFocus(0);
                    break;
                case R.id.sortLayout://返回
                    setFocus(1);
                    break;
                case R.id.searchLayout://返回
                    setFocus(2);
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View childChild = inflater.inflate(R.layout.fragment_book_library,
                container, false);
        initMyView(childChild);
        return childChild;
    }

    private void initMyView(View view) {
        initTitle(view);
        lines=new View[3];
        lines[0] = view.findViewById(R.id.bookTypeLine);
        lines[1] = view.findViewById(R.id.sortLine);
        lines[2] = view.findViewById(R.id.searchLine);
        textViews=new TextView[3];
        textViews[0] = (TextView)view.findViewById(R.id.bookTypeText);
        textViews[1] = (TextView)view.findViewById(R.id.sortText);
        textViews[2] = (TextView)view.findViewById(R.id.searchText);
        relativeLayouts=new RelativeLayout[3];
        relativeLayouts[0] = (RelativeLayout) view.findViewById(R.id.bookTypeLayout);
        relativeLayouts[1] = (RelativeLayout) view.findViewById(R.id.sortLayout);
        relativeLayouts[2] = (RelativeLayout) view.findViewById(R.id.searchLayout);
        relativeLayouts[0].setOnClickListener(onClickListener);
        relativeLayouts[1].setOnClickListener(onClickListener);
        relativeLayouts[2].setOnClickListener(onClickListener);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        fragmentsList = new ArrayList<Fragment>();
        mViewPager.setAdapter(new ResourceMainPagerAdapter(
                mActivity.getSupportFragmentManager(), fragmentsList));
        setFocus(0);
        // 多缓存一个页面
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(new MainOnPageChangeListener());
        initFragment();
    }

    private void initFragment() {
            typeFragment = new TypeFragment();
            sortFragment = new SortFragment();
            searchFragment = new SearchFragment();

            fragmentsList.add(typeFragment);
            fragmentsList.add(sortFragment);
            fragmentsList.add(searchFragment);

            mViewPager.getAdapter().notifyDataSetChanged();
    }



    @Override
    protected void initTitle(View view) {
        super.initTitle(view);
        /*titleText.setText(getResources().getString(R.string.car_fengxi));
        rightImg.setImageResource(R.mipmap.ic_car_carmanage);
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setOnClickListener(onClickListener);*/
    }

    private void setFocus(int index){
        if(currentTemp==index){

        }else{
            for(int i=0;i<relativeLayouts.length;i++){
                if(i==index){
                    currentTemp=index;
                    textViews[i].setTextColor(getResources().getColor(R.color.blue_009be0));
                    lines[i].setVisibility(View.VISIBLE);
                    mViewPager.setCurrentItem(i);
                }else{
                    textViews[i].setTextColor(getResources().getColor(R.color.dialog_title_black));
                    lines[i].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    /**
     * 页卡切换监听
     */
    public class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            setFocus(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }



}
