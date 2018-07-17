package com.topview.www.controller.user.user;

import com.topview.www.po.User;
import com.topview.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Pan梓涵
 * 用户查看个人信息对应的Servlet
 */
public class UserManageServlet extends HttpServlet {

    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从session中获取user对象
        HttpSession session = req.getSession();
        UserService us = UserService.getInstance();
        User user = (User)session.getAttribute(USER);
        String path = us.getUserManage(user);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
