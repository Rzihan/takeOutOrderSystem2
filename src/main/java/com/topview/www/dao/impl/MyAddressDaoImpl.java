package com.topview.www.dao.impl;

import com.topview.www.dao.MyAddressDao;
import com.topview.www.po.MyAddress;
import com.topview.www.po.User;
import com.topview.www.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MyAddressDaoImpl implements MyAddressDao {

    private static final String MY_ADDRESS_MAPPER_NAMESPACE = "mapper.myAddressMapper.";
    private static final String ADD_MY_ADDRESS_STATEMENT = MY_ADDRESS_MAPPER_NAMESPACE + "addMyAddress";
    private static final String DELETE_MY_ADDRESS_STATEMENT = MY_ADDRESS_MAPPER_NAMESPACE + "deleteMyAddress";
    private static final String FIND_ALL_MY_ADDRESS_STATEMENT = MY_ADDRESS_MAPPER_NAMESPACE + "findAllMyAddress";
    private static final String FIND_MY_ADDRESS_BY_ID_STATEMENT = MY_ADDRESS_MAPPER_NAMESPACE + "findMyAddressById";
    private static final String UPDATE_MY_ADDRESS_STATEMENT = MY_ADDRESS_MAPPER_NAMESPACE + "updateMyAddress";

    public boolean addMyAddress(MyAddress myAddress) {
        return MyBatisUtils.addData(ADD_MY_ADDRESS_STATEMENT, myAddress);
    }

    public boolean updateMyAddress(MyAddress myAddress) {
        return MyBatisUtils.updateData(UPDATE_MY_ADDRESS_STATEMENT, myAddress);
    }

    public boolean deleteMyAddress(int myAddressId) {
        return MyBatisUtils.deteleData(DELETE_MY_ADDRESS_STATEMENT, myAddressId);
    }

    public List<MyAddress> findAllMyAddress(User u) {
        SqlSession session = MyBatisUtils.getSqlSession();
        List<MyAddress> resultList = session.selectList(FIND_ALL_MY_ADDRESS_STATEMENT, u);
        session.commit();
        session.close();
        return resultList;
    }

    public MyAddress findMyAddress(int id) {
        SqlSession session = MyBatisUtils.getSqlSession();
        MyAddress myAddress = session.selectOne(FIND_MY_ADDRESS_BY_ID_STATEMENT, id);
        session.commit();
        session.close();
        return myAddress;
    }

}
