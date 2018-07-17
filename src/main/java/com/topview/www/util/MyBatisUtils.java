package com.topview.www.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtils {

    private static final String MYBATIS_CONF_PATH = "/conf.xml";

    public static SqlSession getSqlSession() {
        // 加载MyBatis的配置文件
        InputStream is = MyBatisUtils.class.getResourceAsStream(MYBATIS_CONF_PATH);
        // 构建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sessionFactory.openSession();
    }

    public static boolean addData(String statement, Object obj){
        SqlSession session = getSqlSession();
        int result = session.insert(statement, obj);
        session.commit();
        session.close();
        return result != 0;
    }

    public static boolean deteleData(String statement, Object obj) {
        SqlSession session = getSqlSession();
        int result = session.delete(statement, obj);
        session.commit();
        session.close();
        return result != 0;
    }

    public static boolean updateData(String statement, Object obj){
        SqlSession session = getSqlSession();
        int result = session.update(statement, obj);
        session.commit();
        session.close();
        return result != 0;
    }



}
