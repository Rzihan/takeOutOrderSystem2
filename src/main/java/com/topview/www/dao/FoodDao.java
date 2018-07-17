package com.topview.www.dao;

import com.topview.www.po.Food;
import com.topview.www.po.OrderAndFoods;
import com.topview.www.po.Page;

import java.util.List;
import java.util.Map;

//对食物的操作接口
public interface FoodDao {
	//添加食物
	public int addFood(Food food);
	
	//改变食物的状态
	public int changeFoodState(Food food);
	
	//店家设置食物库存
	public int updateFoodNum(Food food);
	
	//店家，根据店铺id，获取该店铺所有的食物数据
	public List<Food> findFoodsByStoreId(int storeId);
	
	//用户根据店铺id，获取该店上架状态的食物数据
	public List<Food> getPutawayFoodsByStoreId(int storeId);
	
	//用户根据搜索条件，获取所有已上架的食物
	public List<Food> getPutwayFoodsBySearch(String search, Page<Food> page);

	//根据foodId获取食物信息
	public Food getFoodByFoodId(int foodId);

	//系统管理员，获取所有要审核的食物信息
	public List<Food> findAllFood(Map<Integer, String> map);

	//传入List<OrderAndFoods>，改变食物的销量
	public void changeFoodSales(List<OrderAndFoods> oafList);

	//根据userId获取Map<foodId,Food>
	public Map<Integer,Food> getFoodMapByUserId(int userId);

	//根据sql和id获取Map<foodId,Food>
	public Map<Integer,Food> getFoodMapBySqlAndId(String sql, int id);
}
