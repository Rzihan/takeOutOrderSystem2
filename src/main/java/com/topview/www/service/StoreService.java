package com.topview.www.service;

import com.topview.www.dao.StoreDao;
import com.topview.www.dao.impl.StoreDaoImpl;
import com.topview.www.po.Page;
import com.topview.www.po.Store;

import java.util.List;

/**
 * 店铺业务逻辑处理类
 * 采用单例设计模式
 */
public class StoreService {
	
	//私有变量，仅供本类使用
	private static StoreDao sdao = null;
	
	//定义一个私有类变量来存放单例，私有的目的是指外部无法直接获取这个变量，而要使用提供的公共方法来获取
	private static volatile StoreService instance = null;
	
	//定义私有构造器，表示只在类内部使用，亦指单例的实例只能在单例类内部创建
	private StoreService() {}

	public static StoreService getInstance() {
		if(instance == null) {
			synchronized(StoreService.class) {
				if(instance == null) {
					instance = new StoreService();
				}
			}
		}
		return instance;
	}
	
	//静态代码块,用于初始化成员变量，只初始化一次
	static {
		sdao = new StoreDaoImpl();
	}
	
	/**
	 * 用户申请成为商户
	 * @param store 店铺对象
	 * @return 成功返回true,否则返回false
	 */
	public boolean applyForStore(Store store) {
		return sdao.applyForStore(store);
	}
	
	/**
	 * 用户根据用户ID查询审核进度
	 * @param userId 用户id
	 * @return Store返回店铺对象
	 */
	public Store getStoreByUserId(int userId) {
		return sdao.getStoreByUserId(userId);
	}
	
	/**
	 * 用户根据id和自己的用户id删除审核失败的开店申请记录
	 * @param store 店铺对象
	 * @return 删除成功返回true,否则返回false
	 */
	public boolean deleteApplyFor(Store store) {
		return sdao.deleteApplyFor(store);
	}
	
	/**
	 * 用户搜索店铺
	 * @param search 搜索条件
	 * @param page Page对象,用于分页
	 * @return List<Store>返回搜索到的店铺集合
	 */
	public List<Store> userFindStore(String search, Page<Store> page){
		return sdao.userFindStore(search, page);
	}
	
	/**
	 * 系统管理员查询所有用户申请开店的情况
	 * @param page Page对象,用于分页和获取查询结果
	 */
	public void findAllApplyForStore(Page<Store> page){
		 sdao.findAllApplyForStore(page);
	}
	
	/**
	 * 系统管理批准用户开店
	 * @param store Store对象
	 * @return 修改数据成功,返回true,否则返回false
	 */
	public boolean approvalApply(Store store) {
		return sdao.approvalApplyFor(store);
	}
	
	/**
	 * 系统管理员关闭店铺
	 * @param store Store对象
	 * @return 关闭成功,返回true,否则返回false
	 */
	public boolean colseStore(Store store) {
		return sdao.closeStore(store);
	}
	
	/**
	 * 系统管理员拒绝用户申请
	 * @param store Store对象
	 * @return 修改数据成功,返回true,否则返回false
	 */
	public boolean rejectApplyFor(Store store) {
		return sdao.rejectApplyFor(store);
	}
}
