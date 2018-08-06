/**
 * @author:郑日枋
 * @time:2018年2月10日 下午10:56:16
 * @filename:CommentDetailServiceImpl.java
 */
package com.rofe.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.dao.ComentDetailMapper;
import com.rofe.pojo.ComentDetail;
import com.rofe.service.CommentDetailService;

@Service
public class CommentDetailServiceImpl implements CommentDetailService{
	
	@Autowired
	ComentDetailMapper commentDetailMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return commentDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ComentDetail record) {
		return commentDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(ComentDetail record) {
		return commentDetailMapper.insertSelective(record);
	}

	@Override
	public ComentDetail selectByPrimaryKey(Long id) {
		return commentDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ComentDetail record) {
		return commentDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ComentDetail record) {
		return commentDetailMapper.updateByPrimaryKey(record);
	}
	
}
