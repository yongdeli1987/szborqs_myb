package com.szborqs.mybook.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @Author Administrator
 * @Time 2017/1/16 16:28
 */

public class SQLiteTemplate {
    /**
     * DBManager
     */
    private DBManager dBManager;

    /**
     * 数据库连接
     */
    private SQLiteDatabase dataBase = null;

    private SQLiteTemplate() {
    }
    private SQLiteTemplate(DBManager dBManager) {
        this.dBManager = dBManager;
    }
    public static SQLiteTemplate getInstance(DBManager dBManager) {
        return new SQLiteTemplate(dBManager);
    }

    /**
     * 执行一条sql语句
     */
    public void execSQL(String sql, Object[] bindArgs) {
        try {
            dataBase = dBManager.getDatabase();
            dataBase.execSQL(sql, bindArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
    }
    /**
     * 向数据库表中插入一条数据
     *
     * @param table  表名
     * @param content 字段值
     */
    public long insert(String table, ContentValues content) {
        try {
            dataBase = dBManager.getDatabase();
            return dataBase.insert(table, null, content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
        return 0;
    }
    /**
     * 根据某一个字段和值删除一行数据, 如 name="jack"
     *
     * @param table
     * @param field
     * @param value
     * @return 返回值大于0表示删除成功
     */
    public int deleteByField(String table, String field, String value) {
        try {
            dataBase = dBManager.getDatabase();
            return dataBase.delete(table, field + "=?", new String[] { value });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
        return 0;
    }

    /**
     * 根据主键删除一行数据
     *
     * @param table
     * @param id
     * @return 返回值大于0表示删除成功
     */
    public int deleteById(String table, String id) {
        try {
            dataBase = dBManager.getDatabase();
            return deleteByField(table, "_id", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
        return 0;
    }

    /**
     * 根据主键更新一行数据
     *
     * @param table
     * @param id
     * @param values
     * @return 返回值大于0表示更新成功
     */
    public int updateById(String table, String id, ContentValues values) {
        try {
            dataBase = dBManager.getDatabase();
            return dataBase.update(table, values,  "_id=?",
                    new String[] { id });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
        return 0;
    }

    /**
     * 更新数据
     *
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return 返回值大于0表示更新成功
     */
    public int update(String table, ContentValues values, String whereClause,
                      String[] whereArgs) {
        try {
            dataBase = dBManager.getDatabase();
            return dataBase.update(table, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null);
        }
        return 0;
    }

    /**
     * 根据某字段/值查看某条数据是否存在
     * @return
     */
    public Boolean isExistsByField(String table, String field, String value) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ")
                .append(field).append(" =?");
        int count = getCount(sql.toString(), new String[] {value});
        return count>0;
    }

    /**
     * 获取记录数
     *
     * @return
     */
    public Integer getCount(String sql, String[] args) {
        Cursor cursor = null;
        try {
            dataBase = dBManager.getDatabase();
            cursor = dataBase.rawQuery(sql,args);
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(cursor);
        }
        return 0;
    }


    public <T> List<T> queryForList(RowMapper<T> rowMapper, String sql, String[] selectionArgs) {
        List<T> list = null;
        Cursor cursor = null;
        try {
            dataBase = dBManager.getDatabase();
            cursor = dataBase.rawQuery(sql,selectionArgs);
            list = new ArrayList<T>();
            while (cursor.moveToNext()) {
                list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
            }
        } finally {
            close(cursor);
        }
        return list;
    }

    private void close(Cursor cursor){
        if(dataBase!=null){
            dataBase.close();
        }
        if(cursor!=null){
            cursor.close();
        }
    }

    /**
     * @param <T>
     */
    public interface RowMapper<T> {
        /**
         * @param cursor
         *            游标
         * @param index
         *            下标索引
         * @return
         */
        public T mapRow(Cursor cursor, int index);
    }

}
