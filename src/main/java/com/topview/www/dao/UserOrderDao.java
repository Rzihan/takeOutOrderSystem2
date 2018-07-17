package com.topview.www.dao;

import com.topview.www.po.Page;
import com.topview.www.po.Shopcar;
import com.topview.www.po.UserOrder;

import java.util.List;
import java.util.Map;

public interface UserOrderDao {

	//根据userId和addressId创建一行新的订单信息，并返回新订单的id
	public int createUserOrder(UserOrder uo);
	
	//根据用户id，获取所有的订单
	public List<UserOrder> findAllUserOrderByUserId(int userId, Page<UserOrder> page);

	//根据店铺id，获取所有的订单
	public List<UserOrder> findUserOrdersByStoreId(int storeId);

	//根据店铺id和状态，获取所有的订单
	public List<UserOrder> findUserOrders(int storeId, int status);

	//改变订单的状态
	public int changeUserOrderStatus(UserOrder uo);

	//添加多条订单
	public boolean addUserOrders(Map<Integer, List<Shopcar>> map, int userId, int addressId);

}
