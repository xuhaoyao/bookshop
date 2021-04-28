package com.scnu.order.service;

import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.order.entity.Orders;
import com.scnu.vip.entity.Vip;

public interface OrderService {
    Book findBook(int bookid);

    boolean addNewOrder_fromCart(Book book,Integer vipid,Long date);

    boolean addNewOrder_fromShop(Book book,Integer vipid,Long date);

    Vip findVip(int vipid);

    boolean updateVipInfo(Vip vip);

    boolean updateOrder_fromCart(Integer vipid,Integer bookid,Long date);

    PageBean<Orders> findByPage(int pc);

    PageBean<Book> findByPage(int pc,int vipid);

    boolean vipDelBookRecord(int vipid,int bookid,String buyDate);
}
