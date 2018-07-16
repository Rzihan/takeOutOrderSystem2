package com.topview.www.dao.impl;

import com.topview.www.constant.StoreConstants;
import com.topview.www.dao.StoreDao;
import com.topview.www.po.Page;
import com.topview.www.po.Store;
import com.topview.www.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreDaoImpl implements StoreDao {

    private static final Integer APPLY_FOR_STORE_FAILURE = -1;
    private static final String APPLY_FOR_STORE_STATEMENT = "mapper.storeMapper.applyForStore";
    private static final String GET_STORE_BY_USER_ID_STATEMENT = "mapper.storeMapper.getStoreByUserId";
    private static final int DELETE_APPLY_FOR_FAILURE = 0;
    private static final String DELETE_APPLY_FOR_STATEMENT = "mapper.storeMapper.deleteApplyFor";
    private static final String UPDATE_APPLY_FOR_STATE_STATEMENT = "mapper.storeMapper.updateApplyForState";
    private static final String DELETE_USER_AND_ROLE = "mapper.storeMapper.deleteUserAndRole";
    private static final int CLOSE_STORE_FAILURE = 0;

    private static final String GET_ALL_TOTAL = "mapper.storeMapper.getAllTotal";
    private static final String SEARCH = "SEARCH";
    private static final String APPLY_FOR_STATE = "APPLY_FOR_STATE";
    private static final String STARTROW = "STARTROW";
    private static final String PAGE_SIZE = "PAGE_SIZE";
    private static final String USER_FIND_STORES_STATEMENT = "mapper.storeMapper.userFindStores";
    private static final String GET_ALL_APPLY_STORE_ALL_TOTAL_STATEMENT = "mapper.storeMapper.getAllApplyStoreAllTotal";
    private static final String FINA_ALL_APPLY_STORE_STATEMENT = "mapper.storeMapper.findAllApplyStoreStatement";
    private static final String STORE_BEING_CHECK = "STORE_BEING_CHECK";
    private static final String STORE_CHECK_SUCCESS = "STORE_CHECK_SUCCESS";
    private static final String ADD_USER_AND_ROLE_STATEMENT = "mapper.storeMapper.addUserAndRole";


    /**
     * 用户提交开店申请的方法
     * @param store (userId,storeName,storeType,storeComments,storeAddress,storeLogo)
     * @return 成功添加,返回true,否则返回false
     */
    public boolean applyForStore(Store store) {
        SqlSession session = MyBatisUtils.getSqlSession();
        int result = session.insert(APPLY_FOR_STORE_STATEMENT, store);
        session.commit();
        session.close();
        return !APPLY_FOR_STORE_FAILURE.equals(result);
    }

    /**
     * 用户根据自己的user_id，获取店铺信息
     * @param userId 用户的id
     * @return 如果查询到了，返回Store对象，否则返回null
     */
    public Store getStoreByUserId(int userId) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Store store = session.selectOne(GET_STORE_BY_USER_ID_STATEMENT, userId);
        session.commit();
        session.commit();
        return store;
    }

    /**
     * 用户根据id和自己的userId删除开店申请失败的记录
     * @param store Store对象(id,userId)
     * @return 删除成功返回true,否则返回false
     */
    public boolean deleteApplyFor(Store store) {
        SqlSession session = MyBatisUtils.getSqlSession();
        int result = session.delete(DELETE_APPLY_FOR_STATEMENT, store);
        session.commit();
        session.close();
        return DELETE_APPLY_FOR_FAILURE != result;
    }

    /**
     * 用户搜索店铺
     * @param search 搜索条件
     * @param page 分页对象
     * @return 返回查询到的结果集
     */
    public List<Store> userFindStore(String search, Page<Store> page) {
        // 查询得到总记录数
        SqlSession session = MyBatisUtils.getSqlSession();
        // 设置查询条件
        Map<String,Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(SEARCH, "%" + search + "%");
        parameterMap.put(APPLY_FOR_STATE, StoreConstants.CHECK_SUCCESS);
        int total = session.selectOne(GET_ALL_TOTAL, parameterMap);
        page.setTotalRecord(total);
        int startRow = (page.getCurrentPage() - 1 ) * page.getPageSize();

        // 增加分页查询的条件
        parameterMap.put(STARTROW, startRow);
        parameterMap.put(PAGE_SIZE, page.getPageSize());
        List<Store> list = session.selectList(USER_FIND_STORES_STATEMENT, parameterMap);
        page.setList(list);

        session.commit();
        session.close();

        return page.getList();
    }

    /**
     * @param storePage 页面对象
     * 系统管理员查看所有开店申请的状况
     */
    public void findAllApplyForStore(Page<Store> storePage) {
        SqlSession session = MyBatisUtils.getSqlSession();
        Map<String,Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(STORE_BEING_CHECK, StoreConstants.BEING_CHECK);
        parameterMap.put(STORE_CHECK_SUCCESS, StoreConstants.CHECK_SUCCESS);
        //查询得到总记录数
        int total = session.selectOne(GET_ALL_APPLY_STORE_ALL_TOTAL_STATEMENT, parameterMap);
        storePage.setTotalRecord(total);
        int startRow = (storePage.getCurrentPage() - 1) * storePage.getPageSize();

        //设置分页查询条件
        parameterMap.put(STARTROW, startRow);
        parameterMap.put(PAGE_SIZE, storePage.getPageSize());
        List<Store> list = session.selectList(FINA_ALL_APPLY_STORE_STATEMENT, parameterMap);
        storePage.setList(list);

        session.commit();
        session.close();
    }

    /**
     * 系统管理员批准用户申请
     * @param store 店铺对象
     * @return 添加成功,返回true,否则返回false
     */
    public boolean approvalApplyFor(Store store) {
        SqlSession session = MyBatisUtils.getSqlSession();
        // 往store插入数据
        store.setApplyForState(StoreConstants.CHECK_SUCCESS);
        session.update(UPDATE_APPLY_FOR_STATE_STATEMENT, store);
        //往用户角色表插入数据
        int result = session.insert(ADD_USER_AND_ROLE_STATEMENT, store);
        session.commit();
        session.close();
        return result != 0;
    }

    /**
     * 系统管理店拒绝用户申请
     * @param store 店铺对象
     */
    public boolean rejectApplyFor(Store store) {
        SqlSession session = MyBatisUtils.getSqlSession();
        store.setApplyForState(StoreConstants.CHECK_FAILURE);
        int result = session.update(UPDATE_APPLY_FOR_STATE_STATEMENT, store);
        session.commit();
        session.close();
        return result != 0;
    }

    /**
     * 系统管理员关闭店铺操作
     * @param store 店铺对象
     * @return 修改数据成功,返回true,否则返回false
     */
    public boolean closeStore(Store store) {
        SqlSession session = MyBatisUtils.getSqlSession();
        //设置用户店铺的状态为审核失败
        store.setApplyForState(StoreConstants.CHECK_FAILURE);
        session.update(UPDATE_APPLY_FOR_STATE_STATEMENT, store);
        //删除用户角色表中的数据
        int result = session.delete(DELETE_USER_AND_ROLE, store);
        session.commit();
        session.close();
        return CLOSE_STORE_FAILURE != result;
    }
}
