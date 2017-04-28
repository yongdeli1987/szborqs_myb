package com.szborqs.mybook.main.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szborqs.mybook.BaseListAdater;
import com.szborqs.mybook.R;
import com.szborqs.mybook.main.library.model.BookTypeItem;


/**
 * Created by Administrator on 2016/10/26.
 */

public class BookTypeListAdapter extends BaseListAdater {

    public BookTypeListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        final BookTypeItem typeItem = (BookTypeItem) list.get(position);
        if (convertView != null) {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        if (convertView == null || mViewHolder == null) {
            convertView = mInflater.inflate(R.layout.book_type_list_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.nameText = (TextView) convertView.findViewById(R.id.nameText);
            convertView.setTag(mViewHolder);
        }
        mViewHolder.nameText.setText(typeItem.getName());
        return convertView;
    }

    private static class ViewHolder {
        /**
         * 名字
         */
        private TextView nameText;
    }

}
