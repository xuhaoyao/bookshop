package com.scnu.cart.service.impl;

import com.scnu.book.entity.Book;
import com.scnu.cart.dao.CartDao;
import com.scnu.cart.entity.Cart;
import com.scnu.cart.service.CartService;

public class CartServiceImpl implements CartService {
    private CartDao dao = new CartDao();
    @Override
    public Book findBook(int bookid) {
        return dao.findBook(bookid);
    }

    @Override
    public int addToCart(Book book, int vipid, long Date) {
        return dao.addToCart(book,vipid,Date);
    }

    @Override
    public Cart findCartInfo(int vipid) {
        return dao.findCartInfo(vipid);
    }

    @Override
    public boolean deleteCartInfo(int vipid, int bookid) {
        return dao.deleteCartInfo(vipid,bookid);
    }
}
