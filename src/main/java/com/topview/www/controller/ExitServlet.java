package com.topview.www.controller;

import com.topview.www.constant.pathConstant.UserPathConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Pan梓涵
 * 安全退出的Servlet
 */
public class ExitServlet extends HttpServlet {

    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String STORE = "store";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session对象
        HttpSession session = req.getSession();
        //清除session中的user，role，和store
        session.removeAttribute(USER);
        session.removeAttribute(ROLE);
        session.removeAttribute(STORE);
        req.getRequestDispatcher(UserPathConstants.LOGIN_JSP_PATH).forward(req, resp);
    }
}
