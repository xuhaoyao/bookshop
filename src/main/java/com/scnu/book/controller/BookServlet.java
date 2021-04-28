package com.scnu.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.book.service.BookService;
import com.scnu.book.service.impl.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oper = req.getParameter("oper");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if("addBook".equals(oper))
            addBookServlet(req,resp);
        else if("findBooks".equals(oper))
            findBooksServlet(req,resp);
        else if("bookUpdate".equals(oper))
            updateBookServlet(req,resp);
        else if("bookDelete".equals(oper))
            deleteBookServlet(req,resp);
    }

    private String getImgURL(String bookImg){
        StringBuffer url = new StringBuffer();
        int index = bookImg.lastIndexOf("\\");
        for(int i = index + 1;i < bookImg.length();i++){
            url.append(bookImg.charAt(i));
        }
        return ("bookImages/" + url.toString());
    }

    private void addBookServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String bookName,author,press,publishDate,isbn,bookInfo,bookImg;
        BigDecimal price = null;
        bookName = req.getParameter("bookName");
        author = req.getParameter("author");
        press = req.getParameter("press");
        publishDate = req.getParameter("publishDate");
        isbn = req.getParameter("isbn");
        bookInfo = req.getParameter("bookInfo");
        bookImg = getImgURL(req.getParameter("bookImg"));
        price = new BigDecimal(req.getParameter("price"));
        Book book = new Book(null,bookName,author,press,publishDate,isbn,bookInfo,bookImg,price);
        boolean flag = bookService.addBookService(book);
        PrintWriter ps = resp.getWriter();
        String msg = null;
        if(flag == true)
            msg = "录入成功!";
        else
            msg = "该图书已录入";
        ps.write(msg);
    }

    private int getPc(HttpServletRequest req){
        int pc = 1;
        String parm = req.getParameter("pc");
        if(parm != null && !parm.trim().isEmpty())
            pc = Integer.valueOf(parm);
        return pc;
    }

    private String getURL(HttpServletRequest req){
        String url = req.getRequestURL() + "?" + req.getQueryString();
        int index = url.lastIndexOf("&pc=");
        if(index != -1)
            url = url.substring(0,index);
        return url;
    }

    private void findBooksServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int pc = getPc(req);
        String url = getURL(req);
        PageBean<Book> pageBean = bookService.findByPage(pc);
        pageBean.setUrl(url);
        req.setAttribute("pb",pageBean);
        if("网上购书小站管理系统".equals(req.getSession(false).getAttribute("top_Title")))
            req.getRequestDispatcher("/jsps/admin/message.jsp").forward(req,resp);
        else
            req.getRequestDispatcher("/jsps/vip/message.jsp").forward(req,resp);
    }

    private void updateBookServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int bookid = Integer.valueOf(req.getParameter("bookid"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String bookInfo = req.getParameter("bookInfo");
        boolean flag = bookService.updateBookService(bookid,price,bookInfo);
        PrintWriter ps = resp.getWriter();
        String msg = null;
        if(flag == true)
            msg = "修改成功!";
        else
            msg = "系统出错,修改失败!";
        ps.write(msg);
    }

    private void deleteBookServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int bookid = Integer.valueOf(req.getParameter("bookid"));
        boolean flag = bookService.deleteBookService(bookid);
        PrintWriter ps = resp.getWriter();
        if(flag == false)
            ps.write("删除失败!");
        else {
            ps.write("删除成功!");
        }
    }
}
