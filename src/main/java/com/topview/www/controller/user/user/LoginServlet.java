package com.topview.www.controller.user.user;

import com.topview.www.constant.Regex;
import com.topview.www.constant.RoleConstants;
import com.topview.www.po.User;
import com.topview.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //获取传过来的值
//        String userLoginId = req.getParameter("userLoginId");
//        String userPassword = req.getParameter("userPassword");
//        String roleId = req.getParameter("role");
//        //创建一个errorList的集合，用户存放错误信息
//        List<String> errorList = new ArrayList<>();
//        //手机号非空和格式判断
//        if(userLoginId == null || !userLoginId.matches(Regex.REGEX_PHONE)) {
//            errorList.add("手机号非法!!!");
//        }
//        //密码非空和长度判断
//        if(userPassword == null || userPassword.length() < 6) {
//            errorList.add("密码不能少于六位!!!");
//        }
//        //如果不为空，说明存在错误信息，直接返回login.jsp页面
//        if(!errorList.isEmpty()) {
//            req.setAttribute("errorList", errorList);
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//            return;
//        }
//        //将数据封装成功对象
//        User user = new User(userLoginId,userPassword);
//        //获取业务逻辑处理对象
//        UserService us = UserService.getInstance();
//
//        if(us.userLogin(user, roleId, errorList)) {
//            //登录成功，将user和role存在session中
//            int role = Integer.parseInt(roleId);
//            req.getSession().setAttribute("user", user);
//            req.getSession().setAttribute("role", role);
//
//            //判断用户的角色，转到对应的页面
//            if(RoleConstants.ROLE_USER == role) {
//                //角色是普通用户，跳转到用户首页
//                resp.sendRedirect("user/userIndex.jsp");
//            }else if(RoleConstants.ROLE_STORE == role) {
//                //获取店铺信息并放在session中
//                StoreService ss = StoreService.getInstance();
//                req.getSession().setAttribute("store", ss.getStoreByUserId(user.getUserId()));
//                //跳转到店铺主界面
//                resp.sendRedirect("store/storeIndex.jsp");
//            }else if(RoleConstants.ROLE_ADMIN == role) {
//                //如果是管理员，跳转到管理员首页
//                resp.sendRedirect("admin/adminIndex.jsp");
//            }
//        }else {
//            //登录失败，设置errorList，跳转到login.jsp页面
//            req.setAttribute("errorList", errorList);
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//        }

    }
}
