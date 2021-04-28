package com.scnu.order.service.impl;

import com.scnu.book.dao.BookDao;
import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.order.dao.OrderDao;
import com.scnu.order.entity.Orders;
import com.scnu.order.service.OrderService;
import com.scnu.vip.dao.VipDao;
import com.scnu.vip.entity.Vip;

public class OrderServiceImpl implements OrderService {
    private BookDao bookDao = new BookDao();
    private OrderDao orderDao = new OrderDao();
    private VipDao vipDao = new VipDao();
    @Override
    public Book findBook(int bookid) {
        return bookDao.findBook(bookid);
    }

    @Override
    public boolean addNewOrder_fromCart(Book book, Integer vipid, Long date) {
        return orderDao.addNewOrder_fromCart(book,vipid,date);
    }

    @Override
    public Vip findVip(int vipid) {
        return vipDao.QueryVipInfo(vipid);
    }

    @Override
    public boolean addNewOrder_fromShop(Book book, Integer vipid, Long date) {
        return orderDao.addNewOrder_fromShop(book,vipid,date);
    }

    @Override
    public boolean updateVipInfo(Vip vip) {
        return vipDao.updateVipInfo(vip);
    }

    @Override
    public boolean updateOrder_fromCart(Integer vipid, Integer bookid, Long date) {
        return orderDao.updateOrder_fromCart(vipid,bookid,date);
    }

    @Override
    public PageBean<Orders> findByPage(int pc) {
        return orderDao.findByPage(pc);
    }

    @Override
    public PageBean<Book> findByPage(int pc, int vipid) {
        return orderDao.findByPage(pc,vipid);
    }

    @Override
    public boolean vipDelBookRecord(int vipid, int bookid, String buyDate) {
        return orderDao.vipDelBookRecord(vipid,bookid,buyDate);
    }
}
