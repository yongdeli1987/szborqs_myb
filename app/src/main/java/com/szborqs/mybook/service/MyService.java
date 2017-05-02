package com.szborqs.mybook.service;

import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.szborqs.mybook.nohttp.CallServer;
import com.szborqs.mybook.util.BookLog;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;

import javax.net.ssl.SSLContext;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangedReceiver, mFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        unregisterReceiver(networkChangedReceiver);
        super.onDestroy();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /*int code = msg.what;
            if (code >= START_ID) {
                String content = (String) msg.obj;
                if (content != null && !content.equals("")) {
                    showNotification(content, code);
                }
            }*/
            super.handleMessage(msg);
        }
    };

    BroadcastReceiver networkChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BookLog.e("onReceive action:"+action);
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            }
        }
    };


    private void downloadNewApk() {
        String fileDir = "";
        String fileName = "";
        String url = "";
        DownloadRequest mDownloadRequest = NoHttp.createDownloadRequest(url, fileDir, fileName, true, false);
        CallServer.getDownloadInstance().add(0, mDownloadRequest, downloadListener);
    }
    /**
     * 下载监听
     */
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadError(int i, Exception e) {

        }

        @Override
        public void onStart(int i, boolean b, long l, Headers headers, long l1) {

        }

        @Override
        public void onProgress(int i, int progress, long curSize) {
        }

        @Override
        public void onFinish(int i, String path) {

        }

        @Override
        public void onCancel(int i) {

        }
    };


}
