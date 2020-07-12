package com.hawkeye.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -9202109574544652243L;
    private long total;       //总记录数
    private List list;    //结果集
    private int pageNum;     // 第几页
    private int pageSize;    // 每页记录数
    private int pages;       // 总页数


    public PageBean(T data) {
        if (data instanceof PageInfo) {
            PageInfo<T> page = (PageInfo<T>) data;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page.getList();
        }
    }

}