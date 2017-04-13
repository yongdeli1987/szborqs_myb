package com.szborqs.mybook.util;

import android.util.Log;

/**
 * description
 *
 * @Author Administrator
 * @Time 15:26.
 */

public class BookLog {
    private static final String TAG = "my_book";
    private static final boolean DEBUG = true;

    public static void i(String content) {
        if (DEBUG) {
            Log.i(TAG, content);
        }
    }

    public static void e(String content) {
        if (DEBUG) {
            Log.e(TAG, content);
        }
    }
}
