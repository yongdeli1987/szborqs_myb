package com.szborqs.mybook.main.library.model;

import com.szborqs.mybook.BaseItem;

import org.json.JSONObject;

/**
 * description
 *
 * @Author Administrator
 * @Time 2017/4/28 18:55
 */

public class MyBookItem implements BaseItem {


    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookCover;
    private boolean isImport;
    private String curChapterId;
    private String createTime;
    private int curChapterPosition;

    public MyBookItem(){

    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCurChapterId() {
        return curChapterId;
    }

    public void setCurChapterId(String curChapterId) {
        this.curChapterId = curChapterId;
    }

    public int getCurChapterPosition() {
        return curChapterPosition;
    }

    public void setCurChapterPosition(int curChapterPosition) {
        this.curChapterPosition = curChapterPosition;
    }

    public boolean isImport() {
        return isImport;
    }

    public void setImport(boolean anImport) {
        isImport = anImport;
    }
}
