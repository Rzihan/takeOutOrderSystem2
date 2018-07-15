package com.topview.www.dao;

import com.topview.www.po.User;

public interface UserDao {
	//用户注册
	boolean signIn(User u);
		
	//登录
	boolean loginIn(User u);
		
	//ID是否存在
	boolean hasId(User u);
	
	//是否有该角色
	boolean hasRole(User u, String roleId);
	
	//查询个人信息
	boolean findMyManage(User u);
	
	//修改个人信息
	boolean updateMyManage(User u);
	
	//修改个人头像
	boolean updateHeadImg(User u);
}
