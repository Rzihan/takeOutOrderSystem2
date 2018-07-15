package com.topview.www.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pan梓涵
 * 用于所有过滤用户的请求,查看session有没有User对象,如果没有,跳转到登录界面
 * 其中过滤的不包括(.css,.jpg,还有一些登陆,注册页面)
 */
public class CheckFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        if(req.getRequestURI().endsWith(".css")
                ||req.getRequestURI().endsWith(".jpg")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(req.getRequestURI().endsWith("login.jsp")
                || req.getRequestURI().endsWith("LoginServlet")
                || req.getRequestURI().endsWith("signIn.jsp")
                || req.getRequestURI().endsWith("signInFailure.jsp")
                || req.getRequestURI().endsWith("signInSuccess.jsp")
                || req.getRequestURI().endsWith("SignInServlet")
                || req.getRequestURI().endsWith("menu.jsp") ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(null == req.getSession().getAttribute("user")) {
            resp.sendRedirect( req.getContextPath() + "/login.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
