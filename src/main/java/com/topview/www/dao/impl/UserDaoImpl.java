package com.topview.www.dao.impl;

import com.topview.www.dao.UserDao;
import com.topview.www.po.User;
import com.topview.www.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final String USER_MAPPER_NAMESPACE = "mapper.userMapper.";
    private static final String SIGN_IN_STATEMENT = USER_MAPPER_NAMESPACE + "signInUser";
    private static final String LOGIN_IN_STATEMENT = USER_MAPPER_NAMESPACE + "loginInUser";
    private static final String HAS_ID_STATEMENT = USER_MAPPER_NAMESPACE + "hasId";
    private static final String HAS_ROLE_STATEMENT = USER_MAPPER_NAMESPACE + "hasRole";
    private static final User FINA_MY_MANAGE_FAILURE = null;
    private static final String FINA_MY_MANAGE_STATEMENT = USER_MAPPER_NAMESPACE + "findMyManage";
    private static final String UPDATE_MY_MANAGE_STATEMENT = USER_MAPPER_NAMESPACE + "updateMyManage";
    private static final String UPDATE_HEAD_IMAGE_STATEMENT = USER_MAPPER_NAMESPACE + "updateHeadImage";

    /**
     * 用户注册方法的实现
     * 将用户的手机号(登录id),密码(password),昵称(nickname)和邮箱(email)插入数据库
     * @param user 一个用户对象,含有(userLoginId,userPassword,userNickName,userEmail)
     * @return 插入成功就返回true,否则返回false
     */
    public boolean signIn(User user) {
        return MyBatisUtils.addData(SIGN_IN_STATEMENT, user);
    }

    /**
     * 用户登录实现,判断是否存在该id，
     * 若存在，为存入的对象设置userId属性，并返回true
     * 若不存在，返回false
     * @param user 一个用户对象,含有(userLoginId,userPassword)
     * @return 存在该id，返回true，否则，返回false
     */
    public boolean loginIn(User user) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Integer userId = session.selectOne(LOGIN_IN_STATEMENT, user);
        session.commit();
        session.close();
        // 如果不是空对象,表明查询成功
        if(userId != null) {
            user.setUserId(userId);
            return true;
        }
        return false;
    }

    /**
     * 查询是否存在该Id
     * @param user 一个用户对象,含有(userLoginId)
     * @return  如果存在该Id，返回true，否则返回false；
     */
    public boolean hasId(User user) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Integer userId = session.selectOne(HAS_ID_STATEMENT, user);
        session.commit();
        session.close();
        return userId != null;
    }

    /**
     * 判断是否存在该角色
     * @param user User对象,含有(userId)
     * @param roleId 用户权限id
     * @return 若查询到相应记录，说明存在，返回true，否则返回false
     */
    public boolean hasRole(User user, String roleId) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Map<String, Integer> hasRoleMap = new HashMap<>();
        hasRoleMap.put("userId", user.getUserId());
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
     * @param user user对象,含有(userId)
     * @return 查询到返回true，否则false
     */
    public boolean findMyManage(User user) {
        SqlSession session = MyBatisUtils.getSqlSession();
        User tempUser = session.selectOne(FINA_MY_MANAGE_STATEMENT, user);
        session.commit();
        session.close();
        if(tempUser != FINA_MY_MANAGE_FAILURE) {
            //tempUser不为空,表示查询到结果
            user.setUserNickname(tempUser.getUserNickname());
            user.setUserEmail(tempUser.getUserEmail());
            user.setUserHeadportrait(tempUser.getUserHeadportrait());
            return true;
        }
        return false;
    }

    /**
     * 修改个人信息
     * @param user 传入user对象,含有(userId,userNickname,userEmail)
     * @return 更新成功，返回true，更新失败，返回false
     */
    public boolean updateMyManage(User user) {
        return MyBatisUtils.updateData(UPDATE_MY_MANAGE_STATEMENT, user);
    }

    /**
     * 修改用户头像
     * @param user 传入User对象(userId,userHeadportrait)
     * @return 更新成功,返回true,更新失败,返回false
     */
    public boolean updateHeadImg(User user) {
        return MyBatisUtils.updateData(UPDATE_HEAD_IMAGE_STATEMENT, user);
    }

}
