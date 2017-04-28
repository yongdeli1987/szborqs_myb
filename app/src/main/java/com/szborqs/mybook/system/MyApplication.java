package com.szborqs.mybook.system;

import android.app.Application;
import android.content.pm.PackageInfo;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        NoHttp.initialize(this);
        Logger.setDebug(true);
    }

    public static MyApplication getInstance(){
        return instance;
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public String getVersionName() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(
                    getPackageName(), 0);
            return pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "01.00.01";
        }
    }
}
