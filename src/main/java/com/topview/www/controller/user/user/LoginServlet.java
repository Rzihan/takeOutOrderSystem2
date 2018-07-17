package com.topview.www.controller.user.user;


import com.topview.www.constant.pathConstant.*;
import com.topview.www.po.User;
import com.topview.www.service.StoreService;
import com.topview.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private static final String ERROR_LIST = "errorList";//错误结果集
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String STORE = "store";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传过来的值
        String userLoginId = req.getParameter("userLoginId");
        String userPassword = req.getParameter("userPassword");
        String roleId = req.getParameter("role");
        //创建一个errorList的集合，用户存放错误信息
        List<String> errorList = new ArrayList<>();
        //将数据封装成功对象
        User user = new User();
        user.setUserLoginId(userLoginId);
        user.setUserPassword(userPassword);
        //获取业务逻辑处理对象
        UserService us = UserService.getInstance();
        String path = us.userLogin(user, roleId, errorList);
        if(UserPathConstants.LOGIN_JSP_PATH.equals(path)) {
            req.setAttribute(ERROR_LIST, errorList);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        if(StorePathConstants.STORE_INDEX_JSP.equals(path)) {
            StoreService ss = StoreService.getInstance();
            req.getSession().setAttribute(STORE, ss.getStoreByUserId(user.getUserId()));
        }
        req.getSession().setAttribute(USER, user);
        req.getSession().setAttribute(ROLE, Integer.parseInt(roleId));
        resp.sendRedirect(path);

    }
}
