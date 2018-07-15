package com.topview.www.controller.user.user;

import com.topview.www.constant.Regex;
import com.topview.www.po.User;
import com.topview.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pan梓涵
 * 用户注册的servlet
 */
public class SignInServlet extends HttpServlet {

    private static final String ILLEGAL_MOBILE_PHONE_NUMBER = "手机号非法";
    private static final String PASSWORDS_SHOULD_NOT_BE_LESS_THAN_SIX = "密码不能少于六位";
    private static final String NICKNAME_CANNOT_BE_EMPTY = "昵称不能为空";
    private static final String ILLEGAL_EMAIL = "邮箱不合法";
    private static final String ERROR_MASSAGES = "errorList";
    private static final String SIGN_IN_FAILURE_PAGE = "/user/user/signInFailure.jsp";
    private static final String SIGN_IN_SUCCESS_PAGE = "/user/user/signInSuccess.jsp";
    private static final String PHONE_NUMBER_HAS_BEEN_REGISTERED = "手机号已经被注册";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传过来的值
        String userLoginId = req.getParameter("userLoginId");
        String userPassword = req.getParameter("userPassword");
        String userNickname = req.getParameter("userNickname");
        String userEmail = req.getParameter("userEmail");
        //设置一个List，用于存放错误信息，若不为空，返回页面显示错误信息
        List<String> errorList = new ArrayList<String>();
        //进行非空判断，手机号格式判断
        if(userLoginId == null || !userLoginId.matches(Regex.REGEX_PHONE)) {
            errorList.add(ILLEGAL_MOBILE_PHONE_NUMBER);
        }
        //对密码进行非空判断和长度判断
        if(userPassword == null || userPassword.length() < 6) {
            errorList.add(PASSWORDS_SHOULD_NOT_BE_LESS_THAN_SIX);
        }
        //对昵称进行非空判断和长度判断
        if(userNickname == null || userNickname.length() == 0) {
            errorList.add(NICKNAME_CANNOT_BE_EMPTY);
        }
        //进行非空判断，邮箱格式判断
        if(userEmail == null || !userEmail.matches(Regex.REGEX_EMAIL)) {
            errorList.add(ILLEGAL_EMAIL);
        }
        //判断List集合是否为空，若不为空，表示输入的信息有误，返回注册失败页面，将错误信息显示出来
        if(!errorList.isEmpty()) {
            req.setAttribute(ERROR_MASSAGES, errorList);
            req.getRequestDispatcher(SIGN_IN_FAILURE_PAGE).forward(req, resp);
            return;
        }
        //将得到的数据封装成对象
        User user = new User(userLoginId, userPassword, userNickname, userEmail);
        //获取业务逻辑处理对象
        UserService userService = UserService.getInstance();
        if(userService.signIn(user)) {
            //注册成功
            req.getRequestDispatcher(SIGN_IN_SUCCESS_PAGE).forward(req, resp);
        }
        //注册失败
        errorList.add(PHONE_NUMBER_HAS_BEEN_REGISTERED);
        req.setAttribute(ERROR_MASSAGES, errorList);
        req.getRequestDispatcher(SIGN_IN_FAILURE_PAGE).forward(req, resp);
    }
}
