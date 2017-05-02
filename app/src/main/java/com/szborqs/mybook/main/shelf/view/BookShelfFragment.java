package com.szborqs.mybook.main.shelf.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szborqs.mybook.BaseFragment;
import com.szborqs.mybook.R;
import com.szborqs.mybook.db.MyBookManager;
import com.szborqs.mybook.main.library.model.MyBookItem;
import com.szborqs.mybook.main.shelf.adapter.BookShelfListAdater;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class BookShelfFragment extends BaseFragment {

    private BookShelfListAdater bookShelfListAdater;
    private ListView mListview;
    private List<MyBookItem> mList;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View childChild = inflater.inflate(R.layout.fragment_book_shelf,
                container, false);
        initMyView(childChild);
        return childChild;
    }

    private void initMyView(View view) {
        initTitle(view);
        mList=new ArrayList<MyBookItem>();
        bookShelfListAdater=new BookShelfListAdater(mActivity,mList);
        mListview=(ListView)view.findViewById(R.id.mListview);
        mListview.setAdapter(bookShelfListAdater);
        List<MyBookItem> tempList=MyBookManager.getInstance(mActivity).getAllBookList();
        mList.addAll(tempList);
        bookShelfListAdater.notifyDataSetChanged();
    }



    @Override
    protected void initTitle(View view) {
        super.initTitle(view);
        /*titleText.setText(getResources().getString(R.string.car_fengxi));
        rightImg.setImageResource(R.mipmap.ic_car_carmanage);
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setOnClickListener(onClickListener);*/
    }



}
