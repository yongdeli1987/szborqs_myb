package com.szborqs.mybook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * liyongde
 *
 * @Author Administrator
 * @Time 2017/1/16 14:38
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int version = 1; //数据库版本

    public SQLiteHelper(Context context,String name){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 文件信息
        db.execSQL("CREATE TABLE alarm_info  (_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, content NVARCHAR,create_time VARCHAR, receive_time VARCHAR, type INTEGER, status INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
