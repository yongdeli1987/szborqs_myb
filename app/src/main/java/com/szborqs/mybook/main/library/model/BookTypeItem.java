package com.szborqs.mybook.main.library.model;

import com.szborqs.mybook.BaseItem;

/**
 * description
 *
 * @Author Administrator
 * @Time 2017/4/28 18:55
 */

public class BookTypeItem implements BaseItem {

    private String code;
    private String name;

    public BookTypeItem(){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
