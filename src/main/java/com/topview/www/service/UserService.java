package com.topview.www.service;

import com.topview.www.dao.UserDao;
import com.topview.www.dao.impl.UserDaoImpl;
import com.topview.www.po.User;

import java.util.List;

public class UserService {

    private static final String LOGIN_ID_OR_PASSWORD_ERROR = "手机号或密码错误";
    private static final String USER_PERMISSION_ERROR = "用户权限错误";

    //私有变量
    private static UserDao udao = null;

    //定义一个私有类变量来存放单例，私有的目的是指外部无法直接获取这个变量，而要使用提供的公共方法来获取
    private static volatile UserService instance = null;

    //定义私有构造器，表示只在类内部使用，亦指单例的实例只能在单例类内部创建
    private UserService() {}

    /*
     * 定义一个公共的公开的方法来返回该类的实例,双重加锁机制
     * 说明：
     * 第一重判断，如果单例已经存在，那么就不再需要进行同步操作，而是直接返回这个实例，
     * 如果没有创建，才会进入同步块，同步块的目的与之前相同，目的是为了防止有两个调用
     * 同时进行时，导致生成多个实例，有了同步块，每次只能有一个线程调用能访问同步块内
     * 容，当第一个抢到锁的调用获取了实例之后，这个实例就会被创建，之后的所有调用都不
     * 会进入同步块，直接在第一重判断就返回了单例。
     */
    public static UserService getInstance() {
        if(instance == null) {
            synchronized(UserService.class) {
                if(instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    //静态代码块,用于初始化成员变量，只初始化一次
    static {
        udao = new UserDaoImpl();
    }

    /**
     * 用户注册业务逻辑处理
     * @param u 用户对象,含有(userLoginId,userPassword,userNickname,userEmail)
     * @return  注册成功返回true，否则，返回false
     */
    public boolean signIn(User u) {
        if(udao.hasId(u)) {
            //存在该id；返回false;
            return false;
        }
        return udao.signIn(u);
    }

    /**
     * 用户登录业务逻辑处理
     * @param u User对象
     * @param roleId 用户权限值
     * @param errorList 用于存放错误信息
     * @return 如果用户名,密码和用户权限都正确,返回true;否则返回false
     */
    public boolean userLogin(User u,String roleId,List<String> errorList) {
        //用户名或密码错误，存入错误，返回false
        if(!udao.loginIn(u)) {
            errorList.add(LOGIN_ID_OR_PASSWORD_ERROR);
            return false;
        }
        //判断用户权限是否正确
        if(!udao.hasRole(u, roleId)) {
            //用户权限错误，存入错误，返回false
            errorList.add(USER_PERMISSION_ERROR);
            return false;
        }
        //一切流程正常，返回true;
        return true;
    }

    /**
     * 用户更新个人信息
     * @param u 用户对象(userId,userEmail,userNickname)
     * @return 更新成功,返回true;否则返回false
     */
    public boolean updateMyManage(User u) {
        return udao.updateMyManage(u);
    }

    /**
     * 用户更新头像
     * @param u 传入User对象(userId,userHeadportrait)
     * @return 更新成功,返回true,更新失败,返回false
     */
    public boolean updateUserHeadImg(User u){
        return udao.updateHeadImg(u);
    }
}
