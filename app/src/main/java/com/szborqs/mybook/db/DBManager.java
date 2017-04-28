package com.szborqs.mybook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * description
 *
 * @Author liyongde
 * @Time 2017/1/16 15:18
 */

public class DBManager {

    private Context mContext;
    private static DBManager mDBManager = null;

    private DBManager(Context context) {
        mContext = context;
    }
    public static DBManager getInstance(Context context) {
        if(mDBManager==null){
            mDBManager=new DBManager(context);
        }
        return mDBManager;
    }

    /**
     * 关闭数据库 注意:当事务成功或者一次性操作完毕时候再关闭
     */
    public void closeDatabase(SQLiteDatabase dataBase, Cursor cursor) {
        if (null != dataBase) {
            dataBase.close();
        }
        if (null != cursor) {
            cursor.close();
        }
    }



    public SQLiteDatabase getDatabase() {
        return getDatabaseHelper().getWritableDatabase();
    }

    /**
     * 获取SQLiteHelper
     *
     * @return
     */
    public SQLiteHelper getDatabaseHelper() {
        return new SQLiteHelper(mContext, "mybook");
    }

}
