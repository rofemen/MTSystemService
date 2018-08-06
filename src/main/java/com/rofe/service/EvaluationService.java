/**
 * @author:郑日枋
 * @time:2018年2月6日 下午9:40:24
 * @filename:EvaluationService.java
 */
package com.rofe.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.rofe.common.PageInfo;
import com.rofe.pojo.Evaluation;

public interface EvaluationService extends BaseService<Evaluation> {
	 public ArrayList<Evaluation> selectByStuId(Long stuId);
	 public ArrayList<Evaluation> getEiInfoByTcNum(PageInfo<Evaluation> pageInfo,Long tcNum,Long ciCourse);
	 public int getEiInfoCountByTcNum(Long tcNum,Long ciCode);

}
