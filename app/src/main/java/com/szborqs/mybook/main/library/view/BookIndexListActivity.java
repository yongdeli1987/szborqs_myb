package com.szborqs.mybook.main.library.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.szborqs.mybook.BaseActivity;
import com.szborqs.mybook.BaseItem;
import com.szborqs.mybook.R;
import com.szborqs.mybook.config.ActionConfigs;
import com.szborqs.mybook.custom.PullDownLoadListView;
import com.szborqs.mybook.main.library.adapter.BookIndexListAdapter;
import com.szborqs.mybook.main.library.adapter.OnlineBookListAdapter;
import com.szborqs.mybook.main.library.model.ChapterItem;
import com.szborqs.mybook.main.library.model.OnlineBookItem;
import com.szborqs.mybook.nohttp.CallServer;
import com.szborqs.mybook.nohttp.HttpListener;
import com.szborqs.mybook.util.BookLog;
import com.szborqs.mybook.util.SharedMethod;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookIndexListActivity extends BaseActivity {
    private PullDownLoadListView mListview;
    private BookIndexListAdapter mAdapter;
    private List<BaseItem> bookIndexList;
    private static final int GET_LIST_DATA = 100;
    private String bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_index_list);
        initView();
    }

    private void initView() {
        bookId=getIntent().getStringExtra("bookId");
        mListview=(PullDownLoadListView)findViewById(R.id.mListview);
        bookIndexList=new ArrayList<BaseItem>();
        mAdapter=new BookIndexListAdapter(mActivity);
        mAdapter.setList(bookIndexList);
        mListview.setAdapter(mAdapter);
        mListview.setonTopRefreshListener(new PullDownLoadListView.OnTopRefreshListener() {
            @Override
            public void onRefresh() {
                getListData();
            }
        });
        getListData();
    }

    private void getListData() {
        if(SharedMethod.isEmptyString(bookId)){
            return;
        }
        String url = ActionConfigs.GET_CHAPTER_LIST+bookId+"?p=1";
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getRequestInstance().add(mActivity, GET_LIST_DATA, request, mHttpListener, true, true);
    }


    private HttpListener<String> mHttpListener = new HttpListener<String>() {

        @Override
        public void onSucceed(int what, Response<String> response) {

            JSONObject jsonObject = null;
            int code = -1;
            try {
                code = response.responseCode();
                BookLog.e("onSucceed what=" + what + " response=" + response.get());
                jsonObject = new JSONObject(response.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mActivity == null || mActivity.isFinishing()) {
                return;
            }
            switch (what) {
                case GET_LIST_DATA://获取列表数据

                    if(code!=404 && jsonObject!=null){
                        bookIndexList.clear();
                        Iterator<String> keys=jsonObject.keys();
                        int index=0;
                        while(keys.hasNext()){
                            String key=keys.next();
                            try{
                                JSONObject subObject = jsonObject.getJSONObject(key);
                                ChapterItem item=new ChapterItem(subObject);
                                item.setIndex(index);
                                bookIndexList.add(item);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            index++;
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    mListview.onRefreshComplete();
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
                    mListview.onRefreshComplete();
                    break;

            }
        }


    };


}
