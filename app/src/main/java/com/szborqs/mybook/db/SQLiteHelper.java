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
        db.execSQL("CREATE TABLE book_info  (_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,book_id VARCHAR, book_name NVARCHAR,book_author VARCHAR, book_cover VARCHAR,is_import VARCHAR, cur_chapter_id VARCHAR,position INTEGER,create_time VARCHAR);");
        // 文件信息
        db.execSQL("CREATE TABLE chapter_info  (_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,book_id VARCHAR, chapter_id VARCHAR,chapter_name VARCHAR,chapter_index INTEGER,chapter_size INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
