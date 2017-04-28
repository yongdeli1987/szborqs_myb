package com.szborqs.mybook.main.library.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szborqs.mybook.BaseFragment;
import com.szborqs.mybook.BaseItem;
import com.szborqs.mybook.R;
import com.szborqs.mybook.config.ActionConfigs;
import com.szborqs.mybook.custom.PullDownLoadListView;
import com.szborqs.mybook.main.library.adapter.BookTypeListAdapter;
import com.szborqs.mybook.main.library.model.BookTypeItem;
import com.szborqs.mybook.nohttp.CallServer;
import com.szborqs.mybook.nohttp.HttpListener;
import com.szborqs.mybook.util.BookLog;
import com.szborqs.mybook.util.LocalPreference;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class TypeFragment extends BaseFragment {

    private static final int GET_LIST_DATA = 100;

    private List<BaseItem> bookTypeList;
    private PullDownLoadListView mListview;
    private BookTypeListAdapter mAdapter;

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
        View childChild = inflater.inflate(R.layout.fragment_book_type,
                container, false);
        initMyView(childChild);
        return childChild;
    }

    private void initMyView(View view) {
        initTitle(view);
        mListview = (PullDownLoadListView) view.findViewById(R.id.mListview);
        bookTypeList=new ArrayList<BaseItem>();
        mAdapter=new BookTypeListAdapter(mActivity);
        mAdapter.setList(bookTypeList);
        mListview.setAdapter(mAdapter);
        getListData();
    }

    private void getListData() {
        Request<String> request = NoHttp.createStringRequest(ActionConfigs.GET_BOOK_TYPE, RequestMethod.GET);
        CallServer.getRequestInstance().add(mActivity, GET_LIST_DATA, request, mHttpListener, true, true);
    }

    @Override
    protected void initTitle(View view) {
        super.initTitle(view);
        /*titleText.setText(getResources().getString(R.string.car_fengxi));
        rightImg.setImageResource(R.mipmap.ic_car_carmanage);
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setOnClickListener(onClickListener);*/
    }

    private HttpListener<String> mHttpListener = new HttpListener<String>() {

        @Override
        public void onSucceed(int what, Response<String> response) {

            JSONObject jsonObject = null;
            int code = -1;
            try {
                BookLog.e("onSucceed what=" + what + " response=" + response.get());
                jsonObject = new JSONObject(response.get());
                code = jsonObject.optInt("return_code");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mActivity == null || mActivity.isFinishing()) {
                return;
            }
            switch (what) {
                case GET_LIST_DATA://获取列表数据
                if(jsonObject!=null){
                    Iterator<String> keys=jsonObject.keys();
                    LocalPreference.putBookTypeJson(jsonObject.toString());
                    while(keys.hasNext()){
                    String key=keys.next();
                        BookTypeItem item=new  BookTypeItem();
                        item.setCode(key);
                        item.setName(jsonObject.optString(key));
                        bookTypeList.add(item);
                    }
                }
                    mAdapter.notifyDataSetChanged();
                    break;


            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            BookLog.e("onFailed what=" + what);
            if (mActivity == null || mActivity.isFinishing()) {
                return;
            }
            switch (what) {
                case GET_LIST_DATA://获取列表数据

                    break;

            }
        }


    };

}
