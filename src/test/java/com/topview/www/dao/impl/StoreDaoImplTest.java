package com.topview.www.dao.impl;

import com.topview.www.po.Page;
import com.topview.www.po.Store;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StoreDaoImplTest {

    @Test
    public void applyForStore() {
        Store s = new Store();
        s.setUserId(45);
        s.setStoreName("重庆火锅店");
        s.setStoreType("火锅");
        s.setStoreComments("具有本地特色");
        s.setStoreAddress("广州白云区");
        s.setStoreLogo("Test");
        assertEquals(true, new StoreDaoImpl().applyForStore(s));
    }

    @Test
    public void getStoreByUserId() {
        Store s = new StoreDaoImpl().getStoreByUserId(47);
        System.out.println(s);
    }

    @Test
    public void deleteApplyFor() {
        Store s = new Store();
        s.setId(499);
        s.setUserId(27);
        new StoreDaoImpl().deleteApplyFor(s);
    }

    @Test
    public void userFindStore() {
        Page<Store> page = new Page<Store>();
        page.setCurrentPage(1);
        page.setPageSize(10);
        String search = "";
        List<Store> list = new StoreDaoImpl().userFindStore(search, page);
        System.out.println(list);
    }

    @Test
    public void findAllApplyForStore() {
        Page<Store> page = new Page<Store>();
        page.setCurrentPage(1);
        page.setPageSize(1);
        new StoreDaoImpl().findAllApplyForStore(page);
        System.out.println(page.getList());
    }

    @Test
    public void approvalApplyFor() {
        Store s = new Store();
        s.setId(501);
        s.setUserId(32);
        assertEquals(true, new StoreDaoImpl().approvalApplyFor(s));
    }

    @Test
    public void rejectApplyFor() {
        Store s = new Store();
        s.setId(500);
        assertEquals(true, new StoreDaoImpl().rejectApplyFor(s));
    }

    @Test
    public void closeStore() {
        Store s = new Store();
        s.setId(501);
        s.setUserId(32);
        assertEquals(true, new StoreDaoImpl().closeStore(s));
    }
}