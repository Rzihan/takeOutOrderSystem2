package com.topview.www.dao;

import com.topview.www.po.OrderAndFoods;
import com.topview.www.po.UserOrder;

import java.util.List;
import java.util.Map;

/**
 * 订单与食物一对多表的业务逻辑接口
 */
public interface OrderAndFoodsDao {
	
	//在订单与食物一对多关系表添加数据
	public int addOrderAndFood(OrderAndFoods oaf);
	
	//根据订单id获取对应的List集合
	public List<OrderAndFoods> findOrderAndFoodByOrderId(int orderId);
	
	//根据订单id集合，获取Map<Integer,List<OrderAndFoods>>
	public Map<Integer,List<OrderAndFoods>> findOrderAndFoods(List<UserOrder> uoList);
	
	//根据sql和id去获取Map<Integer，List<OrderAndFoods>>
	public Map<Integer,List<OrderAndFoods>> getOrderAndFoods(String sql, int id);
}
