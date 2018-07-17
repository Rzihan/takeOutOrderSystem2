package com.topview.www.dao;

import com.topview.www.po.Shopcar;

import java.util.List;

//购物车相关的业务逻辑接口
public interface ShopcarDao {
	
	//获取一个购物车
	public Shopcar getShopcar(Shopcar shopcar);
	
	//改变购物车中食物的数量
	public boolean changeFoodNum(Shopcar shopcar);
	
	//添加食物到购物车
	public boolean addShopcar(Shopcar shopcar);
	
	//根据userId获取List<Shopcar>
	public List<Shopcar> getShopcarListByUserId(int userId);
}
