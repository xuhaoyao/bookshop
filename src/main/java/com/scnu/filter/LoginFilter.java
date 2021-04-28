package com.scnu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        String oper = req.getParameter("oper");
        if(oper != null && (oper.contains("Login") || oper.contains("Register") || oper.contains("findBooks")))
            filterChain.doFilter(servletRequest,servletResponse);
        else if(uri != null && ("/myWeb/".equals(uri) || uri.contains("login") || uri.contains("register") ||
                uri.contains("index")) || uri.contains("main.jsp") || uri.contains("top.jsp")
            || uri.contains("left.html") || uri.contains("filterMessage.jsp") || uri.contains(".js") || uri.contains(".css") || uri.contains(".jpg"))
            filterChain.doFilter(servletRequest,servletResponse);
        else if(session.getAttribute("adminName") != null || session.getAttribute("vipId") != null)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            req.getRequestDispatcher("/filterMessage.jsp").forward(servletRequest, servletResponse);
    }
}
