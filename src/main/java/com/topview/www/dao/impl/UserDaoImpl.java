package com.topview.www.dao.impl;

import com.topview.www.dao.UserDao;
import com.topview.www.po.User;
import com.topview.www.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final Integer SIGN_IN_FAILURE = -1;
    private static final String SIGN_IN_STATEMENT = "mapper.userMapper.signInUser";
    private static final String LOGIN_IN_STATEMENT = "mapper.userMapper.loginInUser";
    private static final String HAS_ID_STATEMENT = "mapper.userMapper.hasId";
    private static final String HAS_ROLE_STATEMENT = "mapper.userMapper.hasRole";
    private static final User FINA_MY_MANAGE_FAILURE = null;
    private static final String FINA_MY_MANAGE_STATEMENT = "mapper.userMapper.findMyManage";
    private static final int UPDATE_MY_MANAGE_FAILURE = 0;
    private static final String UPDATE_MY_MANAGE_STATEMENT = "mapper.userMapper.updateMyManage";
    private static final int UPDATE_HEAD_IMAGE_FAILURE = 0;
    private static final String UPDATE_HEAD_IMAGE_STATEMENT = "mapper.userMapper.updateHeadImage";

    /**
     * 用户注册方法的实现
     * 将用户的手机号(登录id),密码(password),昵称(nickname)和邮箱(email)插入数据库
     * @param u 一个用户对象,含有(userLoginId,userPassword,userNickName,userEmail)
     * @return 插入成功就返回true,否则返回false
     */
    public boolean signIn(User u) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Integer result = session.insert(SIGN_IN_STATEMENT, u);
        session.commit();
        session.close();
        return !SIGN_IN_FAILURE.equals(result);
    }

    /**
     * 用户登录实现,判断是否存在该id，
     * 若存在，为存入的对象设置userId属性，并返回true
     * 若不存在，返回false
     * @param u 一个用户对象,含有(userLoginId,userPassword)
     * @return 存在该id，返回true，否则，返回false
     */
    public boolean loginIn(User u) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Integer userId = session.selectOne(LOGIN_IN_STATEMENT, u);
        session.commit();
        session.close();
        // 如果不是空对象,表明查询成功
        if(userId != null) {
            u.setUserId(userId);
            return true;
        }
        return false;
    }

    /**
     * 查询是否存在该Id
     * @param u 一个用户对象,含有(userLoginId)
     * @return  如果存在该Id，返回true，否则返回false；
     */
    public boolean hasId(User u) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Integer userId = session.selectOne(HAS_ID_STATEMENT, u);
        session.commit();
        session.close();
        return userId != null;
    }

    /**
     * 判断是否存在该角色
     * @param u User对象,含有(userId)
     * @param roleId 用户权限id
     * @return 若查询到相应记录，说明存在，返回true，否则返回false
     */
    public boolean hasRole(User u, String roleId) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Map<String, Integer> hasRoleMap = new HashMap<String, Integer>();
        hasRoleMap.put("userId", u.getUserId());
        hasRoleMap.put("roleId", Integer.parseInt(roleId));
        Integer userId = session.selectOne(HAS_ROLE_STATEMENT, hasRoleMap);
        session.commit();
        session.close();
        return userId != null;
    }

    /**
     * 用户查询个人信息
     * 如果查询到,为传入的User设置属性值,userNickname,userEmail,userHeadportrait,并返回true
     * 如果结果集为空,返回false
     * @param u user对象,含有(userId)
     * @return 查询到返回true，否则false
     */
    public boolean findMyManage(User u) {
        SqlSession session = MyBatisUtils.getSqlSession();
        User user = session.selectOne(FINA_MY_MANAGE_STATEMENT, u);
        session.commit();
        session.close();
        if(user != FINA_MY_MANAGE_FAILURE) {
            //user不为空,表示查询到结果
            u.setUserNickname(user.getUserNickname());
            u.setUserEmail(user.getUserEmail());
            u.setUserHeadportrait(user.getUserHeadportrait());
            return true;
        }
        return false;
    }

    /**
     * 修改个人信息
     * @param u 传入user对象,含有(userId,userNickname,userEmail)
     * @return 更新成功，返回true，更新失败，返回false
     */
    public boolean updateMyManage(User u) {
        return updateUser(u, UPDATE_MY_MANAGE_STATEMENT, UPDATE_MY_MANAGE_FAILURE);
    }

    /**
     * 修改用户头像
     * @param u 传入User对象(userId,userHeadportrait)
     * @return 更新成功,返回true,更新失败,返回false
     */
    public boolean updateHeadImg(User u) {
        return updateUser(u, UPDATE_HEAD_IMAGE_STATEMENT, UPDATE_HEAD_IMAGE_FAILURE);
    }

    /**
     * 更新User表的数据
     * @param u User对象
     * @param updateHeadImageStatement 用于寻找要调用的select预计
     * @param updateHeadImageFailure 判断是否更新成功
     * @return 更新成功返回true,否则返回false
     */
    private boolean updateUser(User u, String updateHeadImageStatement, int updateHeadImageFailure) {
        SqlSession session = MyBatisUtils.getSqlSession();
        int result = session.update(updateHeadImageStatement, u);
        session.commit();
        session.close();
        return result != updateHeadImageFailure;
    }
}
