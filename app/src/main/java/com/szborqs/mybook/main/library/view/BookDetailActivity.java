package com.szborqs.mybook.main.library.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szborqs.mybook.BaseActivity;
import com.szborqs.mybook.BaseItem;
import com.szborqs.mybook.R;
import com.szborqs.mybook.config.ActionConfigs;
import com.szborqs.mybook.custom.RefreshListView;
import com.szborqs.mybook.main.library.adapter.OnlineBookListAdapter;
import com.szborqs.mybook.main.library.model.OnlineBookItem;
import com.szborqs.mybook.nohttp.CallServer;
import com.szborqs.mybook.nohttp.HttpListener;
import com.szborqs.mybook.util.BookLog;
import com.szborqs.mybook.util.SharedMethod;
import com.szborqs.mybook.util.UniversalImageLoader;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDetailActivity extends BaseActivity {


    private OnlineBookItem onlineBookItem;

    private UniversalImageLoader mImageLoader=null;
    private ImageView headImg;
    private TextView bookNameText;
    private TextView authorText;
    private TextView statusText;
    private TextView lastChapterText;
    private TextView descriptionText;
    private TextView chapterListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initView();
    }

    private void initView() {
        onlineBookItem=(OnlineBookItem)getIntent().getSerializableExtra("bookItem");
        if(onlineBookItem==null){
            this.finish();
        }else{
            mImageLoader=new UniversalImageLoader(mActivity);
            headImg=(ImageView)findViewById(R.id.headImg);
            bookNameText=(TextView)findViewById(R.id.bookNameText);
            authorText=(TextView)findViewById(R.id.authorText);
            statusText=(TextView)findViewById(R.id.statusText);
            lastChapterText=(TextView)findViewById(R.id.lastChapterText);
            descriptionText=(TextView)findViewById(R.id.descriptionText);
            chapterListText=(TextView)findViewById(R.id.chapterListText);

            mImageLoader.imgLoaderNoRepeat(onlineBookItem.getPicUrl(),headImg,R.mipmap.ic_launcher,true);
            bookNameText.setText(onlineBookItem.getName());
            authorText.setText(onlineBookItem.getAuthor());
            statusText.setText(onlineBookItem.getStatus());
            lastChapterText.setText(onlineBookItem.getLastChapterName());
            descriptionText.setText(onlineBookItem.getDescription());

            chapterListText.setOnClickListener(mOnClickListener);
        }


    }


    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.chapterListText:
                    Intent i=new Intent();
                    i.setClass(mActivity,BookIndexListActivity.class);
                    i.putExtra("bookItem",onlineBookItem);
                    startActivity(i);
                    break;
            }
        }
    };






}
