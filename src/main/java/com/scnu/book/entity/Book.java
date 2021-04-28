package com.scnu.book.entity;

import java.math.BigDecimal;

public class Book {
    private Integer bookid;
    private String bookName,author,press,publishDate,isbn,bookInfo,bookImg;
    private BigDecimal price;
    private String buyDate;  //用户买这本书的时间

    public Book() {

    }

    public Book(Integer bookid, String bookName, String author, String press, String publishDate, String isbn, String bookInfo, String bookImg, BigDecimal price) {
        this.bookid = bookid;
        this.bookName = bookName;
        this.author = author;
        this.press = press;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.bookInfo = bookInfo;
        this.bookImg = bookImg;
        this.price = price;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", isbn='" + isbn + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                ", bookImg='" + bookImg + '\'' +
                ", price=" + price +
                '}';
    }
}
