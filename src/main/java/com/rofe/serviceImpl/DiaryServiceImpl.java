/**
 * @author:郑日枋
 * @time:2018年2月27日 下午9:57:59
 * @filename:DiaryServiceImpl.java
 */
package com.rofe.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofe.common.PageInfo;
import com.rofe.dao.DiaryInfoMapper;
import com.rofe.pojo.DiaryInfo;
import com.rofe.service.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService{
	
	@Autowired
	DiaryInfoMapper diaryInfoMapper;

	@Override
	public int deleteByPrimaryKey(int id) {
		return diaryInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DiaryInfo record) {
		return diaryInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryInfo record) {
		return diaryInfoMapper.insertSelective(record);
	}

	@Override
	public DiaryInfo selectByPrimaryKey(int id) {
		return diaryInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryInfo record) {
		return diaryInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryInfo record) {
		return diaryInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public ArrayList<DiaryInfo> getDiaryInfoByNameOrTime(
			PageInfo<DiaryInfo> pageInfo, int clCode, int stuNum, String diaryTime) {
			
		return diaryInfoMapper.getDiaryInfoByNameOrTime(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),clCode,stuNum,diaryTime);
	}

	@Override
	public int getDiaryInfoByNameOrTimeCount(int clCode, int stuNum,
			String diaryTime) {
		return diaryInfoMapper.getDiaryInfoByNameOrTimeCount(clCode, stuNum, diaryTime);
	}

	@Override
	public int batchDelDiaryInfo(ArrayList<Long> diaryIdList) {
		return diaryInfoMapper.batchDelDiaryInfo(diaryIdList);
	}

	@Override
	public ArrayList<DiaryInfo> getDiaryInfoByStuNum(
			PageInfo<DiaryInfo> pageInfo,int stuNum, String diaryTime) {
		return diaryInfoMapper.getDiaryInfoByStuNum(new RowBounds(pageInfo.getFromRecord(), pageInfo
				.getPageSize()),stuNum,diaryTime);
	}

	@Override
	public int getDiaryInfoByStuNumCount(int stuNum,String diaryTime) {
		return diaryInfoMapper.getDiaryInfoByStuNumCount(stuNum,diaryTime);
	}

}
