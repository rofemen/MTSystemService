/**
 * @author:郑日枋
 * @time:2018年2月6日 下午9:41:18
 * @filename:EvaluationServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.EvaluationMapper;
import com.rofe.pojo.Evaluation;
import com.rofe.pojo.Teacher;
import com.rofe.service.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService{
	@Autowired
	EvaluationMapper evaluationMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return evaluationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Evaluation record) {
		return evaluationMapper.insert(record);
	}

	@Override
	public int insertSelective(Evaluation record) {
		return evaluationMapper.insertSelective(record);
	}

	@Override
	public Evaluation selectByPrimaryKey(Long id) {
		return evaluationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Evaluation record) {
		return evaluationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Evaluation record) {
		return evaluationMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<Evaluation> selectByStuId(Long stuId) {
		return evaluationMapper.selectByStuId(stuId);
	}

	@Override
	public ArrayList<Evaluation> getEiInfoByTcNum(PageInfo<Evaluation> pageInfo,Long tcNum,Long ciCode) {
		return evaluationMapper.getEiInfoByTcNum(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),tcNum,ciCode);
	}

	@Override
	public int getEiInfoCountByTcNum(Long tcNum,Long ciCode) {
		return evaluationMapper.getEiInfoCountByTcNum(tcNum,ciCode);
	}

}
