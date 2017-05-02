package com.szborqs.mybook.main.library.model;

import android.content.Context;

import com.szborqs.mybook.BaseItem;
import com.szborqs.mybook.db.MyBookManager;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * description
 *
 * @Author Administrator
 * @Time 2017/4/28 18:55
 */

public class OnlineBookItem implements BaseItem,Serializable {

    private String author;
    private String name;
    private String description;
    private String picUrl;
    private String bookfinger;
    private String type;
    private String lastChapterName;
    private String status;

    public OnlineBookItem(){

    }

    public OnlineBookItem(JSONObject object){
        if(object!=null){
            author=object.optString("author");
            name=object.optString("bookname");
            description=object.optString("intro");
            picUrl=object.optString("picpath");
            bookfinger=object.optString("bookfinger");
            type=object.optString("booktype");
            lastChapterName=object.optString("chapternew");
            status=object.optString("status");
        }

    }

    public void insertIntoDatabase(Context c){
        try{
            MyBookItem item=new MyBookItem();
            item.setBookId(bookfinger);
            item.setBookAuthor(author);
            item.setBookCover(picUrl);
            item.setBookName(name);
            item.setImport(false);
            item.setCreateTime(""+System.currentTimeMillis());
            MyBookManager.getInstance(c).saveBookItem(item);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookfinger() {
        return bookfinger;
    }

    public void setBookfinger(String bookfinger) {
        this.bookfinger = bookfinger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastChapterName() {
        return lastChapterName;
    }

    public void setLastChapterName(String lastChapterName) {
        this.lastChapterName = lastChapterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
