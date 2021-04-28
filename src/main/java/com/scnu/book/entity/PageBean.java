package com.scnu.book.entity;

import java.util.List;

public class PageBean<T> {
    private int pc; //当前页面的页码数
    private int ps; //当前页面的记录数 自己定义
    private int all; //数据库总记录数
    private String url;//页面的路径
    private List<T> beanList;//存放分页页面的数据

    public int getTp(){  //计算页面总页数
        int tp = all / ps;
        return all % ps == 0 ? tp : tp + 1;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }
}
