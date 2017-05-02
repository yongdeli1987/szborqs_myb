package com.szborqs.mybook.main.shelf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.szborqs.mybook.R;
import com.szborqs.mybook.main.library.model.MyBookItem;
import com.szborqs.mybook.util.UniversalImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public  class BookShelfListAdater extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<MyBookItem> mList;
    protected Context mContext;
    private UniversalImageLoader mUniversalImageLoader;

    public BookShelfListAdater(Context mContext,List<MyBookItem> mList) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList=mList;
        mUniversalImageLoader=new UniversalImageLoader(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (convertView == null || mViewHolder == null) {
            convertView = mInflater.inflate(R.layout.book_shelf_list_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.firstImg = (ImageView) convertView.findViewById(R.id.firstImg);
            mViewHolder.secondImg = (ImageView) convertView.findViewById(R.id.secondImg);
            mViewHolder.thirdImg = (ImageView) convertView.findViewById(R.id.thirdImg);
            convertView.setTag(mViewHolder);
        }
        int firstIndex = position*3;
        int sencondIndex = firstIndex+1;
        int thirdIndex = firstIndex+2;
        if(firstIndex>=mList.size()){
            mViewHolder.firstImg.setVisibility(View.INVISIBLE);
        }else{
            mViewHolder.firstImg.setVisibility(View.VISIBLE);
            MyBookItem item=mList.get(firstIndex);
            mUniversalImageLoader.imgLoaderNoRepeat(item.getBookCover(),mViewHolder.firstImg,R.mipmap.ic_launcher,true);
        }
        if(sencondIndex>=mList.size()){
            mViewHolder.secondImg.setVisibility(View.INVISIBLE);
        }else{
            mViewHolder.secondImg.setVisibility(View.VISIBLE);
            MyBookItem item=mList.get(sencondIndex);
            mUniversalImageLoader.imgLoaderNoRepeat(item.getBookCover(),mViewHolder.secondImg,R.mipmap.ic_launcher,true);
        }
        if(thirdIndex>=mList.size()){
            mViewHolder.thirdImg.setVisibility(View.INVISIBLE);
        }else{
            mViewHolder.thirdImg.setVisibility(View.VISIBLE);
            MyBookItem item=mList.get(thirdIndex);
            mUniversalImageLoader.imgLoaderNoRepeat(item.getBookCover(),mViewHolder.thirdImg,R.mipmap.ic_launcher,true);
        }
        return convertView;
    }

    private static class ViewHolder {
        /**
         * 名字
         */
        private ImageView firstImg;
        /**
         * 名字
         */
        private ImageView secondImg;
        /**
         * 名字
         */
        private ImageView thirdImg;

    }


    @Override
    public int getCount() {
        int bookSize=mList.size();
        int tail=bookSize%3;
        int temp=bookSize/3;
        int realCount=(tail==0)?temp:(temp+1);
        if(realCount<4){
            return 4;
        }
        return realCount;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
