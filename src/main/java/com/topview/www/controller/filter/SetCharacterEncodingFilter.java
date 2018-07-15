package com.topview.www.controller.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author Pan梓涵
 * 设置编码格式
 */
public class SetCharacterEncodingFilter implements Filter {


    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
