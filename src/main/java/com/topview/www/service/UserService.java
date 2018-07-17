package com.topview.www.service;

import com.topview.www.constant.Regex;
import com.topview.www.constant.RoleConstants;
import com.topview.www.constant.pathConstant.AdminPathConstants;
import com.topview.www.constant.pathConstant.StorePathConstants;
import com.topview.www.dao.UserDao;
import com.topview.www.dao.impl.UserDaoImpl;
import com.topview.www.po.User;

import java.util.List;
import java.util.Map;

import com.topview.www.constant.pathConstant.UserPathConstants;
import com.topview.www.util.MyUploadUtil;

import javax.servlet.http.HttpServletRequest;

public class UserService {

    private static final String LOGIN_ID_OR_PASSWORD_ERROR = "手机号或密码错误";
    private static final String USER_PERMISSION_ERROR = "用户权限错误";
    private static final String ILLEGAL_MOBILE_PHONE_NUMBER = "手机号非法";
    private static final String PASSWORDS_SHOULD_NOT_BE_LESS_THAN_SIX = "密码不能少于六位";
    private static final String GET_HEAD_IMAGE = "image";
    private static final String UPDATE_HEAD_IMAGE_SUCCESS = "保存成功";
    private static final String UPDATE_HEAD_IMAGE_FAILURE = "保存失败";
    private static final String UPDATE_HEAD_IMAGE_ERROR = "文件上传失败，请选择JPG,GIF,PNG格式的文件";


    //私有变量
    private static UserDao userDao = null;

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
        userDao = new UserDaoImpl();
    }

    /**
     * 用户注册业务逻辑处理
     * @param u 用户对象,含有(userLoginId,userPassword,userNickname,userEmail)
     * @return  注册成功返回true，否则，返回false
     */
    public boolean signIn(User u) {
        if(userDao.hasId(u)) {
            //存在该id；返回false;
            return false;
        }
        return userDao.signIn(u);
    }

    /**
     * 用户登录业务逻辑处理
     * 先对手机号和密码的格式进行判断,若格式不对,往errorList添加错误信息并返回login.jsp页面的路径
     * 再对用户名和密码进行判断,若不存在,往errorList添加错误信息并返回login.jsp页面的路径
     * 再对用户的权限进行判断,若权限错误,往errorList添加错误信息并返回login.jsp页面的路径
     * 判断用户的角色,返回对应的页面的路径
     * @param u User对象
     * @param roleId 用户权限值
     * @param errorList 用于存放错误信息
     * @return 返回路径(字符串)
     */
    public String userLogin(User u,String roleId,List<String> errorList) {
        // 手机号非空和格式判断
        if(u.getUserLoginId() == null || !u.getUserLoginId().matches(Regex.REGEX_PHONE)) {
            errorList.add(ILLEGAL_MOBILE_PHONE_NUMBER);
        }
        // 密码非空和长度判断
        if(u.getUserPassword() == null || u.getUserPassword().length() < 6) {
            errorList.add(PASSWORDS_SHOULD_NOT_BE_LESS_THAN_SIX);
        }
        //如果不为空，说明存在错误信息，直接返回login.jsp页面
        if(!errorList.isEmpty()) {
            return UserPathConstants.LOGIN_JSP_PATH;
        }
        //用户名或密码错误，存入错误，返回login.jsp页面
        if(!userDao.loginIn(u)) {
            errorList.add(LOGIN_ID_OR_PASSWORD_ERROR);
            return UserPathConstants.LOGIN_JSP_PATH;
        }
        //判断用户权限是否正确
        if(!userDao.hasRole(u, roleId)) {
            //用户权限错误，存入错误，返回login.jsp页面
            errorList.add(USER_PERMISSION_ERROR);
            return UserPathConstants.LOGIN_JSP_PATH;
        }
        //一切流程正常，判断用户角色
        // 普通用户
        int role = Integer.parseInt(roleId);

        if(role == RoleConstants.ROLE_USER) {
            return UserPathConstants.USER_INDEX_JSP_PATH;
        }
        // 店家
        if(role == RoleConstants.ROLE_STORE) {
            return StorePathConstants.STORE_INDEX_JSP;
        }
        // 管理员
        if(role == RoleConstants.ROLE_ADMIN) {
            return AdminPathConstants.ADMIN_INDEX_JSP;
        }
        return null;
    }

    /**
     * 用户更新个人信息
     * @param u 用户对象(userId,userEmail,userNickname)
     * @return 更新成功,返回true;否则返回false
     */
    public boolean updateMyManage(User u) {
        return userDao.updateMyManage(u);
    }

    /**
     * 用户更新头像
     * @param user 传入User对象(userId,userHeadportrait)
     * @param
     * @return 更新成功,返回true,更新失败,返回false
     */
    public String updateUserHeadImg(User user, HttpServletRequest req){
        //返回结果的字符串
        String result = "";
        //定义允许上传的文件类型
        String allowedExt[] = {"JPG","GIF","PNG","jpg","gif","png"};
        //获取web应用在web服务器上的绝对路径，用来保存文件
        String realWebBase = req.getSession().getServletContext().getRealPath("/");
        String savePath = realWebBase + UserPathConstants.USER_HEAD_IMAGE_PATH;
        //文件保存目录下创建临时文件保存目录
        String tempFile = realWebBase + UserPathConstants.TEMP_FILE_PATH;
        //创建文件上传工具类对象，把文件保存路径等作为参数初始化该对象
        MyUploadUtil myUpload = new MyUploadUtil(allowedExt,savePath,tempFile);
        //对象调用upload方法实现上传功能
        myUpload.upload(req);
        Map<String,String> map = myUpload.getTextContent();
        //获取上传表单的其他内容
        if(!map.isEmpty()) {
            user.setUserHeadportrait(map.get(GET_HEAD_IMAGE));
            if(userDao.updateHeadImg(user)){
                result = UPDATE_HEAD_IMAGE_SUCCESS;
            }else {
                result = UPDATE_HEAD_IMAGE_FAILURE;
            }
        }else {
            result = UPDATE_HEAD_IMAGE_ERROR;
        }
        return result;
    }

    /**
     * 传入User对象的引用(含有userId),传入数据库查询
     * 用该引用来接受查询得到的(userNickname,userEmail,userHeadportrait)
     * 返回个人信息的页面路径
     * @param user User对象((含有userId))
     * @return 个人信息的页面路径
     */
    public String getUserManage(User user) {
        userDao.findMyManage(user);
        return UserPathConstants.USER_MANAGE_JSP_PATH;
    }
}
