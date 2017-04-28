package com.szborqs.mybook;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public abstract class BaseListAdater extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<BaseItem> list;
    protected Context mContext;

    public BaseListAdater(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<BaseItem> getList() {
        return list;
    }

    public void setList(List<BaseItem> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null && list.size() > 0 && position < list.size()
                && position > -1) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
