package com.topview.www.dao;

import com.topview.www.po.Evaluation;
import com.topview.www.po.Page;

/**
 * 有关操作评价表的接口
 */
public interface EvaluationDao {

	/**
	 * 往评价表中插入数据
	 * @param evaluation 数据包括(userId,foodId,storeId,foodEvaluation)
	 * @return 插入成功返回true,否则返回false
	 */
	boolean addEvaluation(Evaluation evaluation);

	/**
	 * 根据foodId，获取List<Evaluation>
	 * @param foodId 食物id
	 * @param page 分页对象
	 */
	void getEvaluationListByFoodId(int foodId, Page<Evaluation> page);
}
