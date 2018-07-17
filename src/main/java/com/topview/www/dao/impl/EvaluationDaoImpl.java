package com.topview.www.dao.impl;

import com.topview.www.dao.EvaluationDao;
import com.topview.www.po.Evaluation;
import com.topview.www.po.Page;
import com.topview.www.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationDaoImpl implements EvaluationDao {

    private static final String EVALUATION_MAPPER_NAMESPACE = "mapper.evaluationMapper.";
    private static final String ADD_EVALUATION_STATEMENT = EVALUATION_MAPPER_NAMESPACE + "addEvaluation";
    private static final String GET_EVALUATION_ALL_TOTAL_STATEMENT = EVALUATION_MAPPER_NAMESPACE
            + "getEvaluationAllTotal";
    private static final String GET_EVALUATION_LIST_BY_FOOD_ID_STATEMENT = EVALUATION_MAPPER_NAMESPACE
            + "getEvaluationListByFoodId";
    private static final String START_ROW = "START_ROW";//用于分页查询
    private static final String PAGE_SIZE = "PAGE_SIZE";

    public boolean addEvaluation(Evaluation evaluation) {
        return MyBatisUtils.addData(ADD_EVALUATION_STATEMENT, evaluation);
    }

    public void getEvaluationListByFoodId(int foodId, Page<Evaluation> page) {
        SqlSession session = MyBatisUtils.getSqlSession();
        // 查询所有记录数
        int totalRecord = session.selectOne(GET_EVALUATION_ALL_TOTAL_STATEMENT, foodId);
        page.setTotalRecord(totalRecord);
        int startRow = (page.getCurrentPage() - 1) * page.getPageSize();
        // 设置分页查询条件
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(START_ROW, startRow);
        parameterMap.put(PAGE_SIZE, page.getPageSize());
        parameterMap.put("FOOD_ID", foodId);
        List<Evaluation> list = session.selectList(GET_EVALUATION_LIST_BY_FOOD_ID_STATEMENT, parameterMap);
        page.setList(list);
        session.commit();
        session.close();
    }
}
