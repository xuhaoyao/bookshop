package com.scnu.cart.controller;

import com.scnu.book.entity.Book;
import com.scnu.cart.entity.Cart;
import com.scnu.cart.service.CartService;
import com.scnu.cart.service.impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String oper = req.getParameter("oper");
        if("addToCart".equals(oper))
            addToCartServlet(req,resp);
        else if("findCartInfo".equals(oper))
            findCartServlet(req,resp);
        else if("deleteCartInfo".equals(oper))
            deleteCartInfoServlet(req,resp);
    }

    private void addToCartServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Book book = cartService.findBook(Integer.valueOf(req.getParameter("bookid")));
        int flag = cartService.addToCart(book,
                (int)req.getSession(false).getAttribute("vipId"),
                (Long.valueOf(req.getParameter("Date"))));
        PrintWriter pw = resp.getWriter();
        String msg = null;
        if(flag == 1) {
            req.getRequestDispatcher("/orderServlet").forward(req,resp);
        }
        else{
            msg = (flag == 0 ? "该物品已在购物车中!" : "系统异常,操作失败!");
            pw.write(msg);
        }
    }

    private void findCartServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Integer vipId = (Integer)req.getSession(false).getAttribute("vipId");
        Cart cart = cartService.findCartInfo(vipId);
        req.setAttribute("cart",cart);
        req.getRequestDispatcher("/jsps/vip/showCartInfo.jsp").forward(req,resp);
    }

    private void deleteCartInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Integer vipid = (Integer)req.getSession(false).getAttribute("vipId");
        Integer bookid = Integer.valueOf(req.getParameter("bookid"));
        boolean flag = cartService.deleteCartInfo(vipid,bookid);
        if(flag)
            findCartServlet(req,resp);
        else{
            PrintWriter pw = resp.getWriter();
            pw.write("系统出错...请稍后再试");
        }
    }
}
