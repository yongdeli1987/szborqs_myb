package com.szborqs.mybook.main.library.model;

import com.szborqs.mybook.BaseItem;

import org.json.JSONObject;

/**
 * description
 *
 * @Author Administrator
 * @Time 2017/4/28 18:55
 */

public class ChapterItem implements BaseItem {

    private String bookId;
    private String chapterId;
    private String chapterName;
    private int index;
    private int size;

    public ChapterItem(){

    }

    public ChapterItem(JSONObject object){
        if(object!=null){
            chapterId=object.optString("id");
            chapterName=object.optString("chaptername");
        }

    }


    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
