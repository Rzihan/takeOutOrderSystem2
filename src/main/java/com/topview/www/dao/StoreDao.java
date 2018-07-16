package com.topview.www.dao;

import com.topview.www.po.Page;
import com.topview.www.po.Store;

import java.util.List;

/**
 * 店铺表的接口
 * @author Pan梓涵
 */
public interface StoreDao {

    // 用户提交开店申请方法
    boolean applyForStore(Store store);

    //根据用户id，获取store信息
    Store getStoreByUserId(int userId);

    //用户删除自己审核失败的开店申请记录
    boolean deleteApplyFor(Store store);

    //用户搜索店铺
    List<Store> userFindStore(String search, Page<Store> page);

    //系统管理员查看所有开店申请状况
    void findAllApplyForStore(Page<Store> page);

    //系统管理员批准用户申请
    boolean approvalApplyFor(Store store);

    //系统管理员拒绝用户申请
    boolean rejectApplyFor(Store store);

    //系统管理员管理商家店铺
    boolean closeStore(Store store);


}
