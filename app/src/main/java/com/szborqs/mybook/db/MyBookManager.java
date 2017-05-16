package com.szborqs.mybook.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.szborqs.mybook.main.library.model.ChapterItem;
import com.szborqs.mybook.main.library.model.MyBookItem;
import com.szborqs.mybook.util.BookLog;
import com.szborqs.mybook.util.SharedMethod;

import java.util.List;


/**
 * description
 *
 * @Author Administrator
 * @Time 2017/1/16 15:46
 */

public class MyBookManager {
    private static MyBookManager myBookManager = null;
    private static DBManager manager = null;

    private MyBookManager(Context context) {
        manager = DBManager.getInstance(context);
    }

    public static MyBookManager getInstance(Context context) {
        if (myBookManager == null) {
            myBookManager = new MyBookManager(context);
        }
        return myBookManager;
    }

    public long saveBookItem(MyBookItem item){
        long influencesRows = -1;
        String bookId=item.getBookId();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_id", bookId);
        contentValues.put("book_name", item.getBookName());
        contentValues.put("book_author", item.getBookAuthor());
        contentValues.put("book_cover", item.getBookCover());
        contentValues.put("create_time", item.getCreateTime());
        contentValues.put("cur_chapter_id", item.getCurChapterId());
        contentValues.put("position", item.getCurChapterPosition());
        contentValues.put("is_import", item.isImport()?"1":"0");
        boolean tag=isBookExist(bookId);
        BookLog.e(bookId+" isBookExist:"+tag);
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        if(tag){
            influencesRows=st.update("book_info",contentValues,"book_id = ?",new String[]{bookId});
        }else{
            influencesRows=st.insert("book_info",contentValues);
        }
        return influencesRows;
    }

    public long saveChapterItem(ChapterItem item){
        long influencesRows = -1;
        String bookId=item.getBookId();
        String chapterId=item.getChapterId();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_id", item.getBookId());
        contentValues.put("chapter_id", chapterId);
        contentValues.put("chapter_name", item.getChapterName());
        contentValues.put("chapter_index", item.getIndex());
        contentValues.put("chapter_size", item.getSize());
        boolean tag=isChapterExist(item);
        BookLog.e(chapterId+" isChapterExist:"+tag);
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        if(tag){
            influencesRows=st.update("chapter_info",contentValues,"book_id = ? and chapter_id = ?",new String[]{bookId,chapterId});
        }else{
            influencesRows=st.insert("chapter_info",contentValues);
        }
        return influencesRows;
    }


    public boolean isBookExist(String bookId){
        if(SharedMethod.isEmptyString(bookId)){
            return false;
        }
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        return st.isExistsByField("book_info","book_id",bookId);
    }

    public boolean isChapterExist(ChapterItem item){
        if(item==null){
            return false;
        }
        String bookId=item.getBookId();
        String chapterId=item.getChapterId();
        String sql="SELECT COUNT(*) FROM chapter_info WHERE book_id = ? and chapter_id = ?";
        String[] args=new String[]{bookId,chapterId};
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        int count= st.getCount(sql,args);
        return count>0;
    }

    public List<MyBookItem> getAllBookList(){
        List<MyBookItem> result=null;
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        SQLiteTemplate.RowMapper<MyBookItem> rowMapper=new SQLiteTemplate.RowMapper<MyBookItem>() {
            @Override
            public MyBookItem mapRow(Cursor cursor, int index) {
                MyBookItem item=new MyBookItem();
                item.setBookId(cursor.getString(cursor.getColumnIndex("book_id")));
                item.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
                item.setBookName(cursor.getString(cursor.getColumnIndex("book_name")));
                item.setImport(cursor.getString(cursor.getColumnIndex("is_import")).equals("1")?true:false);
                item.setBookCover(cursor.getString(cursor.getColumnIndex("book_cover")));
                item.setBookAuthor(cursor.getString(cursor.getColumnIndex("book_author")));
                item.setCurChapterId(cursor.getString(cursor.getColumnIndex("cur_chapter_id")));
                item.setCurChapterPosition(cursor.getInt(cursor.getColumnIndex("position")));
                return item;
            }
        };
        String sql="select book_id,book_name,create_time,book_cover,book_author,cur_chapter_id,position,is_import from book_info order by create_time desc";
        result=st.queryForList(rowMapper,sql,null);
        return result;
    }



    /*public void updateMsgStatusById(String msgId,int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put("msg_status", status);
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        st.update("im_msg_info",contentValues,"msg_id=?",new String[]{msgId});
    }

    public void delMsgById(String msgId){
        SQLiteTemplate st=SQLiteTemplate.getInstance(manager);
        st.deleteByField("im_msg_info","msg_id",msgId);
    }*/



}
