/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szborqs.mybook.nohttp;

import android.content.Context;

import com.szborqs.mybook.custom.CustomLoadingDialog;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * Created in Nov 4, 2015 12:02:55 PM.
 *
 * @author Yan Zhenjie.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {

    private Context mContext;
    /**
     * Dialog.
     */
    private CustomLoadingDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;


    /**
     * @param activity     context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Context activity, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.mContext = activity;
        this.mRequest = request;
        if (activity != null && isLoading) {
            mWaitDialog = CustomLoadingDialog.createDialog(activity);
        }
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (mContext != null && mWaitDialog != null && !mWaitDialog.isShowing())
            mWaitDialog.show();
    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (mContext != null && mWaitDialog != null && mWaitDialog.isShowing())
            mWaitDialog.dismiss();
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        int responseCode = response.getHeaders().getResponseCode();
        /*if (responseCode == 401) {
            if(!SharedMethod.isEmptyString(UserPrefs.getUserName()) && !SharedMethod.isEmptyString(UserPrefs.getUserPassword())){
                ReloginTask task = new ReloginTask(mContext);
                task.execute();
            }

        }*/

        if (callback != null) {
            callback.onSucceed(what, response);
        }
    }


    /**
     * 失败回调.
     */

    @Override
    public void onFailed(int i, Response<T> response) {
        if (callback != null) {
            callback.onFailed(i, response);
        }
    }
}
