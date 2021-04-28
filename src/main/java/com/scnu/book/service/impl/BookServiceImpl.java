package com.scnu.book.service.impl;

import com.scnu.book.dao.BookDao;
import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.book.service.BookService;

import java.math.BigDecimal;

public class BookServiceImpl implements BookService {
    private BookDao dao = new BookDao();
    @Override
    public boolean addBookService(Book book) {
        return dao.addBook(book);
    }

    @Override
    public PageBean<Book> findByPage(int pc) {
        return dao.findByPage(pc);
    }

    @Override
    public boolean updateBookService(int bookid, BigDecimal price, String bookInfo) {
        return dao.updateBook(bookid,price,bookInfo);
    }

    @Override
    public boolean deleteBookService(int bookid) {
        return dao.deleteBook(bookid);
    }

    @Override
    public Book findBook(int bookid) {
        return dao.findBook(bookid);
    }
}
