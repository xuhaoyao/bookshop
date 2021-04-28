package com.scnu.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scnu.admin.dao.AdminDao;
import com.scnu.admin.entity.Admin;
import com.scnu.admin.service.AdminService;
import com.scnu.admin.service.impl.AdminServiceImpl;
import com.scnu.vip.entity.Vip;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private AdminService adminService = new AdminServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oper = req.getParameter("oper");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if("adminLogin".equals(oper)){
            adminLoginServlet(req,resp);
        }else if("adminQueryVips".equals(oper))
            adminQueryVipsServlet(req,resp);
        else if("adminUpdateVipInfo".equals(oper))
            adminUpdateVipInfoServlet(req,resp);
        else if("adminFindVipInfo".equals(oper))
            adminFindVipInfoServlet(req,resp);
        else if("adminDelVip".equals(oper))
            adminDelVipServlet(req,resp);

    }

    private void adminLoginServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = new Admin(null,username,password);
        boolean flag = adminService.adminLoginService(admin);
        String msg = null;
        PrintWriter pw = resp.getWriter();
        if(flag == true){
            HttpSession session = req.getSession();
            session.setAttribute("adminName",admin.getUsername());
            session.setAttribute("top_Title","网上购书小站管理系统");
            msg = "登陆成功";
        }
        else
            msg = "用户名或密码不正确";
        pw.write(msg);
    }

    private void adminQueryVipsServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List vips = adminService.adminQueryService();
        req.setAttribute("vips",vips);
        req.getRequestDispatcher("/jsps/vipsFind.jsp").forward(req,resp);
    }

    private void adminUpdateVipInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        Vip vip = new Vip(Integer.valueOf(id),username,password,email,address,phone);
        boolean flag = adminService.adminUpdateVipInfoService(vip);
        String update_msg = null;
        if(flag == true)
            update_msg = "会员信息更新成功";
        else
            update_msg = "会员信息更新失败,请查看邮箱是否冲突";
        PrintWriter pw = resp.getWriter();
        pw.write(update_msg);
    }

    private void adminFindVipInfoServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        AdminDao dao = new AdminDao();
        Vip vip = adminService.adminFindVipInfoService(id);
        req.setAttribute("vip",vip);
        req.getRequestDispatcher("/jsps/vipInfo.jsp").forward(req,resp);
    }

    private void adminDelVipServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean flag = adminService.adminDelVipService(Integer.valueOf(id));
        String msg = null;
        if(flag == true)
            msg = "删除成功";
        else
            msg = "删除失败";
        PrintWriter pw = resp.getWriter();
        pw.write(msg);
    }
}
