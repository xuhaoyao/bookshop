package com.scnu.cart.service;

import com.scnu.book.entity.Book;
import com.scnu.cart.entity.Cart;

public interface CartService {
    Book findBook(int bookid);

    int addToCart(Book book,int vipid, long Date);

    Cart findCartInfo(int vipid);

    boolean deleteCartInfo(int vipid,int bookid);
}
