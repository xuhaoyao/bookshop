package com.scnu.vip.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scnu.vip.dao.VipDao;
import com.scnu.vip.entity.Vip;
import com.scnu.vip.service.VipService;
import com.scnu.vip.service.impl.VipServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class VipServlet extends HttpServlet {
    private VipService vipService = new VipServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oper = req.getParameter("oper");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if("vipLogin".equals(oper))
            vipLoginServlet(req,resp);
        else if("vipRegister".equals(oper))
            vipRegisterServlet(req,resp);
        else if("vipFindInfo".equals(oper))
            vipFindInfoServlet(req,resp);
        else if("vipUpdateInfo".equals(oper))
            vipUpdateInfoServlet(req,resp);
    }

    private void vipLoginServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int id = vipService.vipLoginService(username,password);
        PrintWriter ps = resp.getWriter();
        String msg = null;
        if(id != 0) {
            HttpSession session = req.getSession();
            session.setAttribute("vipId",id);
            session.setAttribute("top_Title","网上购书小站");
            msg = "登陆成功";
        }
        else
            msg = "用户名或密码不正确";
        ps.write(msg);
    }

    private void vipRegisterServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String username,password,email,msg = null;
        PrintWriter ps = resp.getWriter();
        username = req.getParameter("username");
        boolean flag = vipService.findUserName(username);
        if(flag) {
            msg = "该用户名已存在!";
        }
        else {
            password = req.getParameter("password");
            email = req.getParameter("email");
            Vip vip = new Vip(null, username, password, email);
            int result = vipService.vipRegisterService(vip);
            if (result == 1)
                msg = "注册成功!";
            else
                msg = "该邮箱已被注册";
        }
        ps.write(msg);
    }

    private void vipFindInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        Integer id = (Integer)session.getAttribute("vipId");
        Vip vip = vipService.vipQueryInfoService(id);
        req.setAttribute("vipInfo",vip);
        req.getRequestDispatcher("/jsps/vip/vipQueryInfo.jsp").forward(req,resp);
    }

    private void vipUpdateInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        Vip vip = new Vip();
        vip.setId((int)session.getAttribute("vipId"));
        vip.setUsername(req.getParameter("username"));
        vip.setPassword(req.getParameter("password"));
        vip.setEmail(req.getParameter("email"));
        vip.setAddress(req.getParameter("address"));
        vip.setPhone(req.getParameter("phone"));
        boolean flag = vipService.updateVipInfo(vip);
        String msg = flag ? "修改成功!" : "该邮箱已被注册";
        PrintWriter pw = resp.getWriter();
        pw.write(msg);
    }
}
