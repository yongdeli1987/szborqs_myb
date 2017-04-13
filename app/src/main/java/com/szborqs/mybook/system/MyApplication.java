package com.szborqs.mybook.system;

import android.app.Application;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
