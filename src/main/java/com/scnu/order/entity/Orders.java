package com.scnu.order.entity;

import java.math.BigDecimal;

public class Orders {
    private Integer ordersid,vipid,bookid;
    private String bookName;
    private BigDecimal price;
    private String ischeckout,startDate,endDate;

    public Orders() {
    }

    public Orders(Integer ordersid, Integer vipid, Integer bookid, String bookName, BigDecimal price, String ischeckout, String startDate, String endDate) {
        this.ordersid = ordersid;
        this.vipid = vipid;
        this.bookid = bookid;
        this.bookName = bookName;
        this.price = price;
        this.ischeckout = ischeckout;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(Integer ordersid) {
        this.ordersid = ordersid;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIscheckout() {
        return ischeckout;
    }

    public void setIscheckout(String ischeckout) {
        this.ischeckout = ischeckout;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
