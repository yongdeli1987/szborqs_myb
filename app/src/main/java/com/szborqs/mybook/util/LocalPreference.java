package com.szborqs.mybook.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.szborqs.mybook.system.MyApplication;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/12.
 */

public class LocalPreference {

    private static SharedPreferences mSharedPreferences=null;

    private static final String TEXT_SIZE="text_size";

    private static SharedPreferences getInstance(){
        if(mSharedPreferences==null){
            mSharedPreferences=MyApplication.getInstance().getSharedPreferences("mybook", Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    public static void putTextSize(int param){
        getInstance().edit().putInt(TEXT_SIZE,param).commit();
    }
    public static int getTextSize(){
        return getInstance().getInt(TEXT_SIZE,-1);
    }

}
