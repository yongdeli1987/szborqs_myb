package com.szborqs.mybook.main.library.view;

import android.os.Bundle;

import com.szborqs.mybook.BaseActivity;
import com.szborqs.mybook.BaseItem;
import com.szborqs.mybook.R;
import com.szborqs.mybook.config.ActionConfigs;
import com.szborqs.mybook.custom.RefreshListView;
import com.szborqs.mybook.main.library.adapter.OnlineBookListAdapter;
import com.szborqs.mybook.main.library.model.BookTypeItem;
import com.szborqs.mybook.main.library.model.OnlineBookItem;
import com.szborqs.mybook.nohttp.CallServer;
import com.szborqs.mybook.nohttp.HttpListener;
import com.szborqs.mybook.util.BookLog;
import com.szborqs.mybook.util.LocalPreference;
import com.szborqs.mybook.util.SharedMethod;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookListActivity extends BaseActivity {
    private RefreshListView mListView;
    private int pageNum = 1;
    private String type;
    private OnlineBookListAdapter mAdapter;
    private List<BaseItem> bookList;
    private static final int GET_LIST_DATA = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        initView();
    }

    private void initView() {
        type=getIntent().getStringExtra("bookType");
        mListView=(RefreshListView)findViewById(R.id.mListview);
        bookList=new ArrayList<BaseItem>();
        mAdapter=new OnlineBookListAdapter(mActivity);
        mAdapter.setList(bookList);
        mListView.setAdapter(mAdapter);
        mListView.setonTopRefreshListener(new RefreshListView.OnTopRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum=1;
                getListData();
            }
        });
        mListView.setonBottomRefreshListener(new RefreshListView.OnBottomRefreshListener() {
            @Override
            public void onRefresh() {
                getListData();
            }
        });
        getListData();
    }

    private void getListData() {
        String url=getDownloadUrl();
        if(SharedMethod.isEmptyString(url)){
            return;
        }
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getRequestInstance().add(mActivity, GET_LIST_DATA, request, mHttpListener, true, true);
    }

    private String getDownloadUrl(){
        if(SharedMethod.isEmptyString(type)){
            return null;
        }
        String url= ActionConfigs.GET_BOOK_LIST+type+"?p="+pageNum;
        return url;
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
                        if(pageNum==1){
                            bookList.clear();
                        }
                        Iterator<String> keys=jsonObject.keys();
                        while(keys.hasNext()){
                            String key=keys.next();
                            try{
                                JSONObject subObject=new JSONObject(jsonObject.optString(key));
                                OnlineBookItem item=new OnlineBookItem(subObject);
                                bookList.add(item);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                    pageNum++;
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
                    mListView.onRefreshComplete();
                    break;

            }
        }


    };


}
