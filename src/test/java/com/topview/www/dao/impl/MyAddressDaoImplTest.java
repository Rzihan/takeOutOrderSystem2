package com.topview.www.dao.impl;

import com.topview.www.po.MyAddress;
import com.topview.www.po.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyAddressDaoImplTest {

    @Test
    public void addMyAddress() {
        MyAddress myAddress = new MyAddress();
        myAddress.setUserId(31);
        myAddress.setLinkman("大头鬼");
        myAddress.setPhone("15521055863");
        myAddress.setAddress("广东工业大学");
        Assert.assertEquals(true, new MyAddressDaoImpl().addMyAddress(myAddress));
    }

    @Test
    public void deleteMyAddress() {
        new MyAddressDaoImpl().deleteMyAddress(35);
    }

    @Test
    public void findAllMyAddree() {
        User user = new User();
        user.setUserId(31);
        List<MyAddress> list = new MyAddressDaoImpl().findAllMyAddress(user);
        System.out.println(list);
    }

    @Test
    public void findMyAddress() {
        System.out.println(new MyAddressDaoImpl().findMyAddress(32));
    }

    @Test
    public void updateMyAddress() {
        MyAddress myAddress = new MyAddress();
        myAddress.setId(34);
        myAddress.setLinkman("大头鬼");
        myAddress.setAddress("工学一号楼");
        myAddress.setPhone("15911111111");
        assertEquals(true, new MyAddressDaoImpl().updateMyAddress(myAddress));
    }


}