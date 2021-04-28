package com.scnu.book.service;

import com.scnu.book.dao.BookDao;
import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;

import java.math.BigDecimal;

public interface BookService {
    boolean addBookService(Book book);

    public PageBean<Book> findByPage(int pc);

    boolean updateBookService(int bookid, BigDecimal price, String bookInfo);

    boolean deleteBookService(int bookid);

    Book findBook(int bookid);
}
