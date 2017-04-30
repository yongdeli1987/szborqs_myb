package com.szborqs.mybook.main.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szborqs.mybook.BaseListAdater;
import com.szborqs.mybook.R;
import com.szborqs.mybook.main.library.model.BookTypeItem;
import com.szborqs.mybook.main.library.model.OnlineBookItem;
import com.szborqs.mybook.util.UniversalImageLoader;


/**
 * Created by Administrator on 2016/10/26.
 */

public class OnlineBookListAdapter extends BaseListAdater {

    UniversalImageLoader mImageLoader;

    public OnlineBookListAdapter(Context context) {
        super(context);
        mImageLoader=new UniversalImageLoader(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        final OnlineBookItem bookItem = (OnlineBookItem) list.get(position);
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (convertView == null || mViewHolder == null) {
            convertView = mInflater.inflate(R.layout.online_book_list_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.headImg = (ImageView) convertView.findViewById(R.id.headImg);
            mViewHolder.bookNameText = (TextView) convertView.findViewById(R.id.bookNameText);
            mViewHolder.authorText = (TextView) convertView.findViewById(R.id.authorText);
            mViewHolder.descriptionText = (TextView) convertView.findViewById(R.id.descriptionText);
            convertView.setTag(mViewHolder);
        }
        mImageLoader.imgLoaderNoRepeat(bookItem.getPicUrl(),mViewHolder.headImg,R.mipmap.ic_launcher,true);
        mViewHolder.bookNameText.setText(bookItem.getName());
        mViewHolder.authorText.setText(bookItem.getAuthor());
        mViewHolder.descriptionText.setText(bookItem.getDescription());
        return convertView;
    }

    private static class ViewHolder {
        /**
         * 名字
         */
        private ImageView headImg;
        /**
         * 名字
         */
        private TextView bookNameText;
        /**
         * 名字
         */
        private TextView authorText;
        /**
         * 名字
         */
        private TextView descriptionText;
    }

}
