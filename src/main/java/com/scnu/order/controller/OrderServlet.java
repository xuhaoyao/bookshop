package com.scnu.order.controller;

import com.scnu.book.entity.Book;
import com.scnu.book.entity.PageBean;
import com.scnu.order.entity.Orders;
import com.scnu.order.service.OrderService;
import com.scnu.order.service.impl.OrderServiceImpl;
import com.scnu.vip.entity.Vip;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String oper = req.getParameter("oper");
        if("addToCart".equals(oper))
            addNewOrderFromCartServlet(req,resp);
        else if("showBuyBookInfo".equals(oper))
            showBuyBookInfoServlet(req,resp);
        else if("buyBook".equals(oper))
            buyBookServlet(req,resp);
        else if("findOrders".equals(oper))
            findOrdersServlet(req, resp);
        else if("findMyBuyBooks".equals(oper))
            findMyBuyBooksServlet(req,resp);
        else if("vipDelBookRecord".equals(oper))
            vipDelBookRecordServlet(req,resp);
    }

    private void addNewOrderFromCartServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Long Date = Long.valueOf(req.getParameter("Date"));
        Book book = orderService.findBook(Integer.valueOf(req.getParameter("bookid")));
        Integer vipid = (Integer)req.getSession(false).getAttribute("vipId");
        boolean flag = orderService.addNewOrder_fromCart(book,vipid,Date);
        PrintWriter pw = resp.getWriter();
        if(flag == true)
            pw.write("加入购物车成功!");
    }

    private void showBuyBookInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Integer bookid = Integer.valueOf(req.getParameter("bookid"));
        Book book = orderService.findBook(bookid);
        Vip vip = orderService.findVip((Integer)req.getSession(false).getAttribute("vipId"));
        req.setAttribute("vipInfo",vip);
        req.setAttribute("book",book);
        req.setAttribute("comeFrom",req.getParameter("comeFrom"));
        req.getRequestDispatcher("/jsps/vip/showBuyBookInfo.jsp").forward(req,resp);
    }

    private void buyBookServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Integer bookid = Integer.valueOf(req.getParameter("bookid"));
        Integer vipid = (Integer)req.getSession(false).getAttribute("vipId");
        Long Date = Long.valueOf(req.getParameter("Date"));
        String comeFrom = req.getParameter("comeFrom");
        boolean flag = false;
        String msg = null;
        //根据该订单从何处来(商店or购物车)从而对订单进行不同的处理(新建订单或者更新订单(购物车))
        if("shop".equals(comeFrom)) {
            Book book = orderService.findBook(bookid);
            flag = orderService.addNewOrder_fromShop(book, vipid, Date);
        }
        else
            flag = orderService.updateOrder_fromCart(vipid,bookid,Date);
        if(flag){
            msg = "订单提交成功!";
            //此时会员的收获信息可能会发生变化,但直接更新收货地址和联系电话
            Vip vip = orderService.findVip(vipid);
            vip.setAddress(req.getParameter("address"));
            vip.setPhone(req.getParameter("phone"));
            orderService.updateVipInfo(vip);
        }
        else
            msg = "系统异常,订单提交失败!";
        PrintWriter pw = resp.getWriter();
        pw.write(msg);
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

    private void findOrdersServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        PageBean<Orders> pageBean = orderService.findByPage(getPc(req));
        pageBean.setUrl(getURL(req));
        req.setAttribute("pb",pageBean);
        req.getRequestDispatcher("/jsps/admin/ordersInfo.jsp").forward(req,resp);
    }

    private void findMyBuyBooksServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        PageBean<Book> pageBean = orderService.findByPage(getPc(req),
                (Integer)req.getSession(false).getAttribute("vipId"));
        pageBean.setUrl(getURL(req));
        req.setAttribute("pb",pageBean);
        req.getRequestDispatcher("/jsps/vip/myBuyBooksInfo.jsp").forward(req,resp);
    }

    private void vipDelBookRecordServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Integer vipid = (Integer)req.getSession(false).getAttribute("vipId");
        Integer bookid = Integer.valueOf(req.getParameter("bookid"));
        String buyDate = req.getParameter("buyDate");
        if(orderService.vipDelBookRecord(vipid,bookid,buyDate))
            findMyBuyBooksServlet(req,resp);
    }
}
