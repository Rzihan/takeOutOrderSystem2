package com.topview.www.dao;

import com.topview.www.po.MyAddress;
import com.topview.www.po.User;

import java.util.List;

//我的地址的接口
public interface MyAddressDao {


	/**
	 * 添加我的地址
	 * @param myAddress 数据包含(userId,linkman,phone,address)
	 * @return 成功返回true,否则false
	 */
	boolean addMyAddress(MyAddress myAddress);

	/**
	 * 删除我地址
	 * @param myAddressId 用户的id
	 * @return 成功返回true,否则false
	 */
	boolean deleteMyAddress(int myAddressId);

	/**
	 * 查看我的所有地址
	 * @param u 数据包括(userId)
	 * @return 返回查询得到的结果List<MyAddress>，查询不到,返回null
	 */
	List<MyAddress> findAllMyAddress(User u);

	/**
	 * 根据编号获取地址信息
	 * @param id 编号
	 * @return 返回查询得到的结果MyAddress,查询不到,返回null
	 */
	MyAddress findMyAddress(int id);

	/**
	 * 修改我的地址
	 * @param myAddress 数据包括(linkman,phone,address,id)
	 * @return 成功返回true,否则false
	 */
	boolean updateMyAddress(MyAddress myAddress);
}
