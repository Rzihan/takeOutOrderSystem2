package com.topview.www.dao.impl;

import com.topview.www.po.Evaluation;
import com.topview.www.po.Page;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvaluationDaoImplTest {

    @Test
    public void addEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setUserId(46);
        evaluation.setFoodId(8);
        evaluation.setStoreId(9);
        evaluation.setFoodEvaluation("工一测试");
        assertEquals(true, new EvaluationDaoImpl().addEvaluation(evaluation));
    }

    @Test
    public void getEvaluationListByFoodId() {
        Page<Evaluation> page = new Page<Evaluation>();
        page.setPageSize(2);
        page.setCurrentPage(1);
        int foodId = 7;
        new EvaluationDaoImpl().getEvaluationListByFoodId(foodId, page);
        System.out.println(page.getList());
    }
}