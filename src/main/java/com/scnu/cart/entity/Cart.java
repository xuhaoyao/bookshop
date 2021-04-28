package com.scnu.cart.entity;

import com.scnu.book.entity.Book;

import java.util.List;

public class Cart {
    private Integer vipid;
    private String buildDate,lastDate;
    private List<Book> bookList;

    public Cart() {
    }

    public Cart(Integer vipid, String buildDate, String lastDate, List<Book> bookList) {
        this.vipid = vipid;
        this.buildDate = buildDate;
        this.lastDate = lastDate;
        this.bookList = bookList;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
